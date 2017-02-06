import java.util.List;
import java.util.Random;

public class td
  extends tk
{
  private vw d;
  aht a;
  private vw e;
  int b;
  double c;
  
  public td(vw ☃, double ☃)
  {
    this.d = ☃;
    this.a = ☃.l;
    this.c = ☃;
    a(3);
  }
  
  public boolean a()
  {
    if (!this.d.df()) {
      return false;
    }
    this.e = f();
    return this.e != null;
  }
  
  public boolean b()
  {
    return (this.e.au()) && (this.e.df()) && (this.b < 60);
  }
  
  public void d()
  {
    this.e = null;
    this.b = 0;
  }
  
  public void e()
  {
    this.d.t().a(this.e, 10.0F, this.d.N());
    this.d.x().a(this.e, this.c);
    this.b += 1;
    if ((this.b >= 60) && (this.d.h(this.e) < 9.0D)) {
      i();
    }
  }
  
  private vw f()
  {
    List<vw> ☃ = this.a.a(this.d.getClass(), this.d.bl().g(8.0D));
    double ☃ = Double.MAX_VALUE;
    vw ☃ = null;
    for (vw ☃ : ☃) {
      if ((this.d.a(☃)) && (this.d.h(☃) < ☃))
      {
        ☃ = ☃;
        ☃ = this.d.h(☃);
      }
    }
    return ☃;
  }
  
  private void i()
  {
    ro ☃ = this.d.a(this.e);
    if (☃ == null) {
      return;
    }
    zj ☃ = this.d.de();
    if ((☃ == null) && (this.e.de() != null)) {
      ☃ = this.e.de();
    }
    if (☃ != null)
    {
      ☃.b(nt.C);
      if ((this.d instanceof vy)) {
        ☃.b(nk.H);
      }
    }
    this.d.b_(6000);
    this.e.b_(6000);
    this.d.dg();
    this.e.dg();
    ☃.b_(41536);
    ☃.b(this.d.p, this.d.q, this.d.r, 0.0F, 0.0F);
    this.a.a(☃);
    
    Random ☃ = this.d.bF();
    for (int ☃ = 0; ☃ < 7; ☃++)
    {
      double ☃ = ☃.nextGaussian() * 0.02D;
      double ☃ = ☃.nextGaussian() * 0.02D;
      double ☃ = ☃.nextGaussian() * 0.02D;
      
      double ☃ = ☃.nextDouble() * this.d.G * 2.0D - this.d.G;
      double ☃ = 0.5D + ☃.nextDouble() * this.d.H;
      double ☃ = ☃.nextDouble() * this.d.G * 2.0D - this.d.G;
      
      this.a.a(cy.I, this.d.p + ☃, this.d.q + ☃, this.d.r + ☃, ☃, ☃, ☃, new int[0]);
    }
    if (this.a.U().b("doMobLoot")) {
      this.a.a(new rx(this.a, this.d.p, this.d.q, this.d.r, ☃.nextInt(7) + 1));
    }
  }
}
