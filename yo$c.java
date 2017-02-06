class yo$c
  extends sy
{
  private yo i;
  
  public yo$c(yo ☃)
  {
    super(☃);
    this.i = ☃;
  }
  
  public void c()
  {
    if ((this.h != sy.a.b) || (this.i.x().n()))
    {
      this.i.l(0.0F);
      yo.a(this.i, false);
      return;
    }
    double ☃ = this.b - this.i.p;
    double ☃ = this.c - this.i.q;
    double ☃ = this.d - this.i.r;
    double ☃ = ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
    ☃ = on.a(☃);
    ☃ /= ☃;
    
    float ☃ = (float)(on.b(☃, ☃) * 57.2957763671875D) - 90.0F;
    
    this.i.v = a(this.i.v, ☃, 90.0F);
    this.i.aM = this.i.v;
    
    float ☃ = (float)(this.e * this.i.a(yt.d).e());
    this.i.l(this.i.ck() + (☃ - this.i.ck()) * 0.125F);
    double ☃ = Math.sin((this.i.T + this.i.O()) * 0.5D) * 0.05D;
    double ☃ = Math.cos(this.i.v * 0.017453292F);
    double ☃ = Math.sin(this.i.v * 0.017453292F);
    this.i.s += ☃ * ☃;
    this.i.u += ☃ * ☃;
    
    ☃ = Math.sin((this.i.T + this.i.O()) * 0.75D) * 0.05D;
    this.i.t += ☃ * (☃ + ☃) * 0.25D;
    this.i.t += this.i.ck() * ☃ * 0.1D;
    
    sx ☃ = this.i.t();
    double ☃ = this.i.p + ☃ / ☃ * 2.0D;
    double ☃ = this.i.bn() + this.i.q + ☃ / ☃;
    double ☃ = this.i.r + ☃ / ☃ * 2.0D;
    double ☃ = ☃.e();
    double ☃ = ☃.f();
    double ☃ = ☃.g();
    if (!☃.b())
    {
      ☃ = ☃;
      ☃ = ☃;
      ☃ = ☃;
    }
    this.i.t().a(☃ + (☃ - ☃) * 0.125D, ☃ + (☃ - ☃) * 0.125D, ☃ + (☃ - ☃) * 0.125D, 10.0F, 40.0F);
    yo.a(this.i, true);
  }
}
