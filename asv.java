public abstract class asv
{
  public static final float[] a = { 1.0F, 0.75F, 0.5F, 0.25F, 0.0F, 0.25F, 0.5F, 0.75F };
  protected aht b;
  private ahy g;
  private String h;
  protected aik c;
  protected boolean d;
  protected boolean e;
  protected final float[] f = new float[16];
  private final float[] i = new float[4];
  
  public final void a(aht ☃)
  {
    this.b = ☃;
    this.g = ☃.T().t();
    this.h = ☃.T().A();
    b();
    a();
  }
  
  protected void a()
  {
    float ☃ = 0.0F;
    for (int ☃ = 0; ☃ <= 15; ☃++)
    {
      float ☃ = 1.0F - ☃ / 15.0F;
      this.f[☃] = ((1.0F - ☃) / (☃ * 3.0F + 1.0F) * (1.0F - ☃) + ☃);
    }
  }
  
  protected void b()
  {
    ahy ☃ = this.b.T().t();
    if (☃ == ahy.c)
    {
      avk ☃ = avk.a(this.b.T().A());
      this.c = new aio(aig.a(☃.a(), ail.b));
    }
    else if (☃ == ahy.g)
    {
      this.c = new aio(ail.c);
    }
    else
    {
      this.c = new aik(this.b.T());
    }
  }
  
  public ary c()
  {
    if (this.g == ahy.c) {
      return new ati(this.b, this.b.O(), this.b.T().r(), this.h);
    }
    if (this.g == ahy.g) {
      return new ath(this.b);
    }
    if (this.g == ahy.f) {
      return new atn(this.b, this.b.O(), this.b.T().r(), this.h);
    }
    return new atn(this.b, this.b.O(), this.b.T().r(), this.h);
  }
  
  public boolean a(int ☃, int ☃)
  {
    cj ☃ = new cj(☃, 0, ☃);
    if (this.b.b(☃).i()) {
      return true;
    }
    return this.b.c(☃).t() == aju.c;
  }
  
  public float a(long ☃, float ☃)
  {
    int ☃ = (int)(☃ % 24000L);
    float ☃ = (☃ + ☃) / 24000.0F - 0.25F;
    if (☃ < 0.0F) {
      ☃ += 1.0F;
    }
    if (☃ > 1.0F) {
      ☃ -= 1.0F;
    }
    float ☃ = ☃;
    ☃ = 1.0F - (float)((Math.cos(☃ * 3.141592653589793D) + 1.0D) / 2.0D);
    ☃ = ☃ + (☃ - ☃) / 3.0F;
    return ☃;
  }
  
  public int a(long ☃)
  {
    return (int)(☃ / 24000L % 8L + 8L) % 8;
  }
  
  public boolean d()
  {
    return true;
  }
  
  public float[] a(float ☃, float ☃)
  {
    float ☃ = 0.4F;
    float ☃ = on.b(☃ * 6.2831855F) - 0.0F;
    float ☃ = -0.0F;
    if ((☃ >= ☃ - ☃) && (☃ <= ☃ + ☃))
    {
      float ☃ = (☃ - ☃) / ☃ * 0.5F + 0.5F;
      float ☃ = 1.0F - (1.0F - on.a(☃ * 3.1415927F)) * 0.99F;
      ☃ *= ☃;
      this.i[0] = (☃ * 0.3F + 0.7F);
      this.i[1] = (☃ * ☃ * 0.7F + 0.2F);
      this.i[2] = (☃ * ☃ * 0.0F + 0.2F);
      this.i[3] = ☃;
      return this.i;
    }
    return null;
  }
  
  public bbj b(float ☃, float ☃)
  {
    float ☃ = on.b(☃ * 6.2831855F) * 2.0F + 0.5F;
    ☃ = on.a(☃, 0.0F, 1.0F);
    
    float ☃ = 0.7529412F;
    float ☃ = 0.84705883F;
    float ☃ = 1.0F;
    ☃ *= (☃ * 0.94F + 0.06F);
    ☃ *= (☃ * 0.94F + 0.06F);
    ☃ *= (☃ * 0.91F + 0.09F);
    
    return new bbj(☃, ☃, ☃);
  }
  
  public boolean e()
  {
    return true;
  }
  
  public float f()
  {
    return 128.0F;
  }
  
  public boolean g()
  {
    return true;
  }
  
  public cj h()
  {
    return null;
  }
  
  public int i()
  {
    if (this.g == ahy.c) {
      return 4;
    }
    return this.b.K() + 1;
  }
  
  public double j()
  {
    if (this.g == ahy.c) {
      return 1.0D;
    }
    return 0.03125D;
  }
  
  public boolean b(int ☃, int ☃)
  {
    return false;
  }
  
  public aik k()
  {
    return this.c;
  }
  
  public boolean l()
  {
    return this.d;
  }
  
  public boolean m()
  {
    return this.e;
  }
  
  public float[] n()
  {
    return this.f;
  }
  
  public arv o()
  {
    return new arv();
  }
  
  public void a(lr ☃) {}
  
  public void b(lr ☃) {}
  
  public abstract asw p();
  
  public void q() {}
  
  public void r() {}
  
  public boolean c(int ☃, int ☃)
  {
    return true;
  }
}
