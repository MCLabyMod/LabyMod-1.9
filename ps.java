public class ps
  implements ox
{
  private static final String[] a = new String['Ā'];
  
  static
  {
    String[] ☃ = a;
    
    ☃[1] = "Item";
    ☃[2] = "XPOrb";
    
    ☃[7] = "ThrownEgg";
    ☃[8] = "LeashKnot";
    ☃[9] = "Painting";
    ☃[10] = "Arrow";
    ☃[11] = "Snowball";
    ☃[12] = "Fireball";
    ☃[13] = "SmallFireball";
    ☃[14] = "ThrownEnderpearl";
    ☃[15] = "EyeOfEnderSignal";
    ☃[16] = "ThrownPotion";
    ☃[17] = "ThrownExpBottle";
    ☃[18] = "ItemFrame";
    ☃[19] = "WitherSkull";
    
    ☃[20] = "PrimedTnt";
    ☃[21] = "FallingSand";
    ☃[22] = "FireworksRocketEntity";
    ☃[23] = "TippedArrow";
    ☃[24] = "SpectralArrow";
    ☃[25] = "ShulkerBullet";
    ☃[26] = "DragonFireball";
    
    ☃[30] = "ArmorStand";
    
    ☃[41] = "Boat";
    
    ☃[42] = "MinecartRideable";
    ☃[43] = "MinecartChest";
    ☃[44] = "MinecartFurnace";
    ☃[45] = "MinecartTNT";
    ☃[46] = "MinecartHopper";
    ☃[47] = "MinecartSpawner";
    ☃[40] = "MinecartCommandBlock";
    
    ☃[48] = "Mob";
    ☃[49] = "Monster";
    
    ☃[50] = "Creeper";
    ☃[51] = "Skeleton";
    ☃[52] = "Spider";
    ☃[53] = "Giant";
    ☃[54] = "Zombie";
    ☃[55] = "Slime";
    ☃[56] = "Ghast";
    ☃[57] = "PigZombie";
    ☃[58] = "Enderman";
    ☃[59] = "CaveSpider";
    ☃[60] = "Silverfish";
    ☃[61] = "Blaze";
    ☃[62] = "LavaSlime";
    ☃[63] = "EnderDragon";
    ☃[64] = "WitherBoss";
    ☃[65] = "Bat";
    ☃[66] = "Witch";
    ☃[67] = "Endermite";
    ☃[68] = "Guardian";
    ☃[69] = "Shulker";
    
    ☃[90] = "Pig";
    ☃[91] = "Sheep";
    ☃[92] = "Cow";
    ☃[93] = "Chicken";
    ☃[94] = "Squid";
    ☃[95] = "Wolf";
    ☃[96] = "MushroomCow";
    ☃[97] = "SnowMan";
    ☃[98] = "Ozelot";
    ☃[99] = "VillagerGolem";
    ☃[100] = "EntityHorse";
    ☃[101] = "Rabbit";
    
    ☃[120] = "Villager";
    
    ☃['È'] = "EnderCrystal";
  }
  
  public int a()
  {
    return 105;
  }
  
  public dn a(dn ☃)
  {
    if ("minecraft:spawn_egg".equals(☃.l("id")))
    {
      dn ☃ = ☃.o("tag");
      dn ☃ = ☃.o("EntityTag");
      short ☃ = ☃.g("Damage");
      if (!☃.b("id", 8))
      {
        String ☃ = a[(☃ & 0xFF)];
        if (☃ != null)
        {
          ☃.a("id", ☃);
          ☃.a("EntityTag", ☃);
          ☃.a("tag", ☃);
        }
      }
      if (☃ != 0) {
        ☃.a("Damage", (short)0);
      }
    }
    return ☃;
  }
}
