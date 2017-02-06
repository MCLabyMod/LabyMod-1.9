import java.util.List;
import java.util.Random;

public class akg
  extends ajt
{
  public static final arn a = arn.a("north");
  public static final arn b = arn.a("east");
  public static final arn c = arn.a("south");
  public static final arn d = arn.a("west");
  public static final arn e = arn.a("up");
  public static final arn f = arn.a("down");
  
  protected akg()
  {
    super(axe.k);
    a(acq.c);
    w(this.A.b().a(a, Boolean.valueOf(false)).a(b, Boolean.valueOf(false)).a(c, Boolean.valueOf(false)).a(d, Boolean.valueOf(false)).a(e, Boolean.valueOf(false)).a(f, Boolean.valueOf(false)));
  }
  
  public arc b(arc ☃, ahx ☃, cj ☃)
  {
    ajt ☃ = ☃.o(☃.b()).t();
    ajt ☃ = ☃.o(☃.a()).t();
    ajt ☃ = ☃.o(☃.c()).t();
    ajt ☃ = ☃.o(☃.f()).t();
    ajt ☃ = ☃.o(☃.d()).t();
    ajt ☃ = ☃.o(☃.e()).t();
    
    return ☃.a(f, Boolean.valueOf((☃ == this) || (☃ == aju.cS) || (☃ == aju.bH))).a(e, Boolean.valueOf((☃ == this) || (☃ == aju.cS))).a(a, Boolean.valueOf((☃ == this) || (☃ == aju.cS))).a(b, Boolean.valueOf((☃ == this) || (☃ == aju.cS))).a(c, Boolean.valueOf((☃ == this) || (☃ == aju.cS))).a(d, Boolean.valueOf((☃ == this) || (☃ == aju.cS)));
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    ☃ = ☃.b(☃, ☃);
    
    float ☃ = 0.1875F;
    
    float ☃ = ((Boolean)☃.c(d)).booleanValue() ? 0.0F : 0.1875F;
    float ☃ = ((Boolean)☃.c(f)).booleanValue() ? 0.0F : 0.1875F;
    float ☃ = ((Boolean)☃.c(a)).booleanValue() ? 0.0F : 0.1875F;
    float ☃ = ((Boolean)☃.c(b)).booleanValue() ? 1.0F : 0.8125F;
    float ☃ = ((Boolean)☃.c(e)).booleanValue() ? 1.0F : 0.8125F;
    float ☃ = ((Boolean)☃.c(c)).booleanValue() ? 1.0F : 0.8125F;
    
    return new bbh(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public void a(arc ☃, aht ☃, cj ☃, bbh ☃, List<bbh> ☃, rr ☃)
  {
    ☃ = ☃.b(☃, ☃);
    
    float ☃ = 0.1875F;
    float ☃ = 0.8125F;
    a(☃, ☃, ☃, new bbh(0.1875D, 0.1875D, 0.1875D, 0.8125D, 0.8125D, 0.8125D));
    if (((Boolean)☃.c(d)).booleanValue()) {
      a(☃, ☃, ☃, new bbh(0.0D, 0.1875D, 0.1875D, 0.1875D, 0.8125D, 0.8125D));
    }
    if (((Boolean)☃.c(b)).booleanValue()) {
      a(☃, ☃, ☃, new bbh(0.8125D, 0.1875D, 0.1875D, 1.0D, 0.8125D, 0.8125D));
    }
    if (((Boolean)☃.c(e)).booleanValue()) {
      a(☃, ☃, ☃, new bbh(0.1875D, 0.8125D, 0.1875D, 0.8125D, 1.0D, 0.8125D));
    }
    if (((Boolean)☃.c(f)).booleanValue()) {
      a(☃, ☃, ☃, new bbh(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.1875D, 0.8125D));
    }
    if (((Boolean)☃.c(a)).booleanValue()) {
      a(☃, ☃, ☃, new bbh(0.1875D, 0.1875D, 0.0D, 0.8125D, 0.8125D, 0.1875D));
    }
    if (((Boolean)☃.c(c)).booleanValue()) {
      a(☃, ☃, ☃, new bbh(0.1875D, 0.1875D, 0.8125D, 0.8125D, 0.8125D, 1.0D));
    }
  }
  
  public int e(arc ☃)
  {
    return 0;
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if (!b(☃, ☃)) {
      ☃.b(☃, true);
    }
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ads.cS;
  }
  
  public int a(Random ☃)
  {
    return ☃.nextInt(2);
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    if (super.a(☃, ☃)) {
      return b(☃, ☃);
    }
    return false;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if (!b(☃, ☃)) {
      ☃.a(☃, this, 1);
    }
  }
  
  public boolean b(aht ☃, cj ☃)
  {
    boolean ☃ = ☃.d(☃.a());
    boolean ☃ = ☃.d(☃.b());
    for (cq ☃ : cq.c.a)
    {
      cj ☃ = ☃.a(☃);
      ajt ☃ = ☃.o(☃).t();
      if (☃ == this)
      {
        if ((!☃) && (!☃)) {
          return false;
        }
        ajt ☃ = ☃.o(☃.b()).t();
        if ((☃ == this) || (☃ == aju.bH)) {
          return true;
        }
      }
    }
    ajt ☃ = ☃.o(☃.b()).t();
    return (☃ == this) || (☃ == aju.bH);
  }
  
  public ahm f()
  {
    return ahm.c;
  }
  
  public boolean a(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    ajt ☃ = ☃.o(☃.a(☃)).t();
    return (☃ != this) && (☃ != aju.cS) && ((☃ != cq.a) || (☃ != aju.bH));
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a, b, c, d, e, f });
  }
  
  public boolean b(ahx ☃, cj ☃)
  {
    return false;
  }
}
