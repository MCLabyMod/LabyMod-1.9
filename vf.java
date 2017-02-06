public abstract class vf
{
  private static int f = 20;
  protected sb a;
  protected aht b;
  protected ayp c;
  protected double d;
  private final sm g;
  private int h;
  private int i;
  private bbj j = bbj.a;
  private bbj k = bbj.a;
  private long l = 0L;
  private long m = 0L;
  private double n;
  private float o = 0.5F;
  private boolean p;
  private long q;
  protected ayo e;
  private cj r;
  private final ayq s;
  
  public vf(sb ☃, aht ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.g = ☃.a(yt.b);
    this.s = a();
    this.b.C().a(this);
  }
  
  protected abstract ayq a();
  
  public void a(double ☃)
  {
    this.d = ☃;
  }
  
  public float h()
  {
    return (float)this.g.e();
  }
  
  public boolean i()
  {
    return this.p;
  }
  
  public void j()
  {
    if (this.b.P() - this.q > f)
    {
      if (this.r != null)
      {
        this.c = null;
        this.c = a(this.r);
        this.q = this.b.P();
        this.p = false;
      }
    }
    else {
      this.p = true;
    }
  }
  
  public final ayp a(double ☃, double ☃, double ☃)
  {
    return a(new cj(on.c(☃), (int)☃, on.c(☃)));
  }
  
  public ayp a(cj ☃)
  {
    if (!b()) {
      return null;
    }
    if ((this.c != null) && (!this.c.b()) && (☃.equals(this.r))) {
      return this.c;
    }
    this.r = ☃;
    
    float ☃ = h();
    this.b.C.a("pathfind");
    cj ☃ = new cj(this.a);
    int ☃ = (int)(☃ + 8.0F);
    
    aic ☃ = new aic(this.b, ☃.a(-☃, -☃, -☃), ☃.a(☃, ☃, ☃), 0);
    ayp ☃ = this.s.a(☃, this.a, this.r, ☃);
    this.b.C.b();
    return ☃;
  }
  
  public ayp a(rr ☃)
  {
    if (!b()) {
      return null;
    }
    cj ☃ = new cj(☃);
    if ((this.c != null) && (!this.c.b()) && (☃.equals(this.r))) {
      return this.c;
    }
    this.r = ☃;
    
    float ☃ = h();
    this.b.C.a("pathfind");
    cj ☃ = new cj(this.a).a();
    int ☃ = (int)(☃ + 16.0F);
    
    aic ☃ = new aic(this.b, ☃.a(-☃, -☃, -☃), ☃.a(☃, ☃, ☃), 0);
    ayp ☃ = this.s.a(☃, this.a, ☃, ☃);
    this.b.C.b();
    return ☃;
  }
  
  public boolean a(double ☃, double ☃, double ☃, double ☃)
  {
    ayp ☃ = a(on.c(☃), (int)☃, on.c(☃));
    return a(☃, ☃);
  }
  
  public boolean a(rr ☃, double ☃)
  {
    ayp ☃ = a(☃);
    if (☃ != null) {
      return a(☃, ☃);
    }
    return false;
  }
  
  public boolean a(ayp ☃, double ☃)
  {
    if (☃ == null)
    {
      this.c = null;
      return false;
    }
    if (!☃.a(this.c)) {
      this.c = ☃;
    }
    d();
    if (this.c.d() == 0) {
      return false;
    }
    this.d = ☃;
    bbj ☃ = c();
    this.i = this.h;
    this.j = ☃;
    return true;
  }
  
  public ayp k()
  {
    return this.c;
  }
  
  public void l()
  {
    this.h += 1;
    if (this.p) {
      j();
    }
    if (n()) {
      return;
    }
    if (b())
    {
      m();
    }
    else if ((this.c != null) && (this.c.e() < this.c.d()))
    {
      bbj ☃ = c();
      bbj ☃ = this.c.a(this.a, this.c.e());
      if ((☃.c > ☃.c) && (!this.a.z) && (on.c(☃.b) == on.c(☃.b)) && (on.c(☃.d) == on.c(☃.d))) {
        this.c.c(this.c.e() + 1);
      }
    }
    if (n()) {
      return;
    }
    bbj ☃ = this.c.a(this.a);
    if (☃ == null) {
      return;
    }
    cj ☃ = new cj(☃).b();
    bbh ☃ = this.b.o(☃).c(this.b, ☃);
    ☃ = ☃.a(0.0D, 1.0D - ☃.e, 0.0D);
    
    this.a.u().a(☃.b, ☃.c, ☃.d, this.d);
  }
  
  protected void m()
  {
    bbj ☃ = c();
    
    int ☃ = this.c.d();
    for (int ☃ = this.c.e(); ☃ < this.c.d(); ☃++) {
      if (this.c.a(☃).b != Math.floor(☃.c))
      {
        ☃ = ☃;
        break;
      }
    }
    this.o = (this.a.G > 0.75F ? this.a.G / 2.0F : 0.75F - this.a.G / 2.0F);
    bbj ☃ = this.c.f();
    if ((on.e((float)(this.a.p - (☃.b + 0.5D))) < this.o) && (on.e((float)(this.a.r - (☃.d + 0.5D))) < this.o)) {
      this.c.c(this.c.e() + 1);
    }
    int ☃ = on.f(this.a.G);
    int ☃ = (int)this.a.H + 1;
    int ☃ = ☃;
    for (int ☃ = ☃ - 1; ☃ >= this.c.e(); ☃--) {
      if (a(☃, this.c.a(this.a, ☃), ☃, ☃, ☃))
      {
        this.c.c(☃);
        break;
      }
    }
    a(☃);
  }
  
  protected void a(bbj ☃)
  {
    if (this.h - this.i > 100)
    {
      if (☃.g(this.j) < 2.25D) {
        o();
      }
      this.i = this.h;
      this.j = ☃;
    }
    if ((this.c != null) && (!this.c.b()))
    {
      bbj ☃ = this.c.f();
      if (!☃.equals(this.k))
      {
        this.k = ☃;
        double ☃ = ☃.f(this.k);
        this.n = (this.a.ck() > 0.0F ? ☃ / this.a.ck() * 1000.0D : 0.0D);
      }
      else
      {
        this.l += System.currentTimeMillis() - this.m;
      }
      if ((this.n > 0.0D) && (this.l > this.n * 3.0D))
      {
        this.k = bbj.a;
        this.l = 0L;
        this.n = 0.0D;
        o();
      }
      this.m = System.currentTimeMillis();
    }
  }
  
  public boolean n()
  {
    return (this.c == null) || (this.c.b());
  }
  
  public void o()
  {
    this.c = null;
  }
  
  protected abstract bbj c();
  
  protected abstract boolean b();
  
  protected boolean p()
  {
    return (this.a.ai()) || (this.a.an());
  }
  
  protected void d() {}
  
  protected abstract boolean a(bbj parambbj1, bbj parambbj2, int paramInt1, int paramInt2, int paramInt3);
  
  public boolean b(cj ☃)
  {
    return this.b.o(☃.b()).b();
  }
}
