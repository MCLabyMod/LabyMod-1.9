import com.google.common.collect.Maps;
import java.util.Map;

public enum et$a
{
  private static final Map<String, a> f;
  private final boolean g;
  private final String h;
  
  private et$a(String ☃, boolean ☃)
  {
    this.h = ☃;
    this.g = ☃;
  }
  
  public boolean a()
  {
    return this.g;
  }
  
  public String b()
  {
    return this.h;
  }
  
  static
  {
    f = Maps.newHashMap();
    for (a ☃ : values()) {
      f.put(☃.b(), ☃);
    }
  }
  
  public static a a(String ☃)
  {
    return (a)f.get(☃);
  }
}
