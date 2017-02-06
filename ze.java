import java.util.Random;
import java.util.UUID;

public class ze
  extends ro
  implements ahf, zd
{
  private static final ke<Integer> bw = kh.a(ze.class, kg.b);
  private int bx;
  private boolean by;
  private boolean bz;
  vp bv;
  private zj bA;
  private ahh bB;
  private int bC;
  private boolean bD;
  private boolean bE;
  private int bF;
  private String bG;
  private int bH;
  private int bI;
  private boolean bJ;
  private boolean bK;
  private qv bL = new qv("Items", false, 8);
  
  public ze(aht ☃)
  {
    this(☃, 0);
  }
  
  public ze(aht ☃, int ☃)
  {
    super(☃);
    l(☃);
    a(0.6F, 1.95F);
    
    ((ve)x()).a(true);
    
    l(true);
  }
  
  protected void r()
  {
    this.bp.a(0, new th(this));
    this.bp.a(1, new ta(this, za.class, 8.0F, 0.6D, 0.6D));
    this.bp.a(1, new uq(this));
    this.bp.a(1, new tq(this));
    this.bp.a(2, new tt(this));
    this.bp.a(3, new uj(this));
    this.bp.a(4, new ub(this, true));
    this.bp.a(5, new tw(this, 0.6D));
    this.bp.a(6, new tr(this));
    this.bp.a(7, new uo(this));
    this.bp.a(9, new tn(this, zj.class, 3.0F, 1.0F));
    this.bp.a(9, new ur(this));
    this.bp.a(9, new ug(this, 0.6D));
    this.bp.a(10, new tp(this, sb.class, 8.0F));
  }
  
  private void dj()
  {
    if (this.bK) {
      return;
    }
    this.bK = true;
    if (m_()) {
      this.bp.a(8, new ud(this, 0.32D));
    } else if (cZ() == 0) {
      this.bp.a(6, new tm(this, 0.6D));
    }
  }
  
  protected void o()
  {
    if (cZ() == 0) {
      this.bp.a(8, new tm(this, 0.6D));
    }
    super.o();
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.d).a(0.5D);
  }
  
  protected void M()
  {
    if (--this.bx <= 0)
    {
      cj ☃ = new cj(this);
      this.l.ai().a(☃);
      this.bx = (70 + this.S.nextInt(50));
      
      this.bv = this.l.ai().a(☃, 32);
      if (this.bv == null)
      {
        cX();
      }
      else
      {
        cj ☃ = this.bv.a();
        a(☃, this.bv.b());
        if (this.bJ)
        {
          this.bJ = false;
          this.bv.b(5);
        }
      }
    }
    if ((!dc()) && (this.bC > 0))
    {
      this.bC -= 1;
      if (this.bC <= 0)
      {
        if (this.bD)
        {
          for (ahg ☃ : this.bB) {
            if (☃.h()) {
              ☃.a(this.S.nextInt(6) + this.S.nextInt(6) + 2);
            }
          }
          dk();
          this.bD = false;
          if ((this.bv != null) && (this.bG != null))
          {
            this.l.a(this, (byte)14);
            this.bv.a(this.bG, 1);
          }
        }
        c(new rl(rm.j, 200, 0));
      }
    }
    super.M();
  }
  
  public boolean a(zj ☃, qm ☃, adq ☃)
  {
    boolean ☃ = (☃ != null) && (☃.b() == ads.bT);
    if ((!☃) && (au()) && (!dc()) && (!m_()))
    {
      if ((!this.l.E) && ((this.bB == null) || (!this.bB.isEmpty())))
      {
        a_(☃);
        ☃.a(this);
      }
      ☃.b(nt.H);
      return true;
    }
    return super.a(☃, ☃, ☃);
  }
  
  protected void i()
  {
    super.i();
    this.Z.a(bw, Integer.valueOf(0));
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    ☃.a("Profession", cZ());
    ☃.a("Riches", this.bF);
    ☃.a("Career", this.bH);
    ☃.a("CareerLevel", this.bI);
    ☃.a("Willing", this.bE);
    if (this.bB != null) {
      ☃.a("Offers", this.bB.a());
    }
    du ☃ = new du();
    for (int ☃ = 0; ☃ < this.bL.u_(); ☃++)
    {
      adq ☃ = this.bL.a(☃);
      if (☃ != null) {
        ☃.a(☃.b(new dn()));
      }
    }
    ☃.a("Inventory", ☃);
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    l(☃.h("Profession"));
    this.bF = ☃.h("Riches");
    this.bH = ☃.h("Career");
    this.bI = ☃.h("CareerLevel");
    this.bE = ☃.p("Willing");
    if (☃.b("Offers", 10))
    {
      dn ☃ = ☃.o("Offers");
      this.bB = new ahh(☃);
    }
    du ☃ = ☃.c("Inventory", 10);
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      adq ☃ = adq.a(☃.b(☃));
      if (☃ != null) {
        this.bL.a(☃);
      }
    }
    l(true);
    dj();
  }
  
  protected boolean K()
  {
    return false;
  }
  
  protected nf G()
  {
    if (dc()) {
      return ng.gt;
    }
    return ng.gp;
  }
  
  protected nf bR()
  {
    return ng.gr;
  }
  
  protected nf bS()
  {
    return ng.gq;
  }
  
  public void l(int ☃)
  {
    this.Z.b(bw, Integer.valueOf(☃));
  }
  
  public int cZ()
  {
    return Math.max(((Integer)this.Z.a(bw)).intValue() % 5, 0);
  }
  
  public boolean da()
  {
    return this.by;
  }
  
  public void o(boolean ☃)
  {
    this.by = ☃;
  }
  
  public void p(boolean ☃)
  {
    this.bz = ☃;
  }
  
  public boolean db()
  {
    return this.bz;
  }
  
  public void a(sa ☃)
  {
    super.a(☃);
    if ((this.bv != null) && (☃ != null))
    {
      this.bv.a(☃);
      if ((☃ instanceof zj))
      {
        int ☃ = -1;
        if (m_()) {
          ☃ = -3;
        }
        this.bv.a(☃.h_(), ☃);
        if (au()) {
          this.l.a(this, (byte)13);
        }
      }
    }
  }
  
  public void a(rc ☃)
  {
    if (this.bv != null)
    {
      rr ☃ = ☃.j();
      if (☃ != null)
      {
        if ((☃ instanceof zj)) {
          this.bv.a(☃.h_(), -2);
        } else if ((☃ instanceof yl)) {
          this.bv.h();
        }
      }
      else
      {
        zj ☃ = this.l.a(this, 16.0D);
        if (☃ != null) {
          this.bv.h();
        }
      }
    }
    super.a(☃);
  }
  
  public void a_(zj ☃)
  {
    this.bA = ☃;
  }
  
  public zj t_()
  {
    return this.bA;
  }
  
  public boolean dc()
  {
    return this.bA != null;
  }
  
  public boolean q(boolean ☃)
  {
    if ((!this.bE) && (☃) && (df()))
    {
      boolean ☃ = false;
      for (int ☃ = 0; ☃ < this.bL.u_(); ☃++)
      {
        adq ☃ = this.bL.a(☃);
        if (☃ != null) {
          if ((☃.b() == ads.R) && (☃.b >= 3))
          {
            ☃ = true;
            this.bL.a(☃, 3);
          }
          else if (((☃.b() == ads.cc) || (☃.b() == ads.cb)) && (☃.b >= 12))
          {
            ☃ = true;
            this.bL.a(☃, 12);
          }
        }
        if (☃)
        {
          this.l.a(this, (byte)18);
          this.bE = true;
          break;
        }
      }
    }
    return this.bE;
  }
  
  public void r(boolean ☃)
  {
    this.bE = ☃;
  }
  
  public void a(ahg ☃)
  {
    ☃.g();
    this.a_ = (-C());
    a(ng.gu, cd(), ce());
    
    int ☃ = 3 + this.S.nextInt(4);
    if ((☃.e() == 1) || (this.S.nextInt(5) == 0))
    {
      this.bC = 40;
      this.bD = true;
      this.bE = true;
      if (this.bA != null) {
        this.bG = this.bA.h_();
      } else {
        this.bG = null;
      }
      ☃ += 5;
    }
    if (☃.a().b() == ads.bY) {
      this.bF += ☃.a().b;
    }
    if (☃.j()) {
      this.l.a(new rx(this.l, this.p, this.q + 0.5D, this.r, ☃));
    }
  }
  
  public void a(adq ☃)
  {
    if ((!this.l.E) && (this.a_ > -C() + 20))
    {
      this.a_ = (-C());
      if (☃ != null) {
        a(ng.gu, cd(), ce());
      } else {
        a(ng.gs, cd(), ce());
      }
    }
  }
  
  public ahh b_(zj ☃)
  {
    if (this.bB == null) {
      dk();
    }
    return this.bB;
  }
  
  private void dk()
  {
    ze.f[][][] ☃ = bM[cZ()];
    if ((this.bH == 0) || (this.bI == 0))
    {
      this.bH = (this.S.nextInt(☃.length) + 1);
      this.bI = 1;
    }
    else
    {
      this.bI += 1;
    }
    if (this.bB == null) {
      this.bB = new ahh();
    }
    int ☃ = this.bH - 1;
    int ☃ = this.bI - 1;
    
    ze.f[][] ☃ = ☃[☃];
    if ((☃ >= 0) && (☃ < ☃.length))
    {
      ze.f[] ☃ = ☃[☃];
      for (ze.f ☃ : ☃) {
        ☃.a(this.bB, this.S);
      }
    }
  }
  
  public void a(ahh ☃) {}
  
  public eu i_()
  {
    bbr ☃ = aO();
    String ☃ = bf();
    if ((☃ != null) && (!☃.isEmpty()))
    {
      fa ☃ = new fa(bbm.a(☃, ☃));
      ☃.b().a(bk());
      ☃.b().a(bc().toString());
      return ☃;
    }
    if (this.bB == null) {
      dk();
    }
    String ☃ = null;
    switch (cZ())
    {
    case 0: 
      if (this.bH == 1) {
        ☃ = "farmer";
      } else if (this.bH == 2) {
        ☃ = "fisherman";
      } else if (this.bH == 3) {
        ☃ = "shepherd";
      } else if (this.bH == 4) {
        ☃ = "fletcher";
      }
      break;
    case 1: 
      ☃ = "librarian";
      
      break;
    case 2: 
      ☃ = "cleric";
      
      break;
    case 3: 
      if (this.bH == 1) {
        ☃ = "armor";
      } else if (this.bH == 2) {
        ☃ = "weapon";
      } else if (this.bH == 3) {
        ☃ = "tool";
      }
      break;
    case 4: 
      if (this.bH == 1) {
        ☃ = "butcher";
      } else if (this.bH == 2) {
        ☃ = "leather";
      }
      break;
    }
    if (☃ != null)
    {
      fb ☃ = new fb("entity.Villager." + ☃, new Object[0]);
      ☃.b().a(bk());
      ☃.b().a(bc().toString());
      if (☃ != null) {
        ☃.b().a(☃.m());
      }
      return ☃;
    }
    return super.i_();
  }
  
  public float bn()
  {
    if (m_()) {
      return 0.81F;
    }
    return 1.62F;
  }
  
  private static final ze.f[][][][] bM = { { { { new ze.a(ads.Q, new ze.g(18, 22)), new ze.a(ads.cc, new ze.g(15, 19)), new ze.a(ads.cb, new ze.g(15, 19)), new ze.e(ads.R, new ze.g(-4, -2)) }, { new ze.a(ado.a(aju.aU), new ze.g(8, 13)), new ze.e(ads.ck, new ze.g(-3, -2)) }, { new ze.a(ado.a(aju.bk), new ze.g(7, 12)), new ze.e(ads.e, new ze.g(-5, -7)) }, { new ze.e(ads.bj, new ze.g(-6, -10)), new ze.e(ads.bg, new ze.g(1, 1)) } }, { { new ze.a(ads.H, new ze.g(15, 20)), new ze.a(ads.j, new ze.g(16, 24)), new ze.d(ads.bb, new ze.g(6, 6), ads.bc, new ze.g(6, 6)) }, { new ze.c(ads.aY, new ze.g(7, 8)) } }, { { new ze.a(ado.a(aju.L), new ze.g(16, 22)), new ze.e(ads.bl, new ze.g(3, 4)) }, { new ze.e(new adq(ado.a(aju.L)), new ze.g(1, 2)), new ze.e(new adq(ado.a(aju.L), 1, 1), new ze.g(1, 2)), new ze.e(new adq(ado.a(aju.L), 1, 2), new ze.g(1, 2)), new ze.e(new adq(ado.a(aju.L), 1, 3), new ze.g(1, 2)), new ze.e(new adq(ado.a(aju.L), 1, 4), new ze.g(1, 2)), new ze.e(new adq(ado.a(aju.L), 1, 5), new ze.g(1, 2)), new ze.e(new adq(ado.a(aju.L), 1, 6), new ze.g(1, 2)), new ze.e(new adq(ado.a(aju.L), 1, 7), new ze.g(1, 2)), new ze.e(new adq(ado.a(aju.L), 1, 8), new ze.g(1, 2)), new ze.e(new adq(ado.a(aju.L), 1, 9), new ze.g(1, 2)), new ze.e(new adq(ado.a(aju.L), 1, 10), new ze.g(1, 2)), new ze.e(new adq(ado.a(aju.L), 1, 11), new ze.g(1, 2)), new ze.e(new adq(ado.a(aju.L), 1, 12), new ze.g(1, 2)), new ze.e(new adq(ado.a(aju.L), 1, 13), new ze.g(1, 2)), new ze.e(new adq(ado.a(aju.L), 1, 14), new ze.g(1, 2)), new ze.e(new adq(ado.a(aju.L), 1, 15), new ze.g(1, 2)) } }, { { new ze.a(ads.H, new ze.g(15, 20)), new ze.e(ads.g, new ze.g(-12, -8)) }, { new ze.e(ads.f, new ze.g(2, 3)), new ze.d(ado.a(aju.n), new ze.g(10, 10), ads.am, new ze.g(6, 10)) } } }, { { { new ze.a(ads.aR, new ze.g(24, 36)), new ze.b() }, { new ze.a(ads.aS, new ze.g(8, 10)), new ze.e(ads.aX, new ze.g(10, 12)), new ze.e(ado.a(aju.X), new ze.g(3, 4)) }, { new ze.a(ads.bX, new ze.g(2, 2)), new ze.e(ads.aZ, new ze.g(10, 12)), new ze.e(ado.a(aju.w), new ze.g(-5, -3)) }, { new ze.b() }, { new ze.b() }, { new ze.e(ads.cy, new ze.g(20, 22)) } } }, { { { new ze.a(ads.bA, new ze.g(36, 40)), new ze.a(ads.m, new ze.g(8, 10)) }, { new ze.e(ads.aE, new ze.g(-4, -1)), new ze.e(new adq(ads.bd, 1, act.l.b()), new ze.g(-2, -1)) }, { new ze.e(ads.bB, new ze.g(4, 7)), new ze.e(ado.a(aju.aX), new ze.g(-3, -1)) }, { new ze.e(ads.bU, new ze.g(3, 11)) } } }, { { { new ze.a(ads.j, new ze.g(16, 24)), new ze.e(ads.aa, new ze.g(4, 6)) }, { new ze.a(ads.l, new ze.g(7, 9)), new ze.e(ads.ab, new ze.g(10, 14)) }, { new ze.a(ads.k, new ze.g(3, 4)), new ze.c(ads.af, new ze.g(16, 19)) }, { new ze.e(ads.Z, new ze.g(5, 7)), new ze.e(ads.Y, new ze.g(9, 11)), new ze.e(ads.W, new ze.g(5, 7)), new ze.e(ads.X, new ze.g(11, 15)) } }, { { new ze.a(ads.j, new ze.g(16, 24)), new ze.e(ads.c, new ze.g(6, 8)) }, { new ze.a(ads.l, new ze.g(7, 9)), new ze.c(ads.n, new ze.g(9, 10)) }, { new ze.a(ads.k, new ze.g(3, 4)), new ze.c(ads.w, new ze.g(12, 15)), new ze.c(ads.z, new ze.g(9, 12)) } }, { { new ze.a(ads.j, new ze.g(16, 24)), new ze.c(ads.a, new ze.g(5, 7)) }, { new ze.a(ads.l, new ze.g(7, 9)), new ze.c(ads.b, new ze.g(9, 11)) }, { new ze.a(ads.k, new ze.g(3, 4)), new ze.c(ads.y, new ze.g(12, 15)) } } }, { { { new ze.a(ads.an, new ze.g(14, 18)), new ze.a(ads.br, new ze.g(14, 18)) }, { new ze.a(ads.j, new ze.g(16, 24)), new ze.e(ads.ao, new ze.g(-7, -5)), new ze.e(ads.bs, new ze.g(-8, -6)) } }, { { new ze.a(ads.aM, new ze.g(9, 12)), new ze.e(ads.U, new ze.g(2, 4)) }, { new ze.c(ads.T, new ze.g(7, 12)) }, { new ze.e(ads.aC, new ze.g(8, 10)) } } } };
  
  static class g
    extends ou<Integer, Integer>
  {
    public g(int ☃, int ☃)
    {
      super(Integer.valueOf(☃));
    }
    
    public int a(Random ☃)
    {
      if (((Integer)a()).intValue() >= ((Integer)b()).intValue()) {
        return ((Integer)a()).intValue();
      }
      return ((Integer)a()).intValue() + ☃.nextInt(((Integer)b()).intValue() - ((Integer)a()).intValue() + 1);
    }
  }
  
  static abstract interface f
  {
    public abstract void a(ahh paramahh, Random paramRandom);
  }
  
  static class a
    implements ze.f
  {
    public ado a;
    public ze.g b;
    
    public a(ado ☃, ze.g ☃)
    {
      this.a = ☃;
      this.b = ☃;
    }
    
    public void a(ahh ☃, Random ☃)
    {
      int ☃ = 1;
      if (this.b != null) {
        ☃ = this.b.a(☃);
      }
      ☃.add(new ahg(new adq(this.a, ☃, 0), ads.bY));
    }
  }
  
  static class e
    implements ze.f
  {
    public adq a;
    public ze.g b;
    
    public e(ado ☃, ze.g ☃)
    {
      this.a = new adq(☃);
      this.b = ☃;
    }
    
    public e(adq ☃, ze.g ☃)
    {
      this.a = ☃;
      this.b = ☃;
    }
    
    public void a(ahh ☃, Random ☃)
    {
      int ☃ = 1;
      if (this.b != null) {
        ☃ = this.b.a(☃);
      }
      adq ☃;
      adq ☃;
      adq ☃;
      if (☃ < 0)
      {
        adq ☃ = new adq(ads.bY);
        ☃ = new adq(this.a.b(), -☃, this.a.i());
      }
      else
      {
        ☃ = new adq(ads.bY, ☃, 0);
        ☃ = new adq(this.a.b(), 1, this.a.i());
      }
      ☃.add(new ahg(☃, ☃));
    }
  }
  
  static class c
    implements ze.f
  {
    public adq a;
    public ze.g b;
    
    public c(ado ☃, ze.g ☃)
    {
      this.a = new adq(☃);
      this.b = ☃;
    }
    
    public void a(ahh ☃, Random ☃)
    {
      int ☃ = 1;
      if (this.b != null) {
        ☃ = this.b.a(☃);
      }
      adq ☃ = new adq(ads.bY, ☃, 0);
      adq ☃ = new adq(this.a.b(), 1, this.a.i());
      ☃ = ago.a(☃, ☃, 5 + ☃.nextInt(15), false);
      
      ☃.add(new ahg(☃, ☃));
    }
  }
  
  static class b
    implements ze.f
  {
    public void a(ahh ☃, Random ☃)
    {
      agm ☃ = (agm)agm.b.a(☃);
      int ☃ = on.a(☃, ☃.d(), ☃.b());
      adq ☃ = ads.cn.a(new agp(☃, ☃));
      int ☃ = 2 + ☃.nextInt(5 + ☃ * 10) + 3 * ☃;
      if (☃.e()) {
        ☃ *= 2;
      }
      if (☃ > 64) {
        ☃ = 64;
      }
      ☃.add(new ahg(new adq(ads.aS), new adq(ads.bY, ☃), ☃));
    }
  }
  
  static class d
    implements ze.f
  {
    public adq a;
    public ze.g b;
    public adq c;
    public ze.g d;
    
    public d(ado ☃, ze.g ☃, ado ☃, ze.g ☃)
    {
      this.a = new adq(☃);
      this.b = ☃;
      this.c = new adq(☃);
      this.d = ☃;
    }
    
    public void a(ahh ☃, Random ☃)
    {
      int ☃ = 1;
      if (this.b != null) {
        ☃ = this.b.a(☃);
      }
      int ☃ = 1;
      if (this.d != null) {
        ☃ = this.d.a(☃);
      }
      ☃.add(new ahg(new adq(this.a.b(), ☃, this.a.i()), new adq(ads.bY), new adq(this.c.b(), ☃, this.c.i())));
    }
  }
  
  public void a(byte ☃)
  {
    if (☃ == 12) {
      a(cy.I);
    } else if (☃ == 13) {
      a(cy.u);
    } else if (☃ == 14) {
      a(cy.v);
    } else {
      super.a(☃);
    }
  }
  
  private void a(cy ☃)
  {
    for (int ☃ = 0; ☃ < 5; ☃++)
    {
      double ☃ = this.S.nextGaussian() * 0.02D;
      double ☃ = this.S.nextGaussian() * 0.02D;
      double ☃ = this.S.nextGaussian() * 0.02D;
      this.l.a(☃, this.p + this.S.nextFloat() * this.G * 2.0F - this.G, this.q + 1.0D + this.S.nextFloat() * this.H, this.r + this.S.nextFloat() * this.G * 2.0F - this.G, ☃, ☃, ☃, new int[0]);
    }
  }
  
  public sd a(ql ☃, sd ☃)
  {
    ☃ = super.a(☃, ☃);
    
    l(this.l.r.nextInt(5));
    
    dj();
    
    return ☃;
  }
  
  public void dd()
  {
    this.bJ = true;
  }
  
  public ze b(ro ☃)
  {
    ze ☃ = new ze(this.l);
    ☃.a(this.l.D(new cj(☃)), null);
    return ☃;
  }
  
  public boolean a(zj ☃)
  {
    return false;
  }
  
  public void a(ya ☃)
  {
    if ((this.l.E) || (this.F)) {
      return;
    }
    yz ☃ = new yz(this.l);
    ☃.b(this.p, this.q, this.r, this.v, this.w);
    ☃.a(this.l.D(new cj(☃)), null);
    ☃.m(cR());
    if (o_())
    {
      ☃.c(bf());
      ☃.i(bg());
    }
    this.l.a(☃);
    T();
  }
  
  public qv de()
  {
    return this.bL;
  }
  
  protected void a(yd ☃)
  {
    adq ☃ = ☃.k();
    ado ☃ = ☃.b();
    if (a(☃))
    {
      adq ☃ = this.bL.a(☃);
      if (☃ == null) {
        ☃.T();
      } else {
        ☃.b = ☃.b;
      }
    }
  }
  
  private boolean a(ado ☃)
  {
    return (☃ == ads.R) || (☃ == ads.cc) || (☃ == ads.cb) || (☃ == ads.Q) || (☃ == ads.P) || (☃ == ads.cV) || (☃ == ads.cU);
  }
  
  public boolean df()
  {
    return m(1);
  }
  
  public boolean dg()
  {
    return m(2);
  }
  
  public boolean dh()
  {
    boolean ☃ = cZ() == 0;
    if (☃) {
      return !m(5);
    }
    return !m(1);
  }
  
  private boolean m(int ☃)
  {
    boolean ☃ = cZ() == 0;
    for (int ☃ = 0; ☃ < this.bL.u_(); ☃++)
    {
      adq ☃ = this.bL.a(☃);
      if (☃ != null)
      {
        if (((☃.b() == ads.R) && (☃.b >= 3 * ☃)) || ((☃.b() == ads.cc) && (☃.b >= 12 * ☃)) || ((☃.b() == ads.cb) && (☃.b >= 12 * ☃)) || ((☃.b() == ads.cV) && (☃.b >= 12 * ☃))) {
          return true;
        }
        if ((☃) && 
          (☃.b() == ads.Q) && (☃.b >= 9 * ☃)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean di()
  {
    for (int ☃ = 0; ☃ < this.bL.u_(); ☃++)
    {
      adq ☃ = this.bL.a(☃);
      if ((☃ != null) && (
        (☃.b() == ads.P) || (☃.b() == ads.cc) || (☃.b() == ads.cb) || (☃.b() == ads.cU))) {
        return true;
      }
    }
    return false;
  }
  
  public boolean c(int ☃, adq ☃)
  {
    if (super.c(☃, ☃)) {
      return true;
    }
    int ☃ = ☃ - 300;
    if ((☃ >= 0) && (☃ < this.bL.u_()))
    {
      this.bL.a(☃, ☃);
      return true;
    }
    return false;
  }
}
