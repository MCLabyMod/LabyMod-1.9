import java.util.List;
import java.util.Random;

public class aox
  extends ajt
{
  public static final arp<aox.a> a = arp.a("variant", aox.a.class);
  
  public aox()
  {
    super(axe.e);
    w(this.A.b().a(a, aox.a.a));
    a(acq.b);
  }
  
  public String c()
  {
    return di.a(a() + "." + aox.a.a.d() + ".name");
  }
  
  public axf r(arc ☃)
  {
    return ((aox.a)☃.c(a)).c();
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    if (☃.c(a) == aox.a.a) {
      return ado.a(aju.e);
    }
    return ado.a(aju.b);
  }
  
  public int d(arc ☃)
  {
    return ((aox.a)☃.c(a)).a();
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    for (aox.a ☃ : ) {
      ☃.add(new adq(☃, 1, ☃.a()));
    }
  }
  
  public arc a(int ☃)
  {
    return u().a(a, aox.a.a(☃));
  }
  
  public int e(arc ☃)
  {
    return ((aox.a)☃.c(a)).a();
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
  
  public static enum a
    implements or
  {
    private static final a[] h;
    private final int i;
    private final String j;
    private final String k;
    private final axf l;
    
    static
    {
      h = new a[values().length];
      for (a ☃ : values()) {
        h[☃.a()] = ☃;
      }
    }
    
    private a(int ☃, axf ☃, String ☃)
    {
      this(☃, ☃, ☃, ☃);
    }
    
    private a(int ☃, axf ☃, String ☃, String ☃)
    {
      this.i = ☃;
      this.j = ☃;
      this.k = ☃;
      this.l = ☃;
    }
    
    public int a()
    {
      return this.i;
    }
    
    public axf c()
    {
      return this.l;
    }
    
    public String toString()
    {
      return this.j;
    }
    
    public static a a(int ☃)
    {
      if ((☃ < 0) || (☃ >= h.length)) {
        ☃ = 0;
      }
      return h[☃];
    }
    
    public String m()
    {
      return this.j;
    }
    
    public String d()
    {
      return this.k;
    }
  }
}
