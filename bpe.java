import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Map.Entry;

public abstract class bpe
  implements bpi
{
  protected Map<arc, bxt> b = Maps.newLinkedHashMap();
  
  public String a(Map<arr<?>, Comparable<?>> ☃)
  {
    StringBuilder ☃ = new StringBuilder();
    for (Map.Entry<arr<?>, Comparable<?>> ☃ : ☃.entrySet())
    {
      if (☃.length() != 0) {
        ☃.append(",");
      }
      arr<?> ☃ = (arr)☃.getKey();
      ☃.append(☃.a());
      ☃.append("=");
      ☃.append(a(☃, (Comparable)☃.getValue()));
    }
    if (☃.length() == 0) {
      ☃.append("normal");
    }
    return ☃.toString();
  }
  
  private <T extends Comparable<T>> String a(arr<T> ☃, Comparable<?> ☃)
  {
    return ☃.a(☃);
  }
  
  public Map<arc, bxt> a(ajt ☃)
  {
    for (arc ☃ : ☃.t().a()) {
      this.b.put(☃, a(☃));
    }
    return this.b;
  }
  
  protected abstract bxt a(arc paramarc);
}
