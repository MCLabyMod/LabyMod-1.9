import shadersmod.client.Shaders;

public class bts
  implements bty<wu>
{
  private static final kk a = new kk("textures/entity/enderdragon/dragon_eyes.png");
  private final brj b;
  
  public bts(brj dragonRendererIn)
  {
    this.b = dragonRendererIn;
  }
  
  public void a(wu entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
  {
    this.b.a(a);
    bni.m();
    bni.d();
    bni.a(bni.r.e, bni.l.e);
    bni.g();
    bni.c(514);
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
    bni.l();
    bni.e();
    bni.c(515);
  }
  
  public boolean a()
  {
    return false;
  }
}
