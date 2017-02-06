public class ty
  extends tk
{
  aht a;
  sb b;
  sa c;
  int d;
  
  public ty(sb ☃)
  {
    this.b = ☃;
    this.a = ☃.l;
    a(3);
  }
  
  public boolean a()
  {
    sa ☃ = this.b.A();
    if (☃ == null) {
      return false;
    }
    this.c = ☃;
    return true;
  }
  
  public boolean b()
  {
    if (!this.c.au()) {
      return false;
    }
    if (this.b.h(this.c) > 225.0D) {
      return false;
    }
    return (!this.b.x().n()) || (a());
  }
  
  public void d()
  {
    this.c = null;
    this.b.x().o();
  }
  
  public void e()
  {
    this.b.t().a(this.c, 30.0F, 30.0F);
    
    double ☃ = this.b.G * 2.0F * (this.b.G * 2.0F);
    double ☃ = this.b.e(this.c.p, this.c.bl().b, this.c.r);
    
    double ☃ = 0.8D;
    if ((☃ > ☃) && (☃ < 16.0D)) {
      ☃ = 1.33D;
    } else if (☃ < 225.0D) {
      ☃ = 0.6D;
    }
    this.b.x().a(this.c, ☃);
    
    this.d = Math.max(this.d - 1, 0);
    if (☃ > ☃) {
      return;
    }
    if (this.d > 0) {
      return;
    }
    this.d = 20;
    this.b.B(this.c);
  }
}
