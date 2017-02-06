import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Set;

public class azt
{
  private static final Set<kk> ap = ;
  private static final Set<kk> aq = Collections.unmodifiableSet(ap);
  public static final kk a = a("empty");
  public static final kk b = a("chests/spawn_bonus_chest");
  public static final kk c = a("chests/end_city_treasure");
  public static final kk d = a("chests/simple_dungeon");
  public static final kk e = a("chests/village_blacksmith");
  public static final kk f = a("chests/abandoned_mineshaft");
  public static final kk g = a("chests/nether_bridge");
  public static final kk h = a("chests/stronghold_library");
  public static final kk i = a("chests/stronghold_crossing");
  public static final kk j = a("chests/stronghold_corridor");
  public static final kk k = a("chests/desert_pyramid");
  public static final kk l = a("chests/jungle_temple");
  public static final kk m = a("chests/igloo_chest");
  public static final kk n = a("entities/witch");
  public static final kk o = a("entities/blaze");
  public static final kk p = a("entities/creeper");
  public static final kk q = a("entities/spider");
  public static final kk r = a("entities/cave_spider");
  public static final kk s = a("entities/giant");
  public static final kk t = a("entities/silverfish");
  public static final kk u = a("entities/enderman");
  public static final kk v = a("entities/guardian");
  public static final kk w = a("entities/elder_guardian");
  public static final kk x = a("entities/shulker");
  public static final kk y = a("entities/iron_golem");
  public static final kk z = a("entities/snowman");
  public static final kk A = a("entities/rabbit");
  public static final kk B = a("entities/chicken");
  public static final kk C = a("entities/pig");
  public static final kk D = a("entities/horse");
  public static final kk E = a("entities/zombie_horse");
  public static final kk F = a("entities/skeleton_horse");
  public static final kk G = a("entities/cow");
  public static final kk H = a("entities/mushroom_cow");
  public static final kk I = a("entities/wolf");
  public static final kk J = a("entities/ocelot");
  public static final kk K = a("entities/sheep");
  public static final kk L = a("entities/sheep/white");
  public static final kk M = a("entities/sheep/orange");
  public static final kk N = a("entities/sheep/magenta");
  public static final kk O = a("entities/sheep/light_blue");
  public static final kk P = a("entities/sheep/yellow");
  public static final kk Q = a("entities/sheep/lime");
  public static final kk R = a("entities/sheep/pink");
  public static final kk S = a("entities/sheep/gray");
  public static final kk T = a("entities/sheep/silver");
  public static final kk U = a("entities/sheep/cyan");
  public static final kk V = a("entities/sheep/purple");
  public static final kk W = a("entities/sheep/blue");
  public static final kk X = a("entities/sheep/brown");
  public static final kk Y = a("entities/sheep/green");
  public static final kk Z = a("entities/sheep/red");
  public static final kk aa = a("entities/sheep/black");
  public static final kk ab = a("entities/bat");
  public static final kk ac = a("entities/slime");
  public static final kk ad = a("entities/magma_cube");
  public static final kk ae = a("entities/ghast");
  public static final kk af = a("entities/squid");
  public static final kk ag = a("entities/endermite");
  public static final kk ah = a("entities/zombie");
  public static final kk ai = a("entities/zombie_pigman");
  public static final kk aj = a("entities/skeleton");
  public static final kk ak = a("entities/wither_skeleton");
  public static final kk al = a("gameplay/fishing");
  public static final kk am = a("gameplay/fishing/junk");
  public static final kk an = a("gameplay/fishing/treasure");
  public static final kk ao = a("gameplay/fishing/fish");
  
  private static kk a(String ☃)
  {
    return a(new kk("minecraft", ☃));
  }
  
  public static kk a(kk ☃)
  {
    if (ap.add(☃)) {
      return ☃;
    }
    throw new IllegalArgumentException(☃ + " is already a registered built-in loot table");
  }
  
  public static Set<kk> a()
  {
    return aq;
  }
}
