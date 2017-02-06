import java.util.Random;

public class adb
  extends ado
{
  public adb()
  {
    a(acq.f);
  }
  
  public qo a(adq ☃, zj ☃, aht ☃, cj ☃, qm ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    arc ☃ = ☃.o(☃);
    if ((!☃.a(☃.a(☃), ☃, ☃)) || (☃.t() != aju.bG) || (((Boolean)☃.c(ald.b)).booleanValue())) {
      return qo.c;
    }
    if (☃.E) {
      return qo.a;
    }
    ☃.a(☃, ☃.a(ald.b, Boolean.valueOf(true)), 2);
    ☃.f(☃, aju.bG);
    ☃.b -= 1;
    for (int ☃ = 0; ☃ < 16; ☃++)
    {
      double ☃ = ☃.p() + (5.0F + i.nextFloat() * 6.0F) / 16.0F;
      double ☃ = ☃.q() + 0.8125F;
      double ☃ = ☃.r() + (5.0F + i.nextFloat() * 6.0F) / 16.0F;
      double ☃ = 0.0D;
      double ☃ = 0.0D;
      double ☃ = 0.0D;
      
      ☃.a(cy.l, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D, new int[0]);
    }
    cq ☃ = (cq)☃.c(ald.a);
    
    arg.b ☃ = ald.e().a(☃, ☃);
    if (☃ != null)
    {
      cj ☃ = ☃.a().a(-3, 0, -3);
      for (int ☃ = 0; ☃ < 3; ☃++) {
        for (int ☃ = 0; ☃ < 3; ☃++) {
          ☃.a(☃.a(☃, 0, ☃), aju.bF.u(), 2);
        }
      }
    }
    return qo.a;
  }
  
  public qp<adq> a(adq ☃, aht ☃, zj ☃, qm ☃)
  {
    bbi ☃ = a(☃, ☃, false);
    if ((☃ != null) && (☃.a == bbi.a.b) && 
      (☃.o(☃.a()).t() == aju.bG)) {
      return new qp(qo.b, ☃);
    }
    if (!☃.E)
    {
      cj ☃ = ((lp)☃).r().a(☃, "Stronghold", new cj(☃));
      if (☃ != null)
      {
        zo ☃ = new zo(☃, ☃.p, ☃.q + ☃.H / 2.0F, ☃.r);
        ☃.a(☃);
        ☃.a(☃);
        
        ☃.a(null, ☃.p, ☃.q, ☃.r, ng.aT, nh.g, 0.5F, 0.4F / (i.nextFloat() * 0.4F + 0.8F));
        ☃.a(null, 1003, new cj(☃), 0);
        if (!☃.bJ.d) {
          ☃.b -= 1;
        }
        ☃.b(nt.b(this));
        return new qp(qo.a, ☃);
      }
    }
    return new qp(qo.c, ☃);
  }
}
