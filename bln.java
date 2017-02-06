import java.util.Random;

public class bln
  extends blx
{
  private float a;
  
  protected bln(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    this.i = (this.i * 0.009999999776482582D + ☃);
    this.j = (this.j * 0.009999999776482582D + ☃);
    this.k = (this.k * 0.009999999776482582D + ☃);
    this.f += (this.p.nextFloat() - this.p.nextFloat()) * 0.05F;
    this.g += (this.p.nextFloat() - this.p.nextFloat()) * 0.05F;
    this.h += (this.p.nextFloat() - this.p.nextFloat()) * 0.05F;
    
    this.a = this.w;
    this.y = (this.z = this.A = 1.0F);
    
    this.v = ((int)(8.0D / (Math.random() * 0.8D + 0.2D)) + 4);
    b(48);
  }
  
  public void a(double ☃, double ☃, double ☃)
  {
    a(l().c(☃, ☃, ☃));
    j();
  }
  
  public void a(bmz ☃, rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    float ☃ = (this.u + ☃) / this.v;
    this.w = (this.a * (1.0F - ☃ * ☃ * 0.5F));
    super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public int a(float ☃)
  {
    float ☃ = (this.u + ☃) / this.v;
    ☃ = on.a(☃, 0.0F, 1.0F);
    int ☃ = super.a(☃);
    
    int ☃ = ☃ & 0xFF;
    int ☃ = ☃ >> 16 & 0xFF;
    ☃ += (int)(☃ * 15.0F * 16.0F);
    if (☃ > 240) {
      ☃ = 240;
    }
    return ☃ | ☃ << 16;
  }
  
  public void a()
  {
    this.c = this.f;
    this.d = this.g;
    this.e = this.h;
    if (this.u++ >= this.v) {
      i();
    }
    a(this.i, this.j, this.k);
    this.i *= 0.9599999785423279D;
    this.j *= 0.9599999785423279D;
    this.k *= 0.9599999785423279D;
    if (this.l)
    {
      this.i *= 0.699999988079071D;
      this.k *= 0.699999988079071D;
    }
  }
  
  public static class a
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      return new bln(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
}
