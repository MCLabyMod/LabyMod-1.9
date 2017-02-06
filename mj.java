import com.google.gson.JsonObject;
import java.io.File;
import java.net.SocketAddress;

public class mj
  extends mq<String, mk>
{
  public mj(File ☃)
  {
    super(☃);
  }
  
  protected mp<String> a(JsonObject ☃)
  {
    return new mk(☃);
  }
  
  public boolean a(SocketAddress ☃)
  {
    String ☃ = c(☃);
    return d(☃);
  }
  
  public mk b(SocketAddress ☃)
  {
    String ☃ = c(☃);
    return (mk)b(☃);
  }
  
  private String c(SocketAddress ☃)
  {
    String ☃ = ☃.toString();
    if (☃.contains("/")) {
      ☃ = ☃.substring(☃.indexOf('/') + 1);
    }
    if (☃.contains(":")) {
      ☃ = ☃.substring(0, ☃.indexOf(':'));
    }
    return ☃;
  }
}
