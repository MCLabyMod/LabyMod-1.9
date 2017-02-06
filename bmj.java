import java.util.Random;

public class bmj
  extends blx
{
  protected bmj(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    float ☃ = this.p.nextFloat() * 0.1F + 0.2F;
    this.y = ☃;
    this.z = ☃;
    this.A = ☃;
    b(0);
    a(0.02F, 0.02F);
    
    this.w *= (this.p.nextFloat() * 0.6F + 0.5F);
    
    this.i *= 0.019999999552965164D;
    this.j *= 0.019999999552965164D;
    this.k *= 0.019999999552965164D;
    
    this.v = ((int)(20.0D / (Math.random() * 0.8D + 0.2D)));
  }
  
  public void a(double ☃, double ☃, double ☃)
  {
    a(l().c(☃, ☃, ☃));
    j();
  }
  
  public void a()
  {
    this.c = this.f;
    this.d = this.g;
    this.e = this.h;
    
    a(this.i, this.j, this.k);
    this.i *= 0.99D;
    this.j *= 0.99D;
    this.k *= 0.99D;
    if (this.v-- <= 0) {
      i();
    }
  }
  
  public static class b
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      return new bmj(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
  
  public static class a
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      blx ☃ = new bmj(☃, ☃, ☃, ☃, ☃, ☃, ☃);
      ☃.b(82);
      ☃.a(1.0F, 1.0F, 1.0F);
      return ☃;
    }
  }
}
