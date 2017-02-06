import java.util.Random;

public class tr
  extends tk
{
  private ze b;
  private ze c;
  private aht d;
  private int e;
  vp a;
  
  public tr(ze ☃)
  {
    this.b = ☃;
    this.d = ☃.l;
    a(3);
  }
  
  public boolean a()
  {
    if (this.b.l() != 0) {
      return false;
    }
    if (this.b.bF().nextInt(500) != 0) {
      return false;
    }
    this.a = this.d.ai().a(new cj(this.b), 0);
    if (this.a == null) {
      return false;
    }
    if ((!f()) || (!this.b.q(true))) {
      return false;
    }
    rr ☃ = this.d.a(ze.class, this.b.bl().b(8.0D, 3.0D, 8.0D), this.b);
    if (☃ == null) {
      return false;
    }
    this.c = ((ze)☃);
    if ((this.c.l() != 0) || (!this.c.q(true))) {
      return false;
    }
    return true;
  }
  
  public void c()
  {
    this.e = 300;
    this.b.o(true);
  }
  
  public void d()
  {
    this.a = null;
    this.c = null;
    this.b.o(false);
  }
  
  public boolean b()
  {
    return (this.e >= 0) && (f()) && (this.b.l() == 0) && (this.b.q(false));
  }
  
  public void e()
  {
    this.e -= 1;
    this.b.t().a(this.c, 10.0F, 30.0F);
    if (this.b.h(this.c) > 2.25D) {
      this.b.x().a(this.c, 0.25D);
    } else if ((this.e == 0) && (this.c.da())) {
      i();
    }
    if (this.b.bF().nextInt(35) == 0) {
      this.d.a(this.b, (byte)12);
    }
  }
  
  private boolean f()
  {
    if (!this.a.i()) {
      return false;
    }
    int ☃ = (int)(this.a.c() * 0.35D);
    return this.a.e() < ☃;
  }
  
  private void i()
  {
    ze ☃ = this.b.b(this.c);
    this.c.b_(6000);
    this.b.b_(6000);
    this.c.r(false);
    this.b.r(false);
    ☃.b_(41536);
    ☃.b(this.b.p, this.b.q, this.b.r, 0.0F, 0.0F);
    this.d.a(☃);
    this.d.a(☃, (byte)12);
  }
}
