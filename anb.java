import java.util.List;
import java.util.Random;

public abstract class anb
  extends alz
{
  public static final arn d = arn.a("seamless");
  public static final arp<anb.a> e = arp.a("variant", anb.a.class);
  
  public anb()
  {
    super(axe.e);
    arc ☃ = this.A.b();
    if (e()) {
      ☃ = ☃.a(d, Boolean.valueOf(false));
    } else {
      ☃ = ☃.a(a, alz.a.b);
    }
    w(☃.a(e, anb.a.a));
    a(acq.b);
  }
  
  public String c()
  {
    return di.a(a() + ".red_sandstone.name");
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ado.a(aju.cP);
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(aju.cP, 1, ((anb.a)☃.c(e)).a());
  }
  
  public String e(int ☃)
  {
    return super.a() + "." + anb.a.a(☃).d();
  }
  
  public arr<?> g()
  {
    return e;
  }
  
  public Comparable<?> a(adq ☃)
  {
    return anb.a.a(☃.i() & 0x7);
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    if (☃ == ado.a(aju.cO)) {
      return;
    }
    for (anb.a ☃ : anb.a.values()) {
      ☃.add(new adq(☃, 1, ☃.a()));
    }
  }
  
  public arc a(int ☃)
  {
    arc ☃ = u().a(e, anb.a.a(☃ & 0x7));
    if (e()) {
      ☃ = ☃.a(d, Boolean.valueOf((☃ & 0x8) != 0));
    } else {
      ☃ = ☃.a(a, (☃ & 0x8) == 0 ? alz.a.b : alz.a.a);
    }
    return ☃;
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    
    ☃ |= ((anb.a)☃.c(e)).a();
    if (e())
    {
      if (((Boolean)☃.c(d)).booleanValue()) {
        ☃ |= 0x8;
      }
    }
    else if (☃.c(a) == alz.a.a) {
      ☃ |= 0x8;
    }
    return ☃;
  }
  
  protected ard b()
  {
    if (e()) {
      return new ard(this, new arr[] { d, e });
    }
    return new ard(this, new arr[] { a, e });
  }
  
  public axf r(arc ☃)
  {
    return ((anb.a)☃.c(e)).c();
  }
  
  public int d(arc ☃)
  {
    return ((anb.a)☃.c(e)).a();
  }
  
  public static enum a
    implements or
  {
    private static final a[] b;
    private final int c;
    private final String d;
    private final axf e;
    
    static
    {
      b = new a[values().length];
      for (a ☃ : values()) {
        b[☃.a()] = ☃;
      }
    }
    
    private a(int ☃, String ☃, axf ☃)
    {
      this.c = ☃;
      this.d = ☃;
      this.e = ☃;
    }
    
    public int a()
    {
      return this.c;
    }
    
    public axf c()
    {
      return this.e;
    }
    
    public String toString()
    {
      return this.d;
    }
    
    public static a a(int ☃)
    {
      if ((☃ < 0) || (☃ >= b.length)) {
        ☃ = 0;
      }
      return b[☃];
    }
    
    public String m()
    {
      return this.d;
    }
    
    public String d()
    {
      return this.d;
    }
  }
}
