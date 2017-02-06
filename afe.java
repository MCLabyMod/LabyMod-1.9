import com.google.common.collect.ImmutableList;
import java.util.List;

public class afe
{
  private static final kk b = new kk("water");
  public static final co<kk, afe> a = new co(b);
  private static int c;
  private final String d;
  private final ImmutableList<rl> e;
  
  public static afe a(int ☃)
  {
    return (afe)a.a(☃);
  }
  
  public static int a(afe ☃)
  {
    return a.a(☃);
  }
  
  public static afe a(String ☃)
  {
    return (afe)a.c(new kk(☃));
  }
  
  public afe(rl... ☃)
  {
    this(null, ☃);
  }
  
  public afe(String ☃, rl... ☃)
  {
    this.d = ☃;
    this.e = ImmutableList.copyOf(☃);
  }
  
  public String b(String ☃)
  {
    return ☃ + this.d;
  }
  
  public List<rl> a()
  {
    return this.e;
  }
  
  public static void b()
  {
    a("empty", new afe(new rl[0]));
    a("water", new afe(new rl[0]));
    a("mundane", new afe(new rl[0]));
    a("thick", new afe(new rl[0]));
    a("awkward", new afe(new rl[0]));
    
    a("night_vision", new afe(new rl[] { new rl(rm.p, 3600) }));
    a("long_night_vision", new afe("night_vision", new rl[] { new rl(rm.p, 9600) }));
    
    a("invisibility", new afe(new rl[] { new rl(rm.n, 3600) }));
    a("long_invisibility", new afe("invisibility", new rl[] { new rl(rm.n, 9600) }));
    
    a("leaping", new afe(new rl[] { new rl(rm.h, 3600) }));
    a("long_leaping", new afe("leaping", new rl[] { new rl(rm.h, 9600) }));
    a("strong_leaping", new afe("leaping", new rl[] { new rl(rm.h, 1800, 1) }));
    
    a("fire_resistance", new afe(new rl[] { new rl(rm.l, 3600) }));
    a("long_fire_resistance", new afe("fire_resistance", new rl[] { new rl(rm.l, 9600) }));
    
    a("swiftness", new afe(new rl[] { new rl(rm.a, 3600) }));
    a("long_swiftness", new afe("swiftness", new rl[] { new rl(rm.a, 9600) }));
    a("strong_swiftness", new afe("swiftness", new rl[] { new rl(rm.a, 1800, 1) }));
    
    a("slowness", new afe(new rl[] { new rl(rm.b, 1800) }));
    a("long_slowness", new afe("slowness", new rl[] { new rl(rm.b, 4800) }));
    
    a("water_breathing", new afe(new rl[] { new rl(rm.m, 3600) }));
    a("long_water_breathing", new afe("water_breathing", new rl[] { new rl(rm.m, 9600) }));
    
    a("healing", new afe(new rl[] { new rl(rm.f, 1) }));
    a("strong_healing", new afe("healing", new rl[] { new rl(rm.f, 1, 1) }));
    
    a("harming", new afe(new rl[] { new rl(rm.g, 1) }));
    a("strong_harming", new afe("harming", new rl[] { new rl(rm.g, 1, 1) }));
    
    a("poison", new afe(new rl[] { new rl(rm.s, 900) }));
    a("long_poison", new afe("poison", new rl[] { new rl(rm.s, 1800) }));
    a("strong_poison", new afe("poison", new rl[] { new rl(rm.s, 432, 1) }));
    
    a("regeneration", new afe(new rl[] { new rl(rm.j, 900) }));
    a("long_regeneration", new afe("regeneration", new rl[] { new rl(rm.j, 1800) }));
    a("strong_regeneration", new afe("regeneration", new rl[] { new rl(rm.j, 450, 1) }));
    
    a("strength", new afe(new rl[] { new rl(rm.e, 3600) }));
    a("long_strength", new afe("strength", new rl[] { new rl(rm.e, 9600) }));
    a("strong_strength", new afe("strength", new rl[] { new rl(rm.e, 1800, 1) }));
    
    a("weakness", new afe(new rl[] { new rl(rm.r, 1800) }));
    a("long_weakness", new afe("weakness", new rl[] { new rl(rm.r, 4800) }));
    
    a("luck", new afe("luck", new rl[] { new rl(rm.z, 6000) }));
    
    a.a();
  }
  
  protected static void a(String ☃, afe ☃)
  {
    a.a(c++, new kk(☃), ☃);
  }
  
  public boolean c()
  {
    if (!this.e.isEmpty()) {
      for (rl ☃ : this.e) {
        if (☃.a().b()) {
          return true;
        }
      }
    }
    return false;
  }
}
