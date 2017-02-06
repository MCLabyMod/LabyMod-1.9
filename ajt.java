import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ajt
{
  private static final kk a = new kk("air");
  public static final co<kk, ajt> h = new co(a);
  public static final ct<arc> i = new ct();
  public static final bbh j = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
  public static final bbh k = null;
  private acq b;
  protected boolean l;
  protected int m;
  protected boolean n;
  protected int o;
  protected boolean p;
  protected float q;
  protected float r;
  
  public static int a(ajt ☃)
  {
    return h.a(☃);
  }
  
  public static int j(arc ☃)
  {
    ajt ☃ = ☃.t();
    return a(☃) + (☃.e(☃) << 12);
  }
  
  public static ajt b(int ☃)
  {
    return (ajt)h.a(☃);
  }
  
  public static arc c(int ☃)
  {
    int ☃ = ☃ & 0xFFF;
    int ☃ = ☃ >> 12 & 0xF;
    return b(☃).a(☃);
  }
  
  public static ajt a(ado ☃)
  {
    if ((☃ instanceof acc)) {
      return ((acc)☃).d();
    }
    return null;
  }
  
  public static ajt b(String ☃)
  {
    kk ☃ = new kk(☃);
    if (h.d(☃)) {
      return (ajt)h.c(☃);
    }
    try
    {
      return (ajt)h.a(Integer.parseInt(☃));
    }
    catch (NumberFormatException localNumberFormatException) {}
    return null;
  }
  
  public boolean k(arc ☃)
  {
    return (☃.a().k()) && (☃.h());
  }
  
  public boolean l(arc ☃)
  {
    return this.l;
  }
  
  public int m(arc ☃)
  {
    return this.m;
  }
  
  public boolean n(arc ☃)
  {
    return this.n;
  }
  
  public int o(arc ☃)
  {
    return this.o;
  }
  
  public boolean p(arc ☃)
  {
    return this.p;
  }
  
  public axe q(arc ☃)
  {
    return this.x;
  }
  
  public axf r(arc ☃)
  {
    return this.y;
  }
  
  public arc a(int ☃)
  {
    return u();
  }
  
  public int e(arc ☃)
  {
    if ((☃ == null) || (☃.r().isEmpty())) {
      return 0;
    }
    throw new IllegalArgumentException("Don't know how to convert " + ☃ + " back into data...");
  }
  
  public arc b(arc ☃, ahx ☃, cj ☃)
  {
    return ☃;
  }
  
  public arc a(arc ☃, aoe ☃)
  {
    return ☃;
  }
  
  public arc a(arc ☃, amr ☃)
  {
    return ☃;
  }
  
  protected boolean s = true;
  protected boolean t;
  protected boolean u;
  protected aop v = aop.d;
  public float w = 1.0F;
  protected final axe x;
  protected final axf y;
  public float z = 0.6F;
  protected final ard A;
  private arc c;
  private String d;
  
  public ajt(axe ☃, axf ☃)
  {
    this.x = ☃;
    this.y = ☃;
    
    this.A = b();
    w(this.A.b());
    
    this.l = u().p();
    this.m = (this.l ? 255 : 0);
    this.n = (!☃.b());
  }
  
  protected ajt(axe ☃)
  {
    this(☃, ☃.r());
  }
  
  protected ajt a(aop ☃)
  {
    this.v = ☃;
    return this;
  }
  
  protected ajt d(int ☃)
  {
    this.m = ☃;
    return this;
  }
  
  protected ajt a(float ☃)
  {
    this.o = ((int)(15.0F * ☃));
    return this;
  }
  
  protected ajt b(float ☃)
  {
    this.r = (☃ * 3.0F);
    return this;
  }
  
  public boolean s(arc ☃)
  {
    return (☃.a().c()) && (☃.h());
  }
  
  public boolean t(arc ☃)
  {
    return (☃.a().k()) && (☃.h()) && (!☃.m());
  }
  
  public boolean j()
  {
    return (this.x.c()) && (u().h());
  }
  
  public boolean c(arc ☃)
  {
    return true;
  }
  
  public boolean b(ahx ☃, cj ☃)
  {
    return !this.x.c();
  }
  
  public aob a(arc ☃)
  {
    return aob.d;
  }
  
  public boolean a(ahx ☃, cj ☃)
  {
    return false;
  }
  
  protected ajt c(float ☃)
  {
    this.q = ☃;
    if (this.r < ☃ * 5.0F) {
      this.r = (☃ * 5.0F);
    }
    return this;
  }
  
  protected ajt k()
  {
    c(-1.0F);
    return this;
  }
  
  public float b(arc ☃, aht ☃, cj ☃)
  {
    return this.q;
  }
  
  protected ajt a(boolean ☃)
  {
    this.t = ☃;
    return this;
  }
  
  public boolean l()
  {
    return this.t;
  }
  
  public boolean m()
  {
    return this.u;
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return j;
  }
  
  public int c(arc ☃, ahx ☃, cj ☃)
  {
    int ☃ = ☃.b(☃, ☃.d());
    if ((☃ == 0) && ((☃.t() instanceof alz)))
    {
      ☃ = ☃.b();
      ☃ = ☃.o(☃);
      return ☃.b(☃, ☃.d());
    }
    return ☃;
  }
  
  public boolean a(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    bbh ☃ = ☃.c(☃, ☃);
    switch (ajt.1.a[☃.ordinal()])
    {
    case 1: 
      if (☃.b > 0.0D) {
        return true;
      }
      break;
    case 2: 
      if (☃.e < 1.0D) {
        return true;
      }
      break;
    case 3: 
      if (☃.c > 0.0D) {
        return true;
      }
      break;
    case 4: 
      if (☃.f < 1.0D) {
        return true;
      }
      break;
    case 5: 
      if (☃.a > 0.0D) {
        return true;
      }
      break;
    case 6: 
      if (☃.d < 1.0D) {
        return true;
      }
      break;
    }
    return !☃.o(☃.a(☃)).p();
  }
  
  public boolean a(ahx ☃, cj ☃, cq ☃)
  {
    return ☃.o(☃).a().a();
  }
  
  public bbh c(arc ☃, aht ☃, cj ☃)
  {
    return ☃.c(☃, ☃).a(☃);
  }
  
  public void a(arc ☃, aht ☃, cj ☃, bbh ☃, List<bbh> ☃, rr ☃)
  {
    a(☃, ☃, ☃, ☃.d(☃, ☃));
  }
  
  protected static void a(cj ☃, bbh ☃, List<bbh> ☃, bbh ☃)
  {
    if (☃ != k)
    {
      bbh ☃ = ☃.a(☃);
      if (☃.b(☃)) {
        ☃.add(☃);
      }
    }
  }
  
  public bbh a(arc ☃, aht ☃, cj ☃)
  {
    return ☃.c(☃, ☃);
  }
  
  public boolean b(arc ☃)
  {
    return true;
  }
  
  public boolean a(arc ☃, boolean ☃)
  {
    return n();
  }
  
  public boolean n()
  {
    return true;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    b(☃, ☃, ☃, ☃);
  }
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃) {}
  
  public void a(arc ☃, aht ☃, cj ☃, Random ☃) {}
  
  public void d(aht ☃, cj ☃, arc ☃) {}
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃) {}
  
  public int a(aht ☃)
  {
    return 10;
  }
  
  public void c(aht ☃, cj ☃, arc ☃) {}
  
  public void b(aht ☃, cj ☃, arc ☃) {}
  
  public int a(Random ☃)
  {
    return 1;
  }
  
  public ado a(arc ☃, Random ☃, int ☃)
  {
    return ado.a(this);
  }
  
  public float a(arc ☃, zj ☃, aht ☃, cj ☃)
  {
    float ☃ = ☃.b(☃, ☃);
    if (☃ < 0.0F) {
      return 0.0F;
    }
    if (!☃.b(☃)) {
      return ☃.a(☃) / ☃ / 100.0F;
    }
    return ☃.a(☃) / ☃ / 30.0F;
  }
  
  public final void b(aht ☃, cj ☃, arc ☃, int ☃)
  {
    a(☃, ☃, ☃, 1.0F, ☃);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, float ☃, int ☃)
  {
    if (☃.E) {
      return;
    }
    int ☃ = a(☃, ☃.r);
    for (int ☃ = 0; ☃ < ☃; ☃++) {
      if (☃.r.nextFloat() <= ☃)
      {
        ado ☃ = a(☃, ☃.r, ☃);
        if (☃ != null) {
          a(☃, ☃, new adq(☃, 1, d(☃)));
        }
      }
    }
  }
  
  public static void a(aht ☃, cj ☃, adq ☃)
  {
    if ((☃.E) || (!☃.U().b("doTileDrops"))) {
      return;
    }
    float ☃ = 0.5F;
    double ☃ = ☃.r.nextFloat() * ☃ + (1.0F - ☃) * 0.5D;
    double ☃ = ☃.r.nextFloat() * ☃ + (1.0F - ☃) * 0.5D;
    double ☃ = ☃.r.nextFloat() * ☃ + (1.0F - ☃) * 0.5D;
    yd ☃ = new yd(☃, ☃.p() + ☃, ☃.q() + ☃, ☃.r() + ☃, ☃);
    ☃.q();
    ☃.a(☃);
  }
  
  protected void b(aht ☃, cj ☃, int ☃)
  {
    if ((!☃.E) && (☃.U().b("doTileDrops"))) {
      while (☃ > 0)
      {
        int ☃ = rx.a(☃);
        ☃ -= ☃;
        ☃.a(new rx(☃, ☃.p() + 0.5D, ☃.q() + 0.5D, ☃.r() + 0.5D, ☃));
      }
    }
  }
  
  public int d(arc ☃)
  {
    return 0;
  }
  
  public float a(rr ☃)
  {
    return this.r / 5.0F;
  }
  
  public bbi a(arc ☃, aht ☃, cj ☃, bbj ☃, bbj ☃)
  {
    return a(☃, ☃, ☃, ☃.c(☃, ☃));
  }
  
  protected bbi a(cj ☃, bbj ☃, bbj ☃, bbh ☃)
  {
    bbj ☃ = ☃.a(☃.p(), ☃.q(), ☃.r());
    bbj ☃ = ☃.a(☃.p(), ☃.q(), ☃.r());
    
    bbi ☃ = ☃.a(☃, ☃);
    if (☃ == null) {
      return null;
    }
    return new bbi(☃.c.b(☃.p(), ☃.q(), ☃.r()), ☃.b, ☃);
  }
  
  public void a(aht ☃, cj ☃, ahp ☃) {}
  
  public ahm f()
  {
    return ahm.a;
  }
  
  public boolean a(aht ☃, cj ☃, cq ☃, adq ☃)
  {
    return b(☃, ☃, ☃);
  }
  
  public boolean b(aht ☃, cj ☃, cq ☃)
  {
    return a(☃, ☃);
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    return ☃.o(☃).t().x.j();
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, zj ☃, qm ☃, adq ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    return false;
  }
  
  public void a(aht ☃, cj ☃, rr ☃) {}
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    return a(☃);
  }
  
  public void a(aht ☃, cj ☃, zj ☃) {}
  
  public bbj a(aht ☃, cj ☃, rr ☃, bbj ☃)
  {
    return ☃;
  }
  
  public int b(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    return 0;
  }
  
  public boolean g(arc ☃)
  {
    return false;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, rr ☃) {}
  
  public int c(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    return 0;
  }
  
  public void a(aht ☃, zj ☃, cj ☃, arc ☃, apv ☃, adq ☃)
  {
    ☃.b(nt.a(this));
    ☃.a(0.025F);
    if ((o()) && (ago.a(agq.r, ☃) > 0))
    {
      adq ☃ = u(☃);
      if (☃ != null) {
        a(☃, ☃, ☃);
      }
    }
    else
    {
      int ☃ = ago.a(agq.t, ☃);
      b(☃, ☃, ☃, ☃);
    }
  }
  
  protected boolean o()
  {
    return (u().h()) && (!this.u);
  }
  
  protected adq u(arc ☃)
  {
    ado ☃ = ado.a(this);
    if (☃ == null) {
      return null;
    }
    int ☃ = 0;
    if (☃.k()) {
      ☃ = e(☃);
    }
    return new adq(☃, 1, ☃);
  }
  
  public int a(int ☃, Random ☃)
  {
    return a(☃);
  }
  
  public void a(aht ☃, cj ☃, arc ☃, sa ☃, adq ☃) {}
  
  public boolean d()
  {
    return (!this.x.a()) && (!this.x.d());
  }
  
  public ajt c(String ☃)
  {
    this.d = ☃;
    return this;
  }
  
  public String c()
  {
    return di.a(a() + ".name");
  }
  
  public String a()
  {
    return "tile." + this.d;
  }
  
  public boolean a(aht ☃, cj ☃, arc ☃, int ☃, int ☃)
  {
    return false;
  }
  
  public boolean p()
  {
    return this.s;
  }
  
  protected ajt q()
  {
    this.s = false;
    return this;
  }
  
  public axh h(arc ☃)
  {
    return this.x.m();
  }
  
  public float f(arc ☃)
  {
    return ☃.k() ? 0.2F : 1.0F;
  }
  
  public void a(aht ☃, cj ☃, rr ☃, float ☃)
  {
    ☃.e(☃, 1.0F);
  }
  
  public void a(aht ☃, rr ☃)
  {
    ☃.t = 0.0D;
  }
  
  public adq a(aht ☃, cj ☃, arc ☃)
  {
    return new adq(ado.a(this), 1, d(☃));
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    ☃.add(new adq(☃));
  }
  
  public acq r()
  {
    return this.b;
  }
  
  public ajt a(acq ☃)
  {
    this.b = ☃;
    return this;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, zj ☃) {}
  
  public void h(aht ☃, cj ☃) {}
  
  public boolean s()
  {
    return true;
  }
  
  public boolean a(ahp ☃)
  {
    return true;
  }
  
  public boolean b(ajt ☃)
  {
    return this == ☃;
  }
  
  public static boolean a(ajt ☃, ajt ☃)
  {
    if ((☃ == null) || (☃ == null)) {
      return false;
    }
    if (☃ == ☃) {
      return true;
    }
    return ☃.b(☃);
  }
  
  public boolean v(arc ☃)
  {
    return false;
  }
  
  public int d(arc ☃, aht ☃, cj ☃)
  {
    return 0;
  }
  
  protected ard b()
  {
    return new ard(this, new arr[0]);
  }
  
  public ard t()
  {
    return this.A;
  }
  
  protected final void w(arc ☃)
  {
    this.c = ☃;
  }
  
  public final arc u()
  {
    return this.c;
  }
  
  public ajt.a v()
  {
    return ajt.a.a;
  }
  
  public aop w()
  {
    return this.v;
  }
  
  public String toString()
  {
    return "Block{" + h.b(this) + "}";
  }
  
  public static void x()
  {
    a(0, a, new ajj().c("air"));
    a(1, "stone", new aox().c(1.5F).b(10.0F).a(aop.d).c("stone"));
    a(2, "grass", new alv().c(0.6F).a(aop.c).c("grass"));
    a(3, "dirt", new akt().c(0.5F).a(aop.b).c("dirt"));
    ajt ☃ = new ajt(axe.e).c(2.0F).b(10.0F).a(aop.d).c("stonebrick").a(acq.b);
    a(4, "cobblestone", ☃);
    ajt ☃ = new anj().c(2.0F).b(5.0F).a(aop.a).c("wood");
    a(5, "planks", ☃);
    a(6, "sapling", new aoh().c(0.0F).a(aop.c).c("sapling"));
    a(7, "bedrock", new anc(axe.e).k().b(6000000.0F).a(aop.d).c("bedrock").q().a(acq.b));
    a(8, "flowing_water", new akz(axe.h).c(100.0F).d(3).c("water").q());
    a(9, "water", new aov(axe.h).c(100.0F).d(3).c("water").q());
    a(10, "flowing_lava", new akz(axe.i).c(100.0F).a(1.0F).c("lava").q());
    a(11, "lava", new aov(axe.i).c(100.0F).a(1.0F).c("lava").q());
    a(12, "sand", new aof().c(0.5F).a(aop.h).c("sand"));
    a(13, "gravel", new alx().c(0.6F).a(aop.b).c("gravel"));
    a(14, "gold_ore", new anh().c(3.0F).b(5.0F).a(aop.d).c("oreGold"));
    a(15, "iron_ore", new anh().c(3.0F).b(5.0F).a(aop.d).c("oreIron"));
    a(16, "coal_ore", new anh().c(3.0F).b(5.0F).a(aop.d).c("oreCoal"));
    a(17, "log", new ang().c("log"));
    a(18, "leaves", new anf().c("leaves"));
    a(19, "sponge", new aoq().c(0.6F).a(aop.c).c("sponge"));
    a(20, "glass", new alt(axe.s, false).c(0.3F).a(aop.f).c("glass"));
    a(21, "lapis_ore", new anh().c(3.0F).b(5.0F).a(aop.d).c("oreLapis"));
    a(22, "lapis_block", new ajt(axe.f, axf.H).c(3.0F).b(5.0F).a(aop.d).c("blockLapis").a(acq.b));
    a(23, "dispenser", new aku().c(3.5F).a(aop.d).c("dispenser"));
    ajt ☃ = new aog().a(aop.d).c(0.8F).c("sandStone");
    a(24, "sandstone", ☃);
    a(25, "noteblock", new and().a(aop.a).c(0.8F).c("musicBlock"));
    a(26, "bed", new ajr().a(aop.a).c(0.2F).c("bed").q());
    a(27, "golden_rail", new ann().c(0.7F).a(aop.e).c("goldenRail"));
    a(28, "detector_rail", new akq().c(0.7F).a(aop.e).c("detectorRail"));
    a(29, "sticky_piston", new aqu(true).c("pistonStickyBase"));
    a(30, "web", new apn().d(1).c(4.0F).c("web"));
    a(31, "tallgrass", new apc().c(0.0F).a(aop.c).c("tallgrass"));
    a(32, "deadbush", new akp().c(0.0F).a(aop.c).c("deadbush"));
    a(33, "piston", new aqu(false).c("pistonBase"));
    a(34, "piston_head", new aqv().c("pistonBase"));
    a(35, "wool", new akj(axe.n).c(0.8F).a(aop.g).c("cloth"));
    a(36, "piston_extension", new aqw());
    a(37, "yellow_flower", new aps().c(0.0F).a(aop.c).c("flower1"));
    a(38, "red_flower", new anu().c(0.0F).a(aop.c).c("flower2"));
    ajt ☃ = new amu().c(0.0F).a(aop.c).a(0.125F).c("mushroom");
    a(39, "brown_mushroom", ☃);
    ajt ☃ = new amu().c(0.0F).a(aop.c).c("mushroom");
    a(40, "red_mushroom", ☃);
    a(41, "gold_block", new ajt(axe.f, axf.F).c(3.0F).b(10.0F).a(aop.e).c("blockGold").a(acq.b));
    a(42, "iron_block", new ajt(axe.f, axf.h).c(5.0F).b(10.0F).a(aop.e).c("blockIron").a(acq.b));
    a(43, "double_stone_slab", new alq().c(2.0F).b(10.0F).a(aop.d).c("stoneSlab"));
    a(44, "stone_slab", new ama().c(2.0F).b(10.0F).a(aop.d).c("stoneSlab"));
    ajt ☃ = new ajt(axe.e, axf.D).c(2.0F).b(10.0F).a(aop.d).c("brick").a(acq.b);
    a(45, "brick_block", ☃);
    a(46, "tnt", new ape().c(0.0F).a(aop.c).c("tnt"));
    a(47, "bookshelf", new ajw().c(1.5F).a(aop.a).c("bookshelf"));
    a(48, "mossy_cobblestone", new ajt(axe.e).c(2.0F).b(10.0F).a(aop.d).c("stoneMoss").a(acq.b));
    a(49, "obsidian", new ane().c(50.0F).b(2000.0F).a(aop.d).c("obsidian"));
    a(50, "torch", new apf().c(0.0F).a(0.9375F).a(aop.a).c("torch"));
    a(51, "fire", new all().c(0.0F).a(1.0F).a(aop.g).c("fire").q());
    a(52, "mob_spawner", new ams().c(5.0F).a(aop.e).c("mobSpawner").q());
    a(53, "oak_stairs", new aot(☃.u().a(anj.a, anj.a.a)).c("stairsWood"));
    a(54, "chest", new ake(ake.a.a).c(2.5F).a(aop.a).c("chest"));
    a(55, "redstone_wire", new anx().c(0.0F).a(aop.d).c("redstoneDust").q());
    a(56, "diamond_ore", new anh().c(3.0F).b(5.0F).a(aop.d).c("oreDiamond"));
    a(57, "diamond_block", new ajt(axe.f, axf.G).c(5.0F).b(10.0F).a(aop.e).c("blockDiamond").a(acq.b));
    a(58, "crafting_table", new akm().c(2.5F).a(aop.a).c("workbench"));
    a(59, "wheat", new akn().c("crops"));
    ajt ☃ = new ali().c(0.6F).a(aop.b).c("farmland");
    a(60, "farmland", ☃);
    a(61, "furnace", new als(false).c(3.5F).a(aop.d).c("furnace").a(acq.c));
    a(62, "lit_furnace", new als(true).c(3.5F).a(aop.d).a(0.875F).c("furnace"));
    a(63, "standing_sign", new aou().c(1.0F).a(aop.a).c("sign").q());
    a(64, "wooden_door", new akv(axe.d).c(3.0F).a(aop.a).c("doorOak").q());
    a(65, "ladder", new amk().c(0.4F).a(aop.j).c("ladder"));
    a(66, "rail", new ant().c(0.7F).a(aop.e).c("rail"));
    a(67, "stone_stairs", new aot(☃.u()).c("stairsStone"));
    a(68, "wall_sign", new apl().c(1.0F).a(aop.a).c("sign").q());
    a(69, "lever", new amn().c(0.5F).a(aop.a).c("lever"));
    a(70, "stone_pressure_plate", new ano(axe.e, ano.a.b).c(0.5F).a(aop.d).c("pressurePlateStone"));
    a(71, "iron_door", new akv(axe.f).c(5.0F).a(aop.e).c("doorIron").q());
    a(72, "wooden_pressure_plate", new ano(axe.d, ano.a.a).c(0.5F).a(aop.a).c("pressurePlateWood"));
    a(73, "redstone_ore", new anw(false).c(3.0F).b(5.0F).a(aop.d).c("oreRedstone").a(acq.b));
    a(74, "lit_redstone_ore", new anw(true).a(0.625F).c(3.0F).b(5.0F).a(aop.d).c("oreRedstone"));
    a(75, "unlit_redstone_torch", new anz(false).c(0.0F).a(aop.a).c("notGate"));
    a(76, "redstone_torch", new anz(true).c(0.0F).a(0.5F).a(aop.a).c("notGate").a(acq.d));
    a(77, "stone_button", new aoz().c(0.5F).a(aop.d).c("button"));
    a(78, "snow_layer", new aon().c(0.1F).a(aop.i).c("snow").d(0));
    a(79, "ice", new ami().c(0.5F).d(3).a(aop.f).c("ice"));
    a(80, "snow", new aom().c(0.2F).a(aop.i).c("snow"));
    a(81, "cactus", new aka().c(0.4F).a(aop.g).c("cactus"));
    a(82, "clay", new akh().c(0.6F).a(aop.b).c("clay"));
    a(83, "reeds", new aoa().c(0.0F).a(aop.c).c("reeds").q());
    a(84, "jukebox", new amj().c(2.0F).b(10.0F).a(aop.d).c("jukebox"));
    a(85, "fence", new alj(axe.d, anj.a.a.c()).c(2.0F).b(5.0F).a(aop.a).c("fence"));
    ajt ☃ = new anq().c(1.0F).a(aop.a).c("pumpkin");
    a(86, "pumpkin", ☃);
    a(87, "netherrack", new amy().c(0.4F).a(aop.d).c("hellrock"));
    a(88, "soul_sand", new aoo().c(0.5F).a(aop.h).c("hellsand"));
    a(89, "glowstone", new alu(axe.s).c(0.3F).a(aop.f).a(1.0F).c("lightgem"));
    a(90, "portal", new ank().c(-1.0F).a(aop.f).a(0.75F).c("portal"));
    a(91, "lit_pumpkin", new anq().c(1.0F).a(aop.a).a(1.0F).c("litpumpkin"));
    a(92, "cake", new akb().c(0.5F).a(aop.g).c("cake").q());
    a(93, "unpowered_repeater", new aoc(false).c(0.0F).a(aop.a).c("diode").q());
    a(94, "powered_repeater", new aoc(true).c(0.0F).a(aop.a).c("diode").q());
    a(95, "stained_glass", new aor(axe.s).c(0.3F).a(aop.f).c("stainedGlass"));
    a(96, "trapdoor", new apg(axe.d).c(3.0F).a(aop.a).c("trapdoor").q());
    a(97, "monster_egg", new amt().c(0.75F).c("monsterStoneEgg"));
    ajt ☃ = new aoy().c(1.5F).b(10.0F).a(aop.d).c("stonebricksmooth");
    a(98, "stonebrick", ☃);
    a(99, "brown_mushroom_block", new amh(axe.d, axf.l, ☃).c(0.2F).a(aop.a).c("mushroom"));
    a(100, "red_mushroom_block", new amh(axe.d, axf.D, ☃).c(0.2F).a(aop.a).c("mushroom"));
    a(101, "iron_bars", new apd(axe.f, true).c(5.0F).b(10.0F).a(aop.e).c("fenceIron"));
    a(102, "glass_pane", new apd(axe.s, false).c(0.3F).a(aop.f).c("thinGlass"));
    ajt ☃ = new amq().c(1.0F).a(aop.a).c("melon");
    a(103, "melon_block", ☃);
    a(104, "pumpkin_stem", new aow(☃).c(0.0F).a(aop.a).c("pumpkinStem"));
    a(105, "melon_stem", new aow(☃).c(0.0F).a(aop.a).c("pumpkinStem"));
    a(106, "vine", new apj().c(0.2F).a(aop.c).c("vine"));
    a(107, "fence_gate", new alk(anj.a.a).c(2.0F).b(5.0F).a(aop.a).c("fenceGate"));
    a(108, "brick_stairs", new aot(☃.u()).c("stairsBrick"));
    a(109, "stone_brick_stairs", new aot(☃.u().a(aoy.a, aoy.a.a)).c("stairsStoneBrickSmooth"));
    a(110, "mycelium", new amv().c(0.6F).a(aop.c).c("mycel"));
    a(111, "waterlily", new apm().c(0.0F).a(aop.c).c("waterlily"));
    ajt ☃ = new amw().c(2.0F).b(10.0F).a(aop.d).c("netherBrick").a(acq.b);
    a(112, "nether_brick", ☃);
    a(113, "nether_brick_fence", new alj(axe.e, axf.K).c(2.0F).b(10.0F).a(aop.d).c("netherFence"));
    a(114, "nether_brick_stairs", new aot(☃.u()).c("stairsNetherBrick"));
    a(115, "nether_wart", new amx().c("netherStalk"));
    a(116, "enchanting_table", new ala().c(5.0F).b(2000.0F).c("enchantmentTable"));
    a(117, "brewing_stand", new ajx().c(0.5F).a(0.125F).c("brewingStand"));
    a(118, "cauldron", new akd().c(2.0F).c("cauldron"));
    a(119, "end_portal", new alc(axe.E).c(-1.0F).b(6000000.0F));
    a(120, "end_portal_frame", new ald().a(aop.f).a(0.125F).c(-1.0F).c("endPortalFrame").b(6000000.0F).a(acq.c));
    a(121, "end_stone", new ajt(axe.e, axf.d).c(3.0F).b(15.0F).a(aop.d).c("whiteStone").a(acq.b));
    a(122, "dragon_egg", new akx().c(3.0F).b(15.0F).a(aop.d).a(0.125F).c("dragonEgg"));
    a(123, "redstone_lamp", new any(false).c(0.3F).a(aop.f).c("redstoneLight").a(acq.d));
    a(124, "lit_redstone_lamp", new any(true).c(0.3F).a(aop.f).c("redstoneLight"));
    a(125, "double_wooden_slab", new alr().c(2.0F).b(5.0F).a(aop.a).c("woodSlab"));
    a(126, "wooden_slab", new amc().c(2.0F).b(5.0F).a(aop.a).c("woodSlab"));
    a(127, "cocoa", new aki().c(0.2F).b(5.0F).a(aop.a).c("cocoa"));
    a(128, "sandstone_stairs", new aot(☃.u().a(aog.a, aog.a.c)).c("stairsSandStone"));
    a(129, "emerald_ore", new anh().c(3.0F).b(5.0F).a(aop.d).c("oreEmerald"));
    a(130, "ender_chest", new alf().c(22.5F).b(1000.0F).a(aop.d).c("enderChest").a(0.5F));
    a(131, "tripwire_hook", new api().c("tripWireSource"));
    a(132, "tripwire", new aph().c("tripWire"));
    a(133, "emerald_block", new ajt(axe.f, axf.I).c(5.0F).b(10.0F).a(aop.e).c("blockEmerald").a(acq.b));
    a(134, "spruce_stairs", new aot(☃.u().a(anj.a, anj.a.b)).c("stairsWoodSpruce"));
    a(135, "birch_stairs", new aot(☃.u().a(anj.a, anj.a.c)).c("stairsWoodBirch"));
    a(136, "jungle_stairs", new aot(☃.u().a(anj.a, anj.a.d)).c("stairsWoodJungle"));
    a(137, "command_block", new akk(axf.B).k().b(6000000.0F).c("commandBlock"));
    a(138, "beacon", new ajq().c("beacon").a(1.0F));
    a(139, "cobblestone_wall", new apk(☃).c("cobbleWall"));
    a(140, "flower_pot", new aln().c(0.0F).a(aop.d).c("flowerPot"));
    a(141, "carrots", new akc().c("carrots"));
    a(142, "potatoes", new anl().c("potatoes"));
    a(143, "wooden_button", new app().c(0.5F).a(aop.a).c("button"));
    a(144, "skull", new aok().c(1.0F).a(aop.d).c("skull"));
    a(145, "anvil", new ajk().c(5.0F).a(aop.k).b(2000.0F).c("anvil"));
    a(146, "trapped_chest", new ake(ake.a.b).c(2.5F).a(aop.a).c("chestTrap"));
    a(147, "light_weighted_pressure_plate", new apo(axe.f, 15, axf.F).c(0.5F).a(aop.a).c("weightedPlate_light"));
    a(148, "heavy_weighted_pressure_plate", new apo(axe.f, 150).c(0.5F).a(aop.a).c("weightedPlate_heavy"));
    a(149, "unpowered_comparator", new akl(false).c(0.0F).a(aop.a).c("comparator").q());
    a(150, "powered_comparator", new akl(true).c(0.0F).a(0.625F).a(aop.a).c("comparator").q());
    a(151, "daylight_detector", new ako(false));
    a(152, "redstone_block", new anm(axe.f, axf.f).c(5.0F).b(10.0F).a(aop.e).c("blockRedstone").a(acq.d));
    a(153, "quartz_ore", new anh(axf.K).c(3.0F).b(5.0F).a(aop.d).c("netherquartz"));
    a(154, "hopper", new amf().c(3.0F).b(8.0F).a(aop.e).c("hopper"));
    ajt ☃ = new ans().a(aop.d).c(0.8F).c("quartzBlock");
    a(155, "quartz_block", ☃);
    a(156, "quartz_stairs", new aot(☃.u().a(ans.a, ans.a.a)).c("stairsQuartz"));
    a(157, "activator_rail", new ann().c(0.7F).a(aop.e).c("activatorRail"));
    a(158, "dropper", new aky().c(3.5F).a(aop.d).c("dropper"));
    a(159, "stained_hardened_clay", new akj(axe.e).c(1.25F).b(7.0F).a(aop.d).c("clayHardenedStained"));
    a(160, "stained_glass_pane", new aos().c(0.3F).a(aop.f).c("thinStainedGlass"));
    a(161, "leaves2", new amz().c("leaves"));
    a(162, "log2", new ana().c("log"));
    a(163, "acacia_stairs", new aot(☃.u().a(anj.a, anj.a.e)).c("stairsWoodAcacia"));
    a(164, "dark_oak_stairs", new aot(☃.u().a(anj.a, anj.a.f)).c("stairsWoodDarkOak"));
    a(165, "slime", new aol().c("slime").a(aop.l));
    a(166, "barrier", new ajm().c("barrier"));
    a(167, "iron_trapdoor", new apg(axe.f).c(5.0F).a(aop.e).c("ironTrapdoor").q());
    a(168, "prismarine", new anp().c(1.5F).b(10.0F).a(aop.d).c("prismarine"));
    a(169, "sea_lantern", new aoi(axe.s).c(0.3F).a(aop.f).a(1.0F).c("seaLantern"));
    a(170, "hay_block", new ame().c(0.5F).a(aop.c).c("hayBlock").a(acq.b));
    a(171, "carpet", new apr().c(0.1F).a(aop.g).c("woolCarpet").d(0));
    a(172, "hardened_clay", new amd().c(1.25F).b(7.0F).a(aop.d).c("clayHardened"));
    a(173, "coal_block", new ajt(axe.e, axf.E).c(5.0F).b(10.0F).a(aop.d).c("blockCoal").a(acq.b));
    a(174, "packed_ice", new ani().c(0.5F).a(aop.f).c("icePacked"));
    a(175, "double_plant", new akw());
    a(176, "standing_banner", new ajl.a().c(1.0F).a(aop.a).c("banner").q());
    a(177, "wall_banner", new ajl.b().c(1.0F).a(aop.a).c("banner").q());
    a(178, "daylight_detector_inverted", new ako(true));
    ajt ☃ = new anv().a(aop.d).c(0.8F).c("redSandStone");
    a(179, "red_sandstone", ☃);
    a(180, "red_sandstone_stairs", new aot(☃.u().a(anv.a, anv.a.c)).c("stairsRedSandStone"));
    a(181, "double_stone_slab2", new alp().c(2.0F).b(10.0F).a(aop.d).c("stoneSlab2"));
    a(182, "stone_slab2", new aly().c(2.0F).b(10.0F).a(aop.d).c("stoneSlab2"));
    a(183, "spruce_fence_gate", new alk(anj.a.b).c(2.0F).b(5.0F).a(aop.a).c("spruceFenceGate"));
    a(184, "birch_fence_gate", new alk(anj.a.c).c(2.0F).b(5.0F).a(aop.a).c("birchFenceGate"));
    a(185, "jungle_fence_gate", new alk(anj.a.d).c(2.0F).b(5.0F).a(aop.a).c("jungleFenceGate"));
    a(186, "dark_oak_fence_gate", new alk(anj.a.f).c(2.0F).b(5.0F).a(aop.a).c("darkOakFenceGate"));
    a(187, "acacia_fence_gate", new alk(anj.a.e).c(2.0F).b(5.0F).a(aop.a).c("acaciaFenceGate"));
    a(188, "spruce_fence", new alj(axe.d, anj.a.b.c()).c(2.0F).b(5.0F).a(aop.a).c("spruceFence"));
    a(189, "birch_fence", new alj(axe.d, anj.a.c.c()).c(2.0F).b(5.0F).a(aop.a).c("birchFence"));
    a(190, "jungle_fence", new alj(axe.d, anj.a.d.c()).c(2.0F).b(5.0F).a(aop.a).c("jungleFence"));
    a(191, "dark_oak_fence", new alj(axe.d, anj.a.f.c()).c(2.0F).b(5.0F).a(aop.a).c("darkOakFence"));
    a(192, "acacia_fence", new alj(axe.d, anj.a.e.c()).c(2.0F).b(5.0F).a(aop.a).c("acaciaFence"));
    a(193, "spruce_door", new akv(axe.d).c(3.0F).a(aop.a).c("doorSpruce").q());
    a(194, "birch_door", new akv(axe.d).c(3.0F).a(aop.a).c("doorBirch").q());
    a(195, "jungle_door", new akv(axe.d).c(3.0F).a(aop.a).c("doorJungle").q());
    a(196, "acacia_door", new akv(axe.d).c(3.0F).a(aop.a).c("doorAcacia").q());
    a(197, "dark_oak_door", new akv(axe.d).c(3.0F).a(aop.a).c("doorDarkOak").q());
    a(198, "end_rod", new ale().c(0.0F).a(0.9375F).a(aop.a).c("endRod"));
    a(199, "chorus_plant", new akg().c(0.4F).a(aop.a).c("chorusPlant"));
    a(200, "chorus_flower", new akf().c(0.4F).a(aop.a).c("chorusFlower"));
    ajt ☃ = new ajt(axe.e).c(1.5F).b(10.0F).a(aop.d).a(acq.b).c("purpurBlock");
    a(201, "purpur_block", ☃);
    a(202, "purpur_pillar", new aod(axe.e).c(1.5F).b(10.0F).a(aop.d).a(acq.b).c("purpurPillar"));
    a(203, "purpur_stairs", new aot(☃.u()).c("stairsPurpur"));
    a(204, "purpur_double_slab", new anr.a().c(2.0F).b(10.0F).a(aop.d).c("purpurSlab"));
    a(205, "purpur_slab", new anr.b().c(2.0F).b(10.0F).a(aop.d).c("purpurSlab"));
    a(206, "end_bricks", new ajt(axe.e).a(aop.d).c(0.8F).a(acq.b).c("endBricks"));
    a(207, "beetroots", new ajs().c("beetroots"));
    ajt ☃ = new alw().c(0.65F).a(aop.c).c("grassPath").q();
    a(208, "grass_path", ☃);
    a(209, "end_gateway", new alb(axe.E).c(-1.0F).b(6000000.0F));
    a(210, "repeating_command_block", new akk(axf.z).k().b(6000000.0F).c("repeatingCommandBlock"));
    a(211, "chain_command_block", new akk(axf.C).k().b(6000000.0F).c("chainCommandBlock"));
    a(212, "frosted_ice", new alo().c(0.5F).d(3).a(aop.f).c("frostedIce"));
    
    a(255, "structure_block", new apb().k().b(6000000.0F).c("structureBlock").a(1.0F));
    
    h.a();
    for (ajt ☃ : h) {
      if (☃.x == axe.a)
      {
        ☃.p = false;
      }
      else
      {
        boolean ☃ = false;
        boolean ☃ = ☃ instanceof aot;
        boolean ☃ = ☃ instanceof alz;
        boolean ☃ = (☃ == ☃) || (☃ == ☃);
        boolean ☃ = ☃.n;
        boolean ☃ = ☃.m == 0;
        if ((☃) || (☃) || (☃) || (☃) || (☃)) {
          ☃ = true;
        }
        ☃.p = ☃;
      }
    }
    Set<ajt> ☃ = Sets.newHashSet(new ajt[] { (ajt)h.c(new kk("tripwire")) });
    for (Iterator ☃ = h.iterator(); ☃.hasNext();)
    {
      ☃ = (ajt)☃.next();
      if (☃.contains(☃)) {
        for (int ☃ = 0; ☃ < 15; ☃++)
        {
          int ☃ = h.a(☃) << 4 | ☃;
          i.a(☃.a(☃), ☃);
        }
      } else {
        for (arc ☃ : ☃.t().a())
        {
          int ☃ = h.a(☃) << 4 | ☃.e(☃);
          i.a(☃, ☃);
        }
      }
    }
    ajt ☃;
  }
  
  private static void a(int ☃, kk ☃, ajt ☃)
  {
    h.a(☃, ☃, ☃);
  }
  
  private static void a(int ☃, String ☃, ajt ☃)
  {
    a(☃, new kk(☃), ☃);
  }
  
  public static enum a
  {
    private a() {}
  }
}
