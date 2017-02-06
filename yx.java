import java.util.Random;

public class yx
  extends sb
  implements yl
{
  private static final ke<Integer> bt = kh.a(yx.class, kg.b);
  public float a;
  public float b;
  public float c;
  private boolean bu;
  
  public yx(aht ☃)
  {
    super(☃);
    
    this.f = new yx.d(this);
  }
  
  protected void r()
  {
    this.bp.a(1, new yx.b(this));
    
    this.bp.a(2, new yx.a(this));
    this.bp.a(3, new yx.e(this));
    
    this.bp.a(5, new yx.c(this));
    
    this.bq.a(1, new ux(this));
    this.bq.a(3, new uw(this, wh.class));
  }
  
  protected void i()
  {
    super.i();
    
    this.Z.a(bt, Integer.valueOf(1));
  }
  
  protected void a(int ☃)
  {
    this.Z.b(bt, Integer.valueOf(☃));
    a(0.51000005F * ☃, 0.51000005F * ☃);
    b(this.p, this.q, this.r);
    a(yt.a).a(☃ * ☃);
    a(yt.d).a(0.2F + 0.1F * ☃);
    c(bW());
    this.b_ = ☃;
  }
  
  public int da()
  {
    return ((Integer)this.Z.a(bt)).intValue();
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    ☃.a("Size", da() - 1);
    ☃.a("wasOnGround", this.bu);
  }
  
  public boolean db()
  {
    return da() <= 1;
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    int ☃ = ☃.h("Size");
    if (☃ < 0) {
      ☃ = 0;
    }
    a(☃ + 1);
    this.bu = ☃.p("wasOnGround");
  }
  
  protected cy o()
  {
    return cy.H;
  }
  
  public void m()
  {
    if ((!this.l.E) && (this.l.ae() == qk.a) && (da() > 0)) {
      this.F = true;
    }
    this.b += (this.a - this.b) * 0.5F;
    this.c = this.b;
    super.m();
    if ((this.z) && (!this.bu))
    {
      int ☃ = da();
      for (int ☃ = 0; ☃ < ☃ * 8; ☃++)
      {
        float ☃ = this.S.nextFloat() * 6.2831855F;
        float ☃ = this.S.nextFloat() * 0.5F + 0.5F;
        float ☃ = on.a(☃) * ☃ * 0.5F * ☃;
        float ☃ = on.b(☃) * ☃ * 0.5F * ☃;
        this.l.a(o(), this.p + ☃, bl().b, this.r + ☃, 0.0D, 0.0D, 0.0D, new int[0]);
      }
      a(cY(), cd(), ((this.S.nextFloat() - this.S.nextFloat()) * 0.2F + 1.0F) / 0.8F);
      this.a = -0.5F;
    }
    else if ((!this.z) && (this.bu))
    {
      this.a = 1.0F;
    }
    this.bu = this.z;
    cV();
  }
  
  protected void cV()
  {
    this.a *= 0.6F;
  }
  
  protected int cU()
  {
    return this.S.nextInt(20) + 10;
  }
  
  protected yx cT()
  {
    return new yx(this.l);
  }
  
  public void a(ke<?> ☃)
  {
    if (bt.equals(☃))
    {
      int ☃ = da();
      a(0.51000005F * ☃, 0.51000005F * ☃);
      this.v = this.aO;
      this.aM = this.aO;
      if ((ai()) && 
        (this.S.nextInt(20) == 0)) {
        ak();
      }
    }
    super.a(☃);
  }
  
  public void T()
  {
    int ☃ = da();
    if ((!this.l.E) && (☃ > 1) && (bQ() <= 0.0F))
    {
      int ☃ = 2 + this.S.nextInt(3);
      for (int ☃ = 0; ☃ < ☃; ☃++)
      {
        float ☃ = (☃ % 2 - 0.5F) * ☃ / 4.0F;
        float ☃ = (☃ / 2 - 0.5F) * ☃ / 4.0F;
        yx ☃ = cT();
        if (o_()) {
          ☃.c(bf());
        }
        if (cN()) {
          ☃.cL();
        }
        ☃.a(☃ / 2);
        ☃.b(this.p + ☃, this.q + 0.5D, this.r + ☃, this.S.nextFloat() * 360.0F, 0.0F);
        this.l.a(☃);
      }
    }
    super.T();
  }
  
  public void i(rr ☃)
  {
    super.i(☃);
    if (((☃ instanceof wh)) && (cW())) {
      d((sa)☃);
    }
  }
  
  public void d(zj ☃)
  {
    if (cW()) {
      d(☃);
    }
  }
  
  protected void d(sa ☃)
  {
    int ☃ = da();
    if ((D(☃)) && (h(☃) < 0.6D * ☃ * (0.6D * ☃)) && 
      (☃.a(rc.a(this), cX())))
    {
      a(ng.fp, 1.0F, (this.S.nextFloat() - this.S.nextFloat()) * 0.2F + 1.0F);
      a(this, ☃);
    }
  }
  
  public float bn()
  {
    return 0.625F * this.H;
  }
  
  protected boolean cW()
  {
    return !db();
  }
  
  protected int cX()
  {
    return da();
  }
  
  protected nf bR()
  {
    if (db()) {
      return ng.fD;
    }
    return ng.fu;
  }
  
  protected nf bS()
  {
    if (db()) {
      return ng.fC;
    }
    return ng.fr;
  }
  
  protected nf cY()
  {
    if (db()) {
      return ng.fF;
    }
    return ng.fx;
  }
  
  protected ado I()
  {
    if (da() == 1) {
      return ads.aT;
    }
    return null;
  }
  
  protected kk J()
  {
    return da() == 1 ? azt.ac : azt.a;
  }
  
  public boolean cF()
  {
    cj ☃ = new cj(on.c(this.p), 0, on.c(this.r));
    ase ☃ = this.l.f(☃);
    if ((this.l.T().t() == ahy.c) && (this.S.nextInt(4) != 1)) {
      return false;
    }
    if (this.l.ae() != qk.a)
    {
      aig ☃ = this.l.b(☃);
      if ((☃ == ail.h) && (this.q > 50.0D) && (this.q < 70.0D) && (this.S.nextFloat() < 0.5F) && 
        (this.S.nextFloat() < this.l.E()) && (this.l.k(new cj(this)) <= this.S.nextInt(8))) {
        return super.cF();
      }
      if ((this.S.nextInt(10) == 0) && (☃.a(987234911L).nextInt(10) == 0) && (this.q < 40.0D)) {
        return super.cF();
      }
    }
    return false;
  }
  
  protected float cd()
  {
    return 0.4F * da();
  }
  
  public int N()
  {
    return 0;
  }
  
  protected boolean dc()
  {
    return da() > 0;
  }
  
  protected void ch()
  {
    this.t = 0.41999998688697815D;
    this.ai = true;
  }
  
  public sd a(ql ☃, sd ☃)
  {
    int ☃ = this.S.nextInt(3);
    if ((☃ < 2) && (this.S.nextFloat() < 0.5F * ☃.c())) {
      ☃++;
    }
    int ☃ = 1 << ☃;
    a(☃);
    
    return super.a(☃, ☃);
  }
  
  static class d
    extends sy
  {
    private float i;
    private int j;
    private yx k;
    private boolean l;
    
    public d(yx ☃)
    {
      super();
      this.k = ☃;
      this.i = (180.0F * ☃.v / 3.1415927F);
    }
    
    public void a(float ☃, boolean ☃)
    {
      this.i = ☃;
      this.l = ☃;
    }
    
    public void a(double ☃)
    {
      this.e = ☃;
      this.h = sy.a.b;
    }
    
    public void c()
    {
      this.a.v = a(this.a.v, this.i, 90.0F);
      this.a.aO = this.a.v;
      this.a.aM = this.a.v;
      if (this.h != sy.a.b)
      {
        this.a.o(0.0F);
        return;
      }
      this.h = sy.a.a;
      if (this.a.z)
      {
        this.a.l((float)(this.e * this.a.a(yt.d).e()));
        if (this.j-- <= 0)
        {
          this.j = this.k.cU();
          if (this.l) {
            this.j /= 3;
          }
          this.k.w().a();
          if (this.k.dc()) {
            this.k.a(this.k.cZ(), this.k.cd(), ((this.k.bF().nextFloat() - this.k.bF().nextFloat()) * 0.2F + 1.0F) * 0.8F);
          }
        }
        else
        {
          this.k.bd = (this.k.be = 0.0F);
          this.a.l(0.0F);
        }
      }
      else
      {
        this.a.l((float)(this.e * this.a.a(yt.d).e()));
      }
    }
  }
  
  protected nf cZ()
  {
    return db() ? ng.fE : ng.fv;
  }
  
  static class a
    extends tk
  {
    private yx a;
    private int b;
    
    public a(yx ☃)
    {
      this.a = ☃;
      a(2);
    }
    
    public boolean a()
    {
      sa ☃ = this.a.A();
      if (☃ == null) {
        return false;
      }
      if (!☃.au()) {
        return false;
      }
      if (((☃ instanceof zj)) && (((zj)☃).bJ.a)) {
        return false;
      }
      return true;
    }
    
    public void c()
    {
      this.b = 300;
      super.c();
    }
    
    public boolean b()
    {
      sa ☃ = this.a.A();
      if (☃ == null) {
        return false;
      }
      if (!☃.au()) {
        return false;
      }
      if (((☃ instanceof zj)) && (((zj)☃).bJ.a)) {
        return false;
      }
      if (--this.b <= 0) {
        return false;
      }
      return true;
    }
    
    public void e()
    {
      this.a.a(this.a.A(), 10.0F, 10.0F);
      ((yx.d)this.a.u()).a(this.a.v, this.a.cW());
    }
  }
  
  static class e
    extends tk
  {
    private yx a;
    private float b;
    private int c;
    
    public e(yx ☃)
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
  
  static class b
    extends tk
  {
    private yx a;
    
    public b(yx ☃)
    {
      this.a = ☃;
      a(5);
      ((ve)☃.x()).c(true);
    }
    
    public boolean a()
    {
      return (this.a.ai()) || (this.a.an());
    }
    
    public void e()
    {
      if (this.a.bF().nextFloat() < 0.8F) {
        this.a.w().a();
      }
      ((yx.d)this.a.u()).a(1.2D);
    }
  }
  
  static class c
    extends tk
  {
    private yx a;
    
    public c(yx ☃)
    {
      this.a = ☃;
      a(5);
    }
    
    public boolean a()
    {
      return true;
    }
    
    public void e()
    {
      ((yx.d)this.a.u()).a(1.0D);
    }
  }
}
