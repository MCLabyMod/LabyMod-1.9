import java.util.List;

public class aoy
  extends ajt
{
  public static final arp<aoy.a> a = arp.a("variant", aoy.a.class);
  public static final int b = aoy.a.a.a();
  public static final int c = aoy.a.b.a();
  public static final int d = aoy.a.c.a();
  public static final int e = aoy.a.d.a();
  
  public aoy()
  {
    super(axe.e);
    w(this.A.b().a(a, aoy.a.a));
    a(acq.b);
  }
  
  public int d(arc ☃)
  {
    return ((aoy.a)☃.c(a)).a();
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    for (aoy.a ☃ : ) {
      ☃.add(new adq(☃, 1, ☃.a()));
    }
  }
  
  public arc a(int ☃)
  {
    return u().a(a, aoy.a.a(☃));
  }
  
  public int e(arc ☃)
  {
    return ((aoy.a)☃.c(a)).a();
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
  
  public static enum a
    implements or
  {
    private static final a[] e;
    private final int f;
    private final String g;
    private final String h;
    
    static
    {
      e = new a[values().length];
      for (a ☃ : values()) {
        e[☃.a()] = ☃;
      }
    }
    
    private a(int ☃, String ☃, String ☃)
    {
      this.f = ☃;
      this.g = ☃;
      this.h = ☃;
    }
    
    public int a()
    {
      return this.f;
    }
    
    public String toString()
    {
      return this.g;
    }
    
    public static a a(int ☃)
    {
      if ((☃ < 0) || (☃ >= e.length)) {
        ☃ = 0;
      }
      return e[☃];
    }
    
    public String m()
    {
      return this.g;
    }
    
    public String c()
    {
      return this.h;
    }
  }
}
