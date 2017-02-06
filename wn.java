import java.util.Random;

public class wn
  extends tk
{
  private final wk a;
  
  public wn(wk ☃)
  {
    this.a = ☃;
  }
  
  public boolean a()
  {
    return this.a.l.a(this.a.p, this.a.q, this.a.r, 10.0D);
  }
  
  public void e()
  {
    ql ☃ = this.a.l.D(new cj(this.a));
    this.a.x(false);
    this.a.a(wm.e);
    this.a.o(true);
    this.a.b_(0);
    this.a.l.d(new ya(this.a.l, this.a.p, this.a.q, this.a.r, true));
    yw ☃ = a(☃, this.a);
    ☃.m(this.a);
    for (int ☃ = 0; ☃ < 3; ☃++)
    {
      wk ☃ = a(☃);
      yw ☃ = a(☃, ☃);
      ☃.m(☃);
      ☃.g(this.a.bF().nextGaussian() * 0.5D, 0.0D, this.a.bF().nextGaussian() * 0.5D);
    }
  }
  
  private wk a(ql ☃)
  {
    wk ☃ = new wk(this.a.l);
    ☃.a(☃, null);
    ☃.b(this.a.p, this.a.q, this.a.r);
    ☃.W = 60;
    ☃.cL();
    ☃.a(wm.e);
    ☃.o(true);
    ☃.b_(0);
    ☃.l.a(☃);
    return ☃;
  }
  
  private yw a(ql ☃, wk ☃)
  {
    yw ☃ = new yw(☃.l);
    ☃.a(☃, null);
    ☃.b(☃.p, ☃.q, ☃.r);
    ☃.W = 60;
    ☃.cL();
    if (☃.a(rw.f) == null) {
      ☃.a(rw.f, new adq(ads.aa));
    }
    ago.a(☃.bF(), ☃.cb(), (int)(5.0F + ☃.c() * ☃.bF().nextInt(18)), false);
    ago.a(☃.bF(), ☃.a(rw.f), (int)(5.0F + ☃.c() * ☃.bF().nextInt(18)), false);
    
    ☃.l.a(☃);
    return ☃;
  }
}
