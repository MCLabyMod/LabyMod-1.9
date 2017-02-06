import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import java.util.Collection;

public class arn
  extends arm<Boolean>
{
  private final ImmutableSet<Boolean> a;
  
  protected arn(String ☃)
  {
    super(☃, Boolean.class);
    this.a = ImmutableSet.of(Boolean.valueOf(true), Boolean.valueOf(false));
  }
  
  public Collection<Boolean> c()
  {
    return this.a;
  }
  
  public static arn a(String ☃)
  {
    return new arn(☃);
  }
  
  public Optional<Boolean> b(String ☃)
  {
    if (("true".equals(☃)) || ("false".equals(☃))) {
      return Optional.of(Boolean.valueOf(☃));
    }
    return Optional.absent();
  }
  
  public String a(Boolean ☃)
  {
    return ☃.toString();
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if (((☃ instanceof arn)) && (super.equals(☃)))
    {
      arn ☃ = (arn)☃;
      
      return this.a.equals(☃.a);
    }
    return false;
  }
  
  public int hashCode()
  {
    return 31 * super.hashCode() + this.a.hashCode();
  }
}
