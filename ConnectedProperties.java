import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class ConnectedProperties
{
  public String name = null;
  public String basePath = null;
  public MatchBlock[] matchBlocks = null;
  public int[] metadatas = null;
  public String[] matchTiles = null;
  public int method = 0;
  public String[] tiles = null;
  public int connect = 0;
  public int faces = 63;
  public aig[] biomes = null;
  public int minHeight = 0;
  public int maxHeight = 1024;
  public int renderPass = 0;
  public boolean innerSeams = false;
  public int width = 0;
  public int height = 0;
  public int[] weights = null;
  public int symmetry = 1;
  public int[] sumWeights = null;
  public int sumAllWeights = 1;
  public bvh[] matchTileIcons = null;
  public bvh[] tileIcons = null;
  public static final int METHOD_NONE = 0;
  public static final int METHOD_CTM = 1;
  public static final int METHOD_HORIZONTAL = 2;
  public static final int METHOD_TOP = 3;
  public static final int METHOD_RANDOM = 4;
  public static final int METHOD_REPEAT = 5;
  public static final int METHOD_VERTICAL = 6;
  public static final int METHOD_FIXED = 7;
  public static final int METHOD_HORIZONTAL_VERTICAL = 8;
  public static final int METHOD_VERTICAL_HORIZONTAL = 9;
  public static final int CONNECT_NONE = 0;
  public static final int CONNECT_BLOCK = 1;
  public static final int CONNECT_TILE = 2;
  public static final int CONNECT_MATERIAL = 3;
  public static final int CONNECT_UNKNOWN = 128;
  public static final int FACE_BOTTOM = 1;
  public static final int FACE_TOP = 2;
  public static final int FACE_NORTH = 4;
  public static final int FACE_SOUTH = 8;
  public static final int FACE_WEST = 16;
  public static final int FACE_EAST = 32;
  public static final int FACE_SIDES = 60;
  public static final int FACE_ALL = 63;
  public static final int FACE_UNKNOWN = 128;
  public static final int SYMMETRY_NONE = 1;
  public static final int SYMMETRY_OPPOSITE = 2;
  public static final int SYMMETRY_ALL = 6;
  public static final int SYMMETRY_UNKNOWN = 128;
  
  public ConnectedProperties(Properties props, String path)
  {
    ConnectedParser cp = new ConnectedParser("ConnectedTextures");
    
    this.name = cp.parseName(path);
    this.basePath = cp.parseBasePath(path);
    
    this.matchBlocks = cp.parseMatchBlocks(props.getProperty("matchBlocks"));
    this.metadatas = cp.parseIntList(props.getProperty("metadata"));
    
    this.matchTiles = parseMatchTiles(props.getProperty("matchTiles"));
    
    this.method = parseMethod(props.getProperty("method"));
    this.tiles = parseTileNames(props.getProperty("tiles"));
    this.connect = parseConnect(props.getProperty("connect"));
    this.faces = parseFaces(props.getProperty("faces"));
    
    this.biomes = cp.parseBiomes(props.getProperty("biomes"));
    this.minHeight = cp.parseInt(props.getProperty("minHeight"), -1);
    this.maxHeight = cp.parseInt(props.getProperty("maxHeight"), 1024);
    
    this.renderPass = cp.parseInt(props.getProperty("renderPass"));
    
    this.innerSeams = ConnectedParser.parseBoolean(props.getProperty("innerSeams"));
    
    this.width = cp.parseInt(props.getProperty("width"));
    this.height = cp.parseInt(props.getProperty("height"));
    
    this.weights = cp.parseIntList(props.getProperty("weights"));
    this.symmetry = parseSymmetry(props.getProperty("symmetry"));
  }
  
  private String[] parseMatchTiles(String str)
  {
    if (str == null) {
      return null;
    }
    String[] names = Config.tokenize(str, " ");
    for (int i = 0; i < names.length; i++)
    {
      String iconName = names[i];
      if (iconName.endsWith(".png")) {
        iconName = iconName.substring(0, iconName.length() - 4);
      }
      iconName = TextureUtils.fixResourcePath(iconName, this.basePath);
      
      names[i] = iconName;
    }
    return names;
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
  
  private String[] parseTileNames(String str)
  {
    if (str == null) {
      return null;
    }
    List list = new ArrayList();
    String[] iconStrs = Config.tokenize(str, " ,");
    for (int i = 0; i < iconStrs.length; i++)
    {
      String iconStr = iconStrs[i];
      if (iconStr.contains("-"))
      {
        String[] subStrs = Config.tokenize(iconStr, "-");
        if (subStrs.length == 2)
        {
          int min = Config.parseInt(subStrs[0], -1);
          int max = Config.parseInt(subStrs[1], -1);
          if ((min >= 0) && (max >= 0))
          {
            if (min > max)
            {
              Config.warn("Invalid interval: " + iconStr + ", when parsing: " + str);
              continue;
            }
            for (int n = min; n <= max; n++) {
              list.add(String.valueOf(n));
            }
            continue;
          }
        }
      }
      list.add(iconStr);
    }
    String[] names = (String[])list.toArray(new String[list.size()]);
    for (int i = 0; i < names.length; i++)
    {
      String iconName = names[i];
      
      iconName = TextureUtils.fixResourcePath(iconName, this.basePath);
      if ((!iconName.startsWith(this.basePath)) && (!iconName.startsWith("textures/")) && (!iconName.startsWith("mcpatcher/"))) {
        iconName = this.basePath + "/" + iconName;
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
      names[i] = iconName;
    }
    return names;
  }
  
  private static int parseSymmetry(String str)
  {
    if (str == null) {
      return 1;
    }
    if (str.equals("opposite")) {
      return 2;
    }
    if (str.equals("all")) {
      return 6;
    }
    Config.warn("Unknown symmetry: " + str);
    
    return 1;
  }
  
  private static int parseFaces(String str)
  {
    if (str == null) {
      return 63;
    }
    String[] faceStrs = Config.tokenize(str, " ,");
    int facesMask = 0;
    for (int i = 0; i < faceStrs.length; i++)
    {
      String faceStr = faceStrs[i];
      int faceMask = parseFace(faceStr);
      
      facesMask |= faceMask;
    }
    return facesMask;
  }
  
  private static int parseFace(String str)
  {
    str = str.toLowerCase();
    if ((str.equals("bottom")) || (str.equals("down"))) {
      return 1;
    }
    if ((str.equals("top")) || (str.equals("up"))) {
      return 2;
    }
    if (str.equals("north")) {
      return 4;
    }
    if (str.equals("south")) {
      return 8;
    }
    if (str.equals("east")) {
      return 32;
    }
    if (str.equals("west")) {
      return 16;
    }
    if (str.equals("sides")) {
      return 60;
    }
    if (str.equals("all")) {
      return 63;
    }
    Config.warn("Unknown face: " + str);
    
    return 128;
  }
  
  private static int parseConnect(String str)
  {
    if (str == null) {
      return 0;
    }
    if (str.equals("block")) {
      return 1;
    }
    if (str.equals("tile")) {
      return 2;
    }
    if (str.equals("material")) {
      return 3;
    }
    Config.warn("Unknown connect: " + str);
    
    return 128;
  }
  
  public static arr getProperty(String key, Collection properties)
  {
    for (Iterator it = properties.iterator(); it.hasNext();)
    {
      arr prop = (arr)it.next();
      if (key.equals(prop.a())) {
        return prop;
      }
    }
    return null;
  }
  
  private static int parseMethod(String str)
  {
    if (str == null) {
      return 1;
    }
    if ((str.equals("ctm")) || (str.equals("glass"))) {
      return 1;
    }
    if ((str.equals("horizontal")) || (str.equals("bookshelf"))) {
      return 2;
    }
    if (str.equals("vertical")) {
      return 6;
    }
    if (str.equals("top")) {
      return 3;
    }
    if (str.equals("random")) {
      return 4;
    }
    if (str.equals("repeat")) {
      return 5;
    }
    if (str.equals("fixed")) {
      return 7;
    }
    if ((str.equals("horizontal+vertical")) || (str.equals("h+v"))) {
      return 8;
    }
    if ((str.equals("vertical+horizontal")) || (str.equals("v+h"))) {
      return 9;
    }
    Config.warn("Unknown method: " + str);
    
    return 0;
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
    if (this.matchBlocks == null) {
      this.matchBlocks = detectMatchBlocks();
    }
    if (this.matchTiles == null) {
      if (this.matchBlocks == null) {
        this.matchTiles = detectMatchTiles();
      }
    }
    if ((this.matchBlocks == null) && (this.matchTiles == null))
    {
      Config.warn("No matchBlocks or matchTiles specified: " + path);
      return false;
    }
    if (this.method == 0)
    {
      Config.warn("No method: " + path);
      return false;
    }
    if ((this.tiles == null) || (this.tiles.length <= 0))
    {
      Config.warn("No tiles specified: " + path);
      return false;
    }
    if (this.connect == 0) {
      this.connect = detectConnect();
    }
    if (this.connect == 128)
    {
      Config.warn("Invalid connect in: " + path);
      return false;
    }
    if (this.renderPass > 0)
    {
      Config.warn("Render pass not supported: " + this.renderPass);
      return false;
    }
    if ((this.faces & 0x80) != 0)
    {
      Config.warn("Invalid faces in: " + path);
      return false;
    }
    if ((this.symmetry & 0x80) != 0)
    {
      Config.warn("Invalid symmetry in: " + path);
      return false;
    }
    switch (this.method)
    {
    case 1: 
      return isValidCtm(path);
    case 2: 
      return isValidHorizontal(path);
    case 6: 
      return isValidVertical(path);
    case 3: 
      return isValidTop(path);
    case 4: 
      return isValidRandom(path);
    case 5: 
      return isValidRepeat(path);
    case 7: 
      return isValidFixed(path);
    case 8: 
      return isValidHorizontalVertical(path);
    case 9: 
      return isValidVerticalHorizontal(path);
    }
    Config.warn("Unknown method: " + path);
    return false;
  }
  
  private int detectConnect()
  {
    if (this.matchBlocks != null) {
      return 1;
    }
    if (this.matchTiles != null) {
      return 2;
    }
    return 128;
  }
  
  private MatchBlock[] detectMatchBlocks()
  {
    int[] ids = detectMatchBlockIds();
    if (ids == null) {
      return null;
    }
    MatchBlock[] mbs = new MatchBlock[ids.length];
    for (int i = 0; i < mbs.length; i++) {
      mbs[i] = new MatchBlock(ids[i]);
    }
    return mbs;
  }
  
  private int[] detectMatchBlockIds()
  {
    if (!this.name.startsWith("block")) {
      return null;
    }
    int startPos = "block".length();
    int pos = startPos;
    while (pos < this.name.length())
    {
      char ch = this.name.charAt(pos);
      if ((ch < '0') || (ch > '9')) {
        break;
      }
      pos++;
    }
    if (pos == startPos) {
      return null;
    }
    String idStr = this.name.substring(startPos, pos);
    int id = Config.parseInt(idStr, -1);
    if (id < 0) {
      return null;
    }
    return new int[] { id };
  }
  
  private String[] detectMatchTiles()
  {
    bvh icon = getIcon(this.name);
    if (icon == null) {
      return null;
    }
    return new String[] { this.name };
  }
  
  private static bvh getIcon(String iconName)
  {
    bvg textureMapBlocks = bcf.z().R();
    
    bvh icon = textureMapBlocks.getSpriteSafe(iconName);
    if (icon != null) {
      return icon;
    }
    icon = textureMapBlocks.getSpriteSafe("blocks/" + iconName);
    return icon;
  }
  
  private boolean isValidCtm(String path)
  {
    if (this.tiles == null) {
      this.tiles = parseTileNames("0-11 16-27 32-43 48-58");
    }
    if (this.tiles.length < 47)
    {
      Config.warn("Invalid tiles, must be at least 47: " + path);
      return false;
    }
    return true;
  }
  
  private boolean isValidHorizontal(String path)
  {
    if (this.tiles == null) {
      this.tiles = parseTileNames("12-15");
    }
    if (this.tiles.length != 4)
    {
      Config.warn("Invalid tiles, must be exactly 4: " + path);
      return false;
    }
    return true;
  }
  
  private boolean isValidVertical(String path)
  {
    if (this.tiles == null)
    {
      Config.warn("No tiles defined for vertical: " + path);
      return false;
    }
    if (this.tiles.length != 4)
    {
      Config.warn("Invalid tiles, must be exactly 4: " + path);
      return false;
    }
    return true;
  }
  
  private boolean isValidHorizontalVertical(String path)
  {
    if (this.tiles == null)
    {
      Config.warn("No tiles defined for horizontal+vertical: " + path);
      return false;
    }
    if (this.tiles.length != 7)
    {
      Config.warn("Invalid tiles, must be exactly 7: " + path);
      return false;
    }
    return true;
  }
  
  private boolean isValidVerticalHorizontal(String path)
  {
    if (this.tiles == null)
    {
      Config.warn("No tiles defined for vertical+horizontal: " + path);
      return false;
    }
    if (this.tiles.length != 7)
    {
      Config.warn("Invalid tiles, must be exactly 7: " + path);
      return false;
    }
    return true;
  }
  
  private boolean isValidRandom(String path)
  {
    if ((this.tiles == null) || (this.tiles.length <= 0))
    {
      Config.warn("Tiles not defined: " + path);
      return false;
    }
    if (this.weights != null)
    {
      if (this.weights.length > this.tiles.length)
      {
        Config.warn("More weights defined than tiles, trimming weights: " + path);
        int[] weights2 = new int[this.tiles.length];
        System.arraycopy(this.weights, 0, weights2, 0, weights2.length);
        this.weights = weights2;
      }
      if (this.weights.length < this.tiles.length)
      {
        Config.warn("Less weights defined than tiles, expanding weights: " + path);
        int[] weights2 = new int[this.tiles.length];
        System.arraycopy(this.weights, 0, weights2, 0, this.weights.length);
        int avgWeight = MathUtils.getAverage(this.weights);
        for (int i = this.weights.length; i < weights2.length; i++) {
          weights2[i] = avgWeight;
        }
        this.weights = weights2;
      }
      this.sumWeights = new int[this.weights.length];
      int sum = 0;
      for (int i = 0; i < this.weights.length; i++)
      {
        sum += this.weights[i];
        this.sumWeights[i] = sum;
      }
      this.sumAllWeights = sum;
      if (this.sumAllWeights <= 0)
      {
        Config.warn("Invalid sum of all weights: " + sum);
        this.sumAllWeights = 1;
      }
    }
    return true;
  }
  
  private boolean isValidRepeat(String path)
  {
    if (this.tiles == null)
    {
      Config.warn("Tiles not defined: " + path);
      return false;
    }
    if ((this.width <= 0) || (this.width > 16))
    {
      Config.warn("Invalid width: " + path);
      return false;
    }
    if ((this.height <= 0) || (this.height > 16))
    {
      Config.warn("Invalid height: " + path);
      return false;
    }
    if (this.tiles.length != this.width * this.height)
    {
      Config.warn("Number of tiles does not equal width x height: " + path);
      return false;
    }
    return true;
  }
  
  private boolean isValidFixed(String path)
  {
    if (this.tiles == null)
    {
      Config.warn("Tiles not defined: " + path);
      return false;
    }
    if (this.tiles.length != 1)
    {
      Config.warn("Number of tiles should be 1 for method: fixed.");
      return false;
    }
    return true;
  }
  
  private boolean isValidTop(String path)
  {
    if (this.tiles == null) {
      this.tiles = parseTileNames("66");
    }
    if (this.tiles.length != 1)
    {
      Config.warn("Invalid tiles, must be exactly 1: " + path);
      return false;
    }
    return true;
  }
  
  public void updateIcons(bvg textureMap)
  {
    if (this.matchTiles != null) {
      this.matchTileIcons = registerIcons(this.matchTiles, textureMap);
    }
    if (this.tiles != null) {
      this.tileIcons = registerIcons(this.tiles, textureMap);
    }
  }
  
  private static bvh[] registerIcons(String[] tileNames, bvg textureMap)
  {
    if (tileNames == null) {
      return null;
    }
    List iconList = new ArrayList();
    for (int i = 0; i < tileNames.length; i++)
    {
      String iconName = tileNames[i];
      
      kk resLoc = new kk(iconName);
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
      String prefixTextures = "textures/";
      String pathSprite = path;
      if (pathSprite.startsWith(prefixTextures)) {
        pathSprite = pathSprite.substring(prefixTextures.length());
      }
      kk locSprite = new kk(domain, pathSprite);
      
      bvh icon = textureMap.a(locSprite);
      
      iconList.add(icon);
    }
    bvh[] icons = (bvh[])iconList.toArray(new bvh[iconList.size()]);
    return icons;
  }
  
  public boolean matchesBlock(ara blockState)
  {
    if (this.matchBlocks != null)
    {
      boolean matchBlock = false;
      for (int i = 0; i < this.matchBlocks.length; i++)
      {
        MatchBlock mb = this.matchBlocks[i];
        if (mb.matches(blockState))
        {
          matchBlock = true;
          break;
        }
      }
      if (!matchBlock) {
        return false;
      }
    }
    if (this.metadatas != null)
    {
      boolean matchMetadata = false;
      int metadata = blockState.getMetadata();
      for (int i = 0; i < this.metadatas.length; i++)
      {
        int md = this.metadatas[i];
        if (md == metadata)
        {
          matchMetadata = true;
          break;
        }
      }
      if (!matchMetadata) {
        return false;
      }
    }
    return true;
  }
  
  public boolean matchesIcon(bvh icon)
  {
    if ((this.matchTileIcons == null) || (this.matchTileIcons.length <= 0)) {
      return true;
    }
    for (int i = 0; i < this.matchTileIcons.length; i++) {
      if (this.matchTileIcons[i] == icon) {
        return true;
      }
    }
    return false;
  }
  
  public String toString()
  {
    return "CTM name: " + this.name + ", basePath: " + this.basePath + ", matchBlocks: " + Config.arrayToString(this.matchBlocks) + ", matchTiles: " + Config.arrayToString(this.matchTiles);
  }
}
