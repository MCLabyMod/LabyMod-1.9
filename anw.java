import java.util.Random;

public class anw
  extends ajt
{
  private final boolean a;
  
  public anw(boolean ☃)
  {
    super(axe.e);
    if (☃) {
      a(true);
    }
    this.a = ☃;
  }
  
  public int a(aht ☃)
  {
    return 30;
  }
  
  public void a(aht ☃, cj ☃, zj ☃)
  {
    b(☃, ☃);
    super.a(☃, ☃, ☃);
  }
  
  public void a(aht ☃, cj ☃, rr ☃)
  {
    b(☃, ☃);
    super.a(☃, ☃, ☃);
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    b(☃, ☃);
    return super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  private void b(aht ☃, cj ☃)
  {
    c(☃, ☃);
    if (this == aju.aC) {
      ☃.a(☃, aju.aD.u());
    }
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if (this == aju.aD) {
      ☃.a(☃, aju.aC.u());
    }
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ads.aE;
  }
  
  public int a(int ☃, Random ☃)
  {
    return a(☃) + ☃.nextInt(☃ + 1);
  }
  
  public int a(Random ☃)
  {
    return 4 + ☃.nextInt(2);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, float ☃, int ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃);
    if (a(☃, ☃.r, ☃) != ado.a(this))
    {
      int ☃ = 1 + ☃.r.nextInt(5);
      b(☃, ☃, ☃);
    }
  }
  
  public void a(arc ☃, aht ☃, cj ☃, Random ☃)
  {
    if (this.a) {
      c(☃, ☃);
    }
  }
  
  private void c(aht ☃, cj ☃)
  {
    Random ☃ = ☃.r;
    double ☃ = 0.0625D;
    for (int ☃ = 0; ☃ < 6; ☃++)
    {
      double ☃ = ☃.p() + ☃.nextFloat();
      double ☃ = ☃.q() + ☃.nextFloat();
      double ☃ = ☃.r() + ☃.nextFloat();
      if ((☃ == 0) && (!☃.o(☃.a()).p())) {
        ☃ = ☃.q() + ☃ + 1.0D;
      }
      if ((☃ == 1) && (!☃.o(☃.b()).p())) {
        ☃ = ☃.q() - ☃;
      }
      if ((☃ == 2) && (!☃.o(☃.d()).p())) {
        ☃ = ☃.r() + ☃ + 1.0D;
      }
      if ((☃ == 3) && (!☃.o(☃.c()).p())) {
        ☃ = ☃.r() - ☃;
      }
      if ((☃ == 4) && (!☃.o(☃.f()).p())) {
        ☃ = ☃.p() + ☃ + 1.0D;
      }
      if ((☃ == 5) && (!☃.o(☃.e()).p())) {
        ☃ = ☃.p() - ☃;
      }
      if ((☃ < ☃.p()) || (☃ > ☃.p() + 1) || (☃ < 0.0D) || (☃ > ☃.q() + 1) || (☃ < ☃.r()) || (☃ > ☃.r() + 1)) {
        ☃.a(cy.E, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D, new int[0]);
      }
    }
  }
  
  protected adq u(arc ☃)
  {
    return new adq(aju.aC);
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(ado.a(aju.aC), 1, d(☃));
  }
}
