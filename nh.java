import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Set;

public enum nh
{
  private static final Map<String, nh> k;
  private final String l;
  
  private nh(String ☃)
  {
    this.l = ☃;
  }
  
  public String a()
  {
    return this.l;
  }
  
  static
  {
    k = Maps.newHashMap();
    for (nh ☃ : values())
    {
      if (k.containsKey(☃.a())) {
        throw new Error("Clash in Sound Category name pools! Cannot insert " + ☃);
      }
      k.put(☃.a(), ☃);
    }
  }
  
  public static nh a(String ☃)
  {
    return (nh)k.get(☃);
  }
  
  public static Set<String> b()
  {
    return k.keySet();
  }
}
