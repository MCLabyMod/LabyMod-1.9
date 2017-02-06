import com.google.common.base.Predicate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;

final class o$10
  implements Predicate<rr>
{
  o$10(m paramm, Map paramMap) {}
  
  public boolean a(rr ☃)
  {
    if (☃ == null) {
      return false;
    }
    bbp ☃ = this.a.h().a(0).ad();
    for (Map.Entry<String, Integer> ☃ : this.b.entrySet())
    {
      String ☃ = (String)☃.getKey();
      boolean ☃ = false;
      if ((☃.endsWith("_min")) && (☃.length() > 4))
      {
        ☃ = true;
        ☃ = ☃.substring(0, ☃.length() - 4);
      }
      bbl ☃ = ☃.b(☃);
      if (☃ == null) {
        return false;
      }
      String ☃ = (☃ instanceof lr) ? ☃.h_() : ☃.bc().toString();
      if (!☃.b(☃, ☃)) {
        return false;
      }
      bbn ☃ = ☃.c(☃, ☃);
      int ☃ = ☃.c();
      if ((☃ < ((Integer)☃.getValue()).intValue()) && (☃)) {
        return false;
      }
      if ((☃ > ((Integer)☃.getValue()).intValue()) && (!☃)) {
        return false;
      }
    }
    return true;
  }
}
