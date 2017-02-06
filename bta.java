public class bta
  extends bse<aap>
{
  public bta(brm ☃)
  {
    super(☃);
  }
  
  protected void a(aap ☃, float ☃, arc ☃)
  {
    int ☃ = ☃.k();
    if ((☃ > -1) && 
      (☃ - ☃ + 1.0F < 10.0F))
    {
      float ☃ = 1.0F - (☃ - ☃ + 1.0F) / 10.0F;
      ☃ = on.a(☃, 0.0F, 1.0F);
      ☃ *= ☃;
      ☃ *= ☃;
      float ☃ = 1.0F + ☃ * 0.3F;
      bni.b(☃, ☃, ☃);
    }
    super.a(☃, ☃, ☃);
    if ((☃ > -1) && (☃ / 5 % 2 == 0))
    {
      boc ☃ = bcf.z().ab();
      
      bni.z();
      bni.g();
      bni.m();
      bni.a(bni.r.l, bni.l.c);
      bni.c(1.0F, 1.0F, 1.0F, (1.0F - (☃ - ☃ + 1.0F) / 100.0F) * 0.8F);
      
      bni.G();
      ☃.a(aju.W.u(), 1.0F);
      bni.H();
      
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      bni.l();
      bni.f();
      bni.y();
    }
  }
}
