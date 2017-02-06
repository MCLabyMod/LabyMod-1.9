import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.PropertyMap;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.utils.Allowed;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import javax.imageio.ImageIO;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.opengl.OpenGLException;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.glu.GLU;

public class bcf
  implements qc, qx
{
  private static final Logger L = ;
  private static final kk M = new kk("textures/gui/title/mojang.png");
  public static final boolean a = g.a() == g.a.d;
  public static byte[] b = new byte[10485760];
  private static final List<DisplayMode> N = Lists.newArrayList(new DisplayMode[] { new DisplayMode(2560, 1600), new DisplayMode(2880, 1800) });
  private final File O;
  private final PropertyMap P;
  private final PropertyMap Q;
  private bkx R;
  private bvi S;
  private static bcf T;
  private final pb U = pc.a();
  public bkt c;
  private boolean V;
  private boolean W = true;
  private boolean X;
  private b Y;
  public int d;
  public int e;
  private boolean Z = false;
  private bcl aa = new bcl(20.0F);
  private qw ab = new qw("client", this, MinecraftServer.av());
  public bku f;
  public bno g;
  private brm ac;
  private brz ad;
  private bnk ae;
  public bmt h;
  private rr af;
  public rr i;
  public bly j;
  private final bcm ag;
  private boolean ah;
  public bct k;
  public bct l;
  public bfb m;
  public bci n;
  public bng o;
  public bqs p;
  private int ai;
  private int aj;
  private int ak;
  private byn al;
  public bfl q;
  public bcu r;
  public boolean s;
  public bbi t;
  public bch u;
  public bcg v;
  public final File w;
  private final File am;
  private final String an;
  private final String ao;
  private final Proxy ap;
  private azk aq;
  private static int ar;
  private int as;
  private String at;
  private int au;
  public boolean x;
  long y = I();
  private int av;
  public final oc z = new oc();
  long A = System.nanoTime();
  private final boolean aw;
  private final boolean ax;
  private ek ay;
  private boolean az;
  public final oo B = new oo();
  private long aA = -1L;
  private bwe aB;
  private final bww aC = new bww();
  private final List<bwi> aD = Lists.newArrayList();
  private final bvx aE;
  private bwk aF;
  private bwq aG;
  private bco aH;
  private bcr aI;
  private bnt aJ;
  private bvg aK;
  private byx aL;
  private byu aM;
  private kk aN;
  private final MinecraftSessionService aO;
  private bwn aP;
  private final Queue<FutureTask<?>> aQ = Queues.newArrayDeque();
  private long aR = 0L;
  private final Thread aS = Thread.currentThread();
  private bxs aT;
  private boc aU;
  volatile boolean C = true;
  public String D = "";
  public boolean E = false;
  public boolean F = false;
  public boolean G = false;
  public boolean H = true;
  long I = I();
  int J;
  private boolean aV;
  long K = -1L;
  private String aW = "root";
  
  public bcf(bib gameConfig)
  {
    T = this;
    this.w = gameConfig.c.a;
    this.am = gameConfig.c.c;
    this.O = gameConfig.c.b;
    this.an = gameConfig.d.b;
    this.ao = gameConfig.d.c;
    this.P = gameConfig.a.b;
    this.Q = gameConfig.a.c;
    this.aE = new bvx(gameConfig.c.a());
    this.ap = (gameConfig.a.d == null ? Proxy.NO_PROXY : gameConfig.a.d);
    this.aO = new YggdrasilAuthenticationService(this.ap, UUID.randomUUID().toString()).createMinecraftSessionService();
    this.ag = gameConfig.a.a;
    L.info("Setting user: " + this.ag.c());
    L.info("(Session ID is " + this.ag.a() + ")");
    this.ax = gameConfig.d.a;
    this.d = (gameConfig.b.a > 0 ? gameConfig.b.a : 1);
    this.e = (gameConfig.b.b > 0 ? gameConfig.b.b : 1);
    this.aj = gameConfig.b.a;
    this.ak = gameConfig.b.b;
    this.V = gameConfig.b.c;
    this.aw = as();
    this.al = null;
    if (gameConfig.e.a != null)
    {
      this.at = gameConfig.e.a;
      this.au = gameConfig.e.b;
    }
    ImageIO.setUseCache(false);
    kn.c();
  }
  
  /* Error */
  public void a()
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_1
    //   2: putfield 353	bcf:C	Z
    //   5: aload_0
    //   6: invokespecial 546	bcf:an	()V
    //   9: goto +30 -> 39
    //   12: astore_1
    //   13: aload_1
    //   14: ldc_w 548
    //   17: invokestatic 552	b:a	(Ljava/lang/Throwable;Ljava/lang/String;)Lb;
    //   20: astore_2
    //   21: aload_2
    //   22: ldc_w 554
    //   25: invokevirtual 557	b:a	(Ljava/lang/String;)Lc;
    //   28: pop
    //   29: aload_0
    //   30: aload_0
    //   31: aload_2
    //   32: invokevirtual 560	bcf:b	(Lb;)Lb;
    //   35: invokevirtual 563	bcf:c	(Lb;)V
    //   38: return
    //   39: aload_0
    //   40: getfield 353	bcf:C	Z
    //   43: ifeq +57 -> 100
    //   46: aload_0
    //   47: getfield 565	bcf:X	Z
    //   50: ifeq +10 -> 60
    //   53: aload_0
    //   54: getfield 567	bcf:Y	Lb;
    //   57: ifnonnull +32 -> 89
    //   60: aload_0
    //   61: invokespecial 569	bcf:av	()V
    //   64: goto -25 -> 39
    //   67: astore_1
    //   68: aload_0
    //   69: invokevirtual 571	bcf:m	()V
    //   72: aload_0
    //   73: new 573	bew
    //   76: dup
    //   77: invokespecial 574	bew:<init>	()V
    //   80: invokevirtual 577	bcf:a	(Lbfb;)V
    //   83: invokestatic 580	java/lang/System:gc	()V
    //   86: goto -47 -> 39
    //   89: aload_0
    //   90: aload_0
    //   91: getfield 567	bcf:Y	Lb;
    //   94: invokevirtual 563	bcf:c	(Lb;)V
    //   97: goto -58 -> 39
    //   100: aload_0
    //   101: invokevirtual 582	bcf:h	()V
    //   104: goto +104 -> 208
    //   107: astore_1
    //   108: aload_0
    //   109: invokevirtual 582	bcf:h	()V
    //   112: goto +97 -> 209
    //   115: astore_1
    //   116: aload_0
    //   117: aload_1
    //   118: invokevirtual 585	e:a	()Lb;
    //   121: invokevirtual 560	bcf:b	(Lb;)Lb;
    //   124: pop
    //   125: aload_0
    //   126: invokevirtual 571	bcf:m	()V
    //   129: getstatic 458	bcf:L	Lorg/apache/logging/log4j/Logger;
    //   132: ldc_w 587
    //   135: aload_1
    //   136: invokeinterface 591 3 0
    //   141: aload_0
    //   142: aload_1
    //   143: invokevirtual 585	e:a	()Lb;
    //   146: invokevirtual 563	bcf:c	(Lb;)V
    //   149: aload_0
    //   150: invokevirtual 582	bcf:h	()V
    //   153: goto +56 -> 209
    //   156: astore_1
    //   157: aload_0
    //   158: new 549	b
    //   161: dup
    //   162: ldc_w 593
    //   165: aload_1
    //   166: invokespecial 595	b:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   169: invokevirtual 560	bcf:b	(Lb;)Lb;
    //   172: astore_2
    //   173: aload_0
    //   174: invokevirtual 571	bcf:m	()V
    //   177: getstatic 458	bcf:L	Lorg/apache/logging/log4j/Logger;
    //   180: ldc_w 597
    //   183: aload_1
    //   184: invokeinterface 591 3 0
    //   189: aload_0
    //   190: aload_2
    //   191: invokevirtual 563	bcf:c	(Lb;)V
    //   194: aload_0
    //   195: invokevirtual 582	bcf:h	()V
    //   198: goto +11 -> 209
    //   201: astore_3
    //   202: aload_0
    //   203: invokevirtual 582	bcf:h	()V
    //   206: aload_3
    //   207: athrow
    //   208: return
    //   209: return
    // Line number table:
    //   Java source line #426	-> byte code offset #0
    //   Java source line #430	-> byte code offset #5
    //   Java source line #438	-> byte code offset #9
    //   Java source line #432	-> byte code offset #12
    //   Java source line #434	-> byte code offset #13
    //   Java source line #435	-> byte code offset #21
    //   Java source line #436	-> byte code offset #29
    //   Java source line #437	-> byte code offset #38
    //   Java source line #444	-> byte code offset #39
    //   Java source line #446	-> byte code offset #46
    //   Java source line #450	-> byte code offset #60
    //   Java source line #457	-> byte code offset #64
    //   Java source line #452	-> byte code offset #67
    //   Java source line #454	-> byte code offset #68
    //   Java source line #455	-> byte code offset #72
    //   Java source line #456	-> byte code offset #83
    //   Java source line #457	-> byte code offset #86
    //   Java source line #461	-> byte code offset #89
    //   Java source line #487	-> byte code offset #100
    //   Java source line #488	-> byte code offset #104
    //   Java source line #465	-> byte code offset #107
    //   Java source line #487	-> byte code offset #108
    //   Java source line #469	-> byte code offset #115
    //   Java source line #471	-> byte code offset #116
    //   Java source line #472	-> byte code offset #125
    //   Java source line #473	-> byte code offset #129
    //   Java source line #474	-> byte code offset #141
    //   Java source line #487	-> byte code offset #149
    //   Java source line #477	-> byte code offset #156
    //   Java source line #479	-> byte code offset #157
    //   Java source line #480	-> byte code offset #173
    //   Java source line #481	-> byte code offset #177
    //   Java source line #482	-> byte code offset #189
    //   Java source line #487	-> byte code offset #194
    //   Java source line #490	-> byte code offset #208
    //   Java source line #492	-> byte code offset #209
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	210	0	this	bcf
    //   12	2	1	throwable	Throwable
    //   67	2	1	var10	OutOfMemoryError
    //   107	2	1	var12	bck
    //   115	28	1	reportedexception	e
    //   156	28	1	throwable1	Throwable
    //   20	12	2	crashreport	b
    //   172	19	2	crashreport1	b
    //   201	6	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   5	9	12	java/lang/Throwable
    //   60	64	67	java/lang/OutOfMemoryError
    //   39	100	107	bck
    //   39	100	115	e
    //   39	100	156	java/lang/Throwable
    //   39	100	201	finally
    //   115	149	201	finally
    //   156	194	201	finally
  }
  
  private void an()
    throws LWJGLException, IOException
  {
    this.u = new bch(this, this.w);
    this.aD.add(this.aE);
    at();
    if ((this.u.C > 0) && (this.u.B > 0))
    {
      this.d = this.u.B;
      this.e = this.u.C;
    }
    L.info("LWJGL Version: " + Sys.getVersion());
    ar();
    aq();
    ap();
    bzg.a();
    this.aJ = new bnt(this.d, this.e, true);
    this.aJ.a(0.0F, 0.0F, 0.0F, 0.0F);
    ao();
    this.aF = new bwk(this.O, new File(this.w, "server-resource-packs"), this.aE, this.aC, this.u);
    this.aB = new bwl(this.aC);
    this.aG = new bwq(this.aC, this.u.aB);
    this.aB.a(this.aG);
    f();
    this.S = new bvi(this.aB);
    this.aB.a(this.S);
    a(this.S);
    this.aP = new bwn(this.S, new File(this.am, "skins"), this.aO);
    this.aq = new azd(new File(this.w, "saves"), this.U);
    this.aL = new byx(this.aB, this.u);
    this.aB.a(this.aL);
    this.aM = new byu(this);
    this.k = new bct(this.u, new kk("textures/font/ascii.png"), this.S, false);
    if (this.u.aB != null)
    {
      this.k.a(e());
      this.k.b(this.aG.b());
    }
    this.l = new bct(this.u, new kk("textures/font/ascii_sga.png"), this.S, false);
    this.aB.a(this.k);
    this.aB.a(this.l);
    this.aB.a(new bwd());
    this.aB.a(new bwc());
    nk.f.a(new nl()
    {
      public String a(String str)
      {
        try
        {
          return String.format(str, new Object[] { bch.c(bcf.this.u.W.j()) });
        }
        catch (Exception exception)
        {
          return "Error: " + exception.getLocalizedMessage();
        }
      }
    });
    this.v = new bcg();
    a("Pre startup");
    bni.y();
    bni.j(7425);
    bni.a(1.0D);
    bni.k();
    bni.c(515);
    bni.e();
    bni.a(516, 0.1F);
    bni.a(bni.i.b);
    bni.n(5889);
    bni.F();
    bni.n(5888);
    a("Startup");
    this.aK = new bvg("textures");
    this.aK.a(this.u.J);
    this.S.a(bvg.g, this.aK);
    this.S.a(bvg.g);
    this.aK.a(false, this.u.J > 0);
    this.aT = new bxs(this.aK);
    this.aB.a(this.aT);
    this.aH = bco.a();
    this.aI = bcr.a(this.aH);
    this.ad = new brz(this.S, this.aT, this.aI);
    this.ac = new brm(this.S, this.ad);
    this.ae = new bnk(this);
    this.aB.a(this.ad);
    this.o = new bng(this, this.aB);
    this.aB.a(this.o);
    this.aU = new boc(this.aT.c(), this.aH);
    this.aB.a(this.aU);
    this.g = new bno(this);
    this.aB.a(this.g);
    this.q = new bfl(this);
    bni.b(0, 0, this.d, this.e);
    this.j = new bly(this.f, this.S);
    a("Post startup");
    this.r = new bcu(this);
    if (this.at != null) {
      a(new bei(new bfi(), this, this.at, this.au));
    } else {
      a(new bfi());
    }
    this.S.c(this.aN);
    this.aN = null;
    this.n = new bci(this);
    this.p = new bqs(this);
    if ((this.u.s) && (!this.V)) {
      r();
    }
    try
    {
      Display.setVSyncEnabled(this.u.t);
    }
    catch (OpenGLException var2)
    {
      this.u.t = false;
      this.u.b();
    }
    this.g.b();
  }
  
  private void ao()
  {
    this.aC.a(new bxm(), bxl.class);
    this.aC.a(new bxc(), bxb.class);
    this.aC.a(new bwz(), bwy.class);
    this.aC.a(new bxi(), bxh.class);
    this.aC.a(new bxf(), bxe.class);
  }
  
  private void ap()
    throws LWJGLException
  {
    Display.setResizable(true);
    Display.setTitle("Minecraft 1.9");
    try
    {
      Display.create(new PixelFormat().withDepthBits(24));
    }
    catch (LWJGLException lwjglexception)
    {
      L.error("Couldn't set pixel format", lwjglexception);
      try
      {
        Thread.sleep(1000L);
      }
      catch (InterruptedException localInterruptedException) {}
      if (this.V) {
        au();
      }
      Display.create();
    }
  }
  
  private void aq()
    throws LWJGLException
  {
    if (this.V)
    {
      Display.setFullscreen(true);
      DisplayMode displaymode = Display.getDisplayMode();
      this.d = Math.max(1, displaymode.getWidth());
      this.e = Math.max(1, displaymode.getHeight());
    }
    else
    {
      Display.setDisplayMode(new DisplayMode(this.d, this.e));
    }
  }
  
  private void ar()
  {
    g.a util$enumos = g.a();
    if (util$enumos != g.a.d)
    {
      InputStream inputstream = null;
      InputStream inputstream1 = null;
      try
      {
        inputstream = this.aE.c(new kk("icons/icon_16x16.png"));
        inputstream1 = this.aE.c(new kk("icons/icon_32x32.png"));
        if ((inputstream != null) && (inputstream1 != null)) {
          Display.setIcon(new ByteBuffer[] { a(inputstream), a(inputstream1) });
        }
      }
      catch (IOException ioexception)
      {
        L.error("Couldn't set icon", ioexception);
      }
      finally
      {
        IOUtils.closeQuietly(inputstream);
        IOUtils.closeQuietly(inputstream1);
      }
    }
  }
  
  private static boolean as()
  {
    String[] astring = { "sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch" };
    for (String s : astring)
    {
      String s1 = System.getProperty(s);
      if ((s1 != null) && (s1.contains("64"))) {
        return true;
      }
    }
    return false;
  }
  
  public bnt b()
  {
    return this.aJ;
  }
  
  public String c()
  {
    return this.an;
  }
  
  public String d()
  {
    return this.ao;
  }
  
  private void at()
  {
    Thread thread = new Thread("Timer hack thread")
    {
      public void run()
      {
        while (bcf.this.C) {
          try
          {
            Thread.sleep(2147483647L);
          }
          catch (InterruptedException localInterruptedException) {}
        }
      }
    };
    thread.setDaemon(true);
    thread.start();
  }
  
  public void a(b crash)
  {
    this.X = true;
    this.Y = crash;
  }
  
  public void c(b crashReportIn)
  {
    File file1 = new File(z().w, "crash-reports");
    File file2 = new File(file1, "crash-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + "-client.txt");
    kn.a(crashReportIn.e());
    if (crashReportIn.f() != null)
    {
      kn.a("#@!@# Game crashed! Crash report saved to: #@!@# " + crashReportIn.f());
      System.exit(-1);
    }
    else if (crashReportIn.a(file2))
    {
      kn.a("#@!@# Game crashed! Crash report saved to: #@!@# " + file2.getAbsolutePath());
      System.exit(-1);
    }
    else
    {
      kn.a("#@?@# Game crashed! Crash report could not be saved. #@?@#");
      System.exit(-2);
    }
  }
  
  public boolean e()
  {
    return (this.aG.a()) || (this.u.aC);
  }
  
  public void f()
  {
    List<bwi> list = Lists.newArrayList(this.aD);
    if (this.al != null) {
      this.al.b();
    }
    for (bwk.a resourcepackrepository$entry : this.aF.d()) {
      list.add(resourcepackrepository$entry.c());
    }
    if (this.aF.f() != null) {
      list.add(this.aF.f());
    }
    try
    {
      this.aB.a(list);
    }
    catch (RuntimeException runtimeexception)
    {
      L.info("Caught error stitching, removing all assigned resourcepacks", runtimeexception);
      list.clear();
      list.addAll(this.aD);
      this.aF.a(Collections.emptyList());
      this.aB.a(list);
      this.u.k.clear();
      this.u.l.clear();
      this.u.b();
    }
    this.aG.a(list);
    if (this.g != null) {
      this.g.a();
    }
  }
  
  private ByteBuffer a(InputStream imageStream)
    throws IOException
  {
    BufferedImage bufferedimage = ImageIO.read(imageStream);
    int[] aint = bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), (int[])null, 0, bufferedimage.getWidth());
    ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);
    for (int i : aint) {
      bytebuffer.putInt(i << 8 | i >> 24 & 0xFF);
    }
    bytebuffer.flip();
    return bytebuffer;
  }
  
  private void au()
    throws LWJGLException
  {
    Set<DisplayMode> set = Sets.newHashSet();
    Collections.addAll(set, Display.getAvailableDisplayModes());
    DisplayMode displaymode = Display.getDesktopDisplayMode();
    if ((!set.contains(displaymode)) && (g.a() == g.a.d))
    {
      Iterator localIterator1 = N.iterator();
      for (;;)
      {
        if (!localIterator1.hasNext()) {
          break label229;
        }
        DisplayMode displaymode1 = (DisplayMode)localIterator1.next();
        
        boolean flag = true;
        for (DisplayMode displaymode2 : set) {
          if ((displaymode2.getBitsPerPixel() == 32) && (displaymode2.getWidth() == displaymode1.getWidth()) && (displaymode2.getHeight() == displaymode1.getHeight()))
          {
            flag = false;
            break;
          }
        }
        if (!flag)
        {
          Iterator iterator = set.iterator();
          if (iterator.hasNext())
          {
            DisplayMode displaymode3 = (DisplayMode)iterator.next();
            if ((displaymode3.getBitsPerPixel() != 32) || (displaymode3.getWidth() != displaymode1.getWidth() / 2) || (displaymode3.getHeight() != displaymode1.getHeight() / 2)) {
              break;
            }
            displaymode = displaymode3;
          }
        }
      }
    }
    label229:
    Display.setDisplayMode(displaymode);
    this.d = displaymode.getWidth();
    this.e = displaymode.getHeight();
  }
  
  private void a(bvi textureManagerInstance)
    throws LWJGLException
  {
    bcx scaledresolution = new bcx(this);
    int i = scaledresolution.e();
    bnt framebuffer = new bnt(scaledresolution.a() * i, scaledresolution.b() * i, true);
    framebuffer.a(false);
    bni.n(5889);
    bni.F();
    bni.a(0.0D, scaledresolution.a(), scaledresolution.b(), 0.0D, 1000.0D, 3000.0D);
    bni.n(5888);
    bni.F();
    bni.c(0.0F, 0.0F, -2000.0F);
    bni.g();
    bni.p();
    bni.j();
    bni.y();
    InputStream inputstream = null;
    try
    {
      inputstream = this.aE.a(M);
      this.aN = textureManagerInstance.a("logo", new bux(ImageIO.read(inputstream)));
      textureManagerInstance.a(this.aN);
    }
    catch (IOException ioexception)
    {
      L.error("Unable to load logo: " + M, ioexception);
    }
    finally
    {
      IOUtils.closeQuietly(inputstream);
    }
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    vertexbuffer.a(7, bvp.i);
    vertexbuffer.b(0.0D, this.e, 0.0D).a(0.0D, 0.0D).b(255, 255, 255, 255).d();
    vertexbuffer.b(this.d, this.e, 0.0D).a(0.0D, 0.0D).b(255, 255, 255, 255).d();
    vertexbuffer.b(this.d, 0.0D, 0.0D).a(0.0D, 0.0D).b(255, 255, 255, 255).d();
    vertexbuffer.b(0.0D, 0.0D, 0.0D).a(0.0D, 0.0D).b(255, 255, 255, 255).d();
    tessellator.b();
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    int j = 256;
    int k = 256;
    a((scaledresolution.a() - j) / 2, (scaledresolution.b() - k) / 2, 0, 0, j, k, 255, 255, 255, 255);
    bni.g();
    bni.p();
    framebuffer.e();
    framebuffer.c(scaledresolution.a() * i, scaledresolution.b() * i);
    bni.e();
    bni.a(516, 0.1F);
    i();
  }
  
  public void a(int posX, int posY, int texU, int texV, int width, int height, int red, int green, int blue, int alpha)
  {
    float f = 0.00390625F;
    float f1 = 0.00390625F;
    bmz vertexbuffer = bnu.a().c();
    vertexbuffer.a(7, bvp.i);
    vertexbuffer.b(posX, posY + height, 0.0D).a(texU * f, (texV + height) * f1).b(red, green, blue, alpha).d();
    vertexbuffer.b(posX + width, posY + height, 0.0D).a((texU + width) * f, (texV + height) * f1).b(red, green, blue, alpha).d();
    vertexbuffer.b(posX + width, posY, 0.0D).a((texU + width) * f, texV * f1).b(red, green, blue, alpha).d();
    vertexbuffer.b(posX, posY, 0.0D).a(texU * f, texV * f1).b(red, green, blue, alpha).d();
    bnu.a().b();
  }
  
  public azk g()
  {
    return this.aq;
  }
  
  public void a(bfb guiScreenIn)
  {
    if (this.m != null) {
      this.m.m();
    }
    if ((guiScreenIn == null) && (this.f == null)) {
      guiScreenIn = new bfi();
    } else if ((guiScreenIn == null) && (this.h.bQ() <= 0.0F)) {
      guiScreenIn = new bem((eu)null);
    }
    if (((guiScreenIn instanceof bfi)) || ((guiScreenIn instanceof bgr)))
    {
      this.u.aq = false;
      this.r.d().a();
    }
    this.m = guiScreenIn;
    if (guiScreenIn != null)
    {
      p();
      bcc.b();
      while (Mouse.next()) {}
      while (Keyboard.next()) {}
      bcx scaledresolution = new bcx(this);
      int i = scaledresolution.a();
      int j = scaledresolution.b();
      guiScreenIn.a(this, i, j);
      this.s = false;
    }
    else
    {
      this.aL.e();
      o();
    }
  }
  
  private void a(String message)
  {
    if (this.W)
    {
      int i = bni.L();
      if (i != 0)
      {
        String s = GLU.gluErrorString(i);
        L.error("########## GL ERROR ##########");
        L.error("@ " + message);
        L.error(i + ": " + s);
      }
    }
  }
  
  /* Error */
  public void h()
  {
    // Byte code:
    //   0: getstatic 458	bcf:L	Lorg/apache/logging/log4j/Logger;
    //   3: ldc_w 1483
    //   6: invokeinterface 478 2 0
    //   11: aload_0
    //   12: aconst_null
    //   13: checkcast 1485	bku
    //   16: invokevirtual 1488	bcf:a	(Lbku;)V
    //   19: goto +4 -> 23
    //   22: astore_1
    //   23: aload_0
    //   24: getfield 730	bcf:aL	Lbyx;
    //   27: invokevirtual 1489	byx:d	()V
    //   30: invokestatic 1492	org/lwjgl/opengl/Display:destroy	()V
    //   33: aload_0
    //   34: getfield 565	bcf:X	Z
    //   37: ifne +27 -> 64
    //   40: iconst_0
    //   41: invokestatic 1168	java/lang/System:exit	(I)V
    //   44: goto +20 -> 64
    //   47: astore_2
    //   48: invokestatic 1492	org/lwjgl/opengl/Display:destroy	()V
    //   51: aload_0
    //   52: getfield 565	bcf:X	Z
    //   55: ifne +7 -> 62
    //   58: iconst_0
    //   59: invokestatic 1168	java/lang/System:exit	(I)V
    //   62: aload_2
    //   63: athrow
    //   64: invokestatic 580	java/lang/System:gc	()V
    //   67: return
    // Line number table:
    //   Java source line #1077	-> byte code offset #0
    //   Java source line #1081	-> byte code offset #11
    //   Java source line #1086	-> byte code offset #19
    //   Java source line #1083	-> byte code offset #22
    //   Java source line #1088	-> byte code offset #23
    //   Java source line #1092	-> byte code offset #30
    //   Java source line #1094	-> byte code offset #33
    //   Java source line #1096	-> byte code offset #40
    //   Java source line #1092	-> byte code offset #47
    //   Java source line #1094	-> byte code offset #51
    //   Java source line #1096	-> byte code offset #58
    //   Java source line #1100	-> byte code offset #64
    //   Java source line #1101	-> byte code offset #67
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	68	0	this	bcf
    //   22	1	1	localThrowable	Throwable
    //   47	16	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   11	19	22	java/lang/Throwable
    //   0	30	47	finally
  }
  
  private void av()
    throws IOException
  {
    long i = System.nanoTime();
    this.B.a("root");
    if ((Display.isCreated()) && (Display.isCloseRequested())) {
      n();
    }
    if ((this.ah) && (this.f != null))
    {
      float f = this.aa.c;
      this.aa.a();
      this.aa.c = f;
    }
    else
    {
      this.aa.a();
    }
    LabyMod.getInstance().setPartialTicks(this.aa.c);
    
    this.B.a("scheduledExecutables");
    synchronized (this.aQ)
    {
      while (!this.aQ.isEmpty()) {
        g.a((FutureTask)this.aQ.poll(), L);
      }
    }
    this.B.b();
    long l = System.nanoTime();
    this.B.a("tick");
    for (int j = 0; j < this.aa.b; j++) {
      t();
    }
    this.B.c("preRenderErrors");
    long i1 = System.nanoTime() - l;
    a("Pre render");
    this.B.c("sound");
    this.aL.a(this.h, this.aa.c);
    this.B.b();
    this.B.a("render");
    bni.G();
    bni.m(16640);
    this.aJ.a(true);
    this.B.a("display");
    bni.y();
    this.B.b();
    if (!this.s)
    {
      this.B.c("gameRenderer");
      this.o.a(this.aa.c, i);
      this.B.b();
    }
    this.B.b();
    if ((this.u.aq) && (this.u.ar) && (!this.u.ao))
    {
      if (!this.B.a) {
        this.B.a();
      }
      this.B.a = true;
      a(i1);
    }
    else
    {
      this.B.a = false;
      this.K = System.nanoTime();
    }
    this.q.a();
    this.aJ.e();
    bni.H();
    bni.G();
    this.aJ.c(this.d, this.e);
    bni.H();
    bni.G();
    this.o.b(this.aa.c);
    bni.H();
    this.B.a("root");
    i();
    Thread.yield();
    a("Post render");
    this.J += 1;
    this.ah = ((E()) && (this.m != null) && (this.m.d()) && (!this.al.a()));
    long k = System.nanoTime();
    this.z.a(k - this.A);
    this.A = k;
    while (I() >= this.I + 1000L)
    {
      ar = this.J;
      this.D = String.format("%d fps (%d chunk update%s) T: %s%s%s%s%s", new Object[] { Integer.valueOf(ar), Integer.valueOf(bqf.a), bqf.a != 1 ? "s" : "", this.u.g == bch.a.i.f() ? "inf" : Integer.valueOf(this.u.g), this.u.t ? " vsync" : "", this.u.i ? "" : " fast", this.u.h == 1 ? " fast-clouds" : this.u.h == 0 ? "" : " fancy-clouds", bzg.f() ? " vbo" : "" });
      bqf.a = 0;
      this.I += 1000L;
      this.J = 0;
      this.ab.b();
      if (!this.ab.d()) {
        this.ab.a();
      }
    }
    if (l())
    {
      this.B.a("fpslimit_wait");
      Display.sync(k());
      this.B.b();
    }
    this.B.b();
  }
  
  public void i()
  {
    this.B.a("display_update");
    Display.update();
    this.B.b();
    j();
  }
  
  protected void j()
  {
    if ((!this.V) && (Display.wasResized()))
    {
      int i = this.d;
      int j = this.e;
      this.d = Display.getWidth();
      this.e = Display.getHeight();
      if ((this.d != i) || (this.e != j))
      {
        if (this.d <= 0) {
          this.d = 1;
        }
        if (this.e <= 0) {
          this.e = 1;
        }
        a(this.d, this.e);
      }
    }
  }
  
  public int k()
  {
    return (this.f == null) && (this.m != null) ? 30 : this.u.g;
  }
  
  public boolean l()
  {
    return k() < bch.a.i.f();
  }
  
  public void m()
  {
    try
    {
      b = new byte[0];
      this.g.l();
    }
    catch (Throwable localThrowable) {}
    try
    {
      System.gc();
      a((bku)null);
    }
    catch (Throwable localThrowable1) {}
    System.gc();
  }
  
  private void b(int keyCount)
  {
    List<oo.a> list = this.B.b(this.aW);
    if ((list != null) && (!list.isEmpty()))
    {
      oo.a profiler$result = (oo.a)list.remove(0);
      if (keyCount == 0)
      {
        if (!profiler$result.c.isEmpty())
        {
          int i = this.aW.lastIndexOf(".");
          if (i >= 0) {
            this.aW = this.aW.substring(0, i);
          }
        }
      }
      else
      {
        keyCount--;
        if ((keyCount < list.size()) && (!((oo.a)list.get(keyCount)).c.equals("unspecified")))
        {
          if (!this.aW.isEmpty()) {
            this.aW += ".";
          }
          this.aW += ((oo.a)list.get(keyCount)).c;
        }
      }
    }
  }
  
  private void a(long elapsedTicksTime)
  {
    if (this.B.a)
    {
      List<oo.a> list = this.B.b(this.aW);
      oo.a profiler$result = (oo.a)list.remove(0);
      bni.m(256);
      bni.n(5889);
      bni.h();
      bni.F();
      bni.a(0.0D, this.d, this.e, 0.0D, 1000.0D, 3000.0D);
      bni.n(5888);
      bni.F();
      bni.c(0.0F, 0.0F, -2000.0F);
      bni.d(1.0F);
      bni.z();
      bnu tessellator = bnu.a();
      bmz vertexbuffer = tessellator.c();
      int i = 160;
      int j = this.d - i - 10;
      int k = this.e - i * 2;
      bni.m();
      vertexbuffer.a(7, bvp.f);
      vertexbuffer.b(j - i * 1.1F, k - i * 0.6F - 16.0F, 0.0D).b(200, 0, 0, 0).d();
      vertexbuffer.b(j - i * 1.1F, k + i * 2, 0.0D).b(200, 0, 0, 0).d();
      vertexbuffer.b(j + i * 1.1F, k + i * 2, 0.0D).b(200, 0, 0, 0).d();
      vertexbuffer.b(j + i * 1.1F, k - i * 0.6F - 16.0F, 0.0D).b(200, 0, 0, 0).d();
      tessellator.b();
      bni.l();
      double d0 = 0.0D;
      for (int l = 0; l < list.size(); l++)
      {
        oo.a profiler$result1 = (oo.a)list.get(l);
        int i1 = on.c(profiler$result1.a / 4.0D) + 1;
        vertexbuffer.a(6, bvp.f);
        int j1 = profiler$result1.a();
        int k1 = j1 >> 16 & 0xFF;
        int l1 = j1 >> 8 & 0xFF;
        int i2 = j1 & 0xFF;
        vertexbuffer.b(j, k, 0.0D).b(k1, l1, i2, 255).d();
        for (int j2 = i1; j2 >= 0; j2--)
        {
          float f = (float)((d0 + profiler$result1.a * j2 / i1) * 6.283185307179586D / 100.0D);
          float f1 = on.a(f) * i;
          float f2 = on.b(f) * i * 0.5F;
          vertexbuffer.b(j + f1, k - f2, 0.0D).b(k1, l1, i2, 255).d();
        }
        tessellator.b();
        vertexbuffer.a(5, bvp.f);
        for (int i3 = i1; i3 >= 0; i3--)
        {
          float f3 = (float)((d0 + profiler$result1.a * i3 / i1) * 6.283185307179586D / 100.0D);
          float f4 = on.a(f3) * i;
          float f5 = on.b(f3) * i * 0.5F;
          vertexbuffer.b(j + f4, k - f5, 0.0D).b(k1 >> 1, l1 >> 1, i2 >> 1, 255).d();
          vertexbuffer.b(j + f4, k - f5 + 10.0F, 0.0D).b(k1 >> 1, l1 >> 1, i2 >> 1, 255).d();
        }
        tessellator.b();
        d0 += profiler$result1.a;
      }
      DecimalFormat decimalformat = new DecimalFormat("##0.00");
      bni.y();
      String s = "";
      if (!profiler$result.c.equals("unspecified")) {
        s = s + "[0] ";
      }
      if (profiler$result.c.isEmpty()) {
        s = s + "ROOT ";
      } else {
        s = s + profiler$result.c + " ";
      }
      int l2 = 16777215;
      this.k.a(s, j - i, k - i / 2 - 16, l2);
      this.k.a(s = decimalformat.format(profiler$result.b) + "%", j + i - this.k.a(s), k - i / 2 - 16, l2);
      for (int k2 = 0; k2 < list.size(); k2++)
      {
        oo.a profiler$result2 = (oo.a)list.get(k2);
        String s1 = "";
        if (profiler$result2.c.equals("unspecified")) {
          s1 = s1 + "[?] ";
        } else {
          s1 = s1 + "[" + (k2 + 1) + "] ";
        }
        s1 = s1 + profiler$result2.c;
        this.k.a(s1, j - i, k + i / 2 + k2 * 8 + 20, profiler$result2.a());
        this.k.a(s1 = decimalformat.format(profiler$result2.a) + "%", j + i - 50 - this.k.a(s1), k + i / 2 + k2 * 8 + 20, profiler$result2.a());
        this.k.a(s1 = decimalformat.format(profiler$result2.b) + "%", j + i - this.k.a(s1), k + i / 2 + k2 * 8 + 20, profiler$result2.a());
      }
    }
  }
  
  public void n()
  {
    this.C = false;
  }
  
  public void o()
  {
    if (Display.isActive()) {
      if (!this.x)
      {
        if (!a) {
          bcc.a();
        }
        this.x = true;
        this.v.a();
        a((bfb)null);
        this.ai = 10000;
      }
    }
  }
  
  public void p()
  {
    if (this.x)
    {
      this.x = false;
      this.v.b();
    }
  }
  
  public void q()
  {
    if (this.m == null)
    {
      a(new bex());
      if ((E()) && (!this.al.a())) {
        this.aL.a();
      }
    }
  }
  
  private void b(boolean leftClick)
  {
    if (!leftClick) {
      this.ai = 0;
    }
    if ((this.ai <= 0) && ((!this.h.cs()) || ((ConfigManager.settings.oldBlockBuild) && (Allowed.blockBuild())))) {
      if ((leftClick) && (this.t != null) && (this.t.a == bbi.a.b))
      {
        cj blockpos = this.t.a();
        if ((this.f.o(blockpos).a() != axe.a) && (this.c.b(blockpos, this.t.b)))
        {
          this.j.a(blockpos, this.t.b);
          this.h.a(qm.a);
        }
      }
      else
      {
        this.c.c();
      }
    }
  }
  
  private void aw()
  {
    if (this.ai <= 0) {
      if (this.t == null)
      {
        L.error("Null returned as 'hitResult', this shouldn't happen!");
        if (this.c.g()) {
          this.ai = 10;
        }
      }
      else if (!this.h.M())
      {
        switch (this.t.a)
        {
        case c: 
          this.c.a(this.h, this.t.d);
          break;
        case b: 
          cj blockpos = this.t.a();
          if (this.f.o(blockpos).a() != axe.a) {
            this.c.a(blockpos, this.t.b);
          }
          break;
        }
        if (this.c.g()) {
          this.ai = 10;
        }
        this.h.cZ();
        
        this.h.a(qm.a);
      }
    }
  }
  
  private void ax()
  {
    if (!this.c.m())
    {
      this.as = 4;
      if (!this.h.M()) {
        for (qm enumhand : qm.values())
        {
          adq itemstack = this.h.b(enumhand);
          if (this.t == null) {
            L.warn("Null returned as 'hitResult', this shouldn't happen!");
          } else {
            switch (this.t.a)
            {
            case c: 
              if (this.c.a(this.h, this.t.d, this.t, this.h.b(enumhand), enumhand) == qo.a) {
                return;
              }
              if (this.c.a(this.h, this.t.d, this.h.b(enumhand), enumhand) == qo.a) {
                return;
              }
              break;
            case b: 
              cj blockpos = this.t.a();
              if (this.f.o(blockpos).a() != axe.a)
              {
                int i = itemstack != null ? itemstack.b : 0;
                qo enumactionresult = this.c.a(this.h, this.f, itemstack, blockpos, this.t.b, this.t.c, enumhand);
                if (enumactionresult == qo.a)
                {
                  this.h.a(enumhand);
                  if (itemstack != null) {
                    if (itemstack.b == 0) {
                      this.h.a(enumhand, (adq)null);
                    } else if ((itemstack.b != i) || (this.c.h())) {
                      this.o.c.a(enumhand);
                    }
                  }
                  return;
                }
              }
              break;
            }
          }
          adq itemstack1 = this.h.b(enumhand);
          if ((itemstack1 != null) && (this.c.a(this.h, this.f, itemstack1, enumhand) == qo.a))
          {
            this.o.c.a(enumhand);
            return;
          }
        }
      }
    }
  }
  
  public void r()
  {
    try
    {
      this.V = (!this.V);
      this.u.s = this.V;
      if (this.V)
      {
        au();
        this.d = Display.getDisplayMode().getWidth();
        this.e = Display.getDisplayMode().getHeight();
        if (this.d <= 0) {
          this.d = 1;
        }
        if (this.e <= 0) {
          this.e = 1;
        }
      }
      else
      {
        Display.setDisplayMode(new DisplayMode(this.aj, this.ak));
        this.d = this.aj;
        this.e = this.ak;
        if (this.d <= 0) {
          this.d = 1;
        }
        if (this.e <= 0) {
          this.e = 1;
        }
      }
      if (this.m != null) {
        a(this.d, this.e);
      } else {
        ay();
      }
      Display.setFullscreen(this.V);
      Display.setVSyncEnabled(this.u.t);
      i();
    }
    catch (Exception exception)
    {
      L.error("Couldn't toggle fullscreen", exception);
    }
  }
  
  private void a(int width, int height)
  {
    this.d = Math.max(1, width);
    this.e = Math.max(1, height);
    if (this.m != null)
    {
      bcx scaledresolution = new bcx(this);
      this.m.b(this, scaledresolution.a(), scaledresolution.b());
    }
    this.n = new bci(this);
    ay();
  }
  
  private void ay()
  {
    this.aJ.a(this.d, this.e);
    if (this.o != null) {
      this.o.a(this.d, this.e);
    }
  }
  
  public byu s()
  {
    return this.aM;
  }
  
  public void t()
    throws IOException
  {
    if (this.as > 0) {
      this.as -= 1;
    }
    this.B.a("gui");
    if (!this.ah) {
      this.r.c();
    }
    this.B.b();
    this.o.a(1.0F);
    this.B.a("gameMode");
    if ((!this.ah) && (this.f != null)) {
      this.c.e();
    }
    this.B.c("textures");
    if (!this.ah) {
      this.S.e();
    }
    if ((this.m == null) && (this.h != null))
    {
      if ((this.h.bQ() <= 0.0F) && (!(this.m instanceof bem))) {
        a((bfb)null);
      } else if ((this.h.cl()) && (this.f != null)) {
        a(new bes());
      }
    }
    else if ((this.m != null) && ((this.m instanceof bes)) && (!this.h.cl())) {
      a((bfb)null);
    }
    if (this.m != null) {
      this.ai = 10000;
    }
    if (this.m != null)
    {
      try
      {
        this.m.p();
      }
      catch (Throwable throwable1)
      {
        b crashreport = b.a(throwable1, "Updating screen events");
        c crashreportcategory = crashreport.a("Affected screen");
        crashreportcategory.a("Screen name", new Callable()
        {
          public String a()
            throws Exception
          {
            return bcf.this.m.getClass().getCanonicalName();
          }
        });
        throw new e(crashreport);
      }
      if (this.m != null) {
        try
        {
          this.m.e();
        }
        catch (Throwable throwable)
        {
          b crashreport1 = b.a(throwable, "Ticking screen");
          c crashreportcategory1 = crashreport1.a("Affected screen");
          crashreportcategory1.a("Screen name", new Callable()
          {
            public String a()
              throws Exception
            {
              return bcf.this.m.getClass().getCanonicalName();
            }
          });
          throw new e(crashreport1);
        }
      }
    }
    if ((this.m == null) || (this.m.p))
    {
      this.B.c("mouse");
      aB();
      if (this.ai > 0) {
        this.ai -= 1;
      }
      this.B.c("keyboard");
      az();
    }
    if (this.f != null)
    {
      if (this.h != null)
      {
        this.av += 1;
        if (this.av == 30)
        {
          this.av = 0;
          this.f.h(this.h);
        }
      }
      this.B.c("gameRenderer");
      if (!this.ah) {
        this.o.e();
      }
      this.B.c("levelRenderer");
      if (!this.ah) {
        this.g.k();
      }
      this.B.c("level");
      if (!this.ah)
      {
        if (this.f.ag() > 0) {
          this.f.d(this.f.ag() - 1);
        }
        this.f.k();
      }
    }
    else if (this.o.a())
    {
      this.o.b();
    }
    if (!this.ah)
    {
      this.aM.c();
      this.aL.c();
    }
    if (this.f != null)
    {
      if (!this.ah)
      {
        this.f.a(this.f.ae() != qk.a, true);
        try
        {
          this.f.d();
        }
        catch (Throwable throwable2)
        {
          b crashreport2 = b.a(throwable2, "Exception in world tick");
          if (this.f == null)
          {
            c crashreportcategory2 = crashreport2.a("Affected level");
            crashreportcategory2.a("Problem", "Level is null!");
          }
          else
          {
            this.f.a(crashreport2);
          }
          throw new e(crashreport2);
        }
      }
      this.B.c("animateTick");
      if ((!this.ah) && (this.f != null)) {
        this.f.b(on.c(this.h.p), on.c(this.h.q), on.c(this.h.r));
      }
      this.B.c("particles");
      if (!this.ah) {
        this.j.a();
      }
    }
    else if (this.ay != null)
    {
      this.B.c("pendingConnection");
      this.ay.a();
    }
    this.B.b();
    this.y = I();
  }
  
  private void az()
    throws IOException
  {
    while (Keyboard.next())
    {
      int i = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 'Ā' : Keyboard.getEventKey();
      if (this.aA > 0L)
      {
        if (I() - this.aA >= 6000L) {
          throw new e(new b("Manually triggered debug crash", new Throwable()));
        }
        if ((!Keyboard.isKeyDown(46)) || (!Keyboard.isKeyDown(61))) {
          this.aA = -1L;
        }
      }
      else if ((Keyboard.isKeyDown(46)) && (Keyboard.isKeyDown(61)))
      {
        this.aV = true;
        this.aA = I();
      }
      W();
      if (this.m != null) {
        this.m.l();
      }
      boolean flag = Keyboard.getEventKeyState();
      if (flag)
      {
        if ((i == 62) && (this.o != null)) {
          this.o.c();
        }
        boolean flag1 = false;
        if (this.m == null)
        {
          if (i == 1) {
            q();
          }
          flag1 = (Keyboard.isKeyDown(61)) && (c(i));
          this.aV |= flag1;
          if (i == 59) {
            this.u.ao = (!this.u.ao);
          }
        }
        if (flag1)
        {
          bcc.a(i, false);
        }
        else
        {
          bcc.a(i, true);
          bcc.a(i);
        }
        if (this.u.ar)
        {
          if (i == 11) {
            b(0);
          }
          for (int j = 0; j < 9; j++) {
            if (i == 2 + j) {
              b(j + 1);
            }
          }
        }
      }
      else
      {
        bcc.a(i, false);
        if (i == 61) {
          if (this.aV)
          {
            this.aV = false;
          }
          else
          {
            this.u.aq = (!this.u.aq);
            this.u.ar = ((this.u.aq) && (bfb.r()));
            this.u.as = ((this.u.aq) && (bfb.s()));
          }
        }
      }
    }
    aA();
  }
  
  private boolean c(int p_184122_1_)
  {
    if (p_184122_1_ == 30)
    {
      this.g.a();
      a("Reloading all chunks", new Object[0]);
      return true;
    }
    if (p_184122_1_ == 48)
    {
      boolean flag = !this.ac.b();
      this.ac.b(flag);
      a("Hitboxes: {0}", new Object[] { flag ? "shown" : "hidden" });
      return true;
    }
    if (p_184122_1_ == 32)
    {
      if (this.r != null) {
        this.r.d().a();
      }
      return true;
    }
    if (p_184122_1_ == 33)
    {
      this.u.a(bch.a.f, bfb.r() ? -1 : 1);
      a("RenderDistance: {0}", new Object[] { Integer.valueOf(this.u.c) });
      return true;
    }
    if (p_184122_1_ == 35)
    {
      this.u.x = (!this.u.x);
      a("Advanced tooltips: {0}", new Object[] { this.u.x ? "shown" : "hidden" });
      this.u.b();
      return true;
    }
    if (p_184122_1_ == 49)
    {
      if (!this.h.a(2, "")) {
        a("Unable to switch gamemode, no permission", new Object[0]);
      } else if (this.h.l_()) {
        this.h.g("/gamemode spectator");
      } else if (this.h.y()) {
        this.h.g("/gamemode creative");
      }
      return true;
    }
    if (p_184122_1_ == 25)
    {
      this.u.y = (!this.u.y);
      this.u.b();
      a("PauseOnLostFocus: {0}", new Object[] { this.u.y ? "enabled" : "disabled" });
      return true;
    }
    if (p_184122_1_ == 16)
    {
      a("Keybindings:", new Object[0]);
      bda guinewchat = this.r.d();
      guinewchat.a(new fa("F3 + A = Reload chunks"));
      guinewchat.a(new fa("F3 + B = Show hitboxes"));
      guinewchat.a(new fa("F3 + D = Clear chat"));
      guinewchat.a(new fa("F3 + F = Cycle renderdistance (Shift to inverse)"));
      guinewchat.a(new fa("F3 + H = Advanced tooltips"));
      guinewchat.a(new fa("F3 + N = Cycle creative <-> spectator"));
      guinewchat.a(new fa("F3 + P = Pause on lost focus"));
      guinewchat.a(new fa("F3 + Q = Show this list"));
      guinewchat.a(new fa("F3 + T = Reload resourcepacks"));
      return true;
    }
    if (p_184122_1_ == 20)
    {
      f();
      a("Reloaded resourcepacks", new Object[0]);
      return true;
    }
    return false;
  }
  
  private void aA()
  {
    for (; this.u.ag.g(); this.g.o())
    {
      this.u.ap += 1;
      if (this.u.ap > 2) {
        this.u.ap = 0;
      }
      if (this.u.ap == 0) {
        this.o.a(aa());
      } else if (this.u.ap == 1) {
        this.o.a((rr)null);
      }
    }
    while (this.u.ah.g()) {
      this.u.au = (!this.u.au);
    }
    for (int i = 0; i < 9; i++) {
      if (this.u.ak[i].g()) {
        if (this.h.y()) {
          this.r.g().a(i);
        } else {
          this.h.br.d = i;
        }
      }
    }
    while (this.u.W.g())
    {
      v().a(new ik(ik.a.c));
      if (this.c.j()) {
        this.h.D();
      } else {
        a(new bgk(this.h));
      }
    }
    while (this.u.X.g()) {
      if (!this.h.y()) {
        v().a(new ix(ix.a.g, cj.a, cq.a));
      }
    }
    while (this.u.Y.g()) {
      if (!this.h.y()) {
        this.h.a(bfb.q());
      }
    }
    boolean flag = this.u.m != zj.b.c;
    if (flag)
    {
      while (this.u.ac.g()) {
        a(new bee());
      }
      if ((this.m == null) && (this.u.ae.g())) {
        a(new bee("/"));
      }
    }
    if (this.h.cs())
    {
      if (!this.u.Z.e()) {
        this.c.c(this.h);
      }
      while (this.u.aa.g()) {}
      while (this.u.Z.g()) {}
      while (this.u.ab.g()) {}
    }
    while (this.u.aa.g()) {
      aw();
    }
    while (this.u.Z.g()) {
      ax();
    }
    while (this.u.ab.g()) {
      aC();
    }
    if ((this.u.Z.e()) && (this.as == 0) && (!this.h.cs())) {
      ax();
    }
    b((this.m == null) && (this.u.aa.e()) && (this.x));
  }
  
  private void aB()
    throws IOException
  {
    while (Mouse.next())
    {
      int i = Mouse.getEventButton();
      bcc.a(i - 100, Mouse.getEventButtonState());
      if (Mouse.getEventButtonState()) {
        if ((this.h.y()) && (i == 2)) {
          this.r.g().b();
        } else {
          bcc.a(i - 100);
        }
      }
      long j = I() - this.y;
      if (j <= 200L)
      {
        int k = Mouse.getEventDWheel();
        if (k != 0) {
          if (this.h.y())
          {
            k = k < 0 ? -1 : 1;
            if (this.r.g().a())
            {
              this.r.g().b(-k);
            }
            else
            {
              float f = on.a(this.h.bJ.a() + k * 0.005F, 0.0F, 0.2F);
              this.h.bJ.a(f);
            }
          }
          else
          {
            this.h.br.f(k);
          }
        }
        if (this.m == null)
        {
          if ((!this.x) && (Mouse.getEventButtonState())) {
            o();
          }
        }
        else if (this.m != null) {
          this.m.k();
        }
      }
    }
  }
  
  private void a(String p_184120_1_, Object... p_184120_2_)
  {
    this.r.d().a(new fa("").a(new fa("[Debug]: ").a(new ez().a(a.o).a(Boolean.valueOf(true)))).a(MessageFormat.format(p_184120_1_, p_184120_2_)));
  }
  
  public void a(String folderName, String worldName, ahw worldSettingsIn)
  {
    a((bku)null);
    System.gc();
    azi isavehandler = this.aq.a(folderName, false);
    azh worldinfo = isavehandler.d();
    if ((worldinfo == null) && (worldSettingsIn != null))
    {
      worldinfo = new azh(worldSettingsIn, folderName);
      isavehandler.a(worldinfo);
    }
    if (worldSettingsIn == null) {
      worldSettingsIn = new ahw(worldinfo);
    }
    try
    {
      YggdrasilAuthenticationService yggdrasilauthenticationservice = new YggdrasilAuthenticationService(this.ap, UUID.randomUUID().toString());
      MinecraftSessionService minecraftsessionservice = yggdrasilauthenticationservice.createMinecraftSessionService();
      GameProfileRepository gameprofilerepository = yggdrasilauthenticationservice.createProfileRepository();
      mi playerprofilecache = new mi(gameprofilerepository, new File(this.w, MinecraftServer.a.getName()));
      aqo.a(playerprofilecache);
      aqo.a(minecraftsessionservice);
      mi.a(false);
      this.al = new byn(this, folderName, worldName, worldSettingsIn, yggdrasilauthenticationservice, minecraftsessionservice, gameprofilerepository, playerprofilecache);
      this.al.F();
      this.az = true;
    }
    catch (Throwable throwable)
    {
      b crashreport = b.a(throwable, "Starting integrated server");
      c crashreportcategory = crashreport.a("Starting integrated server");
      crashreportcategory.a("Level ID", folderName);
      crashreportcategory.a("Level Name", worldName);
      throw new e(crashreport);
    }
    this.n.a(bwo.a("menu.loadingLevel", new Object[0]));
    while (!this.al.an())
    {
      String s = this.al.k();
      if (s != null) {
        this.n.c(bwo.a(s, new Object[0]));
      } else {
        this.n.c("");
      }
      try
      {
        Thread.sleep(200L);
      }
      catch (InterruptedException localInterruptedException) {}
    }
    a(new bez());
    SocketAddress socketaddress = this.al.am().a();
    ek networkmanager = ek.a(socketaddress);
    networkmanager.a(new bkr(networkmanager, this, (bfb)null));
    networkmanager.a(new jj(107, socketaddress.toString(), 0, el.d));
    networkmanager.a(new js(K().e()));
    this.ay = networkmanager;
  }
  
  public void a(bku worldClientIn)
  {
    a(worldClientIn, "");
  }
  
  public void a(bku worldClientIn, String loadingMessage)
  {
    if (worldClientIn == null)
    {
      bks nethandlerplayclient = v();
      if (nethandlerplayclient != null) {
        nethandlerplayclient.b();
      }
      if ((this.al != null) && (this.al.M())) {
        this.al.x();
      }
      this.al = null;
      this.q.b();
      this.o.k().a();
      this.c = null;
    }
    this.af = null;
    this.ay = null;
    if (this.n != null)
    {
      this.n.b(loadingMessage);
      this.n.c("");
    }
    if ((worldClientIn == null) && (this.f != null))
    {
      this.aF.g();
      this.r.i();
      a((bkx)null);
      this.az = false;
    }
    this.aL.b();
    this.f = worldClientIn;
    if (this.g != null) {
      this.g.a(worldClientIn);
    }
    if (this.j != null) {
      this.j.a(worldClientIn);
    }
    bpm.a.a(worldClientIn);
    if (worldClientIn != null)
    {
      if (!this.az)
      {
        YggdrasilAuthenticationService yggdrasilauthenticationservice = new YggdrasilAuthenticationService(this.ap, UUID.randomUUID().toString());
        MinecraftSessionService minecraftsessionservice = yggdrasilauthenticationservice.createMinecraftSessionService();
        GameProfileRepository gameprofilerepository = yggdrasilauthenticationservice.createProfileRepository();
        mi playerprofilecache = new mi(gameprofilerepository, new File(this.w, MinecraftServer.a.getName()));
        aqo.a(playerprofilecache);
        aqo.a(minecraftsessionservice);
        mi.a(false);
      }
      if (this.h == null)
      {
        this.h = this.c.a(worldClientIn, new nu());
        this.c.b(this.h);
      }
      this.h.S();
      worldClientIn.a(this.h);
      this.h.e = new bms(this.u);
      this.c.a(this.h);
      this.af = this.h;
    }
    else
    {
      this.aq.d();
      this.h = null;
    }
    System.gc();
    this.y = 0L;
  }
  
  public void a(int dimension)
  {
    this.f.h();
    this.f.c();
    int i = 0;
    String s = null;
    if (this.h != null)
    {
      i = this.h.O();
      this.f.e(this.h);
      s = this.h.E();
    }
    this.af = null;
    bmt entityplayersp = this.h;
    this.h = this.c.a(this.f, this.h == null ? new nu() : this.h.G());
    this.h.R().a(entityplayersp.R().c());
    this.h.am = dimension;
    this.af = this.h;
    this.h.S();
    this.h.h(s);
    this.f.a(this.h);
    this.c.b(this.h);
    this.h.e = new bms(this.u);
    this.h.f(i);
    this.c.a(this.h);
    this.h.m(entityplayersp.cX());
    if ((this.m instanceof bem)) {
      a((bfb)null);
    }
  }
  
  public final boolean u()
  {
    return this.ax;
  }
  
  public bks v()
  {
    return this.h != null ? this.h.d : null;
  }
  
  public static boolean w()
  {
    return (T == null) || (!T.u.ao);
  }
  
  public static boolean x()
  {
    return (T != null) && (T.u.i);
  }
  
  public static boolean y()
  {
    return (T != null) && (T.u.j != 0);
  }
  
  private void aC()
  {
    if ((this.t != null) && (this.t.a != bbi.a.a))
    {
      boolean flag = this.h.bJ.d;
      apv tileentity = null;
      adq itemstack;
      if (this.t.a == bbi.a.b)
      {
        cj blockpos = this.t.a();
        arc iblockstate = this.f.o(blockpos);
        ajt block = iblockstate.t();
        if (iblockstate.a() == axe.a) {
          return;
        }
        adq itemstack = block.a(this.f, blockpos, iblockstate);
        if (itemstack == null) {
          return;
        }
        if ((flag) && (bfb.q()) && (block.m())) {
          tileentity = this.f.r(blockpos);
        }
      }
      else
      {
        if ((this.t.a != bbi.a.c) || (this.t.d == null) || (!flag)) {
          return;
        }
        adq itemstack;
        if ((this.t.d instanceof xu))
        {
          itemstack = new adq(ads.ap);
        }
        else
        {
          adq itemstack;
          if ((this.t.d instanceof xt))
          {
            itemstack = new adq(ads.cx);
          }
          else
          {
            adq itemstack;
            if ((this.t.d instanceof xs))
            {
              xs entityitemframe = (xs)this.t.d;
              adq itemstack1 = entityitemframe.r();
              adq itemstack;
              if (itemstack1 == null) {
                itemstack = new adq(ads.bZ);
              } else {
                itemstack = adq.c(itemstack1);
              }
            }
            else
            {
              adq itemstack;
              if ((this.t.d instanceof aah))
              {
                aah entityminecart = (aah)this.t.d;
                ado item;
                ado item;
                ado item;
                ado item;
                ado item;
                ado item;
                switch (entityminecart.v())
                {
                case c: 
                  item = ads.aV;
                  break;
                case b: 
                  item = ads.aU;
                  break;
                case d: 
                  item = ads.cr;
                  break;
                case f: 
                  item = ads.cs;
                  break;
                case g: 
                  item = ads.cz;
                  break;
                default: 
                  item = ads.aB;
                }
                itemstack = new adq(item);
              }
              else
              {
                adq itemstack;
                if ((this.t.d instanceof aag))
                {
                  itemstack = new adq(((aag)this.t.d).j());
                }
                else
                {
                  adq itemstack;
                  if ((this.t.d instanceof xq))
                  {
                    itemstack = new adq(ads.ct);
                  }
                  else
                  {
                    adq itemstack;
                    if ((this.t.d instanceof wt))
                    {
                      itemstack = new adq(ads.cP);
                    }
                    else
                    {
                      String s = rt.b(this.t.d);
                      if (!rt.a.containsKey(s)) {
                        return;
                      }
                      itemstack = new adq(ads.bT);
                      aeu.a(itemstack, s);
                    }
                  }
                }
              }
            }
          }
        }
      }
      if (itemstack.b() == null)
      {
        String s1 = "";
        if (this.t.a == bbi.a.b) {
          s1 = ((kk)ajt.h.b(this.f.o(this.t.a()).t())).toString();
        } else if (this.t.a == bbi.a.c) {
          s1 = rt.b(this.t.d);
        }
        L.warn("Picking on: [{}] {} gave null item", new Object[] { this.t.a, s1 });
      }
      else
      {
        zi inventoryplayer = this.h.br;
        if (tileentity != null) {
          a(itemstack, tileentity);
        }
        int i = inventoryplayer.b(itemstack);
        if (flag)
        {
          inventoryplayer.a(itemstack);
          this.c.a(this.h.b(qm.a), 36 + inventoryplayer.d);
        }
        else if (i != -1)
        {
          if (zi.e(i)) {
            inventoryplayer.d = i;
          } else {
            this.c.a(i);
          }
        }
      }
    }
  }
  
  private adq a(adq p_184119_1_, apv p_184119_2_)
  {
    dn nbttagcompound = new dn();
    p_184119_2_.b(nbttagcompound);
    if ((p_184119_1_.b() == ads.ch) && (nbttagcompound.e("Owner")))
    {
      dn nbttagcompound2 = nbttagcompound.o("Owner");
      dn nbttagcompound3 = new dn();
      nbttagcompound3.a("SkullOwner", nbttagcompound2);
      p_184119_1_.d(nbttagcompound3);
      return p_184119_1_;
    }
    p_184119_1_.a("BlockEntityTag", nbttagcompound);
    dn nbttagcompound1 = new dn();
    du nbttaglist = new du();
    nbttaglist.a(new ea("(+NBT)"));
    nbttagcompound1.a("Lore", nbttaglist);
    p_184119_1_.a("display", nbttagcompound1);
    return p_184119_1_;
  }
  
  public b b(b theCrash)
  {
    theCrash.g().a("Launched Version", new Callable()
    {
      public String a()
        throws Exception
      {
        return bcf.a(bcf.this);
      }
    });
    theCrash.g().a("LWJGL", new Callable()
    {
      public String a()
      {
        return Sys.getVersion();
      }
    });
    theCrash.g().a("OpenGL", new Callable()
    {
      public String a()
      {
        return bni.u(7937) + " GL version " + bni.u(7938) + ", " + bni.u(7936);
      }
    });
    theCrash.g().a("GL Caps", new Callable()
    {
      public String call()
      {
        return bzg.c();
      }
    });
    theCrash.g().a("Using VBOs", new Callable()
    {
      public String call()
      {
        return bcf.this.u.u ? "Yes" : "No";
      }
    });
    theCrash.g().a("Is Modded", new Callable()
    {
      public String call()
        throws Exception
      {
        String s = ClientBrandRetriever.getClientModName();
        return bcf.class.getSigners() == null ? "Very likely; Jar signature invalidated" : !s.equals("vanilla") ? "Definitely; Client brand changed to '" + s + "'" : "Probably not. Jar signature remains and client brand is untouched.";
      }
    });
    theCrash.g().a("Type", new Callable()
    {
      public String a()
        throws Exception
      {
        return "Client (map_client.txt)";
      }
    });
    theCrash.g().a("Resource Packs", new Callable()
    {
      public String a()
        throws Exception
      {
        StringBuilder stringbuilder = new StringBuilder();
        for (String s : bcf.this.u.k)
        {
          if (stringbuilder.length() > 0) {
            stringbuilder.append(", ");
          }
          stringbuilder.append(s);
          if (bcf.this.u.l.contains(s)) {
            stringbuilder.append(" (incompatible)");
          }
        }
        return stringbuilder.toString();
      }
    });
    theCrash.g().a("Current Language", new Callable()
    {
      public String a()
        throws Exception
      {
        return bcf.b(bcf.this).c().toString();
      }
    });
    theCrash.g().a("Profiler Position", new Callable()
    {
      public String a()
        throws Exception
      {
        return bcf.this.B.a ? bcf.this.B.c() : "N/A (disabled)";
      }
    });
    theCrash.g().a("CPU", new Callable()
    {
      public String a()
      {
        return bzg.k();
      }
    });
    if (this.f != null) {
      this.f.a(theCrash);
    }
    return theCrash;
  }
  
  public static bcf z()
  {
    return T;
  }
  
  public ListenableFuture<Object> A()
  {
    a(new Runnable()
    {
      public void run()
      {
        bcf.this.f();
      }
    });
  }
  
  public void a(qw playerSnooper)
  {
    playerSnooper.a("fps", Integer.valueOf(ar));
    playerSnooper.a("vsync_enabled", Boolean.valueOf(this.u.t));
    playerSnooper.a("display_frequency", Integer.valueOf(Display.getDisplayMode().getFrequency()));
    playerSnooper.a("display_type", this.V ? "fullscreen" : "windowed");
    playerSnooper.a("run_time", Long.valueOf((MinecraftServer.av() - playerSnooper.g()) / 60L * 1000L));
    playerSnooper.a("current_action", aD());
    String s = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN ? "little" : "big";
    playerSnooper.a("endianness", s);
    playerSnooper.a("subtitles", Boolean.valueOf(this.u.N));
    playerSnooper.a("resource_packs", Integer.valueOf(this.aF.d().size()));
    int i = 0;
    for (bwk.a resourcepackrepository$entry : this.aF.d()) {
      playerSnooper.a("resource_pack[" + i++ + "]", resourcepackrepository$entry.d());
    }
    if ((this.al != null) && (this.al.ar() != null)) {
      playerSnooper.a("snooper_partner", this.al.ar().f());
    }
  }
  
  private String aD()
  {
    return this.R != null ? "multiplayer" : this.R.d() ? "playing_lan" : this.al != null ? "singleplayer" : this.al.a() ? "hosting_lan" : "out_of_game";
  }
  
  public void b(qw playerSnooper)
  {
    playerSnooper.b("opengl_version", bni.u(7938));
    playerSnooper.b("opengl_vendor", bni.u(7936));
    playerSnooper.b("client_brand", ClientBrandRetriever.getClientModName());
    playerSnooper.b("launched_version", this.an);
    ContextCapabilities contextcapabilities = GLContext.getCapabilities();
    playerSnooper.b("gl_caps[ARB_arrays_of_arrays]", Boolean.valueOf(contextcapabilities.GL_ARB_arrays_of_arrays));
    playerSnooper.b("gl_caps[ARB_base_instance]", Boolean.valueOf(contextcapabilities.GL_ARB_base_instance));
    playerSnooper.b("gl_caps[ARB_blend_func_extended]", Boolean.valueOf(contextcapabilities.GL_ARB_blend_func_extended));
    playerSnooper.b("gl_caps[ARB_clear_buffer_object]", Boolean.valueOf(contextcapabilities.GL_ARB_clear_buffer_object));
    playerSnooper.b("gl_caps[ARB_color_buffer_float]", Boolean.valueOf(contextcapabilities.GL_ARB_color_buffer_float));
    playerSnooper.b("gl_caps[ARB_compatibility]", Boolean.valueOf(contextcapabilities.GL_ARB_compatibility));
    playerSnooper.b("gl_caps[ARB_compressed_texture_pixel_storage]", Boolean.valueOf(contextcapabilities.GL_ARB_compressed_texture_pixel_storage));
    playerSnooper.b("gl_caps[ARB_compute_shader]", Boolean.valueOf(contextcapabilities.GL_ARB_compute_shader));
    playerSnooper.b("gl_caps[ARB_copy_buffer]", Boolean.valueOf(contextcapabilities.GL_ARB_copy_buffer));
    playerSnooper.b("gl_caps[ARB_copy_image]", Boolean.valueOf(contextcapabilities.GL_ARB_copy_image));
    playerSnooper.b("gl_caps[ARB_depth_buffer_float]", Boolean.valueOf(contextcapabilities.GL_ARB_depth_buffer_float));
    playerSnooper.b("gl_caps[ARB_compute_shader]", Boolean.valueOf(contextcapabilities.GL_ARB_compute_shader));
    playerSnooper.b("gl_caps[ARB_copy_buffer]", Boolean.valueOf(contextcapabilities.GL_ARB_copy_buffer));
    playerSnooper.b("gl_caps[ARB_copy_image]", Boolean.valueOf(contextcapabilities.GL_ARB_copy_image));
    playerSnooper.b("gl_caps[ARB_depth_buffer_float]", Boolean.valueOf(contextcapabilities.GL_ARB_depth_buffer_float));
    playerSnooper.b("gl_caps[ARB_depth_clamp]", Boolean.valueOf(contextcapabilities.GL_ARB_depth_clamp));
    playerSnooper.b("gl_caps[ARB_depth_texture]", Boolean.valueOf(contextcapabilities.GL_ARB_depth_texture));
    playerSnooper.b("gl_caps[ARB_draw_buffers]", Boolean.valueOf(contextcapabilities.GL_ARB_draw_buffers));
    playerSnooper.b("gl_caps[ARB_draw_buffers_blend]", Boolean.valueOf(contextcapabilities.GL_ARB_draw_buffers_blend));
    playerSnooper.b("gl_caps[ARB_draw_elements_base_vertex]", Boolean.valueOf(contextcapabilities.GL_ARB_draw_elements_base_vertex));
    playerSnooper.b("gl_caps[ARB_draw_indirect]", Boolean.valueOf(contextcapabilities.GL_ARB_draw_indirect));
    playerSnooper.b("gl_caps[ARB_draw_instanced]", Boolean.valueOf(contextcapabilities.GL_ARB_draw_instanced));
    playerSnooper.b("gl_caps[ARB_explicit_attrib_location]", Boolean.valueOf(contextcapabilities.GL_ARB_explicit_attrib_location));
    playerSnooper.b("gl_caps[ARB_explicit_uniform_location]", Boolean.valueOf(contextcapabilities.GL_ARB_explicit_uniform_location));
    playerSnooper.b("gl_caps[ARB_fragment_layer_viewport]", Boolean.valueOf(contextcapabilities.GL_ARB_fragment_layer_viewport));
    playerSnooper.b("gl_caps[ARB_fragment_program]", Boolean.valueOf(contextcapabilities.GL_ARB_fragment_program));
    playerSnooper.b("gl_caps[ARB_fragment_shader]", Boolean.valueOf(contextcapabilities.GL_ARB_fragment_shader));
    playerSnooper.b("gl_caps[ARB_fragment_program_shadow]", Boolean.valueOf(contextcapabilities.GL_ARB_fragment_program_shadow));
    playerSnooper.b("gl_caps[ARB_framebuffer_object]", Boolean.valueOf(contextcapabilities.GL_ARB_framebuffer_object));
    playerSnooper.b("gl_caps[ARB_framebuffer_sRGB]", Boolean.valueOf(contextcapabilities.GL_ARB_framebuffer_sRGB));
    playerSnooper.b("gl_caps[ARB_geometry_shader4]", Boolean.valueOf(contextcapabilities.GL_ARB_geometry_shader4));
    playerSnooper.b("gl_caps[ARB_gpu_shader5]", Boolean.valueOf(contextcapabilities.GL_ARB_gpu_shader5));
    playerSnooper.b("gl_caps[ARB_half_float_pixel]", Boolean.valueOf(contextcapabilities.GL_ARB_half_float_pixel));
    playerSnooper.b("gl_caps[ARB_half_float_vertex]", Boolean.valueOf(contextcapabilities.GL_ARB_half_float_vertex));
    playerSnooper.b("gl_caps[ARB_instanced_arrays]", Boolean.valueOf(contextcapabilities.GL_ARB_instanced_arrays));
    playerSnooper.b("gl_caps[ARB_map_buffer_alignment]", Boolean.valueOf(contextcapabilities.GL_ARB_map_buffer_alignment));
    playerSnooper.b("gl_caps[ARB_map_buffer_range]", Boolean.valueOf(contextcapabilities.GL_ARB_map_buffer_range));
    playerSnooper.b("gl_caps[ARB_multisample]", Boolean.valueOf(contextcapabilities.GL_ARB_multisample));
    playerSnooper.b("gl_caps[ARB_multitexture]", Boolean.valueOf(contextcapabilities.GL_ARB_multitexture));
    playerSnooper.b("gl_caps[ARB_occlusion_query2]", Boolean.valueOf(contextcapabilities.GL_ARB_occlusion_query2));
    playerSnooper.b("gl_caps[ARB_pixel_buffer_object]", Boolean.valueOf(contextcapabilities.GL_ARB_pixel_buffer_object));
    playerSnooper.b("gl_caps[ARB_seamless_cube_map]", Boolean.valueOf(contextcapabilities.GL_ARB_seamless_cube_map));
    playerSnooper.b("gl_caps[ARB_shader_objects]", Boolean.valueOf(contextcapabilities.GL_ARB_shader_objects));
    playerSnooper.b("gl_caps[ARB_shader_stencil_export]", Boolean.valueOf(contextcapabilities.GL_ARB_shader_stencil_export));
    playerSnooper.b("gl_caps[ARB_shader_texture_lod]", Boolean.valueOf(contextcapabilities.GL_ARB_shader_texture_lod));
    playerSnooper.b("gl_caps[ARB_shadow]", Boolean.valueOf(contextcapabilities.GL_ARB_shadow));
    playerSnooper.b("gl_caps[ARB_shadow_ambient]", Boolean.valueOf(contextcapabilities.GL_ARB_shadow_ambient));
    playerSnooper.b("gl_caps[ARB_stencil_texturing]", Boolean.valueOf(contextcapabilities.GL_ARB_stencil_texturing));
    playerSnooper.b("gl_caps[ARB_sync]", Boolean.valueOf(contextcapabilities.GL_ARB_sync));
    playerSnooper.b("gl_caps[ARB_tessellation_shader]", Boolean.valueOf(contextcapabilities.GL_ARB_tessellation_shader));
    playerSnooper.b("gl_caps[ARB_texture_border_clamp]", Boolean.valueOf(contextcapabilities.GL_ARB_texture_border_clamp));
    playerSnooper.b("gl_caps[ARB_texture_buffer_object]", Boolean.valueOf(contextcapabilities.GL_ARB_texture_buffer_object));
    playerSnooper.b("gl_caps[ARB_texture_cube_map]", Boolean.valueOf(contextcapabilities.GL_ARB_texture_cube_map));
    playerSnooper.b("gl_caps[ARB_texture_cube_map_array]", Boolean.valueOf(contextcapabilities.GL_ARB_texture_cube_map_array));
    playerSnooper.b("gl_caps[ARB_texture_non_power_of_two]", Boolean.valueOf(contextcapabilities.GL_ARB_texture_non_power_of_two));
    playerSnooper.b("gl_caps[ARB_uniform_buffer_object]", Boolean.valueOf(contextcapabilities.GL_ARB_uniform_buffer_object));
    playerSnooper.b("gl_caps[ARB_vertex_blend]", Boolean.valueOf(contextcapabilities.GL_ARB_vertex_blend));
    playerSnooper.b("gl_caps[ARB_vertex_buffer_object]", Boolean.valueOf(contextcapabilities.GL_ARB_vertex_buffer_object));
    playerSnooper.b("gl_caps[ARB_vertex_program]", Boolean.valueOf(contextcapabilities.GL_ARB_vertex_program));
    playerSnooper.b("gl_caps[ARB_vertex_shader]", Boolean.valueOf(contextcapabilities.GL_ARB_vertex_shader));
    playerSnooper.b("gl_caps[EXT_bindable_uniform]", Boolean.valueOf(contextcapabilities.GL_EXT_bindable_uniform));
    playerSnooper.b("gl_caps[EXT_blend_equation_separate]", Boolean.valueOf(contextcapabilities.GL_EXT_blend_equation_separate));
    playerSnooper.b("gl_caps[EXT_blend_func_separate]", Boolean.valueOf(contextcapabilities.GL_EXT_blend_func_separate));
    playerSnooper.b("gl_caps[EXT_blend_minmax]", Boolean.valueOf(contextcapabilities.GL_EXT_blend_minmax));
    playerSnooper.b("gl_caps[EXT_blend_subtract]", Boolean.valueOf(contextcapabilities.GL_EXT_blend_subtract));
    playerSnooper.b("gl_caps[EXT_draw_instanced]", Boolean.valueOf(contextcapabilities.GL_EXT_draw_instanced));
    playerSnooper.b("gl_caps[EXT_framebuffer_multisample]", Boolean.valueOf(contextcapabilities.GL_EXT_framebuffer_multisample));
    playerSnooper.b("gl_caps[EXT_framebuffer_object]", Boolean.valueOf(contextcapabilities.GL_EXT_framebuffer_object));
    playerSnooper.b("gl_caps[EXT_framebuffer_sRGB]", Boolean.valueOf(contextcapabilities.GL_EXT_framebuffer_sRGB));
    playerSnooper.b("gl_caps[EXT_geometry_shader4]", Boolean.valueOf(contextcapabilities.GL_EXT_geometry_shader4));
    playerSnooper.b("gl_caps[EXT_gpu_program_parameters]", Boolean.valueOf(contextcapabilities.GL_EXT_gpu_program_parameters));
    playerSnooper.b("gl_caps[EXT_gpu_shader4]", Boolean.valueOf(contextcapabilities.GL_EXT_gpu_shader4));
    playerSnooper.b("gl_caps[EXT_multi_draw_arrays]", Boolean.valueOf(contextcapabilities.GL_EXT_multi_draw_arrays));
    playerSnooper.b("gl_caps[EXT_packed_depth_stencil]", Boolean.valueOf(contextcapabilities.GL_EXT_packed_depth_stencil));
    playerSnooper.b("gl_caps[EXT_paletted_texture]", Boolean.valueOf(contextcapabilities.GL_EXT_paletted_texture));
    playerSnooper.b("gl_caps[EXT_rescale_normal]", Boolean.valueOf(contextcapabilities.GL_EXT_rescale_normal));
    playerSnooper.b("gl_caps[EXT_separate_shader_objects]", Boolean.valueOf(contextcapabilities.GL_EXT_separate_shader_objects));
    playerSnooper.b("gl_caps[EXT_shader_image_load_store]", Boolean.valueOf(contextcapabilities.GL_EXT_shader_image_load_store));
    playerSnooper.b("gl_caps[EXT_shadow_funcs]", Boolean.valueOf(contextcapabilities.GL_EXT_shadow_funcs));
    playerSnooper.b("gl_caps[EXT_shared_texture_palette]", Boolean.valueOf(contextcapabilities.GL_EXT_shared_texture_palette));
    playerSnooper.b("gl_caps[EXT_stencil_clear_tag]", Boolean.valueOf(contextcapabilities.GL_EXT_stencil_clear_tag));
    playerSnooper.b("gl_caps[EXT_stencil_two_side]", Boolean.valueOf(contextcapabilities.GL_EXT_stencil_two_side));
    playerSnooper.b("gl_caps[EXT_stencil_wrap]", Boolean.valueOf(contextcapabilities.GL_EXT_stencil_wrap));
    playerSnooper.b("gl_caps[EXT_texture_3d]", Boolean.valueOf(contextcapabilities.GL_EXT_texture_3d));
    playerSnooper.b("gl_caps[EXT_texture_array]", Boolean.valueOf(contextcapabilities.GL_EXT_texture_array));
    playerSnooper.b("gl_caps[EXT_texture_buffer_object]", Boolean.valueOf(contextcapabilities.GL_EXT_texture_buffer_object));
    playerSnooper.b("gl_caps[EXT_texture_integer]", Boolean.valueOf(contextcapabilities.GL_EXT_texture_integer));
    playerSnooper.b("gl_caps[EXT_texture_lod_bias]", Boolean.valueOf(contextcapabilities.GL_EXT_texture_lod_bias));
    playerSnooper.b("gl_caps[EXT_texture_sRGB]", Boolean.valueOf(contextcapabilities.GL_EXT_texture_sRGB));
    playerSnooper.b("gl_caps[EXT_vertex_shader]", Boolean.valueOf(contextcapabilities.GL_EXT_vertex_shader));
    playerSnooper.b("gl_caps[EXT_vertex_weighting]", Boolean.valueOf(contextcapabilities.GL_EXT_vertex_weighting));
    playerSnooper.b("gl_caps[gl_max_vertex_uniforms]", Integer.valueOf(bni.v(35658)));
    bni.L();
    playerSnooper.b("gl_caps[gl_max_fragment_uniforms]", Integer.valueOf(bni.v(35657)));
    bni.L();
    playerSnooper.b("gl_caps[gl_max_vertex_attribs]", Integer.valueOf(bni.v(34921)));
    bni.L();
    playerSnooper.b("gl_caps[gl_max_vertex_texture_image_units]", Integer.valueOf(bni.v(35660)));
    bni.L();
    playerSnooper.b("gl_caps[gl_max_texture_image_units]", Integer.valueOf(bni.v(34930)));
    bni.L();
    playerSnooper.b("gl_caps[gl_max_array_texture_layers]", Integer.valueOf(bni.v(35071)));
    bni.L();
    playerSnooper.b("gl_max_texture_size", Integer.valueOf(B()));
  }
  
  public static int B()
  {
    for (int i = 16384; i > 0; i >>= 1)
    {
      bni.a(32868, 0, 6408, i, i, 0, 6408, 5121, (IntBuffer)null);
      int j = bni.c(32868, 0, 4096);
      if (j != 0) {
        return i;
      }
    }
    return -1;
  }
  
  public boolean Z()
  {
    return this.u.r;
  }
  
  public void a(bkx serverDataIn)
  {
    this.R = serverDataIn;
  }
  
  public bkx C()
  {
    return this.R;
  }
  
  public boolean D()
  {
    return this.az;
  }
  
  public boolean E()
  {
    return (this.az) && (this.al != null);
  }
  
  public byn F()
  {
    return this.al;
  }
  
  public static void G()
  {
    if (T != null)
    {
      byn integratedserver = T.F();
      if (integratedserver != null) {
        integratedserver.u();
      }
    }
  }
  
  public qw H()
  {
    return this.ab;
  }
  
  public static long I()
  {
    return Sys.getTime() * 1000L / Sys.getTimerResolution();
  }
  
  public boolean J()
  {
    return this.V;
  }
  
  public bcm K()
  {
    return this.ag;
  }
  
  public PropertyMap L()
  {
    if (this.Q.isEmpty())
    {
      GameProfile gameprofile = X().fillProfileProperties(this.ag.e(), false);
      this.Q.putAll(gameprofile.getProperties());
    }
    return this.Q;
  }
  
  public Proxy M()
  {
    return this.ap;
  }
  
  public bvi N()
  {
    return this.S;
  }
  
  public bwg O()
  {
    return this.aB;
  }
  
  public bwk P()
  {
    return this.aF;
  }
  
  public bwq Q()
  {
    return this.aG;
  }
  
  public bvg R()
  {
    return this.aK;
  }
  
  public boolean S()
  {
    return this.aw;
  }
  
  public boolean T()
  {
    return this.ah;
  }
  
  public byx U()
  {
    return this.aL;
  }
  
  public byu.a V()
  {
    return this.h != null ? byu.a.b : (this.h.bJ.d) && (this.h.bJ.c) ? byu.a.c : (this.h.l.s instanceof atb) ? byu.a.g : this.r.j().d() ? byu.a.f : (this.h.l.s instanceof asx) ? byu.a.e : byu.a.a;
  }
  
  public void W()
  {
    int i = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 'Ā' : Keyboard.getEventKey();
    if ((i != 0) && (!Keyboard.isRepeatEvent())) {
      if ((!(this.m instanceof bfr)) || (((bfr)this.m).g <= I() - 20L)) {
        if (Keyboard.getEventKeyState()) {
          if (i == this.u.ai.j()) {
            r();
          } else if (i == this.u.af.j()) {
            this.r.d().a(bcj.a(this.w, this.d, this.e, this.aJ));
          }
        }
      }
    }
  }
  
  public MinecraftSessionService X()
  {
    return this.aO;
  }
  
  public bwn Y()
  {
    return this.aP;
  }
  
  public rr aa()
  {
    return this.af;
  }
  
  public void a(rr viewingEntity)
  {
    this.af = viewingEntity;
    this.o.a(viewingEntity);
  }
  
  public <V> ListenableFuture<V> a(Callable<V> callableToSchedule)
  {
    Validate.notNull(callableToSchedule);
    if (!aE())
    {
      ListenableFutureTask<V> listenablefuturetask = ListenableFutureTask.create(callableToSchedule);
      synchronized (this.aQ)
      {
        this.aQ.add(listenablefuturetask);
        return listenablefuturetask;
      }
    }
    try
    {
      return Futures.immediateFuture(callableToSchedule.call());
    }
    catch (Exception exception)
    {
      return Futures.immediateFailedCheckedFuture(exception);
    }
  }
  
  public ListenableFuture<Object> a(Runnable runnableToSchedule)
  {
    Validate.notNull(runnableToSchedule);
    return a(Executors.callable(runnableToSchedule));
  }
  
  public boolean aE()
  {
    return Thread.currentThread() == this.aS;
  }
  
  public boc ab()
  {
    return this.aU;
  }
  
  public brm ac()
  {
    return this.ac;
  }
  
  public brz ad()
  {
    return this.ad;
  }
  
  public bnk ae()
  {
    return this.ae;
  }
  
  public static int af()
  {
    return ar;
  }
  
  public oc ag()
  {
    return this.z;
  }
  
  public static Map<String, String> ah()
  {
    Map<String, String> map = Maps.newHashMap();
    map.put("X-Minecraft-Username", z().K().c());
    map.put("X-Minecraft-UUID", z().K().b());
    map.put("X-Minecraft-Version", "1.9");
    return map;
  }
  
  public boolean ai()
  {
    return this.Z;
  }
  
  public void a(boolean isConnected)
  {
    this.Z = isConnected;
  }
  
  public pb aj()
  {
    return this.U;
  }
  
  public float ak()
  {
    return this.aa.c;
  }
  
  public bco al()
  {
    return this.aH;
  }
}
