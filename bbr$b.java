import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Set;

public enum bbr$b
{
  private static Map<String, b> g;
  public final String e;
  public final int f;
  
  static
  {
    g = Maps.newHashMap();
    for (b ☃ : values()) {
      g.put(☃.e, ☃);
    }
  }
  
  public static String[] a()
  {
    return (String[])g.keySet().toArray(new String[g.size()]);
  }
  
  public static b a(String ☃)
  {
    return (b)g.get(☃);
  }
  
  private bbr$b(String ☃, int ☃)
  {
    this.e = ☃;
    this.f = ☃;
  }
}
