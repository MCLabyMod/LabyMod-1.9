class ym$c
  extends tk
{
  private ym b;
  public int a;
  
  public ym$c(ym ☃)
  {
    this.b = ☃;
  }
  
  public boolean a()
  {
    return this.b.A() != null;
  }
  
  public void c()
  {
    this.a = 0;
  }
  
  public void d()
  {
    this.b.a(false);
  }
  
  public void e()
  {
    sa ☃ = this.b.A();
    
    double ☃ = 64.0D;
    if ((☃.h(this.b) < ☃ * ☃) && (this.b.D(☃)))
    {
      aht ☃ = this.b.l;
      
      this.a += 1;
      if (this.a == 10) {
        ☃.a(null, 1015, new cj(this.b), 0);
      }
      if (this.a == 20)
      {
        double ☃ = 4.0D;
        bbj ☃ = this.b.f(1.0F);
        
        double ☃ = ☃.p - (this.b.p + ☃.b * ☃);
        double ☃ = ☃.bl().b + ☃.H / 2.0F - (0.5D + this.b.q + this.b.H / 2.0F);
        double ☃ = ☃.r - (this.b.r + ☃.d * ☃);
        
        ☃.a(null, 1016, new cj(this.b), 0);
        zr ☃ = new zr(☃, this.b, ☃, ☃, ☃);
        ☃.e = this.b.cT();
        ☃.p = (this.b.p + ☃.b * ☃);
        ☃.q = (this.b.q + this.b.H / 2.0F + 0.5D);
        ☃.r = (this.b.r + ☃.d * ☃);
        ☃.a(☃);
        this.a = -40;
      }
    }
    else if (this.a > 0)
    {
      this.a -= 1;
    }
    this.b.a(this.a > 10);
  }
}
