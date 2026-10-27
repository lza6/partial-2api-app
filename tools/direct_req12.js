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
            var clsObj = inst2.getClass();
            var ck0Cls = Ck0.class ? null : null;
            // 用 java.lang.Class.isInstance via wrapper:
            var result = inst2 instanceof Object;
            // 直接试 z:
            try {
                var r = v14Inst.z("/v1/config-apps/package/com.photoeditor.remakemefaceswapaigenerator", factory.use("org.json.JSONObject").$new(), inst2);
                console.log("[T] z ok: " + String(r).slice(0,80));
            } catch (e) {
                console.log("[T] z err: " + String(e).slice(0,200));
                // 尝试通过 handle 传: inst2.$h
                try {
                    var r2 = v14Inst.z("/v1/config-apps/package/com.photoeditor.remakemefaceswapaigenerator", factory.use("org.json.JSONObject").$new(), inst2.$h);
                    console.log("[T] z(h) ok: " + String(r2).slice(0,80));
                } catch (e2) { console.log("[T] z(h) err: " + String(e2).slice(0,150)); }
            }
        },
        onComplete: function () {}
    });
});
