import java.util.List;

public class bnq
  extends bnd
{
  public void a(ahm layer)
  {
    if (this.b)
    {
      if (this.a.size() == 0) {
        return;
      }
      for (bqf renderchunk : this.a)
      {
        bqe listedrenderchunk = (bqe)renderchunk;
        bni.G();
        a(renderchunk);
        bni.s(listedrenderchunk.a(layer, listedrenderchunk.h()));
        bni.H();
      }
      if (Config.isMultiTexture()) {
        bni.bindCurrentTexture();
      }
      bni.I();
      this.a.clear();
    }
  }
}
