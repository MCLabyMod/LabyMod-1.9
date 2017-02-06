import com.google.common.collect.Maps;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class pw
  implements pd
{
  private static final Logger a = ;
  private static final Map<String, String> b = Maps.newHashMap();
  
  static
  {
    Map<String, String> ☃ = b;
    
    ☃.put("minecraft:furnace", "Furnace");
    ☃.put("minecraft:lit_furnace", "Furnace");
    ☃.put("minecraft:chest", "Chest");
    ☃.put("minecraft:trapped_chest", "Chest");
    ☃.put("minecraft:ender_chest", "EnderChest");
    ☃.put("minecraft:jukebox", "RecordPlayer");
    ☃.put("minecraft:dispenser", "Trap");
    ☃.put("minecraft:dropper", "Dropper");
    ☃.put("minecraft:sign", "Sign");
    ☃.put("minecraft:mob_spawner", "MobSpawner");
    ☃.put("minecraft:noteblock", "Music");
    ☃.put("minecraft:brewing_stand", "Cauldron");
    ☃.put("minecraft:enhanting_table", "EnchantTable");
    ☃.put("minecraft:command_block", "CommandBlock");
    ☃.put("minecraft:beacon", "Beacon");
    ☃.put("minecraft:skull", "Skull");
    ☃.put("minecraft:daylight_detector", "DLDetector");
    ☃.put("minecraft:hopper", "Hopper");
    ☃.put("minecraft:banner", "Banner");
    ☃.put("minecraft:flower_pot", "FlowerPot");
    ☃.put("minecraft:repeating_command_block", "CommandBlock");
    ☃.put("minecraft:chain_command_block", "CommandBlock");
    
    ☃.put("minecraft:standing_sign", "Sign");
    ☃.put("minecraft:wall_sign", "Sign");
    ☃.put("minecraft:piston_head", "Piston");
    ☃.put("minecraft:daylight_detector_inverted", "DLDetector");
    ☃.put("minecraft:unpowered_comparator", "Comparator");
    ☃.put("minecraft:powered_comparator", "Comparator");
    ☃.put("minecraft:wall_banner", "Banner");
    ☃.put("minecraft:standing_banner", "Banner");
    ☃.put("minecraft:structure_block", "Structure");
    ☃.put("minecraft:end_portal", "Airportal");
    ☃.put("minecraft:end_gateway", "EndGateway");
    
    ☃.put("minecraft:shield", "Shield");
  }
  
  private static String a(String ☃)
  {
    return (String)b.get(new kk(☃).toString());
  }
  
  public dn a(pa ☃, dn ☃, int ☃)
  {
    if (!☃.b("tag", 10)) {
      return ☃;
    }
    dn ☃ = ☃.o("tag");
    if (☃.b("BlockEntityTag", 10))
    {
      dn ☃ = ☃.o("BlockEntityTag");
      
      String ☃ = ☃.l("id");
      String ☃ = a(☃);
      boolean ☃;
      boolean ☃;
      if (☃ == null)
      {
        a.warn("Unable to resolve BlockEntity for ItemInstance: {}", new Object[] { ☃ });
        ☃ = false;
      }
      else
      {
        ☃ = !☃.e("id");
        ☃.a("id", ☃);
      }
      ☃.a(oz.d, ☃, ☃);
      if (☃) {
        ☃.q("id");
      }
    }
    return ☃;
  }
}
