import com.google.common.collect.Lists;
import java.util.List;

public abstract class agm
{
  public static final cx<kk, agm> b = new cx();
  private final rw[] a;
  private final agm.a e;
  public agn c;
  protected String d;
  
  public static agm c(int ☃)
  {
    return (agm)b.a(☃);
  }
  
  public static int b(agm ☃)
  {
    return b.a(☃);
  }
  
  public static agm b(String ☃)
  {
    return (agm)b.c(new kk(☃));
  }
  
  public static enum a
  {
    private final int e;
    
    private a(int ☃)
    {
      this.e = ☃;
    }
    
    public int a()
    {
      return this.e;
    }
  }
  
  protected agm(agm.a ☃, agn ☃, rw[] ☃)
  {
    this.e = ☃;
    this.c = ☃;
    this.a = ☃;
  }
  
  public Iterable<adq> a(sa ☃)
  {
    List<adq> ☃ = Lists.newArrayList();
    for (rw ☃ : this.a)
    {
      adq ☃ = ☃.a(☃);
      if (☃ != null) {
        ☃.add(☃);
      }
    }
    return ☃.size() > 0 ? ☃ : null;
  }
  
  public agm.a c()
  {
    return this.e;
  }
  
  public int d()
  {
    return 1;
  }
  
  public int b()
  {
    return 1;
  }
  
  public int a(int ☃)
  {
    return 1 + ☃ * 10;
  }
  
  public int b(int ☃)
  {
    return a(☃) + 5;
  }
  
  public int a(int ☃, rc ☃)
  {
    return 0;
  }
  
  public float a(int ☃, sf ☃)
  {
    return 0.0F;
  }
  
  public boolean a(agm ☃)
  {
    return this != ☃;
  }
  
  public agm c(String ☃)
  {
    this.d = ☃;
    return this;
  }
  
  public String a()
  {
    return "enchantment." + this.d;
  }
  
  public String d(int ☃)
  {
    String ☃ = di.a(a());
    if ((☃ == 1) && (b() == 1)) {
      return ☃;
    }
    return ☃ + " " + di.a(new StringBuilder().append("enchantment.level.").append(☃).toString());
  }
  
  public boolean a(adq ☃)
  {
    return this.c.a(☃.b());
  }
  
  public void a(sa ☃, rr ☃, int ☃) {}
  
  public void b(sa ☃, rr ☃, int ☃) {}
  
  public boolean e()
  {
    return false;
  }
  
  public static void f()
  {
    rw[] ☃ = { rw.f, rw.e, rw.d, rw.c };
    
    b.a(0, new kk("protection"), new agy(agm.a.a, agy.a.a, ☃));
    b.a(1, new kk("fire_protection"), new agy(agm.a.b, agy.a.b, ☃));
    b.a(2, new kk("feather_falling"), new agy(agm.a.b, agy.a.c, ☃));
    b.a(3, new kk("blast_protection"), new agy(agm.a.c, agy.a.d, ☃));
    b.a(4, new kk("projectile_protection"), new agy(agm.a.b, agy.a.e, ☃));
    b.a(5, new kk("respiration"), new agx(agm.a.c, ☃));
    b.a(6, new kk("aqua_affinity"), new ahc(agm.a.c, ☃));
    b.a(7, new kk("thorns"), new agz(agm.a.d, ☃));
    b.a(8, new kk("depth_strider"), new ahb(agm.a.c, ☃));
    b.a(9, new kk("frost_walker"), new agt(agm.a.c, new rw[] { rw.c }));
    
    b.a(16, new kk("sharpness"), new agj(agm.a.a, 0, new rw[] { rw.a }));
    b.a(17, new kk("smite"), new agj(agm.a.b, 1, new rw[] { rw.a }));
    b.a(18, new kk("bane_of_arthropods"), new agj(agm.a.b, 2, new rw[] { rw.a }));
    b.a(19, new kk("knockback"), new agu(agm.a.b, new rw[] { rw.a }));
    b.a(20, new kk("fire_aspect"), new agr(agm.a.c, new rw[] { rw.a }));
    b.a(21, new kk("looting"), new agv(agm.a.c, agn.g, new rw[] { rw.a }));
    
    b.a(32, new kk("efficiency"), new agl(agm.a.a, new rw[] { rw.a }));
    b.a(33, new kk("silk_touch"), new aha(agm.a.d, new rw[] { rw.a }));
    b.a(34, new kk("unbreaking"), new agk(agm.a.b, new rw[] { rw.a }));
    b.a(35, new kk("fortune"), new agv(agm.a.c, agn.h, new rw[] { rw.a }));
    
    b.a(48, new kk("power"), new agf(agm.a.a, new rw[] { rw.a }));
    b.a(49, new kk("punch"), new agi(agm.a.c, new rw[] { rw.a }));
    b.a(50, new kk("flame"), new agg(agm.a.c, new rw[] { rw.a }));
    b.a(51, new kk("infinity"), new agh(agm.a.d, new rw[] { rw.a }));
    
    b.a(61, new kk("luck_of_the_sea"), new agv(agm.a.c, agn.i, new rw[] { rw.a }));
    b.a(62, new kk("lure"), new ags(agm.a.c, agn.i, new rw[] { rw.a }));
    
    b.a(70, new kk("mending"), new agw(agm.a.c, rw.values()));
  }
}
