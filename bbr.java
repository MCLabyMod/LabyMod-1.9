import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public abstract class bbr
{
  public boolean a(bbr ☃)
  {
    if (☃ == null) {
      return false;
    }
    if (this == ☃) {
      return true;
    }
    return false;
  }
  
  public abstract String b();
  
  public abstract String d(String paramString);
  
  public abstract boolean h();
  
  public abstract boolean g();
  
  public abstract bbr.b i();
  
  public abstract a m();
  
  public abstract Collection<String> d();
  
  public abstract bbr.b j();
  
  public abstract bbr.a k();
  
  public static enum b
  {
    private static Map<String, b> g;
    public final String e;
    public final int f;
    
    static
    {
      g = Maps.newHashMap();
      for (b ☃ : values()) {
        g.put(☃.e, ☃);
      }
    }
    
    public static String[] a()
    {
      return (String[])g.keySet().toArray(new String[g.size()]);
    }
    
    public static b a(String ☃)
    {
      return (b)g.get(☃);
    }
    
    private b(String ☃, int ☃)
    {
      this.e = ☃;
      this.f = ☃;
    }
  }
  
  public static enum a
  {
    private static Map<String, a> g;
    public final String e;
    public final int f;
    
    static
    {
      g = Maps.newHashMap();
      for (a ☃ : values()) {
        g.put(☃.e, ☃);
      }
    }
    
    public static String[] a()
    {
      return (String[])g.keySet().toArray(new String[g.size()]);
    }
    
    public static a a(String ☃)
    {
      return (a)g.get(☃);
    }
    
    private a(String ☃, int ☃)
    {
      this.e = ☃;
      this.f = ☃;
    }
  }
}
