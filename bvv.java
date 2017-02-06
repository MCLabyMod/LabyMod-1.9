import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bvv
{
  private static final Logger a = ;
  private final Map<String, File> b = Maps.newHashMap();
  
  protected bvv() {}
  
  public bvv(File ☃, String ☃)
  {
    File ☃ = new File(☃, "objects");
    
    File ☃ = new File(☃, "indexes/" + ☃ + ".json");
    BufferedReader ☃ = null;
    try
    {
      ☃ = Files.newReader(☃, Charsets.UTF_8);
      JsonObject ☃ = new JsonParser().parse(☃).getAsJsonObject();
      JsonObject ☃ = od.a(☃, "objects", null);
      if (☃ != null) {
        for (Map.Entry<String, JsonElement> ☃ : ☃.entrySet())
        {
          JsonObject ☃ = (JsonObject)☃.getValue();
          
          String ☃ = (String)☃.getKey();
          String[] ☃ = ☃.split("/", 2);
          String ☃ = ☃[0] + ":" + ☃[1];
          
          String ☃ = od.h(☃, "hash");
          File ☃ = new File(☃, ☃.substring(0, 2) + "/" + ☃);
          
          this.b.put(☃, ☃);
        }
      }
    }
    catch (JsonParseException ☃)
    {
      a.error("Unable to parse resource index file: " + ☃);
    }
    catch (FileNotFoundException ☃)
    {
      a.error("Can't find the resource index file: " + ☃);
    }
    finally
    {
      IOUtils.closeQuietly(☃);
    }
  }
  
  public File a(kk ☃)
  {
    String ☃ = ☃.toString();
    return (File)this.b.get(☃);
  }
  
  public boolean b(kk ☃)
  {
    File ☃ = a(☃);
    return (☃ != null) && (☃.isFile());
  }
  
  public File a()
  {
    return (File)this.b.get("pack.mcmeta");
  }
}
