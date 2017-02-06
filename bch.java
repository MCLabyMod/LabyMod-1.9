import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import shadersmod.client.Shaders;

public class bch
{
  private static final Logger aD = ;
  private static final Gson aE = new Gson();
  private static final ParameterizedType aF = new ParameterizedType()
  {
    public Type[] getActualTypeArguments()
    {
      return new Type[] { String.class };
    }
    
    public Type getRawType()
    {
      return List.class;
    }
    
    public Type getOwnerType()
    {
      return null;
    }
  };
  private static final String[] aG = { "options.guiScale.auto", "options.guiScale.small", "options.guiScale.normal", "options.guiScale.large" };
  private static final String[] aH = { "options.particles.all", "options.particles.decreased", "options.particles.minimal" };
  private static final String[] aI = { "options.ao.off", "options.ao.min", "options.ao.max" };
  private static final String[] aJ = { "options.off", "options.graphics.fast", "options.graphics.fancy" };
  private static final String[] aK = { "options.off", "options.attack.crosshair", "options.attack.hotbar" };
  public float a = 0.5F;
  public boolean b;
  public int c = -1;
  public boolean d = true;
  public boolean e;
  public boolean f = true;
  public int g = 120;
  public int h = 2;
  public boolean i = true;
  public int j = 2;
  public List<String> k = Lists.newArrayList();
  public List<String> l = Lists.newArrayList();
  public zj.b m = zj.b.a;
  public boolean n = true;
  public boolean o = true;
  public boolean p = true;
  public float q = 1.0F;
  public boolean r = true;
  public boolean s;
  public boolean t = true;
  public boolean u = false;
  public boolean v = false;
  public boolean w;
  public boolean x;
  public boolean y = true;
  private final Set<zk> aL = Sets.newHashSet(zk.values());
  public boolean z;
  public rz A = rz.b;
  public int B;
  public int C;
  public boolean D = true;
  public float E = 1.0F;
  public float F = 1.0F;
  public float G = 0.44366196F;
  public float H = 1.0F;
  public boolean I = true;
  public int J = 4;
  private Map<nh, Float> aM = Maps.newEnumMap(nh.class);
  public boolean K = true;
  public boolean L = true;
  public int M = 0;
  public boolean N = false;
  public boolean O = true;
  public bcc P = new bcc("key.forward", 17, "key.categories.movement");
  public bcc Q = new bcc("key.left", 30, "key.categories.movement");
  public bcc R = new bcc("key.back", 31, "key.categories.movement");
  public bcc S = new bcc("key.right", 32, "key.categories.movement");
  public bcc T = new bcc("key.jump", 57, "key.categories.movement");
  public bcc U = new bcc("key.sneak", 42, "key.categories.movement");
  public bcc V = new bcc("key.sprint", 29, "key.categories.movement");
  public bcc W = new bcc("key.inventory", 18, "key.categories.inventory");
  public bcc X = new bcc("key.swapHands", 33, "key.categories.inventory");
  public bcc Y = new bcc("key.drop", 16, "key.categories.inventory");
  public bcc Z = new bcc("key.use", -99, "key.categories.gameplay");
  public bcc aa = new bcc("key.attack", -100, "key.categories.gameplay");
  public bcc ab = new bcc("key.pickItem", -98, "key.categories.gameplay");
  public bcc ac = new bcc("key.chat", 20, "key.categories.multiplayer");
  public bcc ad = new bcc("key.playerlist", 15, "key.categories.multiplayer");
  public bcc ae = new bcc("key.command", 53, "key.categories.multiplayer");
  public bcc af = new bcc("key.screenshot", 60, "key.categories.misc");
  public bcc ag = new bcc("key.togglePerspective", 63, "key.categories.misc");
  public bcc ah = new bcc("key.smoothCamera", 0, "key.categories.misc");
  public bcc ai = new bcc("key.fullscreen", 87, "key.categories.misc");
  public bcc aj = new bcc("key.spectatorOutlines", 0, "key.categories.misc");
  public bcc[] ak = { new bcc("key.hotbar.1", 2, "key.categories.inventory"), new bcc("key.hotbar.2", 3, "key.categories.inventory"), new bcc("key.hotbar.3", 4, "key.categories.inventory"), new bcc("key.hotbar.4", 5, "key.categories.inventory"), new bcc("key.hotbar.5", 6, "key.categories.inventory"), new bcc("key.hotbar.6", 7, "key.categories.inventory"), new bcc("key.hotbar.7", 8, "key.categories.inventory"), new bcc("key.hotbar.8", 9, "key.categories.inventory"), new bcc("key.hotbar.9", 10, "key.categories.inventory") };
  public bcc[] al;
  protected bcf am;
  private File aN;
  public qk an;
  public boolean ao;
  public int ap;
  public boolean aq;
  public boolean ar;
  public boolean as;
  public String at;
  public boolean au;
  public boolean av;
  public float aw;
  public float ax;
  public float ay;
  public int az;
  public int aA;
  public String aB;
  public boolean aC;
  public int ofFogType = 1;
  public float ofFogStart = 0.8F;
  public int ofMipmapType = 0;
  public boolean ofOcclusionFancy = false;
  public boolean ofSmoothFps = false;
  public boolean ofSmoothWorld = Config.isSingleProcessor();
  public boolean ofLazyChunkLoading = Config.isSingleProcessor();
  public float ofAoLevel = 1.0F;
  public int ofAaLevel = 0;
  public int ofAfLevel = 1;
  public int ofClouds = 0;
  public float ofCloudsHeight = 0.0F;
  public int ofTrees = 0;
  public int ofRain = 0;
  public int ofDroppedItems = 0;
  public int ofBetterGrass = 3;
  public int ofAutoSaveTicks = 4000;
  public boolean ofLagometer = false;
  public boolean ofProfiler = false;
  public boolean ofShowFps = false;
  public boolean ofWeather = true;
  public boolean ofSky = true;
  public boolean ofStars = true;
  public boolean ofSunMoon = true;
  public int ofVignette = 0;
  public int ofChunkUpdates = 1;
  public int ofChunkLoading = 0;
  public boolean ofChunkUpdatesDynamic = false;
  public int ofTime = 0;
  public boolean ofClearWater = false;
  public boolean ofBetterSnow = false;
  public String ofFullscreenMode = "Default";
  public boolean ofSwampColors = true;
  public boolean ofRandomMobs = true;
  public boolean ofSmoothBiomes = true;
  public boolean ofCustomFonts = true;
  public boolean ofCustomColors = true;
  public boolean ofCustomSky = true;
  public boolean ofShowCapes = true;
  public int ofConnectedTextures = 2;
  public boolean ofCustomItems = true;
  public boolean ofNaturalTextures = false;
  public boolean ofFastMath = false;
  public boolean ofFastRender = true;
  public int ofTranslucentBlocks = 0;
  public int ofAnimatedWater = 0;
  public int ofAnimatedLava = 0;
  public boolean ofAnimatedFire = true;
  public boolean ofAnimatedPortal = true;
  public boolean ofAnimatedRedstone = true;
  public boolean ofAnimatedExplosion = true;
  public boolean ofAnimatedFlame = true;
  public boolean ofAnimatedSmoke = true;
  public boolean ofVoidParticles = true;
  public boolean ofWaterParticles = true;
  public boolean ofRainSplash = true;
  public boolean ofPortalParticles = true;
  public boolean ofPotionParticles = true;
  public boolean ofFireworkParticles = true;
  public boolean ofDrippingWaterLava = true;
  public boolean ofAnimatedTerrain = true;
  public boolean ofAnimatedTextures = true;
  public static final int DEFAULT = 0;
  public static final int FAST = 1;
  public static final int FANCY = 2;
  public static final int OFF = 3;
  public static final int SMART = 4;
  public static final int ANIM_ON = 0;
  public static final int ANIM_GENERATED = 1;
  public static final int ANIM_OFF = 2;
  public static final int CL_DEFAULT = 0;
  public static final int CL_SMOOTH = 1;
  public static final int CL_THREADED = 2;
  public static final String DEFAULT_STR = "Default";
  private static final int[] OF_TREES_VALUES = { 0, 1, 4, 2 };
  public bcc ofKeyBindZoom;
  private File optionsFileOF;
  
  public bch(bcf mcIn, File optionsFileIn)
  {
    this.al = ((bcc[])ArrayUtils.addAll(new bcc[] { this.aa, this.Z, this.P, this.Q, this.R, this.S, this.T, this.U, this.V, this.Y, this.W, this.ac, this.ad, this.ab, this.ae, this.af, this.ag, this.ah, this.ai, this.aj, this.X }, this.ak));
    this.an = qk.c;
    this.at = "";
    this.aw = 70.0F;
    this.aB = "en_US";
    this.aC = false;
    this.am = mcIn;
    this.aN = new File(optionsFileIn, "options.txt");
    if ((mcIn.S()) && (Runtime.getRuntime().maxMemory() >= 1000000000L)) {
      bch.a.f.a(32.0F);
    } else {
      bch.a.f.a(16.0F);
    }
    this.c = (mcIn.S() ? 12 : 8);
    
    this.optionsFileOF = new File(optionsFileIn, "optionsof.txt");
    
    this.g = ((int)bch.a.i.f());
    
    this.ofKeyBindZoom = new bcc("Zoom", 46, "key.categories.misc");
    this.al = ((bcc[])ArrayUtils.add(this.al, this.ofKeyBindZoom));
    
    bch.a.f.a(32.0F);
    
    this.c = 8;
    
    a();
    
    Config.initGameSettings(this);
  }
  
  public bch()
  {
    this.al = ((bcc[])ArrayUtils.addAll(new bcc[] { this.aa, this.Z, this.P, this.Q, this.R, this.S, this.T, this.U, this.V, this.Y, this.W, this.ac, this.ad, this.ab, this.ae, this.af, this.ag, this.ah, this.ai, this.aj, this.X }, this.ak));
    this.an = qk.c;
    this.at = "";
    this.aw = 70.0F;
    this.aB = "en_US";
    this.aC = false;
  }
  
  public static String c(int key)
  {
    return key < 256 ? Keyboard.getKeyName(key) : key < 0 ? bwo.a("key.mouseButton", new Object[] { Integer.valueOf(key + 101) }) : String.format("%c", new Object[] { Character.valueOf((char)(key - 256)) }).toUpperCase();
  }
  
  public static boolean a(bcc key)
  {
    return key.j() < 0 ? Mouse.isButtonDown(key.j() + 100) : key.j() == 0 ? false : Keyboard.isKeyDown(key.j());
  }
  
  public void a(bcc key, int keyCode)
  {
    key.b(keyCode);
    b();
  }
  
  public void a(bch.a settingsOption, float value)
  {
    setOptionFloatValueOF(settingsOption, value);
    if (settingsOption == bch.a.b) {
      this.a = value;
    }
    if (settingsOption == bch.a.c) {
      this.aw = value;
    }
    if (settingsOption == bch.a.d) {
      this.ax = value;
    }
    if (settingsOption == bch.a.i)
    {
      this.g = ((int)value);
      
      this.t = false;
      if (this.g <= 0)
      {
        this.g = ((int)bch.a.i.f());
        this.t = true;
      }
      updateVSync();
    }
    if (settingsOption == bch.a.s)
    {
      this.q = value;
      this.am.r.d().b();
    }
    if (settingsOption == bch.a.B)
    {
      this.H = value;
      this.am.r.d().b();
    }
    if (settingsOption == bch.a.C)
    {
      this.G = value;
      this.am.r.d().b();
    }
    if (settingsOption == bch.a.A)
    {
      this.F = value;
      this.am.r.d().b();
    }
    if (settingsOption == bch.a.z)
    {
      this.E = value;
      this.am.r.d().b();
    }
    if (settingsOption == bch.a.D)
    {
      int i = this.J;
      this.J = ((int)value);
      if (i != value)
      {
        this.am.R().a(this.J);
        this.am.N().a(bvg.g);
        this.am.R().a(false, this.J > 0);
        this.am.A();
      }
    }
    if (settingsOption == bch.a.f)
    {
      this.c = ((int)value);
      this.am.g.o();
    }
  }
  
  public void a(bch.a settingsOption, int value)
  {
    setOptionValueOF(settingsOption, value);
    if (settingsOption == bch.a.f) {
      a(settingsOption, on.a(this.c + value, settingsOption.e(), settingsOption.f()));
    }
    if (settingsOption == bch.a.H) {
      this.A = this.A.a();
    }
    if (settingsOption == bch.a.a) {
      this.b = (!this.b);
    }
    if (settingsOption == bch.a.n) {
      this.az = (this.az + value & 0x3);
    }
    if (settingsOption == bch.a.o) {
      this.aA = ((this.aA + value) % 3);
    }
    if (settingsOption == bch.a.g) {
      this.d = (!this.d);
    }
    if (settingsOption == bch.a.k) {
      this.h = ((this.h + value) % 3);
    }
    if (settingsOption == bch.a.E)
    {
      this.aC = (!this.aC);
      this.am.k.a((this.am.Q().a()) || (this.aC));
    }
    if (settingsOption == bch.a.j) {
      this.f = (!this.f);
    }
    if (settingsOption == bch.a.h)
    {
      this.e = (!this.e);
      this.am.f();
    }
    if (settingsOption == bch.a.l)
    {
      this.i = (!this.i);
      
      updateRenderClouds();
      
      this.am.g.a();
    }
    if (settingsOption == bch.a.m)
    {
      this.j = ((this.j + value) % 3);
      this.am.g.a();
    }
    if (settingsOption == bch.a.p) {
      this.m = zj.b.a((this.m.a() + value) % 3);
    }
    if (settingsOption == bch.a.q) {
      this.n = (!this.n);
    }
    if (settingsOption == bch.a.r) {
      this.o = (!this.o);
    }
    if (settingsOption == bch.a.t) {
      this.p = (!this.p);
    }
    if (settingsOption == bch.a.u) {
      this.r = (!this.r);
    }
    if (settingsOption == bch.a.y) {
      this.z = (!this.z);
    }
    if (settingsOption == bch.a.v)
    {
      this.s = (!this.s);
      if (this.am.J() != this.s) {
        this.am.r();
      }
    }
    if (settingsOption == bch.a.w)
    {
      this.t = (!this.t);
      Display.setVSyncEnabled(this.t);
    }
    if (settingsOption == bch.a.x)
    {
      this.u = (!this.u);
      this.am.g.a();
    }
    if (settingsOption == bch.a.F) {
      this.v = (!this.v);
    }
    if (settingsOption == bch.a.G) {
      this.L = (!this.L);
    }
    if (settingsOption == bch.a.I) {
      this.M = ((this.M + value) % 3);
    }
    if (settingsOption == bch.a.J) {
      this.N = (!this.N);
    }
    if (settingsOption == bch.a.K) {
      this.O = (!this.O);
    }
    b();
  }
  
  public float a(bch.a settingOption)
  {
    if (settingOption == bch.a.CLOUD_HEIGHT) {
      return this.ofCloudsHeight;
    }
    if (settingOption == bch.a.AO_LEVEL) {
      return this.ofAoLevel;
    }
    if (settingOption == bch.a.AA_LEVEL) {
      return this.ofAaLevel;
    }
    if (settingOption == bch.a.AF_LEVEL) {
      return this.ofAfLevel;
    }
    if (settingOption == bch.a.MIPMAP_TYPE) {
      return this.ofMipmapType;
    }
    if (settingOption == bch.a.i)
    {
      if ((this.g == bch.a.i.f()) && (this.t)) {
        return 0.0F;
      }
      return this.g;
    }
    return settingOption == bch.a.f ? this.c : settingOption == bch.a.D ? this.J : settingOption == bch.a.i ? this.g : settingOption == bch.a.A ? this.F : settingOption == bch.a.z ? this.E : settingOption == bch.a.C ? this.G : settingOption == bch.a.B ? this.H : settingOption == bch.a.s ? this.q : settingOption == bch.a.b ? this.a : settingOption == bch.a.e ? this.ay : settingOption == bch.a.d ? this.ax : settingOption == bch.a.c ? this.aw : 0.0F;
  }
  
  public boolean b(bch.a settingOption)
  {
    switch (settingOption)
    {
    case a: 
      return this.b;
    case g: 
      return this.d;
    case h: 
      return this.e;
    case j: 
      return this.f;
    case q: 
      return this.n;
    case r: 
      return this.o;
    case t: 
      return this.p;
    case u: 
      return this.r;
    case v: 
      return this.s;
    case w: 
      return this.t;
    case x: 
      return this.u;
    case y: 
      return this.z;
    case E: 
      return this.aC;
    case F: 
      return this.v;
    case G: 
      return this.L;
    case J: 
      return this.N;
    case K: 
      return this.O;
    }
    return false;
  }
  
  private static String a(String[] strArray, int index)
  {
    if ((index < 0) || (index >= strArray.length)) {
      index = 0;
    }
    return bwo.a(strArray[index], new Object[0]);
  }
  
  public String c(bch.a settingOption)
  {
    String strOF = getKeyBindingOF(settingOption);
    if (strOF != null) {
      return strOF;
    }
    String s = bwo.a(settingOption.d(), new Object[0]) + ": ";
    if (settingOption.a())
    {
      float f1 = a(settingOption);
      float f = settingOption.c(f1);
      return s + (int)(f * 100.0F) + "%";
    }
    if (settingOption.b())
    {
      boolean flag = b(settingOption);
      return s + bwo.a("options.off", new Object[0]);
    }
    if (settingOption == bch.a.H) {
      return s + this.A;
    }
    if (settingOption == bch.a.n) {
      return s + a(aG, this.az);
    }
    if (settingOption == bch.a.p) {
      return s + bwo.a(this.m.b(), new Object[0]);
    }
    if (settingOption == bch.a.o) {
      return s + a(aH, this.aA);
    }
    if (settingOption == bch.a.m) {
      return s + a(aI, this.j);
    }
    if (settingOption == bch.a.k) {
      return s + a(aJ, this.h);
    }
    if (settingOption == bch.a.l)
    {
      if (this.i) {
        return s + bwo.a("options.graphics.fancy", new Object[0]);
      }
      String s1 = "options.graphics.fast";
      return s + bwo.a("options.graphics.fast", new Object[0]);
    }
    return settingOption == bch.a.I ? s + a(aK, this.M) : s;
  }
  
  public void a()
  {
    try
    {
      if (!this.aN.exists()) {
        return;
      }
      BufferedReader bufferedreader = new BufferedReader(new FileReader(this.aN));
      String s = "";
      this.aM.clear();
      while ((s = bufferedreader.readLine()) != null) {
        try
        {
          String[] astring = s.split(":");
          if (astring[0].equals("mouseSensitivity")) {
            this.a = a(astring[1]);
          }
          if (astring[0].equals("fov")) {
            this.aw = (a(astring[1]) * 40.0F + 70.0F);
          }
          if (astring[0].equals("gamma")) {
            this.ax = a(astring[1]);
          }
          if (astring[0].equals("saturation")) {
            this.ay = a(astring[1]);
          }
          if (astring[0].equals("invertYMouse")) {
            this.b = astring[1].equals("true");
          }
          if (astring[0].equals("renderDistance")) {
            this.c = Integer.parseInt(astring[1]);
          }
          if (astring[0].equals("guiScale")) {
            this.az = Integer.parseInt(astring[1]);
          }
          if (astring[0].equals("particles")) {
            this.aA = Integer.parseInt(astring[1]);
          }
          if (astring[0].equals("bobView")) {
            this.d = astring[1].equals("true");
          }
          if (astring[0].equals("anaglyph3d")) {
            this.e = astring[1].equals("true");
          }
          if (astring[0].equals("maxFps"))
          {
            this.g = Integer.parseInt(astring[1]);
            
            this.t = false;
            if (this.g <= 0)
            {
              this.g = ((int)bch.a.i.f());
              this.t = true;
            }
            updateVSync();
          }
          if (astring[0].equals("fboEnable")) {
            this.f = astring[1].equals("true");
          }
          if (astring[0].equals("difficulty")) {
            this.an = qk.a(Integer.parseInt(astring[1]));
          }
          if (astring[0].equals("fancyGraphics"))
          {
            this.i = astring[1].equals("true");
            
            updateRenderClouds();
          }
          if (astring[0].equals("ao")) {
            if (astring[1].equals("true")) {
              this.j = 2;
            } else if (astring[1].equals("false")) {
              this.j = 0;
            } else {
              this.j = Integer.parseInt(astring[1]);
            }
          }
          if (astring[0].equals("renderClouds")) {
            if (astring[1].equals("true")) {
              this.h = 2;
            } else if (astring[1].equals("false")) {
              this.h = 0;
            } else if (astring[1].equals("fast")) {
              this.h = 1;
            }
          }
          if (astring[0].equals("attackIndicator")) {
            if (astring[1].equals("0")) {
              this.M = 0;
            } else if (astring[1].equals("1")) {
              this.M = 1;
            } else if (astring[1].equals("2")) {
              this.M = 2;
            }
          }
          if (astring[0].equals("resourcePacks"))
          {
            this.k = ((List)aE.fromJson(s.substring(s.indexOf(':') + 1), aF));
            if (this.k == null) {
              this.k = Lists.newArrayList();
            }
          }
          if (astring[0].equals("incompatibleResourcePacks"))
          {
            this.l = ((List)aE.fromJson(s.substring(s.indexOf(':') + 1), aF));
            if (this.l == null) {
              this.l = Lists.newArrayList();
            }
          }
          if ((astring[0].equals("lastServer")) && (astring.length >= 2)) {
            this.at = s.substring(s.indexOf(':') + 1);
          }
          if ((astring[0].equals("lang")) && (astring.length >= 2)) {
            this.aB = astring[1];
          }
          if (astring[0].equals("chatVisibility")) {
            this.m = zj.b.a(Integer.parseInt(astring[1]));
          }
          if (astring[0].equals("chatColors")) {
            this.n = astring[1].equals("true");
          }
          if (astring[0].equals("chatLinks")) {
            this.o = astring[1].equals("true");
          }
          if (astring[0].equals("chatLinksPrompt")) {
            this.p = astring[1].equals("true");
          }
          if (astring[0].equals("chatOpacity")) {
            this.q = a(astring[1]);
          }
          if (astring[0].equals("snooperEnabled")) {
            this.r = astring[1].equals("true");
          }
          if (astring[0].equals("fullscreen")) {
            this.s = astring[1].equals("true");
          }
          if (astring[0].equals("enableVsync"))
          {
            this.t = astring[1].equals("true");
            
            updateVSync();
          }
          if (astring[0].equals("useVbo")) {
            this.u = astring[1].equals("true");
          }
          if (astring[0].equals("hideServerAddress")) {
            this.w = astring[1].equals("true");
          }
          if (astring[0].equals("advancedItemTooltips")) {
            this.x = astring[1].equals("true");
          }
          if (astring[0].equals("pauseOnLostFocus")) {
            this.y = astring[1].equals("true");
          }
          if (astring[0].equals("touchscreen")) {
            this.z = astring[1].equals("true");
          }
          if (astring[0].equals("overrideHeight")) {
            this.C = Integer.parseInt(astring[1]);
          }
          if (astring[0].equals("overrideWidth")) {
            this.B = Integer.parseInt(astring[1]);
          }
          if (astring[0].equals("heldItemTooltips")) {
            this.D = astring[1].equals("true");
          }
          if (astring[0].equals("chatHeightFocused")) {
            this.H = a(astring[1]);
          }
          if (astring[0].equals("chatHeightUnfocused")) {
            this.G = a(astring[1]);
          }
          if (astring[0].equals("chatScale")) {
            this.E = a(astring[1]);
          }
          if (astring[0].equals("chatWidth")) {
            this.F = a(astring[1]);
          }
          if (astring[0].equals("showInventoryAchievementHint")) {
            this.I = astring[1].equals("true");
          }
          if (astring[0].equals("mipmapLevels")) {
            this.J = Integer.parseInt(astring[1]);
          }
          if (astring[0].equals("forceUnicodeFont")) {
            this.aC = astring[1].equals("true");
          }
          if (astring[0].equals("reducedDebugInfo")) {
            this.v = astring[1].equals("true");
          }
          if (astring[0].equals("useNativeTransport")) {
            this.K = astring[1].equals("true");
          }
          if (astring[0].equals("entityShadows")) {
            this.L = astring[1].equals("true");
          }
          if (astring[0].equals("mainHand")) {
            this.A = (astring[1].equals("left") ? rz.a : rz.b);
          }
          if (astring[0].equals("showSubtitles")) {
            this.N = astring[1].equals("true");
          }
          if (astring[0].equals("realmsNotifications")) {
            this.O = astring[1].equals("true");
          }
          for (bcc keybinding : this.al) {
            if (astring[0].equals("key_" + keybinding.h())) {
              keybinding.b(Integer.parseInt(astring[1]));
            }
          }
          for (nh soundcategory : nh.values()) {
            if (astring[0].equals("soundCategory_" + soundcategory.a())) {
              this.aM.put(soundcategory, Float.valueOf(a(astring[1])));
            }
          }
          for (zk enumplayermodelparts : zk.values()) {
            if (astring[0].equals("modelPart_" + enumplayermodelparts.c())) {
              a(enumplayermodelparts, astring[1].equals("true"));
            }
          }
        }
        catch (Exception var8)
        {
          aD.warn("Skipping bad option: " + s);
          
          var8.printStackTrace();
        }
      }
      bcc.c();
      bufferedreader.close();
    }
    catch (Exception exception)
    {
      aD.error("Failed to load options", exception);
    }
    loadOfOptions();
  }
  
  private float a(String str)
  {
    return str.equals("false") ? 0.0F : str.equals("true") ? 1.0F : Float.parseFloat(str);
  }
  
  public void b()
  {
    if (Reflector.FMLClientHandler.exists())
    {
      Object fml = Reflector.call(Reflector.FMLClientHandler_instance, new Object[0]);
      if ((fml != null) && (Reflector.callBoolean(fml, Reflector.FMLClientHandler_isLoading, new Object[0]))) {
        return;
      }
    }
    try
    {
      PrintWriter printwriter = new PrintWriter(new FileWriter(this.aN));
      printwriter.println("invertYMouse:" + this.b);
      printwriter.println("mouseSensitivity:" + this.a);
      printwriter.println("fov:" + (this.aw - 70.0F) / 40.0F);
      printwriter.println("gamma:" + this.ax);
      printwriter.println("saturation:" + this.ay);
      printwriter.println("renderDistance:" + this.c);
      printwriter.println("guiScale:" + this.az);
      printwriter.println("particles:" + this.aA);
      printwriter.println("bobView:" + this.d);
      printwriter.println("anaglyph3d:" + this.e);
      printwriter.println("maxFps:" + this.g);
      printwriter.println("fboEnable:" + this.f);
      printwriter.println("difficulty:" + this.an.a());
      printwriter.println("fancyGraphics:" + this.i);
      printwriter.println("ao:" + this.j);
      switch (this.h)
      {
      case 0: 
        printwriter.println("renderClouds:false");
        break;
      case 1: 
        printwriter.println("renderClouds:fast");
        break;
      case 2: 
        printwriter.println("renderClouds:true");
      }
      printwriter.println("resourcePacks:" + aE.toJson(this.k));
      printwriter.println("incompatibleResourcePacks:" + aE.toJson(this.l));
      printwriter.println("lastServer:" + this.at);
      printwriter.println("lang:" + this.aB);
      printwriter.println("chatVisibility:" + this.m.a());
      printwriter.println("chatColors:" + this.n);
      printwriter.println("chatLinks:" + this.o);
      printwriter.println("chatLinksPrompt:" + this.p);
      printwriter.println("chatOpacity:" + this.q);
      printwriter.println("snooperEnabled:" + this.r);
      printwriter.println("fullscreen:" + this.s);
      printwriter.println("enableVsync:" + this.t);
      printwriter.println("useVbo:" + this.u);
      printwriter.println("hideServerAddress:" + this.w);
      printwriter.println("advancedItemTooltips:" + this.x);
      printwriter.println("pauseOnLostFocus:" + this.y);
      printwriter.println("touchscreen:" + this.z);
      printwriter.println("overrideWidth:" + this.B);
      printwriter.println("overrideHeight:" + this.C);
      printwriter.println("heldItemTooltips:" + this.D);
      printwriter.println("chatHeightFocused:" + this.H);
      printwriter.println("chatHeightUnfocused:" + this.G);
      printwriter.println("chatScale:" + this.E);
      printwriter.println("chatWidth:" + this.F);
      printwriter.println("showInventoryAchievementHint:" + this.I);
      printwriter.println("mipmapLevels:" + this.J);
      printwriter.println("forceUnicodeFont:" + this.aC);
      printwriter.println("reducedDebugInfo:" + this.v);
      printwriter.println("useNativeTransport:" + this.K);
      printwriter.println("entityShadows:" + this.L);
      printwriter.println("mainHand:" + (this.A == rz.a ? "left" : "right"));
      printwriter.println("attackIndicator:" + this.M);
      printwriter.println("showSubtitles:" + this.N);
      printwriter.println("realmsNotifications:" + this.O);
      for (bcc keybinding : this.al) {
        printwriter.println("key_" + keybinding.h() + ":" + keybinding.j());
      }
      for (nh soundcategory : nh.values()) {
        printwriter.println("soundCategory_" + soundcategory.a() + ":" + a(soundcategory));
      }
      for (zk enumplayermodelparts : zk.values()) {
        printwriter.println("modelPart_" + enumplayermodelparts.c() + ":" + this.aL.contains(enumplayermodelparts));
      }
      printwriter.close();
    }
    catch (Exception exception)
    {
      aD.error("Failed to save options", exception);
    }
    saveOfOptions();
    
    c();
  }
  
  public float a(nh p_186711_1_)
  {
    return this.aM.containsKey(p_186711_1_) ? ((Float)this.aM.get(p_186711_1_)).floatValue() : 1.0F;
  }
  
  public void a(nh p_186712_1_, float p_186712_2_)
  {
    this.am.U().a(p_186712_1_, p_186712_2_);
    this.aM.put(p_186712_1_, Float.valueOf(p_186712_2_));
  }
  
  public void c()
  {
    if (this.am.h != null)
    {
      int i = 0;
      for (zk enumplayermodelparts : this.aL) {
        i |= enumplayermodelparts.a();
      }
      this.am.h.d.a(new il(this.aB, this.c, this.m, this.n, i, this.A));
    }
  }
  
  public Set<zk> d()
  {
    return ImmutableSet.copyOf(this.aL);
  }
  
  public void a(zk modelPart, boolean enable)
  {
    if (enable) {
      this.aL.add(modelPart);
    } else {
      this.aL.remove(modelPart);
    }
    c();
  }
  
  public void a(zk modelPart)
  {
    if (!d().contains(modelPart)) {
      this.aL.add(modelPart);
    } else {
      this.aL.remove(modelPart);
    }
    c();
  }
  
  public int e()
  {
    return this.c >= 4 ? this.h : 0;
  }
  
  public boolean f()
  {
    return this.K;
  }
  
  private void setOptionFloatValueOF(bch.a option, float val)
  {
    if (option == bch.a.CLOUD_HEIGHT)
    {
      this.ofCloudsHeight = val;
      
      this.am.g.resetClouds();
    }
    if (option == bch.a.AO_LEVEL)
    {
      this.ofAoLevel = val;
      this.am.g.a();
    }
    if ((option != bch.a.AA_LEVEL) || (
    
      (option != bch.a.AF_LEVEL) || 
      
      (option == bch.a.MIPMAP_TYPE)))
    {
      int valInt = (int)val;
      this.ofMipmapType = Config.limit(valInt, 0, 3);
      
      this.am.f();
    }
  }
  
  private void setOptionValueOF(bch.a par1EnumOptions, int par2)
  {
    if (par1EnumOptions == bch.a.FOG_FANCY) {
      switch (this.ofFogType)
      {
      case 1: 
        this.ofFogType = 2;
        if (!Config.isFancyFogAvailable()) {
          this.ofFogType = 3;
        }
        break;
      case 2: 
        this.ofFogType = 3;
        break;
      case 3: 
        this.ofFogType = 1;
        break;
      default: 
        this.ofFogType = 1;
      }
    }
    if (par1EnumOptions == bch.a.FOG_START)
    {
      this.ofFogStart += 0.2F;
      if (this.ofFogStart > 0.81F) {
        this.ofFogStart = 0.2F;
      }
    }
    if (par1EnumOptions == bch.a.SMOOTH_FPS) {
      this.ofSmoothFps = (!this.ofSmoothFps);
    }
    if (par1EnumOptions == bch.a.SMOOTH_WORLD)
    {
      this.ofSmoothWorld = (!this.ofSmoothWorld);
      
      Config.updateThreadPriorities();
    }
    if (par1EnumOptions == bch.a.CLOUDS)
    {
      this.ofClouds += 1;
      if (this.ofClouds > 3) {
        this.ofClouds = 0;
      }
      updateRenderClouds();
      
      this.am.g.resetClouds();
    }
    if (par1EnumOptions == bch.a.TREES)
    {
      this.ofTrees = nextValue(this.ofTrees, OF_TREES_VALUES);
      
      this.am.g.a();
    }
    if (par1EnumOptions == bch.a.DROPPED_ITEMS)
    {
      this.ofDroppedItems += 1;
      if (this.ofDroppedItems > 2) {
        this.ofDroppedItems = 0;
      }
    }
    if (par1EnumOptions == bch.a.RAIN)
    {
      this.ofRain += 1;
      if (this.ofRain > 3) {
        this.ofRain = 0;
      }
    }
    if (par1EnumOptions == bch.a.ANIMATED_WATER)
    {
      this.ofAnimatedWater += 1;
      if (this.ofAnimatedWater > 2) {
        this.ofAnimatedWater = 0;
      }
    }
    if (par1EnumOptions == bch.a.ANIMATED_LAVA)
    {
      this.ofAnimatedLava += 1;
      if (this.ofAnimatedLava > 2) {
        this.ofAnimatedLava = 0;
      }
    }
    if (par1EnumOptions == bch.a.ANIMATED_FIRE) {
      this.ofAnimatedFire = (!this.ofAnimatedFire);
    }
    if (par1EnumOptions == bch.a.ANIMATED_PORTAL) {
      this.ofAnimatedPortal = (!this.ofAnimatedPortal);
    }
    if (par1EnumOptions == bch.a.ANIMATED_REDSTONE) {
      this.ofAnimatedRedstone = (!this.ofAnimatedRedstone);
    }
    if (par1EnumOptions == bch.a.ANIMATED_EXPLOSION) {
      this.ofAnimatedExplosion = (!this.ofAnimatedExplosion);
    }
    if (par1EnumOptions == bch.a.ANIMATED_FLAME) {
      this.ofAnimatedFlame = (!this.ofAnimatedFlame);
    }
    if (par1EnumOptions == bch.a.ANIMATED_SMOKE) {
      this.ofAnimatedSmoke = (!this.ofAnimatedSmoke);
    }
    if (par1EnumOptions == bch.a.VOID_PARTICLES) {
      this.ofVoidParticles = (!this.ofVoidParticles);
    }
    if (par1EnumOptions == bch.a.WATER_PARTICLES) {
      this.ofWaterParticles = (!this.ofWaterParticles);
    }
    if (par1EnumOptions == bch.a.PORTAL_PARTICLES) {
      this.ofPortalParticles = (!this.ofPortalParticles);
    }
    if (par1EnumOptions == bch.a.POTION_PARTICLES) {
      this.ofPotionParticles = (!this.ofPotionParticles);
    }
    if (par1EnumOptions == bch.a.FIREWORK_PARTICLES) {
      this.ofFireworkParticles = (!this.ofFireworkParticles);
    }
    if (par1EnumOptions == bch.a.DRIPPING_WATER_LAVA) {
      this.ofDrippingWaterLava = (!this.ofDrippingWaterLava);
    }
    if (par1EnumOptions == bch.a.ANIMATED_TERRAIN) {
      this.ofAnimatedTerrain = (!this.ofAnimatedTerrain);
    }
    if (par1EnumOptions == bch.a.ANIMATED_TEXTURES) {
      this.ofAnimatedTextures = (!this.ofAnimatedTextures);
    }
    if (par1EnumOptions == bch.a.RAIN_SPLASH) {
      this.ofRainSplash = (!this.ofRainSplash);
    }
    if (par1EnumOptions == bch.a.LAGOMETER) {
      this.ofLagometer = (!this.ofLagometer);
    }
    if (par1EnumOptions == bch.a.SHOW_FPS) {
      this.ofShowFps = (!this.ofShowFps);
    }
    if (par1EnumOptions == bch.a.AUTOSAVE_TICKS)
    {
      this.ofAutoSaveTicks *= 10;
      if (this.ofAutoSaveTicks > 40000) {
        this.ofAutoSaveTicks = 40;
      }
    }
    if (par1EnumOptions == bch.a.BETTER_GRASS)
    {
      this.ofBetterGrass += 1;
      if (this.ofBetterGrass > 3) {
        this.ofBetterGrass = 1;
      }
      this.am.g.a();
    }
    if (par1EnumOptions == bch.a.CONNECTED_TEXTURES)
    {
      this.ofConnectedTextures += 1;
      if (this.ofConnectedTextures > 3) {
        this.ofConnectedTextures = 1;
      }
      if (this.ofConnectedTextures != 2) {
        this.am.f();
      }
    }
    if (par1EnumOptions == bch.a.WEATHER) {
      this.ofWeather = (!this.ofWeather);
    }
    if (par1EnumOptions == bch.a.SKY) {
      this.ofSky = (!this.ofSky);
    }
    if (par1EnumOptions == bch.a.STARS) {
      this.ofStars = (!this.ofStars);
    }
    if (par1EnumOptions == bch.a.SUN_MOON) {
      this.ofSunMoon = (!this.ofSunMoon);
    }
    if (par1EnumOptions == bch.a.VIGNETTE)
    {
      this.ofVignette += 1;
      if (this.ofVignette > 2) {
        this.ofVignette = 0;
      }
    }
    if (par1EnumOptions == bch.a.CHUNK_UPDATES)
    {
      this.ofChunkUpdates += 1;
      if (this.ofChunkUpdates > 5) {
        this.ofChunkUpdates = 1;
      }
    }
    if (par1EnumOptions == bch.a.CHUNK_LOADING)
    {
      this.ofChunkLoading += 1;
      if (this.ofChunkLoading > 2) {
        this.ofChunkLoading = 0;
      }
      updateChunkLoading();
    }
    if (par1EnumOptions == bch.a.CHUNK_UPDATES_DYNAMIC) {
      this.ofChunkUpdatesDynamic = (!this.ofChunkUpdatesDynamic);
    }
    if (par1EnumOptions == bch.a.TIME)
    {
      this.ofTime += 1;
      if (this.ofTime > 2) {
        this.ofTime = 0;
      }
    }
    if (par1EnumOptions == bch.a.CLEAR_WATER)
    {
      this.ofClearWater = (!this.ofClearWater);
      updateWaterOpacity();
    }
    if (par1EnumOptions == bch.a.PROFILER) {
      this.ofProfiler = (!this.ofProfiler);
    }
    if (par1EnumOptions == bch.a.BETTER_SNOW)
    {
      this.ofBetterSnow = (!this.ofBetterSnow);
      this.am.g.a();
    }
    if (par1EnumOptions == bch.a.SWAMP_COLORS)
    {
      this.ofSwampColors = (!this.ofSwampColors);
      CustomColors.updateUseDefaultGrassFoliageColors();
      this.am.g.a();
    }
    if (par1EnumOptions == bch.a.RANDOM_MOBS)
    {
      this.ofRandomMobs = (!this.ofRandomMobs);
      RandomMobs.resetTextures();
    }
    if (par1EnumOptions == bch.a.SMOOTH_BIOMES)
    {
      this.ofSmoothBiomes = (!this.ofSmoothBiomes);
      CustomColors.updateUseDefaultGrassFoliageColors();
      this.am.g.a();
    }
    if (par1EnumOptions == bch.a.CUSTOM_FONTS)
    {
      this.ofCustomFonts = (!this.ofCustomFonts);
      
      this.am.k.a(Config.getResourceManager());
      this.am.l.a(Config.getResourceManager());
    }
    if (par1EnumOptions == bch.a.CUSTOM_COLORS)
    {
      this.ofCustomColors = (!this.ofCustomColors);
      
      CustomColors.update();
      
      this.am.g.a();
    }
    if (par1EnumOptions == bch.a.CUSTOM_ITEMS)
    {
      this.ofCustomItems = (!this.ofCustomItems);
      
      this.am.f();
    }
    if (par1EnumOptions == bch.a.CUSTOM_SKY)
    {
      this.ofCustomSky = (!this.ofCustomSky);
      
      CustomSky.update();
    }
    if (par1EnumOptions == bch.a.SHOW_CAPES) {
      this.ofShowCapes = (!this.ofShowCapes);
    }
    if (par1EnumOptions == bch.a.NATURAL_TEXTURES)
    {
      this.ofNaturalTextures = (!this.ofNaturalTextures);
      
      NaturalTextures.update();
      
      this.am.g.a();
    }
    if (par1EnumOptions == bch.a.FAST_MATH)
    {
      this.ofFastMath = (!this.ofFastMath);
      
      on.fastMath = this.ofFastMath;
    }
    if (par1EnumOptions == bch.a.FAST_RENDER)
    {
      if ((!this.ofFastRender) && (Config.isShaders()))
      {
        Config.showGuiMessage(Lang.get("of.message.fr.shaders1"), Lang.get("of.message.fr.shaders2"));
        return;
      }
      this.ofFastRender = (!this.ofFastRender);
      if (this.ofFastRender) {
        this.am.o.b();
      }
      Config.updateFramebufferSize();
    }
    if (par1EnumOptions == bch.a.TRANSLUCENT_BLOCKS)
    {
      if (this.ofTranslucentBlocks == 0) {
        this.ofTranslucentBlocks = 1;
      } else if (this.ofTranslucentBlocks == 1) {
        this.ofTranslucentBlocks = 2;
      } else if (this.ofTranslucentBlocks == 2) {
        this.ofTranslucentBlocks = 0;
      } else {
        this.ofTranslucentBlocks = 0;
      }
      this.am.g.a();
    }
    if (par1EnumOptions == bch.a.LAZY_CHUNK_LOADING)
    {
      this.ofLazyChunkLoading = (!this.ofLazyChunkLoading);
      
      Config.updateAvailableProcessors();
      if (!Config.isSingleProcessor()) {
        this.ofLazyChunkLoading = false;
      }
      this.am.g.a();
    }
    if (par1EnumOptions == bch.a.FULLSCREEN_MODE)
    {
      List modeList = Arrays.asList(Config.getFullscreenModes());
      if (this.ofFullscreenMode.equals("Default"))
      {
        this.ofFullscreenMode = ((String)modeList.get(0));
      }
      else
      {
        int index = modeList.indexOf(this.ofFullscreenMode);
        if (index < 0)
        {
          this.ofFullscreenMode = "Default";
        }
        else
        {
          index++;
          if (index >= modeList.size()) {
            this.ofFullscreenMode = "Default";
          } else {
            this.ofFullscreenMode = ((String)modeList.get(index));
          }
        }
      }
    }
    if (par1EnumOptions == bch.a.HELD_ITEM_TOOLTIPS) {
      this.D = (!this.D);
    }
  }
  
  private String getKeyBindingOF(bch.a par1EnumOptions)
  {
    String var2 = bwo.a(par1EnumOptions.d(), new Object[0]) + ": ";
    if (var2 == null) {
      var2 = par1EnumOptions.d();
    }
    String s = var2;
    if (par1EnumOptions == bch.a.f)
    {
      int distChunks = (int)a(par1EnumOptions);
      String str = bwo.a("options.renderDistance.tiny", new Object[0]);
      int baseDist = 2;
      if (distChunks >= 4)
      {
        str = bwo.a("options.renderDistance.short", new Object[0]);
        baseDist = 4;
      }
      if (distChunks >= 8)
      {
        str = bwo.a("options.renderDistance.normal", new Object[0]);
        baseDist = 8;
      }
      if (distChunks >= 16)
      {
        str = bwo.a("options.renderDistance.far", new Object[0]);
        baseDist = 16;
      }
      if (distChunks >= 32)
      {
        str = Lang.get("of.options.renderDistance.extreme");
        baseDist = 32;
      }
      int diff = this.c - baseDist;
      String descr = str;
      if (diff > 0) {
        descr = descr + "+";
      }
      return var2 + distChunks + " " + descr + "";
    }
    if (par1EnumOptions == bch.a.FOG_FANCY)
    {
      switch (this.ofFogType)
      {
      case 1: 
        return s + Lang.getFast();
      case 2: 
        return s + Lang.getFancy();
      case 3: 
        return s + Lang.getOff();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.FOG_START) {
      return s + this.ofFogStart;
    }
    if (par1EnumOptions == bch.a.MIPMAP_TYPE)
    {
      switch (this.ofMipmapType)
      {
      case 0: 
        return s + Lang.get("of.options.mipmap.nearest");
      case 1: 
        return s + Lang.get("of.options.mipmap.linear");
      case 2: 
        return s + Lang.get("of.options.mipmap.bilinear");
      case 3: 
        return s + Lang.get("of.options.mipmap.trilinear");
      }
      return s + "of.options.mipmap.nearest";
    }
    if (par1EnumOptions == bch.a.SMOOTH_FPS)
    {
      if (this.ofSmoothFps) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.SMOOTH_WORLD)
    {
      if (this.ofSmoothWorld) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.CLOUDS)
    {
      switch (this.ofClouds)
      {
      case 1: 
        return s + Lang.getFast();
      case 2: 
        return s + Lang.getFancy();
      case 3: 
        return s + Lang.getOff();
      }
      return s + Lang.getDefault();
    }
    if (par1EnumOptions == bch.a.TREES)
    {
      switch (this.ofTrees)
      {
      case 1: 
        return s + Lang.getFast();
      case 2: 
        return s + Lang.getFancy();
      case 4: 
        return s + Lang.get("of.general.smart");
      }
      return s + Lang.getDefault();
    }
    if (par1EnumOptions == bch.a.DROPPED_ITEMS)
    {
      switch (this.ofDroppedItems)
      {
      case 1: 
        return s + Lang.getFast();
      case 2: 
        return s + Lang.getFancy();
      }
      return s + Lang.getDefault();
    }
    if (par1EnumOptions == bch.a.RAIN)
    {
      switch (this.ofRain)
      {
      case 1: 
        return s + Lang.getFast();
      case 2: 
        return s + Lang.getFancy();
      case 3: 
        return s + Lang.getOff();
      }
      return s + Lang.getDefault();
    }
    if (par1EnumOptions == bch.a.ANIMATED_WATER)
    {
      switch (this.ofAnimatedWater)
      {
      case 1: 
        return s + "Dynamic";
      case 2: 
        return s + Lang.getOff();
      }
      return s + Lang.getOn();
    }
    if (par1EnumOptions == bch.a.ANIMATED_LAVA)
    {
      switch (this.ofAnimatedLava)
      {
      case 1: 
        return s + Lang.get("of.options.animation.dynamic");
      case 2: 
        return s + Lang.getOff();
      }
      return s + Lang.getOn();
    }
    if (par1EnumOptions == bch.a.ANIMATED_FIRE)
    {
      if (this.ofAnimatedFire) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.ANIMATED_PORTAL)
    {
      if (this.ofAnimatedPortal) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.ANIMATED_REDSTONE)
    {
      if (this.ofAnimatedRedstone) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.ANIMATED_EXPLOSION)
    {
      if (this.ofAnimatedExplosion) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.ANIMATED_FLAME)
    {
      if (this.ofAnimatedFlame) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.ANIMATED_SMOKE)
    {
      if (this.ofAnimatedSmoke) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.VOID_PARTICLES)
    {
      if (this.ofVoidParticles) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.WATER_PARTICLES)
    {
      if (this.ofWaterParticles) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.PORTAL_PARTICLES)
    {
      if (this.ofPortalParticles) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.POTION_PARTICLES)
    {
      if (this.ofPotionParticles) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.FIREWORK_PARTICLES)
    {
      if (this.ofFireworkParticles) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.DRIPPING_WATER_LAVA)
    {
      if (this.ofDrippingWaterLava) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.ANIMATED_TERRAIN)
    {
      if (this.ofAnimatedTerrain) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.ANIMATED_TEXTURES)
    {
      if (this.ofAnimatedTextures) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.RAIN_SPLASH)
    {
      if (this.ofRainSplash) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.LAGOMETER)
    {
      if (this.ofLagometer) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.SHOW_FPS)
    {
      if (this.ofShowFps) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.AUTOSAVE_TICKS)
    {
      if (this.ofAutoSaveTicks <= 40) {
        return s + Lang.get("of.options.save.default");
      }
      if (this.ofAutoSaveTicks <= 400) {
        return s + Lang.get("of.options.save.20s");
      }
      if (this.ofAutoSaveTicks <= 4000) {
        return s + Lang.get("of.options.save.3min");
      }
      return s + Lang.get("of.options.save.30min");
    }
    if (par1EnumOptions == bch.a.BETTER_GRASS)
    {
      switch (this.ofBetterGrass)
      {
      case 1: 
        return s + Lang.getFast();
      case 2: 
        return s + Lang.getFancy();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.CONNECTED_TEXTURES)
    {
      switch (this.ofConnectedTextures)
      {
      case 1: 
        return s + Lang.getFast();
      case 2: 
        return s + Lang.getFancy();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.WEATHER)
    {
      if (this.ofWeather) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.SKY)
    {
      if (this.ofSky) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.STARS)
    {
      if (this.ofStars) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.SUN_MOON)
    {
      if (this.ofSunMoon) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.VIGNETTE)
    {
      switch (this.ofVignette)
      {
      case 1: 
        return s + Lang.getFast();
      case 2: 
        return s + Lang.getFancy();
      }
      return s + Lang.getDefault();
    }
    if (par1EnumOptions == bch.a.CHUNK_UPDATES) {
      return s + this.ofChunkUpdates;
    }
    if (par1EnumOptions == bch.a.CHUNK_LOADING)
    {
      if (this.ofChunkLoading == 1) {
        return s + Lang.get("of.options.chunkLoading.smooth");
      }
      if (this.ofChunkLoading == 2) {
        return s + Lang.get("of.options.chunkLoading.multiCore");
      }
      return s + Lang.getDefault();
    }
    if (par1EnumOptions == bch.a.CHUNK_UPDATES_DYNAMIC)
    {
      if (this.ofChunkUpdatesDynamic) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.TIME)
    {
      if (this.ofTime == 1) {
        return s + Lang.get("of.options.time.dayOnly");
      }
      if (this.ofTime == 2) {
        return s + Lang.get("of.options.time.nightOnly");
      }
      return s + Lang.getDefault();
    }
    if (par1EnumOptions == bch.a.CLEAR_WATER)
    {
      if (this.ofClearWater) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.AA_LEVEL)
    {
      String suffix = "";
      if (this.ofAaLevel != Config.getAntialiasingLevel()) {
        suffix = " (" + Lang.get("of.general.restart") + ")";
      }
      if (this.ofAaLevel == 0) {
        return s + Lang.getOff() + suffix;
      }
      return s + this.ofAaLevel + suffix;
    }
    if (par1EnumOptions == bch.a.AF_LEVEL)
    {
      if (this.ofAfLevel == 1) {
        return s + Lang.getOff();
      }
      return s + this.ofAfLevel;
    }
    if (par1EnumOptions == bch.a.PROFILER)
    {
      if (this.ofProfiler) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.BETTER_SNOW)
    {
      if (this.ofBetterSnow) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.SWAMP_COLORS)
    {
      if (this.ofSwampColors) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.RANDOM_MOBS)
    {
      if (this.ofRandomMobs) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.SMOOTH_BIOMES)
    {
      if (this.ofSmoothBiomes) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.CUSTOM_FONTS)
    {
      if (this.ofCustomFonts) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.CUSTOM_COLORS)
    {
      if (this.ofCustomColors) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.CUSTOM_SKY)
    {
      if (this.ofCustomSky) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.SHOW_CAPES)
    {
      if (this.ofShowCapes) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.CUSTOM_ITEMS)
    {
      if (this.ofCustomItems) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.NATURAL_TEXTURES)
    {
      if (this.ofNaturalTextures) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.FAST_MATH)
    {
      if (this.ofFastMath) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.FAST_RENDER)
    {
      if (this.ofFastRender) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.TRANSLUCENT_BLOCKS)
    {
      if (this.ofTranslucentBlocks == 1) {
        return s + Lang.getFast();
      }
      if (this.ofTranslucentBlocks == 2) {
        return s + Lang.getFancy();
      }
      return s + Lang.getDefault();
    }
    if (par1EnumOptions == bch.a.LAZY_CHUNK_LOADING)
    {
      if (this.ofLazyChunkLoading) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.FULLSCREEN_MODE) {
      return s + this.ofFullscreenMode;
    }
    if (par1EnumOptions == bch.a.HELD_ITEM_TOOLTIPS)
    {
      if (this.D) {
        return s + Lang.getOn();
      }
      return s + Lang.getOff();
    }
    if (par1EnumOptions == bch.a.i)
    {
      float var6 = a(par1EnumOptions);
      if (var6 == 0.0F) {
        return var2 + Lang.get("of.options.framerateLimit.vsync");
      }
      if (var6 == bch.a.a(par1EnumOptions)) {
        return var2 + bwo.a("options.framerateLimit.max", new Object[0]);
      }
      return var2 + (int)var6 + " fps";
    }
    return null;
  }
  
  public void loadOfOptions()
  {
    try
    {
      File ofReadFile = this.optionsFileOF;
      if (!ofReadFile.exists()) {
        ofReadFile = this.aN;
      }
      if (!ofReadFile.exists()) {
        return;
      }
      BufferedReader bufferedreader = new BufferedReader(new FileReader(ofReadFile));
      for (String s = ""; (s = bufferedreader.readLine()) != null;) {
        try
        {
          String[] as = s.split(":");
          if ((as[0].equals("ofRenderDistanceChunks")) && (as.length >= 2))
          {
            this.c = Integer.valueOf(as[1]).intValue();
            this.c = Config.limit(this.c, 2, 32);
          }
          if ((as[0].equals("ofFogType")) && (as.length >= 2))
          {
            this.ofFogType = Integer.valueOf(as[1]).intValue();
            this.ofFogType = Config.limit(this.ofFogType, 1, 3);
          }
          if ((as[0].equals("ofFogStart")) && (as.length >= 2))
          {
            this.ofFogStart = Float.valueOf(as[1]).floatValue();
            if (this.ofFogStart < 0.2F) {
              this.ofFogStart = 0.2F;
            }
            if (this.ofFogStart > 0.81F) {
              this.ofFogStart = 0.8F;
            }
          }
          if ((as[0].equals("ofMipmapType")) && (as.length >= 2))
          {
            this.ofMipmapType = Integer.valueOf(as[1]).intValue();
            this.ofMipmapType = Config.limit(this.ofMipmapType, 0, 3);
          }
          if ((as[0].equals("ofOcclusionFancy")) && (as.length >= 2)) {
            this.ofOcclusionFancy = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofSmoothFps")) && (as.length >= 2)) {
            this.ofSmoothFps = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofSmoothWorld")) && (as.length >= 2)) {
            this.ofSmoothWorld = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofAoLevel")) && (as.length >= 2))
          {
            this.ofAoLevel = Float.valueOf(as[1]).floatValue();
            this.ofAoLevel = Config.limit(this.ofAoLevel, 0.0F, 1.0F);
          }
          if ((as[0].equals("ofClouds")) && (as.length >= 2))
          {
            this.ofClouds = Integer.valueOf(as[1]).intValue();
            this.ofClouds = Config.limit(this.ofClouds, 0, 3);
            
            updateRenderClouds();
          }
          if ((as[0].equals("ofCloudsHeight")) && (as.length >= 2))
          {
            this.ofCloudsHeight = Float.valueOf(as[1]).floatValue();
            this.ofCloudsHeight = Config.limit(this.ofCloudsHeight, 0.0F, 1.0F);
          }
          if ((as[0].equals("ofTrees")) && (as.length >= 2))
          {
            this.ofTrees = Integer.valueOf(as[1]).intValue();
            this.ofTrees = limit(this.ofTrees, OF_TREES_VALUES);
          }
          if ((as[0].equals("ofDroppedItems")) && (as.length >= 2))
          {
            this.ofDroppedItems = Integer.valueOf(as[1]).intValue();
            this.ofDroppedItems = Config.limit(this.ofDroppedItems, 0, 2);
          }
          if ((as[0].equals("ofRain")) && (as.length >= 2))
          {
            this.ofRain = Integer.valueOf(as[1]).intValue();
            this.ofRain = Config.limit(this.ofRain, 0, 3);
          }
          if ((as[0].equals("ofAnimatedWater")) && (as.length >= 2))
          {
            this.ofAnimatedWater = Integer.valueOf(as[1]).intValue();
            this.ofAnimatedWater = Config.limit(this.ofAnimatedWater, 0, 2);
          }
          if ((as[0].equals("ofAnimatedLava")) && (as.length >= 2))
          {
            this.ofAnimatedLava = Integer.valueOf(as[1]).intValue();
            this.ofAnimatedLava = Config.limit(this.ofAnimatedLava, 0, 2);
          }
          if ((as[0].equals("ofAnimatedFire")) && (as.length >= 2)) {
            this.ofAnimatedFire = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofAnimatedPortal")) && (as.length >= 2)) {
            this.ofAnimatedPortal = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofAnimatedRedstone")) && (as.length >= 2)) {
            this.ofAnimatedRedstone = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofAnimatedExplosion")) && (as.length >= 2)) {
            this.ofAnimatedExplosion = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofAnimatedFlame")) && (as.length >= 2)) {
            this.ofAnimatedFlame = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofAnimatedSmoke")) && (as.length >= 2)) {
            this.ofAnimatedSmoke = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofVoidParticles")) && (as.length >= 2)) {
            this.ofVoidParticles = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofWaterParticles")) && (as.length >= 2)) {
            this.ofWaterParticles = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofPortalParticles")) && (as.length >= 2)) {
            this.ofPortalParticles = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofPotionParticles")) && (as.length >= 2)) {
            this.ofPotionParticles = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofFireworkParticles")) && (as.length >= 2)) {
            this.ofFireworkParticles = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofDrippingWaterLava")) && (as.length >= 2)) {
            this.ofDrippingWaterLava = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofAnimatedTerrain")) && (as.length >= 2)) {
            this.ofAnimatedTerrain = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofAnimatedTextures")) && (as.length >= 2)) {
            this.ofAnimatedTextures = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofRainSplash")) && (as.length >= 2)) {
            this.ofRainSplash = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofLagometer")) && (as.length >= 2)) {
            this.ofLagometer = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofShowFps")) && (as.length >= 2)) {
            this.ofShowFps = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofAutoSaveTicks")) && (as.length >= 2))
          {
            this.ofAutoSaveTicks = Integer.valueOf(as[1]).intValue();
            this.ofAutoSaveTicks = Config.limit(this.ofAutoSaveTicks, 40, 40000);
          }
          if ((as[0].equals("ofBetterGrass")) && (as.length >= 2))
          {
            this.ofBetterGrass = Integer.valueOf(as[1]).intValue();
            this.ofBetterGrass = Config.limit(this.ofBetterGrass, 1, 3);
          }
          if ((as[0].equals("ofConnectedTextures")) && (as.length >= 2))
          {
            this.ofConnectedTextures = Integer.valueOf(as[1]).intValue();
            this.ofConnectedTextures = Config.limit(this.ofConnectedTextures, 1, 3);
          }
          if ((as[0].equals("ofWeather")) && (as.length >= 2)) {
            this.ofWeather = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofSky")) && (as.length >= 2)) {
            this.ofSky = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofStars")) && (as.length >= 2)) {
            this.ofStars = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofSunMoon")) && (as.length >= 2)) {
            this.ofSunMoon = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofVignette")) && (as.length >= 2))
          {
            this.ofVignette = Integer.valueOf(as[1]).intValue();
            this.ofVignette = Config.limit(this.ofVignette, 0, 2);
          }
          if ((as[0].equals("ofChunkUpdates")) && (as.length >= 2))
          {
            this.ofChunkUpdates = Integer.valueOf(as[1]).intValue();
            this.ofChunkUpdates = Config.limit(this.ofChunkUpdates, 1, 5);
          }
          if ((as[0].equals("ofChunkLoading")) && (as.length >= 2))
          {
            this.ofChunkLoading = Integer.valueOf(as[1]).intValue();
            this.ofChunkLoading = Config.limit(this.ofChunkLoading, 0, 2);
            
            updateChunkLoading();
          }
          if ((as[0].equals("ofChunkUpdatesDynamic")) && (as.length >= 2)) {
            this.ofChunkUpdatesDynamic = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofTime")) && (as.length >= 2))
          {
            this.ofTime = Integer.valueOf(as[1]).intValue();
            this.ofTime = Config.limit(this.ofTime, 0, 2);
          }
          if ((as[0].equals("ofClearWater")) && (as.length >= 2))
          {
            this.ofClearWater = Boolean.valueOf(as[1]).booleanValue();
            updateWaterOpacity();
          }
          if (((!as[0].equals("ofAaLevel")) || (as.length < 2)) || (
          
            ((!as[0].equals("ofAfLevel")) || (as.length < 2)) || (
            
            (as[0].equals("ofProfiler")) && (as.length >= 2)))) {
            this.ofProfiler = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofBetterSnow")) && (as.length >= 2)) {
            this.ofBetterSnow = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofSwampColors")) && (as.length >= 2)) {
            this.ofSwampColors = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofRandomMobs")) && (as.length >= 2)) {
            this.ofRandomMobs = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofSmoothBiomes")) && (as.length >= 2)) {
            this.ofSmoothBiomes = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofCustomFonts")) && (as.length >= 2)) {
            this.ofCustomFonts = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofCustomColors")) && (as.length >= 2)) {
            this.ofCustomColors = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofCustomItems")) && (as.length >= 2)) {
            this.ofCustomItems = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofCustomSky")) && (as.length >= 2)) {
            this.ofCustomSky = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofShowCapes")) && (as.length >= 2)) {
            this.ofShowCapes = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofNaturalTextures")) && (as.length >= 2)) {
            this.ofNaturalTextures = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofLazyChunkLoading")) && (as.length >= 2)) {
            this.ofLazyChunkLoading = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofFullscreenMode")) && (as.length >= 2)) {
            this.ofFullscreenMode = as[1];
          }
          if ((as[0].equals("ofFastMath")) && (as.length >= 2))
          {
            this.ofFastMath = Boolean.valueOf(as[1]).booleanValue();
            
            on.fastMath = this.ofFastMath;
          }
          if ((as[0].equals("ofFastRender")) && (as.length >= 2)) {
            this.ofFastRender = Boolean.valueOf(as[1]).booleanValue();
          }
          if ((as[0].equals("ofTranslucentBlocks")) && (as.length >= 2))
          {
            this.ofTranslucentBlocks = Integer.valueOf(as[1]).intValue();
            this.ofTranslucentBlocks = Config.limit(this.ofTranslucentBlocks, 0, 2);
          }
          if (as[0].equals("key_" + this.ofKeyBindZoom.h())) {
            this.ofKeyBindZoom.b(Integer.parseInt(as[1]));
          }
        }
        catch (Exception exception1)
        {
          Config.dbg("Skipping bad option: " + s);
          exception1.printStackTrace();
        }
      }
      bcc.c();
      bufferedreader.close();
    }
    catch (Exception exception)
    {
      Config.warn("Failed to load options");
      exception.printStackTrace();
    }
  }
  
  public void saveOfOptions()
  {
    try
    {
      PrintWriter printwriter = new PrintWriter(new FileWriter(this.optionsFileOF));
      
      printwriter.println("ofRenderDistanceChunks:" + this.c);
      
      printwriter.println("ofFogType:" + this.ofFogType);
      printwriter.println("ofFogStart:" + this.ofFogStart);
      
      printwriter.println("ofMipmapType:" + this.ofMipmapType);
      
      printwriter.println("ofOcclusionFancy:" + this.ofOcclusionFancy);
      printwriter.println("ofSmoothFps:" + this.ofSmoothFps);
      printwriter.println("ofSmoothWorld:" + this.ofSmoothWorld);
      printwriter.println("ofAoLevel:" + this.ofAoLevel);
      printwriter.println("ofClouds:" + this.ofClouds);
      printwriter.println("ofCloudsHeight:" + this.ofCloudsHeight);
      printwriter.println("ofTrees:" + this.ofTrees);
      
      printwriter.println("ofDroppedItems:" + this.ofDroppedItems);
      printwriter.println("ofRain:" + this.ofRain);
      
      printwriter.println("ofAnimatedWater:" + this.ofAnimatedWater);
      printwriter.println("ofAnimatedLava:" + this.ofAnimatedLava);
      printwriter.println("ofAnimatedFire:" + this.ofAnimatedFire);
      printwriter.println("ofAnimatedPortal:" + this.ofAnimatedPortal);
      printwriter.println("ofAnimatedRedstone:" + this.ofAnimatedRedstone);
      printwriter.println("ofAnimatedExplosion:" + this.ofAnimatedExplosion);
      printwriter.println("ofAnimatedFlame:" + this.ofAnimatedFlame);
      printwriter.println("ofAnimatedSmoke:" + this.ofAnimatedSmoke);
      printwriter.println("ofVoidParticles:" + this.ofVoidParticles);
      printwriter.println("ofWaterParticles:" + this.ofWaterParticles);
      printwriter.println("ofPortalParticles:" + this.ofPortalParticles);
      printwriter.println("ofPotionParticles:" + this.ofPotionParticles);
      printwriter.println("ofFireworkParticles:" + this.ofFireworkParticles);
      printwriter.println("ofDrippingWaterLava:" + this.ofDrippingWaterLava);
      printwriter.println("ofAnimatedTerrain:" + this.ofAnimatedTerrain);
      printwriter.println("ofAnimatedTextures:" + this.ofAnimatedTextures);
      
      printwriter.println("ofRainSplash:" + this.ofRainSplash);
      printwriter.println("ofLagometer:" + this.ofLagometer);
      printwriter.println("ofShowFps:" + this.ofShowFps);
      printwriter.println("ofAutoSaveTicks:" + this.ofAutoSaveTicks);
      printwriter.println("ofBetterGrass:" + this.ofBetterGrass);
      printwriter.println("ofConnectedTextures:" + this.ofConnectedTextures);
      printwriter.println("ofWeather:" + this.ofWeather);
      printwriter.println("ofSky:" + this.ofSky);
      printwriter.println("ofStars:" + this.ofStars);
      printwriter.println("ofSunMoon:" + this.ofSunMoon);
      printwriter.println("ofVignette:" + this.ofVignette);
      printwriter.println("ofChunkUpdates:" + this.ofChunkUpdates);
      printwriter.println("ofChunkLoading:" + this.ofChunkLoading);
      printwriter.println("ofChunkUpdatesDynamic:" + this.ofChunkUpdatesDynamic);
      printwriter.println("ofTime:" + this.ofTime);
      printwriter.println("ofClearWater:" + this.ofClearWater);
      
      printwriter.println("ofAaLevel:" + this.ofAaLevel);
      printwriter.println("ofAfLevel:" + this.ofAfLevel);
      printwriter.println("ofProfiler:" + this.ofProfiler);
      printwriter.println("ofBetterSnow:" + this.ofBetterSnow);
      printwriter.println("ofSwampColors:" + this.ofSwampColors);
      printwriter.println("ofRandomMobs:" + this.ofRandomMobs);
      printwriter.println("ofSmoothBiomes:" + this.ofSmoothBiomes);
      printwriter.println("ofCustomFonts:" + this.ofCustomFonts);
      printwriter.println("ofCustomColors:" + this.ofCustomColors);
      printwriter.println("ofCustomItems:" + this.ofCustomItems);
      printwriter.println("ofCustomSky:" + this.ofCustomSky);
      printwriter.println("ofShowCapes:" + this.ofShowCapes);
      printwriter.println("ofNaturalTextures:" + this.ofNaturalTextures);
      printwriter.println("ofLazyChunkLoading:" + this.ofLazyChunkLoading);
      printwriter.println("ofFullscreenMode:" + this.ofFullscreenMode);
      printwriter.println("ofFastMath:" + this.ofFastMath);
      printwriter.println("ofFastRender:" + this.ofFastRender);
      printwriter.println("ofTranslucentBlocks:" + this.ofTranslucentBlocks);
      
      printwriter.println("key_" + this.ofKeyBindZoom.h() + ":" + this.ofKeyBindZoom.j());
      
      printwriter.close();
    }
    catch (Exception exception)
    {
      Config.warn("Failed to save options");
      exception.printStackTrace();
    }
  }
  
  private void updateRenderClouds()
  {
    switch (this.ofClouds)
    {
    case 3: 
      this.h = 0;
      break;
    case 1: 
      this.h = 1;
      break;
    case 2: 
      this.h = 2;
      break;
    default: 
      if (this.i) {
        this.h = 2;
      } else {
        this.h = 1;
      }
      break;
    }
  }
  
  public void resetSettings()
  {
    this.c = 8;
    this.d = true;
    this.e = false;
    this.g = ((int)bch.a.i.f());
    
    this.t = false;
    updateVSync();
    
    this.J = 4;
    
    this.i = true;
    this.j = 2;
    this.h = 2;
    this.aw = 70.0F;
    this.ax = 0.0F;
    this.az = 0;
    this.aA = 0;
    this.D = true;
    this.u = false;
    
    this.aC = false;
    
    this.ofFogType = 1;
    this.ofFogStart = 0.8F;
    
    this.ofMipmapType = 0;
    
    this.ofOcclusionFancy = false;
    this.ofSmoothFps = false;
    
    Config.updateAvailableProcessors();
    this.ofSmoothWorld = Config.isSingleProcessor();
    this.ofLazyChunkLoading = Config.isSingleProcessor();
    
    this.ofFastMath = false;
    this.ofFastRender = false;
    
    this.ofTranslucentBlocks = 0;
    
    this.ofAoLevel = 1.0F;
    
    this.ofAaLevel = 0;
    this.ofAfLevel = 1;
    
    this.ofClouds = 0;
    this.ofCloudsHeight = 0.0F;
    this.ofTrees = 0;
    
    this.ofRain = 0;
    
    this.ofBetterGrass = 3;
    this.ofAutoSaveTicks = 4000;
    this.ofLagometer = false;
    this.ofShowFps = false;
    this.ofProfiler = false;
    this.ofWeather = true;
    this.ofSky = true;
    this.ofStars = true;
    this.ofSunMoon = true;
    this.ofVignette = 0;
    this.ofChunkUpdates = 1;
    this.ofChunkLoading = 0;
    this.ofChunkUpdatesDynamic = false;
    this.ofTime = 0;
    this.ofClearWater = false;
    
    this.ofBetterSnow = false;
    this.ofFullscreenMode = "Default";
    this.ofSwampColors = true;
    this.ofRandomMobs = true;
    this.ofSmoothBiomes = true;
    this.ofCustomFonts = true;
    this.ofCustomColors = true;
    this.ofCustomItems = true;
    this.ofCustomSky = true;
    this.ofShowCapes = true;
    this.ofConnectedTextures = 2;
    this.ofNaturalTextures = false;
    
    this.ofAnimatedWater = 0;
    this.ofAnimatedLava = 0;
    this.ofAnimatedFire = true;
    this.ofAnimatedPortal = true;
    this.ofAnimatedRedstone = true;
    this.ofAnimatedExplosion = true;
    this.ofAnimatedFlame = true;
    this.ofAnimatedSmoke = true;
    this.ofVoidParticles = true;
    this.ofWaterParticles = true;
    this.ofRainSplash = true;
    this.ofPortalParticles = true;
    this.ofPotionParticles = true;
    this.ofFireworkParticles = true;
    this.ofDrippingWaterLava = true;
    this.ofAnimatedTerrain = true;
    
    this.ofAnimatedTextures = true;
    
    Shaders.setShaderPack(Shaders.packNameNone);
    Shaders.configAntialiasingLevel = 0;
    Shaders.uninit();
    Shaders.storeConfig();
    
    updateWaterOpacity();
    
    this.am.f();
    
    b();
  }
  
  public void updateVSync()
  {
    Display.setVSyncEnabled(this.t);
  }
  
  private void updateWaterOpacity()
  {
    if ((this.am.D()) && (this.am.F() != null)) {
      Config.waterOpacityChanged = true;
    }
    ClearWater.updateWaterOpacity(this, this.am.f);
  }
  
  public void updateChunkLoading()
  {
    if (this.am.g != null) {
      this.am.g.a();
    }
  }
  
  public void setAllAnimations(boolean flag)
  {
    int animVal = flag ? 0 : 2;
    
    this.ofAnimatedWater = animVal;
    this.ofAnimatedLava = animVal;
    this.ofAnimatedFire = flag;
    this.ofAnimatedPortal = flag;
    this.ofAnimatedRedstone = flag;
    this.ofAnimatedExplosion = flag;
    this.ofAnimatedFlame = flag;
    this.ofAnimatedSmoke = flag;
    this.ofVoidParticles = flag;
    this.ofWaterParticles = flag;
    this.ofRainSplash = flag;
    this.ofPortalParticles = flag;
    this.ofPotionParticles = flag;
    this.ofFireworkParticles = flag;
    this.aA = (flag ? 0 : 2);
    this.ofDrippingWaterLava = flag;
    this.ofAnimatedTerrain = flag;
    
    this.ofAnimatedTextures = flag;
  }
  
  private static int nextValue(int val, int[] vals)
  {
    int index = indexOf(val, vals);
    if (index < 0) {
      return vals[0];
    }
    index++;
    if (index >= vals.length) {
      index = 0;
    }
    return vals[index];
  }
  
  private static int limit(int val, int[] vals)
  {
    int index = indexOf(val, vals);
    if (index < 0) {
      return vals[0];
    }
    return val;
  }
  
  private static int indexOf(int val, int[] vals)
  {
    for (int i = 0; i < vals.length; i++) {
      if (vals[i] == val) {
        return i;
      }
    }
    return -1;
  }
  
  public static enum a
  {
    private final boolean L;
    private final boolean M;
    private final String N;
    private final float O;
    private float P;
    private float Q;
    
    public static a a(int ordinal)
    {
      for (a gamesettings$options : ) {
        if (gamesettings$options.c() == ordinal) {
          return gamesettings$options;
        }
      }
      return null;
    }
    
    private a(String str, boolean isFloat, boolean isBoolean)
    {
      this(str, isFloat, isBoolean, 0.0F, 1.0F, 0.0F);
    }
    
    private a(String str, boolean isFloat, boolean isBoolean, float valMin, float valMax, float valStep)
    {
      this.N = str;
      this.L = isFloat;
      this.M = isBoolean;
      this.P = valMin;
      this.Q = valMax;
      this.O = valStep;
    }
    
    public boolean a()
    {
      return this.L;
    }
    
    public boolean b()
    {
      return this.M;
    }
    
    public int c()
    {
      return ordinal();
    }
    
    public String d()
    {
      return this.N;
    }
    
    public float e()
    {
      return this.P;
    }
    
    public float f()
    {
      return this.Q;
    }
    
    public void a(float value)
    {
      this.Q = value;
    }
    
    public float c(float value)
    {
      return on.a((e(value) - this.P) / (this.Q - this.P), 0.0F, 1.0F);
    }
    
    public float d(float value)
    {
      return e(this.P + (this.Q - this.P) * on.a(value, 0.0F, 1.0F));
    }
    
    public float e(float value)
    {
      value = f(value);
      return on.a(value, this.P, this.Q);
    }
    
    protected float f(float value)
    {
      if (this.O > 0.0F) {
        value = this.O * Math.round(value / this.O);
      }
      return value;
    }
  }
}
