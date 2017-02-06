public class vi
  extends vf
{
  public vi(sb ☃, aht ☃)
  {
    super(☃, ☃);
  }
  
  protected ayq a()
  {
    return new ayq(new ayr());
  }
  
  protected boolean b()
  {
    return p();
  }
  
  protected bbj c()
  {
    return new bbj(this.a.p, this.a.q + this.a.H * 0.5D, this.a.r);
  }
  
  protected void m()
  {
    bbj ☃ = c();
    
    float ☃ = this.a.G * this.a.G;
    int ☃ = 6;
    if (☃.g(this.c.a(this.a, this.c.e())) < ☃) {
      this.c.a();
    }
    for (int ☃ = Math.min(this.c.e() + ☃, this.c.d() - 1); ☃ > this.c.e(); ☃--)
    {
      bbj ☃ = this.c.a(this.a, ☃);
      if (☃.g(☃) <= 36.0D) {
        if (a(☃, ☃, 0, 0, 0))
        {
          this.c.c(☃);
          break;
        }
      }
    }
    a(☃);
  }
  
  protected void d()
  {
    super.d();
  }
  
  protected boolean a(bbj ☃, bbj ☃, int ☃, int ☃, int ☃)
  {
    bbi ☃ = this.b.a(☃, new bbj(☃.b, ☃.c + this.a.H * 0.5D, ☃.d), false, true, false);
    return (☃ == null) || (☃.a == bbi.a.a);
  }
  
  public boolean b(cj ☃)
  {
    return !this.b.o(☃).b();
  }
}
