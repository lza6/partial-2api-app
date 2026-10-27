// 启动即执行一次请求并输出
Java.perform(function () {
    Java.choose("v14", {
        onMatch: function (inst) {
            var v14 = inst;
            console.log("[DR] v14 ok");
            var Cont = Java.registerClass({
                name: "com.dr.C" + Date.now(),
                implements: [Java.use("kotlin.coroutines.Continuation")],
                methods: {
                    getContext: function () { return Java.use("kotlinx.coroutines.EmptyCoroutineContext").INSTANCE.value; },
                    resumeWith: function (r) {
                        var d; try { d = String(r.toString()); } catch (e) { d = "ERR:" + e; }
                        console.log("===RESULT===\n" + d);
                    }
                }
            }).$new();
            var path = "/v1/config-apps/package/com.photoeditor.remakemefaceswapaigenerator";
            var r = v14.z(path, Java.use("org.json.JSONObject").$new(), Cont);
            console.log("[DR] z() returned: " + String(r).slice(0, 40));
        },
        onComplete: function () {}
    });
});
