import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RandomMobsProperties
{
  public String name = null;
  public String basePath = null;
  public kk[] resourceLocations = null;
  public RandomMobsRule[] rules = null;
  
  public RandomMobsProperties(String path, kk[] variants)
  {
    ConnectedParser cp = new ConnectedParser("RandomMobs");
    
    this.name = cp.parseName(path);
    this.basePath = cp.parseBasePath(path);
    
    this.resourceLocations = variants;
  }
  
  public RandomMobsProperties(Properties props, String path, kk baseResLoc)
  {
    ConnectedParser cp = new ConnectedParser("RandomMobs");
    
    this.name = cp.parseName(path);
    this.basePath = cp.parseBasePath(path);
    
    this.rules = parseRules(props, baseResLoc, cp);
  }
  
  public kk getTextureLocation(kk loc, sb el)
  {
    if (this.rules != null) {
      for (int i = 0; i < this.rules.length; i++)
      {
        RandomMobsRule rule = this.rules[i];
        if (rule.matches(el)) {
          return rule.getTextureLocation(loc, el.randomMobsId);
        }
      }
    }
    if (this.resourceLocations != null)
    {
      int randomId = el.randomMobsId;
      
      int index = randomId % this.resourceLocations.length;
      
      return this.resourceLocations[index];
    }
    return loc;
  }
  
  private RandomMobsRule[] parseRules(Properties props, kk baseResLoc, ConnectedParser cp)
  {
    List list = new ArrayList();
    int count = props.size();
    for (int i = 0; i < count; i++)
    {
      int index = i + 1;
      String valSkins = props.getProperty("skins." + index);
      if (valSkins != null)
      {
        int[] skins = cp.parseIntList(valSkins);
        int[] weights = cp.parseIntList(props.getProperty("weights." + index));
        aig[] biomes = cp.parseBiomes(props.getProperty("biomes." + index));
        RangeListInt heights = cp.parseRangeListInt(props.getProperty("heights." + index));
        if (heights == null) {
          heights = parseMinMaxHeight(props, index);
        }
        RandomMobsRule rule = new RandomMobsRule(baseResLoc, skins, weights, biomes, heights);
        list.add(rule);
      }
    }
    RandomMobsRule[] rules = (RandomMobsRule[])list.toArray(new RandomMobsRule[list.size()]);
    
    return rules;
  }
  
  private RangeListInt parseMinMaxHeight(Properties props, int index)
  {
    String minHeightStr = props.getProperty("minHeight." + index);
    String maxHeightStr = props.getProperty("maxHeight." + index);
    if ((minHeightStr == null) && (maxHeightStr == null)) {
      return null;
    }
    int minHeight = 0;
    if (minHeightStr != null)
    {
      minHeight = Config.parseInt(minHeightStr, -1);
      if (minHeight < 0)
      {
        Config.warn("Invalid minHeight: " + minHeightStr);
        return null;
      }
    }
    int maxHeight = 256;
    if (maxHeightStr != null)
    {
      maxHeight = Config.parseInt(maxHeightStr, -1);
      if (maxHeight < 0)
      {
        Config.warn("Invalid maxHeight: " + maxHeightStr);
        return null;
      }
    }
    if (maxHeight < 0)
    {
      Config.warn("Invalid minHeight, maxHeight: " + minHeightStr + ", " + maxHeightStr);
      return null;
    }
    RangeListInt list = new RangeListInt();
    list.addRange(new RangeInt(minHeight, maxHeight));
    
    return list;
  }
  
  public boolean isValid(String path)
  {
    if ((this.resourceLocations == null) && (this.rules == null))
    {
      Config.warn("No skins specified: " + path);
      return false;
    }
    if (this.rules != null) {
      for (int i = 0; i < this.rules.length; i++)
      {
        RandomMobsRule rule = this.rules[i];
        if (!rule.isValid(path)) {
          return false;
        }
      }
    }
    if (this.resourceLocations != null) {
      for (int i = 0; i < this.resourceLocations.length; i++)
      {
        kk loc = this.resourceLocations[i];
        if (!Config.hasResource(loc))
        {
          Config.warn("Texture not found: " + loc.a());
          return false;
        }
      }
    }
    return true;
  }
}
