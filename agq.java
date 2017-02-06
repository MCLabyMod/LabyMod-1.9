public class agq
{
  public static final agm a = a("protection");
  public static final agm b = a("fire_protection");
  public static final agm c = a("feather_falling");
  public static final agm d = a("blast_protection");
  public static final agm e = a("projectile_protection");
  public static final agm f = a("respiration");
  public static final agm g = a("aqua_affinity");
  public static final agm h = a("thorns");
  public static final agm i = a("depth_strider");
  public static final agm j = a("frost_walker");
  public static final agm k = a("sharpness");
  public static final agm l = a("smite");
  public static final agm m = a("bane_of_arthropods");
  public static final agm n = a("knockback");
  public static final agm o = a("fire_aspect");
  public static final agm p = a("looting");
  public static final agm q = a("efficiency");
  public static final agm r = a("silk_touch");
  public static final agm s = a("unbreaking");
  public static final agm t = a("fortune");
  public static final agm u = a("power");
  public static final agm v = a("punch");
  public static final agm w = a("flame");
  public static final agm x = a("infinity");
  public static final agm y = a("luck_of_the_sea");
  public static final agm z = a("lure");
  public static final agm A = a("mending");
  
  static
  {
    if (!kn.a()) {
      throw new RuntimeException("Accessed MobEffects before Bootstrap!");
    }
  }
  
  private static agm a(String ☃)
  {
    agm ☃ = (agm)agm.b.c(new kk(☃));
    if (☃ == null) {
      throw new IllegalStateException("Invalid Enchantment requested: " + ☃);
    }
    return ☃;
  }
}
