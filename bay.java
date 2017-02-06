import com.google.common.collect.Maps;
import java.util.Map;

public class bay
{
  private static final Map<kk, baz.a<?>> a = ;
  private static final Map<Class<? extends baz>, baz.a<?>> b = Maps.newHashMap();
  
  static
  {
    a(new bba.a());
  }
  
  public static <T extends baz> void a(baz.a<? extends T> ☃)
  {
    kk ☃ = ☃.a();
    Class<T> ☃ = ☃.b();
    if (a.containsKey(☃)) {
      throw new IllegalArgumentException("Can't re-register entity property name " + ☃);
    }
    if (b.containsKey(☃)) {
      throw new IllegalArgumentException("Can't re-register entity property class " + ☃.getName());
    }
    a.put(☃, ☃);
    b.put(☃, ☃);
  }
  
  public static baz.a<?> a(kk ☃)
  {
    baz.a<?> ☃ = (baz.a)a.get(☃);
    if (☃ == null) {
      throw new IllegalArgumentException("Unknown loot entity property '" + ☃ + "'");
    }
    return ☃;
  }
  
  public static <T extends baz> baz.a<T> a(T ☃)
  {
    baz.a<?> ☃ = (baz.a)b.get(☃.getClass());
    if (☃ == null) {
      throw new IllegalArgumentException("Unknown loot entity property " + ☃);
    }
    return ☃;
  }
}
