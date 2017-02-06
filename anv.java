import java.util.List;

public class anv
  extends ajt
{
  public static final arp<anv.a> a = arp.a("type", anv.a.class);
  
  public anv()
  {
    super(axe.e, aof.a.b.c());
    w(this.A.b().a(a, anv.a.a));
    a(acq.b);
  }
  
  public int d(arc ☃)
  {
    return ((anv.a)☃.c(a)).a();
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    for (anv.a ☃ : ) {
      ☃.add(new adq(☃, 1, ☃.a()));
    }
  }
  
  public arc a(int ☃)
  {
    return u().a(a, anv.a.a(☃));
  }
  
  public int e(arc ☃)
  {
    return ((anv.a)☃.c(a)).a();
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
