import io.netty.util.internal.ThreadLocalRandom;
import java.util.UUID;
import org.apache.commons.lang3.Validate;

public class sn
{
  private final double a;
  private final int b;
  private final String c;
  private final UUID d;
  private boolean e = true;
  
  public sn(String ☃, double ☃, int ☃)
  {
    this(on.a(ThreadLocalRandom.current()), ☃, ☃, ☃);
  }
  
  public sn(UUID ☃, String ☃, double ☃, int ☃)
  {
    this.d = ☃;
    this.c = ☃;
    this.a = ☃;
    this.b = ☃;
    
    Validate.notEmpty(☃, "Modifier name cannot be empty", new Object[0]);
    Validate.inclusiveBetween(0L, 2L, ☃, "Invalid operation");
  }
  
  public UUID a()
  {
    return this.d;
  }
  
  public String b()
  {
    return this.c;
  }
  
  public int c()
  {
    return this.b;
  }
  
  public double d()
  {
    return this.a;
  }
  
  public boolean e()
  {
    return this.e;
  }
  
  public sn a(boolean ☃)
  {
    this.e = ☃;
    return this;
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if ((☃ == null) || (getClass() != ☃.getClass())) {
      return false;
    }
    sn ☃ = (sn)☃;
    if (this.d != null ? !this.d.equals(☃.d) : ☃.d != null) {
      return false;
    }
    return true;
  }
  
  public int hashCode()
  {
    return this.d != null ? this.d.hashCode() : 0;
  }
  
  public String toString()
  {
    return "AttributeModifier{amount=" + this.a + ", operation=" + this.b + ", name='" + this.c + '\'' + ", id=" + this.d + ", serialize=" + this.e + '}';
  }
}
