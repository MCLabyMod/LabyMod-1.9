import java.util.List;

public class anj
  extends ajt
{
  public static final arp<anj.a> a = arp.a("variant", anj.a.class);
  
  public anj()
  {
    super(axe.d);
    w(this.A.b().a(a, anj.a.a));
    a(acq.b);
  }
  
  public int d(arc ☃)
  {
    return ((anj.a)☃.c(a)).a();
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    for (anj.a ☃ : ) {
      ☃.add(new adq(☃, 1, ☃.a()));
    }
  }
  
  public arc a(int ☃)
  {
    return u().a(a, anj.a.a(☃));
  }
  
  public axf r(arc ☃)
  {
    return ((anj.a)☃.c(a)).c();
  }
  
  public int e(arc ☃)
  {
    return ((anj.a)☃.c(a)).a();
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
  
  public static enum a
    implements or
  {
    private static final a[] g;
    private final int h;
    private final String i;
    private final String j;
    private final axf k;
    
    static
    {
      g = new a[values().length];
      for (a ☃ : values()) {
        g[☃.a()] = ☃;
      }
    }
    
    private a(int ☃, String ☃, axf ☃)
    {
      this(☃, ☃, ☃, ☃);
    }
    
    private a(int ☃, String ☃, String ☃, axf ☃)
    {
      this.h = ☃;
      this.i = ☃;
      this.j = ☃;
      this.k = ☃;
    }
    
    public int a()
    {
      return this.h;
    }
    
    public axf c()
    {
      return this.k;
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
    
    public String d()
    {
      return this.j;
    }
  }
}
