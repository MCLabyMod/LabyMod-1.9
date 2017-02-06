import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;

public class arp<T extends Enum<T>,  extends or>
  extends arm<T>
{
  private final ImmutableSet<T> a;
  private final Map<String, T> b = Maps.newHashMap();
  
  protected arp(String ☃, Class<T> ☃, Collection<T> ☃)
  {
    super(☃, ☃);
    this.a = ImmutableSet.copyOf(☃);
    for (T ☃ : ☃)
    {
      String ☃ = ((or)☃).m();
      if (this.b.containsKey(☃)) {
        throw new IllegalArgumentException("Multiple values have the same name '" + ☃ + "'");
      }
      this.b.put(☃, ☃);
    }
  }
  
  public Collection<T> c()
  {
    return this.a;
  }
  
  public Optional<T> b(String ☃)
  {
    return Optional.fromNullable(this.b.get(☃));
  }
  
  public String a(T ☃)
  {
    return ((or)☃).m();
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if (((☃ instanceof arp)) && (super.equals(☃)))
    {
      arp<?> ☃ = (arp)☃;
      return (this.a.equals(☃.a)) && (this.b.equals(☃.b));
    }
    return false;
  }
  
  public int hashCode()
  {
    int ☃ = super.hashCode();
    ☃ = 31 * ☃ + this.a.hashCode();
    ☃ = 31 * ☃ + this.b.hashCode();
    return ☃;
  }
  
  public static <T extends Enum<T>,  extends or> arp<T> a(String ☃, Class<T> ☃)
  {
    return a(☃, ☃, Predicates.alwaysTrue());
  }
  
  public static <T extends Enum<T>,  extends or> arp<T> a(String ☃, Class<T> ☃, Predicate<T> ☃)
  {
    return a(☃, ☃, Collections2.filter(Lists.newArrayList(☃.getEnumConstants()), ☃));
  }
  
  public static <T extends Enum<T>,  extends or> arp<T> a(String ☃, Class<T> ☃, T... ☃)
  {
    return a(☃, ☃, Lists.newArrayList(☃));
  }
  
  public static <T extends Enum<T>,  extends or> arp<T> a(String ☃, Class<T> ☃, Collection<T> ☃)
  {
    return new arp(☃, ☃, ☃);
  }
}
