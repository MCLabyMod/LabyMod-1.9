import com.google.common.base.Charsets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class bvu
  implements bwi
{
  private static final Logger b = ;
  public final File a;
  
  public bvu(File resourcePackFileIn)
  {
    this.a = resourcePackFileIn;
  }
  
  private static String c(kk location)
  {
    return String.format("%s/%s/%s", new Object[] { "assets", location.b(), location.a() });
  }
  
  protected static String a(File p_110595_0_, File p_110595_1_)
  {
    return p_110595_0_.toURI().relativize(p_110595_1_.toURI()).getPath();
  }
  
  public InputStream a(kk location)
    throws IOException
  {
    return a(c(location));
  }
  
  public boolean b(kk location)
  {
    return b(c(location));
  }
  
  protected abstract InputStream a(String paramString)
    throws IOException;
  
  protected abstract boolean b(String paramString);
  
  protected void c(String name)
  {
    b.warn("ResourcePack: ignored non-lowercase namespace: {} in {}", new Object[] { name, this.a });
  }
  
  public <T extends bwu> T a(bww metadataSerializer, String metadataSectionName)
    throws IOException
  {
    return a(metadataSerializer, a("pack.mcmeta"), metadataSectionName);
  }
  
  static <T extends bwu> T a(bww metadataSerializer, InputStream p_110596_1_, String sectionName)
  {
    JsonObject jsonobject = null;
    BufferedReader bufferedreader = null;
    try
    {
      bufferedreader = new BufferedReader(new InputStreamReader(p_110596_1_, Charsets.UTF_8));
      jsonobject = new JsonParser().parse(bufferedreader).getAsJsonObject();
    }
    catch (RuntimeException runtimeexception)
    {
      throw new JsonParseException(runtimeexception);
    }
    finally
    {
      IOUtils.closeQuietly(bufferedreader);
    }
    return metadataSerializer.a(sectionName, jsonobject);
  }
  
  public BufferedImage a()
    throws IOException
  {
    return bvk.a(a("pack.png"));
  }
  
  public String b()
  {
    return this.a.getName();
  }
}
