import java.util.Random;

class yx$e
  extends tk
{
  private yx a;
  private float b;
  private int c;
  
  public yx$e(yx ☃)
  {
    this.a = ☃;
    a(2);
  }
  
  public boolean a()
  {
    return (this.a.A() == null) && ((this.a.z) || (this.a.ai()) || (this.a.an()) || (this.a.a(rm.y)));
  }
  
  public void e()
  {
    if (--this.c <= 0)
    {
      this.c = (40 + this.a.bF().nextInt(60));
      this.b = this.a.bF().nextInt(360);
    }
    ((yx.d)this.a.u()).a(this.b, false);
  }
}
