import java.util.Random;

public class avg
  extends atp
{
  private static final arc a = aju.r.u().a(ang.b, anj.a.a);
  private static final arc b = aju.t.u().a(anf.e, anj.a.a).a(aml.b, Boolean.valueOf(false));
  private final int c;
  private final boolean d;
  private final arc e;
  private final arc f;
  
  public avg(boolean ☃)
  {
    this(☃, 4, a, b, false);
  }
  
  public avg(boolean ☃, int ☃, arc ☃, arc ☃, boolean ☃)
  {
    super(☃);
    this.c = ☃;
    this.e = ☃;
    this.f = ☃;
    this.d = ☃;
  }
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    int ☃ = ☃.nextInt(3) + this.c;
    
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
    
    int ☃ = 3;
    int ☃ = 0;
    for (int ☃ = ☃.q() - ☃ + ☃; ☃ <= ☃.q() + ☃; ☃++)
    {
      int ☃ = ☃ - (☃.q() + ☃);
      int ☃ = ☃ + 1 - ☃ / 2;
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
            if ((☃ == axe.a) || (☃ == axe.j) || (☃ == axe.l)) {
              a(☃, ☃, this.f);
            }
          }
        }
      }
    }
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      axe ☃ = ☃.o(☃.b(☃)).a();
      if ((☃ == axe.a) || (☃ == axe.j) || (☃ == axe.l))
      {
        a(☃, ☃.b(☃), this.e);
        if ((this.d) && (☃ > 0))
        {
          if ((☃.nextInt(3) > 0) && (☃.d(☃.a(-1, ☃, 0)))) {
            a(☃, ☃.a(-1, ☃, 0), apj.c);
          }
          if ((☃.nextInt(3) > 0) && (☃.d(☃.a(1, ☃, 0)))) {
            a(☃, ☃.a(1, ☃, 0), apj.e);
          }
          if ((☃.nextInt(3) > 0) && (☃.d(☃.a(0, ☃, -1)))) {
            a(☃, ☃.a(0, ☃, -1), apj.d);
          }
          if ((☃.nextInt(3) > 0) && (☃.d(☃.a(0, ☃, 1)))) {
            a(☃, ☃.a(0, ☃, 1), apj.b);
          }
        }
      }
    }
    if (this.d)
    {
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
                b(☃, ☃, apj.c);
              }
              if ((☃.nextInt(4) == 0) && (☃.o(☃).a() == axe.a)) {
                b(☃, ☃, apj.e);
              }
              if ((☃.nextInt(4) == 0) && (☃.o(☃).a() == axe.a)) {
                b(☃, ☃, apj.d);
              }
              if ((☃.nextInt(4) == 0) && (☃.o(☃).a() == axe.a)) {
                b(☃, ☃, apj.b);
              }
            }
          }
        }
      }
      if ((☃.nextInt(5) == 0) && (☃ > 5)) {
        for (int ☃ = 0; ☃ < 2; ☃++) {
          for (cq ☃ : cq.c.a) {
            if (☃.nextInt(4 - ☃) == 0)
            {
              cq ☃ = ☃.d();
              a(☃, ☃.nextInt(3), ☃.a(☃.g(), ☃ - 5 + ☃, ☃.i()), ☃);
            }
          }
        }
      }
    }
    return true;
  }
  
  private void a(aht ☃, int ☃, cj ☃, cq ☃)
  {
    a(☃, ☃, aju.bN.u().a(aki.a, Integer.valueOf(☃)).a(aki.D, ☃));
  }
  
  private void a(aht ☃, cj ☃, arn ☃)
  {
    a(☃, ☃, aju.bn.u().a(☃, Boolean.valueOf(true)));
  }
  
  private void b(aht ☃, cj ☃, arn ☃)
  {
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
