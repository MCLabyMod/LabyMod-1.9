import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import org.lwjgl.opengl.GL11;

public class CustomItemProperties
{
  public String name = null;
  public String basePath = null;
  public int type = 1;
  public int[] items = null;
  public String texture = null;
  public Map<String, String> mapTextures = null;
  public RangeListInt damage = null;
  public boolean damagePercent = false;
  public int damageMask = 0;
  public RangeListInt stackSize = null;
  public RangeListInt enchantmentIds = null;
  public RangeListInt enchantmentLevels = null;
  public NbtTagValue[] nbtTagValues = null;
  public int blend = 1;
  public float speed = 0.0F;
  public float rotation = 0.0F;
  public int layer = 0;
  public float duration = 1.0F;
  public int weight = 0;
  public kk textureLocation = null;
  public Map mapTextureLocations = null;
  public bvh sprite = null;
  public Map mapSprites = null;
  public bxo model = null;
  public Map<String, bxo> mapModels = null;
  private int textureWidth = 0;
  private int textureHeight = 0;
  public static final int TYPE_UNKNOWN = 0;
  public static final int TYPE_ITEM = 1;
  public static final int TYPE_ENCHANTMENT = 2;
  public static final int TYPE_ARMOR = 3;
  
  public CustomItemProperties(Properties props, String path)
  {
    this.name = parseName(path);
    this.basePath = parseBasePath(path);
    
    this.type = parseType(props.getProperty("type"));
    
    this.items = parseItems(props.getProperty("items"), props.getProperty("matchItems"));
    
    this.mapTextures = parseTextures(props, this.basePath);
    this.texture = parseTexture(props.getProperty("texture"), props.getProperty("tile"), props.getProperty("source"), path, this.basePath, this.type, this.mapTextures);
    
    String damageStr = props.getProperty("damage");
    if (damageStr != null)
    {
      this.damagePercent = damageStr.contains("%");
      damageStr.replace("%", "");
      this.damage = parseRangeListInt(damageStr);
      this.damageMask = parseInt(props.getProperty("damageMask"), 0);
    }
    this.stackSize = parseRangeListInt(props.getProperty("stackSize"));
    this.enchantmentIds = parseRangeListInt(props.getProperty("enchantmentIDs"));
    this.enchantmentLevels = parseRangeListInt(props.getProperty("enchantmentLevels"));
    this.nbtTagValues = parseNbtTagValues(props);
    
    this.blend = Blender.parseBlend(props.getProperty("blend"));
    this.speed = parseFloat(props.getProperty("speed"), 0.0F);
    this.rotation = parseFloat(props.getProperty("rotation"), 0.0F);
    this.layer = parseInt(props.getProperty("layer"), 0);
    this.weight = parseInt(props.getProperty("weight"), 0);
    this.duration = parseFloat(props.getProperty("duration"), 1.0F);
  }
  
  private static String parseName(String path)
  {
    String str = path;
    
    int pos = str.lastIndexOf('/');
    if (pos >= 0) {
      str = str.substring(pos + 1);
    }
    int pos2 = str.lastIndexOf('.');
    if (pos2 >= 0) {
      str = str.substring(0, pos2);
    }
    return str;
  }
  
  private static String parseBasePath(String path)
  {
    int pos = path.lastIndexOf('/');
    if (pos < 0) {
      return "";
    }
    return path.substring(0, pos);
  }
  
  private int parseType(String str)
  {
    if (str == null) {
      return 1;
    }
    if (str.equals("item")) {
      return 1;
    }
    if (str.equals("enchantment")) {
      return 2;
    }
    if (str.equals("armor")) {
      return 3;
    }
    Config.warn("Unknown method: " + str);
    
    return 0;
  }
  
  private int[] parseItems(String str, String str2)
  {
    if (str == null) {
      str = str2;
    }
    if (str == null) {
      return null;
    }
    str = str.trim();
    
    Set setItemIds = new TreeSet();
    String[] tokens = Config.tokenize(str, " ");
    for (int i = 0; i < tokens.length; i++)
    {
      String token = tokens[i];
      
      int val = Config.parseInt(token, -1);
      if (val >= 0)
      {
        setItemIds.add(new Integer(val));
      }
      else
      {
        if (token.contains("-"))
        {
          String[] parts = Config.tokenize(token, "-");
          if (parts.length == 2)
          {
            int val1 = Config.parseInt(parts[0], -1);
            int val2 = Config.parseInt(parts[1], -1);
            if ((val1 >= 0) && (val2 >= 0))
            {
              int min = Math.min(val1, val2);
              int max = Math.max(val1, val2);
              for (int x = min; x <= max; x++) {
                setItemIds.add(new Integer(x));
              }
              continue;
            }
          }
        }
        ado item = ado.d(token);
        if (item == null)
        {
          Config.warn("Item not found: " + token);
        }
        else
        {
          int id = ado.a(item);
          if (id < 0) {
            Config.warn("Item not found: " + token);
          } else {
            setItemIds.add(new Integer(id));
          }
        }
      }
    }
    Integer[] integers = (Integer[])setItemIds.toArray(new Integer[setItemIds.size()]);
    int[] ints = new int[integers.length];
    for (int i = 0; i < ints.length; i++) {
      ints[i] = integers[i].intValue();
    }
    return ints;
  }
  
  private static String parseTexture(String texStr, String texStr2, String texStr3, String path, String basePath, int type, Map<String, String> mapTexs)
  {
    if (texStr == null) {
      texStr = texStr2;
    }
    if (texStr == null) {
      texStr = texStr3;
    }
    if (texStr != null)
    {
      String png = ".png";
      if (texStr.endsWith(png)) {
        texStr = texStr.substring(0, texStr.length() - png.length());
      }
      texStr = fixTextureName(texStr, basePath);
      
      return texStr;
    }
    if (type == 3) {
      return null;
    }
    if (mapTexs != null)
    {
      String bowStandbyTex = (String)mapTexs.get("texture.bow_standby");
      if (bowStandbyTex != null) {
        return bowStandbyTex;
      }
    }
    String str = path;
    
    int pos = str.lastIndexOf('/');
    if (pos >= 0) {
      str = str.substring(pos + 1);
    }
    int pos2 = str.lastIndexOf('.');
    if (pos2 >= 0) {
      str = str.substring(0, pos2);
    }
    str = fixTextureName(str, basePath);
    
    return str;
  }
  
  private static Map parseTextures(Properties props, String basePath)
  {
    String prefix = "texture.";
    Map mapProps = getMatchingProperties(props, prefix);
    if (mapProps.size() <= 0) {
      return null;
    }
    Set keySet = mapProps.keySet();
    Map mapTex = new LinkedHashMap();
    for (Iterator it = keySet.iterator(); it.hasNext();)
    {
      String key = (String)it.next();
      String val = (String)mapProps.get(key);
      
      val = fixTextureName(val, basePath);
      
      mapTex.put(key, val);
    }
    return mapTex;
  }
  
  private static String fixTextureName(String iconName, String basePath)
  {
    iconName = TextureUtils.fixResourcePath(iconName, basePath);
    if ((!iconName.startsWith(basePath)) && (!iconName.startsWith("textures/")) && (!iconName.startsWith("mcpatcher/"))) {
      iconName = basePath + "/" + iconName;
    }
    if (iconName.endsWith(".png")) {
      iconName = iconName.substring(0, iconName.length() - 4);
    }
    String pathBlocks = "textures/blocks/";
    if (iconName.startsWith(pathBlocks)) {
      iconName = iconName.substring(pathBlocks.length());
    }
    if (iconName.startsWith("/")) {
      iconName = iconName.substring(1);
    }
    return iconName;
  }
  
  private int parseInt(String str, int defVal)
  {
    if (str == null) {
      return defVal;
    }
    str = str.trim();
    int val = Config.parseInt(str, Integer.MIN_VALUE);
    if (val == Integer.MIN_VALUE)
    {
      Config.warn("Invalid integer: " + str);
      return defVal;
    }
    return val;
  }
  
  private float parseFloat(String str, float defVal)
  {
    if (str == null) {
      return defVal;
    }
    str = str.trim();
    float val = Config.parseFloat(str, Float.MIN_VALUE);
    if (val == Float.MIN_VALUE)
    {
      Config.warn("Invalid float: " + str);
      return defVal;
    }
    return val;
  }
  
  private RangeListInt parseRangeListInt(String str)
  {
    if (str == null) {
      return null;
    }
    String[] tokens = Config.tokenize(str, " ");
    RangeListInt rangeList = new RangeListInt();
    for (int i = 0; i < tokens.length; i++)
    {
      String token = tokens[i];
      RangeInt range = parseRangeInt(token);
      if (range == null)
      {
        Config.warn("Invalid range list: " + str);
        return null;
      }
      rangeList.addRange(range);
    }
    return rangeList;
  }
  
  private RangeInt parseRangeInt(String str)
  {
    if (str == null) {
      return null;
    }
    str = str.trim();
    
    int countMinus = str.length() - str.replace("-", "").length();
    if (countMinus > 1)
    {
      Config.warn("Invalid range: " + str);
      return null;
    }
    String[] tokens = Config.tokenize(str, "- ");
    
    int[] vals = new int[tokens.length];
    for (int i = 0; i < tokens.length; i++)
    {
      String token = tokens[i];
      int val = Config.parseInt(token, -1);
      if (val < 0)
      {
        Config.warn("Invalid range: " + str);
        return null;
      }
      vals[i] = val;
    }
    if (vals.length == 1)
    {
      int val = vals[0];
      if (str.startsWith("-")) {
        return new RangeInt(0, val);
      }
      if (str.endsWith("-")) {
        return new RangeInt(val, 255);
      }
      return new RangeInt(val, val);
    }
    if (vals.length == 2)
    {
      int min = Math.min(vals[0], vals[1]);
      int max = Math.max(vals[0], vals[1]);
      return new RangeInt(min, max);
    }
    Config.warn("Invalid range: " + str);
    return null;
  }
  
  private NbtTagValue[] parseNbtTagValues(Properties props)
  {
    String PREFIX_NBT = "nbt.";
    
    Map mapNbt = getMatchingProperties(props, PREFIX_NBT);
    if (mapNbt.size() <= 0) {
      return null;
    }
    List listNbts = new ArrayList();
    Set keySet = mapNbt.keySet();
    for (Iterator it = keySet.iterator(); it.hasNext();)
    {
      String key = (String)it.next();
      String val = (String)mapNbt.get(key);
      
      String id = key.substring(PREFIX_NBT.length());
      
      NbtTagValue nbt = new NbtTagValue(id, val);
      listNbts.add(nbt);
    }
    NbtTagValue[] nbts = (NbtTagValue[])listNbts.toArray(new NbtTagValue[listNbts.size()]);
    
    return nbts;
  }
  
  private static Map getMatchingProperties(Properties props, String keyPrefix)
  {
    Map map = new LinkedHashMap();
    Set keySet = props.keySet();
    for (Iterator it = keySet.iterator(); it.hasNext();)
    {
      String key = (String)it.next();
      String val = props.getProperty(key);
      if (key.startsWith(keyPrefix)) {
        map.put(key, val);
      }
    }
    return map;
  }
  
  public boolean isValid(String path)
  {
    if ((this.name == null) || (this.name.length() <= 0))
    {
      Config.warn("No name found: " + path);
      return false;
    }
    if (this.basePath == null)
    {
      Config.warn("No base path found: " + path);
      return false;
    }
    if (this.type == 0)
    {
      Config.warn("No type defined: " + path);
      return false;
    }
    if ((this.type == 1) || (this.type == 3)) {
      if (this.items == null)
      {
        Config.warn("No items defined: " + path);
        return false;
      }
    }
    if ((this.texture == null) && (this.mapTextures == null))
    {
      Config.warn("No texture specified: " + path);
      return false;
    }
    if (this.type == 2) {
      if (this.enchantmentIds == null)
      {
        Config.warn("No enchantmentIDs specified: " + path);
        return false;
      }
    }
    return true;
  }
  
  public void updateIcons(bvg textureMap)
  {
    if (this.texture != null)
    {
      this.textureLocation = getTextureLocation(this.texture);
      if (this.type == 1)
      {
        kk spriteLocation = getSpriteLocation(this.textureLocation);
        this.sprite = textureMap.a(spriteLocation);
      }
    }
    Iterator it;
    if (this.mapTextures != null)
    {
      this.mapTextureLocations = new HashMap();
      this.mapSprites = new HashMap();
      Set keySet = this.mapTextures.keySet();
      for (it = keySet.iterator(); it.hasNext();)
      {
        String key = (String)it.next();
        String val = (String)this.mapTextures.get(key);
        
        kk locTex = getTextureLocation(val);
        this.mapTextureLocations.put(key, locTex);
        if (this.type == 1)
        {
          kk locSprite = getSpriteLocation(locTex);
          bvh icon = textureMap.a(locSprite);
          this.mapSprites.put(key, icon);
        }
      }
    }
  }
  
  private kk getTextureLocation(String texName)
  {
    if (texName == null) {
      return null;
    }
    kk resLoc = new kk(texName);
    String domain = resLoc.b();
    String path = resLoc.a();
    if (!path.contains("/")) {
      path = "textures/blocks/" + path;
    }
    String filePath = path + ".png";
    
    kk locFile = new kk(domain, filePath);
    boolean exists = Config.hasResource(locFile);
    if (!exists) {
      Config.warn("File not found: " + filePath);
    }
    return locFile;
  }
  
  private kk getSpriteLocation(kk resLoc)
  {
    String pathTex = resLoc.a();
    pathTex = StrUtils.removePrefix(pathTex, "textures/");
    pathTex = StrUtils.removeSuffix(pathTex, ".png");
    
    kk locTex = new kk(resLoc.b(), pathTex);
    
    return locTex;
  }
  
  public void updateModel(bvg textureMap, boo itemModelGenerator)
  {
    String[] textures = getModelTextures();
    boolean useTint = isUseTint();
    this.model = makeBakedModel(textureMap, itemModelGenerator, textures, useTint);
    Iterator it;
    if ((this.type == 1) && (this.mapTextures != null))
    {
      Set<String> keySet = this.mapTextures.keySet();
      for (it = keySet.iterator(); it.hasNext();)
      {
        String key = (String)it.next();
        String tex = (String)this.mapTextures.get(key);
        String path = StrUtils.removePrefix(key, "texture.");
        if ((path.startsWith("bow")) || (path.startsWith("fishing_rod")))
        {
          String[] texNames = { tex };
          bxo modelTex = makeBakedModel(textureMap, itemModelGenerator, texNames, useTint);
          if (this.mapModels == null) {
            this.mapModels = new HashMap();
          }
          this.mapModels.put(path, modelTex);
        }
      }
    }
  }
  
  private boolean isUseTint()
  {
    return true;
  }
  
  private static bxo makeBakedModel(bvg textureMap, boo itemModelGenerator, String[] textures, boolean useTint)
  {
    bok modelBlockBase = makeModelBlock(textures);
    bok modelBlock = itemModelGenerator.a(textureMap, modelBlockBase);
    bxo model = bakeModel(textureMap, modelBlock, useTint);
    return model;
  }
  
  private String[] getModelTextures()
  {
    if ((this.type == 1) && (this.items.length == 1))
    {
      ado item = ado.c(this.items[0]);
      if ((item == ads.bG) && (this.damage != null) && (this.damage.getCountRanges() > 0))
      {
        RangeInt range = this.damage.getRange(0);
        int valDamage = range.getMin();
        boolean splash = (valDamage & 0x4000) != 0;
        
        String texOverlay = getMapTexture(this.mapTextures, "texture.potion_overlay", "items/potion_overlay");
        
        String texMain = null;
        if (splash) {
          texMain = getMapTexture(this.mapTextures, "texture.potion_bottle_splash", "items/potion_bottle_splash");
        } else {
          texMain = getMapTexture(this.mapTextures, "texture.potion_bottle_drinkable", "items/potion_bottle_drinkable");
        }
        return new String[] { texOverlay, texMain };
      }
      if ((item instanceof abw))
      {
        abw itemArmor = (abw)item;
        if (itemArmor.d() == abw.a.a)
        {
          String material = "leather";
          String type = "helmet";
          if (itemArmor.c == rw.f) {
            type = "helmet";
          }
          if (itemArmor.c == rw.e) {
            type = "chestplate";
          }
          if (itemArmor.c == rw.d) {
            type = "leggings";
          }
          if (itemArmor.c == rw.c) {
            type = "boots";
          }
          String key = material + "_" + type;
          String texMain = getMapTexture(this.mapTextures, "texture." + key, "items/" + key);
          String texOverlay = getMapTexture(this.mapTextures, "texture." + key + "_overlay", "items/" + key + "_overlay");
          
          return new String[] { texMain, texOverlay };
        }
      }
    }
    return new String[] { this.texture };
  }
  
  private String getMapTexture(Map<String, String> map, String key, String def)
  {
    if (map == null) {
      return def;
    }
    String str = (String)map.get(key);
    if (str == null) {
      return def;
    }
    return str;
  }
  
  private static bok makeModelBlock(String[] modelTextures)
  {
    StringBuffer sb = new StringBuffer();
    sb.append("{\"parent\": \"builtin/generated\",\"textures\": {");
    for (int i = 0; i < modelTextures.length; i++)
    {
      String modelTexture = modelTextures[i];
      if (i > 0) {
        sb.append(", ");
      }
      sb.append("\"layer" + i + "\": \"" + modelTexture + "\"");
    }
    sb.append("}}");
    String modelStr = sb.toString();
    bok model = bok.a(modelStr);
    return model;
  }
  
  private static bxo bakeModel(bvg textureMap, bok modelBlockIn, boolean useTint)
  {
    bxp modelRotationIn = bxp.a;
    boolean uvLocked = false;
    
    bvh var4 = textureMap.getSpriteSafe(modelBlockIn.c("particle"));
    bxv.a var5 = new bxv.a(modelBlockIn, modelBlockIn.g()).a(var4);
    Iterator var6 = modelBlockIn.a().iterator();
    while (var6.hasNext())
    {
      bog var7 = (bog)var6.next();
      Iterator var8 = var7.c.keySet().iterator();
      while (var8.hasNext())
      {
        cq var9 = (cq)var8.next();
        boh var10 = (boh)var7.c.get(var9);
        if (!useTint) {
          var10 = new boh(var10.b, -1, var10.d, var10.e);
        }
        bvh var11 = textureMap.getSpriteSafe(modelBlockIn.c(var10.d));
        bof quad = makeBakedQuad(var7, var10, var11, var9, modelRotationIn, uvLocked);
        if (var10.b == null) {
          var5.a(quad);
        } else {
          var5.a(modelRotationIn.a(var10.b), quad);
        }
      }
    }
    return var5.b();
  }
  
  private static bof makeBakedQuad(bog blockPart, boh blockPartFace, bvh textureAtlasSprite, cq enumFacing, bxp modelRotation, boolean uvLocked)
  {
    bon faceBakery = new bon();
    return faceBakery.a(blockPart.a, blockPart.b, blockPartFace, textureAtlasSprite, enumFacing, modelRotation, blockPart.d, uvLocked, blockPart.e);
  }
  
  public String toString()
  {
    return "" + this.basePath + "/" + this.name + ", type: " + this.type + ", items: [" + Config.arrayToString(this.items) + "], textture: " + this.texture;
  }
  
  public float getTextureWidth(bvi textureManager)
  {
    if (this.textureWidth <= 0)
    {
      if (this.textureLocation != null)
      {
        bvj tex = textureManager.b(this.textureLocation);
        int texId = tex.b();
        int prevTexId = bni.getBoundTexture();
        
        bni.i(texId);
        
        this.textureWidth = GL11.glGetTexLevelParameteri(3553, 0, 4096);
        
        bni.i(prevTexId);
      }
      if (this.textureWidth <= 0) {
        this.textureWidth = 16;
      }
    }
    return this.textureWidth;
  }
  
  public float getTextureHeight(bvi textureManager)
  {
    if (this.textureHeight <= 0)
    {
      if (this.textureLocation != null)
      {
        bvj tex = textureManager.b(this.textureLocation);
        int texId = tex.b();
        int prevTexId = bni.getBoundTexture();
        
        bni.i(texId);
        
        this.textureHeight = GL11.glGetTexLevelParameteri(3553, 0, 4097);
        
        bni.i(prevTexId);
      }
      if (this.textureHeight <= 0) {
        this.textureHeight = 16;
      }
    }
    return this.textureHeight;
  }
  
  public bxo getModel(bxt modelLocation)
  {
    if ((modelLocation != null) && (this.mapTextures != null))
    {
      String modelPath = modelLocation.a();
      if (this.mapModels != null)
      {
        bxo customModel = (bxo)this.mapModels.get(modelPath);
        if (customModel != null) {
          return customModel;
        }
      }
    }
    return this.model;
  }
}
