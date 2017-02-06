import java.util.List;

public class alj
  extends ajt
{
  public static final arn a = arn.a("north");
  public static final arn b = arn.a("east");
  public static final arn c = arn.a("south");
  public static final arn d = arn.a("west");
  protected static final bbh[] e = { new bbh(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D), new bbh(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 1.0D), new bbh(0.0D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D), new bbh(0.0D, 0.0D, 0.375D, 0.625D, 1.0D, 1.0D), new bbh(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 0.625D), new bbh(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 0.625D, 1.0D, 0.625D), new bbh(0.0D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D), new bbh(0.375D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D), new bbh(0.375D, 0.0D, 0.375D, 1.0D, 1.0D, 1.0D), new bbh(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D), new bbh(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 1.0D), new bbh(0.375D, 0.0D, 0.0D, 1.0D, 1.0D, 0.625D), new bbh(0.375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.625D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) };
  public static final bbh f = new bbh(0.375D, 0.0D, 0.375D, 0.625D, 1.5D, 0.625D);
  public static final bbh g = new bbh(0.375D, 0.0D, 0.625D, 0.625D, 1.5D, 1.0D);
  public static final bbh B = new bbh(0.0D, 0.0D, 0.375D, 0.375D, 1.5D, 0.625D);
  public static final bbh C = new bbh(0.375D, 0.0D, 0.0D, 0.625D, 1.5D, 0.375D);
  public static final bbh D = new bbh(0.625D, 0.0D, 0.375D, 1.0D, 1.5D, 0.625D);
  
  public alj(axe ☃, axf ☃)
  {
    super(☃, ☃);
    w(this.A.b().a(a, Boolean.valueOf(false)).a(b, Boolean.valueOf(false)).a(c, Boolean.valueOf(false)).a(d, Boolean.valueOf(false)));
    a(acq.c);
  }
  
  public void a(arc ☃, aht ☃, cj ☃, bbh ☃, List<bbh> ☃, rr ☃)
  {
    ☃ = ☃.b(☃, ☃);
    
    a(☃, ☃, ☃, f);
    if (((Boolean)☃.c(a)).booleanValue()) {
      a(☃, ☃, ☃, C);
    }
    if (((Boolean)☃.c(b)).booleanValue()) {
      a(☃, ☃, ☃, D);
    }
    if (((Boolean)☃.c(c)).booleanValue()) {
      a(☃, ☃, ☃, g);
    }
    if (((Boolean)☃.c(d)).booleanValue()) {
      a(☃, ☃, ☃, B);
    }
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    ☃ = b(☃, ☃, ☃);
    
    return e[i(☃)];
  }
  
  private static int i(arc ☃)
  {
    int ☃ = 0;
    if (((Boolean)☃.c(a)).booleanValue()) {
      ☃ |= 1 << cq.c.b();
    }
    if (((Boolean)☃.c(b)).booleanValue()) {
      ☃ |= 1 << cq.f.b();
    }
    if (((Boolean)☃.c(c)).booleanValue()) {
      ☃ |= 1 << cq.d.b();
    }
    if (((Boolean)☃.c(d)).booleanValue()) {
      ☃ |= 1 << cq.e.b();
    }
    return ☃;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean b(ahx ☃, cj ☃)
  {
    return false;
  }
  
  public boolean c(ahx ☃, cj ☃)
  {
    arc ☃ = ☃.o(☃);
    ajt ☃ = ☃.t();
    if (☃ == aju.cv) {
      return false;
    }
    if ((((☃ instanceof alj)) && (☃.x == this.x)) || ((☃ instanceof alk))) {
      return true;
    }
    if ((☃.x.k()) && (☃.h())) {
      return ☃.x != axe.C;
    }
    return false;
  }
  
  public boolean a(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    return true;
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (☃.E) {
      return true;
    }
    return adt.a(☃, ☃, ☃);
  }
  
  public int e(arc ☃)
  {
    return 0;
  }
  
  public arc b(arc ☃, ahx ☃, cj ☃)
  {
    return ☃.a(a, Boolean.valueOf(c(☃, ☃.c()))).a(b, Boolean.valueOf(c(☃, ☃.f()))).a(c, Boolean.valueOf(c(☃, ☃.d()))).a(d, Boolean.valueOf(c(☃, ☃.e())));
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    switch (alj.1.a[☃.ordinal()])
    {
    case 1: 
      return ☃.a(a, ☃.c(c)).a(b, ☃.c(d)).a(c, ☃.c(a)).a(d, ☃.c(b));
    case 2: 
      return ☃.a(a, ☃.c(b)).a(b, ☃.c(c)).a(c, ☃.c(d)).a(d, ☃.c(a));
    case 3: 
      return ☃.a(a, ☃.c(d)).a(b, ☃.c(a)).a(c, ☃.c(b)).a(d, ☃.c(c));
    }
    return ☃;
  }
  
  public arc a(arc ☃, amr ☃)
  {
    switch (alj.1.b[☃.ordinal()])
    {
    case 1: 
      return ☃.a(a, ☃.c(c)).a(c, ☃.c(a));
    case 2: 
      return ☃.a(b, ☃.c(d)).a(d, ☃.c(b));
    }
    return super.a(☃, ☃);
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a, b, d, c });
  }
}
