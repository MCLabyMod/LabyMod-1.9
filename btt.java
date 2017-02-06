import shadersmod.client.Shaders;

public class btt
  implements bty<yj>
{
  private static final kk a = new kk("textures/entity/enderman/enderman_eyes.png");
  private final brk b;
  
  public btt(brk endermanRendererIn)
  {
    this.b = endermanRendererIn;
  }
  
  public void a(yj entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
  {
    this.b.a(a);
    bni.m();
    bni.d();
    bni.a(bni.r.e, bni.l.e);
    bni.g();
    bni.a(!entitylivingbaseIn.aN());
    int i = 61680;
    int j = i % 65536;
    int k = i / 65536;
    bzg.a(bzg.r, j, k);
    bni.f();
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    if (Config.isShaders()) {
      Shaders.beginSpiderEyes();
    }
    this.b.b().a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    this.b.a(entitylivingbaseIn, partialTicks);
    bni.a(true);
    bni.l();
    bni.e();
  }
  
  public boolean a()
  {
    return false;
  }
}
