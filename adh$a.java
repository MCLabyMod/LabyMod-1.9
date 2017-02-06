import com.google.common.collect.Maps;
import java.util.Map;

public enum adh$a
{
  private static final Map<Integer, a> e;
  private final int f;
  private final String g;
  private final int h;
  private final float i;
  private final int j;
  private final float k;
  private boolean l = false;
  
  static
  {
    e = Maps.newHashMap();
    for (a ☃ : values()) {
      e.put(Integer.valueOf(☃.a()), ☃);
    }
  }
  
  private adh$a(int ☃, String ☃, int ☃, float ☃, int ☃, float ☃)
  {
    this.f = ☃;
    this.g = ☃;
    this.h = ☃;
    this.i = ☃;
    this.j = ☃;
    this.k = ☃;
    this.l = true;
  }
  
  private adh$a(int ☃, String ☃, int ☃, float ☃)
  {
    this.f = ☃;
    this.g = ☃;
    this.h = ☃;
    this.i = ☃;
    this.j = 0;
    this.k = 0.0F;
    this.l = false;
  }
  
  public int a()
  {
    return this.f;
  }
  
  public String b()
  {
    return this.g;
  }
  
  public int c()
  {
    return this.h;
  }
  
  public float d()
  {
    return this.i;
  }
  
  public int e()
  {
    return this.j;
  }
  
  public float f()
  {
    return this.k;
  }
  
  public boolean g()
  {
    return this.l;
  }
  
  public static a a(int ☃)
  {
    a ☃ = (a)e.get(Integer.valueOf(☃));
    if (☃ == null) {
      return a;
    }
    return ☃;
  }
  
  public static a a(adq ☃)
  {
    if ((☃.b() instanceof adh)) {
      return a(☃.i());
    }
    return a;
  }
}
