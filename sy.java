public class sy
{
  protected final sb a;
  protected double b;
  protected double c;
  protected double d;
  protected double e;
  protected float f;
  protected float g;
  protected sy.a h = sy.a.a;
  
  public sy(sb ☃)
  {
    this.a = ☃;
  }
  
  public boolean a()
  {
    return this.h == sy.a.b;
  }
  
  public double b()
  {
    return this.e;
  }
  
  public void a(double ☃, double ☃, double ☃, double ☃)
  {
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
    this.e = ☃;
    this.h = sy.a.b;
  }
  
  public void a(float ☃, float ☃)
  {
    this.h = sy.a.c;
    this.f = ☃;
    this.g = ☃;
    this.e = 0.25D;
  }
  
  public void a(sy ☃)
  {
    this.h = ☃.h;
    this.b = ☃.b;
    this.c = ☃.c;
    this.d = ☃.d;
    this.e = Math.max(☃.e, 1.0D);
    this.f = ☃.f;
    this.g = ☃.g;
  }
  
  public void c()
  {
    if (this.h == sy.a.c)
    {
      float ☃ = (float)this.a.a(yt.d).e();
      float ☃ = (float)this.e * ☃;
      
      float ☃ = this.f;
      float ☃ = this.g;
      float ☃ = on.c(☃ * ☃ + ☃ * ☃);
      if (☃ < 1.0F) {
        ☃ = 1.0F;
      }
      ☃ = ☃ / ☃;
      ☃ *= ☃;
      ☃ *= ☃;
      
      float ☃ = on.a(this.a.v * 0.017453292F);
      float ☃ = on.b(this.a.v * 0.017453292F);
      float ☃ = ☃ * ☃ - ☃ * ☃;
      float ☃ = ☃ * ☃ + ☃ * ☃;
      if (ays.a(this.a.l, on.c(this.a.p + ☃), on.c(this.a.q), on.c(this.a.r + ☃)) != aym.c)
      {
        this.f = 1.0F;
        this.g = 0.0F;
        ☃ = ☃;
      }
      this.a.l(☃);
      this.a.o(this.f);
      this.a.p(this.g);
      
      this.h = sy.a.a;
    }
    else if (this.h == sy.a.b)
    {
      this.h = sy.a.a;
      
      double ☃ = this.b - this.a.p;
      double ☃ = this.d - this.a.r;
      double ☃ = this.c - this.a.q;
      double ☃ = ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
      if (☃ < 2.500000277905201E-7D)
      {
        this.a.o(0.0F);
        return;
      }
      float ☃ = (float)(on.b(☃, ☃) * 57.2957763671875D) - 90.0F;
      
      this.a.v = a(this.a.v, ☃, 90.0F);
      this.a.l((float)(this.e * this.a.a(yt.d).e()));
      if ((☃ > this.a.P) && (☃ * ☃ + ☃ * ☃ < 1.0D)) {
        this.a.w().a();
      }
    }
    else
    {
      this.a.o(0.0F);
    }
  }
  
  protected float a(float ☃, float ☃, float ☃)
  {
    float ☃ = on.g(☃ - ☃);
    if (☃ > ☃) {
      ☃ = ☃;
    }
    if (☃ < -☃) {
      ☃ = -☃;
    }
    float ☃ = ☃ + ☃;
    if (☃ < 0.0F) {
      ☃ += 360.0F;
    } else if (☃ > 360.0F) {
      ☃ -= 360.0F;
    }
    return ☃;
  }
  
  public double d()
  {
    return this.b;
  }
  
  public double e()
  {
    return this.c;
  }
  
  public double f()
  {
    return this.d;
  }
  
  public static enum a
  {
    private a() {}
  }
}
