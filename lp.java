import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class lp
  extends aht
  implements qc
{
  private static final Logger a = ;
  private final MinecraftServer I;
  private final lm J;
  private final lv K;
  private final Set<aie> L = Sets.newHashSet();
  private final TreeSet<aie> M = new TreeSet();
  private final Map<UUID, rr> N = Maps.newHashMap();
  public boolean b;
  private boolean O;
  private int P;
  private final aib Q;
  private final aia R = new aia();
  protected final vq c = new vq(this);
  private lp.a[] S = { new lp.a(null), new lp.a(null) };
  private int T;
  
  public lp(MinecraftServer ☃, azi ☃, azh ☃, int ☃, oo ☃)
  {
    super(☃, ☃, asw.a(☃).d(), ☃, false);
    this.I = ☃;
    this.J = new lm(this);
    this.K = new lv(this);
    
    this.s.a(this);
    this.v = n();
    
    this.Q = new aib(this);
    
    H();
    I();
    
    aj().a(☃.aD());
  }
  
  public aht b()
  {
    this.z = new azs(this.w);
    
    String ☃ = vr.a(this.s);
    vr ☃ = (vr)this.z.a(vr.class, ☃);
    if (☃ == null)
    {
      this.A = new vr(this);
      this.z.a(☃, this.A);
    }
    else
    {
      this.A = ☃;
      this.A.a(this);
    }
    this.D = new kw(this.I);
    bbq ☃ = (bbq)this.z.a(bbq.class, "scoreboard");
    if (☃ == null)
    {
      ☃ = new bbq();
      this.z.a("scoreboard", ☃);
    }
    ☃.a(this.D);
    ((kw)this.D).a(new ayw(☃));
    
    this.B = new bab(new File(new File(this.w.b(), "data"), "loot_tables"));
    
    aj().c(this.x.B(), this.x.C());
    aj().c(this.x.H());
    aj().b(this.x.G());
    aj().c(this.x.I());
    aj().b(this.x.J());
    if (this.x.E() > 0L) {
      aj().a(this.x.D(), this.x.F(), this.x.E());
    } else {
      aj().a(this.x.D());
    }
    return this;
  }
  
  public void d()
  {
    super.d();
    if ((T().s()) && (ae() != qk.d)) {
      T().a(qk.d);
    }
    this.s.k().b();
    if (g())
    {
      if (U().b("doDaylightCycle"))
      {
        long ☃ = this.x.f() + 24000L;
        this.x.c(☃ - ☃ % 24000L);
      }
      f();
    }
    this.C.a("mobSpawner");
    if ((U().b("doMobSpawning")) && (this.x.t() != ahy.g)) {
      this.R.a(this, this.F, this.G, this.x.e() % 400L == 0L);
    }
    this.C.c("chunkSource");
    this.v.d();
    int ☃ = a(1.0F);
    if (☃ != af()) {
      c(☃);
    }
    this.x.b(this.x.e() + 1L);
    if (U().b("doDaylightCycle")) {
      this.x.c(this.x.f() + 1L);
    }
    this.C.c("tickPending");
    a(false);
    
    this.C.c("tickBlocks");
    j();
    
    this.C.c("chunkMap");
    this.K.c();
    
    this.C.c("village");
    this.A.a();
    this.c.a();
    
    this.C.c("portalForcer");
    this.Q.a(P());
    
    this.C.b();
    
    ao();
  }
  
  public aig.c a(sc ☃, cj ☃)
  {
    List<aig.c> ☃ = r().a(☃, ☃);
    if ((☃ == null) || (☃.isEmpty())) {
      return null;
    }
    return (aig.c)ov.a(this.r, ☃);
  }
  
  public boolean a(sc ☃, aig.c ☃, cj ☃)
  {
    List<aig.c> ☃ = r().a(☃, ☃);
    if ((☃ == null) || (☃.isEmpty())) {
      return false;
    }
    return ☃.contains(☃);
  }
  
  public void e()
  {
    this.O = false;
    if (!this.i.isEmpty())
    {
      int ☃ = 0;
      int ☃ = 0;
      for (zj ☃ : this.i) {
        if (☃.y()) {
          ☃++;
        } else if (☃.cl()) {
          ☃++;
        }
      }
      this.O = ((☃ > 0) && (☃ >= this.i.size() - ☃));
    }
  }
  
  protected void f()
  {
    this.O = false;
    for (zj ☃ : this.i) {
      if (☃.cl()) {
        ☃.a(false, false, true);
      }
    }
    c();
  }
  
  private void c()
  {
    this.x.g(0);
    this.x.b(false);
    this.x.f(0);
    this.x.a(false);
  }
  
  public boolean g()
  {
    if ((this.O) && (!this.E))
    {
      for (zj ☃ : this.i) {
        if ((!☃.y()) && (!☃.cM())) {
          return false;
        }
      }
      return true;
    }
    return false;
  }
  
  public void h()
  {
    if (this.x.c() <= 0) {
      this.x.b(K() + 1);
    }
    int ☃ = this.x.b();
    int ☃ = this.x.d();
    int ☃ = 0;
    while (c(new cj(☃, 0, ☃)).a() == axe.a)
    {
      ☃ += this.r.nextInt(8) - this.r.nextInt(8);
      ☃ += this.r.nextInt(8) - this.r.nextInt(8);
      ☃++;
      if (☃ == 10000) {
        break;
      }
    }
    this.x.a(☃);
    this.x.c(☃);
  }
  
  protected boolean a(int ☃, int ☃, boolean ☃)
  {
    return r().e(☃, ☃);
  }
  
  protected void i()
  {
    this.C.a("playerCheckLight");
    if (!this.i.isEmpty())
    {
      int ☃ = this.r.nextInt(this.i.size());
      zj ☃ = (zj)this.i.get(☃);
      int ☃ = on.c(☃.p) + this.r.nextInt(11) - 5;
      int ☃ = on.c(☃.q) + this.r.nextInt(11) - 5;
      int ☃ = on.c(☃.r) + this.r.nextInt(11) - 5;
      w(new cj(☃, ☃, ☃));
    }
    this.C.b();
  }
  
  protected void j()
  {
    i();
    if (this.x.t() == ahy.g)
    {
      Iterator<ase> ☃ = this.K.b();
      while (☃.hasNext()) {
        ((ase)☃.next()).b(false);
      }
      return;
    }
    int ☃ = U().c("randomTickSpeed");
    boolean ☃ = W();
    boolean ☃ = V();
    
    this.C.a("pollingChunks");
    Iterator<ase> ☃ = this.K.b();
    while (☃.hasNext())
    {
      this.C.a("getChunk");
      ase ☃ = (ase)☃.next();
      int ☃ = ☃.b * 16;
      int ☃ = ☃.c * 16;
      
      this.C.c("checkNextLight");
      ☃.n();
      
      this.C.c("tickChunk");
      ☃.b(false);
      
      this.C.c("thunder");
      if ((☃) && (☃) && (this.r.nextInt(100000) == 0))
      {
        this.l = (this.l * 3 + 1013904223);
        int ☃ = this.l >> 2;
        
        cj ☃ = a(new cj(☃ + (☃ & 0xF), 0, ☃ + (☃ >> 8 & 0xF)));
        if (B(☃))
        {
          ql ☃ = D(☃);
          if (this.r.nextDouble() < ☃.b() * 0.05D)
          {
            wk ☃ = new wk(this);
            ☃.a(wm.e);
            ☃.x(true);
            ☃.b_(0);
            ☃.b(☃.p(), ☃.q(), ☃.r());
            a(☃);
            d(new ya(this, ☃.p(), ☃.q(), ☃.r(), true));
          }
          else
          {
            d(new ya(this, ☃.p(), ☃.q(), ☃.r(), false));
          }
        }
      }
      this.C.c("iceandsnow");
      if (this.r.nextInt(16) == 0)
      {
        this.l = (this.l * 3 + 1013904223);
        int ☃ = this.l >> 2;
        
        cj ☃ = p(new cj(☃ + (☃ & 0xF), 0, ☃ + (☃ >> 8 & 0xF)));
        cj ☃ = ☃.b();
        if (v(☃)) {
          a(☃, aju.aI.u());
        }
        if ((☃) && (f(☃, true))) {
          a(☃, aju.aH.u());
        }
        if ((☃) && 
          (b(☃).d())) {
          o(☃).t().h(this, ☃);
        }
      }
      this.C.c("tickBlocks");
      if (☃ > 0) {
        for (asf ☃ : ☃.h()) {
          if ((☃ != ase.a) && (☃.b())) {
            for (int ☃ = 0; ☃ < ☃; ☃++)
            {
              this.l = (this.l * 3 + 1013904223);
              int ☃ = this.l >> 2;
              int ☃ = ☃ & 0xF;
              int ☃ = ☃ >> 8 & 0xF;
              int ☃ = ☃ >> 16 & 0xF;
              
              arc ☃ = ☃.a(☃, ☃, ☃);
              ajt ☃ = ☃.t();
              this.C.a("randomTick");
              if (☃.l()) {
                ☃.a(this, new cj(☃ + ☃, ☃ + ☃.d(), ☃ + ☃), ☃, this.r);
              }
              this.C.b();
            }
          }
        }
      }
      this.C.b();
    }
    this.C.b();
  }
  
  protected cj a(cj ☃)
  {
    cj ☃ = p(☃);
    bbh ☃ = new bbh(☃, new cj(☃.p(), Y(), ☃.r())).g(3.0D);
    
    List<sa> ☃ = a(sa.class, ☃, new Predicate()
    {
      public boolean a(sa ☃)
      {
        return (☃ != null) && (☃.au()) && (lp.this.h(☃.c()));
      }
    });
    if (!☃.isEmpty()) {
      return ((sa)☃.get(this.r.nextInt(☃.size()))).c();
    }
    if (☃.q() == -1) {
      ☃ = ☃.b(2);
    }
    return ☃;
  }
  
  public boolean a(cj ☃, ajt ☃)
  {
    aie ☃ = new aie(☃, ☃);
    return this.U.contains(☃);
  }
  
  public boolean b(cj ☃, ajt ☃)
  {
    aie ☃ = new aie(☃, ☃);
    return this.L.contains(☃);
  }
  
  public void a(cj ☃, ajt ☃, int ☃)
  {
    a(☃, ☃, ☃, 0);
  }
  
  public void a(cj ☃, ajt ☃, int ☃, int ☃)
  {
    if (((☃ instanceof cj.a)) || ((☃ instanceof cj.b)))
    {
      ☃ = new cj(☃);
      LogManager.getLogger().warn("Tried to assign a mutable BlockPos to tick data...", new Error(☃.getClass().toString()));
    }
    int ☃ = 0;
    axe ☃ = ☃.u().a();
    if ((this.d) && (☃ != axe.a))
    {
      if (☃.s())
      {
        ☃ = 8;
        if (a(☃.a(-☃, -☃, -☃), ☃.a(☃, ☃, ☃)))
        {
          arc ☃ = o(☃);
          if ((☃.a() != axe.a) && (☃.t() == ☃)) {
            ☃.t().b(this, ☃, ☃, this.r);
          }
        }
        return;
      }
      ☃ = 1;
    }
    aie ☃ = new aie(☃, ☃);
    if (a(☃.a(-☃, -☃, -☃), ☃.a(☃, ☃, ☃)))
    {
      if (☃ != axe.a)
      {
        ☃.a(☃ + this.x.e());
        ☃.a(☃);
      }
      if (!this.L.contains(☃))
      {
        this.L.add(☃);
        this.M.add(☃);
      }
    }
  }
  
  public void b(cj ☃, ajt ☃, int ☃, int ☃)
  {
    if (((☃ instanceof cj.a)) || ((☃ instanceof cj.b)))
    {
      ☃ = new cj(☃);
      LogManager.getLogger().warn("Tried to assign a mutable BlockPos to tick data...", new Error(☃.getClass().toString()));
    }
    aie ☃ = new aie(☃, ☃);
    ☃.a(☃);
    
    axe ☃ = ☃.u().a();
    if (☃ != axe.a) {
      ☃.a(☃ + this.x.e());
    }
    if (!this.L.contains(☃))
    {
      this.L.add(☃);
      this.M.add(☃);
    }
  }
  
  public void k()
  {
    if (this.i.isEmpty())
    {
      if (this.P++ < 300) {}
    }
    else {
      m();
    }
    this.s.r();
    super.k();
  }
  
  protected void l()
  {
    super.l();
    
    this.C.c("players");
    for (int ☃ = 0; ☃ < this.i.size(); ☃++)
    {
      rr ☃ = (rr)this.i.get(☃);
      
      rr ☃ = ☃.by();
      if (☃ != null)
      {
        if ((☃.F) || (!☃.w(☃))) {
          ☃.p();
        }
      }
      else
      {
        this.C.a("tick");
        if (!☃.F) {
          try
          {
            g(☃);
          }
          catch (Throwable ☃)
          {
            b ☃ = b.a(☃, "Ticking player");
            c ☃ = ☃.a("Player being ticked");
            
            ☃.a(☃);
            
            throw new e(☃);
          }
        }
        this.C.b();
        
        this.C.a("remove");
        if (☃.F)
        {
          int ☃ = ☃.ab;
          int ☃ = ☃.ad;
          if ((☃.aa) && (a(☃, ☃, true))) {
            a(☃, ☃).b(☃);
          }
          this.e.remove(☃);
          c(☃);
        }
        this.C.b();
      }
    }
  }
  
  public void m()
  {
    this.P = 0;
  }
  
  private List<aie> U = Lists.newArrayList();
  
  public boolean a(boolean ☃)
  {
    if (this.x.t() == ahy.g) {
      return false;
    }
    int ☃ = this.M.size();
    if (☃ != this.L.size()) {
      throw new IllegalStateException("TickNextTick list out of synch");
    }
    if (☃ > 1000) {
      ☃ = 1000;
    }
    this.C.a("cleaning");
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      aie ☃ = (aie)this.M.first();
      if ((!☃) && (☃.b > this.x.e())) {
        break;
      }
      this.M.remove(☃);
      this.L.remove(☃);
      this.U.add(☃);
    }
    this.C.b();
    
    this.C.a("ticking");
    Iterator<aie> ☃ = this.U.iterator();
    while (☃.hasNext())
    {
      aie ☃ = (aie)☃.next();
      ☃.remove();
      
      int ☃ = 0;
      if (a(☃.a.a(-☃, -☃, -☃), ☃.a.a(☃, ☃, ☃)))
      {
        arc ☃ = o(☃.a);
        if ((☃.a() != axe.a) && (ajt.a(☃.t(), ☃.a()))) {
          try
          {
            ☃.t().b(this, ☃.a, ☃, this.r);
          }
          catch (Throwable ☃)
          {
            b ☃ = b.a(☃, "Exception while ticking a block");
            c ☃ = ☃.a("Block being ticked");
            c.a(☃, ☃.a, ☃);
            throw new e(☃);
          }
        }
      }
      else
      {
        a(☃.a, ☃.a(), 0);
      }
    }
    this.C.b();
    
    this.U.clear();
    
    return !this.M.isEmpty();
  }
  
  public List<aie> a(ase ☃, boolean ☃)
  {
    ahn ☃ = ☃.k();
    int ☃ = (☃.a << 4) - 2;
    int ☃ = ☃ + 16 + 2;
    int ☃ = (☃.b << 4) - 2;
    int ☃ = ☃ + 16 + 2;
    
    return a(new avp(☃, 0, ☃, ☃, 256, ☃), ☃);
  }
  
  public List<aie> a(avp ☃, boolean ☃)
  {
    List<aie> ☃ = null;
    for (int ☃ = 0; ☃ < 2; ☃++)
    {
      Iterator<aie> ☃;
      Iterator<aie> ☃;
      if (☃ == 0) {
        ☃ = this.M.iterator();
      } else {
        ☃ = this.U.iterator();
      }
      while (☃.hasNext())
      {
        aie ☃ = (aie)☃.next();
        cj ☃ = ☃.a;
        if ((☃.p() >= ☃.a) && (☃.p() < ☃.d) && (☃.r() >= ☃.c) && (☃.r() < ☃.f))
        {
          if (☃)
          {
            if (☃ == 0) {
              this.L.remove(☃);
            }
            ☃.remove();
          }
          if (☃ == null) {
            ☃ = Lists.newArrayList();
          }
          ☃.add(☃);
        }
      }
    }
    return ☃;
  }
  
  public void a(rr ☃, boolean ☃)
  {
    if ((!am()) && (((☃ instanceof vw)) || ((☃ instanceof wi)))) {
      ☃.T();
    }
    if ((!al()) && ((☃ instanceof zd))) {
      ☃.T();
    }
    super.a(☃, ☃);
  }
  
  private boolean al()
  {
    return this.I.ad();
  }
  
  private boolean am()
  {
    return this.I.ac();
  }
  
  protected arz n()
  {
    asm ☃ = this.w.a(this.s);
    return new lo(this, ☃, this.s.c());
  }
  
  public List<apv> a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    List<apv> ☃ = Lists.newArrayList();
    for (int ☃ = 0; ☃ < this.g.size(); ☃++)
    {
      apv ☃ = (apv)this.g.get(☃);
      cj ☃ = ☃.v();
      if ((☃.p() >= ☃) && (☃.q() >= ☃) && (☃.r() >= ☃) && (☃.p() < ☃) && (☃.q() < ☃) && (☃.r() < ☃)) {
        ☃.add(☃);
      }
    }
    return ☃;
  }
  
  public boolean a(zj ☃, cj ☃)
  {
    return (!this.I.a(this, ☃, ☃)) && (aj().a(☃));
  }
  
  public void a(ahw ☃)
  {
    if (!this.x.v())
    {
      try
      {
        b(☃);
        if (this.x.t() == ahy.g) {
          an();
        }
        super.a(☃);
      }
      catch (Throwable ☃)
      {
        b ☃ = b.a(☃, "Exception initializing level");
        try
        {
          a(☃);
        }
        catch (Throwable localThrowable1) {}
        throw new e(☃);
      }
      this.x.d(true);
    }
  }
  
  private void an()
  {
    this.x.f(false);
    this.x.c(true);
    this.x.b(false);
    this.x.a(false);
    this.x.i(1000000000);
    this.x.c(6000L);
    this.x.a(ahw.a.e);
    this.x.g(false);
    this.x.a(qk.a);
    this.x.e(true);
    U().a("doDaylightCycle", "false");
  }
  
  private void b(ahw ☃)
  {
    if (!this.s.e())
    {
      this.x.a(cj.a.b(this.s.i()));
      return;
    }
    if (this.x.t() == ahy.g)
    {
      this.x.a(cj.a.a());
      return;
    }
    this.y = true;
    
    aik ☃ = this.s.k();
    List<aig> ☃ = ☃.a();
    Random ☃ = new Random(O());
    
    cj ☃ = ☃.a(0, 0, 256, ☃, ☃);
    
    int ☃ = 8;
    int ☃ = this.s.i();
    int ☃ = 8;
    if (☃ != null)
    {
      ☃ = ☃.p();
      ☃ = ☃.r();
    }
    else
    {
      a.warn("Unable to find spawn biome");
    }
    int ☃ = 0;
    while (!this.s.a(☃, ☃))
    {
      ☃ += ☃.nextInt(64) - ☃.nextInt(64);
      ☃ += ☃.nextInt(64) - ☃.nextInt(64);
      ☃++;
      if (☃ == 1000) {
        break;
      }
    }
    this.x.a(new cj(☃, ☃, ☃));
    this.y = false;
    if (☃.c()) {
      o();
    }
  }
  
  protected void o()
  {
    att ☃ = new att();
    for (int ☃ = 0; ☃ < 10; ☃++)
    {
      int ☃ = this.x.b() + this.r.nextInt(6) - this.r.nextInt(6);
      int ☃ = this.x.d() + this.r.nextInt(6) - this.r.nextInt(6);
      
      cj ☃ = q(new cj(☃, 0, ☃)).a();
      if (☃.b(this, this.r, ☃)) {
        break;
      }
    }
  }
  
  public cj p()
  {
    return this.s.h();
  }
  
  public void a(boolean ☃, op ☃)
    throws ahu
  {
    lo ☃ = r();
    if (!☃.e()) {
      return;
    }
    if (☃ != null) {
      ☃.a("Saving level");
    }
    a();
    if (☃ != null) {
      ☃.c("Saving chunks");
    }
    ☃.a(☃);
    
    List<ase> ☃ = Lists.newArrayList(☃.a());
    for (ase ☃ : ☃) {
      if (☃ != null) {
        if (!this.K.a(☃.b, ☃.c)) {
          ☃.a(☃.b, ☃.c);
        }
      }
    }
  }
  
  public void q()
  {
    lo ☃ = r();
    if (!☃.e()) {
      return;
    }
    ☃.c();
  }
  
  protected void a()
    throws ahu
  {
    N();
    for (lp ☃ : this.I.d) {
      if ((☃ instanceof ll)) {
        ((ll)☃).c();
      }
    }
    this.x.a(aj().h());
    this.x.d(aj().f());
    this.x.c(aj().g());
    this.x.e(aj().m());
    this.x.f(aj().n());
    this.x.j(aj().q());
    this.x.k(aj().p());
    
    this.x.b(aj().j());
    this.x.e(aj().i());
    
    this.w.a(this.x, this.I.al().t());
    this.z.a();
  }
  
  public boolean a(rr ☃)
  {
    if (i(☃)) {
      return super.a(☃);
    }
    return false;
  }
  
  public void a(Collection<rr> ☃)
  {
    ArrayList<rr> ☃ = Lists.newArrayList(☃);
    for (rr ☃ : ☃) {
      if (i(☃))
      {
        this.e.add(☃);
        b(☃);
      }
    }
  }
  
  private boolean i(rr ☃)
  {
    if (☃.F)
    {
      a.warn("Tried to add entity " + rt.b(☃) + " but it was marked as removed already");
      return false;
    }
    UUID ☃ = ☃.bc();
    if (this.N.containsKey(☃))
    {
      rr ☃ = (rr)this.N.get(☃);
      if (this.f.contains(☃))
      {
        this.f.remove(☃);
      }
      else
      {
        if (!(☃ instanceof zj))
        {
          a.warn("Keeping entity " + rt.b(☃) + " that already exists with UUID " + ☃.toString());
          return false;
        }
        a.warn("Force-added player with duplicate UUID " + ☃.toString());
      }
      f(☃);
    }
    return true;
  }
  
  protected void b(rr ☃)
  {
    super.b(☃);
    this.k.a(☃.O(), ☃);
    this.N.put(☃.bc(), ☃);
    rr[] ☃ = ☃.aR();
    if (☃ != null) {
      for (int ☃ = 0; ☃ < ☃.length; ☃++) {
        this.k.a(☃[☃].O(), ☃[☃]);
      }
    }
  }
  
  protected void c(rr ☃)
  {
    super.c(☃);
    this.k.d(☃.O());
    this.N.remove(☃.bc());
    rr[] ☃ = ☃.aR();
    if (☃ != null) {
      for (int ☃ = 0; ☃ < ☃.length; ☃++) {
        this.k.d(☃[☃].O());
      }
    }
  }
  
  public boolean d(rr ☃)
  {
    if (super.d(☃))
    {
      this.I.al().a(null, ☃.p, ☃.q, ☃.r, 512.0D, this.s.p().a(), new fl(☃));
      return true;
    }
    return false;
  }
  
  public void a(rr ☃, byte ☃)
  {
    v().b(☃, new gk(☃, ☃));
  }
  
  public lo r()
  {
    return (lo)super.z();
  }
  
  public ahp a(rr ☃, double ☃, double ☃, double ☃, float ☃, boolean ☃, boolean ☃)
  {
    ahp ☃ = new ahp(this, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    ☃.a();
    ☃.a(false);
    if (!☃) {
      ☃.d();
    }
    for (zj ☃ : this.i) {
      if (☃.e(☃, ☃, ☃) < 4096.0D) {
        ((lr)☃).a.a(new gl(☃, ☃, ☃, ☃, ☃.e(), (bbj)☃.b().get(☃)));
      }
    }
    return ☃;
  }
  
  public void c(cj ☃, ajt ☃, int ☃, int ☃)
  {
    ahl ☃ = new ahl(☃, ☃, ☃, ☃);
    for (ahl ☃ : this.S[this.T]) {
      if (☃.equals(☃)) {
        return;
      }
    }
    this.S[this.T].add(☃);
  }
  
  private void ao()
  {
    while (!this.S[this.T].isEmpty())
    {
      int ☃ = this.T;
      this.T ^= 0x1;
      for (ahl ☃ : this.S[☃]) {
        if (a(☃)) {
          this.I.al().a(null, ☃.a().p(), ☃.a().q(), ☃.a().r(), 64.0D, this.s.p().a(), new ft(☃.a(), ☃.d(), ☃.b(), ☃.c()));
        }
      }
      this.S[☃].clear();
    }
  }
  
  private boolean a(ahl ☃)
  {
    arc ☃ = o(☃.a());
    if (☃.t() == ☃.d()) {
      return ☃.t().a(this, ☃.a(), ☃, ☃.b(), ☃.c());
    }
    return false;
  }
  
  public void s()
  {
    this.w.a();
  }
  
  protected void t()
  {
    boolean ☃ = W();
    super.t();
    if (this.n != this.o) {
      this.I.al().a(new gn(7, this.o), this.s.p().a());
    }
    if (this.p != this.q) {
      this.I.al().a(new gn(8, this.q), this.s.p().a());
    }
    if (☃ != W())
    {
      if (☃) {
        this.I.al().a(new gn(2, 0.0F));
      } else {
        this.I.al().a(new gn(1, 0.0F));
      }
      this.I.al().a(new gn(7, this.o));
      this.I.al().a(new gn(8, this.q));
    }
  }
  
  public MinecraftServer u()
  {
    return this.I;
  }
  
  public lm v()
  {
    return this.J;
  }
  
  public lv w()
  {
    return this.K;
  }
  
  public aib x()
  {
    return this.Q;
  }
  
  public awm y()
  {
    return this.w.h();
  }
  
  public void a(cy ☃, double ☃, double ☃, double ☃, int ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
  {
    a(☃, false, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public void a(cy ☃, boolean ☃, double ☃, double ☃, double ☃, int ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
  {
    gr ☃ = new gr(☃, ☃, (float)☃, (float)☃, (float)☃, (float)☃, (float)☃, (float)☃, (float)☃, ☃, ☃);
    for (int ☃ = 0; ☃ < this.i.size(); ☃++)
    {
      lr ☃ = (lr)this.i.get(☃);
      
      a(☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
  
  public void a(lr ☃, cy ☃, boolean ☃, double ☃, double ☃, double ☃, int ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
  {
    ff<?> ☃ = new gr(☃, ☃, (float)☃, (float)☃, (float)☃, (float)☃, (float)☃, (float)☃, (float)☃, ☃, ☃);
    
    a(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  private void a(lr ☃, boolean ☃, double ☃, double ☃, double ☃, ff<?> ☃)
  {
    cj ☃ = ☃.c();
    double ☃ = ☃.e(☃, ☃, ☃);
    if ((☃ <= 1024.0D) || ((☃) && (☃ <= 262144.0D))) {
      ☃.a.a(☃);
    }
  }
  
  public rr a(UUID ☃)
  {
    return (rr)this.N.get(☃);
  }
  
  public ListenableFuture<Object> a(Runnable ☃)
  {
    return this.I.a(☃);
  }
  
  public boolean aE()
  {
    return this.I.aE();
  }
  
  static class a
    extends ArrayList<ahl>
  {}
}
