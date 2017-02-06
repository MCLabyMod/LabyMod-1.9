import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.IntBuffer;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import shadersmod.client.MultiTexID;
import shadersmod.client.Shaders;

public class TextureUtils
{
  public static final String texGrassTop = "grass_top";
  public static final String texStone = "stone";
  public static final String texDirt = "dirt";
  public static final String texCoarseDirt = "coarse_dirt";
  public static final String texGrassSide = "grass_side";
  public static final String texStoneslabSide = "stone_slab_side";
  public static final String texStoneslabTop = "stone_slab_top";
  public static final String texBedrock = "bedrock";
  public static final String texSand = "sand";
  public static final String texGravel = "gravel";
  public static final String texLogOak = "log_oak";
  public static final String texLogBigOak = "log_big_oak";
  public static final String texLogAcacia = "log_acacia";
  public static final String texLogSpruce = "log_spruce";
  public static final String texLogBirch = "log_birch";
  public static final String texLogJungle = "log_jungle";
  public static final String texLogOakTop = "log_oak_top";
  public static final String texLogBigOakTop = "log_big_oak_top";
  public static final String texLogAcaciaTop = "log_acacia_top";
  public static final String texLogSpruceTop = "log_spruce_top";
  public static final String texLogBirchTop = "log_birch_top";
  public static final String texLogJungleTop = "log_jungle_top";
  public static final String texLeavesOak = "leaves_oak";
  public static final String texLeavesBigOak = "leaves_big_oak";
  public static final String texLeavesAcacia = "leaves_acacia";
  public static final String texLeavesBirch = "leaves_birch";
  public static final String texLeavesSpuce = "leaves_spruce";
  public static final String texLeavesJungle = "leaves_jungle";
  public static final String texGoldOre = "gold_ore";
  public static final String texIronOre = "iron_ore";
  public static final String texCoalOre = "coal_ore";
  public static final String texObsidian = "obsidian";
  public static final String texGrassSideOverlay = "grass_side_overlay";
  public static final String texSnow = "snow";
  public static final String texGrassSideSnowed = "grass_side_snowed";
  public static final String texMyceliumSide = "mycelium_side";
  public static final String texMyceliumTop = "mycelium_top";
  public static final String texDiamondOre = "diamond_ore";
  public static final String texRedstoneOre = "redstone_ore";
  public static final String texLapisOre = "lapis_ore";
  public static final String texCactusSide = "cactus_side";
  public static final String texClay = "clay";
  public static final String texFarmlandWet = "farmland_wet";
  public static final String texFarmlandDry = "farmland_dry";
  public static final String texNetherrack = "netherrack";
  public static final String texSoulSand = "soul_sand";
  public static final String texGlowstone = "glowstone";
  public static final String texLeavesSpruce = "leaves_spruce";
  public static final String texLeavesSpruceOpaque = "leaves_spruce_opaque";
  public static final String texEndStone = "end_stone";
  public static final String texSandstoneTop = "sandstone_top";
  public static final String texSandstoneBottom = "sandstone_bottom";
  public static final String texRedstoneLampOff = "redstone_lamp_off";
  public static final String texRedstoneLampOn = "redstone_lamp_on";
  public static final String texWaterStill = "water_still";
  public static final String texWaterFlow = "water_flow";
  public static final String texLavaStill = "lava_still";
  public static final String texLavaFlow = "lava_flow";
  public static final String texFireLayer0 = "fire_layer_0";
  public static final String texFireLayer1 = "fire_layer_1";
  public static final String texPortal = "portal";
  public static final String texGlass = "glass";
  public static final String texGlassPaneTop = "glass_pane_top";
  public static final String texCompass = "compass";
  public static final String texClock = "clock";
  public static bvh iconGrassTop;
  public static bvh iconGrassSide;
  public static bvh iconGrassSideOverlay;
  public static bvh iconSnow;
  public static bvh iconGrassSideSnowed;
  public static bvh iconMyceliumSide;
  public static bvh iconMyceliumTop;
  public static bvh iconWaterStill;
  public static bvh iconWaterFlow;
  public static bvh iconLavaStill;
  public static bvh iconLavaFlow;
  public static bvh iconPortal;
  public static bvh iconFireLayer0;
  public static bvh iconFireLayer1;
  public static bvh iconGlass;
  public static bvh iconGlassPaneTop;
  public static bvh iconCompass;
  public static bvh iconClock;
  public static final String SPRITE_PREFIX_BLOCKS = "minecraft:blocks/";
  public static final String SPRITE_PREFIX_ITEMS = "minecraft:items/";
  private static IntBuffer staticBuffer = bce.f(256);
  
  public static void update()
  {
    bvg mapBlocks = getTextureMapBlocks();
    if (mapBlocks == null) {
      return;
    }
    String prefix = "minecraft:blocks/";
    iconGrassTop = mapBlocks.getSpriteSafe(prefix + "grass_top");
    iconGrassSide = mapBlocks.getSpriteSafe(prefix + "grass_side");
    iconGrassSideOverlay = mapBlocks.getSpriteSafe(prefix + "grass_side_overlay");
    iconSnow = mapBlocks.getSpriteSafe(prefix + "snow");
    iconGrassSideSnowed = mapBlocks.getSpriteSafe(prefix + "grass_side_snowed");
    iconMyceliumSide = mapBlocks.getSpriteSafe(prefix + "mycelium_side");
    iconMyceliumTop = mapBlocks.getSpriteSafe(prefix + "mycelium_top");
    
    iconWaterStill = mapBlocks.getSpriteSafe(prefix + "water_still");
    iconWaterFlow = mapBlocks.getSpriteSafe(prefix + "water_flow");
    iconLavaStill = mapBlocks.getSpriteSafe(prefix + "lava_still");
    iconLavaFlow = mapBlocks.getSpriteSafe(prefix + "lava_flow");
    iconFireLayer0 = mapBlocks.getSpriteSafe(prefix + "fire_layer_0");
    iconFireLayer1 = mapBlocks.getSpriteSafe(prefix + "fire_layer_1");
    iconPortal = mapBlocks.getSpriteSafe(prefix + "portal");
    
    iconGlass = mapBlocks.getSpriteSafe(prefix + "glass");
    iconGlassPaneTop = mapBlocks.getSpriteSafe(prefix + "glass_pane_top");
    
    String prefixItems = "minecraft:items/";
    iconCompass = mapBlocks.getSpriteSafe(prefixItems + "compass");
    iconClock = mapBlocks.getSpriteSafe(prefixItems + "clock");
  }
  
  public static BufferedImage fixTextureDimensions(String name, BufferedImage bi)
  {
    if ((name.startsWith("/mob/zombie")) || (name.startsWith("/mob/pigzombie")))
    {
      int width = bi.getWidth();
      int height = bi.getHeight();
      if (width == height * 2)
      {
        BufferedImage scaledImage = new BufferedImage(width, height * 2, 2);
        Graphics2D gr = scaledImage.createGraphics();
        
        gr.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        gr.drawImage(bi, 0, 0, width, height, null);
        return scaledImage;
      }
    }
    return bi;
  }
  
  public static int ceilPowerOfTwo(int val)
  {
    int i = 1;
    while (i < val) {
      i *= 2;
    }
    return i;
  }
  
  public static int getPowerOfTwo(int val)
  {
    int i = 1;
    int po2 = 0;
    while (i < val)
    {
      i *= 2;
      po2++;
    }
    return po2;
  }
  
  public static int twoToPower(int power)
  {
    int val = 1;
    for (int i = 0; i < power; i++) {
      val *= 2;
    }
    return val;
  }
  
  public static void refreshBlockTextures() {}
  
  public static bvj getTexture(String path)
  {
    return getTexture(new kk(path));
  }
  
  public static bvj getTexture(kk loc)
  {
    bvj tex = Config.getTextureManager().b(loc);
    if (tex != null) {
      return tex;
    }
    if (!Config.hasResource(loc)) {
      return null;
    }
    tex = new bvd(loc);
    Config.getTextureManager().a(loc, tex);
    
    return tex;
  }
  
  public static void resourcesReloaded(bwg rm)
  {
    if (getTextureMapBlocks() == null) {
      return;
    }
    Config.dbg("*** Reloading custom textures ***");
    
    CustomSky.reset();
    TextureAnimations.reset();
    
    update();
    NaturalTextures.update();
    BetterGrass.update();
    BetterSnow.update();
    
    TextureAnimations.update();
    CustomColors.update();
    CustomSky.update();
    RandomMobs.resetTextures();
    CustomItems.updateModels();
    
    Shaders.resourcesReloaded();
    
    Lang.resourcesReloaded();
    
    Config.updateTexturePackClouds();
    
    SmartLeaves.updateLeavesModels();
    
    Config.getTextureManager().e();
  }
  
  public static bvg getTextureMapBlocks()
  {
    return bcf.z().R();
  }
  
  public static void registerResourceListener()
  {
    bwg rm = Config.getResourceManager();
    if ((rm instanceof bwe))
    {
      bwe rrm = (bwe)rm;
      
      bwh rl = new bwh()
      {
        public void a(bwg var1)
        {
          TextureUtils.resourcesReloaded(var1);
        }
      };
      rrm.a(rl);
    }
    bvm tto = new bvm()
    {
      public void e() {}
      
      public void a(bwg var1)
        throws IOException
      {}
      
      public int b()
      {
        return 0;
      }
      
      public void b(boolean p_174936_1, boolean p_174936_2) {}
      
      public void a() {}
      
      public MultiTexID getMultiTexID()
      {
        return null;
      }
    };
    kk ttol = new kk("optifine/TickableTextures");
    Config.getTextureManager().a(ttol, tto);
  }
  
  public static String fixResourcePath(String path, String basePath)
  {
    String strAssMc = "assets/minecraft/";
    if (path.startsWith(strAssMc))
    {
      path = path.substring(strAssMc.length());
      return path;
    }
    if (path.startsWith("./"))
    {
      path = path.substring(2);
      if (!basePath.endsWith("/")) {
        basePath = basePath + "/";
      }
      path = basePath + path;
      return path;
    }
    if (path.startsWith("/~")) {
      path = path.substring(1);
    }
    String strMcpatcher = "mcpatcher/";
    if (path.startsWith("~/"))
    {
      path = path.substring(2);
      
      path = strMcpatcher + path;
      return path;
    }
    if (path.startsWith("/"))
    {
      path = strMcpatcher + path.substring(1);
      return path;
    }
    return path;
  }
  
  public static String getBasePath(String path)
  {
    int pos = path.lastIndexOf('/');
    if (pos < 0) {
      return "";
    }
    return path.substring(0, pos);
  }
  
  public static void applyAnisotropicLevel()
  {
    if (GLContext.getCapabilities().GL_EXT_texture_filter_anisotropic)
    {
      float maxLevel = GL11.glGetFloat(34047);
      float level = Config.getAnisotropicFilterLevel();
      level = Math.min(level, maxLevel);
      GL11.glTexParameterf(3553, 34046, level);
    }
  }
  
  public static void bindTexture(int glTexId)
  {
    bni.i(glTexId);
  }
  
  public static boolean isPowerOfTwo(int x)
  {
    int x2 = on.c(x);
    return x2 == x;
  }
  
  public static BufferedImage scaleToPowerOfTwo(BufferedImage bi, int minSize)
  {
    if (bi == null) {
      return bi;
    }
    int w = bi.getWidth();
    int h = bi.getHeight();
    
    int w2 = Math.max(w, minSize);
    w2 = on.c(w2);
    if (w2 == w) {
      return bi;
    }
    int h2 = h * w2 / w;
    
    BufferedImage bi2 = new BufferedImage(w2, h2, 2);
    Graphics2D g2 = bi2.createGraphics();
    
    Object method = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
    if (w2 % w != 0) {
      method = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
    }
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, method);
    g2.drawImage(bi, 0, 0, w2, h2, null);
    
    return bi2;
  }
  
  public static BufferedImage scaleMinTo(BufferedImage bi, int minSize)
  {
    if (bi == null) {
      return bi;
    }
    int w = bi.getWidth();
    int h = bi.getHeight();
    if (w >= minSize) {
      return bi;
    }
    int w2 = w;
    while (w2 < minSize) {
      w2 *= 2;
    }
    int h2 = h * w2 / w;
    
    BufferedImage bi2 = new BufferedImage(w2, h2, 2);
    Graphics2D g2 = bi2.createGraphics();
    
    Object method = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
    
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, method);
    g2.drawImage(bi, 0, 0, w2, h2, null);
    
    return bi2;
  }
  
  public static Dimension getImageSize(InputStream in, String suffix)
  {
    Iterator iter = ImageIO.getImageReadersBySuffix(suffix);
    while (iter.hasNext())
    {
      ImageReader reader = (ImageReader)iter.next();
      try
      {
        ImageInputStream iis = ImageIO.createImageInputStream(in);
        reader.setInput(iis);
        int width = reader.getWidth(reader.getMinIndex());
        int height = reader.getHeight(reader.getMinIndex());
        
        return new Dimension(width, height);
      }
      catch (IOException e)
      {
        reader.dispose();
      }
      finally
      {
        reader.dispose();
      }
    }
    return null;
  }
}
