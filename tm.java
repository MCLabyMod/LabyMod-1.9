public class tm
  extends tv
{
  private final ze c;
  private boolean d;
  private boolean e;
  private int f;
  
  public tm(ze ☃, double ☃)
  {
    super(☃, ☃, 16);
    this.c = ☃;
  }
  
  public boolean a()
  {
    if (this.a <= 0)
    {
      if (!this.c.l.U().b("mobGriefing")) {
        return false;
      }
      this.f = -1;
      this.d = this.c.di();
      this.e = this.c.dh();
    }
    return super.a();
  }
  
  public boolean b()
  {
    return (this.f >= 0) && (super.b());
  }
  
  public void c()
  {
    super.c();
  }
  
  public void d()
  {
    super.d();
  }
  
  public void e()
  {
    super.e();
    
    this.c.t().a(this.b.p() + 0.5D, this.b.q() + 1, this.b.r() + 0.5D, 10.0F, this.c.N());
    if (f())
    {
      aht ☃ = this.c.l;
      cj ☃ = this.b.a();
      
      arc ☃ = ☃.o(☃);
      ajt ☃ = ☃.t();
      if ((this.f == 0) && ((☃ instanceof akn)) && (((akn)☃).y(☃)))
      {
        ☃.b(☃, true);
      }
      else if ((this.f == 1) && (☃ == aju.a))
      {
        qv ☃ = this.c.de();
        for (int ☃ = 0; ☃ < ☃.u_(); ☃++)
        {
          adq ☃ = ☃.a(☃);
          boolean ☃ = false;
          if (☃ != null) {
            if (☃.b() == ads.P)
            {
              ☃.a(☃, aju.aj.u(), 3);
              ☃ = true;
            }
            else if (☃.b() == ads.cc)
            {
              ☃.a(☃, aju.cc.u(), 3);
              ☃ = true;
            }
            else if (☃.b() == ads.cb)
            {
              ☃.a(☃, aju.cb.u(), 3);
              ☃ = true;
            }
            else if (☃.b() == ads.cU)
            {
              ☃.a(☃, aju.cZ.u(), 3);
              ☃ = true;
            }
          }
          if (☃)
          {
            ☃.b -= 1;
            if (☃.b > 0) {
              break;
            }
            ☃.a(☃, null); break;
          }
        }
      }
      this.f = -1;
      
      this.a = 10;
    }
  }
  
  protected boolean a(aht ☃, cj ☃)
  {
    ajt ☃ = ☃.o(☃).t();
    if (☃ == aju.ak)
    {
      ☃ = ☃.a();
      arc ☃ = ☃.o(☃);
      ☃ = ☃.t();
      if (((☃ instanceof akn)) && (((akn)☃).y(☃)) && (this.e) && ((this.f == 0) || (this.f < 0)))
      {
        this.f = 0;
        return true;
      }
      if ((☃ == aju.a) && (this.d) && ((this.f == 1) || (this.f < 0)))
      {
        this.f = 1;
        return true;
      }
    }
    return false;
  }
}
