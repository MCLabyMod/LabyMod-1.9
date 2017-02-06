import com.google.common.base.Function;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class ado
{
  public static final cx<kk, ado> f = new cx();
  private static final Map<ajt, ado> a = Maps.newHashMap();
  private static final adr b = new adr()
  {
    public float a(adq ☃, aht ☃, sa ☃)
    {
      return ☃.g() ? 1.0F : 0.0F;
    }
  };
  private static final adr c = new adr()
  {
    public float a(adq ☃, aht ☃, sa ☃)
    {
      return on.a(☃.h() / ☃.j(), 0.0F, 1.0F);
    }
  };
  private static final adr d = new adr()
  {
    public float a(adq ☃, aht ☃, sa ☃)
    {
      return (☃ == null) || (☃.cr() == rz.b) ? 0.0F : 1.0F;
    }
  };
  private static final adr e = new adr()
  {
    public float a(adq ☃, aht ☃, sa ☃)
    {
      return (☃ instanceof zj) ? ((zj)☃).da().a(☃.b(), 0.0F) : 0.0F;
    }
  };
  private final db<kk, adr> m = new dd();
  protected static final UUID g = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
  protected static final UUID h = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");
  private acq n;
  
  public static int a(ado ☃)
  {
    return ☃ == null ? 0 : f.a(☃);
  }
  
  public static ado c(int ☃)
  {
    return (ado)f.a(☃);
  }
  
  public static ado a(ajt ☃)
  {
    return (ado)a.get(☃);
  }
  
  public static ado d(String ☃)
  {
    ado ☃ = (ado)f.c(new kk(☃));
    if (☃ == null) {
      try
      {
        return c(Integer.parseInt(☃));
      }
      catch (NumberFormatException localNumberFormatException) {}
    }
    return ☃;
  }
  
  public final void a(kk ☃, adr ☃)
  {
    this.m.a(☃, ☃);
  }
  
  public adr a(kk ☃)
  {
    return (adr)this.m.c(☃);
  }
  
  public boolean i()
  {
    return !this.m.c().isEmpty();
  }
  
  public boolean a(dn ☃)
  {
    return false;
  }
  
  public static enum a
  {
    private final int f;
    private final int g;
    private final float h;
    private final float i;
    private final int j;
    
    private a(int ☃, int ☃, float ☃, float ☃, int ☃)
    {
      this.f = ☃;
      this.g = ☃;
      this.h = ☃;
      this.i = ☃;
      this.j = ☃;
    }
    
    public int a()
    {
      return this.g;
    }
    
    public float b()
    {
      return this.h;
    }
    
    public float c()
    {
      return this.i;
    }
    
    public int d()
    {
      return this.f;
    }
    
    public int e()
    {
      return this.j;
    }
    
    public ado f()
    {
      if (this == a) {
        return ado.a(aju.f);
      }
      if (this == b) {
        return ado.a(aju.e);
      }
      if (this == e) {
        return ads.m;
      }
      if (this == c) {
        return ads.l;
      }
      if (this == d) {
        return ads.k;
      }
      return null;
    }
  }
  
  protected static Random i = new Random();
  protected int j = 64;
  private int o;
  protected boolean k;
  protected boolean l;
  private ado p;
  private String q;
  
  public ado()
  {
    a(new kk("lefthanded"), d);
    a(new kk("cooldown"), e);
  }
  
  public ado d(int ☃)
  {
    this.j = ☃;
    return this;
  }
  
  public qo a(adq ☃, zj ☃, aht ☃, cj ☃, qm ☃, cq ☃, float ☃, float ☃, float ☃)
  {
    return qo.b;
  }
  
  public float a(adq ☃, arc ☃)
  {
    return 1.0F;
  }
  
  public qp<adq> a(adq ☃, aht ☃, zj ☃, qm ☃)
  {
    return new qp(qo.b, ☃);
  }
  
  public adq a(adq ☃, aht ☃, sa ☃)
  {
    return ☃;
  }
  
  public int j()
  {
    return this.j;
  }
  
  public int a(int ☃)
  {
    return 0;
  }
  
  public boolean k()
  {
    return this.l;
  }
  
  protected ado a(boolean ☃)
  {
    this.l = ☃;
    return this;
  }
  
  public int l()
  {
    return this.o;
  }
  
  protected ado e(int ☃)
  {
    this.o = ☃;
    if (☃ > 0)
    {
      a(new kk("damaged"), b);
      a(new kk("damage"), c);
    }
    return this;
  }
  
  public boolean m()
  {
    return (this.o > 0) && ((!this.l) || (this.j == 1));
  }
  
  public boolean a(adq ☃, sa ☃, sa ☃)
  {
    return false;
  }
  
  public boolean a(adq ☃, aht ☃, arc ☃, cj ☃, sa ☃)
  {
    return false;
  }
  
  public boolean a(arc ☃)
  {
    return false;
  }
  
  public boolean a(adq ☃, zj ☃, sa ☃, qm ☃)
  {
    return false;
  }
  
  public ado n()
  {
    this.k = true;
    return this;
  }
  
  public boolean A_()
  {
    return this.k;
  }
  
  public boolean C_()
  {
    return false;
  }
  
  public ado c(String ☃)
  {
    this.q = ☃;
    return this;
  }
  
  public String j(adq ☃)
  {
    String ☃ = f_(☃);
    if (☃ == null) {
      return "";
    }
    return di.a(☃);
  }
  
  public String a()
  {
    return "item." + this.q;
  }
  
  public String f_(adq ☃)
  {
    return "item." + this.q;
  }
  
  public ado b(ado ☃)
  {
    this.p = ☃;
    return this;
  }
  
  public boolean p()
  {
    return true;
  }
  
  public ado q()
  {
    return this.p;
  }
  
  public boolean r()
  {
    return this.p != null;
  }
  
  public void a(adq ☃, aht ☃, rr ☃, int ☃, boolean ☃) {}
  
  public void b(adq ☃, aht ☃, zj ☃) {}
  
  public boolean f()
  {
    return false;
  }
  
  public afa f(adq ☃)
  {
    return afa.a;
  }
  
  public int e(adq ☃)
  {
    return 0;
  }
  
  public void a(adq ☃, aht ☃, sa ☃, int ☃) {}
  
  public void a(adq ☃, zj ☃, List<String> ☃, boolean ☃) {}
  
  public String a(adq ☃)
  {
    return ("" + di.a(new StringBuilder().append(j(☃)).append(".name").toString())).trim();
  }
  
  public boolean i_(adq ☃)
  {
    if (☃.w()) {
      return true;
    }
    return false;
  }
  
  public aee g(adq ☃)
  {
    if (☃.w()) {
      return aee.c;
    }
    return aee.a;
  }
  
  public boolean g_(adq ☃)
  {
    return (j() == 1) && (m());
  }
  
  protected bbi a(aht ☃, zj ☃, boolean ☃)
  {
    float ☃ = ☃.w;
    float ☃ = ☃.v;
    
    double ☃ = ☃.p;
    double ☃ = ☃.q + ☃.bn();
    double ☃ = ☃.r;
    
    bbj ☃ = new bbj(☃, ☃, ☃);
    
    float ☃ = on.b(-☃ * 0.017453292F - 3.1415927F);
    float ☃ = on.a(-☃ * 0.017453292F - 3.1415927F);
    float ☃ = -on.b(-☃ * 0.017453292F);
    float ☃ = on.a(-☃ * 0.017453292F);
    
    float ☃ = ☃ * ☃;
    float ☃ = ☃;
    float ☃ = ☃ * ☃;
    
    double ☃ = 5.0D;
    bbj ☃ = ☃.b(☃ * ☃, ☃ * ☃, ☃ * ☃);
    
    return ☃.a(☃, ☃, ☃, !☃, false);
  }
  
  public int c()
  {
    return 0;
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    ☃.add(new adq(☃));
  }
  
  public acq b()
  {
    return this.n;
  }
  
  public ado a(acq ☃)
  {
    this.n = ☃;
    return this;
  }
  
  public boolean s()
  {
    return false;
  }
  
  public boolean a(adq ☃, adq ☃)
  {
    return false;
  }
  
  public Multimap<String, sn> a(rw ☃)
  {
    return HashMultimap.create();
  }
  
  public static void t()
  {
    a(aju.b, new adz(aju.b, aju.b, new Function()
    {
      public String a(adq ☃)
      {
        return aox.a.a(☃.i()).d();
      }
    }).b("stone"));
    
    a(aju.c, new aey(aju.c, false));
    a(aju.d, new adz(aju.d, aju.d, new Function()
    {
      public String a(adq ☃)
      {
        return akt.a.a(☃.i()).c();
      }
    }).b("dirt"));
    
    b(aju.e);
    a(aju.f, new adz(aju.f, aju.f, new Function()
    {
      public String a(adq ☃)
      {
        return anj.a.a(☃.i()).d();
      }
    }).b("wood"));
    
    a(aju.g, new adz(aju.g, aju.g, new Function()
    {
      public String a(adq ☃)
      {
        return anj.a.a(☃.i()).d();
      }
    }).b("sapling"));
    
    b(aju.h);
    a(aju.m, new adz(aju.m, aju.m, new Function()
    {
      public String a(adq ☃)
      {
        return aof.a.a(☃.i()).d();
      }
    }).b("sand"));
    
    b(aju.n);
    b(aju.o);
    b(aju.p);
    b(aju.q);
    a(aju.r, new adz(aju.r, aju.r, new Function()
    {
      public String a(adq ☃)
      {
        return anj.a.a(☃.i()).d();
      }
    }).b("log"));
    
    a(aju.s, new adz(aju.s, aju.s, new Function()
    {
      public String a(adq ☃)
      {
        return anj.a.a(☃.i() + 4).d();
      }
    }).b("log"));
    
    a(aju.t, new adu(aju.t).b("leaves"));
    a(aju.u, new adu(aju.u).b("leaves"));
    a(aju.v, new adz(aju.v, aju.v, new Function()
    {
      public String a(adq ☃)
      {
        return (☃.i() & 0x1) == 1 ? "wet" : "dry";
      }
    }).b("sponge"));
    
    b(aju.w);
    b(aju.x);
    b(aju.y);
    b(aju.z);
    a(aju.A, new adz(aju.A, aju.A, new Function()
    {
      public String a(adq ☃)
      {
        return aog.a.a(☃.i()).c();
      }
    }).b("sandStone"));
    
    b(aju.B);
    b(aju.D);
    b(aju.E);
    a(aju.F, new aec(aju.F));
    b(aju.G);
    a(aju.H, new aey(aju.H, true).a(new String[] { "shrub", "grass", "fern" }));
    b(aju.I);
    a(aju.J, new aec(aju.J));
    a(aju.L, new acv(aju.L).b("cloth"));
    a(aju.N, new adz(aju.N, aju.N, new Function()
    {
      public String a(adq ☃)
      {
        return alm.a.a(alm.b.a, ☃.i()).d();
      }
    }).b("flower"));
    
    a(aju.O, new adz(aju.O, aju.O, new Function()
    {
      public String a(adq ☃)
      {
        return alm.a.a(alm.b.b, ☃.i()).d();
      }
    }).b("rose"));
    
    b(aju.P);
    b(aju.Q);
    b(aju.R);
    b(aju.S);
    a(aju.U, new aer(aju.U, aju.U, aju.T).b("stoneSlab"));
    b(aju.V);
    b(aju.W);
    b(aju.X);
    b(aju.Y);
    b(aju.Z);
    b(aju.aa);
    b(aju.cQ);
    b(aju.cR);
    b(aju.cS);
    b(aju.cT);
    b(aju.cU);
    b(aju.cV);
    a(aju.cX, new aer(aju.cX, aju.cX, aju.cW).b("purpurSlab"));
    b(aju.ac);
    b(aju.ad);
    b(aju.ae);
    b(aju.ag);
    b(aju.ah);
    b(aju.ai);
    b(aju.ak);
    b(aju.al);
    b(aju.au);
    b(aju.av);
    b(aju.aw);
    b(aju.ay);
    b(aju.az);
    b(aju.aB);
    b(aju.aC);
    b(aju.aF);
    b(aju.aG);
    a(aju.aH, new aes(aju.aH));
    b(aju.aI);
    b(aju.aJ);
    b(aju.aK);
    b(aju.aL);
    b(aju.aN);
    b(aju.aO);
    b(aju.aP);
    b(aju.aQ);
    b(aju.aR);
    b(aju.aS);
    b(aju.aT);
    b(aju.aU);
    b(aju.aV);
    b(aju.aW);
    b(aju.aX);
    b(aju.aZ);
    b(aju.bd);
    a(aju.be, new adz(aju.be, aju.be, new Function()
    {
      public String a(adq ☃)
      {
        return amt.a.a(☃.i()).c();
      }
    }).b("monsterStoneEgg"));
    
    a(aju.bf, new adz(aju.bf, aju.bf, new Function()
    {
      public String a(adq ☃)
      {
        return aoy.a.a(☃.i()).c();
      }
    }).b("stonebricksmooth"));
    
    b(aju.bg);
    b(aju.bh);
    b(aju.bi);
    b(aju.bj);
    b(aju.bk);
    a(aju.bn, new aey(aju.bn, false));
    b(aju.bo);
    b(aju.bp);
    b(aju.bq);
    b(aju.br);
    b(aju.bs);
    b(aju.bt);
    b(aju.bu);
    b(aju.bv);
    b(aju.bw);
    a(aju.bx, new afb(aju.bx));
    b(aju.by);
    b(aju.bz);
    b(aju.bA);
    b(aju.bC);
    b(aju.bG);
    b(aju.bH);
    b(aju.cY);
    b(aju.bI);
    b(aju.bJ);
    a(aju.bM, new aer(aju.bM, aju.bM, aju.bL).b("woodSlab"));
    b(aju.bO);
    b(aju.bP);
    b(aju.bQ);
    b(aju.bR);
    b(aju.bT);
    b(aju.bU);
    b(aju.bV);
    b(aju.bW);
    b(aju.bX);
    b(aju.bY);
    a(aju.bZ, new adz(aju.bZ, aju.bZ, new Function()
    {
      public String a(adq ☃)
      {
        return apk.a.a(☃.i()).c();
      }
    }).b("cobbleWall"));
    
    b(aju.cd);
    a(aju.cf, new abv(aju.cf).b("anvil"));
    b(aju.cg);
    b(aju.ch);
    b(aju.ci);
    b(aju.cl);
    b(aju.cn);
    b(aju.co);
    b(aju.cp);
    a(aju.cq, new adz(aju.cq, aju.cq, new String[] { "default", "chiseled", "lines" }).b("quartzBlock"));
    b(aju.cr);
    b(aju.cs);
    b(aju.ct);
    a(aju.cu, new acv(aju.cu).b("clayHardenedStained"));
    b(aju.cv);
    b(aju.cw);
    b(aju.cx);
    a(aju.cy, new acv(aju.cy).b("woolCarpet"));
    b(aju.cz);
    b(aju.cA);
    b(aju.cB);
    b(aju.cC);
    b(aju.cD);
    b(aju.cE);
    b(aju.da);
    a(aju.cF, new adz(aju.cF, aju.cF, new Function()
    {
      public String a(adq ☃)
      {
        return akw.b.a(☃.i()).c();
      }
    }).b("doublePlant"));
    
    a(aju.cG, new acv(aju.cG).b("stainedGlass"));
    a(aju.cH, new acv(aju.cH).b("stainedGlassPane"));
    a(aju.cI, new adz(aju.cI, aju.cI, new Function()
    {
      public String a(adq ☃)
      {
        return anp.a.a(☃.i()).c();
      }
    }).b("prismarine"));
    
    b(aju.cJ);
    a(aju.cM, new adz(aju.cM, aju.cM, new Function()
    {
      public String a(adq ☃)
      {
        return anv.a.a(☃.i()).c();
      }
    }).b("redSandStone"));
    
    b(aju.cN);
    a(aju.cP, new aer(aju.cP, aju.cP, aju.cO).b("stoneSlab2"));
    b(aju.dc);
    b(aju.dd);
    
    a(256, "iron_shovel", new aen(ado.a.c).c("shovelIron"));
    a(257, "iron_pickaxe", new aeb(ado.a.c).c("pickaxeIron"));
    a(258, "iron_axe", new abz(ado.a.c).c("hatchetIron"));
    a(259, "flint_and_steel", new adj().c("flintAndSteel"));
    a(260, "apple", new adk(4, 0.3F, false).c("apple"));
    a(261, "bow", new ach().c("bow"));
    a(262, "arrow", new aby().c("arrow"));
    a(263, "coal", new acn().c("coal"));
    a(264, "diamond", new ado().c("diamond").a(acq.l));
    a(265, "iron_ingot", new ado().c("ingotIron").a(acq.l));
    a(266, "gold_ingot", new ado().c("ingotGold").a(acq.l));
    a(267, "iron_sword", new aex(ado.a.c).c("swordIron"));
    a(268, "wooden_sword", new aex(ado.a.a).c("swordWood"));
    a(269, "wooden_shovel", new aen(ado.a.a).c("shovelWood"));
    a(270, "wooden_pickaxe", new aeb(ado.a.a).c("pickaxeWood"));
    a(271, "wooden_axe", new abz(ado.a.a).c("hatchetWood"));
    a(272, "stone_sword", new aex(ado.a.b).c("swordStone"));
    a(273, "stone_shovel", new aen(ado.a.b).c("shovelStone"));
    a(274, "stone_pickaxe", new aeb(ado.a.b).c("pickaxeStone"));
    a(275, "stone_axe", new abz(ado.a.b).c("hatchetStone"));
    a(276, "diamond_sword", new aex(ado.a.d).c("swordDiamond"));
    a(277, "diamond_shovel", new aen(ado.a.d).c("shovelDiamond"));
    a(278, "diamond_pickaxe", new aeb(ado.a.d).c("pickaxeDiamond"));
    a(279, "diamond_axe", new abz(ado.a.d).c("hatchetDiamond"));
    a(280, "stick", new ado().n().c("stick").a(acq.l));
    a(281, "bowl", new ado().c("bowl").a(acq.l));
    a(282, "mushroom_stew", new aci(6).c("mushroomStew"));
    a(283, "golden_sword", new aex(ado.a.e).c("swordGold"));
    a(284, "golden_shovel", new aen(ado.a.e).c("shovelGold"));
    a(285, "golden_pickaxe", new aeb(ado.a.e).c("pickaxeGold"));
    a(286, "golden_axe", new abz(ado.a.e).c("hatchetGold"));
    a(287, "string", new acd(aju.bS).c("string").a(acq.l));
    a(288, "feather", new ado().c("feather").a(acq.l));
    a(289, "gunpowder", new ado().c("sulphur").a(acq.l));
    a(290, "wooden_hoe", new adn(ado.a.a).c("hoeWood"));
    a(291, "stone_hoe", new adn(ado.a.b).c("hoeStone"));
    a(292, "iron_hoe", new adn(ado.a.c).c("hoeIron"));
    a(293, "diamond_hoe", new adn(ado.a.d).c("hoeDiamond"));
    a(294, "golden_hoe", new adn(ado.a.e).c("hoeGold"));
    a(295, "wheat_seeds", new aej(aju.aj, aju.ak).c("seeds"));
    a(296, "wheat", new ado().c("wheat").a(acq.l));
    a(297, "bread", new adk(5, 0.6F, false).c("bread"));
    a(298, "leather_helmet", new abw(abw.a.a, 0, rw.f).c("helmetCloth"));
    a(299, "leather_chestplate", new abw(abw.a.a, 0, rw.e).c("chestplateCloth"));
    a(300, "leather_leggings", new abw(abw.a.a, 0, rw.d).c("leggingsCloth"));
    a(301, "leather_boots", new abw(abw.a.a, 0, rw.c).c("bootsCloth"));
    a(302, "chainmail_helmet", new abw(abw.a.b, 1, rw.f).c("helmetChain"));
    a(303, "chainmail_chestplate", new abw(abw.a.b, 1, rw.e).c("chestplateChain"));
    a(304, "chainmail_leggings", new abw(abw.a.b, 1, rw.d).c("leggingsChain"));
    a(305, "chainmail_boots", new abw(abw.a.b, 1, rw.c).c("bootsChain"));
    a(306, "iron_helmet", new abw(abw.a.c, 2, rw.f).c("helmetIron"));
    a(307, "iron_chestplate", new abw(abw.a.c, 2, rw.e).c("chestplateIron"));
    a(308, "iron_leggings", new abw(abw.a.c, 2, rw.d).c("leggingsIron"));
    a(309, "iron_boots", new abw(abw.a.c, 2, rw.c).c("bootsIron"));
    a(310, "diamond_helmet", new abw(abw.a.e, 3, rw.f).c("helmetDiamond"));
    a(311, "diamond_chestplate", new abw(abw.a.e, 3, rw.e).c("chestplateDiamond"));
    a(312, "diamond_leggings", new abw(abw.a.e, 3, rw.d).c("leggingsDiamond"));
    a(313, "diamond_boots", new abw(abw.a.e, 3, rw.c).c("bootsDiamond"));
    a(314, "golden_helmet", new abw(abw.a.d, 4, rw.f).c("helmetGold"));
    a(315, "golden_chestplate", new abw(abw.a.d, 4, rw.e).c("chestplateGold"));
    a(316, "golden_leggings", new abw(abw.a.d, 4, rw.d).c("leggingsGold"));
    a(317, "golden_boots", new abw(abw.a.d, 4, rw.c).c("bootsGold"));
    a(318, "flint", new ado().c("flint").a(acq.l));
    a(319, "porkchop", new adk(3, 0.3F, true).c("porkchopRaw"));
    a(320, "cooked_porkchop", new adk(8, 0.8F, true).c("porkchopCooked"));
    a(321, "painting", new adm(xu.class).c("painting"));
    a(322, "golden_apple", new adl(4, 1.2F, false).h().c("appleGold"));
    a(323, "sign", new aeo().c("sign"));
    a(324, "wooden_door", new acs(aju.ao).c("doorOak"));
    ado ☃ = new acj(aju.a).c("bucket").d(16);
    a(325, "bucket", ☃);
    a(326, "water_bucket", new acj(aju.i).c("bucketWater").b(☃));
    a(327, "lava_bucket", new acj(aju.k).c("bucketLava").b(☃));
    a(328, "minecart", new ady(aah.a.a).c("minecart"));
    a(329, "saddle", new aeh().c("saddle"));
    a(330, "iron_door", new acs(aju.aA).c("doorIron"));
    a(331, "redstone", new aeg().c("redstone"));
    a(332, "snowball", new aet().c("snowball"));
    a(333, "boat", new ace(aag.b.a));
    a(334, "leather", new ado().c("leather").a(acq.l));
    a(335, "milk_bucket", new adx().c("milk").b(☃));
    a(336, "brick", new ado().c("brick").a(acq.l));
    a(337, "clay_ball", new ado().c("clay").a(acq.l));
    a(338, "reeds", new acd(aju.aM).c("reeds").a(acq.l));
    a(339, "paper", new ado().c("paper").a(acq.f));
    a(340, "book", new acf().c("book").a(acq.f));
    a(341, "slime_ball", new ado().c("slimeball").a(acq.f));
    a(342, "chest_minecart", new ady(aah.a.b).c("minecartChest"));
    a(343, "furnace_minecart", new ady(aah.a.c).c("minecartFurnace"));
    a(344, "egg", new acw().c("egg"));
    a(345, "compass", new aco().c("compass").a(acq.i));
    a(346, "fishing_rod", new adi().c("fishingRod"));
    a(347, "clock", new acm().c("clock").a(acq.i));
    a(348, "glowstone_dust", new ado().c("yellowDust").a(acq.l));
    a(349, "fish", new adh(false).c("fish").a(true));
    a(350, "cooked_fish", new adh(true).c("fish").a(true));
    a(351, "dye", new acu().c("dyePowder"));
    a(352, "bone", new ado().c("bone").n().a(acq.f));
    a(353, "sugar", new ado().c("sugar").a(acq.l));
    a(354, "cake", new acd(aju.ba).d(1).c("cake").a(acq.h));
    a(355, "bed", new acb().d(1).c("bed"));
    a(356, "repeater", new acd(aju.bb).c("diode").a(acq.d));
    a(357, "cookie", new adk(2, 0.1F, false).c("cookie"));
    a(358, "filled_map", new adw().c("map"));
    a(359, "shears", new ael().c("shears"));
    a(360, "melon", new adk(2, 0.3F, false).c("melon"));
    a(361, "pumpkin_seeds", new aej(aju.bl, aju.ak).c("seeds_pumpkin"));
    a(362, "melon_seeds", new aej(aju.bm, aju.ak).c("seeds_melon"));
    a(363, "beef", new adk(3, 0.3F, true).c("beefRaw"));
    a(364, "cooked_beef", new adk(8, 0.8F, true).c("beefCooked"));
    a(365, "chicken", new adk(2, 0.3F, true).a(new rl(rm.q, 600, 0), 0.3F).c("chickenRaw"));
    a(366, "cooked_chicken", new adk(6, 0.6F, true).c("chickenCooked"));
    a(367, "rotten_flesh", new adk(4, 0.1F, true).a(new rl(rm.q, 600, 0), 0.8F).c("rottenFlesh"));
    a(368, "ender_pearl", new adc().c("enderPearl"));
    a(369, "blaze_rod", new ado().c("blazeRod").a(acq.l).n());
    a(370, "ghast_tear", new ado().c("ghastTear").a(acq.k));
    a(371, "gold_nugget", new ado().c("goldNugget").a(acq.l));
    a(372, "nether_wart", new aej(aju.bB, aju.aW).c("netherStalkSeeds"));
    a(373, "potion", new aed().c("potion"));
    ado ☃ = new acg().c("glassBottle");
    a(374, "glass_bottle", ☃);
    a(375, "spider_eye", new adk(2, 0.8F, false).a(new rl(rm.s, 100, 0), 1.0F).c("spiderEye"));
    a(376, "fermented_spider_eye", new ado().c("fermentedSpiderEye").a(acq.k));
    a(377, "blaze_powder", new ado().c("blazePowder").a(acq.k));
    a(378, "magma_cream", new ado().c("magmaCream").a(acq.k));
    a(379, "brewing_stand", new acd(aju.bD).c("brewingStand").a(acq.k));
    a(380, "cauldron", new acd(aju.bE).c("cauldron").a(acq.k));
    a(381, "ender_eye", new adb().c("eyeOfEnder"));
    a(382, "speckled_melon", new ado().c("speckledMelon").a(acq.k));
    a(383, "spawn_egg", new aeu().c("monsterPlacer"));
    a(384, "experience_bottle", new add().c("expBottle"));
    a(385, "fire_charge", new ade().c("fireball"));
    a(386, "writable_book", new afc().c("writingBook").a(acq.f));
    a(387, "written_book", new afd().c("writtenBook").d(16));
    a(388, "emerald", new ado().c("emerald").a(acq.l));
    a(389, "item_frame", new adm(xs.class).c("frame"));
    a(390, "flower_pot", new acd(aju.ca).c("flowerPot").a(acq.c));
    a(391, "carrot", new aei(3, 0.6F, aju.cb, aju.ak).c("carrots"));
    a(392, "potato", new aei(1, 0.3F, aju.cc, aju.ak).c("potato"));
    a(393, "baked_potato", new adk(5, 0.6F, false).c("potatoBaked"));
    a(394, "poisonous_potato", new adk(2, 0.3F, false).a(new rl(rm.s, 100, 0), 0.6F).c("potatoPoisonous"));
    a(395, "map", new acy().c("emptyMap"));
    a(396, "golden_carrot", new adk(6, 1.2F, false).c("carrotGolden").a(acq.k));
    a(397, "skull", new aeq().c("skull"));
    a(398, "carrot_on_a_stick", new ack().c("carrotOnAStick"));
    a(399, "nether_star", new aep().c("netherStar").a(acq.l));
    a(400, "pumpkin_pie", new adk(8, 0.3F, false).c("pumpkinPie").a(acq.h));
    a(401, "fireworks", new adg().c("fireworks"));
    a(402, "firework_charge", new adf().c("fireworksCharge").a(acq.f));
    a(403, "enchanted_book", new acz().d(1).c("enchantedBook"));
    a(404, "comparator", new acd(aju.cj).c("comparator").a(acq.d));
    a(405, "netherbrick", new ado().c("netherbrick").a(acq.l));
    a(406, "quartz", new ado().c("netherquartz").a(acq.l));
    a(407, "tnt_minecart", new ady(aah.a.d).c("minecartTnt"));
    a(408, "hopper_minecart", new ady(aah.a.f).c("minecartHopper"));
    a(409, "prismarine_shard", new ado().c("prismarineShard").a(acq.l));
    a(410, "prismarine_crystals", new ado().c("prismarineCrystals").a(acq.l));
    a(411, "rabbit", new adk(3, 0.3F, true).c("rabbitRaw"));
    a(412, "cooked_rabbit", new adk(5, 0.6F, true).c("rabbitCooked"));
    a(413, "rabbit_stew", new aci(10).c("rabbitStew"));
    a(414, "rabbit_foot", new ado().c("rabbitFoot").a(acq.k));
    a(415, "rabbit_hide", new ado().c("rabbitHide").a(acq.l));
    a(416, "armor_stand", new abx().c("armorStand").d(16));
    a(417, "iron_horse_armor", new ado().c("horsearmormetal").d(1).a(acq.f));
    a(418, "golden_horse_armor", new ado().c("horsearmorgold").d(1).a(acq.f));
    a(419, "diamond_horse_armor", new ado().c("horsearmordiamond").d(1).a(acq.f));
    a(420, "lead", new adt().c("leash"));
    a(421, "name_tag", new aea().c("nameTag"));
    a(422, "command_block_minecart", new ady(aah.a.g).c("minecartCommandBlock").a(null));
    a(423, "mutton", new adk(2, 0.3F, true).c("muttonRaw"));
    a(424, "cooked_mutton", new adk(6, 0.8F, true).c("muttonCooked"));
    a(425, "banner", new aca().b("banner"));
    a(426, "end_crystal", new ada());
    a(427, "spruce_door", new acs(aju.ap).c("doorSpruce"));
    a(428, "birch_door", new acs(aju.aq).c("doorBirch"));
    a(429, "jungle_door", new acs(aju.ar).c("doorJungle"));
    a(430, "acacia_door", new acs(aju.as).c("doorAcacia"));
    a(431, "dark_oak_door", new acs(aju.at).c("doorDarkOak"));
    a(432, "chorus_fruit", new acl(4, 0.3F).h().c("chorusFruit").a(acq.l));
    a(433, "chorus_fruit_popped", new ado().c("chorusFruitPopped").a(acq.l));
    a(434, "beetroot", new adk(1, 0.6F, false).c("beetroot"));
    a(435, "beetroot_seeds", new aej(aju.cZ, aju.ak).c("beetroot_seeds"));
    a(436, "beetroot_soup", new aci(6).c("beetroot_soup"));
    a(437, "dragon_breath", new ado().a(acq.k).c("dragon_breath").b(☃));
    a(438, "splash_potion", new aew().c("splash_potion"));
    a(439, "spectral_arrow", new aev().c("spectral_arrow"));
    a(440, "tipped_arrow", new aez().c("tipped_arrow"));
    a(441, "lingering_potion", new adv().c("lingering_potion"));
    a(442, "shield", new aem().c("shield"));
    a(443, "elytra", new acx().c("elytra"));
    a(444, "spruce_boat", new ace(aag.b.b));
    a(445, "birch_boat", new ace(aag.b.c));
    a(446, "jungle_boat", new ace(aag.b.d));
    a(447, "acacia_boat", new ace(aag.b.e));
    a(448, "dark_oak_boat", new ace(aag.b.f));
    
    a(2256, "record_13", new aef("13", ng.eu).c("record"));
    a(2257, "record_cat", new aef("cat", ng.ew).c("record"));
    a(2258, "record_blocks", new aef("blocks", ng.ev).c("record"));
    a(2259, "record_chirp", new aef("chirp", ng.ex).c("record"));
    a(2260, "record_far", new aef("far", ng.ey).c("record"));
    a(2261, "record_mall", new aef("mall", ng.ez).c("record"));
    a(2262, "record_mellohi", new aef("mellohi", ng.eA).c("record"));
    a(2263, "record_stal", new aef("stal", ng.eB).c("record"));
    a(2264, "record_strad", new aef("strad", ng.eC).c("record"));
    a(2265, "record_ward", new aef("ward", ng.eE).c("record"));
    a(2266, "record_11", new aef("11", ng.et).c("record"));
    a(2267, "record_wait", new aef("wait", ng.eD).c("record"));
  }
  
  private static void b(ajt ☃)
  {
    a(☃, new acc(☃));
  }
  
  protected static void a(ajt ☃, ado ☃)
  {
    a(ajt.a(☃), (kk)ajt.h.b(☃), ☃);
    a.put(☃, ☃);
  }
  
  private static void a(int ☃, String ☃, ado ☃)
  {
    a(☃, new kk(☃), ☃);
  }
  
  private static void a(int ☃, kk ☃, ado ☃)
  {
    f.a(☃, ☃, ☃);
  }
}
