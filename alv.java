import java.util.Random;

public class alv
  extends ajt
  implements ajv
{
  public static final arn a = arn.a("snowy");
  
  protected alv()
  {
    super(axe.b);
    w(this.A.b().a(a, Boolean.valueOf(false)));
    a(true);
    a(acq.b);
  }
  
  public arc b(arc ☃, ahx ☃, cj ☃)
  {
    ajt ☃ = ☃.o(☃.a()).t();
    return ☃.a(a, Boolean.valueOf((☃ == aju.aJ) || (☃ == aju.aH)));
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if (☃.E) {
      return;
    }
    if ((☃.k(☃.a()) < 4) && (☃.o(☃.a()).c() > 2))
    {
      ☃.a(☃, aju.d.u());
      return;
    }
    if (☃.k(☃.a()) >= 9) {
      for (int ☃ = 0; ☃ < 4; ☃++)
      {
        cj ☃ = ☃.a(☃.nextInt(3) - 1, ☃.nextInt(5) - 3, ☃.nextInt(3) - 1);
        if ((☃.q() >= 0) && (☃.q() < 256) && (!☃.e(☃))) {
          return;
        }
        arc ☃ = ☃.o(☃.a());
        arc ☃ = ☃.o(☃);
        if ((☃.t() == aju.d) && (☃.c(akt.a) == akt.a.a) && (☃.k(☃.a()) >= 4) && (☃.c() <= 2)) {
          ☃.a(☃, aju.c.u());
        }
      }
    }
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return aju.d.a(aju.d.u().a(akt.a, akt.a.a), ☃, ☃);
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, boolean ☃)
  {
    return true;
  }
  
  public boolean a(aht ☃, Random ☃, cj ☃, arc ☃)
  {
    return true;
  }
  
  public void b(aht ☃, Random ☃, cj ☃, arc ☃)
  {
    cj ☃ = ☃.a();
    label257:
    for (int ☃ = 0; ☃ < 128; ☃++)
    {
      cj ☃ = ☃;
      for (int ☃ = 0; ☃ < ☃ / 16; ☃++)
      {
        ☃ = ☃.a(☃.nextInt(3) - 1, (☃.nextInt(3) - 1) * ☃.nextInt(3) / 2, ☃.nextInt(3) - 1);
        if ((☃.o(☃.b()).t() != aju.c) || (☃.o(☃).l())) {
          break label257;
        }
      }
      if (☃.o(☃).t().x == axe.a) {
        if (☃.nextInt(8) == 0)
        {
          alm.a ☃ = ☃.b(☃).a(☃, ☃);
          alm ☃ = ☃.a().a();
          arc ☃ = ☃.u().a(☃.g(), ☃);
          if (☃.f(☃, ☃, ☃)) {
            ☃.a(☃, ☃, 3);
          }
        }
        else
        {
          arc ☃ = aju.H.u().a(apc.a, apc.a.b);
          if (aju.H.f(☃, ☃, ☃)) {
            ☃.a(☃, ☃, 3);
          }
        }
      }
    }
  }
  
  public ahm f()
  {
    return ahm.b;
  }
  
  public int e(arc ☃)
  {
    return 0;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { a });
  }
}
