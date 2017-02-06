import java.util.Random;

class ym$d
  extends tk
{
  private ym a;
  
  public ym$d(ym ☃)
  {
    this.a = ☃;
    
    a(1);
  }
  
  public boolean a()
  {
    sy ☃ = this.a.u();
    if (!☃.a()) {
      return true;
    }
    double ☃ = ☃.d() - this.a.p;
    double ☃ = ☃.e() - this.a.q;
    double ☃ = ☃.f() - this.a.r;
    
    double ☃ = ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
    if ((☃ < 1.0D) || (☃ > 3600.0D)) {
      return true;
    }
    return false;
  }
  
  public boolean b()
  {
    return false;
  }
  
  public void c()
  {
    Random ☃ = this.a.bF();
    double ☃ = this.a.p + (☃.nextFloat() * 2.0F - 1.0F) * 16.0F;
    double ☃ = this.a.q + (☃.nextFloat() * 2.0F - 1.0F) * 16.0F;
    double ☃ = this.a.r + (☃.nextFloat() * 2.0F - 1.0F) * 16.0F;
    this.a.u().a(☃, ☃, ☃, 1.0D);
  }
}
