import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Set;

public enum cy
{
  private final String U;
  private final int V;
  private final boolean W;
  private final int X;
  private static final Map<Integer, cy> Y;
  private static final Map<String, cy> Z;
  
  static
  {
    Y = Maps.newHashMap();
    Z = Maps.newHashMap();
    for (cy ☃ : values())
    {
      Y.put(Integer.valueOf(☃.c()), ☃);
      Z.put(☃.b(), ☃);
    }
  }
  
  private cy(String ☃, int ☃, boolean ☃, int ☃)
  {
    this.U = ☃;
    this.V = ☃;
    this.W = ☃;
    this.X = ☃;
  }
  
  private cy(String ☃, int ☃, boolean ☃)
  {
    this(☃, ☃, ☃, 0);
  }
  
  public static Set<String> a()
  {
    return Z.keySet();
  }
  
  public String b()
  {
    return this.U;
  }
  
  public int c()
  {
    return this.V;
  }
  
  public int d()
  {
    return this.X;
  }
  
  public boolean e()
  {
    return this.W;
  }
  
  public static cy a(int ☃)
  {
    return (cy)Y.get(Integer.valueOf(☃));
  }
  
  public static cy a(String ☃)
  {
    return (cy)Z.get(☃);
  }
}
