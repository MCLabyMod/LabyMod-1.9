public class btz
  implements bty<we>
{
  private static final kk a = new kk("textures/entity/sheep/sheep_fur.png");
  private final bsn b;
  private final bjj c = new bjj();
  
  public btz(bsn sheepRendererIn)
  {
    this.b = sheepRendererIn;
  }
  
  public void a(we entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
  {
    if ((!entitylivingbaseIn.da()) && (!entitylivingbaseIn.aN()))
    {
      this.b.a(a);
      if ((entitylivingbaseIn.o_()) && ("jeb_".equals(entitylivingbaseIn.bf())))
      {
        int i1 = 25;
        int i = entitylivingbaseIn.T / 25 + entitylivingbaseIn.O();
        int j = act.values().length;
        int k = i % j;
        int l = (i + 1) % j;
        float f = (entitylivingbaseIn.T % 25 + partialTicks) / 25.0F;
        float[] afloat1 = we.a(act.b(k));
        float[] afloat2 = we.a(act.b(l));
        if (Config.isCustomColors())
        {
          afloat1 = CustomColors.getSheepColors(act.b(k), afloat1);
          afloat2 = CustomColors.getSheepColors(act.b(l), afloat2);
        }
        bni.d(afloat1[0] * (1.0F - f) + afloat2[0] * f, afloat1[1] * (1.0F - f) + afloat2[1] * f, afloat1[2] * (1.0F - f) + afloat2[2] * f);
      }
      else
      {
        float[] afloat = we.a(entitylivingbaseIn.cZ());
        bni.d(afloat[0], afloat[1], afloat[2]);
      }
      this.c.a(this.b.b());
      this.c.a(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
      this.c.a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }
  }
  
  public boolean a()
  {
    return true;
  }
}
