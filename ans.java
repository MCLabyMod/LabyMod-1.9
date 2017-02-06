import java.util.List;

public class ans
  extends ajt
{
  public static final arp<ans.a> a = arp.a("variant", ans.a.class);
  
  public ans()
  {
    super(axe.e);
    w(this.A.b().a(a, ans.a.a));
    a(acq.b);
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    if (☃ == ans.a.c.a())
    {
      switch (ans.1.a[☃.k().ordinal()])
      {
      case 1: 
        return u().a(a, ans.a.e);
      case 2: 
        return u().a(a, ans.a.d);
      }
      return u().a(a, ans.a.c);
    }
    if (☃ == ans.a.b.a()) {
      return u().a(a, ans.a.b);
    }
    return u().a(a, ans.a.a);
  }
  
  public int d(arc ☃)
  {
    ans.a ☃ = (ans.a)☃.c(a);
    if ((☃ == ans.a.d) || (☃ == ans.a.e)) {
      return ans.a.c.a();
    }
    return ☃.a();
  }
  
  protected adq u(arc ☃)
  {
    ans.a ☃ = (ans.a)☃.c(a);
    if ((☃ == ans.a.d) || (☃ == ans.a.e)) {
      return new adq(ado.a(this), 1, ans.a.c.a());
    }
    return super.u(☃);
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    ☃.add(new adq(☃, 1, ans.a.a.a()));
    ☃.add(new adq(☃, 1, ans.a.b.a()));
    ☃.add(new adq(☃, 1, ans.a.c.a()));
  }
  
  public axf r(arc ☃)
  {
    return axf.p;
  }
  
  public arc a(int ☃)
  {
    return u().a(a, ans.a.a(☃));
  }
  
  public int e(arc ☃)
  {
    return ((ans.a)☃.c(a)).a();
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    switch (ans.1.c[☃.ordinal()])
    {
    case 1: 
    case 2: 
      switch (ans.1.b[((ans.a)☃.c(a)).ordinal()])
      {
      case 1: 
        return ☃.a(a, ans.a.e);
      case 2: 
        return ☃.a(a, ans.a.d);
      }
      return ☃;
    }
    return ☃;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
  
  public static enum a
    implements or
  {
    private static final a[] f;
    private final int g;
    private final String h;
    private final String i;
    
    static
    {
      f = new a[values().length];
      for (a ☃ : values()) {
        f[☃.a()] = ☃;
      }
    }
    
    private a(int ☃, String ☃, String ☃)
    {
      this.g = ☃;
      this.h = ☃;
      this.i = ☃;
    }
    
    public int a()
    {
      return this.g;
    }
    
    public String toString()
    {
      return this.i;
    }
    
    public static a a(int ☃)
    {
      if ((☃ < 0) || (☃ >= f.length)) {
        ☃ = 0;
      }
      return f[☃];
    }
    
    public String m()
    {
      return this.h;
    }
  }
}
