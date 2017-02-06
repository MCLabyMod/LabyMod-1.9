package net.minecraft.server;

import ahr;
import aht;
import ahu;
import ahw;
import ahw.a;
import ahy;
import asv;
import asw;
import azd;
import azh;
import azi;
import azk;
import b;
import bbj;
import bc;
import c;
import cj;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import e;
import eu;
import fa;
import g;
import hw;
import i;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.security.KeyPair;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import javax.imageio.ImageIO;
import jz;
import jz.a;
import jz.c;
import ky;
import l;
import lj;
import ll;
import lm;
import lo;
import lp;
import lq;
import lr;
import m;
import ma;
import mi;
import mm;
import n.a;
import on;
import oo;
import op;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pb;
import qc;
import qk;
import qw;
import qx;
import rr;
import zj;

public abstract class MinecraftServer
  implements Runnable, m, qc, qx
{
  private static final Logger k = ;
  public static final File a = new File("usercache.json");
  private final azk l;
  private final qw m = new qw("server", this, av());
  private final File n;
  private final List<ky> o = Lists.newArrayList();
  protected final l b;
  public final oo c = new oo();
  private final ma p;
  private final jz q = new jz();
  private final Random r = new Random();
  private final pb s;
  private int u = -1;
  public lp[] d;
  private mm v;
  private boolean w = true;
  private boolean x;
  private int y;
  protected final Proxy e;
  public String f;
  public int g;
  private boolean z;
  private boolean A;
  private boolean B;
  private boolean C;
  private boolean D;
  private String E;
  private int F;
  private int G = 0;
  public final long[] h = new long[100];
  public long[][] i;
  private KeyPair H;
  private String I;
  private String J;
  private String K;
  private boolean L;
  private boolean M;
  private String N = "";
  private String O = "";
  private boolean P;
  private long Q;
  private String R;
  private boolean S;
  private boolean T;
  private final YggdrasilAuthenticationService U;
  private final MinecraftSessionService V;
  private final GameProfileRepository W;
  private final mi X;
  private long Y = 0L;
  protected final Queue<FutureTask<?>> j = Queues.newArrayDeque();
  private Thread Z;
  private long aa = av();
  private boolean ab;
  
  public MinecraftServer(File ☃, Proxy ☃, pb ☃, YggdrasilAuthenticationService ☃, MinecraftSessionService ☃, GameProfileRepository ☃, mi ☃)
  {
    this.e = ☃;
    this.U = ☃;
    this.V = ☃;
    this.W = ☃;
    this.X = ☃;
    this.n = ☃;
    this.p = new ma(this);
    this.b = i();
    this.l = new azd(☃, ☃);
    this.s = ☃;
  }
  
  protected bc i()
  {
    return new bc(this);
  }
  
  protected abstract boolean j()
    throws IOException;
  
  protected void a(String ☃)
  {
    if (W().b(☃))
    {
      k.info("Converting map!");
      b("menu.convertingLevel");
      W().a(☃, new op()
      {
        private long b = MinecraftServer.av();
        
        public void a(String ☃) {}
        
        public void b(String ☃) {}
        
        public void a(int ☃)
        {
          if (MinecraftServer.av() - this.b >= 1000L)
          {
            this.b = MinecraftServer.av();
            MinecraftServer.aJ().info("Converting... " + ☃ + "%");
          }
        }
        
        public void a() {}
        
        public void c(String ☃) {}
      });
    }
  }
  
  protected synchronized void b(String ☃)
  {
    this.R = ☃;
  }
  
  public synchronized String k()
  {
    return this.R;
  }
  
  protected void a(String ☃, String ☃, long ☃, ahy ☃, String ☃)
  {
    a(☃);
    b("menu.loadingLevel");
    
    this.d = new lp[3];
    this.i = new long[this.d.length][100];
    
    azi ☃ = this.l.a(☃, true);
    a(S(), ☃);
    
    azh ☃ = ☃.d();
    ahw ☃;
    if (☃ == null)
    {
      ahw ☃;
      ahw ☃;
      if (V())
      {
        ☃ = lj.a;
      }
      else
      {
        ☃ = new ahw(☃, n(), m(), p(), ☃);
        ☃.a(☃);
        if (this.M) {
          ☃.a();
        }
      }
      ☃ = new azh(☃, ☃);
    }
    else
    {
      ☃.a(☃);
      ☃ = new ahw(☃);
    }
    for (int ☃ = 0; ☃ < this.d.length; ☃++)
    {
      int ☃ = 0;
      if (☃ == 1) {
        ☃ = -1;
      }
      if (☃ == 2) {
        ☃ = 1;
      }
      if (☃ == 0)
      {
        if (V()) {
          this.d[☃] = ((lp)new lj(this, ☃, ☃, ☃, this.c).b());
        } else {
          this.d[☃] = ((lp)new lp(this, ☃, ☃, ☃, this.c).b());
        }
        this.d[☃].a(☃);
      }
      else
      {
        this.d[☃] = ((lp)new ll(this, ☃, ☃, this.d[0], this.c).b());
      }
      this.d[☃].a(new lq(this, this.d[☃]));
      if (!R()) {
        this.d[☃].T().a(n());
      }
    }
    this.v.a(this.d);
    
    a(o());
    
    l();
  }
  
  protected void l()
  {
    int ☃ = 16;
    int ☃ = 4;
    
    int ☃ = 192;
    int ☃ = 625;
    int ☃ = 0;
    
    b("menu.generatingTerrain");
    
    int ☃ = 0;
    k.info("Preparing start region for level " + ☃);
    lp ☃ = this.d[☃];
    cj ☃ = ☃.R();
    
    long ☃ = av();
    for (int ☃ = 65344; (☃ <= 192) && (w()); ☃ += 16) {
      for (int ☃ = 65344; (☃ <= 192) && (w()); ☃ += 16)
      {
        long ☃ = av();
        if (☃ - ☃ > 1000L)
        {
          a_("Preparing spawn area", ☃ * 100 / 625);
          ☃ = ☃;
        }
        ☃++;
        
        ☃.r().d(☃.p() + ☃ >> 4, ☃.r() + ☃ >> 4);
      }
    }
    t();
  }
  
  protected void a(String ☃, azi ☃)
  {
    File ☃ = new File(☃.b(), "resources.zip");
    if (☃.isFile()) {
      a_("level://" + ☃ + "/" + "resources.zip", "");
    }
  }
  
  public abstract boolean m();
  
  public abstract ahw.a n();
  
  public abstract qk o();
  
  public abstract boolean p();
  
  public abstract int q();
  
  public abstract boolean r();
  
  public abstract boolean s();
  
  protected void a_(String ☃, int ☃)
  {
    this.f = ☃;
    this.g = ☃;
    k.info(☃ + ": " + ☃ + "%");
  }
  
  protected void t()
  {
    this.f = null;
    this.g = 0;
  }
  
  public void a(boolean ☃)
  {
    for (lp ☃ : this.d) {
      if (☃ != null)
      {
        if (!☃) {
          k.info("Saving chunks for level '" + ☃.T().j() + "'/" + ☃.s.p().b());
        }
        try
        {
          ☃.a(true, null);
        }
        catch (ahu ☃)
        {
          k.warn(☃.getMessage());
        }
      }
    }
  }
  
  public void u()
  {
    k.info("Stopping server");
    if (am() != null) {
      am().b();
    }
    if (this.v != null)
    {
      k.info("Saving players");
      this.v.j();
      this.v.u();
    }
    if (this.d != null)
    {
      k.info("Saving worlds");
      for (int ☃ = 0; ☃ < this.d.length; ☃++) {
        if (this.d[☃] != null) {
          this.d[☃].b = false;
        }
      }
      a(false);
      for (int ☃ = 0; ☃ < this.d.length; ☃++) {
        if (this.d[☃] != null) {
          this.d[☃].s();
        }
      }
    }
    if (this.m.d()) {
      this.m.e();
    }
  }
  
  public boolean w()
  {
    return this.w;
  }
  
  public void x()
  {
    this.w = false;
  }
  
  public void run()
  {
    try
    {
      if (j())
      {
        this.aa = av();
        long ☃ = 0L;
        
        this.q.a(new fa(this.E));
        this.q.a(new jz.c("1.9", 107));
        
        a(this.q);
        while (this.w)
        {
          long ☃ = av();
          long ☃ = ☃ - this.aa;
          if ((☃ > 2000L) && (this.aa - this.Q >= 15000L))
          {
            k.warn("Can't keep up! Did the system time change, or is the server overloaded? Running {}ms behind, skipping {} tick(s)", new Object[] { Long.valueOf(☃), Long.valueOf(☃ / 50L) });
            ☃ = 2000L;
            this.Q = this.aa;
          }
          if (☃ < 0L)
          {
            k.warn("Time ran backwards! Did the system time change?");
            ☃ = 0L;
          }
          ☃ += ☃;
          this.aa = ☃;
          if (this.d[0].g())
          {
            C();
            ☃ = 0L;
          }
          else
          {
            while (☃ > 50L)
            {
              ☃ -= 50L;
              C();
            }
          }
          Thread.sleep(Math.max(1L, 50L - ☃));
          this.P = true;
        }
      }
      else
      {
        a(null);
      }
    }
    catch (Throwable ☃)
    {
      k.error("Encountered an unexpected exception", ☃);
      
      b ☃ = null;
      if ((☃ instanceof e)) {
        ☃ = b(((e)☃).a());
      } else {
        ☃ = b(new b("Exception in server tick loop", ☃));
      }
      File ☃ = new File(new File(A(), "crash-reports"), "crash-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + "-server.txt");
      if (☃.a(☃)) {
        k.error("This crash report has been saved to: " + ☃.getAbsolutePath());
      } else {
        k.error("We were unable to save this crash report to disk.");
      }
      a(☃);
    }
    finally
    {
      try
      {
        this.x = true;
        u();
      }
      catch (Throwable ☃)
      {
        k.error("Exception stopping the server", ☃);
      }
      finally
      {
        B();
      }
    }
  }
  
  public void a(jz ☃)
  {
    File ☃ = d("server-icon.png");
    if (!☃.exists()) {
      ☃ = W().b(S(), "icon.png");
    }
    if (☃.isFile())
    {
      ByteBuf ☃ = Unpooled.buffer();
      try
      {
        BufferedImage ☃ = ImageIO.read(☃);
        Validate.validState(☃.getWidth() == 64, "Must be 64 pixels wide", new Object[0]);
        Validate.validState(☃.getHeight() == 64, "Must be 64 pixels high", new Object[0]);
        ImageIO.write(☃, "PNG", new ByteBufOutputStream(☃));
        ByteBuf ☃ = Base64.encode(☃);
        ☃.a("data:image/png;base64," + ☃.toString(Charsets.UTF_8));
      }
      catch (Exception ☃)
      {
        k.error("Couldn't load server icon", ☃);
      }
      finally
      {
        ☃.release();
      }
    }
  }
  
  public boolean y()
  {
    this.ab = ((this.ab) || (z().isFile()));
    return this.ab;
  }
  
  public File z()
  {
    return W().b(S(), "icon.png");
  }
  
  public File A()
  {
    return new File(".");
  }
  
  protected void a(b ☃) {}
  
  protected void B() {}
  
  public void C()
  {
    long ☃ = System.nanoTime();
    
    this.y += 1;
    if (this.S)
    {
      this.S = false;
      this.c.a = true;
      this.c.a();
    }
    this.c.a("root");
    D();
    if (☃ - this.Y >= 5000000000L)
    {
      this.Y = ☃;
      this.q.a(new jz.a(I(), H()));
      
      GameProfile[] ☃ = new GameProfile[Math.min(H(), 12)];
      int ☃ = on.a(this.r, 0, H() - ☃.length);
      for (int ☃ = 0; ☃ < ☃.length; ☃++) {
        ☃[☃] = ((lr)this.v.v().get(☃ + ☃)).cK();
      }
      Collections.shuffle(Arrays.asList(☃));
      this.q.b().a(☃);
    }
    if (this.y % 900 == 0)
    {
      this.c.a("save");
      this.v.j();
      a(true);
      this.c.b();
    }
    this.c.a("tallying");
    this.h[(this.y % 100)] = (System.nanoTime() - ☃);
    
    this.c.b();
    
    this.c.a("snooper");
    if ((!this.m.d()) && (this.y > 100)) {
      this.m.a();
    }
    if (this.y % 6000 == 0) {
      this.m.b();
    }
    this.c.b();
    
    this.c.b();
  }
  
  public void D()
  {
    this.c.a("jobs");
    synchronized (this.j)
    {
      while (!this.j.isEmpty()) {
        g.a((FutureTask)this.j.poll(), k);
      }
    }
    this.c.c("levels");
    for (int ☃ = 0; ☃ < this.d.length; ☃++)
    {
      long ☃ = System.nanoTime();
      if ((☃ == 0) || (E()))
      {
        lp ☃ = this.d[☃];
        this.c.a(☃.T().j());
        if (this.y % 20 == 0)
        {
          this.c.a("timeSync");
          this.v.a(new hw(☃.P(), ☃.Q(), ☃.U().b("doDaylightCycle")), ☃.s.p().a());
          this.c.b();
        }
        this.c.a("tick");
        try
        {
          ☃.d();
        }
        catch (Throwable ☃)
        {
          b ☃ = b.a(☃, "Exception ticking world");
          ☃.a(☃);
          throw new e(☃);
        }
        try
        {
          ☃.k();
        }
        catch (Throwable ☃)
        {
          b ☃ = b.a(☃, "Exception ticking world entities");
          ☃.a(☃);
          throw new e(☃);
        }
        this.c.b();
        this.c.a("tracker");
        ☃.v().a();
        this.c.b();
        
        this.c.b();
      }
      this.i[☃][(this.y % 100)] = (System.nanoTime() - ☃);
    }
    this.c.c("connection");
    am().c();
    this.c.c("players");
    this.v.e();
    
    this.c.c("tickables");
    for (int ☃ = 0; ☃ < this.o.size(); ☃++) {
      ((ky)this.o.get(☃)).c();
    }
    this.c.b();
  }
  
  public boolean E()
  {
    return true;
  }
  
  public void F()
  {
    this.Z = new Thread(this, "Server thread");
    this.Z.start();
  }
  
  public File d(String ☃)
  {
    return new File(A(), ☃);
  }
  
  public void f(String ☃)
  {
    k.warn(☃);
  }
  
  public lp a(int ☃)
  {
    if (☃ == -1) {
      return this.d[1];
    }
    if (☃ == 1) {
      return this.d[2];
    }
    return this.d[0];
  }
  
  public String G()
  {
    return "1.9";
  }
  
  public int H()
  {
    return this.v.o();
  }
  
  public int I()
  {
    return this.v.p();
  }
  
  public String[] J()
  {
    return this.v.f();
  }
  
  public GameProfile[] K()
  {
    return this.v.g();
  }
  
  public String getServerModName()
  {
    return "vanilla";
  }
  
  public b b(b ☃)
  {
    ☃.g().a("Profiler Position", new Callable()
    {
      public String a()
        throws Exception
      {
        return MinecraftServer.this.c.a ? MinecraftServer.this.c.c() : "N/A (disabled)";
      }
    });
    if (this.v != null) {
      ☃.g().a("Player Count", new Callable()
      {
        public String a()
        {
          return MinecraftServer.a(MinecraftServer.this).o() + " / " + MinecraftServer.a(MinecraftServer.this).p() + "; " + MinecraftServer.a(MinecraftServer.this).v();
        }
      });
    }
    return ☃;
  }
  
  public List<String> a(m ☃, String ☃, cj ☃, boolean ☃)
  {
    List<String> ☃ = Lists.newArrayList();
    
    boolean ☃ = ☃.startsWith("/");
    if (☃) {
      ☃ = ☃.substring(1);
    }
    if ((☃) || (☃))
    {
      boolean ☃ = !☃.contains(" ");
      
      List<String> ☃ = this.b.a(☃, ☃, ☃);
      if (!☃.isEmpty()) {
        for (String ☃ : ☃) {
          if (☃) {
            ☃.add("/" + ☃);
          } else {
            ☃.add(☃);
          }
        }
      }
      return ☃;
    }
    String[] ☃ = ☃.split(" ", -1);
    String ☃ = ☃[(☃.length - 1)];
    for (String ☃ : this.v.f()) {
      if (i.a(☃, ☃)) {
        ☃.add(☃);
      }
    }
    return ☃;
  }
  
  public boolean M()
  {
    return this.n != null;
  }
  
  public String h_()
  {
    return "Server";
  }
  
  public void a(eu ☃)
  {
    k.info(☃.c());
  }
  
  public boolean a(int ☃, String ☃)
  {
    return true;
  }
  
  public l N()
  {
    return this.b;
  }
  
  public KeyPair O()
  {
    return this.H;
  }
  
  public String Q()
  {
    return this.I;
  }
  
  public void i(String ☃)
  {
    this.I = ☃;
  }
  
  public boolean R()
  {
    return this.I != null;
  }
  
  public String S()
  {
    return this.J;
  }
  
  public void j(String ☃)
  {
    this.J = ☃;
  }
  
  public void k(String ☃)
  {
    this.K = ☃;
  }
  
  public String T()
  {
    return this.K;
  }
  
  public void a(KeyPair ☃)
  {
    this.H = ☃;
  }
  
  public void a(qk ☃)
  {
    for (int ☃ = 0; ☃ < this.d.length; ☃++)
    {
      aht ☃ = this.d[☃];
      if (☃ != null) {
        if (☃.T().s())
        {
          ☃.T().a(qk.d);
          ☃.a(true, true);
        }
        else if (R())
        {
          ☃.T().a(☃);
          ☃.a(☃.ae() != qk.a, true);
        }
        else
        {
          ☃.T().a(☃);
          ☃.a(U(), this.A);
        }
      }
    }
  }
  
  protected boolean U()
  {
    return true;
  }
  
  public boolean V()
  {
    return this.L;
  }
  
  public void b(boolean ☃)
  {
    this.L = ☃;
  }
  
  public void c(boolean ☃)
  {
    this.M = ☃;
  }
  
  public azk W()
  {
    return this.l;
  }
  
  public String X()
  {
    return this.N;
  }
  
  public String Y()
  {
    return this.O;
  }
  
  public void a_(String ☃, String ☃)
  {
    this.N = ☃;
    this.O = ☃;
  }
  
  public void a(qw ☃)
  {
    ☃.a("whitelist_enabled", Boolean.valueOf(false));
    ☃.a("whitelist_count", Integer.valueOf(0));
    if (this.v != null)
    {
      ☃.a("players_current", Integer.valueOf(H()));
      ☃.a("players_max", Integer.valueOf(I()));
      ☃.a("players_seen", Integer.valueOf(this.v.q().length));
    }
    ☃.a("uses_auth", Boolean.valueOf(this.z));
    ☃.a("gui_state", ao() ? "enabled" : "disabled");
    ☃.a("run_time", Long.valueOf((av() - ☃.g()) / 60L * 1000L));
    
    ☃.a("avg_tick_ms", Integer.valueOf((int)(on.a(this.h) * 1.0E-6D)));
    
    int ☃ = 0;
    if (this.d != null) {
      for (int ☃ = 0; ☃ < this.d.length; ☃++) {
        if (this.d[☃] != null)
        {
          lp ☃ = this.d[☃];
          azh ☃ = ☃.T();
          
          ☃.a("world[" + ☃ + "][dimension]", Integer.valueOf(☃.s.p().a()));
          ☃.a("world[" + ☃ + "][mode]", ☃.q());
          ☃.a("world[" + ☃ + "][difficulty]", ☃.ae());
          ☃.a("world[" + ☃ + "][hardcore]", Boolean.valueOf(☃.s()));
          ☃.a("world[" + ☃ + "][generator_name]", ☃.t().a());
          ☃.a("world[" + ☃ + "][generator_version]", Integer.valueOf(☃.t().d()));
          ☃.a("world[" + ☃ + "][height]", Integer.valueOf(this.F));
          ☃.a("world[" + ☃ + "][chunks_loaded]", Integer.valueOf(☃.r().g()));
          
          ☃++;
        }
      }
    }
    ☃.a("worlds", Integer.valueOf(☃));
  }
  
  public void b(qw ☃)
  {
    ☃.b("singleplayer", Boolean.valueOf(R()));
    ☃.b("server_brand", getServerModName());
    ☃.b("gui_supported", GraphicsEnvironment.isHeadless() ? "headless" : "supported");
    ☃.b("dedicated", Boolean.valueOf(aa()));
  }
  
  public boolean Z()
  {
    return true;
  }
  
  public abstract boolean aa();
  
  public boolean ab()
  {
    return this.z;
  }
  
  public void d(boolean ☃)
  {
    this.z = ☃;
  }
  
  public boolean ac()
  {
    return this.A;
  }
  
  public void e(boolean ☃)
  {
    this.A = ☃;
  }
  
  public boolean ad()
  {
    return this.B;
  }
  
  public abstract boolean ae();
  
  public void f(boolean ☃)
  {
    this.B = ☃;
  }
  
  public boolean af()
  {
    return this.C;
  }
  
  public void g(boolean ☃)
  {
    this.C = ☃;
  }
  
  public boolean ag()
  {
    return this.D;
  }
  
  public void h(boolean ☃)
  {
    this.D = ☃;
  }
  
  public abstract boolean ah();
  
  public String ai()
  {
    return this.E;
  }
  
  public void l(String ☃)
  {
    this.E = ☃;
  }
  
  public int aj()
  {
    return this.F;
  }
  
  public void c(int ☃)
  {
    this.F = ☃;
  }
  
  public boolean ak()
  {
    return this.x;
  }
  
  public mm al()
  {
    return this.v;
  }
  
  public void a(mm ☃)
  {
    this.v = ☃;
  }
  
  public void a(ahw.a ☃)
  {
    for (int ☃ = 0; ☃ < this.d.length; ☃++) {
      this.d[☃].T().a(☃);
    }
  }
  
  public ma am()
  {
    return this.p;
  }
  
  public boolean an()
  {
    return this.P;
  }
  
  public boolean ao()
  {
    return false;
  }
  
  public abstract String a(ahw.a parama, boolean paramBoolean);
  
  public int ap()
  {
    return this.y;
  }
  
  public void aq()
  {
    this.S = true;
  }
  
  public qw ar()
  {
    return this.m;
  }
  
  public cj c()
  {
    return cj.a;
  }
  
  public bbj d()
  {
    return bbj.a;
  }
  
  public aht e()
  {
    return this.d[0];
  }
  
  public rr f()
  {
    return null;
  }
  
  public boolean a(aht ☃, cj ☃, zj ☃)
  {
    return false;
  }
  
  public boolean at()
  {
    return this.T;
  }
  
  public Proxy au()
  {
    return this.e;
  }
  
  public static long av()
  {
    return System.currentTimeMillis();
  }
  
  public int aw()
  {
    return this.G;
  }
  
  public void d(int ☃)
  {
    this.G = ☃;
  }
  
  public eu i_()
  {
    return new fa(h_());
  }
  
  public boolean ax()
  {
    return true;
  }
  
  public MinecraftSessionService ay()
  {
    return this.V;
  }
  
  public GameProfileRepository az()
  {
    return this.W;
  }
  
  public mi aA()
  {
    return this.X;
  }
  
  public jz aB()
  {
    return this.q;
  }
  
  public void aC()
  {
    this.Y = 0L;
  }
  
  public rr a(UUID ☃)
  {
    for (lp ☃ : this.d) {
      if (☃ != null)
      {
        rr ☃ = ☃.a(☃);
        if (☃ != null) {
          return ☃;
        }
      }
    }
    return null;
  }
  
  public boolean z_()
  {
    return this.d[0].U().b("sendCommandFeedback");
  }
  
  public void a(n.a ☃, int ☃) {}
  
  public MinecraftServer h()
  {
    return this;
  }
  
  public int aD()
  {
    return 29999984;
  }
  
  public <V> ListenableFuture<V> a(Callable<V> ☃)
  {
    Validate.notNull(☃);
    if ((!aE()) && (!ak()))
    {
      ListenableFutureTask<V> ☃ = ListenableFutureTask.create(☃);
      synchronized (this.j)
      {
        this.j.add(☃);
      }
      return ☃;
    }
    try
    {
      return Futures.immediateFuture(☃.call());
    }
    catch (Exception ☃)
    {
      return Futures.immediateFailedCheckedFuture(☃);
    }
  }
  
  public ListenableFuture<Object> a(Runnable ☃)
  {
    Validate.notNull(☃);
    
    return a(Executors.callable(☃));
  }
  
  public boolean aE()
  {
    return Thread.currentThread() == this.Z;
  }
  
  public int aF()
  {
    return 256;
  }
  
  public pb aI()
  {
    return this.s;
  }
  
  public int a(lp ☃)
  {
    if (☃ != null) {
      return ☃.U().c("spawnRadius");
    }
    return 10;
  }
}
