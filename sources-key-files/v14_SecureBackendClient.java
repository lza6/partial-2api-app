package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkRequest;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.tasks.Task;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: r8-map-id-7d7e7eba03e2a3bf4e7c82ff0492649f5c831daa109059b39d92547825ffc67c */
/* JADX INFO: loaded from: classes4.dex */
public final class v14 {
    public static final zv2 n = new zv2();
    public final Context a;
    public final SharedPreferences b;
    public final m23 c;
    public final m23 d;
    public final gb2 e;
    public final gb2 f;
    public final gb2 g;
    public final zv2 h;
    public x04 i;
    public final oo2 j;
    public final String k;
    public long l;
    public long m;

    public v14(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.a = applicationContext;
        final int i = 0;
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("remakeface_full_secure_backend_client", 0);
        this.b = sharedPreferences;
        l23 l23Var = new l23();
        l23Var.h = false;
        l23Var.i = false;
        l23Var.f = false;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        l23Var.b(30L, timeUnit);
        l23Var.x = t05.b(60L, timeUnit);
        timeUnit.getClass();
        l23Var.y = t05.b(60L, timeUnit);
        m23 m23Var = new m23(l23Var);
        this.c = m23Var;
        l23 l23VarB = m23Var.b();
        l23VarB.w = t05.b(30L, timeUnit);
        timeUnit.getClass();
        l23VarB.y = t05.b(120L, timeUnit);
        l23VarB.x = t05.b(120L, timeUnit);
        l23VarB.v = t05.b(180L, timeUnit);
        this.d = new m23(l23VarB);
        e63 e63Var = new e63(7);
        te2 te2Var = te2.a;
        this.e = h62.u(te2Var, e63Var);
        this.f = h62.u(te2Var, new ji1(this) { // from class: v04
            public final /* synthetic */ v14 b;

            {
                this.b = this;
            }

            @Override // defpackage.ji1
            public final Object invoke() {
                xa5 xa5Var;
                int i2 = i;
                v14 v14Var = this.b;
                switch (i2) {
                    case 0:
                        return new np3(v14Var.o());
                    default:
                        Context context2 = v14Var.a;
                        synchronized (hc5.class) {
                            try {
                                if (hc5.a == null) {
                                    Context applicationContext2 = context2.getApplicationContext();
                                    if (applicationContext2 != null) {
                                        context2 = applicationContext2;
                                    }
                                    hc5.a = new xa5(context2);
                                }
                                xa5Var = hc5.a;
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        jc5 jc5Var = (jc5) xa5Var.a.a();
                        jc5Var.getClass();
                        return jc5Var;
                }
            }
        });
        final int i2 = 1;
        this.g = h62.u(te2Var, new ji1(this) { // from class: v04
            public final /* synthetic */ v14 b;

            {
                this.b = this;
            }

            @Override // defpackage.ji1
            public final Object invoke() {
                xa5 xa5Var;
                int i3 = i2;
                v14 v14Var = this.b;
                switch (i3) {
                    case 0:
                        return new np3(v14Var.o());
                    default:
                        Context context2 = v14Var.a;
                        synchronized (hc5.class) {
                            try {
                                if (hc5.a == null) {
                                    Context applicationContext2 = context2.getApplicationContext();
                                    if (applicationContext2 != null) {
                                        context2 = applicationContext2;
                                    }
                                    hc5.a = new xa5(context2);
                                }
                                xa5Var = hc5.a;
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        jc5 jc5Var = (jc5) xa5Var.a.a();
                        jc5Var.getClass();
                        return jc5Var;
                }
            }
        });
        this.h = new zv2();
        Pattern pattern = oo2.d;
        this.j = m42.x("application/json; charset=utf-8");
        this.k = qg4.K0("https://api.appbms.com/", '/');
        this.l = sharedPreferences.getLong("server_time_offset_ms", 0L);
        this.m = sharedPreferences.getLong("server_time_synced_at_ms", 0L);
    }

    public static final lo a(v14 v14Var, String str, String str2) {
        v14Var.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = jCurrentTimeMillis - v14Var.m;
        Long lValueOf = Long.valueOf(v14Var.l);
        if (v14Var.m <= 0 || 0 > j || j >= 300001) {
            lValueOf = null;
        }
        return new lo(str, str2, jCurrentTimeMillis, lValueOf);
    }

    /* JADX WARN: Code duplicated, block: B:101:0x016a  */
    /* JADX WARN: Code duplicated, block: B:108:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:112:0x01c8  */
    /* JADX WARN: Code duplicated, block: B:119:0x01e2  */
    /* JADX WARN: Code duplicated, block: B:125:0x0201  */
    /* JADX WARN: Code duplicated, block: B:151:0x00df A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:158:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:159:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:160:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:161:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:162:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:48:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:51:0x00da A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:52:0x00db  */
    /* JADX WARN: Code duplicated, block: B:57:0x00f4 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:62:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:70:0x0118  */
    /* JADX WARN: Code duplicated, block: B:71:0x011c  */
    /* JADX WARN: Code duplicated, block: B:73:0x011f  */
    /* JADX WARN: Code duplicated, block: B:74:0x0128  */
    /* JADX WARN: Code duplicated, block: B:76:0x012b  */
    /* JADX WARN: Code duplicated, block: B:77:0x012f  */
    /* JADX WARN: Code duplicated, block: B:79:0x0132  */
    /* JADX WARN: Code duplicated, block: B:80:0x0135  */
    /* JADX WARN: Code duplicated, block: B:83:0x013a  */
    /* JADX WARN: Code duplicated, block: B:84:0x013e  */
    /* JADX WARN: Code duplicated, block: B:86:0x0141  */
    /* JADX WARN: Code duplicated, block: B:87:0x0148  */
    /* JADX WARN: Code duplicated, block: B:89:0x014b  */
    /* JADX WARN: Code duplicated, block: B:8:0x0018  */
    /* JADX WARN: Code duplicated, block: B:90:0x014e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:91:0x0150  */
    /* JADX WARN: Code duplicated, block: B:92:0x0153 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:93:0x0155  */
    /* JADX WARN: Code duplicated, block: B:94:0x0158  */
    /* JADX WARN: Code duplicated, block: B:97:0x0164  */
    /* JADX WARN: Code duplicated, block: B:99:0x0167  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v0, types: [v14] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r2v27, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v32 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.Throwable] */
    public static final Object b(v14 v14Var, ck0 ck0Var) throws Exception {
        z04 z04Var;
        String string;
        String str;
        String str2;
        w04 w04Var;
        Long l;
        String str3;
        Object objM;
        Object objB;
        boolean z;
        bf4 bf4Var;
        Object objValueOf;
        lv lvVar;
        String str4;
        kd3 kd3Var;
        Object objValueOf2;
        String simpleName;
        lv lvVar2;
        String str5;
        String str6;
        String str7;
        y04 y04Var;
        ?? r1;
        if (ck0Var instanceof z04) {
            z04Var = (z04) ck0Var;
            int i = z04Var.z;
            if ((i & Integer.MIN_VALUE) != 0) {
                z04Var.z = i - Integer.MIN_VALUE;
            } else {
                z04Var = new z04(v14Var, ck0Var);
            }
        } else {
            z04Var = new z04(v14Var, ck0Var);
        }
        z04 z04Var2 = z04Var;
        Object objV = z04Var2.x;
        ?? r2 = z04Var2.z;
        ql0 ql0Var = ql0.a;
        try {
            try {
                try {
                    try {
                        switch (r2) {
                            case 0:
                                f32.e0(objV);
                                v14Var.j();
                                SharedPreferences sharedPreferences = v14Var.b;
                                string = sharedPreferences.getString("installation_id", null);
                                if (string == null || qg4.o0(string)) {
                                    string = UUID.randomUUID().toString();
                                    string.getClass();
                                    sharedPreferences.edit().putString("installation_id", string).apply();
                                }
                                z04Var2.t = string;
                                str = "1.3.2";
                                z04Var2.u = "1.3.2";
                                z04Var2.z = 1;
                                objV = v14Var.v(string, "1.3.2", z04Var2);
                                if (objV == ql0Var) {
                                    return ql0Var;
                                }
                                str2 = str;
                                w04Var = (w04) objV;
                                if (!w04Var.c) {
                                    z04Var2.t = null;
                                    z04Var2.u = null;
                                    z04Var2.z = 2;
                                    objB = v14Var.B(i(w04Var, string, str2, v14Var.p(), null), "custom-device-trust", z04Var2);
                                    if (objB == ql0Var) {
                                        return ql0Var;
                                    }
                                    return objB;
                                }
                                l = w04Var.d;
                                if (l == null) {
                                    Log.d("SecureBackendClient", "Play Integrity skipped because backend did not provide cloud project number");
                                    z04Var2.t = string;
                                    z04Var2.u = str2;
                                    z04Var2.v = null;
                                    z04Var2.z = 5;
                                    objV = v14Var.l(w04Var, string, str2, z04Var2);
                                    if (objV == ql0Var) {
                                        return ql0Var;
                                    }
                                    str5 = str2;
                                    y04Var = (y04) objV;
                                    return y04Var;
                                }
                                try {
                                    long jLongValue = l.longValue();
                                    z04Var2.t = string;
                                    z04Var2.u = str2;
                                    z04Var2.v = l;
                                    z04Var2.z = 3;
                                    objM = v14Var.m(w04Var, string, str2, jLongValue, z04Var2);
                                    if (objM == ql0Var) {
                                        return ql0Var;
                                    }
                                    return objM;
                                } catch (Exception e) {
                                    e = e;
                                    str3 = str2;
                                    z = e instanceof lv;
                                    if (z) {
                                        lvVar2 = (lv) e;
                                        if (lvVar2.a == 503) {
                                            break;
                                        }
                                        throw e;
                                    }
                                    long jLongValue2 = l.longValue();
                                    if (e instanceof bf4) {
                                        bf4Var = (bf4) e;
                                    } else {
                                        bf4Var = null;
                                    }
                                    if (bf4Var != null) {
                                        objValueOf = Integer.valueOf(bf4Var.a());
                                    } else {
                                        objValueOf = null;
                                    }
                                    if (z) {
                                        lvVar = (lv) e;
                                    } else {
                                        lvVar = null;
                                    }
                                    if (lvVar != null) {
                                        str4 = lvVar.b;
                                    } else {
                                        str4 = null;
                                    }
                                    if (e instanceof kd3) {
                                        kd3Var = (kd3) e;
                                    } else {
                                        kd3Var = null;
                                    }
                                    if (kd3Var != null) {
                                        objValueOf2 = Long.valueOf(kd3Var.a);
                                    } else {
                                        objValueOf2 = null;
                                    }
                                    if (objValueOf2 != null) {
                                        simpleName = "timeout";
                                    } else if (objValueOf != null) {
                                        simpleName = "standard_integrity";
                                    } else if (str4 != null) {
                                        simpleName = "backend";
                                    } else {
                                        simpleName = e.getClass().getSimpleName();
                                    }
                                    if (objValueOf2 == null) {
                                        objValueOf2 = "none";
                                    }
                                    if (objValueOf == null) {
                                        objValueOf = "none";
                                    }
                                    if (str4 == null) {
                                        str4 = "none";
                                    }
                                    Log.w("SecureBackendClient", "Play Integrity failed; falling back to Android Key Attestation. cloudProjectNumber=" + jLongValue2 + " failureType=" + simpleName + " timeoutMs=" + objValueOf2 + " standardErrorCode=" + objValueOf + " backendErrorCode=" + str4, e);
                                    z04Var2.t = string;
                                    z04Var2.u = str3;
                                    z04Var2.v = null;
                                    z04Var2.z = 4;
                                    objV = v14Var.v(string, str3, z04Var2);
                                    if (objV == ql0Var) {
                                        return ql0Var;
                                    }
                                    str2 = str3;
                                    w04Var = (w04) objV;
                                    try {
                                        z04Var2.t = string;
                                        z04Var2.u = str2;
                                        z04Var2.v = null;
                                        z04Var2.z = 5;
                                        objV = v14Var.l(w04Var, string, str2, z04Var2);
                                        if (objV == ql0Var) {
                                            return ql0Var;
                                        }
                                        str5 = str2;
                                        y04Var = (y04) objV;
                                        return y04Var;
                                    } catch (Exception e2) {
                                        e = e2;
                                        str5 = str2;
                                        str6 = string;
                                        str7 = str5;
                                        Exception exc = e;
                                        z04Var2.t = str6;
                                        z04Var2.u = str7;
                                        z04Var2.v = null;
                                        z04Var2.w = exc;
                                        z04Var2.z = 6;
                                        objV = v14Var.v(str6, str7, z04Var2);
                                        r2 = exc;
                                        if (objV == ql0Var) {
                                            return ql0Var;
                                        }
                                        w04 w04Var2 = (w04) objV;
                                        String strP = v14Var.p();
                                        try {
                                            z04Var2.t = null;
                                            z04Var2.u = null;
                                            z04Var2.v = null;
                                            z04Var2.w = r2;
                                            z04Var2.z = 7;
                                            objV = v14Var.B(i(w04Var2, str6, str7, strP, "integrity-soft-bypass"), null, z04Var2);
                                            if (objV == ql0Var) {
                                                return ql0Var;
                                            }
                                            r1 = r2;
                                            y04Var = (y04) objV;
                                        } catch (Exception e3) {
                                            e = e3;
                                            r1 = r2;
                                            r00.h(e, r1);
                                            throw e;
                                        }
                                    }
                                }
                                break;
                                break;
                            case 1:
                                str = z04Var2.u;
                                string = z04Var2.t;
                                f32.e0(objV);
                                str2 = str;
                                w04Var = (w04) objV;
                                if (!w04Var.c) {
                                    z04Var2.t = null;
                                    z04Var2.u = null;
                                    z04Var2.z = 2;
                                    objB = v14Var.B(i(w04Var, string, str2, v14Var.p(), null), "custom-device-trust", z04Var2);
                                    if (objB == ql0Var) {
                                        return ql0Var;
                                    }
                                    return objB;
                                }
                                l = w04Var.d;
                                if (l == null) {
                                    long jLongValue3 = l.longValue();
                                    z04Var2.t = string;
                                    z04Var2.u = str2;
                                    z04Var2.v = l;
                                    z04Var2.z = 3;
                                    objM = v14Var.m(w04Var, string, str2, jLongValue3, z04Var2);
                                    if (objM == ql0Var) {
                                        return ql0Var;
                                    }
                                    return objM;
                                }
                                Log.d("SecureBackendClient", "Play Integrity skipped because backend did not provide cloud project number");
                                z04Var2.t = string;
                                z04Var2.u = str2;
                                z04Var2.v = null;
                                z04Var2.z = 5;
                                objV = v14Var.l(w04Var, string, str2, z04Var2);
                                if (objV == ql0Var) {
                                    return ql0Var;
                                }
                                str5 = str2;
                                y04Var = (y04) objV;
                                return y04Var;
                            case 2:
                                f32.e0(objV);
                                return objV;
                            case 3:
                                Long l2 = z04Var2.v;
                                String str8 = z04Var2.u;
                                String str9 = z04Var2.t;
                                try {
                                    f32.e0(objV);
                                    return objV;
                                } catch (Exception e4) {
                                    e = e4;
                                    l = l2;
                                    str3 = str8;
                                    string = str9;
                                    z = e instanceof lv;
                                    if (z) {
                                        lvVar2 = (lv) e;
                                        if (lvVar2.a == 503 || !r02.T(lvVar2.b, "PLAY_INTEGRITY_FALLBACK_REQUIRED")) {
                                            throw e;
                                        }
                                    }
                                    long jLongValue4 = l.longValue();
                                    if (e instanceof bf4) {
                                        bf4Var = (bf4) e;
                                    } else {
                                        bf4Var = null;
                                    }
                                    if (bf4Var != null) {
                                        objValueOf = Integer.valueOf(bf4Var.a());
                                    } else {
                                        objValueOf = null;
                                    }
                                    if (z) {
                                        lvVar = (lv) e;
                                    } else {
                                        lvVar = null;
                                    }
                                    if (lvVar != null) {
                                        str4 = lvVar.b;
                                    } else {
                                        str4 = null;
                                    }
                                    if (e instanceof kd3) {
                                        kd3Var = (kd3) e;
                                    } else {
                                        kd3Var = null;
                                    }
                                    if (kd3Var != null) {
                                        objValueOf2 = Long.valueOf(kd3Var.a);
                                    } else {
                                        objValueOf2 = null;
                                    }
                                    if (objValueOf2 != null) {
                                        simpleName = "timeout";
                                    } else if (objValueOf != null) {
                                        simpleName = "standard_integrity";
                                    } else if (str4 != null) {
                                        simpleName = "backend";
                                    } else {
                                        simpleName = e.getClass().getSimpleName();
                                    }
                                    if (objValueOf2 == null) {
                                        objValueOf2 = "none";
                                    }
                                    if (objValueOf == null) {
                                        objValueOf = "none";
                                    }
                                    if (str4 == null) {
                                        str4 = "none";
                                    }
                                    Log.w("SecureBackendClient", "Play Integrity failed; falling back to Android Key Attestation. cloudProjectNumber=" + jLongValue4 + " failureType=" + simpleName + " timeoutMs=" + objValueOf2 + " standardErrorCode=" + objValueOf + " backendErrorCode=" + str4, e);
                                    z04Var2.t = string;
                                    z04Var2.u = str3;
                                    z04Var2.v = null;
                                    z04Var2.z = 4;
                                    objV = v14Var.v(string, str3, z04Var2);
                                    if (objV == ql0Var) {
                                        return ql0Var;
                                    }
                                    str2 = str3;
                                    w04Var = (w04) objV;
                                    z04Var2.t = string;
                                    z04Var2.u = str2;
                                    z04Var2.v = null;
                                    z04Var2.z = 5;
                                    objV = v14Var.l(w04Var, string, str2, z04Var2);
                                    if (objV == ql0Var) {
                                        return ql0Var;
                                    }
                                    str5 = str2;
                                    y04Var = (y04) objV;
                                    return y04Var;
                                }
                            case 4:
                                str3 = z04Var2.u;
                                string = z04Var2.t;
                                f32.e0(objV);
                                str2 = str3;
                                w04Var = (w04) objV;
                                z04Var2.t = string;
                                z04Var2.u = str2;
                                z04Var2.v = null;
                                z04Var2.z = 5;
                                objV = v14Var.l(w04Var, string, str2, z04Var2);
                                if (objV == ql0Var) {
                                    return ql0Var;
                                }
                                str5 = str2;
                                y04Var = (y04) objV;
                                return y04Var;
                            case 5:
                                str5 = z04Var2.u;
                                string = z04Var2.t;
                                try {
                                    f32.e0(objV);
                                    y04Var = (y04) objV;
                                    break;
                                } catch (Exception e5) {
                                    e = e5;
                                    str6 = string;
                                    str7 = str5;
                                    Exception exc2 = e;
                                    z04Var2.t = str6;
                                    z04Var2.u = str7;
                                    z04Var2.v = null;
                                    z04Var2.w = exc2;
                                    z04Var2.z = 6;
                                    objV = v14Var.v(str6, str7, z04Var2);
                                    r2 = exc2;
                                    if (objV == ql0Var) {
                                        return ql0Var;
                                    }
                                    w04 w04Var3 = (w04) objV;
                                    String strP2 = v14Var.p();
                                    z04Var2.t = null;
                                    z04Var2.u = null;
                                    z04Var2.v = null;
                                    z04Var2.w = r2;
                                    z04Var2.z = 7;
                                    objV = v14Var.B(i(w04Var3, str6, str7, strP2, "integrity-soft-bypass"), null, z04Var2);
                                    if (objV == ql0Var) {
                                        return ql0Var;
                                    }
                                    r1 = r2;
                                    y04Var = (y04) objV;
                                    break;
                                }
                                return y04Var;
                            case 6:
                                Exception exc3 = z04Var2.w;
                                str7 = z04Var2.u;
                                str6 = z04Var2.t;
                                f32.e0(objV);
                                r2 = exc3;
                                w04 w04Var4 = (w04) objV;
                                String strP3 = v14Var.p();
                                z04Var2.t = null;
                                z04Var2.u = null;
                                z04Var2.v = null;
                                z04Var2.w = r2;
                                z04Var2.z = 7;
                                objV = v14Var.B(i(w04Var4, str6, str7, strP3, "integrity-soft-bypass"), null, z04Var2);
                                if (objV == ql0Var) {
                                    return ql0Var;
                                }
                                r1 = r2;
                                y04Var = (y04) objV;
                                return y04Var;
                            case 7:
                                r1 = z04Var2.w;
                                try {
                                    f32.e0(objV);
                                    r1 = r1;
                                    y04Var = (y04) objV;
                                    return y04Var;
                                } catch (Exception e6) {
                                    e = e6;
                                    r00.h(e, r1);
                                    throw e;
                                }
                            default:
                                ne.n("call to 'resume' before 'invoke' with coroutine");
                                return null;
                        }
                    } catch (CancellationException e7) {
                        throw e7;
                    }
                } catch (CancellationException e8) {
                    throw e8;
                }
            } catch (CancellationException e9) {
                throw e9;
            }
        } catch (CancellationException e10) {
            throw e10;
        } catch (Exception e11) {
            r00.h(e11, r2);
            throw e11;
        }
    }

    public static final void c(v14 v14Var, yo3 yo3Var) throws cg4 {
        v14Var.getClass();
        try {
            m23 m23Var = v14Var.d;
            m23Var.getClass();
            yo3Var.getClass();
            eq3 eq3VarF = new ql3(m23Var, yo3Var).f();
            try {
                int i = eq3VarF.d;
                if (!eq3VarF.b()) {
                    throw new cg4("Unable to upload image. Please check your connection and try again.", Integer.valueOf(i), i == 408 || i == 429 || i >= 500, null, 8);
                }
                eq3VarF.close();
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    ux1.o(eq3VarF, th);
                    throw th2;
                }
            }
        } catch (cg4 e) {
            throw e;
        } catch (IOException e2) {
            throw new cg4("Image upload timed out. Please check your connection and try again.", null, true, e2, 2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0015  */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0078, code lost:
    
        if (r12 == r6) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object d(defpackage.v14 r9, long r10, defpackage.ck0 r12) {
        /*
            zv2 r0 = r9.h
            boolean r1 = r12 instanceof defpackage.f14
            if (r1 == 0) goto L15
            r1 = r12
            f14 r1 = (defpackage.f14) r1
            int r2 = r1.w
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L15
            int r2 = r2 - r3
            r1.w = r2
            goto L1a
        L15:
            f14 r1 = new f14
            r1.<init>(r9, r12)
        L1a:
            java.lang.Object r12 = r1.u
            int r2 = r1.w
            r3 = 2
            r4 = 0
            r5 = 1
            ql0 r6 = defpackage.ql0.a
            if (r2 == 0) goto L3d
            if (r2 == r5) goto L37
            if (r2 != r3) goto L31
            long r10 = r1.t
            defpackage.f32.e0(r12)     // Catch: java.lang.Throwable -> L2f
            goto L7b
        L2f:
            r9 = move-exception
            goto L92
        L31:
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            defpackage.ne.n(r9)
            return r4
        L37:
            long r10 = r1.t
            defpackage.f32.e0(r12)
            goto L4b
        L3d:
            defpackage.f32.e0(r12)
            r1.t = r10
            r1.w = r5
            java.lang.Object r12 = r0.d(r1)
            if (r12 != r6) goto L4b
            goto L7a
        L4b:
            x04 r12 = r9.i     // Catch: java.lang.Throwable -> L2f
            if (r12 == 0) goto L5c
            long r7 = r12.a     // Catch: java.lang.Throwable -> L2f
            int r2 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r2 != 0) goto L56
            goto L57
        L56:
            r12 = r4
        L57:
            if (r12 == 0) goto L5c
            dd5 r9 = r12.b     // Catch: java.lang.Throwable -> L2f
            goto L8b
        L5c:
            gb2 r12 = r9.g     // Catch: java.lang.Throwable -> L2f
            java.lang.Object r12 = r12.getValue()     // Catch: java.lang.Throwable -> L2f
            jc5 r12 = (defpackage.jc5) r12     // Catch: java.lang.Throwable -> L2f
            nj5 r2 = new nj5     // Catch: java.lang.Throwable -> L2f
            r2.<init>(r10)     // Catch: java.lang.Throwable -> L2f
            com.google.android.gms.tasks.Task r12 = r12.a(r2)     // Catch: java.lang.Throwable -> L2f
            r12.getClass()     // Catch: java.lang.Throwable -> L2f
            r1.t = r10     // Catch: java.lang.Throwable -> L2f
            r1.w = r3     // Catch: java.lang.Throwable -> L2f
            java.lang.Object r12 = h(r12, r1)     // Catch: java.lang.Throwable -> L2f
            if (r12 != r6) goto L7b
        L7a:
            return r6
        L7b:
            r1 = r12
            dd5 r1 = (defpackage.dd5) r1     // Catch: java.lang.Throwable -> L2f
            x04 r2 = new x04     // Catch: java.lang.Throwable -> L2f
            r1.getClass()     // Catch: java.lang.Throwable -> L2f
            r2.<init>(r10, r1)     // Catch: java.lang.Throwable -> L2f
            r9.i = r2     // Catch: java.lang.Throwable -> L2f
            r9 = r12
            dd5 r9 = (defpackage.dd5) r9     // Catch: java.lang.Throwable -> L2f
        L8b:
            r9.getClass()     // Catch: java.lang.Throwable -> L2f
            r0.f(r4)
            return r9
        L92:
            r0.f(r4)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.v14.d(v14, long, ck0):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public static final Object e(v14 v14Var, String str, Map map, ck0 ck0Var) throws Throwable {
        m14 m14Var;
        String str2;
        Map map2;
        v14Var.getClass();
        if (ck0Var instanceof m14) {
            m14Var = (m14) ck0Var;
            int i = m14Var.x;
            if ((i & Integer.MIN_VALUE) != 0) {
                m14Var.x = i - Integer.MIN_VALUE;
            } else {
                m14Var = new m14(v14Var, ck0Var);
            }
        } else {
            m14Var = new m14(v14Var, ck0Var);
        }
        Object obj = m14Var.v;
        int i2 = m14Var.x;
        bk0 bk0Var = null;
        Object obj2 = ql0.a;
        if (i2 == 0) {
            f32.e0(obj);
            m14Var.t = str;
            m14Var.u = map;
            m14Var.x = 1;
            Object objT = v14Var.t();
            if (objT == null) {
                objT = v14Var.D(new no0(v14Var, bk0Var, 3), m14Var);
            }
            if (objT != obj2) {
                str2 = str;
                obj = objT;
                map2 = map;
            }
        }
        if (i2 != 1) {
            if (i2 == 2) {
                f32.e0(obj);
                return obj;
            }
            ne.n("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        map2 = m14Var.u;
        String str3 = m14Var.t;
        f32.e0(obj);
        str2 = str3;
        y04 y04Var = (y04) obj;
        q74 q74VarY = v14Var.y("GET", str2, y04Var.a, new byte[0], y04Var.c, v14Var.l + System.currentTimeMillis());
        xo3 xo3Var = new xo3();
        xo3Var.g(v14Var.k + str2);
        xo3Var.d("X-Signed-Recovery-Version", "1");
        xo3Var.e("GET", null);
        for (Map.Entry entry : map2.entrySet()) {
            xo3Var.d((String) entry.getKey(), (String) entry.getValue());
        }
        for (Map.Entry entry2 : q74VarY.a().entrySet()) {
            xo3Var.d((String) entry2.getKey(), (String) entry2.getValue());
        }
        yo3 yo3VarB = xo3Var.b();
        m14Var.t = null;
        m14Var.u = null;
        m14Var.x = 2;
        Object objN = v14Var.n(yo3VarB, m14Var);
        return objN == obj2 ? obj2 : objN;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object f(v14 v14Var, String str, JSONObject jSONObject, ck0 ck0Var) {
        n14 n14Var;
        v14Var.getClass();
        if (ck0Var instanceof n14) {
            n14Var = (n14) ck0Var;
            int i = n14Var.x;
            if ((i & Integer.MIN_VALUE) != 0) {
                n14Var.x = i - Integer.MIN_VALUE;
            } else {
                n14Var = new n14(v14Var, ck0Var);
            }
        } else {
            n14Var = new n14(v14Var, ck0Var);
        }
        Object obj = n14Var.v;
        int i2 = n14Var.x;
        Object obj2 = ql0.a;
        if (i2 == 0) {
            f32.e0(obj);
            n14Var.t = str;
            n14Var.u = jSONObject;
            n14Var.x = 1;
            if (v14Var.C(false, n14Var) != obj2) {
            }
        }
        if (i2 != 1) {
            if (i2 == 2) {
                f32.e0(obj);
                return obj;
            }
            ne.n("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        jSONObject = n14Var.u;
        str = n14Var.t;
        f32.e0(obj);
        li1 tdVar = new td(v14Var, str, jSONObject, null, 2);
        n14Var.t = null;
        n14Var.u = null;
        n14Var.x = 2;
        Object objA = v14Var.A(r74.POST, tdVar, n14Var);
        return objA == obj2 ? obj2 : objA;
    }

    /* JADX WARN: Code duplicated, block: B:8:0x001b  */
    public static final Object g(v14 v14Var, String str, JSONObject jSONObject, ck0 ck0Var) throws Throwable {
        o14 o14Var;
        Object objT;
        String str2;
        JSONObject jSONObject2;
        v14Var.getClass();
        if (ck0Var instanceof o14) {
            o14Var = (o14) ck0Var;
            int i = o14Var.x;
            if ((i & Integer.MIN_VALUE) != 0) {
                o14Var.x = i - Integer.MIN_VALUE;
            } else {
                o14Var = new o14(v14Var, ck0Var);
            }
        } else {
            o14Var = new o14(v14Var, ck0Var);
        }
        o14 o14Var2 = o14Var;
        Object obj = o14Var2.v;
        int i2 = o14Var2.x;
        bk0 bk0Var = null;
        Object obj2 = ql0.a;
        if (i2 == 0) {
            f32.e0(obj);
            o14Var2.t = str;
            o14Var2.u = jSONObject;
            o14Var2.x = 1;
            objT = v14Var.t();
            if (objT == null) {
                objT = v14Var.D(new no0(v14Var, bk0Var, 3), o14Var2);
            }
            if (objT != obj2) {
                str2 = str;
                jSONObject2 = jSONObject;
            }
        }
        if (i2 != 1) {
            if (i2 == 2) {
                f32.e0(obj);
                return obj;
            }
            ne.n("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        JSONObject jSONObject3 = o14Var2.u;
        String str3 = o14Var2.t;
        f32.e0(obj);
        objT = obj;
        jSONObject2 = jSONObject3;
        str2 = str3;
        y04 y04Var = (y04) objT;
        String string = jSONObject2.toString();
        string.getClass();
        byte[] bytes = string.getBytes(u80.a);
        bytes.getClass();
        q74 q74VarY = v14Var.y("POST", str2, y04Var.a, bytes, y04Var.c, System.currentTimeMillis() + v14Var.l);
        xo3 xo3Var = new xo3();
        xo3Var.g(v14Var.k + str2);
        xo3Var.d("X-Signed-Recovery-Version", "1");
        xo3Var.e("POST", l72.q(string, v14Var.j));
        for (Map.Entry entry : q74VarY.a().entrySet()) {
            xo3Var.d((String) entry.getKey(), (String) entry.getValue());
        }
        yo3 yo3VarB = xo3Var.b();
        o14Var2.t = null;
        o14Var2.u = null;
        o14Var2.x = 2;
        Object objN = v14Var.n(yo3VarB, o14Var2);
        return objN == obj2 ? obj2 : objN;
    }

    public static Object h(Task task, ck0 ck0Var) {
        w50 w50Var = new w50(1, k05.z(ck0Var));
        w50Var.t();
        task.addOnSuccessListener(new fg1(new ph3(w50Var, 1), 25));
        task.addOnFailureListener(new k92(w50Var, 4));
        task.addOnCanceledListener(new k92(w50Var, 5));
        return w50Var.s();
    }

    public static JSONObject i(w04 w04Var, String str, String str2, String str3, String str4) throws JSONException {
        JSONObject jSONObjectPut = new JSONObject().put("platform", "android").put("appIdentifier", "com.photoeditor.remakemefaceswapaigenerator").put("packageName", "com.photoeditor.remakemefaceswapaigenerator").put("challengeId", w04Var.a).put("publicKeyPem", str3).put("installationId", str).put("appVersion", str2);
        if (str4 != null) {
            jSONObjectPut.put("attestationProvider", str4);
        }
        jSONObjectPut.getClass();
        return jSONObjectPut;
    }

    public static boolean r(IOException iOException) {
        lv lvVar = iOException instanceof lv ? (lv) iOException : null;
        return lvVar != null && lvVar.a == 401 && r02.T(lvVar.b, "UNAUTHORIZED");
    }

    public static boolean s(IOException iOException) {
        lv lvVar = iOException instanceof lv ? (lv) iOException : null;
        return lvVar != null && lvVar.a == 401 && ab0.i0(d54.g0("DEVICE_SESSION_REFRESH_REQUIRED", "DEVICE_SESSION_INVALID", "SESSION_INVALID", "SIGNATURE_INVALID"), lvVar.b);
    }

    public static Object x(li1 li1Var, bk0 bk0Var) {
        hr0 hr0Var = iv0.a;
        return rn5.X(xq0.c, new k14(li1Var, null, 0), bk0Var);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(4:22|115|23|(1:109)(1:26)) */
    /* JADX WARN: Code duplicated, block: B:100:0x01dc  */
    /* JADX WARN: Code duplicated, block: B:101:0x01de  */
    /* JADX WARN: Code duplicated, block: B:102:0x01df  */
    /* JADX WARN: Code duplicated, block: B:104:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:109:0x0204 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:110:0x0205  */
    /* JADX WARN: Code duplicated, block: B:26:0x00cb A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:31:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:38:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:40:0x0101  */
    /* JADX WARN: Code duplicated, block: B:57:0x0137  */
    /* JADX WARN: Code duplicated, block: B:61:0x014f  */
    /* JADX WARN: Code duplicated, block: B:63:0x0155 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:64:0x0157  */
    /* JADX WARN: Code duplicated, block: B:65:0x0159  */
    /* JADX WARN: Code duplicated, block: B:72:0x0177  */
    /* JADX WARN: Code duplicated, block: B:74:0x0179  */
    /* JADX WARN: Code duplicated, block: B:76:0x017d  */
    /* JADX WARN: Code duplicated, block: B:77:0x0181  */
    /* JADX WARN: Code duplicated, block: B:79:0x0184  */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Code duplicated, block: B:80:0x0186  */
    /* JADX WARN: Code duplicated, block: B:82:0x019e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:86:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:87:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:89:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:90:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:93:0x01b8 A[ADDED_TO_REGION] */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00cc, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00cd, code lost:
    
        r16 = r10;
        r10 = r0;
        r0 = r16;
        r6 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0130, code lost:
    
        if (C(true, r8) == r7) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0134, code lost:
    
        r1 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x014a, code lost:
    
        if (k(r8) == r7) goto L109;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1, types: [lv] */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17, types: [lv] */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.io.IOException, li1, r74] */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:110:0x0205 -> B:111:0x0207). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00f8 -> B:115:0x00b2). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object A(defpackage.r74 r18, defpackage.li1 r19, defpackage.ck0 r20) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 544
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.v14.A(r74, li1, ck0):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object B(JSONObject jSONObject, String str, ck0 ck0Var) throws Throwable {
        r14 r14Var;
        String str2;
        Object kq3Var;
        if (ck0Var instanceof r14) {
            r14Var = (r14) ck0Var;
            int i = r14Var.w;
            if ((i & Integer.MIN_VALUE) != 0) {
                r14Var.w = i - Integer.MIN_VALUE;
            } else {
                r14Var = new r14(this, ck0Var);
            }
        } else {
            r14Var = new r14(this, ck0Var);
        }
        Object objN = r14Var.u;
        int i2 = r14Var.w;
        if (i2 == 0) {
            f32.e0(objN);
            if (str == null) {
                str = jSONObject.optString("attestationProvider");
                if (qg4.o0(str)) {
                    str = "unknown";
                }
            }
            xo3 xo3Var = new xo3();
            xo3Var.g(this.k + "/v1/device-sessions");
            xo3Var.d("X-Attestation-Recovery-Version", "1");
            String string = jSONObject.toString();
            string.getClass();
            xo3Var.e("POST", l72.q(string, this.j));
            yo3 yo3VarB = xo3Var.b();
            r14Var.t = str;
            r14Var.w = 1;
            objN = n(yo3VarB, r14Var);
            Object obj = ql0.a;
            if (objN == obj) {
                return obj;
            }
            str2 = str;
        } else {
            if (i2 != 1) {
                ne.n("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            str2 = r14Var.t;
            f32.e0(objN);
        }
        String str3 = (String) objN;
        str2.getClass();
        JSONObject jSONObject2 = new JSONObject(str3);
        long jOptLong = jSONObject2.optLong("ttlSeconds", 0L);
        long j = jOptLong < 60 ? 60L : jOptLong;
        String string2 = jSONObject2.getString("deviceSessionId");
        string2.getClass();
        long millis = TimeUnit.SECONDS.toMillis(j) + System.currentTimeMillis();
        y04 y04Var = new y04(millis, string2, "v2", str3);
        if (!this.b.edit().putString("device_session_id", string2).putLong("device_session_expires_at", millis).putString("device_session_signing_key_version", "v2").putString("device_session_trust_level", jSONObject2.optString("trustLevel")).putString("device_session_package_name", jSONObject2.optString("packageName")).commit()) {
            ne.n("Unable to persist the new device session");
            return null;
        }
        try {
            gi giVarO = o();
            KeyStore keyStore = giVarO.b;
            ReentrantLock reentrantLock = giVarO.a;
            reentrantLock.lock();
            try {
                if (keyStore.containsAlias("remakeface_full_device_signing_key_v2") && keyStore.containsAlias("remakeface_full_device_signing_key_v1")) {
                    keyStore.deleteEntry("remakeface_full_device_signing_key_v1");
                }
                reentrantLock.unlock();
                kq3Var = fy4.a;
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        } catch (Throwable th2) {
            kq3Var = new kq3(th2);
        }
        Throwable thA = lq3.a(kq3Var);
        if (thA != null) {
            Log.w("SecureBackendClient", "Device session persisted, but legacy signing-key cleanup was deferred", thA);
        }
        String strOptString = jSONObject2.optString("trustProvider");
        if (qg4.o0(strOptString)) {
            strOptString = "none";
        }
        String strOptString2 = jSONObject2.optString("trustLevel");
        String str4 = qg4.o0(strOptString2) ? "none" : strOptString2;
        StringBuilder sbX = f62.x("Device session created attemptedProvider=", str2, " trustProvider=", strOptString, " trustLevel=");
        sbX.append(str4);
        sbX.append(" ttlSeconds=");
        sbX.append(j);
        Log.i("SecureBackendClient", sbX.toString());
        return y04Var;
    }

    public final Object C(boolean z, ck0 ck0Var) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (!z) {
            long j = this.m;
            if (j > 0 && jCurrentTimeMillis - j < PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS) {
                return Boolean.TRUE;
            }
        }
        return u(ck0Var);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0056, code lost:
    
        if (r6 == r5) goto L24;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object D(defpackage.li1 r7, defpackage.ck0 r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof defpackage.u14
            if (r0 == 0) goto L13
            r0 = r8
            u14 r0 = (defpackage.u14) r0
            int r1 = r0.w
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.w = r1
            goto L18
        L13:
            u14 r0 = new u14
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r6 = r0.u
            int r8 = r0.w
            r1 = 2
            zv2 r2 = defpackage.v14.n
            r3 = 1
            r4 = 0
            ql0 r5 = defpackage.ql0.a
            if (r8 == 0) goto L3d
            if (r8 == r3) goto L35
            if (r8 != r1) goto L2f
            defpackage.f32.e0(r6)     // Catch: java.lang.Throwable -> L2d
            goto L59
        L2d:
            r6 = move-exception
            goto L5d
        L2f:
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            defpackage.ne.n(r6)
            return r4
        L35:
            vi4 r7 = r0.t
            li1 r7 = (defpackage.li1) r7
            defpackage.f32.e0(r6)
            goto L4e
        L3d:
            defpackage.f32.e0(r6)
            r6 = r7
            vi4 r6 = (defpackage.vi4) r6
            r0.t = r6
            r0.w = r3
            java.lang.Object r6 = r2.d(r0)
            if (r6 != r5) goto L4e
            goto L58
        L4e:
            r0.t = r4     // Catch: java.lang.Throwable -> L2d
            r0.w = r1     // Catch: java.lang.Throwable -> L2d
            java.lang.Object r6 = r7.invoke(r0)     // Catch: java.lang.Throwable -> L2d
            if (r6 != r5) goto L59
        L58:
            return r5
        L59:
            r2.f(r4)
            return r6
        L5d:
            r2.f(r4)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.v14.D(li1, ck0):java.lang.Object");
    }

    public final void j() {
        this.b.edit().remove("device_session_id").remove("device_session_expires_at").remove("device_session_signing_key_version").remove("device_session_trust_level").remove("device_session_package_name").apply();
    }

    public final Object k(p14 p14Var) {
        Object objD = D(new cm(this, null, 1), p14Var);
        return objD == ql0.a ? objD : fy4.a;
    }

    public final Object l(w04 w04Var, String str, String str2, z04 z04Var) throws JSONException {
        byte[] bArrDecode = Base64.decode(w04Var.b, 11);
        bArrDecode.getClass();
        gi giVarO = o();
        KeyStore keyStore = giVarO.b;
        ReentrantLock reentrantLock = giVarO.a;
        reentrantLock.lock();
        try {
            if (keyStore.containsAlias("remakeface_full_device_signing_key_v2")) {
                keyStore.deleteEntry("remakeface_full_device_signing_key_v2");
            }
            gi.a(bArrDecode);
            Certificate[] certificateChain = keyStore.getCertificateChain("remakeface_full_device_signing_key_v2");
            List listI0 = certificateChain != null ? ar.i0(certificateChain) : null;
            if (listI0 == null) {
                listI0 = p21.a;
            }
            if (listI0.isEmpty()) {
                throw new IllegalStateException("Android Keystore did not return an attestation certificate chain");
            }
            Object objO0 = ab0.o0(listI0);
            objO0.getClass();
            giVarO.d((Certificate) objO0);
            Object objO1 = ab0.o0(listI0);
            objO1.getClass();
            byte[] encoded = ((Certificate) objO1).getPublicKey().getEncoded();
            encoded.getClass();
            String strC = gi.c("PUBLIC KEY", encoded);
            List<Certificate> list = listI0;
            ArrayList arrayList = new ArrayList(bb0.X(list, 10));
            for (Certificate certificate : list) {
                certificate.getClass();
                byte[] encoded2 = certificate.getEncoded();
                encoded2.getClass();
                arrayList.add(gi.c("CERTIFICATE", encoded2));
            }
            reentrantLock.unlock();
            JSONObject jSONObjectPut = i(w04Var, str, str2, strC, "android-key-attestation").put("attestationCertificateChain", new JSONArray((Collection) arrayList));
            jSONObjectPut.getClass();
            return B(jSONObjectPut, null, z04Var);
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001d  */
    public final Object m(w04 w04Var, String str, String str2, long j, ck0 ck0Var) throws Throwable {
        a14 a14Var;
        w04 w04Var2;
        long j2;
        String str3;
        String str4;
        String str5 = str;
        if (ck0Var instanceof a14) {
            a14Var = (a14) ck0Var;
            int i = a14Var.A;
            if ((i & Integer.MIN_VALUE) != 0) {
                a14Var.A = i - Integer.MIN_VALUE;
            } else {
                a14Var = new a14(this, ck0Var);
            }
        } else {
            a14Var = new a14(this, ck0Var);
        }
        Object obj = a14Var.y;
        int i2 = a14Var.A;
        Object obj2 = ql0.a;
        if (i2 == 0) {
            f32.e0(obj);
            String strB = o().b();
            String str6 = w04Var.a;
            String str7 = w04Var.b;
            Charset charset = u80.a;
            byte[] bytes = str5.getBytes(charset);
            bytes.getClass();
            byte[] bArrDigest = MessageDigest.getInstance("SHA-256").digest(bytes);
            bArrDigest.getClass();
            String strEncodeToString = Base64.encodeToString(bArrDigest, 11);
            strEncodeToString.getClass();
            String strConcat = "sha256:".concat(strEncodeToString);
            String strZ = xg4.Z(xg4.Z(strB, "-----BEGIN PUBLIC KEY-----", RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED), "-----END PUBLIC KEY-----", RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED);
            Pattern patternCompile = Pattern.compile("\\s");
            patternCompile.getClass();
            String strReplaceAll = patternCompile.matcher(strZ).replaceAll(RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED);
            strReplaceAll.getClass();
            byte[] bArrDecode = Base64.decode(strReplaceAll, 0);
            bArrDecode.getClass();
            byte[] bArrDigest2 = MessageDigest.getInstance("SHA-256").digest(bArrDecode);
            bArrDigest2.getClass();
            String strEncodeToString2 = Base64.encodeToString(bArrDigest2, 11);
            strEncodeToString2.getClass();
            byte[] bytes2 = ab0.u0(mh5.A("idecor.play_integrity_session.v1", str6, str7, "com.photoeditor.remakemefaceswapaigenerator", strConcat, str2, "sha256:".concat(strEncodeToString2)), "\n", null, null, 0, null, 62).getBytes(charset);
            bytes2.getClass();
            byte[] bArrDigest3 = MessageDigest.getInstance("SHA-256").digest(bytes2);
            bArrDigest3.getClass();
            String strEncodeToString3 = Base64.encodeToString(bArrDigest3, 11);
            strEncodeToString3.getClass();
            a14Var.t = w04Var;
            a14Var.u = str5;
            a14Var.v = str2;
            a14Var.w = strB;
            a14Var.x = j;
            a14Var.A = 1;
            Object objW = w(strEncodeToString3, j, a14Var);
            if (objW != obj2) {
                w04Var2 = w04Var;
                j2 = j;
                str3 = strB;
                obj = objW;
                str4 = str2;
            }
        }
        if (i2 != 1) {
            if (i2 == 2) {
                f32.e0(obj);
                return obj;
            }
            ne.n("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        long j3 = a14Var.x;
        str3 = a14Var.w;
        str4 = a14Var.v;
        String str8 = a14Var.u;
        w04Var2 = a14Var.t;
        f32.e0(obj);
        j2 = j3;
        str5 = str8;
        long j4 = j2;
        JSONObject jSONObjectPut = i(w04Var2, str5, str4, str3, "play-integrity").put("playIntegrityToken", (String) obj);
        jSONObjectPut.getClass();
        a14Var.t = null;
        a14Var.u = null;
        a14Var.v = null;
        a14Var.w = null;
        a14Var.x = j4;
        a14Var.A = 2;
        Object objB = B(jSONObjectPut, null, a14Var);
        return objB == obj2 ? obj2 : objB;
    }

    public final Object n(yo3 yo3Var, ck0 ck0Var) throws Throwable {
        hr0 hr0Var = iv0.a;
        return rn5.X(xq0.c, new hb(this, yo3Var, null, 9), ck0Var);
    }

    public final gi o() {
        return (gi) this.e.getValue();
    }

    public final String p() throws qa0 {
        try {
            return o().b();
        } catch (Exception e) {
            Log.w("SecureBackendClient", "Fallback signing key creation failed; clearing key alias and retrying once", e);
            try {
                gi giVarO = o();
                KeyStore keyStore = giVarO.b;
                ReentrantLock reentrantLock = giVarO.a;
                reentrantLock.lock();
                try {
                    if (keyStore.containsAlias("remakeface_full_device_signing_key_v2")) {
                        keyStore.deleteEntry("remakeface_full_device_signing_key_v2");
                    }
                    reentrantLock.unlock();
                    try {
                        return o().b();
                    } catch (Exception e2) {
                        r00.h(e2, e);
                        throw new qa0("Unable to prepare this device session. Please try again.", e2);
                    }
                } catch (Throwable th) {
                    reentrantLock.unlock();
                    throw th;
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object q(long j, ck0 ck0Var) {
        g14 g14Var;
        zv2 zv2Var;
        if (ck0Var instanceof g14) {
            g14Var = (g14) ck0Var;
            int i = g14Var.x;
            if ((i & Integer.MIN_VALUE) != 0) {
                g14Var.x = i - Integer.MIN_VALUE;
            } else {
                g14Var = new g14(this, ck0Var);
            }
        } else {
            g14Var = new g14(this, ck0Var);
        }
        Object obj = g14Var.v;
        int i2 = g14Var.x;
        if (i2 == 0) {
            f32.e0(obj);
            zv2 zv2Var2 = this.h;
            g14Var.u = zv2Var2;
            g14Var.t = j;
            g14Var.x = 1;
            Object objD = zv2Var2.d(g14Var);
            ql0 ql0Var = ql0.a;
            if (objD == ql0Var) {
                return ql0Var;
            }
            zv2Var = zv2Var2;
        } else {
            if (i2 != 1) {
                ne.n("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            j = g14Var.t;
            zv2Var = g14Var.u;
            f32.e0(obj);
        }
        try {
            x04 x04Var = this.i;
            if (x04Var != null && x04Var.a == j) {
                this.i = null;
            }
            return fy4.a;
        } finally {
            zv2Var.f(null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0034  */
    /* JADX WARN: Code duplicated, block: B:9:0x0036  */
    public final y04 t() {
        String str;
        SharedPreferences sharedPreferences = this.b;
        String string = sharedPreferences.getString("device_session_id", null);
        long j = sharedPreferences.getLong("device_session_expires_at", 0L);
        gi giVarO = o();
        String string2 = sharedPreferences.getString("device_session_signing_key_version", null);
        KeyStore keyStore = giVarO.b;
        ReentrantLock reentrantLock = giVarO.a;
        reentrantLock.lock();
        try {
            if (r02.T(string2, "v2")) {
                if (keyStore.containsAlias("remakeface_full_device_signing_key_v2")) {
                    str = "v2";
                } else {
                    str = null;
                }
            } else if (r02.T(string2, "v1")) {
                if (keyStore.containsAlias("remakeface_full_device_signing_key_v1")) {
                    str = "v1";
                } else {
                    str = null;
                }
            } else if (keyStore.containsAlias("remakeface_full_device_signing_key_v1")) {
                str = "v1";
            } else if (keyStore.containsAlias("remakeface_full_device_signing_key_v2")) {
                str = "v2";
            } else {
                str = null;
            }
            if (string == null || qg4.o0(string) || str == null || j - WorkRequest.MIN_BACKOFF_MILLIS <= System.currentTimeMillis()) {
                return null;
            }
            return new y04(j, string, str, "{\"deviceSessionId\":\"" + string + "\",\"expiresAtMillis\":" + j + "}");
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object u(ck0 ck0Var) {
        h14 h14Var;
        long j;
        if (ck0Var instanceof h14) {
            h14Var = (h14) ck0Var;
            int i = h14Var.w;
            if ((i & Integer.MIN_VALUE) != 0) {
                h14Var.w = i - Integer.MIN_VALUE;
            } else {
                h14Var = new h14(this, ck0Var);
            }
        } else {
            h14Var = new h14(this, ck0Var);
        }
        Object objN = h14Var.u;
        int i2 = h14Var.w;
        boolean z = false;
        try {
            if (i2 == 0) {
                f32.e0(objN);
                long jCurrentTimeMillis = System.currentTimeMillis();
                xo3 xo3Var = new xo3();
                xo3Var.a = k05.r(this.k);
                xo3Var.e("GET", null);
                for (Map.Entry entry : k05.E().entrySet()) {
                    xo3Var.d((String) entry.getKey(), (String) entry.getValue());
                }
                yo3 yo3VarB = xo3Var.b();
                h14Var.t = jCurrentTimeMillis;
                h14Var.w = 1;
                objN = n(yo3VarB, h14Var);
                Object obj = ql0.a;
                if (objN == obj) {
                    return obj;
                }
                j = jCurrentTimeMillis;
            } else {
                if (i2 != 1) {
                    ne.n("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                j = h14Var.t;
                f32.e0(objN);
            }
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            this.l = new JSONObject((String) objN).getLong("serverTimeMs") - (j + ((jCurrentTimeMillis2 - j) / 2));
            this.m = jCurrentTimeMillis2;
            this.b.edit().putLong("server_time_offset_ms", this.l).putLong("server_time_synced_at_ms", this.m).apply();
            z = true;
        } catch (IOException | JSONException unused) {
        }
        return Boolean.valueOf(z);
    }

    /* JADX WARN: Code duplicated, block: B:26:0x00da A[PHI: r6
  0x00da: PHI (r6v6 java.lang.Long) = (r6v5 java.lang.Long), (r6v8 java.lang.Long) binds: [B:32:0x00ee, B:25:0x00d8] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object v(String str, String str2, ck0 ck0Var) throws Throwable {
        i14 i14Var;
        Long lD0;
        if (ck0Var instanceof i14) {
            i14Var = (i14) ck0Var;
            int i = i14Var.v;
            if ((i & Integer.MIN_VALUE) != 0) {
                i14Var.v = i - Integer.MIN_VALUE;
            } else {
                i14Var = new i14(this, ck0Var);
            }
        } else {
            i14Var = new i14(this, ck0Var);
        }
        Object objN = i14Var.t;
        int i2 = i14Var.v;
        Long l = null;
        if (i2 == 0) {
            f32.e0(objN);
            JSONObject jSONObjectPut = new JSONObject().put("platform", "android").put("appIdentifier", "com.photoeditor.remakemefaceswapaigenerator").put("packageName", "com.photoeditor.remakemefaceswapaigenerator").put("installationId", str).put("appVersion", str2);
            xo3 xo3Var = new xo3();
            xo3Var.g(this.k + "/v1/device-sessions/challenges");
            String string = jSONObjectPut.toString();
            string.getClass();
            xo3Var.e("POST", l72.q(string, this.j));
            yo3 yo3VarB = xo3Var.b();
            i14Var.v = 1;
            objN = n(yo3VarB, i14Var);
            Object obj = ql0.a;
            if (objN == obj) {
                return obj;
            }
        } else {
            if (i2 != 1) {
                ne.n("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            f32.e0(objN);
        }
        JSONObject jSONObject = new JSONObject((String) objN);
        String string2 = jSONObject.getString("challengeId");
        string2.getClass();
        String string3 = jSONObject.getString("challenge");
        string3.getClass();
        boolean zOptBoolean = jSONObject.optBoolean("deviceSessionAttestationRequired", true);
        if (jSONObject.has("playIntegrityCloudProjectNumber") && !jSONObject.isNull("playIntegrityCloudProjectNumber")) {
            Object objOpt = jSONObject.opt("playIntegrityCloudProjectNumber");
            if (objOpt instanceof Number) {
                long jLongValue = ((Number) objOpt).longValue();
                lD0 = Long.valueOf(jLongValue);
                if (jLongValue > 0) {
                    l = lD0;
                }
            } else if ((objOpt instanceof String) && (lD0 = xg4.d0((String) objOpt)) != null && lD0.longValue() > 0) {
                l = lD0;
            }
        }
        return new w04(string2, l, string3, zOptBoolean);
    }

    /* JADX WARN: Code duplicated, block: B:39:0x007f  */
    /* JADX WARN: Code duplicated, block: B:46:0x0090  */
    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    public final Object w(String str, long j, ck0 ck0Var) throws bf4, kd3 {
        j14 j14Var;
        long j2;
        wq4 wq4Var;
        if (ck0Var instanceof j14) {
            j14Var = (j14) ck0Var;
            int i = j14Var.x;
            if ((i & Integer.MIN_VALUE) != 0) {
                j14Var.x = i - Integer.MIN_VALUE;
            } else {
                j14Var = new j14(this, ck0Var);
            }
        } else {
            j14Var = new j14(this, ck0Var);
        }
        j14 j14Var2 = j14Var;
        Object objZ = j14Var2.v;
        int i2 = j14Var2.x;
        Object obj = ql0.a;
        try {
            if (i2 == 0) {
                f32.e0(objZ);
                try {
                    u9 u9Var = new u9(this, j, str, (bk0) null);
                    j14Var2.u = j;
                    j14Var2.x = 1;
                    objZ = x22.Z(WorkRequest.MIN_BACKOFF_MILLIS, u9Var, j14Var2);
                    if (objZ != obj) {
                        j2 = j;
                    }
                } catch (bf4 e) {
                    e = e;
                    j2 = j;
                    j14Var2.t = e;
                    j14Var2.u = j2;
                    j14Var2.x = 3;
                    if (q(j2, j14Var2) != obj) {
                        throw e;
                    }
                } catch (wq4 e2) {
                    e = e2;
                    j2 = j;
                    j14Var2.t = e;
                    j14Var2.u = j2;
                    j14Var2.x = 2;
                    if (q(j2, j14Var2) != obj) {
                        wq4Var = e;
                        throw new kd3(wq4Var);
                    }
                }
                return obj;
            }
            if (i2 != 1) {
                if (i2 == 2) {
                    wq4Var = (wq4) j14Var2.t;
                    f32.e0(objZ);
                    throw new kd3(wq4Var);
                }
                if (i2 != 3) {
                    ne.n("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                bf4 bf4Var = (bf4) j14Var2.t;
                f32.e0(objZ);
                throw bf4Var;
            }
            j2 = j14Var2.u;
            try {
                f32.e0(objZ);
            } catch (bf4 e3) {
                e = e3;
                j14Var2.t = e;
                j14Var2.u = j2;
                j14Var2.x = 3;
                if (q(j2, j14Var2) != obj) {
                    throw e;
                }
                return obj;
            } catch (wq4 e4) {
                e = e4;
                j14Var2.t = e;
                j14Var2.u = j2;
                j14Var2.x = 2;
                if (q(j2, j14Var2) != obj) {
                    wq4Var = e;
                    throw new kd3(wq4Var);
                }
                return obj;
            }
            objZ.getClass();
            return (String) objZ;
        } catch (CancellationException e5) {
            throw e5;
        }
    }

    public final q74 y(String str, String str2, String str3, byte[] bArr, String str4, long j) throws zt0 {
        try {
            return ((np3) this.f.getValue()).a(str, str2, str3, bArr, str4, j);
        } catch (Exception e) {
            throw new zt0("Unable to sign the protected request with the current device key", e);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object z(String str, Map map, ck0 ck0Var) throws IOException {
        l14 l14Var;
        if (ck0Var instanceof l14) {
            l14Var = (l14) ck0Var;
            int i = l14Var.x;
            if ((i & Integer.MIN_VALUE) != 0) {
                l14Var.x = i - Integer.MIN_VALUE;
            } else {
                l14Var = new l14(this, ck0Var);
            }
        } else {
            l14Var = new l14(this, ck0Var);
        }
        Object obj = l14Var.v;
        int i2 = l14Var.x;
        Object obj2 = ql0.a;
        if (i2 == 0) {
            f32.e0(obj);
            l14Var.t = str;
            l14Var.u = map;
            l14Var.x = 1;
            if (C(false, l14Var) != obj2) {
            }
        }
        if (i2 != 1) {
            if (i2 != 2) {
                ne.n("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            Map map2 = l14Var.u;
            f32.e0(obj);
            return obj;
        }
        map = l14Var.u;
        str = l14Var.t;
        f32.e0(obj);
        li1 tdVar = new td(this, str, map, null, 1);
        l14Var.t = null;
        l14Var.u = null;
        l14Var.x = 2;
        Object objA = A(r74.GET, tdVar, l14Var);
        return objA == obj2 ? obj2 : objA;
    }
}
