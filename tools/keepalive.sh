#!/bin/bash
export PATH="/c/Python314/Scripts:/d/android-sdk/platform-tools:$PATH"
cd /d/remakeface132
while true; do
  PID=$(adb shell pidof com.photoeditor.remakemefaceswapaigenerator | tr -d '\r')
  if [ -z "$PID" ]; then
    adb shell monkey -p com.photoeditor.remakemefaceswapaigenerator -c android.intent.category.LAUNCHER 1 >/dev/null 2>&1
    sleep 6
    PID=$(adb shell pidof com.photoeditor.remakemefaceswapaigenerator | tr -d '\r')
    echo "$(date +%T) restarted PID=$PID"
  fi
  sleep 3
done
