public class pq
  implements ox
{
  private static final String[] a = new String[''];
  
  static
  {
    a[0] = "minecraft:water";
    a[1] = "minecraft:regeneration";
    a[2] = "minecraft:swiftness";
    a[3] = "minecraft:fire_resistance";
    a[4] = "minecraft:poison";
    a[5] = "minecraft:healing";
    a[6] = "minecraft:night_vision";
    a[7] = null;
    a[8] = "minecraft:weakness";
    a[9] = "minecraft:strength";
    a[10] = "minecraft:slowness";
    a[11] = "minecraft:leaping";
    a[12] = "minecraft:harming";
    a[13] = "minecraft:water_breathing";
    a[14] = "minecraft:invisibility";
    a[15] = null;
    a[16] = "minecraft:awkward";
    a[17] = "minecraft:regeneration";
    a[18] = "minecraft:swiftness";
    a[19] = "minecraft:fire_resistance";
    a[20] = "minecraft:poison";
    a[21] = "minecraft:healing";
    a[22] = "minecraft:night_vision";
    a[23] = null;
    a[24] = "minecraft:weakness";
    a[25] = "minecraft:strength";
    a[26] = "minecraft:slowness";
    a[27] = "minecraft:leaping";
    a[28] = "minecraft:harming";
    a[29] = "minecraft:water_breathing";
    a[30] = "minecraft:invisibility";
    a[31] = null;
    a[32] = "minecraft:thick";
    a[33] = "minecraft:strong_regeneration";
    a[34] = "minecraft:strong_swiftness";
    a[35] = "minecraft:fire_resistance";
    a[36] = "minecraft:strong_poison";
    a[37] = "minecraft:strong_healing";
    a[38] = "minecraft:night_vision";
    a[39] = null;
    a[40] = "minecraft:weakness";
    a[41] = "minecraft:strong_strength";
    a[42] = "minecraft:slowness";
    a[43] = "minecraft:strong_leaping";
    a[44] = "minecraft:strong_harming";
    a[45] = "minecraft:water_breathing";
    a[46] = "minecraft:invisibility";
    a[47] = null;
    a[48] = null;
    a[49] = "minecraft:strong_regeneration";
    a[50] = "minecraft:strong_swiftness";
    a[51] = "minecraft:fire_resistance";
    a[52] = "minecraft:strong_poison";
    a[53] = "minecraft:strong_healing";
    a[54] = "minecraft:night_vision";
    a[55] = null;
    a[56] = "minecraft:weakness";
    a[57] = "minecraft:strong_strength";
    a[58] = "minecraft:slowness";
    a[59] = "minecraft:strong_leaping";
    a[60] = "minecraft:strong_harming";
    a[61] = "minecraft:water_breathing";
    a[62] = "minecraft:invisibility";
    a[63] = null;
    a[64] = "minecraft:mundane";
    a[65] = "minecraft:long_regeneration";
    a[66] = "minecraft:long_swiftness";
    a[67] = "minecraft:long_fire_resistance";
    a[68] = "minecraft:long_poison";
    a[69] = "minecraft:healing";
    a[70] = "minecraft:long_night_vision";
    a[71] = null;
    a[72] = "minecraft:long_weakness";
    a[73] = "minecraft:long_strength";
    a[74] = "minecraft:long_slowness";
    a[75] = "minecraft:long_leaping";
    a[76] = "minecraft:harming";
    a[77] = "minecraft:long_water_breathing";
    a[78] = "minecraft:long_invisibility";
    a[79] = null;
    a[80] = "minecraft:awkward";
    a[81] = "minecraft:long_regeneration";
    a[82] = "minecraft:long_swiftness";
    a[83] = "minecraft:long_fire_resistance";
    a[84] = "minecraft:long_poison";
    a[85] = "minecraft:healing";
    a[86] = "minecraft:long_night_vision";
    a[87] = null;
    a[88] = "minecraft:long_weakness";
    a[89] = "minecraft:long_strength";
    a[90] = "minecraft:long_slowness";
    a[91] = "minecraft:long_leaping";
    a[92] = "minecraft:harming";
    a[93] = "minecraft:long_water_breathing";
    a[94] = "minecraft:long_invisibility";
    a[95] = null;
    a[96] = "minecraft:thick";
    a[97] = "minecraft:regeneration";
    a[98] = "minecraft:swiftness";
    a[99] = "minecraft:long_fire_resistance";
    a[100] = "minecraft:poison";
    a[101] = "minecraft:strong_healing";
    a[102] = "minecraft:long_night_vision";
    a[103] = null;
    a[104] = "minecraft:long_weakness";
    a[105] = "minecraft:strength";
    a[106] = "minecraft:long_slowness";
    a[107] = "minecraft:leaping";
    a[108] = "minecraft:strong_harming";
    a[109] = "minecraft:long_water_breathing";
    a[110] = "minecraft:long_invisibility";
    a[111] = null;
    a[112] = null;
    a[113] = "minecraft:regeneration";
    a[114] = "minecraft:swiftness";
    a[115] = "minecraft:long_fire_resistance";
    a[116] = "minecraft:poison";
    a[117] = "minecraft:strong_healing";
    a[118] = "minecraft:long_night_vision";
    a[119] = null;
    a[120] = "minecraft:long_weakness";
    a[121] = "minecraft:strength";
    a[122] = "minecraft:long_slowness";
    a[123] = "minecraft:leaping";
    a[124] = "minecraft:strong_harming";
    a[125] = "minecraft:long_water_breathing";
    a[126] = "minecraft:long_invisibility";
    a[127] = null;
  }
  
  public int a()
  {
    return 102;
  }
  
  public dn a(dn ☃)
  {
    if ("minecraft:potion".equals(☃.l("id")))
    {
      dn ☃ = ☃.o("tag");
      short ☃ = ☃.g("Damage");
      if (!☃.b("Potion", 8))
      {
        String ☃ = a[(☃ & 0x7F)];
        ☃.a("Potion", ☃ == null ? "minecraft:water" : ☃);
        ☃.a("tag", ☃);
        if ((☃ & 0x4000) == 16384) {
          ☃.a("id", "minecraft:splash_potion");
        }
      }
      if (☃ != 0) {
        ☃.a("Damage", (short)0);
      }
    }
    return ☃;
  }
}
