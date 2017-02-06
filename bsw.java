public class bsw
  extends bsg<wg>
{
  private static final kk a = new kk("textures/entity/squid.png");
  
  public bsw(brm ☃, bjc ☃, float ☃)
  {
    super(☃, ☃, ☃);
  }
  
  protected kk a(wg ☃)
  {
    return a;
  }
  
  protected void a(wg ☃, float ☃, float ☃, float ☃)
  {
    float ☃ = ☃.b + (☃.a - ☃.b) * ☃;
    float ☃ = ☃.bt + (☃.c - ☃.bt) * ☃;
    
    bni.c(0.0F, 0.5F, 0.0F);
    bni.b(180.0F - ☃, 0.0F, 1.0F, 0.0F);
    bni.b(☃, 1.0F, 0.0F, 0.0F);
    bni.b(☃, 0.0F, 1.0F, 0.0F);
    bni.c(0.0F, -1.2F, 0.0F);
  }
  
  protected float a(wg ☃, float ☃)
  {
    return ☃.bx + (☃.bw - ☃.bx) * ☃;
  }
}
