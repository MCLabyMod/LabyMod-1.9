import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class rt
{
  private static final Logger b = ;
  private static final Map<String, Class<? extends rr>> c = Maps.newHashMap();
  private static final Map<Class<? extends rr>, String> d = Maps.newHashMap();
  private static final Map<Integer, Class<? extends rr>> e = Maps.newHashMap();
  private static final Map<Class<? extends rr>, Integer> f = Maps.newHashMap();
  private static final Map<String, Integer> g = Maps.newHashMap();
  public static final Map<String, rt.a> a = Maps.newLinkedHashMap();
  
  private static void a(Class<? extends rr> ☃, String ☃, int ☃)
  {
    if (c.containsKey(☃)) {
      throw new IllegalArgumentException("ID is already registered: " + ☃);
    }
    if (e.containsKey(Integer.valueOf(☃))) {
      throw new IllegalArgumentException("ID is already registered: " + ☃);
    }
    if (☃ == 0) {
      throw new IllegalArgumentException("Cannot register to reserved id: " + ☃);
    }
    if (☃ == null) {
      throw new IllegalArgumentException("Cannot register null clazz for id: " + ☃);
    }
    c.put(☃, ☃);
    d.put(☃, ☃);
    e.put(Integer.valueOf(☃), ☃);
    f.put(☃, Integer.valueOf(☃));
    g.put(☃, Integer.valueOf(☃));
  }
  
  private static void a(Class<? extends rr> ☃, String ☃, int ☃, int ☃, int ☃)
  {
    a(☃, ☃, ☃);
    
    a.put(☃, new rt.a(☃, ☃, ☃));
  }
  
  static
  {
    a(yd.class, "Item", 1);
    a(rx.class, "XPOrb", 2);
    a(rp.class, "AreaEffectCloud", 3);
    
    a(zz.class, "ThrownEgg", 7);
    a(xt.class, "LeashKnot", 8);
    a(xu.class, "Painting", 9);
    a(aad.class, "Arrow", 10);
    a(zw.class, "Snowball", 11);
    a(zr.class, "Fireball", 12);
    a(zv.class, "SmallFireball", 13);
    a(aaa.class, "ThrownEnderpearl", 14);
    a(zo.class, "EyeOfEnderSignal", 15);
    a(aac.class, "ThrownPotion", 16);
    a(aab.class, "ThrownExpBottle", 17);
    a(xs.class, "ItemFrame", 18);
    a(aae.class, "WitherSkull", 19);
    
    a(ye.class, "PrimedTnt", 20);
    a(yc.class, "FallingSand", 21);
    a(zq.class, "FireworksRocketEntity", 22);
    a(zx.class, "SpectralArrow", 24);
    a(zu.class, "ShulkerBullet", 25);
    a(zn.class, "DragonFireball", 26);
    
    a(xq.class, "ArmorStand", 30);
    
    a(aag.class, "Boat", 41);
    a(aan.class, aah.a.a.b(), 42);
    a(aai.class, aah.a.b.b(), 43);
    a(aal.class, aah.a.c.b(), 44);
    a(aap.class, aah.a.d.b(), 45);
    a(aam.class, aah.a.f.b(), 46);
    a(aao.class, aah.a.e.b(), 47);
    a(aaj.class, aah.a.g.b(), 40);
    
    a(sb.class, "Mob", 48);
    a(yq.class, "Monster", 49);
    
    a(yi.class, "Creeper", 50, 894731, 0);
    a(yw.class, "Skeleton", 51, 12698049, 4802889);
    a(yy.class, "Spider", 52, 3419431, 11013646);
    a(yn.class, "Giant", 53);
    a(za.class, "Zombie", 54, 44975, 7969893);
    a(yx.class, "Slime", 55, 5349438, 8306542);
    a(ym.class, "Ghast", 56, 16382457, 12369084);
    a(yr.class, "PigZombie", 57, 15373203, 5009705);
    a(yj.class, "Enderman", 58, 1447446, 0);
    a(yh.class, "CaveSpider", 59, 803406, 11013646);
    a(yv.class, "Silverfish", 60, 7237230, 3158064);
    a(yg.class, "Blaze", 61, 16167425, 16775294);
    a(yp.class, "LavaSlime", 62, 3407872, 16579584);
    a(wu.class, "EnderDragon", 63);
    a(xo.class, "WitherBoss", 64);
    a(vu.class, "Bat", 65, 4996656, 986895);
    a(yz.class, "Witch", 66, 3407872, 5349438);
    a(yk.class, "Endermite", 67, 1447446, 7237230);
    a(yo.class, "Guardian", 68, 5931634, 15826224);
    a(yu.class, "Shulker", 69, 9725844, 5060690);
    
    a(wc.class, "Pig", 90, 15771042, 14377823);
    a(we.class, "Sheep", 91, 15198183, 16758197);
    a(vy.class, "Cow", 92, 4470310, 10592673);
    a(vx.class, "Chicken", 93, 10592673, 16711680);
    a(wg.class, "Squid", 94, 2243405, 7375001);
    a(wj.class, "Wolf", 95, 14144467, 13545366);
    a(wa.class, "MushroomCow", 96, 10489616, 12040119);
    a(wf.class, "SnowMan", 97);
    a(wb.class, "Ozelot", 98, 15720061, 5653556);
    a(wh.class, "VillagerGolem", 99);
    a(wk.class, "EntityHorse", 100, 12623485, 15656192);
    a(wd.class, "Rabbit", 101, 10051392, 7555121);
    
    a(ze.class, "Villager", 120, 5651507, 12422002);
    
    a(wt.class, "EnderCrystal", 200);
  }
  
  public static rr a(String ☃, aht ☃)
  {
    rr ☃ = null;
    try
    {
      Class<? extends rr> ☃ = (Class)c.get(☃);
      if (☃ != null) {
        ☃ = (rr)☃.getConstructor(new Class[] { aht.class }).newInstance(new Object[] { ☃ });
      }
    }
    catch (Exception ☃)
    {
      ☃.printStackTrace();
    }
    return ☃;
  }
  
  public static rr a(dn ☃, aht ☃)
  {
    rr ☃ = null;
    try
    {
      Class<? extends rr> ☃ = (Class)c.get(☃.l("id"));
      if (☃ != null) {
        ☃ = (rr)☃.getConstructor(new Class[] { aht.class }).newInstance(new Object[] { ☃ });
      }
    }
    catch (Exception ☃)
    {
      ☃.printStackTrace();
    }
    if (☃ != null) {
      ☃.f(☃);
    } else {
      b.warn("Skipping Entity with id " + ☃.l("id"));
    }
    return ☃;
  }
  
  public static rr a(int ☃, aht ☃)
  {
    rr ☃ = null;
    try
    {
      Class<? extends rr> ☃ = a(☃);
      if (☃ != null) {
        ☃ = (rr)☃.getConstructor(new Class[] { aht.class }).newInstance(new Object[] { ☃ });
      }
    }
    catch (Exception ☃)
    {
      ☃.printStackTrace();
    }
    if (☃ == null) {
      b.warn("Skipping Entity with id " + ☃);
    }
    return ☃;
  }
  
  public static rr b(String ☃, aht ☃)
  {
    return a(a(☃), ☃);
  }
  
  public static int a(rr ☃)
  {
    Integer ☃ = (Integer)f.get(☃.getClass());
    return ☃ == null ? 0 : ☃.intValue();
  }
  
  public static Class<? extends rr> a(int ☃)
  {
    return (Class)e.get(Integer.valueOf(☃));
  }
  
  public static String b(rr ☃)
  {
    return a(☃.getClass());
  }
  
  public static String a(Class<? extends rr> ☃)
  {
    return (String)d.get(☃);
  }
  
  public static int a(String ☃)
  {
    Integer ☃ = (Integer)g.get(☃);
    if (☃ == null) {
      return 90;
    }
    return ☃.intValue();
  }
  
  public static List<String> b()
  {
    Set<String> ☃ = c.keySet();
    List<String> ☃ = Lists.newArrayList();
    for (String ☃ : ☃)
    {
      Class<? extends rr> ☃ = (Class)c.get(☃);
      if ((☃.getModifiers() & 0x400) != 1024) {
        ☃.add(☃);
      }
    }
    ☃.add("LightningBolt");
    return ☃;
  }
  
  public static boolean a(rr ☃, String ☃)
  {
    String ☃ = b(☃);
    if ((☃ == null) && ((☃ instanceof zj))) {
      ☃ = "Player";
    } else if ((☃ == null) && ((☃ instanceof ya))) {
      ☃ = "LightningBolt";
    }
    return ☃.equals(☃);
  }
  
  public static boolean b(String ☃)
  {
    return ("Player".equals(☃)) || (b().contains(☃));
  }
  
  public static void a() {}
  
  public static class a
  {
    public final String a;
    public final int b;
    public final int c;
    public final np d;
    public final np e;
    
    public a(String ☃, int ☃, int ☃)
    {
      this.a = ☃;
      this.b = ☃;
      this.c = ☃;
      this.d = nt.a(this);
      this.e = nt.b(this);
    }
  }
}
