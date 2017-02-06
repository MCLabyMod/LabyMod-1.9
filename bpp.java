public class bpp
  extends bpn<aqd>
{
  private static final kk a = new kk("textures/entity/enchanting_table_book.png");
  private bil d = new bil();
  
  public void a(aqd ☃, double ☃, double ☃, double ☃, float ☃, int ☃)
  {
    bni.G();
    bni.c((float)☃ + 0.5F, (float)☃ + 0.75F, (float)☃ + 0.5F);
    
    float ☃ = ☃.a + ☃;
    
    bni.c(0.0F, 0.1F + on.a(☃ * 0.1F) * 0.01F, 0.0F);
    float ☃ = ☃.l - ☃.m;
    while (☃ >= 3.1415927F) {
      ☃ -= 6.2831855F;
    }
    while (☃ < -3.1415927F) {
      ☃ += 6.2831855F;
    }
    float ☃ = ☃.m + ☃ * ☃;
    
    bni.b(-☃ * 57.295776F, 0.0F, 1.0F, 0.0F);
    bni.b(80.0F, 0.0F, 0.0F, 1.0F);
    a(a);
    
    float ☃ = ☃.g + (☃.f - ☃.g) * ☃ + 0.25F;
    float ☃ = ☃.g + (☃.f - ☃.g) * ☃ + 0.75F;
    ☃ = (☃ - on.b(☃)) * 1.6F - 0.3F;
    ☃ = (☃ - on.b(☃)) * 1.6F - 0.3F;
    if (☃ < 0.0F) {
      ☃ = 0.0F;
    }
    if (☃ < 0.0F) {
      ☃ = 0.0F;
    }
    if (☃ > 1.0F) {
      ☃ = 1.0F;
    }
    if (☃ > 1.0F) {
      ☃ = 1.0F;
    }
    float ☃ = ☃.k + (☃.j - ☃.k) * ☃;
    bni.q();
    this.d.a(null, ☃, ☃, ☃, ☃, 0.0F, 0.0625F);
    bni.H();
  }
}
