public class buh
  implements bty<wj>
{
  private static final kk a = new kk("textures/entity/wolf/wolf_collar.png");
  private final bth b;
  
  public buh(bth wolfRendererIn)
  {
    this.b = wolfRendererIn;
  }
  
  public void a(wj entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
  {
    if ((entitylivingbaseIn.cZ()) && (!entitylivingbaseIn.aN()))
    {
      this.b.a(a);
      act enumdyecolor = act.b(entitylivingbaseIn.dk().a());
      float[] afloat = we.a(enumdyecolor);
      if (Config.isCustomColors()) {
        afloat = CustomColors.getWolfCollarColors(enumdyecolor, afloat);
      }
      bni.d(afloat[0], afloat[1], afloat[2]);
      this.b.b().a(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }
  }
  
  public boolean a()
  {
    return true;
  }
}
