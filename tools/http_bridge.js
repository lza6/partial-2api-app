// HTTP 桥: 在 APP 进程内起 ServerSocket:8899, 收 HTTP 请求 → 调 v14 协议栈 → 返回结果
var v14Inst = null;
Java.perform(function () {
    Java.choose("v14", {
        onMatch: function (i) { v14Inst = i; },
        onComplete: function () {
            console.log("[HTTP-BRIDGE] v14=" + (v14Inst !== null));
            startServer();
        }
    });
});

function startServer() {
    var Thread = Java.use("java.lang.Thread");
    var ServerRunnable = Java.registerClass({
        name: "com.bridge.Server",
        implements: [Java.use("java.lang.Runnable")],
        methods: {
            run: function () {
                try {
                    var addr = Java.use("java.net.InetAddress").getByName("0.0.0.0");
                    var ss = Java.use("java.net.ServerSocket").$new(8899, 50, addr);
                    console.log("[HTTP-BRIDGE] listening :8899");
                    while (true) {
                        var sock = ss.accept();
                        handle(sock);
                    }
                } catch (e) { console.log("[HTTP-BRIDGE] srv err: " + e); }
            }
        }
    });
    Thread.$new(ServerRunnable.$new()).start();
}

function handle(sock) {
    var HandlerT = Java.registerClass({
        name: "com.bridge.H" + Date.now(),
        implements: [Java.use("java.lang.Runnable")],
        methods: {
            run: function () {
                try {
                    var ins = sock.getInputStream();
                    var br = Java.use("java.io.BufferedReader").$new(
                        Java.use("java.io.InputStreamReader").$new(ins, "UTF-8"));
                    var first = br.readLine();
                    if (first === null) { sock.close(); return; }
                    var pp = String(first).split(" ");
                    var method = pp[0], rawPath = pp.length > 1 ? pp[1] : "/";
                    var cl = 0;
                    var line;
                    while ((line = br.readLine()) !== null && String(line).length > 0) {
                        var l = String(line).toLowerCase();
                        if (l.indexOf("content-length:") === 0) cl = parseInt(String(line).split(":")[1].trim());
                    }
                    var body = "";
                    if (cl > 0) {
                        var buf = Java.array('byte', new Array(cl).fill(0));
                        var n = ins.read(buf, 0, cl);
                        body = Java.use("java.lang.String").$new(buf, 0, n, "UTF-8");
                    }
                    console.log("[BRIDGE] " + method + " " + rawPath);
                    var path = decodeURIComponent(rawPath.replace(/^\/proxy\//, "/"));
                    var out = doRequest(method, path, body);
                    var os = sock.getOutputStream();
                    var resp = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\nContent-Length: " +
                        out.length + "\r\nConnection: close\r\n\r\n" + out;
                    os.write(Java.use("java.lang.String").$new(resp).getBytes("UTF-8"));
                    os.flush(); sock.close();
                } catch (e) {
                    console.log("[BRIDGE] conn err: " + e);
                    try { sock.close(); } catch (e2) {}
                }
            }
        }
    });
    Java.use("java.lang.Thread").$new(HandlerT.$new()).start();
}

function doRequest(method, path, body) {
    // suspend 调用 — 在专有线程 + latch 等待
    var resultBox = { v: null };
    var latch = Java.use("java.util.concurrent.CountDownLatch").$new(1);
    var Cont = Java.registerClass({
        name: "com.bridge.C" + Date.now(),
        implements: [Java.use("kotlin.coroutines.Continuation")],
        methods: {
            getContext: function () { return Java.use("kotlinx.coroutines.EmptyCoroutineContext").INSTANCE.value; },
            resumeWith: function (r) {
                try { resultBox.v = String(r.toString()); } catch (e) { resultBox.v = "ERR:" + e; }
                latch.countDown();
            }
        }
    }).$new();
    var WorkT = Java.registerClass({
        name: "com.bridge.W" + Date.now(),
        implements: [Java.use("java.lang.Runnable")],
        methods: {
            run: function () {
                try {
                    var r;
                    if (method === "GET") {
                        r = v14Inst.z(path, Java.use("org.json.JSONObject").$new(), Cont);
                    } else {
                        r = v14Inst.f(path, Java.use("org.json.JSONObject").$new(body || "{}"), Cont);
                    }
                    if (r !== null && String(r) !== "COROUTINE_SUSPENDED") {
                        resultBox.v = String(r);
                        latch.countDown();
                    }
                } catch (e) {
                    resultBox.v = "ERR:" + e;
                    latch.countDown();
                }
            }
        }
    });
    Java.use("java.lang.Thread").$new(WorkT.$new()).start();
    // 主线程等待(在 HTTP handler 线程 — 可阻塞)
    var ok = latch.await(25, Java.use("java.util.concurrent.TimeUnit").SECONDS);
    return ok && resultBox.v ? resultBox.v : '{"error":"timeout_or_null"}';
}
