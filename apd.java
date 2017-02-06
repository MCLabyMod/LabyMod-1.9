import java.util.List;
import java.util.Random;

public class apd
  extends ajt
{
  public static final arn b = arn.a("north");
  public static final arn c = arn.a("east");
  public static final arn d = arn.a("south");
  public static final arn e = arn.a("west");
  protected static final bbh[] f = { new bbh(0.4375D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D), new bbh(0.4375D, 0.0D, 0.4375D, 0.5625D, 1.0D, 1.0D), new bbh(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D), new bbh(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 1.0D), new bbh(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new bbh(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D), new bbh(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D), new bbh(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new bbh(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new bbh(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D), new bbh(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D), new bbh(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new bbh(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D), new bbh(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) };
  private final boolean a;
  
  protected apd(axe ☃, boolean ☃)
  {
    super(☃);
    w(this.A.b().a(b, Boolean.valueOf(false)).a(c, Boolean.valueOf(false)).a(d, Boolean.valueOf(false)).a(e, Boolean.valueOf(false)));
    this.a = ☃;
    a(acq.c);
  }
  
  public void a(arc ☃, aht ☃, cj ☃, bbh ☃, List<bbh> ☃, rr ☃)
  {
    ☃ = b(☃, ☃, ☃);
    
    a(☃, ☃, ☃, f[0]);
    if (((Boolean)☃.c(b)).booleanValue()) {
      a(☃, ☃, ☃, f[a(cq.c)]);
    }
    if (((Boolean)☃.c(d)).booleanValue()) {
      a(☃, ☃, ☃, f[a(cq.d)]);
    }
    if (((Boolean)☃.c(c)).booleanValue()) {
      a(☃, ☃, ☃, f[a(cq.f)]);
    }
    if (((Boolean)☃.c(e)).booleanValue()) {
      a(☃, ☃, ☃, f[a(cq.e)]);
    }
  }
  
  private static int a(cq ☃)
  {
    return 1 << ☃.b();
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    ☃ = b(☃, ☃, ☃);
    return f[i(☃)];
  }
  
  private static int i(arc ☃)
  {
    int ☃ = 0;
    if (((Boolean)☃.c(b)).booleanValue()) {
      ☃ |= a(cq.c);
    }
    if (((Boolean)☃.c(c)).booleanValue()) {
      ☃ |= a(cq.f);
    }
    if (((Boolean)☃.c(d)).booleanValue()) {
      ☃ |= a(cq.d);
    }
    if (((Boolean)☃.c(e)).booleanValue()) {
      ☃ |= a(cq.e);
    }
    return ☃;
  }
  
  public arc b(arc ☃, ahx ☃, cj ☃)
  {
    return ☃.a(b, Boolean.valueOf(c(☃.o(☃.c()).t()))).a(d, Boolean.valueOf(c(☃.o(☃.d()).t()))).a(e, Boolean.valueOf(c(☃.o(☃.e()).t()))).a(c, Boolean.valueOf(c(☃.o(☃.f()).t())));
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    if (!this.a) {
      return null;
    }
    return super.a(☃, ☃, ☃);
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean a(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    if (☃.o(☃.a(☃)).t() == this) {
      return false;
    }
    return super.a(☃, ☃, ☃, ☃);
  }
  
  public final boolean c(ajt ☃)
  {
    return (☃.u().h()) || (☃ == this) || (☃ == aju.w) || (☃ == aju.cG) || (☃ == aju.cH) || ((☃ instanceof apd));
  }
  
  protected boolean o()
  {
    return true;
  }
  
  public ahm f()
  {
    return ahm.b;
  }
  
  public int e(arc ☃)
  {
    return 0;
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    switch (apd.1.a[☃.ordinal()])
    {
    case 1: 
      return ☃.a(b, ☃.c(d)).a(c, ☃.c(e)).a(d, ☃.c(b)).a(e, ☃.c(c));
    case 2: 
      return ☃.a(b, ☃.c(c)).a(c, ☃.c(d)).a(d, ☃.c(e)).a(e, ☃.c(b));
    case 3: 
      return ☃.a(b, ☃.c(e)).a(c, ☃.c(b)).a(d, ☃.c(c)).a(e, ☃.c(d));
    }
    return ☃;
  }
  
  public arc a(arc ☃, amr ☃)
  {
    switch (apd.1.b[☃.ordinal()])
    {
    case 1: 
      return ☃.a(b, ☃.c(d)).a(d, ☃.c(b));
    case 2: 
      return ☃.a(c, ☃.c(e)).a(e, ☃.c(c));
    }
    return super.a(☃, ☃);
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { b, c, e, d });
  }
}
