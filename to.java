import java.util.Random;

public class to
  extends tk
{
  sb a;
  sa b;
  float c;
  
  public to(sb ☃, float ☃)
  {
    this.a = ☃;
    this.c = ☃;
    a(5);
  }
  
  public boolean a()
  {
    this.b = this.a.A();
    if (this.b == null) {
      return false;
    }
    double ☃ = this.a.h(this.b);
    if ((☃ < 4.0D) || (☃ > 16.0D)) {
      return false;
    }
    if (!this.a.z) {
      return false;
    }
    if (this.a.bF().nextInt(5) != 0) {
      return false;
    }
    return true;
  }
  
  public boolean b()
  {
    return !this.a.z;
  }
  
  public void c()
  {
    double ☃ = this.b.p - this.a.p;
    double ☃ = this.b.r - this.a.r;
    float ☃ = on.a(☃ * ☃ + ☃ * ☃);
    this.a.s += ☃ / ☃ * 0.5D * 0.800000011920929D + this.a.s * 0.20000000298023224D;
    this.a.u += ☃ / ☃ * 0.5D * 0.800000011920929D + this.a.u * 0.20000000298023224D;
    this.a.t = this.c;
  }
}
