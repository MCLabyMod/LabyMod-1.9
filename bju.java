public class bju
  extends bjc
{
  public bkm a;
  public bkm b;
  public bkm c;
  public bkm d;
  public bkm e;
  
  public bju()
  {
    float ☃ = 4.0F;
    float ☃ = 0.0F;
    
    this.c = new bkm(this, 0, 0).b(64, 64);
    this.c.a(-4.0F, -8.0F, -4.0F, 8, 8, 8, ☃ - 0.5F);
    this.c.a(0.0F, 0.0F + ☃, 0.0F);
    
    this.d = new bkm(this, 32, 0).b(64, 64);
    this.d.a(-1.0F, 0.0F, -1.0F, 12, 2, 2, ☃ - 0.5F);
    this.d.a(0.0F, 0.0F + ☃ + 9.0F - 7.0F, 0.0F);
    
    this.e = new bkm(this, 32, 0).b(64, 64);
    this.e.a(-1.0F, 0.0F, -1.0F, 12, 2, 2, ☃ - 0.5F);
    this.e.a(0.0F, 0.0F + ☃ + 9.0F - 7.0F, 0.0F);
    
    this.a = new bkm(this, 0, 16).b(64, 64);
    this.a.a(-5.0F, -10.0F, -5.0F, 10, 10, 10, ☃ - 0.5F);
    this.a.a(0.0F, 0.0F + ☃ + 9.0F, 0.0F);
    
    this.b = new bkm(this, 0, 36).b(64, 64);
    this.b.a(-6.0F, -12.0F, -6.0F, 12, 12, 12, ☃ - 0.5F);
    this.b.a(0.0F, 0.0F + ☃ + 20.0F, 0.0F);
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    this.c.g = (☃ * 0.017453292F);
    this.c.f = (☃ * 0.017453292F);
    this.a.g = (☃ * 0.017453292F * 0.25F);
    
    float ☃ = on.a(this.a.g);
    float ☃ = on.b(this.a.g);
    
    this.d.h = 1.0F;
    this.e.h = -1.0F;
    this.d.g = (0.0F + this.a.g);
    this.e.g = (3.1415927F + this.a.g);
    
    this.d.c = (☃ * 5.0F);
    this.d.e = (-☃ * 5.0F);
    
    this.e.c = (-☃ * 5.0F);
    this.e.e = (☃ * 5.0F);
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    this.a.a(☃);
    this.b.a(☃);
    this.c.a(☃);
    this.d.a(☃);
    this.e.a(☃);
  }
}
