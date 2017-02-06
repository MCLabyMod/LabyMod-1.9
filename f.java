import io.netty.util.ResourceLeakDetector;
import io.netty.util.ResourceLeakDetector.Level;

public class f
{
  public static final ResourceLeakDetector.Level a = ResourceLeakDetector.Level.DISABLED;
  
  public static boolean a(char ☃)
  {
    return (☃ != '§') && (☃ >= ' ') && (☃ != '');
  }
  
  public static final char[] b = { '/', '\n', '\r', '\t', '\000', '\f', '`', '?', '*', '\\', '<', '>', '|', '"', ':' };
  
  public static String a(String ☃)
  {
    StringBuilder ☃ = new StringBuilder();
    for (char ☃ : ☃.toCharArray()) {
      if (a(☃)) {
        ☃.append(☃);
      }
    }
    return ☃.toString();
  }
  
  static
  {
    ResourceLeakDetector.setLevel(a);
  }
}
