import java.util.Random;

class yu$e
  extends tk
{
  private int b;
  
  private yu$e(yu paramyu) {}
  
  public boolean a()
  {
    return (this.a.A() == null) && (yu.a(this.a).nextInt(40) == 0);
  }
  
  public boolean b()
  {
    return (this.a.A() == null) && (this.b > 0);
  }
  
  public void c()
  {
    this.b = (20 * (1 + yu.b(this.a).nextInt(3)));
    this.a.a(30);
  }
  
  public void d()
  {
    if (this.a.A() == null) {
      this.a.a(0);
    }
  }
  
  public void e()
  {
    this.b -= 1;
  }
}
