import com.google.common.collect.Maps;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.io.IOUtils;

public class awm
{
  private final Map<String, awo> a = Maps.newHashMap();
  private final String b;
  
  public awm()
  {
    this("structures");
  }
  
  public awm(String ☃)
  {
    this.b = ☃;
  }
  
  public awo a(MinecraftServer ☃, kk ☃)
  {
    String ☃ = ☃.a();
    if (this.a.containsKey(☃)) {
      return (awo)this.a.get(☃);
    }
    if (☃ != null) {
      b(☃, ☃);
    } else {
      a(☃);
    }
    if (this.a.containsKey(☃)) {
      return (awo)this.a.get(☃);
    }
    awo ☃ = new awo();
    this.a.put(☃, ☃);
    return ☃;
  }
  
  public boolean b(MinecraftServer ☃, kk ☃)
  {
    String ☃ = ☃.a();
    File ☃ = ☃.d(this.b);
    File ☃ = new File(☃, ☃ + ".nbt");
    if (!☃.exists()) {
      return a(☃);
    }
    InputStream ☃ = null;
    try
    {
      ☃ = new FileInputStream(☃);
      a(☃, ☃);
    }
    catch (Throwable ☃)
    {
      return false;
    }
    finally
    {
      IOUtils.closeQuietly(☃);
    }
    return true;
  }
  
  private boolean a(kk ☃)
  {
    String ☃ = ☃.b();
    String ☃ = ☃.a();
    
    InputStream ☃ = null;
    try
    {
      ☃ = MinecraftServer.class.getResourceAsStream("/assets/" + ☃ + "/structures/" + ☃ + ".nbt");
      a(☃, ☃);
    }
    catch (Throwable ☃)
    {
      return false;
    }
    finally
    {
      IOUtils.closeQuietly(☃);
    }
    return true;
  }
  
  private void a(String ☃, InputStream ☃)
    throws IOException
  {
    dn ☃ = dx.a(☃);
    awo ☃ = new awo();
    ☃.b(☃);
    this.a.put(☃, ☃);
  }
  
  public boolean c(MinecraftServer ☃, kk ☃)
  {
    String ☃ = ☃.a();
    if (!this.a.containsKey(☃)) {
      return false;
    }
    File ☃ = ☃.d(this.b);
    if (!☃.exists())
    {
      if (!☃.mkdirs()) {
        return false;
      }
    }
    else if (!☃.isDirectory()) {
      return false;
    }
    File ☃ = new File(☃, ☃ + ".nbt");
    dn ☃ = new dn();
    awo ☃ = (awo)this.a.get(☃);
    OutputStream ☃ = null;
    try
    {
      ☃.a(☃);
      ☃ = new FileOutputStream(☃);
      dx.a(☃, ☃);
    }
    catch (Throwable ☃)
    {
      return false;
    }
    finally
    {
      IOUtils.closeQuietly(☃);
    }
    return true;
  }
}
