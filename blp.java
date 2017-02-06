public class blp
  extends blx
{
  float a;
  
  protected blp(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    this(☃, ☃, ☃, ☃, ☃, ☃, ☃, 2.0F);
  }
  
  protected blp(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, float ☃)
  {
    super(☃, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D);
    this.i *= 0.009999999776482582D;
    this.j *= 0.009999999776482582D;
    this.k *= 0.009999999776482582D;
    this.j += 0.1D;
    
    this.w *= 0.75F;
    this.w *= ☃;
    this.a = this.w;
    
    this.v = 16;
    
    b(80);
  }
  
  public void a(bmz ☃, rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    float ☃ = (this.u + ☃) / this.v * 32.0F;
    ☃ = on.a(☃, 0.0F, 1.0F);
    
    this.w = (this.a * ☃);
    super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
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
    if (this.g == this.d)
    {
      this.i *= 1.1D;
      this.k *= 1.1D;
    }
    this.i *= 0.8600000143051147D;
    this.j *= 0.8600000143051147D;
    this.k *= 0.8600000143051147D;
    if (this.l)
    {
      this.i *= 0.699999988079071D;
      this.k *= 0.699999988079071D;
    }
  }
  
  public static class b
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      return new blp(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
  
  public static class a
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      blx ☃ = new blp(☃, ☃, ☃ + 0.5D, ☃, ☃, ☃, ☃);
      ☃.b(81);
      ☃.a(1.0F, 1.0F, 1.0F);
      return ☃;
    }
  }
}
