public class bls
  extends blx
{
  private rr a;
  private rr G;
  private int H;
  private int I;
  private float J;
  private brm K = bcf.z().ac();
  
  public bls(aht ☃, rr ☃, rr ☃, float ☃)
  {
    super(☃, ☃.p, ☃.q, ☃.r, ☃.s, ☃.t, ☃.u);
    this.a = ☃;
    this.G = ☃;
    this.I = 3;
    this.J = ☃;
  }
  
  public void a(bmz ☃, rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    float ☃ = (this.H + ☃) / this.I;
    ☃ *= ☃;
    
    double ☃ = this.a.p;
    double ☃ = this.a.q;
    double ☃ = this.a.r;
    
    double ☃ = this.G.M + (this.G.p - this.G.M) * ☃;
    double ☃ = this.G.N + (this.G.q - this.G.N) * ☃ + this.J;
    double ☃ = this.G.O + (this.G.r - this.G.O) * ☃;
    
    double ☃ = ☃ + (☃ - ☃) * ☃;
    double ☃ = ☃ + (☃ - ☃) * ☃;
    double ☃ = ☃ + (☃ - ☃) * ☃;
    
    int ☃ = a(☃);
    int ☃ = ☃ % 65536;
    int ☃ = ☃ / 65536;
    bzg.a(bzg.r, ☃, ☃);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    
    ☃ -= D;
    ☃ -= E;
    ☃ -= F;
    
    bni.f();
    this.K.a(this.a, ☃, ☃, ☃, this.a.v, ☃, false);
  }
  
  public void a()
  {
    this.H += 1;
    if (this.H == this.I) {
      i();
    }
  }
  
  public int b()
  {
    return 3;
  }
}
