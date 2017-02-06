import java.util.List;
import java.util.Random;

public abstract class apa
  extends alz
{
  public static final arn d = arn.a("seamless");
  public static final arp<apa.a> e = arp.a("variant", apa.a.class);
  
  public apa()
  {
    super(axe.e);
    arc ☃ = this.A.b();
    if (e()) {
      ☃ = ☃.a(d, Boolean.valueOf(false));
    } else {
      ☃ = ☃.a(a, alz.a.b);
    }
    w(☃.a(e, apa.a.a));
    a(acq.b);
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ado.a(aju.U);
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(aju.U, 1, ((apa.a)☃.c(e)).a());
  }
  
  public String e(int ☃)
  {
    return super.a() + "." + apa.a.a(☃).d();
  }
  
  public arr<?> g()
  {
    return e;
  }
  
  public Comparable<?> a(adq ☃)
  {
    return apa.a.a(☃.i() & 0x7);
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    if (☃ == ado.a(aju.T)) {
      return;
    }
    for (apa.a ☃ : apa.a.values()) {
      if (☃ != apa.a.c) {
        ☃.add(new adq(☃, 1, ☃.a()));
      }
    }
  }
  
  public arc a(int ☃)
  {
    arc ☃ = u().a(e, apa.a.a(☃ & 0x7));
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
    
    ☃ |= ((apa.a)☃.c(e)).a();
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
  
  public int d(arc ☃)
  {
    return ((apa.a)☃.c(e)).a();
  }
  
  public axf r(arc ☃)
  {
    return ((apa.a)☃.c(e)).c();
  }
  
  public static enum a
    implements or
  {
    private static final a[] i;
    private final int j;
    private final axf k;
    private final String l;
    private final String m;
    
    static
    {
      i = new a[values().length];
      for (a ☃ : values()) {
        i[☃.a()] = ☃;
      }
    }
    
    private a(int ☃, axf ☃, String ☃)
    {
      this(☃, ☃, ☃, ☃);
    }
    
    private a(int ☃, axf ☃, String ☃, String ☃)
    {
      this.j = ☃;
      this.k = ☃;
      this.l = ☃;
      this.m = ☃;
    }
    
    public int a()
    {
      return this.j;
    }
    
    public axf c()
    {
      return this.k;
    }
    
    public String toString()
    {
      return this.l;
    }
    
    public static a a(int ☃)
    {
      if ((☃ < 0) || (☃ >= i.length)) {
        ☃ = 0;
      }
      return i[☃];
    }
    
    public String m()
    {
      return this.l;
    }
    
    public String d()
    {
      return this.m;
    }
  }
}
