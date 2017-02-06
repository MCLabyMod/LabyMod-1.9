package shadersmod.client;

import bvr;
import bvs;
import bvs.a;
import bvs.b;

public class SVertexFormat
{
  public static final int vertexSizeBlock = 14;
  public static final int offsetMidTexCoord = 8;
  public static final int offsetTangent = 10;
  public static final int offsetEntity = 12;
  public static final bvr defVertexFormatTextured = ;
  
  public static bvr makeDefVertexFormatBlock()
  {
    bvr vf = new bvr();
    vf.a(new bvs(0, bvs.a.a, bvs.b.a, 3));
    vf.a(new bvs(0, bvs.a.b, bvs.b.c, 4));
    vf.a(new bvs(0, bvs.a.a, bvs.b.d, 2));
    vf.a(new bvs(1, bvs.a.e, bvs.b.d, 2));
    
    vf.a(new bvs(0, bvs.a.c, bvs.b.b, 3));
    vf.a(new bvs(0, bvs.a.c, bvs.b.g, 1));
    
    vf.a(new bvs(0, bvs.a.a, bvs.b.g, 2));
    
    vf.a(new bvs(0, bvs.a.e, bvs.b.g, 4));
    
    vf.a(new bvs(0, bvs.a.e, bvs.b.g, 4));
    return vf;
  }
  
  public static bvr makeDefVertexFormatItem()
  {
    bvr vf = new bvr();
    vf.a(new bvs(0, bvs.a.a, bvs.b.a, 3));
    vf.a(new bvs(0, bvs.a.b, bvs.b.c, 4));
    vf.a(new bvs(0, bvs.a.a, bvs.b.d, 2));
    
    vf.a(new bvs(0, bvs.a.e, bvs.b.g, 2));
    
    vf.a(new bvs(0, bvs.a.c, bvs.b.b, 3));
    vf.a(new bvs(0, bvs.a.c, bvs.b.g, 1));
    
    vf.a(new bvs(0, bvs.a.a, bvs.b.g, 2));
    
    vf.a(new bvs(0, bvs.a.e, bvs.b.g, 4));
    
    vf.a(new bvs(0, bvs.a.e, bvs.b.g, 4));
    return vf;
  }
  
  public static bvr makeDefVertexFormatTextured()
  {
    bvr vf = new bvr();
    vf.a(new bvs(0, bvs.a.a, bvs.b.a, 3));
    vf.a(new bvs(0, bvs.a.b, bvs.b.g, 4));
    vf.a(new bvs(0, bvs.a.a, bvs.b.d, 2));
    
    vf.a(new bvs(0, bvs.a.e, bvs.b.g, 2));
    
    vf.a(new bvs(0, bvs.a.c, bvs.b.b, 3));
    vf.a(new bvs(0, bvs.a.c, bvs.b.g, 1));
    
    vf.a(new bvs(0, bvs.a.a, bvs.b.g, 2));
    
    vf.a(new bvs(0, bvs.a.e, bvs.b.g, 4));
    
    vf.a(new bvs(0, bvs.a.e, bvs.b.g, 4));
    return vf;
  }
  
  public static void setDefBakedFormat(bvr vf)
  {
    vf.a();
    vf.a(new bvs(0, bvs.a.a, bvs.b.a, 3));
    vf.a(new bvs(0, bvs.a.b, bvs.b.c, 4));
    vf.a(new bvs(0, bvs.a.a, bvs.b.d, 2));
    vf.a(new bvs(0, bvs.a.e, bvs.b.g, 2));
    vf.a(new bvs(0, bvs.a.c, bvs.b.b, 3));
    vf.a(new bvs(0, bvs.a.c, bvs.b.g, 1));
    
    vf.a(new bvs(0, bvs.a.a, bvs.b.g, 2));
    
    vf.a(new bvs(0, bvs.a.e, bvs.b.g, 4));
    
    vf.a(new bvs(0, bvs.a.e, bvs.b.g, 4));
  }
}
