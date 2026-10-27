Java.perform(function () {
    Java.choose("v14", {
        onMatch: function (inst) {
            var v14 = inst;
            console.log("[DR] v14 ok");
            // 关键: Continuation 是 kotlinx 的, 需从正确 loader 拿。
            // 方法: 用 v14 实例的 classLoader:
            var loader = v14.getClass().getClassLoader();
            var factory = Java.ClassFactory.get(loader);
            var ContItf = factory.use("kotlin.coroutines.Continuation");
            var EmptyCtx = factory.use("kotlin.coroutines.EmptyCoroutineContext");
            var Cont = Java.registerClass({
                name: "com.dr.C" + Date.now(),
                implements: [ContItf],
                methods: {
                    getContext: function () { return EmptyCtx.INSTANCE.value; },
                    resumeWith: function (r) {
                        var d; try { d = String(r.toString()); } catch (e) { d = "ERR:" + e; }
                        console.log("===RESULT===\n" + d);
                    }
                }
            }).$new();
            var path = "/v1/config-apps/package/com.photoeditor.remakemefaceswapaigenerator";
            var r = v14.z(path, factory.use("org.json.JSONObject").$new(), Cont);
            console.log("[DR] z returned: " + String(r).slice(0, 40));
        },
        onComplete: function () {}
    });
});
