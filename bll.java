import java.util.Random;

public class bll
  extends blx
{
  protected bll(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    this.i = (☃ + (Math.random() * 2.0D - 1.0D) * 0.05000000074505806D);
    this.j = (☃ + (Math.random() * 2.0D - 1.0D) * 0.05000000074505806D);
    this.k = (☃ + (Math.random() * 2.0D - 1.0D) * 0.05000000074505806D);
    
    this.y = (this.z = this.A = this.p.nextFloat() * 0.3F + 0.7F);
    this.w = (this.p.nextFloat() * this.p.nextFloat() * 6.0F + 1.0F);
    
    this.v = ((int)(16.0D / (this.p.nextFloat() * 0.8D + 0.2D)) + 2);
  }
  
  public void a()
  {
    this.c = this.f;
    this.d = this.g;
    this.e = this.h;
    if (this.u++ >= this.v) {
      i();
    }
    b(7 - this.u * 8 / this.v);
    
    this.j += 0.004D;
    a(this.i, this.j, this.k);
    this.i *= 0.8999999761581421D;
    this.j *= 0.8999999761581421D;
    this.k *= 0.8999999761581421D;
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
      return new bll(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
}
