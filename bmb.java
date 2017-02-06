import java.util.Random;

public class bmb
  extends blx
{
  private float a;
  private double G;
  private double H;
  private double I;
  
  protected bmb(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    this.i = ☃;
    this.j = ☃;
    this.k = ☃;
    this.G = (this.f = ☃);
    this.H = (this.g = ☃);
    this.I = (this.h = ☃);
    
    float ☃ = this.p.nextFloat() * 0.6F + 0.4F;
    this.a = (this.w = this.p.nextFloat() * 0.2F + 0.5F);
    this.y = (this.z = this.A = 1.0F * ☃);
    this.z *= 0.3F;
    this.y *= 0.9F;
    
    this.v = ((int)(Math.random() * 10.0D) + 40);
    b((int)(Math.random() * 8.0D));
  }
  
  public void a(double ☃, double ☃, double ☃)
  {
    a(l().c(☃, ☃, ☃));
    j();
  }
  
  public void a(bmz ☃, rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    float ☃ = (this.u + ☃) / this.v;
    ☃ = 1.0F - ☃;
    ☃ *= ☃;
    ☃ = 1.0F - ☃;
    this.w = (this.a * ☃);
    super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public int a(float ☃)
  {
    int ☃ = super.a(☃);
    
    float ☃ = this.u / this.v;
    ☃ *= ☃;
    ☃ *= ☃;
    
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
    
    float ☃ = this.u / this.v;
    float ☃ = ☃;
    ☃ = -☃ + ☃ * ☃ * 2.0F;
    ☃ = 1.0F - ☃;
    
    this.f = (this.G + this.i * ☃);
    this.g = (this.H + this.j * ☃ + (1.0F - ☃));
    this.h = (this.I + this.k * ☃);
    if (this.u++ >= this.v) {
      i();
    }
  }
  
  public static class a
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      return new bmb(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
}
