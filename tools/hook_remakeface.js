// RemakeFace V1.3.2 — dump SecureBackendClient 真实签名请求
// 用法: frida -U -f com.photoeditor.remakemefaceswapaigenerator -l hook_remakeface.js
Java.perform(function () {
    // 1) OkHttp 请求拦截: dump 最终 headers (验证签名头)
    try {
        var Request = Java.use("okhttp3.Request");
        var Builder = Java.use("okhttp3.Request$Builder");
        Builder.build.implementation = function () {
            var req = this.build();
            var url = req.url().toString();
            if (url.indexOf("appbms") !== -1 || url.indexOf("masyadi") !== -1) {
                console.log("\n==== REQUEST ====");
                console.log(url);
                console.log(req.method());
                var body = req.body();
                if (body !== null) {
                    try {
                        var buf = Java.use("okio.Buffer").$new();
                        body.writeTo(buf);
                        console.log("BODY: " + buf.readUtf8().slice(0, 1500));
                    } catch (e) { console.log("BODY: <" + e + ">"); }
                }
                var headers = req.headers();
                for (var i = 0; i < headers.size(); i++) {
                    var n = headers.name(i);
                    if (n.indexOf("X-") === 0 || n.indexOf("User-Agent") === 0)
                        console.log("H " + n + ": " + headers.value(i));
                }
            }
            return req;
        };
        console.log("[+] OkHttp Builder.build hooked");
    } catch (e) { console.log("[-] okhttp hook fail: " + e); }

    // 2) np3.a 签名函数: dump canonical 输入
    try {
        var np3 = Java.use("np3");
        np3.a.overload(
            "java.lang.String", "java.lang.String", "java.lang.String",
            "[B", "java.lang.String", "long"
        ).implementation = function (method, path, sid, body, keyVer, ts) {
            console.log("\n==== np3.a SIGN ====");
            console.log("method=" + method + " path=" + path + " keyVer=" + keyVer + " ts=" + ts);
            console.log("bodyLen=" + (body ? body.length : 0));
            if (body && body.length > 0 && body.length < 2000) {
                console.log("bodyStr=" + Java.use("java.lang.String").$new(body, "UTF-8"));
            }
            var result = this.a(method, path, sid, body, keyVer, ts);
            var m = result.a();
            var sz = m.size();
            for (var i = 0; i < sz; i++) {
                var ent = Java.cast(m, Java.use("java.util.Map")).entrySet().toArray()[i];
                console.log("SIG " + ent.getKey() + ": " + ent.getValue());
            }
            return result;
        };
        console.log("[+] np3.a hooked");
    } catch (e) { console.log("[-] np3 hook fail: " + e); }

    // 3) 会话注册 publicKeyPem
    try {
        var v14 = Java.use("v14");
        v14.i.implementation = function (w, inst, ver, pem, att) {
            console.log("\n==== session register ====");
            console.log("publicKeyPem:\n" + pem);
            console.log("installationId=" + inst + " appVersion=" + ver + " attestation=" + att);
            return this.i(w, inst, ver, pem, att);
        };
        console.log("[+] v14.i hooked");
    } catch (e) { console.log("[-] v14.i hook fail: " + e); }
});
