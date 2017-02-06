import java.util.List;

public class apk
  extends ajt
{
  public static final arn a = arn.a("up");
  public static final arn b = arn.a("north");
  public static final arn c = arn.a("east");
  public static final arn d = arn.a("south");
  public static final arn e = arn.a("west");
  public static final arp<apk.a> f = arp.a("variant", apk.a.class);
  protected static final bbh[] g = { new bbh(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D), new bbh(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 1.0D), new bbh(0.0D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D), new bbh(0.0D, 0.0D, 0.25D, 0.75D, 1.0D, 1.0D), new bbh(0.25D, 0.0D, 0.0D, 0.75D, 1.0D, 0.75D), new bbh(0.3125D, 0.0D, 0.0D, 0.6875D, 0.875D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 0.75D, 1.0D, 0.75D), new bbh(0.0D, 0.0D, 0.0D, 0.75D, 1.0D, 1.0D), new bbh(0.25D, 0.0D, 0.25D, 1.0D, 1.0D, 0.75D), new bbh(0.25D, 0.0D, 0.25D, 1.0D, 1.0D, 1.0D), new bbh(0.0D, 0.0D, 0.3125D, 1.0D, 0.875D, 0.6875D), new bbh(0.0D, 0.0D, 0.25D, 1.0D, 1.0D, 1.0D), new bbh(0.25D, 0.0D, 0.0D, 1.0D, 1.0D, 0.75D), new bbh(0.25D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.75D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) };
  protected static final bbh[] B = { g[0].e(1.5D), g[1].e(1.5D), g[2].e(1.5D), g[3].e(1.5D), g[4].e(1.5D), g[5].e(1.5D), g[6].e(1.5D), g[7].e(1.5D), g[8].e(1.5D), g[9].e(1.5D), g[10].e(1.5D), g[11].e(1.5D), g[12].e(1.5D), g[13].e(1.5D), g[14].e(1.5D), g[15].e(1.5D) };
  
  public apk(ajt ☃)
  {
    super(☃.x);
    w(this.A.b().a(a, Boolean.valueOf(false)).a(b, Boolean.valueOf(false)).a(c, Boolean.valueOf(false)).a(d, Boolean.valueOf(false)).a(e, Boolean.valueOf(false)).a(f, apk.a.a));
    c(☃.q);
    b(☃.r / 3.0F);
    a(☃.v);
    a(acq.b);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    ☃ = b(☃, ☃, ☃);
    
    return g[i(☃)];
  }
  
  public bbh a(arc ☃, aht ☃, cj ☃)
  {
    ☃ = b(☃, ☃, ☃);
    
    return B[i(☃)];
  }
  
  private static int i(arc ☃)
  {
    int ☃ = 0;
    if (((Boolean)☃.c(b)).booleanValue()) {
      ☃ |= 1 << cq.c.b();
    }
    if (((Boolean)☃.c(c)).booleanValue()) {
      ☃ |= 1 << cq.f.b();
    }
    if (((Boolean)☃.c(d)).booleanValue()) {
      ☃ |= 1 << cq.d.b();
    }
    if (((Boolean)☃.c(e)).booleanValue()) {
      ☃ |= 1 << cq.e.b();
    }
    return ☃;
  }
  
  public String c()
  {
    return di.a(a() + "." + apk.a.a.c() + ".name");
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean b(ahx ☃, cj ☃)
  {
    return false;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  private boolean c(ahx ☃, cj ☃)
  {
    arc ☃ = ☃.o(☃);
    ajt ☃ = ☃.t();
    if (☃ == aju.cv) {
      return false;
    }
    if ((☃ == this) || ((☃ instanceof alk))) {
      return true;
    }
    if ((☃.x.k()) && (☃.h())) {
      return ☃.x != axe.C;
    }
    return false;
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    for (apk.a ☃ : ) {
      ☃.add(new adq(☃, 1, ☃.a()));
    }
  }
  
  public int d(arc ☃)
  {
    return ((apk.a)☃.c(f)).a();
  }
  
  public boolean a(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    if (☃ == cq.a) {
      return super.a(☃, ☃, ☃, ☃);
    }
    return true;
  }
  
  public arc a(int ☃)
  {
    return u().a(f, apk.a.a(☃));
  }
  
  public int e(arc ☃)
  {
    return ((apk.a)☃.c(f)).a();
  }
  
  public arc b(arc ☃, ahx ☃, cj ☃)
  {
    boolean ☃ = c(☃, ☃.c());
    boolean ☃ = c(☃, ☃.f());
    boolean ☃ = c(☃, ☃.d());
    boolean ☃ = c(☃, ☃.e());
    boolean ☃ = ((☃) && (!☃) && (☃) && (!☃)) || ((!☃) && (☃) && (!☃) && (☃));
    
    return ☃.a(a, Boolean.valueOf((!☃) || (!☃.d(☃.a())))).a(b, Boolean.valueOf(☃)).a(c, Boolean.valueOf(☃)).a(d, Boolean.valueOf(☃)).a(e, Boolean.valueOf(☃));
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a, b, c, e, d, f });
  }
  
  public static enum a
    implements or
  {
    private static final a[] c;
    private final int d;
    private final String e;
    private String f;
    
    static
    {
      c = new a[values().length];
      for (a ☃ : values()) {
        c[☃.a()] = ☃;
      }
    }
    
    private a(int ☃, String ☃, String ☃)
    {
      this.d = ☃;
      this.e = ☃;
      this.f = ☃;
    }
    
    public int a()
    {
      return this.d;
    }
    
    public String toString()
    {
      return this.e;
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
    
    public String c()
    {
      return this.f;
    }
  }
}
