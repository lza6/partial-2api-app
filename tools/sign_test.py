import json,time,urllib.request,urllib.error,ssl,hashlib,base64,os,uuid,subprocess
ctx=ssl.create_default_context(); ctx.check_hostname=False; ctx.verify_mode=ssl.CERT_NONE
PKG="com.photoeditor.remakemefaceswapaigenerator"
BASE="https://api.appbms.com"
def post(url,obj,extra=None):
    body=json.dumps(obj).encode()
    h={"Content-Type":"application/json","User-Agent":"okhttp/4.12.0"}
    if extra: h.update(extra)
    req=urllib.request.Request(url,data=body,method="POST",headers=h)
    try:
        r=urllib.request.urlopen(req,timeout=15,context=ctx); return r.status,r.read().decode()
    except urllib.error.HTTPError as e: return e.code,e.read().decode()
pub=open('ec.pub').read().strip()
c=post(BASE+"/v1/device-sessions/challenges",{"platform":"android","appIdentifier":PKG,"packageName":PKG,"installationId":str(uuid.uuid4()),"appVersion":"1.3.2"})
cj=json.loads(c[1])
st,s=post(BASE+"/v1/device-sessions",{"platform":"android","appIdentifier":PKG,"packageName":PKG,"challengeId":cj["challengeId"],"publicKeyPem":pub,"installationId":str(uuid.uuid4()),"appVersion":"1.3.2"})
dsid=json.loads(s)["deviceSessionId"]
print("dsid",dsid)
def signed_get(path):
    ts=str(int(time.time()*1000))
    nonce=base64.urlsafe_b64encode(os.urandom(16)).decode().rstrip('=')
    bh=base64.b64encode(hashlib.sha256(b'').digest()).decode()
    open('m.bin','wb').write(("GET\n"+bh).encode())
    subprocess.run('openssl dgst -sha256 -sign ec.key -out s.bin m.bin',shell=True,check=True,capture_output=True)
    sig=base64.b64encode(open('s.bin','rb').read()).decode()
    req=urllib.request.Request(BASE+path,method="GET",
      headers={"User-Agent":"okhttp/4.12.0","X-Device-Session-Id":dsid,"X-Request-Timestamp":ts,
               "X-Request-Nonce":nonce,"X-Body-SHA256":bh,"X-Device-Signature":sig})
    try:
        r=urllib.request.urlopen(req,timeout=15,context=ctx); return r.status,r.read().decode()
    except urllib.error.HTTPError as e: return e.code,e.read().decode()
st,r=signed_get("/v1/config-apps/package/com.photoeditor.remakemefaceswapaigenerator")
print('config-apps:',st,r[:600])
