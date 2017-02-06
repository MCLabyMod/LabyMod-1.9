import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class aot
  extends ajt
{
  public static final aro a = amg.D;
  public static final arp<aot.a> b = arp.a("half", aot.a.class);
  public static final arp<aot.b> c = arp.a("shape", aot.b.class);
  protected static final bbh d = new bbh(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);
  protected static final bbh e = new bbh(0.0D, 0.5D, 0.0D, 0.5D, 1.0D, 1.0D);
  protected static final bbh f = new bbh(0.5D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);
  protected static final bbh g = new bbh(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 0.5D);
  protected static final bbh B = new bbh(0.0D, 0.5D, 0.5D, 1.0D, 1.0D, 1.0D);
  protected static final bbh C = new bbh(0.0D, 0.5D, 0.0D, 0.5D, 1.0D, 0.5D);
  protected static final bbh D = new bbh(0.5D, 0.5D, 0.0D, 1.0D, 1.0D, 0.5D);
  protected static final bbh E = new bbh(0.0D, 0.5D, 0.5D, 0.5D, 1.0D, 1.0D);
  protected static final bbh F = new bbh(0.5D, 0.5D, 0.5D, 1.0D, 1.0D, 1.0D);
  protected static final bbh G = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
  protected static final bbh H = new bbh(0.0D, 0.0D, 0.0D, 0.5D, 0.5D, 1.0D);
  protected static final bbh I = new bbh(0.5D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
  protected static final bbh J = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 0.5D);
  protected static final bbh K = new bbh(0.0D, 0.0D, 0.5D, 1.0D, 0.5D, 1.0D);
  protected static final bbh L = new bbh(0.0D, 0.0D, 0.0D, 0.5D, 0.5D, 0.5D);
  protected static final bbh M = new bbh(0.5D, 0.0D, 0.0D, 1.0D, 0.5D, 0.5D);
  protected static final bbh N = new bbh(0.0D, 0.0D, 0.5D, 0.5D, 0.5D, 1.0D);
  protected static final bbh O = new bbh(0.5D, 0.0D, 0.5D, 1.0D, 0.5D, 1.0D);
  private final ajt P;
  private final arc Q;
  
  protected aot(arc ☃)
  {
    super(☃.t().x);
    w(this.A.b().a(a, cq.c).a(b, aot.a.b).a(c, aot.b.a));
    this.P = ☃.t();
    this.Q = ☃;
    c(this.P.q);
    b(this.P.r / 3.0F);
    a(this.P.v);
    d(255);
    a(acq.b);
  }
  
  public void a(arc ☃, aht ☃, cj ☃, bbh ☃, List<bbh> ☃, rr ☃)
  {
    ☃ = b(☃, ☃, ☃);
    for (bbh ☃ : x(☃)) {
      a(☃, ☃, ☃, ☃);
    }
  }
  
  private static List<bbh> x(arc ☃)
  {
    List<bbh> ☃ = Lists.newArrayList();
    
    boolean ☃ = ☃.c(b) == aot.a.a;
    ☃.add(☃ ? d : G);
    
    aot.b ☃ = (aot.b)☃.c(c);
    if ((☃ == aot.b.a) || (☃ == aot.b.b) || (☃ == aot.b.c)) {
      ☃.add(y(☃));
    }
    if (☃ != aot.b.a) {
      ☃.add(z(☃));
    }
    return ☃;
  }
  
  private static bbh y(arc ☃)
  {
    boolean ☃ = ☃.c(b) == aot.a.a;
    switch (aot.1.a[((cq)☃.c(a)).ordinal()])
    {
    case 1: 
    default: 
      return ☃ ? J : g;
    case 2: 
      return ☃ ? K : B;
    case 3: 
      return ☃ ? H : e;
    }
    return ☃ ? I : f;
  }
  
  private static bbh z(arc ☃)
  {
    cq ☃ = (cq)☃.c(a);
    cq ☃;
    switch (aot.1.b[((aot.b)☃.c(c)).ordinal()])
    {
    case 1: 
    default: 
      ☃ = ☃;
      break;
    case 2: 
      ☃ = ☃.e();
      break;
    case 3: 
      ☃ = ☃.d();
      break;
    case 4: 
      ☃ = ☃.f();
    }
    boolean ☃ = ☃.c(b) == aot.a.a;
    switch (aot.1.a[☃.ordinal()])
    {
    case 1: 
    default: 
      return ☃ ? L : C;
    case 4: 
      return ☃ ? M : D;
    case 2: 
      return ☃ ? O : F;
    }
    return ☃ ? N : E;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public void a(arc ☃, aht ☃, cj ☃, Random ☃)
  {
    this.P.a(☃, ☃, ☃, ☃);
  }
  
  public void a(aht ☃, cj ☃, zj ☃)
  {
    this.P.a(☃, ☃, ☃);
  }
  
  public void d(aht ☃, cj ☃, arc ☃)
  {
    this.P.d(☃, ☃, ☃);
  }
  
  public int c(arc ☃, ahx ☃, cj ☃)
  {
    return this.Q.a(☃, ☃);
  }
  
  public float a(rr ☃)
  {
    return this.P.a(☃);
  }
  
  public ahm f()
  {
    return this.P.f();
  }
  
  public int a(aht ☃)
  {
    return this.P.a(☃);
  }
  
  public bbh c(arc ☃, aht ☃, cj ☃)
  {
    return this.Q.c(☃, ☃);
  }
  
  public bbj a(aht ☃, cj ☃, rr ☃, bbj ☃)
  {
    return this.P.a(☃, ☃, ☃, ☃);
  }
  
  public boolean n()
  {
    return this.P.n();
  }
  
  public boolean a(arc ☃, boolean ☃)
  {
    return this.P.a(☃, ☃);
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    return this.P.a(☃, ☃);
  }
  
  public void c(aht ☃, cj ☃, arc ☃)
  {
    a(☃, ☃, this.Q, aju.a);
    this.P.c(☃, ☃, this.Q);
  }
  
  public void b(aht ☃, cj ☃, arc ☃)
  {
    this.P.b(☃, ☃, this.Q);
  }
  
  public void a(aht ☃, cj ☃, rr ☃)
  {
    this.P.a(☃, ☃, ☃);
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    this.P.b(☃, ☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    return this.P.a(☃, ☃, this.Q, ☃, ☃, ☃, cq.a, 0.0F, 0.0F, 0.0F);
  }
  
  public void a(aht ☃, cj ☃, ahp ☃)
  {
    this.P.a(☃, ☃, ☃);
  }
  
  public boolean k(arc ☃)
  {
    return ☃.c(b) == aot.a.a;
  }
  
  public axf r(arc ☃)
  {
    return this.P.r(this.Q);
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    arc ☃ = super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    ☃ = ☃.a(a, ☃.bi()).a(c, aot.b.a);
    if ((☃ == cq.a) || ((☃ != cq.b) && (☃ > 0.5D))) {
      return ☃.a(b, aot.a.a);
    }
    return ☃.a(b, aot.a.b);
  }
  
  public bbi a(arc ☃, aht ☃, cj ☃, bbj ☃, bbj ☃)
  {
    List<bbi> ☃ = Lists.newArrayList();
    for (bbh ☃ : x(b(☃, ☃, ☃))) {
      ☃.add(a(☃, ☃, ☃, ☃));
    }
    bbi ☃ = null;
    double ☃ = 0.0D;
    for (bbi ☃ : ☃) {
      if (☃ != null)
      {
        double ☃ = ☃.c.g(☃);
        if (☃ > ☃)
        {
          ☃ = ☃;
          ☃ = ☃;
        }
      }
    }
    return ☃;
  }
  
  public arc a(int ☃)
  {
    arc ☃ = u().a(b, (☃ & 0x4) > 0 ? aot.a.a : aot.a.b);
    
    ☃ = ☃.a(a, cq.a(5 - (☃ & 0x3)));
    
    return ☃;
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    if (☃.c(b) == aot.a.a) {
      ☃ |= 0x4;
    }
    ☃ |= 5 - ((cq)☃.c(a)).a();
    
    return ☃;
  }
  
  public arc b(arc ☃, ahx ☃, cj ☃)
  {
    return ☃.a(c, d(☃, ☃, ☃));
  }
  
  private static aot.b d(arc ☃, ahx ☃, cj ☃)
  {
    cq ☃ = (cq)☃.c(a);
    arc ☃ = ☃.o(☃.a(☃));
    if ((i(☃)) && (☃.c(b) == ☃.c(b)))
    {
      cq ☃ = (cq)☃.c(a);
      if ((☃.k() != ((cq)☃.c(a)).k()) && (d(☃, ☃, ☃, ☃.d())))
      {
        if (☃ == ☃.f()) {
          return aot.b.d;
        }
        return aot.b.e;
      }
    }
    arc ☃ = ☃.o(☃.a(☃.d()));
    if ((i(☃)) && (☃.c(b) == ☃.c(b)))
    {
      cq ☃ = (cq)☃.c(a);
      if ((☃.k() != ((cq)☃.c(a)).k()) && (d(☃, ☃, ☃, ☃)))
      {
        if (☃ == ☃.f()) {
          return aot.b.b;
        }
        return aot.b.c;
      }
    }
    return aot.b.a;
  }
  
  private static boolean d(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    arc ☃ = ☃.o(☃.a(☃));
    return (!i(☃)) || (☃.c(a) != ☃.c(a)) || (☃.c(b) != ☃.c(b));
  }
  
  public static boolean i(arc ☃)
  {
    return ☃.t() instanceof aot;
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    return ☃.a(a, ☃.a((cq)☃.c(a)));
  }
  
  public arc a(arc ☃, amr ☃)
  {
    cq ☃ = (cq)☃.c(a);
    aot.b ☃ = (aot.b)☃.c(c);
    switch (aot.1.c[☃.ordinal()])
    {
    case 1: 
      if (☃.k() == cq.a.c)
      {
        switch (aot.1.b[☃.ordinal()])
        {
        case 4: 
          return ☃.a(aoe.c).a(c, aot.b.c);
        case 3: 
          return ☃.a(aoe.c).a(c, aot.b.b);
        case 1: 
          return ☃.a(aoe.c).a(c, aot.b.e);
        case 2: 
          return ☃.a(aoe.c).a(c, aot.b.d);
        }
        return ☃.a(aoe.c);
      }
      break;
    case 2: 
      if (☃.k() == cq.a.a) {
        switch (aot.1.b[☃.ordinal()])
        {
        case 4: 
          return ☃.a(aoe.c).a(c, aot.b.b);
        case 3: 
          return ☃.a(aoe.c).a(c, aot.b.c);
        case 1: 
          return ☃.a(aoe.c).a(c, aot.b.e);
        case 2: 
          return ☃.a(aoe.c).a(c, aot.b.d);
        case 5: 
          return ☃.a(aoe.c);
        }
      }
      break;
    }
    return super.a(☃, ☃);
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
  
  public static enum b
    implements or
  {
    private final String f;
    
    private b(String ☃)
    {
      this.f = ☃;
    }
    
    public String toString()
    {
      return this.f;
    }
    
    public String m()
    {
      return this.f;
    }
  }
}
