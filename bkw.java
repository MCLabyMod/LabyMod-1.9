import java.net.IDN;
import java.util.Hashtable;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class bkw
{
  private final String a;
  private final int b;
  
  private bkw(String ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public String a()
  {
    try
    {
      return IDN.toASCII(this.a);
    }
    catch (IllegalArgumentException ☃) {}
    return "";
  }
  
  public int b()
  {
    return this.b;
  }
  
  public static bkw a(String ☃)
  {
    if (☃ == null) {
      return null;
    }
    String[] ☃ = ☃.split(":");
    if (☃.startsWith("["))
    {
      int ☃ = ☃.indexOf("]");
      if (☃ > 0)
      {
        String ☃ = ☃.substring(1, ☃);
        String ☃ = ☃.substring(☃ + 1).trim();
        if ((☃.startsWith(":")) && (!☃.isEmpty()))
        {
          ☃ = ☃.substring(1);
          ☃ = new String[2];
          ☃[0] = ☃;
          ☃[1] = ☃;
        }
        else
        {
          ☃ = new String[1];
          ☃[0] = ☃;
        }
      }
    }
    if (☃.length > 2)
    {
      ☃ = new String[1];
      ☃[0] = ☃;
    }
    String ☃ = ☃[0];
    int ☃ = ☃.length > 1 ? a(☃[1], 25565) : 25565;
    if (☃ == 25565)
    {
      String[] ☃ = b(☃);
      ☃ = ☃[0];
      ☃ = a(☃[1], 25565);
    }
    return new bkw(☃, ☃);
  }
  
  private static String[] b(String ☃)
  {
    try
    {
      String ☃ = "com.sun.jndi.dns.DnsContextFactory";
      
      Class.forName("com.sun.jndi.dns.DnsContextFactory");
      
      Hashtable<String, String> ☃ = new Hashtable();
      ☃.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
      ☃.put("java.naming.provider.url", "dns:");
      ☃.put("com.sun.jndi.dns.timeout.retries", "1");
      DirContext ☃ = new InitialDirContext(☃);
      Attributes ☃ = ☃.getAttributes("_minecraft._tcp." + ☃, new String[] { "SRV" });
      String[] ☃ = ☃.get("srv").get().toString().split(" ", 4);
      return new String[] { ☃[3], ☃[2] };
    }
    catch (Throwable ☃) {}
    return tmp142_138;
  }
  
  private static int a(String ☃, int ☃)
  {
    try
    {
      return Integer.parseInt(☃.trim());
    }
    catch (Exception localException) {}
    return ☃;
  }
}
