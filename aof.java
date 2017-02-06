import java.util.List;

public class aof
  extends alh
{
  public static final arp<aof.a> a = arp.a("variant", aof.a.class);
  
  public aof()
  {
    w(this.A.b().a(a, aof.a.a));
  }
  
  public int d(arc ☃)
  {
    return ((aof.a)☃.c(a)).a();
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    for (aof.a ☃ : ) {
      ☃.add(new adq(☃, 1, ☃.a()));
    }
  }
  
  public axf r(arc ☃)
  {
    return ((aof.a)☃.c(a)).c();
  }
  
  public arc a(int ☃)
  {
    return u().a(a, aof.a.a(☃));
  }
  
  public int e(arc ☃)
  {
    return ((aof.a)☃.c(a)).a();
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
  
  public static enum a
    implements or
  {
    private static final a[] c;
    private final int d;
    private final String e;
    private final axf f;
    private final String g;
    
    static
    {
      c = new a[values().length];
      for (a ☃ : values()) {
        c[☃.a()] = ☃;
      }
    }
    
    private a(int ☃, String ☃, String ☃, axf ☃)
    {
      this.d = ☃;
      this.e = ☃;
      this.f = ☃;
      this.g = ☃;
    }
    
    public int a()
    {
      return this.d;
    }
    
    public String toString()
    {
      return this.e;
    }
    
    public axf c()
    {
      return this.f;
    }
    
    public static a a(int ☃)
    {
      if ((☃ < 0) || (☃ >= c.length)) {
        ☃ = 0;
      }
      return c[☃];
    }
    
    public String m()
    {
      return this.e;
    }
    
    public String d()
    {
      return this.g;
    }
  }
}
