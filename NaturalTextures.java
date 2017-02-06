import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class NaturalTextures
{
  private static NaturalProperties[] propertiesByIndex = new NaturalProperties[0];
  
  public static void update()
  {
    propertiesByIndex = new NaturalProperties[0];
    if (!Config.isNaturalTextures()) {
      return;
    }
    String fileName = "optifine/natural.properties";
    try
    {
      kk loc = new kk(fileName);
      if (!Config.hasResource(loc))
      {
        Config.dbg("NaturalTextures: configuration \"" + fileName + "\" not found");
        propertiesByIndex = makeDefaultProperties();
        return;
      }
      InputStream in = Config.getResourceStream(loc);
      ArrayList list = new ArrayList(256);
      String configStr = Config.readInputStream(in);
      in.close();
      String[] configLines = Config.tokenize(configStr, "\n\r");
      Config.dbg("Natural Textures: Parsing configuration \"" + fileName + "\"");
      bvg textureMapBlocks = TextureUtils.getTextureMapBlocks();
      for (int i = 0; i < configLines.length; i++)
      {
        String line = configLines[i].trim();
        if (!line.startsWith("#"))
        {
          String[] strs = Config.tokenize(line, "=");
          if (strs.length != 2)
          {
            Config.warn("Natural Textures: Invalid \"" + fileName + "\" line: " + line);
          }
          else
          {
            String key = strs[0].trim();
            String type = strs[1].trim();
            
            String texName = key;
            bvh ts = textureMapBlocks.getSpriteSafe("minecraft:blocks/" + texName);
            if (ts == null)
            {
              Config.warn("Natural Textures: Texture not found: \"" + fileName + "\" line: " + line);
            }
            else
            {
              int tileNum = ts.getIndexInMap();
              if (tileNum < 0)
              {
                Config.warn("Natural Textures: Invalid \"" + fileName + "\" line: " + line);
              }
              else
              {
                NaturalProperties props = new NaturalProperties(type);
                if (props.isValid())
                {
                  while (list.size() <= tileNum) {
                    list.add(null);
                  }
                  list.set(tileNum, props);
                  
                  Config.dbg("NaturalTextures: " + texName + " = " + type);
                }
              }
            }
          }
        }
      }
      propertiesByIndex = (NaturalProperties[])list.toArray(new NaturalProperties[list.size()]);
    }
    catch (FileNotFoundException e)
    {
      Config.warn("NaturalTextures: configuration \"" + fileName + "\" not found");
      propertiesByIndex = makeDefaultProperties();
      return;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public static bof getNaturalTexture(cj blockPosIn, bof quad)
  {
    bvh sprite = quad.a();
    if (sprite == null) {
      return quad;
    }
    NaturalProperties nps = getNaturalProperties(sprite);
    if (nps == null) {
      return quad;
    }
    int side = ConnectedTextures.getSide(quad.e());
    int rand = Config.getRandom(blockPosIn, side);
    
    int rotate = 0;
    boolean flipU = false;
    if (nps.rotation > 1) {
      rotate = rand & 0x3;
    }
    if (nps.rotation == 2) {
      rotate = rotate / 2 * 2;
    }
    if (nps.flip) {
      flipU = (rand & 0x4) != 0;
    }
    return nps.getQuad(quad, rotate, flipU);
  }
  
  public static NaturalProperties getNaturalProperties(bvh icon)
  {
    if (!(icon instanceof bvh)) {
      return null;
    }
    bvh ts = icon;
    
    int tileNum = ts.getIndexInMap();
    if ((tileNum < 0) || (tileNum >= propertiesByIndex.length)) {
      return null;
    }
    NaturalProperties props = propertiesByIndex[tileNum];
    
    return props;
  }
  
  private static NaturalProperties[] makeDefaultProperties()
  {
    Config.dbg("NaturalTextures: Creating default configuration.");
    
    List propsList = new ArrayList();
    
    setIconProperties(propsList, "coarse_dirt", "4F");
    
    setIconProperties(propsList, "grass_side", "F");
    setIconProperties(propsList, "grass_side_overlay", "F");
    
    setIconProperties(propsList, "stone_slab_top", "F");
    
    setIconProperties(propsList, "gravel", "2");
    
    setIconProperties(propsList, "log_oak", "2F");
    setIconProperties(propsList, "log_spruce", "2F");
    setIconProperties(propsList, "log_birch", "F");
    setIconProperties(propsList, "log_jungle", "2F");
    setIconProperties(propsList, "log_acacia", "2F");
    setIconProperties(propsList, "log_big_oak", "2F");
    
    setIconProperties(propsList, "log_oak_top", "4F");
    setIconProperties(propsList, "log_spruce_top", "4F");
    setIconProperties(propsList, "log_birch_top", "4F");
    setIconProperties(propsList, "log_jungle_top", "4F");
    setIconProperties(propsList, "log_acacia_top", "4F");
    setIconProperties(propsList, "log_big_oak_top", "4F");
    
    setIconProperties(propsList, "leaves_oak", "2F");
    setIconProperties(propsList, "leaves_spruce", "2F");
    setIconProperties(propsList, "leaves_birch", "2F");
    setIconProperties(propsList, "leaves_jungle", "2");
    setIconProperties(propsList, "leaves_big_oak", "2F");
    setIconProperties(propsList, "leaves_acacia", "2F");
    
    setIconProperties(propsList, "gold_ore", "2F");
    setIconProperties(propsList, "iron_ore", "2F");
    setIconProperties(propsList, "coal_ore", "2F");
    setIconProperties(propsList, "diamond_ore", "2F");
    setIconProperties(propsList, "redstone_ore", "2F");
    setIconProperties(propsList, "lapis_ore", "2F");
    
    setIconProperties(propsList, "obsidian", "4F");
    
    setIconProperties(propsList, "snow", "4F");
    setIconProperties(propsList, "grass_side_snowed", "F");
    
    setIconProperties(propsList, "cactus_side", "2F");
    
    setIconProperties(propsList, "clay", "4F");
    
    setIconProperties(propsList, "mycelium_side", "F");
    setIconProperties(propsList, "mycelium_top", "4F");
    
    setIconProperties(propsList, "farmland_wet", "2F");
    setIconProperties(propsList, "farmland_dry", "2F");
    
    setIconProperties(propsList, "netherrack", "4F");
    setIconProperties(propsList, "soul_sand", "4F");
    setIconProperties(propsList, "glowstone", "4");
    
    setIconProperties(propsList, "end_stone", "4");
    
    setIconProperties(propsList, "sandstone_top", "4");
    setIconProperties(propsList, "sandstone_bottom", "4F");
    
    setIconProperties(propsList, "redstone_lamp_on", "4F");
    setIconProperties(propsList, "redstone_lamp_off", "4F");
    
    NaturalProperties[] terrainProps = (NaturalProperties[])propsList.toArray(new NaturalProperties[propsList.size()]);
    
    return terrainProps;
  }
  
  private static void setIconProperties(List propsList, String iconName, String propStr)
  {
    bvg terrainMap = TextureUtils.getTextureMapBlocks();
    bvh icon = terrainMap.getSpriteSafe("minecraft:blocks/" + iconName);
    if (icon == null)
    {
      Config.warn("*** NaturalProperties: Icon not found: " + iconName + " ***");
      return;
    }
    if (!(icon instanceof bvh))
    {
      Config.warn("*** NaturalProperties: Icon is not IconStitched: " + iconName + ": " + icon.getClass().getName() + " ***");
      return;
    }
    bvh ts = icon;
    
    int index = ts.getIndexInMap();
    if (index < 0)
    {
      Config.warn("*** NaturalProperties: Invalid index for icon: " + iconName + ": " + index + " ***");
      return;
    }
    if (!Config.isFromDefaultResourcePack(new kk("textures/blocks/" + iconName + ".png"))) {
      return;
    }
    while (index >= propsList.size()) {
      propsList.add(null);
    }
    NaturalProperties props = new NaturalProperties(propStr);
    
    propsList.set(index, props);
    
    Config.dbg("NaturalTextures: " + iconName + " = " + propStr);
  }
}
