public abstract class ry
  extends sb
{
  public ry(aht ☃)
  {
    super(☃);
  }
  
  public void e(float ☃, float ☃) {}
  
  protected void a(double ☃, boolean ☃, arc ☃, cj ☃) {}
  
  public void g(float ☃, float ☃)
  {
    if (ai())
    {
      a(☃, ☃, 0.02F);
      d(this.s, this.t, this.u);
      
      this.s *= 0.800000011920929D;
      this.t *= 0.800000011920929D;
      this.u *= 0.800000011920929D;
    }
    else if (an())
    {
      a(☃, ☃, 0.02F);
      d(this.s, this.t, this.u);
      this.s *= 0.5D;
      this.t *= 0.5D;
      this.u *= 0.5D;
    }
    else
    {
      float ☃ = 0.91F;
      if (this.z) {
        ☃ = this.l.o(new cj(on.c(this.p), on.c(bl().b) - 1, on.c(this.r))).t().z * 0.91F;
      }
      float ☃ = 0.16277136F / (☃ * ☃ * ☃);
      a(☃, ☃, this.z ? 0.1F * ☃ : 0.02F);
      
      ☃ = 0.91F;
      if (this.z) {
        ☃ = this.l.o(new cj(on.c(this.p), on.c(bl().b) - 1, on.c(this.r))).t().z * 0.91F;
      }
      d(this.s, this.t, this.u);
      
      this.s *= ☃;
      this.t *= ☃;
      this.u *= ☃;
    }
    this.aE = this.aF;
    double ☃ = this.p - this.m;
    double ☃ = this.r - this.o;
    float ☃ = on.a(☃ * ☃ + ☃ * ☃) * 4.0F;
    if (☃ > 1.0F) {
      ☃ = 1.0F;
    }
    this.aF += (☃ - this.aF) * 0.4F;
    this.aG += this.aF;
  }
  
  public boolean n_()
  {
    return false;
  }
}
