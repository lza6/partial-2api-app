Java.perform(function () {
    Java.choose("v14", {
        onMatch: function (inst) {
            var v14Inst = inst;
            var loader = v14Inst.getClass().getClassLoader();
            var factory = Java.ClassFactory.get(loader);
            var Ck0 = factory.use("ck0");
            var Cls = factory.registerClass({
                name: "com.dr.T" + Date.now(),
                superClass: Ck0,
                methods: {
                    $init: function () { this.$super.$init(null); },
                    getContext: function () { return null; },
                    resumeWith: function (r) {}
                }
            });
            var inst2 = Cls.$new();
            console.log("[T] created: " + inst2.getClass().getName());
            console.log("[T] instanceof ck0: " + Ck0.isInstance(inst2));
            try {
                var r = v14Inst.z("/v1/config-apps/package/com.photoeditor.remakemefaceswapaigenerator", factory.use("org.json.JSONObject").$new(), inst2);
                console.log("[T] z ok: " + String(r).slice(0,60));
            } catch (e) { console.log("[T] z err: " + String(e).slice(0,150)); }
        },
        onComplete: function () {}
    });
});
