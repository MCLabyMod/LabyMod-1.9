public class btg
  extends brn<aae>
{
  private static final kk a = new kk("textures/entity/wither/wither_invulnerable.png");
  private static final kk b = new kk("textures/entity/wither/wither.png");
  private final bjq g;
  
  public btg(brm ☃)
  {
    super(☃);
    this.g = new bjq();
  }
  
  private float a(float ☃, float ☃, float ☃)
  {
    float ☃ = ☃ - ☃;
    while (☃ < -180.0F) {
      ☃ += 360.0F;
    }
    while (☃ >= 180.0F) {
      ☃ -= 360.0F;
    }
    return ☃ + ☃ * ☃;
  }
  
  public void a(aae ☃, double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    bni.G();
    bni.r();
    
    float ☃ = a(☃.x, ☃.v, ☃);
    float ☃ = ☃.y + (☃.w - ☃.y) * ☃;
    
    bni.c((float)☃, (float)☃, (float)☃);
    
    float ☃ = 0.0625F;
    bni.D();
    bni.b(-1.0F, -1.0F, 1.0F);
    
    bni.e();
    
    d(☃);
    if (this.f)
    {
      bni.h();
      bni.e(c(☃));
    }
    this.g.a(☃, 0.0F, 0.0F, 0.0F, ☃, ☃, ☃);
    if (this.f)
    {
      bni.n();
      bni.i();
    }
    bni.H();
    
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  protected kk a(aae ☃)
  {
    return ☃.n() ? a : b;
  }
}
