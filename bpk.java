public class bpk
  extends bpn<apt>
{
  private bih a = new bih();
  
  public void a(apt ☃, double ☃, double ☃, double ☃, float ☃, int ☃)
  {
    boolean ☃ = ☃.D() != null;
    boolean ☃ = (!☃) || (☃.w() == aju.cK);
    int ☃ = ☃ ? ☃.u() : 0;
    long ☃ = ☃ ? ☃.D().P() : 0L;
    
    bni.G();
    float ☃ = 0.6666667F;
    if (☃)
    {
      bni.c((float)☃ + 0.5F, (float)☃ + 0.75F * ☃, (float)☃ + 0.5F);
      float ☃ = ☃ * 360 / 16.0F;
      bni.b(-☃, 0.0F, 1.0F, 0.0F);
      this.a.b.j = true;
    }
    else
    {
      int ☃ = ☃;
      float ☃ = 0.0F;
      if (☃ == 2) {
        ☃ = 180.0F;
      }
      if (☃ == 4) {
        ☃ = 90.0F;
      }
      if (☃ == 5) {
        ☃ = -90.0F;
      }
      bni.c((float)☃ + 0.5F, (float)☃ - 0.25F * ☃, (float)☃ + 0.5F);
      bni.b(-☃, 0.0F, 1.0F, 0.0F);
      bni.c(0.0F, -0.3125F, -0.4375F);
      
      this.a.b.j = false;
    }
    cj ☃ = ☃.v();
    float ☃ = ☃.p() * 7 + ☃.q() * 9 + ☃.r() * 13 + (float)☃ + ☃;
    this.a.a.f = ((-0.0125F + 0.01F * on.b(☃ * 3.1415927F * 0.02F)) * 3.1415927F);
    
    bni.D();
    kk ☃ = a(☃);
    if (☃ != null)
    {
      a(☃);
      
      bni.G();
      bni.b(☃, -☃, -☃);
      this.a.a();
      bni.H();
    }
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bni.H();
  }
  
  private kk a(apt ☃)
  {
    return bny.a.a(☃.g(), ☃.c(), ☃.e());
  }
}
