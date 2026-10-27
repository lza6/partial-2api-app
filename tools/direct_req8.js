Java.perform(function () {
    Java.choose("v14", {
        onMatch: function (inst) {
            var v14Inst = inst;
            var loader = v14Inst.getClass().getClassLoader();
            var factory = Java.ClassFactory.get(loader);
            console.log("[DR] v14 ok");
            var Ck0 = factory.use("ck0");
            var JSONObject = factory.use("org.json.JSONObject");
            var ContCls = factory.registerClass({
                name: "com.dr.C" + Date.now(),
                superClass: Ck0,
                methods: {
                    $init: function () { this.$super.$init(null); },
                    getContext: function () { return null; },
                    intercepted: function () { return null; },
                    releaseIntercepted: function () {},
                    resumeWith: function (r) {
                        var d; try { d = String(r.toString()); } catch (e) { d = "ERR:" + e; }
                        console.log("===RESULT===\n" + d);
                    }
                }
            });
            var Cont = ContCls.$new();
            var path = "/v1/config-apps/package/com.photoeditor.remakemefaceswapaigenerator";
            try {
                var r = v14Inst.z(path, JSONObject.$new(), Cont);
                console.log("[DR] z returned: " + String(r).slice(0, 60));
            } catch (e) { console.log("[DR] z err: " + String(e).slice(0, 200)); }
        },
        onComplete: function () {}
    });
});
