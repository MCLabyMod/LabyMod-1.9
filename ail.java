public abstract class ail
{
  static
  {
    if (!kn.a()) {
      throw new RuntimeException("Accessed Biomes before Bootstrap!");
    }
  }
  
  public static final aig a = a("ocean");
  public static final aig b = a;
  public static final aig c = a("plains");
  public static final aig d = a("desert");
  public static final aig e = a("extreme_hills");
  public static final aig f = a("forest");
  public static final aig g = a("taiga");
  public static final aig h = a("swampland");
  public static final aig i = a("river");
  public static final aig j = a("hell");
  public static final aig k = a("sky");
  public static final aig l = a("frozen_ocean");
  public static final aig m = a("frozen_river");
  public static final aig n = a("ice_flats");
  public static final aig o = a("ice_mountains");
  public static final aig p = a("mushroom_island");
  public static final aig q = a("mushroom_island_shore");
  public static final aig r = a("beaches");
  public static final aig s = a("desert_hills");
  public static final aig t = a("forest_hills");
  public static final aig u = a("taiga_hills");
  public static final aig v = a("smaller_extreme_hills");
  public static final aig w = a("jungle");
  public static final aig x = a("jungle_hills");
  public static final aig y = a("jungle_edge");
  public static final aig z = a("deep_ocean");
  public static final aig A = a("stone_beach");
  public static final aig B = a("cold_beach");
  public static final aig C = a("birch_forest");
  public static final aig D = a("birch_forest_hills");
  public static final aig E = a("roofed_forest");
  public static final aig F = a("taiga_cold");
  public static final aig G = a("taiga_cold_hills");
  public static final aig H = a("redwood_taiga");
  public static final aig I = a("redwood_taiga_hills");
  public static final aig J = a("extreme_hills_with_trees");
  public static final aig K = a("savanna");
  public static final aig L = a("savanna_rock");
  public static final aig M = a("mesa");
  public static final aig N = a("mesa_rock");
  public static final aig O = a("mesa_clear_rock");
  public static final aig P = a("void");
  public static final aig Q = a("mutated_plains");
  public static final aig R = a("mutated_desert");
  public static final aig S = a("mutated_extreme_hills");
  public static final aig T = a("mutated_forest");
  public static final aig U = a("mutated_taiga");
  public static final aig V = a("mutated_swampland");
  public static final aig W = a("mutated_ice_flats");
  public static final aig X = a("mutated_jungle");
  public static final aig Y = a("mutated_jungle_edge");
  public static final aig Z = a("mutated_birch_forest");
  public static final aig aa = a("mutated_birch_forest_hills");
  public static final aig ab = a("mutated_roofed_forest");
  public static final aig ac = a("mutated_taiga_cold");
  public static final aig ad = a("mutated_redwood_taiga");
  public static final aig ae = a("mutated_redwood_taiga_hills");
  public static final aig af = a("mutated_extreme_hills_with_trees");
  public static final aig ag = a("mutated_savanna");
  public static final aig ah = a("mutated_savanna_rock");
  public static final aig ai = a("mutated_mesa");
  public static final aig aj = a("mutated_mesa_rock");
  public static final aig ak = a("mutated_mesa_clear_rock");
  
  private static aig a(String ☃)
  {
    aig ☃ = (aig)aig.q.c(new kk(☃));
    if (☃ == null) {
      throw new IllegalStateException("Invalid Biome requested: " + ☃);
    }
    return ☃;
  }
}
