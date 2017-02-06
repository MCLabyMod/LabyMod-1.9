import java.util.Random;

public class bmi
  extends blx
{
  protected bmi(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃ - 0.125D, ☃, ☃, ☃, ☃);
    
    this.y = 0.4F;
    this.z = 0.4F;
    this.A = 0.7F;
    b(0);
    a(0.01F, 0.01F);
    
    this.w *= (this.p.nextFloat() * 0.6F + 0.2F);
    
    this.i = (☃ * 0.0D);
    this.j = (☃ * 0.0D);
    this.k = (☃ * 0.0D);
    
    this.v = ((int)(16.0D / (Math.random() * 0.8D + 0.2D)));
  }
  
  public void a()
  {
    this.c = this.f;
    this.d = this.g;
    this.e = this.h;
    
    a(this.i, this.j, this.k);
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
      return new bmi(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
}
