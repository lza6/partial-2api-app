// App-to-Proxy 桥 — RemakeFace V1.3.2
// 原理: 模拟器内起 HTTP 服务(端口 8899), 外部请求进来后用 APP 的 v14 协议栈转发执行
// 关键: APP 用自己的 AndroidKeystore 签名 → 服务器信任
Java.perform(function () {
    var v14Cls = Java.use("v14");
    var pending = {};   // id -> {resolve context}

    // hook v14.n 抓响应回传给桥
    v14Cls.n.implementation = function (req, cont) {
        var url = String(req.a.value);
        var method = String(req.b.value);
        var id = Java.use("java.util.UUID").randomUUID().toString();
        console.log("[BRIDGE] req " + method + " " + url);
        var resp = this.n(req, cont);
        return resp;
    };

    // 主桥逻辑: 在 APP 进程内嵌 NanoHTTPD 轻量服务
    Java.scheduleOnMainThread(function () {
        try {
            var ServerCls = Java.use("fi.iki.elonen.NanoHTTPD");  // APP 可能没这个库
        } catch (e) {
            // 用更底层: java ServerSocket 手写
            var Thread = Java.use("java.lang.Thread");
            var Runnable = Java.registerClass({
                name: "com.bridge.ProxyServer",
                implements: [Java.use("java.lang.Runnable")],
                methods: {
                    run: function () {
                        try {
                            var ssk = Java.use("java.net.ServerSocket");
                            var server = ssk.$new(8899);
                            console.log("[BRIDGE] listening on 8899");
                            while (true) {
                                var sock = server.accept();
                                handleConn(sock);
                            }
                        } catch (e) { console.log("[BRIDGE] server err: " + e); }
                    }
                }
            });
            Thread.$new(Runnable.$new()).start();
            console.log("[BRIDGE] server thread started");
        }
    });

    function handleConn(sock) {
        // 在新线程处理, 读 HTTP 请求, 调 v14.z/f 执行, 写回
        var Thread = Java.use("java.lang.Thread");
        var Runnable = Java.registerClass({
            name: "com.bridge.ConnHandler" + Math.floor(Math.random() * 100000),
            implements: [Java.use("java.lang.Runnable")],
            methods: {
                run: function () {
                    try {
                        var ins = sock.getInputStream();
                        var br = Java.use("java.io.BufferedReader").$new(
                            Java.use("java.io.InputStreamReader").$new(ins, "UTF-8"));
                        var line = br.readLine();
                        if (line === null) return;
                        var parts = String(line).split(" ");
                        var method = parts[0], path = parts[1];
                        // 读 headers 到空行
                        var headers = {};
                        while ((line = br.readLine()) !== null && String(line).length > 0) {
                            var idx = String(line).indexOf(":");
                            if (idx > 0) headers[String(line).slice(0, idx).trim()] = String(line).slice(idx + 1).trim();
                        }
                        // 读 body (Content-Length)
                        var bodyStr = "";
                        var cl = parseInt(headers["Content-Length"] || "0");
                        if (cl > 0) {
                            var buf = Java.array('byte', new Array(cl).fill(0));
                            var n = ins.read(buf, 0, cl);
                            bodyStr = Java.use("java.lang.String").$new(buf, 0, n, "UTF-8");
                        }
                        console.log("[BRIDGE] " + method + " " + path + " bodyLen=" + bodyStr.length);

                        // 用 APP 协议栈执行 — 找 v14 单例并调用
                        var result = execViaV14(method, path, bodyStr);
                        // 写回 HTTP 响应
                        var os = sock.getOutputStream();
                        var respBody = result || '{"error":"bridge exec failed"}';
                        var resp = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\nContent-Length: " +
                            respBody.length + "\r\nConnection: close\r\n\r\n" + respBody;
                        os.write(Java.use("java.lang.String").$new(resp).getBytes("UTF-8"));
                        os.flush();
                        sock.close();
                    } catch (e) {
                        console.log("[BRIDGE] conn err: " + e);
                        try { sock.close(); } catch (e2) {}
                    }
                }
            }
        });
        Thread.$new(Runnable.$new()).start();
    }

    function execViaV14(method, path, bodyStr) {
        // v14.z/f 是 suspend 函数, 需要协程。用 Kotlin coroutines runBlocking 包装:
        try {
            var v14Inst = null;
            Java.choose("v14", { onMatch: function (i) { v14Inst = i; }, onComplete: function () {} });
            if (v14Inst === null) return '{"error":"no v14 instance"}';

            // 构造空 Continuation
            var EmptyCont = Java.registerClass({
                name: "com.bridge.EmptyCont" + Math.floor(Math.random() * 100000),
                implements: [Java.use("kotlin.coroutines.Continuation")],
                methods: {
                    getContext: function () {
                        return Java.use("kotlin.coroutines.EmptyCoroutineContext").INSTANCE.value;
                    },
                    resumeWith: function (r) { console.log("[BRIDGE] resume: " + r); }
                }
            });

            // runBlocking 风格: 直接调用会挂起 — 用线程 + CountDownLatch 等待
            // 简化: 直接调 z(路径, params, cont) — z 内部走完整签名+网络
            var JSONObject = Java.use("org.json.JSONObject");
            var params = JSONObject.$new();  // 空 query
            var r = v14Inst.z(path, params, EmptyCont.$new());
            // z 返回挂起标记或结果 — suspend 函数首次调用会返回 COROUTINE_SUSPENDED
            // 真正结果通过 continuation 回调。等待: 轮询共享变量
            var deadline = Date.now() + 30000;
            while (Date.now() < deadline) {
                Java.perform(function () {});
                try { Thread.sleep(500); } catch (e) {}
                if (global._bridgeResult) break;
            }
            return global._bridgeResult || '{"error":"timeout"}';
        } catch (e) {
            return JSON.stringify({ error: String(e) });
        }
    }
});
