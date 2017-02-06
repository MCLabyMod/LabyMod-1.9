import java.util.Random;

public class aup
  extends auq
{
  private static final arc e = aju.r.u().a(ang.b, anj.a.b);
  private static final arc f = aju.t.u().a(anf.e, anj.a.b).a(aml.b, Boolean.valueOf(false));
  private static final arc g = aju.d.u().a(akt.a, akt.a.c);
  private boolean h;
  
  public aup(boolean ☃, boolean ☃)
  {
    super(☃, 13, 15, e, f);
    this.h = ☃;
  }
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    int ☃ = a(☃);
    if (!a(☃, ☃, ☃, ☃)) {
      return false;
    }
    a(☃, ☃.p(), ☃.r(), ☃.q() + ☃, 0, ☃);
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      arc ☃ = ☃.o(☃.b(☃));
      if ((☃.a() == axe.a) || (☃.a() == axe.j)) {
        a(☃, ☃.b(☃), this.b);
      }
      if (☃ < ☃ - 1)
      {
        ☃ = ☃.o(☃.a(1, ☃, 0));
        if ((☃.a() == axe.a) || (☃.a() == axe.j)) {
          a(☃, ☃.a(1, ☃, 0), this.b);
        }
        ☃ = ☃.o(☃.a(1, ☃, 1));
        if ((☃.a() == axe.a) || (☃.a() == axe.j)) {
          a(☃, ☃.a(1, ☃, 1), this.b);
        }
        ☃ = ☃.o(☃.a(0, ☃, 1));
        if ((☃.a() == axe.a) || (☃.a() == axe.j)) {
          a(☃, ☃.a(0, ☃, 1), this.b);
        }
      }
    }
    return true;
  }
  
  private void a(aht ☃, int ☃, int ☃, int ☃, int ☃, Random ☃)
  {
    int ☃ = ☃.nextInt(5) + (this.h ? this.a : 3);
    
    int ☃ = 0;
    for (int ☃ = ☃ - ☃; ☃ <= ☃; ☃++)
    {
      int ☃ = ☃ - ☃;
      int ☃ = ☃ + on.d(☃ / ☃ * 3.5F);
      a(☃, new cj(☃, ☃, ☃), ☃ + ((☃ > 0) && (☃ == ☃) && ((☃ & 0x1) == 0) ? 1 : 0));
      ☃ = ☃;
    }
  }
  
  public void a(aht ☃, Random ☃, cj ☃)
  {
    b(☃, ☃.e().c());
    b(☃, ☃.g(2).c());
    b(☃, ☃.e().e(2));
    b(☃, ☃.g(2).e(2));
    for (int ☃ = 0; ☃ < 5; ☃++)
    {
      int ☃ = ☃.nextInt(64);
      int ☃ = ☃ % 8;
      int ☃ = ☃ / 8;
      if ((☃ == 0) || (☃ == 7) || (☃ == 0) || (☃ == 7)) {
        b(☃, ☃.a(-3 + ☃, 0, -3 + ☃));
      }
    }
  }
  
  private void b(aht ☃, cj ☃)
  {
    for (int ☃ = -2; ☃ <= 2; ☃++) {
      for (int ☃ = -2; ☃ <= 2; ☃++) {
        if ((Math.abs(☃) != 2) || (Math.abs(☃) != 2)) {
          c(☃, ☃.a(☃, 0, ☃));
        }
      }
    }
  }
  
  private void c(aht ☃, cj ☃)
  {
    for (int ☃ = 2; ☃ >= -3; ☃--)
    {
      cj ☃ = ☃.b(☃);
      arc ☃ = ☃.o(☃);
      ajt ☃ = ☃.t();
      if ((☃ == aju.c) || (☃ == aju.d)) {
        a(☃, ☃, g);
      } else {
        if ((☃.a() != axe.a) && (☃ < 0)) {
          break;
        }
      }
    }
  }
}
