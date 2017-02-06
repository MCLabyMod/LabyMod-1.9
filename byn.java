import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class byn
  extends MinecraftServer
{
  private static final Logger k = ;
  private final bcf l;
  private final ahw m;
  private boolean n;
  private boolean o;
  private byq p;
  
  public byn(bcf p_i46523_1_, String p_i46523_2_, String p_i46523_3_, ahw p_i46523_4_, YggdrasilAuthenticationService authServiceIn, MinecraftSessionService sessionServiceIn, GameProfileRepository profileRepoIn, mi profileCacheIn)
  {
    super(new File(p_i46523_1_.w, "saves"), p_i46523_1_.M(), p_i46523_1_.aj(), authServiceIn, sessionServiceIn, profileRepoIn, profileCacheIn);
    i(p_i46523_1_.K().c());
    j(p_i46523_2_);
    k(p_i46523_3_);
    b(p_i46523_1_.u());
    c(p_i46523_4_.c());
    c(256);
    a(new bym(this));
    this.l = p_i46523_1_;
    this.m = (V() ? lj.a : p_i46523_4_);
  }
  
  protected bc i()
  {
    return new byo(this);
  }
  
  protected void a(String saveName, String worldNameIn, long seed, ahy type, String worldNameIn2)
  {
    a(saveName);
    
    azi isavehandler = W().a(saveName, true);
    azh worldinfo = isavehandler.d();
    if (Reflector.DimensionManager.exists())
    {
      lp overWorld = V() ? (lp)new lj(this, isavehandler, worldinfo, 0, this.c).b() : (lp)new WorldServerOF(this, isavehandler, worldinfo, 0, this.c).b();
      
      overWorld.a(this.m);
      
      Integer[] dimIds = (Integer[])Reflector.call(Reflector.DimensionManager_getStaticDimensionIDs, new Object[0]);
      Integer[] arr$ = dimIds;int len$ = arr$.length;
      for (int i$ = 0; i$ < len$; i$++)
      {
        int dim = arr$[i$].intValue();
        
        lp world = dim == 0 ? overWorld : (lp)new ll(this, isavehandler, dim, overWorld, this.c).b();
        
        world.a(new lq(this, world));
        if (!R()) {
          world.T().a(n());
        }
        if (Reflector.EventBus.exists()) {
          Reflector.postForgeBusEvent(Reflector.WorldEvent_Load_Constructor, new Object[] { world });
        }
      }
      al().a(new lp[] { overWorld });
      if (overWorld.T().x() == null) {
        a(this.l.u.an);
      }
    }
    else
    {
      this.d = new lp[3];
      this.i = new long[this.d.length][100];
      a(S(), isavehandler);
      if (worldinfo == null) {
        worldinfo = new azh(this.m, worldNameIn);
      } else {
        worldinfo.a(worldNameIn);
      }
      for (int i = 0; i < this.d.length; i++)
      {
        int j = 0;
        if (i == 1) {
          j = -1;
        }
        if (i == 2) {
          j = 1;
        }
        if (i == 0)
        {
          if (V()) {
            this.d[i] = ((lp)new lj(this, isavehandler, worldinfo, j, this.c).b());
          } else {
            this.d[i] = ((lp)new WorldServerOF(this, isavehandler, worldinfo, j, this.c).b());
          }
          this.d[i].a(this.m);
        }
        else
        {
          this.d[i] = ((lp)new ll(this, isavehandler, j, this.d[0], this.c).b());
        }
        this.d[i].a(new lq(this, this.d[i]));
      }
      al().a(this.d);
      if (this.d[0].T().x() == null) {
        a(this.l.u.an);
      }
    }
    l();
  }
  
  protected boolean j()
    throws IOException
  {
    k.info("Starting integrated minecraft server version 1.9");
    d(true);
    e(true);
    f(true);
    g(true);
    h(true);
    k.info("Generating keypair");
    a(ob.b());
    if (Reflector.FMLCommonHandler_handleServerAboutToStart.exists())
    {
      Object inst = Reflector.call(Reflector.FMLCommonHandler_instance, new Object[0]);
      if (!Reflector.callBoolean(inst, Reflector.FMLCommonHandler_handleServerAboutToStart, new Object[] { this })) {
        return false;
      }
    }
    a(S(), T(), this.m.d(), this.m.h(), this.m.j());
    l(Q() + " - " + this.d[0].T().j());
    if (Reflector.FMLCommonHandler_handleServerStarting.exists())
    {
      Object inst = Reflector.call(Reflector.FMLCommonHandler_instance, new Object[0]);
      if (Reflector.FMLCommonHandler_handleServerStarting.getReturnType() == Boolean.TYPE) {
        return Reflector.callBoolean(inst, Reflector.FMLCommonHandler_handleServerStarting, new Object[] { this });
      }
      Reflector.callVoid(inst, Reflector.FMLCommonHandler_handleServerStarting, new Object[] { this });
    }
    return true;
  }
  
  public void C()
  {
    boolean flag = this.n;
    this.n = ((bcf.z().v() != null) && (bcf.z().T()));
    if ((!flag) && (this.n))
    {
      k.info("Saving and pausing game...");
      al().j();
      a(false);
    }
    if (this.n)
    {
      synchronized (this.j)
      {
        while (!this.j.isEmpty()) {
          g.a((FutureTask)this.j.poll(), k);
        }
      }
    }
    else
    {
      super.C();
      if (this.l.u.c != al().s())
      {
        k.info("Changing view distance to {}, from {}", new Object[] { Integer.valueOf(this.l.u.c), Integer.valueOf(al().s()) });
        al().a(this.l.u.c);
      }
      if (this.l.f != null)
      {
        azh worldinfo1 = this.d[0].T();
        azh worldinfo = this.l.f.T();
        if ((!worldinfo1.y()) && (worldinfo.x() != worldinfo1.x()))
        {
          k.info("Changing difficulty to {}, from {}", new Object[] { worldinfo.x(), worldinfo1.x() });
          a(worldinfo.x());
        }
        else if ((worldinfo.y()) && (!worldinfo1.y()))
        {
          k.info("Locking difficulty to {}", new Object[] { worldinfo.x() });
          for (lp worldserver : this.d) {
            if (worldserver != null) {
              worldserver.T().e(true);
            }
          }
        }
      }
    }
  }
  
  public boolean m()
  {
    return false;
  }
  
  public ahw.a n()
  {
    return this.m.e();
  }
  
  public qk o()
  {
    if (this.l.f == null) {
      return this.l.u.an;
    }
    return this.l.f.T().x();
  }
  
  public boolean p()
  {
    return this.m.f();
  }
  
  public boolean r()
  {
    return true;
  }
  
  public boolean s()
  {
    return true;
  }
  
  public void a(boolean dontLog)
  {
    super.a(dontLog);
  }
  
  public File A()
  {
    return this.l.w;
  }
  
  public boolean aa()
  {
    return false;
  }
  
  public boolean ae()
  {
    return false;
  }
  
  protected void a(b report)
  {
    this.l.a(report);
  }
  
  public b b(b report)
  {
    report = super.b(report);
    report.g().a("Type", new Callable()
    {
      public String a()
        throws Exception
      {
        return "Integrated Server (map_client.txt)";
      }
    });
    report.g().a("Is Modded", new Callable()
    {
      public String a()
        throws Exception
      {
        String s = ClientBrandRetriever.getClientModName();
        if (!s.equals("vanilla")) {
          return "Definitely; Client brand changed to '" + s + "'";
        }
        s = byn.this.getServerModName();
        return bcf.class.getSigners() == null ? "Very likely; Jar signature invalidated" : !s.equals("vanilla") ? "Definitely; Server brand changed to '" + s + "'" : "Probably not. Jar signature remains and both client + server brands are untouched.";
      }
    });
    return report;
  }
  
  public void a(qk difficulty)
  {
    super.a(difficulty);
    if (this.l.f != null) {
      this.l.f.T().a(difficulty);
    }
  }
  
  public void a(qw playerSnooper)
  {
    super.a(playerSnooper);
    playerSnooper.a("snooper_partner", this.l.H().f());
  }
  
  public boolean Z()
  {
    return bcf.z().Z();
  }
  
  public String a(ahw.a type, boolean allowCheats)
  {
    try
    {
      int i = -1;
      try
      {
        i = oe.a();
      }
      catch (IOException var5) {}
      if (i <= 0) {
        i = 25564;
      }
      am().a((InetAddress)null, i);
      k.info("Started on " + i);
      this.o = true;
      this.p = new byq(ai(), i + "");
      this.p.start();
      al().a(type);
      al().c(allowCheats);
      this.l.h.o(allowCheats ? 4 : 0);
      return i + "";
    }
    catch (IOException var6) {}
    return null;
  }
  
  public void u()
  {
    super.u();
    if (this.p != null)
    {
      this.p.interrupt();
      this.p = null;
    }
  }
  
  public void x()
  {
    Futures.getUnchecked(a(new Runnable()
    {
      public void run()
      {
        for (lr entityplayermp : Lists.newArrayList(byn.this.al().v())) {
          byn.this.al().e(entityplayermp);
        }
      }
    }));
    super.x();
    if (this.p != null)
    {
      this.p.interrupt();
      this.p = null;
    }
  }
  
  public boolean a()
  {
    return this.o;
  }
  
  public void a(ahw.a gameMode)
  {
    super.a(gameMode);
    al().a(gameMode);
  }
  
  public boolean ah()
  {
    return true;
  }
  
  public int q()
  {
    return 4;
  }
  
  public void b()
  {
    if (aE()) {
      this.d[0].ak().a();
    } else {
      a(new Runnable()
      {
        public void run()
        {
          byn.this.b();
        }
      });
    }
  }
}
