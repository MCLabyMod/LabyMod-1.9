import java.util.Random;

public class blf
  extends blx
{
  protected blf(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    this.y = 1.0F;
    this.z = 1.0F;
    this.A = 1.0F;
    b(32);
    a(0.02F, 0.02F);
    
    this.w *= (this.p.nextFloat() * 0.6F + 0.2F);
    
    this.i = (☃ * 0.20000000298023224D + (Math.random() * 2.0D - 1.0D) * 0.019999999552965164D);
    this.j = (☃ * 0.20000000298023224D + (Math.random() * 2.0D - 1.0D) * 0.019999999552965164D);
    this.k = (☃ * 0.20000000298023224D + (Math.random() * 2.0D - 1.0D) * 0.019999999552965164D);
    
    this.v = ((int)(8.0D / (Math.random() * 0.8D + 0.2D)));
  }
  
  public void a()
  {
    this.c = this.f;
    this.d = this.g;
    this.e = this.h;
    
    this.j += 0.002D;
    a(this.i, this.j, this.k);
    this.i *= 0.8500000238418579D;
    this.j *= 0.8500000238418579D;
    this.k *= 0.8500000238418579D;
    if (this.b.o(new cj(this.f, this.g, this.h)).a() != axe.h) {
      i();
    }
    if (this.v-- <= 0) {
      i();
    }
  }
  
  public static class a
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      return new blf(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
}
