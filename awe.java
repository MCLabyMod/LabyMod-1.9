import com.google.common.collect.Maps;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class awe
{
  private static final Logger a = ;
  private static Map<String, Class<? extends awh>> b = Maps.newHashMap();
  private static Map<Class<? extends awh>, String> c = Maps.newHashMap();
  private static Map<String, Class<? extends awg>> d = Maps.newHashMap();
  private static Map<Class<? extends awg>, String> e = Maps.newHashMap();
  
  private static void b(Class<? extends awh> ☃, String ☃)
  {
    b.put(☃, ☃);
    c.put(☃, ☃);
  }
  
  static void a(Class<? extends awg> ☃, String ☃)
  {
    d.put(☃, ☃);
    e.put(☃, ☃);
  }
  
  static
  {
    b(avu.class, "Mineshaft");
    b(awj.a.class, "Village");
    b(avv.a.class, "Fortress");
    b(awb.a.class, "Stronghold");
    b(avz.a.class, "Temple");
    b(avx.a.class, "Monument");
    b(avq.a.class, "EndCity");
    
    avt.a();
    awk.a();
    avw.a();
    awc.a();
    awa.a();
    avy.a();
    avr.a();
  }
  
  public static String a(awh ☃)
  {
    return (String)c.get(☃.getClass());
  }
  
  public static String a(awg ☃)
  {
    return (String)e.get(☃.getClass());
  }
  
  public static awh a(dn ☃, aht ☃)
  {
    awh ☃ = null;
    try
    {
      Class<? extends awh> ☃ = (Class)b.get(☃.l("id"));
      if (☃ != null) {
        ☃ = (awh)☃.newInstance();
      }
    }
    catch (Exception ☃)
    {
      a.warn("Failed Start with id " + ☃.l("id"));
      ☃.printStackTrace();
    }
    if (☃ != null) {
      ☃.a(☃, ☃);
    } else {
      a.warn("Skipping Structure with id " + ☃.l("id"));
    }
    return ☃;
  }
  
  public static awg b(dn ☃, aht ☃)
  {
    awg ☃ = null;
    try
    {
      Class<? extends awg> ☃ = (Class)d.get(☃.l("id"));
      if (☃ != null) {
        ☃ = (awg)☃.newInstance();
      }
    }
    catch (Exception ☃)
    {
      a.warn("Failed Piece with id " + ☃.l("id"));
      ☃.printStackTrace();
    }
    if (☃ != null) {
      ☃.a(☃, ☃);
    } else {
      a.warn("Skipping Piece with id " + ☃.l("id"));
    }
    return ☃;
  }
}
