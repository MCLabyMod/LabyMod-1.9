import java.util.Random;

public class auu
  extends atp
{
  private static final arc a = aju.r.u().a(ang.b, anj.a.b);
  private static final arc b = aju.t.u().a(anf.e, anj.a.b).a(aml.b, Boolean.valueOf(false));
  
  public auu()
  {
    super(false);
  }
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    int ☃ = ☃.nextInt(5) + 7;
    int ☃ = ☃ - ☃.nextInt(2) - 3;
    int ☃ = ☃ - ☃;
    int ☃ = 1 + ☃.nextInt(☃ + 1);
    
    boolean ☃ = true;
    if ((☃.q() < 1) || (☃.q() + ☃ + 1 > 256)) {
      return false;
    }
    for (int ☃ = ☃.q(); (☃ <= ☃.q() + 1 + ☃) && (☃); ☃++)
    {
      int ☃ = 1;
      if (☃ - ☃.q() < ☃) {
        ☃ = 0;
      } else {
        ☃ = ☃;
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
    if (((☃ != aju.c) && (☃ != aju.d)) || (☃.q() >= 256 - ☃ - 1)) {
      return false;
    }
    a(☃, ☃.b());
    
    int ☃ = 0;
    for (int ☃ = ☃.q() + ☃; ☃ >= ☃.q() + ☃; ☃--)
    {
      for (int ☃ = ☃.p() - ☃; ☃ <= ☃.p() + ☃; ☃++)
      {
        int ☃ = ☃ - ☃.p();
        for (int ☃ = ☃.r() - ☃; ☃ <= ☃.r() + ☃; ☃++)
        {
          int ☃ = ☃ - ☃.r();
          if ((Math.abs(☃) != ☃) || (Math.abs(☃) != ☃) || (☃ <= 0))
          {
            cj ☃ = new cj(☃, ☃, ☃);
            if (!☃.o(☃).b()) {
              a(☃, ☃, b);
            }
          }
        }
      }
      if ((☃ >= 1) && (☃ == ☃.q() + ☃ + 1)) {
        ☃--;
      } else if (☃ < ☃) {
        ☃++;
      }
    }
    for (int ☃ = 0; ☃ < ☃ - 1; ☃++)
    {
      axe ☃ = ☃.o(☃.b(☃)).a();
      if ((☃ == axe.a) || (☃ == axe.j)) {
        a(☃, ☃.b(☃), a);
      }
    }
    return true;
  }
}
