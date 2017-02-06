import java.util.Random;

public class wd
  extends vw
{
  private static final ke<Integer> bv = kh.a(wd.class, kg.b);
  private int bw = 0;
  private int bx = 0;
  private boolean bz = false;
  private int bA = 0;
  private int bB = 0;
  
  public wd(aht ☃)
  {
    super(☃);
    a(0.4F, 0.5F);
    
    this.g = new wd.d(this);
    
    this.f = new wd.e(this);
    
    c(0.0D);
  }
  
  protected void r()
  {
    this.bp.a(1, new th(this));
    this.bp.a(1, new wd.f(this, 2.2D));
    this.bp.a(2, new td(this, 0.8D));
    this.bp.a(3, new up(this, 1.0D, ads.cb, false));
    this.bp.a(3, new up(this, 1.0D, ads.cg, false));
    this.bp.a(3, new up(this, 1.0D, ado.a(aju.N), false));
    this.bp.a(4, new wd.b(this, zj.class, 8.0F, 2.2D, 2.2D));
    this.bp.a(4, new wd.b(this, wj.class, 10.0F, 2.2D, 2.2D));
    this.bp.a(4, new wd.b(this, yq.class, 4.0F, 2.2D, 2.2D));
    this.bp.a(5, new wd.g(this));
    this.bp.a(6, new ug(this, 0.6D));
    this.bp.a(11, new tp(this, zj.class, 10.0F));
  }
  
  protected float cg()
  {
    if ((this.A) || ((this.f.a()) && (this.f.e() > this.q + 0.5D))) {
      return 0.5F;
    }
    ayp ☃ = this.h.k();
    if ((☃ != null) && (☃.e() < ☃.d()))
    {
      bbj ☃ = ☃.a(this);
      if (☃.c > this.q) {
        return 0.5F;
      }
    }
    if (this.f.b() <= 0.6D) {
      return 0.2F;
    }
    return 0.3F;
  }
  
  protected void ch()
  {
    super.ch();
    double ☃ = this.f.b();
    if (☃ > 0.0D)
    {
      double ☃ = this.s * this.s + this.u * this.u;
      if (☃ < 0.010000000000000002D) {
        a(0.0F, 1.0F, 0.1F);
      }
    }
    if (!this.l.E) {
      this.l.a(this, (byte)1);
    }
  }
  
  public float r(float ☃)
  {
    if (this.bx == 0) {
      return 0.0F;
    }
    return (this.bw + ☃) / this.bx;
  }
  
  public void c(double ☃)
  {
    x().a(☃);
    this.f.a(this.f.d(), this.f.e(), this.f.f(), ☃);
  }
  
  public void k(boolean ☃)
  {
    super.k(☃);
    if (☃) {
      a(da(), cd(), ((this.S.nextFloat() - this.S.nextFloat()) * 0.2F + 1.0F) * 0.8F);
    }
  }
  
  public void cZ()
  {
    k(true);
    this.bx = 10;
    this.bw = 0;
  }
  
  protected void i()
  {
    super.i();
    
    this.Z.a(bv, Integer.valueOf(0));
  }
  
  public void M()
  {
    if (this.bA > 0) {
      this.bA -= 1;
    }
    if (this.bB > 0)
    {
      this.bB -= this.S.nextInt(3);
      if (this.bB < 0) {
        this.bB = 0;
      }
    }
    if (this.z)
    {
      if (!this.bz)
      {
        k(false);
        dj();
      }
      if ((db() == 99) && (this.bA == 0))
      {
        sa ☃ = A();
        if ((☃ != null) && (h(☃) < 16.0D))
        {
          a(☃.p, ☃.r);
          this.f.a(☃.p, ☃.q, ☃.r, this.f.b());
          cZ();
          this.bz = true;
        }
      }
      wd.d ☃ = (wd.d)this.g;
      if (!☃.c())
      {
        if ((this.f.a()) && (this.bA == 0))
        {
          ayp ☃ = this.h.k();
          bbj ☃ = new bbj(this.f.d(), this.f.e(), this.f.f());
          if ((☃ != null) && (☃.e() < ☃.d())) {
            ☃ = ☃.a(this);
          }
          a(☃.b, ☃.d);
          cZ();
        }
      }
      else if (!☃.d()) {
        dd();
      }
    }
    this.bz = this.z;
  }
  
  public void al() {}
  
  private void a(double ☃, double ☃)
  {
    this.v = ((float)(on.b(☃ - this.r, ☃ - this.p) * 57.2957763671875D) - 90.0F);
  }
  
  private void dd()
  {
    ((wd.d)this.g).a(true);
  }
  
  private void dh()
  {
    ((wd.d)this.g).a(false);
  }
  
  private void di()
  {
    if (this.f.b() < 2.2D) {
      this.bA = 10;
    } else {
      this.bA = 1;
    }
  }
  
  private void dj()
  {
    di();
    dh();
  }
  
  public void n()
  {
    super.n();
    if (this.bw != this.bx)
    {
      this.bw += 1;
    }
    else if (this.bx != 0)
    {
      this.bw = 0;
      this.bx = 0;
      k(false);
    }
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.a).a(3.0D);
    a(yt.d).a(0.30000001192092896D);
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    ☃.a("RabbitType", db());
    ☃.a("MoreCarrotTicks", this.bB);
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    l(☃.h("RabbitType"));
    this.bB = ☃.h("MoreCarrotTicks");
  }
  
  protected nf da()
  {
    return ng.es;
  }
  
  protected nf G()
  {
    return ng.eo;
  }
  
  protected nf bR()
  {
    return ng.er;
  }
  
  protected nf bS()
  {
    return ng.eq;
  }
  
  public boolean B(rr ☃)
  {
    if (db() == 99)
    {
      a(ng.ep, 1.0F, (this.S.nextFloat() - this.S.nextFloat()) * 0.2F + 1.0F);
      return ☃.a(rc.a(this), 8.0F);
    }
    return ☃.a(rc.a(this), 3.0F);
  }
  
  public nh bz()
  {
    return db() == 99 ? nh.f : nh.g;
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (b(☃)) {
      return false;
    }
    return super.a(☃, ☃);
  }
  
  protected kk J()
  {
    return azt.A;
  }
  
  private boolean a(ado ☃)
  {
    return (☃ == ads.cb) || (☃ == ads.cg) || (☃ == ado.a(aju.N));
  }
  
  public wd b(ro ☃)
  {
    wd ☃ = new wd(this.l);
    int ☃ = dk();
    if (this.S.nextInt(20) != 0) {
      if (((☃ instanceof wd)) && (this.S.nextBoolean())) {
        ☃ = ((wd)☃).db();
      } else {
        ☃ = db();
      }
    }
    ☃.l(☃);
    return ☃;
  }
  
  public boolean e(adq ☃)
  {
    return (☃ != null) && (a(☃.b()));
  }
  
  public int db()
  {
    return ((Integer)this.Z.a(bv)).intValue();
  }
  
  public void l(int ☃)
  {
    if (☃ == 99)
    {
      a(yt.g).a(8.0D);
      this.bp.a(4, new wd.a(this));
      this.bq.a(1, new uv(this, false, new Class[0]));
      this.bq.a(2, new uy(this, zj.class, true));
      this.bq.a(2, new uy(this, wj.class, true));
      if (!o_()) {
        c(di.a("entity.KillerBunny.name"));
      }
    }
    this.Z.b(bv, Integer.valueOf(☃));
  }
  
  public sd a(ql ☃, sd ☃)
  {
    ☃ = super.a(☃, ☃);
    
    int ☃ = dk();
    boolean ☃ = false;
    if ((☃ instanceof wd.c))
    {
      ☃ = ((wd.c)☃).a;
      ☃ = true;
    }
    else
    {
      ☃ = new wd.c(☃);
    }
    l(☃);
    if (☃) {
      b_(41536);
    }
    return ☃;
  }
  
  private int dk()
  {
    aig ☃ = this.l.b(new cj(this));
    
    int ☃ = this.S.nextInt(100);
    if (☃.p()) {
      return ☃ < 80 ? 1 : 3;
    }
    if ((☃ instanceof aim)) {
      return 4;
    }
    return ☃ < 90 ? 5 : ☃ < 50 ? 0 : 2;
  }
  
  public static class c
    implements sd
  {
    public int a;
    
    public c(int ☃)
    {
      this.a = ☃;
    }
  }
  
  private boolean dl()
  {
    return this.bB == 0;
  }
  
  protected void dc()
  {
    akc ☃ = (akc)aju.cb;
    arc ☃ = ☃.e(☃.g());
    
    this.l.a(cy.M, this.p + this.S.nextFloat() * this.G * 2.0F - this.G, this.q + 0.5D + this.S.nextFloat() * this.H, this.r + this.S.nextFloat() * this.G * 2.0F - this.G, 0.0D, 0.0D, 0.0D, new int[] { ajt.j(☃) });
    this.bB = 40;
  }
  
  public void a(byte ☃)
  {
    if (☃ == 1)
    {
      am();
      
      this.bx = 10;
      this.bw = 0;
    }
    else
    {
      super.a(☃);
    }
  }
  
  public void a(ke<?> ☃)
  {
    super.a(☃);
  }
  
  public class d
    extends sw
  {
    private wd c;
    private boolean d = false;
    
    public d(wd ☃)
    {
      super();
      this.c = ☃;
    }
    
    public boolean c()
    {
      return this.a;
    }
    
    public boolean d()
    {
      return this.d;
    }
    
    public void a(boolean ☃)
    {
      this.d = ☃;
    }
    
    public void b()
    {
      if (this.a)
      {
        this.c.cZ();
        this.a = false;
      }
    }
  }
  
  static class e
    extends sy
  {
    private wd i;
    private double j;
    
    public e(wd ☃)
    {
      super();
      this.i = ☃;
    }
    
    public void c()
    {
      if ((this.i.z) && (!wd.a(this.i)) && (!((wd.d)wd.b(this.i)).c())) {
        this.i.c(0.0D);
      } else if (a()) {
        this.i.c(this.j);
      }
      super.c();
    }
    
    public void a(double ☃, double ☃, double ☃, double ☃)
    {
      if (this.i.ai()) {
        ☃ = 1.5D;
      }
      super.a(☃, ☃, ☃, ☃);
      if (☃ > 0.0D) {
        this.j = ☃;
      }
    }
  }
  
  static class b<T extends rr>
    extends ta<T>
  {
    private wd c;
    
    public b(wd ☃, Class<T> ☃, float ☃, double ☃, double ☃)
    {
      super(☃, ☃, ☃, ☃);
      this.c = ☃;
    }
    
    public boolean a()
    {
      return (this.c.db() != 99) && (super.a());
    }
  }
  
  static class g
    extends tv
  {
    private final wd c;
    private boolean d;
    private boolean e = false;
    
    public g(wd ☃)
    {
      super(0.699999988079071D, 16);
      this.c = ☃;
    }
    
    public boolean a()
    {
      if (this.a <= 0)
      {
        if (!this.c.l.U().b("mobGriefing")) {
          return false;
        }
        this.e = false;
        this.d = wd.c(this.c);
        this.d = true;
      }
      return super.a();
    }
    
    public boolean b()
    {
      return (this.e) && (super.b());
    }
    
    public void c()
    {
      super.c();
    }
    
    public void d()
    {
      super.d();
    }
    
    public void e()
    {
      super.e();
      
      this.c.t().a(this.b.p() + 0.5D, this.b.q() + 1, this.b.r() + 0.5D, 10.0F, this.c.N());
      if (f())
      {
        aht ☃ = this.c.l;
        cj ☃ = this.b.a();
        
        arc ☃ = ☃.o(☃);
        ajt ☃ = ☃.t();
        if ((this.e) && ((☃ instanceof akc)))
        {
          Integer ☃ = (Integer)☃.c(akc.c);
          if (☃.intValue() == 0)
          {
            ☃.a(☃, aju.a.u(), 2);
            ☃.b(☃, true);
          }
          else
          {
            ☃.a(☃, ☃.a(akc.c, Integer.valueOf(☃.intValue() - 1)), 2);
            ☃.b(2001, ☃, ajt.j(☃));
          }
          this.c.dc();
        }
        this.e = false;
        
        this.a = 10;
      }
    }
    
    protected boolean a(aht ☃, cj ☃)
    {
      ajt ☃ = ☃.o(☃).t();
      if ((☃ == aju.ak) && (this.d) && (!this.e))
      {
        ☃ = ☃.a();
        arc ☃ = ☃.o(☃);
        ☃ = ☃.t();
        if (((☃ instanceof akc)) && (((akc)☃).y(☃)))
        {
          this.e = true;
          return true;
        }
      }
      return false;
    }
  }
  
  static class f
    extends uc
  {
    private wd b;
    
    public f(wd ☃, double ☃)
    {
      super(☃);
      this.b = ☃;
    }
    
    public void e()
    {
      super.e();
      
      this.b.c(this.a);
    }
  }
  
  static class a
    extends ts
  {
    public a(wd ☃)
    {
      super(1.4D, true);
    }
    
    protected double a(sa ☃)
    {
      return 4.0F + ☃.G;
    }
  }
}
