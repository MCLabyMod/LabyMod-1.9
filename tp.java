import java.util.Random;

public class tp
  extends tk
{
  protected sb a;
  protected rr b;
  protected float c;
  private int e;
  private float f;
  protected Class<? extends rr> d;
  
  public tp(sb ☃, Class<? extends rr> ☃, float ☃)
  {
    this.a = ☃;
    this.d = ☃;
    this.c = ☃;
    this.f = 0.02F;
    a(2);
  }
  
  public tp(sb ☃, Class<? extends rr> ☃, float ☃, float ☃)
  {
    this.a = ☃;
    this.d = ☃;
    this.c = ☃;
    this.f = ☃;
    a(2);
  }
  
  public boolean a()
  {
    if (this.a.bF().nextFloat() >= this.f) {
      return false;
    }
    if (this.a.A() != null) {
      this.b = this.a.A();
    }
    if (this.d == zj.class) {
      this.b = this.a.l.a(this.a, this.c);
    } else {
      this.b = this.a.l.a(this.d, this.a.bl().b(this.c, 3.0D, this.c), this.a);
    }
    return this.b != null;
  }
  
  public boolean b()
  {
    if (!this.b.au()) {
      return false;
    }
    if (this.a.h(this.b) > this.c * this.c) {
      return false;
    }
    return this.e > 0;
  }
  
  public void c()
  {
    this.e = (40 + this.a.bF().nextInt(40));
  }
  
  public void d()
  {
    this.b = null;
  }
  
  public void e()
  {
    this.a.t().a(this.b.p, this.b.q + this.b.bn(), this.b.r, this.a.cE(), this.a.N());
    this.e -= 1;
  }
}
