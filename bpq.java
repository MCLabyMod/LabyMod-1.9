public class bpq
  extends bpn<aqe>
{
  private static final kk a = new kk("textures/entity/chest/ender.png");
  private bim d = new bim();
  
  public void a(aqe ☃, double ☃, double ☃, double ☃, float ☃, int ☃)
  {
    int ☃ = 0;
    if (☃.t()) {
      ☃ = ☃.u();
    }
    if (☃ >= 0)
    {
      a(b[☃]);
      bni.n(5890);
      bni.G();
      bni.b(4.0F, 4.0F, 1.0F);
      bni.c(0.0625F, 0.0625F, 0.0625F);
      bni.n(5888);
    }
    else
    {
      a(a);
    }
    bni.G();
    bni.D();
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bni.c((float)☃, (float)☃ + 1.0F, (float)☃ + 1.0F);
    bni.b(1.0F, -1.0F, -1.0F);
    
    bni.c(0.5F, 0.5F, 0.5F);
    int ☃ = 0;
    if (☃ == 2) {
      ☃ = 180;
    }
    if (☃ == 3) {
      ☃ = 0;
    }
    if (☃ == 4) {
      ☃ = 90;
    }
    if (☃ == 5) {
      ☃ = -90;
    }
    bni.b(☃, 0.0F, 1.0F, 0.0F);
    bni.c(-0.5F, -0.5F, -0.5F);
    
    float ☃ = ☃.f + (☃.a - ☃.f) * ☃;
    ☃ = 1.0F - ☃;
    ☃ = 1.0F - ☃ * ☃ * ☃;
    
    this.d.a.f = (-(☃ * 1.5707964F));
    this.d.a();
    bni.E();
    bni.H();
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    if (☃ >= 0)
    {
      bni.n(5890);
      bni.H();
      bni.n(5888);
    }
  }
}
