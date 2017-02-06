import com.mojang.authlib.GameProfile;

public class bmu
  extends bmq
{
  private int d;
  private double e;
  private double f;
  private double g;
  private double h;
  private double bQ;
  
  public bmu(aht ☃, GameProfile ☃)
  {
    super(☃, ☃);
    
    this.P = 0.0F;
    this.Q = true;
    
    this.cl = 0.25F;
  }
  
  public boolean a(double ☃)
  {
    double ☃ = bl().a() * 10.0D;
    if (Double.isNaN(☃)) {
      ☃ = 1.0D;
    }
    ☃ *= 64.0D * be();
    return ☃ < ☃ * ☃;
  }
  
  public boolean a(rc ☃, float ☃)
  {
    return true;
  }
  
  public void a(double ☃, double ☃, double ☃, float ☃, float ☃, int ☃, boolean ☃)
  {
    this.e = ☃;
    this.f = ☃;
    this.g = ☃;
    this.h = ☃;
    this.bQ = ☃;
    
    this.d = ☃;
  }
  
  public void m()
  {
    this.cl = 0.0F;
    super.m();
    
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
  
  public void n()
  {
    if (this.d > 0)
    {
      double ☃ = this.p + (this.e - this.p) / this.d;
      double ☃ = this.q + (this.f - this.q) / this.d;
      double ☃ = this.r + (this.g - this.r) / this.d;
      
      double ☃ = this.h - this.v;
      while (☃ < -180.0D) {
        ☃ += 360.0D;
      }
      while (☃ >= 180.0D) {
        ☃ -= 360.0D;
      }
      this.v = ((float)(this.v + ☃ / this.d));
      this.w = ((float)(this.w + (this.bQ - this.w) / this.d));
      
      this.d -= 1;
      b(☃, ☃, ☃);
      b(this.v, this.w);
    }
    this.bw = this.bx;
    
    bY();
    
    float ☃ = on.a(this.s * this.s + this.u * this.u);
    float ☃ = (float)Math.atan(-this.t * 0.20000000298023224D) * 15.0F;
    if (☃ > 0.1F) {
      ☃ = 0.1F;
    }
    if ((!this.z) || (bQ() <= 0.0F)) {
      ☃ = 0.0F;
    }
    if ((this.z) || (bQ() <= 0.0F)) {
      ☃ = 0.0F;
    }
    this.bx += (☃ - this.bx) * 0.4F;
    this.aJ += (☃ - this.aJ) * 0.8F;
    
    this.l.C.a("push");
    cn();
    this.l.C.b();
  }
  
  public void a(eu ☃)
  {
    bcf.z().r.d().a(☃);
  }
  
  public boolean a(int ☃, String ☃)
  {
    return false;
  }
  
  public cj c()
  {
    return new cj(this.p + 0.5D, this.q + 0.5D, this.r + 0.5D);
  }
}
