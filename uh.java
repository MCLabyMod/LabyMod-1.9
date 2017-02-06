public class uh
  extends tk
{
  private final sb a;
  private final ys b;
  private sa c;
  private int d = -1;
  private double e;
  private int f;
  private int g;
  private int h;
  private float i;
  private float j;
  
  public uh(ys ☃, double ☃, int ☃, float ☃)
  {
    this(☃, ☃, ☃, ☃, ☃);
  }
  
  public uh(ys ☃, double ☃, int ☃, int ☃, float ☃)
  {
    if (!(☃ instanceof sa)) {
      throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
    }
    this.b = ☃;
    this.a = ((sb)☃);
    this.e = ☃;
    this.g = ☃;
    this.h = ☃;
    this.i = ☃;
    this.j = (☃ * ☃);
    a(3);
  }
  
  public boolean a()
  {
    sa ☃ = this.a.A();
    if (☃ == null) {
      return false;
    }
    this.c = ☃;
    return true;
  }
  
  public boolean b()
  {
    return (a()) || (!this.a.x().n());
  }
  
  public void d()
  {
    this.c = null;
    this.f = 0;
    this.d = -1;
  }
  
  public void e()
  {
    double ☃ = this.a.e(this.c.p, this.c.bl().b, this.c.r);
    boolean ☃ = this.a.y().a(this.c);
    if (☃) {
      this.f += 1;
    } else {
      this.f = 0;
    }
    if ((☃ > this.j) || (this.f < 20)) {
      this.a.x().a(this.c, this.e);
    } else {
      this.a.x().o();
    }
    this.a.t().a(this.c, 30.0F, 30.0F);
    if (--this.d == 0)
    {
      if ((☃ > this.j) || (!☃)) {
        return;
      }
      float ☃ = on.a(☃) / this.i;
      float ☃ = ☃;
      ☃ = on.a(☃, 0.1F, 1.0F);
      
      this.b.a(this.c, ☃);
      this.d = on.d(☃ * (this.h - this.g) + this.g);
    }
    else if (this.d < 0)
    {
      float ☃ = on.a(☃) / this.i;
      this.d = on.d(☃ * (this.h - this.g) + this.g);
    }
  }
}
