import java.util.Random;

abstract class awa$d
  extends awg
{
  protected int a;
  protected int b;
  protected int c;
  protected int d = -1;
  
  public awa$d() {}
  
  protected awa$d(Random ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    super(0);
    
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    
    a(cq.c.a.a(☃));
    if (e().k() == cq.a.c) {
      this.l = new avp(☃, ☃, ☃, ☃ + ☃ - 1, ☃ + ☃ - 1, ☃ + ☃ - 1);
    } else {
      this.l = new avp(☃, ☃, ☃, ☃ + ☃ - 1, ☃ + ☃ - 1, ☃ + ☃ - 1);
    }
  }
  
  protected void a(dn ☃)
  {
    ☃.a("Width", this.a);
    ☃.a("Height", this.b);
    ☃.a("Depth", this.c);
    ☃.a("HPos", this.d);
  }
  
  protected void b(dn ☃)
  {
    this.a = ☃.h("Width");
    this.b = ☃.h("Height");
    this.c = ☃.h("Depth");
    this.d = ☃.h("HPos");
  }
  
  protected boolean a(aht ☃, avp ☃, int ☃)
  {
    if (this.d >= 0) {
      return true;
    }
    int ☃ = 0;
    int ☃ = 0;
    cj.a ☃ = new cj.a();
    for (int ☃ = this.l.c; ☃ <= this.l.f; ☃++) {
      for (int ☃ = this.l.a; ☃ <= this.l.d; ☃++)
      {
        ☃.c(☃, 64, ☃);
        if (☃.b(☃))
        {
          ☃ += Math.max(☃.q(☃).q(), ☃.s.i());
          ☃++;
        }
      }
    }
    if (☃ == 0) {
      return false;
    }
    this.d = (☃ / ☃);
    this.l.a(0, this.d - this.l.b + ☃, 0);
    return true;
  }
}
