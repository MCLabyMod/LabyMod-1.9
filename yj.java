import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class yj
  extends yq
{
  private static final UUID a = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
  private static final sn b = new sn(a, "Attacking speed boost", 0.15000000596046448D, 0).a(false);
  private static final Set<ajt> c = Sets.newIdentityHashSet();
  private static final ke<Optional<arc>> bv = kh.a(yj.class, kg.g);
  private static final ke<Boolean> bw = kh.a(yj.class, kg.h);
  
  static
  {
    c.add(aju.c);
    c.add(aju.d);
    c.add(aju.m);
    c.add(aju.n);
    c.add(aju.N);
    c.add(aju.O);
    c.add(aju.P);
    c.add(aju.Q);
    c.add(aju.W);
    c.add(aju.aK);
    c.add(aju.aL);
    c.add(aju.aU);
    c.add(aju.bk);
    c.add(aju.bw);
  }
  
  private int bx = 0;
  private int by = 0;
  
  public yj(aht ☃)
  {
    super(☃);
    a(0.6F, 2.9F);
    this.P = 1.0F;
    
    a(aym.g, -1.0F);
  }
  
  protected void r()
  {
    this.bp.a(0, new th(this));
    this.bp.a(2, new ts(this, 1.0D, false));
    
    this.bp.a(7, new ug(this, 1.0D));
    this.bp.a(8, new tp(this, zj.class, 8.0F));
    this.bp.a(8, new uf(this));
    
    this.bp.a(10, new yj.a(this));
    this.bp.a(11, new yj.c(this));
    
    this.bq.a(1, new yj.b(this));
    this.bq.a(2, new uv(this, false, new Class[0]));
    this.bq.a(3, new uy(this, yk.class, 10, true, false, new Predicate()
    {
      public boolean a(yk ☃)
      {
        return ☃.o();
      }
    }));
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.a).a(40.0D);
    a(yt.d).a(0.30000001192092896D);
    a(yt.e).a(7.0D);
    a(yt.b).a(64.0D);
  }
  
  public void c(sa ☃)
  {
    super.c(☃);
    
    sm ☃ = a(yt.d);
    if (☃ == null)
    {
      this.by = 0;
      this.Z.b(bw, Boolean.valueOf(false));
      
      ☃.c(b);
    }
    else
    {
      this.by = this.T;
      this.Z.b(bw, Boolean.valueOf(true));
      if (!☃.a(b)) {
        ☃.b(b);
      }
    }
  }
  
  protected void i()
  {
    super.i();
    
    this.Z.a(bv, Optional.absent());
    this.Z.a(bw, Boolean.valueOf(false));
  }
  
  public void o()
  {
    if (this.T >= this.bx + 400)
    {
      this.bx = this.T;
      if (!ad()) {
        this.l.a(this.p, this.q + bn(), this.r, ng.aY, bz(), 2.5F, 1.0F, false);
      }
    }
  }
  
  public void a(ke<?> ☃)
  {
    if ((bw.equals(☃)) && 
      (dc()) && (this.l.E)) {
      o();
    }
    super.a(☃);
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    arc ☃ = db();
    if (☃ != null)
    {
      ☃.a("carried", (short)ajt.a(☃.t()));
      ☃.a("carriedData", (short)☃.t().e(☃));
    }
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    arc ☃;
    arc ☃;
    if (☃.b("carried", 8)) {
      ☃ = ajt.b(☃.l("carried")).a(☃.g("carriedData") & 0xFFFF);
    } else {
      ☃ = ajt.b(☃.g("carried")).a(☃.g("carriedData") & 0xFFFF);
    }
    if ((☃ == null) || (☃.t() == null) || (☃.a() == axe.a)) {
      ☃ = null;
    }
    a(☃);
  }
  
  private boolean c(zj ☃)
  {
    adq ☃ = ☃.br.b[3];
    if ((☃ != null) && (☃.b() == ado.a(aju.aU))) {
      return false;
    }
    bbj ☃ = ☃.f(1.0F).a();
    bbj ☃ = new bbj(this.p - ☃.p, bl().b + this.H / 2.0F - (☃.q + ☃.bn()), this.r - ☃.r);
    double ☃ = ☃.b();
    ☃ = ☃.a();
    double ☃ = ☃.b(☃);
    if (☃ > 1.0D - 0.025D / ☃) {
      return ☃.D(this);
    }
    return false;
  }
  
  public float bn()
  {
    return 2.55F;
  }
  
  public void n()
  {
    if (this.l.E) {
      for (int ☃ = 0; ☃ < 2; ☃++) {
        this.l.a(cy.y, this.p + (this.S.nextDouble() - 0.5D) * this.G, this.q + this.S.nextDouble() * this.H - 0.25D, this.r + (this.S.nextDouble() - 0.5D) * this.G, (this.S.nextDouble() - 0.5D) * 2.0D, -this.S.nextDouble(), (this.S.nextDouble() - 0.5D) * 2.0D, new int[0]);
      }
    }
    this.bc = false;
    
    super.n();
  }
  
  protected void M()
  {
    if (ah()) {
      a(rc.f, 1.0F);
    }
    if ((this.l.B()) && (this.T >= this.by + 600))
    {
      float ☃ = e(1.0F);
      if ((☃ > 0.5F) && 
        (this.l.h(new cj(this))) && (this.S.nextFloat() * 30.0F < (☃ - 0.4F) * 2.0F))
      {
        c(null);
        da();
      }
    }
    super.M();
  }
  
  protected boolean da()
  {
    double ☃ = this.p + (this.S.nextDouble() - 0.5D) * 64.0D;
    double ☃ = this.q + (this.S.nextInt(64) - 32);
    double ☃ = this.r + (this.S.nextDouble() - 0.5D) * 64.0D;
    return l(☃, ☃, ☃);
  }
  
  protected boolean a(rr ☃)
  {
    bbj ☃ = new bbj(this.p - ☃.p, bl().b + this.H / 2.0F - ☃.q + ☃.bn(), this.r - ☃.r);
    ☃ = ☃.a();
    double ☃ = 16.0D;
    double ☃ = this.p + (this.S.nextDouble() - 0.5D) * 8.0D - ☃.b * ☃;
    double ☃ = this.q + (this.S.nextInt(16) - 8) - ☃.c * ☃;
    double ☃ = this.r + (this.S.nextDouble() - 0.5D) * 8.0D - ☃.d * ☃;
    return l(☃, ☃, ☃);
  }
  
  private boolean l(double ☃, double ☃, double ☃)
  {
    boolean ☃ = k(☃, ☃, ☃);
    if (☃)
    {
      this.l.a(null, this.m, this.n, this.o, ng.aZ, bz(), 1.0F, 1.0F);
      a(ng.aZ, 1.0F, 1.0F);
    }
    return ☃;
  }
  
  protected nf G()
  {
    return dc() ? ng.aX : ng.aU;
  }
  
  protected nf bR()
  {
    return ng.aW;
  }
  
  protected nf bS()
  {
    return ng.aV;
  }
  
  protected void a(boolean ☃, int ☃)
  {
    super.a(☃, ☃);
    arc ☃ = db();
    if (☃ != null) {
      a(new adq(☃.t(), 1, ☃.t().e(☃)), 0.0F);
    }
  }
  
  protected kk J()
  {
    return azt.u;
  }
  
  public void a(arc ☃)
  {
    this.Z.b(bv, Optional.fromNullable(☃));
  }
  
  public arc db()
  {
    return (arc)((Optional)this.Z.a(bv)).orNull();
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (b(☃)) {
      return false;
    }
    if ((☃ instanceof re))
    {
      for (int ☃ = 0; ☃ < 64; ☃++) {
        if (da()) {
          return true;
        }
      }
      return false;
    }
    boolean ☃ = super.a(☃, ☃);
    if ((☃.e()) && (this.S.nextInt(10) != 0)) {
      da();
    }
    return ☃;
  }
  
  public boolean dc()
  {
    return ((Boolean)this.Z.a(bw)).booleanValue();
  }
  
  static class b
    extends uy<zj>
  {
    private final yj i;
    private zj j;
    private int k;
    private int l;
    
    public b(yj ☃)
    {
      super(zj.class, false);
      this.i = ☃;
    }
    
    public boolean a()
    {
      double ☃ = f();
      this.j = this.i.l.a(this.i.p, this.i.q, this.i.r, ☃, ☃, null, new Predicate()
      {
        public boolean a(zj ☃)
        {
          return (☃ != null) && (yj.a(yj.this, ☃));
        }
      });
      return this.j != null;
    }
    
    public void c()
    {
      this.k = 5;
      this.l = 0;
    }
    
    public void d()
    {
      this.j = null;
      
      super.d();
    }
    
    public boolean b()
    {
      if (this.j != null)
      {
        if (!yj.a(this.i, this.j)) {
          return false;
        }
        this.i.a(this.j, 10.0F, 10.0F);
        return true;
      }
      if ((this.d != null) && (((zj)this.d).au())) {
        return true;
      }
      return super.b();
    }
    
    public void e()
    {
      if (this.j != null)
      {
        if (--this.k <= 0)
        {
          this.d = this.j;
          this.j = null;
          super.c();
        }
      }
      else
      {
        if (this.d != null) {
          if (yj.a(this.i, (zj)this.d))
          {
            if (((zj)this.d).h(this.i) < 16.0D) {
              this.i.da();
            }
            this.l = 0;
          }
          else if ((((zj)this.d).h(this.i) > 256.0D) && 
            (this.l++ >= 30) && 
            (this.i.a(this.d)))
          {
            this.l = 0;
          }
        }
        super.e();
      }
    }
  }
  
  static class a
    extends tk
  {
    private final yj a;
    
    public a(yj ☃)
    {
      this.a = ☃;
    }
    
    public boolean a()
    {
      if (this.a.db() == null) {
        return false;
      }
      if (!this.a.l.U().b("mobGriefing")) {
        return false;
      }
      if (this.a.bF().nextInt(2000) != 0) {
        return false;
      }
      return true;
    }
    
    public void e()
    {
      Random ☃ = this.a.bF();
      aht ☃ = this.a.l;
      
      int ☃ = on.c(this.a.p - 1.0D + ☃.nextDouble() * 2.0D);
      int ☃ = on.c(this.a.q + ☃.nextDouble() * 2.0D);
      int ☃ = on.c(this.a.r - 1.0D + ☃.nextDouble() * 2.0D);
      cj ☃ = new cj(☃, ☃, ☃);
      arc ☃ = ☃.o(☃);
      arc ☃ = ☃.o(☃.b());
      
      arc ☃ = this.a.db();
      if ((☃ != null) && (a(☃, ☃, ☃.t(), ☃, ☃)))
      {
        ☃.a(☃, ☃, 3);
        this.a.a(null);
      }
    }
    
    private boolean a(aht ☃, cj ☃, ajt ☃, arc ☃, arc ☃)
    {
      if (!☃.a(☃, ☃)) {
        return false;
      }
      if (☃.a() != axe.a) {
        return false;
      }
      if (☃.a() == axe.a) {
        return false;
      }
      if (!☃.h()) {
        return false;
      }
      return true;
    }
  }
  
  static class c
    extends tk
  {
    private final yj a;
    
    public c(yj ☃)
    {
      this.a = ☃;
    }
    
    public boolean a()
    {
      if (this.a.db() != null) {
        return false;
      }
      if (!this.a.l.U().b("mobGriefing")) {
        return false;
      }
      if (this.a.bF().nextInt(20) != 0) {
        return false;
      }
      return true;
    }
    
    public void e()
    {
      Random ☃ = this.a.bF();
      aht ☃ = this.a.l;
      
      int ☃ = on.c(this.a.p - 2.0D + ☃.nextDouble() * 4.0D);
      int ☃ = on.c(this.a.q + ☃.nextDouble() * 3.0D);
      int ☃ = on.c(this.a.r - 2.0D + ☃.nextDouble() * 4.0D);
      cj ☃ = new cj(☃, ☃, ☃);
      arc ☃ = ☃.o(☃);
      ajt ☃ = ☃.t();
      
      bbi ☃ = ☃.a(new bbj(on.c(this.a.p) + 0.5F, ☃ + 0.5F, on.c(this.a.r) + 0.5F), new bbj(☃ + 0.5F, ☃ + 0.5F, ☃ + 0.5F), false, true, false);
      boolean ☃ = (☃ != null) && (☃.a().equals(☃));
      if ((yj.dd().contains(☃)) && (☃))
      {
        this.a.a(☃);
        ☃.g(☃);
      }
    }
  }
}
