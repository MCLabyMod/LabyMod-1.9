import net.minecraftforge.client.model.pipeline.IVertexConsumer;
import net.minecraftforge.client.model.pipeline.IVertexProducer;

public class bof
  implements IVertexProducer
{
  protected int[] a;
  protected final int b;
  protected final cq c;
  protected final bvh d;
  private int[] vertexDataSingle = null;
  
  public bof(int[] vertexDataIn, int tintIndexIn, cq faceIn, bvh spriteIn)
  {
    this.a = vertexDataIn;
    this.b = tintIndexIn;
    this.c = faceIn;
    this.d = spriteIn;
    
    fixVertexData();
  }
  
  public bvh a()
  {
    return this.d;
  }
  
  public int[] b()
  {
    fixVertexData();
    
    return this.a;
  }
  
  public boolean c()
  {
    return this.b != -1;
  }
  
  public int d()
  {
    return this.b;
  }
  
  public cq e()
  {
    return this.c;
  }
  
  public int[] getVertexDataSingle()
  {
    if (this.vertexDataSingle == null) {
      this.vertexDataSingle = makeVertexDataSingle(b(), a());
    }
    return this.vertexDataSingle;
  }
  
  private static int[] makeVertexDataSingle(int[] vd, bvh sprite)
  {
    int[] vdSingle = (int[])vd.clone();
    
    int ku = sprite.sheetWidth / sprite.c();
    int kv = sprite.sheetHeight / sprite.d();
    
    int step = vdSingle.length / 4;
    for (int i = 0; i < 4; i++)
    {
      int pos = i * step;
      
      float tu = Float.intBitsToFloat(vdSingle[(pos + 4)]);
      float tv = Float.intBitsToFloat(vdSingle[(pos + 4 + 1)]);
      
      float u = sprite.toSingleU(tu);
      float v = sprite.toSingleV(tv);
      
      vdSingle[(pos + 4)] = Float.floatToRawIntBits(u);
      vdSingle[(pos + 4 + 1)] = Float.floatToRawIntBits(v);
    }
    return vdSingle;
  }
  
  public void pipe(IVertexConsumer consumer)
  {
    Reflector.callVoid(Reflector.LightUtil_putBakedQuad, new Object[] { consumer, this });
  }
  
  private static bvh getSpriteByUv(int[] vertexData)
  {
    float uMin = 1.0F;
    float vMin = 1.0F;
    float uMax = 0.0F;
    float vMax = 0.0F;
    
    int step = vertexData.length / 4;
    for (int i = 0; i < 4; i++)
    {
      int pos = i * step;
      
      float tu = Float.intBitsToFloat(vertexData[(pos + 4)]);
      float tv = Float.intBitsToFloat(vertexData[(pos + 4 + 1)]);
      
      uMin = Math.min(uMin, tu);
      vMin = Math.min(vMin, tv);
      uMax = Math.max(uMax, tu);
      vMax = Math.max(vMax, tv);
    }
    float uMid = (uMin + uMax) / 2.0F;
    float vMid = (vMin + vMax) / 2.0F;
    bvh spriteUv = bcf.z().R().getIconByUV(uMid, vMid);
    return spriteUv;
  }
  
  private void fixVertexData()
  {
    if (Config.isShaders())
    {
      if (this.a.length == 28) {
        this.a = expandVertexData(this.a);
      }
    }
    else if (this.a.length == 56) {
      this.a = compactVertexData(this.a);
    }
  }
  
  private static int[] expandVertexData(int[] vd)
  {
    int step = vd.length / 4;
    int stepNew = step * 2;
    int[] vdNew = new int[stepNew * 4];
    for (int i = 0; i < 4; i++) {
      System.arraycopy(vd, i * step, vdNew, i * stepNew, step);
    }
    return vdNew;
  }
  
  private static int[] compactVertexData(int[] vd)
  {
    int step = vd.length / 4;
    int stepNew = step / 2;
    int[] vdNew = new int[stepNew * 4];
    for (int i = 0; i < 4; i++) {
      System.arraycopy(vd, i * step, vdNew, i * stepNew, stepNew);
    }
    return vdNew;
  }
  
  public String toString()
  {
    return "vertex: " + this.a.length / 7 + ", tint: " + this.b + ", facing: " + this.c + ", sprite: " + this.d;
  }
}
