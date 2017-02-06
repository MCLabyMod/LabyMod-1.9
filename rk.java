import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class rk
{
  public static final cx<kk, rk> b = new cx();
  
  public static rk a(int ☃)
  {
    return (rk)b.a(☃);
  }
  
  public static int a(rk ☃)
  {
    return b.a(☃);
  }
  
  public static rk b(String ☃)
  {
    return (rk)b.c(new kk(☃));
  }
  
  private final Map<sl, sn> a = Maps.newHashMap();
  private final boolean c;
  private final int d;
  private String e = "";
  private int f = -1;
  private double g;
  private boolean h;
  
  protected rk(boolean ☃, int ☃)
  {
    this.c = ☃;
    if (☃) {
      this.g = 0.5D;
    } else {
      this.g = 1.0D;
    }
    this.d = ☃;
  }
  
  protected rk b(int ☃, int ☃)
  {
    this.f = (☃ + ☃ * 8);
    return this;
  }
  
  public void a(sa ☃, int ☃)
  {
    if (this == rm.j)
    {
      if (☃.bQ() < ☃.bW()) {
        ☃.b(1.0F);
      }
    }
    else if (this == rm.s)
    {
      if (☃.bQ() > 1.0F) {
        ☃.a(rc.m, 1.0F);
      }
    }
    else if (this == rm.t) {
      ☃.a(rc.n, 1.0F);
    } else if ((this == rm.q) && ((☃ instanceof zj))) {
      ((zj)☃).a(0.025F * (☃ + 1));
    } else if ((this == rm.w) && ((☃ instanceof zj)))
    {
      if (!☃.l.E) {
        ((zj)☃).cS().a(☃ + 1, 1.0F);
      }
    }
    else if (((this == rm.f) && (!☃.bP())) || ((this == rm.g) && (☃.bP()))) {
      ☃.b(Math.max(4 << ☃, 0));
    } else if (((this == rm.g) && (!☃.bP())) || ((this == rm.f) && (☃.bP()))) {
      ☃.a(rc.m, 6 << ☃);
    }
  }
  
  public void a(rr ☃, rr ☃, sa ☃, int ☃, double ☃)
  {
    if (((this == rm.f) && (!☃.bP())) || ((this == rm.g) && (☃.bP())))
    {
      int ☃ = (int)(☃ * (4 << ☃) + 0.5D);
      ☃.b(☃);
    }
    else if (((this == rm.g) && (!☃.bP())) || ((this == rm.f) && (☃.bP())))
    {
      int ☃ = (int)(☃ * (6 << ☃) + 0.5D);
      if (☃ == null) {
        ☃.a(rc.m, ☃);
      } else {
        ☃.a(rc.b(☃, ☃), ☃);
      }
    }
  }
  
  public boolean a(int ☃, int ☃)
  {
    if (this == rm.j)
    {
      int ☃ = 50 >> ☃;
      if (☃ > 0) {
        return ☃ % ☃ == 0;
      }
      return true;
    }
    if (this == rm.s)
    {
      int ☃ = 25 >> ☃;
      if (☃ > 0) {
        return ☃ % ☃ == 0;
      }
      return true;
    }
    if (this == rm.t)
    {
      int ☃ = 40 >> ☃;
      if (☃ > 0) {
        return ☃ % ☃ == 0;
      }
      return true;
    }
    if (this == rm.q) {
      return true;
    }
    return false;
  }
  
  public boolean b()
  {
    return false;
  }
  
  public rk c(String ☃)
  {
    this.e = ☃;
    return this;
  }
  
  public String a()
  {
    return this.e;
  }
  
  public boolean c()
  {
    return this.f >= 0;
  }
  
  public int d()
  {
    return this.f;
  }
  
  public boolean e()
  {
    return this.c;
  }
  
  public static String a(rl ☃, float ☃)
  {
    if (☃.g()) {
      return "**:**";
    }
    int ☃ = on.d(☃.b() * ☃);
    return os.a(☃);
  }
  
  protected rk a(double ☃)
  {
    this.g = ☃;
    return this;
  }
  
  public int g()
  {
    return this.d;
  }
  
  public rk a(sl ☃, String ☃, double ☃, int ☃)
  {
    sn ☃ = new sn(UUID.fromString(☃), a(), ☃, ☃);
    this.a.put(☃, ☃);
    return this;
  }
  
  public Map<sl, sn> h()
  {
    return this.a;
  }
  
  public void a(sa ☃, sp ☃, int ☃)
  {
    for (Map.Entry<sl, sn> ☃ : this.a.entrySet())
    {
      sm ☃ = ☃.a((sl)☃.getKey());
      if (☃ != null) {
        ☃.c((sn)☃.getValue());
      }
    }
  }
  
  public void b(sa ☃, sp ☃, int ☃)
  {
    for (Map.Entry<sl, sn> ☃ : this.a.entrySet())
    {
      sm ☃ = ☃.a((sl)☃.getKey());
      if (☃ != null)
      {
        sn ☃ = (sn)☃.getValue();
        ☃.c(☃);
        ☃.b(new sn(☃.a(), a() + " " + ☃, a(☃, ☃), ☃.c()));
      }
    }
  }
  
  public double a(int ☃, sn ☃)
  {
    return ☃.d() * (☃ + 1);
  }
  
  public boolean i()
  {
    return this.h;
  }
  
  public rk j()
  {
    this.h = true;
    return this;
  }
  
  public static void k()
  {
    b.a(1, new kk("speed"), new rk(false, 8171462).c("effect.moveSpeed").b(0, 0).a(yt.d, "91AEAA56-376B-4498-935B-2F7F68070635", 0.20000000298023224D, 2).j());
    b.a(2, new kk("slowness"), new rk(true, 5926017).c("effect.moveSlowdown").b(1, 0).a(yt.d, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15000000596046448D, 2));
    b.a(3, new kk("haste"), new rk(false, 14270531).c("effect.digSpeed").b(2, 0).a(1.5D).j().a(yt.f, "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3", 0.10000000149011612D, 2));
    b.a(4, new kk("mining_fatigue"), new rk(true, 4866583).c("effect.digSlowDown").b(3, 0).a(yt.f, "55FCED67-E92A-486E-9800-B47F202C4386", -0.10000000149011612D, 2));
    b.a(5, new kk("strength"), new rh(false, 9643043, 3.0D).c("effect.damageBoost").b(4, 0).a(yt.e, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 0.0D, 0).j());
    b.a(6, new kk("instant_health"), new rj(false, 16262179).c("effect.heal").j());
    b.a(7, new kk("instant_damage"), new rj(true, 4393481).c("effect.harm").j());
    b.a(8, new kk("jump_boost"), new rk(false, 2293580).c("effect.jump").b(2, 1).j());
    b.a(9, new kk("nausea"), new rk(true, 5578058).c("effect.confusion").b(3, 1).a(0.25D));
    b.a(10, new kk("regeneration"), new rk(false, 13458603).c("effect.regeneration").b(7, 0).a(0.25D).j());
    b.a(11, new kk("resistance"), new rk(false, 10044730).c("effect.resistance").b(6, 1).j());
    b.a(12, new kk("fire_resistance"), new rk(false, 14981690).c("effect.fireResistance").b(7, 1).j());
    b.a(13, new kk("water_breathing"), new rk(false, 3035801).c("effect.waterBreathing").b(0, 2).j());
    b.a(14, new kk("invisibility"), new rk(false, 8356754).c("effect.invisibility").b(0, 1).j());
    b.a(15, new kk("blindness"), new rk(true, 2039587).c("effect.blindness").b(5, 1).a(0.25D));
    b.a(16, new kk("night_vision"), new rk(false, 2039713).c("effect.nightVision").b(4, 1).j());
    b.a(17, new kk("hunger"), new rk(true, 5797459).c("effect.hunger").b(1, 1));
    b.a(18, new kk("weakness"), new rh(true, 4738376, -4.0D).c("effect.weakness").b(5, 0).a(yt.e, "22653B89-116E-49DC-9B6B-9971489B5BE5", 0.0D, 0));
    b.a(19, new kk("poison"), new rk(true, 5149489).c("effect.poison").b(6, 0).a(0.25D));
    b.a(20, new kk("wither"), new rk(true, 3484199).c("effect.wither").b(1, 2).a(0.25D));
    b.a(21, new kk("health_boost"), new ri(false, 16284963).c("effect.healthBoost").b(7, 2).a(yt.a, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0D, 0).j());
    b.a(22, new kk("absorption"), new rg(false, 2445989).c("effect.absorption").b(2, 2).j());
    b.a(23, new kk("saturation"), new rj(false, 16262179).c("effect.saturation").j());
    b.a(24, new kk("glowing"), new rk(false, 9740385).c("effect.glowing").b(4, 2));
    b.a(25, new kk("levitation"), new rk(true, 13565951).c("effect.levitation").b(3, 2));
    b.a(26, new kk("luck"), new rk(false, 3381504).c("effect.luck").b(5, 2).j().a(yt.h, "03C3C89D-7037-4B42-869F-B146BCB64D2E", 1.0D, 0));
    b.a(27, new kk("unluck"), new rk(true, 12624973).c("effect.unluck").b(6, 2).a(yt.h, "CC5AF142-2BD2-4215-B636-2605AED11727", -1.0D, 0));
  }
}
