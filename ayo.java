public abstract class ayo
{
  protected ahx a;
  protected sb b;
  protected final oh<ayn> c = new oh();
  protected int d;
  protected int e;
  protected int f;
  protected boolean g;
  protected boolean h;
  protected boolean i;
  
  public void a(ahx ☃, sb ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c.c();
    
    this.d = on.d(☃.G + 1.0F);
    this.e = on.d(☃.H + 1.0F);
    this.f = on.d(☃.G + 1.0F);
  }
  
  public void a() {}
  
  protected ayn a(int ☃, int ☃, int ☃)
  {
    int ☃ = ayn.b(☃, ☃, ☃);
    ayn ☃ = (ayn)this.c.a(☃);
    if (☃ == null)
    {
      ☃ = new ayn(☃, ☃, ☃);
      this.c.a(☃, ☃);
    }
    return ☃;
  }
  
  public abstract ayn b();
  
  public abstract ayn a(double paramDouble1, double paramDouble2, double paramDouble3);
  
  public abstract int a(ayn[] paramArrayOfayn, ayn paramayn1, ayn paramayn2, float paramFloat);
  
  public abstract aym a(ahx paramahx, int paramInt1, int paramInt2, int paramInt3, sb paramsb, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean1, boolean paramBoolean2);
  
  public void a(boolean ☃)
  {
    this.g = ☃;
  }
  
  public void b(boolean ☃)
  {
    this.h = ☃;
  }
  
  public void c(boolean ☃)
  {
    this.i = ☃;
  }
  
  public boolean c()
  {
    return this.g;
  }
  
  public boolean d()
  {
    return this.h;
  }
  
  public boolean e()
  {
    return this.i;
  }
}
