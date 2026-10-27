# partial-2api-app — RemakeFace AI V1.3.2 PRO 逆向与 2API 交付

> 包名 `com.photoeditor.remakemefaceswapaigenerator` · AOSP 测试证书重打包版 + Pairip 加固 · 双后端架构

## 📊 成果总览

| 模块 | 状态 |
|------|------|
| 模板 API（`app-remakeme.masyadi.com`） | ✅ **完全打通**（无鉴权，demo 可用） |
| 设备会话注册（`api.appbms.com`） | ✅ 脚本复现成功（无 Play Integrity 要求） |
| 请求签名协议 | ✅ 100% 还原（静态+动态双重实锤） |
| AI 端点（`/v1/ai/*`） | ⚠️ 协议已知，待过 AndroidKeystore attestation 关 |

## 🔐 签名协议（SecureBackendClient）

```
设备密钥:  EC P-256 (secp256r1), AndroidKeyStore alias "remakeface_full_device_signing_key_v2"
canonical: METHOD大写 + "\n" + Base64url(SHA256(body))     # flag 11 = URL_SAFE|NO_WRAP|NO_PADDING
签名:      SHA256withECDSA (DER)
注册流程:  POST /v1/device-sessions/challenges → POST /v1/device-sessions (challengeId + publicKeyPem)
会话有效期: 6 小时 (TTL 21599s)
```

请求头（每请求 5 个签名头 + 2 个全局头）：

```
X-Device-Session-Id / X-Request-Timestamp / X-Request-Nonce / X-Body-SHA256 / X-Device-Signature
X-App-Package-Name: com.photoeditor.remakemefaceswapaigenerator
X-App-Version: 1.3.2
```

## 🚀 Demo

```bash
python demo_server.py     # http://127.0.0.1:8902
```

- `/` — 网页 demo（模板库 + 协议展示 + 实时会话注册）
- `/api/categories` `/api/trending` — 模板代理（无鉴权）
- `/api/protocol` — 签名协议展示 + 实时注册演示

## 📂 目录

```
├── README.md                      本文件
├── README_RemakeFace逆向报告.md    完整逆向报告（端点全景/安全发现/401攻坚记录）
├── demo_server.py / demo.html     本地 demo
├── sources-key-files/             关键还原源码
│   ├── v14_SecureBackendClient.java   核心协议类
│   ├── np3_restored.java              签名引擎 (canonical + ECDSA)
│   ├── q74_restored.java              SignedRequestHeaders (5签名头)
│   ├── gi_restored.java               AndroidKeystore 密钥管理
│   ├── y04_restored.java              DeviceSession 数据类
│   └── TemplateApiService.java        模板 Retrofit 接口
├── tools/                         Frida hooks + 签名复现脚本
└── screenshots/                   APP 界面截图
```

## ⚠️ 安全与合规

- 仅用于安全研究与协议学习；上游为第三方生产服务，所有验证均为低频单次请求
- 该 APK 为第三方重打包版（AOSP 测试证书 + 内嵌推广模块），请勿在真实设备安装
