public class blw
  extends blx
{
  float a;
  
  protected blw(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    this(☃, ☃, ☃, ☃, ☃, ☃, ☃, 2.0F);
  }
  
  protected blw(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, float ☃)
  {
    super(☃, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D);
    this.i *= 0.009999999776482582D;
    this.j *= 0.009999999776482582D;
    this.k *= 0.009999999776482582D;
    this.j += 0.2D;
    
    this.y = (on.a(((float)☃ + 0.0F) * 6.2831855F) * 0.65F + 0.35F);
    this.z = (on.a(((float)☃ + 0.33333334F) * 6.2831855F) * 0.65F + 0.35F);
    this.A = (on.a(((float)☃ + 0.6666667F) * 6.2831855F) * 0.65F + 0.35F);
    
    this.w *= 0.75F;
    this.w *= ☃;
    this.a = this.w;
    
    this.v = 6;
    
    b(64);
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
    this.i *= 0.6600000262260437D;
    this.j *= 0.6600000262260437D;
    this.k *= 0.6600000262260437D;
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
      return new blw(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
}
