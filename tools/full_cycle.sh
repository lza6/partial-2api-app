#!/bin/bash
export PATH="/c/Python314/Scripts:/d/android-sdk/platform-tools:$PATH"
cd /d/remakeface132
E="-s emulator-5554"
adb $E install -r -t app.apk 2>&1 | tail -1
sleep 8
adb $E shell monkey -p com.photoeditor.remakemefaceswapaigenerator -c android.intent.category.LAUNCHER 1 >/dev/null 2>&1
sleep 3
PID=""
for i in $(seq 1 80); do PID=$(adb $E shell pidof com.photoeditor.remakemefaceswapaigenerator | tr -d '\r'); [ -n "$PID" ] && break; sleep 0.4; done
echo "PID=$PID"
# 立即挂捕获 hook
timeout 90 frida -U -p $PID -l capture_cont.js -q > capture.log 2>&1 &
FPID=$!
sleep 5
# 触发 splash 请求
adb $E shell input keyevent KEYCODE_HOME; sleep 2
adb $E shell am start -n com.photoeditor.remakemefaceswapaigenerator/.MainActivity >/dev/null 2>&1
sleep 20
echo "=== capture.log ==="
cat capture.log | head -8
kill $FPID 2>/dev/null
