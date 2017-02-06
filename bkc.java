public class bkc
  extends bka
{
  public boolean g;
  private bkm h;
  private bkm i;
  
  public bkc(float ☃)
  {
    super(☃, 0.0F, 64, 128);
    
    this.h = new bkm(this).b(64, 128);
    this.h.a(0.0F, -2.0F, 0.0F);
    this.h.a(0, 0).a(0.0F, 3.0F, -6.75F, 1, 1, 1, -0.25F);
    this.f.a(this.h);
    
    this.i = new bkm(this).b(64, 128);
    this.i.a(-5.0F, -10.03125F, -5.0F);
    this.i.a(0, 64).a(0.0F, 0.0F, 0.0F, 10, 2, 10);
    this.a.a(this.i);
    
    bkm ☃ = new bkm(this).b(64, 128);
    ☃.a(1.75F, -4.0F, 2.0F);
    ☃.a(0, 76).a(0.0F, 0.0F, 0.0F, 7, 4, 7);
    ☃.f = -0.05235988F;
    ☃.h = 0.02617994F;
    this.i.a(☃);
    
    bkm ☃ = new bkm(this).b(64, 128);
    ☃.a(1.75F, -4.0F, 2.0F);
    ☃.a(0, 87).a(0.0F, 0.0F, 0.0F, 4, 4, 4);
    ☃.f = -0.10471976F;
    ☃.h = 0.05235988F;
    ☃.a(☃);
    
    bkm ☃ = new bkm(this).b(64, 128);
    ☃.a(1.75F, -2.0F, 2.0F);
    ☃.a(0, 95).a(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.25F);
    ☃.f = -0.20943952F;
    ☃.h = 0.10471976F;
    ☃.a(☃);
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    this.f.o = (this.f.p = this.f.q = 0.0F);
    
    float ☃ = 0.01F * (☃.O() % 10);
    this.f.f = (on.a(☃.T * ☃) * 4.5F * 0.017453292F);
    this.f.g = 0.0F;
    this.f.h = (on.b(☃.T * ☃) * 2.5F * 0.017453292F);
    if (this.g)
    {
      this.f.f = -0.9F;
      this.f.q = -0.09375F;
      this.f.p = 0.1875F;
    }
  }
}
