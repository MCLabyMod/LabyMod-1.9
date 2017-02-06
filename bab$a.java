import com.google.common.base.Charsets;
import com.google.common.cache.CacheLoader;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.logging.log4j.Logger;

class bab$a
  extends CacheLoader<kk, azy>
{
  private bab$a(bab parambab) {}
  
  public azy a(kk ☃)
    throws Exception
  {
    if (☃.a().contains("."))
    {
      bab.b().debug("Invalid loot table name '" + ☃ + "' (can't contain periods)");
      return azy.a;
    }
    azy ☃ = b(☃);
    if (☃ == null) {
      ☃ = c(☃);
    }
    if (☃ == null)
    {
      ☃ = azy.a;
      bab.b().warn("Couldn't find resource table {}", new Object[] { ☃ });
    }
    return ☃;
  }
  
  private azy b(kk ☃)
  {
    File ☃ = new File(new File(bab.a(this.a), ☃.b()), ☃.a() + ".json");
    if (☃.exists())
    {
      if (☃.isFile())
      {
        String ☃;
        try
        {
          ☃ = Files.toString(☃, Charsets.UTF_8);
        }
        catch (IOException ☃)
        {
          bab.b().warn("Couldn't load loot table " + ☃ + " from " + ☃, ☃);
          return azy.a;
        }
        try
        {
          return (azy)bab.c().fromJson(☃, azy.class);
        }
        catch (JsonParseException ☃)
        {
          bab.b().error("Couldn't load loot table " + ☃ + " from " + ☃, ☃);
          return azy.a;
        }
      }
      bab.b().warn("Expected to find loot table " + ☃ + " at " + ☃ + " but it was a folder.");
      return azy.a;
    }
    return null;
  }
  
  private azy c(kk ☃)
  {
    URL ☃ = bab.class.getResource("/assets/" + ☃.b() + "/loot_tables/" + ☃.a() + ".json");
    if (☃ != null)
    {
      String ☃;
      try
      {
        ☃ = Resources.toString(☃, Charsets.UTF_8);
      }
      catch (IOException ☃)
      {
        bab.b().warn("Couldn't load loot table " + ☃ + " from " + ☃, ☃);
        return azy.a;
      }
      try
      {
        return (azy)bab.c().fromJson(☃, azy.class);
      }
      catch (JsonParseException ☃)
      {
        bab.b().error("Couldn't load loot table " + ☃ + " from " + ☃, ☃);
        return azy.a;
      }
    }
    return null;
  }
}
