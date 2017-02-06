public class blg
  extends blx
{
  float a;
  
  protected blg(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    this(☃, ☃, ☃, ☃, ☃, ☃, ☃, 1.0F);
  }
  
  protected blg(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, float ☃)
  {
    super(☃, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D);
    this.i *= 0.10000000149011612D;
    this.j *= 0.10000000149011612D;
    this.k *= 0.10000000149011612D;
    this.i += ☃ * 0.4D;
    this.j += ☃ * 0.4D;
    this.k += ☃ * 0.4D;
    
    this.y = (this.z = this.A = (float)(Math.random() * 0.30000001192092896D + 0.6000000238418579D));
    this.w *= 0.75F;
    this.w *= ☃;
    this.a = this.w;
    
    this.v = ((int)(6.0D / (Math.random() * 0.8D + 0.6D)));
    this.v = ((int)(this.v * ☃));
    
    b(65);
    a();
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
    this.z = ((float)(this.z * 0.96D));
    this.A = ((float)(this.A * 0.9D));
    
    this.i *= 0.699999988079071D;
    this.j *= 0.699999988079071D;
    this.k *= 0.699999988079071D;
    this.j -= 0.019999999552965164D;
    if (this.l)
    {
      this.i *= 0.699999988079071D;
      this.k *= 0.699999988079071D;
    }
  }
  
  public static class c
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      return new blg(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
  
  public static class b
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      blx ☃ = new blg(☃, ☃, ☃, ☃, ☃, ☃, ☃);
      ☃.a(☃.d() * 0.3F, ☃.e() * 0.8F, ☃.f());
      ☃.h();
      return ☃;
    }
  }
  
  public static class a
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      blx ☃ = new blg(☃, ☃, ☃, ☃, ☃, ☃ + 1.0D, ☃, 1.0F);
      
      ☃.a(20);
      ☃.b(67);
      return ☃;
    }
  }
}
