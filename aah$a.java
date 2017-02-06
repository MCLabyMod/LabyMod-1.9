import com.google.common.collect.Maps;
import java.util.Map;

public enum aah$a
{
  private static final Map<Integer, a> h;
  private final int i;
  private final String j;
  
  static
  {
    h = Maps.newHashMap();
    for (a ☃ : values()) {
      h.put(Integer.valueOf(☃.a()), ☃);
    }
  }
  
  private aah$a(int ☃, String ☃)
  {
    this.i = ☃;
    this.j = ☃;
  }
  
  public int a()
  {
    return this.i;
  }
  
  public String b()
  {
    return this.j;
  }
  
  public static a a(int ☃)
  {
    a ☃ = (a)h.get(Integer.valueOf(☃));
    return ☃ == null ? a : ☃;
  }
}
