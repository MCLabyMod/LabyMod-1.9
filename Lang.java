import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

public class Lang
{
  private static final Splitter splitter = Splitter.on('=').limit(2);
  private static final Pattern pattern = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
  
  public static void resourcesReloaded()
  {
    Map localeProperties = bwo.getLocaleProperties();
    
    List<String> listFiles = new ArrayList();
    String PREFIX = "optifine/lang/";
    String EN_US = "en_US";
    String SUFFIX = ".lang";
    listFiles.add(PREFIX + EN_US + SUFFIX);
    if (!Config.getGameSettings().aB.equals(EN_US)) {
      listFiles.add(PREFIX + Config.getGameSettings().aB + SUFFIX);
    }
    String[] files = (String[])listFiles.toArray(new String[listFiles.size()]);
    
    loadResources(Config.getDefaultResourcePack(), files, localeProperties);
    
    bwi[] resourcePacks = Config.getResourcePacks();
    for (int i = 0; i < resourcePacks.length; i++)
    {
      bwi rp = resourcePacks[i];
      loadResources(rp, files, localeProperties);
    }
  }
  
  private static void loadResources(bwi rp, String[] files, Map localeProperties)
  {
    try
    {
      for (int i = 0; i < files.length; i++)
      {
        String file = files[i];
        kk loc = new kk(file);
        if (rp.b(loc))
        {
          InputStream in = rp.a(loc);
          if (in != null) {
            loadLocaleData(in, localeProperties);
          }
        }
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public static void loadLocaleData(InputStream is, Map localeProperties)
    throws IOException
  {
    Iterator it = IOUtils.readLines(is, Charsets.UTF_8).iterator();
    while (it.hasNext())
    {
      String line = (String)it.next();
      if ((!line.isEmpty()) && (line.charAt(0) != '#'))
      {
        String[] parts = (String[])Iterables.toArray(splitter.split(line), String.class);
        if ((parts != null) && (parts.length == 2))
        {
          String key = parts[0];
          String value = pattern.matcher(parts[1]).replaceAll("%$1s");
          localeProperties.put(key, value);
        }
      }
    }
  }
  
  public static String get(String key)
  {
    return bwo.a(key, new Object[0]);
  }
  
  public static String get(String key, String def)
  {
    String str = bwo.a(key, new Object[0]);
    if ((str == null) || (str.equals(key))) {
      return def;
    }
    return str;
  }
  
  public static String getOn()
  {
    return bwo.a("options.on", new Object[0]);
  }
  
  public static String getOff()
  {
    return bwo.a("options.off", new Object[0]);
  }
  
  public static String getFast()
  {
    return bwo.a("options.graphics.fast", new Object[0]);
  }
  
  public static String getFancy()
  {
    return bwo.a("options.graphics.fancy", new Object[0]);
  }
  
  public static String getDefault()
  {
    return bwo.a("generator.default", new Object[0]);
  }
}
