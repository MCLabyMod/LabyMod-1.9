import java.util.List;
import java.util.Random;

public class acu
  extends ado
{
  public static final int[] a = { 1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 11250603, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320 };
  
  public acu()
  {
    a(true);
    e(0);
    a(acq.l);
  }
  
  public String f_(adq ☃)
  {
    int ☃ = ☃.i();
    return super.a() + "." + act.a(☃).d();
  }
  
  public qo a(adq ☃, zj ☃, aht ☃, cj ☃, qm ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    if (!☃.a(☃.a(☃), ☃, ☃)) {
      return qo.c;
    }
    act ☃ = act.a(☃.i());
    if (☃ == act.a)
    {
      if (a(☃, ☃, ☃))
      {
        if (!☃.E) {
          ☃.b(2005, ☃, 0);
        }
        return qo.a;
      }
    }
    else if (☃ == act.m)
    {
      arc ☃ = ☃.o(☃);
      ajt ☃ = ☃.t();
      if ((☃ == aju.r) && (☃.c(ang.b) == anj.a.d))
      {
        if ((☃ == cq.a) || (☃ == cq.b)) {
          return qo.c;
        }
        ☃ = ☃.a(☃);
        if (☃.d(☃))
        {
          arc ☃ = aju.bN.a(☃, ☃, ☃, ☃, ☃, ☃, 0, ☃);
          ☃.a(☃, ☃, 10);
          if (!☃.bJ.d) {
            ☃.b -= 1;
          }
        }
        return qo.a;
      }
      return qo.c;
    }
    return qo.b;
  }
  
  public static boolean a(adq ☃, aht ☃, cj ☃)
  {
    arc ☃ = ☃.o(☃);
    if ((☃.t() instanceof ajv))
    {
      ajv ☃ = (ajv)☃.t();
      if (☃.a(☃, ☃, ☃, ☃.E))
      {
        if (!☃.E)
        {
          if (☃.a(☃, ☃.r, ☃, ☃)) {
            ☃.b(☃, ☃.r, ☃, ☃);
          }
          ☃.b -= 1;
        }
        return true;
      }
    }
    return false;
  }
  
  public static void a(aht ☃, cj ☃, int ☃)
  {
    if (☃ == 0) {
      ☃ = 15;
    }
    arc ☃ = ☃.o(☃);
    if (☃.a() == axe.a) {
      return;
    }
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      double ☃ = i.nextGaussian() * 0.02D;
      double ☃ = i.nextGaussian() * 0.02D;
      double ☃ = i.nextGaussian() * 0.02D;
      ☃.a(cy.v, ☃.p() + i.nextFloat(), ☃.q() + i.nextFloat() * ☃.c(☃, ☃).e, ☃.r() + i.nextFloat(), ☃, ☃, ☃, new int[0]);
    }
  }
  
  public boolean a(adq ☃, zj ☃, sa ☃, qm ☃)
  {
    if ((☃ instanceof we))
    {
      we ☃ = (we)☃;
      act ☃ = act.a(☃.i());
      if ((!☃.da()) && (☃.cZ() != ☃))
      {
        ☃.b(☃);
        ☃.b -= 1;
      }
      return true;
    }
    return false;
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    for (int ☃ = 0; ☃ < 16; ☃++) {
      ☃.add(new adq(☃, 1, ☃));
    }
  }
}
