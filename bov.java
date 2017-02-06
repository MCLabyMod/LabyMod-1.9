import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

public class bov
  implements bow
{
  private final Iterable<bow> c;
  
  public bov(Iterable<bow> ☃)
  {
    this.c = ☃;
  }
  
  public Predicate<arc> a(final ard ☃)
  {
    Predicates.and(Iterables.transform(this.c, new Function()
    {
      public Predicate<arc> a(bow ☃)
      {
        return ☃ == null ? null : ☃.a(☃);
      }
    }));
  }
}
