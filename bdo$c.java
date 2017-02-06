import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class bdo$c
  extends bdo.f
{
  private final Predicate<String> a;
  
  public bdo$c(int ☃, String ☃, boolean ☃, Predicate<String> ☃)
  {
    super(☃, ☃, ☃);
    this.a = ((Predicate)Objects.firstNonNull(☃, Predicates.alwaysTrue()));
  }
  
  public Predicate<String> a()
  {
    return this.a;
  }
}
