import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import net.minecraft.server.MinecraftServer;

public class azh
{
  private String b;
  private int c;
  private boolean d;
  public static final qk a = qk.c;
  private long e;
  private ahy f = ahy.b;
  private String g = "";
  private int h;
  private int i;
  private int j;
  private long k;
  private long l;
  private long m;
  private long n;
  private dn o;
  private int p;
  private String q;
  private int r;
  private int s;
  private boolean t;
  private int u;
  private boolean v;
  private int w;
  private ahw.a x;
  private boolean y;
  private boolean z;
  private boolean A;
  private boolean B;
  private qk C;
  private boolean D;
  private double E = 0.0D;
  private double F = 0.0D;
  private double G = 6.0E7D;
  private long H = 0L;
  private double I = 0.0D;
  private double J = 5.0D;
  private double K = 0.2D;
  private int L = 5;
  private int M = 15;
  private final Map<asw, dn> N = Maps.newEnumMap(asw.class);
  private ahr O = new ahr();
  
  protected azh() {}
  
  public azh(dn ☃)
  {
    if (☃.b("Version", 10))
    {
      dn ☃ = ☃.o("Version");
      this.b = ☃.l("Name");
      this.c = ☃.h("Id");
      this.d = ☃.p("Snapshot");
    }
    this.e = ☃.i("RandomSeed");
    if (☃.b("generatorName", 8))
    {
      String ☃ = ☃.l("generatorName");
      this.f = ahy.a(☃);
      if (this.f == null)
      {
        this.f = ahy.b;
      }
      else if (this.f.f())
      {
        int ☃ = 0;
        if (☃.b("generatorVersion", 99)) {
          ☃ = ☃.h("generatorVersion");
        }
        this.f = this.f.a(☃);
      }
      if (☃.b("generatorOptions", 8)) {
        this.g = ☃.l("generatorOptions");
      }
    }
    this.x = ahw.a.a(☃.h("GameType"));
    if (☃.b("MapFeatures", 99)) {
      this.y = ☃.p("MapFeatures");
    } else {
      this.y = true;
    }
    this.h = ☃.h("SpawnX");
    this.i = ☃.h("SpawnY");
    this.j = ☃.h("SpawnZ");
    this.k = ☃.i("Time");
    if (☃.b("DayTime", 99)) {
      this.l = ☃.i("DayTime");
    } else {
      this.l = this.k;
    }
    this.m = ☃.i("LastPlayed");
    this.n = ☃.i("SizeOnDisk");
    this.q = ☃.l("LevelName");
    this.r = ☃.h("version");
    this.s = ☃.h("clearWeatherTime");
    this.u = ☃.h("rainTime");
    this.t = ☃.p("raining");
    this.w = ☃.h("thunderTime");
    this.v = ☃.p("thundering");
    this.z = ☃.p("hardcore");
    if (☃.b("initialized", 99)) {
      this.B = ☃.p("initialized");
    } else {
      this.B = true;
    }
    if (☃.b("allowCommands", 99)) {
      this.A = ☃.p("allowCommands");
    } else {
      this.A = (this.x == ahw.a.c);
    }
    if (☃.b("Player", 10))
    {
      this.o = ☃.o("Player");
      this.p = this.o.h("Dimension");
    }
    if (☃.b("GameRules", 10)) {
      this.O.a(☃.o("GameRules"));
    }
    if (☃.b("Difficulty", 99)) {
      this.C = qk.a(☃.f("Difficulty"));
    }
    if (☃.b("DifficultyLocked", 1)) {
      this.D = ☃.p("DifficultyLocked");
    }
    if (☃.b("BorderCenterX", 99)) {
      this.E = ☃.k("BorderCenterX");
    }
    if (☃.b("BorderCenterZ", 99)) {
      this.F = ☃.k("BorderCenterZ");
    }
    if (☃.b("BorderSize", 99)) {
      this.G = ☃.k("BorderSize");
    }
    if (☃.b("BorderSizeLerpTime", 99)) {
      this.H = ☃.i("BorderSizeLerpTime");
    }
    if (☃.b("BorderSizeLerpTarget", 99)) {
      this.I = ☃.k("BorderSizeLerpTarget");
    }
    if (☃.b("BorderSafeZone", 99)) {
      this.J = ☃.k("BorderSafeZone");
    }
    if (☃.b("BorderDamagePerBlock", 99)) {
      this.K = ☃.k("BorderDamagePerBlock");
    }
    if (☃.b("BorderWarningBlocks", 99)) {
      this.L = ☃.h("BorderWarningBlocks");
    }
    if (☃.b("BorderWarningTime", 99)) {
      this.M = ☃.h("BorderWarningTime");
    }
    dn ☃;
    if (☃.b("DimensionData", 10))
    {
      ☃ = ☃.o("DimensionData");
      for (String ☃ : ☃.c()) {
        this.N.put(asw.a(Integer.parseInt(☃)), ☃.o(☃));
      }
    }
  }
  
  public azh(ahw ☃, String ☃)
  {
    a(☃);
    this.q = ☃;
    this.C = a;
    this.B = false;
  }
  
  public void a(ahw ☃)
  {
    this.e = ☃.d();
    this.x = ☃.e();
    this.y = ☃.g();
    this.z = ☃.f();
    this.f = ☃.h();
    this.g = ☃.j();
    this.A = ☃.i();
  }
  
  public azh(azh ☃)
  {
    this.e = ☃.e;
    this.f = ☃.f;
    this.g = ☃.g;
    this.x = ☃.x;
    this.y = ☃.y;
    this.h = ☃.h;
    this.i = ☃.i;
    this.j = ☃.j;
    this.k = ☃.k;
    this.l = ☃.l;
    this.m = ☃.m;
    this.n = ☃.n;
    this.o = ☃.o;
    this.p = ☃.p;
    this.q = ☃.q;
    this.r = ☃.r;
    this.u = ☃.u;
    this.t = ☃.t;
    this.w = ☃.w;
    this.v = ☃.v;
    this.z = ☃.z;
    this.A = ☃.A;
    this.B = ☃.B;
    this.O = ☃.O;
    this.C = ☃.C;
    this.D = ☃.D;
    this.E = ☃.E;
    this.F = ☃.F;
    this.G = ☃.G;
    this.H = ☃.H;
    this.I = ☃.I;
    this.J = ☃.J;
    this.K = ☃.K;
    this.M = ☃.M;
    this.L = ☃.L;
  }
  
  public dn a(dn ☃)
  {
    if (☃ == null) {
      ☃ = this.o;
    }
    dn ☃ = new dn();
    a(☃, ☃);
    return ☃;
  }
  
  private void a(dn ☃, dn ☃)
  {
    dn ☃ = new dn();
    ☃.a("Name", "1.9");
    ☃.a("Id", 169);
    ☃.a("Snapshot", false);
    ☃.a("Version", ☃);
    
    ☃.a("DataVersion", 169);
    
    ☃.a("RandomSeed", this.e);
    ☃.a("generatorName", this.f.a());
    ☃.a("generatorVersion", this.f.d());
    ☃.a("generatorOptions", this.g);
    ☃.a("GameType", this.x.a());
    ☃.a("MapFeatures", this.y);
    ☃.a("SpawnX", this.h);
    ☃.a("SpawnY", this.i);
    ☃.a("SpawnZ", this.j);
    ☃.a("Time", this.k);
    ☃.a("DayTime", this.l);
    ☃.a("SizeOnDisk", this.n);
    ☃.a("LastPlayed", MinecraftServer.av());
    ☃.a("LevelName", this.q);
    ☃.a("version", this.r);
    ☃.a("clearWeatherTime", this.s);
    ☃.a("rainTime", this.u);
    ☃.a("raining", this.t);
    ☃.a("thunderTime", this.w);
    ☃.a("thundering", this.v);
    ☃.a("hardcore", this.z);
    ☃.a("allowCommands", this.A);
    ☃.a("initialized", this.B);
    ☃.a("BorderCenterX", this.E);
    ☃.a("BorderCenterZ", this.F);
    ☃.a("BorderSize", this.G);
    ☃.a("BorderSizeLerpTime", this.H);
    ☃.a("BorderSafeZone", this.J);
    ☃.a("BorderDamagePerBlock", this.K);
    ☃.a("BorderSizeLerpTarget", this.I);
    ☃.a("BorderWarningBlocks", this.L);
    ☃.a("BorderWarningTime", this.M);
    if (this.C != null) {
      ☃.a("Difficulty", (byte)this.C.a());
    }
    ☃.a("DifficultyLocked", this.D);
    ☃.a("GameRules", this.O.a());
    
    dn ☃ = new dn();
    for (Map.Entry<asw, dn> ☃ : this.N.entrySet()) {
      ☃.a(String.valueOf(((asw)☃.getKey()).a()), (eb)☃.getValue());
    }
    ☃.a("DimensionData", ☃);
    if (☃ != null) {
      ☃.a("Player", ☃);
    }
  }
  
  public long a()
  {
    return this.e;
  }
  
  public int b()
  {
    return this.h;
  }
  
  public int c()
  {
    return this.i;
  }
  
  public int d()
  {
    return this.j;
  }
  
  public long e()
  {
    return this.k;
  }
  
  public long f()
  {
    return this.l;
  }
  
  public long g()
  {
    return this.n;
  }
  
  public dn h()
  {
    return this.o;
  }
  
  public void a(int ☃)
  {
    this.h = ☃;
  }
  
  public void b(int ☃)
  {
    this.i = ☃;
  }
  
  public void c(int ☃)
  {
    this.j = ☃;
  }
  
  public void b(long ☃)
  {
    this.k = ☃;
  }
  
  public void c(long ☃)
  {
    this.l = ☃;
  }
  
  public void a(cj ☃)
  {
    this.h = ☃.p();
    this.i = ☃.q();
    this.j = ☃.r();
  }
  
  public String j()
  {
    return this.q;
  }
  
  public void a(String ☃)
  {
    this.q = ☃;
  }
  
  public int k()
  {
    return this.r;
  }
  
  public void e(int ☃)
  {
    this.r = ☃;
  }
  
  public long l()
  {
    return this.m;
  }
  
  public int z()
  {
    return this.s;
  }
  
  public void i(int ☃)
  {
    this.s = ☃;
  }
  
  public boolean m()
  {
    return this.v;
  }
  
  public void a(boolean ☃)
  {
    this.v = ☃;
  }
  
  public int n()
  {
    return this.w;
  }
  
  public void f(int ☃)
  {
    this.w = ☃;
  }
  
  public boolean o()
  {
    return this.t;
  }
  
  public void b(boolean ☃)
  {
    this.t = ☃;
  }
  
  public int p()
  {
    return this.u;
  }
  
  public void g(int ☃)
  {
    this.u = ☃;
  }
  
  public ahw.a q()
  {
    return this.x;
  }
  
  public boolean r()
  {
    return this.y;
  }
  
  public void f(boolean ☃)
  {
    this.y = ☃;
  }
  
  public void a(ahw.a ☃)
  {
    this.x = ☃;
  }
  
  public boolean s()
  {
    return this.z;
  }
  
  public void g(boolean ☃)
  {
    this.z = ☃;
  }
  
  public ahy t()
  {
    return this.f;
  }
  
  public void a(ahy ☃)
  {
    this.f = ☃;
  }
  
  public String A()
  {
    return this.g == null ? "" : this.g;
  }
  
  public boolean u()
  {
    return this.A;
  }
  
  public void c(boolean ☃)
  {
    this.A = ☃;
  }
  
  public boolean v()
  {
    return this.B;
  }
  
  public void d(boolean ☃)
  {
    this.B = ☃;
  }
  
  public ahr w()
  {
    return this.O;
  }
  
  public double B()
  {
    return this.E;
  }
  
  public double C()
  {
    return this.F;
  }
  
  public double D()
  {
    return this.G;
  }
  
  public void a(double ☃)
  {
    this.G = ☃;
  }
  
  public long E()
  {
    return this.H;
  }
  
  public void e(long ☃)
  {
    this.H = ☃;
  }
  
  public double F()
  {
    return this.I;
  }
  
  public void b(double ☃)
  {
    this.I = ☃;
  }
  
  public void c(double ☃)
  {
    this.F = ☃;
  }
  
  public void d(double ☃)
  {
    this.E = ☃;
  }
  
  public double G()
  {
    return this.J;
  }
  
  public void e(double ☃)
  {
    this.J = ☃;
  }
  
  public double H()
  {
    return this.K;
  }
  
  public void f(double ☃)
  {
    this.K = ☃;
  }
  
  public int I()
  {
    return this.L;
  }
  
  public int J()
  {
    return this.M;
  }
  
  public void j(int ☃)
  {
    this.L = ☃;
  }
  
  public void k(int ☃)
  {
    this.M = ☃;
  }
  
  public qk x()
  {
    return this.C;
  }
  
  public void a(qk ☃)
  {
    this.C = ☃;
  }
  
  public boolean y()
  {
    return this.D;
  }
  
  public void e(boolean ☃)
  {
    this.D = ☃;
  }
  
  public void a(c ☃)
  {
    ☃.a("Level seed", new Callable()
    {
      public String a()
        throws Exception
      {
        return String.valueOf(azh.this.a());
      }
    });
    ☃.a("Level generator", new Callable()
    {
      public String a()
        throws Exception
      {
        return String.format("ID %02d - %s, ver %d. Features enabled: %b", new Object[] { Integer.valueOf(azh.a(azh.this).g()), azh.a(azh.this).a(), Integer.valueOf(azh.a(azh.this).d()), Boolean.valueOf(azh.b(azh.this)) });
      }
    });
    ☃.a("Level generator options", new Callable()
    {
      public String a()
        throws Exception
      {
        return azh.c(azh.this);
      }
    });
    ☃.a("Level spawn location", new Callable()
    {
      public String a()
        throws Exception
      {
        return c.a(azh.d(azh.this), azh.e(azh.this), azh.f(azh.this));
      }
    });
    ☃.a("Level time", new Callable()
    {
      public String a()
        throws Exception
      {
        return String.format("%d game time, %d day time", new Object[] { Long.valueOf(azh.g(azh.this)), Long.valueOf(azh.h(azh.this)) });
      }
    });
    ☃.a("Level dimension", new Callable()
    {
      public String a()
        throws Exception
      {
        return String.valueOf(azh.i(azh.this));
      }
    });
    ☃.a("Level storage version", new Callable()
    {
      public String a()
        throws Exception
      {
        String ☃ = "Unknown?";
        try
        {
          switch (azh.j(azh.this))
          {
          case 19133: 
            ☃ = "Anvil";
            break;
          case 19132: 
            ☃ = "McRegion";
          }
        }
        catch (Throwable localThrowable) {}
        return String.format("0x%05X - %s", new Object[] { Integer.valueOf(azh.j(azh.this)), ☃ });
      }
    });
    ☃.a("Level weather", new Callable()
    {
      public String a()
        throws Exception
      {
        return String.format("Rain time: %d (now: %b), thunder time: %d (now: %b)", new Object[] { Integer.valueOf(azh.k(azh.this)), Boolean.valueOf(azh.l(azh.this)), Integer.valueOf(azh.m(azh.this)), Boolean.valueOf(azh.n(azh.this)) });
      }
    });
    ☃.a("Level game mode", new Callable()
    {
      public String a()
        throws Exception
      {
        return String.format("Game mode: %s (ID %d). Hardcore: %b. Cheats: %b", new Object[] { azh.o(azh.this).b(), Integer.valueOf(azh.o(azh.this).a()), Boolean.valueOf(azh.p(azh.this)), Boolean.valueOf(azh.q(azh.this)) });
      }
    });
  }
  
  public dn a(asw ☃)
  {
    dn ☃ = (dn)this.N.get(☃);
    if (☃ == null) {
      return new dn();
    }
    return ☃;
  }
  
  public void a(asw ☃, dn ☃)
  {
    this.N.put(☃, ☃);
  }
  
  public int K()
  {
    return this.c;
  }
  
  public boolean L()
  {
    return this.d;
  }
  
  public String M()
  {
    return this.b;
  }
}
