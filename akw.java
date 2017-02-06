import java.util.List;
import java.util.Random;

public class akw
  extends ajy
  implements ajv
{
  public static final arp<akw.b> a = arp.a("variant", akw.b.class);
  public static final arp<akw.a> c = arp.a("half", akw.a.class);
  public static final arp<cq> d = amg.D;
  
  public akw()
  {
    super(axe.l);
    w(this.A.b().a(a, akw.b.a).a(c, akw.a.b).a(d, cq.c));
    c(0.0F);
    a(aop.c);
    c("doublePlant");
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return j;
  }
  
  private akw.b a(ahx ☃, cj ☃, arc ☃)
  {
    if (☃.t() == this)
    {
      ☃ = ☃.b(☃, ☃);
      
      return (akw.b)☃.c(a);
    }
    return akw.b.d;
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    return (super.a(☃, ☃)) && (☃.d(☃.a()));
  }
  
  public boolean a(ahx ☃, cj ☃)
  {
    arc ☃ = ☃.o(☃);
    if (☃.t() == this)
    {
      akw.b ☃ = (akw.b)☃.b(☃, ☃).c(a);
      return (☃ == akw.b.d) || (☃ == akw.b.c);
    }
    return true;
  }
  
  protected void e(aht ☃, cj ☃, arc ☃)
  {
    if (f(☃, ☃, ☃)) {
      return;
    }
    boolean ☃ = ☃.c(c) == akw.a.a;
    cj ☃ = ☃ ? ☃ : ☃.a();
    cj ☃ = ☃ ? ☃.b() : ☃;
    
    ajt ☃ = ☃ ? this : ☃.o(☃).t();
    ajt ☃ = ☃ ? ☃.o(☃).t() : this;
    if (☃ == this) {
      ☃.a(☃, aju.a.u(), 2);
    }
    if (☃ == this)
    {
      ☃.a(☃, aju.a.u(), 3);
      if (!☃) {
        b(☃, ☃, ☃, 0);
      }
    }
  }
  
  public boolean f(aht ☃, cj ☃, arc ☃)
  {
    if (☃.c(c) == akw.a.a) {
      return ☃.o(☃.b()).t() == this;
    }
    arc ☃ = ☃.o(☃.a());
    return (☃.t() == this) && (super.f(☃, ☃, ☃));
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    if (☃.c(c) == akw.a.a) {
      return null;
    }
    akw.b ☃ = (akw.b)☃.c(a);
    if (☃ == akw.b.d) {
      return null;
    }
    if (☃ == akw.b.c)
    {
      if (☃.nextInt(8) == 0) {
        return ads.P;
      }
      return null;
    }
    return ado.a(this);
  }
  
  public int d(arc ☃)
  {
    if ((☃.c(c) == akw.a.a) || (☃.c(a) == akw.b.c)) {
      return 0;
    }
    return ((akw.b)☃.c(a)).a();
  }
  
  public void a(aht ☃, cj ☃, akw.b ☃, int ☃)
  {
    ☃.a(☃, u().a(c, akw.a.b).a(a, ☃), ☃);
    ☃.a(☃.a(), u().a(c, akw.a.a), ☃);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, sa ☃, adq ☃)
  {
    ☃.a(☃.a(), u().a(c, akw.a.a), 2);
  }
  
  public void a(aht ☃, zj ☃, cj ☃, arc ☃, apv ☃, adq ☃)
  {
    if ((!☃.E) && (☃ != null) && (☃.b() == ads.bl)) {
      if ((☃.c(c) == akw.a.b) && 
        (b(☃, ☃, ☃, ☃))) {
        return;
      }
    }
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, zj ☃)
  {
    if (☃.c(c) == akw.a.a)
    {
      if (☃.o(☃.b()).t() == this) {
        if (!☃.bJ.d)
        {
          arc ☃ = ☃.o(☃.b());
          akw.b ☃ = (akw.b)☃.c(a);
          if ((☃ == akw.b.d) || (☃ == akw.b.c))
          {
            if (!☃.E)
            {
              if ((☃.cb() != null) && (☃.cb().b() == ads.bl))
              {
                b(☃, ☃, ☃, ☃);
                ☃.g(☃.b());
              }
              else
              {
                ☃.b(☃.b(), true);
              }
            }
            else {
              ☃.g(☃.b());
            }
          }
          else {
            ☃.b(☃.b(), true);
          }
        }
        else
        {
          ☃.g(☃.b());
        }
      }
    }
    else if (☃.o(☃.a()).t() == this) {
      ☃.a(☃.a(), aju.a.u(), 2);
    }
    super.a(☃, ☃, ☃, ☃);
  }
  
  private boolean b(aht ☃, cj ☃, arc ☃, zj ☃)
  {
    akw.b ☃ = (akw.b)☃.c(a);
    if ((☃ == akw.b.d) || (☃ == akw.b.c))
    {
      ☃.b(nt.a(this));
      
      int ☃ = (☃ == akw.b.c ? apc.a.b : apc.a.c).a();
      a(☃, ☃, new adq(aju.H, 2, ☃));
      return true;
    }
    return false;
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    for (akw.b ☃ : ) {
      ☃.add(new adq(☃, 1, ☃.a()));
    }
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(this, 1, a(☃, ☃, ☃).a());
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, boolean ☃)
  {
    akw.b ☃ = a(☃, ☃, ☃);
    
    return (☃ != akw.b.c) && (☃ != akw.b.d);
  }
  
  public boolean a(aht ☃, Random ☃, cj ☃, arc ☃)
  {
    return true;
  }
  
  public void b(aht ☃, Random ☃, cj ☃, arc ☃)
  {
    a(☃, ☃, new adq(this, 1, a(☃, ☃, ☃).a()));
  }
  
  public arc a(int ☃)
  {
    if ((☃ & 0x8) > 0) {
      return u().a(c, akw.a.a);
    }
    return u().a(c, akw.a.b).a(a, akw.b.a(☃ & 0x7));
  }
  
  public arc b(arc ☃, ahx ☃, cj ☃)
  {
    if (☃.c(c) == akw.a.a)
    {
      arc ☃ = ☃.o(☃.b());
      if (☃.t() == this) {
        ☃ = ☃.a(a, ☃.c(a));
      }
    }
    return ☃;
  }
  
  public int e(arc ☃)
  {
    if (☃.c(c) == akw.a.a) {
      return 0x8 | ((cq)☃.c(d)).b();
    }
    return ((akw.b)☃.c(a)).a();
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { c, a, d });
  }
  
  public static enum b
    implements or
  {
    private static final b[] g;
    private final int h;
    private final String i;
    private final String j;
    
    static
    {
      g = new b[values().length];
      for (b ☃ : values()) {
        g[☃.a()] = ☃;
      }
    }
    
    private b(int ☃, String ☃)
    {
      this(☃, ☃, ☃);
    }
    
    private b(int ☃, String ☃, String ☃)
    {
      this.h = ☃;
      this.i = ☃;
      this.j = ☃;
    }
    
    public int a()
    {
      return this.h;
    }
    
    public String toString()
    {
      return this.i;
    }
    
    public static b a(int ☃)
    {
      if ((☃ < 0) || (☃ >= g.length)) {
        ☃ = 0;
      }
      return g[☃];
    }
    
    public String m()
    {
      return this.i;
    }
    
    public String c()
    {
      return this.j;
    }
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
  
  public ajt.a v()
  {
    return ajt.a.b;
  }
}
