import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BetterGrass
{
  private static bxo modelEmpty = new bxv(new ArrayList(), new HashMap(), false, false, null, null, null);
  private static bxo modelCubeMycelium = null;
  private static bxo modelCubeGrassSnowy = null;
  private static bxo modelCubeGrass = null;
  
  public static void update()
  {
    modelCubeGrass = BlockModelUtils.makeModelCube("minecraft:blocks/grass_top", 0);
    modelCubeGrassSnowy = BlockModelUtils.makeModelCube("minecraft:blocks/snow", -1);
    modelCubeMycelium = BlockModelUtils.makeModelCube("minecraft:blocks/mycelium_top", -1);
  }
  
  public static List getFaceQuads(ahx blockAccess, arc blockState, cj blockPos, cq facing, List quads)
  {
    ajt block = blockState.t();
    if ((facing == cq.b) || (facing == cq.a)) {
      return quads;
    }
    if ((block instanceof amv))
    {
      if (Config.isBetterGrassFancy())
      {
        if (getBlockAt(blockPos.b(), facing, blockAccess) == aju.bw) {
          return modelCubeMycelium.a(blockState, facing, 0L);
        }
        return quads;
      }
      return modelCubeMycelium.a(blockState, facing, 0L);
    }
    if ((block instanceof alv))
    {
      ajt blockUp = blockAccess.o(blockPos.a()).t();
      boolean snowy = (blockUp == aju.aJ) || (blockUp == aju.aH);
      if (Config.isBetterGrassFancy())
      {
        if (snowy)
        {
          if (getBlockAt(blockPos, facing, blockAccess) == aju.aH) {
            return modelCubeGrassSnowy.a(blockState, facing, 0L);
          }
        }
        else if (getBlockAt(blockPos.b(), facing, blockAccess) == aju.c) {
          return modelCubeGrass.a(blockState, facing, 0L);
        }
      }
      else
      {
        if (snowy) {
          return modelCubeGrassSnowy.a(blockState, facing, 0L);
        }
        return modelCubeGrass.a(blockState, facing, 0L);
      }
    }
    return quads;
  }
  
  private static ajt getBlockAt(cj blockPos, cq facing, ahx blockAccess)
  {
    cj pos = blockPos.a(facing);
    
    ajt block = blockAccess.o(pos).t();
    
    return block;
  }
}
