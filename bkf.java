public class bkf
  extends bix
{
  public bkf()
  {
    this(0.0F, false);
  }
  
  public bkf(float ☃, boolean ☃)
  {
    super(☃, 0.0F, 64, ☃ ? 32 : 64);
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    boolean ☃ = ((☃ instanceof za)) && (((za)☃).db());
    float ☃ = on.a(this.o * 3.1415927F);
    float ☃ = on.a((1.0F - (1.0F - this.o) * (1.0F - this.o)) * 3.1415927F);
    this.h.h = 0.0F;
    this.i.h = 0.0F;
    this.h.g = (-(0.1F - ☃ * 0.6F));
    this.i.g = (0.1F - ☃ * 0.6F);
    
    float ☃ = -3.1415927F / (☃ ? 1.5F : 2.25F);
    this.h.f = ☃;
    this.i.f = ☃;
    
    this.h.f += ☃ * 1.2F - ☃ * 0.4F;
    this.i.f += ☃ * 1.2F - ☃ * 0.4F;
    
    this.h.h += on.b(☃ * 0.09F) * 0.05F + 0.05F;
    this.i.h -= on.b(☃ * 0.09F) * 0.05F + 0.05F;
    this.h.f += on.a(☃ * 0.067F) * 0.05F;
    this.i.f -= on.a(☃ * 0.067F) * 0.05F;
  }
}
