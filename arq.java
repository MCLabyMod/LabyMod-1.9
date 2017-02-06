import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;

public class arq
  extends arm<Integer>
{
  private final ImmutableSet<Integer> a;
  
  protected arq(String ☃, int ☃, int ☃)
  {
    super(☃, Integer.class);
    if (☃ < 0) {
      throw new IllegalArgumentException("Min value of " + ☃ + " must be 0 or greater");
    }
    if (☃ <= ☃) {
      throw new IllegalArgumentException("Max value of " + ☃ + " must be greater than min (" + ☃ + ")");
    }
    Set<Integer> ☃ = Sets.newHashSet();
    for (int ☃ = ☃; ☃ <= ☃; ☃++) {
      ☃.add(Integer.valueOf(☃));
    }
    this.a = ImmutableSet.copyOf(☃);
  }
  
  public Collection<Integer> c()
  {
    return this.a;
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if (((☃ instanceof arq)) && (super.equals(☃)))
    {
      arq ☃ = (arq)☃;
      
      return this.a.equals(☃.a);
    }
    return false;
  }
  
  public int hashCode()
  {
    return 31 * super.hashCode() + this.a.hashCode();
  }
  
  public static arq a(String ☃, int ☃, int ☃)
  {
    return new arq(☃, ☃, ☃);
  }
  
  public Optional<Integer> b(String ☃)
  {
    try
    {
      Integer ☃ = Integer.valueOf(☃);
      
      return this.a.contains(☃) ? Optional.of(☃) : Optional.absent();
    }
    catch (NumberFormatException ☃) {}
    return Optional.absent();
  }
  
  public String a(Integer ☃)
  {
    return ☃.toString();
  }
}
