var v14Inst = null;
var REQ = "/data/data/com.photoeditor.remakemefaceswapaigenerator/cache/bridge_req.json";
var RES = "/data/data/com.photoeditor.remakemefaceswapaigenerator/cache/bridge_res.json";

Java.perform(function () {
    Java.choose("v14", {
        onMatch: function (i) { v14Inst = i; },
        onComplete: function () {
            console.log("[FBRIDGE] v14=" + (v14Inst !== null));
            // 清残留
            try { Java.use("java.io.File").$new(RES).delete(); } catch (e) {}
            pollLoop();
        }
    });
});

function readFile(p) {
    try {
        var fis = Java.use("java.io.FileInputStream").$new(p);
        var baos = Java.use("java.io.ByteArrayOutputStream").$new();
        var buf = Java.array('byte', new Array(8192).fill(0));
        var n;
        while ((n = fis.read(buf)) > 0) baos.write(buf, 0, n);
        fis.close();
        return Java.use("java.lang.String").$new(baos.toByteArray(), "UTF-8");
    } catch (e) { return null; }
}

function writeFile(p, content) {
    var fos = Java.use("java.io.FileOutputStream").$new(p, false);
    fos.write(Java.use("java.lang.String").$new(content).getBytes("UTF-8"));
    fos.close();
}

function pollLoop() {
    var PollT = Java.registerClass({
        name: "com.bridge.Poll" + Date.now(),
        implements: [Java.use("java.lang.Runnable")],
        methods: {
            run: function () {
                while (true) {
                    try {
                        var req = readFile(REQ);
                        if (req !== null && String(req).length > 2) {
                            Java.use("java.io.File").$new(REQ).delete();
                            var j = JSON.parse(String(req));
                            console.log("[FBRIDGE] req " + j.method + " " + j.path);
                            doRequest(j.method, j.path, j.body, j.id);
                        }
                    } catch (e) { console.log("[FBRIDGE] poll err: " + e); }
                    try { Java.use("java.lang.Thread").sleep(500); } catch (e) {}
                }
            }
        }
    });
    Java.use("java.lang.Thread").$new(PollT.$new()).start();
    console.log("[FBRIDGE] polling started");
}

function doRequest(method, path, body, id) {
    var Cont = Java.registerClass({
        name: "com.bridge.FC" + Date.now(),
        implements: [Java.use("kotlin.coroutines.Continuation")],
        methods: {
            getContext: function () { return Java.use("kotlinx.coroutines.EmptyCoroutineContext").INSTANCE.value; },
            resumeWith: function (r) {
                var d; try { d = String(r.toString()); } catch (e) { d = "ERR:" + e; }
                try { writeFile(RES, JSON.stringify({ id: id, data: d })); console.log("[FBRIDGE] result written"); }
                catch (e) { console.log("[FBRIDGE] write err " + e); }
            }
        }
    }).$new();
    try {
        var r;
        if (method === "GET") {
            r = v14Inst.z(path, Java.use("org.json.JSONObject").$new(), Cont);
        } else {
            r = v14Inst.f(path, Java.use("org.json.JSONObject").$new(body || "{}"), Cont);
        }
        if (r !== null && String(r) !== "COROUTINE_SUSPENDED") {
            writeFile(RES, JSON.stringify({ id: id, data: String(r) }));
        }
    } catch (e) {
        writeFile(RES, JSON.stringify({ id: id, data: "ERR:" + e }));
    }
}
