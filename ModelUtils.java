import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ModelUtils
{
  public static void dbgModel(bxo model)
  {
    if (model == null) {
      return;
    }
    Config.dbg("Model: " + model + ", ao: " + model.a() + ", gui3d: " + model.b() + ", builtIn: " + model.c() + ", particle: " + model.d());
    
    cq[] faces = cq.n;
    for (int i = 0; i < faces.length; i++)
    {
      cq face = faces[i];
      List faceQuads = model.a(null, face, 0L);
      dbgQuads(face.m(), faceQuads, "  ");
    }
    List generalQuads = model.a(null, null, 0L);
    dbgQuads("General", generalQuads, "  ");
  }
  
  private static void dbgQuads(String name, List quads, String prefix)
  {
    for (Iterator it = quads.iterator(); it.hasNext();)
    {
      bof quad = (bof)it.next();
      dbgQuad(name, quad, prefix);
    }
  }
  
  public static void dbgQuad(String name, bof quad, String prefix)
  {
    Config.dbg(prefix + "Quad: " + quad.getClass().getName() + ", type: " + name + ", face: " + quad.e() + ", tint: " + quad.d() + ", sprite: " + quad.a());
    dbgVertexData(quad.b(), "  " + prefix);
  }
  
  public static void dbgVertexData(int[] vd, String prefix)
  {
    int step = vd.length / 4;
    Config.dbg(prefix + "Length: " + vd.length + ", step: " + step);
    for (int i = 0; i < 4; i++)
    {
      int pos = i * step;
      
      float x = Float.intBitsToFloat(vd[(pos + 0)]);
      float y = Float.intBitsToFloat(vd[(pos + 1)]);
      float z = Float.intBitsToFloat(vd[(pos + 2)]);
      int col = vd[(pos + 3)];
      float u = Float.intBitsToFloat(vd[(pos + 4)]);
      float v = Float.intBitsToFloat(vd[(pos + 5)]);
      
      Config.dbg(prefix + i + " xyz: " + x + "," + y + "," + z + " col: " + col + " u,v: " + u + "," + v);
    }
  }
  
  public static bxo duplicateModel(bxo model)
  {
    List generalQuads2 = duplicateQuadList(model.a(null, null, 0L));
    
    cq[] faces = cq.n;
    Map<cq, List<bof>> faceQuads2 = new HashMap();
    for (int i = 0; i < faces.length; i++)
    {
      cq face = faces[i];
      List quads = model.a(null, face, 0L);
      List quads2 = duplicateQuadList(quads);
      faceQuads2.put(face, quads2);
    }
    bxv model2 = new bxv(generalQuads2, faceQuads2, model.a(), model.b(), model.d(), model.e(), model.f());
    
    return model2;
  }
  
  public static List duplicateQuadList(List list)
  {
    List list2 = new ArrayList();
    for (Iterator it = list.iterator(); it.hasNext();)
    {
      bof quad = (bof)it.next();
      bof quad2 = duplicateQuad(quad);
      list2.add(quad2);
    }
    return list2;
  }
  
  public static bof duplicateQuad(bof quad)
  {
    bof quad2 = new bof((int[])quad.b().clone(), quad.d(), quad.e(), quad.a());
    
    return quad2;
  }
}
