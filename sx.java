public class sx
{
  private sb a;
  private float b;
  private float c;
  private boolean d;
  private double e;
  private double f;
  private double g;
  
  public sx(sb ☃)
  {
    this.a = ☃;
  }
  
  public void a(rr ☃, float ☃, float ☃)
  {
    this.e = ☃.p;
    if ((☃ instanceof sa)) {
      this.f = (☃.q + ☃.bn());
    } else {
      this.f = ((☃.bl().b + ☃.bl().e) / 2.0D);
    }
    this.g = ☃.r;
    this.b = ☃;
    this.c = ☃;
    this.d = true;
  }
  
  public void a(double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    this.e = ☃;
    this.f = ☃;
    this.g = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = true;
  }
  
  public void a()
  {
    this.a.w = 0.0F;
    if (this.d)
    {
      this.d = false;
      
      double ☃ = this.e - this.a.p;
      double ☃ = this.f - (this.a.q + this.a.bn());
      double ☃ = this.g - this.a.r;
      double ☃ = on.a(☃ * ☃ + ☃ * ☃);
      
      float ☃ = (float)(on.b(☃, ☃) * 57.2957763671875D) - 90.0F;
      float ☃ = (float)-(on.b(☃, ☃) * 57.2957763671875D);
      this.a.w = a(this.a.w, ☃, this.c);
      this.a.aO = a(this.a.aO, ☃, this.b);
    }
    else
    {
      this.a.aO = a(this.a.aO, this.a.aM, 10.0F);
    }
    float ☃ = on.g(this.a.aO - this.a.aM);
    if (!this.a.x().n())
    {
      if (☃ < -75.0F) {
        this.a.aO = (this.a.aM - 75.0F);
      }
      if (☃ > 75.0F) {
        this.a.aO = (this.a.aM + 75.0F);
      }
    }
  }
  
  private float a(float ☃, float ☃, float ☃)
  {
    float ☃ = on.g(☃ - ☃);
    if (☃ > ☃) {
      ☃ = ☃;
    }
    if (☃ < -☃) {
      ☃ = -☃;
    }
    return ☃ + ☃;
  }
  
  public boolean b()
  {
    return this.d;
  }
  
  public double e()
  {
    return this.e;
  }
  
  public double f()
  {
    return this.f;
  }
  
  public double g()
  {
    return this.g;
  }
}
