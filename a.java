import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum a
{
  private static final Map<String, a> w;
  private static final Pattern x;
  private final String y;
  private final char z;
  private final boolean A;
  private final String B;
  private final int C;
  
  static
  {
    w = Maps.newHashMap();
    x = Pattern.compile("(?i)" + String.valueOf('§') + "[0-9A-FK-OR]");
    for (a ☃ : values()) {
      w.put(c(☃.y), ☃);
    }
  }
  
  private static String c(String ☃)
  {
    return ☃.toLowerCase().replaceAll("[^a-z]", "");
  }
  
  private a(String ☃, char ☃, int ☃)
  {
    this(☃, ☃, false, ☃);
  }
  
  private a(String ☃, char ☃, boolean ☃)
  {
    this(☃, ☃, ☃, -1);
  }
  
  private a(String ☃, char ☃, boolean ☃, int ☃)
  {
    this.y = ☃;
    this.z = ☃;
    this.A = ☃;
    this.C = ☃;
    
    this.B = ("§" + ☃);
  }
  
  public int b()
  {
    return this.C;
  }
  
  public boolean c()
  {
    return this.A;
  }
  
  public boolean d()
  {
    return (!this.A) && (this != v);
  }
  
  public String e()
  {
    return name().toLowerCase();
  }
  
  public String toString()
  {
    return this.B;
  }
  
  public static String a(String ☃)
  {
    return ☃ == null ? null : x.matcher(☃).replaceAll("");
  }
  
  public static a b(String ☃)
  {
    if (☃ == null) {
      return null;
    }
    return (a)w.get(c(☃));
  }
  
  public static a a(int ☃)
  {
    if (☃ < 0) {
      return v;
    }
    for (a ☃ : values()) {
      if (☃.b() == ☃) {
        return ☃;
      }
    }
    return null;
  }
  
  public static Collection<String> a(boolean ☃, boolean ☃)
  {
    List<String> ☃ = Lists.newArrayList();
    for (a ☃ : values()) {
      if ((!☃.d()) || (☃)) {
        if ((!☃.c()) || (☃)) {
          ☃.add(☃.e());
        }
      }
    }
    return ☃;
  }
}
