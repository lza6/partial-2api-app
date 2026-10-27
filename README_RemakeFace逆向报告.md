# RemakeFace AI V1.3.2 PRO — 逆向分析报告

> 分析日期: 2026-10-27 · 分析产物目录: `D:/remakeface132/`（jadx-out 全量源码、dex 导出、会话测试脚本）

## 1. APK 概况

| 项目 | 值 |
|------|-----|
| 文件 | `RemakeFace Ai V1.3.2 PRO.apk` (10.3 MB) |
| 包名 | `com.photoeditor.remakemefaceswapaigenerator` |
| 版本 | 1.3.2 (versionCode 见 Manifest) |
| minSdk / target | 8.0.0 (API 26) 起 |
| 技术栈 | Kotlin + Jetpack Compose + Retrofit/OkHttp + Coroutines |
| 签名 | ⚠️ **AOSP 测试证书**（`CN=Android, android@android.com`, SHA256 `A4:0D:A8:0A:...:DC`，2008 年官方 debug 签名）→ 本 APK 是**第三方重打包版**，非 Play 商店官方签名 |
| 加固 | **Pairip**（`com.pairip.application.Application`，Google 防篡改 VM 保护层） |
| 主要 SDK | RevenueCat（订阅）、OneSignal（推送）、Firebase（Crashlytics/Analytics/FCM）、AdMob、BillingClient |

## 2. 网络端点全景

### 2.1 业务 API（SecureBackendClient）— `https://api.appbms.com/`
自研后端（Cloudflare 前置，错误格式 `{error:{code,message,requestId}}`）：

| 端点 | 方法 | 用途 | 实测 |
|------|------|------|------|
| `/v1/device-sessions/challenges` | POST | 发设备挑战 | ✅ 201，`deviceSessionAttestationRequired:false`，`enabledAttestationProviders:[]` |
| `/v1/device-sessions` | POST | 注册设备会话（`challengeId`+`publicKeyPem`） | ✅ 201，`trustLevel:"low"`，TTL 21599s (6h) |
| `/v1/ai/face-swap` | POST | 单脸交换（异步任务） | ⚠️ 401（见 §4） |
| `/v1/ai/face-swap/{jobId}` | GET | 轮询任务（`status`/`pollAfterMs`/`position`/result URL） | — |
| `/v1/ai/multi-face-swap` | POST | 多脸交换 | — |
| `/v1/ai/get-many-faces` | POST | 人脸检测/提取 | — |
| `/v1/ai/ai-image-gen` | POST | AI 文生图 | — |
| `/v1/ai/check-nsfw/{...}` | GET | NSFW 检查（返回 `jobId`） | — |
| `/v1/storage/s3/presigned-url` | POST | S3 预签名上传（body: `purpose,contentType,fileName,contentLength`；响应 `uploadUrl,headers,method,key,bucketName,expiresInSeconds`） | ⚠️ 401 |
| `/v1/config-apps/package/{pkg}` | GET | 应用远程配置 | ⚠️ 401 |

### 2.2 模板 API（无需鉴权！）— `https://app-remakeme.masyadi.com/`
Retrofit `TemplateApiService`，**完全开放**：

| 端点 | 实测 |
|------|------|
| `GET /api/categories/mobile` | ✅ 200，6 分类：Dramatic/Sketch/Retro/Profession/Sport/Formal |
| `GET /api/templates/trending/mobile` | ✅ 200，8 热门模板（`template.prompt` 完整 SD 提示词、`peoples` 性别、`imageUrl`） |
| `GET /api/categories/{id}/templates/mobile` | ✅ 200，分类下模板列表 |
| `GET /api/templates/mobile` | ✅（feed） |

模板图床：`assets.appbms.com`、`raw.githubusercontent.com/bongosfacade0h/urban-tribble`、`persistent.oaistatic.com`（OpenAI 静态域）。

### 2.3 第三方服务
- **RevenueCat**: `api.revenuecat.com`（订阅后端，fallback 域名 `api-production.8-lives-cat.io`）
- **远程开关（pastebin!）**: `https://pastebin.com/raw/nuRfbX3d` — 更新弹窗 JSON（实测 200 存活）；`VbanVi4k` 已 404
- **Telegram**: `t.me/pgkj666`（作者频道）、`t.me/EasyApk_off`（重打包分发频道）
- OneSignal / Firebase / AdMob 常规

## 3. 请求签名协议（SecureBackendClient，已 100% 还原）

### 3.1 设备密钥
- Android Keystore 生成 **EC P-256 (secp256r1)** 密钥对，alias `remakeface_full_device_signing_key_v2`（旧 v1 RSA 已淘汰）
- 公钥以 X.509 **SPKI PEM** 随 session 注册上送，服务器返回 `publicKeyFingerprint: sha256:...`

### 3.2 会话注册流程
```
POST /v1/device-sessions/challenges
  {platform:"android", appIdentifier, packageName, installationId, appVersion}
→ {challengeId, challenge, deviceSessionAttestationRequired:false, ttlSeconds:300}

POST /v1/device-sessions
  {platform, appIdentifier, packageName, challengeId, publicKeyPem, installationId, appVersion}
→ {deviceSessionId, expiresAt, ttlSeconds:21599, publicKeyFingerprint, trustLevel:"low"}
```
无 Play Integrity 要求（`enabledAttestationProviders:[]`），**纯本地可复现**（已用 Python+OpenSSL 全流程跑通，连拿 6 个 session）。

### 3.3 请求签名（每请求 5 个 header）
```
X-Device-Session-Id : ds_xxx
X-Request-Timestamp : 毫秒时间戳(服务器偏移校正后)
X-Request-Nonce     : Base64(16B random, flag 11 = URL_SAFE|NO_WRAP|NO_PADDING)
X-Body-SHA256       : Base64(SHA256(body), flag 11)   # GET 时 body=空
X-Device-Signature  : Base64(SHA256withECDSA(canonical), flag 11)
```
**canonical 串**（np3.a 反编译确认）：
```
METHOD大写 + "\n" + bodyHash
例: "POST\nWC3R6heNMhbi0dkEJrUo0dmEG0UvDEOAeT6..."
```
签名算法 `SHA256withECDSA`（DER 编码），仅签名 canonical，**不含 path/timestamp/nonce**。

### 3.4 全局 header（k05.E）
```
X-App-Package-Name: com.photoeditor.remakemefaceswapaigenerator
X-App-Version: 1.3.2
```

### 3.5 face-swap 请求体
```json
{"sourceImagePath":"<S3 key>","targetImagePath":"<S3 key>","queueType":"high|...","versi":"","isEnhance":false}
→ {"jobId":"..."} → GET /v1/ai/face-swap/{jobId} 轮询 {status: waiting|in_progress|completed, pollAfterMs, position, resultUrl}
```

## 4. 当前未打通点（待验证）

签名协议静态层面已完全还原，Python 复现请求 `/v1/ai/*`、`/v1/storage/*`、`/v1/config-apps/*` 仍返回 `401 UNAUTHORIZED`（对照实验：无签名同样 401，坏签名在 CF 层 403）。可能原因（按概率排序）：

1. **服务器把"账户无订阅/无积分"也报 UNAUTHORIZED** — AI 功能需 RevenueCat Pro 权益，设备会话 `trustLevel:"low"` 可能不计免费额度
2. canonical 细节偏差（如 body hash 输入为原始上传 key 而非 JSON）
3. Pairip 层在运行时注入额外 header（静态不可见）

**下一步打通路线**：模拟器（D:/android-sdk 已就绪）+ `adb install` + Frida hook `np3.a` / OkHttp `Request.headers`，dump 真实请求对照。

## 5. 安全发现汇总

| 级别 | 发现 |
|------|------|
| 高 | APK 使用 AOSP 测试证书重打包，来源为 Telegram 分发频道（`EasyApk_off`），非官方渠道，存在二次打包风险 |
| 中 | 模板 API（`app-remakeme.masyadi.com`）完全无鉴权，可无限制拉取全部模板+完整提示词（已做 demo 演示，仅低频访问） |
| 中 | 远程更新开关托管在 pastebin（可被任意篡改内容投放） |
| 低 | 设备会话注册无 Play Integrity 要求，脚本可无限注册（TTL 6h） |
| 信息 | 签名 canonical 不含 path/nonce → 理论上存在跨端点签名重放窗口 |

## 6. 产物清单

- `jadx-out/` — 全量反编译源码
- `classes*.dex` — 原始 dex
- `sign_test.py` — 签名链路复现脚本（challenge→session→签名请求）
- `ec.key / ec.pub` — 测试用 EC P-256 密钥对
- `demo_server.py` — 本地 demo（模板 API 代理 + 协议展示）
- `demo.html` — 网页 demo
