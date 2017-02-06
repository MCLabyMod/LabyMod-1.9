public class RandomMobsRule
{
  private kk baseResLoc = null;
  private int[] skins = null;
  private kk[] resourceLocations = null;
  private int[] weights = null;
  private aig[] biomes = null;
  private RangeListInt heights = null;
  public int[] sumWeights = null;
  public int sumAllWeights = 1;
  
  public RandomMobsRule(kk baseResLoc, int[] skins, int[] weights, aig[] biomes, RangeListInt heights)
  {
    this.baseResLoc = baseResLoc;
    this.skins = skins;
    this.weights = weights;
    this.biomes = biomes;
    this.heights = heights;
  }
  
  public boolean isValid(String path)
  {
    this.resourceLocations = new kk[this.skins.length];
    
    kk locMcp = RandomMobs.getMcpatcherLocation(this.baseResLoc);
    if (locMcp == null)
    {
      Config.warn("Invalid path: " + this.baseResLoc.a());
      return false;
    }
    for (int i = 0; i < this.resourceLocations.length; i++)
    {
      int index = this.skins[i];
      if (index <= 1)
      {
        this.resourceLocations[i] = this.baseResLoc;
      }
      else
      {
        kk locNew = RandomMobs.getLocationIndexed(locMcp, index);
        if (locNew == null)
        {
          Config.warn("Invalid path: " + this.baseResLoc.a());
          return false;
        }
        if (!Config.hasResource(locNew))
        {
          Config.warn("Texture not found: " + locNew.a());
          return false;
        }
        this.resourceLocations[i] = locNew;
      }
    }
    if (this.weights != null)
    {
      if (this.weights.length > this.resourceLocations.length)
      {
        Config.warn("More weights defined than skins, trimming weights: " + path);
        int[] weights2 = new int[this.resourceLocations.length];
        System.arraycopy(this.weights, 0, weights2, 0, weights2.length);
        this.weights = weights2;
      }
      if (this.weights.length < this.resourceLocations.length)
      {
        Config.warn("Less weights defined than skins, expanding weights: " + path);
        int[] weights2 = new int[this.resourceLocations.length];
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
        if (this.weights[i] < 0)
        {
          Config.warn("Invalid weight: " + this.weights[i]);
          return false;
        }
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
  
  public boolean matches(sb el)
  {
    if (this.biomes != null)
    {
      aig spawnBiome = el.spawnBiome;
      boolean matchBiome = false;
      for (int i = 0; i < this.biomes.length; i++)
      {
        aig biome = this.biomes[i];
        if (biome == spawnBiome)
        {
          matchBiome = true;
          break;
        }
      }
      if (!matchBiome) {
        return false;
      }
    }
    if ((this.heights != null) && (el.spawnPosition != null)) {
      return this.heights.isInRange(el.spawnPosition.q());
    }
    return true;
  }
  
  public kk getTextureLocation(kk loc, int randomId)
  {
    int index = 0;
    if (this.weights == null)
    {
      index = randomId % this.resourceLocations.length;
    }
    else
    {
      int randWeight = randomId % this.sumAllWeights;
      for (int i = 0; i < this.sumWeights.length; i++) {
        if (this.sumWeights[i] > randWeight)
        {
          index = i;
          break;
        }
      }
    }
    return this.resourceLocations[index];
  }
}
