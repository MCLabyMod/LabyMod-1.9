import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class lr
  extends zj
  implements aba
{
  private static final Logger bQ = ;
  private String bR = "en_US";
  public mb a;
  public final MinecraftServer b;
  public final ls c;
  public double d;
  public double e;
  private final List<Integer> bS = Lists.newLinkedList();
  private final no bT;
  private float bU = Float.MIN_VALUE;
  private int bV = Integer.MIN_VALUE;
  private int bW = Integer.MIN_VALUE;
  private int bX = Integer.MIN_VALUE;
  private int bY = Integer.MIN_VALUE;
  private int bZ = Integer.MIN_VALUE;
  private float ca = -1.0E8F;
  private int cb = -99999999;
  private boolean cc = true;
  private int cd = -99999999;
  private int ce = 60;
  private zj.b cf;
  private boolean cg = true;
  private long ch = System.currentTimeMillis();
  private rr ci = null;
  private boolean cj;
  private int ck;
  public boolean f;
  public int g;
  public boolean h;
  
  public lr(MinecraftServer ☃, lp ☃, GameProfile ☃, ls ☃)
  {
    super(☃, ☃);
    ☃.b = this;
    this.c = ☃;
    
    cj ☃ = ☃.R();
    if ((!☃.s.m()) && (☃.T().q() != ahw.a.d))
    {
      int ☃ = Math.max(0, ☃.a(☃));
      int ☃ = on.c(☃.aj().b(☃.p(), ☃.r()));
      if (☃ < ☃) {
        ☃ = ☃;
      }
      if (☃ <= 1) {
        ☃ = 1;
      }
      ☃ = ☃.q(☃.a(this.S.nextInt(☃ * 2 + 1) - ☃, 0, this.S.nextInt(☃ * 2 + 1) - ☃));
    }
    this.b = ☃;
    this.bT = ☃.al().a(this);
    this.P = 0.0F;
    
    a(☃, 0.0F, 0.0F);
    while ((!☃.a(this, bl()).isEmpty()) && (this.q < 255.0D)) {
      b(this.p, this.q + 1.0D, this.r);
    }
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    if (☃.b("playerGameType", 99)) {
      if (h().at()) {
        this.c.a(h().n());
      } else {
        this.c.a(ahw.a.a(☃.h("playerGameType")));
      }
    }
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    
    ☃.a("playerGameType", this.c.b().a());
    
    rr ☃ = bw();
    if (by() != null) {
      if (((☃ != this ? 1 : 0) & (☃.b(lr.class).size() == 1 ? 1 : 0)) != 0)
      {
        dn ☃ = new dn();
        dn ☃ = new dn();
        ☃.d(☃);
        
        ☃.a("Attach", by().bc());
        ☃.a("Entity", ☃);
        ☃.a("RootVehicle", ☃);
      }
    }
  }
  
  public void a(int ☃)
  {
    super.a(☃);
    this.cd = -1;
  }
  
  public void b(int ☃)
  {
    super.b(☃);
    this.cd = -1;
  }
  
  public void j_()
  {
    this.bt.a(this);
  }
  
  public void j()
  {
    super.j();
    
    this.a.a(new gy(bU(), gy.a.a));
  }
  
  public void k()
  {
    super.k();
    
    this.a.a(new gy(bU(), gy.a.b));
  }
  
  protected adp l()
  {
    return new aek(this);
  }
  
  public void m()
  {
    this.c.a();
    
    this.ce -= 1;
    if (this.W > 0) {
      this.W -= 1;
    }
    this.bt.b();
    if ((!this.l.E) && 
      (!this.bt.a(this)))
    {
      q();
      this.bt = this.bs;
    }
    while (!this.bS.isEmpty())
    {
      int ☃ = Math.min(this.bS.size(), Integer.MAX_VALUE);
      int[] ☃ = new int[☃];
      Iterator<Integer> ☃ = this.bS.iterator();
      int ☃ = 0;
      while ((☃.hasNext()) && (☃ < ☃))
      {
        ☃[(☃++)] = ((Integer)☃.next()).intValue();
        ☃.remove();
      }
      this.a.a(new hc(☃));
    }
    rr ☃ = G();
    if (☃ != this) {
      if (!☃.au())
      {
        e(this);
      }
      else
      {
        a(☃.p, ☃.q, ☃.r, ☃.v, ☃.w);
        this.b.al().d(this);
        if (aK()) {
          e(this);
        }
      }
    }
  }
  
  public void k_()
  {
    try
    {
      super.m();
      for (int ☃ = 0; ☃ < this.br.u_(); ☃++)
      {
        adq ☃ = this.br.a(☃);
        if ((☃ != null) && 
          (☃.b().f()))
        {
          ff<?> ☃ = ((acp)☃.b()).a(☃, this.l, this);
          if (☃ != null) {
            this.a.a(☃);
          }
        }
      }
      if ((bQ() == this.ca) && (this.cb == this.bu.a()))
      {
        if ((this.bu.e() == 0.0F) == this.cc) {}
      }
      else
      {
        this.a.a(new hq(bQ(), this.bu.a(), this.bu.e()));
        this.ca = bQ();
        this.cb = this.bu.a();
        this.cc = (this.bu.e() == 0.0F);
      }
      if (bQ() + cp() != this.bU)
      {
        this.bU = (bQ() + cp());
        a(bbv.g, on.f(this.bU));
      }
      if (this.bu.a() != this.bV)
      {
        this.bV = this.bu.a();
        a(bbv.h, on.f(this.bV));
      }
      if (aP() != this.bW)
      {
        this.bW = aP();
        a(bbv.i, on.f(this.bW));
      }
      if (bT() != this.bX)
      {
        this.bX = bT();
        a(bbv.j, on.f(this.bX));
      }
      if (this.bL != this.bZ)
      {
        this.bZ = this.bL;
        a(bbv.k, on.f(this.bZ));
      }
      if (this.bK != this.bY)
      {
        this.bY = this.bK;
        a(bbv.l, on.f(this.bY));
      }
      if (this.bL != this.cd)
      {
        this.cd = this.bL;
        this.a.a(new hp(this.bM, this.bL, this.bK));
      }
      if ((this.T % 20 * 5 == 0) && (!E().a(nk.L))) {
        o();
      }
    }
    catch (Throwable ☃)
    {
      b ☃ = b.a(☃, "Ticking player");
      c ☃ = ☃.a("Player being ticked");
      
      a(☃);
      
      throw new e(☃);
    }
  }
  
  private void a(bbv ☃, int ☃)
  {
    Collection<bbl> ☃ = cW().a(☃);
    for (bbl ☃ : ☃)
    {
      bbn ☃ = cW().c(h_(), ☃);
      ☃.c(☃);
    }
  }
  
  protected void o()
  {
    aig ☃ = this.l.b(new cj(on.c(this.p), 0, on.c(this.r)));
    String ☃ = ☃.l();
    nv ☃ = (nv)E().b(nk.L);
    if (☃ == null) {
      ☃ = (nv)E().a(nk.L, new nv());
    }
    ☃.add(☃);
    if ((E().b(nk.L)) && (☃.size() >= aig.i.size()))
    {
      Set<aig> ☃ = Sets.newHashSet(aig.i);
      for (String ☃ : ☃)
      {
        Iterator<aig> ☃ = ☃.iterator();
        while (☃.hasNext())
        {
          aig ☃ = (aig)☃.next();
          if (☃.l().equals(☃)) {
            ☃.remove();
          }
        }
        if (☃.isEmpty()) {
          break;
        }
      }
      if (☃.isEmpty()) {
        b(nk.L);
      }
    }
  }
  
  public void a(rc ☃)
  {
    boolean ☃ = this.l.U().b("showDeathMessages");
    this.a.a(new gy(bU(), gy.a.c, ☃));
    if (☃)
    {
      bbr ☃ = aO();
      if ((☃ == null) || (☃.j() == bbr.b.a)) {
        this.b.al().a(bU().b());
      } else if (☃.j() == bbr.b.c) {
        this.b.al().a(this, bU().b());
      } else if (☃.j() == bbr.b.d) {
        this.b.al().b(this, bU().b());
      }
    }
    if ((!this.l.U().b("keepInventory")) && (!y())) {
      this.br.n();
    }
    Collection<bbl> ☃ = this.l.ad().a(bbv.d);
    for (bbl ☃ : ☃)
    {
      bbn ☃ = cW().c(h_(), ☃);
      ☃.a();
    }
    sa ☃ = bV();
    if (☃ != null)
    {
      rt.a ☃ = (rt.a)rt.a.get(rt.b(☃));
      if (☃ != null) {
        b(☃.e);
      }
      ☃.b(this, this.ba);
    }
    b(nt.A);
    a(nt.h);
    
    bU().g();
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (b(☃)) {
      return false;
    }
    boolean ☃ = (this.b.aa()) && (dc()) && ("fall".equals(☃.r));
    if ((!☃) && (this.ce > 0) && (☃ != rc.k)) {
      return false;
    }
    if ((☃ instanceof rd))
    {
      rr ☃ = ☃.j();
      if (((☃ instanceof zj)) && (!a((zj)☃))) {
        return false;
      }
      if ((☃ instanceof zm))
      {
        zm ☃ = (zm)☃;
        if (((☃.e instanceof zj)) && (!a((zj)☃.e))) {
          return false;
        }
      }
    }
    return super.a(☃, ☃);
  }
  
  public boolean a(zj ☃)
  {
    if (!dc()) {
      return false;
    }
    return super.a(☃);
  }
  
  private boolean dc()
  {
    return this.b.af();
  }
  
  public rr c(int ☃)
  {
    this.cj = true;
    if ((this.am == 1) && (☃ == 1))
    {
      this.l.e(this);
      if (!this.h)
      {
        this.h = true;
        if (a(nk.D))
        {
          this.a.a(new gn(4, 0.0F));
        }
        else
        {
          b(nk.D);
          this.a.a(new gn(4, 1.0F));
        }
      }
      return this;
    }
    if ((this.am == 0) && (☃ == 1))
    {
      b(nk.C);
      ☃ = 1;
    }
    else
    {
      b(nk.y);
    }
    this.b.al().a(this, ☃);
    this.a.a(new gq(1032, cj.a, 0, false));
    
    this.cd = -1;
    this.ca = -1.0F;
    this.cb = -1;
    return this;
  }
  
  public boolean a(lr ☃)
  {
    if (☃.y()) {
      return G() == this;
    }
    if (y()) {
      return false;
    }
    return super.a(☃);
  }
  
  private void a(apv ☃)
  {
    if (☃ != null)
    {
      ff<?> ☃ = ☃.D_();
      if (☃ != null) {
        this.a.a(☃);
      }
    }
  }
  
  public void a(rr ☃, int ☃)
  {
    super.a(☃, ☃);
    this.bt.b();
  }
  
  public zj.a a(cj ☃)
  {
    zj.a ☃ = super.a(☃);
    if (☃ == zj.a.a)
    {
      b(nt.ad);
      ff<?> ☃ = new hb(this, ☃);
      x().v().a(this, ☃);
      this.a.a(this.p, this.q, this.r, this.v, this.w);
      this.a.a(☃);
    }
    return ☃;
  }
  
  public void a(boolean ☃, boolean ☃, boolean ☃)
  {
    if (cl()) {
      x().v().b(this, new fp(this, 2));
    }
    super.a(☃, ☃, ☃);
    if (this.a != null) {
      this.a.a(this.p, this.q, this.r, this.v, this.w);
    }
  }
  
  public boolean a(rr ☃, boolean ☃)
  {
    rr ☃ = by();
    if (!super.a(☃, ☃)) {
      return false;
    }
    rr ☃ = by();
    if ((☃ != ☃) && (this.a != null)) {
      this.a.a(this.p, this.q, this.r, this.v, this.w);
    }
    return true;
  }
  
  public void p()
  {
    rr ☃ = by();
    super.p();
    
    rr ☃ = by();
    if ((☃ != ☃) && (this.a != null)) {
      this.a.a(this.p, this.q, this.r, this.v, this.w);
    }
  }
  
  public boolean b(rc ☃)
  {
    return (super.b(☃)) || (K());
  }
  
  protected void a(double ☃, boolean ☃, arc ☃, cj ☃) {}
  
  protected void b(cj ☃)
  {
    if (!y()) {
      super.b(☃);
    }
  }
  
  public void a(double ☃, boolean ☃)
  {
    int ☃ = on.c(this.p);
    int ☃ = on.c(this.q - 0.20000000298023224D);
    int ☃ = on.c(this.r);
    
    cj ☃ = new cj(☃, ☃, ☃);
    arc ☃ = this.l.o(☃);
    if (☃.a() == axe.a)
    {
      cj ☃ = ☃.b();
      arc ☃ = this.l.o(☃);
      ajt ☃ = ☃.t();
      if (((☃ instanceof alj)) || ((☃ instanceof apk)) || ((☃ instanceof alk)))
      {
        ☃ = ☃;
        ☃ = ☃;
      }
    }
    super.a(☃, ☃, ☃, ☃);
  }
  
  public void a(aqn ☃)
  {
    ☃.a(this);
    this.a.a(new gw(☃.v()));
  }
  
  private void dd()
  {
    this.ck = (this.ck % 100 + 1);
  }
  
  public void a(qn ☃)
  {
    if (((☃ instanceof qu)) && (((qu)☃).b() != null) && (y()))
    {
      a(new fb("container.spectatorCantOpen", new Object[0]).a(new ez().a(a.m)));
      return;
    }
    dd();
    this.a.a(new gc(this.ck, ☃.k(), ☃.i_()));
    this.bt = ☃.a(this.br, this);
    this.bt.d = this.ck;
    this.bt.a(this);
  }
  
  public void a(qg ☃)
  {
    if (((☃ instanceof qu)) && (((qu)☃).b() != null) && (y()))
    {
      a(new fb("container.spectatorCantOpen", new Object[0]).a(new ez().a(a.m)));
      return;
    }
    if (this.bt != this.bs) {
      q();
    }
    if ((☃ instanceof qs))
    {
      qs ☃ = (qs)☃;
      if ((☃.x_()) && (!a(☃.y_())) && (!y()))
      {
        this.a.a(new fy(new fb("container.isLocked", new Object[] { ☃.i_() }), (byte)2));
        this.a.a(new hz(ng.W, nh.e, this.p, this.q, this.r, 1.0F, 1.0F));
        return;
      }
    }
    dd();
    if ((☃ instanceof qn))
    {
      this.a.a(new gc(this.ck, ((qn)☃).k(), ☃.i_(), ☃.u_()));
      this.bt = ((qn)☃).a(this.br, this);
    }
    else
    {
      this.a.a(new gc(this.ck, "minecraft:container", ☃.i_(), ☃.u_()));
      this.bt = new abb(this.br, ☃, this);
    }
    this.bt.d = this.ck;
    this.bt.a(this);
  }
  
  public void a(ahf ☃)
  {
    dd();
    this.bt = new abo(this.br, ☃, this.l);
    this.bt.d = this.ck;
    this.bt.a(this);
    qg ☃ = ((abo)this.bt).e();
    
    eu ☃ = ☃.i_();
    this.a.a(new gc(this.ck, "minecraft:villager", ☃, ☃.u_()));
    
    ahh ☃ = ☃.b_(this);
    if (☃ != null)
    {
      em ☃ = new em(Unpooled.buffer());
      
      ☃.writeInt(this.ck);
      ☃.a(☃);
      
      this.a.a(new gh("MC|TrList", ☃));
    }
  }
  
  public void a(wk ☃, qg ☃)
  {
    if (this.bt != this.bs) {
      q();
    }
    dd();
    this.a.a(new gc(this.ck, "EntityHorse", ☃.i_(), ☃.u_(), ☃.O()));
    this.bt = new abk(this.br, ☃, ☃, this);
    this.bt.d = this.ck;
    this.bt.a(this);
  }
  
  public void a(adq ☃, qm ☃)
  {
    ado ☃ = ☃.b();
    if (☃ == ads.bX)
    {
      em ☃ = new em(Unpooled.buffer());
      ☃.a(☃);
      this.a.a(new gh("MC|BOpen", ☃));
    }
  }
  
  public void a(apy ☃)
  {
    if (a(2, ""))
    {
      ☃.d(true);
      a(☃);
    }
  }
  
  public void a(aau ☃, int ☃, adq ☃)
  {
    if ((☃.a(☃) instanceof abs)) {
      return;
    }
    if (this.f) {
      return;
    }
    this.a.a(new gf(☃.d, ☃, ☃));
  }
  
  public void a(aau ☃)
  {
    a(☃, ☃.a());
  }
  
  public void a(aau ☃, List<adq> ☃)
  {
    this.a.a(new gd(☃.d, ☃));
    this.a.a(new gf(-1, -1, this.br.o()));
  }
  
  public void a(aau ☃, int ☃, int ☃)
  {
    this.a.a(new ge(☃.d, ☃, ☃));
  }
  
  public void a(aau ☃, qg ☃)
  {
    for (int ☃ = 0; ☃ < ☃.g(); ☃++) {
      this.a.a(new ge(☃.d, ☃, ☃.c_(☃)));
    }
  }
  
  public void q()
  {
    this.a.a(new gb(this.bt.d));
    s();
  }
  
  public void r()
  {
    if (this.f) {
      return;
    }
    this.a.a(new gf(-1, -1, this.br.o()));
  }
  
  public void s()
  {
    this.bt.b(this);
    this.bt = this.bs;
  }
  
  public void a(float ☃, float ☃, boolean ☃, boolean ☃)
  {
    if (aI())
    {
      if ((☃ >= -1.0F) && (☃ <= 1.0F)) {
        this.bd = ☃;
      }
      if ((☃ >= -1.0F) && (☃ <= 1.0F)) {
        this.be = ☃;
      }
      this.bc = ☃;
      d(☃);
    }
  }
  
  public boolean a(nj ☃)
  {
    return this.bT.a(☃);
  }
  
  public void a(np ☃, int ☃)
  {
    if (☃ == null) {
      return;
    }
    this.bT.b(this, ☃, ☃);
    for (bbl ☃ : cW().a(☃.k())) {
      cW().c(h_(), ☃).a(☃);
    }
    if (this.bT.e()) {
      this.bT.a(this);
    }
  }
  
  public void a(np ☃)
  {
    if (☃ == null) {
      return;
    }
    this.bT.a(this, ☃, 0);
    for (bbl ☃ : cW().a(☃.k())) {
      cW().c(h_(), ☃).c(0);
    }
    if (this.bT.e()) {
      this.bT.a(this);
    }
  }
  
  public void t()
  {
    az();
    if (this.bF) {
      a(true, false, false);
    }
  }
  
  public void u()
  {
    this.ca = -1.0E8F;
  }
  
  public void b(eu ☃)
  {
    this.a.a(new fy(☃));
  }
  
  protected void v()
  {
    if ((this.bm != null) && (cs()))
    {
      this.a.a(new gk(this, (byte)9));
      super.v();
    }
  }
  
  public void a(zj ☃, boolean ☃)
  {
    super.a(☃, ☃);
    this.cd = -1;
    this.ca = -1.0F;
    this.cb = -1;
    this.bS.addAll(((lr)☃).bS);
  }
  
  protected void a(rl ☃)
  {
    super.a(☃);
    this.a.a(new ie(O(), ☃));
  }
  
  protected void a(rl ☃, boolean ☃)
  {
    super.a(☃, ☃);
    this.a.a(new ie(O(), ☃));
  }
  
  protected void b(rl ☃)
  {
    super.b(☃);
    this.a.a(new hd(O(), ☃.a()));
  }
  
  public void a(double ☃, double ☃, double ☃)
  {
    this.a.a(☃, ☃, ☃, this.v, this.w);
  }
  
  public void a(rr ☃)
  {
    x().v().b(this, new fp(☃, 4));
  }
  
  public void b(rr ☃)
  {
    x().v().b(this, new fp(☃, 5));
  }
  
  public void w()
  {
    if (this.a == null) {
      return;
    }
    this.a.a(new gx(this.bJ));
    F();
  }
  
  public lp x()
  {
    return (lp)this.l;
  }
  
  public void a(ahw.a ☃)
  {
    this.c.a(☃);
    this.a.a(new gn(3, ☃.a()));
    if (☃ == ahw.a.e) {
      p();
    } else {
      e(this);
    }
    w();
    cq();
  }
  
  public boolean y()
  {
    return this.c.b() == ahw.a.e;
  }
  
  public boolean l_()
  {
    return this.c.b() == ahw.a.c;
  }
  
  public void a(eu ☃)
  {
    this.a.a(new fy(☃));
  }
  
  public boolean a(int ☃, String ☃)
  {
    if (("seed".equals(☃)) && (!this.b.aa())) {
      return true;
    }
    if (("tell".equals(☃)) || ("help".equals(☃)) || ("me".equals(☃)) || ("trigger".equals(☃))) {
      return true;
    }
    if (this.b.al().h(cK()))
    {
      mo ☃ = (mo)this.b.al().m().b(cK());
      if (☃ != null) {
        return ☃.a() >= ☃;
      }
      return this.b.q() >= ☃;
    }
    return false;
  }
  
  public String A()
  {
    String ☃ = this.a.a.b().toString();
    ☃ = ☃.substring(☃.indexOf("/") + 1);
    ☃ = ☃.substring(0, ☃.indexOf(":"));
    return ☃;
  }
  
  public void a(il ☃)
  {
    this.bR = ☃.a();
    
    this.cf = ☃.c();
    this.cg = ☃.d();
    
    R().b(bp, Byte.valueOf((byte)☃.e()));
    R().b(bq, Byte.valueOf((byte)(☃.f() == rz.a ? 0 : 1)));
  }
  
  public zj.b C()
  {
    return this.cf;
  }
  
  public void a(String ☃, String ☃)
  {
    this.a.a(new he(☃, ☃));
  }
  
  public cj c()
  {
    return new cj(this.p, this.q + 0.5D, this.r);
  }
  
  public void D()
  {
    this.ch = MinecraftServer.av();
  }
  
  public no E()
  {
    return this.bT;
  }
  
  public void c(rr ☃)
  {
    if ((☃ instanceof zj)) {
      this.a.a(new hc(new int[] { ☃.O() }));
    } else {
      this.bS.add(Integer.valueOf(☃.O()));
    }
  }
  
  public void d(rr ☃)
  {
    this.bS.remove(Integer.valueOf(☃.O()));
  }
  
  protected void F()
  {
    if (y())
    {
      bM();
      g(true);
    }
    else
    {
      super.F();
    }
    x().v().a(this);
  }
  
  public rr G()
  {
    return this.ci == null ? this : this.ci;
  }
  
  public void e(rr ☃)
  {
    rr ☃ = G();
    this.ci = (☃ == null ? this : ☃);
    if (☃ != this.ci)
    {
      this.a.a(new hi(this.ci));
      a(this.ci.p, this.ci.q, this.ci.r);
    }
  }
  
  protected void H()
  {
    if ((this.aj > 0) && (!this.cj)) {
      this.aj -= 1;
    }
  }
  
  public void f(rr ☃)
  {
    if (this.c.b() == ahw.a.e) {
      e(☃);
    } else {
      super.f(☃);
    }
  }
  
  public long I()
  {
    return this.ch;
  }
  
  public eu J()
  {
    return null;
  }
  
  public void a(qm ☃)
  {
    super.a(☃);
    cZ();
  }
  
  public boolean K()
  {
    return this.cj;
  }
  
  public void L()
  {
    this.cj = false;
  }
  
  public void M()
  {
    b(7, true);
  }
  
  public void N()
  {
    b(7, true);
    b(7, false);
  }
}
