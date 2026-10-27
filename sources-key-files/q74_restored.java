// q74 — 还原自 classes4.dex (androguard decompile)
// 混淆原名: q74

// <init> (Ljava/lang/String; Ljava/lang/String; Ljava/lang/String; Ljava/lang/String; Ljava/lang/String;)V

    public q74(String p1, String p2, String p3, String p4, String p5)
    {
        p1.getClass();
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        this.e = p5;
        return;
    }


// a ()Ljava/util/Map;

    public final java.util.Map a()
    {
        return kn2.h0(new c83[] {new c83("X-Device-Session-Id", this.a), new c83("X-Request-Timestamp", this.b), new c83("X-Request-Nonce", this.c), new c83("X-Body-SHA256", this.d), new c83("X-Device-Signature", this.e)}));
    }


// equals (Ljava/lang/Object;)Z

    public final boolean equals(Object p3)
    {
        if ((this == p3) || (((p3 instanceof q74)) && ((r02.T(this.a, ((q74) p3).a)) && ((this.b.equals(((q74) p3).b)) && ((this.c.equals(((q74) p3).c)) && ((this.d.equals(((q74) p3).d)) && (this.e.equals(((q74) p3).e)))))))) {
            return 1;
        } else {
            return 0;
        }
    }


// hashCode ()I

    public final int hashCode()
    {
        return (this.e.hashCode() + f62.i(f62.i(f62.i((this.a.hashCode() * 31), 31, this.b), 31, this.c), 31, this.d));
    }


// toString ()Ljava/lang/String;

    public final String toString()
    {
        StringBuilder v0_1 = f62.x("SignedRequestHeaders(deviceSessionId=", this.a, ", timestampMs=", this.b, ", nonce=");
        f62.C(v0_1, this.c, ", bodyHash=", this.d, ", signature=");
        return f62.p(this.e, ")", v0_1);
    }


