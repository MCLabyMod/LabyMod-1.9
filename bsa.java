public class bsa
  extends bsg<yp>
{
  private static final kk a = new kk("textures/entity/slime/magmacube.png");
  
  public bsa(brm ☃)
  {
    super(☃, new biz(), 0.25F);
  }
  
  protected kk a(yp ☃)
  {
    return a;
  }
  
  protected void a(yp ☃, float ☃)
  {
    int ☃ = ☃.da();
    float ☃ = (☃.c + (☃.b - ☃.c) * ☃) / (☃ * 0.5F + 1.0F);
    float ☃ = 1.0F / (☃ + 1.0F);
    float ☃ = ☃;
    bni.b(☃ * ☃, 1.0F / ☃ * ☃, ☃ * ☃);
  }
}
