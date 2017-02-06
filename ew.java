import com.google.common.collect.Maps;
import java.util.Map;

public class ew
{
  private final ew.a a;
  private final eu b;
  
  public ew(ew.a ☃, eu ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public ew.a a()
  {
    return this.a;
  }
  
  public eu b()
  {
    return this.b;
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if ((☃ == null) || (getClass() != ☃.getClass())) {
      return false;
    }
    ew ☃ = (ew)☃;
    if (this.a != ☃.a) {
      return false;
    }
    if (this.b != null ? !this.b.equals(☃.b) : ☃.b != null) {
      return false;
    }
    return true;
  }
  
  public String toString()
  {
    return "HoverEvent{action=" + this.a + ", value='" + this.b + '\'' + '}';
  }
  
  public int hashCode()
  {
    int ☃ = this.a.hashCode();
    ☃ = 31 * ☃ + (this.b != null ? this.b.hashCode() : 0);
    return ☃;
  }
  
  public static enum a
  {
    private static final Map<String, a> e;
    private final boolean f;
    private final String g;
    
    private a(String ☃, boolean ☃)
    {
      this.g = ☃;
      this.f = ☃;
    }
    
    public boolean a()
    {
      return this.f;
    }
    
    public String b()
    {
      return this.g;
    }
    
    static
    {
      e = Maps.newHashMap();
      for (a ☃ : values()) {
        e.put(☃.b(), ☃);
      }
    }
    
    public static a a(String ☃)
    {
      return (a)e.get(☃);
    }
  }
}
