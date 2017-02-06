public class rm
{
  static
  {
    if (!kn.a()) {
      throw new RuntimeException("Accessed MobEffects before Bootstrap!");
    }
  }
  
  public static final rk a = a("speed");
  public static final rk b = a("slowness");
  public static final rk c = a("haste");
  public static final rk d = a("mining_fatigue");
  public static final rk e = a("strength");
  public static final rk f = a("instant_health");
  public static final rk g = a("instant_damage");
  public static final rk h = a("jump_boost");
  public static final rk i = a("nausea");
  public static final rk j = a("regeneration");
  public static final rk k = a("resistance");
  public static final rk l = a("fire_resistance");
  public static final rk m = a("water_breathing");
  public static final rk n = a("invisibility");
  public static final rk o = a("blindness");
  public static final rk p = a("night_vision");
  public static final rk q = a("hunger");
  public static final rk r = a("weakness");
  public static final rk s = a("poison");
  public static final rk t = a("wither");
  public static final rk u = a("health_boost");
  public static final rk v = a("absorption");
  public static final rk w = a("saturation");
  public static final rk x = a("glowing");
  public static final rk y = a("levitation");
  public static final rk z = a("luck");
  public static final rk A = a("unluck");
  
  private static rk a(String ☃)
  {
    rk ☃ = (rk)rk.b.c(new kk(☃));
    if (☃ == null) {
      throw new IllegalStateException("Invalid MobEffect requested: " + ☃);
    }
    return ☃;
  }
}
