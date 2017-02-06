import de.labystudio.gommehd.GommeHDSign;
import java.util.List;

public class bpt
  extends bpn<aqn>
{
  private static final kk a = new kk("textures/entity/sign.png");
  private final bjo d = new bjo();
  
  public void a(aqn te, double x, double y, double z, float partialTicks, int destroyStage)
  {
    GommeHDSign.render(te);
    ajt block = te.w();
    bni.G();
    float f = 0.6666667F;
    if (block == aju.an)
    {
      bni.c((float)x + 0.5F, (float)y + 0.75F * f, (float)z + 0.5F);
      float f1 = te.u() * 360 / 16.0F;
      bni.b(-f1, 0.0F, 1.0F, 0.0F);
      this.d.b.j = true;
    }
    else
    {
      int k = te.u();
      float f2 = 0.0F;
      if (k == 2) {
        f2 = 180.0F;
      }
      if (k == 4) {
        f2 = 90.0F;
      }
      if (k == 5) {
        f2 = -90.0F;
      }
      bni.c((float)x + 0.5F, (float)y + 0.75F * f, (float)z + 0.5F);
      bni.b(-f2, 0.0F, 1.0F, 0.0F);
      bni.c(0.0F, -0.3125F, -0.4375F);
      this.d.b.j = false;
    }
    if (destroyStage >= 0)
    {
      a(b[destroyStage]);
      bni.n(5890);
      bni.G();
      bni.b(4.0F, 2.0F, 1.0F);
      bni.c(0.0625F, 0.0625F, 0.0625F);
      bni.n(5888);
    }
    else
    {
      a(a);
    }
    bni.D();
    bni.G();
    bni.b(f, -f, -f);
    this.d.a();
    bni.H();
    bct fontrenderer = b();
    float f3 = 0.015625F * f;
    bni.c(0.0F, 0.5F * f, 0.07F * f);
    bni.b(f3, -f3, f3);
    bni.a(0.0F, 0.0F, -1.0F * f3);
    bni.a(false);
    int i = 0;
    if (destroyStage < 0) {
      for (int j = 0; j < te.a.length; j++) {
        if (te.a[j] != null)
        {
          eu itextcomponent = te.a[j];
          List<eu> list = bdb.a(itextcomponent, 90, fontrenderer, false, true);
          String s = (list != null) && (!list.isEmpty()) ? ((eu)list.get(0)).d() : "";
          if (j == te.f)
          {
            s = "> " + s + " <";
            fontrenderer.a(s, -fontrenderer.a(s) / 2, j * 10 - te.a.length * 5, i);
          }
          else
          {
            fontrenderer.a(s, -fontrenderer.a(s) / 2, j * 10 - te.a.length * 5, i);
          }
        }
      }
    }
    bni.a(true);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bni.H();
    if (destroyStage >= 0)
    {
      bni.n(5890);
      bni.H();
      bni.n(5888);
    }
  }
}
