import java.util.List;
import java.util.Random;

public class aqv
  extends aks
{
  public static final arp<aqv.a> a = arp.a("type", aqv.a.class);
  public static final arn b = arn.a("short");
  protected static final bbh c = new bbh(0.75D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
  protected static final bbh d = new bbh(0.0D, 0.0D, 0.0D, 0.25D, 1.0D, 1.0D);
  protected static final bbh e = new bbh(0.0D, 0.0D, 0.75D, 1.0D, 1.0D, 1.0D);
  protected static final bbh f = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.25D);
  protected static final bbh g = new bbh(0.0D, 0.75D, 0.0D, 1.0D, 1.0D, 1.0D);
  protected static final bbh B = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);
  protected static final bbh C = new bbh(0.375D, -0.25D, 0.375D, 0.625D, 0.75D, 0.625D);
  protected static final bbh D = new bbh(0.375D, 0.25D, 0.375D, 0.625D, 1.25D, 0.625D);
  protected static final bbh E = new bbh(0.375D, 0.375D, -0.25D, 0.625D, 0.625D, 0.75D);
  protected static final bbh F = new bbh(0.375D, 0.375D, 0.25D, 0.625D, 0.625D, 1.25D);
  protected static final bbh G = new bbh(-0.25D, 0.375D, 0.375D, 0.75D, 0.625D, 0.625D);
  protected static final bbh I = new bbh(0.25D, 0.375D, 0.375D, 1.25D, 0.625D, 0.625D);
  
  public aqv()
  {
    super(axe.H);
    w(this.A.b().a(H, cq.c).a(a, aqv.a.a).a(b, Boolean.valueOf(false)));
    a(aop.d);
    c(0.5F);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    switch (aqv.1.a[((cq)☃.c(H)).ordinal()])
    {
    case 1: 
    default: 
      return B;
    case 2: 
      return g;
    case 3: 
      return f;
    case 4: 
      return e;
    case 5: 
      return d;
    }
    return c;
  }
  
  public void a(arc ☃, aht ☃, cj ☃, bbh ☃, List<bbh> ☃, rr ☃)
  {
    a(☃, ☃, ☃, ☃.c(☃, ☃));
    a(☃, ☃, ☃, i(☃));
  }
  
  private bbh i(arc ☃)
  {
    switch (aqv.1.a[((cq)☃.c(H)).ordinal()])
    {
    case 1: 
    default: 
      return D;
    case 2: 
      return C;
    case 3: 
      return F;
    case 4: 
      return E;
    case 5: 
      return I;
    }
    return G;
  }
  
  public boolean k(arc ☃)
  {
    return ☃.c(H) == cq.b;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, zj ☃)
  {
    if (☃.bJ.d)
    {
      cj ☃ = ☃.a(((cq)☃.c(H)).d());
      ajt ☃ = ☃.o(☃).t();
      if ((☃ == aju.J) || (☃ == aju.F)) {
        ☃.g(☃);
      }
    }
    super.a(☃, ☃, ☃, ☃);
  }
  
  public void b(aht ☃, cj ☃, arc ☃)
  {
    super.b(☃, ☃, ☃);
    cq ☃ = ((cq)☃.c(H)).d();
    ☃ = ☃.a(☃);
    
    arc ☃ = ☃.o(☃);
    if (((☃.t() == aju.J) || (☃.t() == aju.F)) && 
      (((Boolean)☃.c(aqu.a)).booleanValue()))
    {
      ☃.t().b(☃, ☃, ☃, 0);
      ☃.g(☃);
    }
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    return false;
  }
  
  public boolean b(aht ☃, cj ☃, cq ☃)
  {
    return false;
  }
  
  public int a(Random ☃)
  {
    return 0;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    cq ☃ = (cq)☃.c(H);
    cj ☃ = ☃.a(☃.d());
    arc ☃ = ☃.o(☃);
    if ((☃.t() != aju.J) && (☃.t() != aju.F)) {
      ☃.g(☃);
    } else {
      ☃.t().a(☃, ☃, ☃, ☃);
    }
  }
  
  public boolean a(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    return true;
  }
  
  public static cq e(int ☃)
  {
    int ☃ = ☃ & 0x7;
    if (☃ > 5) {
      return null;
    }
    return cq.a(☃);
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(☃.c(a) == aqv.a.b ? aju.F : aju.J);
  }
  
  public arc a(int ☃)
  {
    return u().a(H, e(☃)).a(a, (☃ & 0x8) > 0 ? aqv.a.b : aqv.a.a);
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    
    ☃ |= ((cq)☃.c(H)).a();
    if (☃.c(a) == aqv.a.b) {
      ☃ |= 0x8;
    }
    return ☃;
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    return ☃.a(H, ☃.a((cq)☃.c(H)));
  }
  
  public arc a(arc ☃, amr ☃)
  {
    return ☃.a(☃.a((cq)☃.c(H)));
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { H, a, b });
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
