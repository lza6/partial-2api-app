Java.perform(function () {
    Java.choose("v14", {
        onMatch: function (inst) {
            var v14 = inst;
            console.log("[DR] v14 ok");
            var factory = Java.ClassFactory.get(v14.getClass().getClassLoader());
            var Ck0 = factory.use("ck0");
            var JSONObject = factory.use("org.json.JSONObject");
            var EmptyCtx = factory.use("gl0");
            // gl0 是 CoroutineContext 的混淆名? EmptyCoroutineContext 单例在哪 — 先 dump gl0:
            var Gl0 = factory.use("gl0");
            var fields = Gl0.class.value.getDeclaredFields();
            for (var i = 0; i < fields.length; i++) {
                console.log("  gl0 field: " + fields[i].getName());
            }
            var Cont = Java.registerClass({
                name: "com.dr.C" + Date.now(),
                superClass: Ck0,
                methods: {
                    $init: function () {
                        // 调 super(EmptyContext) — 构造签名 (bk0) 与 (bk0, gl0)
                        // bk0 是 completion 链 — 试 (bk0=null)?
                        this.$super.$init(null, EmptyCtx.INSTANCE ? EmptyCtx.INSTANCE.value : null);
                    },
                    getContext: function () { return null; },
                    resumeWith: function (r) {
                        var d; try { d = String(r.toString()); } catch (e) { d = "ERR:" + e; }
                        console.log("===RESULT===\n" + d);
                    }
                }
            }).$new();
            var path = "/v1/config-apps/package/com.photoeditor.remakemefaceswapaigenerator";
            try {
                var r = v14.z(path, JSONObject.$new(), Cont);
                console.log("[DR] z returned: " + String(r).slice(0, 50));
            } catch (e) { console.log("[DR] z err: " + e); }
        },
        onComplete: function () {}
    });
});
