class wd$g
  extends tv
{
  private final wd c;
  private boolean d;
  private boolean e = false;
  
  public wd$g(wd ☃)
  {
    super(☃, 0.699999988079071D, 16);
    this.c = ☃;
  }
  
  public boolean a()
  {
    if (this.a <= 0)
    {
      if (!this.c.l.U().b("mobGriefing")) {
        return false;
      }
      this.e = false;
      this.d = wd.c(this.c);
      this.d = true;
    }
    return super.a();
  }
  
  public boolean b()
  {
    return (this.e) && (super.b());
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
      if ((this.e) && ((☃ instanceof akc)))
      {
        Integer ☃ = (Integer)☃.c(akc.c);
        if (☃.intValue() == 0)
        {
          ☃.a(☃, aju.a.u(), 2);
          ☃.b(☃, true);
        }
        else
        {
          ☃.a(☃, ☃.a(akc.c, Integer.valueOf(☃.intValue() - 1)), 2);
          ☃.b(2001, ☃, ajt.j(☃));
        }
        this.c.dc();
      }
      this.e = false;
      
      this.a = 10;
    }
  }
  
  protected boolean a(aht ☃, cj ☃)
  {
    ajt ☃ = ☃.o(☃).t();
    if ((☃ == aju.ak) && (this.d) && (!this.e))
    {
      ☃ = ☃.a();
      arc ☃ = ☃.o(☃);
      ☃ = ☃.t();
      if (((☃ instanceof akc)) && (((akc)☃).y(☃)))
      {
        this.e = true;
        return true;
      }
    }
    return false;
  }
}
