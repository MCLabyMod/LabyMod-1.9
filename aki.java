import java.util.Random;

public class aki
  extends amg
  implements ajv
{
  public static final arq a = arq.a("age", 0, 2);
  protected static final bbh[] b = { new bbh(0.6875D, 0.4375D, 0.375D, 0.9375D, 0.75D, 0.625D), new bbh(0.5625D, 0.3125D, 0.3125D, 0.9375D, 0.75D, 0.6875D), new bbh(0.5625D, 0.3125D, 0.3125D, 0.9375D, 0.75D, 0.6875D) };
  protected static final bbh[] c = { new bbh(0.0625D, 0.4375D, 0.375D, 0.3125D, 0.75D, 0.625D), new bbh(0.0625D, 0.3125D, 0.3125D, 0.4375D, 0.75D, 0.6875D), new bbh(0.0625D, 0.3125D, 0.3125D, 0.4375D, 0.75D, 0.6875D) };
  protected static final bbh[] d = { new bbh(0.375D, 0.4375D, 0.0625D, 0.625D, 0.75D, 0.3125D), new bbh(0.3125D, 0.3125D, 0.0625D, 0.6875D, 0.75D, 0.4375D), new bbh(0.3125D, 0.3125D, 0.0625D, 0.6875D, 0.75D, 0.4375D) };
  protected static final bbh[] e = { new bbh(0.375D, 0.4375D, 0.6875D, 0.625D, 0.75D, 0.9375D), new bbh(0.3125D, 0.3125D, 0.5625D, 0.6875D, 0.75D, 0.9375D), new bbh(0.3125D, 0.3125D, 0.5625D, 0.6875D, 0.75D, 0.9375D) };
  
  public aki()
  {
    super(axe.k);
    w(this.A.b().a(D, cq.c).a(a, Integer.valueOf(0)));
    a(true);
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if (!e(☃, ☃, ☃))
    {
      f(☃, ☃, ☃);
    }
    else if (☃.r.nextInt(5) == 0)
    {
      int ☃ = ((Integer)☃.c(a)).intValue();
      if (☃ < 2) {
        ☃.a(☃, ☃.a(a, Integer.valueOf(☃ + 1)), 2);
      }
    }
  }
  
  public boolean e(aht ☃, cj ☃, arc ☃)
  {
    ☃ = ☃.a((cq)☃.c(D));
    
    arc ☃ = ☃.o(☃);
    return (☃.t() == aju.r) && (☃.c(ang.b) == anj.a.d);
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    int ☃ = ((Integer)☃.c(a)).intValue();
    switch (aki.1.a[((cq)☃.c(D)).ordinal()])
    {
    case 1: 
      return e[☃];
    case 2: 
    default: 
      return d[☃];
    case 3: 
      return c[☃];
    }
    return b[☃];
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    return ☃.a(D, ☃.a((cq)☃.c(D)));
  }
  
  public arc a(arc ☃, amr ☃)
  {
    return ☃.a(☃.a((cq)☃.c(D)));
  }
  
  public void a(aht ☃, cj ☃, arc ☃, sa ☃, adq ☃)
  {
    cq ☃ = cq.a(☃.v);
    ☃.a(☃, ☃.a(D, ☃), 2);
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    if (!☃.k().c()) {
      ☃ = cq.c;
    }
    return u().a(D, ☃.d()).a(a, Integer.valueOf(0));
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if (!e(☃, ☃, ☃)) {
      f(☃, ☃, ☃);
    }
  }
  
  private void f(aht ☃, cj ☃, arc ☃)
  {
    ☃.a(☃, aju.a.u(), 3);
    b(☃, ☃, ☃, 0);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, float ☃, int ☃)
  {
    int ☃ = ((Integer)☃.c(a)).intValue();
    int ☃ = 1;
    if (☃ >= 2) {
      ☃ = 3;
    }
    for (int ☃ = 0; ☃ < ☃; ☃++) {
      a(☃, ☃, new adq(ads.bd, 1, act.m.b()));
    }
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(ads.bd, 1, act.m.b());
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, boolean ☃)
  {
    return ((Integer)☃.c(a)).intValue() < 2;
  }
  
  public boolean a(aht ☃, Random ☃, cj ☃, arc ☃)
  {
    return true;
  }
  
  public void b(aht ☃, Random ☃, cj ☃, arc ☃)
  {
    ☃.a(☃, ☃.a(a, Integer.valueOf(((Integer)☃.c(a)).intValue() + 1)), 2);
  }
  
  public ahm f()
  {
    return ahm.c;
  }
  
  public arc a(int ☃)
  {
    return u().a(D, cq.b(☃)).a(a, Integer.valueOf((☃ & 0xF) >> 2));
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    
    ☃ |= ((cq)☃.c(D)).b();
    ☃ |= ((Integer)☃.c(a)).intValue() << 2;
    
    return ☃;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { D, a });
  }
}
