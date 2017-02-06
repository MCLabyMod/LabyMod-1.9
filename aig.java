import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class aig
{
  private static final Logger y = ;
  protected static final arc a = aju.b.u();
  protected static final arc b = aju.a.u();
  protected static final arc c = aju.h.u();
  protected static final arc d = aju.n.u();
  protected static final arc e = aju.cM.u();
  protected static final arc f = aju.A.u();
  protected static final arc g = aju.aI.u();
  protected static final arc h = aju.j.u();
  public static final Set<aig> i = Sets.newHashSet();
  public static final ct<aig> j = new ct();
  protected static final awv k = new awv(new Random(1234L), 1);
  protected static final awv l = new awv(new Random(2345L), 1);
  protected static final atz m = new atz();
  protected static final avg n = new avg(false);
  protected static final atq o = new atq(false);
  protected static final ave p = new ave();
  public static final cx<kk, aig> q = new cx();
  private final String z;
  private final float A;
  private final float B;
  private final float C;
  private final float D;
  private final int E;
  private final boolean F;
  private final boolean G;
  private final String H;
  
  public static int a(aig ☃)
  {
    return q.a(☃);
  }
  
  public static aig a(int ☃)
  {
    return (aig)q.a(☃);
  }
  
  public static aig b(aig ☃)
  {
    return (aig)j.a(a(☃));
  }
  
  public arc r = aju.c.u();
  public arc s = aju.d.u();
  public aij t;
  protected List<aig.c> u = Lists.newArrayList();
  protected List<aig.c> v = Lists.newArrayList();
  protected List<aig.c> w = Lists.newArrayList();
  protected List<aig.c> x = Lists.newArrayList();
  
  protected aig(aig.a ☃)
  {
    this.z = aig.a.a(☃);
    this.A = aig.a.b(☃);
    this.B = aig.a.c(☃);
    this.C = aig.a.d(☃);
    this.D = aig.a.e(☃);
    this.E = aig.a.f(☃);
    this.F = aig.a.g(☃);
    this.G = aig.a.h(☃);
    this.H = aig.a.i(☃);
    
    this.t = a();
    
    this.v.add(new aig.c(we.class, 12, 4, 4));
    this.v.add(new aig.c(wc.class, 10, 4, 4));
    this.v.add(new aig.c(vx.class, 10, 4, 4));
    this.v.add(new aig.c(vy.class, 8, 4, 4));
    
    this.u.add(new aig.c(yy.class, 100, 4, 4));
    this.u.add(new aig.c(za.class, 100, 4, 4));
    this.u.add(new aig.c(yw.class, 100, 4, 4));
    this.u.add(new aig.c(yi.class, 100, 4, 4));
    this.u.add(new aig.c(yx.class, 100, 4, 4));
    this.u.add(new aig.c(yj.class, 10, 1, 4));
    this.u.add(new aig.c(yz.class, 5, 1, 1));
    
    this.w.add(new aig.c(wg.class, 10, 4, 4));
    
    this.x.add(new aig.c(vu.class, 10, 8, 8));
  }
  
  protected aij a()
  {
    return new aij();
  }
  
  public boolean b()
  {
    return this.H != null;
  }
  
  public atp a(Random ☃)
  {
    if (☃.nextInt(10) == 0) {
      return o;
    }
    return n;
  }
  
  public aud b(Random ☃)
  {
    return new avf(apc.a.b);
  }
  
  public alm.a a(Random ☃, cj ☃)
  {
    if (☃.nextInt(3) > 0) {
      return alm.a.a;
    }
    return alm.a.b;
  }
  
  public int a(float ☃)
  {
    ☃ /= 3.0F;
    ☃ = on.a(☃, -1.0F, 1.0F);
    return on.c(0.62222224F - ☃ * 0.05F, 0.5F + ☃ * 0.1F, 1.0F);
  }
  
  public List<aig.c> a(sc ☃)
  {
    switch (aig.1.a[☃.ordinal()])
    {
    case 1: 
      return this.u;
    case 2: 
      return this.v;
    case 3: 
      return this.w;
    case 4: 
      return this.x;
    }
    return Collections.emptyList();
  }
  
  public static enum b
  {
    private b() {}
  }
  
  public static class c
    extends ov.a
  {
    public Class<? extends sb> b;
    public int c;
    public int d;
    
    public c(Class<? extends sb> ☃, int ☃, int ☃, int ☃)
    {
      super();
      this.b = ☃;
      this.c = ☃;
      this.d = ☃;
    }
    
    public String toString()
    {
      return this.b.getSimpleName() + "*(" + this.c + "-" + this.d + "):" + this.a;
    }
  }
  
  public boolean c()
  {
    return p();
  }
  
  public boolean d()
  {
    if (p()) {
      return false;
    }
    return this.G;
  }
  
  public boolean e()
  {
    return k() > 0.85F;
  }
  
  public float f()
  {
    return 0.1F;
  }
  
  public final float a(cj ☃)
  {
    if (☃.q() > 64)
    {
      float ☃ = (float)(k.a(☃.p() / 8.0F, ☃.r() / 8.0F) * 4.0D);
      return n() - (☃ + ☃.q() - 64.0F) * 0.05F / 30.0F;
    }
    return n();
  }
  
  public void a(aht ☃, Random ☃, cj ☃)
  {
    this.t.a(☃, ☃, this, ☃);
  }
  
  public int b(cj ☃)
  {
    double ☃ = on.a(a(☃), 0.0F, 1.0F);
    double ☃ = on.a(k(), 0.0F, 1.0F);
    
    return ahs.a(☃, ☃);
  }
  
  public int c(cj ☃)
  {
    double ☃ = on.a(a(☃), 0.0F, 1.0F);
    double ☃ = on.a(k(), 0.0F, 1.0F);
    
    return ahq.a(☃, ☃);
  }
  
  public void a(aht ☃, Random ☃, atf ☃, int ☃, int ☃, double ☃)
  {
    b(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public final void b(aht ☃, Random ☃, atf ☃, int ☃, int ☃, double ☃)
  {
    int ☃ = ☃.K();
    arc ☃ = this.r;
    arc ☃ = this.s;
    int ☃ = -1;
    int ☃ = (int)(☃ / 3.0D + 3.0D + ☃.nextDouble() * 0.25D);
    
    int ☃ = ☃ & 0xF;
    int ☃ = ☃ & 0xF;
    cj.a ☃ = new cj.a();
    for (int ☃ = 255; ☃ >= 0; ☃--) {
      if (☃ <= ☃.nextInt(5))
      {
        ☃.a(☃, ☃, ☃, c);
      }
      else
      {
        arc ☃ = ☃.a(☃, ☃, ☃);
        if (☃.a() == axe.a) {
          ☃ = -1;
        } else if (☃.t() == aju.b) {
          if (☃ == -1)
          {
            if (☃ <= 0)
            {
              ☃ = b;
              ☃ = a;
            }
            else if ((☃ >= ☃ - 4) && (☃ <= ☃ + 1))
            {
              ☃ = this.r;
              ☃ = this.s;
            }
            if ((☃ < ☃) && ((☃ == null) || (☃.a() == axe.a))) {
              if (a(☃.c(☃, ☃, ☃)) < 0.15F) {
                ☃ = g;
              } else {
                ☃ = h;
              }
            }
            ☃ = ☃;
            if (☃ >= ☃ - 1)
            {
              ☃.a(☃, ☃, ☃, ☃);
            }
            else if (☃ < ☃ - 7 - ☃)
            {
              ☃ = b;
              ☃ = a;
              ☃.a(☃, ☃, ☃, d);
            }
            else
            {
              ☃.a(☃, ☃, ☃, ☃);
            }
          }
          else if (☃ > 0)
          {
            ☃--;
            ☃.a(☃, ☃, ☃, ☃);
            if ((☃ == 0) && (☃.t() == aju.m))
            {
              ☃ = ☃.nextInt(4) + Math.max(0, ☃ - 63);
              ☃ = ☃.c(aof.a) == aof.a.b ? e : f;
            }
          }
        }
      }
    }
  }
  
  public Class<? extends aig> g()
  {
    return getClass();
  }
  
  public aig.b h()
  {
    if (n() < 0.2D) {
      return aig.b.b;
    }
    if (n() < 1.0D) {
      return aig.b.c;
    }
    return aig.b.d;
  }
  
  public static aig b(int ☃)
  {
    return a(☃, null);
  }
  
  public static aig a(int ☃, aig ☃)
  {
    aig ☃ = a(☃);
    if (☃ == null) {
      return ☃;
    }
    return ☃;
  }
  
  public boolean i()
  {
    return false;
  }
  
  public final float j()
  {
    return this.A;
  }
  
  public final float k()
  {
    return this.D;
  }
  
  public final String l()
  {
    return this.z;
  }
  
  public final float m()
  {
    return this.B;
  }
  
  public final float n()
  {
    return this.C;
  }
  
  public final int o()
  {
    return this.E;
  }
  
  public final boolean p()
  {
    return this.F;
  }
  
  public static void q()
  {
    a(0, "ocean", new aix(new aig.a("Ocean").c(-1.0F).d(0.1F)));
    a(1, "plains", new aiy(false, new aig.a("Plains").c(0.125F).d(0.05F).a(0.8F).b(0.4F)));
    a(2, "desert", new aim(new aig.a("Desert").c(0.125F).d(0.05F).a(2.0F).b(0.0F).a()));
    a(3, "extreme_hills", new ain(ain.a.a, new aig.a("Extreme Hills").c(1.0F).d(0.5F).a(0.2F).b(0.3F)));
    a(4, "forest", new aip(aip.a.a, new aig.a("Forest").a(0.7F).b(0.8F)));
    a(5, "taiga", new ajd(ajd.a.a, new aig.a("Taiga").c(0.2F).d(0.2F).a(0.25F).b(0.8F)));
    a(6, "swampland", new ajc(new aig.a("Swampland").c(-0.2F).d(0.1F).a(0.8F).b(0.9F).a(14745518)));
    a(7, "river", new aiz(new aig.a("River").c(-0.5F).d(0.0F)));
    a(8, "hell", new aiq(new aig.a("Hell").a(2.0F).b(0.0F).a()));
    a(9, "sky", new aje(new aig.a("The End").a()));
    a(10, "frozen_ocean", new aix(new aig.a("FrozenOcean").c(-1.0F).d(0.1F).a(0.0F).b(0.5F).b()));
    a(11, "frozen_river", new aiz(new aig.a("FrozenRiver").c(-0.5F).d(0.0F).a(0.0F).b(0.5F).b()));
    a(12, "ice_flats", new air(false, new aig.a("Ice Plains").c(0.125F).d(0.05F).a(0.0F).b(0.5F).b()));
    a(13, "ice_mountains", new air(false, new aig.a("Ice Mountains").c(0.45F).d(0.3F).a(0.0F).b(0.5F).b()));
    a(14, "mushroom_island", new aiu(new aig.a("MushroomIsland").c(0.2F).d(0.3F).a(0.9F).b(1.0F)));
    a(15, "mushroom_island_shore", new aiu(new aig.a("MushroomIslandShore").c(0.0F).d(0.025F).a(0.9F).b(1.0F)));
    a(16, "beaches", new aif(new aig.a("Beach").c(0.0F).d(0.025F).a(0.8F).b(0.4F)));
    a(17, "desert_hills", new aim(new aig.a("DesertHills").c(0.45F).d(0.3F).a(2.0F).b(0.0F).a()));
    a(18, "forest_hills", new aip(aip.a.a, new aig.a("ForestHills").c(0.45F).d(0.3F).a(0.7F).b(0.8F)));
    a(19, "taiga_hills", new ajd(ajd.a.a, new aig.a("TaigaHills").a(0.25F).b(0.8F).c(0.45F).d(0.3F)));
    a(20, "smaller_extreme_hills", new ain(ain.a.b, new aig.a("Extreme Hills Edge").c(0.8F).d(0.3F).a(0.2F).b(0.3F)));
    a(21, "jungle", new ais(false, new aig.a("Jungle").a(0.95F).b(0.9F)));
    a(22, "jungle_hills", new ais(false, new aig.a("JungleHills").c(0.45F).d(0.3F).a(0.95F).b(0.9F)));
    a(23, "jungle_edge", new ais(true, new aig.a("JungleEdge").a(0.95F).b(0.8F)));
    a(24, "deep_ocean", new aix(new aig.a("Deep Ocean").c(-1.8F).d(0.1F)));
    a(25, "stone_beach", new ajb(new aig.a("Stone Beach").c(0.1F).d(0.8F).a(0.2F).b(0.3F)));
    a(26, "cold_beach", new aif(new aig.a("Cold Beach").c(0.0F).d(0.025F).a(0.05F).b(0.3F).b()));
    a(27, "birch_forest", new aip(aip.a.c, new aig.a("Birch Forest").a(0.6F).b(0.6F)));
    a(28, "birch_forest_hills", new aip(aip.a.c, new aig.a("Birch Forest Hills").c(0.45F).d(0.3F).a(0.6F).b(0.6F)));
    a(29, "roofed_forest", new aip(aip.a.d, new aig.a("Roofed Forest").a(0.7F).b(0.8F)));
    a(30, "taiga_cold", new ajd(ajd.a.a, new aig.a("Cold Taiga").c(0.2F).d(0.2F).a(-0.5F).b(0.4F).b()));
    a(31, "taiga_cold_hills", new ajd(ajd.a.a, new aig.a("Cold Taiga Hills").c(0.45F).d(0.3F).a(-0.5F).b(0.4F).b()));
    a(32, "redwood_taiga", new ajd(ajd.a.b, new aig.a("Mega Taiga").a(0.3F).b(0.8F).c(0.2F).d(0.2F)));
    a(33, "redwood_taiga_hills", new ajd(ajd.a.b, new aig.a("Mega Taiga Hills").c(0.45F).d(0.3F).a(0.3F).b(0.8F)));
    a(34, "extreme_hills_with_trees", new ain(ain.a.b, new aig.a("Extreme Hills+").c(1.0F).d(0.5F).a(0.2F).b(0.3F)));
    a(35, "savanna", new aja(new aig.a("Savanna").c(0.125F).d(0.05F).a(1.2F).b(0.0F).a()));
    a(36, "savanna_rock", new aja(new aig.a("Savanna Plateau").c(1.5F).d(0.025F).a(1.0F).b(0.0F).a()));
    a(37, "mesa", new ait(false, false, new aig.a("Mesa").a(2.0F).b(0.0F).a()));
    a(38, "mesa_rock", new ait(false, true, new aig.a("Mesa Plateau F").c(1.5F).d(0.025F).a(2.0F).b(0.0F).a()));
    a(39, "mesa_clear_rock", new ait(false, false, new aig.a("Mesa Plateau").c(1.5F).d(0.025F).a(2.0F).b(0.0F).a()));
    
    a(127, "void", new ajg(new aig.a("The Void").a()));
    
    a(129, "mutated_plains", new aiy(true, new aig.a("Sunflower Plains").a("plains").c(0.125F).d(0.05F).a(0.8F).b(0.4F)));
    a(130, "mutated_desert", new aim(new aig.a("Desert M").a("desert").c(0.225F).d(0.25F).a(2.0F).b(0.0F).a()));
    a(131, "mutated_extreme_hills", new ain(ain.a.c, new aig.a("Extreme Hills M").a("extreme_hills").c(1.0F).d(0.5F).a(0.2F).b(0.3F)));
    a(132, "mutated_forest", new aip(aip.a.b, new aig.a("Flower Forest").a("forest").d(0.4F).a(0.7F).b(0.8F)));
    a(133, "mutated_taiga", new ajd(ajd.a.a, new aig.a("Taiga M").a("taiga").c(0.3F).d(0.4F).a(0.25F).b(0.8F)));
    a(134, "mutated_swampland", new ajc(new aig.a("Swampland M").a("swampland").c(-0.1F).d(0.3F).a(0.8F).b(0.9F).a(14745518)));
    a(140, "mutated_ice_flats", new air(true, new aig.a("Ice Plains Spikes").a("ice_flats").c(0.425F).d(0.45000002F).a(0.0F).b(0.5F).b()));
    a(149, "mutated_jungle", new ais(false, new aig.a("Jungle M").a("jungle").c(0.2F).d(0.4F).a(0.95F).b(0.9F)));
    a(151, "mutated_jungle_edge", new ais(true, new aig.a("JungleEdge M").a("jungle_edge").c(0.2F).d(0.4F).a(0.95F).b(0.8F)));
    
    a(155, "mutated_birch_forest", new aiv(new aig.a("Birch Forest M").a("birch_forest").c(0.2F).d(0.4F).a(0.6F).b(0.6F)));
    a(156, "mutated_birch_forest_hills", new aiv(new aig.a("Birch Forest Hills M").a("birch_forest").c(0.55F).d(0.5F).a(0.6F).b(0.6F)));
    a(157, "mutated_roofed_forest", new aip(aip.a.d, new aig.a("Roofed Forest M").a("roofed_forest").c(0.2F).d(0.4F).a(0.7F).b(0.8F)));
    a(158, "mutated_taiga_cold", new ajd(ajd.a.a, new aig.a("Cold Taiga M").a("taiga_cold").c(0.3F).d(0.4F).a(-0.5F).b(0.4F).b()));
    a(160, "mutated_redwood_taiga", new ajd(ajd.a.c, new aig.a("Mega Spruce Taiga").a("redwood_taiga").c(0.2F).d(0.2F).a(0.25F).b(0.8F)));
    a(161, "mutated_redwood_taiga_hills", new ajd(ajd.a.c, new aig.a("Redwood Taiga Hills M").a("redwood_taiga_hills").c(0.2F).d(0.2F).a(0.25F).b(0.8F)));
    a(162, "mutated_extreme_hills_with_trees", new ain(ain.a.c, new aig.a("Extreme Hills+ M").a("extreme_hills_with_trees").c(1.0F).d(0.5F).a(0.2F).b(0.3F)));
    a(163, "mutated_savanna", new aiw(new aig.a("Savanna M").a("savanna").c(0.3625F).d(1.225F).a(1.1F).b(0.0F).a()));
    a(164, "mutated_savanna_rock", new aiw(new aig.a("Savanna Plateau M").a("savanna_rock").c(1.05F).d(1.2125001F).a(1.0F).b(0.0F).a()));
    a(165, "mutated_mesa", new ait(true, false, new aig.a("Mesa (Bryce)").a("mesa").a(2.0F).b(0.0F).a()));
    a(166, "mutated_mesa_rock", new ait(false, true, new aig.a("Mesa Plateau F M").a("mesa_rock").c(0.45F).d(0.3F).a(2.0F).b(0.0F).a()));
    a(167, "mutated_mesa_clear_rock", new ait(false, false, new aig.a("Mesa Plateau M").a("mesa_clear_rock").c(0.45F).d(0.3F).a(2.0F).b(0.0F).a()));
    
    Collections.addAll(i, new aig[] { ail.a, ail.c, ail.d, ail.e, ail.f, ail.g, ail.h, ail.i, ail.m, ail.n, ail.o, ail.p, ail.q, ail.r, ail.s, ail.t, ail.u, ail.w, ail.x, ail.y, ail.z, ail.A, ail.B, ail.C, ail.D, ail.E, ail.F, ail.G, ail.H, ail.I, ail.J, ail.K, ail.L, ail.M, ail.N, ail.O });
  }
  
  private static void a(int ☃, String ☃, aig ☃)
  {
    q.a(☃, new kk(☃), ☃);
    if (☃.b()) {
      j.a(☃, a((aig)q.c(new kk(☃.H))));
    }
  }
  
  public static class a
  {
    private final String a;
    private float b = 0.1F;
    private float c = 0.2F;
    private float d = 0.5F;
    private float e = 0.5F;
    private int f = 16777215;
    private boolean g;
    private boolean h = true;
    private String i;
    
    public a(String ☃)
    {
      this.a = ☃;
    }
    
    protected a a(float ☃)
    {
      if ((☃ > 0.1F) && (☃ < 0.2F)) {
        throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
      }
      this.d = ☃;
      return this;
    }
    
    protected a b(float ☃)
    {
      this.e = ☃;
      return this;
    }
    
    protected a c(float ☃)
    {
      this.b = ☃;
      return this;
    }
    
    protected a d(float ☃)
    {
      this.c = ☃;
      return this;
    }
    
    protected a a()
    {
      this.h = false;
      return this;
    }
    
    protected a b()
    {
      this.g = true;
      return this;
    }
    
    protected a a(int ☃)
    {
      this.f = ☃;
      return this;
    }
    
    protected a a(String ☃)
    {
      this.i = ☃;
      return this;
    }
  }
}
