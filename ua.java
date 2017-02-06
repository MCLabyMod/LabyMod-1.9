import java.util.Random;

public class ua
  extends tk
{
  private wh a;
  private ze b;
  private int c;
  
  public ua(wh ☃)
  {
    this.a = ☃;
    a(3);
  }
  
  public boolean a()
  {
    if (!this.a.l.B()) {
      return false;
    }
    if (this.a.bF().nextInt(8000) != 0) {
      return false;
    }
    this.b = ((ze)this.a.l.a(ze.class, this.a.bl().b(6.0D, 2.0D, 6.0D), this.a));
    return this.b != null;
  }
  
  public boolean b()
  {
    return this.c > 0;
  }
  
  public void c()
  {
    this.c = 400;
    this.a.a(true);
  }
  
  public void d()
  {
    this.a.a(false);
    this.b = null;
  }
  
  public void e()
  {
    this.a.t().a(this.b, 30.0F, 30.0F);
    this.c -= 1;
  }
}
