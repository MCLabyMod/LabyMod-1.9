public class bse<T extends aah>
  extends brn<T>
{
  private static final kk b = new kk("textures/entity/minecart.png");
  protected bjc a = new bjb();
  
  public bse(brm ☃)
  {
    super(☃);
    this.d = 0.5F;
  }
  
  public void a(T ☃, double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    bni.G();
    
    d(☃);
    
    long ☃ = ☃.O() * 493286711L;
    ☃ = ☃ * ☃ * 4392167121L + ☃ * 98761L;
    
    float ☃ = (((float)(☃ >> 16 & 0x7) + 0.5F) / 8.0F - 0.5F) * 0.004F;
    float ☃ = (((float)(☃ >> 20 & 0x7) + 0.5F) / 8.0F - 0.5F) * 0.004F;
    float ☃ = (((float)(☃ >> 24 & 0x7) + 0.5F) / 8.0F - 0.5F) * 0.004F;
    
    bni.c(☃, ☃, ☃);
    
    double ☃ = ☃.M + (☃.p - ☃.M) * ☃;
    double ☃ = ☃.N + (☃.q - ☃.N) * ☃;
    double ☃ = ☃.O + (☃.r - ☃.O) * ☃;
    
    double ☃ = 0.30000001192092896D;
    
    bbj ☃ = ☃.k(☃, ☃, ☃);
    
    float ☃ = ☃.y + (☃.w - ☃.y) * ☃;
    if (☃ != null)
    {
      bbj ☃ = ☃.a(☃, ☃, ☃, ☃);
      bbj ☃ = ☃.a(☃, ☃, ☃, -☃);
      if (☃ == null) {
        ☃ = ☃;
      }
      if (☃ == null) {
        ☃ = ☃;
      }
      ☃ += ☃.b - ☃;
      ☃ += (☃.c + ☃.c) / 2.0D - ☃;
      ☃ += ☃.d - ☃;
      
      bbj ☃ = ☃.b(-☃.b, -☃.c, -☃.d);
      if (☃.b() != 0.0D)
      {
        ☃ = ☃.a();
        ☃ = (float)(Math.atan2(☃.d, ☃.b) * 180.0D / 3.141592653589793D);
        ☃ = (float)(Math.atan(☃.c) * 73.0D);
      }
    }
    bni.c((float)☃, (float)☃ + 0.375F, (float)☃);
    
    bni.b(180.0F - ☃, 0.0F, 1.0F, 0.0F);
    bni.b(-☃, 0.0F, 0.0F, 1.0F);
    float ☃ = ☃.t() - ☃;
    float ☃ = ☃.s() - ☃;
    if (☃ < 0.0F) {
      ☃ = 0.0F;
    }
    if (☃ > 0.0F) {
      bni.b(on.a(☃) * ☃ * ☃ / 10.0F * ☃.u(), 1.0F, 0.0F, 0.0F);
    }
    int ☃ = ☃.y();
    if (this.f)
    {
      bni.h();
      bni.e(c(☃));
    }
    arc ☃ = ☃.w();
    if (☃.i() != aob.a)
    {
      bni.G();
      
      a(bvg.g);
      float ☃ = 0.75F;
      
      bni.b(☃, ☃, ☃);
      bni.c(-0.5F, (☃ - 8) / 16.0F, 0.5F);
      a(☃, ☃, ☃);
      
      bni.H();
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      d(☃);
    }
    bni.b(-1.0F, -1.0F, 1.0F);
    this.a.a(☃, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    bni.H();
    if (this.f)
    {
      bni.n();
      bni.i();
    }
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  protected kk a(T ☃)
  {
    return b;
  }
  
  protected void a(T ☃, float ☃, arc ☃)
  {
    bni.G();
    bcf.z().ab().a(☃, ☃.e(☃));
    bni.H();
  }
}
