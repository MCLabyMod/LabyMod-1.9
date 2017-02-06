import java.util.Random;

public class atr
  extends atp
{
  private static final arc a = aju.r.u().a(ang.b, anj.a.c);
  private static final arc b = aju.t.u().a(anf.e, anj.a.c).a(anf.b, Boolean.valueOf(false));
  private boolean c;
  
  public atr(boolean ☃, boolean ☃)
  {
    super(☃);
    this.c = ☃;
  }
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    int ☃ = ☃.nextInt(3) + 5;
    if (this.c) {
      ☃ += ☃.nextInt(7);
    }
    boolean ☃ = true;
    if ((☃.q() < 1) || (☃.q() + ☃ + 1 > 256)) {
      return false;
    }
    for (int ☃ = ☃.q(); ☃ <= ☃.q() + 1 + ☃; ☃++)
    {
      int ☃ = 1;
      if (☃ == ☃.q()) {
        ☃ = 0;
      }
      if (☃ >= ☃.q() + 1 + ☃ - 2) {
        ☃ = 2;
      }
      cj.a ☃ = new cj.a();
      for (int ☃ = ☃.p() - ☃; (☃ <= ☃.p() + ☃) && (☃); ☃++) {
        for (int ☃ = ☃.r() - ☃; (☃ <= ☃.r() + ☃) && (☃); ☃++) {
          if ((☃ >= 0) && (☃ < 256))
          {
            if (!a(☃.o(☃.c(☃, ☃, ☃)).t())) {
              ☃ = false;
            }
          }
          else {
            ☃ = false;
          }
        }
      }
    }
    if (!☃) {
      return false;
    }
    ajt ☃ = ☃.o(☃.b()).t();
    if (((☃ != aju.c) && (☃ != aju.d) && (☃ != aju.ak)) || (☃.q() >= 256 - ☃ - 1)) {
      return false;
    }
    a(☃, ☃.b());
    for (int ☃ = ☃.q() - 3 + ☃; ☃ <= ☃.q() + ☃; ☃++)
    {
      int ☃ = ☃ - (☃.q() + ☃);
      int ☃ = 1 - ☃ / 2;
      for (int ☃ = ☃.p() - ☃; ☃ <= ☃.p() + ☃; ☃++)
      {
        int ☃ = ☃ - ☃.p();
        for (int ☃ = ☃.r() - ☃; ☃ <= ☃.r() + ☃; ☃++)
        {
          int ☃ = ☃ - ☃.r();
          if ((Math.abs(☃) != ☃) || (Math.abs(☃) != ☃) || ((☃.nextInt(2) != 0) && (☃ != 0)))
          {
            cj ☃ = new cj(☃, ☃, ☃);
            axe ☃ = ☃.o(☃).a();
            if ((☃ == axe.a) || (☃ == axe.j)) {
              a(☃, ☃, b);
            }
          }
        }
      }
    }
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      axe ☃ = ☃.o(☃.b(☃)).a();
      if ((☃ == axe.a) || (☃ == axe.j)) {
        a(☃, ☃.b(☃), a);
      }
    }
    return true;
  }
}
