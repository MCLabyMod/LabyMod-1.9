final class CustomColors$3
  implements CustomColors.IColorizer
{
  public int getColor(arc blockState, ahx blockAccess, cj blockPos)
  {
    if (CustomColors.access$200() != null) {
      return CustomColors.access$200().getColor(blockAccess, blockPos);
    }
    return ahq.a();
  }
  
  public boolean isColorConstant()
  {
    return CustomColors.access$200() == null;
  }
}
