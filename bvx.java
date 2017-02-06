import com.google.common.collect.ImmutableSet;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public class bvx
  implements bwi
{
  public static final Set<String> a = ImmutableSet.of("minecraft", "realms");
  private final bvv b;
  
  public bvx(bvv resourceIndexIn)
  {
    this.b = resourceIndexIn;
  }
  
  public InputStream a(kk location)
    throws IOException
  {
    InputStream inputstream = d(location);
    if (inputstream != null) {
      return inputstream;
    }
    InputStream inputstream1 = c(location);
    if (inputstream1 != null) {
      return inputstream1;
    }
    throw new FileNotFoundException(location.a());
  }
  
  public InputStream c(kk location)
    throws IOException, FileNotFoundException
  {
    File file1 = this.b.a(location);
    return (file1 != null) && (file1.isFile()) ? new FileInputStream(file1) : null;
  }
  
  private InputStream d(kk location)
  {
    InputStream is = Config.class.getResourceAsStream("/assets/replace/" + location.b() + "/" + location.a());
    if (is != null) {
      return is;
    }
    return bvx.class.getResourceAsStream("/assets/" + location.b() + "/" + location.a());
  }
  
  public boolean b(kk location)
  {
    return (d(location) != null) || (this.b.b(location));
  }
  
  public Set<String> c()
  {
    return a;
  }
  
  public <T extends bwu> T a(bww metadataSerializer, String metadataSectionName)
    throws IOException
  {
    try
    {
      InputStream inputstream = new FileInputStream(this.b.a());
      return bvu.a(metadataSerializer, inputstream, metadataSectionName);
    }
    catch (RuntimeException var4)
    {
      return (bwu)null;
    }
    catch (FileNotFoundException var5) {}
    return (bwu)null;
  }
  
  public BufferedImage a()
    throws IOException
  {
    return bvk.a(bvx.class.getResourceAsStream("/" + new kk("pack.png").a()));
  }
  
  public String b()
  {
    return "Default";
  }
}
