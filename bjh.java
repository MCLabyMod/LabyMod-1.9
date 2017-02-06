public class bjh
  extends bjc
{
  public bkm a;
  public bkm b;
  public bkm c;
  public bkm d;
  public bkm e;
  public bkm f;
  protected float g = 8.0F;
  protected float h = 4.0F;
  
  public bjh(int ☃, float ☃)
  {
    this.a = new bkm(this, 0, 0);
    this.a.a(-4.0F, -4.0F, -8.0F, 8, 8, 8, ☃);
    this.a.a(0.0F, 18 - ☃, -6.0F);
    
    this.b = new bkm(this, 28, 8);
    this.b.a(-5.0F, -10.0F, -7.0F, 10, 16, 8, ☃);
    this.b.a(0.0F, 17 - ☃, 2.0F);
    
    this.c = new bkm(this, 0, 16);
    this.c.a(-2.0F, 0.0F, -2.0F, 4, ☃, 4, ☃);
    this.c.a(-3.0F, 24 - ☃, 7.0F);
    
    this.d = new bkm(this, 0, 16);
    this.d.a(-2.0F, 0.0F, -2.0F, 4, ☃, 4, ☃);
    this.d.a(3.0F, 24 - ☃, 7.0F);
    
    this.e = new bkm(this, 0, 16);
    this.e.a(-2.0F, 0.0F, -2.0F, 4, ☃, 4, ☃);
    this.e.a(-3.0F, 24 - ☃, -5.0F);
    
    this.f = new bkm(this, 0, 16);
    this.f.a(-2.0F, 0.0F, -2.0F, 4, ☃, 4, ☃);
    this.f.a(3.0F, 24 - ☃, -5.0F);
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    if (this.q)
    {
      float ☃ = 2.0F;
      bni.G();
      bni.c(0.0F, this.g * ☃, this.h * ☃);
      this.a.a(☃);
      bni.H();
      bni.G();
      bni.b(1.0F / ☃, 1.0F / ☃, 1.0F / ☃);
      bni.c(0.0F, 24.0F * ☃, 0.0F);
      this.b.a(☃);
      this.c.a(☃);
      this.d.a(☃);
      this.e.a(☃);
      this.f.a(☃);
      bni.H();
    }
    else
    {
      this.a.a(☃);
      this.b.a(☃);
      this.c.a(☃);
      this.d.a(☃);
      this.e.a(☃);
      this.f.a(☃);
    }
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    this.a.f = (☃ * 0.017453292F);
    this.a.g = (☃ * 0.017453292F);
    this.b.f = 1.5707964F;
    
    this.c.f = (on.b(☃ * 0.6662F) * 1.4F * ☃);
    this.d.f = (on.b(☃ * 0.6662F + 3.1415927F) * 1.4F * ☃);
    this.e.f = (on.b(☃ * 0.6662F + 3.1415927F) * 1.4F * ☃);
    this.f.f = (on.b(☃ * 0.6662F) * 1.4F * ☃);
  }
}
