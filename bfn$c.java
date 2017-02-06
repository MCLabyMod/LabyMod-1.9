import java.util.Comparator;

class bfn$c
  extends bfn.e
{
  /* Error */
  public bfn$c(bfn arg1, bcf ☃)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: putfield 17	bfn$c:u	Lbfn;
    //   5: aload_0
    //   6: aload_1
    //   7: aload_2
    //   8: invokespecial 19	bfn$e:<init>	(Lbfn;Lbcf;)V
    //   11: aload_0
    //   12: invokestatic 25	com/google/common/collect/Lists:newArrayList	()Ljava/util/ArrayList;
    //   15: putfield 29	bfn$c:w	Ljava/util/List;
    //   18: getstatic 34	nt:d	Ljava/util/List;
    //   21: invokeinterface 40 1 0
    //   26: astore_3
    //   27: aload_3
    //   28: invokeinterface 46 1 0
    //   33: ifeq +175 -> 208
    //   36: aload_3
    //   37: invokeinterface 50 1 0
    //   42: checkcast 52	nn
    //   45: astore 4
    //   47: iconst_0
    //   48: istore 5
    //   50: aload 4
    //   52: invokevirtual 56	nn:a	()Lado;
    //   55: astore 6
    //   57: aload_1
    //   58: invokestatic 60	bfn:b	(Lbfn;)Lnu;
    //   61: aload 4
    //   63: invokevirtual 65	nu:a	(Lnp;)I
    //   66: ifle +9 -> 75
    //   69: iconst_1
    //   70: istore 5
    //   72: goto +116 -> 188
    //   75: aload 6
    //   77: invokestatic 70	nt:c	(Lado;)Lnp;
    //   80: ifnull +24 -> 104
    //   83: aload_1
    //   84: invokestatic 60	bfn:b	(Lbfn;)Lnu;
    //   87: aload 6
    //   89: invokestatic 70	nt:c	(Lado;)Lnp;
    //   92: invokevirtual 65	nu:a	(Lnp;)I
    //   95: ifle +9 -> 104
    //   98: iconst_1
    //   99: istore 5
    //   101: goto +87 -> 188
    //   104: aload 6
    //   106: invokestatic 72	nt:a	(Lado;)Lnp;
    //   109: ifnull +24 -> 133
    //   112: aload_1
    //   113: invokestatic 60	bfn:b	(Lbfn;)Lnu;
    //   116: aload 6
    //   118: invokestatic 72	nt:a	(Lado;)Lnp;
    //   121: invokevirtual 65	nu:a	(Lnp;)I
    //   124: ifle +9 -> 133
    //   127: iconst_1
    //   128: istore 5
    //   130: goto +58 -> 188
    //   133: aload 6
    //   135: invokestatic 74	nt:d	(Lado;)Lnp;
    //   138: ifnull +24 -> 162
    //   141: aload_1
    //   142: invokestatic 60	bfn:b	(Lbfn;)Lnu;
    //   145: aload 6
    //   147: invokestatic 74	nt:d	(Lado;)Lnp;
    //   150: invokevirtual 65	nu:a	(Lnp;)I
    //   153: ifle +9 -> 162
    //   156: iconst_1
    //   157: istore 5
    //   159: goto +29 -> 188
    //   162: aload 6
    //   164: invokestatic 76	nt:e	(Lado;)Lnp;
    //   167: ifnull +21 -> 188
    //   170: aload_1
    //   171: invokestatic 60	bfn:b	(Lbfn;)Lnu;
    //   174: aload 6
    //   176: invokestatic 76	nt:e	(Lado;)Lnp;
    //   179: invokevirtual 65	nu:a	(Lnp;)I
    //   182: ifle +6 -> 188
    //   185: iconst_1
    //   186: istore 5
    //   188: iload 5
    //   190: ifeq +15 -> 205
    //   193: aload_0
    //   194: getfield 29	bfn$c:w	Ljava/util/List;
    //   197: aload 4
    //   199: invokeinterface 80 2 0
    //   204: pop
    //   205: goto -178 -> 27
    //   208: aload_0
    //   209: new 10	bfn$c$1
    //   212: dup
    //   213: aload_0
    //   214: aload_1
    //   215: invokespecial 83	bfn$c$1:<init>	(Lbfn$c;Lbfn;)V
    //   218: putfield 87	bfn$c:x	Ljava/util/Comparator;
    //   221: return
    // Line number table:
    //   Java source line #475	-> byte code offset #0
    //   Java source line #476	-> byte code offset #5
    //   Java source line #478	-> byte code offset #11
    //   Java source line #479	-> byte code offset #18
    //   Java source line #480	-> byte code offset #47
    //   Java source line #481	-> byte code offset #50
    //   Java source line #483	-> byte code offset #57
    //   Java source line #484	-> byte code offset #69
    //   Java source line #485	-> byte code offset #75
    //   Java source line #486	-> byte code offset #98
    //   Java source line #487	-> byte code offset #104
    //   Java source line #488	-> byte code offset #127
    //   Java source line #489	-> byte code offset #133
    //   Java source line #490	-> byte code offset #156
    //   Java source line #491	-> byte code offset #162
    //   Java source line #492	-> byte code offset #185
    //   Java source line #494	-> byte code offset #188
    //   Java source line #495	-> byte code offset #193
    //   Java source line #497	-> byte code offset #205
    //   Java source line #499	-> byte code offset #208
    //   Java source line #544	-> byte code offset #221
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	222	0	this	c
    //   1	214	1	???	bfn
    //   0	222	2	☃	bcf
    //   26	182	3	☃	java.util.Iterator
    //   45	153	4	☃	nn
    //   48	141	5	☃	boolean
    //   55	120	6	☃	ado
  }
  
  protected void a(int ☃, int ☃, bnu ☃)
  {
    super.a(☃, ☃, ☃);
    if (this.v == 0) {
      bfn.a(this.u, ☃ + 115 - 18 + 1, ☃ + 1 + 1, 72, 18);
    } else {
      bfn.a(this.u, ☃ + 115 - 18, ☃ + 1, 72, 18);
    }
    if (this.v == 1) {
      bfn.a(this.u, ☃ + 165 - 18 + 1, ☃ + 1 + 1, 18, 18);
    } else {
      bfn.a(this.u, ☃ + 165 - 18, ☃ + 1, 18, 18);
    }
    if (this.v == 2) {
      bfn.a(this.u, ☃ + 215 - 18 + 1, ☃ + 1 + 1, 36, 18);
    } else {
      bfn.a(this.u, ☃ + 215 - 18, ☃ + 1, 36, 18);
    }
    if (this.v == 3) {
      bfn.a(this.u, ☃ + 265 - 18 + 1, ☃ + 1 + 1, 90, 18);
    } else {
      bfn.a(this.u, ☃ + 265 - 18, ☃ + 1, 90, 18);
    }
    if (this.v == 4) {
      bfn.a(this.u, ☃ + 315 - 18 + 1, ☃ + 1 + 1, 108, 18);
    } else {
      bfn.a(this.u, ☃ + 315 - 18, ☃ + 1, 108, 18);
    }
  }
  
  protected void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    nn ☃ = c(☃);
    
    ado ☃ = ☃.a();
    bfn.a(this.u, ☃ + 40, ☃, ☃);
    
    a(nt.c(☃), ☃ + 115, ☃, ☃ % 2 == 0);
    a(nt.a(☃), ☃ + 165, ☃, ☃ % 2 == 0);
    a(☃, ☃ + 215, ☃, ☃ % 2 == 0);
    a(nt.d(☃), ☃ + 265, ☃, ☃ % 2 == 0);
    a(nt.e(☃), ☃ + 315, ☃, ☃ % 2 == 0);
  }
  
  protected String b(int ☃)
  {
    if (☃ == 1) {
      return "stat.crafted";
    }
    if (☃ == 2) {
      return "stat.used";
    }
    if (☃ == 3) {
      return "stat.pickup";
    }
    if (☃ == 4) {
      return "stat.dropped";
    }
    return "stat.depleted";
  }
}
