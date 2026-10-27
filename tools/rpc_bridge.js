var v14Inst = null;
var _result = null, _done = false;

Java.perform(function () {
    Java.choose("v14", {
        onMatch: function (i) { v14Inst = i; },
        onComplete: function () { console.log("[RPC] v14: " + (v14Inst ? "OK" : "NOT FOUND")); }
    });
});

rpc.exports = {
    ping: function () { return "pong"; },
    hasV14: function () { return v14Inst !== null; },
    request: function (method, path, bodyJson) {
        return new Promise(function (resolve) {
            Java.perform(function () {
                try {
                    if (v14Inst === null) { resolve("ERR:no_v14"); return; }
                    _done = false; _result = null;
                    var Cont = Java.registerClass({
                        name: "com.rpc.C" + Date.now(),
                        implements: [Java.use("kotlin.coroutines.Continuation")],
                        methods: {
                            getContext: function () {
                                return Java.use("kotlinx.coroutines.EmptyCoroutineContext").INSTANCE.value;
                            },
                            resumeWith: function (r) {
                                try { _result = String(r.toString()); } catch (e) { _result = "ERR:" + e; }
                                _done = true;
                            }
                        }
                    }).$new();
                    var r;
                    if (method === "GET") {
                        r = v14Inst.z(path, Java.use("org.json.JSONObject").$new(), Cont);
                    } else {
                        r = v14Inst.f(path, Java.use("org.json.JSONObject").$new(bodyJson || "{}"), Cont);
                    }
                    if (r !== null && String(r) !== "COROUTINE_SUSPENDED") { resolve(String(r)); return; }
                    // 异步轮询 — 不阻塞 frida JS 线程
                    var start = Date.now();
                    var iv = setInterval(function () {
                        if (_done) { clearInterval(iv); resolve(_result || "null"); }
                        else if (Date.now() - start > 25000) { clearInterval(iv); resolve("ERR:timeout"); }
                    }, 200);
                } catch (e) { resolve("ERR:" + e); }
            });
        });
    }
};
