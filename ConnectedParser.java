import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConnectedParser
{
  private String context = null;
  private static final MatchBlock[] NO_MATCH_BLOCKS = new MatchBlock[0];
  
  public ConnectedParser(String context)
  {
    this.context = context;
  }
  
  public String parseName(String path)
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
  
  public String parseBasePath(String path)
  {
    int pos = path.lastIndexOf('/');
    if (pos < 0) {
      return "";
    }
    return path.substring(0, pos);
  }
  
  public MatchBlock[] parseMatchBlocks(String propMatchBlocks)
  {
    if (propMatchBlocks == null) {
      return null;
    }
    List list = new ArrayList();
    String[] blockStrs = Config.tokenize(propMatchBlocks, " ");
    for (int i = 0; i < blockStrs.length; i++)
    {
      String blockStr = blockStrs[i];
      MatchBlock[] mbs = parseMatchBlock(blockStr);
      if (mbs == null) {
        return NO_MATCH_BLOCKS;
      }
      list.addAll(Arrays.asList(mbs));
    }
    MatchBlock[] mbs = (MatchBlock[])list.toArray(new MatchBlock[list.size()]);
    
    return mbs;
  }
  
  public MatchBlock[] parseMatchBlock(String blockStr)
  {
    if (blockStr == null) {
      return null;
    }
    blockStr = blockStr.trim();
    if (blockStr.length() <= 0) {
      return null;
    }
    String[] parts = Config.tokenize(blockStr, ":");
    
    String domain = "minecraft";
    int blockIndex = 0;
    if ((parts.length > 1) && (isFullBlockName(parts)))
    {
      domain = parts[0];
      blockIndex = 1;
    }
    else
    {
      domain = "minecraft";
      blockIndex = 0;
    }
    String blockPart = parts[blockIndex];
    String[] params = (String[])Arrays.copyOfRange(parts, blockIndex + 1, parts.length);
    
    ajt[] blocks = parseBlockPart(domain, blockPart);
    if (blocks == null) {
      return null;
    }
    MatchBlock[] datas = new MatchBlock[blocks.length];
    for (int i = 0; i < blocks.length; i++)
    {
      ajt block = blocks[i];
      int blockId = ajt.a(block);
      
      int[] metadatas = null;
      if (params.length > 0)
      {
        metadatas = parseBlockMetadatas(block, params);
        if (metadatas == null) {
          return null;
        }
      }
      MatchBlock bd = new MatchBlock(blockId, metadatas);
      datas[i] = bd;
    }
    return datas;
  }
  
  public boolean isFullBlockName(String[] parts)
  {
    if (parts.length < 2) {
      return false;
    }
    String part1 = parts[1];
    if (part1.length() < 1) {
      return false;
    }
    if (startsWithDigit(part1)) {
      return false;
    }
    if (part1.contains("=")) {
      return false;
    }
    return true;
  }
  
  public boolean startsWithDigit(String str)
  {
    if (str == null) {
      return false;
    }
    if (str.length() < 1) {
      return false;
    }
    char ch = str.charAt(0);
    
    return Character.isDigit(ch);
  }
  
  public ajt[] parseBlockPart(String domain, String blockPart)
  {
    if (startsWithDigit(blockPart))
    {
      int[] ids = parseIntList(blockPart);
      if (ids == null) {
        return null;
      }
      ajt[] blocks = new ajt[ids.length];
      for (int i = 0; i < ids.length; i++)
      {
        int id = ids[i];
        ajt block = ajt.b(id);
        if (block == null)
        {
          warn("Block not found for id: " + id);
          return null;
        }
        blocks[i] = block;
      }
      return blocks;
    }
    String fullName = domain + ":" + blockPart;
    ajt block = ajt.b(fullName);
    if (block == null)
    {
      warn("Block not found for name: " + fullName);
      return null;
    }
    ajt[] blocks = { block };
    
    return blocks;
  }
  
  public int[] parseBlockMetadatas(ajt block, String[] params)
  {
    if (params.length <= 0) {
      return null;
    }
    String param0 = params[0];
    if (startsWithDigit(param0))
    {
      int[] mds = parseIntList(param0);
      return mds;
    }
    arc stateDefault = block.u();
    Collection properties = stateDefault.r();
    
    Map<arr, List<Comparable>> mapPropValues = new HashMap();
    for (int i = 0; i < params.length; i++)
    {
      String param = params[i];
      if (param.length() > 0)
      {
        String[] parts = Config.tokenize(param, "=");
        if (parts.length != 2)
        {
          warn("Invalid block property: " + param);
          return null;
        }
        String key = parts[0];
        String valStr = parts[1];
        
        arr prop = ConnectedProperties.getProperty(key, properties);
        if (prop == null)
        {
          warn("Property not found: " + key + ", block: " + block);
          return null;
        }
        List<Comparable> list = (List)mapPropValues.get(key);
        if (list == null)
        {
          list = new ArrayList();
          mapPropValues.put(prop, list);
        }
        String[] vals = Config.tokenize(valStr, ",");
        for (int v = 0; v < vals.length; v++)
        {
          String val = vals[v];
          Comparable propVal = parsePropertyValue(prop, val);
          if (propVal == null)
          {
            warn("Property value not found: " + val + ", property: " + key + ", block: " + block);
            return null;
          }
          list.add(propVal);
        }
      }
    }
    if (mapPropValues.isEmpty()) {
      return null;
    }
    List listMetadatas = new ArrayList();
    for (int i = 0; i < 16; i++)
    {
      int md = i;
      try
      {
        arc bs = getStateFromMeta(block, md);
        if (matchState(bs, mapPropValues)) {
          listMetadatas.add(Integer.valueOf(md));
        }
      }
      catch (IllegalArgumentException e) {}
    }
    if (listMetadatas.size() == 16) {
      return null;
    }
    int[] metadatas = new int[listMetadatas.size()];
    for (int i = 0; i < metadatas.length; i++) {
      metadatas[i] = ((Integer)listMetadatas.get(i)).intValue();
    }
    return metadatas;
  }
  
  private arc getStateFromMeta(ajt block, int md)
  {
    try
    {
      arc bs = block.a(md);
      arc bsLow;
      if (block == aju.cF) {
        if (md > 7) {
          bsLow = block.a(md & 0x7);
        }
      }
      return bs.a(akw.a, bsLow.c(akw.a));
    }
    catch (IllegalArgumentException e) {}
    return block.u();
  }
  
  public static Comparable parsePropertyValue(arr prop, String valStr)
  {
    Class valueClass = prop.b();
    Comparable valueObj = parseValue(valStr, valueClass);
    if (valueObj == null)
    {
      Collection propertyValues = prop.c();
      valueObj = getPropertyValue(valStr, propertyValues);
    }
    return valueObj;
  }
  
  public static Comparable getPropertyValue(String value, Collection propertyValues)
  {
    for (Iterator it = propertyValues.iterator(); it.hasNext();)
    {
      Comparable obj = (Comparable)it.next();
      if (String.valueOf(obj).equals(value)) {
        return obj;
      }
    }
    return null;
  }
  
  public static Comparable parseValue(String str, Class cls)
  {
    if (cls == String.class) {
      return str;
    }
    if (cls == Boolean.class) {
      return Boolean.valueOf(str);
    }
    if (cls == Float.class) {
      return Float.valueOf(str);
    }
    if (cls == Double.class) {
      return Double.valueOf(str);
    }
    if (cls == Integer.class) {
      return Integer.valueOf(str);
    }
    if (cls == Long.class) {
      return Long.valueOf(str);
    }
    return null;
  }
  
  public boolean matchState(arc bs, Map<arr, List<Comparable>> mapPropValues)
  {
    Set<arr> keys = mapPropValues.keySet();
    for (Iterator<arr> it = keys.iterator(); it.hasNext();)
    {
      arr prop = (arr)it.next();
      List<Comparable> vals = (List)mapPropValues.get(prop);
      
      Comparable bsVal = bs.c(prop);
      if (bsVal == null) {
        return false;
      }
      if (!vals.contains(bsVal)) {
        return false;
      }
    }
    return true;
  }
  
  public aig[] parseBiomes(String str)
  {
    if (str == null) {
      return null;
    }
    String[] biomeNames = Config.tokenize(str, " ");
    List list = new ArrayList();
    for (int i = 0; i < biomeNames.length; i++)
    {
      String biomeName = biomeNames[i];
      aig biome = findBiome(biomeName);
      if (biome == null) {
        warn("Biome not found: " + biomeName);
      } else {
        list.add(biome);
      }
    }
    aig[] biomeArr = (aig[])list.toArray(new aig[list.size()]);
    return biomeArr;
  }
  
  public aig findBiome(String biomeName)
  {
    biomeName = biomeName.toLowerCase();
    if (biomeName.equals("nether")) {
      return ail.j;
    }
    Set<kk> biomeIds = aig.q.c();
    for (Iterator it = biomeIds.iterator(); it.hasNext();)
    {
      kk loc = (kk)it.next();
      aig biome = (aig)aig.q.c(loc);
      if (biome != null)
      {
        String name = biome.l().replace(" ", "").toLowerCase();
        if (name.equals(biomeName)) {
          return biome;
        }
      }
    }
    return null;
  }
  
  public int parseInt(String str)
  {
    if (str == null) {
      return -1;
    }
    int num = Config.parseInt(str, -1);
    if (num < 0) {
      warn("Invalid number: " + str);
    }
    return num;
  }
  
  public int parseInt(String str, int defVal)
  {
    if (str == null) {
      return defVal;
    }
    int num = Config.parseInt(str, -1);
    if (num < 0)
    {
      warn("Invalid number: " + str);
      return defVal;
    }
    return num;
  }
  
  public int[] parseIntList(String str)
  {
    if (str == null) {
      return null;
    }
    List list = new ArrayList();
    String[] intStrs = Config.tokenize(str, " ,");
    for (int i = 0; i < intStrs.length; i++)
    {
      String intStr = intStrs[i];
      if (intStr.contains("-"))
      {
        String[] subStrs = Config.tokenize(intStr, "-");
        if (subStrs.length != 2)
        {
          warn("Invalid interval: " + intStr + ", when parsing: " + str);
        }
        else
        {
          int min = Config.parseInt(subStrs[0], -1);
          int max = Config.parseInt(subStrs[1], -1);
          if ((min < 0) || (max < 0) || (min > max)) {
            warn("Invalid interval: " + intStr + ", when parsing: " + str);
          } else {
            for (int n = min; n <= max; n++) {
              list.add(Integer.valueOf(n));
            }
          }
        }
      }
      else
      {
        int val = Config.parseInt(intStr, -1);
        if (val < 0) {
          warn("Invalid number: " + intStr + ", when parsing: " + str);
        } else {
          list.add(Integer.valueOf(val));
        }
      }
    }
    int[] ints = new int[list.size()];
    for (int i = 0; i < ints.length; i++) {
      ints[i] = ((Integer)list.get(i)).intValue();
    }
    return ints;
  }
  
  public boolean[] parseFaces(String str, boolean[] defVal)
  {
    if (str == null) {
      return defVal;
    }
    EnumSet setFaces = EnumSet.allOf(cq.class);
    String[] faceStrs = Config.tokenize(str, " ,");
    for (int i = 0; i < faceStrs.length; i++)
    {
      String faceStr = faceStrs[i];
      if (faceStr.equals("sides"))
      {
        setFaces.add(cq.c);
        setFaces.add(cq.d);
        setFaces.add(cq.e);
        setFaces.add(cq.f);
      }
      else if (faceStr.equals("all"))
      {
        setFaces.addAll(Arrays.asList(cq.n));
      }
      else
      {
        cq face = parseFace(faceStr);
        if (face != null) {
          setFaces.add(face);
        }
      }
    }
    boolean[] faces = new boolean[cq.n.length];
    for (int i = 0; i < faces.length; i++) {
      faces[i] = setFaces.contains(cq.n[i]);
    }
    return faces;
  }
  
  public cq parseFace(String str)
  {
    str = str.toLowerCase();
    if ((str.equals("bottom")) || (str.equals("down"))) {
      return cq.a;
    }
    if ((str.equals("top")) || (str.equals("up"))) {
      return cq.b;
    }
    if (str.equals("north")) {
      return cq.c;
    }
    if (str.equals("south")) {
      return cq.d;
    }
    if (str.equals("east")) {
      return cq.f;
    }
    if (str.equals("west")) {
      return cq.e;
    }
    Config.warn("Unknown face: " + str);
    
    return null;
  }
  
  public void dbg(String str)
  {
    Config.dbg("" + this.context + ": " + str);
  }
  
  public void warn(String str)
  {
    Config.warn("" + this.context + ": " + str);
  }
  
  public RangeListInt parseRangeListInt(String str)
  {
    if (str == null) {
      return null;
    }
    RangeListInt list = new RangeListInt();
    String[] parts = Config.tokenize(str, " ,");
    for (int i = 0; i < parts.length; i++)
    {
      String part = parts[i];
      RangeInt ri = parseRangeInt(part);
      if (ri == null) {
        return null;
      }
      list.addRange(ri);
    }
    return list;
  }
  
  private RangeInt parseRangeInt(String str)
  {
    if (str == null) {
      return null;
    }
    if (str.indexOf('-') >= 0)
    {
      String[] parts = Config.tokenize(str, "-");
      if (parts.length != 2)
      {
        warn("Invalid range: " + str);
        return null;
      }
      int min = Config.parseInt(parts[0], -1);
      int max = Config.parseInt(parts[1], -1);
      if ((min < 0) || (max < 0))
      {
        warn("Invalid range: " + str);
        return null;
      }
      return new RangeInt(min, max);
    }
    int val = Config.parseInt(str, -1);
    if (val < 0)
    {
      warn("Invalid integer: " + str);
      return null;
    }
    return new RangeInt(val, val);
  }
  
  public static boolean parseBoolean(String str)
  {
    if (str == null) {
      return false;
    }
    return str.toLowerCase().equals("true");
  }
  
  public static int parseColor(String str, int defVal)
  {
    if (str == null) {
      return defVal;
    }
    str = str.trim();
    try
    {
      return Integer.parseInt(str, 16) & 0xFFFFFF;
    }
    catch (NumberFormatException e) {}
    return defVal;
  }
}
