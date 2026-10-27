# -*- coding: utf-8 -*-
"""
RemakeFace AI V1.3.2 — 本地 demo 服务器
端口 8902。仅代理【无鉴权】的模板 API + 展示签名协议，低频访问上游。
启动: python demo_server.py
"""
import json, ssl, urllib.request, hashlib, base64, os, uuid, subprocess
from http.server import HTTPServer, BaseHTTPRequestHandler

TPL_BASE = "https://app-remakeme.masyadi.com"
API_BASE = "https://api.appbms.com"
PKG = "com.photoeditor.remakemefaceswapaigenerator"
CTX = ssl.create_default_context(); CTX.check_hostname = False; CTX.verify_mode = ssl.CERT_NONE

def fetch(url):
    req = urllib.request.Request(url, headers={"Accept": "application/json", "User-Agent": "Mozilla/5.0"})
    with urllib.request.urlopen(req, timeout=15, context=CTX) as r:
        return json.loads(r.read().decode())

def b64url(b): return base64.urlsafe_b64encode(b).decode().rstrip("=")

def register_device_session():
    """复现 SecureBackendClient 会话注册（challenge → session）"""
    with open("ec.pub") as f: pub = f.read().strip()
    def post(path, obj):
        body = json.dumps(obj).encode()
        req = urllib.request.Request(API_BASE + path, data=body, method="POST", headers={
            "Content-Type": "application/json", "User-Agent": "okhttp/4.12.0",
            "X-App-Package-Name": PKG, "X-App-Version": "1.3.2"})
        try:
            with urllib.request.urlopen(req, timeout=15, context=CTX) as r:
                return r.status, json.loads(r.read().decode())
        except urllib.error.HTTPError as e:
            return e.code, e.read().decode()[:200]
    st, cj = post("/v1/device-sessions/challenges", {
        "platform": "android", "appIdentifier": PKG, "packageName": PKG,
        "installationId": str(uuid.uuid4()), "appVersion": "1.3.2"})
    if st != 201: return {"error": "challenge failed", "detail": cj}
    st, sess = post("/v1/device-sessions", {
        "platform": "android", "appIdentifier": PKG, "packageName": PKG,
        "challengeId": cj["challengeId"], "publicKeyPem": pub,
        "installationId": str(uuid.uuid4()), "appVersion": "1.3.2"})
    return {"status": st, "session": sess}

class Handler(BaseHTTPRequestHandler):
    def log_message(self, *a): pass

    def _send(self, code, data, ctype="application/json; charset=utf-8"):
        body = json.dumps(data, ensure_ascii=False).encode() if not isinstance(data, bytes) else data
        self.send_response(code)
        self.send_header("Content-Type", ctype)
        self.send_header("Content-Length", str(len(body)))
        self.end_headers()
        self.wfile.write(body)

    def do_GET(self):
        if self.path in ("/", "/index.html"):
            with open("demo.html", "rb") as f: return self._send(200, f.read(), "text/html; charset=utf-8")
        if self.path == "/api/categories":
            return self._send(200, fetch(TPL_BASE + "/api/categories/mobile"))
        if self.path == "/api/trending":
            return self._send(200, fetch(TPL_BASE + "/api/templates/trending/mobile"))
        if self.path.startswith("/api/category/"):
            cid = self.path.split("/")[3]
            return self._send(200, fetch(f"{TPL_BASE}/api/categories/{cid}/templates/mobile"))
        if self.path == "/api/protocol":
            """展示还原的签名协议 + 实时会话注册演示"""
            sess = register_device_session()
            return self._send(200, {
                "signature_protocol": {
                    "device_key": "EC P-256 (secp256r1), AndroidKeyStore alias remakeface_full_device_signing_key_v2",
                    "canonical": 'METHOD_UPPERCASE + "\\n" + Base64url(SHA256(body))',
                    "algorithm": "SHA256withECDSA (DER)",
                    "headers": {
                        "X-Device-Session-Id": "ds_...",
                        "X-Request-Timestamp": "<ms>",
                        "X-Request-Nonce": "Base64url(16B random)",
                        "X-Body-SHA256": "Base64url(SHA256(body))",
                        "X-Device-Signature": "Base64url(SHA256withECDSA(canonical))",
                    },
                    "global_headers": {"X-App-Package-Name": PKG, "X-App-Version": "1.3.2"},
                    "session_flow": ["POST /v1/device-sessions/challenges", "POST /v1/device-sessions (challengeId + publicKeyPem)"],
                },
                "live_session_demo": sess,
            })
        if self.path == "/api/health":
            return self._send(200, {"ok": True, "name": "RemakeFace demo", "port": 8902})
        self._send(404, {"error": "not found"})

if __name__ == "__main__":
    print("RemakeFace demo → http://127.0.0.1:8902")
    HTTPServer(("127.0.0.1", 8902), Handler).serve_forever()
