import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class CustomColors
{
  private static CustomColormap waterColors = null;
  private static CustomColormap foliagePineColors = null;
  private static CustomColormap foliageBirchColors = null;
  private static CustomColormap swampFoliageColors = null;
  private static CustomColormap swampGrassColors = null;
  private static CustomColormap[] colorsBlockColormaps = null;
  private static CustomColormap[][] blockColormaps = (CustomColormap[][])null;
  private static CustomColormap skyColors = null;
  private static CustomColorFader skyColorFader = new CustomColorFader();
  private static CustomColormap fogColors = null;
  private static CustomColorFader fogColorFader = new CustomColorFader();
  private static CustomColormap underwaterColors = null;
  private static CustomColorFader underwaterColorFader = new CustomColorFader();
  private static CustomColormap[] lightMapsColorsRgb = null;
  private static int lightmapMinDimensionId = 0;
  private static float[][] sunRgbs = new float[16][3];
  private static float[][] torchRgbs = new float[16][3];
  private static CustomColormap redstoneColors = null;
  private static CustomColormap xpOrbColors = null;
  private static CustomColormap stemColors = null;
  private static CustomColormap stemMelonColors = null;
  private static CustomColormap stemPumpkinColors = null;
  private static CustomColormap myceliumParticleColors = null;
  private static boolean useDefaultGrassFoliageColors = true;
  private static int particleWaterColor = -1;
  private static int particlePortalColor = -1;
  private static int lilyPadColor = -1;
  private static int expBarTextColor = -1;
  private static int bossTextColor = -1;
  private static int signTextColor = -1;
  private static bbj fogColorNether = null;
  private static bbj fogColorEnd = null;
  private static bbj skyColorEnd = null;
  private static int[] spawnEggPrimaryColors = null;
  private static int[] spawnEggSecondaryColors = null;
  private static float[][] wolfCollarColors = (float[][])null;
  private static float[][] sheepColors = (float[][])null;
  private static int[] textColors = null;
  private static int[] mapColorsOriginal = null;
  private static int[] potionColors = null;
  private static final arc BLOCK_STATE_DIRT = aju.d.u();
  private static final arc BLOCK_STATE_WATER = aju.j.u();
  public static Random random = new Random();
  private static final CustomColors.IColorizer COLORIZER_GRASS = new CustomColors.IColorizer()
  {
    public int getColor(arc blockState, ahx blockAccess, cj blockPos)
    {
      aig biome = CustomColors.getColorBiome(blockAccess, blockPos);
      if ((CustomColors.swampGrassColors != null) && (biome == ail.h)) {
        return CustomColors.swampGrassColors.getColor(biome, blockPos);
      }
      return biome.b(blockPos);
    }
    
    public boolean isColorConstant()
    {
      return false;
    }
  };
  private static final CustomColors.IColorizer COLORIZER_FOLIAGE = new CustomColors.IColorizer()
  {
    public int getColor(arc blockState, ahx blockAccess, cj blockPos)
    {
      aig biome = CustomColors.getColorBiome(blockAccess, blockPos);
      if ((CustomColors.swampFoliageColors != null) && (biome == ail.h)) {
        return CustomColors.swampFoliageColors.getColor(biome, blockPos);
      }
      return biome.c(blockPos);
    }
    
    public boolean isColorConstant()
    {
      return false;
    }
  };
  private static final CustomColors.IColorizer COLORIZER_FOLIAGE_PINE = new CustomColors.IColorizer()
  {
    public int getColor(arc blockState, ahx blockAccess, cj blockPos)
    {
      if (CustomColors.foliagePineColors != null) {
        return CustomColors.foliagePineColors.getColor(blockAccess, blockPos);
      }
      return ahq.a();
    }
    
    public boolean isColorConstant()
    {
      return CustomColors.foliagePineColors == null;
    }
  };
  private static final CustomColors.IColorizer COLORIZER_FOLIAGE_BIRCH = new CustomColors.IColorizer()
  {
    public int getColor(arc blockState, ahx blockAccess, cj blockPos)
    {
      if (CustomColors.foliageBirchColors != null) {
        return CustomColors.foliageBirchColors.getColor(blockAccess, blockPos);
      }
      return ahq.b();
    }
    
    public boolean isColorConstant()
    {
      return CustomColors.foliageBirchColors == null;
    }
  };
  private static final CustomColors.IColorizer COLORIZER_WATER = new CustomColors.IColorizer()
  {
    public int getColor(arc blockState, ahx blockAccess, cj blockPos)
    {
      aig biome = CustomColors.getColorBiome(blockAccess, blockPos);
      if (CustomColors.waterColors != null) {
        return CustomColors.waterColors.getColor(biome, blockPos);
      }
      if (Reflector.ForgeBiomeGenBase_getWaterColorMultiplier.exists()) {
        return Reflector.callInt(biome, Reflector.ForgeBiomeGenBase_getWaterColorMultiplier, new Object[0]);
      }
      return CustomColors.getBlockColors().a(blockState, blockAccess, blockPos, 0);
    }
    
    public boolean isColorConstant()
    {
      return false;
    }
  };
  
  public static void update()
  {
    waterColors = null;
    foliageBirchColors = null;
    foliagePineColors = null;
    swampGrassColors = null;
    swampFoliageColors = null;
    skyColors = null;
    fogColors = null;
    underwaterColors = null;
    redstoneColors = null;
    xpOrbColors = null;
    stemColors = null;
    myceliumParticleColors = null;
    lightMapsColorsRgb = null;
    
    particleWaterColor = -1;
    particlePortalColor = -1;
    lilyPadColor = -1;
    expBarTextColor = -1;
    bossTextColor = -1;
    signTextColor = -1;
    fogColorNether = null;
    fogColorEnd = null;
    skyColorEnd = null;
    colorsBlockColormaps = null;
    blockColormaps = (CustomColormap[][])null;
    useDefaultGrassFoliageColors = true;
    
    spawnEggPrimaryColors = null;
    spawnEggSecondaryColors = null;
    
    wolfCollarColors = (float[][])null;
    sheepColors = (float[][])null;
    
    textColors = null;
    
    setMapColors(mapColorsOriginal);
    
    potionColors = null;
    
    String mcpColormap = "mcpatcher/colormap/";
    
    String[] waterPaths = { "water.png", "watercolorX.png" };
    waterColors = getCustomColors(mcpColormap, waterPaths, 256, 256);
    
    updateUseDefaultGrassFoliageColors();
    if (!Config.isCustomColors()) {
      return;
    }
    String[] pinePaths = { "pine.png", "pinecolor.png" };
    foliagePineColors = getCustomColors(mcpColormap, pinePaths, 256, 256);
    String[] birchPaths = { "birch.png", "birchcolor.png" };
    foliageBirchColors = getCustomColors(mcpColormap, birchPaths, 256, 256);
    
    String[] swampGrassPaths = { "swampgrass.png", "swampgrasscolor.png" };
    swampGrassColors = getCustomColors(mcpColormap, swampGrassPaths, 256, 256);
    String[] swampFoliagePaths = { "swampfoliage.png", "swampfoliagecolor.png" };
    swampFoliageColors = getCustomColors(mcpColormap, swampFoliagePaths, 256, 256);
    
    String[] sky0Paths = { "sky0.png", "skycolor0.png" };
    skyColors = getCustomColors(mcpColormap, sky0Paths, 256, 256);
    String[] fog0Paths = { "fog0.png", "fogcolor0.png" };
    fogColors = getCustomColors(mcpColormap, fog0Paths, 256, 256);
    String[] underwaterPaths = { "underwater.png", "underwatercolor.png" };
    underwaterColors = getCustomColors(mcpColormap, underwaterPaths, 256, 256);
    
    String[] redstonePaths = { "redstone.png", "redstonecolor.png" };
    redstoneColors = getCustomColors(mcpColormap, redstonePaths, 16, 1);
    
    xpOrbColors = getCustomColors(mcpColormap + "xporb.png", -1, -1);
    
    String[] stemPaths = { "stem.png", "stemcolor.png" };
    stemColors = getCustomColors(mcpColormap, stemPaths, 8, 1);
    stemPumpkinColors = getCustomColors(mcpColormap + "pumpkinstem.png", 8, 1);
    stemMelonColors = getCustomColors(mcpColormap + "melonstem.png", 8, 1);
    
    String[] myceliumPaths = { "myceliumparticle.png", "myceliumparticlecolor.png" };
    myceliumParticleColors = getCustomColors(mcpColormap, myceliumPaths, -1, -1);
    
    Pair<CustomColormap[], Integer> lightMaps = parseLightmapsRgb();
    lightMapsColorsRgb = (CustomColormap[])lightMaps.getLeft();
    lightmapMinDimensionId = ((Integer)lightMaps.getRight()).intValue();
    
    readColorProperties("mcpatcher/color.properties");
    
    blockColormaps = readBlockColormaps(new String[] { mcpColormap + "custom/", mcpColormap + "blocks/" }, colorsBlockColormaps, 256, 256);
    
    updateUseDefaultGrassFoliageColors();
  }
  
  private static Pair<CustomColormap[], Integer> parseLightmapsRgb()
  {
    String lightmapPrefix = "mcpatcher/lightmap/world";
    String lightmapSuffix = ".png";
    String[] pathsLightmap = ResUtils.collectFiles(lightmapPrefix, lightmapSuffix);
    Map<Integer, String> mapLightmaps = new HashMap();
    for (int i = 0; i < pathsLightmap.length; i++)
    {
      String path = pathsLightmap[i];
      String dimIdStr = StrUtils.removePrefixSuffix(path, lightmapPrefix, lightmapSuffix);
      int dimId = Config.parseInt(dimIdStr, Integer.MIN_VALUE);
      if (dimId == Integer.MIN_VALUE) {
        warn("Invalid dimension ID: " + dimIdStr + ", path: " + path);
      } else {
        mapLightmaps.put(Integer.valueOf(dimId), path);
      }
    }
    Set<Integer> setDimIds = mapLightmaps.keySet();
    Integer[] dimIds = (Integer[])setDimIds.toArray(new Integer[setDimIds.size()]);
    
    Arrays.sort(dimIds);
    if (dimIds.length <= 0) {
      return new ImmutablePair(null, Integer.valueOf(0));
    }
    int minDimId = dimIds[0].intValue();
    int maxDimId = dimIds[(dimIds.length - 1)].intValue();
    int countDim = maxDimId - minDimId + 1;
    CustomColormap[] colormaps = new CustomColormap[countDim];
    for (int i = 0; i < dimIds.length; i++)
    {
      Integer dimId = dimIds[i];
      String path = (String)mapLightmaps.get(dimId);
      CustomColormap colors = getCustomColors(path, -1, -1);
      if (colors != null) {
        if (colors.getWidth() < 16)
        {
          warn("Invalid lightmap width: " + colors.getWidth() + ", path: " + path);
        }
        else
        {
          int lightmapIndex = dimId.intValue() - minDimId;
          colormaps[lightmapIndex] = colors;
        }
      }
    }
    return new ImmutablePair(colormaps, Integer.valueOf(minDimId));
  }
  
  private static int getTextureHeight(String path, int defHeight)
  {
    try
    {
      InputStream in = Config.getResourceStream(new kk(path));
      if (in == null) {
        return defHeight;
      }
      BufferedImage bi = ImageIO.read(in);
      in.close();
      if (bi == null) {
        return defHeight;
      }
      return bi.getHeight();
    }
    catch (IOException e) {}
    return defHeight;
  }
  
  private static void readColorProperties(String fileName)
  {
    try
    {
      kk loc = new kk(fileName);
      InputStream in = Config.getResourceStream(loc);
      if (in == null) {
        return;
      }
      dbg("Loading " + fileName);
      Properties props = new Properties();
      props.load(in);
      in.close();
      
      particleWaterColor = readColor(props, new String[] { "particle.water", "drop.water" });
      particlePortalColor = readColor(props, "particle.portal");
      lilyPadColor = readColor(props, "lilypad");
      expBarTextColor = readColor(props, "text.xpbar");
      bossTextColor = readColor(props, "text.boss");
      signTextColor = readColor(props, "text.sign");
      
      fogColorNether = readColorVec3(props, "fog.nether");
      fogColorEnd = readColorVec3(props, "fog.end");
      skyColorEnd = readColorVec3(props, "sky.end");
      
      colorsBlockColormaps = readCustomColormaps(props, fileName);
      
      spawnEggPrimaryColors = readSpawnEggColors(props, fileName, "egg.shell.", "Spawn egg shell");
      spawnEggSecondaryColors = readSpawnEggColors(props, fileName, "egg.spots.", "Spawn egg spot");
      
      wolfCollarColors = readDyeColors(props, fileName, "collar.", "Wolf collar");
      
      sheepColors = readDyeColors(props, fileName, "sheep.", "Sheep");
      
      textColors = readTextColors(props, fileName, "text.code.", "Text");
      
      int[] mapColors = readMapColors(props, fileName, "map.", "Map");
      if (mapColors != null)
      {
        if (mapColorsOriginal == null) {
          mapColorsOriginal = getMapColors();
        }
        setMapColors(mapColors);
      }
      potionColors = readPotionColors(props, fileName, "potion.", "Potion");
    }
    catch (FileNotFoundException e) {}catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  private static CustomColormap[] readCustomColormaps(Properties props, String fileName)
  {
    List list = new ArrayList();
    
    String palettePrefix = "palette.block.";
    Map map = new HashMap();
    Set keys = props.keySet();
    for (Iterator iter = keys.iterator(); iter.hasNext();)
    {
      String key = (String)iter.next();
      String value = props.getProperty(key);
      if (key.startsWith(palettePrefix)) {
        map.put(key, value);
      }
    }
    String[] propNames = (String[])map.keySet().toArray(new String[map.size()]);
    for (int i = 0; i < propNames.length; i++)
    {
      String name = propNames[i];
      String value = props.getProperty(name);
      dbg("Block palette: " + name + " = " + value);
      String path = name.substring(palettePrefix.length());
      
      String basePath = TextureUtils.getBasePath(fileName);
      path = TextureUtils.fixResourcePath(path, basePath);
      
      CustomColormap colors = getCustomColors(path, 256, 256);
      if (colors == null)
      {
        warn("Colormap not found: " + path);
      }
      else
      {
        ConnectedParser cp = new ConnectedParser("CustomColors");
        MatchBlock[] mbs = cp.parseMatchBlocks(value);
        if ((mbs == null) || (mbs.length <= 0))
        {
          warn("Invalid match blocks: " + value);
        }
        else
        {
          for (int m = 0; m < mbs.length; m++)
          {
            MatchBlock mb = mbs[m];
            colors.addMatchBlock(mb);
          }
          list.add(colors);
        }
      }
    }
    if (list.size() <= 0) {
      return null;
    }
    CustomColormap[] cms = (CustomColormap[])list.toArray(new CustomColormap[list.size()]);
    
    return cms;
  }
  
  private static CustomColormap[][] readBlockColormaps(String[] basePaths, CustomColormap[] basePalettes, int width, int height)
  {
    String[] paths = ResUtils.collectFiles(basePaths, new String[] { ".properties" });
    
    Arrays.sort(paths);
    
    List blockList = new ArrayList();
    for (int i = 0; i < paths.length; i++)
    {
      String path = paths[i];
      dbg("Block colormap: " + path);
      try
      {
        kk locFile = new kk("minecraft", path);
        InputStream in = Config.getResourceStream(locFile);
        if (in == null)
        {
          warn("File not found: " + path);
        }
        else
        {
          Properties props = new Properties();
          props.load(in);
          CustomColormap cm = new CustomColormap(props, path, width, height);
          if (cm.isValid(path)) {
            if (cm.isValidMatchBlocks(path)) {
              addToBlockList(cm, blockList);
            }
          }
        }
      }
      catch (FileNotFoundException e)
      {
        warn("File not found: " + path);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    if (basePalettes != null) {
      for (int i = 0; i < basePalettes.length; i++)
      {
        CustomColormap cm = basePalettes[i];
        addToBlockList(cm, blockList);
      }
    }
    if (blockList.size() <= 0) {
      return (CustomColormap[][])null;
    }
    CustomColormap[][] cmArr = blockListToArray(blockList);
    
    return cmArr;
  }
  
  private static void addToBlockList(CustomColormap cm, List blockList)
  {
    int[] ids = cm.getMatchBlockIds();
    if ((ids == null) || (ids.length <= 0))
    {
      warn("No match blocks: " + Config.arrayToString(ids));
      return;
    }
    for (int i = 0; i < ids.length; i++)
    {
      int blockId = ids[i];
      if (blockId < 0) {
        warn("Invalid block ID: " + blockId);
      } else {
        addToList(cm, blockList, blockId);
      }
    }
  }
  
  private static void addToList(CustomColormap cm, List list, int id)
  {
    while (id >= list.size()) {
      list.add(null);
    }
    List subList = (List)list.get(id);
    if (subList == null)
    {
      subList = new ArrayList();
      list.set(id, subList);
    }
    subList.add(cm);
  }
  
  private static CustomColormap[][] blockListToArray(List list)
  {
    CustomColormap[][] colArr = new CustomColormap[list.size()][];
    for (int i = 0; i < list.size(); i++)
    {
      List subList = (List)list.get(i);
      if (subList != null)
      {
        CustomColormap[] subArr = (CustomColormap[])subList.toArray(new CustomColormap[subList.size()]);
        
        colArr[i] = subArr;
      }
    }
    return colArr;
  }
  
  private static int readColor(Properties props, String[] names)
  {
    for (int i = 0; i < names.length; i++)
    {
      String name = names[i];
      int col = readColor(props, name);
      if (col >= 0) {
        return col;
      }
    }
    return -1;
  }
  
  private static int readColor(Properties props, String name)
  {
    String str = props.getProperty(name);
    if (str == null) {
      return -1;
    }
    str = str.trim();
    
    int color = parseColor(str);
    if (color < 0)
    {
      warn("Invalid color: " + name + " = " + str);
      return color;
    }
    dbg(name + " = " + str);
    
    return color;
  }
  
  private static int parseColor(String str)
  {
    if (str == null) {
      return -1;
    }
    str = str.trim();
    try
    {
      return Integer.parseInt(str, 16) & 0xFFFFFF;
    }
    catch (NumberFormatException e) {}
    return -1;
  }
  
  private static bbj readColorVec3(Properties props, String name)
  {
    int col = readColor(props, name);
    if (col < 0) {
      return null;
    }
    int red = col >> 16 & 0xFF;
    int green = col >> 8 & 0xFF;
    int blue = col & 0xFF;
    
    float redF = red / 255.0F;
    float greenF = green / 255.0F;
    float blueF = blue / 255.0F;
    
    return new bbj(redF, greenF, blueF);
  }
  
  private static CustomColormap getCustomColors(String basePath, String[] paths, int width, int height)
  {
    for (int i = 0; i < paths.length; i++)
    {
      String path = paths[i];
      
      path = basePath + path;
      
      CustomColormap cols = getCustomColors(path, width, height);
      if (cols != null) {
        return cols;
      }
    }
    return null;
  }
  
  public static CustomColormap getCustomColors(String pathImage, int width, int height)
  {
    try
    {
      kk loc = new kk(pathImage);
      if (!Config.hasResource(loc)) {
        return null;
      }
      dbg("Colormap " + pathImage);
      
      Properties props = new Properties();
      String pathProps = StrUtils.replaceSuffix(pathImage, ".png", ".properties");
      kk locProps = new kk(pathProps);
      if (Config.hasResource(locProps))
      {
        InputStream in = Config.getResourceStream(locProps);
        props.load(in);
        in.close();
        dbg("Colormap properties: " + pathProps);
      }
      else
      {
        props.put("format", "vanilla");
        props.put("source", pathImage);
        pathProps = pathImage;
      }
      CustomColormap cm = new CustomColormap(props, pathProps, width, height);
      if (!cm.isValid(pathProps)) {
        return null;
      }
      return cm;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static void updateUseDefaultGrassFoliageColors()
  {
    useDefaultGrassFoliageColors = (foliageBirchColors == null) && (foliagePineColors == null) && (swampGrassColors == null) && (swampFoliageColors == null) && (Config.isSwampColors()) && (Config.isSmoothBiomes());
  }
  
  public static int getColorMultiplier(bof quad, arc blockState, ahx blockAccess, cj blockPos, RenderEnv renderEnv)
  {
    ajt block = blockState.t();
    
    arc bs = renderEnv.getBlockState();
    if (blockColormaps != null)
    {
      if (!quad.c())
      {
        if (block == aju.c) {
          bs = BLOCK_STATE_DIRT;
        }
        if (block == aju.af) {
          return -1;
        }
      }
      if (block == aju.cF) {
        if (renderEnv.getMetadata() >= 8)
        {
          blockPos = blockPos.b();
          bs = blockAccess.o(blockPos);
        }
      }
      CustomColormap cm = getBlockColormap(bs);
      if (cm != null)
      {
        if ((Config.isSmoothBiomes()) && (!cm.isColorConstant())) {
          return getSmoothColorMultiplier(blockState, blockAccess, blockPos, cm, renderEnv.getColorizerBlockPosM());
        }
        return cm.getColor(blockAccess, blockPos);
      }
    }
    if (!quad.c()) {
      return -1;
    }
    if (block == aju.bx) {
      return getLilypadColorMultiplier(blockAccess, blockPos);
    }
    if (block == aju.af) {
      return getRedstoneColor(renderEnv.getBlockState());
    }
    if ((block instanceof aow)) {
      return getStemColorMultiplier(block, blockAccess, blockPos, renderEnv);
    }
    if (useDefaultGrassFoliageColors) {
      return -1;
    }
    int metadata = renderEnv.getMetadata();
    CustomColors.IColorizer colorizer;
    if ((block == aju.c) || (block == aju.H) || (block == aju.cF))
    {
      colorizer = COLORIZER_GRASS;
    }
    else if (block == aju.cF)
    {
      CustomColors.IColorizer colorizer = COLORIZER_GRASS;
      if (metadata >= 8) {
        blockPos = blockPos.b();
      }
    }
    else if (block == aju.t)
    {
      CustomColors.IColorizer colorizer;
      switch (metadata & 0x3)
      {
      case 0: 
        colorizer = COLORIZER_FOLIAGE;
        break;
      case 1: 
        colorizer = COLORIZER_FOLIAGE_PINE;
        break;
      case 2: 
        colorizer = COLORIZER_FOLIAGE_BIRCH;
        break;
      default: 
        colorizer = COLORIZER_FOLIAGE;break;
      }
    }
    else
    {
      CustomColors.IColorizer colorizer;
      if (block == aju.u)
      {
        colorizer = COLORIZER_FOLIAGE;
      }
      else
      {
        CustomColors.IColorizer colorizer;
        if (block == aju.bn) {
          colorizer = COLORIZER_FOLIAGE;
        } else {
          return -1;
        }
      }
    }
    CustomColors.IColorizer colorizer;
    if ((Config.isSmoothBiomes()) && (!colorizer.isColorConstant())) {
      return getSmoothColorMultiplier(blockState, blockAccess, blockPos, colorizer, renderEnv.getColorizerBlockPosM());
    }
    return colorizer.getColor(bs, blockAccess, blockPos);
  }
  
  protected static aig getColorBiome(ahx blockAccess, cj blockPos)
  {
    aig biome = blockAccess.b(blockPos);
    if ((biome == ail.h) && (!Config.isSwampColors())) {
      biome = ail.c;
    }
    return biome;
  }
  
  private static CustomColormap getBlockColormap(arc blockState)
  {
    if (blockColormaps == null) {
      return null;
    }
    if (!(blockState instanceof ara)) {
      return null;
    }
    ara bs = (ara)blockState;
    int blockId = bs.getBlockId();
    if ((blockId < 0) || (blockId >= blockColormaps.length)) {
      return null;
    }
    CustomColormap[] cms = blockColormaps[blockId];
    if (cms == null) {
      return null;
    }
    for (int i = 0; i < cms.length; i++)
    {
      CustomColormap cm = cms[i];
      if (cm.matchesBlock(bs)) {
        return cm;
      }
    }
    return null;
  }
  
  private static int getSmoothColorMultiplier(arc blockState, ahx blockAccess, cj blockPos, CustomColors.IColorizer colorizer, BlockPosM blockPosM)
  {
    int sumRed = 0;
    int sumGreen = 0;
    int sumBlue = 0;
    int x = blockPos.p();
    int y = blockPos.q();
    int z = blockPos.r();
    BlockPosM posM = blockPosM;
    for (int ix = x - 1; ix <= x + 1; ix++) {
      for (int iz = z - 1; iz <= z + 1; iz++)
      {
        posM.setXyz(ix, y, iz);
        int col = colorizer.getColor(blockState, blockAccess, posM);
        
        sumRed += (col >> 16 & 0xFF);
        sumGreen += (col >> 8 & 0xFF);
        sumBlue += (col & 0xFF);
      }
    }
    int r = sumRed / 9;
    int g = sumGreen / 9;
    int b = sumBlue / 9;
    
    return r << 16 | g << 8 | b;
  }
  
  public static int getFluidColor(ahx blockAccess, arc blockState, cj blockPos, RenderEnv renderEnv)
  {
    ajt block = blockState.t();
    
    CustomColors.IColorizer colorizer = getBlockColormap(blockState);
    if (colorizer == null) {
      if (blockState.a() == axe.h) {
        colorizer = COLORIZER_WATER;
      }
    }
    if (colorizer == null) {
      return getBlockColors().a(blockState, blockAccess, blockPos, 0);
    }
    if ((Config.isSmoothBiomes()) && (!colorizer.isColorConstant())) {
      return getSmoothColorMultiplier(blockState, blockAccess, blockPos, colorizer, renderEnv.getColorizerBlockPosM());
    }
    return colorizer.getColor(blockState, blockAccess, blockPos);
  }
  
  public static bco getBlockColors()
  {
    return bcf.z().al();
  }
  
  public static void updatePortalFX(blx fx)
  {
    if (particlePortalColor < 0) {
      return;
    }
    int col = particlePortalColor;
    int red = col >> 16 & 0xFF;
    int green = col >> 8 & 0xFF;
    int blue = col & 0xFF;
    
    float redF = red / 255.0F;
    float greenF = green / 255.0F;
    float blueF = blue / 255.0F;
    
    fx.a(redF, greenF, blueF);
  }
  
  public static void updateMyceliumFX(blx fx)
  {
    if (myceliumParticleColors == null) {
      return;
    }
    int col = myceliumParticleColors.getColorRandom();
    
    int red = col >> 16 & 0xFF;
    int green = col >> 8 & 0xFF;
    int blue = col & 0xFF;
    
    float redF = red / 255.0F;
    float greenF = green / 255.0F;
    float blueF = blue / 255.0F;
    
    fx.a(redF, greenF, blueF);
  }
  
  private static int getRedstoneColor(arc blockState)
  {
    if (redstoneColors == null) {
      return -1;
    }
    int level = getRedstoneLevel(blockState, 15);
    
    int col = redstoneColors.getColor(level);
    
    return col;
  }
  
  public static void updateReddustFX(blx fx, ahx blockAccess, double x, double y, double z)
  {
    if (redstoneColors == null) {
      return;
    }
    arc state = blockAccess.o(new cj(x, y, z));
    int level = getRedstoneLevel(state, 15);
    
    int col = redstoneColors.getColor(level);
    
    int red = col >> 16 & 0xFF;
    int green = col >> 8 & 0xFF;
    int blue = col & 0xFF;
    
    float redF = red / 255.0F;
    float greenF = green / 255.0F;
    float blueF = blue / 255.0F;
    
    fx.a(redF, greenF, blueF);
  }
  
  private static int getRedstoneLevel(arc state, int def)
  {
    ajt block = state.t();
    if (!(block instanceof anx)) {
      return def;
    }
    Object val = state.c(anx.e);
    if (!(val instanceof Integer)) {
      return def;
    }
    Integer valInt = (Integer)val;
    return valInt.intValue();
  }
  
  public static int getXpOrbColor(float timer)
  {
    if (xpOrbColors == null) {
      return -1;
    }
    int index = (int)((on.a(timer) + 1.0F) * (xpOrbColors.getLength() - 1) / 2.0D);
    int col = xpOrbColors.getColor(index);
    return col;
  }
  
  public static void updateWaterFX(blx fx, ahx blockAccess, double x, double y, double z)
  {
    if ((waterColors == null) && (blockColormaps == null)) {
      return;
    }
    cj blockPos = new cj(x, y, z);
    RenderEnv renderEnv = RenderEnv.getInstance(blockAccess, BLOCK_STATE_WATER, blockPos);
    int col = getFluidColor(blockAccess, BLOCK_STATE_WATER, blockPos, renderEnv);
    int red = col >> 16 & 0xFF;
    int green = col >> 8 & 0xFF;
    int blue = col & 0xFF;
    
    float redF = red / 255.0F;
    float greenF = green / 255.0F;
    float blueF = blue / 255.0F;
    if (particleWaterColor >= 0)
    {
      int redDrop = particleWaterColor >> 16 & 0xFF;
      int greenDrop = particleWaterColor >> 8 & 0xFF;
      int blueDrop = particleWaterColor & 0xFF;
      
      redF *= redDrop / 255.0F;
      greenF *= greenDrop / 255.0F;
      blueF *= blueDrop / 255.0F;
    }
    fx.a(redF, greenF, blueF);
  }
  
  private static int getLilypadColorMultiplier(ahx blockAccess, cj blockPos)
  {
    if (lilyPadColor < 0) {
      return getBlockColors().a(aju.bx.u(), blockAccess, blockPos, 0);
    }
    return lilyPadColor;
  }
  
  private static bbj getFogColorNether(bbj col)
  {
    if (fogColorNether == null) {
      return col;
    }
    return fogColorNether;
  }
  
  private static bbj getFogColorEnd(bbj col)
  {
    if (fogColorEnd == null) {
      return col;
    }
    return fogColorEnd;
  }
  
  private static bbj getSkyColorEnd(bbj col)
  {
    if (skyColorEnd == null) {
      return col;
    }
    return skyColorEnd;
  }
  
  public static bbj getSkyColor(bbj skyColor3d, ahx blockAccess, double x, double y, double z)
  {
    if (skyColors == null) {
      return skyColor3d;
    }
    int col = skyColors.getColorSmooth(blockAccess, x, y, z, 3);
    
    int red = col >> 16 & 0xFF;
    int green = col >> 8 & 0xFF;
    int blue = col & 0xFF;
    
    float redF = red / 255.0F;
    float greenF = green / 255.0F;
    float blueF = blue / 255.0F;
    
    float cRed = (float)skyColor3d.b / 0.5F;
    
    float cGreen = (float)skyColor3d.c / 0.66275F;
    
    float cBlue = (float)skyColor3d.d;
    
    redF *= cRed;
    greenF *= cGreen;
    blueF *= cBlue;
    
    bbj newCol = skyColorFader.getColor(redF, greenF, blueF);
    
    return newCol;
  }
  
  private static bbj getFogColor(bbj fogColor3d, ahx blockAccess, double x, double y, double z)
  {
    if (fogColors == null) {
      return fogColor3d;
    }
    int col = fogColors.getColorSmooth(blockAccess, x, y, z, 3);
    
    int red = col >> 16 & 0xFF;
    int green = col >> 8 & 0xFF;
    int blue = col & 0xFF;
    
    float redF = red / 255.0F;
    float greenF = green / 255.0F;
    float blueF = blue / 255.0F;
    
    float cRed = (float)fogColor3d.b / 0.753F;
    
    float cGreen = (float)fogColor3d.c / 0.8471F;
    
    float cBlue = (float)fogColor3d.d;
    
    redF *= cRed;
    greenF *= cGreen;
    blueF *= cBlue;
    
    bbj newCol = fogColorFader.getColor(redF, greenF, blueF);
    
    return newCol;
  }
  
  public static bbj getUnderwaterColor(ahx blockAccess, double x, double y, double z)
  {
    if (underwaterColors == null) {
      return null;
    }
    int col = underwaterColors.getColorSmooth(blockAccess, x, y, z, 3);
    
    int red = col >> 16 & 0xFF;
    int green = col >> 8 & 0xFF;
    int blue = col & 0xFF;
    
    float redF = red / 255.0F;
    float greenF = green / 255.0F;
    float blueF = blue / 255.0F;
    
    bbj newCol = underwaterColorFader.getColor(redF, greenF, blueF);
    
    return newCol;
  }
  
  private static int getStemColorMultiplier(ajt blockStem, ahx blockAccess, cj blockPos, RenderEnv renderEnv)
  {
    CustomColormap colors = stemColors;
    if ((blockStem == aju.bl) && (stemPumpkinColors != null)) {
      colors = stemPumpkinColors;
    }
    if ((blockStem == aju.bm) && (stemMelonColors != null)) {
      colors = stemMelonColors;
    }
    if (colors == null) {
      return getBlockColors().a(blockStem.u(), blockAccess, blockPos, 0);
    }
    int level = renderEnv.getMetadata();
    
    return colors.getColor(level);
  }
  
  public static boolean updateLightmap(aht world, float torchFlickerX, int[] lmColors, boolean nightvision)
  {
    if (world == null) {
      return false;
    }
    if (lightMapsColorsRgb == null) {
      return false;
    }
    int dimensionId = world.s.p().a();
    
    int lightMapIndex = dimensionId - lightmapMinDimensionId;
    if ((lightMapIndex < 0) || (lightMapIndex >= lightMapsColorsRgb.length)) {
      return false;
    }
    CustomColormap lightMapRgb = lightMapsColorsRgb[lightMapIndex];
    if (lightMapRgb == null) {
      return false;
    }
    int height = lightMapRgb.getHeight();
    if ((nightvision) && (height < 64)) {
      return false;
    }
    int width = lightMapRgb.getWidth();
    if (width < 16)
    {
      warn("Invalid lightmap width: " + width + " for dimension: " + dimensionId);
      lightMapsColorsRgb[lightMapIndex] = null;
      return false;
    }
    int startIndex = 0;
    if (nightvision) {
      startIndex = width * 16 * 2;
    }
    float sun = 1.1666666F * (world.b(1.0F) - 0.2F);
    if (world.ag() > 0) {
      sun = 1.0F;
    }
    sun = Config.limitTo1(sun);
    float sunX = sun * (width - 1);
    float torchX = Config.limitTo1(torchFlickerX + 0.5F) * (width - 1);
    float gamma = Config.limitTo1(Config.getGameSettings().ax);
    boolean hasGamma = gamma > 1.0E-4F;
    
    float[][] colorsRgb = lightMapRgb.getColorsRgb();
    getLightMapColumn(colorsRgb, sunX, startIndex, width, sunRgbs);
    getLightMapColumn(colorsRgb, torchX, startIndex + 16 * width, width, torchRgbs);
    
    float[] rgb = new float[3];
    for (int is = 0; is < 16; is++) {
      for (int it = 0; it < 16; it++)
      {
        for (int ic = 0; ic < 3; ic++)
        {
          float comp = Config.limitTo1(sunRgbs[is][ic] + torchRgbs[it][ic]);
          if (hasGamma)
          {
            float cg = 1.0F - comp;
            cg = 1.0F - cg * cg * cg * cg;
            comp = gamma * cg + (1.0F - gamma) * comp;
          }
          rgb[ic] = comp;
        }
        int r = (int)(rgb[0] * 255.0F);
        int g = (int)(rgb[1] * 255.0F);
        int b = (int)(rgb[2] * 255.0F);
        
        lmColors[(is * 16 + it)] = (0xFF000000 | r << 16 | g << 8 | b);
      }
    }
    return true;
  }
  
  private static void getLightMapColumn(float[][] origMap, float x, int offset, int width, float[][] colRgb)
  {
    int xLow = (int)Math.floor(x);
    int xHigh = (int)Math.ceil(x);
    if (xLow == xHigh)
    {
      for (int y = 0; y < 16; y++)
      {
        float[] rgbLow = origMap[(offset + y * width + xLow)];
        float[] rgb = colRgb[y];
        for (int i = 0; i < 3; i++) {
          rgb[i] = rgbLow[i];
        }
      }
      return;
    }
    float dLow = 1.0F - (x - xLow);
    float dHigh = 1.0F - (xHigh - x);
    for (int y = 0; y < 16; y++)
    {
      float[] rgbLow = origMap[(offset + y * width + xLow)];
      float[] rgbHigh = origMap[(offset + y * width + xHigh)];
      float[] rgb = colRgb[y];
      for (int i = 0; i < 3; i++) {
        rgb[i] = (rgbLow[i] * dLow + rgbHigh[i] * dHigh);
      }
    }
  }
  
  public static bbj getWorldFogColor(bbj fogVec, aht world, rr renderViewEntity, float partialTicks)
  {
    asw worldType = world.s.p();
    switch (worldType)
    {
    case b: 
      fogVec = getFogColorNether(fogVec);
      break;
    case a: 
      bcf mc = bcf.z();
      fogVec = getFogColor(fogVec, mc.f, renderViewEntity.p, renderViewEntity.q + 1.0D, renderViewEntity.r);
      break;
    case c: 
      fogVec = getFogColorEnd(fogVec);
    }
    return fogVec;
  }
  
  public static bbj getWorldSkyColor(bbj skyVec, aht world, rr renderViewEntity, float partialTicks)
  {
    asw worldType = world.s.p();
    switch (worldType)
    {
    case a: 
      bcf mc = bcf.z();
      skyVec = getSkyColor(skyVec, mc.f, renderViewEntity.p, renderViewEntity.q + 1.0D, renderViewEntity.r);
      break;
    case c: 
      skyVec = getSkyColorEnd(skyVec);
    }
    return skyVec;
  }
  
  private static int[] readSpawnEggColors(Properties props, String fileName, String prefix, String logName)
  {
    List<Integer> list = new ArrayList();
    Set keys = props.keySet();
    int countColors = 0;
    for (Iterator iter = keys.iterator(); iter.hasNext();)
    {
      String key = (String)iter.next();
      String value = props.getProperty(key);
      if (key.startsWith(prefix))
      {
        String name = StrUtils.removePrefix(key, prefix);
        
        int id = getEntityId(name);
        int color = parseColor(value);
        if ((id < 0) || (color < 0))
        {
          warn("Invalid spawn egg color: " + key + " = " + value);
        }
        else
        {
          while (list.size() <= id) {
            list.add(Integer.valueOf(-1));
          }
          list.set(id, Integer.valueOf(color));
          
          countColors++;
        }
      }
    }
    if (countColors <= 0) {
      return null;
    }
    dbg(logName + " colors: " + countColors);
    
    int[] colors = new int[list.size()];
    for (int i = 0; i < colors.length; i++) {
      colors[i] = ((Integer)list.get(i)).intValue();
    }
    return colors;
  }
  
  private static int getSpawnEggColor(aeu item, adq itemStack, int layer, int color)
  {
    int id = itemStack.i();
    int[] eggColors = layer == 0 ? spawnEggPrimaryColors : spawnEggSecondaryColors;
    if (eggColors == null) {
      return color;
    }
    if ((id < 0) || (id >= eggColors.length)) {
      return color;
    }
    int eggColor = eggColors[id];
    if (eggColor < 0) {
      return color;
    }
    return eggColor;
  }
  
  public static int getColorFromItemStack(adq itemStack, int layer, int color)
  {
    if (itemStack == null) {
      return color;
    }
    ado item = itemStack.b();
    if (item == null) {
      return color;
    }
    if ((item instanceof aeu)) {
      return getSpawnEggColor((aeu)item, itemStack, layer, color);
    }
    return color;
  }
  
  private static float[][] readDyeColors(Properties props, String fileName, String prefix, String logName)
  {
    act[] dyeValues = act.values();
    Map<String, act> mapDyes = new HashMap();
    for (int i = 0; i < dyeValues.length; i++)
    {
      act dye = dyeValues[i];
      mapDyes.put(dye.m(), dye);
    }
    float[][] colors = new float[dyeValues.length][];
    int countColors = 0;
    
    Set keys = props.keySet();
    for (Iterator iter = keys.iterator(); iter.hasNext();)
    {
      String key = (String)iter.next();
      String value = props.getProperty(key);
      if (key.startsWith(prefix))
      {
        String name = StrUtils.removePrefix(key, prefix);
        if (name.equals("lightBlue")) {
          name = "light_blue";
        }
        act dye = (act)mapDyes.get(name);
        int color = parseColor(value);
        if ((dye == null) || (color < 0))
        {
          warn("Invalid color: " + key + " = " + value);
        }
        else
        {
          float[] rgb = new float[3];
          rgb[0] = ((color >> 16 & 0xFF) / 255.0F);
          rgb[1] = ((color >> 8 & 0xFF) / 255.0F);
          rgb[2] = ((color & 0xFF) / 255.0F);
          
          colors[dye.ordinal()] = rgb;
          
          countColors++;
        }
      }
    }
    if (countColors <= 0) {
      return (float[][])null;
    }
    dbg(logName + " colors: " + countColors);
    
    return colors;
  }
  
  private static float[] getDyeColors(act dye, float[][] dyeColors, float[] colors)
  {
    if (dyeColors == null) {
      return colors;
    }
    if (dye == null) {
      return colors;
    }
    float[] customColors = dyeColors[dye.ordinal()];
    if (customColors == null) {
      return colors;
    }
    return customColors;
  }
  
  public static float[] getWolfCollarColors(act dye, float[] colors)
  {
    return getDyeColors(dye, wolfCollarColors, colors);
  }
  
  public static float[] getSheepColors(act dye, float[] colors)
  {
    return getDyeColors(dye, sheepColors, colors);
  }
  
  private static int[] readTextColors(Properties props, String fileName, String prefix, String logName)
  {
    int[] colors = new int[32];
    Arrays.fill(colors, -1);
    int countColors = 0;
    
    Set keys = props.keySet();
    for (Iterator iter = keys.iterator(); iter.hasNext();)
    {
      String key = (String)iter.next();
      String value = props.getProperty(key);
      if (key.startsWith(prefix))
      {
        String name = StrUtils.removePrefix(key, prefix);
        
        int code = Config.parseInt(name, -1);
        int color = parseColor(value);
        if ((code < 0) || (code >= colors.length) || (color < 0))
        {
          warn("Invalid color: " + key + " = " + value);
        }
        else
        {
          colors[code] = color;
          
          countColors++;
        }
      }
    }
    if (countColors <= 0) {
      return null;
    }
    dbg(logName + " colors: " + countColors);
    
    return colors;
  }
  
  public static int getTextColor(int index, int color)
  {
    if (textColors == null) {
      return color;
    }
    if ((index < 0) || (index >= textColors.length)) {
      return color;
    }
    int customColor = textColors[index];
    if (customColor < 0) {
      return color;
    }
    return customColor;
  }
  
  private static int[] readMapColors(Properties props, String fileName, String prefix, String logName)
  {
    int[] colors = new int[axf.a.length];
    Arrays.fill(colors, -1);
    int countColors = 0;
    
    Set keys = props.keySet();
    for (Iterator iter = keys.iterator(); iter.hasNext();)
    {
      String key = (String)iter.next();
      String value = props.getProperty(key);
      if (key.startsWith(prefix))
      {
        String name = StrUtils.removePrefix(key, prefix);
        
        int index = getMapColorIndex(name);
        int color = parseColor(value);
        if ((index < 0) || (index >= colors.length) || (color < 0))
        {
          warn("Invalid color: " + key + " = " + value);
        }
        else
        {
          colors[index] = color;
          
          countColors++;
        }
      }
    }
    if (countColors <= 0) {
      return null;
    }
    dbg(logName + " colors: " + countColors);
    
    return colors;
  }
  
  private static int[] readPotionColors(Properties props, String fileName, String prefix, String logName)
  {
    int[] colors = new int[getMaxPotionId()];
    Arrays.fill(colors, -1);
    int countColors = 0;
    
    Set keys = props.keySet();
    for (Iterator iter = keys.iterator(); iter.hasNext();)
    {
      String key = (String)iter.next();
      String value = props.getProperty(key);
      if (key.startsWith(prefix))
      {
        String name = key;
        
        int index = getPotionId(name);
        int color = parseColor(value);
        if ((index < 0) || (index >= colors.length) || (color < 0))
        {
          warn("Invalid color: " + key + " = " + value);
        }
        else
        {
          colors[index] = color;
          
          countColors++;
        }
      }
    }
    if (countColors <= 0) {
      return null;
    }
    dbg(logName + " colors: " + countColors);
    
    return colors;
  }
  
  private static int getMaxPotionId()
  {
    int maxId = 0;
    Set<kk> keys = rk.b.c();
    for (Iterator it = keys.iterator(); it.hasNext();)
    {
      kk rl = (kk)it.next();
      rk potion = (rk)rk.b.c(rl);
      int id = rk.a(potion);
      if (id > maxId) {
        maxId = id;
      }
    }
    return maxId;
  }
  
  private static int getPotionId(String name)
  {
    if (name.equals("potion.water")) {
      return 0;
    }
    name = StrUtils.replacePrefix(name, "potion.", "effect.");
    
    Set<kk> keys = rk.b.c();
    for (Iterator it = keys.iterator(); it.hasNext();)
    {
      kk rl = (kk)it.next();
      rk potion = (rk)rk.b.c(rl);
      if (potion.a().equals(name)) {
        return rk.a(potion);
      }
    }
    return -1;
  }
  
  public static int getPotionColor(int potionId, int color)
  {
    if (potionColors == null) {
      return color;
    }
    if ((potionId < 0) || (potionId >= potionColors.length)) {
      return color;
    }
    int potionColor = potionColors[potionId];
    if (potionColor < 0) {
      return color;
    }
    return potionColor;
  }
  
  private static int getMapColorIndex(String name)
  {
    if (name == null) {
      return -1;
    }
    if (name.equals("air")) {
      return axf.b.M;
    }
    if (name.equals("grass")) {
      return axf.c.M;
    }
    if (name.equals("sand")) {
      return axf.d.M;
    }
    if (name.equals("cloth")) {
      return axf.e.M;
    }
    if (name.equals("tnt")) {
      return axf.f.M;
    }
    if (name.equals("ice")) {
      return axf.g.M;
    }
    if (name.equals("iron")) {
      return axf.h.M;
    }
    if (name.equals("foliage")) {
      return axf.i.M;
    }
    if (name.equals("snow")) {
      return axf.j.M;
    }
    if (name.equals("clay")) {
      return axf.k.M;
    }
    if (name.equals("dirt")) {
      return axf.l.M;
    }
    if (name.equals("stone")) {
      return axf.m.M;
    }
    if (name.equals("water")) {
      return axf.n.M;
    }
    if (name.equals("wood")) {
      return axf.o.M;
    }
    if (name.equals("quartz")) {
      return axf.p.M;
    }
    if (name.equals("adobe")) {
      return axf.q.M;
    }
    if (name.equals("magenta")) {
      return axf.r.M;
    }
    if (name.equals("lightBlue")) {
      return axf.s.M;
    }
    if (name.equals("light_blue")) {
      return axf.s.M;
    }
    if (name.equals("yellow")) {
      return axf.t.M;
    }
    if (name.equals("lime")) {
      return axf.u.M;
    }
    if (name.equals("pink")) {
      return axf.v.M;
    }
    if (name.equals("gray")) {
      return axf.w.M;
    }
    if (name.equals("silver")) {
      return axf.x.M;
    }
    if (name.equals("cyan")) {
      return axf.y.M;
    }
    if (name.equals("purple")) {
      return axf.z.M;
    }
    if (name.equals("blue")) {
      return axf.A.M;
    }
    if (name.equals("brown")) {
      return axf.B.M;
    }
    if (name.equals("green")) {
      return axf.C.M;
    }
    if (name.equals("red")) {
      return axf.D.M;
    }
    if (name.equals("black")) {
      return axf.E.M;
    }
    if (name.equals("gold")) {
      return axf.F.M;
    }
    if (name.equals("diamond")) {
      return axf.G.M;
    }
    if (name.equals("lapis")) {
      return axf.H.M;
    }
    if (name.equals("emerald")) {
      return axf.I.M;
    }
    if (name.equals("obsidian")) {
      return axf.J.M;
    }
    if (name.equals("netherrack")) {
      return axf.K.M;
    }
    return -1;
  }
  
  private static int[] getMapColors()
  {
    axf[] mapColors = axf.a;
    int[] colors = new int[mapColors.length];
    Arrays.fill(colors, -1);
    for (int i = 0; (i < mapColors.length) && (i < colors.length); i++)
    {
      axf mapColor = mapColors[i];
      if (mapColor != null) {
        colors[i] = mapColor.L;
      }
    }
    return colors;
  }
  
  private static void setMapColors(int[] colors)
  {
    if (colors == null) {
      return;
    }
    axf[] mapColors = axf.a;
    for (int i = 0; (i < mapColors.length) && (i < colors.length); i++)
    {
      axf mapColor = mapColors[i];
      if (mapColor != null)
      {
        int color = colors[i];
        if (color >= 0) {
          mapColor.L = color;
        }
      }
    }
  }
  
  private static int getEntityId(String name)
  {
    if (name == null) {
      return -1;
    }
    int id = rt.a(name);
    if (id < 0) {
      return -1;
    }
    Class cls = rt.a(id);
    if (cls == null) {
      return -1;
    }
    String idName = rt.a(cls);
    if (!Config.equals(name, idName)) {
      return -1;
    }
    return id;
  }
  
  private static void dbg(String str)
  {
    Config.dbg("CustomColors: " + str);
  }
  
  private static void warn(String str)
  {
    Config.warn("CustomColors: " + str);
  }
  
  public static int getExpBarTextColor(int color)
  {
    if (expBarTextColor < 0) {
      return color;
    }
    return expBarTextColor;
  }
  
  public static int getBossTextColor(int color)
  {
    if (bossTextColor < 0) {
      return color;
    }
    return bossTextColor;
  }
  
  public static int getSignTextColor(int color)
  {
    if (signTextColor < 0) {
      return color;
    }
    return signTextColor;
  }
  
  public static abstract interface IColorizer
  {
    public abstract int getColor(arc paramarc, ahx paramahx, cj paramcj);
    
    public abstract boolean isColorConstant();
  }
}
