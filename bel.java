import com.google.common.base.Predicate;
import com.google.common.primitives.Floats;
import java.util.List;
import java.util.Random;

public class bel
  extends bfb
  implements bde.a, bdo.b
{
  private bhk i;
  protected String a = "Customize World Settings";
  protected String f = "Page 1 of 3";
  protected String g = "Basic Settings";
  protected String[] h = new String[4];
  private bdo r;
  private bcz s;
  private bcz t;
  private bcz u;
  private bcz v;
  private bcz w;
  private bcz x;
  private bcz y;
  private bcz z;
  private boolean A = false;
  private int B = 0;
  private boolean C = false;
  private Predicate<String> D = new Predicate()
  {
    public boolean a(String ☃)
    {
      Float ☃ = Floats.tryParse(☃);
      return (☃.isEmpty()) || ((☃ != null) && (Floats.isFinite(☃.floatValue())) && (☃.floatValue() >= 0.0F));
    }
  };
  private atg.a E = new atg.a();
  private atg.a F;
  private Random G = new Random();
  
  public bel(bfb ☃, String ☃)
  {
    this.i = ((bhk)☃);
    a(☃);
  }
  
  public void b()
  {
    int ☃ = 0;
    int ☃ = 0;
    if (this.r != null)
    {
      ☃ = this.r.e();
      ☃ = this.r.n();
    }
    this.a = bwo.a("options.customizeTitle", new Object[0]);
    this.n.clear();
    
    this.n.add(this.v = new bcz(302, 20, 5, 80, 20, bwo.a("createWorld.customize.custom.prev", new Object[0])));
    this.n.add(this.w = new bcz(303, this.l - 100, 5, 80, 20, bwo.a("createWorld.customize.custom.next", new Object[0])));
    
    this.n.add(this.u = new bcz(304, this.l / 2 - 187, this.m - 27, 90, 20, bwo.a("createWorld.customize.custom.defaults", new Object[0])));
    this.n.add(this.t = new bcz(301, this.l / 2 - 92, this.m - 27, 90, 20, bwo.a("createWorld.customize.custom.randomize", new Object[0])));
    this.n.add(this.z = new bcz(305, this.l / 2 + 3, this.m - 27, 90, 20, bwo.a("createWorld.customize.custom.presets", new Object[0])));
    this.n.add(this.s = new bcz(300, this.l / 2 + 98, this.m - 27, 90, 20, bwo.a("gui.done", new Object[0])));
    this.u.l = this.A;
    
    this.x = new bcz(306, this.l / 2 - 55, 160, 50, 20, bwo.a("gui.yes", new Object[0]));
    this.x.m = false;
    this.n.add(this.x);
    
    this.y = new bcz(307, this.l / 2 + 5, 160, 50, 20, bwo.a("gui.no", new Object[0]));
    this.y.m = false;
    this.n.add(this.y);
    if (this.B != 0)
    {
      this.x.m = true;
      this.y.m = true;
    }
    f();
    if (☃ != 0)
    {
      this.r.c(☃);
      this.r.h(☃);
      i();
    }
  }
  
  public void k()
  {
    super.k();
    this.r.p();
  }
  
  private void f()
  {
    bdo.f[] ☃ = { new bdo.g(160, bwo.a("createWorld.customize.custom.seaLevel", new Object[0]), true, this, 1.0F, 255.0F, this.F.r), new bdo.a(148, bwo.a("createWorld.customize.custom.useCaves", new Object[0]), true, this.F.s), new bdo.a(150, bwo.a("createWorld.customize.custom.useStrongholds", new Object[0]), true, this.F.v), new bdo.a(151, bwo.a("createWorld.customize.custom.useVillages", new Object[0]), true, this.F.w), new bdo.a(152, bwo.a("createWorld.customize.custom.useMineShafts", new Object[0]), true, this.F.x), new bdo.a(153, bwo.a("createWorld.customize.custom.useTemples", new Object[0]), true, this.F.y), new bdo.a(210, bwo.a("createWorld.customize.custom.useMonuments", new Object[0]), true, this.F.z), new bdo.a(154, bwo.a("createWorld.customize.custom.useRavines", new Object[0]), true, this.F.A), new bdo.a(149, bwo.a("createWorld.customize.custom.useDungeons", new Object[0]), true, this.F.t), new bdo.g(157, bwo.a("createWorld.customize.custom.dungeonChance", new Object[0]), true, this, 1.0F, 100.0F, this.F.u), new bdo.a(155, bwo.a("createWorld.customize.custom.useWaterLakes", new Object[0]), true, this.F.B), new bdo.g(158, bwo.a("createWorld.customize.custom.waterLakeChance", new Object[0]), true, this, 1.0F, 100.0F, this.F.C), new bdo.a(156, bwo.a("createWorld.customize.custom.useLavaLakes", new Object[0]), true, this.F.D), new bdo.g(159, bwo.a("createWorld.customize.custom.lavaLakeChance", new Object[0]), true, this, 10.0F, 100.0F, this.F.E), new bdo.a(161, bwo.a("createWorld.customize.custom.useLavaOceans", new Object[0]), true, this.F.F), new bdo.g(162, bwo.a("createWorld.customize.custom.fixedBiome", new Object[0]), true, this, -1.0F, 37.0F, this.F.G), new bdo.g(163, bwo.a("createWorld.customize.custom.biomeSize", new Object[0]), true, this, 1.0F, 8.0F, this.F.H), new bdo.g(164, bwo.a("createWorld.customize.custom.riverSize", new Object[0]), true, this, 1.0F, 5.0F, this.F.I) };
    
    bdo.f[] ☃ = { new bdo.e(416, bwo.a("tile.dirt.name", new Object[0]), false), null, new bdo.g(165, bwo.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.F.J), new bdo.g(166, bwo.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.F.K), new bdo.g(167, bwo.a("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, this.F.L), new bdo.g(168, bwo.a("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, this.F.M), new bdo.e(417, bwo.a("tile.gravel.name", new Object[0]), false), null, new bdo.g(169, bwo.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.F.N), new bdo.g(170, bwo.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.F.O), new bdo.g(171, bwo.a("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, this.F.P), new bdo.g(172, bwo.a("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, this.F.Q), new bdo.e(418, bwo.a("tile.stone.granite.name", new Object[0]), false), null, new bdo.g(173, bwo.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.F.R), new bdo.g(174, bwo.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.F.S), new bdo.g(175, bwo.a("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, this.F.T), new bdo.g(176, bwo.a("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, this.F.U), new bdo.e(419, bwo.a("tile.stone.diorite.name", new Object[0]), false), null, new bdo.g(177, bwo.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.F.V), new bdo.g(178, bwo.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.F.W), new bdo.g(179, bwo.a("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, this.F.X), new bdo.g(180, bwo.a("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, this.F.Y), new bdo.e(420, bwo.a("tile.stone.andesite.name", new Object[0]), false), null, new bdo.g(181, bwo.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.F.Z), new bdo.g(182, bwo.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.F.aa), new bdo.g(183, bwo.a("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, this.F.ab), new bdo.g(184, bwo.a("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, this.F.ac), new bdo.e(421, bwo.a("tile.oreCoal.name", new Object[0]), false), null, new bdo.g(185, bwo.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.F.ad), new bdo.g(186, bwo.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.F.ae), new bdo.g(187, bwo.a("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, this.F.af), new bdo.g(189, bwo.a("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, this.F.ag), new bdo.e(422, bwo.a("tile.oreIron.name", new Object[0]), false), null, new bdo.g(190, bwo.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.F.ah), new bdo.g(191, bwo.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.F.ai), new bdo.g(192, bwo.a("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, this.F.aj), new bdo.g(193, bwo.a("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, this.F.ak), new bdo.e(423, bwo.a("tile.oreGold.name", new Object[0]), false), null, new bdo.g(194, bwo.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.F.al), new bdo.g(195, bwo.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.F.am), new bdo.g(196, bwo.a("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, this.F.an), new bdo.g(197, bwo.a("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, this.F.ao), new bdo.e(424, bwo.a("tile.oreRedstone.name", new Object[0]), false), null, new bdo.g(198, bwo.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.F.ap), new bdo.g(199, bwo.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.F.aq), new bdo.g(200, bwo.a("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, this.F.ar), new bdo.g(201, bwo.a("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, this.F.as), new bdo.e(425, bwo.a("tile.oreDiamond.name", new Object[0]), false), null, new bdo.g(202, bwo.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.F.at), new bdo.g(203, bwo.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.F.au), new bdo.g(204, bwo.a("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0F, 255.0F, this.F.av), new bdo.g(205, bwo.a("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0F, 255.0F, this.F.aw), new bdo.e(426, bwo.a("tile.oreLapis.name", new Object[0]), false), null, new bdo.g(206, bwo.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0F, 50.0F, this.F.ax), new bdo.g(207, bwo.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0F, 40.0F, this.F.ay), new bdo.g(208, bwo.a("createWorld.customize.custom.center", new Object[0]), false, this, 0.0F, 255.0F, this.F.az), new bdo.g(209, bwo.a("createWorld.customize.custom.spread", new Object[0]), false, this, 0.0F, 255.0F, this.F.aA) };
    
    bdo.f[] ☃ = { new bdo.g(100, bwo.a("createWorld.customize.custom.mainNoiseScaleX", new Object[0]), false, this, 1.0F, 5000.0F, this.F.i), new bdo.g(101, bwo.a("createWorld.customize.custom.mainNoiseScaleY", new Object[0]), false, this, 1.0F, 5000.0F, this.F.j), new bdo.g(102, bwo.a("createWorld.customize.custom.mainNoiseScaleZ", new Object[0]), false, this, 1.0F, 5000.0F, this.F.k), new bdo.g(103, bwo.a("createWorld.customize.custom.depthNoiseScaleX", new Object[0]), false, this, 1.0F, 2000.0F, this.F.f), new bdo.g(104, bwo.a("createWorld.customize.custom.depthNoiseScaleZ", new Object[0]), false, this, 1.0F, 2000.0F, this.F.g), new bdo.g(105, bwo.a("createWorld.customize.custom.depthNoiseScaleExponent", new Object[0]), false, this, 0.01F, 20.0F, this.F.h), new bdo.g(106, bwo.a("createWorld.customize.custom.baseSize", new Object[0]), false, this, 1.0F, 25.0F, this.F.l), new bdo.g(107, bwo.a("createWorld.customize.custom.coordinateScale", new Object[0]), false, this, 1.0F, 6000.0F, this.F.b), new bdo.g(108, bwo.a("createWorld.customize.custom.heightScale", new Object[0]), false, this, 1.0F, 6000.0F, this.F.c), new bdo.g(109, bwo.a("createWorld.customize.custom.stretchY", new Object[0]), false, this, 0.01F, 50.0F, this.F.m), new bdo.g(110, bwo.a("createWorld.customize.custom.upperLimitScale", new Object[0]), false, this, 1.0F, 5000.0F, this.F.d), new bdo.g(111, bwo.a("createWorld.customize.custom.lowerLimitScale", new Object[0]), false, this, 1.0F, 5000.0F, this.F.e), new bdo.g(112, bwo.a("createWorld.customize.custom.biomeDepthWeight", new Object[0]), false, this, 1.0F, 20.0F, this.F.n), new bdo.g(113, bwo.a("createWorld.customize.custom.biomeDepthOffset", new Object[0]), false, this, 0.0F, 20.0F, this.F.o), new bdo.g(114, bwo.a("createWorld.customize.custom.biomeScaleWeight", new Object[0]), false, this, 1.0F, 20.0F, this.F.p), new bdo.g(115, bwo.a("createWorld.customize.custom.biomeScaleOffset", new Object[0]), false, this, 0.0F, 20.0F, this.F.q) };
    
    bdo.f[] ☃ = { new bdo.e(400, bwo.a("createWorld.customize.custom.mainNoiseScaleX", new Object[0]) + ":", false), new bdo.c(132, String.format("%5.3f", new Object[] { Float.valueOf(this.F.i) }), false, this.D), new bdo.e(401, bwo.a("createWorld.customize.custom.mainNoiseScaleY", new Object[0]) + ":", false), new bdo.c(133, String.format("%5.3f", new Object[] { Float.valueOf(this.F.j) }), false, this.D), new bdo.e(402, bwo.a("createWorld.customize.custom.mainNoiseScaleZ", new Object[0]) + ":", false), new bdo.c(134, String.format("%5.3f", new Object[] { Float.valueOf(this.F.k) }), false, this.D), new bdo.e(403, bwo.a("createWorld.customize.custom.depthNoiseScaleX", new Object[0]) + ":", false), new bdo.c(135, String.format("%5.3f", new Object[] { Float.valueOf(this.F.f) }), false, this.D), new bdo.e(404, bwo.a("createWorld.customize.custom.depthNoiseScaleZ", new Object[0]) + ":", false), new bdo.c(136, String.format("%5.3f", new Object[] { Float.valueOf(this.F.g) }), false, this.D), new bdo.e(405, bwo.a("createWorld.customize.custom.depthNoiseScaleExponent", new Object[0]) + ":", false), new bdo.c(137, String.format("%2.3f", new Object[] { Float.valueOf(this.F.h) }), false, this.D), new bdo.e(406, bwo.a("createWorld.customize.custom.baseSize", new Object[0]) + ":", false), new bdo.c(138, String.format("%2.3f", new Object[] { Float.valueOf(this.F.l) }), false, this.D), new bdo.e(407, bwo.a("createWorld.customize.custom.coordinateScale", new Object[0]) + ":", false), new bdo.c(139, String.format("%5.3f", new Object[] { Float.valueOf(this.F.b) }), false, this.D), new bdo.e(408, bwo.a("createWorld.customize.custom.heightScale", new Object[0]) + ":", false), new bdo.c(140, String.format("%5.3f", new Object[] { Float.valueOf(this.F.c) }), false, this.D), new bdo.e(409, bwo.a("createWorld.customize.custom.stretchY", new Object[0]) + ":", false), new bdo.c(141, String.format("%2.3f", new Object[] { Float.valueOf(this.F.m) }), false, this.D), new bdo.e(410, bwo.a("createWorld.customize.custom.upperLimitScale", new Object[0]) + ":", false), new bdo.c(142, String.format("%5.3f", new Object[] { Float.valueOf(this.F.d) }), false, this.D), new bdo.e(411, bwo.a("createWorld.customize.custom.lowerLimitScale", new Object[0]) + ":", false), new bdo.c(143, String.format("%5.3f", new Object[] { Float.valueOf(this.F.e) }), false, this.D), new bdo.e(412, bwo.a("createWorld.customize.custom.biomeDepthWeight", new Object[0]) + ":", false), new bdo.c(144, String.format("%2.3f", new Object[] { Float.valueOf(this.F.n) }), false, this.D), new bdo.e(413, bwo.a("createWorld.customize.custom.biomeDepthOffset", new Object[0]) + ":", false), new bdo.c(145, String.format("%2.3f", new Object[] { Float.valueOf(this.F.o) }), false, this.D), new bdo.e(414, bwo.a("createWorld.customize.custom.biomeScaleWeight", new Object[0]) + ":", false), new bdo.c(146, String.format("%2.3f", new Object[] { Float.valueOf(this.F.p) }), false, this.D), new bdo.e(415, bwo.a("createWorld.customize.custom.biomeScaleOffset", new Object[0]) + ":", false), new bdo.c(147, String.format("%2.3f", new Object[] { Float.valueOf(this.F.q) }), false, this.D) };
    
    this.r = new bdo(this.j, this.l, this.m, 32, this.m - 32, 25, this, new bdo.f[][] { ☃, ☃, ☃, ☃ });
    for (int ☃ = 0; ☃ < 4; ☃++) {
      this.h[☃] = bwo.a("createWorld.customize.custom.page" + ☃, new Object[0]);
    }
    i();
  }
  
  public String a()
  {
    return this.F.toString().replace("\n", "");
  }
  
  public void a(String ☃)
  {
    if ((☃ != null) && (!☃.isEmpty())) {
      this.F = atg.a.a(☃);
    } else {
      this.F = new atg.a();
    }
  }
  
  public void a(int ☃, String ☃)
  {
    float ☃ = 0.0F;
    try
    {
      ☃ = Float.parseFloat(☃);
    }
    catch (NumberFormatException localNumberFormatException) {}
    float ☃ = 0.0F;
    switch (☃)
    {
    case 139: 
      ☃ = this.F.b = on.a(☃, 1.0F, 6000.0F);
      break;
    case 140: 
      ☃ = this.F.c = on.a(☃, 1.0F, 6000.0F);
      break;
    case 142: 
      ☃ = this.F.d = on.a(☃, 1.0F, 5000.0F);
      break;
    case 143: 
      ☃ = this.F.e = on.a(☃, 1.0F, 5000.0F);
      break;
    case 135: 
      ☃ = this.F.f = on.a(☃, 1.0F, 2000.0F);
      break;
    case 136: 
      ☃ = this.F.g = on.a(☃, 1.0F, 2000.0F);
      break;
    case 137: 
      ☃ = this.F.h = on.a(☃, 0.01F, 20.0F);
      break;
    case 132: 
      ☃ = this.F.i = on.a(☃, 1.0F, 5000.0F);
      break;
    case 133: 
      ☃ = this.F.j = on.a(☃, 1.0F, 5000.0F);
      break;
    case 134: 
      ☃ = this.F.k = on.a(☃, 1.0F, 5000.0F);
      break;
    case 138: 
      ☃ = this.F.l = on.a(☃, 1.0F, 25.0F);
      break;
    case 141: 
      ☃ = this.F.m = on.a(☃, 0.01F, 50.0F);
      break;
    case 144: 
      ☃ = this.F.n = on.a(☃, 1.0F, 20.0F);
      break;
    case 145: 
      ☃ = this.F.o = on.a(☃, 0.0F, 20.0F);
      break;
    case 146: 
      ☃ = this.F.p = on.a(☃, 1.0F, 20.0F);
      break;
    case 147: 
      ☃ = this.F.q = on.a(☃, 0.0F, 20.0F);
    }
    if ((☃ != ☃) && (☃ != 0.0F)) {
      ((bdd)this.r.d(☃)).a(b(☃, ☃));
    }
    ((bde)this.r.d(☃ - 132 + 100)).a(☃, false);
    if (!this.F.equals(this.E)) {
      a(true);
    }
  }
  
  private void a(boolean ☃)
  {
    this.A = ☃;
    this.u.l = ☃;
  }
  
  public String a(int ☃, String ☃, float ☃)
  {
    return ☃ + ": " + b(☃, ☃);
  }
  
  private String b(int ☃, float ☃)
  {
    switch (☃)
    {
    case 100: 
    case 101: 
    case 102: 
    case 103: 
    case 104: 
    case 107: 
    case 108: 
    case 110: 
    case 111: 
    case 132: 
    case 133: 
    case 134: 
    case 135: 
    case 136: 
    case 139: 
    case 140: 
    case 142: 
    case 143: 
      return String.format("%5.3f", new Object[] { Float.valueOf(☃) });
    case 105: 
    case 106: 
    case 109: 
    case 112: 
    case 113: 
    case 114: 
    case 115: 
    case 137: 
    case 138: 
    case 141: 
    case 144: 
    case 145: 
    case 146: 
    case 147: 
      return String.format("%2.3f", new Object[] { Float.valueOf(☃) });
    case 162: 
      if (☃ < 0.0F) {
        return bwo.a("gui.all", new Object[0]);
      }
      if ((int)☃ >= aig.a(ail.j))
      {
        aig ☃ = aig.a((int)☃ + 2);
        return ☃ != null ? ☃.l() : "?";
      }
      aig ☃ = aig.a((int)☃);
      return ☃ != null ? ☃.l() : "?";
    }
    return String.format("%d", new Object[] { Integer.valueOf((int)☃) });
  }
  
  public void a(int ☃, boolean ☃)
  {
    switch (☃)
    {
    case 148: 
      this.F.s = ☃;
      break;
    case 149: 
      this.F.t = ☃;
      break;
    case 150: 
      this.F.v = ☃;
      break;
    case 151: 
      this.F.w = ☃;
      break;
    case 152: 
      this.F.x = ☃;
      break;
    case 153: 
      this.F.y = ☃;
      break;
    case 154: 
      this.F.A = ☃;
      break;
    case 210: 
      this.F.z = ☃;
      break;
    case 155: 
      this.F.B = ☃;
      break;
    case 156: 
      this.F.D = ☃;
      break;
    case 161: 
      this.F.F = ☃;
    }
    if (!this.F.equals(this.E)) {
      a(true);
    }
  }
  
  public void a(int ☃, float ☃)
  {
    switch (☃)
    {
    case 107: 
      this.F.b = ☃;
      break;
    case 108: 
      this.F.c = ☃;
      break;
    case 110: 
      this.F.d = ☃;
      break;
    case 111: 
      this.F.e = ☃;
      break;
    case 103: 
      this.F.f = ☃;
      break;
    case 104: 
      this.F.g = ☃;
      break;
    case 105: 
      this.F.h = ☃;
      break;
    case 100: 
      this.F.i = ☃;
      break;
    case 101: 
      this.F.j = ☃;
      break;
    case 102: 
      this.F.k = ☃;
      break;
    case 106: 
      this.F.l = ☃;
      break;
    case 109: 
      this.F.m = ☃;
      break;
    case 112: 
      this.F.n = ☃;
      break;
    case 113: 
      this.F.o = ☃;
      break;
    case 114: 
      this.F.p = ☃;
      break;
    case 115: 
      this.F.q = ☃;
      break;
    case 157: 
      this.F.u = ((int)☃);
      break;
    case 158: 
      this.F.C = ((int)☃);
      break;
    case 159: 
      this.F.E = ((int)☃);
      break;
    case 160: 
      this.F.r = ((int)☃);
      break;
    case 162: 
      this.F.G = ((int)☃);
      break;
    case 163: 
      this.F.H = ((int)☃);
      break;
    case 164: 
      this.F.I = ((int)☃);
      break;
    case 166: 
      this.F.K = ((int)☃);
      break;
    case 165: 
      this.F.J = ((int)☃);
      break;
    case 167: 
      this.F.L = ((int)☃);
      break;
    case 168: 
      this.F.M = ((int)☃);
      break;
    case 170: 
      this.F.O = ((int)☃);
      break;
    case 169: 
      this.F.N = ((int)☃);
      break;
    case 171: 
      this.F.P = ((int)☃);
      break;
    case 172: 
      this.F.Q = ((int)☃);
      break;
    case 174: 
      this.F.S = ((int)☃);
      break;
    case 173: 
      this.F.R = ((int)☃);
      break;
    case 175: 
      this.F.T = ((int)☃);
      break;
    case 176: 
      this.F.U = ((int)☃);
      break;
    case 178: 
      this.F.W = ((int)☃);
      break;
    case 177: 
      this.F.V = ((int)☃);
      break;
    case 179: 
      this.F.X = ((int)☃);
      break;
    case 180: 
      this.F.Y = ((int)☃);
      break;
    case 182: 
      this.F.aa = ((int)☃);
      break;
    case 181: 
      this.F.Z = ((int)☃);
      break;
    case 183: 
      this.F.ab = ((int)☃);
      break;
    case 184: 
      this.F.ac = ((int)☃);
      break;
    case 186: 
      this.F.ae = ((int)☃);
      break;
    case 185: 
      this.F.ad = ((int)☃);
      break;
    case 187: 
      this.F.af = ((int)☃);
      break;
    case 189: 
      this.F.ag = ((int)☃);
      break;
    case 191: 
      this.F.ai = ((int)☃);
      break;
    case 190: 
      this.F.ah = ((int)☃);
      break;
    case 192: 
      this.F.aj = ((int)☃);
      break;
    case 193: 
      this.F.ak = ((int)☃);
      break;
    case 195: 
      this.F.am = ((int)☃);
      break;
    case 194: 
      this.F.al = ((int)☃);
      break;
    case 196: 
      this.F.an = ((int)☃);
      break;
    case 197: 
      this.F.ao = ((int)☃);
      break;
    case 199: 
      this.F.aq = ((int)☃);
      break;
    case 198: 
      this.F.ap = ((int)☃);
      break;
    case 200: 
      this.F.ar = ((int)☃);
      break;
    case 201: 
      this.F.as = ((int)☃);
      break;
    case 203: 
      this.F.au = ((int)☃);
      break;
    case 202: 
      this.F.at = ((int)☃);
      break;
    case 204: 
      this.F.av = ((int)☃);
      break;
    case 205: 
      this.F.aw = ((int)☃);
      break;
    case 207: 
      this.F.ay = ((int)☃);
      break;
    case 206: 
      this.F.ax = ((int)☃);
      break;
    case 208: 
      this.F.az = ((int)☃);
      break;
    case 209: 
      this.F.aA = ((int)☃);
    }
    if ((☃ >= 100) && (☃ < 116))
    {
      bcv ☃ = this.r.d(☃ - 100 + 132);
      if (☃ != null) {
        ((bdd)☃).a(b(☃, ☃));
      }
    }
    if (!this.F.equals(this.E)) {
      a(true);
    }
  }
  
  protected void a(bcz ☃)
  {
    if (!☃.l) {
      return;
    }
    switch (☃.k)
    {
    case 300: 
      this.i.a = this.F.toString();
      this.j.a(this.i);
      break;
    case 305: 
      this.j.a(new bek(this));
      break;
    case 301: 
      for (int ☃ = 0; ☃ < this.r.b(); ☃++)
      {
        bdo.d ☃ = this.r.e(☃);
        bcv ☃ = ☃.a();
        if ((☃ instanceof bcz))
        {
          bcz ☃ = (bcz)☃;
          if ((☃ instanceof bde))
          {
            float ☃ = ((bde)☃).d() * (0.75F + this.G.nextFloat() * 0.5F) + (this.G.nextFloat() * 0.1F - 0.05F);
            ((bde)☃).a(on.a(☃, 0.0F, 1.0F));
          }
          else if ((☃ instanceof bdj))
          {
            ((bdj)☃).b(this.G.nextBoolean());
          }
        }
        bcv ☃ = ☃.b();
        if ((☃ instanceof bcz))
        {
          bcz ☃ = (bcz)☃;
          if ((☃ instanceof bde))
          {
            float ☃ = ((bde)☃).d() * (0.75F + this.G.nextFloat() * 0.5F) + (this.G.nextFloat() * 0.1F - 0.05F);
            ((bde)☃).a(on.a(☃, 0.0F, 1.0F));
          }
          else if ((☃ instanceof bdj))
          {
            ((bdj)☃).b(this.G.nextBoolean());
          }
        }
      }
      break;
    case 302: 
      this.r.h();
      i();
      break;
    case 303: 
      this.r.i();
      i();
      break;
    case 304: 
      if (this.A) {
        b(304);
      }
      break;
    case 307: 
      this.B = 0;
      h();
      break;
    case 306: 
      h();
    }
  }
  
  private void g()
  {
    this.F.a();
    f();
    a(false);
  }
  
  private void b(int ☃)
  {
    this.B = ☃;
    b(true);
  }
  
  private void h()
  {
    switch (this.B)
    {
    case 300: 
      a((bdj)this.r.d(300));
      break;
    case 304: 
      g();
    }
    this.B = 0;
    this.C = true;
    b(false);
  }
  
  private void b(boolean ☃)
  {
    this.x.m = ☃;
    this.y.m = ☃;
    this.t.l = (!☃);
    this.s.l = (!☃);
    this.v.l = (!☃);
    this.w.l = (!☃);
    this.u.l = ((this.A) && (!☃));
    this.z.l = (!☃);
    this.r.a(!☃);
  }
  
  private void i()
  {
    this.v.l = (this.r.e() != 0);
    this.w.l = (this.r.e() != this.r.f() - 1);
    this.f = bwo.a("book.pageIndicator", new Object[] { Integer.valueOf(this.r.e() + 1), Integer.valueOf(this.r.f()) });
    this.g = this.h[this.r.e()];
    this.t.l = (this.r.e() != this.r.f() - 1);
  }
  
  protected void a(char ☃, int ☃)
  {
    super.a(☃, ☃);
    if (this.B != 0) {
      return;
    }
    switch (☃)
    {
    case 208: 
      a(-1.0F);
      break;
    case 200: 
      a(1.0F);
      break;
    default: 
      this.r.a(☃, ☃);
    }
  }
  
  private void a(float ☃)
  {
    bcv ☃ = this.r.g();
    if (!(☃ instanceof bdd)) {
      return;
    }
    float ☃ = ☃;
    if (bfb.r())
    {
      ☃ *= 0.1F;
      if (bfb.q()) {
        ☃ *= 0.1F;
      }
    }
    else if (bfb.q())
    {
      ☃ *= 10.0F;
      if (bfb.s()) {
        ☃ *= 10.0F;
      }
    }
    bdd ☃ = (bdd)☃;
    Float ☃ = Floats.tryParse(☃.b());
    if (☃ == null) {
      return;
    }
    ☃ = Float.valueOf(☃.floatValue() + ☃);
    int ☃ = ☃.d();
    String ☃ = b(☃.d(), ☃.floatValue());
    ☃.a(☃);
    a(☃, ☃);
  }
  
  protected void a(int ☃, int ☃, int ☃)
  {
    super.a(☃, ☃, ☃);
    if ((this.B != 0) || (this.C)) {
      return;
    }
    this.r.b(☃, ☃, ☃);
  }
  
  protected void b(int ☃, int ☃, int ☃)
  {
    super.b(☃, ☃, ☃);
    if (this.C)
    {
      this.C = false;
      return;
    }
    if (this.B != 0) {
      return;
    }
    this.r.c(☃, ☃, ☃);
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    c();
    this.r.a(☃, ☃, ☃);
    
    a(this.q, this.a, this.l / 2, 2, 16777215);
    a(this.q, this.f, this.l / 2, 12, 16777215);
    a(this.q, this.g, this.l / 2, 22, 16777215);
    
    super.a(☃, ☃, ☃);
    if (this.B != 0)
    {
      a(0, 0, this.l, this.m, Integer.MIN_VALUE);
      
      a(this.l / 2 - 91, this.l / 2 + 90, 99, -2039584);
      a(this.l / 2 - 91, this.l / 2 + 90, 185, -6250336);
      b(this.l / 2 - 91, 99, 185, -2039584);
      b(this.l / 2 + 90, 99, 185, -6250336);
      
      float ☃ = 85.0F;
      float ☃ = 180.0F;
      
      bni.g();
      bni.p();
      bnu ☃ = bnu.a();
      bmz ☃ = ☃.c();
      this.j.N().a(b);
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      float ☃ = 32.0F;
      ☃.a(7, bvp.i);
      ☃.b(this.l / 2 - 90, 185.0D, 0.0D).a(0.0D, 2.65625D).b(64, 64, 64, 64).d();
      ☃.b(this.l / 2 + 90, 185.0D, 0.0D).a(5.625D, 2.65625D).b(64, 64, 64, 64).d();
      ☃.b(this.l / 2 + 90, 100.0D, 0.0D).a(5.625D, 0.0D).b(64, 64, 64, 64).d();
      ☃.b(this.l / 2 - 90, 100.0D, 0.0D).a(0.0D, 0.0D).b(64, 64, 64, 64).d();
      ☃.b();
      
      a(this.q, bwo.a("createWorld.customize.custom.confirmTitle", new Object[0]), this.l / 2, 105, 16777215);
      a(this.q, bwo.a("createWorld.customize.custom.confirm1", new Object[0]), this.l / 2, 125, 16777215);
      a(this.q, bwo.a("createWorld.customize.custom.confirm2", new Object[0]), this.l / 2, 135, 16777215);
      
      this.x.a(this.j, ☃, ☃);
      this.y.a(this.j, ☃, ☃);
    }
  }
}
