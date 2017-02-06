import java.util.Random;

public class ave
  extends atp
{
  private static final arc a = aju.r.u().a(ang.b, anj.a.a);
  private static final arc b = aju.t.u().a(anf.e, anj.a.a).a(anf.b, Boolean.valueOf(false));
  
  public ave()
  {
    super(false);
  }
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    int ☃ = ☃.nextInt(4) + 5;
    while (☃.o(☃.b()).a() == axe.h) {
      ☃ = ☃.b();
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
        ☃ = 3;
      }
      cj.a ☃ = new cj.a();
      for (int ☃ = ☃.p() - ☃; (☃ <= ☃.p() + ☃) && (☃); ☃++) {
        for (int ☃ = ☃.r() - ☃; (☃ <= ☃.r() + ☃) && (☃); ☃++) {
          if ((☃ >= 0) && (☃ < 256))
          {
            arc ☃ = ☃.o(☃.c(☃, ☃, ☃));
            ajt ☃ = ☃.t();
            if ((☃.a() != axe.a) && (☃.a() != axe.j)) {
              if ((☃ == aju.j) || (☃ == aju.i))
              {
                if (☃ > ☃.q()) {
                  ☃ = false;
                }
              }
              else {
                ☃ = false;
              }
            }
          }
          else
          {
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
    for (int ☃ = ☃.q() - 3 + ☃; ☃ <= ☃.q() + ☃; ☃++)
    {
      int ☃ = ☃ - (☃.q() + ☃);
      int ☃ = 2 - ☃ / 2;
      for (int ☃ = ☃.p() - ☃; ☃ <= ☃.p() + ☃; ☃++)
      {
        int ☃ = ☃ - ☃.p();
        for (int ☃ = ☃.r() - ☃; ☃ <= ☃.r() + ☃; ☃++)
        {
          int ☃ = ☃ - ☃.r();
          if ((Math.abs(☃) != ☃) || (Math.abs(☃) != ☃) || ((☃.nextInt(2) != 0) && (☃ != 0)))
          {
            cj ☃ = new cj(☃, ☃, ☃);
            if (!☃.o(☃).b()) {
              a(☃, ☃, b);
            }
          }
        }
      }
    }
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      arc ☃ = ☃.o(☃.b(☃));
      ajt ☃ = ☃.t();
      if ((☃.a() == axe.a) || (☃.a() == axe.j) || (☃ == aju.i) || (☃ == aju.j)) {
        a(☃, ☃.b(☃), a);
      }
    }
    for (int ☃ = ☃.q() - 3 + ☃; ☃ <= ☃.q() + ☃; ☃++)
    {
      int ☃ = ☃ - (☃.q() + ☃);
      int ☃ = 2 - ☃ / 2;
      cj.a ☃ = new cj.a();
      for (int ☃ = ☃.p() - ☃; ☃ <= ☃.p() + ☃; ☃++) {
        for (int ☃ = ☃.r() - ☃; ☃ <= ☃.r() + ☃; ☃++)
        {
          ☃.c(☃, ☃, ☃);
          if (☃.o(☃).a() == axe.j)
          {
            cj ☃ = ☃.e();
            cj ☃ = ☃.f();
            cj ☃ = ☃.c();
            cj ☃ = ☃.d();
            if ((☃.nextInt(4) == 0) && (☃.o(☃).a() == axe.a)) {
              a(☃, ☃, apj.c);
            }
            if ((☃.nextInt(4) == 0) && (☃.o(☃).a() == axe.a)) {
              a(☃, ☃, apj.e);
            }
            if ((☃.nextInt(4) == 0) && (☃.o(☃).a() == axe.a)) {
              a(☃, ☃, apj.d);
            }
            if ((☃.nextInt(4) == 0) && (☃.o(☃).a() == axe.a)) {
              a(☃, ☃, apj.b);
            }
          }
        }
      }
    }
    return true;
  }
  
  private void a(aht ☃, cj ☃, arn ☃)
  {
    arc ☃ = aju.bn.u().a(☃, Boolean.valueOf(true));
    a(☃, ☃, ☃);
    int ☃ = 4;
    
    ☃ = ☃.b();
    while ((☃.o(☃).a() == axe.a) && (☃ > 0))
    {
      a(☃, ☃, ☃);
      ☃ = ☃.b();
      ☃--;
    }
  }
}
