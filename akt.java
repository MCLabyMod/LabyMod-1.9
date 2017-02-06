import java.util.List;

public class akt
  extends ajt
{
  public static final arp<akt.a> a = arp.a("variant", akt.a.class);
  public static final arn b = arn.a("snowy");
  
  protected akt()
  {
    super(axe.c);
    w(this.A.b().a(a, akt.a.a).a(b, Boolean.valueOf(false)));
    a(acq.b);
  }
  
  public axf r(arc ☃)
  {
    return ((akt.a)☃.c(a)).d();
  }
  
  public arc b(arc ☃, ahx ☃, cj ☃)
  {
    if (☃.c(a) == akt.a.c)
    {
      ajt ☃ = ☃.o(☃.a()).t();
      ☃ = ☃.a(b, Boolean.valueOf((☃ == aju.aJ) || (☃ == aju.aH)));
    }
    return ☃;
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    ☃.add(new adq(this, 1, akt.a.a.a()));
    ☃.add(new adq(this, 1, akt.a.b.a()));
    ☃.add(new adq(this, 1, akt.a.c.a()));
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(this, 1, ((akt.a)☃.c(a)).a());
  }
  
  public arc a(int ☃)
  {
    return u().a(a, akt.a.a(☃));
  }
  
  public int e(arc ☃)
  {
    return ((akt.a)☃.c(a)).a();
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a, b });
  }
  
  public int d(arc ☃)
  {
    akt.a ☃ = (akt.a)☃.c(a);
    if (☃ == akt.a.c) {
      ☃ = akt.a.a;
    }
    return ☃.a();
  }
  
  public static enum a
    implements or
  {
    private static final a[] d;
    private final int e;
    private final String f;
    private final String g;
    private final axf h;
    
    static
    {
      d = new a[values().length];
      for (a ☃ : values()) {
        d[☃.a()] = ☃;
      }
    }
    
    private a(int ☃, String ☃, axf ☃)
    {
      this(☃, ☃, ☃, ☃);
    }
    
    private a(int ☃, String ☃, String ☃, axf ☃)
    {
      this.e = ☃;
      this.f = ☃;
      this.g = ☃;
      this.h = ☃;
    }
    
    public int a()
    {
      return this.e;
    }
    
    public String c()
    {
      return this.g;
    }
    
    public axf d()
    {
      return this.h;
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
}
