import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

public abstract class arm<T extends Comparable<T>>
  implements arr<T>
{
  private final Class<T> a;
  private final String b;
  
  protected arm(String ☃, Class<T> ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public String a()
  {
    return this.b;
  }
  
  public Class<T> b()
  {
    return this.a;
  }
  
  public String toString()
  {
    return Objects.toStringHelper(this).add("name", this.b).add("clazz", this.a).add("values", c()).toString();
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if ((☃ instanceof arm))
    {
      arm<?> ☃ = (arm)☃;
      
      return (this.a.equals(☃.a)) && (this.b.equals(☃.b));
    }
    return false;
  }
  
  public int hashCode()
  {
    return 31 * this.a.hashCode() + this.b.hashCode();
  }
}
