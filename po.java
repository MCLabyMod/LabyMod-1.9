import com.google.common.collect.Lists;
import java.util.List;

public class po
  implements ox
{
  private static final List<String> a = Lists.newArrayList(new String[] { "MinecartRideable", "MinecartChest", "MinecartFurnace", "MinecartTNT", "MinecartSpawner", "MinecartHopper", "MinecartCommandBlock" });
  
  public int a()
  {
    return 106;
  }
  
  public dn a(dn ☃)
  {
    if ("Minecart".equals(☃.l("id")))
    {
      String ☃ = "MinecartRideable";
      int ☃ = ☃.h("Type");
      if ((☃ > 0) && (☃ < a.size())) {
        ☃ = (String)a.get(☃);
      }
      ☃.a("id", ☃);
      ☃.q("Type");
    }
    return ☃;
  }
}
