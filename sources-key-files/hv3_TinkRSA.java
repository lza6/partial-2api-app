package defpackage;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-7d7e7eba03e2a3bf4e7c82ff0492649f5c831daa109059b39d92547825ffc67c */
/* JADX INFO: loaded from: classes.dex */
public final class hv3 implements bk3 {
    public static final byte[] f = new byte[0];
    public static final byte[] g = {0};
    public final RSAPublicKey a;
    public final String b;
    public final byte[] c;
    public final byte[] d;
    public final Provider e;

    public hv3(RSAPublicKey rSAPublicKey, tu3 tu3Var, byte[] bArr, byte[] bArr2, Provider provider) throws GeneralSecurityException {
        if (!f62.e(2)) {
            zj5.i("Can not use RSA-PKCS1.5 in FIPS-mode, as BoringCrypto module is not available.");
            throw null;
        }
        d15.b(rSAPublicKey.getModulus().bitLength());
        d15.c(rSAPublicKey.getPublicExponent());
        this.a = rSAPublicKey;
        this.b = b(tu3Var);
        this.c = bArr;
        this.d = bArr2;
        this.e = provider;
    }

    public static hv3 a(dv3 dv3Var, Provider provider) throws NoSuchAlgorithmException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA", provider);
        BigInteger bigInteger = dv3Var.b;
        vu3 vu3Var = dv3Var.a;
        return new hv3((RSAPublicKey) keyFactory.generatePublic(new RSAPublicKeySpec(bigInteger, vu3Var.b)), vu3Var.d, dv3Var.c.b(), vu3Var.c.equals(uu3.d) ? g : f, provider);
    }

    public static String b(tu3 tu3Var) throws GeneralSecurityException {
        if (tu3Var == tu3.b) {
            return "SHA256withRSA";
        }
        if (tu3Var == tu3.c) {
            return "SHA384withRSA";
        }
        if (tu3Var == tu3.d) {
            return "SHA512withRSA";
        }
        zj5.i("unknown hash type");
        return null;
    }

    @Override // defpackage.bk3
    public final void verify(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        boolean zVerify;
        byte[] bArr3 = this.c;
        if (!s05.b(bArr3, bArr)) {
            zj5.i("Invalid signature (output prefix mismatch)");
            return;
        }
        Signature signature = Signature.getInstance(this.b, this.e);
        signature.initVerify(this.a);
        signature.update(bArr2);
        byte[] bArr4 = this.d;
        if (bArr4.length > 0) {
            signature.update(bArr4);
        }
        try {
            zVerify = signature.verify(Arrays.copyOfRange(bArr, bArr3.length, bArr.length));
        } catch (RuntimeException unused) {
            zVerify = false;
        }
        if (zVerify) {
            return;
        }
        zj5.i("Invalid signature");
    }
}
