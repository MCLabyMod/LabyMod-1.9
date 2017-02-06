import java.util.Random;

public class ala
  extends ajn
{
  protected static final bbh a = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
  
  protected ala()
  {
    super(axe.e, axf.D);
    d(0);
    a(acq.c);
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return a;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public void a(arc ☃, aht ☃, cj ☃, Random ☃)
  {
    super.a(☃, ☃, ☃, ☃);
    for (int ☃ = -2; ☃ <= 2; ☃++) {
      for (int ☃ = -2; ☃ <= 2; ☃++)
      {
        if ((☃ > -2) && (☃ < 2) && (☃ == -1)) {
          ☃ = 2;
        }
        if (☃.nextInt(16) == 0) {
          for (int ☃ = 0; ☃ <= 1; ☃++)
          {
            cj ☃ = ☃.a(☃, ☃, ☃);
            if (☃.o(☃).t() == aju.X)
            {
              if (!☃.d(☃.a(☃ / 2, 0, ☃ / 2))) {
                break;
              }
              ☃.a(cy.z, ☃.p() + 0.5D, ☃.q() + 2.0D, ☃.r() + 0.5D, ☃ + ☃.nextFloat() - 0.5D, ☃ - ☃.nextFloat() - 1.0F, ☃ + ☃.nextFloat() - 0.5D, new int[0]);
            }
          }
        }
      }
    }
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
    return new aqd();
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (☃.E) {
      return true;
    }
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof aqd)) {
      ☃.a((aqd)☃);
    }
    return true;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, sa ☃, adq ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃);
    if (☃.s())
    {
      apv ☃ = ☃.r(☃);
      if ((☃ instanceof aqd)) {
        ((aqd)☃).a(☃.q());
      }
    }
  }
}
