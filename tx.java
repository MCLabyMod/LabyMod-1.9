public class tx
  extends tk
{
  private sh a;
  private sa b;
  private double c;
  private double d;
  private double e;
  private double f;
  private float g;
  
  public tx(sh ☃, double ☃, float ☃)
  {
    this.a = ☃;
    this.f = ☃;
    this.g = ☃;
    a(1);
  }
  
  public boolean a()
  {
    this.b = this.a.A();
    if (this.b == null) {
      return false;
    }
    if (this.b.h(this.a) > this.g * this.g) {
      return false;
    }
    bbj ☃ = vm.a(this.a, 16, 7, new bbj(this.b.p, this.b.q, this.b.r));
    if (☃ == null) {
      return false;
    }
    this.c = ☃.b;
    this.d = ☃.c;
    this.e = ☃.d;
    return true;
  }
  
  public boolean b()
  {
    return (!this.a.x().n()) && (this.b.au()) && (this.b.h(this.a) < this.g * this.g);
  }
  
  public void d()
  {
    this.b = null;
  }
  
  public void c()
  {
    this.a.x().a(this.c, this.d, this.e, this.f);
  }
}
