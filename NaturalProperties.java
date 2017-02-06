import java.util.IdentityHashMap;
import java.util.Map;

public class NaturalProperties
{
  public int rotation = 1;
  public boolean flip = false;
  private Map[] quadMaps = new Map[8];
  
  public NaturalProperties(String type)
  {
    if (type.equals("4"))
    {
      this.rotation = 4;
      return;
    }
    if (type.equals("2"))
    {
      this.rotation = 2;
      return;
    }
    if (type.equals("F"))
    {
      this.flip = true;
      return;
    }
    if (type.equals("4F"))
    {
      this.rotation = 4;
      this.flip = true;
      return;
    }
    if (type.equals("2F"))
    {
      this.rotation = 2;
      this.flip = true;
      return;
    }
    Config.warn("NaturalTextures: Unknown type: " + type);
  }
  
  public boolean isValid()
  {
    if ((this.rotation == 2) || (this.rotation == 4)) {
      return true;
    }
    if (this.flip) {
      return true;
    }
    return false;
  }
  
  public synchronized bof getQuad(bof quadIn, int rotate, boolean flipU)
  {
    int index = rotate;
    if (flipU) {
      index |= 0x4;
    }
    if ((index <= 0) || (index >= this.quadMaps.length)) {
      return quadIn;
    }
    Map map = this.quadMaps[index];
    if (map == null)
    {
      map = new IdentityHashMap(1);
      this.quadMaps[index] = map;
    }
    bof quad = (bof)map.get(quadIn);
    if (quad == null)
    {
      quad = makeQuad(quadIn, rotate, flipU);
      map.put(quadIn, quad);
    }
    return quad;
  }
  
  private bof makeQuad(bof quad, int rotate, boolean flipU)
  {
    int[] vertexData = quad.b();
    int tintIndex = quad.d();
    cq face = quad.e();
    bvh sprite = quad.a();
    
    vertexData = fixVertexData(vertexData, rotate, flipU);
    
    bof bq = new bof(vertexData, tintIndex, face, sprite);
    
    return bq;
  }
  
  private int[] fixVertexData(int[] vertexData, int rotate, boolean flipU)
  {
    int[] vertexData2 = (int[])vertexData.clone();
    
    int v2 = 4 - rotate;
    if (flipU) {
      v2 += 3;
    }
    v2 %= 4;
    
    int mul = vertexData2.length / 4;
    for (int v = 0; v < 4; v++)
    {
      int pos = v * mul;
      int pos2 = v2 * mul;
      
      vertexData2[(pos2 + 4)] = vertexData[(pos + 4)];
      vertexData2[(pos2 + 4 + 1)] = vertexData[(pos + 4 + 1)];
      if (flipU)
      {
        v2--;
        if (v2 < 0) {
          v2 = 3;
        }
      }
      else
      {
        v2++;
        if (v2 > 3) {
          v2 = 0;
        }
      }
    }
    return vertexData2;
  }
}
