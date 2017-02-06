import java.util.List;
import java.util.Random;

public class apc
  extends ajy
  implements ajv
{
  public static final arp<apc.a> a = arp.a("type", apc.a.class);
  protected static final bbh c = new bbh(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
  
  protected apc()
  {
    super(axe.l);
    w(this.A.b().a(a, apc.a.a));
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return c;
  }
  
  public boolean f(aht ☃, cj ☃, arc ☃)
  {
    return i(☃.o(☃.b()));
  }
  
  public boolean a(ahx ☃, cj ☃)
  {
    return true;
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    if (☃.nextInt(8) == 0) {
      return ads.P;
    }
    return null;
  }
  
  public int a(int ☃, Random ☃)
  {
    return 1 + ☃.nextInt(☃ * 2 + 1);
  }
  
  public void a(aht ☃, zj ☃, cj ☃, arc ☃, apv ☃, adq ☃)
  {
    if ((!☃.E) && (☃ != null) && (☃.b() == ads.bl))
    {
      ☃.b(nt.a(this));
      
      a(☃, ☃, new adq(aju.H, 1, ((apc.a)☃.c(a)).a()));
    }
    else
    {
      super.a(☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(this, 1, ☃.t().e(☃));
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    for (int ☃ = 1; ☃ < 3; ☃++) {
      ☃.add(new adq(☃, 1, ☃));
    }
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, boolean ☃)
  {
    return ☃.c(a) != apc.a.a;
  }
  
  public boolean a(aht ☃, Random ☃, cj ☃, arc ☃)
  {
    return true;
  }
  
  public void b(aht ☃, Random ☃, cj ☃, arc ☃)
  {
    akw.b ☃ = akw.b.c;
    if (☃.c(a) == apc.a.c) {
      ☃ = akw.b.d;
    }
    if (aju.cF.a(☃, ☃)) {
      aju.cF.a(☃, ☃, ☃, 2);
    }
  }
  
  public arc a(int ☃)
  {
    return u().a(a, apc.a.a(☃));
  }
  
  public int e(arc ☃)
  {
    return ((apc.a)☃.c(a)).a();
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
  
  public static enum a
    implements or
  {
    private static final a[] d;
    private final int e;
    private final String f;
    
    static
    {
      d = new a[values().length];
      for (a ☃ : values()) {
        d[☃.a()] = ☃;
      }
    }
    
    private a(int ☃, String ☃)
    {
      this.e = ☃;
      this.f = ☃;
    }
    
    public int a()
    {
      return this.e;
    }
    
    public String toString()
    {
      return this.f;
    }
    
    public static a a(int ☃)
    {
      if ((☃ < 0) || (☃ >= d.length)) {
        ☃ = 0;
      }
      return d[☃];
    }
    
    public String m()
    {
      return this.f;
    }
  }
  
  public ajt.a v()
  {
    return ajt.a.c;
  }
}
