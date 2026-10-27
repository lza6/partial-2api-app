// y04 — 还原自 classes4.dex (androguard decompile)
// 混淆原名: y04

// <init> (J Ljava/lang/String; Ljava/lang/String; Ljava/lang/String;)V

    public y04(long p1, String p3, String p4, String p5)
    {
        p3.getClass();
        p5.getClass();
        this.a = p3;
        this.b = p1;
        this.c = p4;
        this.d = p5;
        return;
    }


// equals (Ljava/lang/Object;)Z

    public final boolean equals(Object p5)
    {
        if ((this == p5) || (((p5 instanceof y04)) && ((r02.T(this.a, ((y04) p5).a)) && ((this.b == ((y04) p5).b) && ((this.c.equals(((y04) p5).c)) && (r02.T(this.d, ((y04) p5).d))))))) {
            return 1;
        } else {
            return 0;
        }
    }


// hashCode ()I

    public final int hashCode()
    {
        return (this.d.hashCode() + f62.i(f62.g((this.a.hashCode() * 31), 31, this.b), 31, this.c));
    }


// toString ()Ljava/lang/String;

    public final String toString()
    {
        StringBuilder v0_1 = new StringBuilder("DeviceSession(id=");
        v0_1.append(this.a);
        v0_1.append(", expiresAtMillis=");
        v0_1.append(this.b);
        f62.C(v0_1, ", signingKeyVersion=", this.c, ", rawJson=", this.d);
        v0_1.append(")");
        return v0_1.toString();
    }


