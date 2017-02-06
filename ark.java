import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

public class ark
  implements Predicate<arc>
{
  public static final Predicate<arc> a = new Predicate()
  {
    public boolean a(arc ☃)
    {
      return true;
    }
  };
  private final ard b;
  private final Map<arr<?>, Predicate<?>> c = Maps.newHashMap();
  
  private ark(ard ☃)
  {
    this.b = ☃;
  }
  
  public static ark a(ajt ☃)
  {
    return new ark(☃.t());
  }
  
  public boolean a(arc ☃)
  {
    if ((☃ == null) || (!☃.t().equals(this.b.c()))) {
      return false;
    }
    for (Map.Entry<arr<?>, Predicate<?>> ☃ : this.c.entrySet()) {
      if (!a(☃, (arr)☃.getKey(), (Predicate)☃.getValue())) {
        return false;
      }
    }
    return true;
  }
  
  protected <T extends Comparable<T>> boolean a(arc ☃, arr<T> ☃, Predicate<?> ☃)
  {
    return ☃.apply(☃.c(☃));
  }
  
  public <V extends Comparable<V>> ark a(arr<V> ☃, Predicate<? extends V> ☃)
  {
    if (!this.b.d().contains(☃)) {
      throw new IllegalArgumentException(this.b + " cannot support property " + ☃);
    }
    this.c.put(☃, ☃);
    return this;
  }
}
