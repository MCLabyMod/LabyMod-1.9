import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

public class boz
  implements bow
{
  final Iterable<bow> c;
  
  public boz(Iterable<bow> ☃)
  {
    this.c = ☃;
  }
  
  public Predicate<arc> a(final ard ☃)
  {
    Predicates.or(Iterables.transform(this.c, new Function()
    {
      public Predicate<arc> a(bow ☃)
      {
        return ☃ == null ? null : ☃.a(☃);
      }
    }));
  }
}
