package shadersmod.client;

import Config;
import Lang;
import PropertiesOrdered;
import Reflector;
import ReflectorClass;
import ReflectorField;
import ado;
import adq;
import aht;
import ajt;
import aob;
import asv;
import asw;
import axe;
import bbj;
import bcd;
import bcf;
import bch;
import bcu;
import bda;
import bjc;
import bkm;
import bku;
import bmt;
import bmz;
import bng;
import bni;
import bni.m;
import bno;
import bnt;
import bnu;
import brm;
import brn;
import bsg;
import bvj;
import bvp;
import bzg;
import co;
import cx;
import fa;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kk;
import oo;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.GLU;
import rr;
import sa;
import shadersmod.common.SMCLog;

public class Shaders
{
  static bcf mc;
  static bng entityRenderer;
  public static boolean isInitializedOnce = false;
  public static boolean isShaderPackInitialized = false;
  public static ContextCapabilities capabilities;
  public static String glVersionString;
  public static String glVendorString;
  public static String glRendererString;
  public static boolean hasGlGenMipmap = false;
  public static boolean hasForge = false;
  public static int numberResetDisplayList = 0;
  static boolean needResetModels = false;
  private static int renderDisplayWidth = 0;
  private static int renderDisplayHeight = 0;
  public static int renderWidth = 0;
  public static int renderHeight = 0;
  public static boolean isRenderingWorld = false;
  public static boolean isRenderingSky = false;
  public static boolean isCompositeRendered = false;
  public static boolean isRenderingDfb = false;
  public static boolean isShadowPass = false;
  public static boolean isSleeping;
  public static boolean isHandRendered;
  public static boolean renderItemPass1DepthMask = false;
  public static adq itemToRender;
  static float[] sunPosition = new float[4];
  static float[] moonPosition = new float[4];
  static float[] shadowLightPosition = new float[4];
  static float[] upPosition = new float[4];
  static float[] shadowLightPositionVector = new float[4];
  static float[] upPosModelView = { 0.0F, 100.0F, 0.0F, 0.0F };
  static float[] sunPosModelView = { 0.0F, 100.0F, 0.0F, 0.0F };
  static float[] moonPosModelView = { 0.0F, -100.0F, 0.0F, 0.0F };
  private static float[] tempMat = new float[16];
  static float clearColorR;
  static float clearColorG;
  static float clearColorB;
  static float skyColorR;
  static float skyColorG;
  static float skyColorB;
  static long worldTime = 0L;
  static long lastWorldTime = 0L;
  static long diffWorldTime = 0L;
  static float celestialAngle = 0.0F;
  static float sunAngle = 0.0F;
  static float shadowAngle = 0.0F;
  static int moonPhase = 0;
  static long systemTime = 0L;
  static long lastSystemTime = 0L;
  static long diffSystemTime = 0L;
  static int frameCounter = 0;
  static float frameTimeCounter = 0.0F;
  static int systemTimeInt32 = 0;
  static float rainStrength = 0.0F;
  static float wetness = 0.0F;
  public static float wetnessHalfLife = 600.0F;
  public static float drynessHalfLife = 200.0F;
  public static float eyeBrightnessHalflife = 10.0F;
  static boolean usewetness = false;
  static int isEyeInWater = 0;
  static int eyeBrightness = 0;
  static float eyeBrightnessFadeX = 0.0F;
  static float eyeBrightnessFadeY = 0.0F;
  static float eyePosY = 0.0F;
  static float centerDepth = 0.0F;
  static float centerDepthSmooth = 0.0F;
  static float centerDepthSmoothHalflife = 1.0F;
  static boolean centerDepthSmoothEnabled = false;
  static int superSamplingLevel = 1;
  static boolean updateChunksErrorRecorded = false;
  static boolean lightmapEnabled = false;
  static boolean fogEnabled = true;
  public static int entityAttrib = 10;
  public static int midTexCoordAttrib = 11;
  public static int tangentAttrib = 12;
  public static boolean useEntityAttrib = false;
  public static boolean useMidTexCoordAttrib = false;
  public static boolean useMultiTexCoord3Attrib = false;
  public static boolean useTangentAttrib = false;
  public static boolean progUseEntityAttrib = false;
  public static boolean progUseMidTexCoordAttrib = false;
  public static boolean progUseTangentAttrib = false;
  public static int atlasSizeX = 0;
  public static int atlasSizeY = 0;
  public static int uniformEntityHurt = -1;
  public static int uniformEntityFlash = -1;
  public static boolean useEntityHurtFlash;
  static double previousCameraPositionX;
  static double previousCameraPositionY;
  static double previousCameraPositionZ;
  static double cameraPositionX;
  static double cameraPositionY;
  static double cameraPositionZ;
  static int shadowPassInterval = 0;
  public static boolean needResizeShadow = false;
  static int shadowMapWidth = 1024;
  static int shadowMapHeight = 1024;
  static int spShadowMapWidth = 1024;
  static int spShadowMapHeight = 1024;
  static float shadowMapFOV = 90.0F;
  static float shadowMapHalfPlane = 160.0F;
  static boolean shadowMapIsOrtho = true;
  static int shadowPassCounter = 0;
  static int preShadowPassThirdPersonView;
  public static boolean shouldSkipDefaultShadow = false;
  static boolean waterShadowEnabled = false;
  static final int MaxDrawBuffers = 8;
  static final int MaxColorBuffers = 8;
  static final int MaxDepthBuffers = 3;
  static final int MaxShadowColorBuffers = 8;
  static final int MaxShadowDepthBuffers = 2;
  static int usedColorBuffers = 0;
  static int usedDepthBuffers = 0;
  static int usedShadowColorBuffers = 0;
  static int usedShadowDepthBuffers = 0;
  static int usedColorAttachs = 0;
  static int usedDrawBuffers = 0;
  static int dfb = 0;
  static int sfb = 0;
  private static int[] gbuffersFormat = new int[8];
  public static int activeProgram = 0;
  public static final int ProgramNone = 0;
  public static final int ProgramBasic = 1;
  public static final int ProgramTextured = 2;
  public static final int ProgramTexturedLit = 3;
  public static final int ProgramSkyBasic = 4;
  public static final int ProgramSkyTextured = 5;
  public static final int ProgramClouds = 6;
  public static final int ProgramTerrain = 7;
  public static final int ProgramTerrainSolid = 8;
  public static final int ProgramTerrainCutoutMip = 9;
  public static final int ProgramTerrainCutout = 10;
  public static final int ProgramDamagedBlock = 11;
  public static final int ProgramWater = 12;
  public static final int ProgramBlock = 13;
  public static final int ProgramBeaconBeam = 14;
  public static final int ProgramItem = 15;
  public static final int ProgramEntities = 16;
  public static final int ProgramArmorGlint = 17;
  public static final int ProgramSpiderEyes = 18;
  public static final int ProgramHand = 19;
  public static final int ProgramWeather = 20;
  public static final int ProgramComposite = 21;
  public static final int ProgramComposite1 = 22;
  public static final int ProgramComposite2 = 23;
  public static final int ProgramComposite3 = 24;
  public static final int ProgramComposite4 = 25;
  public static final int ProgramComposite5 = 26;
  public static final int ProgramComposite6 = 27;
  public static final int ProgramComposite7 = 28;
  public static final int ProgramFinal = 29;
  public static final int ProgramShadow = 30;
  public static final int ProgramShadowSolid = 31;
  public static final int ProgramShadowCutout = 32;
  public static final int ProgramCount = 33;
  public static final int MaxCompositePasses = 8;
  private static final String[] programNames = { "", "gbuffers_basic", "gbuffers_textured", "gbuffers_textured_lit", "gbuffers_skybasic", "gbuffers_skytextured", "gbuffers_clouds", "gbuffers_terrain", "gbuffers_terrain_solid", "gbuffers_terrain_cutout_mip", "gbuffers_terrain_cutout", "gbuffers_damagedblock", "gbuffers_water", "gbuffers_block", "gbuffers_beaconbeam", "gbuffers_item", "gbuffers_entities", "gbuffers_armor_glint", "gbuffers_spidereyes", "gbuffers_hand", "gbuffers_weather", "composite", "composite1", "composite2", "composite3", "composite4", "composite5", "composite6", "composite7", "final", "shadow", "shadow_solid", "shadow_cutout" };
  private static final int[] programBackups = { 0, 0, 1, 2, 1, 2, 2, 3, 7, 7, 7, 7, 7, 7, 2, 3, 3, 2, 2, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 30 };
  static int[] programsID = new int[33];
  private static int[] programsRef = new int[33];
  private static int programIDCopyDepth = 0;
  private static String[] programsDrawBufSettings = new String[33];
  private static String newDrawBufSetting = null;
  static IntBuffer[] programsDrawBuffers = new IntBuffer[33];
  static IntBuffer activeDrawBuffers = null;
  private static String[] programsColorAtmSettings = new String[33];
  private static String newColorAtmSetting = null;
  private static String activeColorAtmSettings = null;
  private static int[] programsCompositeMipmapSetting = new int[33];
  private static int newCompositeMipmapSetting = 0;
  private static int activeCompositeMipmapSetting = 0;
  public static Properties loadedShaders = null;
  public static Properties shadersConfig = null;
  public static bvj defaultTexture = null;
  public static boolean normalMapEnabled = false;
  public static boolean[] shadowHardwareFilteringEnabled = new boolean[2];
  public static boolean[] shadowMipmapEnabled = new boolean[2];
  public static boolean[] shadowFilterNearest = new boolean[2];
  public static boolean[] shadowColorMipmapEnabled = new boolean[8];
  public static boolean[] shadowColorFilterNearest = new boolean[8];
  public static boolean configTweakBlockDamage = true;
  public static boolean configCloudShadow = true;
  public static float configHandDepthMul = 0.125F;
  public static float configRenderResMul = 1.0F;
  public static float configShadowResMul = 1.0F;
  public static int configTexMinFilB = 0;
  public static int configTexMinFilN = 0;
  public static int configTexMinFilS = 0;
  public static int configTexMagFilB = 0;
  public static int configTexMagFilN = 0;
  public static int configTexMagFilS = 0;
  public static boolean configShadowClipFrustrum = true;
  public static boolean configNormalMap = true;
  public static boolean configSpecularMap = true;
  public static PropertyDefaultTrueFalse configOldLighting = new PropertyDefaultTrueFalse("oldLighting", "Classic Lighting", 0);
  public static int configAntialiasingLevel = 0;
  public static final int texMinFilRange = 3;
  public static final int texMagFilRange = 2;
  public static final String[] texMinFilDesc = { "Nearest", "Nearest-Nearest", "Nearest-Linear" };
  public static final String[] texMagFilDesc = { "Nearest", "Linear" };
  public static final int[] texMinFilValue = { 9728, 9984, 9986 };
  public static final int[] texMagFilValue = { 9728, 9729 };
  static IShaderPack shaderPack = null;
  public static boolean shaderPackLoaded = false;
  static File currentshader;
  static String currentshadername;
  public static String packNameNone = "OFF";
  static String packNameDefault = "(internal)";
  static String shaderpacksdirname = "shaderpacks";
  static String optionsfilename = "optionsshaders.txt";
  static File shadersdir = new File(bcf.z().w, "shaders");
  static File shaderpacksdir = new File(bcf.z().w, shaderpacksdirname);
  static File configFile = new File(bcf.z().w, optionsfilename);
  static ShaderOption[] shaderPackOptions = null;
  static ShaderProfile[] shaderPackProfiles = null;
  static Map<String, ShaderOption[]> shaderPackGuiScreens = null;
  public static PropertyDefaultFastFancyOff shaderPackClouds = new PropertyDefaultFastFancyOff("clouds", "Clouds", 0);
  public static PropertyDefaultTrueFalse shaderPackOldLighting = new PropertyDefaultTrueFalse("oldLighting", "Classic Lighting", 0);
  private static Map<String, String> shaderPackResources = new HashMap();
  private static aht currentWorld = null;
  private static List<Integer> shaderPackDimensions = new ArrayList();
  public static final boolean enableShadersOption = true;
  private static final boolean enableShadersDebug = true;
  private static final boolean saveFinalShaders = System.getProperty("shaders.debug.save", "false").equals("true");
  public static float blockLightLevel05 = 0.5F;
  public static float blockLightLevel06 = 0.6F;
  public static float blockLightLevel08 = 0.8F;
  public static float aoLevel = 0.8F;
  public static float blockAoLight = 1.0F - aoLevel;
  public static float sunPathRotation = 0.0F;
  public static float shadowAngleInterval = 0.0F;
  public static int fogMode = 0;
  public static float fogColorR;
  public static float fogColorG;
  public static float fogColorB;
  public static float shadowIntervalSize = 2.0F;
  public static int terrainIconSize = 16;
  public static int[] terrainTextureSize = new int[2];
  private static HFNoiseTexture noiseTexture;
  private static boolean noiseTextureEnabled = false;
  private static int noiseTextureResolution = 256;
  static final int[] dfbColorTexturesA = new int[16];
  static final int[] colorTexturesToggle = new int[8];
  static final int[] colorTextureTextureImageUnit = { 0, 1, 2, 3, 7, 8, 9, 10 };
  static final boolean[][] programsToggleColorTextures = new boolean[33][8];
  private static final int bigBufferSize = 2196;
  private static final ByteBuffer bigBuffer = (ByteBuffer)BufferUtils.createByteBuffer(2196).limit(0);
  static final float[] faProjection = new float[16];
  static final float[] faProjectionInverse = new float[16];
  static final float[] faModelView = new float[16];
  static final float[] faModelViewInverse = new float[16];
  static final float[] faShadowProjection = new float[16];
  static final float[] faShadowProjectionInverse = new float[16];
  static final float[] faShadowModelView = new float[16];
  static final float[] faShadowModelViewInverse = new float[16];
  static final FloatBuffer projection = nextFloatBuffer(16);
  static final FloatBuffer projectionInverse = nextFloatBuffer(16);
  static final FloatBuffer modelView = nextFloatBuffer(16);
  static final FloatBuffer modelViewInverse = nextFloatBuffer(16);
  static final FloatBuffer shadowProjection = nextFloatBuffer(16);
  static final FloatBuffer shadowProjectionInverse = nextFloatBuffer(16);
  static final FloatBuffer shadowModelView = nextFloatBuffer(16);
  static final FloatBuffer shadowModelViewInverse = nextFloatBuffer(16);
  static final FloatBuffer previousProjection = nextFloatBuffer(16);
  static final FloatBuffer previousModelView = nextFloatBuffer(16);
  static final FloatBuffer tempMatrixDirectBuffer = nextFloatBuffer(16);
  static final FloatBuffer tempDirectFloatBuffer = nextFloatBuffer(16);
  static final IntBuffer dfbColorTextures = nextIntBuffer(16);
  static final IntBuffer dfbDepthTextures = nextIntBuffer(3);
  static final IntBuffer sfbColorTextures = nextIntBuffer(8);
  static final IntBuffer sfbDepthTextures = nextIntBuffer(2);
  static final IntBuffer dfbDrawBuffers = nextIntBuffer(8);
  static final IntBuffer sfbDrawBuffers = nextIntBuffer(8);
  static final IntBuffer drawBuffersNone = nextIntBuffer(8);
  static final IntBuffer drawBuffersAll = nextIntBuffer(8);
  static final IntBuffer drawBuffersClear0 = nextIntBuffer(8);
  static final IntBuffer drawBuffersClear1 = nextIntBuffer(8);
  static final IntBuffer drawBuffersClearColor = nextIntBuffer(8);
  static final IntBuffer drawBuffersColorAtt0 = nextIntBuffer(8);
  static final IntBuffer[] drawBuffersBuffer = nextIntBufferArray(33, 8);
  static Map<ajt, Integer> mapBlockToEntityData;
  
  static
  {
    drawBuffersNone.limit(0);
    drawBuffersColorAtt0.put(36064).position(0).limit(1);
  }
  
  private static ByteBuffer nextByteBuffer(int size)
  {
    ByteBuffer buffer = bigBuffer;
    int pos = buffer.limit();
    buffer.position(pos).limit(pos + size);
    return buffer.slice();
  }
  
  private static IntBuffer nextIntBuffer(int size)
  {
    ByteBuffer buffer = bigBuffer;
    int pos = buffer.limit();
    buffer.position(pos).limit(pos + size * 4);
    return buffer.asIntBuffer();
  }
  
  private static FloatBuffer nextFloatBuffer(int size)
  {
    ByteBuffer buffer = bigBuffer;
    int pos = buffer.limit();
    buffer.position(pos).limit(pos + size * 4);
    return buffer.asFloatBuffer();
  }
  
  private static IntBuffer[] nextIntBufferArray(int count, int size)
  {
    IntBuffer[] aib = new IntBuffer[count];
    for (int i = 0; i < count; i++) {
      aib[i] = nextIntBuffer(size);
    }
    return aib;
  }
  
  public static void loadConfig()
  {
    SMCLog.info("Load ShadersMod configuration.");
    try
    {
      if (!shaderpacksdir.exists()) {
        shaderpacksdir.mkdir();
      }
    }
    catch (Exception e)
    {
      SMCLog.severe("Failed to open the shaderpacks directory: " + shaderpacksdir);
    }
    shadersConfig = new PropertiesOrdered();
    shadersConfig.setProperty(EnumShaderOption.SHADER_PACK.getPropertyKey(), "");
    if (configFile.exists()) {
      try
      {
        FileReader reader = new FileReader(configFile);
        shadersConfig.load(reader);
        reader.close();
      }
      catch (Exception e) {}
    }
    if (!configFile.exists()) {
      try
      {
        storeConfig();
      }
      catch (Exception e) {}
    }
    EnumShaderOption[] ops = EnumShaderOption.values();
    for (int i = 0; i < ops.length; i++)
    {
      EnumShaderOption op = ops[i];
      String key = op.getPropertyKey();
      String def = op.getValueDefault();
      String val = shadersConfig.getProperty(key, def);
      setEnumShaderOption(op, val);
    }
    loadShaderPack();
  }
  
  private static void setEnumShaderOption(EnumShaderOption eso, String str)
  {
    if (str == null) {
      str = eso.getValueDefault();
    }
    switch (eso)
    {
    case ANTIALIASING: 
      configAntialiasingLevel = Config.parseInt(str, 0);
      break;
    case NORMAL_MAP: 
      configNormalMap = Config.parseBoolean(str, true);
      break;
    case SPECULAR_MAP: 
      configSpecularMap = Config.parseBoolean(str, true);
      break;
    case RENDER_RES_MUL: 
      configRenderResMul = Config.parseFloat(str, 1.0F);
      break;
    case SHADOW_RES_MUL: 
      configShadowResMul = Config.parseFloat(str, 1.0F);
      break;
    case HAND_DEPTH_MUL: 
      configHandDepthMul = Config.parseFloat(str, 0.125F);
      break;
    case CLOUD_SHADOW: 
      configCloudShadow = Config.parseBoolean(str, true);
      break;
    case OLD_LIGHTING: 
      configOldLighting.setPropertyValue(str);
      break;
    case SHADER_PACK: 
      currentshadername = str;
      break;
    case TWEAK_BLOCK_DAMAGE: 
      configTweakBlockDamage = Config.parseBoolean(str, true);
      break;
    case SHADOW_CLIP_FRUSTRUM: 
      configShadowClipFrustrum = Config.parseBoolean(str, true);
      break;
    case TEX_MIN_FIL_B: 
      configTexMinFilB = Config.parseInt(str, 0);
      break;
    case TEX_MIN_FIL_N: 
      configTexMinFilN = Config.parseInt(str, 0);
      break;
    case TEX_MIN_FIL_S: 
      configTexMinFilS = Config.parseInt(str, 0);
      break;
    case TEX_MAG_FIL_B: 
      configTexMagFilB = Config.parseInt(str, 0);
      break;
    case TEX_MAG_FIL_N: 
      configTexMagFilB = Config.parseInt(str, 0);
      break;
    case TEX_MAG_FIL_S: 
      configTexMagFilB = Config.parseInt(str, 0);
      break;
    default: 
      throw new IllegalArgumentException("Unknown option: " + eso);
    }
  }
  
  public static void storeConfig()
  {
    SMCLog.info("Save ShadersMod configuration.");
    if (shadersConfig == null) {
      shadersConfig = new PropertiesOrdered();
    }
    EnumShaderOption[] ops = EnumShaderOption.values();
    for (int i = 0; i < ops.length; i++)
    {
      EnumShaderOption op = ops[i];
      String key = op.getPropertyKey();
      String val = getEnumShaderOption(op);
      shadersConfig.setProperty(key, val);
    }
    try
    {
      FileWriter writer = new FileWriter(configFile);
      shadersConfig.store(writer, null);
      writer.close();
    }
    catch (Exception ex)
    {
      SMCLog.severe("Error saving configuration: " + ex.getClass().getName() + ": " + ex.getMessage());
    }
  }
  
  public static String getEnumShaderOption(EnumShaderOption eso)
  {
    switch (eso)
    {
    case ANTIALIASING: 
      return Integer.toString(configAntialiasingLevel);
    case NORMAL_MAP: 
      return Boolean.toString(configNormalMap);
    case SPECULAR_MAP: 
      return Boolean.toString(configSpecularMap);
    case RENDER_RES_MUL: 
      return Float.toString(configRenderResMul);
    case SHADOW_RES_MUL: 
      return Float.toString(configShadowResMul);
    case HAND_DEPTH_MUL: 
      return Float.toString(configHandDepthMul);
    case CLOUD_SHADOW: 
      return Boolean.toString(configCloudShadow);
    case OLD_LIGHTING: 
      return configOldLighting.getPropertyValue();
    case SHADER_PACK: 
      return currentshadername;
    case TWEAK_BLOCK_DAMAGE: 
      return Boolean.toString(configTweakBlockDamage);
    case SHADOW_CLIP_FRUSTRUM: 
      return Boolean.toString(configShadowClipFrustrum);
    case TEX_MIN_FIL_B: 
      return Integer.toString(configTexMinFilB);
    case TEX_MIN_FIL_N: 
      return Integer.toString(configTexMinFilN);
    case TEX_MIN_FIL_S: 
      return Integer.toString(configTexMinFilS);
    case TEX_MAG_FIL_B: 
      return Integer.toString(configTexMagFilB);
    case TEX_MAG_FIL_N: 
      return Integer.toString(configTexMagFilB);
    case TEX_MAG_FIL_S: 
      return Integer.toString(configTexMagFilB);
    }
    throw new IllegalArgumentException("Unknown option: " + eso);
  }
  
  public static void setShaderPack(String par1name)
  {
    currentshadername = par1name;
    shadersConfig.setProperty(EnumShaderOption.SHADER_PACK.getPropertyKey(), par1name);
    loadShaderPack();
  }
  
  public static void loadShaderPack()
  {
    boolean shaderPackLoadedPrev = shaderPackLoaded;
    boolean oldLightingPrev = isOldLighting();
    
    shaderPackLoaded = false;
    if (shaderPack != null)
    {
      shaderPack.close();
      shaderPack = null;
      shaderPackResources.clear();
      shaderPackDimensions.clear();
      shaderPackOptions = null;
      shaderPackProfiles = null;
      shaderPackGuiScreens = null;
      shaderPackClouds.resetValue();
      shaderPackOldLighting.resetValue();
    }
    boolean shadersBlocked = false;
    if (Config.isAntialiasing())
    {
      SMCLog.info("Shaders can not be loaded, Antialiasing is enabled: " + Config.getAntialiasingLevel() + "x");
      shadersBlocked = true;
    }
    if (Config.isAnisotropicFiltering())
    {
      SMCLog.info("Shaders can not be loaded, Anisotropic Filtering is enabled: " + Config.getAnisotropicFilterLevel() + "x");
      shadersBlocked = true;
    }
    if (Config.isFastRender())
    {
      SMCLog.info("Shaders can not be loaded, Fast Render is enabled.");
      shadersBlocked = true;
    }
    String packName = shadersConfig.getProperty(EnumShaderOption.SHADER_PACK.getPropertyKey(), packNameDefault);
    if ((!packName.isEmpty()) && (!packName.equals(packNameNone)) && (!shadersBlocked)) {
      if (packName.equals(packNameDefault))
      {
        shaderPack = new ShaderPackDefault();
        shaderPackLoaded = true;
      }
      else
      {
        try
        {
          File packFile = new File(shaderpacksdir, packName);
          if (packFile.isDirectory())
          {
            shaderPack = new ShaderPackFolder(packName, packFile);
            shaderPackLoaded = true;
          }
          else if ((packFile.isFile()) && (packName.toLowerCase().endsWith(".zip")))
          {
            shaderPack = new ShaderPackZip(packName, packFile);
            shaderPackLoaded = true;
          }
        }
        catch (Exception e) {}
      }
    }
    if (shaderPack != null)
    {
      SMCLog.info("Loaded shaderpack: " + getShaderPackName());
    }
    else
    {
      SMCLog.info("No shaderpack loaded.");
      shaderPack = new ShaderPackNone();
    }
    loadShaderPackResources();
    
    loadShaderPackDimensions();
    
    shaderPackOptions = loadShaderPackOptions();
    
    loadShaderPackProperties();
    
    boolean formatChanged = shaderPackLoaded != shaderPackLoadedPrev;
    boolean oldLightingChanged = isOldLighting() != oldLightingPrev;
    if ((formatChanged) || (oldLightingChanged))
    {
      bvp.updateVertexFormats();
      if (Reflector.LightUtil.exists())
      {
        Reflector.LightUtil_itemConsumer.setValue(null);
        Reflector.LightUtil_tessellator.setValue(null);
      }
      updateBlockLightLevel();
      
      mc.A();
    }
  }
  
  private static void loadShaderPackDimensions()
  {
    shaderPackDimensions.clear();
    
    StringBuffer sb = new StringBuffer();
    for (int i = -128; i <= 128; i++)
    {
      String worldDir = "/shaders/world" + i;
      if (shaderPack.hasDirectory(worldDir))
      {
        shaderPackDimensions.add(Integer.valueOf(i));
        sb.append(" " + i);
      }
    }
    if (sb.length() > 0) {
      Config.dbg("[Shaders] Dimensions:" + sb);
    }
  }
  
  private static void loadShaderPackProperties()
  {
    shaderPackClouds.resetValue();
    shaderPackOldLighting.resetValue();
    if (shaderPack == null) {
      return;
    }
    String path = "/shaders/shaders.properties";
    try
    {
      InputStream in = shaderPack.getResourceAsStream(path);
      if (in == null) {
        return;
      }
      Properties props = new PropertiesOrdered();
      props.load(in);
      in.close();
      
      shaderPackClouds.loadFrom(props);
      shaderPackOldLighting.loadFrom(props);
      
      shaderPackProfiles = ShaderPackParser.parseProfiles(props, shaderPackOptions);
      
      shaderPackGuiScreens = ShaderPackParser.parseGuiScreens(props, shaderPackProfiles, shaderPackOptions);
    }
    catch (IOException e)
    {
      Config.warn("[Shaders] Error reading: " + path);
    }
  }
  
  public static ShaderOption[] getShaderPackOptions(String screenName)
  {
    ShaderOption[] ops = (ShaderOption[])shaderPackOptions.clone();
    if (shaderPackGuiScreens == null)
    {
      if (shaderPackProfiles != null)
      {
        ShaderOptionProfile optionProfile = new ShaderOptionProfile(shaderPackProfiles, ops);
        ops = (ShaderOption[])Config.addObjectToArray(ops, optionProfile, 0);
      }
      ops = getVisibleOptions(ops);
      
      return ops;
    }
    String key = screenName != null ? "screen." + screenName : "screen";
    ShaderOption[] sos = (ShaderOption[])shaderPackGuiScreens.get(key);
    if (sos == null) {
      return new ShaderOption[0];
    }
    List<ShaderOption> list = new ArrayList();
    for (int i = 0; i < sos.length; i++)
    {
      ShaderOption so = sos[i];
      if (so == null)
      {
        list.add(null);
      }
      else if ((so instanceof ShaderOptionRest))
      {
        ShaderOption[] restOps = getShaderOptionsRest(shaderPackGuiScreens, ops);
        list.addAll(Arrays.asList(restOps));
      }
      else
      {
        list.add(so);
      }
    }
    ShaderOption[] sosExp = (ShaderOption[])list.toArray(new ShaderOption[list.size()]);
    
    return sosExp;
  }
  
  private static ShaderOption[] getShaderOptionsRest(Map<String, ShaderOption[]> mapScreens, ShaderOption[] ops)
  {
    Set<String> setNames = new HashSet();
    Set<String> keys = mapScreens.keySet();
    for (Iterator it = keys.iterator(); it.hasNext();)
    {
      String key = (String)it.next();
      ShaderOption[] sos = (ShaderOption[])mapScreens.get(key);
      for (int v = 0; v < sos.length; v++)
      {
        ShaderOption so = sos[v];
        if (so != null) {
          setNames.add(so.getName());
        }
      }
    }
    List<ShaderOption> list = new ArrayList();
    for (int i = 0; i < ops.length; i++)
    {
      ShaderOption so = ops[i];
      if (so.isVisible())
      {
        String name = so.getName();
        if (!setNames.contains(name)) {
          list.add(so);
        }
      }
    }
    ShaderOption[] sos = (ShaderOption[])list.toArray(new ShaderOption[list.size()]);
    
    return sos;
  }
  
  public static ShaderOption getShaderOption(String name)
  {
    return ShaderUtils.getShaderOption(name, shaderPackOptions);
  }
  
  public static ShaderOption[] getShaderPackOptions()
  {
    return shaderPackOptions;
  }
  
  private static ShaderOption[] getVisibleOptions(ShaderOption[] ops)
  {
    List<ShaderOption> list = new ArrayList();
    for (int i = 0; i < ops.length; i++)
    {
      ShaderOption so = ops[i];
      if (so.isVisible()) {
        list.add(so);
      }
    }
    ShaderOption[] sos = (ShaderOption[])list.toArray(new ShaderOption[list.size()]);
    
    return sos;
  }
  
  public static void saveShaderPackOptions()
  {
    saveShaderPackOptions(shaderPackOptions, shaderPack);
  }
  
  private static void saveShaderPackOptions(ShaderOption[] sos, IShaderPack sp)
  {
    Properties props = new Properties();
    if (shaderPackOptions != null) {
      for (int i = 0; i < sos.length; i++)
      {
        ShaderOption so = sos[i];
        if (so.isChanged()) {
          if (so.isEnabled()) {
            props.setProperty(so.getName(), so.getValue());
          }
        }
      }
    }
    try
    {
      saveOptionProperties(sp, props);
    }
    catch (IOException e)
    {
      Config.warn("[Shaders] Error saving configuration for " + shaderPack.getName());
      e.printStackTrace();
    }
  }
  
  private static void saveOptionProperties(IShaderPack sp, Properties props)
    throws IOException
  {
    String path = shaderpacksdirname + "/" + sp.getName() + ".txt";
    File propFile = new File(bcf.z().w, path);
    if (props.isEmpty())
    {
      propFile.delete();
      return;
    }
    FileOutputStream fos = new FileOutputStream(propFile);
    props.store(fos, null);
    fos.flush();
    fos.close();
  }
  
  private static ShaderOption[] loadShaderPackOptions()
  {
    try
    {
      ShaderOption[] sos = ShaderPackParser.parseShaderPackOptions(shaderPack, programNames, shaderPackDimensions);
      Properties props = loadOptionProperties(shaderPack);
      for (int i = 0; i < sos.length; i++)
      {
        ShaderOption so = sos[i];
        String val = props.getProperty(so.getName());
        if (val != null)
        {
          so.resetValue();
          if (!so.setValue(val)) {
            Config.warn("[Shaders] Invalid value, option: " + so.getName() + ", value: " + val);
          }
        }
      }
      return sos;
    }
    catch (IOException e)
    {
      Config.warn("[Shaders] Error reading configuration for " + shaderPack.getName());
      e.printStackTrace();
    }
    return null;
  }
  
  private static Properties loadOptionProperties(IShaderPack sp)
    throws IOException
  {
    Properties props = new Properties();
    
    String path = shaderpacksdirname + "/" + sp.getName() + ".txt";
    File propFile = new File(bcf.z().w, path);
    if ((!propFile.exists()) || (!propFile.isFile()) || (!propFile.canRead())) {
      return props;
    }
    FileInputStream fis = new FileInputStream(propFile);
    props.load(fis);
    fis.close();
    
    return props;
  }
  
  public static ShaderOption[] getChangedOptions(ShaderOption[] ops)
  {
    List<ShaderOption> list = new ArrayList();
    for (int i = 0; i < ops.length; i++)
    {
      ShaderOption op = ops[i];
      if (op.isEnabled()) {
        if (op.isChanged()) {
          list.add(op);
        }
      }
    }
    ShaderOption[] cops = (ShaderOption[])list.toArray(new ShaderOption[list.size()]);
    
    return cops;
  }
  
  private static String applyOptions(String line, ShaderOption[] ops)
  {
    if ((ops == null) || (ops.length <= 0)) {
      return line;
    }
    for (int i = 0; i < ops.length; i++)
    {
      ShaderOption op = ops[i];
      String opName = op.getName();
      if (op.matchesLine(line))
      {
        line = op.getSourceLine();
        
        break;
      }
    }
    return line;
  }
  
  static ArrayList listOfShaders()
  {
    ArrayList<String> list = new ArrayList();
    list.add(packNameNone);
    list.add(packNameDefault);
    try
    {
      if (!shaderpacksdir.exists()) {
        shaderpacksdir.mkdir();
      }
      File[] listOfFiles = shaderpacksdir.listFiles();
      for (int i = 0; i < listOfFiles.length; i++)
      {
        File file = listOfFiles[i];
        String name = file.getName();
        if (file.isDirectory())
        {
          File subDir = new File(file, "shaders");
          if ((subDir.exists()) && (subDir.isDirectory())) {
            list.add(name);
          }
        }
        else if (file.isFile())
        {
          if (name.toLowerCase().endsWith(".zip")) {
            list.add(name);
          }
        }
      }
    }
    catch (Exception e) {}
    return list;
  }
  
  static String versiontostring(int vv)
  {
    String vs = Integer.toString(vv);
    return Integer.toString(Integer.parseInt(vs.substring(1, 3))) + "." + Integer.toString(Integer.parseInt(vs.substring(3, 5))) + "." + Integer.toString(Integer.parseInt(vs.substring(5)));
  }
  
  public static int checkFramebufferStatus(String location)
  {
    int status = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
    if (status != 36053) {
      System.err.format("FramebufferStatus 0x%04X at %s\n", new Object[] { Integer.valueOf(status), location });
    }
    return status;
  }
  
  public static int checkGLError(String location)
  {
    int errorCode = GL11.glGetError();
    if (errorCode != 0)
    {
      boolean skipPrint = false;
      if (!skipPrint) {
        if (errorCode == 1286)
        {
          int status = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
          System.err.format("GL error 0x%04X: %s (Fb status 0x%04X) at %s\n", new Object[] { Integer.valueOf(errorCode), GLU.gluErrorString(errorCode), Integer.valueOf(status), location });
        }
        else
        {
          System.err.format("GL error 0x%04X: %s at %s\n", new Object[] { Integer.valueOf(errorCode), GLU.gluErrorString(errorCode), location });
        }
      }
    }
    return errorCode;
  }
  
  public static int checkGLError(String location, String info)
  {
    int errorCode = GL11.glGetError();
    if (errorCode != 0) {
      System.err.format("GL error 0x%04x: %s at %s %s\n", new Object[] { Integer.valueOf(errorCode), GLU.gluErrorString(errorCode), location, info });
    }
    return errorCode;
  }
  
  public static int checkGLError(String location, String info1, String info2)
  {
    int errorCode = GL11.glGetError();
    if (errorCode != 0) {
      System.err.format("GL error 0x%04x: %s at %s %s %s\n", new Object[] { Integer.valueOf(errorCode), GLU.gluErrorString(errorCode), location, info1, info2 });
    }
    return errorCode;
  }
  
  private static void printChat(String str)
  {
    mc.r.d().a(new fa(str));
  }
  
  private static void printChatAndLogError(String str)
  {
    SMCLog.severe(str);
    mc.r.d().a(new fa(str));
  }
  
  public static void printIntBuffer(String title, IntBuffer buf)
  {
    StringBuilder sb = new StringBuilder(128);
    sb.append(title).append(" [pos ").append(buf.position()).append(" lim ").append(buf.limit()).append(" cap ").append(buf.capacity()).append(" :");
    
    int lim = buf.limit();
    for (int i = 0; i < lim; i++) {
      sb.append(" ").append(buf.get(i));
    }
    sb.append("]");
    SMCLog.info(sb.toString());
  }
  
  public static void startup(bcf mc)
  {
    checkShadersModInstalled();
    
    mc = mc;
    mc = bcf.z();
    capabilities = GLContext.getCapabilities();
    glVersionString = GL11.glGetString(7938);
    glVendorString = GL11.glGetString(7936);
    glRendererString = GL11.glGetString(7937);
    SMCLog.info("ShadersMod version: 2.4.12");
    SMCLog.info("OpenGL Version: " + glVersionString);
    SMCLog.info("Vendor:  " + glVendorString);
    SMCLog.info("Renderer: " + glRendererString);
    SMCLog.info("Capabilities: " + (capabilities.OpenGL20 ? " 2.0 " : " - ") + (capabilities.OpenGL21 ? " 2.1 " : " - ") + (capabilities.OpenGL30 ? " 3.0 " : " - ") + (capabilities.OpenGL32 ? " 3.2 " : " - ") + (capabilities.OpenGL40 ? " 4.0 " : " - "));
    
    SMCLog.info("GL_MAX_DRAW_BUFFERS: " + GL11.glGetInteger(34852));
    SMCLog.info("GL_MAX_COLOR_ATTACHMENTS_EXT: " + GL11.glGetInteger(36063));
    SMCLog.info("GL_MAX_TEXTURE_IMAGE_UNITS: " + GL11.glGetInteger(34930));
    hasGlGenMipmap = capabilities.OpenGL30;
    loadConfig();
  }
  
  private static String toStringYN(boolean b)
  {
    return b ? "Y" : "N";
  }
  
  public static void updateBlockLightLevel()
  {
    if (isOldLighting())
    {
      blockLightLevel05 = 0.5F;
      blockLightLevel06 = 0.6F;
      blockLightLevel08 = 0.8F;
    }
    else
    {
      blockLightLevel05 = 1.0F;
      blockLightLevel06 = 1.0F;
      blockLightLevel08 = 1.0F;
    }
  }
  
  public static boolean isOldLighting()
  {
    if (!configOldLighting.isDefault()) {
      return configOldLighting.isTrue();
    }
    if (!shaderPackOldLighting.isDefault()) {
      return shaderPackOldLighting.isTrue();
    }
    return true;
  }
  
  public static void init()
  {
    boolean firstInit;
    boolean firstInit;
    if (!isInitializedOnce)
    {
      isInitializedOnce = true;
      firstInit = true;
    }
    else
    {
      firstInit = false;
    }
    if (!isShaderPackInitialized)
    {
      checkGLError("Shaders.init pre");
      if ((getShaderPackName() == null) || 
      
        (!capabilities.OpenGL20)) {
        printChatAndLogError("No OpenGL 2.0");
      }
      if (!capabilities.GL_EXT_framebuffer_object) {
        printChatAndLogError("No EXT_framebuffer_object");
      }
      dfbDrawBuffers.position(0).limit(8);
      dfbColorTextures.position(0).limit(16);
      
      dfbDepthTextures.position(0).limit(3);
      sfbDrawBuffers.position(0).limit(8);
      sfbDepthTextures.position(0).limit(2);
      sfbColorTextures.position(0).limit(8);
      
      usedColorBuffers = 4;
      usedDepthBuffers = 1;
      usedShadowColorBuffers = 0;
      usedShadowDepthBuffers = 0;
      usedColorAttachs = 1;
      usedDrawBuffers = 1;
      Arrays.fill(gbuffersFormat, 6408);
      Arrays.fill(shadowHardwareFilteringEnabled, false);
      Arrays.fill(shadowMipmapEnabled, false);
      Arrays.fill(shadowFilterNearest, false);
      Arrays.fill(shadowColorMipmapEnabled, false);
      Arrays.fill(shadowColorFilterNearest, false);
      centerDepthSmoothEnabled = false;
      noiseTextureEnabled = false;
      sunPathRotation = 0.0F;
      shadowIntervalSize = 2.0F;
      aoLevel = 0.8F;
      blockAoLight = 1.0F - aoLevel;
      useEntityAttrib = false;
      useMidTexCoordAttrib = false;
      useMultiTexCoord3Attrib = false;
      useTangentAttrib = false;
      waterShadowEnabled = false;
      updateChunksErrorRecorded = false;
      updateBlockLightLevel();
      
      ShaderProfile activeProfile = ShaderUtils.detectProfile(shaderPackProfiles, shaderPackOptions, false);
      
      String worldPrefix = "";
      if (currentWorld != null)
      {
        int dimId = currentWorld.s.p().a();
        if (shaderPackDimensions.contains(Integer.valueOf(dimId))) {
          worldPrefix = "world" + dimId + "/";
        }
      }
      if (saveFinalShaders) {
        clearDirectory(new File(shaderpacksdir, "debug"));
      }
      for (int i = 0; i < 33; i++)
      {
        String programName = programNames[i];
        if (programName.equals(""))
        {
          programsID[i] = (programsRef[i] = 0);
          programsDrawBufSettings[i] = null;
          programsColorAtmSettings[i] = null;
          programsCompositeMipmapSetting[i] = 0;
        }
        else
        {
          newDrawBufSetting = null;
          newColorAtmSetting = null;
          newCompositeMipmapSetting = 0;
          
          String programPath = worldPrefix + programName;
          if (activeProfile != null) {
            if (activeProfile.isProgramDisabled(programPath))
            {
              SMCLog.info("Program disabled: " + programPath);
              programName = "<disabled>";
              programPath = worldPrefix + programName;
            }
          }
          String programFullPath = "/shaders/" + programPath;
          int pr = setupProgram(i, programFullPath + ".vsh", programFullPath + ".fsh");
          if (pr > 0) {
            SMCLog.info("Program loaded: " + programPath);
          }
          programsID[i] = (programsRef[i] = pr);
          programsDrawBufSettings[i] = (pr != 0 ? newDrawBufSetting : null);
          programsColorAtmSettings[i] = (pr != 0 ? newColorAtmSetting : null);
          programsCompositeMipmapSetting[i] = (pr != 0 ? newCompositeMipmapSetting : 0);
        }
      }
      int maxDrawBuffers = GL11.glGetInteger(34852);
      Map<String, IntBuffer> drawBuffersMap = new HashMap();
      for (int p = 0; p < 33; p++)
      {
        Arrays.fill(programsToggleColorTextures[p], false);
        if (p == 29)
        {
          programsDrawBuffers[p] = null;
        }
        else if (programsID[p] == 0)
        {
          if (p == 30) {
            programsDrawBuffers[p] = drawBuffersNone;
          } else {
            programsDrawBuffers[p] = drawBuffersColorAtt0;
          }
        }
        else
        {
          String str = programsDrawBufSettings[p];
          if (str != null)
          {
            IntBuffer intbuf = drawBuffersBuffer[p];
            int numDB = str.length();
            if (numDB > usedDrawBuffers) {
              usedDrawBuffers = numDB;
            }
            if (numDB > maxDrawBuffers) {
              numDB = maxDrawBuffers;
            }
            programsDrawBuffers[p] = intbuf;
            intbuf.limit(numDB);
            for (int i = 0; i < numDB; i++)
            {
              int drawBuffer = 0;
              if (str.length() > i)
              {
                int ca = str.charAt(i) - '0';
                if (p != 30)
                {
                  if ((ca >= 0) && (ca <= 7))
                  {
                    programsToggleColorTextures[p][ca] = 1;
                    drawBuffer = ca + 36064;
                    if (ca > usedColorAttachs) {
                      usedColorAttachs = ca;
                    }
                    if (ca > usedColorBuffers) {
                      usedColorBuffers = ca;
                    }
                  }
                }
                else if ((ca >= 0) && (ca <= 1))
                {
                  drawBuffer = ca + 36064;
                  if (ca > usedShadowColorBuffers) {
                    usedShadowColorBuffers = ca;
                  }
                }
              }
              intbuf.put(i, drawBuffer);
            }
          }
          else if ((p != 30) && (p != 31) && (p != 32))
          {
            programsDrawBuffers[p] = dfbDrawBuffers;
            usedDrawBuffers = usedColorBuffers;
            Arrays.fill(programsToggleColorTextures[p], 0, usedColorBuffers, true);
          }
          else
          {
            programsDrawBuffers[p] = sfbDrawBuffers;
          }
        }
      }
      usedColorAttachs = usedColorBuffers;
      
      shadowPassInterval = usedShadowDepthBuffers > 0 ? 1 : 0;
      shouldSkipDefaultShadow = usedShadowDepthBuffers > 0;
      
      SMCLog.info("usedColorBuffers: " + usedColorBuffers);
      SMCLog.info("usedDepthBuffers: " + usedDepthBuffers);
      SMCLog.info("usedShadowColorBuffers: " + usedShadowColorBuffers);
      SMCLog.info("usedShadowDepthBuffers: " + usedShadowDepthBuffers);
      SMCLog.info("usedColorAttachs: " + usedColorAttachs);
      SMCLog.info("usedDrawBuffers: " + usedDrawBuffers);
      
      dfbDrawBuffers.position(0).limit(usedDrawBuffers);
      dfbColorTextures.position(0).limit(usedColorBuffers * 2);
      for (int i = 0; i < usedDrawBuffers; i++) {
        dfbDrawBuffers.put(i, 36064 + i);
      }
      if (usedDrawBuffers > maxDrawBuffers) {
        printChatAndLogError("[Shaders] Error: Not enough draw buffers, needed: " + usedDrawBuffers + ", available: " + maxDrawBuffers);
      }
      sfbDrawBuffers.position(0).limit(usedShadowColorBuffers);
      for (int i = 0; i < usedShadowColorBuffers; i++) {
        sfbDrawBuffers.put(i, 36064 + i);
      }
      for (int i = 0; i < 33; i++)
      {
        int n = i;
        while ((programsID[n] == 0) && (programBackups[n] != n)) {
          n = programBackups[n];
        }
        if ((n != i) && (i != 30))
        {
          programsID[i] = programsID[n];
          programsDrawBufSettings[i] = programsDrawBufSettings[n];
          programsDrawBuffers[i] = programsDrawBuffers[n];
        }
      }
      resize();
      
      resizeShadow();
      if (noiseTextureEnabled) {
        setupNoiseTexture();
      }
      if (defaultTexture == null) {
        defaultTexture = ShadersTex.createDefaultTexture();
      }
      bni.G();
      bni.b(-90.0F, 0.0F, 1.0F, 0.0F);
      preCelestialRotate();
      postCelestialRotate();
      bni.H();
      
      isShaderPackInitialized = true;
      
      loadEntityDataMap();
      
      resetDisplayList();
      if (!firstInit) {}
      checkGLError("Shaders.init");
    }
  }
  
  public static void resetDisplayList()
  {
    numberResetDisplayList += 1;
    
    needResetModels = true;
    SMCLog.info("Reset world renderers");
    mc.g.a();
  }
  
  public static void resetDisplayListModels()
  {
    if (needResetModels)
    {
      needResetModels = false;
      SMCLog.info("Reset model renderers");
      Iterator<brn> it = mc.ac().getEntityRenderMap().values().iterator();
      while (it.hasNext())
      {
        brn ren = (brn)it.next();
        if ((ren instanceof bsg))
        {
          bsg rle = (bsg)ren;
          
          resetDisplayListModel(rle.b());
        }
      }
    }
  }
  
  public static void resetDisplayListModel(bjc model)
  {
    if (model != null)
    {
      Iterator it = model.r.iterator();
      while (it.hasNext())
      {
        Object obj = it.next();
        if ((obj instanceof bkm)) {
          resetDisplayListModelRenderer((bkm)obj);
        }
      }
    }
  }
  
  public static void resetDisplayListModelRenderer(bkm mrr)
  {
    mrr.resetDisplayList();
    if (mrr.m != null)
    {
      int i = 0;
      for (int n = mrr.m.size(); i < n; i++) {
        resetDisplayListModelRenderer((bkm)mrr.m.get(i));
      }
    }
  }
  
  private static int setupProgram(int program, String vShaderPath, String fShaderPath)
  {
    checkGLError("pre setupProgram");
    int programid = ARBShaderObjects.glCreateProgramObjectARB();
    checkGLError("create");
    if (programid != 0)
    {
      progUseEntityAttrib = false;
      progUseMidTexCoordAttrib = false;
      progUseTangentAttrib = false;
      int vShader = createVertShader(vShaderPath);
      int fShader = createFragShader(fShaderPath);
      checkGLError("create");
      if ((vShader != 0) || (fShader != 0))
      {
        if (vShader != 0)
        {
          ARBShaderObjects.glAttachObjectARB(programid, vShader);
          checkGLError("attach");
        }
        if (fShader != 0)
        {
          ARBShaderObjects.glAttachObjectARB(programid, fShader);
          checkGLError("attach");
        }
        if (progUseEntityAttrib)
        {
          ARBVertexShader.glBindAttribLocationARB(programid, entityAttrib, "mc_Entity");
          checkGLError("mc_Entity");
        }
        if (progUseMidTexCoordAttrib)
        {
          ARBVertexShader.glBindAttribLocationARB(programid, midTexCoordAttrib, "mc_midTexCoord");
          checkGLError("mc_midTexCoord");
        }
        if (progUseTangentAttrib)
        {
          ARBVertexShader.glBindAttribLocationARB(programid, tangentAttrib, "at_tangent");
          checkGLError("at_tangent");
        }
        ARBShaderObjects.glLinkProgramARB(programid);
        if (GL20.glGetProgrami(programid, 35714) != 1) {
          SMCLog.severe("Error linking program: " + programid);
        }
        printLogInfo(programid, vShaderPath + ", " + fShaderPath);
        if (vShader != 0)
        {
          ARBShaderObjects.glDetachObjectARB(programid, vShader);
          ARBShaderObjects.glDeleteObjectARB(vShader);
        }
        if (fShader != 0)
        {
          ARBShaderObjects.glDetachObjectARB(programid, fShader);
          ARBShaderObjects.glDeleteObjectARB(fShader);
        }
        programsID[program] = programid;
        useProgram(program);
        ARBShaderObjects.glValidateProgramARB(programid);
        useProgram(0);
        printLogInfo(programid, vShaderPath + ", " + fShaderPath);
        int valid = GL20.glGetProgrami(programid, 35715);
        if (valid != 1)
        {
          String Q = "\"";
          printChatAndLogError("[Shaders] Error: Invalid program " + Q + programNames[program] + Q);
          ARBShaderObjects.glDeleteObjectARB(programid);
          programid = 0;
        }
      }
      else
      {
        ARBShaderObjects.glDeleteObjectARB(programid);
        programid = 0;
      }
    }
    return programid;
  }
  
  private static int createVertShader(String filename)
  {
    int vertShader = ARBShaderObjects.glCreateShaderObjectARB(35633);
    if (vertShader == 0) {
      return 0;
    }
    StringBuilder vertexCode = new StringBuilder(131072);
    
    BufferedReader reader = null;
    try
    {
      reader = new BufferedReader(new InputStreamReader(shaderPack.getResourceAsStream(filename)));
    }
    catch (Exception e)
    {
      try
      {
        reader = new BufferedReader(new FileReader(new File(filename)));
      }
      catch (Exception e2)
      {
        ARBShaderObjects.glDeleteObjectARB(vertShader);
        return 0;
      }
    }
    ShaderOption[] activeOptions = getChangedOptions(shaderPackOptions);
    if (reader != null) {
      try
      {
        String line;
        while ((line = reader.readLine()) != null)
        {
          line = applyOptions(line, activeOptions);
          
          vertexCode.append(line).append('\n');
          if (line.matches("attribute [_a-zA-Z0-9]+ mc_Entity.*"))
          {
            useEntityAttrib = true;
            progUseEntityAttrib = true;
          }
          else if (line.matches("attribute [_a-zA-Z0-9]+ mc_midTexCoord.*"))
          {
            useMidTexCoordAttrib = true;
            progUseMidTexCoordAttrib = true;
          }
          else if (line.matches(".*gl_MultiTexCoord3.*"))
          {
            useMultiTexCoord3Attrib = true;
          }
          else if (line.matches("attribute [_a-zA-Z0-9]+ at_tangent.*"))
          {
            useTangentAttrib = true;
            progUseTangentAttrib = true;
          }
        }
        reader.close();
      }
      catch (Exception e)
      {
        SMCLog.severe("Couldn't read " + filename + "!");
        e.printStackTrace();
        ARBShaderObjects.glDeleteObjectARB(vertShader);
        return 0;
      }
    }
    if (saveFinalShaders) {
      saveShader(filename, vertexCode.toString());
    }
    ARBShaderObjects.glShaderSourceARB(vertShader, vertexCode);
    ARBShaderObjects.glCompileShaderARB(vertShader);
    if (GL20.glGetShaderi(vertShader, 35713) != 1) {
      SMCLog.severe("Error compiling vertex shader: " + filename);
    }
    printShaderLogInfo(vertShader, filename);
    return vertShader;
  }
  
  private static final Pattern gbufferFormatPattern = Pattern.compile("[ \t]*const[ \t]*int[ \t]*(\\w+)Format[ \t]*=[ \t]*([RGBA81632F]*)[ \t]*;.*");
  private static final Pattern gbufferMipmapEnabledPattern = Pattern.compile("[ \t]*const[ \t]*bool[ \t]*(\\w+)MipmapEnabled[ \t]*=[ \t]*true[ \t]*;.*");
  
  private static int createFragShader(String filename)
  {
    int fragShader = ARBShaderObjects.glCreateShaderObjectARB(35632);
    if (fragShader == 0) {
      return 0;
    }
    StringBuilder fragCode = new StringBuilder(131072);
    
    BufferedReader reader = null;
    try
    {
      reader = new BufferedReader(new InputStreamReader(shaderPack.getResourceAsStream(filename)));
    }
    catch (Exception e)
    {
      try
      {
        reader = new BufferedReader(new FileReader(new File(filename)));
      }
      catch (Exception e2)
      {
        ARBShaderObjects.glDeleteObjectARB(fragShader);
        return 0;
      }
    }
    ShaderOption[] activeOptions = getChangedOptions(shaderPackOptions);
    if (reader != null) {
      try
      {
        reader = ShaderPackParser.resolveIncludes(reader, filename, shaderPack, 0);
        String line;
        while ((line = reader.readLine()) != null)
        {
          line = applyOptions(line, activeOptions);
          
          fragCode.append(line).append('\n');
          if (!line.matches("#version .*")) {
            if (line.matches("uniform [ _a-zA-Z0-9]+ shadow;.*"))
            {
              if (usedShadowDepthBuffers < 1) {
                usedShadowDepthBuffers = 1;
              }
            }
            else if (line.matches("uniform [ _a-zA-Z0-9]+ watershadow;.*"))
            {
              waterShadowEnabled = true;
              if (usedShadowDepthBuffers < 2) {
                usedShadowDepthBuffers = 2;
              }
            }
            else if (line.matches("uniform [ _a-zA-Z0-9]+ shadowtex0;.*"))
            {
              if (usedShadowDepthBuffers < 1) {
                usedShadowDepthBuffers = 1;
              }
            }
            else if (line.matches("uniform [ _a-zA-Z0-9]+ shadowtex1;.*"))
            {
              if (usedShadowDepthBuffers < 2) {
                usedShadowDepthBuffers = 2;
              }
            }
            else if (line.matches("uniform [ _a-zA-Z0-9]+ shadowcolor;.*"))
            {
              if (usedShadowColorBuffers < 1) {
                usedShadowColorBuffers = 1;
              }
            }
            else if (line.matches("uniform [ _a-zA-Z0-9]+ shadowcolor0;.*"))
            {
              if (usedShadowColorBuffers < 1) {
                usedShadowColorBuffers = 1;
              }
            }
            else if (line.matches("uniform [ _a-zA-Z0-9]+ shadowcolor1;.*"))
            {
              if (usedShadowColorBuffers < 2) {
                usedShadowColorBuffers = 2;
              }
            }
            else if (line.matches("uniform [ _a-zA-Z0-9]+ shadowcolor2;.*"))
            {
              if (usedShadowColorBuffers < 3) {
                usedShadowColorBuffers = 3;
              }
            }
            else if (line.matches("uniform [ _a-zA-Z0-9]+ shadowcolor3;.*"))
            {
              if (usedShadowColorBuffers < 4) {
                usedShadowColorBuffers = 4;
              }
            }
            else if (line.matches("uniform [ _a-zA-Z0-9]+ depthtex0;.*"))
            {
              if (usedDepthBuffers < 1) {
                usedDepthBuffers = 1;
              }
            }
            else if (line.matches("uniform [ _a-zA-Z0-9]+ depthtex1;.*"))
            {
              if (usedDepthBuffers < 2) {
                usedDepthBuffers = 2;
              }
            }
            else if (line.matches("uniform [ _a-zA-Z0-9]+ depthtex2;.*"))
            {
              if (usedDepthBuffers < 3) {
                usedDepthBuffers = 3;
              }
            }
            else if (line.matches("uniform [ _a-zA-Z0-9]+ gdepth;.*"))
            {
              if (gbuffersFormat[1] == 6408) {
                gbuffersFormat[1] = 34836;
              }
            }
            else if ((usedColorBuffers < 5) && (line.matches("uniform [ _a-zA-Z0-9]+ gaux1;.*")))
            {
              usedColorBuffers = 5;
            }
            else if ((usedColorBuffers < 6) && (line.matches("uniform [ _a-zA-Z0-9]+ gaux2;.*")))
            {
              usedColorBuffers = 6;
            }
            else if ((usedColorBuffers < 7) && (line.matches("uniform [ _a-zA-Z0-9]+ gaux3;.*")))
            {
              usedColorBuffers = 7;
            }
            else if ((usedColorBuffers < 8) && (line.matches("uniform [ _a-zA-Z0-9]+ gaux4;.*")))
            {
              usedColorBuffers = 8;
            }
            else if ((usedColorBuffers < 5) && (line.matches("uniform [ _a-zA-Z0-9]+ colortex4;.*")))
            {
              usedColorBuffers = 5;
            }
            else if ((usedColorBuffers < 6) && (line.matches("uniform [ _a-zA-Z0-9]+ colortex5;.*")))
            {
              usedColorBuffers = 6;
            }
            else if ((usedColorBuffers < 7) && (line.matches("uniform [ _a-zA-Z0-9]+ colortex6;.*")))
            {
              usedColorBuffers = 7;
            }
            else if ((usedColorBuffers < 8) && (line.matches("uniform [ _a-zA-Z0-9]+ colortex7;.*")))
            {
              usedColorBuffers = 8;
            }
            else if ((usedColorBuffers < 8) && (line.matches("uniform [ _a-zA-Z0-9]+ centerDepthSmooth;.*")))
            {
              centerDepthSmoothEnabled = true;
            }
            else if (line.matches("/\\* SHADOWRES:[0-9]+ \\*/.*"))
            {
              String[] parts = line.split("(:| )", 4);
              SMCLog.info("Shadow map resolution: " + parts[2]);
              spShadowMapWidth = spShadowMapHeight = Integer.parseInt(parts[2]);
              shadowMapWidth = shadowMapHeight = Math.round(spShadowMapWidth * configShadowResMul);
            }
            else if (line.matches("[ \t]*const[ \t]*int[ \t]*shadowMapResolution[ \t]*=[ \t]*-?[0-9.]+f?;.*"))
            {
              String[] parts = line.split("(=[ \t]*|;)");
              SMCLog.info("Shadow map resolution: " + parts[1]);
              spShadowMapWidth = spShadowMapHeight = Integer.parseInt(parts[1]);
              shadowMapWidth = shadowMapHeight = Math.round(spShadowMapWidth * configShadowResMul);
            }
            else if (line.matches("/\\* SHADOWFOV:[0-9\\.]+ \\*/.*"))
            {
              String[] parts = line.split("(:| )", 4);
              SMCLog.info("Shadow map field of view: " + parts[2]);
              shadowMapFOV = Float.parseFloat(parts[2]);
              shadowMapIsOrtho = false;
            }
            else if (line.matches("/\\* SHADOWHPL:[0-9\\.]+ \\*/.*"))
            {
              String[] parts = line.split("(:| )", 4);
              SMCLog.info("Shadow map half-plane: " + parts[2]);
              shadowMapHalfPlane = Float.parseFloat(parts[2]);
              shadowMapIsOrtho = true;
            }
            else if (line.matches("[ \t]*const[ \t]*float[ \t]*shadowDistance[ \t]*=[ \t]*-?[0-9.]+f?;.*"))
            {
              String[] parts = line.split("(=[ \t]*|;)");
              SMCLog.info("Shadow map distance: " + parts[1]);
              shadowMapHalfPlane = Float.parseFloat(parts[1]);
              shadowMapIsOrtho = true;
            }
            else if (line.matches("[ \t]*const[ \t]*float[ \t]*shadowIntervalSize[ \t]*=[ \t]*-?[0-9.]+f?;.*"))
            {
              String[] parts = line.split("(=[ \t]*|;)");
              SMCLog.info("Shadow map interval size: " + parts[1]);
              shadowIntervalSize = Float.parseFloat(parts[1]);
            }
            else if (line.matches("[ \t]*const[ \t]*bool[ \t]*generateShadowMipmap[ \t]*=[ \t]*true[ \t]*;.*"))
            {
              SMCLog.info("Generate shadow mipmap");
              Arrays.fill(shadowMipmapEnabled, true);
            }
            else if (line.matches("[ \t]*const[ \t]*bool[ \t]*generateShadowColorMipmap[ \t]*=[ \t]*true[ \t]*;.*"))
            {
              SMCLog.info("Generate shadow color mipmap");
              Arrays.fill(shadowColorMipmapEnabled, true);
            }
            else if (line.matches("[ \t]*const[ \t]*bool[ \t]*shadowHardwareFiltering[ \t]*=[ \t]*true[ \t]*;.*"))
            {
              SMCLog.info("Hardware shadow filtering enabled.");
              Arrays.fill(shadowHardwareFilteringEnabled, true);
            }
            else if (line.matches("[ \t]*const[ \t]*bool[ \t]*shadowHardwareFiltering0[ \t]*=[ \t]*true[ \t]*;.*"))
            {
              SMCLog.info("shadowHardwareFiltering0");
              shadowHardwareFilteringEnabled[0] = true;
            }
            else if (line.matches("[ \t]*const[ \t]*bool[ \t]*shadowHardwareFiltering1[ \t]*=[ \t]*true[ \t]*;.*"))
            {
              SMCLog.info("shadowHardwareFiltering1");
              shadowHardwareFilteringEnabled[1] = true;
            }
            else if (line.matches("[ \t]*const[ \t]*bool[ \t]*(shadowtex0Mipmap|shadowtexMipmap)[ \t]*=[ \t]*true[ \t]*;.*"))
            {
              SMCLog.info("shadowtex0Mipmap");
              shadowMipmapEnabled[0] = true;
            }
            else if (line.matches("[ \t]*const[ \t]*bool[ \t]*(shadowtex1Mipmap)[ \t]*=[ \t]*true[ \t]*;.*"))
            {
              SMCLog.info("shadowtex1Mipmap");
              shadowMipmapEnabled[1] = true;
            }
            else if (line.matches("[ \t]*const[ \t]*bool[ \t]*(shadowcolor0Mipmap|shadowColor0Mipmap)[ \t]*=[ \t]*true[ \t]*;.*"))
            {
              SMCLog.info("shadowcolor0Mipmap");
              shadowColorMipmapEnabled[0] = true;
            }
            else if (line.matches("[ \t]*const[ \t]*bool[ \t]*(shadowcolor1Mipmap|shadowColor1Mipmap)[ \t]*=[ \t]*true[ \t]*;.*"))
            {
              SMCLog.info("shadowcolor1Mipmap");
              shadowColorMipmapEnabled[1] = true;
            }
            else if (line.matches("[ \t]*const[ \t]*bool[ \t]*(shadowtex0Nearest|shadowtexNearest|shadow0MinMagNearest)[ \t]*=[ \t]*true[ \t]*;.*"))
            {
              SMCLog.info("shadowtex0Nearest");
              shadowFilterNearest[0] = true;
            }
            else if (line.matches("[ \t]*const[ \t]*bool[ \t]*(shadowtex1Nearest|shadow1MinMagNearest)[ \t]*=[ \t]*true[ \t]*;.*"))
            {
              SMCLog.info("shadowtex1Nearest");
              shadowFilterNearest[1] = true;
            }
            else if (line.matches("[ \t]*const[ \t]*bool[ \t]*(shadowcolor0Nearest|shadowColor0Nearest|shadowColor0MinMagNearest)[ \t]*=[ \t]*true[ \t]*;.*"))
            {
              SMCLog.info("shadowcolor0Nearest");
              shadowColorFilterNearest[0] = true;
            }
            else if (line.matches("[ \t]*const[ \t]*bool[ \t]*(shadowcolor1Nearest|shadowColor1Nearest|shadowColor1MinMagNearest)[ \t]*=[ \t]*true[ \t]*;.*"))
            {
              SMCLog.info("shadowcolor1Nearest");
              shadowColorFilterNearest[1] = true;
            }
            else if (line.matches("/\\* WETNESSHL:[0-9\\.]+ \\*/.*"))
            {
              String[] parts = line.split("(:| )", 4);
              SMCLog.info("Wetness halflife: " + parts[2]);
              wetnessHalfLife = Float.parseFloat(parts[2]);
            }
            else if (line.matches("[ \t]*const[ \t]*float[ \t]*wetnessHalflife[ \t]*=[ \t]*-?[0-9.]+f?;.*"))
            {
              String[] parts = line.split("(=[ \t]*|;)");
              SMCLog.info("Wetness halflife: " + parts[1]);
              wetnessHalfLife = Float.parseFloat(parts[1]);
            }
            else if (line.matches("/\\* DRYNESSHL:[0-9\\.]+ \\*/.*"))
            {
              String[] parts = line.split("(:| )", 4);
              SMCLog.info("Dryness halflife: " + parts[2]);
              drynessHalfLife = Float.parseFloat(parts[2]);
            }
            else if (line.matches("[ \t]*const[ \t]*float[ \t]*drynessHalflife[ \t]*=[ \t]*-?[0-9.]+f?;.*"))
            {
              String[] parts = line.split("(=[ \t]*|;)");
              SMCLog.info("Dryness halflife: " + parts[1]);
              drynessHalfLife = Float.parseFloat(parts[1]);
            }
            else if (line.matches("[ \t]*const[ \t]*float[ \t]*eyeBrightnessHalflife[ \t]*=[ \t]*-?[0-9.]+f?;.*"))
            {
              String[] parts = line.split("(=[ \t]*|;)");
              SMCLog.info("Eye brightness halflife: " + parts[1]);
              eyeBrightnessHalflife = Float.parseFloat(parts[1]);
            }
            else if (line.matches("[ \t]*const[ \t]*float[ \t]*centerDepthHalflife[ \t]*=[ \t]*-?[0-9.]+f?;.*"))
            {
              String[] parts = line.split("(=[ \t]*|;)");
              SMCLog.info("Center depth halflife: " + parts[1]);
              centerDepthSmoothHalflife = Float.parseFloat(parts[1]);
            }
            else if (line.matches("[ \t]*const[ \t]*float[ \t]*sunPathRotation[ \t]*=[ \t]*-?[0-9.]+f?;.*"))
            {
              String[] parts = line.split("(=[ \t]*|;)");
              SMCLog.info("Sun path rotation: " + parts[1]);
              sunPathRotation = Float.parseFloat(parts[1]);
            }
            else if (line.matches("[ \t]*const[ \t]*float[ \t]*ambientOcclusionLevel[ \t]*=[ \t]*-?[0-9.]+f?;.*"))
            {
              String[] parts = line.split("(=[ \t]*|;)");
              SMCLog.info("AO Level: " + parts[1]);
              aoLevel = Float.parseFloat(parts[1]);
              blockAoLight = 1.0F - aoLevel;
            }
            else if (line.matches("[ \t]*const[ \t]*int[ \t]*superSamplingLevel[ \t]*=[ \t]*-?[0-9.]+f?;.*"))
            {
              String[] parts = line.split("(=[ \t]*|;)");
              int ssaa = Integer.parseInt(parts[1]);
              if (ssaa > 1)
              {
                SMCLog.info("Super sampling level: " + ssaa + "x");
                superSamplingLevel = ssaa;
              }
              else
              {
                superSamplingLevel = 1;
              }
            }
            else if (line.matches("[ \t]*const[ \t]*int[ \t]*noiseTextureResolution[ \t]*=[ \t]*-?[0-9.]+f?;.*"))
            {
              String[] parts = line.split("(=[ \t]*|;)");
              SMCLog.info("Noise texture enabled");
              SMCLog.info("Noise texture resolution: " + parts[1]);
              noiseTextureResolution = Integer.parseInt(parts[1]);
              noiseTextureEnabled = true;
            }
            else if (line.matches("[ \t]*const[ \t]*int[ \t]*\\w+Format[ \t]*=[ \t]*[RGBA81632F]*[ \t]*;.*"))
            {
              Matcher m = gbufferFormatPattern.matcher(line);
              m.matches();
              String name = m.group(1);
              String value = m.group(2);
              int bufferindex = getBufferIndexFromString(name);
              int format = getTextureFormatFromString(value);
              if ((bufferindex >= 0) && (format != 0))
              {
                gbuffersFormat[bufferindex] = format;
                SMCLog.info("%s format: %s", new Object[] { name, value });
              }
            }
            else if (line.matches("/\\* GAUX4FORMAT:RGBA32F \\*/.*"))
            {
              SMCLog.info("gaux4 format : RGB32AF");
              gbuffersFormat[7] = 34836;
            }
            else if (line.matches("/\\* GAUX4FORMAT:RGB32F \\*/.*"))
            {
              SMCLog.info("gaux4 format : RGB32F");
              gbuffersFormat[7] = 34837;
            }
            else if (line.matches("/\\* GAUX4FORMAT:RGB16 \\*/.*"))
            {
              SMCLog.info("gaux4 format : RGB16");
              gbuffersFormat[7] = 32852;
            }
            else if (line.matches("[ \t]*const[ \t]*bool[ \t]*\\w+MipmapEnabled[ \t]*=[ \t]*true[ \t]*;.*"))
            {
              if ((filename.matches(".*composite[0-9]?.fsh")) || (filename.matches(".*final.fsh")))
              {
                Matcher m = gbufferMipmapEnabledPattern.matcher(line);
                m.matches();
                String name = m.group(1);
                
                int bufferindex = getBufferIndexFromString(name);
                if (bufferindex >= 0)
                {
                  newCompositeMipmapSetting |= 1 << bufferindex;
                  SMCLog.info("%s mipmap enabled for %s", new Object[] { name, filename });
                }
              }
            }
            else if (line.matches("/\\* DRAWBUFFERS:[0-7N]* \\*/.*"))
            {
              String[] parts = line.split("(:| )", 4);
              newDrawBufSetting = parts[2];
            }
          }
        }
        reader.close();
      }
      catch (Exception e)
      {
        SMCLog.severe("Couldn't read " + filename + "!");
        e.printStackTrace();
        ARBShaderObjects.glDeleteObjectARB(fragShader);
        return 0;
      }
    }
    if (saveFinalShaders) {
      saveShader(filename, fragCode.toString());
    }
    ARBShaderObjects.glShaderSourceARB(fragShader, fragCode);
    ARBShaderObjects.glCompileShaderARB(fragShader);
    if (GL20.glGetShaderi(fragShader, 35713) != 1) {
      SMCLog.severe("Error compiling fragment shader: " + filename);
    }
    printShaderLogInfo(fragShader, filename);
    return fragShader;
  }
  
  private static void saveShader(String filename, String code)
  {
    try
    {
      File file = new File(shaderpacksdir, "debug/" + filename);
      
      file.getParentFile().mkdirs();
      
      Config.writeFile(file, code);
    }
    catch (IOException e)
    {
      Config.warn("Error saving: " + filename);
      e.printStackTrace();
    }
  }
  
  private static void clearDirectory(File dir)
  {
    if (!dir.exists()) {
      return;
    }
    if (!dir.isDirectory()) {
      return;
    }
    File[] files = dir.listFiles();
    if (files == null) {
      return;
    }
    for (int i = 0; i < files.length; i++)
    {
      File file = files[i];
      if (file.isDirectory()) {
        clearDirectory(file);
      }
      file.delete();
    }
  }
  
  private static boolean printLogInfo(int obj, String name)
  {
    IntBuffer iVal = BufferUtils.createIntBuffer(1);
    ARBShaderObjects.glGetObjectParameterARB(obj, 35716, iVal);
    
    int length = iVal.get();
    if (length > 1)
    {
      ByteBuffer infoLog = BufferUtils.createByteBuffer(length);
      iVal.flip();
      ARBShaderObjects.glGetInfoLogARB(obj, iVal, infoLog);
      byte[] infoBytes = new byte[length];
      infoLog.get(infoBytes);
      if (infoBytes[(length - 1)] == 0) {
        infoBytes[(length - 1)] = 10;
      }
      String out = new String(infoBytes);
      SMCLog.info("Info log: " + name + "\n" + out);
      return false;
    }
    return true;
  }
  
  private static boolean printShaderLogInfo(int shader, String name)
  {
    IntBuffer iVal = BufferUtils.createIntBuffer(1);
    int length = GL20.glGetShaderi(shader, 35716);
    if (length > 1)
    {
      String log = GL20.glGetShaderInfoLog(shader, length);
      SMCLog.info("Shader info log: " + name + "\n" + log);
      return false;
    }
    return true;
  }
  
  public static void setDrawBuffers(IntBuffer drawBuffers)
  {
    if (drawBuffers == null) {
      drawBuffers = drawBuffersNone;
    }
    if (activeDrawBuffers != drawBuffers)
    {
      activeDrawBuffers = drawBuffers;
      GL20.glDrawBuffers(drawBuffers);
    }
  }
  
  public static void useProgram(int program)
  {
    checkGLError("pre-useProgram");
    if (isShadowPass)
    {
      program = 30;
      if (programsID[30] == 0)
      {
        normalMapEnabled = false;
        return;
      }
    }
    if (activeProgram == program) {
      return;
    }
    activeProgram = program;
    ARBShaderObjects.glUseProgramObjectARB(programsID[program]);
    if (programsID[program] == 0)
    {
      normalMapEnabled = false;
      return;
    }
    if (checkGLError("useProgram ", programNames[program]) != 0) {
      programsID[program] = 0;
    }
    IntBuffer drawBuffers = programsDrawBuffers[program];
    if (isRenderingDfb)
    {
      setDrawBuffers(drawBuffers);
      checkGLError(programNames[program], " draw buffers = ", programsDrawBufSettings[program]);
    }
    activeCompositeMipmapSetting = programsCompositeMipmapSetting[program];
    uniformEntityHurt = ARBShaderObjects.glGetUniformLocationARB(programsID[activeProgram], "entityHurt");
    uniformEntityFlash = ARBShaderObjects.glGetUniformLocationARB(programsID[activeProgram], "entityFlash");
    switch (program)
    {
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
    case 10: 
    case 11: 
    case 12: 
    case 13: 
    case 16: 
    case 18: 
    case 19: 
    case 20: 
      normalMapEnabled = true;
      setProgramUniform1i("texture", 0);
      setProgramUniform1i("lightmap", 1);
      setProgramUniform1i("normals", 2);
      setProgramUniform1i("specular", 3);
      setProgramUniform1i("shadow", waterShadowEnabled ? 5 : 4);
      setProgramUniform1i("watershadow", 4);
      setProgramUniform1i("shadowtex0", 4);
      setProgramUniform1i("shadowtex1", 5);
      setProgramUniform1i("depthtex0", 6);
      setProgramUniform1i("depthtex1", 12);
      setProgramUniform1i("shadowcolor", 13);
      setProgramUniform1i("shadowcolor0", 13);
      setProgramUniform1i("shadowcolor1", 14);
      setProgramUniform1i("noisetex", 15);
      break;
    case 21: 
    case 22: 
    case 23: 
    case 24: 
    case 25: 
    case 26: 
    case 27: 
    case 28: 
    case 29: 
      normalMapEnabled = false;
      setProgramUniform1i("gcolor", 0);
      setProgramUniform1i("gdepth", 1);
      setProgramUniform1i("gnormal", 2);
      setProgramUniform1i("composite", 3);
      setProgramUniform1i("gaux1", 7);
      setProgramUniform1i("gaux2", 8);
      setProgramUniform1i("gaux3", 9);
      setProgramUniform1i("gaux4", 10);
      setProgramUniform1i("colortex0", 0);
      setProgramUniform1i("colortex1", 1);
      setProgramUniform1i("colortex2", 2);
      setProgramUniform1i("colortex3", 3);
      setProgramUniform1i("colortex4", 7);
      setProgramUniform1i("colortex5", 8);
      setProgramUniform1i("colortex6", 9);
      setProgramUniform1i("colortex7", 10);
      setProgramUniform1i("shadow", waterShadowEnabled ? 5 : 4);
      setProgramUniform1i("watershadow", 4);
      setProgramUniform1i("shadowtex0", 4);
      setProgramUniform1i("shadowtex1", 5);
      setProgramUniform1i("gdepthtex", 6);
      setProgramUniform1i("depthtex0", 6);
      setProgramUniform1i("depthtex1", 11);
      setProgramUniform1i("depthtex2", 12);
      setProgramUniform1i("shadowcolor", 13);
      setProgramUniform1i("shadowcolor0", 13);
      setProgramUniform1i("shadowcolor1", 14);
      setProgramUniform1i("noisetex", 15);
      break;
    case 30: 
    case 31: 
    case 32: 
      setProgramUniform1i("tex", 0);
      setProgramUniform1i("texture", 0);
      setProgramUniform1i("lightmap", 1);
      setProgramUniform1i("normals", 2);
      setProgramUniform1i("specular", 3);
      setProgramUniform1i("shadow", waterShadowEnabled ? 5 : 4);
      setProgramUniform1i("watershadow", 4);
      setProgramUniform1i("shadowtex0", 4);
      setProgramUniform1i("shadowtex1", 5);
      setProgramUniform1i("shadowcolor", 13);
      setProgramUniform1i("shadowcolor0", 13);
      setProgramUniform1i("shadowcolor1", 14);
      setProgramUniform1i("noisetex", 15);
      break;
    case 14: 
    case 15: 
    case 17: 
    default: 
      normalMapEnabled = false;
    }
    adq stack = mc.h.cb();
    ado item = stack != null ? stack.b() : null;
    ajt block;
    int itemID;
    ajt block;
    if (item != null)
    {
      int itemID = ado.f.a(item);
      block = (ajt)ajt.h.a(itemID);
    }
    else
    {
      itemID = -1;
      block = null;
    }
    setProgramUniform1i("heldItemId", itemID);
    setProgramUniform1i("heldBlockLightValue", block != null ? block.o(block.u()) : 0);
    setProgramUniform1i("fogMode", fogEnabled ? fogMode : 0);
    setProgramUniform3f("fogColor", fogColorR, fogColorG, fogColorB);
    setProgramUniform3f("skyColor", skyColorR, skyColorG, skyColorB);
    setProgramUniform1i("worldTime", (int)worldTime % 24000);
    setProgramUniform1i("moonPhase", moonPhase);
    setProgramUniform1f("frameTimeCounter", frameTimeCounter);
    setProgramUniform1f("sunAngle", sunAngle);
    setProgramUniform1f("shadowAngle", shadowAngle);
    setProgramUniform1f("rainStrength", rainStrength);
    setProgramUniform1f("aspectRatio", renderWidth / renderHeight);
    setProgramUniform1f("viewWidth", renderWidth);
    setProgramUniform1f("viewHeight", renderHeight);
    setProgramUniform1f("near", 0.05F);
    setProgramUniform1f("far", mc.u.c * 16);
    setProgramUniform3f("sunPosition", sunPosition[0], sunPosition[1], sunPosition[2]);
    setProgramUniform3f("moonPosition", moonPosition[0], moonPosition[1], moonPosition[2]);
    setProgramUniform3f("shadowLightPosition", shadowLightPosition[0], shadowLightPosition[1], shadowLightPosition[2]);
    setProgramUniform3f("upPosition", upPosition[0], upPosition[1], upPosition[2]);
    setProgramUniform3f("previousCameraPosition", (float)previousCameraPositionX, (float)previousCameraPositionY, (float)previousCameraPositionZ);
    setProgramUniform3f("cameraPosition", (float)cameraPositionX, (float)cameraPositionY, (float)cameraPositionZ);
    setProgramUniformMatrix4ARB("gbufferModelView", false, modelView);
    setProgramUniformMatrix4ARB("gbufferModelViewInverse", false, modelViewInverse);
    setProgramUniformMatrix4ARB("gbufferPreviousProjection", false, previousProjection);
    setProgramUniformMatrix4ARB("gbufferProjection", false, projection);
    setProgramUniformMatrix4ARB("gbufferProjectionInverse", false, projectionInverse);
    setProgramUniformMatrix4ARB("gbufferPreviousModelView", false, previousModelView);
    if (usedShadowDepthBuffers > 0)
    {
      setProgramUniformMatrix4ARB("shadowProjection", false, shadowProjection);
      setProgramUniformMatrix4ARB("shadowProjectionInverse", false, shadowProjectionInverse);
      setProgramUniformMatrix4ARB("shadowModelView", false, shadowModelView);
      setProgramUniformMatrix4ARB("shadowModelViewInverse", false, shadowModelViewInverse);
    }
    setProgramUniform1f("wetness", wetness);
    setProgramUniform1f("eyeAltitude", eyePosY);
    
    setProgramUniform2i("eyeBrightness", eyeBrightness & 0xFFFF, eyeBrightness >> 16);
    setProgramUniform2i("eyeBrightnessSmooth", Math.round(eyeBrightnessFadeX), Math.round(eyeBrightnessFadeY));
    setProgramUniform2i("terrainTextureSize", terrainTextureSize[0], terrainTextureSize[1]);
    setProgramUniform1i("terrainIconSize", terrainIconSize);
    setProgramUniform1i("isEyeInWater", isEyeInWater);
    setProgramUniform1i("hideGUI", mc.u.ao ? 1 : 0);
    setProgramUniform1f("centerDepthSmooth", centerDepthSmooth);
    setProgramUniform2i("atlasSize", atlasSizeX, atlasSizeY);
    checkGLError("useProgram ", programNames[program]);
  }
  
  public static void setProgramUniform1i(String name, int x)
  {
    int gp = programsID[activeProgram];
    if (gp != 0)
    {
      int uniform = ARBShaderObjects.glGetUniformLocationARB(gp, name);
      ARBShaderObjects.glUniform1iARB(uniform, x);
      
      checkGLError(programNames[activeProgram], name);
    }
  }
  
  public static void setProgramUniform2i(String name, int x, int y)
  {
    int gp = programsID[activeProgram];
    if (gp != 0)
    {
      int uniform = ARBShaderObjects.glGetUniformLocationARB(gp, name);
      ARBShaderObjects.glUniform2iARB(uniform, x, y);
      
      checkGLError(programNames[activeProgram], name);
    }
  }
  
  public static void setProgramUniform1f(String name, float x)
  {
    int gp = programsID[activeProgram];
    if (gp != 0)
    {
      int uniform = ARBShaderObjects.glGetUniformLocationARB(gp, name);
      ARBShaderObjects.glUniform1fARB(uniform, x);
      
      checkGLError(programNames[activeProgram], name);
    }
  }
  
  public static void setProgramUniform3f(String name, float x, float y, float z)
  {
    int gp = programsID[activeProgram];
    if (gp != 0)
    {
      int uniform = ARBShaderObjects.glGetUniformLocationARB(gp, name);
      ARBShaderObjects.glUniform3fARB(uniform, x, y, z);
      
      checkGLError(programNames[activeProgram], name);
    }
  }
  
  public static void setProgramUniformMatrix4ARB(String name, boolean transpose, FloatBuffer matrix)
  {
    int gp = programsID[activeProgram];
    if ((gp != 0) && (matrix != null))
    {
      int uniform = ARBShaderObjects.glGetUniformLocationARB(gp, name);
      ARBShaderObjects.glUniformMatrix4ARB(uniform, transpose, matrix);
      
      checkGLError(programNames[activeProgram], name);
    }
  }
  
  private static int getBufferIndexFromString(String name)
  {
    if ((name.equals("colortex0")) || (name.equals("gcolor"))) {
      return 0;
    }
    if ((name.equals("colortex1")) || (name.equals("gdepth"))) {
      return 1;
    }
    if ((name.equals("colortex2")) || (name.equals("gnormal"))) {
      return 2;
    }
    if ((name.equals("colortex3")) || (name.equals("composite"))) {
      return 3;
    }
    if ((name.equals("colortex4")) || (name.equals("gaux1"))) {
      return 4;
    }
    if ((name.equals("colortex5")) || (name.equals("gaux2"))) {
      return 5;
    }
    if ((name.equals("colortex6")) || (name.equals("gaux3"))) {
      return 6;
    }
    if ((name.equals("colortex7")) || (name.equals("gaux4"))) {
      return 7;
    }
    return -1;
  }
  
  private static int getTextureFormatFromString(String par)
  {
    if (par.matches("[ \t]*R8[ \t]*")) {
      return 33321;
    }
    if (par.matches("[ \t]*RG8[ \t]*")) {
      return 33323;
    }
    if (par.matches("[ \t]*RGB8[ \t]*")) {
      return 32849;
    }
    if (par.matches("[ \t]*RGBA8[ \t]*")) {
      return 32856;
    }
    if (par.matches("[ \t]*R16[ \t]*")) {
      return 33322;
    }
    if (par.matches("[ \t]*RG16[ \t]*")) {
      return 33324;
    }
    if (par.matches("[ \t]*RGB16[ \t]*")) {
      return 32852;
    }
    if (par.matches("[ \t]*RGBA16[ \t]*")) {
      return 32859;
    }
    if (par.matches("[ \t]*R32F[ \t]*")) {
      return 33326;
    }
    if (par.matches("[ \t]*RG32F[ \t]*")) {
      return 33328;
    }
    if (par.matches("[ \t]*RGB32F[ \t]*")) {
      return 34837;
    }
    if (par.matches("[ \t]*RGBA32F[ \t]*")) {
      return 34836;
    }
    return 0;
  }
  
  private static void setupNoiseTexture()
  {
    if (noiseTexture == null) {
      noiseTexture = new HFNoiseTexture(noiseTextureResolution, noiseTextureResolution);
    }
  }
  
  private static final Pattern patternLoadEntityDataMap = Pattern.compile("\\s*([\\w:]+)\\s*=\\s*([-]?\\d+)\\s*");
  
  private static void loadEntityDataMap()
  {
    mapBlockToEntityData = new IdentityHashMap(300);
    if (mapBlockToEntityData.isEmpty())
    {
      Iterator<kk> it = ajt.h.c().iterator();
      while (it.hasNext())
      {
        kk key = (kk)it.next();
        ajt block = (ajt)ajt.h.c(key);
        int id = ajt.h.a(block);
        
        mapBlockToEntityData.put(block, Integer.valueOf(id));
      }
    }
    BufferedReader reader = null;
    try
    {
      reader = new BufferedReader(new InputStreamReader(shaderPack.getResourceAsStream("/mc_Entity_x.txt")));
    }
    catch (Exception e) {}
    if (reader != null) {
      try
      {
        String line;
        while ((line = reader.readLine()) != null)
        {
          Matcher m = patternLoadEntityDataMap.matcher(line);
          if (m.matches())
          {
            String name = m.group(1);
            String value = m.group(2);
            int id = Integer.parseInt(value);
            ajt block = ajt.b(name);
            if (block != null) {
              mapBlockToEntityData.put(block, Integer.valueOf(id));
            } else {
              SMCLog.warning("Unknown block name %s", new Object[] { name });
            }
          }
          else
          {
            SMCLog.warning("unmatched %s\n", new Object[] { line });
          }
        }
      }
      catch (Exception e)
      {
        SMCLog.warning("Error parsing mc_Entity_x.txt");
      }
    }
    if (reader != null) {
      try
      {
        reader.close();
      }
      catch (Exception e) {}
    }
  }
  
  private static IntBuffer fillIntBufferZero(IntBuffer buf)
  {
    int limit = buf.limit();
    for (int i = buf.position(); i < limit; i++) {
      buf.put(i, 0);
    }
    return buf;
  }
  
  public static void uninit()
  {
    if (isShaderPackInitialized)
    {
      checkGLError("Shaders.uninit pre");
      for (int i = 0; i < 33; i++)
      {
        if (programsRef[i] != 0)
        {
          ARBShaderObjects.glDeleteObjectARB(programsRef[i]);
          checkGLError("del programRef");
        }
        programsRef[i] = 0;
        programsID[i] = 0;
        programsDrawBufSettings[i] = null;
        programsDrawBuffers[i] = null;
        programsCompositeMipmapSetting[i] = 0;
      }
      if (dfb != 0)
      {
        EXTFramebufferObject.glDeleteFramebuffersEXT(dfb);
        dfb = 0;
        checkGLError("del dfb");
      }
      if (sfb != 0)
      {
        EXTFramebufferObject.glDeleteFramebuffersEXT(sfb);
        sfb = 0;
        checkGLError("del sfb");
      }
      if (dfbDepthTextures != null)
      {
        bni.deleteTextures(dfbDepthTextures);
        fillIntBufferZero(dfbDepthTextures);
        checkGLError("del dfbDepthTextures");
      }
      if (dfbColorTextures != null)
      {
        bni.deleteTextures(dfbColorTextures);
        fillIntBufferZero(dfbColorTextures);
        checkGLError("del dfbTextures");
      }
      if (sfbDepthTextures != null)
      {
        bni.deleteTextures(sfbDepthTextures);
        fillIntBufferZero(sfbDepthTextures);
        checkGLError("del shadow depth");
      }
      if (sfbColorTextures != null)
      {
        bni.deleteTextures(sfbColorTextures);
        fillIntBufferZero(sfbColorTextures);
        checkGLError("del shadow color");
      }
      if (dfbDrawBuffers != null) {
        fillIntBufferZero(dfbDrawBuffers);
      }
      if (noiseTexture != null)
      {
        noiseTexture.destroy();
        noiseTexture = null;
      }
      SMCLog.info("Uninit");
      
      shadowPassInterval = 0;
      shouldSkipDefaultShadow = false;
      isShaderPackInitialized = false;
      checkGLError("Shaders.uninit");
    }
  }
  
  public static void scheduleResize()
  {
    renderDisplayHeight = 0;
  }
  
  public static void scheduleResizeShadow()
  {
    needResizeShadow = true;
  }
  
  private static void resize()
  {
    renderDisplayWidth = mc.d;
    renderDisplayHeight = mc.e;
    
    renderWidth = Math.round(renderDisplayWidth * configRenderResMul);
    renderHeight = Math.round(renderDisplayHeight * configRenderResMul);
    setupFrameBuffer();
  }
  
  private static void resizeShadow()
  {
    needResizeShadow = false;
    shadowMapWidth = Math.round(spShadowMapWidth * configShadowResMul);
    shadowMapHeight = Math.round(spShadowMapHeight * configShadowResMul);
    setupShadowFrameBuffer();
  }
  
  private static void setupFrameBuffer()
  {
    if (dfb != 0)
    {
      EXTFramebufferObject.glDeleteFramebuffersEXT(dfb);
      bni.deleteTextures(dfbDepthTextures);
      bni.deleteTextures(dfbColorTextures);
    }
    dfb = EXTFramebufferObject.glGenFramebuffersEXT();
    GL11.glGenTextures((IntBuffer)dfbDepthTextures.clear().limit(usedDepthBuffers));
    GL11.glGenTextures((IntBuffer)dfbColorTextures.clear().limit(16));
    
    dfbDepthTextures.position(0);
    dfbColorTextures.position(0);
    dfbColorTextures.get(dfbColorTexturesA).position(0);
    
    EXTFramebufferObject.glBindFramebufferEXT(36160, dfb);
    GL20.glDrawBuffers(0);
    GL11.glReadBuffer(0);
    for (int i = 0; i < usedDepthBuffers; i++)
    {
      bni.i(dfbDepthTextures.get(i));
      GL11.glTexParameteri(3553, 10242, 10496);
      GL11.glTexParameteri(3553, 10243, 10496);
      GL11.glTexParameteri(3553, 10241, 9728);
      GL11.glTexParameteri(3553, 10240, 9728);
      GL11.glTexParameteri(3553, 34891, 6409);
      GL11.glTexImage2D(3553, 0, 6402, renderWidth, renderHeight, 0, 6402, 5126, (FloatBuffer)null);
    }
    EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, dfbDepthTextures.get(0), 0);
    GL20.glDrawBuffers(dfbDrawBuffers);
    GL11.glReadBuffer(0);
    checkGLError("FT d");
    for (int i = 0; i < usedColorBuffers; i++)
    {
      bni.i(dfbColorTexturesA[i]);
      
      GL11.glTexParameteri(3553, 10242, 10496);
      GL11.glTexParameteri(3553, 10243, 10496);
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      GL11.glTexImage2D(3553, 0, gbuffersFormat[i], renderWidth, renderHeight, 0, 32993, 33639, (ByteBuffer)null);
      EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + i, 3553, dfbColorTexturesA[i], 0);
      checkGLError("FT c");
    }
    for (int i = 0; i < usedColorBuffers; i++)
    {
      bni.i(dfbColorTexturesA[(8 + i)]);
      
      GL11.glTexParameteri(3553, 10242, 10496);
      GL11.glTexParameteri(3553, 10243, 10496);
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      GL11.glTexImage2D(3553, 0, gbuffersFormat[i], renderWidth, renderHeight, 0, 32993, 33639, (ByteBuffer)null);
      checkGLError("FT ca");
    }
    int status = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
    if (status == 36058)
    {
      printChatAndLogError("[Shaders] Error: Failed framebuffer incomplete formats");
      for (int i = 0; i < usedColorBuffers; i++)
      {
        bni.i(dfbColorTextures.get(i));
        GL11.glTexImage2D(3553, 0, 6408, renderWidth, renderHeight, 0, 32993, 33639, (ByteBuffer)null);
        EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + i, 3553, dfbColorTextures.get(i), 0);
        checkGLError("FT c");
      }
      status = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
      if (status == 36053) {
        SMCLog.info("complete");
      }
    }
    bni.i(0);
    if (status != 36053) {
      printChatAndLogError("[Shaders] Error: Failed creating framebuffer! (Status " + status + ")");
    } else {
      SMCLog.info("Framebuffer created.");
    }
  }
  
  private static void setupShadowFrameBuffer()
  {
    if (usedShadowDepthBuffers == 0) {
      return;
    }
    if (sfb != 0)
    {
      EXTFramebufferObject.glDeleteFramebuffersEXT(sfb);
      bni.deleteTextures(sfbDepthTextures);
      bni.deleteTextures(sfbColorTextures);
    }
    sfb = EXTFramebufferObject.glGenFramebuffersEXT();
    EXTFramebufferObject.glBindFramebufferEXT(36160, sfb);
    GL11.glDrawBuffer(0);
    GL11.glReadBuffer(0);
    
    GL11.glGenTextures((IntBuffer)sfbDepthTextures.clear().limit(usedShadowDepthBuffers));
    GL11.glGenTextures((IntBuffer)sfbColorTextures.clear().limit(usedShadowColorBuffers));
    
    sfbDepthTextures.position(0);
    sfbColorTextures.position(0);
    for (int i = 0; i < usedShadowDepthBuffers; i++)
    {
      bni.i(sfbDepthTextures.get(i));
      GL11.glTexParameterf(3553, 10242, 10496.0F);
      GL11.glTexParameterf(3553, 10243, 10496.0F);
      int filter = shadowFilterNearest[i] != 0 ? 9728 : 9729;
      GL11.glTexParameteri(3553, 10241, filter);
      GL11.glTexParameteri(3553, 10240, filter);
      if (shadowHardwareFilteringEnabled[i] != 0) {
        GL11.glTexParameteri(3553, 34892, 34894);
      }
      GL11.glTexImage2D(3553, 0, 6402, shadowMapWidth, shadowMapHeight, 0, 6402, 5126, (FloatBuffer)null);
    }
    EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, sfbDepthTextures.get(0), 0);
    checkGLError("FT sd");
    for (int i = 0; i < usedShadowColorBuffers; i++)
    {
      bni.i(sfbColorTextures.get(i));
      GL11.glTexParameterf(3553, 10242, 10496.0F);
      GL11.glTexParameterf(3553, 10243, 10496.0F);
      int filter = shadowColorFilterNearest[i] != 0 ? 9728 : 9729;
      GL11.glTexParameteri(3553, 10241, filter);
      GL11.glTexParameteri(3553, 10240, filter);
      GL11.glTexImage2D(3553, 0, 6408, shadowMapWidth, shadowMapHeight, 0, 32993, 33639, (ByteBuffer)null);
      EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + i, 3553, sfbColorTextures.get(i), 0);
      checkGLError("FT sc");
    }
    bni.i(0);
    if (usedShadowColorBuffers > 0) {
      GL20.glDrawBuffers(sfbDrawBuffers);
    }
    int status = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
    if (status != 36053) {
      printChatAndLogError("[Shaders] Error: Failed creating shadow framebuffer! (Status " + status + ")");
    } else {
      SMCLog.info("Shadow framebuffer created.");
    }
  }
  
  public static void beginRender(bcf minecraft, float partialTicks, long finishTimeNano)
  {
    checkGLError("pre beginRender");
    
    checkWorldChanged(mc.f);
    
    mc = minecraft;
    mc.B.a("init");
    entityRenderer = mc.o;
    if (!isShaderPackInitialized) {
      try
      {
        init();
      }
      catch (IllegalStateException e)
      {
        if (Config.normalize(e.getMessage()).equals("Function is not supported"))
        {
          printChatAndLogError("[Shaders] Error: " + e.getMessage());
          
          e.printStackTrace();
          
          setShaderPack(packNameNone);
          
          return;
        }
      }
    }
    if ((mc.d != renderDisplayWidth) || (mc.e != renderDisplayHeight)) {
      resize();
    }
    if (needResizeShadow) {
      resizeShadow();
    }
    worldTime = mc.f.Q();
    diffWorldTime = (worldTime - lastWorldTime) % 24000L;
    if (diffWorldTime < 0L) {
      diffWorldTime += 24000L;
    }
    lastWorldTime = worldTime;
    moonPhase = mc.f.D();
    
    systemTime = System.currentTimeMillis();
    if (lastSystemTime == 0L) {
      lastSystemTime = systemTime;
    }
    diffSystemTime = systemTime - lastSystemTime;
    lastSystemTime = systemTime;
    
    frameTimeCounter += (float)diffSystemTime * 0.001F;
    frameTimeCounter %= 3600.0F;
    
    rainStrength = minecraft.f.j(partialTicks);
    
    float fadeScalar = (float)diffSystemTime * 0.01F;
    
    float temp1 = (float)Math.exp(Math.log(0.5D) * fadeScalar / (wetness < rainStrength ? drynessHalfLife : wetnessHalfLife));
    wetness = wetness * temp1 + rainStrength * (1.0F - temp1);
    
    rr renderViewEntity = mc.aa();
    isSleeping = ((renderViewEntity instanceof sa)) && (((sa)renderViewEntity).cl());
    
    eyePosY = (float)renderViewEntity.q * partialTicks + (float)renderViewEntity.N * (1.0F - partialTicks);
    eyeBrightness = renderViewEntity.d(partialTicks);
    
    float fadeScalar = (float)diffSystemTime * 0.01F;
    float temp2 = (float)Math.exp(Math.log(0.5D) * fadeScalar / eyeBrightnessHalflife);
    eyeBrightnessFadeX = eyeBrightnessFadeX * temp2 + (eyeBrightness & 0xFFFF) * (1.0F - temp2);
    eyeBrightnessFadeY = eyeBrightnessFadeY * temp2 + (eyeBrightness >> 16) * (1.0F - temp2);
    
    isEyeInWater = (mc.u.ap == 0) && (!isSleeping) && (mc.h.a(axe.h)) ? 1 : 0;
    
    bbj skyColorV = mc.f.a(mc.aa(), partialTicks);
    skyColorR = (float)skyColorV.b;
    skyColorG = (float)skyColorV.c;
    skyColorB = (float)skyColorV.d;
    
    isRenderingWorld = true;
    isCompositeRendered = false;
    isHandRendered = false;
    if (usedShadowDepthBuffers >= 1)
    {
      bni.g(33988);
      bni.i(sfbDepthTextures.get(0));
      if (usedShadowDepthBuffers >= 2)
      {
        bni.g(33989);
        bni.i(sfbDepthTextures.get(1));
      }
    }
    bni.g(33984);
    for (int i = 0; i < usedColorBuffers; i++)
    {
      bni.i(dfbColorTexturesA[i]);
      GL11.glTexParameteri(3553, 10240, 9729);
      GL11.glTexParameteri(3553, 10241, 9729);
      bni.i(dfbColorTexturesA[(8 + i)]);
      GL11.glTexParameteri(3553, 10240, 9729);
      GL11.glTexParameteri(3553, 10241, 9729);
    }
    bni.i(0);
    for (int i = 0; (i < 4) && (4 + i < usedColorBuffers); i++)
    {
      bni.g(33991 + i);
      bni.i(dfbColorTextures.get(4 + i));
    }
    bni.g(33990);
    bni.i(dfbDepthTextures.get(0));
    if (usedDepthBuffers >= 2)
    {
      bni.g(33995);
      bni.i(dfbDepthTextures.get(1));
      if (usedDepthBuffers >= 3)
      {
        bni.g(33996);
        bni.i(dfbDepthTextures.get(2));
      }
    }
    for (int i = 0; i < usedShadowColorBuffers; i++)
    {
      bni.g(33997 + i);
      bni.i(sfbColorTextures.get(i));
    }
    if (noiseTextureEnabled)
    {
      bni.g(33984 + noiseTexture.textureUnit);
      bni.i(noiseTexture.getID());
      GL11.glTexParameteri(3553, 10242, 10497);
      GL11.glTexParameteri(3553, 10243, 10497);
      GL11.glTexParameteri(3553, 10240, 9729);
      GL11.glTexParameteri(3553, 10241, 9729);
    }
    bni.g(33984);
    
    previousCameraPositionX = cameraPositionX;
    previousCameraPositionY = cameraPositionY;
    previousCameraPositionZ = cameraPositionZ;
    
    previousProjection.position(0);
    projection.position(0);
    previousProjection.put(projection);
    previousProjection.position(0);
    projection.position(0);
    previousModelView.position(0);
    modelView.position(0);
    previousModelView.put(modelView);
    previousModelView.position(0);
    modelView.position(0);
    
    checkGLError("beginRender");
    
    ShadersRender.renderShadowMap(entityRenderer, 0, partialTicks, finishTimeNano);
    
    mc.B.b();
    
    EXTFramebufferObject.glBindFramebufferEXT(36160, dfb);
    for (int i = 0; i < usedColorBuffers; i++)
    {
      colorTexturesToggle[i] = 0;
      EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + i, 3553, dfbColorTexturesA[i], 0);
    }
    checkGLError("end beginRender");
  }
  
  private static void checkWorldChanged(aht world)
  {
    if (currentWorld == world) {
      return;
    }
    aht oldWorld = currentWorld;
    
    currentWorld = world;
    if ((oldWorld == null) || (world == null)) {
      return;
    }
    int dimIdOld = oldWorld.s.p().a();
    int dimIdNew = world.s.p().a();
    
    boolean dimShadersOld = shaderPackDimensions.contains(Integer.valueOf(dimIdOld));
    boolean dimShadersNew = shaderPackDimensions.contains(Integer.valueOf(dimIdNew));
    if ((dimShadersOld) || (dimShadersNew)) {
      uninit();
    }
  }
  
  public static void beginRenderPass(int pass, float partialTicks, long finishTimeNano)
  {
    if (isShadowPass) {
      return;
    }
    EXTFramebufferObject.glBindFramebufferEXT(36160, dfb);
    GL11.glViewport(0, 0, renderWidth, renderHeight);
    activeDrawBuffers = null;
    ShadersTex.bindNSTextures(defaultTexture.getMultiTexID());
    useProgram(2);
    checkGLError("end beginRenderPass");
  }
  
  public static void setViewport(int vx, int vy, int vw, int vh)
  {
    bni.a(true, true, true, true);
    if (isShadowPass)
    {
      GL11.glViewport(0, 0, shadowMapWidth, shadowMapHeight);
    }
    else
    {
      GL11.glViewport(0, 0, renderWidth, renderHeight);
      EXTFramebufferObject.glBindFramebufferEXT(36160, dfb);
      isRenderingDfb = true;
      bni.q();
      bni.k();
      setDrawBuffers(drawBuffersNone);
      useProgram(2);
      checkGLError("beginRenderPass");
    }
  }
  
  public static int setFogMode(int val)
  {
    return fogMode = val;
  }
  
  public static void setFogColor(float r, float g, float b)
  {
    fogColorR = r;
    fogColorG = g;
    fogColorB = b;
  }
  
  public static void setClearColor(float red, float green, float blue, float alpha)
  {
    bni.a(red, green, blue, alpha);
    clearColorR = red;
    clearColorG = green;
    clearColorB = blue;
  }
  
  public static void clearRenderBuffer()
  {
    if (isShadowPass)
    {
      checkGLError("shadow clear pre");
      EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, sfbDepthTextures.get(0), 0);
      GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
      
      GL20.glDrawBuffers(programsDrawBuffers[30]);
      checkFramebufferStatus("shadow clear");
      GL11.glClear(16640);
      checkGLError("shadow clear");
      return;
    }
    checkGLError("clear pre");
    
    GL20.glDrawBuffers(36064);
    
    GL11.glClear(16384);
    
    GL20.glDrawBuffers(36065);
    GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glClear(16384);
    for (int i = 2; i < usedColorBuffers; i++)
    {
      GL20.glDrawBuffers(36064 + i);
      GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
      GL11.glClear(16384);
    }
    setDrawBuffers(dfbDrawBuffers);
    checkFramebufferStatus("clear");
    checkGLError("clear");
  }
  
  public static void setCamera(float partialTicks)
  {
    rr viewEntity = mc.aa();
    
    double x = viewEntity.M + (viewEntity.p - viewEntity.M) * partialTicks;
    double y = viewEntity.N + (viewEntity.q - viewEntity.N) * partialTicks;
    double z = viewEntity.O + (viewEntity.r - viewEntity.O) * partialTicks;
    
    cameraPositionX = x;
    cameraPositionY = y;
    cameraPositionZ = z;
    
    GL11.glGetFloat(2983, (FloatBuffer)projection.position(0));
    SMath.invertMat4FBFA((FloatBuffer)projectionInverse.position(0), (FloatBuffer)projection.position(0), faProjectionInverse, faProjection);
    projection.position(0);
    projectionInverse.position(0);
    
    GL11.glGetFloat(2982, (FloatBuffer)modelView.position(0));
    SMath.invertMat4FBFA((FloatBuffer)modelViewInverse.position(0), (FloatBuffer)modelView.position(0), faModelViewInverse, faModelView);
    modelView.position(0);
    modelViewInverse.position(0);
    
    checkGLError("setCamera");
  }
  
  public static void setCameraShadow(float partialTicks)
  {
    rr viewEntity = mc.aa();
    double x = viewEntity.M + (viewEntity.p - viewEntity.M) * partialTicks;
    double y = viewEntity.N + (viewEntity.q - viewEntity.N) * partialTicks;
    double z = viewEntity.O + (viewEntity.r - viewEntity.O) * partialTicks;
    
    cameraPositionX = x;
    cameraPositionY = y;
    cameraPositionZ = z;
    
    GL11.glGetFloat(2983, (FloatBuffer)projection.position(0));
    SMath.invertMat4FBFA((FloatBuffer)projectionInverse.position(0), (FloatBuffer)projection.position(0), faProjectionInverse, faProjection);
    projection.position(0);
    projectionInverse.position(0);
    
    GL11.glGetFloat(2982, (FloatBuffer)modelView.position(0));
    SMath.invertMat4FBFA((FloatBuffer)modelViewInverse.position(0), (FloatBuffer)modelView.position(0), faModelViewInverse, faModelView);
    modelView.position(0);
    modelViewInverse.position(0);
    
    GL11.glViewport(0, 0, shadowMapWidth, shadowMapHeight);
    
    GL11.glMatrixMode(5889);
    GL11.glLoadIdentity();
    if (shadowMapIsOrtho) {
      GL11.glOrtho(-shadowMapHalfPlane, shadowMapHalfPlane, -shadowMapHalfPlane, shadowMapHalfPlane, 0.05000000074505806D, 256.0D);
    } else {
      GLU.gluPerspective(shadowMapFOV, shadowMapWidth / shadowMapHeight, 0.05F, 256.0F);
    }
    GL11.glMatrixMode(5888);
    GL11.glLoadIdentity();
    GL11.glTranslatef(0.0F, 0.0F, -100.0F);
    GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
    celestialAngle = mc.f.c(partialTicks);
    sunAngle = celestialAngle < 0.75F ? celestialAngle + 0.25F : celestialAngle - 0.75F;
    float angle = celestialAngle * -360.0F;
    float angleInterval = shadowAngleInterval > 0.0F ? angle % shadowAngleInterval - shadowAngleInterval * 0.5F : 0.0F;
    if (sunAngle <= 0.5D)
    {
      GL11.glRotatef(angle - angleInterval, 0.0F, 0.0F, 1.0F);
      GL11.glRotatef(sunPathRotation, 1.0F, 0.0F, 0.0F);
      shadowAngle = sunAngle;
    }
    else
    {
      GL11.glRotatef(angle + 180.0F - angleInterval, 0.0F, 0.0F, 1.0F);
      GL11.glRotatef(sunPathRotation, 1.0F, 0.0F, 0.0F);
      shadowAngle = sunAngle - 0.5F;
    }
    if (shadowMapIsOrtho)
    {
      float trans = shadowIntervalSize;
      float trans2 = trans / 2.0F;
      GL11.glTranslatef((float)x % trans - trans2, (float)y % trans - trans2, (float)z % trans - trans2);
    }
    float raSun = sunAngle * 6.2831855F;
    float x1 = (float)Math.cos(raSun);
    float y1 = (float)Math.sin(raSun);
    float raTilt = sunPathRotation * 6.2831855F;
    float x2 = x1;
    float y2 = y1 * (float)Math.cos(raTilt);
    float z2 = y1 * (float)Math.sin(raTilt);
    if (sunAngle > 0.5D)
    {
      x2 = -x2;
      y2 = -y2;
      z2 = -z2;
    }
    shadowLightPositionVector[0] = x2;
    shadowLightPositionVector[1] = y2;
    shadowLightPositionVector[2] = z2;
    shadowLightPositionVector[3] = 0.0F;
    
    GL11.glGetFloat(2983, (FloatBuffer)shadowProjection.position(0));
    SMath.invertMat4FBFA((FloatBuffer)shadowProjectionInverse.position(0), (FloatBuffer)shadowProjection.position(0), faShadowProjectionInverse, faShadowProjection);
    shadowProjection.position(0);
    shadowProjectionInverse.position(0);
    
    GL11.glGetFloat(2982, (FloatBuffer)shadowModelView.position(0));
    SMath.invertMat4FBFA((FloatBuffer)shadowModelViewInverse.position(0), (FloatBuffer)shadowModelView.position(0), faShadowModelViewInverse, faShadowModelView);
    shadowModelView.position(0);
    shadowModelViewInverse.position(0);
    
    setProgramUniformMatrix4ARB("gbufferProjection", false, projection);
    setProgramUniformMatrix4ARB("gbufferProjectionInverse", false, projectionInverse);
    setProgramUniformMatrix4ARB("gbufferPreviousProjection", false, previousProjection);
    setProgramUniformMatrix4ARB("gbufferModelView", false, modelView);
    setProgramUniformMatrix4ARB("gbufferModelViewInverse", false, modelViewInverse);
    setProgramUniformMatrix4ARB("gbufferPreviousModelView", false, previousModelView);
    setProgramUniformMatrix4ARB("shadowProjection", false, shadowProjection);
    setProgramUniformMatrix4ARB("shadowProjectionInverse", false, shadowProjectionInverse);
    setProgramUniformMatrix4ARB("shadowModelView", false, shadowModelView);
    setProgramUniformMatrix4ARB("shadowModelViewInverse", false, shadowModelViewInverse);
    
    mc.u.ap = 1;
    checkGLError("setCamera");
  }
  
  public static void preCelestialRotate()
  {
    setUpPosition();
    GL11.glRotatef(sunPathRotation * 1.0F, 0.0F, 0.0F, 1.0F);
    checkGLError("preCelestialRotate");
  }
  
  public static void postCelestialRotate()
  {
    FloatBuffer modelView = tempMatrixDirectBuffer;
    modelView.clear();
    GL11.glGetFloat(2982, modelView);
    modelView.get(tempMat, 0, 16);
    SMath.multiplyMat4xVec4(sunPosition, tempMat, sunPosModelView);
    SMath.multiplyMat4xVec4(moonPosition, tempMat, moonPosModelView);
    System.arraycopy(shadowAngle == sunAngle ? sunPosition : moonPosition, 0, shadowLightPosition, 0, 3);
    checkGLError("postCelestialRotate");
  }
  
  public static void setUpPosition()
  {
    FloatBuffer modelView = tempMatrixDirectBuffer;
    modelView.clear();
    GL11.glGetFloat(2982, modelView);
    modelView.get(tempMat, 0, 16);
    SMath.multiplyMat4xVec4(upPosition, tempMat, upPosModelView);
  }
  
  public static void genCompositeMipmap()
  {
    if (hasGlGenMipmap)
    {
      for (int i = 0; i < usedColorBuffers; i++) {
        if ((activeCompositeMipmapSetting & 1 << i) != 0)
        {
          bni.g(33984 + colorTextureTextureImageUnit[i]);
          GL11.glTexParameteri(3553, 10241, 9987);
          GL30.glGenerateMipmap(3553);
        }
      }
      bni.g(33984);
    }
  }
  
  public static void drawComposite()
  {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glBegin(7);
    GL11.glTexCoord2f(0.0F, 0.0F);
    GL11.glVertex3f(0.0F, 0.0F, 0.0F);
    GL11.glTexCoord2f(1.0F, 0.0F);
    GL11.glVertex3f(1.0F, 0.0F, 0.0F);
    GL11.glTexCoord2f(1.0F, 1.0F);
    GL11.glVertex3f(1.0F, 1.0F, 0.0F);
    GL11.glTexCoord2f(0.0F, 1.0F);
    GL11.glVertex3f(0.0F, 1.0F, 0.0F);
    GL11.glEnd();
  }
  
  public static void renderCompositeFinal()
  {
    if (isShadowPass) {
      return;
    }
    checkGLError("pre-renderCompositeFinal");
    
    GL11.glPushMatrix();
    GL11.glLoadIdentity();
    GL11.glMatrixMode(5889);
    GL11.glPushMatrix();
    GL11.glLoadIdentity();
    GL11.glOrtho(0.0D, 1.0D, 0.0D, 1.0D, 0.0D, 1.0D);
    
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    bni.y();
    bni.d();
    bni.l();
    bni.k();
    bni.c(519);
    bni.a(false);
    bni.g();
    if (usedShadowDepthBuffers >= 1)
    {
      bni.g(33988);
      bni.i(sfbDepthTextures.get(0));
      if (usedShadowDepthBuffers >= 2)
      {
        bni.g(33989);
        bni.i(sfbDepthTextures.get(1));
      }
    }
    for (int i = 0; i < usedColorBuffers; i++)
    {
      bni.g(33984 + colorTextureTextureImageUnit[i]);
      bni.i(dfbColorTexturesA[i]);
    }
    bni.g(33990);
    bni.i(dfbDepthTextures.get(0));
    if (usedDepthBuffers >= 2)
    {
      bni.g(33995);
      bni.i(dfbDepthTextures.get(1));
      if (usedDepthBuffers >= 3)
      {
        bni.g(33996);
        bni.i(dfbDepthTextures.get(2));
      }
    }
    for (int i = 0; i < usedShadowColorBuffers; i++)
    {
      bni.g(33997 + i);
      bni.i(sfbColorTextures.get(i));
    }
    if (noiseTextureEnabled)
    {
      bni.g(33984 + noiseTexture.textureUnit);
      bni.i(noiseTexture.getID());
      GL11.glTexParameteri(3553, 10242, 10497);
      GL11.glTexParameteri(3553, 10243, 10497);
      GL11.glTexParameteri(3553, 10240, 9729);
      GL11.glTexParameteri(3553, 10241, 9729);
    }
    bni.g(33984);
    
    boolean enableAltBuffers = true;
    for (int i = 0; i < usedColorBuffers; i++) {
      EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + i, 3553, dfbColorTexturesA[(8 + i)], 0);
    }
    EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, dfbDepthTextures.get(0), 0);
    
    GL20.glDrawBuffers(dfbDrawBuffers);
    checkGLError("pre-composite");
    for (int cp = 0; cp < 8; cp++) {
      if (programsID[(21 + cp)] != 0)
      {
        useProgram(21 + cp);
        checkGLError(programNames[(21 + cp)]);
        if (activeCompositeMipmapSetting != 0) {
          genCompositeMipmap();
        }
        drawComposite();
        for (int i = 0; i < usedColorBuffers; i++) {
          if (programsToggleColorTextures[(21 + cp)][i] != 0)
          {
            int t0 = colorTexturesToggle[i];
            int t1 = colorTexturesToggle[i] = 8 - t0;
            bni.g(33984 + colorTextureTextureImageUnit[i]);
            bni.i(dfbColorTexturesA[(t1 + i)]);
            EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064 + i, 3553, dfbColorTexturesA[(t0 + i)], 0);
          }
        }
        bni.g(33984);
      }
    }
    checkGLError("composite");
    
    isRenderingDfb = false;
    mc.b().a(true);
    bzg.a(bzg.c, bzg.e, 3553, mc.b().g, 0);
    GL11.glViewport(0, 0, mc.d, mc.e);
    if (bng.a)
    {
      boolean maskR = bng.b != 0;
      bni.a(maskR, !maskR, !maskR, true);
    }
    bni.a(true);
    GL11.glClearColor(clearColorR, clearColorG, clearColorB, 1.0F);
    GL11.glClear(16640);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    bni.y();
    bni.d();
    bni.l();
    bni.k();
    bni.c(519);
    bni.a(false);
    
    checkGLError("pre-final");
    useProgram(29);
    checkGLError("final");
    if (activeCompositeMipmapSetting != 0) {
      genCompositeMipmap();
    }
    drawComposite();
    
    checkGLError("renderCompositeFinal");
    isCompositeRendered = true;
    bni.f();
    bni.y();
    bni.e();
    bni.m();
    bni.c(515);
    bni.a(true);
    
    GL11.glPopMatrix();
    GL11.glMatrixMode(5888);
    GL11.glPopMatrix();
    
    useProgram(0);
  }
  
  public static void endRender()
  {
    if (isShadowPass)
    {
      checkGLError("shadow endRender");
      return;
    }
    if (!isCompositeRendered) {
      renderCompositeFinal();
    }
    isRenderingWorld = false;
    
    bni.a(true, true, true, true);
    
    useProgram(0);
    bcd.a();
    checkGLError("endRender end");
  }
  
  public static void beginSky()
  {
    isRenderingSky = true;
    fogEnabled = true;
    setDrawBuffers(dfbDrawBuffers);
    useProgram(5);
    pushEntity(-2, 0);
  }
  
  public static void setSkyColor(bbj v3color)
  {
    skyColorR = (float)v3color.b;
    skyColorG = (float)v3color.c;
    skyColorB = (float)v3color.d;
    setProgramUniform3f("skyColor", skyColorR, skyColorG, skyColorB);
  }
  
  public static void drawHorizon()
  {
    bmz tess = bnu.a().c();
    float farDistance = mc.u.c * 16;
    double xzq = farDistance * 0.9238D;
    double xzp = farDistance * 0.3826D;
    double xzn = -xzp;
    double xzm = -xzq;
    double top = 16.0D;
    double bot = -cameraPositionY;
    
    tess.a(7, bvp.e);
    
    tess.b(xzn, bot, xzm).d();
    tess.b(xzn, top, xzm).d();
    tess.b(xzm, top, xzn).d();
    tess.b(xzm, bot, xzn).d();
    
    tess.b(xzm, bot, xzn).d();
    tess.b(xzm, top, xzn).d();
    tess.b(xzm, top, xzp).d();
    tess.b(xzm, bot, xzp).d();
    
    tess.b(xzm, bot, xzp).d();
    tess.b(xzm, top, xzp).d();
    tess.b(xzn, top, xzp).d();
    tess.b(xzn, bot, xzp).d();
    
    tess.b(xzn, bot, xzp).d();
    tess.b(xzn, top, xzp).d();
    tess.b(xzp, top, xzq).d();
    tess.b(xzp, bot, xzq).d();
    
    tess.b(xzp, bot, xzq).d();
    tess.b(xzp, top, xzq).d();
    tess.b(xzq, top, xzp).d();
    tess.b(xzq, bot, xzp).d();
    
    tess.b(xzq, bot, xzp).d();
    tess.b(xzq, top, xzp).d();
    tess.b(xzq, top, xzn).d();
    tess.b(xzq, bot, xzn).d();
    
    tess.b(xzq, bot, xzn).d();
    tess.b(xzq, top, xzn).d();
    tess.b(xzp, top, xzm).d();
    tess.b(xzp, bot, xzm).d();
    
    tess.b(xzp, bot, xzm).d();
    tess.b(xzp, top, xzm).d();
    tess.b(xzn, top, xzm).d();
    tess.b(xzn, bot, xzm).d();
    
    bnu.a().b();
  }
  
  public static void preSkyList()
  {
    GL11.glColor3f(fogColorR, fogColorG, fogColorB);
    
    drawHorizon();
    
    GL11.glColor3f(skyColorR, skyColorG, skyColorB);
  }
  
  public static void endSky()
  {
    isRenderingSky = false;
    setDrawBuffers(dfbDrawBuffers);
    useProgram(lightmapEnabled ? 3 : 2);
    popEntity();
  }
  
  public static void beginUpdateChunks()
  {
    checkGLError("beginUpdateChunks1");
    checkFramebufferStatus("beginUpdateChunks1");
    if (!isShadowPass) {
      useProgram(7);
    }
    checkGLError("beginUpdateChunks2");
    checkFramebufferStatus("beginUpdateChunks2");
  }
  
  public static void endUpdateChunks()
  {
    checkGLError("endUpdateChunks1");
    checkFramebufferStatus("endUpdateChunks1");
    if (!isShadowPass) {
      useProgram(7);
    }
    checkGLError("endUpdateChunks2");
    checkFramebufferStatus("endUpdateChunks2");
  }
  
  public static boolean shouldRenderClouds(bch gs)
  {
    if (!shaderPackLoaded) {
      return true;
    }
    checkGLError("shouldRenderClouds");
    return gs.h > 0 ? true : isShadowPass ? configCloudShadow : false;
  }
  
  public static void beginClouds()
  {
    fogEnabled = true;
    pushEntity(-3, 0);
    useProgram(6);
  }
  
  public static void endClouds()
  {
    disableFog();
    popEntity();
  }
  
  public static void beginEntities()
  {
    if (isRenderingWorld)
    {
      useProgram(16);
      if (programsID[activeProgram] != 0)
      {
        useEntityHurtFlash = (uniformEntityHurt != -1) || (uniformEntityFlash != -1);
        if (uniformEntityHurt != -1) {
          ARBShaderObjects.glUniform1iARB(uniformEntityHurt, 0);
        }
        if (uniformEntityHurt != -1) {
          ARBShaderObjects.glUniform1iARB(uniformEntityFlash, 0);
        }
      }
      else
      {
        useEntityHurtFlash = false;
      }
      resetDisplayListModels();
    }
  }
  
  public static void nextEntity()
  {
    if (isRenderingWorld) {
      useProgram(16);
    }
  }
  
  public static void beginSpiderEyes()
  {
    if (isRenderingWorld) {
      if (programsID[18] != programsID[0])
      {
        useProgram(18);
        bni.e();
        bni.a(516, 0.0F);
        bni.b(770, 771);
      }
    }
  }
  
  public static void endEntities()
  {
    if (isRenderingWorld) {
      useProgram(lightmapEnabled ? 3 : 2);
    }
  }
  
  public static void setEntityHurtFlash(int hurt, int flash)
  {
    if ((useEntityHurtFlash) && (isRenderingWorld) && (!isShadowPass))
    {
      if (uniformEntityHurt != -1) {
        ARBShaderObjects.glUniform1iARB(uniformEntityHurt, hurt);
      }
      if (uniformEntityFlash != -1) {
        ARBShaderObjects.glUniform1iARB(uniformEntityFlash, flash >> 24);
      }
      checkGLError("setEntityHurtFlash");
    }
  }
  
  public static void resetEntityHurtFlash()
  {
    setEntityHurtFlash(0, 0);
  }
  
  public static void beginLivingDamage()
  {
    if (isRenderingWorld)
    {
      ShadersTex.bindTexture(defaultTexture);
      if (!isShadowPass) {
        setDrawBuffers(drawBuffersColorAtt0);
      }
    }
  }
  
  public static void endLivingDamage()
  {
    if (isRenderingWorld) {
      if (!isShadowPass) {
        setDrawBuffers(programsDrawBuffers[16]);
      }
    }
  }
  
  public static void beginBlockEntities()
  {
    if (isRenderingWorld)
    {
      checkGLError("beginBlockEntities");
      useProgram(13);
    }
  }
  
  public static void endBlockEntities()
  {
    if (isRenderingWorld)
    {
      checkGLError("endBlockEntities");
      useProgram(lightmapEnabled ? 3 : 2);
      ShadersTex.bindNSTextures(defaultTexture.getMultiTexID());
    }
  }
  
  public static void beginLitParticles()
  {
    useProgram(3);
  }
  
  public static void beginParticles()
  {
    useProgram(2);
  }
  
  public static void endParticles()
  {
    useProgram(3);
  }
  
  public static void readCenterDepth()
  {
    if (!isShadowPass) {
      if (centerDepthSmoothEnabled)
      {
        tempDirectFloatBuffer.clear();
        GL11.glReadPixels(renderWidth / 2, renderHeight / 2, 1, 1, 6402, 5126, tempDirectFloatBuffer);
        centerDepth = tempDirectFloatBuffer.get(0);
        
        float fadeScalar = (float)diffSystemTime * 0.01F;
        float fadeFactor = (float)Math.exp(Math.log(0.5D) * fadeScalar / centerDepthSmoothHalflife);
        centerDepthSmooth = centerDepthSmooth * fadeFactor + centerDepth * (1.0F - fadeFactor);
      }
    }
  }
  
  public static void beginWeather()
  {
    if (!isShadowPass)
    {
      if (usedDepthBuffers >= 3)
      {
        bni.g(33996);
        GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, renderWidth, renderHeight);
        bni.g(33984);
      }
      bni.k();
      bni.m();
      bni.b(770, 771);
      bni.e();
      useProgram(20);
    }
  }
  
  public static void endWeather()
  {
    bni.l();
    useProgram(3);
  }
  
  public static void preWater()
  {
    if (usedDepthBuffers >= 2)
    {
      bni.g(33995);
      checkGLError("pre copy depth");
      GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, renderWidth, renderHeight);
      checkGLError("copy depth");
      bni.g(33984);
    }
    ShadersTex.bindNSTextures(defaultTexture.getMultiTexID());
  }
  
  public static void beginWater()
  {
    if (isRenderingWorld) {
      if (!isShadowPass)
      {
        useProgram(12);
        bni.m();
        bni.a(true);
      }
      else
      {
        bni.a(true);
      }
    }
  }
  
  public static void endWater()
  {
    if (isRenderingWorld) {
      useProgram((!isShadowPass) || (lightmapEnabled) ? 3 : 2);
    }
  }
  
  public static void beginProjectRedHalo()
  {
    if (isRenderingWorld) {
      useProgram(1);
    }
  }
  
  public static void endProjectRedHalo()
  {
    if (isRenderingWorld) {
      useProgram(3);
    }
  }
  
  public static void applyHandDepth()
  {
    if (configHandDepthMul != 1.0D) {
      GL11.glScaled(1.0D, 1.0D, configHandDepthMul);
    }
  }
  
  public static void beginHand()
  {
    GL11.glMatrixMode(5888);
    GL11.glPushMatrix();
    GL11.glMatrixMode(5889);
    GL11.glPushMatrix();
    GL11.glMatrixMode(5888);
    useProgram(19);
    checkGLError("beginHand");
    checkFramebufferStatus("beginHand");
  }
  
  public static void endHand()
  {
    checkGLError("pre endHand");
    checkFramebufferStatus("pre endHand");
    GL11.glMatrixMode(5889);
    GL11.glPopMatrix();
    GL11.glMatrixMode(5888);
    GL11.glPopMatrix();
    bni.b(770, 771);
    
    checkGLError("endHand");
  }
  
  public static void beginFPOverlay()
  {
    bni.g();
    bni.l();
  }
  
  public static void glEnableWrapper(int cap)
  {
    GL11.glEnable(cap);
    if (cap == 3553) {
      enableTexture2D();
    } else if (cap == 2912) {
      enableFog();
    }
  }
  
  public static void glDisableWrapper(int cap)
  {
    GL11.glDisable(cap);
    if (cap == 3553) {
      disableTexture2D();
    } else if (cap == 2912) {
      disableFog();
    }
  }
  
  public static void sglEnableT2D(int cap)
  {
    GL11.glEnable(cap);
    enableTexture2D();
  }
  
  public static void sglDisableT2D(int cap)
  {
    GL11.glDisable(cap);
    disableTexture2D();
  }
  
  public static void sglEnableFog(int cap)
  {
    GL11.glEnable(cap);
    enableFog();
  }
  
  public static void sglDisableFog(int cap)
  {
    GL11.glDisable(cap);
    disableFog();
  }
  
  public static void enableTexture2D()
  {
    if (isRenderingSky) {
      useProgram(5);
    } else if (activeProgram == 1) {
      useProgram(lightmapEnabled ? 3 : 2);
    }
  }
  
  public static void disableTexture2D()
  {
    if (isRenderingSky) {
      useProgram(4);
    } else if ((activeProgram == 2) || (activeProgram == 3)) {
      useProgram(1);
    }
  }
  
  public static void beginLeash()
  {
    useProgram(1);
  }
  
  public static void endLeash()
  {
    useProgram(16);
  }
  
  public static void enableFog()
  {
    fogEnabled = true;
    setProgramUniform1i("fogMode", fogMode);
  }
  
  public static void disableFog()
  {
    fogEnabled = false;
    setProgramUniform1i("fogMode", 0);
  }
  
  public static void setFog(bni.m fogMode)
  {
    bni.a(fogMode);
    fogMode = fogMode.d;
    if (fogEnabled) {
      setProgramUniform1i("fogMode", fogMode.d);
    }
  }
  
  public static void sglFogi(int pname, int param)
  {
    GL11.glFogi(pname, param);
    if (pname == 2917)
    {
      fogMode = param;
      if (fogEnabled) {
        setProgramUniform1i("fogMode", fogMode);
      }
    }
  }
  
  public static void enableLightmap()
  {
    lightmapEnabled = true;
    if (activeProgram == 2) {
      useProgram(3);
    }
  }
  
  public static void disableLightmap()
  {
    lightmapEnabled = false;
    if (activeProgram == 3) {
      useProgram(2);
    }
  }
  
  public static int[] entityData = new int[32];
  public static int entityDataIndex = 0;
  
  public static int getEntityData()
  {
    return entityData[(entityDataIndex * 2)];
  }
  
  public static int getEntityData2()
  {
    return entityData[(entityDataIndex * 2 + 1)];
  }
  
  public static int setEntityData1(int data1)
  {
    entityData[(entityDataIndex * 2)] = (entityData[(entityDataIndex * 2)] & 0xFFFF | data1 << 16);
    return data1;
  }
  
  public static int setEntityData2(int data2)
  {
    entityData[(entityDataIndex * 2 + 1)] = (entityData[(entityDataIndex * 2 + 1)] & 0xFFFF0000 | data2 & 0xFFFF);
    return data2;
  }
  
  public static void pushEntity(int data0, int data1)
  {
    entityDataIndex += 1;
    entityData[(entityDataIndex * 2)] = (data0 & 0xFFFF | data1 << 16);
    entityData[(entityDataIndex * 2 + 1)] = 0;
  }
  
  public static void pushEntity(int data0)
  {
    entityDataIndex += 1;
    entityData[(entityDataIndex * 2)] = (data0 & 0xFFFF);
    entityData[(entityDataIndex * 2 + 1)] = 0;
  }
  
  public static void pushEntity(ajt block)
  {
    entityDataIndex += 1;
    int blockRenderType = block.a(block.u()).ordinal();
    entityData[(entityDataIndex * 2)] = (ajt.h.a(block) & 0xFFFF | blockRenderType << 16);
    entityData[(entityDataIndex * 2 + 1)] = 0;
  }
  
  public static void popEntity()
  {
    entityData[(entityDataIndex * 2)] = 0;
    entityData[(entityDataIndex * 2 + 1)] = 0;
    entityDataIndex -= 1;
  }
  
  public static void mcProfilerEndSection()
  {
    mc.B.b();
  }
  
  public static String getShaderPackName()
  {
    if (shaderPack == null) {
      return null;
    }
    if ((shaderPack instanceof ShaderPackNone)) {
      return null;
    }
    return shaderPack.getName();
  }
  
  public static void nextAntialiasingLevel()
  {
    configAntialiasingLevel += 2;
    
    configAntialiasingLevel = configAntialiasingLevel / 2 * 2;
    if (configAntialiasingLevel > 4) {
      configAntialiasingLevel = 0;
    }
    configAntialiasingLevel = Config.limit(configAntialiasingLevel, 0, 4);
  }
  
  public static void checkShadersModInstalled()
  {
    try
    {
      cls = Class.forName("shadersmod.transform.SMCClassTransformer");
    }
    catch (Throwable e)
    {
      Class cls;
      return;
    }
    throw new RuntimeException("Shaders Mod detected. Please remove it, OptiFine has built-in support for shaders.");
  }
  
  private static void loadShaderPackResources()
  {
    shaderPackResources = new HashMap();
    if (!shaderPackLoaded) {
      return;
    }
    List<String> listFiles = new ArrayList();
    String PREFIX = "/shaders/lang/";
    String EN_US = "en_US";
    String SUFFIX = ".lang";
    listFiles.add(PREFIX + EN_US + SUFFIX);
    if (!Config.getGameSettings().aB.equals(EN_US)) {
      listFiles.add(PREFIX + Config.getGameSettings().aB + SUFFIX);
    }
    try
    {
      for (it = listFiles.iterator(); it.hasNext();)
      {
        String file = (String)it.next();
        InputStream in = shaderPack.getResourceAsStream(file);
        if (in != null)
        {
          props = new Properties();
          Lang.loadLocaleData(in, props);
          in.close();
          
          Set keys = props.keySet();
          for (itp = keys.iterator(); itp.hasNext();)
          {
            String key = (String)itp.next();
            String value = props.getProperty(key);
            shaderPackResources.put(key, value);
          }
        }
      }
    }
    catch (IOException e)
    {
      Iterator it;
      Properties props;
      Iterator itp;
      e.printStackTrace();
    }
  }
  
  public static String translate(String key, String def)
  {
    String str = (String)shaderPackResources.get(key);
    if (str == null) {
      return def;
    }
    return str;
  }
  
  public static boolean isProgramPath(String program)
  {
    if (program == null) {
      return false;
    }
    if (program.length() <= 0) {
      return false;
    }
    int pos = program.lastIndexOf("/");
    if (pos >= 0) {
      program = program.substring(pos + 1);
    }
    return Arrays.asList(programNames).contains(program);
  }
  
  static void checkOptifine() {}
  
  public static void nextBlockEntity() {}
  
  public static void endFPOverlay() {}
  
  public static void resourcesReloaded() {}
}
