public class bso
  extends brn<zu>
{
  private static final kk a = new kk("textures/entity/shulker/spark.png");
  private final bjm b;
  
  public bso(brm ☃)
  {
    super(☃);
    this.b = new bjm();
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
  
  public void a(zu ☃, double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    bni.G();
    
    float ☃ = a(☃.x, ☃.v, ☃);
    float ☃ = ☃.y + (☃.w - ☃.y) * ☃;
    float ☃ = ☃.T + ☃;
    
    bni.c((float)☃, (float)☃ + 0.15F, (float)☃);
    bni.b(on.a(☃ * 0.1F) * 180.0F, 0.0F, 1.0F, 0.0F);
    bni.b(on.b(☃ * 0.1F) * 180.0F, 1.0F, 0.0F, 0.0F);
    bni.b(on.a(☃ * 0.15F) * 360.0F, 0.0F, 0.0F, 1.0F);
    
    float ☃ = 0.03125F;
    bni.D();
    bni.b(-1.0F, -1.0F, 1.0F);
    
    d(☃);
    
    this.b.a(☃, 0.0F, 0.0F, 0.0F, ☃, ☃, ☃);
    
    bni.m();
    bni.c(1.0F, 1.0F, 1.0F, 0.5F);
    
    bni.b(1.5F, 1.5F, 1.5F);
    this.b.a(☃, 0.0F, 0.0F, 0.0F, ☃, ☃, ☃);
    bni.l();
    bni.H();
    
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  protected kk a(zu ☃)
  {
    return a;
  }
}
