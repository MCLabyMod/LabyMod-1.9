public class tz
  extends tv
{
  private final wb c;
  
  public tz(wb ☃, double ☃)
  {
    super(☃, ☃, 8);
    this.c = ☃;
  }
  
  public boolean a()
  {
    return (this.c.cZ()) && (!this.c.db()) && (super.a());
  }
  
  public boolean b()
  {
    return super.b();
  }
  
  public void c()
  {
    super.c();
    this.c.dd().a(false);
  }
  
  public void d()
  {
    super.d();
    this.c.q(false);
  }
  
  public void e()
  {
    super.e();
    
    this.c.dd().a(false);
    if (!f()) {
      this.c.q(false);
    } else if (!this.c.db()) {
      this.c.q(true);
    }
  }
  
  protected boolean a(aht ☃, cj ☃)
  {
    if (!☃.d(☃.a())) {
      return false;
    }
    arc ☃ = ☃.o(☃);
    ajt ☃ = ☃.t();
    if (☃ == aju.ae)
    {
      apv ☃ = ☃.r(☃);
      if (((☃ instanceof apx)) && (((apx)☃).l < 1)) {
        return true;
      }
    }
    else
    {
      if (☃ == aju.am) {
        return true;
      }
      if ((☃ == aju.C) && (☃.c(ajr.a) != ajr.a.a)) {
        return true;
      }
    }
    return false;
  }
}
