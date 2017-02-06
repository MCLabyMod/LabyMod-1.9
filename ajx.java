import java.util.List;
import java.util.Random;

public class ajx
  extends ajn
{
  public static final arn[] a = { arn.a("has_bottle_0"), arn.a("has_bottle_1"), arn.a("has_bottle_2") };
  protected static final bbh b = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
  protected static final bbh c = new bbh(0.4375D, 0.0D, 0.4375D, 0.5625D, 0.875D, 0.5625D);
  
  public ajx()
  {
    super(axe.f);
    w(this.A.b().a(a[0], Boolean.valueOf(false)).a(a[1], Boolean.valueOf(false)).a(a[2], Boolean.valueOf(false)));
  }
  
  public String c()
  {
    return di.a("item.brewingStand.name");
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public aob a(arc ☃)
  {
    return aob.d;
  }
  
  public apv a(aht ☃, int ☃)
  {
    return new apw();
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public void a(arc ☃, aht ☃, cj ☃, bbh ☃, List<bbh> ☃, rr ☃)
  {
    a(☃, ☃, ☃, c);
    a(☃, ☃, ☃, b);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return b;
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (☃.E) {
      return true;
    }
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof apw))
    {
      ☃.a((apw)☃);
      ☃.b(nt.O);
    }
    return true;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, sa ☃, adq ☃)
  {
    if (☃.s())
    {
      apv ☃ = ☃.r(☃);
      if ((☃ instanceof apw)) {
        ((apw)☃).a(☃.q());
      }
    }
  }
  
  public void a(arc ☃, aht ☃, cj ☃, Random ☃)
  {
    double ☃ = ☃.p() + 0.4F + ☃.nextFloat() * 0.2F;
    double ☃ = ☃.q() + 0.7F + ☃.nextFloat() * 0.3F;
    double ☃ = ☃.r() + 0.4F + ☃.nextFloat() * 0.2F;
    
    ☃.a(cy.l, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D, new int[0]);
  }
  
  public void b(aht ☃, cj ☃, arc ☃)
  {
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof apw)) {
      qj.a(☃, ☃, (apw)☃);
    }
    super.b(☃, ☃, ☃);
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ads.bP;
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(ads.bP);
  }
  
  public boolean v(arc ☃)
  {
    return true;
  }
  
  public int d(arc ☃, aht ☃, cj ☃)
  {
    return aau.a(☃.r(☃));
  }
  
  public ahm f()
  {
    return ahm.c;
  }
  
  public arc a(int ☃)
  {
    arc ☃ = u();
    for (int ☃ = 0; ☃ < 3; ☃++) {
      ☃ = ☃.a(a[☃], Boolean.valueOf((☃ & 1 << ☃) > 0));
    }
    return ☃;
  }
  
  public int e(arc ☃)
  {
    int ☃ = 0;
    for (int ☃ = 0; ☃ < 3; ☃++) {
      if (((Boolean)☃.c(a[☃])).booleanValue()) {
        ☃ |= 1 << ☃;
      }
    }
    return ☃;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a[0], a[1], a[2] });
  }
}
