public class biq
  extends bjc
{
  private bkm a;
  private bkm b;
  
  public biq()
  {
    this.b = new bkm(this, 22, 0);
    this.b.a(-10.0F, 0.0F, 0.0F, 10, 20, 2, 1.0F);
    this.a = new bkm(this, 22, 0);
    this.a.i = true;
    this.a.a(0.0F, 0.0F, 0.0F, 10, 20, 2, 1.0F);
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    bni.E();
    bni.r();
    this.b.a(☃);
    this.a.a(☃);
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    float ☃ = 0.2617994F;
    float ☃ = -0.2617994F;
    float ☃ = 0.0F;
    float ☃ = 0.0F;
    if (((☃ instanceof sa)) && (((sa)☃).cB()))
    {
      float ☃ = 1.0F;
      if (☃.t < 0.0D)
      {
        bbj ☃ = new bbj(☃.s, ☃.t, ☃.u).a();
        ☃ = 1.0F - (float)Math.pow(-☃.c, 1.5D);
      }
      ☃ = ☃ * 0.34906584F + (1.0F - ☃) * ☃;
      ☃ = ☃ * -1.5707964F + (1.0F - ☃) * ☃;
    }
    else if (☃.aK())
    {
      ☃ = 0.6981317F;
      ☃ = -0.7853982F;
      ☃ = 3.0F;
      ☃ = 0.08726646F;
    }
    this.b.c = 5.0F;
    this.b.d = ☃;
    if ((☃ instanceof bmq))
    {
      bmq ☃ = (bmq)☃; bmq 
        tmp197_195 = ☃;tmp197_195.a = ((float)(tmp197_195.a + (☃ - ☃.a) * 0.1D)); bmq 
        tmp222_220 = ☃;tmp222_220.b = ((float)(tmp222_220.b + (☃ - ☃.b) * 0.1D)); bmq 
        tmp247_245 = ☃;tmp247_245.c = ((float)(tmp247_245.c + (☃ - ☃.c) * 0.1D));
      this.b.f = ☃.a;
      this.b.g = ☃.b;
      this.b.h = ☃.c;
    }
    else
    {
      this.b.f = ☃;
      this.b.h = ☃;
      this.b.g = ☃;
    }
    this.a.c = (-this.b.c);
    this.a.g = (-this.b.g);
    this.a.d = this.b.d;
    this.a.f = this.b.f;
    this.a.h = (-this.b.h);
  }
  
  public void a(sa ☃, float ☃, float ☃, float ☃)
  {
    super.a(☃, ☃, ☃, ☃);
  }
}
