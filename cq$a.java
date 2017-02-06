import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import java.util.Map;

public enum cq$a
  implements Predicate<cq>, or
{
  private static final Map<String, a> d;
  private final String e;
  private final cq.c f;
  
  private cq$a(String name, cq.c plane)
  {
    this.e = name;
    this.f = plane;
  }
  
  public static a a(String name)
  {
    return name == null ? null : (a)d.get(name.toLowerCase());
  }
  
  public String a()
  {
    return this.e;
  }
  
  public boolean b()
  {
    return this.f == cq.c.b;
  }
  
  public boolean c()
  {
    return this.f == cq.c.a;
  }
  
  public String toString()
  {
    return this.e;
  }
  
  public boolean a(cq p_apply_1_)
  {
    return (p_apply_1_ != null) && (p_apply_1_.k() == this);
  }
  
  public cq.c d()
  {
    return this.f;
  }
  
  public String m()
  {
    return this.e;
  }
  
  static
  {
    d = Maps.newHashMap();
    for (a enumfacing$axis : values()) {
      d.put(enumfacing$axis.a().toLowerCase(), enumfacing$axis);
    }
  }
}
