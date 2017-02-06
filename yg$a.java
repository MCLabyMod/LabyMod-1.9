import java.util.Random;

class yg$a
  extends tk
{
  private yg a;
  private int b;
  private int c;
  
  public yg$a(yg ☃)
  {
    this.a = ☃;
    
    a(3);
  }
  
  public boolean a()
  {
    sa ☃ = this.a.A();
    if ((☃ == null) || (!☃.au())) {
      return false;
    }
    return true;
  }
  
  public void c()
  {
    this.b = 0;
  }
  
  public void d()
  {
    this.a.a(false);
  }
  
  public void e()
  {
    this.c -= 1;
    
    sa ☃ = this.a.A();
    
    double ☃ = this.a.h(☃);
    if (☃ < 4.0D)
    {
      if (this.c <= 0)
      {
        this.c = 20;
        this.a.B(☃);
      }
      this.a.u().a(☃.p, ☃.q, ☃.r, 1.0D);
    }
    else if (☃ < 256.0D)
    {
      double ☃ = ☃.p - this.a.p;
      double ☃ = ☃.bl().b + ☃.H / 2.0F - (this.a.q + this.a.H / 2.0F);
      double ☃ = ☃.r - this.a.r;
      if (this.c <= 0)
      {
        this.b += 1;
        if (this.b == 1)
        {
          this.c = 60;
          this.a.a(true);
        }
        else if (this.b <= 4)
        {
          this.c = 6;
        }
        else
        {
          this.c = 100;
          this.b = 0;
          this.a.a(false);
        }
        if (this.b > 1)
        {
          float ☃ = on.c(on.a(☃)) * 0.5F;
          
          this.a.l.a(null, 1018, new cj((int)this.a.p, (int)this.a.q, (int)this.a.r), 0);
          for (int ☃ = 0; ☃ < 1; ☃++)
          {
            zv ☃ = new zv(this.a.l, this.a, ☃ + this.a.bF().nextGaussian() * ☃, ☃, ☃ + this.a.bF().nextGaussian() * ☃);
            ☃.q = (this.a.q + this.a.H / 2.0F + 0.5D);
            this.a.l.a(☃);
          }
        }
      }
      this.a.t().a(☃, 10.0F, 10.0F);
    }
    else
    {
      this.a.x().o();
      this.a.u().a(☃.p, ☃.q, ☃.r, 1.0D);
    }
    super.e();
  }
}
