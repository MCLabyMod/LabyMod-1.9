public class un
  extends tk
{
  yi a;
  sa b;
  
  public un(yi ☃)
  {
    this.a = ☃;
    a(1);
  }
  
  public boolean a()
  {
    sa ☃ = this.a.A();
    return (this.a.da() > 0) || ((☃ != null) && (this.a.h(☃) < 9.0D));
  }
  
  public void c()
  {
    this.a.x().o();
    this.b = this.a.A();
  }
  
  public void d()
  {
    this.b = null;
  }
  
  public void e()
  {
    if (this.b == null)
    {
      this.a.a(-1);
      return;
    }
    if (this.a.h(this.b) > 49.0D)
    {
      this.a.a(-1);
      return;
    }
    if (!this.a.y().a(this.b))
    {
      this.a.a(-1);
      return;
    }
    this.a.a(1);
  }
}
