var captured = null;
Java.perform(function () {
    Java.choose("v14", {
        onMatch: function (inst) {
            var factory = Java.ClassFactory.get(inst.getClass().getClassLoader());
            var v14 = factory.use("v14");
            v14.z.implementation = function (path, params, cont) {
                if (cont !== null && captured === null) {
                    captured = cont;
                    console.log("[CAP] z cont: " + cont.getClass().getName());
                }
                return this.z(path, params, cont);
            };
            v14.f.implementation = function (path, json, cont) {
                if (cont !== null && captured === null) {
                    captured = cont;
                    console.log("[CAP] f cont captured");
                }
                return this.f(path, json, cont);
            };
            console.log("[CAP] armed");
        },
        onComplete: function () {}
    });
});
