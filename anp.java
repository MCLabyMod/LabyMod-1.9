import java.util.List;

public class anp
  extends ajt
{
  public static final arp<anp.a> a = arp.a("variant", anp.a.class);
  public static final int b = anp.a.a.a();
  public static final int c = anp.a.b.a();
  public static final int d = anp.a.c.a();
  
  public anp()
  {
    super(axe.e);
    w(this.A.b().a(a, anp.a.a));
    a(acq.b);
  }
  
  public String c()
  {
    return di.a(a() + "." + anp.a.a.c() + ".name");
  }
  
  public axf r(arc ☃)
  {
    if (☃.c(a) == anp.a.a) {
      return axf.y;
    }
    return axf.G;
  }
  
  public int d(arc ☃)
  {
    return ((anp.a)☃.c(a)).a();
  }
  
  public int e(arc ☃)
  {
    return ((anp.a)☃.c(a)).a();
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
  
  public arc a(int ☃)
  {
    return u().a(a, anp.a.a(☃));
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    ☃.add(new adq(☃, 1, b));
    ☃.add(new adq(☃, 1, c));
    ☃.add(new adq(☃, 1, d));
  }
  
  public static enum a
    implements or
  {
    private static final a[] d;
    private final int e;
    private final String f;
    private final String g;
    
    static
    {
      d = new a[values().length];
      for (a ☃ : values()) {
        d[☃.a()] = ☃;
      }
    }
    
    private a(int ☃, String ☃, String ☃)
    {
      this.e = ☃;
      this.f = ☃;
      this.g = ☃;
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
    
    public String c()
    {
      return this.g;
    }
  }
}
