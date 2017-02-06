import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.lwjgl.util.vector.Vector3f;

public class BlockModelUtils
{
  public static bxo makeModelCube(String spriteName, int tintIndex)
  {
    bvh sprite = Config.getMinecraft().R().a(spriteName);
    
    return makeModelCube(sprite, tintIndex);
  }
  
  public static bxo makeModelCube(bvh sprite, int tintIndex)
  {
    List generalQuads = new ArrayList();
    cq[] facings = cq.n;
    Map<cq, List<bof>> faceQuads = new HashMap();
    for (int i = 0; i < facings.length; i++)
    {
      cq facing = facings[i];
      List quads = new ArrayList();
      quads.add(makeBakedQuad(facing, sprite, tintIndex));
      
      faceQuads.put(facing, quads);
    }
    boq itemOverrideList = new boq(new ArrayList());
    bxo bakedModel = new bxv(generalQuads, faceQuads, true, true, sprite, bos.a, itemOverrideList);
    return bakedModel;
  }
  
  private static bof makeBakedQuad(cq facing, bvh sprite, int tintIndex)
  {
    Vector3f posFrom = new Vector3f(0.0F, 0.0F, 0.0F);
    Vector3f posTo = new Vector3f(16.0F, 16.0F, 16.0F);
    boj uv = new boj(new float[] { 0.0F, 0.0F, 16.0F, 16.0F }, 0);
    boh face = new boh(facing, tintIndex, "#" + facing.m(), uv);
    bxp modelRotation = bxp.a;
    boi partRotation = null;
    boolean uvLocked = false;
    boolean shade = true;
    
    bon faceBakery = new bon();
    bof quad = faceBakery.a(posFrom, posTo, face, sprite, facing, modelRotation, partRotation, uvLocked, shade);
    return quad;
  }
}
