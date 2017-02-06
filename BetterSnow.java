public class BetterSnow
{
  private static bxo modelSnowLayer = null;
  
  public static void update()
  {
    modelSnowLayer = Config.getMinecraft().ab().a().b(aju.aH.u());
  }
  
  public static bxo getModelSnowLayer()
  {
    return modelSnowLayer;
  }
  
  public static arc getStateSnowLayer()
  {
    return aju.aH.u();
  }
  
  public static boolean shouldRender(ahx blockAccess, arc blockState, cj blockPos)
  {
    ajt block = blockState.t();
    if (!checkBlock(block, blockState)) {
      return false;
    }
    return hasSnowNeighbours(blockAccess, blockPos);
  }
  
  private static boolean hasSnowNeighbours(ahx blockAccess, cj pos)
  {
    ajt blockSnow = aju.aH;
    if ((blockAccess.o(pos.c()).t() == blockSnow) || (blockAccess.o(pos.d()).t() == blockSnow) || (blockAccess.o(pos.e()).t() == blockSnow) || (blockAccess.o(pos.f()).t() == blockSnow)) {
      return blockAccess.o(pos.b()).p();
    }
    return false;
  }
  
  private static boolean checkBlock(ajt block, arc blockState)
  {
    if (blockState.h()) {
      return false;
    }
    if (blockState.p()) {
      return false;
    }
    if ((block instanceof aon)) {
      return false;
    }
    if ((block instanceof ajy)) {
      if (((block instanceof akw)) || ((block instanceof alm)) || ((block instanceof amu)) || ((block instanceof aoh)) || ((block instanceof apc))) {
        return true;
      }
    }
    if (((block instanceof alj)) || ((block instanceof alk)) || ((block instanceof aln)) || ((block instanceof apd)) || ((block instanceof aoa)) || ((block instanceof apk))) {
      return true;
    }
    if ((block instanceof anz)) {
      if (blockState.c(apf.a) == cq.b) {
        return true;
      }
    }
    if ((block instanceof amn))
    {
      Object orient = blockState.c(amn.a);
      if ((orient == amn.a.g) || (orient == amn.a.f)) {
        return true;
      }
    }
    return false;
  }
}
