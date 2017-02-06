public class btb
  extends brn<ye>
{
  public btb(brm ☃)
  {
    super(☃);
    this.d = 0.5F;
  }
  
  public void a(ye ☃, double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    boc ☃ = bcf.z().ab();
    
    bni.G();
    bni.c((float)☃, (float)☃ + 0.5F, (float)☃);
    if (☃.l() - ☃ + 1.0F < 10.0F)
    {
      float ☃ = 1.0F - (☃.l() - ☃ + 1.0F) / 10.0F;
      ☃ = on.a(☃, 0.0F, 1.0F);
      ☃ *= ☃;
      ☃ *= ☃;
      float ☃ = 1.0F + ☃ * 0.3F;
      bni.b(☃, ☃, ☃);
    }
    float ☃ = (1.0F - (☃.l() - ☃ + 1.0F) / 100.0F) * 0.8F;
    d(☃);
    bni.b(-90.0F, 0.0F, 1.0F, 0.0F);
    bni.c(-0.5F, -0.5F, 0.5F);
    ☃.a(aju.W.u(), ☃.e(☃));
    bni.c(0.0F, 0.0F, 1.0F);
    if (this.f)
    {
      bni.h();
      bni.e(c(☃));
      ☃.a(aju.W.u(), 1.0F);
      bni.n();
      bni.i();
    }
    else if (☃.l() / 5 % 2 == 0)
    {
      bni.z();
      bni.g();
      bni.m();
      bni.a(bni.r.l, bni.l.c);
      bni.c(1.0F, 1.0F, 1.0F, ☃);
      bni.a(-3.0F, -3.0F);
      bni.s();
      ☃.a(aju.W.u(), 1.0F);
      bni.a(0.0F, 0.0F);
      bni.t();
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      bni.l();
      bni.f();
      bni.y();
    }
    bni.H();
    
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  protected kk a(ye ☃)
  {
    return bvg.g;
  }
}
