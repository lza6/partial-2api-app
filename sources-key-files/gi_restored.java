// gi — 还原自 classes4.dex (androguard decompile)
// 混淆原名: gi

// <init> ()V

    public gi()
    {
        this.a = new java.util.concurrent.locks.ReentrantLock();
        java.security.KeyStore v0_3 = java.security.KeyStore.getInstance("AndroidKeyStore");
        v0_3.load(0);
        this.b = v0_3;
        return;
    }


// a ([B)V

    public static void a(byte[] p6)
    {
        java.security.KeyPairGenerator v0_1 = java.security.KeyPairGenerator.getInstance("EC", "AndroidKeyStore");
        android.security.keystore.KeyGenParameterSpec$Builder v1_4 = new java.util.Date((System.currentTimeMillis() - 300000));
        java.util.Date v2_1 = new java.util.Date(1.0602237692195e-311);
        android.security.keystore.KeyGenParameterSpec$Builder v1_2 = new android.security.keystore.KeyGenParameterSpec$Builder("remakeface_full_device_signing_key_v2", 4).setAlgorithmParameterSpec(new java.security.spec.ECGenParameterSpec("secp256r1")).setDigests(new String[] {"SHA-256"})).setCertificateNotBefore(v1_4).setCertificateNotAfter(v2_1).setKeyValidityStart(v1_4).setKeyValidityEnd(v2_1);
        v1_2.getClass();
        if (p6 != null) {
            v1_2.setAttestationChallenge(p6);
        }
        v0_1.initialize(v1_2.build());
        v0_1.generateKeyPair();
        return;
    }


// c (Ljava/lang/String; [B)Ljava/lang/String;

    public static String c(String p9, byte[] p10)
    {
        java.util.ArrayList v5_1;
        StringBuilder v0_1 = new StringBuilder();
        String v1_3 = new StringBuilder("-----BEGIN ");
        v1_3.append(p9);
        v1_3.append("-----");
        v0_1.append(v1_3.toString());
        v0_1.append(10);
        StringBuilder v10_1 = android.util.Base64.encodeToString(p10, 2);
        v10_1.getClass();
        l05.m(64, 64);
        String v3_2 = v10_1.length();
        int v6 = 0;
        if ((v3_2 % 64) != 0) {
            v5_1 = 1;
        } else {
            v5_1 = 0;
        }
        java.util.ArrayList v5_3 = new java.util.ArrayList(((v3_2 / 64) + v5_1));
        int v4_2 = 0;
        while ((v4_2 >= 0) && (v4_2 < v3_2)) {
            int v8;
            int v7 = (v4_2 + 64);
            if ((v7 >= 0) && (v7 <= v3_2)) {
                v8 = v7;
            } else {
                v8 = v3_2;
            }
            int v4_3 = v10_1.subSequence(v4_2, v8);
            v4_3.getClass();
            v5_3.add(v4_3.toString());
            v4_2 = v7;
        }
        StringBuilder v10_2 = v5_3.size();
        while (v6 < v10_2) {
            String v3_3 = v5_3.get(v6);
            v6++;
            v0_1.append(((String) v3_3));
            v0_1.append(10);
        }
        StringBuilder v10_4 = new StringBuilder("-----END ");
        v10_4.append(p9);
        v10_4.append("-----");
        v0_1.append(v10_4.toString());
        return v0_1.toString();
    }


// b ()Ljava/lang/String;

    public final String b()
    {
        java.security.KeyStore v2 = this.b;
        java.util.concurrent.locks.ReentrantLock v3 = this.a;
        v3.lock();
        try {
            if (!v2.containsAlias("remakeface_full_device_signing_key_v2")) {
                gi.a(0);
            }
            try {
                java.security.cert.Certificate v4_0 = v2.getCertificate("remakeface_full_device_signing_key_v2");
            } catch (java.security.cert.Certificate v4_3) {
                v4_0 = new kq3(v4_3);
                Throwable v6_2 = lq3.a(v4_0);
                if (v6_2 != null) {
                    v2.deleteEntry("remakeface_full_device_signing_key_v2");
                    gi.a(0);
                    try {
                        kq3 v1_1 = v2.getCertificate("remakeface_full_device_signing_key_v2");
                    } catch (Throwable v7_2) {
                        v1_1 = new kq3(v7_2);
                        Throwable v7_3 = lq3.a(v1_1);
                        if (v7_3 != null) {
                            r00.h(v7_3, v6_2);
                            throw v7_3;
                        } else {
                            v4_0 = ((java.security.cert.Certificate) v1_1);
                        }
                    }
                    if (v1_1 == null) {
                        Throwable v7_1 = new IllegalStateException;
                        v7_1("Android Keystore did not return a signing certificate");
                        throw v7_1;
                    } else {
                        this.d(v1_1);
                    }
                } else {
                }
                byte[] v0_2 = ((java.security.cert.Certificate) v4_0).getPublicKey().getEncoded();
                v0_2.getClass();
                Throwable v7_6 = gi.c("PUBLIC KEY", v0_2);
                v3.unlock();
                return v7_6;
            }
            if (v4_0 == null) {
                throw new IllegalStateException("Android Keystore did not return a signing certificate");
            } else {
                this.d(v4_0);
            }
        } catch (Throwable v7_4) {
            v3.unlock();
            throw v7_4;
        }
    }


// d (Ljava/security/cert/Certificate;)V

    public final void d(java.security.cert.Certificate p7)
    {
        byte[] v7_1;
        boolean v1_0 = 0;
        if (!(p7 instanceof java.security.cert.X509Certificate)) {
            v7_1 = 0;
        } else {
            v7_1 = ((java.security.cert.X509Certificate) p7);
        }
        if (v7_1 == null) {
            ne.n("Android Keystore signing certificate is not X.509");
            return;
        } else {
            v7_1.checkValidity(new java.util.Date());
            if (v7_1.getNotAfter().getTime() > 1.0602237692195e-311) {
                ne.n("Android Keystore certificate validity exceeds the safe 2037 limit");
                return;
            } else {
                String v6_2 = this.b;
                byte[] v2_1 = v6_2.getKey("remakeface_full_device_signing_key_v2", 0);
                if ((v2_1 instanceof java.security.PrivateKey)) {
                    v1_0 = ((java.security.PrivateKey) v2_1);
                }
                if (!v1_0) {
                    ne.n("Android Keystore private key is missing after generation");
                    return;
                } else {
                    byte[] v2_3 = "remakeface-keystore-v2-validation".getBytes(u80.a);
                    v2_3.getClass();
                    java.security.PublicKey v4_1 = java.security.Signature.getInstance("SHA256withECDSA");
                    v4_1.initSign(v1_0);
                    v4_1.update(v2_3);
                    boolean v1_2 = v4_1.sign();
                    java.security.Signature v3_3 = java.security.Signature.getInstance("SHA256withECDSA");
                    v3_3.initVerify(v7_1.getPublicKey());
                    v3_3.update(v2_3);
                    if (!v3_3.verify(v1_2)) {
                        ne.n("Android Keystore certificate public key does not match the private key");
                        return;
                    } else {
                        String v6_5 = v6_2.getCertificate("remakeface_full_device_signing_key_v2");
                        if (v6_5 == null) {
                            ne.n("Android Keystore signing certificate disappeared after generation");
                            return;
                        } else {
                            if (!java.util.Arrays.equals(v6_5.getPublicKey().getEncoded(), v7_1.getPublicKey().getEncoded())) {
                                ne.n("Android Keystore certificate public key does not match the stored signing key");
                                return;
                            } else {
                                return;
                            }
                        }
                    }
                }
            }
        }
    }


