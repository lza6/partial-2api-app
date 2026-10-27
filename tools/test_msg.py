import frida, sys, time, json, uuid, threading
js = open('rpc_bridge2.js', encoding='utf-8').read()
session = frida.get_usb_device().attach(int(sys.argv[1]))
script = session.create_script(js)
results = {}
done = threading.Event()
def on_msg(msg, data):
    if msg['type'] == 'send':
        p = msg['payload']
        print("[js]", p)
        if p.get('event') == 'result':
            results['r'] = p['data']
            done.set()
script.on('message', on_msg)
script.load()
time.sleep(2)
rid = str(uuid.uuid4())
script.post({'type':'request','payload':{'id':rid,'method':'GET','path':'/v1/config-apps/package/com.photoeditor.remakemefaceswapaigenerator','body':None}})
done.wait(30)
print("RESULT:", results.get('r','TIMEOUT')[:600])
