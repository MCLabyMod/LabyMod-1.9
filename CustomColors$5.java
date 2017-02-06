final class CustomColors$5
  implements CustomColors.IColorizer
{
  public int getColor(arc blockState, ahx blockAccess, cj blockPos)
  {
    aig biome = CustomColors.getColorBiome(blockAccess, blockPos);
    if (CustomColors.access$400() != null) {
      return CustomColors.access$400().getColor(biome, blockPos);
    }
    if (Reflector.ForgeBiomeGenBase_getWaterColorMultiplier.exists()) {
      return Reflector.callInt(biome, Reflector.ForgeBiomeGenBase_getWaterColorMultiplier, new Object[0]);
    }
    return CustomColors.getBlockColors().a(blockState, blockAccess, blockPos, 0);
  }
  
  public boolean isColorConstant()
  {
    return false;
  }
}
