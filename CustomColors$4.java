final class CustomColors$4
  implements CustomColors.IColorizer
{
  public int getColor(arc blockState, ahx blockAccess, cj blockPos)
  {
    if (CustomColors.access$300() != null) {
      return CustomColors.access$300().getColor(blockAccess, blockPos);
    }
    return ahq.b();
  }
  
  public boolean isColorConstant()
  {
    return CustomColors.access$300() == null;
  }
}
