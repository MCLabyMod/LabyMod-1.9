public class bii
  extends bjc
{
  private bkm a;
  private bkm b;
  private bkm c;
  private bkm d;
  private bkm e;
  private bkm f;
  
  public bii()
  {
    this.s = 64;
    this.t = 64;
    
    this.a = new bkm(this, 0, 0);
    this.a.a(-3.0F, -3.0F, -3.0F, 6, 6, 6);
    
    bkm ☃ = new bkm(this, 24, 0);
    ☃.a(-4.0F, -6.0F, -2.0F, 3, 4, 1);
    this.a.a(☃);
    bkm ☃ = new bkm(this, 24, 0);
    ☃.i = true;
    ☃.a(1.0F, -6.0F, -2.0F, 3, 4, 1);
    this.a.a(☃);
    
    this.b = new bkm(this, 0, 16);
    this.b.a(-3.0F, 4.0F, -3.0F, 6, 12, 6);
    this.b.a(0, 34).a(-5.0F, 16.0F, 0.0F, 10, 6, 1);
    
    this.c = new bkm(this, 42, 0);
    this.c.a(-12.0F, 1.0F, 1.5F, 10, 16, 1);
    this.e = new bkm(this, 24, 16);
    this.e.a(-12.0F, 1.0F, 1.5F);
    this.e.a(-8.0F, 1.0F, 0.0F, 8, 12, 1);
    
    this.d = new bkm(this, 42, 0);
    this.d.i = true;
    this.d.a(2.0F, 1.0F, 1.5F, 10, 16, 1);
    this.f = new bkm(this, 24, 16);
    this.f.i = true;
    this.f.a(12.0F, 1.0F, 1.5F);
    this.f.a(0.0F, 1.0F, 0.0F, 8, 12, 1);
    
    this.b.a(this.c);
    this.b.a(this.d);
    this.c.a(this.e);
    this.d.a(this.f);
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    this.a.a(☃);
    this.b.a(☃);
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    if (((vu)☃).o())
    {
      this.a.f = (☃ * 0.017453292F);
      this.a.g = (3.1415927F - ☃ * 0.017453292F);
      this.a.h = 3.1415927F;
      
      this.a.a(0.0F, -2.0F, 0.0F);
      this.c.a(-3.0F, 0.0F, 3.0F);
      this.d.a(3.0F, 0.0F, 3.0F);
      
      this.b.f = 3.1415927F;
      
      this.c.f = -0.15707964F;
      this.c.g = -1.2566371F;
      this.e.g = -1.7278761F;
      this.d.f = this.c.f;
      this.d.g = (-this.c.g);
      this.f.g = (-this.e.g);
    }
    else
    {
      this.a.f = (☃ * 0.017453292F);
      this.a.g = (☃ * 0.017453292F);
      this.a.h = 0.0F;
      
      this.a.a(0.0F, 0.0F, 0.0F);
      this.c.a(0.0F, 0.0F, 0.0F);
      this.d.a(0.0F, 0.0F, 0.0F);
      
      this.b.f = (0.7853982F + on.b(☃ * 0.1F) * 0.15F);
      this.b.g = 0.0F;
      
      this.c.g = (on.b(☃ * 1.3F) * 3.1415927F * 0.25F);
      this.d.g = (-this.c.g);
      this.e.g = (this.c.g * 0.5F);
      this.f.g = (-this.c.g * 0.5F);
    }
  }
}
