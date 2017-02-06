import java.util.Random;

public class blj
  extends blx
{
  private float a;
  private double G;
  private double H;
  private double I;
  
  protected blj(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    this.i = ☃;
    this.j = ☃;
    this.k = ☃;
    this.G = ☃;
    this.H = ☃;
    this.I = ☃;
    this.f = (this.c = ☃ + ☃);
    this.g = (this.d = ☃ + ☃);
    this.h = (this.e = ☃ + ☃);
    
    float ☃ = this.p.nextFloat() * 0.6F + 0.4F;
    this.a = (this.w = this.p.nextFloat() * 0.5F + 0.2F);
    this.y = (this.z = this.A = 1.0F * ☃);
    this.z *= 0.9F;
    this.y *= 0.9F;
    
    this.v = ((int)(Math.random() * 10.0D) + 30);
    b((int)(Math.random() * 26.0D + 1.0D + 224.0D));
  }
  
  public void a(double ☃, double ☃, double ☃)
  {
    a(l().c(☃, ☃, ☃));
    j();
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
    ☃ = 1.0F - ☃;
    
    float ☃ = 1.0F - ☃;
    ☃ *= ☃;
    ☃ *= ☃;
    this.f = (this.G + this.i * ☃);
    this.g = (this.H + this.j * ☃ - ☃ * 1.2F);
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
      return new blj(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
}
