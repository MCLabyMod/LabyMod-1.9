public class apg
  extends ajt
{
  public static final aro a = amg.D;
  public static final arn b = arn.a("open");
  public static final arp<apg.a> c = arp.a("half", apg.a.class);
  protected static final bbh d = new bbh(0.0D, 0.0D, 0.0D, 0.1875D, 1.0D, 1.0D);
  protected static final bbh e = new bbh(0.8125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
  protected static final bbh f = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1875D);
  protected static final bbh g = new bbh(0.0D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D);
  protected static final bbh B = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D);
  protected static final bbh C = new bbh(0.0D, 0.8125D, 0.0D, 1.0D, 1.0D, 1.0D);
  
  protected apg(axe ☃)
  {
    super(☃);
    w(this.A.b().a(a, cq.c).a(b, Boolean.valueOf(false)).a(c, apg.a.b));
    float ☃ = 0.5F;
    float ☃ = 1.0F;
    a(acq.d);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    bbh ☃;
    if (((Boolean)☃.c(b)).booleanValue())
    {
      bbh ☃;
      switch (apg.1.a[((cq)☃.c(a)).ordinal()])
      {
      case 1: 
      default: 
        ☃ = g;
        break;
      case 2: 
        ☃ = f;
        break;
      case 3: 
        ☃ = e;
        break;
      case 4: 
        ☃ = d;
        break;
      }
    }
    else
    {
      bbh ☃;
      if (☃.c(c) == apg.a.a) {
        ☃ = C;
      } else {
        ☃ = B;
      }
    }
    return ☃;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean b(ahx ☃, cj ☃)
  {
    return !((Boolean)☃.o(☃).c(b)).booleanValue();
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (this.x == axe.f) {
      return true;
    }
    ☃ = ☃.a(b);
    ☃.a(☃, ☃, 2);
    
    a(☃, ☃, ☃, ((Boolean)☃.c(b)).booleanValue());
    return true;
  }
  
  protected void a(zj ☃, aht ☃, cj ☃, boolean ☃)
  {
    if (☃)
    {
      int ☃ = this.x == axe.f ? 1037 : 1007;
      ☃.a(☃, ☃, ☃, 0);
    }
    else
    {
      int ☃ = this.x == axe.f ? 1036 : 1013;
      ☃.a(☃, ☃, ☃, 0);
    }
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if (☃.E) {
      return;
    }
    boolean ☃ = ☃.y(☃);
    if ((☃) || (☃.u().m()))
    {
      boolean ☃ = ((Boolean)☃.c(b)).booleanValue();
      if (☃ != ☃)
      {
        ☃.a(☃, ☃.a(b, Boolean.valueOf(☃)), 2);
        a(null, ☃, ☃, ☃);
      }
    }
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    arc ☃ = u();
    if (☃.k().c())
    {
      ☃ = ☃.a(a, ☃).a(b, Boolean.valueOf(false));
      ☃ = ☃.a(c, ☃ > 0.5F ? apg.a.a : apg.a.b);
    }
    else
    {
      ☃ = ☃.a(a, ☃.bi().d()).a(b, Boolean.valueOf(false));
      ☃ = ☃.a(c, ☃ == cq.b ? apg.a.b : apg.a.a);
    }
    return ☃;
  }
  
  public boolean b(aht ☃, cj ☃, cq ☃)
  {
    return true;
  }
  
  protected static cq e(int ☃)
  {
    switch (☃ & 0x3)
    {
    case 0: 
      return cq.c;
    case 1: 
      return cq.d;
    case 2: 
      return cq.e;
    }
    return cq.f;
  }
  
  protected static int a(cq ☃)
  {
    switch (apg.1.a[☃.ordinal()])
    {
    case 1: 
      return 0;
    case 2: 
      return 1;
    case 3: 
      return 2;
    }
    return 3;
  }
  
  public ahm f()
  {
    return ahm.c;
  }
  
  public arc a(int ☃)
  {
    return u().a(a, e(☃)).a(b, Boolean.valueOf((☃ & 0x4) != 0)).a(c, (☃ & 0x8) == 0 ? apg.a.b : apg.a.a);
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    
    ☃ |= a((cq)☃.c(a));
    if (((Boolean)☃.c(b)).booleanValue()) {
      ☃ |= 0x4;
    }
    if (☃.c(c) == apg.a.a) {
      ☃ |= 0x8;
    }
    return ☃;
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    return ☃.a(a, ☃.a((cq)☃.c(a)));
  }
  
  public arc a(arc ☃, amr ☃)
  {
    return ☃.a(☃.a((cq)☃.c(a)));
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a, b, c });
  }
  
  public static enum a
    implements or
  {
    private final String c;
    
    private a(String ☃)
    {
      this.c = ☃;
    }
    
    public String toString()
    {
      return this.c;
    }
    
    public String m()
    {
      return this.c;
    }
  }
}
