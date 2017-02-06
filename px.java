import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class px
  implements pd
{
  private static final Logger a = ;
  
  public dn a(pa ☃, dn ☃, int ☃)
  {
    dn ☃ = ☃.o("tag");
    if (☃.b("EntityTag", 10))
    {
      dn ☃ = ☃.o("EntityTag");
      
      String ☃ = ☃.l("id");
      String ☃;
      if ("minecraft:armor_stand".equals(☃))
      {
        ☃ = "ArmorStand";
      }
      else
      {
        String ☃;
        if ("minecraft:spawn_egg".equals(☃)) {
          ☃ = ☃.l("id");
        } else {
          return ☃;
        }
      }
      String ☃;
      boolean ☃;
      boolean ☃;
      if (☃ == null)
      {
        a.warn("Unable to resolve Entity for ItemInstance: {}", new Object[] { ☃ });
        ☃ = false;
      }
      else
      {
        ☃ = !☃.b("id", 8);
        ☃.a("id", ☃);
      }
      ☃.a(oz.e, ☃, ☃);
      if (☃) {
        ☃.q("id");
      }
    }
    return ☃;
  }
}
