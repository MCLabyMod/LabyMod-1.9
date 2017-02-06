import java.util.List;
import shadersmod.client.ShadersRender;

public class bnv
  extends bnd
{
  public void a(ahm layer)
  {
    if (this.b)
    {
      for (bqf renderchunk : this.a)
      {
        bvq vertexbuffer = renderchunk.b(layer.ordinal());
        bni.G();
        a(renderchunk);
        renderchunk.g();
        vertexbuffer.a();
        a();
        vertexbuffer.a(7);
        bni.H();
      }
      bzg.g(bzg.R, 0);
      bni.I();
      this.a.clear();
    }
  }
  
  private void a()
  {
    if (Config.isShaders())
    {
      ShadersRender.setupArrayPointersVbo();
      return;
    }
    bni.d(3, 5126, 28, 0);
    bni.e(4, 5121, 28, 12);
    bni.c(2, 5126, 28, 16);
    bzg.l(bzg.r);
    bni.c(2, 5122, 28, 24);
    bzg.l(bzg.q);
  }
}
