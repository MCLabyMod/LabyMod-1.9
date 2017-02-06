import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class wk
  extends vw
  implements qi, sj
{
  private static final Predicate<rr> bB = new Predicate()
  {
    public boolean a(rr ☃)
    {
      return ((☃ instanceof wk)) && (((wk)☃).jdMethod_do());
    }
  };
  private static final sl bC = new ss(null, "horse.jumpStrength", 0.7D, 0.0D, 2.0D).a("Jump Strength").a(true);
  private static final UUID bD = UUID.fromString("556E1665-8B10-40C8-8F9D-CF9B1667F295");
  private static final ke<Byte> bE = kh.a(wk.class, kg.a);
  private static final ke<Integer> bF = kh.a(wk.class, kg.b);
  private static final ke<Integer> bG = kh.a(wk.class, kg.b);
  private static final ke<Optional<UUID>> bH = kh.a(wk.class, kg.m);
  private static final ke<Integer> bI = kh.a(wk.class, kg.b);
  private static final String[] bJ = { "textures/entity/horse/horse_white.png", "textures/entity/horse/horse_creamy.png", "textures/entity/horse/horse_chestnut.png", "textures/entity/horse/horse_brown.png", "textures/entity/horse/horse_black.png", "textures/entity/horse/horse_gray.png", "textures/entity/horse/horse_darkbrown.png" };
  private static final String[] bK = { "hwh", "hcr", "hch", "hbr", "hbl", "hgr", "hdb" };
  private static final String[] bL = { null, "textures/entity/horse/horse_markings_white.png", "textures/entity/horse/horse_markings_whitefield.png", "textures/entity/horse/horse_markings_whitedots.png", "textures/entity/horse/horse_markings_blackdots.png" };
  private static final String[] bM = { "", "wo_", "wmo", "wdo", "bdo" };
  private final wn bN = new wn(this);
  private int bO;
  private int bP;
  private int bQ;
  public int bv;
  public int bw;
  protected boolean bx;
  private aav bR;
  private boolean bS;
  protected int bz;
  protected float bA;
  private boolean bT;
  private boolean bU;
  private int bV = 0;
  private float bW;
  private float bX;
  private float bY;
  private float bZ;
  private float ca;
  private float cb;
  private int cc;
  private String cd;
  
  public wk(aht ☃)
  {
    super(☃);
    
    a(1.3964844F, 1.6F);
    this.Y = false;
    r(false);
    
    dJ();
  }
  
  protected void r()
  {
    this.bp.a(0, new th(this));
    this.bp.a(1, new uc(this, 1.2D));
    this.bp.a(1, new ul(this, 1.2D));
    this.bp.a(2, new td(this, 1.0D));
    this.bp.a(4, new tj(this, 1.0D));
    this.bp.a(6, new ug(this, 0.7D));
    this.bp.a(7, new tp(this, zj.class, 6.0F));
    this.bp.a(8, new uf(this));
  }
  
  protected void i()
  {
    super.i();
    this.Z.a(bE, Byte.valueOf((byte)0));
    this.Z.a(bF, Integer.valueOf(wm.a.k()));
    this.Z.a(bG, Integer.valueOf(0));
    this.Z.a(bH, Optional.absent());
    this.Z.a(bI, Integer.valueOf(wl.a.a()));
  }
  
  public void a(wm ☃)
  {
    this.Z.b(bF, Integer.valueOf(☃.k()));
    dL();
  }
  
  public wm cZ()
  {
    return wm.a(((Integer)this.Z.a(bF)).intValue());
  }
  
  public void l(int ☃)
  {
    this.Z.b(bG, Integer.valueOf(☃));
    dL();
  }
  
  public int da()
  {
    return ((Integer)this.Z.a(bG)).intValue();
  }
  
  public String h_()
  {
    if (o_()) {
      return bf();
    }
    return cZ().d().c();
  }
  
  private boolean o(int ☃)
  {
    return (((Byte)this.Z.a(bE)).byteValue() & ☃) != 0;
  }
  
  private void c(int ☃, boolean ☃)
  {
    byte ☃ = ((Byte)this.Z.a(bE)).byteValue();
    if (☃) {
      this.Z.b(bE, Byte.valueOf((byte)(☃ | ☃)));
    } else {
      this.Z.b(bE, Byte.valueOf((byte)(☃ & (☃ ^ 0xFFFFFFFF))));
    }
  }
  
  public boolean db()
  {
    return !m_();
  }
  
  public boolean dc()
  {
    return o(2);
  }
  
  public boolean dd()
  {
    return db();
  }
  
  public UUID dh()
  {
    return (UUID)((Optional)this.Z.a(bH)).orNull();
  }
  
  public void b(UUID ☃)
  {
    this.Z.b(bH, Optional.fromNullable(☃));
  }
  
  public float di()
  {
    return 0.5F;
  }
  
  public void a(boolean ☃)
  {
    if (☃) {
      a(di());
    } else {
      a(1.0F);
    }
  }
  
  public boolean dj()
  {
    return this.bx;
  }
  
  public void o(boolean ☃)
  {
    c(2, ☃);
  }
  
  public void p(boolean ☃)
  {
    this.bx = ☃;
  }
  
  public boolean a(zj ☃)
  {
    return (!cZ().h()) && (super.a(☃));
  }
  
  protected void q(float ☃)
  {
    if ((☃ > 6.0F) && (dm())) {
      u(false);
    }
  }
  
  public boolean dk()
  {
    return (cZ().f()) && (o(8));
  }
  
  public wl dl()
  {
    return wl.a(((Integer)this.Z.a(bI)).intValue());
  }
  
  public boolean dm()
  {
    return o(32);
  }
  
  public boolean dn()
  {
    return o(64);
  }
  
  public boolean jdMethod_do()
  {
    return o(16);
  }
  
  public boolean dp()
  {
    return this.bS;
  }
  
  public void f(adq ☃)
  {
    wl ☃ = wl.a(☃);
    this.Z.b(bI, Integer.valueOf(☃.a()));
    dL();
    if (!this.l.E)
    {
      a(yt.g).b(bD);
      int ☃ = ☃.c();
      if (☃ != 0) {
        a(yt.g).b(new sn(bD, "Horse armor bonus", ☃, 0).a(false));
      }
    }
  }
  
  public void q(boolean ☃)
  {
    c(16, ☃);
  }
  
  public void r(boolean ☃)
  {
    c(8, ☃);
  }
  
  public void s(boolean ☃)
  {
    this.bS = ☃;
  }
  
  public void t(boolean ☃)
  {
    c(4, ☃);
  }
  
  public int dq()
  {
    return this.bz;
  }
  
  public void m(int ☃)
  {
    this.bz = ☃;
  }
  
  public int n(int ☃)
  {
    int ☃ = on.a(dq() + ☃, 0, dw());
    
    m(☃);
    return ☃;
  }
  
  public boolean a(rc ☃, float ☃)
  {
    rr ☃ = ☃.j();
    if ((aJ()) && (☃ != null) && (y(☃))) {
      return false;
    }
    return super.a(☃, ☃);
  }
  
  public boolean aq()
  {
    return !aJ();
  }
  
  public boolean dr()
  {
    int ☃ = on.c(this.p);
    int ☃ = on.c(this.r);
    
    this.l.b(new cj(☃, 0, ☃));
    return true;
  }
  
  public void ds()
  {
    if ((this.l.E) || (!dk())) {
      return;
    }
    a(ado.a(aju.ae), 1);
    r(false);
  }
  
  private void dH()
  {
    dO();
    if (!ad()) {
      this.l.a(null, this.p, this.q, this.r, ng.cs, bz(), 1.0F, 1.0F + (this.S.nextFloat() - this.S.nextFloat()) * 0.2F);
    }
  }
  
  public void e(float ☃, float ☃)
  {
    if (☃ > 1.0F) {
      a(ng.cw, 0.4F, 1.0F);
    }
    int ☃ = on.f((☃ * 0.5F - 3.0F) * ☃);
    if (☃ <= 0) {
      return;
    }
    a(rc.i, ☃);
    if (aJ()) {
      for (rr ☃ : bv()) {
        ☃.a(rc.i, ☃);
      }
    }
    arc ☃ = this.l.o(new cj(this.p, this.q - 0.2D - this.x, this.r));
    ajt ☃ = ☃.t();
    if ((☃.a() != axe.a) && (!ad()))
    {
      aop ☃ = ☃.w();
      this.l.a(null, this.p, this.q, this.r, ☃.d(), bz(), ☃.a() * 0.5F, ☃.b() * 0.75F);
    }
  }
  
  private int dI()
  {
    wm ☃ = cZ();
    if ((dk()) && (☃.f())) {
      return 17;
    }
    return 2;
  }
  
  private void dJ()
  {
    aav ☃ = this.bR;
    
    this.bR = new aav("HorseChest", dI());
    this.bR.a(h_());
    if (☃ != null)
    {
      ☃.b(this);
      
      int ☃ = Math.min(☃.u_(), this.bR.u_());
      for (int ☃ = 0; ☃ < ☃; ☃++)
      {
        adq ☃ = ☃.a(☃);
        if (☃ != null) {
          this.bR.a(☃, ☃.k());
        }
      }
    }
    this.bR.a(this);
    dK();
  }
  
  private void dK()
  {
    if (!this.l.E)
    {
      t(this.bR.a(0) != null);
      if (cZ().j()) {
        f(this.bR.a(1));
      }
    }
  }
  
  public void a(qv ☃)
  {
    wl ☃ = dl();
    boolean ☃ = du();
    dK();
    if (this.T > 20)
    {
      if ((☃ == wl.a) && (☃ != dl())) {
        a(ng.cp, 0.5F, 1.0F);
      } else if (☃ != dl()) {
        a(ng.cp, 0.5F, 1.0F);
      }
      if ((!☃) && (du())) {
        a(ng.cx, 0.5F, 1.0F);
      }
    }
  }
  
  public boolean cF()
  {
    dr();
    return super.cF();
  }
  
  protected wk a(rr ☃, double ☃)
  {
    double ☃ = Double.MAX_VALUE;
    
    rr ☃ = null;
    List<rr> ☃ = this.l.a(☃, ☃.bl().a(☃, ☃, ☃), bB);
    for (rr ☃ : ☃)
    {
      double ☃ = ☃.e(☃.p, ☃.q, ☃.r);
      if (☃ < ☃)
      {
        ☃ = ☃;
        ☃ = ☃;
      }
    }
    return (wk)☃;
  }
  
  public double dt()
  {
    return a(bC).e();
  }
  
  protected nf bS()
  {
    dO();
    return cZ().c();
  }
  
  protected nf bR()
  {
    dO();
    if (this.S.nextInt(3) == 0) {
      dQ();
    }
    return cZ().b();
  }
  
  public boolean du()
  {
    return o(4);
  }
  
  protected nf G()
  {
    dO();
    if ((this.S.nextInt(10) == 0) && (!cf())) {
      dQ();
    }
    return cZ().a();
  }
  
  protected nf dv()
  {
    dO();
    dQ();
    wm ☃ = cZ();
    if (☃.h()) {
      return null;
    }
    if (☃.g()) {
      return ng.ay;
    }
    return ng.co;
  }
  
  protected void a(cj ☃, ajt ☃)
  {
    aop ☃ = ☃.w();
    if (this.l.o(☃.a()).t() == aju.aH) {
      ☃ = aju.aH.w();
    }
    if (!☃.u().a().d())
    {
      wm ☃ = cZ();
      if ((aJ()) && (!☃.g()))
      {
        this.cc += 1;
        if ((this.cc > 5) && (this.cc % 3 == 0))
        {
          a(ng.ct, ☃.a() * 0.15F, ☃.b());
          if ((☃ == wm.a) && (this.S.nextInt(10) == 0)) {
            a(ng.cq, ☃.a() * 0.6F, ☃.b());
          }
        }
        else if (this.cc <= 5)
        {
          a(ng.cz, ☃.a() * 0.15F, ☃.b());
        }
      }
      else if (☃ == aop.a)
      {
        a(ng.cz, ☃.a() * 0.15F, ☃.b());
      }
      else
      {
        a(ng.cy, ☃.a() * 0.15F, ☃.b());
      }
    }
  }
  
  protected void bA()
  {
    super.bA();
    
    bZ().b(bC);
    
    a(yt.a).a(53.0D);
    a(yt.d).a(0.22499999403953552D);
  }
  
  public int cJ()
  {
    return 6;
  }
  
  public int dw()
  {
    return 100;
  }
  
  protected float cd()
  {
    return 0.8F;
  }
  
  public int C()
  {
    return 400;
  }
  
  public boolean dx()
  {
    return (cZ() == wm.a) || (dl() != wl.a);
  }
  
  private String[] ce = new String[3];
  private boolean cf = false;
  
  private void dL()
  {
    this.cd = null;
  }
  
  public boolean dy()
  {
    return this.cf;
  }
  
  private void dM()
  {
    this.cd = "horse/";
    this.ce[0] = null;
    this.ce[1] = null;
    this.ce[2] = null;
    
    wm ☃ = cZ();
    int ☃ = da();
    if (☃ == wm.a)
    {
      int ☃ = ☃ & 0xFF;
      int ☃ = (☃ & 0xFF00) >> 8;
      if (☃ >= bJ.length)
      {
        this.cf = false;
        return;
      }
      this.ce[0] = bJ[☃];
      this.cd += bK[☃];
      if (☃ >= bL.length)
      {
        this.cf = false;
        return;
      }
      this.ce[1] = bL[☃];
      this.cd += bM[☃];
    }
    else
    {
      this.ce[0] = "";
      this.cd = (this.cd + "_" + ☃ + "_");
    }
    wl ☃ = dl();
    this.ce[2] = ☃.d();
    this.cd += ☃.b();
    this.cf = true;
  }
  
  public String dz()
  {
    if (this.cd == null) {
      dM();
    }
    return this.cd;
  }
  
  public String[] dA()
  {
    if (this.cd == null) {
      dM();
    }
    return this.ce;
  }
  
  public void f(zj ☃)
  {
    if ((!this.l.E) && ((!aJ()) || (w(☃))) && (dc()))
    {
      this.bR.a(h_());
      ☃.a(this, this.bR);
    }
  }
  
  public boolean a(zj ☃, qm ☃, adq ☃)
  {
    if ((☃ != null) && (☃.b() == ads.bT)) {
      return super.a(☃, ☃, ☃);
    }
    if ((!dc()) && 
      (cZ().h())) {
      return false;
    }
    if ((dc()) && (db()) && (☃.aK()))
    {
      f(☃);
      return true;
    }
    if ((dd()) && (aJ())) {
      return super.a(☃, ☃, ☃);
    }
    if (☃ != null)
    {
      if (cZ().j())
      {
        wl ☃ = wl.a(☃);
        if (☃ != wl.a)
        {
          if (!dc())
          {
            dE();
            return true;
          }
          f(☃);
          return true;
        }
      }
      boolean ☃ = false;
      if (!cZ().h())
      {
        float ☃ = 0.0F;
        int ☃ = 0;
        int ☃ = 0;
        if (☃.b() == ads.Q)
        {
          ☃ = 2.0F;
          ☃ = 20;
          ☃ = 3;
        }
        else if (☃.b() == ads.bf)
        {
          ☃ = 1.0F;
          ☃ = 30;
          ☃ = 3;
        }
        else if (ajt.a(☃.b()) == aju.cx)
        {
          ☃ = 20.0F;
          ☃ = 180;
        }
        else if (☃.b() == ads.e)
        {
          ☃ = 3.0F;
          ☃ = 60;
          ☃ = 3;
        }
        else if (☃.b() == ads.cg)
        {
          ☃ = 4.0F;
          ☃ = 60;
          ☃ = 5;
          if ((dc()) && (l() == 0))
          {
            ☃ = true;
            c(☃);
          }
        }
        else if (☃.b() == ads.aq)
        {
          ☃ = 10.0F;
          ☃ = 240;
          ☃ = 10;
          if ((dc()) && (l() == 0) && (!df()))
          {
            ☃ = true;
            c(☃);
          }
        }
        if ((bQ() < bW()) && (☃ > 0.0F))
        {
          b(☃);
          ☃ = true;
        }
        if ((!db()) && (☃ > 0))
        {
          if (!this.l.E) {
            a(☃);
          }
          ☃ = true;
        }
        if ((☃ > 0) && ((☃) || (!dc())) && (dq() < dw()))
        {
          ☃ = true;
          if (!this.l.E) {
            n(☃);
          }
        }
        if (☃) {
          dH();
        }
      }
      if ((!dc()) && (!☃))
      {
        if (☃.a(☃, this, ☃)) {
          return true;
        }
        dE();
        return true;
      }
      if ((!☃) && (cZ().f()) && (!dk()) && 
        (☃.b() == ado.a(aju.ae)))
      {
        r(true);
        a(ng.az, 1.0F, (this.S.nextFloat() - this.S.nextFloat()) * 0.2F + 1.0F);
        ☃ = true;
        dJ();
      }
      if ((!☃) && (dd()) && (!du()) && 
        (☃.b() == ads.aC))
      {
        f(☃);
        return true;
      }
      if (☃)
      {
        if (!☃.bJ.d) {
          ☃.b -= 1;
        }
        return true;
      }
    }
    if ((dd()) && (!aJ()))
    {
      if ((☃ != null) && (☃.a(☃, this, ☃))) {
        return true;
      }
      h(☃);
      return true;
    }
    return super.a(☃, ☃, ☃);
  }
  
  private void h(zj ☃)
  {
    ☃.v = this.v;
    ☃.w = this.w;
    u(false);
    v(false);
    if (!this.l.E) {
      ☃.m(this);
    }
  }
  
  protected boolean cf()
  {
    if ((aJ()) && (du())) {
      return true;
    }
    return (dm()) || (dn());
  }
  
  public boolean e(adq ☃)
  {
    return false;
  }
  
  private void dN()
  {
    this.bv = 1;
  }
  
  public void a(rc ☃)
  {
    super.a(☃);
    if (!this.l.E) {
      dF();
    }
  }
  
  public void n()
  {
    if (this.S.nextInt(200) == 0) {
      dN();
    }
    super.n();
    if (!this.l.E)
    {
      if ((this.S.nextInt(900) == 0) && (this.aA == 0)) {
        b(1.0F);
      }
      if ((!dm()) && (!aJ()) && (this.S.nextInt(300) == 0) && 
        (this.l.o(new cj(on.c(this.p), on.c(this.q) - 1, on.c(this.r))).t() == aju.c)) {
        u(true);
      }
      if ((dm()) && (++this.bO > 50))
      {
        this.bO = 0;
        u(false);
      }
      if ((jdMethod_do()) && (!db()) && (!dm()))
      {
        wk ☃ = a(this, 16.0D);
        if ((☃ != null) && (h(☃) > 4.0D)) {
          this.h.a(☃);
        }
      }
      if ((dG()) && (this.bV++ >= 18000)) {
        T();
      }
    }
  }
  
  public void m()
  {
    super.m();
    if ((this.l.E) && (this.Z.a()))
    {
      this.Z.e();
      dL();
    }
    if ((this.bP > 0) && (++this.bP > 30))
    {
      this.bP = 0;
      c(128, false);
    }
    if ((bx()) && 
      (this.bQ > 0) && (++this.bQ > 20))
    {
      this.bQ = 0;
      v(false);
    }
    if ((this.bv > 0) && (++this.bv > 8)) {
      this.bv = 0;
    }
    if (this.bw > 0)
    {
      this.bw += 1;
      if (this.bw > 300) {
        this.bw = 0;
      }
    }
    this.bX = this.bW;
    if (dm())
    {
      this.bW += (1.0F - this.bW) * 0.4F + 0.05F;
      if (this.bW > 1.0F) {
        this.bW = 1.0F;
      }
    }
    else
    {
      this.bW += (0.0F - this.bW) * 0.4F - 0.05F;
      if (this.bW < 0.0F) {
        this.bW = 0.0F;
      }
    }
    this.bZ = this.bY;
    if (dn())
    {
      this.bX = (this.bW = 0.0F);
      this.bY += (1.0F - this.bY) * 0.4F + 0.05F;
      if (this.bY > 1.0F) {
        this.bY = 1.0F;
      }
    }
    else
    {
      this.bT = false;
      
      this.bY += (0.8F * this.bY * this.bY * this.bY - this.bY) * 0.6F - 0.05F;
      if (this.bY < 0.0F) {
        this.bY = 0.0F;
      }
    }
    this.cb = this.ca;
    if (o(128))
    {
      this.ca += (1.0F - this.ca) * 0.7F + 0.05F;
      if (this.ca > 1.0F) {
        this.ca = 1.0F;
      }
    }
    else
    {
      this.ca += (0.0F - this.ca) * 0.7F - 0.05F;
      if (this.ca < 0.0F) {
        this.ca = 0.0F;
      }
    }
  }
  
  private void dO()
  {
    if (!this.l.E)
    {
      this.bP = 1;
      c(128, true);
    }
  }
  
  private boolean dP()
  {
    return (!aJ()) && (!aI()) && (dc()) && (db()) && (cZ().i()) && (bQ() >= bW()) && (df());
  }
  
  public void u(boolean ☃)
  {
    c(32, ☃);
  }
  
  public void v(boolean ☃)
  {
    if (☃) {
      u(false);
    }
    c(64, ☃);
  }
  
  private void dQ()
  {
    if (bx())
    {
      this.bQ = 1;
      v(true);
    }
  }
  
  public void dE()
  {
    dQ();
    nf ☃ = dv();
    if (☃ != null) {
      a(☃, cd(), ce());
    }
  }
  
  public void dF()
  {
    a(this, this.bR);
    ds();
  }
  
  private void a(rr ☃, aav ☃)
  {
    if ((☃ == null) || (this.l.E)) {
      return;
    }
    for (int ☃ = 0; ☃ < ☃.u_(); ☃++)
    {
      adq ☃ = ☃.a(☃);
      if (☃ != null) {
        a(☃, 0.0F);
      }
    }
  }
  
  public boolean g(zj ☃)
  {
    b(☃.bc());
    o(true);
    return true;
  }
  
  public void g(float ☃, float ☃)
  {
    if ((!aJ()) || (!cK()) || (!du()))
    {
      this.P = 0.5F;
      this.aQ = 0.02F;
      super.g(☃, ☃);
      return;
    }
    sa ☃ = (sa)bt();
    
    this.x = (this.v = ☃.v);
    this.w = (☃.w * 0.5F);
    b(this.v, this.w);
    this.aO = (this.aM = this.v);
    
    ☃ = ☃.bd * 0.5F;
    ☃ = ☃.be;
    if (☃ <= 0.0F)
    {
      ☃ *= 0.25F;
      this.cc = 0;
    }
    if ((this.z) && (this.bA == 0.0F) && (dn()) && (!this.bT))
    {
      ☃ = 0.0F;
      ☃ = 0.0F;
    }
    if ((this.bA > 0.0F) && (!dj()) && (this.z))
    {
      this.t = (dt() * this.bA);
      if (a(rm.h)) {
        this.t += (b(rm.h).c() + 1) * 0.1F;
      }
      p(true);
      this.ai = true;
      if (☃ > 0.0F)
      {
        float ☃ = on.a(this.v * 0.017453292F);
        float ☃ = on.b(this.v * 0.017453292F);
        
        this.s += -0.4F * ☃ * this.bA;
        this.u += 0.4F * ☃ * this.bA;
        
        a(ng.cv, 0.4F, 1.0F);
      }
      this.bA = 0.0F;
    }
    this.P = 1.0F;
    this.aQ = (ck() * 0.1F);
    if (bx())
    {
      l((float)a(yt.d).e());
      super.g(☃, ☃);
    }
    else if ((☃ instanceof zj))
    {
      this.s = 0.0D;
      this.t = 0.0D;
      this.u = 0.0D;
    }
    if (this.z)
    {
      this.bA = 0.0F;
      p(false);
    }
    this.aE = this.aF;
    double ☃ = this.p - this.m;
    double ☃ = this.r - this.o;
    float ☃ = on.a(☃ * ☃ + ☃ * ☃) * 4.0F;
    if (☃ > 1.0F) {
      ☃ = 1.0F;
    }
    this.aF += (☃ - this.aF) * 0.4F;
    this.aG += this.aF;
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    
    ☃.a("EatingHaystack", dm());
    ☃.a("ChestedHorse", dk());
    ☃.a("HasReproduced", dp());
    ☃.a("Bred", jdMethod_do());
    ☃.a("Type", cZ().k());
    ☃.a("Variant", da());
    ☃.a("Temper", dq());
    ☃.a("Tame", dc());
    ☃.a("SkeletonTrap", dG());
    ☃.a("SkeletonTrapTime", this.bV);
    if (dh() != null) {
      ☃.a("OwnerUUID", dh().toString());
    }
    if (dk())
    {
      du ☃ = new du();
      for (int ☃ = 2; ☃ < this.bR.u_(); ☃++)
      {
        adq ☃ = this.bR.a(☃);
        if (☃ != null)
        {
          dn ☃ = new dn();
          
          ☃.a("Slot", (byte)☃);
          
          ☃.b(☃);
          ☃.a(☃);
        }
      }
      ☃.a("Items", ☃);
    }
    if (this.bR.a(1) != null) {
      ☃.a("ArmorItem", this.bR.a(1).b(new dn()));
    }
    if (this.bR.a(0) != null) {
      ☃.a("SaddleItem", this.bR.a(0).b(new dn()));
    }
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    u(☃.p("EatingHaystack"));
    q(☃.p("Bred"));
    r(☃.p("ChestedHorse"));
    s(☃.p("HasReproduced"));
    a(wm.a(☃.h("Type")));
    l(☃.h("Variant"));
    m(☃.h("Temper"));
    o(☃.p("Tame"));
    x(☃.p("SkeletonTrap"));
    this.bV = ☃.h("SkeletonTrapTime");
    String ☃ = "";
    if (☃.b("OwnerUUID", 8))
    {
      ☃ = ☃.l("OwnerUUID");
    }
    else
    {
      String ☃ = ☃.l("Owner");
      ☃ = ml.a(h(), ☃);
    }
    if (!☃.isEmpty()) {
      b(UUID.fromString(☃));
    }
    sm ☃ = bZ().a("Speed");
    if (☃ != null) {
      a(yt.d).a(☃.b() * 0.25D);
    }
    if (dk())
    {
      du ☃ = ☃.c("Items", 10);
      dJ();
      for (int ☃ = 0; ☃ < ☃.c(); ☃++)
      {
        dn ☃ = ☃.b(☃);
        int ☃ = ☃.f("Slot") & 0xFF;
        if ((☃ >= 2) && (☃ < this.bR.u_())) {
          this.bR.a(☃, adq.a(☃));
        }
      }
    }
    if (☃.b("ArmorItem", 10))
    {
      adq ☃ = adq.a(☃.o("ArmorItem"));
      if ((☃ != null) && (wl.b(☃.b()))) {
        this.bR.a(1, ☃);
      }
    }
    if (☃.b("SaddleItem", 10))
    {
      adq ☃ = adq.a(☃.o("SaddleItem"));
      if ((☃ != null) && (☃.b() == ads.aC)) {
        this.bR.a(0, ☃);
      }
    }
    dK();
  }
  
  public boolean a(vw ☃)
  {
    if (☃ == this) {
      return false;
    }
    if (☃.getClass() != getClass()) {
      return false;
    }
    wk ☃ = (wk)☃;
    if ((!dP()) || (!☃.dP())) {
      return false;
    }
    wm ☃ = cZ();
    wm ☃ = ☃.cZ();
    
    return (☃ == ☃) || ((☃ == wm.a) && (☃ == wm.b)) || ((☃ == wm.b) && (☃ == wm.a));
  }
  
  public ro a(ro ☃)
  {
    wk ☃ = (wk)☃;
    wk ☃ = new wk(this.l);
    
    wm ☃ = cZ();
    wm ☃ = ☃.cZ();
    wm ☃ = wm.a;
    if (☃ == ☃) {
      ☃ = ☃;
    } else if (((☃ == wm.a) && (☃ == wm.b)) || ((☃ == wm.b) && (☃ == wm.a))) {
      ☃ = wm.c;
    }
    if (☃ == wm.a)
    {
      int ☃ = this.S.nextInt(9);
      int ☃;
      int ☃;
      if (☃ < 4)
      {
        ☃ = da() & 0xFF;
      }
      else
      {
        int ☃;
        if (☃ < 8) {
          ☃ = ☃.da() & 0xFF;
        } else {
          ☃ = this.S.nextInt(7);
        }
      }
      int ☃ = this.S.nextInt(5);
      if (☃ < 2) {
        ☃ |= da() & 0xFF00;
      } else if (☃ < 4) {
        ☃ |= ☃.da() & 0xFF00;
      } else {
        ☃ |= this.S.nextInt(5) << 8 & 0xFF00;
      }
      ☃.l(☃);
    }
    ☃.a(☃);
    
    double ☃ = a(yt.a).b() + ☃.a(yt.a).b() + dR();
    ☃.a(yt.a).a(☃ / 3.0D);
    
    double ☃ = a(bC).b() + ☃.a(bC).b() + dS();
    ☃.a(bC).a(☃ / 3.0D);
    
    double ☃ = a(yt.d).b() + ☃.a(yt.d).b() + dT();
    ☃.a(yt.d).a(☃ / 3.0D);
    
    return ☃;
  }
  
  public sd a(ql ☃, sd ☃)
  {
    ☃ = super.a(☃, ☃);
    
    wm ☃ = wm.a;
    int ☃ = 0;
    if ((☃ instanceof wk.a))
    {
      ☃ = ((wk.a)☃).a;
      ☃ = ((wk.a)☃).b & 0xFF | this.S.nextInt(5) << 8;
    }
    else
    {
      if (this.S.nextInt(10) == 0)
      {
        ☃ = wm.b;
      }
      else
      {
        int ☃ = this.S.nextInt(7);
        int ☃ = this.S.nextInt(5);
        ☃ = wm.a;
        ☃ = ☃ | ☃ << 8;
      }
      ☃ = new wk.a(☃, ☃);
    }
    a(☃);
    l(☃);
    if (this.S.nextInt(5) == 0) {
      b_(41536);
    }
    if (☃.h())
    {
      a(yt.a).a(15.0D);
      a(yt.d).a(0.20000000298023224D);
    }
    else
    {
      a(yt.a).a(dR());
      if (☃ == wm.a) {
        a(yt.d).a(dT());
      } else {
        a(yt.d).a(0.17499999701976776D);
      }
    }
    if (☃.g()) {
      a(bC).a(0.5D);
    } else {
      a(bC).a(dS());
    }
    c(bW());
    
    return ☃;
  }
  
  public boolean cK()
  {
    rr ☃ = bt();
    if (!(☃ instanceof sa)) {
      return false;
    }
    return true;
  }
  
  public float r(float ☃)
  {
    return this.bX + (this.bW - this.bX) * ☃;
  }
  
  public float s(float ☃)
  {
    return this.bZ + (this.bY - this.bZ) * ☃;
  }
  
  public float t(float ☃)
  {
    return this.cb + (this.ca - this.cb) * ☃;
  }
  
  public void a_(int ☃)
  {
    if (du())
    {
      if (☃ < 0)
      {
        ☃ = 0;
      }
      else
      {
        this.bT = true;
        dQ();
      }
      if (☃ >= 90) {
        this.bA = 1.0F;
      } else {
        this.bA = (0.4F + 0.4F * ☃ / 90.0F);
      }
    }
  }
  
  public boolean b()
  {
    return du();
  }
  
  public void b(int ☃)
  {
    this.bT = true;
    dQ();
  }
  
  public void r_() {}
  
  protected void w(boolean ☃)
  {
    cy ☃ = ☃ ? cy.I : cy.l;
    for (int ☃ = 0; ☃ < 7; ☃++)
    {
      double ☃ = this.S.nextGaussian() * 0.02D;
      double ☃ = this.S.nextGaussian() * 0.02D;
      double ☃ = this.S.nextGaussian() * 0.02D;
      this.l.a(☃, this.p + this.S.nextFloat() * this.G * 2.0F - this.G, this.q + 0.5D + this.S.nextFloat() * this.H, this.r + this.S.nextFloat() * this.G * 2.0F - this.G, ☃, ☃, ☃, new int[0]);
    }
  }
  
  public void a(byte ☃)
  {
    if (☃ == 7) {
      w(true);
    } else if (☃ == 6) {
      w(false);
    } else {
      super.a(☃);
    }
  }
  
  public void k(rr ☃)
  {
    super.k(☃);
    if ((☃ instanceof sb))
    {
      sb ☃ = (sb)☃;
      this.aM = ☃.aM;
    }
    if (this.bZ > 0.0F)
    {
      float ☃ = on.a(this.aM * 0.017453292F);
      float ☃ = on.b(this.aM * 0.017453292F);
      float ☃ = 0.7F * this.bZ;
      float ☃ = 0.15F * this.bZ;
      
      ☃.b(this.p + ☃ * ☃, this.q + ay() + ☃.ax() + ☃, this.r - ☃ * ☃);
      if ((☃ instanceof sa)) {
        ((sa)☃).aM = this.aM;
      }
    }
  }
  
  public double ay()
  {
    double ☃ = super.ay();
    if (cZ() == wm.e) {
      ☃ -= 0.1875D;
    } else if (cZ() == wm.b) {
      ☃ -= 0.25D;
    }
    return ☃;
  }
  
  private float dR()
  {
    return 15.0F + this.S.nextInt(8) + this.S.nextInt(9);
  }
  
  private double dS()
  {
    return 0.4000000059604645D + this.S.nextDouble() * 0.2D + this.S.nextDouble() * 0.2D + this.S.nextDouble() * 0.2D;
  }
  
  private double dT()
  {
    return (0.44999998807907104D + this.S.nextDouble() * 0.3D + this.S.nextDouble() * 0.3D + this.S.nextDouble() * 0.3D) * 0.25D;
  }
  
  public boolean dG()
  {
    return this.bU;
  }
  
  public void x(boolean ☃)
  {
    if (☃ != this.bU)
    {
      this.bU = ☃;
      if (☃) {
        this.bp.a(1, this.bN);
      } else {
        this.bp.a(this.bN);
      }
    }
  }
  
  public static class a
    implements sd
  {
    public wm a;
    public int b;
    
    public a(wm ☃, int ☃)
    {
      this.a = ☃;
      this.b = ☃;
    }
  }
  
  public boolean n_()
  {
    return false;
  }
  
  public float bn()
  {
    return this.H;
  }
  
  public boolean c(int ☃, adq ☃)
  {
    if ((☃ == 499) && (cZ().f()))
    {
      if ((☃ == null) && (dk()))
      {
        r(false);
        dJ();
        return true;
      }
      if ((☃ != null) && (☃.b() == ado.a(aju.ae)) && (!dk()))
      {
        r(true);
        dJ();
        return true;
      }
    }
    int ☃ = ☃ - 400;
    if ((☃ >= 0) && (☃ < 2) && (☃ < this.bR.u_()))
    {
      if ((☃ == 0) && (☃ != null) && (☃.b() != ads.aC)) {
        return false;
      }
      if ((☃ == 1) && (((☃ != null) && (!wl.b(☃.b()))) || (!cZ().j()))) {
        return false;
      }
      this.bR.a(☃, ☃);
      dK();
      return true;
    }
    int ☃ = ☃ - 500 + 2;
    if ((☃ >= 2) && (☃ < this.bR.u_()))
    {
      this.bR.a(☃, ☃);
      return true;
    }
    return false;
  }
  
  public rr bt()
  {
    if (bu().isEmpty()) {
      return null;
    }
    return (rr)bu().get(0);
  }
  
  public sf ca()
  {
    if (cZ().h()) {
      return sf.b;
    }
    return sf.a;
  }
  
  protected kk J()
  {
    return cZ().l();
  }
}
