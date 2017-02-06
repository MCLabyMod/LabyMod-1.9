import java.util.Random;

public class tb
  extends tk
{
  private wj a;
  private zj b;
  private aht c;
  private float d;
  private int e;
  
  public tb(wj ☃, float ☃)
  {
    this.a = ☃;
    this.c = ☃.l;
    this.d = ☃;
    a(2);
  }
  
  public boolean a()
  {
    this.b = this.c.a(this.a, this.d);
    if (this.b == null) {
      return false;
    }
    return a(this.b);
  }
  
  public boolean b()
  {
    if (!this.b.au()) {
      return false;
    }
    if (this.a.h(this.b) > this.d * this.d) {
      return false;
    }
    return (this.e > 0) && (a(this.b));
  }
  
  public void c()
  {
    this.a.s(true);
    this.e = (40 + this.a.bF().nextInt(40));
  }
  
  public void d()
  {
    this.a.s(false);
    this.b = null;
  }
  
  public void e()
  {
    this.a.t().a(this.b.p, this.b.q + this.b.bn(), this.b.r, 10.0F, this.a.N());
    this.e -= 1;
  }
  
  private boolean a(zj ☃)
  {
    for (qm ☃ : )
    {
      adq ☃ = ☃.b(☃);
      if (☃ != null)
      {
        if ((this.a.cZ()) && (☃.b() == ads.be)) {
          return true;
        }
        if (this.a.e(☃)) {
          return true;
        }
      }
    }
    return false;
  }
}
