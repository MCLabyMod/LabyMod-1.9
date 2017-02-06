public class bmd
  extends blx
{
  private final int a;
  private final int G;
  private final float H;
  private float I;
  private float J;
  private float K;
  private boolean L;
  
  public bmd(aht ☃, double ☃, double ☃, double ☃, int ☃, int ☃, float ☃)
  {
    super(☃, ☃, ☃, ☃);
    this.a = ☃;
    this.G = ☃;
    this.H = ☃;
  }
  
  public void c(int ☃)
  {
    float ☃ = ((☃ & 0xFF0000) >> 16) / 255.0F;
    float ☃ = ((☃ & 0xFF00) >> 8) / 255.0F;
    float ☃ = ((☃ & 0xFF) >> 0) / 255.0F;
    float ☃ = 1.0F;
    a(☃ * ☃, ☃ * ☃, ☃ * ☃);
  }
  
  public void d(int ☃)
  {
    this.I = (((☃ & 0xFF0000) >> 16) / 255.0F);
    this.J = (((☃ & 0xFF00) >> 8) / 255.0F);
    this.K = (((☃ & 0xFF) >> 0) / 255.0F);
    this.L = true;
  }
  
  public boolean c()
  {
    return true;
  }
  
  public void a()
  {
    this.c = this.f;
    this.d = this.g;
    this.e = this.h;
    if (this.u++ >= this.v) {
      i();
    }
    if (this.u > this.v / 2)
    {
      e(1.0F - (this.u - this.v / 2) / this.v);
      if (this.L)
      {
        this.y += (this.I - this.y) * 0.2F;
        this.z += (this.J - this.z) * 0.2F;
        this.A += (this.K - this.A) * 0.2F;
      }
    }
    b(this.a + (this.G - 1 - this.u * this.G / this.v));
    
    this.j += this.H;
    a(this.i, this.j, this.k);
    this.i *= 0.9100000262260437D;
    this.j *= 0.9100000262260437D;
    this.k *= 0.9100000262260437D;
    if (this.l)
    {
      this.i *= 0.699999988079071D;
      this.k *= 0.699999988079071D;
    }
  }
  
  public int a(float ☃)
  {
    return 15728880;
  }
}
