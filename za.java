import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class za
  extends yq
{
  protected static final sl a = new ss(null, "zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D).a("Spawn Reinforcements Chance");
  private static final UUID b = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
  private static final sn c = new sn(b, "Baby speed boost", 0.5D, 1);
  private static final ke<Boolean> bv = kh.a(za.class, kg.h);
  private static final ke<Integer> bw = kh.a(za.class, kg.b);
  private static final ke<Boolean> bx = kh.a(za.class, kg.h);
  private static final ke<Boolean> by = kh.a(za.class, kg.h);
  private final tc bz = new tc(this);
  private int bA;
  private boolean bB = false;
  
  public za(aht ☃)
  {
    super(☃);
    
    a(0.6F, 1.95F);
  }
  
  protected void r()
  {
    this.bp.a(0, new th(this));
    this.bp.a(2, new us(this, 1.0D, false));
    this.bp.a(5, new tw(this, 1.0D));
    this.bp.a(7, new ug(this, 1.0D));
    this.bp.a(8, new tp(this, zj.class, 8.0F));
    this.bp.a(8, new uf(this));
    
    o();
  }
  
  protected void o()
  {
    this.bp.a(6, new tu(this, 1.0D, false));
    
    this.bq.a(1, new uv(this, true, new Class[] { yr.class }));
    this.bq.a(2, new uy(this, zj.class, true));
    this.bq.a(3, new uy(this, ze.class, false));
    this.bq.a(3, new uy(this, wh.class, true));
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.b).a(35.0D);
    a(yt.d).a(0.23000000417232513D);
    a(yt.e).a(3.0D);
    a(yt.g).a(2.0D);
    
    bZ().b(a).a(this.S.nextDouble() * 0.10000000149011612D);
  }
  
  protected void i()
  {
    super.i();
    
    R().a(bv, Boolean.valueOf(false));
    R().a(bw, Integer.valueOf(0));
    R().a(bx, Boolean.valueOf(false));
    R().a(by, Boolean.valueOf(false));
  }
  
  public void a(boolean ☃)
  {
    R().b(by, Boolean.valueOf(☃));
  }
  
  public boolean db()
  {
    return ((Boolean)R().a(by)).booleanValue();
  }
  
  public boolean dc()
  {
    return this.bB;
  }
  
  public void o(boolean ☃)
  {
    if (this.bB != ☃)
    {
      this.bB = ☃;
      ((ve)x()).a(☃);
      if (☃) {
        this.bp.a(1, this.bz);
      } else {
        this.bp.a(this.bz);
      }
    }
  }
  
  public boolean m_()
  {
    return ((Boolean)R().a(bv)).booleanValue();
  }
  
  protected int b(zj ☃)
  {
    if (m_()) {
      this.b_ = ((int)(this.b_ * 2.5F));
    }
    return super.b(☃);
  }
  
  public void p(boolean ☃)
  {
    R().b(bv, Boolean.valueOf(☃));
    if ((this.l != null) && (!this.l.E))
    {
      sm ☃ = a(yt.d);
      ☃.c(c);
      if (☃) {
        ☃.b(c);
      }
    }
    q(☃);
  }
  
  public boolean dd()
  {
    return ((Integer)R().a(bw)).intValue() > 0;
  }
  
  public int de()
  {
    return ((Integer)R().a(bw)).intValue() - 1;
  }
  
  public void a(int ☃)
  {
    R().b(bw, Integer.valueOf(☃ + 1));
  }
  
  public void df()
  {
    R().b(bw, Integer.valueOf(0));
  }
  
  public void a(ke<?> ☃)
  {
    if (bv.equals(☃)) {
      q(m_());
    }
    super.a(☃);
  }
  
  public void n()
  {
    if ((this.l.B()) && (!this.l.E) && (!m_()))
    {
      float ☃ = e(1.0F);
      cj ☃ = (by() instanceof aag) ? new cj(this.p, Math.round(this.q), this.r).a() : new cj(this.p, Math.round(this.q), this.r);
      if ((☃ > 0.5F) && (this.S.nextFloat() * 30.0F < (☃ - 0.4F) * 2.0F) && (this.l.h(☃)))
      {
        boolean ☃ = true;
        
        adq ☃ = a(rw.f);
        if (☃ != null)
        {
          if (☃.e())
          {
            ☃.b(☃.h() + this.S.nextInt(2));
            if (☃.h() >= ☃.j())
            {
              b(☃);
              a(rw.f, null);
            }
          }
          ☃ = false;
        }
        if (☃) {
          g(8);
        }
      }
    }
    super.n();
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (super.a(☃, ☃))
    {
      sa ☃ = A();
      if ((☃ == null) && ((☃.j() instanceof sa))) {
        ☃ = (sa)☃.j();
      }
      if ((☃ != null) && (this.l.ae() == qk.d) && (this.S.nextFloat() < a(a).e()) && (this.l.U().b("doMobSpawning")))
      {
        int ☃ = on.c(this.p);
        int ☃ = on.c(this.q);
        int ☃ = on.c(this.r);
        za ☃ = new za(this.l);
        for (int ☃ = 0; ☃ < 50; ☃++)
        {
          int ☃ = ☃ + on.a(this.S, 7, 40) * on.a(this.S, -1, 1);
          int ☃ = ☃ + on.a(this.S, 7, 40) * on.a(this.S, -1, 1);
          int ☃ = ☃ + on.a(this.S, 7, 40) * on.a(this.S, -1, 1);
          if ((this.l.o(new cj(☃, ☃ - 1, ☃)).q()) && (this.l.k(new cj(☃, ☃, ☃)) < 10))
          {
            ☃.b(☃, ☃, ☃);
            if ((!this.l.a(☃, ☃, ☃, 7.0D)) && (this.l.a(☃.bl(), ☃)) && (this.l.a(☃, ☃.bl()).isEmpty()) && (!this.l.e(☃.bl())))
            {
              this.l.a(☃);
              ☃.c(☃);
              ☃.a(this.l.D(new cj(☃)), null);
              
              a(a).b(new sn("Zombie reinforcement caller charge", -0.05000000074505806D, 0));
              ☃.a(a).b(new sn("Zombie reinforcement callee charge", -0.05000000074505806D, 0));
              break;
            }
          }
        }
      }
      return true;
    }
    return false;
  }
  
  public void m()
  {
    if ((!this.l.E) && (dg()))
    {
      int ☃ = di();
      
      this.bA -= ☃;
      if (this.bA <= 0) {
        dh();
      }
    }
    super.m();
  }
  
  public boolean B(rr ☃)
  {
    boolean ☃ = super.B(☃);
    if (☃)
    {
      int ☃ = this.l.ae().a();
      if ((cb() == null) && (aH()) && (this.S.nextFloat() < ☃ * 0.3F)) {
        ☃.g(2 * ☃);
      }
    }
    return ☃;
  }
  
  protected nf G()
  {
    return dd() ? ng.hv : ng.hg;
  }
  
  protected nf bR()
  {
    return dd() ? ng.hz : ng.ho;
  }
  
  protected nf bS()
  {
    return dd() ? ng.hy : ng.hk;
  }
  
  protected void a(cj ☃, ajt ☃)
  {
    a(dd() ? ng.hA : ng.hu, 0.15F, 1.0F);
  }
  
  public sf ca()
  {
    return sf.b;
  }
  
  protected kk J()
  {
    return azt.ah;
  }
  
  protected void a(ql ☃)
  {
    super.a(☃);
    if (this.S.nextFloat() < (this.l.ae() == qk.d ? 0.05F : 0.01F))
    {
      int ☃ = this.S.nextInt(3);
      if (☃ == 0) {
        a(rw.a, new adq(ads.n));
      } else {
        a(rw.a, new adq(ads.a));
      }
    }
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    if (m_()) {
      ☃.a("IsBaby", true);
    }
    if (dd())
    {
      ☃.a("IsVillager", true);
      ☃.a("VillagerProfession", de());
    }
    ☃.a("ConversionTime", dg() ? this.bA : -1);
    ☃.a("CanBreakDoors", dc());
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    if (☃.p("IsBaby")) {
      p(true);
    }
    if (☃.p("IsVillager")) {
      if (☃.b("VillagerProfession", 99)) {
        a(☃.h("VillagerProfession"));
      } else {
        a(this.l.r.nextInt(5));
      }
    }
    if ((☃.b("ConversionTime", 99)) && (☃.h("ConversionTime") > -1)) {
      b(☃.h("ConversionTime"));
    }
    o(☃.p("CanBreakDoors"));
  }
  
  public void b(sa ☃)
  {
    super.b(☃);
    if (((this.l.ae() == qk.c) || (this.l.ae() == qk.d)) && ((☃ instanceof ze)))
    {
      if ((this.l.ae() != qk.d) && (this.S.nextBoolean())) {
        return;
      }
      ze ☃ = (ze)☃;
      za ☃ = new za(this.l);
      ☃.u(☃);
      this.l.e(☃);
      ☃.a(this.l.D(new cj(☃)), new za.a(false, true, null));
      ☃.a(☃.cZ());
      ☃.p(☃.m_());
      ☃.m(☃.cR());
      if (☃.o_())
      {
        ☃.c(☃.bf());
        ☃.i(☃.bg());
      }
      this.l.a(☃);
      
      this.l.a(null, 1026, new cj((int)this.p, (int)this.q, (int)this.r), 0);
    }
  }
  
  public float bn()
  {
    float ☃ = 1.74F;
    if (m_()) {
      ☃ = (float)(☃ - 0.81D);
    }
    return ☃;
  }
  
  protected boolean c(adq ☃)
  {
    if ((☃.b() == ads.aW) && (m_()) && (aI())) {
      return false;
    }
    return super.c(☃);
  }
  
  public sd a(ql ☃, sd ☃)
  {
    ☃ = super.a(☃, ☃);
    float ☃ = ☃.c();
    
    l(this.S.nextFloat() < 0.55F * ☃);
    if (☃ == null) {
      ☃ = new za.a(this.l.r.nextFloat() < 0.05F, this.l.r.nextFloat() < 0.05F, null);
    }
    if ((☃ instanceof za.a))
    {
      za.a ☃ = (za.a)☃;
      if (☃.b) {
        a(this.S.nextInt(5));
      }
      if (☃.a)
      {
        p(true);
        if (this.l.r.nextFloat() < 0.05D)
        {
          List<vx> ☃ = this.l.a(vx.class, bl().b(5.0D, 3.0D, 5.0D), rv.b);
          if (!☃.isEmpty())
          {
            vx ☃ = (vx)☃.get(0);
            ☃.o(true);
            m(☃);
          }
        }
        else if (this.l.r.nextFloat() < 0.05D)
        {
          vx ☃ = new vx(this.l);
          ☃.b(this.p, this.q, this.r, this.v, 0.0F);
          ☃.a(☃, null);
          ☃.o(true);
          this.l.a(☃);
          m(☃);
        }
      }
    }
    o(this.S.nextFloat() < ☃ * 0.1F);
    
    a(☃);
    b(☃);
    if (a(rw.f) == null)
    {
      Calendar ☃ = this.l.ac();
      if ((☃.get(2) + 1 == 10) && (☃.get(5) == 31) && (this.S.nextFloat() < 0.25F))
      {
        a(rw.f, new adq(this.S.nextFloat() < 0.1F ? aju.aZ : aju.aU));
        this.bs[rw.f.b()] = 0.0F;
      }
    }
    a(yt.c).b(new sn("Random spawn bonus", this.S.nextDouble() * 0.05000000074505806D, 0));
    double ☃ = this.S.nextDouble() * 1.5D * ☃;
    if (☃ > 1.0D) {
      a(yt.b).b(new sn("Random zombie-spawn bonus", ☃, 2));
    }
    if (this.S.nextFloat() < ☃ * 0.05F)
    {
      a(a).b(new sn("Leader zombie bonus", this.S.nextDouble() * 0.25D + 0.5D, 0));
      a(yt.a).b(new sn("Leader zombie bonus", this.S.nextDouble() * 3.0D + 1.0D, 2));
      o(true);
    }
    return ☃;
  }
  
  public boolean a(zj ☃, qm ☃, adq ☃)
  {
    if ((☃ != null) && (☃.b() == ads.aq) && (☃.i() == 0) && (dd()) && (a(rm.r)))
    {
      if (!☃.bJ.d) {
        ☃.b -= 1;
      }
      if (!this.l.E) {
        b(this.S.nextInt(2401) + 3600);
      }
      return true;
    }
    return false;
  }
  
  protected void b(int ☃)
  {
    this.bA = ☃;
    R().b(bx, Boolean.valueOf(true));
    
    d(rm.r);
    c(new rl(rm.e, ☃, Math.min(this.l.ae().a() - 1, 0)));
    
    this.l.a(this, (byte)16);
  }
  
  public void a(byte ☃)
  {
    if (☃ == 16)
    {
      if (!ad()) {
        this.l.a(this.p + 0.5D, this.q + 0.5D, this.r + 0.5D, ng.hx, bz(), 1.0F + this.S.nextFloat(), this.S.nextFloat() * 0.7F + 0.3F, false);
      }
    }
    else {
      super.a(☃);
    }
  }
  
  protected boolean K()
  {
    return !dg();
  }
  
  public boolean dg()
  {
    return ((Boolean)R().a(bx)).booleanValue();
  }
  
  protected void dh()
  {
    ze ☃ = new ze(this.l);
    ☃.u(this);
    ☃.a(this.l.D(new cj(☃)), null);
    ☃.dd();
    if (m_()) {
      ☃.b_(41536);
    }
    this.l.e(this);
    ☃.m(cR());
    ☃.l(de());
    if (o_())
    {
      ☃.c(bf());
      ☃.i(bg());
    }
    this.l.a(☃);
    
    ☃.c(new rl(rm.i, 200, 0));
    this.l.a(null, 1027, new cj((int)this.p, (int)this.q, (int)this.r), 0);
  }
  
  protected int di()
  {
    int ☃ = 1;
    if (this.S.nextFloat() < 0.01F)
    {
      int ☃ = 0;
      
      cj.a ☃ = new cj.a();
      for (int ☃ = (int)this.p - 4; (☃ < (int)this.p + 4) && (☃ < 14); ☃++) {
        for (int ☃ = (int)this.q - 4; (☃ < (int)this.q + 4) && (☃ < 14); ☃++) {
          for (int ☃ = (int)this.r - 4; (☃ < (int)this.r + 4) && (☃ < 14); ☃++)
          {
            ajt ☃ = this.l.o(☃.c(☃, ☃, ☃)).t();
            if ((☃ == aju.bi) || (☃ == aju.C))
            {
              if (this.S.nextFloat() < 0.3F) {
                ☃++;
              }
              ☃++;
            }
          }
        }
      }
    }
    return ☃;
  }
  
  class a
    implements sd
  {
    public boolean a = false;
    public boolean b = false;
    
    private a(boolean ☃, boolean ☃)
    {
      this.a = ☃;
      this.b = ☃;
    }
  }
  
  public void q(boolean ☃)
  {
    a(☃ ? 0.5F : 1.0F);
  }
  
  private float bC = -1.0F;
  private float bD;
  
  protected final void a(float ☃, float ☃)
  {
    boolean ☃ = (this.bC > 0.0F) && (this.bD > 0.0F);
    
    this.bC = ☃;
    this.bD = ☃;
    if (!☃) {
      a(1.0F);
    }
  }
  
  protected final void a(float ☃)
  {
    super.a(this.bC * ☃, this.bD * ☃);
  }
  
  public double ax()
  {
    return m_() ? 0.0D : -0.35D;
  }
  
  public void a(rc ☃)
  {
    super.a(☃);
    if (((☃.j() instanceof yi)) && (!(this instanceof yr)) && 
      (((yi)☃.j()).o()) && (((yi)☃.j()).dd()))
    {
      ((yi)☃.j()).de();
      a(new adq(ads.ch, 1, 2), 0.0F);
    }
  }
}
