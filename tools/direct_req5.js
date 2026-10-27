Java.perform(function () {
    Java.choose("v14", {
        onMatch: function (inst) {
            var v14 = inst;
            console.log("[DR] v14 ok");
            var factory = Java.ClassFactory.get(v14.getClass().getClassLoader());
            var JSONObject = factory.use("org.json.JSONObject");
            var path = "/v1/config-apps/package/com.photoeditor.remakemefaceswapaigenerator";
            try {
                var r = v14.z(path, JSONObject.$new(), null);
                console.log("[DR] z sync returned: " + String(r).slice(0, 200));
            } catch (e) { console.log("[DR] z err: " + String(e).slice(0, 200)); }
        },
        onComplete: function () {}
    });
});
