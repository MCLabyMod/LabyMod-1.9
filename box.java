import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import java.util.List;

public class box
  implements bow
{
  private static final Splitter c = Splitter.on('|').omitEmptyStrings();
  private final String d;
  private final String e;
  
  public box(String ☃, String ☃)
  {
    this.d = ☃;
    this.e = ☃;
  }
  
  public Predicate<arc> a(ard ☃)
  {
    final arr<?> ☃ = ☃.a(this.d);
    if (☃ == null) {
      throw new RuntimeException(toString() + ": Definition: " + ☃ + " has no property: " + this.d);
    }
    String ☃ = this.e;
    boolean ☃ = (!☃.isEmpty()) && (☃.charAt(0) == '!');
    if (☃) {
      ☃ = ☃.substring(1);
    }
    List<String> ☃ = c.splitToList(☃);
    if (☃.isEmpty()) {
      throw new RuntimeException(toString() + ": has an empty value: " + this.e);
    }
    Predicate<arc> ☃;
    Predicate<arc> ☃;
    if (☃.size() == 1) {
      ☃ = a(☃, ☃);
    } else {
      ☃ = Predicates.or(Iterables.transform(☃, new Function()
      {
        public Predicate<arc> a(String ☃)
        {
          return box.a(box.this, ☃, ☃);
        }
      }));
    }
    return ☃ ? Predicates.not(☃) : ☃;
  }
  
  private Predicate<arc> a(final arr<?> ☃, String ☃)
  {
    final Optional<?> ☃ = ☃.b(☃);
    if (!☃.isPresent()) {
      throw new RuntimeException(toString() + ": has an unknown value: " + this.e);
    }
    new Predicate()
    {
      public boolean a(arc ☃)
      {
        return (☃ != null) && (☃.c(☃).equals(☃.get()));
      }
    };
  }
  
  public String toString()
  {
    return Objects.toStringHelper(this).add("key", this.d).add("value", this.e).toString();
  }
}
