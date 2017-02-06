import java.util.List;
import java.util.Random;

public class amt
  extends ajt
{
  public static final arp<amt.a> a = arp.a("variant", amt.a.class);
  
  public amt()
  {
    super(axe.B);
    w(this.A.b().a(a, amt.a.a));
    c(0.0F);
    a(acq.c);
  }
  
  public int a(Random ☃)
  {
    return 0;
  }
  
  public static boolean i(arc ☃)
  {
    ajt ☃ = ☃.t();
    
    return (☃ == aju.b.u().a(aox.a, aox.a.a)) || (☃ == aju.e) || (☃ == aju.bf);
  }
  
  protected adq u(arc ☃)
  {
    switch (amt.1.a[((amt.a)☃.c(a)).ordinal()])
    {
    case 1: 
      return new adq(aju.e);
    case 2: 
      return new adq(aju.bf);
    case 3: 
      return new adq(aju.bf, 1, aoy.a.b.a());
    case 4: 
      return new adq(aju.bf, 1, aoy.a.c.a());
    case 5: 
      return new adq(aju.bf, 1, aoy.a.d.a());
    }
    return new adq(aju.b);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, float ☃, int ☃)
  {
    if ((!☃.E) && (☃.U().b("doTileDrops")))
    {
      yv ☃ = new yv(☃);
      ☃.b(☃.p() + 0.5D, ☃.q(), ☃.r() + 0.5D, 0.0F, 0.0F);
      ☃.a(☃);
      
      ☃.E();
    }
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(this, 1, ☃.t().e(☃));
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    for (amt.a ☃ : ) {
      ☃.add(new adq(☃, 1, ☃.a()));
    }
  }
  
  public arc a(int ☃)
  {
    return u().a(a, amt.a.a(☃));
  }
  
  public int e(arc ☃)
  {
    return ((amt.a)☃.c(a)).a();
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
  
  public static abstract enum a
    implements or
  {
    private static final a[] g;
    private final int h;
    private final String i;
    private final String j;
    
    static
    {
      g = new a[values().length];
      for (a ☃ : values()) {
        g[☃.a()] = ☃;
      }
    }
    
    private a(int ☃, String ☃)
    {
      this(☃, ☃, ☃);
    }
    
    private a(int ☃, String ☃, String ☃)
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
    
    public static a a(int ☃)
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
    
    public static a a(arc ☃)
    {
      for (a ☃ : ) {
        if (☃ == ☃.d()) {
          return ☃;
        }
      }
      return a;
    }
    
    public abstract arc d();
  }
}
