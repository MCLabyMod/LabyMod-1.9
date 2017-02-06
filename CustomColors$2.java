final class CustomColors$2
  implements CustomColors.IColorizer
{
  public int getColor(arc blockState, ahx blockAccess, cj blockPos)
  {
    aig biome = CustomColors.getColorBiome(blockAccess, blockPos);
    if ((CustomColors.access$100() != null) && (biome == ail.h)) {
      return CustomColors.access$100().getColor(biome, blockPos);
    }
    return biome.c(blockPos);
  }
  
  public boolean isColorConstant()
  {
    return false;
  }
}
