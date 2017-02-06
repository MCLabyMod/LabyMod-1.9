import java.util.Random;

public class ava
  extends atp
{
  private static final arc a = aju.s.u().a(ana.b, anj.a.e);
  private static final arc b = aju.u.u().a(amz.e, anj.a.e).a(aml.b, Boolean.valueOf(false));
  
  public ava(boolean ☃)
  {
    super(☃);
  }
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    int ☃ = ☃.nextInt(3) + ☃.nextInt(3) + 5;
    
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
    if (((☃ != aju.c) && (☃ != aju.d)) || (☃.q() >= 256 - ☃ - 1)) {
      return false;
    }
    a(☃, ☃.b());
    
    cq ☃ = cq.c.a.a(☃);
    int ☃ = ☃ - ☃.nextInt(4) - 1;
    int ☃ = 3 - ☃.nextInt(3);
    
    int ☃ = ☃.p();int ☃ = ☃.r();
    int ☃ = 0;
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      int ☃ = ☃.q() + ☃;
      if ((☃ >= ☃) && (☃ > 0))
      {
        ☃ += ☃.g();
        ☃ += ☃.i();
        ☃--;
      }
      cj ☃ = new cj(☃, ☃, ☃);
      axe ☃ = ☃.o(☃).a();
      if ((☃ == axe.a) || (☃ == axe.j))
      {
        b(☃, ☃);
        ☃ = ☃;
      }
    }
    cj ☃ = new cj(☃, ☃, ☃);
    for (int ☃ = -3; ☃ <= 3; ☃++) {
      for (int ☃ = -3; ☃ <= 3; ☃++) {
        if ((Math.abs(☃) != 3) || (Math.abs(☃) != 3)) {
          c(☃, ☃.a(☃, 0, ☃));
        }
      }
    }
    ☃ = ☃.a();
    for (int ☃ = -1; ☃ <= 1; ☃++) {
      for (int ☃ = -1; ☃ <= 1; ☃++) {
        c(☃, ☃.a(☃, 0, ☃));
      }
    }
    c(☃, ☃.g(2));
    c(☃, ☃.f(2));
    c(☃, ☃.e(2));
    c(☃, ☃.d(2));
    
    ☃ = ☃.p();
    ☃ = ☃.r();
    cq ☃ = cq.c.a.a(☃);
    if (☃ != ☃)
    {
      int ☃ = ☃ - ☃.nextInt(2) - 1;
      int ☃ = 1 + ☃.nextInt(3);
      
      ☃ = 0;
      for (int ☃ = ☃; (☃ < ☃) && (☃ > 0); ☃--)
      {
        if (☃ >= 1)
        {
          int ☃ = ☃.q() + ☃;
          ☃ += ☃.g();
          ☃ += ☃.i();
          cj ☃ = new cj(☃, ☃, ☃);
          axe ☃ = ☃.o(☃).a();
          if ((☃ == axe.a) || (☃ == axe.j))
          {
            b(☃, ☃);
            ☃ = ☃;
          }
        }
        ☃++;
      }
      if (☃ > 0)
      {
        cj ☃ = new cj(☃, ☃, ☃);
        for (int ☃ = -2; ☃ <= 2; ☃++) {
          for (int ☃ = -2; ☃ <= 2; ☃++) {
            if ((Math.abs(☃) != 2) || (Math.abs(☃) != 2)) {
              c(☃, ☃.a(☃, 0, ☃));
            }
          }
        }
        ☃ = ☃.a();
        for (int ☃ = -1; ☃ <= 1; ☃++) {
          for (int ☃ = -1; ☃ <= 1; ☃++) {
            c(☃, ☃.a(☃, 0, ☃));
          }
        }
      }
    }
    return true;
  }
  
  private void b(aht ☃, cj ☃)
  {
    a(☃, ☃, a);
  }
  
  private void c(aht ☃, cj ☃)
  {
    axe ☃ = ☃.o(☃).a();
    if ((☃ == axe.a) || (☃ == axe.j)) {
      a(☃, ☃, b);
    }
  }
}
