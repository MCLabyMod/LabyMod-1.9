import com.google.common.collect.Maps;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class asr
{
  private static final Map<File, asq> a = ;
  
  public static synchronized asq a(File ☃, int ☃, int ☃)
  {
    File ☃ = new File(☃, "region");
    File ☃ = new File(☃, "r." + (☃ >> 5) + "." + (☃ >> 5) + ".mca");
    
    asq ☃ = (asq)a.get(☃);
    if (☃ != null) {
      return ☃;
    }
    if (!☃.exists()) {
      ☃.mkdirs();
    }
    if (a.size() >= 256) {
      a();
    }
    asq ☃ = new asq(☃);
    a.put(☃, ☃);
    return ☃;
  }
  
  public static synchronized void a()
  {
    for (asq ☃ : a.values()) {
      try
      {
        if (☃ != null) {
          ☃.c();
        }
      }
      catch (IOException ☃)
      {
        ☃.printStackTrace();
      }
    }
    a.clear();
  }
  
  public static DataInputStream c(File ☃, int ☃, int ☃)
  {
    asq ☃ = a(☃, ☃, ☃);
    return ☃.a(☃ & 0x1F, ☃ & 0x1F);
  }
  
  public static DataOutputStream d(File ☃, int ☃, int ☃)
  {
    asq ☃ = a(☃, ☃, ☃);
    return ☃.b(☃ & 0x1F, ☃ & 0x1F);
  }
}
