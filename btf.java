public class btf
  extends bsg<xo>
{
  private static final kk a = new kk("textures/entity/wither/wither_invulnerable.png");
  private static final kk b = new kk("textures/entity/wither/wither.png");
  
  public btf(brm ☃)
  {
    super(☃, new bkd(0.0F), 1.0F);
    
    a(new bug(this));
  }
  
  protected kk a(xo ☃)
  {
    int ☃ = ☃.cZ();
    if ((☃ <= 0) || ((☃ <= 80) && (☃ / 5 % 2 == 1))) {
      return b;
    }
    return a;
  }
  
  protected void a(xo ☃, float ☃)
  {
    float ☃ = 2.0F;
    
    int ☃ = ☃.cZ();
    if (☃ > 0) {
      ☃ -= (☃ - ☃) / 220.0F * 0.5F;
    }
    bni.b(☃, ☃, ☃);
  }
}
