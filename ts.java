import java.util.Random;

public class ts
  extends tk
{
  aht a;
  protected sh b;
  int c;
  double d;
  boolean e;
  ayp f;
  private int h;
  private double i;
  private double j;
  private double k;
  protected final int g = 20;
  
  public ts(sh ☃, double ☃, boolean ☃)
  {
    this.b = ☃;
    this.a = ☃.l;
    this.d = ☃;
    this.e = ☃;
    a(3);
  }
  
  public boolean a()
  {
    sa ☃ = this.b.A();
    if (☃ == null) {
      return false;
    }
    if (!☃.au()) {
      return false;
    }
    this.f = this.b.x().a(☃);
    return this.f != null;
  }
  
  public boolean b()
  {
    sa ☃ = this.b.A();
    if (☃ == null) {
      return false;
    }
    if (!☃.au()) {
      return false;
    }
    if (!this.e)
    {
      if (this.b.x().n()) {
        return false;
      }
      return true;
    }
    if (!this.b.f(new cj(☃))) {
      return false;
    }
    if (((☃ instanceof zj)) && ((((zj)☃).y()) || (((zj)☃).l_()))) {
      return false;
    }
    return true;
  }
  
  public void c()
  {
    this.b.x().a(this.f, this.d);
    this.h = 0;
  }
  
  public void d()
  {
    sa ☃ = this.b.A();
    if (((☃ instanceof zj)) && ((((zj)☃).y()) || (((zj)☃).l_()))) {
      this.b.c(null);
    }
    this.b.x().o();
  }
  
  public void e()
  {
    sa ☃ = this.b.A();
    this.b.t().a(☃, 30.0F, 30.0F);
    double ☃ = this.b.e(☃.p, ☃.bl().b, ☃.r);
    double ☃ = a(☃);
    this.h -= 1;
    if (((this.e) || (this.b.y().a(☃))) && 
      (this.h <= 0) && (
      ((this.i == 0.0D) && (this.j == 0.0D) && (this.k == 0.0D)) || (☃.e(this.i, this.j, this.k) >= 1.0D) || (this.b.bF().nextFloat() < 0.05F)))
    {
      this.i = ☃.p;
      this.j = ☃.bl().b;
      this.k = ☃.r;
      this.h = (4 + this.b.bF().nextInt(7));
      if (☃ > 1024.0D) {
        this.h += 10;
      } else if (☃ > 256.0D) {
        this.h += 5;
      }
      if (!this.b.x().a(☃, this.d)) {
        this.h += 15;
      }
    }
    this.c = Math.max(this.c - 1, 0);
    if ((☃ <= ☃) && (this.c <= 0))
    {
      this.c = 20;
      this.b.a(qm.a);
      this.b.B(☃);
    }
  }
  
  protected double a(sa ☃)
  {
    return this.b.G * 2.0F * (this.b.G * 2.0F) + ☃.G;
  }
}
