import java.util.Random;

public class blh
  extends blx
{
  private final float a;
  private boolean G;
  
  protected blh(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    this.i = ☃;
    this.j = ☃;
    this.k = ☃;
    
    this.y = on.a(this.p, 0.7176471F, 0.8745098F);
    this.z = on.a(this.p, 0.0F, 0.0F);
    this.A = on.a(this.p, 0.8235294F, 0.9764706F);
    
    this.w *= 0.75F;
    this.a = this.w;
    
    this.v = ((int)(20.0D / (this.p.nextFloat() * 0.8D + 0.2D)));
    this.G = false;
  }
  
  public void a()
  {
    this.c = this.f;
    this.d = this.g;
    this.e = this.h;
    if (this.u++ >= this.v)
    {
      i();
      return;
    }
    b(3 * this.u / this.v + 5);
    if (this.l)
    {
      this.j = 0.0D;
      this.G = true;
    }
    if (this.G) {
      this.j += 0.002D;
    }
    a(this.i, this.j, this.k);
    if (this.g == this.d)
    {
      this.i *= 1.1D;
      this.k *= 1.1D;
    }
    this.i *= 0.9599999785423279D;
    this.k *= 0.9599999785423279D;
    if (this.G) {
      this.j *= 0.9599999785423279D;
    }
  }
  
  public void a(bmz ☃, rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    this.w = (this.a * on.a((this.u + ☃) / this.v * 32.0F, 0.0F, 1.0F));
    super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public static class a
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      return new blh(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
}
