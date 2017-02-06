final class CustomColors$1
  implements CustomColors.IColorizer
{
  public int getColor(arc blockState, ahx blockAccess, cj blockPos)
  {
    aig biome = CustomColors.getColorBiome(blockAccess, blockPos);
    if ((CustomColors.access$000() != null) && (biome == ail.h)) {
      return CustomColors.access$000().getColor(biome, blockPos);
    }
    return biome.b(blockPos);
  }
  
  public boolean isColorConstant()
  {
    return false;
  }
}
