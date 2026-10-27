# App-to-Proxy 桥 — 技术方案与进度

> 目标：借真实 APP 的 AndroidKeystore 签名通道，把外部 API 请求代理给 APP 协议栈执行，绕过服务器对"非 Keystore 公钥"的拒绝。

## 架构

```
调用方 → 本地桥(HTTP :8899 / 文件信号) → [Frida 注入 APP 进程]
                                            → v14.z()/f() (APP 协议栈)
                                            → np3.a() (真实 Keystore ECDSA)
                                            → api.appbms.com 200 ✅
```

## 三种桥实现（tools/）

| 文件 | 通道 | 状态 |
|------|------|------|
| `http_bridge.js` | APP 进程内 ServerSocket :8899 | ✅ 服务起成功（bind IPv6 需 adb forward IPv6 或改 IPv4） |
| `file_bridge.js` | `/data/local/tmp/bridge_{req,res}.json` 轮询 | ✅ polling 起成功（需处理 SELinux + 文件属主） |
| `rpc_bridge.js` | frida message 通道 | ⚠️ frida 17 python API 无 Java bridge，需 CLI |

## 核心卡点与解法

### 卡点 1：suspend 函数需要 Continuation
`v14.z(path, params, ck0)` 第三参是混淆类 `ck0`（= kotlinx ContinuationImpl）。
`Java.registerClass` 生成的类在 frida 注入 loader，与 APP loader 的 `ck0` 不兼容（instanceof 失败）。

**解法（已实现）**：`capture_cont.js` — hook `z()/f()` 捕获 APP 自身传的真实 cont 实例复用。
触发时机：APP 冷启动的 session 注册会调用 → 需在 spawn 模式挂 hook（Pairip 使 attach 窗口 <3s）。

### 卡点 2：Pairip 反调试
- spawn 模式可用：`frida -U -f <pkg> -l hook.js --timeout 90`
- attach 后进程存活 15~60s 不等
- 建议 keepalive（`keepalive.sh`）+ 每次冷启动抢注

### 卡点 3：环境稳定性
- 模拟器 adb 频繁 offline → 脚本全部带 `adb kill-server` 自愈
- streamed install 卡死 → 用 `adb push + pm install /data/local/tmp/`
- 安装后 PM 异步 → 启动前 sleep 8+ 秒

## 下一步（待续会话）

1. spawn 模式 + capture_cont.js → 拿到真实 cont 实例
2. http_bridge 加 cont 复用逻辑 → 端到端代理 `/v1/ai/face-swap`
3. 包装成 new-api 渠道（转发模式）

