// np3 — 还原自 classes4.dex (androguard decompile)
// 混淆原名: np3

// <init> (Lgi;)V

    public np3(gi p1)
    {
        p1.getClass();
        this.a = p1;
        this.b = new java.security.SecureRandom();
        return;
    }


// a (Ljava/lang/String; Ljava/lang/String; Ljava/lang/String; [B Ljava/lang/String; J)Lq74;

    public final q74 a(String p17, String p18, String p19, byte[] p20, String p21, long p22)
    {
        p18.getClass();
        p19.getClass();
        String v2_4 = String.valueOf(p22);
        String v3_3 = new byte[16];
        this.b.nextBytes(v3_3);
        String v3_0 = android.util.Base64.encodeToString(v3_3, 11);
        v3_0.getClass();
        String v4_3 = java.security.MessageDigest.getInstance("SHA-256").digest(p20);
        v4_3.getClass();
        String v4_4 = android.util.Base64.encodeToString(v4_3, 11);
        v4_4.getClass();
        v2_4.getClass();
        String v5_3 = p17.toUpperCase(java.util.Locale.ROOT);
        v5_3.getClass();
        String v7 = v4_4;
        String v4_5 = v2_4;
        String v3_1 = v3_0;
        String v2_5 = ab0.u0(((Iterable) mh5.A(new String[] {v5_3, v7}))), "\n", 0, 0, 0, 0, 62);
        IllegalStateException v0_6 = this.a;
        boolean v6_1 = v0_6.b;
        java.util.concurrent.locks.ReentrantLock v9_2 = v0_6.a;
        v9_2.lock();
        IllegalStateException v0_7 = "remakeface_full_device_signing_key_v2";
        java.security.PrivateKey v12_1 = 0;
        if (!p21.equals("v1")) {
            if (!p21.equals("v2")) {
                if (!v6_1.containsAlias("remakeface_full_device_signing_key_v2")) {
                    if (!v6_1.containsAlias("remakeface_full_device_signing_key_v1")) {
                        v0_7 = 0;
                    } else {
                        v0_7 = "remakeface_full_device_signing_key_v1";
                    }
                } else {
                }
            } else {
                if (!v6_1.containsAlias("remakeface_full_device_signing_key_v2")) {
                } else {
                }
            }
        } else {
            if (!v6_1.containsAlias("remakeface_full_device_signing_key_v1")) {
            }
        }
        if (v0_7 == null) {
            throw new IllegalStateException("Device private key is missing");
        } else {
            IllegalStateException v0_12 = v6_1.getKey(v0_7, 0);
            if ((v0_12 instanceof java.security.PrivateKey)) {
                v12_1 = ((java.security.PrivateKey) v0_12);
            }
            if (v12_1 == null) {
                throw new IllegalStateException("Device private key is missing");
            } else {
                IllegalStateException v0_1 = java.security.Signature.getInstance("SHA256withECDSA");
                v0_1.initSign(v12_1);
                String v1_2 = v2_5.getBytes(u80.a);
                v1_2.getClass();
                v0_1.update(v1_2);
                IllegalStateException v0_2 = v0_1.sign();
                v0_2.getClass();
                IllegalStateException v0_3 = android.util.Base64.encodeToString(v0_2, 11);
                v0_3.getClass();
                v9_2.unlock();
                return new q74(p19, v4_5, v3_1, v7, v0_3);
            }
        }
    }


