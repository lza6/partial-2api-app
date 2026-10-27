Java.perform(function () {
    Java.choose("v14", {
        onMatch: function (inst) {
            var v14 = inst;
            console.log("[DR] v14 ok");
            var factory = Java.ClassFactory.get(v14.getClass().getClassLoader());
            var Ck0 = factory.use("ck0");
            var JSONObject = factory.use("org.json.JSONObject");
            var Cont = Java.registerClass({
                name: "com.dr.C" + Date.now(),
                implements: [Ck0],
                methods: {
                    // ck0 是 kotlinx Continuation 的 wrapper? 先 dump 它的方法再实现 — 保守: 实现全部接口方法
                    $init: function () {},
                    resumeWith: function (r) {
                        var d; try { d = String(r.toString()); } catch (e) { d = "ERR:" + e; }
                        console.log("===RESULT===\n" + d);
                    }
                }
            }).$new();
            var path = "/v1/config-apps/package/com.photoeditor.remakemefaceswapaigenerator";
            var r = v14.z(path, JSONObject.$new(), Cont);
            console.log("[DR] z returned: " + String(r).slice(0, 50));
        },
        onComplete: function () {}
    });
});
