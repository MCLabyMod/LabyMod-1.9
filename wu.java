import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class wu
  extends sb
  implements wr, yl
{
  private static final Logger bH = ;
  public static final ke<Integer> a = kh.a(wu.class, kg.b);
  public double[][] b = new double[64][3];
  public int c = -1;
  public ws[] bt;
  public ws bu;
  public ws bv;
  public ws bw;
  public ws bx;
  public ws by;
  public ws bz;
  public ws bA;
  public ws bB;
  public float bC;
  public float bD;
  public boolean bE;
  public int bF;
  public wt bG;
  private final ata bI;
  private final xl bJ;
  private int bK = 200;
  private int bL;
  private final ayn[] bM = new ayn[24];
  private final int[] bN = new int[24];
  private final ayl bO = new ayl();
  
  public wu(aht ☃)
  {
    super(☃);
    
    this.bt = new ws[] { this.bu = new ws(this, "head", 6.0F, 6.0F), this.bv = new ws(this, "neck", 6.0F, 6.0F), this.bw = new ws(this, "body", 8.0F, 8.0F), this.bx = new ws(this, "tail", 4.0F, 4.0F), this.by = new ws(this, "tail", 4.0F, 4.0F), this.bz = new ws(this, "tail", 4.0F, 4.0F), this.bA = new ws(this, "wing", 4.0F, 4.0F), this.bB = new ws(this, "wing", 4.0F, 4.0F) };
    
    c(bW());
    
    a(16.0F, 8.0F);
    
    this.Q = true;
    this.Y = true;
    
    this.bK = 100;
    
    this.ah = true;
    if ((!☃.E) && ((☃.s instanceof atb))) {
      this.bI = ((atb)☃.s).s();
    } else {
      this.bI = null;
    }
    this.bJ = new xl(this);
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.a).a(200.0D);
  }
  
  protected void i()
  {
    super.i();
    R().a(a, Integer.valueOf(xk.k.b()));
  }
  
  public double[] a(int ☃, float ☃)
  {
    if (bQ() <= 0.0F) {
      ☃ = 0.0F;
    }
    ☃ = 1.0F - ☃;
    
    int ☃ = this.c - ☃ & 0x3F;
    int ☃ = this.c - ☃ - 1 & 0x3F;
    double[] ☃ = new double[3];
    double ☃ = this.b[☃][0];
    double ☃ = on.g(this.b[☃][0] - ☃);
    ☃[0] = (☃ + ☃ * ☃);
    
    ☃ = this.b[☃][1];
    ☃ = this.b[☃][1] - ☃;
    
    ☃[1] = (☃ + ☃ * ☃);
    ☃[2] = (this.b[☃][2] + (this.b[☃][2] - this.b[☃][2]) * ☃);
    return ☃;
  }
  
  public void n()
  {
    if (this.l.E)
    {
      c(bQ());
      if (!ad())
      {
        float ☃ = on.b(this.bD * 6.2831855F);
        float ☃ = on.b(this.bC * 6.2831855F);
        if ((☃ <= -0.3F) && (☃ >= -0.3F)) {
          this.l.a(this.p, this.q, this.r, ng.aP, bz(), 5.0F, 0.8F + this.S.nextFloat() * 0.3F, false);
        }
        if ((!this.bJ.a().a()) && (--this.bK < 0))
        {
          this.l.a(this.p, this.q, this.r, ng.aQ, bz(), 2.5F, 0.8F + this.S.nextFloat() * 0.3F, false);
          this.bK = (200 + this.S.nextInt(200));
        }
      }
    }
    this.bC = this.bD;
    if (bQ() <= 0.0F)
    {
      float ☃ = (this.S.nextFloat() - 0.5F) * 8.0F;
      float ☃ = (this.S.nextFloat() - 0.5F) * 4.0F;
      float ☃ = (this.S.nextFloat() - 0.5F) * 8.0F;
      this.l.a(cy.b, this.p + ☃, this.q + 2.0D + ☃, this.r + ☃, 0.0D, 0.0D, 0.0D, new int[0]);
      return;
    }
    cV();
    
    float ☃ = 0.2F / (on.a(this.s * this.s + this.u * this.u) * 10.0F + 1.0F);
    ☃ *= (float)Math.pow(2.0D, this.t);
    if (this.bJ.a().a()) {
      this.bD += 0.1F;
    } else if (this.bE) {
      this.bD += ☃ * 0.5F;
    } else {
      this.bD += ☃;
    }
    this.v = on.g(this.v);
    if (cR())
    {
      this.bD = 0.5F;
      return;
    }
    if (this.c < 0) {
      for (int ☃ = 0; ☃ < this.b.length; ☃++)
      {
        this.b[☃][0] = this.v;
        this.b[☃][1] = this.q;
      }
    }
    if (++this.c == this.b.length) {
      this.c = 0;
    }
    this.b[this.c][0] = this.v;
    this.b[this.c][1] = this.q;
    if (this.l.E)
    {
      if (this.bg > 0)
      {
        double ☃ = this.p + (this.bh - this.p) / this.bg;
        double ☃ = this.q + (this.bi - this.q) / this.bg;
        double ☃ = this.r + (this.bj - this.r) / this.bg;
        
        double ☃ = on.g(this.bk - this.v);
        
        this.v = ((float)(this.v + ☃ / this.bg));
        this.w = ((float)(this.w + (this.bl - this.w) / this.bg));
        
        this.bg -= 1;
        b(☃, ☃, ☃);
        b(this.v, this.w);
      }
      this.bJ.a().b();
    }
    else
    {
      xe ☃ = this.bJ.a();
      ☃.c();
      if (this.bJ.a() != ☃)
      {
        ☃ = this.bJ.a();
        ☃.c();
      }
      bbj ☃ = ☃.g();
      if (☃ != null)
      {
        double ☃ = ☃.b - this.p;
        double ☃ = ☃.c - this.q;
        double ☃ = ☃.d - this.r;
        
        double ☃ = ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
        float ☃ = ☃.f();
        ☃ = on.a(☃ / on.a(☃ * ☃ + ☃ * ☃), -☃, ☃);
        this.t += ☃ * 0.10000000149011612D;
        this.v = on.g(this.v);
        
        double ☃ = on.a(on.g(180.0D - on.b(☃, ☃) * 57.2957763671875D - this.v), -50.0D, 50.0D);
        bbj ☃ = new bbj(☃.b - this.p, ☃.c - this.q, ☃.d - this.r).a();
        bbj ☃ = new bbj(on.a(this.v * 0.017453292F), this.t, -on.b(this.v * 0.017453292F)).a();
        float ☃ = Math.max(((float)☃.b(☃) + 0.5F) / 1.5F, 0.0F);
        
        this.bf *= 0.8F;
        this.bf = ((float)(this.bf + ☃ * ☃.h()));
        this.v += this.bf * 0.1F;
        
        float ☃ = (float)(2.0D / (☃ + 1.0D));
        float ☃ = 0.06F;
        a(0.0F, -1.0F, ☃ * (☃ * ☃ + (1.0F - ☃)));
        if (this.bE) {
          d(this.s * 0.800000011920929D, this.t * 0.800000011920929D, this.u * 0.800000011920929D);
        } else {
          d(this.s, this.t, this.u);
        }
        bbj ☃ = new bbj(this.s, this.t, this.u).a();
        float ☃ = ((float)☃.b(☃) + 1.0F) / 2.0F;
        ☃ = 0.8F + 0.15F * ☃;
        
        this.s *= ☃;
        this.u *= ☃;
        this.t *= 0.9100000262260437D;
      }
    }
    this.aM = this.v;
    
    this.bu.G = (this.bu.H = 1.0F);
    this.bv.G = (this.bv.H = 3.0F);
    this.bx.G = (this.bx.H = 2.0F);
    this.by.G = (this.by.H = 2.0F);
    this.bz.G = (this.bz.H = 2.0F);
    this.bw.H = 3.0F;
    this.bw.G = 5.0F;
    this.bA.H = 2.0F;
    this.bA.G = 4.0F;
    this.bB.H = 3.0F;
    this.bB.G = 4.0F;
    
    float ☃ = (float)(a(5, 1.0F)[1] - a(10, 1.0F)[1]) * 10.0F * 0.017453292F;
    float ☃ = on.b(☃);
    float ☃ = on.a(☃);
    
    float ☃ = this.v * 0.017453292F;
    float ☃ = on.a(☃);
    float ☃ = on.b(☃);
    
    this.bw.m();
    this.bw.b(this.p + ☃ * 0.5F, this.q, this.r - ☃ * 0.5F, 0.0F, 0.0F);
    this.bA.m();
    this.bA.b(this.p + ☃ * 4.5F, this.q + 2.0D, this.r + ☃ * 4.5F, 0.0F, 0.0F);
    this.bB.m();
    this.bB.b(this.p - ☃ * 4.5F, this.q + 2.0D, this.r - ☃ * 4.5F, 0.0F, 0.0F);
    if ((!this.l.E) && (this.ax == 0))
    {
      a(this.l.b(this, this.bA.bl().b(4.0D, 2.0D, 4.0D).c(0.0D, -2.0D, 0.0D)));
      a(this.l.b(this, this.bB.bl().b(4.0D, 2.0D, 4.0D).c(0.0D, -2.0D, 0.0D)));
      b(this.l.b(this, this.bu.bl().g(1.0D)));
      b(this.l.b(this, this.bv.bl().g(1.0D)));
    }
    double[] ☃ = a(5, 1.0F);
    
    float ☃ = on.a(this.v * 0.017453292F - this.bf * 0.01F);
    float ☃ = on.b(this.v * 0.017453292F - this.bf * 0.01F);
    this.bu.m();
    this.bv.m();
    float ☃ = q(1.0F);
    this.bu.b(this.p + ☃ * 6.5F * ☃, this.q + ☃ + ☃ * 6.5F, this.r - ☃ * 6.5F * ☃, 0.0F, 0.0F);
    this.bv.b(this.p + ☃ * 5.5F * ☃, this.q + ☃ + ☃ * 5.5F, this.r - ☃ * 5.5F * ☃, 0.0F, 0.0F);
    for (int ☃ = 0; ☃ < 3; ☃++)
    {
      ws ☃ = null;
      if (☃ == 0) {
        ☃ = this.bx;
      }
      if (☃ == 1) {
        ☃ = this.by;
      }
      if (☃ == 2) {
        ☃ = this.bz;
      }
      double[] ☃ = a(12 + ☃ * 2, 1.0F);
      
      float ☃ = this.v * 0.017453292F + c(☃[0] - ☃[0]) * 0.017453292F;
      float ☃ = on.a(☃);
      float ☃ = on.b(☃);
      
      float ☃ = 1.5F;
      float ☃ = (☃ + 1) * 2.0F;
      ☃.m();
      ☃.b(this.p - (☃ * ☃ + ☃ * ☃) * ☃, this.q + (☃[1] - ☃[1]) - (☃ + ☃) * ☃ + 1.5D, this.r + (☃ * ☃ + ☃ * ☃) * ☃, 0.0F, 0.0F);
    }
    if (!this.l.E)
    {
      this.bE = (b(this.bu.bl()) | b(this.bv.bl()) | b(this.bw.bl()));
      if (this.bI != null) {
        this.bI.b(this);
      }
    }
  }
  
  private float q(float ☃)
  {
    double ☃ = 0.0D;
    if (this.bJ.a().a())
    {
      ☃ = -1.0D;
    }
    else
    {
      double[] ☃ = a(5, 1.0F);
      double[] ☃ = a(0, 1.0F);
      ☃ = ☃[1] - ☃[0];
    }
    return (float)☃;
  }
  
  private void cV()
  {
    if (this.bG != null) {
      if (this.bG.F) {
        this.bG = null;
      } else if ((this.T % 10 == 0) && 
        (bQ() < bW())) {
        c(bQ() + 1.0F);
      }
    }
    if (this.S.nextInt(10) == 0)
    {
      List<wt> ☃ = this.l.a(wt.class, bl().g(32.0D));
      
      wt ☃ = null;
      double ☃ = Double.MAX_VALUE;
      for (wt ☃ : ☃)
      {
        double ☃ = ☃.h(this);
        if (☃ < ☃)
        {
          ☃ = ☃;
          ☃ = ☃;
        }
      }
      this.bG = ☃;
    }
  }
  
  private void a(List<rr> ☃)
  {
    double ☃ = (this.bw.bl().a + this.bw.bl().d) / 2.0D;
    double ☃ = (this.bw.bl().c + this.bw.bl().f) / 2.0D;
    for (rr ☃ : ☃) {
      if ((☃ instanceof sa))
      {
        double ☃ = ☃.p - ☃;
        double ☃ = ☃.r - ☃;
        double ☃ = ☃ * ☃ + ☃ * ☃;
        ☃.g(☃ / ☃ * 4.0D, 0.20000000298023224D, ☃ / ☃ * 4.0D);
        if ((!this.bJ.a().a()) && (((sa)☃).bH() < ☃.T - 2))
        {
          ☃.a(rc.a(this), 5.0F);
          a(this, ☃);
        }
      }
    }
  }
  
  private void b(List<rr> ☃)
  {
    for (int ☃ = 0; ☃ < ☃.size(); ☃++)
    {
      rr ☃ = (rr)☃.get(☃);
      if ((☃ instanceof sa))
      {
        ☃.a(rc.a(this), 10.0F);
        a(this, ☃);
      }
    }
  }
  
  private float c(double ☃)
  {
    return (float)on.g(☃);
  }
  
  private boolean b(bbh ☃)
  {
    int ☃ = on.c(☃.a);
    int ☃ = on.c(☃.b);
    int ☃ = on.c(☃.c);
    int ☃ = on.c(☃.d);
    int ☃ = on.c(☃.e);
    int ☃ = on.c(☃.f);
    boolean ☃ = false;
    boolean ☃ = false;
    for (int ☃ = ☃; ☃ <= ☃; ☃++) {
      for (int ☃ = ☃; ☃ <= ☃; ☃++) {
        for (int ☃ = ☃; ☃ <= ☃; ☃++)
        {
          cj ☃ = new cj(☃, ☃, ☃);
          arc ☃ = this.l.o(☃);
          ajt ☃ = ☃.t();
          if ((☃.a() != axe.a) && (☃.a() != axe.o)) {
            if (!this.l.U().b("mobGriefing")) {
              ☃ = true;
            } else if ((☃ == aju.cv) || (☃ == aju.Z) || (☃ == aju.bH) || (☃ == aju.h) || (☃ == aju.bF) || (☃ == aju.bG)) {
              ☃ = true;
            } else if ((☃ == aju.bX) || (☃ == aju.dc) || (☃ == aju.dd) || (☃ == aju.bi) || (☃ == aju.db)) {
              ☃ = true;
            } else {
              ☃ = (this.l.g(☃)) || (☃);
            }
          }
        }
      }
    }
    if (☃)
    {
      double ☃ = ☃.a + (☃.d - ☃.a) * this.S.nextFloat();
      double ☃ = ☃.b + (☃.e - ☃.b) * this.S.nextFloat();
      double ☃ = ☃.c + (☃.f - ☃.c) * this.S.nextFloat();
      this.l.a(cy.b, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D, new int[0]);
    }
    return ☃;
  }
  
  public boolean a(ws ☃, rc ☃, float ☃)
  {
    ☃ = this.bJ.a().a(☃, ☃, ☃);
    if (☃ != this.bu) {
      ☃ = ☃ / 4.0F + Math.min(☃, 1.0F);
    }
    if (☃ < 0.01F) {
      return false;
    }
    if (((☃.j() instanceof zj)) || (☃.c()))
    {
      float ☃ = bQ();
      e(☃, ☃);
      if ((bQ() <= 0.0F) && (!this.bJ.a().a()))
      {
        c(1.0F);
        this.bJ.a(xk.j);
      }
      if (this.bJ.a().a())
      {
        this.bL = ((int)(this.bL + (☃ - bQ())));
        if (this.bL > 0.25F * bW())
        {
          this.bL = 0;
          this.bJ.a(xk.e);
        }
      }
    }
    return true;
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (((☃ instanceof rd)) && (((rd)☃).x())) {
      a(this.bw, ☃, ☃);
    }
    return false;
  }
  
  protected boolean e(rc ☃, float ☃)
  {
    return super.a(☃, ☃);
  }
  
  public void Q()
  {
    T();
    if (this.bI != null)
    {
      this.bI.b(this);
      this.bI.a(this);
    }
  }
  
  protected void bC()
  {
    if (this.bI != null) {
      this.bI.b(this);
    }
    this.bF += 1;
    if ((this.bF >= 180) && (this.bF <= 200))
    {
      float ☃ = (this.S.nextFloat() - 0.5F) * 8.0F;
      float ☃ = (this.S.nextFloat() - 0.5F) * 4.0F;
      float ☃ = (this.S.nextFloat() - 0.5F) * 8.0F;
      this.l.a(cy.c, this.p + ☃, this.q + 2.0D + ☃, this.r + ☃, 0.0D, 0.0D, 0.0D, new int[0]);
    }
    boolean ☃ = this.l.U().b("doMobLoot");
    int ☃ = 500;
    if ((this.bI != null) && (!this.bI.d())) {
      ☃ = 12000;
    }
    if (!this.l.E)
    {
      if ((this.bF > 150) && (this.bF % 5 == 0) && (☃)) {
        a(on.d(☃ * 0.08F));
      }
      if (this.bF == 1) {
        this.l.a(1028, new cj(this), 0);
      }
    }
    d(0.0D, 0.10000000149011612D, 0.0D);
    this.aM = (this.v += 20.0F);
    if ((this.bF == 200) && (!this.l.E))
    {
      if (☃) {
        a(on.d(☃ * 0.2F));
      }
      if (this.bI != null) {
        this.bI.a(this);
      }
      T();
    }
  }
  
  private void a(int ☃)
  {
    while (☃ > 0)
    {
      int ☃ = rx.a(☃);
      ☃ -= ☃;
      this.l.a(new rx(this.l, this.p, this.q, this.r, ☃));
    }
  }
  
  public int o()
  {
    if (this.bM[0] == null)
    {
      int ☃ = 0;
      int ☃ = 0;
      int ☃ = 0;
      int ☃ = 0;
      for (int ☃ = 0; ☃ < 24; ☃++)
      {
        int ☃ = 5;
        ☃ = ☃;
        if (☃ < 12)
        {
          ☃ = (int)(60.0F * on.b(2.0F * (-3.1415927F + 0.2617994F * ☃)));
          ☃ = (int)(60.0F * on.a(2.0F * (-3.1415927F + 0.2617994F * ☃)));
        }
        else if (☃ < 20)
        {
          ☃ -= 12;
          ☃ = (int)(40.0F * on.b(2.0F * (-3.1415927F + 0.3926991F * ☃)));
          ☃ = (int)(40.0F * on.a(2.0F * (-3.1415927F + 0.3926991F * ☃)));
          ☃ += 10;
        }
        else
        {
          ☃ -= 20;
          ☃ = (int)(20.0F * on.b(2.0F * (-3.1415927F + 0.7853982F * ☃)));
          ☃ = (int)(20.0F * on.a(2.0F * (-3.1415927F + 0.7853982F * ☃)));
        }
        ☃ = Math.max(this.l.K() + 10, this.l.q(new cj(☃, 0, ☃)).q() + ☃);
        
        this.bM[☃] = new ayn(☃, ☃, ☃);
      }
      this.bN[0] = 6146;
      this.bN[1] = 8197;
      this.bN[2] = 8202;
      this.bN[3] = 16404;
      this.bN[4] = 32808;
      this.bN[5] = 32848;
      this.bN[6] = 65696;
      this.bN[7] = 131392;
      this.bN[8] = 131712;
      this.bN[9] = 263424;
      this.bN[10] = 526848;
      this.bN[11] = 525313;
      
      this.bN[12] = 1581057;
      this.bN[13] = 3166214;
      this.bN[14] = 2138120;
      this.bN[15] = 6373424;
      this.bN[16] = 4358208;
      this.bN[17] = 12910976;
      this.bN[18] = 9044480;
      this.bN[19] = 9706496;
      
      this.bN[20] = 15216640;
      this.bN[21] = 13688832;
      this.bN[22] = 11763712;
      this.bN[23] = 8257536;
    }
    return l(this.p, this.q, this.r);
  }
  
  public int l(double ☃, double ☃, double ☃)
  {
    float ☃ = 10000.0F;
    int ☃ = 0;
    ayn ☃ = new ayn(on.c(☃), on.c(☃), on.c(☃));
    int ☃ = 0;
    if ((this.bI == null) || (this.bI.c() == 0)) {
      ☃ = 12;
    }
    for (int ☃ = ☃; ☃ < 24; ☃++) {
      if (this.bM[☃] != null)
      {
        float ☃ = this.bM[☃].b(☃);
        if (☃ < ☃)
        {
          ☃ = ☃;
          ☃ = ☃;
        }
      }
    }
    return ☃;
  }
  
  public ayp a(int ☃, int ☃, ayn ☃)
  {
    for (int ☃ = 0; ☃ < 24; ☃++)
    {
      ayn ☃ = this.bM[☃];
      ☃.i = false;
      ☃.g = 0.0F;
      ☃.e = 0.0F;
      ☃.f = 0.0F;
      ☃.h = null;
      ☃.d = -1;
    }
    ayn ☃ = this.bM[☃];
    ayn ☃ = this.bM[☃];
    
    ☃.e = 0.0F;
    ☃.f = ☃.a(☃);
    ☃.g = ☃.f;
    
    this.bO.a();
    this.bO.a(☃);
    
    ayn ☃ = ☃;
    
    int ☃ = 0;
    if ((this.bI == null) || (this.bI.c() == 0)) {
      ☃ = 12;
    }
    while (!this.bO.e())
    {
      ayn ☃ = this.bO.c();
      if (☃.equals(☃))
      {
        if (☃ != null)
        {
          ☃.h = ☃;
          ☃ = ☃;
        }
        return a(☃, ☃);
      }
      if (☃.a(☃) < ☃.a(☃)) {
        ☃ = ☃;
      }
      ☃.i = true;
      
      int ☃ = 0;
      for (int ☃ = 0; ☃ < 24; ☃++) {
        if (this.bM[☃] == ☃)
        {
          ☃ = ☃;
          break;
        }
      }
      for (int ☃ = ☃; ☃ < 24; ☃++) {
        if ((this.bN[☃] & 1 << ☃) > 0)
        {
          ayn ☃ = this.bM[☃];
          if (!☃.i)
          {
            float ☃ = ☃.e + ☃.a(☃);
            if ((!☃.a()) || (☃ < ☃.e))
            {
              ☃.h = ☃;
              ☃.e = ☃;
              ☃.f = ☃.a(☃);
              if (☃.a())
              {
                this.bO.a(☃, ☃.e + ☃.f);
              }
              else
              {
                ☃.g = (☃.e + ☃.f);
                this.bO.a(☃);
              }
            }
          }
        }
      }
    }
    if (☃ == ☃) {
      return null;
    }
    bH.debug("Failed to find path from {} to {}", new Object[] { Integer.valueOf(☃), Integer.valueOf(☃) });
    if (☃ != null)
    {
      ☃.h = ☃;
      ☃ = ☃;
    }
    return a(☃, ☃);
  }
  
  private ayp a(ayn ☃, ayn ☃)
  {
    int ☃ = 1;
    ayn ☃ = ☃;
    while (☃.h != null)
    {
      ☃++;
      ☃ = ☃.h;
    }
    ayn[] ☃ = new ayn[☃];
    ☃ = ☃;
    ☃[(--☃)] = ☃;
    while (☃.h != null)
    {
      ☃ = ☃.h;
      ☃[(--☃)] = ☃;
    }
    return new ayp(☃);
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    ☃.a("DragonPhase", this.bJ.a().i().b());
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    if (☃.e("DragonPhase")) {
      this.bJ.a(xk.a(☃.h("DragonPhase")));
    }
  }
  
  protected void L() {}
  
  public rr[] aR()
  {
    return this.bt;
  }
  
  public boolean ap()
  {
    return false;
  }
  
  public aht a()
  {
    return this.l;
  }
  
  public nh bz()
  {
    return nh.f;
  }
  
  protected nf G()
  {
    return ng.aM;
  }
  
  protected nf bR()
  {
    return ng.aR;
  }
  
  protected float cd()
  {
    return 5.0F;
  }
  
  public float a(int ☃, double[] ☃, double[] ☃)
  {
    xe ☃ = this.bJ.a();
    xk<? extends xe> ☃ = ☃.i();
    double ☃;
    double ☃;
    if ((☃ == xk.d) || (☃ == xk.e))
    {
      cj ☃ = this.l.q(auc.a);
      float ☃ = Math.max(on.a(d(☃)) / 4.0F, 1.0F);
      ☃ = ☃ / ☃;
    }
    else
    {
      double ☃;
      if (☃.a())
      {
        ☃ = ☃;
      }
      else
      {
        double ☃;
        if (☃ == 6) {
          ☃ = 0.0D;
        } else {
          ☃ = ☃[1] - ☃[1];
        }
      }
    }
    return (float)☃;
  }
  
  public bbj a(float ☃)
  {
    xe ☃ = this.bJ.a();
    xk<? extends xe> ☃ = ☃.i();
    bbj ☃;
    if ((☃ == xk.d) || (☃ == xk.e))
    {
      cj ☃ = this.l.q(auc.a);
      float ☃ = Math.max(on.a(d(☃)) / 4.0F, 1.0F);
      float ☃ = 6.0F / ☃;
      
      float ☃ = this.w;
      float ☃ = 1.5F;
      this.w = (-☃ * ☃ * 5.0F);
      
      bbj ☃ = f(☃);
      this.w = ☃;
    }
    else if (☃.a())
    {
      float ☃ = this.w;
      float ☃ = 1.5F;
      this.w = (-6.0F * ☃ * 5.0F);
      
      bbj ☃ = f(☃);
      this.w = ☃;
    }
    else
    {
      ☃ = f(☃);
    }
    return ☃;
  }
  
  public void a(wt ☃, cj ☃, rc ☃)
  {
    zj ☃;
    zj ☃;
    if ((☃.j() instanceof zj)) {
      ☃ = (zj)☃.j();
    } else {
      ☃ = this.l.a(☃, 64.0D, 64.0D);
    }
    if (☃ == this.bG) {
      a(this.bu, rc.b(☃), 10.0F);
    }
    this.bJ.a().a(☃, ☃, ☃, ☃);
  }
  
  public void a(ke<?> ☃)
  {
    if ((a.equals(☃)) && (this.l.E)) {
      this.bJ.a(xk.a(((Integer)R().a(a)).intValue()));
    }
    super.a(☃);
  }
  
  public xl cT()
  {
    return this.bJ;
  }
  
  public ata cU()
  {
    return this.bI;
  }
  
  public void c(rl ☃) {}
  
  protected boolean n(rr ☃)
  {
    return false;
  }
  
  public boolean aV()
  {
    return false;
  }
}
