import java.util.Random;

class yu$a
  extends tk
{
  private int b;
  
  public yu$a(yu paramyu)
  {
    a(3);
  }
  
  public boolean a()
  {
    sa ☃ = this.a.A();
    if ((☃ == null) || (!☃.au())) {
      return false;
    }
    if (this.a.l.ae() == qk.a) {
      return false;
    }
    return true;
  }
  
  public void c()
  {
    this.b = 20;
    this.a.a(100);
  }
  
  public void d()
  {
    this.a.a(0);
  }
  
  public void e()
  {
    if (this.a.l.ae() == qk.a) {
      return;
    }
    this.b -= 1;
    
    sa ☃ = this.a.A();
    this.a.t().a(☃, 180.0F, 180.0F);
    
    double ☃ = this.a.h(☃);
    if (☃ < 400.0D)
    {
      if (this.b <= 0)
      {
        this.b = (20 + yu.c(this.a).nextInt(10) * 20 / 2);
        
        zu ☃ = new zu(this.a.l, this.a, ☃, this.a.cZ().k());
        this.a.l.a(☃);
        this.a.a(ng.fb, 2.0F, (yu.d(this.a).nextFloat() - yu.e(this.a).nextFloat()) * 0.2F + 1.0F);
      }
    }
    else {
      this.a.c(null);
    }
    super.e();
  }
}
