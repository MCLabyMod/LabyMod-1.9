import com.google.common.collect.Maps;
import java.util.Map;

public enum ew$a
{
  private static final Map<String, a> e;
  private final boolean f;
  private final String g;
  
  private ew$a(String ☃, boolean ☃)
  {
    this.g = ☃;
    this.f = ☃;
  }
  
  public boolean a()
  {
    return this.f;
  }
  
  public String b()
  {
    return this.g;
  }
  
  static
  {
    e = Maps.newHashMap();
    for (a ☃ : values()) {
      e.put(☃.b(), ☃);
    }
  }
  
  public static a a(String ☃)
  {
    return (a)e.get(☃);
  }
}
