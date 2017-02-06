public class brs
  extends bsg<ym>
{
  private static final kk a = new kk("textures/entity/ghast/ghast.png");
  private static final kk b = new kk("textures/entity/ghast/ghast_shooting.png");
  
  public brs(brm ☃)
  {
    super(☃, new bit(), 0.5F);
  }
  
  protected kk a(ym ☃)
  {
    if (☃.o()) {
      return b;
    }
    return a;
  }
  
  protected void a(ym ☃, float ☃)
  {
    float ☃ = 1.0F;
    float ☃ = (8.0F + ☃) / 2.0F;
    float ☃ = (8.0F + 1.0F / ☃) / 2.0F;
    bni.b(☃, ☃, ☃);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
  }
}
