import java.nio.ByteBuffer;
import java.util.List;
import shadersmod.client.SVertexBuilder;

public class bna
{
  public void a(bmz vertexBufferIn)
  {
    if (vertexBufferIn.h() > 0)
    {
      bvr vertexformat = vertexBufferIn.g();
      int i = vertexformat.g();
      ByteBuffer bytebuffer = vertexBufferIn.f();
      List<bvs> list = vertexformat.h();
      
      boolean forgePreDrawExists = Reflector.ForgeVertexFormatElementEnumUseage_preDraw.exists();
      boolean forgePostDrawExists = Reflector.ForgeVertexFormatElementEnumUseage_postDraw.exists();
      for (int j = 0; j < list.size(); j++)
      {
        bvs vertexformatelement = (bvs)list.get(j);
        bvs.b vertexformatelement$enumusage = vertexformatelement.b();
        if (forgePreDrawExists)
        {
          Reflector.callVoid(vertexformatelement$enumusage, Reflector.ForgeVertexFormatElementEnumUseage_preDraw, new Object[] { vertexformat, Integer.valueOf(j), Integer.valueOf(i), bytebuffer });
        }
        else
        {
          int k = vertexformatelement.a().c();
          int l = vertexformatelement.d();
          bytebuffer.position(vertexformat.d(j));
          switch (vertexformatelement$enumusage)
          {
          case a: 
            bni.b(vertexformatelement.c(), k, i, bytebuffer);
            bni.q(32884);
            break;
          case d: 
            bzg.l(bzg.q + l);
            bni.a(vertexformatelement.c(), k, i, bytebuffer);
            bni.q(32888);
            bzg.l(bzg.q);
            break;
          case c: 
            bni.c(vertexformatelement.c(), k, i, bytebuffer);
            bni.q(32886);
            break;
          case b: 
            bni.a(k, i, bytebuffer);
            bni.q(32885);
          }
        }
      }
      bmz wr = vertexBufferIn;
      if (wr.isMultiTexture()) {
        wr.drawMultiTexture();
      } else if (Config.isShaders()) {
        SVertexBuilder.drawArrays(vertexBufferIn.i(), 0, vertexBufferIn.h(), vertexBufferIn);
      } else {
        bni.f(vertexBufferIn.i(), 0, vertexBufferIn.h());
      }
      int i1 = 0;
      for (int j1 = list.size(); i1 < j1; i1++)
      {
        bvs vertexformatelement1 = (bvs)list.get(i1);
        bvs.b vertexformatelement$enumusage1 = vertexformatelement1.b();
        if (forgePostDrawExists)
        {
          Reflector.callVoid(vertexformatelement$enumusage1, Reflector.ForgeVertexFormatElementEnumUseage_postDraw, new Object[] { vertexformat, Integer.valueOf(j1), Integer.valueOf(i), bytebuffer });
        }
        else
        {
          int k1 = vertexformatelement1.d();
          switch (vertexformatelement$enumusage1)
          {
          case a: 
            bni.p(32884);
            break;
          case d: 
            bzg.l(bzg.q + k1);
            bni.p(32888);
            bzg.l(bzg.q);
            break;
          case c: 
            bni.p(32886);
            bni.I();
            break;
          case b: 
            bni.p(32885);
          }
        }
      }
    }
    vertexBufferIn.b();
  }
}
