import com.google.common.collect.Maps;
import java.util.Map;

public class et
{
  private final et.a a;
  private final String b;
  
  public et(et.a ☃, String ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public et.a a()
  {
    return this.a;
  }
  
  public String b()
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
    et ☃ = (et)☃;
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
    return "ClickEvent{action=" + this.a + ", value='" + this.b + '\'' + '}';
  }
  
  public int hashCode()
  {
    int ☃ = this.a.hashCode();
    ☃ = 31 * ☃ + (this.b != null ? this.b.hashCode() : 0);
    return ☃;
  }
  
  public static enum a
  {
    private static final Map<String, a> f;
    private final boolean g;
    private final String h;
    
    private a(String ☃, boolean ☃)
    {
      this.h = ☃;
      this.g = ☃;
    }
    
    public boolean a()
    {
      return this.g;
    }
    
    public String b()
    {
      return this.h;
    }
    
    static
    {
      f = Maps.newHashMap();
      for (a ☃ : values()) {
        f.put(☃.b(), ☃);
      }
    }
    
    public static a a(String ☃)
    {
      return (a)f.get(☃);
    }
  }
}
