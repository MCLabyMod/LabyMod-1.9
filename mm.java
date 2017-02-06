import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class mm
{
  public static final File a = new File("banned-players.json");
  public static final File b = new File("banned-ips.json");
  public static final File c = new File("ops.json");
  public static final File d = new File("whitelist.json");
  private static final Logger f = LogManager.getLogger();
  private static final SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
  private final MinecraftServer h;
  private final List<lr> i = Lists.newArrayList();
  private final Map<UUID, lr> j = Maps.newHashMap();
  private final mr k = new mr(a);
  private final mj l = new mj(b);
  private final mn m = new mn(c);
  private final mt n = new mt(d);
  private final Map<UUID, no> o = Maps.newHashMap();
  private azq p;
  private boolean q;
  protected int e;
  private int r;
  private ahw.a s;
  private boolean t;
  private int u;
  
  public mm(MinecraftServer ☃)
  {
    this.h = ☃;
    
    this.k.a(false);
    this.l.a(false);
    
    this.e = 8;
  }
  
  public void a(ek ☃, lr ☃)
  {
    GameProfile ☃ = ☃.cK();
    
    mi ☃ = this.h.aA();
    GameProfile ☃ = ☃.a(☃.getId());
    String ☃ = ☃ == null ? ☃.getName() : ☃.getName();
    ☃.a(☃);
    
    dn ☃ = a(☃);
    ☃.a(this.h.a(☃.am));
    ☃.c.a((lp)☃.l);
    
    String ☃ = "local";
    if (☃.b() != null) {
      ☃ = ☃.b().toString();
    }
    f.info(☃.h_() + "[" + ☃ + "] logged in with entity id " + ☃.O() + " at (" + ☃.p + ", " + ☃.q + ", " + ☃.r + ")");
    
    lp ☃ = this.h.a(☃.am);
    azh ☃ = ☃.T();
    
    cj ☃ = ☃.R();
    
    a(☃, null, ☃);
    
    mb ☃ = new mb(this.h, ☃, ☃);
    ☃.a(new gs(☃.O(), ☃.c.b(), ☃.s(), ☃.s.p().a(), ☃.ae(), p(), ☃.t(), ☃.U().b("reducedDebugInfo")));
    ☃.a(new gh("MC|Brand", new em(Unpooled.buffer()).a(c().getServerModName())));
    ☃.a(new fw(☃.x(), ☃.y()));
    ☃.a(new hv(☃));
    ☃.a(new gx(☃.bJ));
    ☃.a(new hj(☃.br.d));
    f(☃);
    
    ☃.E().d();
    ☃.E().b(☃);
    
    a((kw)☃.ad(), ☃);
    
    this.h.aC();
    fb ☃;
    fb ☃;
    if (!☃.h_().equalsIgnoreCase(☃)) {
      ☃ = new fb("multiplayer.player.joined.renamed", new Object[] { ☃.i_(), ☃ });
    } else {
      ☃ = new fb("multiplayer.player.joined", new Object[] { ☃.i_() });
    }
    ☃.b().a(a.o);
    a(☃);
    c(☃);
    
    ☃.a(☃.p, ☃.q, ☃.r, ☃.v, ☃.w);
    b(☃, ☃);
    if (!this.h.X().isEmpty()) {
      ☃.a(this.h.X(), this.h.Y());
    }
    for (rl ☃ : ☃.bO()) {
      ☃.a(new ie(☃.O(), ☃));
    }
    if (☃ != null) {
      if (☃.b("RootVehicle", 10))
      {
        dn ☃ = ☃.o("RootVehicle");
        rr ☃ = ass.a(☃.o("Entity"), ☃, true);
        if (☃ != null)
        {
          UUID ☃ = ☃.a("Attach");
          if (☃.bc().equals(☃)) {
            ☃.a(☃, true);
          } else {
            for (rr ☃ : ☃.bv()) {
              if (☃.bc().equals(☃))
              {
                ☃.a(☃, true);
                break;
              }
            }
          }
          if (!☃.aI())
          {
            f.warn("Couldn't reattach entity to player");
            ☃.f(☃);
            for (rr ☃ : ☃.bv()) {
              ☃.f(☃);
            }
          }
        }
      }
      else if (☃.b("Riding", 10))
      {
        rr ☃ = ass.a(☃.o("Riding"), ☃, true);
        if (☃ != null) {
          ☃.a(☃, true);
        }
      }
    }
    ☃.j_();
  }
  
  protected void a(kw ☃, lr ☃)
  {
    Set<bbl> ☃ = Sets.newHashSet();
    for (bbm ☃ : ☃.g()) {
      ☃.a.a(new ht(☃, 0));
    }
    for (int ☃ = 0; ☃ < 19; ☃++)
    {
      bbl ☃ = ☃.a(☃);
      if ((☃ != null) && (!☃.contains(☃)))
      {
        List<ff<?>> ☃ = ☃.d(☃);
        for (ff<?> ☃ : ☃) {
          ☃.a.a(☃);
        }
        ☃.add(☃);
      }
    }
  }
  
  public void a(lp[] ☃)
  {
    this.p = ☃[0].S().e();
    ☃[0].aj().a(new art()
    {
      public void a(arv ☃, double ☃)
      {
        mm.this.a(new hh(☃, hh.a.a));
      }
      
      public void a(arv ☃, double ☃, double ☃, long ☃)
      {
        mm.this.a(new hh(☃, hh.a.b));
      }
      
      public void a(arv ☃, double ☃, double ☃)
      {
        mm.this.a(new hh(☃, hh.a.c));
      }
      
      public void a(arv ☃, int ☃)
      {
        mm.this.a(new hh(☃, hh.a.e));
      }
      
      public void b(arv ☃, int ☃)
      {
        mm.this.a(new hh(☃, hh.a.f));
      }
      
      public void b(arv ☃, double ☃) {}
      
      public void c(arv ☃, double ☃) {}
    });
  }
  
  public void a(lr ☃, lp ☃)
  {
    lp ☃ = ☃.x();
    if (☃ != null) {
      ☃.w().b(☃);
    }
    ☃.w().a(☃);
    
    ☃.r().d((int)☃.p >> 4, (int)☃.r >> 4);
  }
  
  public int d()
  {
    return lv.b(s());
  }
  
  public dn a(lr ☃)
  {
    dn ☃ = this.h.d[0].T().h();
    dn ☃;
    if ((☃.h_().equals(this.h.Q())) && (☃ != null))
    {
      dn ☃ = this.h.aI().a(oz.b, ☃);
      ☃.f(☃);
      f.debug("loading single player");
    }
    else
    {
      ☃ = this.p.b(☃);
    }
    return ☃;
  }
  
  protected void b(lr ☃)
  {
    this.p.a(☃);
    no ☃ = (no)this.o.get(☃.bc());
    if (☃ != null) {
      ☃.b();
    }
  }
  
  public void c(lr ☃)
  {
    this.i.add(☃);
    this.j.put(☃.bc(), ☃);
    
    a(new gz(gz.a.a, new lr[] { ☃ }));
    
    lp ☃ = this.h.a(☃.am);
    for (int ☃ = 0; ☃ < this.i.size(); ☃++) {
      ☃.a.a(new gz(gz.a.a, new lr[] { (lr)this.i.get(☃) }));
    }
    ☃.a(☃);
    a(☃, null);
  }
  
  public void d(lr ☃)
  {
    ☃.x().w().c(☃);
  }
  
  public void e(lr ☃)
  {
    lp ☃ = ☃.x();
    ☃.b(nt.f);
    b(☃);
    if (☃.aI())
    {
      rr ☃ = ☃.bw();
      if (☃.b(lr.class).size() == 1)
      {
        f.debug("Removing player mount");
        ☃.p();
        ☃.f(☃);
        for (rr ☃ : ☃.bv()) {
          ☃.f(☃);
        }
        ☃.a(☃.ab, ☃.ad).e();
      }
    }
    ☃.e(☃);
    ☃.w().b(☃);
    this.i.remove(☃);
    UUID ☃ = ☃.bc();
    lr ☃ = (lr)this.j.get(☃);
    if (☃ == ☃)
    {
      this.j.remove(☃);
      this.o.remove(☃);
    }
    a(new gz(gz.a.e, new lr[] { ☃ }));
  }
  
  public String a(SocketAddress ☃, GameProfile ☃)
  {
    if (this.k.a(☃))
    {
      ms ☃ = (ms)this.k.b(☃);
      String ☃ = "You are banned from this server!\nReason: " + ☃.d();
      if (☃.c() != null) {
        ☃ = ☃ + "\nYour ban will be removed on " + g.format(☃.c());
      }
      return ☃;
    }
    if (!e(☃)) {
      return "You are not white-listed on this server!";
    }
    if (this.l.a(☃))
    {
      mk ☃ = this.l.b(☃);
      String ☃ = "Your IP address is banned from this server!\nReason: " + ☃.d();
      if (☃.c() != null) {
        ☃ = ☃ + "\nYour ban will be removed on " + g.format(☃.c());
      }
      return ☃;
    }
    if ((this.i.size() >= this.e) && (!f(☃))) {
      return "The server is full!";
    }
    return null;
  }
  
  public lr g(GameProfile ☃)
  {
    UUID ☃ = zj.a(☃);
    List<lr> ☃ = Lists.newArrayList();
    for (int ☃ = 0; ☃ < this.i.size(); ☃++)
    {
      lr ☃ = (lr)this.i.get(☃);
      if (☃.bc().equals(☃)) {
        ☃.add(☃);
      }
    }
    lr ☃ = (lr)this.j.get(☃.getId());
    if ((☃ != null) && (!☃.contains(☃))) {
      ☃.add(☃);
    }
    for (lr ☃ : ☃) {
      ☃.a.c("You logged in from another location");
    }
    ls ☃;
    ls ☃;
    if (this.h.V()) {
      ☃ = new lk(this.h.a(0));
    } else {
      ☃ = new ls(this.h.a(0));
    }
    return new lr(this.h, this.h.a(0), ☃, ☃);
  }
  
  public lr a(lr ☃, int ☃, boolean ☃)
  {
    ☃.x().v().b(☃);
    ☃.x().v().b(☃);
    ☃.x().w().b(☃);
    this.i.remove(☃);
    this.h.a(☃.am).f(☃);
    
    cj ☃ = ☃.cO();
    boolean ☃ = ☃.cP();
    
    ☃.am = ☃;
    ls ☃;
    ls ☃;
    if (this.h.V()) {
      ☃ = new lk(this.h.a(☃.am));
    } else {
      ☃ = new ls(this.h.a(☃.am));
    }
    lr ☃ = new lr(this.h, this.h.a(☃.am), ☃.cK(), ☃);
    ☃.a = ☃.a;
    ☃.a(☃, ☃);
    ☃.f(☃.O());
    ☃.v(☃);
    ☃.a(☃.cr());
    for (String ☃ : ☃.P()) {
      ☃.a(☃);
    }
    lp ☃ = this.h.a(☃.am);
    a(☃, ☃, ☃);
    if (☃ != null)
    {
      cj ☃ = zj.a(this.h.a(☃.am), ☃, ☃);
      if (☃ != null)
      {
        ☃.b(☃.p() + 0.5F, ☃.q() + 0.1F, ☃.r() + 0.5F, 0.0F, 0.0F);
        ☃.a(☃, ☃);
      }
      else
      {
        ☃.a.a(new gn(0, 0.0F));
      }
    }
    ☃.r().d((int)☃.p >> 4, (int)☃.r >> 4);
    while ((!☃.a(☃, ☃.bl()).isEmpty()) && (☃.q < 256.0D)) {
      ☃.b(☃.p, ☃.q + 1.0D, ☃.r);
    }
    ☃.a.a(new hf(☃.am, ☃.l.ae(), ☃.l.T().t(), ☃.c.b()));
    cj ☃ = ☃.R();
    ☃.a.a(☃.p, ☃.q, ☃.r, ☃.v, ☃.w);
    ☃.a.a(new hv(☃));
    ☃.a.a(new hp(☃.bM, ☃.bL, ☃.bK));
    b(☃, ☃);
    f(☃);
    
    ☃.w().a(☃);
    ☃.a(☃);
    this.i.add(☃);
    this.j.put(☃.bc(), ☃);
    
    ☃.j_();
    ☃.c(☃.bQ());
    return ☃;
  }
  
  public void f(lr ☃)
  {
    GameProfile ☃ = ☃.cK();
    int ☃ = h(☃) ? this.m.a(☃) : 0;
    ☃ = (this.h.R()) && (this.h.d[0].T().u()) ? 4 : ☃;
    ☃ = this.t ? 4 : ☃;
    b(☃, ☃);
  }
  
  public void a(lr ☃, int ☃)
  {
    int ☃ = ☃.am;
    lp ☃ = this.h.a(☃.am);
    ☃.am = ☃;
    
    lp ☃ = this.h.a(☃.am);
    
    ☃.a.a(new hf(☃.am, ☃.l.ae(), ☃.l.T().t(), ☃.c.b()));
    f(☃);
    
    ☃.f(☃);
    ☃.F = false;
    
    a(☃, ☃, ☃, ☃);
    
    a(☃, ☃);
    ☃.a.a(☃.p, ☃.q, ☃.r, ☃.v, ☃.w);
    ☃.c.a(☃);
    ☃.a.a(new gx(☃.bJ));
    b(☃, ☃);
    g(☃);
    for (rl ☃ : ☃.bO()) {
      ☃.a.a(new ie(☃.O(), ☃));
    }
  }
  
  public void a(rr ☃, int ☃, lp ☃, lp ☃)
  {
    double ☃ = ☃.p;
    double ☃ = ☃.r;
    double ☃ = 8.0D;
    float ☃ = ☃.v;
    
    ☃.C.a("moving");
    if (☃.am == -1)
    {
      ☃ = on.a(☃ / ☃, ☃.aj().b() + 16.0D, ☃.aj().d() - 16.0D);
      ☃ = on.a(☃ / ☃, ☃.aj().c() + 16.0D, ☃.aj().e() - 16.0D);
      ☃.b(☃, ☃.q, ☃, ☃.v, ☃.w);
      if (☃.au()) {
        ☃.a(☃, false);
      }
    }
    else if (☃.am == 0)
    {
      ☃ = on.a(☃ * ☃, ☃.aj().b() + 16.0D, ☃.aj().d() - 16.0D);
      ☃ = on.a(☃ * ☃, ☃.aj().c() + 16.0D, ☃.aj().e() - 16.0D);
      ☃.b(☃, ☃.q, ☃, ☃.v, ☃.w);
      if (☃.au()) {
        ☃.a(☃, false);
      }
    }
    else
    {
      cj ☃;
      cj ☃;
      if (☃ == 1) {
        ☃ = ☃.R();
      } else {
        ☃ = ☃.p();
      }
      ☃ = ☃.p();
      ☃.q = ☃.q();
      ☃ = ☃.r();
      
      ☃.b(☃, ☃.q, ☃, 90.0F, 0.0F);
      if (☃.au()) {
        ☃.a(☃, false);
      }
    }
    ☃.C.b();
    if (☃ != 1)
    {
      ☃.C.a("placing");
      
      ☃ = on.a((int)☃, -29999872, 29999872);
      ☃ = on.a((int)☃, -29999872, 29999872);
      if (☃.au())
      {
        ☃.b(☃, ☃.q, ☃, ☃.v, ☃.w);
        ☃.x().a(☃, ☃);
        ☃.a(☃);
        ☃.a(☃, false);
      }
      ☃.C.b();
    }
    ☃.a(☃);
  }
  
  public void e()
  {
    if (++this.u > 600)
    {
      a(new gz(gz.a.c, this.i));
      this.u = 0;
    }
  }
  
  public void a(ff<?> ☃)
  {
    for (int ☃ = 0; ☃ < this.i.size(); ☃++) {
      ((lr)this.i.get(☃)).a.a(☃);
    }
  }
  
  public void a(ff<?> ☃, int ☃)
  {
    for (int ☃ = 0; ☃ < this.i.size(); ☃++)
    {
      lr ☃ = (lr)this.i.get(☃);
      if (☃.am == ☃) {
        ☃.a.a(☃);
      }
    }
  }
  
  public void a(zj ☃, eu ☃)
  {
    bbr ☃ = ☃.aO();
    if (☃ == null) {
      return;
    }
    Collection<String> ☃ = ☃.d();
    for (String ☃ : ☃)
    {
      lr ☃ = a(☃);
      if ((☃ != null) && (☃ != ☃)) {
        ☃.a(☃);
      }
    }
  }
  
  public void b(zj ☃, eu ☃)
  {
    bbr ☃ = ☃.aO();
    if (☃ == null)
    {
      a(☃);
      return;
    }
    for (int ☃ = 0; ☃ < this.i.size(); ☃++)
    {
      lr ☃ = (lr)this.i.get(☃);
      if (☃.aO() != ☃) {
        ☃.a(☃);
      }
    }
  }
  
  public String b(boolean ☃)
  {
    String ☃ = "";
    List<lr> ☃ = Lists.newArrayList(this.i);
    for (int ☃ = 0; ☃ < ☃.size(); ☃++)
    {
      if (☃ > 0) {
        ☃ = ☃ + ", ";
      }
      ☃ = ☃ + ((lr)☃.get(☃)).h_();
      if (☃) {
        ☃ = ☃ + " (" + ((lr)☃.get(☃)).bc().toString() + ")";
      }
    }
    return ☃;
  }
  
  public String[] f()
  {
    String[] ☃ = new String[this.i.size()];
    for (int ☃ = 0; ☃ < this.i.size(); ☃++) {
      ☃[☃] = ((lr)this.i.get(☃)).h_();
    }
    return ☃;
  }
  
  public GameProfile[] g()
  {
    GameProfile[] ☃ = new GameProfile[this.i.size()];
    for (int ☃ = 0; ☃ < this.i.size(); ☃++) {
      ☃[☃] = ((lr)this.i.get(☃)).cK();
    }
    return ☃;
  }
  
  public mr h()
  {
    return this.k;
  }
  
  public mj i()
  {
    return this.l;
  }
  
  public void a(GameProfile ☃)
  {
    int ☃ = this.h.q();
    this.m.a(new mo(☃, this.h.q(), this.m.b(☃)));
    b(a(☃.getId()), ☃);
  }
  
  public void b(GameProfile ☃)
  {
    this.m.c(☃);
    b(a(☃.getId()), 0);
  }
  
  private void b(lr ☃, int ☃)
  {
    if ((☃ != null) && (☃.a != null))
    {
      byte ☃;
      byte ☃;
      if (☃ <= 0)
      {
        ☃ = 24;
      }
      else
      {
        byte ☃;
        if (☃ >= 4) {
          ☃ = 28;
        } else {
          ☃ = (byte)(24 + ☃);
        }
      }
      ☃.a.a(new gk(☃, ☃));
    }
  }
  
  public boolean e(GameProfile ☃)
  {
    return (!this.q) || (this.m.d(☃)) || (this.n.d(☃));
  }
  
  public boolean h(GameProfile ☃)
  {
    return (this.m.d(☃)) || ((this.h.R()) && (this.h.d[0].T().u()) && (this.h.Q().equalsIgnoreCase(☃.getName()))) || (this.t);
  }
  
  public lr a(String ☃)
  {
    for (lr ☃ : this.i) {
      if (☃.h_().equalsIgnoreCase(☃)) {
        return ☃;
      }
    }
    return null;
  }
  
  public void a(zj ☃, double ☃, double ☃, double ☃, double ☃, int ☃, ff<?> ☃)
  {
    for (int ☃ = 0; ☃ < this.i.size(); ☃++)
    {
      lr ☃ = (lr)this.i.get(☃);
      if (☃ != ☃) {
        if (☃.am == ☃)
        {
          double ☃ = ☃ - ☃.p;
          double ☃ = ☃ - ☃.q;
          double ☃ = ☃ - ☃.r;
          if (☃ * ☃ + ☃ * ☃ + ☃ * ☃ < ☃ * ☃) {
            ☃.a.a(☃);
          }
        }
      }
    }
  }
  
  public void j()
  {
    for (int ☃ = 0; ☃ < this.i.size(); ☃++) {
      b((lr)this.i.get(☃));
    }
  }
  
  public void d(GameProfile ☃)
  {
    this.n.a(new mu(☃));
  }
  
  public void c(GameProfile ☃)
  {
    this.n.c(☃);
  }
  
  public mt k()
  {
    return this.n;
  }
  
  public String[] l()
  {
    return this.n.a();
  }
  
  public mn m()
  {
    return this.m;
  }
  
  public String[] n()
  {
    return this.m.a();
  }
  
  public void a() {}
  
  public void b(lr ☃, lp ☃)
  {
    arv ☃ = this.h.d[0].aj();
    ☃.a.a(new hh(☃, hh.a.d));
    ☃.a.a(new hw(☃.P(), ☃.Q(), ☃.U().b("doDaylightCycle")));
    if (☃.W())
    {
      ☃.a.a(new gn(1, 0.0F));
      ☃.a.a(new gn(7, ☃.j(1.0F)));
      ☃.a.a(new gn(8, ☃.h(1.0F)));
    }
  }
  
  public void g(lr ☃)
  {
    ☃.a(☃.bs);
    ☃.u();
    ☃.a.a(new hj(☃.br.d));
  }
  
  public int o()
  {
    return this.i.size();
  }
  
  public int p()
  {
    return this.e;
  }
  
  public String[] q()
  {
    return this.h.d[0].S().e().f();
  }
  
  public void a(boolean ☃)
  {
    this.q = ☃;
  }
  
  public List<lr> b(String ☃)
  {
    List<lr> ☃ = Lists.newArrayList();
    for (lr ☃ : this.i) {
      if (☃.A().equals(☃)) {
        ☃.add(☃);
      }
    }
    return ☃;
  }
  
  public int s()
  {
    return this.r;
  }
  
  public MinecraftServer c()
  {
    return this.h;
  }
  
  public dn t()
  {
    return null;
  }
  
  public void a(ahw.a ☃)
  {
    this.s = ☃;
  }
  
  private void a(lr ☃, lr ☃, aht ☃)
  {
    if (☃ != null) {
      ☃.c.a(☃.c.b());
    } else if (this.s != null) {
      ☃.c.a(this.s);
    }
    ☃.c.b(☃.T().q());
  }
  
  public void c(boolean ☃)
  {
    this.t = ☃;
  }
  
  public void u()
  {
    for (int ☃ = 0; ☃ < this.i.size(); ☃++) {
      ((lr)this.i.get(☃)).a.c("Server closed");
    }
  }
  
  public void a(eu ☃, boolean ☃)
  {
    this.h.a(☃);
    byte ☃ = ☃ ? 1 : 0;
    a(new fy(☃, ☃));
  }
  
  public void a(eu ☃)
  {
    a(☃, true);
  }
  
  public no a(zj ☃)
  {
    UUID ☃ = ☃.bc();
    no ☃ = ☃ == null ? null : (no)this.o.get(☃);
    if (☃ == null)
    {
      File ☃ = new File(this.h.a(0).S().b(), "stats");
      File ☃ = new File(☃, ☃.toString() + ".json");
      if (!☃.exists())
      {
        File ☃ = new File(☃, ☃.h_() + ".json");
        if ((☃.exists()) && (☃.isFile())) {
          ☃.renameTo(☃);
        }
      }
      ☃ = new no(this.h, ☃);
      ☃.a();
      this.o.put(☃, ☃);
    }
    return ☃;
  }
  
  public void a(int ☃)
  {
    this.r = ☃;
    if (this.h.d == null) {
      return;
    }
    for (lp ☃ : this.h.d) {
      if (☃ != null)
      {
        ☃.w().a(☃);
        ☃.v().a(☃);
      }
    }
  }
  
  public List<lr> v()
  {
    return this.i;
  }
  
  public lr a(UUID ☃)
  {
    return (lr)this.j.get(☃);
  }
  
  public boolean f(GameProfile ☃)
  {
    return false;
  }
}
