import shadersmod.client.Shaders;

public class buc<T extends yy>
  implements bty<T>
{
  private static final kk a = new kk("textures/entity/spider_eyes.png");
  private final bsv<T> b;
  
  public buc(bsv<T> spiderRendererIn)
  {
    this.b = spiderRendererIn;
  }
  
  public void a(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
  {
    this.b.a(a);
    bni.m();
    bni.d();
    bni.a(bni.r.e, bni.l.e);
    if (entitylivingbaseIn.aN()) {
      bni.a(false);
    } else {
      bni.a(true);
    }
    int i = 61680;
    int j = i % 65536;
    int k = i / 65536;
    bzg.a(bzg.r, j, k);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    if (Config.isShaders()) {
      Shaders.beginSpiderEyes();
    }
    this.b.b().a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    i = entitylivingbaseIn.d(partialTicks);
    j = i % 65536;
    k = i / 65536;
    bzg.a(bzg.r, j, k);
    this.b.a(entitylivingbaseIn, partialTicks);
    bni.l();
    bni.e();
  }
  
  public boolean a()
  {
    return false;
  }
}
