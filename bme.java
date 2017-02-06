public class bme
  extends blx
{
  float a;
  
  private bme(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    this(☃, ☃, ☃, ☃, ☃, ☃, ☃, 1.0F);
  }
  
  protected bme(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, float ☃)
  {
    super(☃, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D);
    this.i *= 0.10000000149011612D;
    this.j *= 0.10000000149011612D;
    this.k *= 0.10000000149011612D;
    this.i += ☃;
    this.j += ☃;
    this.k += ☃;
    
    this.y = (this.z = this.A = (float)(Math.random() * 0.30000001192092896D));
    this.w *= 0.75F;
    this.w *= ☃;
    this.a = this.w;
    
    this.v = ((int)(8.0D / (Math.random() * 0.8D + 0.2D)));
    this.v = ((int)(this.v * ☃));
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
    b(7 - this.u * 8 / this.v);
    
    this.j += 0.004D;
    a(this.i, this.j, this.k);
    if (this.g == this.d)
    {
      this.i *= 1.1D;
      this.k *= 1.1D;
    }
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
      return new bme(☃, ☃, ☃, ☃, ☃, ☃, ☃, null);
    }
  }
}
