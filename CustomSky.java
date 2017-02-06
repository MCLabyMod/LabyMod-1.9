import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CustomSky
{
  private static CustomSkyLayer[][] worldSkyLayers = (CustomSkyLayer[][])null;
  
  public static void reset()
  {
    worldSkyLayers = (CustomSkyLayer[][])null;
  }
  
  public static void update()
  {
    
    if (!Config.isCustomSky()) {
      return;
    }
    worldSkyLayers = readCustomSkies();
  }
  
  private static CustomSkyLayer[][] readCustomSkies()
  {
    CustomSkyLayer[][] wsls = new CustomSkyLayer[10][0];
    String prefix = "mcpatcher/sky/world";
    int lastWorldId = -1;
    for (int w = 0; w < wsls.length; w++)
    {
      String worldPrefix = prefix + w + "/sky";
      List listSkyLayers = new ArrayList();
      for (int i = 1; i < 1000; i++)
      {
        String path = worldPrefix + i + ".properties";
        try
        {
          kk locPath = new kk(path);
          InputStream in = Config.getResourceStream(locPath);
          if (in == null) {
            break;
          }
          Properties props = new Properties();
          props.load(in);
          in.close();
          
          Config.dbg("CustomSky properties: " + path);
          
          String defSource = worldPrefix + i + ".png";
          CustomSkyLayer sl = new CustomSkyLayer(props, defSource);
          if (sl.isValid(path))
          {
            kk locSource = new kk(sl.source);
            bvj tex = TextureUtils.getTexture(locSource);
            if (tex == null)
            {
              Config.log("CustomSky: Texture not found: " + locSource);
            }
            else
            {
              sl.textureId = tex.b();
              
              listSkyLayers.add(sl);
              
              in.close();
            }
          }
        }
        catch (FileNotFoundException e)
        {
          break;
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
      if (listSkyLayers.size() > 0)
      {
        CustomSkyLayer[] sls = (CustomSkyLayer[])listSkyLayers.toArray(new CustomSkyLayer[listSkyLayers.size()]);
        
        wsls[w] = sls;
        
        lastWorldId = w;
      }
    }
    if (lastWorldId < 0) {
      return (CustomSkyLayer[][])null;
    }
    int worldCount = lastWorldId + 1;
    CustomSkyLayer[][] wslsTrim = new CustomSkyLayer[worldCount][0];
    for (int i = 0; i < wslsTrim.length; i++) {
      wslsTrim[i] = wsls[i];
    }
    return wslsTrim;
  }
  
  public static void renderSky(aht world, bvi re, float celestialAngle, float rainBrightness)
  {
    if (worldSkyLayers == null) {
      return;
    }
    if (Config.getGameSettings().c < 8) {
      return;
    }
    int dimId = world.s.p().a();
    if ((dimId < 0) || (dimId >= worldSkyLayers.length)) {
      return;
    }
    CustomSkyLayer[] sls = worldSkyLayers[dimId];
    if (sls == null) {
      return;
    }
    long time = world.Q();
    int timeOfDay = (int)(time % 24000L);
    for (int i = 0; i < sls.length; i++)
    {
      CustomSkyLayer sl = sls[i];
      if (sl.isActive(world, timeOfDay)) {
        sl.render(timeOfDay, celestialAngle, rainBrightness);
      }
    }
    Blender.clearBlend(rainBrightness);
  }
  
  public static boolean hasSkyLayers(aht world)
  {
    if (worldSkyLayers == null) {
      return false;
    }
    if (Config.getGameSettings().c < 8) {
      return false;
    }
    int dimId = world.s.p().a();
    if ((dimId < 0) || (dimId >= worldSkyLayers.length)) {
      return false;
    }
    CustomSkyLayer[] sls = worldSkyLayers[dimId];
    if (sls == null) {
      return false;
    }
    return sls.length > 0;
  }
}
