import java.util.Random;

public class akv
  extends ajt
{
  public static final aro a = amg.D;
  public static final arn b = arn.a("open");
  public static final arp<akv.b> c = arp.a("hinge", akv.b.class);
  public static final arn d = arn.a("powered");
  public static final arp<akv.a> e = arp.a("half", akv.a.class);
  protected static final bbh f = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1875D);
  protected static final bbh g = new bbh(0.0D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D);
  protected static final bbh B = new bbh(0.8125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
  protected static final bbh C = new bbh(0.0D, 0.0D, 0.0D, 0.1875D, 1.0D, 1.0D);
  
  protected akv(axe ☃)
  {
    super(☃);
    w(this.A.b().a(a, cq.c).a(b, Boolean.valueOf(false)).a(c, akv.b.a).a(d, Boolean.valueOf(false)).a(e, akv.a.b));
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    ☃ = ☃.b(☃, ☃);
    
    cq ☃ = (cq)☃.c(a);
    boolean ☃ = !((Boolean)☃.c(b)).booleanValue();
    boolean ☃ = ☃.c(c) == akv.b.b;
    switch (akv.1.a[☃.ordinal()])
    {
    case 1: 
    default: 
      return ☃ ? g : ☃ ? C : f;
    case 2: 
      return ☃ ? C : ☃ ? f : B;
    case 3: 
      return ☃ ? f : ☃ ? B : g;
    }
    return ☃ ? B : ☃ ? g : C;
  }
  
  public String c()
  {
    return di.a((a() + ".name").replaceAll("tile", "item"));
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean b(ahx ☃, cj ☃)
  {
    return g(c(☃, ☃));
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  private int e()
  {
    return this.x == axe.f ? 1011 : 1012;
  }
  
  private int g()
  {
    return this.x == axe.f ? 1005 : 1006;
  }
  
  public axf r(arc ☃)
  {
    if (☃.t() == aju.aA) {
      return axf.h;
    }
    if (☃.t() == aju.ao) {
      return anj.a.a.c();
    }
    if (☃.t() == aju.ap) {
      return anj.a.b.c();
    }
    if (☃.t() == aju.aq) {
      return anj.a.c.c();
    }
    if (☃.t() == aju.ar) {
      return anj.a.d.c();
    }
    if (☃.t() == aju.as) {
      return anj.a.e.c();
    }
    if (☃.t() == aju.at) {
      return anj.a.f.c();
    }
    return super.r(☃);
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (this.x == axe.f) {
      return true;
    }
    cj ☃ = ☃.c(e) == akv.a.b ? ☃ : ☃.b();
    arc ☃ = ☃.equals(☃) ? ☃ : ☃.o(☃);
    if (☃.t() != this) {
      return false;
    }
    ☃ = ☃.a(b);
    ☃.a(☃, ☃, 10);
    ☃.b(☃, ☃);
    
    ☃.a(☃, ((Boolean)☃.c(b)).booleanValue() ? g() : e(), ☃, 0);
    return true;
  }
  
  public void a(aht ☃, cj ☃, boolean ☃)
  {
    arc ☃ = ☃.o(☃);
    if (☃.t() != this) {
      return;
    }
    cj ☃ = ☃.c(e) == akv.a.b ? ☃ : ☃.b();
    arc ☃ = ☃ == ☃ ? ☃ : ☃.o(☃);
    if ((☃.t() == this) && (((Boolean)☃.c(b)).booleanValue() != ☃))
    {
      ☃.a(☃, ☃.a(b, Boolean.valueOf(☃)), 10);
      ☃.b(☃, ☃);
      
      ☃.a(null, ☃ ? g() : e(), ☃, 0);
    }
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if (☃.c(e) == akv.a.a)
    {
      cj ☃ = ☃.b();
      arc ☃ = ☃.o(☃);
      if (☃.t() != this) {
        ☃.g(☃);
      } else if (☃ != this) {
        a(☃, ☃, ☃, ☃);
      }
    }
    else
    {
      boolean ☃ = false;
      cj ☃ = ☃.a();
      arc ☃ = ☃.o(☃);
      if (☃.t() != this)
      {
        ☃.g(☃);
        ☃ = true;
      }
      if (!☃.o(☃.b()).q())
      {
        ☃.g(☃);
        ☃ = true;
        if (☃.t() == this) {
          ☃.g(☃);
        }
      }
      if (☃)
      {
        if (!☃.E) {
          b(☃, ☃, ☃, 0);
        }
      }
      else
      {
        boolean ☃ = (☃.y(☃)) || (☃.y(☃));
        if ((☃ != this) && ((☃) || (☃.u().m())) && 
          (☃ != ((Boolean)☃.c(d)).booleanValue()))
        {
          ☃.a(☃, ☃.a(d, Boolean.valueOf(☃)), 2);
          if (☃ != ((Boolean)☃.c(b)).booleanValue())
          {
            ☃.a(☃, ☃.a(b, Boolean.valueOf(☃)), 2);
            ☃.b(☃, ☃);
            ☃.a(null, ☃ ? g() : e(), ☃, 0);
          }
        }
      }
    }
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    if (☃.c(e) == akv.a.a) {
      return null;
    }
    return h();
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    if (☃.q() >= 255) {
      return false;
    }
    return (☃.o(☃.b()).q()) && (super.a(☃, ☃)) && (super.a(☃, ☃.a()));
  }
  
  public axh h(arc ☃)
  {
    return axh.b;
  }
  
  public static int c(ahx ☃, cj ☃)
  {
    arc ☃ = ☃.o(☃);
    int ☃ = ☃.t().e(☃);
    boolean ☃ = i(☃);
    
    arc ☃ = ☃.o(☃.b());
    int ☃ = ☃.t().e(☃);
    int ☃ = ☃ ? ☃ : ☃;
    arc ☃ = ☃.o(☃.a());
    int ☃ = ☃.t().e(☃);
    int ☃ = ☃ ? ☃ : ☃;
    
    boolean ☃ = (☃ & 0x1) != 0;
    boolean ☃ = (☃ & 0x2) != 0;
    return e(☃) | (☃ ? 8 : 0) | (☃ ? 16 : 0) | (☃ ? 32 : 0);
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(h());
  }
  
  private ado h()
  {
    if (this == aju.aA) {
      return ads.aD;
    }
    if (this == aju.ap) {
      return ads.at;
    }
    if (this == aju.aq) {
      return ads.au;
    }
    if (this == aju.ar) {
      return ads.av;
    }
    if (this == aju.as) {
      return ads.aw;
    }
    if (this == aju.at) {
      return ads.ax;
    }
    return ads.as;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, zj ☃)
  {
    cj ☃ = ☃.b();
    cj ☃ = ☃.a();
    if ((☃.bJ.d) && (☃.c(e) == akv.a.a) && (☃.o(☃).t() == this)) {
      ☃.g(☃);
    }
    if ((☃.c(e) == akv.a.b) && (☃.o(☃).t() == this))
    {
      if (☃.bJ.d) {
        ☃.g(☃);
      }
      ☃.g(☃);
    }
  }
  
  public ahm f()
  {
    return ahm.c;
  }
  
  public arc b(arc ☃, ahx ☃, cj ☃)
  {
    if (☃.c(e) == akv.a.b)
    {
      arc ☃ = ☃.o(☃.a());
      if (☃.t() == this) {
        ☃ = ☃.a(c, ☃.c(c)).a(d, ☃.c(d));
      }
    }
    else
    {
      arc ☃ = ☃.o(☃.b());
      if (☃.t() == this) {
        ☃ = ☃.a(a, ☃.c(a)).a(b, ☃.c(b));
      }
    }
    return ☃;
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    if (☃.c(e) != akv.a.b) {
      return ☃;
    }
    return ☃.a(a, ☃.a((cq)☃.c(a)));
  }
  
  public arc a(arc ☃, amr ☃)
  {
    return ☃.a(☃.a((cq)☃.c(a)));
  }
  
  public arc a(int ☃)
  {
    if ((☃ & 0x8) > 0) {
      return u().a(e, akv.a.a).a(c, (☃ & 0x1) > 0 ? akv.b.b : akv.b.a).a(d, Boolean.valueOf((☃ & 0x2) > 0));
    }
    return u().a(e, akv.a.b).a(a, cq.b(☃ & 0x3).f()).a(b, Boolean.valueOf((☃ & 0x4) > 0));
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    if (☃.c(e) == akv.a.a)
    {
      ☃ |= 0x8;
      if (☃.c(c) == akv.b.b) {
        ☃ |= 0x1;
      }
      if (((Boolean)☃.c(d)).booleanValue()) {
        ☃ |= 0x2;
      }
    }
    else
    {
      ☃ |= ((cq)☃.c(a)).e().b();
      if (((Boolean)☃.c(b)).booleanValue()) {
        ☃ |= 0x4;
      }
    }
    return ☃;
  }
  
  public static enum a
    implements or
  {
    private a() {}
    
    public String toString()
    {
      return m();
    }
    
    public String m()
    {
      return this == a ? "upper" : "lower";
    }
  }
  
  public static enum b
    implements or
  {
    private b() {}
    
    public String toString()
    {
      return m();
    }
    
    public String m()
    {
      return this == a ? "left" : "right";
    }
  }
  
  protected static int e(int ☃)
  {
    return ☃ & 0x7;
  }
  
  public static boolean d(ahx ☃, cj ☃)
  {
    return g(c(☃, ☃));
  }
  
  public static cq f(ahx ☃, cj ☃)
  {
    return f(c(☃, ☃));
  }
  
  public static cq f(int ☃)
  {
    return cq.b(☃ & 0x3).f();
  }
  
  protected static boolean g(int ☃)
  {
    return (☃ & 0x4) != 0;
  }
  
  protected static boolean i(int ☃)
  {
    return (☃ & 0x8) != 0;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { e, a, b, c, d });
  }
}
