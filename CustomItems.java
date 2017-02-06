import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import shadersmod.client.Shaders;
import shadersmod.client.ShadersRender;

public class CustomItems
{
  private static CustomItemProperties[][] itemProperties = (CustomItemProperties[][])null;
  private static CustomItemProperties[][] enchantmentProperties = (CustomItemProperties[][])null;
  private static Map mapPotionIds = null;
  private static boo itemModelGenerator = new boo();
  private static boolean useGlint = true;
  public static final int MASK_POTION_SPLASH = 16384;
  public static final int MASK_POTION_NAME = 63;
  public static final String KEY_TEXTURE_OVERLAY = "texture.potion_overlay";
  public static final String KEY_TEXTURE_SPLASH = "texture.potion_bottle_splash";
  public static final String KEY_TEXTURE_DRINKABLE = "texture.potion_bottle_drinkable";
  public static final String DEFAULT_TEXTURE_OVERLAY = "items/potion_overlay";
  public static final String DEFAULT_TEXTURE_SPLASH = "items/potion_bottle_splash";
  public static final String DEFAULT_TEXTURE_DRINKABLE = "items/potion_bottle_drinkable";
  private static final int[] EMPTY_INT_ARRAY = new int[0];
  private static final int[][] EMPTY_INT2_ARRAY = new int[0][];
  
  public static void updateIcons(bvg textureMap)
  {
    itemProperties = (CustomItemProperties[][])null;
    enchantmentProperties = (CustomItemProperties[][])null;
    useGlint = true;
    if (!Config.isCustomItems()) {
      return;
    }
    readCitProperties("mcpatcher/cit.properties");
    
    bwi[] rps = Config.getResourcePacks();
    for (int i = rps.length - 1; i >= 0; i--)
    {
      bwi rp = rps[i];
      updateIcons(textureMap, rp);
    }
    updateIcons(textureMap, Config.getDefaultResourcePack());
    if (itemProperties.length <= 0) {
      itemProperties = (CustomItemProperties[][])null;
    }
    if (enchantmentProperties.length <= 0) {
      enchantmentProperties = (CustomItemProperties[][])null;
    }
  }
  
  private static void readCitProperties(String fileName)
  {
    try
    {
      kk loc = new kk(fileName);
      InputStream in = Config.getResourceStream(loc);
      if (in == null) {
        return;
      }
      Config.dbg("CustomItems: Loading " + fileName);
      Properties props = new Properties();
      props.load(in);
      in.close();
      
      useGlint = Config.parseBoolean(props.getProperty("useGlint"), true);
    }
    catch (FileNotFoundException e) {}catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  private static void updateIcons(bvg textureMap, bwi rp)
  {
    String[] names = ResUtils.collectFiles(rp, "mcpatcher/cit/", ".properties", null);
    
    Map mapAutoProperties = makeAutoImageProperties(rp);
    if (mapAutoProperties.size() > 0)
    {
      Set keySetAuto = mapAutoProperties.keySet();
      String[] keysAuto = (String[])keySetAuto.toArray(new String[keySetAuto.size()]);
      names = (String[])Config.addObjectsToArray(names, keysAuto);
    }
    Arrays.sort(names);
    
    List itemList = makePropertyList(itemProperties);
    List enchantmentList = makePropertyList(enchantmentProperties);
    for (int i = 0; i < names.length; i++)
    {
      String name = names[i];
      Config.dbg("CustomItems: " + name);
      try
      {
        CustomItemProperties cip = null;
        if (mapAutoProperties.containsKey(name)) {
          cip = (CustomItemProperties)mapAutoProperties.get(name);
        }
        if (cip == null)
        {
          kk locFile = new kk(name);
          InputStream in = rp.a(locFile);
          if (in == null)
          {
            Config.warn("CustomItems file not found: " + name);
          }
          else
          {
            Properties props = new Properties();
            props.load(in);
            cip = new CustomItemProperties(props, name);
          }
        }
        else if (cip.isValid(name))
        {
          cip.updateIcons(textureMap);
          
          addToItemList(cip, itemList);
          
          addToEnchantmentList(cip, enchantmentList);
        }
      }
      catch (FileNotFoundException e)
      {
        Config.warn("CustomItems file not found: " + name);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    itemProperties = propertyListToArray(itemList);
    enchantmentProperties = propertyListToArray(enchantmentList);
    
    Comparator comp = getPropertiesComparator();
    for (int i = 0; i < itemProperties.length; i++)
    {
      CustomItemProperties[] cips = itemProperties[i];
      if (cips != null) {
        Arrays.sort(cips, comp);
      }
    }
    for (int i = 0; i < enchantmentProperties.length; i++)
    {
      CustomItemProperties[] cips = enchantmentProperties[i];
      if (cips != null) {
        Arrays.sort(cips, comp);
      }
    }
  }
  
  private static Comparator getPropertiesComparator()
  {
    Comparator comp = new Comparator()
    {
      public int compare(Object o1, Object o2)
      {
        CustomItemProperties cip1 = (CustomItemProperties)o1;
        CustomItemProperties cip2 = (CustomItemProperties)o2;
        if (cip1.layer != cip2.layer) {
          return cip1.layer - cip2.layer;
        }
        if (cip1.weight != cip2.weight) {
          return cip2.weight - cip1.weight;
        }
        if (!cip1.basePath.equals(cip2.basePath)) {
          return cip1.basePath.compareTo(cip2.basePath);
        }
        return cip1.name.compareTo(cip2.name);
      }
    };
    return comp;
  }
  
  public static void updateModels()
  {
    if (itemProperties == null) {
      return;
    }
    for (int id = 0; id < itemProperties.length; id++)
    {
      CustomItemProperties[] cips = itemProperties[id];
      if (cips != null) {
        for (int c = 0; c < cips.length; c++)
        {
          CustomItemProperties cip = cips[c];
          if (cip != null) {
            if (cip.type == 1)
            {
              bvg textureMap = bcf.z().R();
              cip.updateModel(textureMap, itemModelGenerator);
            }
          }
        }
      }
    }
  }
  
  private static Map makeAutoImageProperties(bwi rp)
  {
    Map map = new HashMap();
    map.putAll(makePotionImageProperties(rp, false));
    map.putAll(makePotionImageProperties(rp, true));
    return map;
  }
  
  private static Map makePotionImageProperties(bwi rp, boolean splash)
  {
    Map map = new HashMap();
    
    String type = splash ? "splash/" : "normal/";
    String[] prefixes = { "mcpatcher/cit/potion/" + type, "mcpatcher/cit/Potion/" + type };
    String[] suffixes = { ".png" };
    String[] names = ResUtils.collectFiles(rp, prefixes, suffixes);
    for (int i = 0; i < names.length; i++)
    {
      String path = names[i];
      String name = path;
      
      name = StrUtils.removePrefixSuffix(name, prefixes, suffixes);
      Properties props = makePotionProperties(name, splash, path);
      if (props != null)
      {
        String pathProp = StrUtils.removeSuffix(path, suffixes) + ".properties";
        
        CustomItemProperties cip = new CustomItemProperties(props, pathProp);
        map.put(pathProp, cip);
      }
    }
    return map;
  }
  
  private static Properties makePotionProperties(String name, boolean splash, String path)
  {
    if (StrUtils.endsWith(name, new String[] { "_n", "_s" })) {
      return null;
    }
    if ((name.equals("empty")) && (!splash))
    {
      int itemId = ado.a(ads.bJ);
      Properties props = new Properties();
      props.put("type", "item");
      props.put("items", "" + itemId);
      
      return props;
    }
    int potionItemId = ado.a(ads.bG);
    
    int[] damages = (int[])getMapPotionIds().get(name);
    if (damages == null)
    {
      Config.warn("Potion not found for image: " + path);
      return null;
    }
    StringBuffer bufDamage = new StringBuffer();
    for (int i = 0; i < damages.length; i++)
    {
      int damage = damages[i];
      if (splash) {
        damage |= 0x4000;
      }
      if (i > 0) {
        bufDamage.append(" ");
      }
      bufDamage.append(damage);
    }
    int damageMask = 16447;
    
    Properties props = new Properties();
    props.put("type", "item");
    props.put("items", "" + potionItemId);
    props.put("damage", "" + bufDamage.toString());
    props.put("damageMask", "" + damageMask);
    if (splash) {
      props.put("texture.potion_bottle_splash", name);
    } else {
      props.put("texture.potion_bottle_drinkable", name);
    }
    return props;
  }
  
  private static Map getMapPotionIds()
  {
    if (mapPotionIds == null)
    {
      mapPotionIds = new LinkedHashMap();
      
      mapPotionIds.put("water", new int[] { 0 });
      mapPotionIds.put("awkward", new int[] { 16 });
      mapPotionIds.put("thick", new int[] { 32 });
      mapPotionIds.put("potent", new int[] { 48 });
      
      mapPotionIds.put("regeneration", getPotionIds(1));
      mapPotionIds.put("moveSpeed", getPotionIds(2));
      mapPotionIds.put("fireResistance", getPotionIds(3));
      mapPotionIds.put("poison", getPotionIds(4));
      mapPotionIds.put("heal", getPotionIds(5));
      mapPotionIds.put("nightVision", getPotionIds(6));
      
      mapPotionIds.put("clear", getPotionIds(7));
      mapPotionIds.put("bungling", getPotionIds(23));
      mapPotionIds.put("charming", getPotionIds(39));
      mapPotionIds.put("rank", getPotionIds(55));
      
      mapPotionIds.put("weakness", getPotionIds(8));
      mapPotionIds.put("damageBoost", getPotionIds(9));
      mapPotionIds.put("moveSlowdown", getPotionIds(10));
      
      mapPotionIds.put("diffuse", getPotionIds(11));
      mapPotionIds.put("smooth", getPotionIds(27));
      mapPotionIds.put("refined", getPotionIds(43));
      mapPotionIds.put("acrid", getPotionIds(59));
      
      mapPotionIds.put("harm", getPotionIds(12));
      mapPotionIds.put("waterBreathing", getPotionIds(13));
      mapPotionIds.put("invisibility", getPotionIds(14));
      
      mapPotionIds.put("thin", getPotionIds(15));
      mapPotionIds.put("debonair", getPotionIds(31));
      mapPotionIds.put("sparkling", getPotionIds(47));
      mapPotionIds.put("stinky", getPotionIds(63));
    }
    return mapPotionIds;
  }
  
  private static int[] getPotionIds(int baseId)
  {
    return new int[] { baseId, baseId + 16, baseId + 32, baseId + 48 };
  }
  
  private static int getPotionNameDamage(String name)
  {
    String fullName = "effect." + name;
    
    Set<kk> keys = rk.b.c();
    for (Iterator it = keys.iterator(); it.hasNext();)
    {
      kk rl = (kk)it.next();
      rk potion = (rk)rk.b.c(rl);
      String potionName = potion.a();
      if (fullName.equals(potionName)) {
        return rk.a(potion);
      }
    }
    return -1;
  }
  
  private static List makePropertyList(CustomItemProperties[][] propsArr)
  {
    List list = new ArrayList();
    if (propsArr != null) {
      for (int i = 0; i < propsArr.length; i++)
      {
        CustomItemProperties[] props = propsArr[i];
        List propList = null;
        if (props != null) {
          propList = new ArrayList(Arrays.asList(props));
        }
        list.add(propList);
      }
    }
    return list;
  }
  
  private static CustomItemProperties[][] propertyListToArray(List list)
  {
    CustomItemProperties[][] propArr = new CustomItemProperties[list.size()][];
    for (int i = 0; i < list.size(); i++)
    {
      List subList = (List)list.get(i);
      if (subList != null)
      {
        CustomItemProperties[] subArr = (CustomItemProperties[])subList.toArray(new CustomItemProperties[subList.size()]);
        
        Arrays.sort(subArr, new CustomItemsComparator());
        
        propArr[i] = subArr;
      }
    }
    return propArr;
  }
  
  private static void addToItemList(CustomItemProperties cp, List itemList)
  {
    if (cp.items == null) {
      return;
    }
    for (int i = 0; i < cp.items.length; i++)
    {
      int itemId = cp.items[i];
      if (itemId <= 0) {
        Config.warn("Invalid item ID: " + itemId);
      } else {
        addToList(cp, itemList, itemId);
      }
    }
  }
  
  private static void addToEnchantmentList(CustomItemProperties cp, List enchantmentList)
  {
    if (cp.type != 2) {
      return;
    }
    if (cp.enchantmentIds == null) {
      return;
    }
    for (int i = 0; i < 256; i++)
    {
      int id = i;
      if (cp.enchantmentIds.isInRange(id)) {
        addToList(cp, enchantmentList, id);
      }
    }
  }
  
  private static void addToList(CustomItemProperties cp, List list, int id)
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
    subList.add(cp);
  }
  
  public static bxo getCustomItemModel(adq itemStack, bxo model, bxt modelLocation)
  {
    if (model.b()) {
      return model;
    }
    if (itemProperties == null) {
      return model;
    }
    if (itemStack == null) {
      return model;
    }
    CustomItemProperties props = getCustomItemProperties(itemStack, 1);
    if (props == null) {
      return model;
    }
    return props.getModel(modelLocation);
  }
  
  public static boolean bindCustomArmorTexture(adq itemStack, int layer, String overlay)
  {
    if (itemProperties == null) {
      return false;
    }
    kk loc = getCustomArmorLocation(itemStack, layer, overlay);
    if (loc == null) {
      return false;
    }
    Config.getTextureManager().a(loc);
    
    return true;
  }
  
  private static kk getCustomArmorLocation(adq itemStack, int layer, String overlay)
  {
    if (itemStack == null) {
      return null;
    }
    CustomItemProperties props = getCustomItemProperties(itemStack, 3);
    if (props == null) {
      return null;
    }
    if (props.mapTextureLocations == null) {
      return null;
    }
    ado item = itemStack.b();
    if (!(item instanceof abw)) {
      return null;
    }
    abw itemArmor = (abw)item;
    String material = itemArmor.d().d();
    
    StringBuffer sb = new StringBuffer();
    sb.append("texture.");
    sb.append(material);
    sb.append("_layer_");
    sb.append(layer);
    if (overlay != null)
    {
      sb.append("_");
      sb.append(overlay);
    }
    String key = sb.toString();
    kk loc = (kk)props.mapTextureLocations.get(key);
    
    return loc;
  }
  
  private static CustomItemProperties getCustomItemProperties(adq itemStack, int type)
  {
    ado item = itemStack.b();
    
    int itemId = ado.a(item);
    if ((itemId >= 0) && (itemId < itemProperties.length))
    {
      CustomItemProperties[] cips = itemProperties[itemId];
      if (cips != null) {
        for (int i = 0; i < cips.length; i++)
        {
          CustomItemProperties cip = cips[i];
          if (cip.type == type) {
            if (matchesProperties(cip, itemStack, (int[][])null)) {
              return cip;
            }
          }
        }
      }
    }
    return null;
  }
  
  private static boolean matchesProperties(CustomItemProperties cip, adq itemStack, int[][] enchantmentIdLevels)
  {
    ado item = itemStack.b();
    if (cip.damage != null)
    {
      int damage = itemStack.h();
      if (cip.damageMask != 0) {
        damage &= cip.damageMask;
      }
      if (cip.damagePercent)
      {
        int damageMax = item.l();
        damage = (int)(damage * 100 / damageMax);
      }
      if (!cip.damage.isInRange(damage)) {
        return false;
      }
    }
    if (cip.stackSize != null) {
      if (!cip.stackSize.isInRange(itemStack.b)) {
        return false;
      }
    }
    int[][] idLevels = enchantmentIdLevels;
    if (cip.enchantmentIds != null)
    {
      if (idLevels == null) {
        idLevels = getEnchantmentIdLevels(itemStack);
      }
      boolean idMatch = false;
      for (int i = 0; i < idLevels.length; i++)
      {
        int id = idLevels[i][0];
        if (cip.enchantmentIds.isInRange(id))
        {
          idMatch = true;
          break;
        }
      }
      if (!idMatch) {
        return false;
      }
    }
    if (cip.enchantmentLevels != null)
    {
      if (idLevels == null) {
        idLevels = getEnchantmentIdLevels(itemStack);
      }
      boolean levelMatch = false;
      for (int i = 0; i < idLevels.length; i++)
      {
        int level = idLevels[i][1];
        if (cip.enchantmentLevels.isInRange(level))
        {
          levelMatch = true;
          break;
        }
      }
      if (!levelMatch) {
        return false;
      }
    }
    if (cip.nbtTagValues != null)
    {
      dn nbt = itemStack.o();
      for (int i = 0; i < cip.nbtTagValues.length; i++)
      {
        NbtTagValue ntv = cip.nbtTagValues[i];
        if (!ntv.matches(nbt)) {
          return false;
        }
      }
    }
    return true;
  }
  
  private static int[][] getEnchantmentIdLevels(adq itemStack)
  {
    ado item = itemStack.b();
    du nbt = item == ads.cn ? ads.cn.h(itemStack) : itemStack.p();
    if ((nbt == null) || (nbt.c() <= 0)) {
      return EMPTY_INT2_ARRAY;
    }
    int[][] arr = new int[nbt.c()][2];
    for (int i = 0; i < nbt.c(); i++)
    {
      dn tag = nbt.b(i);
      int id = tag.g("id");
      int lvl = tag.g("lvl");
      arr[i][0] = id;
      arr[i][1] = lvl;
    }
    return arr;
  }
  
  public static boolean renderCustomEffect(brz renderItem, adq itemStack, bxo model)
  {
    if (enchantmentProperties == null) {
      return false;
    }
    if (itemStack == null) {
      return false;
    }
    int[][] idLevels = getEnchantmentIdLevels(itemStack);
    if (idLevels.length <= 0) {
      return false;
    }
    Set layersRendered = null;
    
    boolean rendered = false;
    bvi textureManager = Config.getTextureManager();
    for (int i = 0; i < idLevels.length; i++)
    {
      int id = idLevels[i][0];
      if ((id >= 0) && (id < enchantmentProperties.length))
      {
        CustomItemProperties[] cips = enchantmentProperties[id];
        if (cips != null) {
          for (int p = 0; p < cips.length; p++)
          {
            CustomItemProperties cip = cips[p];
            if (layersRendered == null) {
              layersRendered = new HashSet();
            }
            if (layersRendered.add(Integer.valueOf(id))) {
              if (matchesProperties(cip, itemStack, idLevels)) {
                if (cip.textureLocation != null)
                {
                  textureManager.a(cip.textureLocation);
                  float width = cip.getTextureWidth(textureManager);
                  if (!rendered)
                  {
                    rendered = true;
                    
                    bni.a(false);
                    bni.c(514);
                    bni.g();
                    bni.n(5890);
                  }
                  Blender.setupBlend(cip.blend, 1.0F);
                  
                  bni.G();
                  bni.b(width / 2.0F, width / 2.0F, width / 2.0F);
                  float offset = cip.speed * (float)(bcf.I() % 3000L) / 3000.0F / 8.0F;
                  bni.c(offset, 0.0F, 0.0F);
                  bni.b(cip.rotation, 0.0F, 0.0F, 1.0F);
                  renderItem.a(model, -1);
                  bni.H();
                }
              }
            }
          }
        }
      }
    }
    if (rendered)
    {
      bni.e();
      bni.m();
      bni.b(770, 771);
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      
      bni.n(5888);
      bni.f();
      bni.c(515);
      bni.a(true);
      textureManager.a(bvg.g);
    }
    return rendered;
  }
  
  public static boolean renderCustomArmorEffect(sa entity, adq itemStack, bjc model, float limbSwing, float prevLimbSwing, float partialTicks, float timeLimbSwing, float yaw, float pitch, float scale)
  {
    if (enchantmentProperties == null) {
      return false;
    }
    if (Config.isShaders()) {
      if (Shaders.isShadowPass) {
        return false;
      }
    }
    if (itemStack == null) {
      return false;
    }
    int[][] idLevels = getEnchantmentIdLevels(itemStack);
    if (idLevels.length <= 0) {
      return false;
    }
    Set layersRendered = null;
    
    boolean rendered = false;
    bvi textureManager = Config.getTextureManager();
    for (int i = 0; i < idLevels.length; i++)
    {
      int id = idLevels[i][0];
      if ((id >= 0) && (id < enchantmentProperties.length))
      {
        CustomItemProperties[] cips = enchantmentProperties[id];
        if (cips != null) {
          for (int p = 0; p < cips.length; p++)
          {
            CustomItemProperties cip = cips[p];
            if (layersRendered == null) {
              layersRendered = new HashSet();
            }
            if (layersRendered.add(Integer.valueOf(id))) {
              if (matchesProperties(cip, itemStack, idLevels)) {
                if (cip.textureLocation != null)
                {
                  textureManager.a(cip.textureLocation);
                  float width = cip.getTextureWidth(textureManager);
                  if (!rendered)
                  {
                    rendered = true;
                    
                    ShadersRender.layerArmorBaseDrawEnchantedGlintBegin();
                    
                    bni.m();
                    bni.c(514);
                    bni.a(false);
                  }
                  Blender.setupBlend(cip.blend, 1.0F);
                  
                  bni.g();
                  bni.n(5890);
                  bni.F();
                  bni.b(cip.rotation, 0.0F, 0.0F, 1.0F);
                  float texScale = width / 8.0F;
                  bni.b(texScale, texScale / 2.0F, texScale);
                  float offset = cip.speed * (float)(bcf.I() % 3000L) / 3000.0F / 8.0F;
                  bni.c(0.0F, offset, 0.0F);
                  bni.n(5888);
                  model.a(entity, limbSwing, prevLimbSwing, timeLimbSwing, yaw, pitch, scale);
                }
              }
            }
          }
        }
      }
    }
    if (rendered)
    {
      bni.e();
      bni.m();
      bni.b(770, 771);
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      
      bni.n(5890);
      bni.F();
      bni.n(5888);
      bni.f();
      bni.a(true);
      bni.c(515);
      bni.l();
      if (Config.isShaders()) {
        ShadersRender.layerArmorBaseDrawEnchantedGlintEnd();
      }
    }
    return rendered;
  }
  
  public static boolean isUseGlint()
  {
    return useGlint;
  }
}
