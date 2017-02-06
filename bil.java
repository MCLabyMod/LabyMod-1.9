public class bil
  extends bjc
{
  public bkm a;
  public bkm b;
  public bkm c;
  public bkm d;
  public bkm e;
  public bkm f;
  public bkm g;
  
  public bil()
  {
    this.a = new bkm(this).a(0, 0).a(-6.0F, -5.0F, 0.0F, 6, 10, 0);
    this.b = new bkm(this).a(16, 0).a(0.0F, -5.0F, 0.0F, 6, 10, 0);
    
    this.g = new bkm(this).a(12, 0).a(-1.0F, -5.0F, 0.0F, 2, 10, 0);
    
    this.c = new bkm(this).a(0, 10).a(0.0F, -4.0F, -0.99F, 5, 8, 1);
    this.d = new bkm(this).a(12, 10).a(0.0F, -4.0F, -0.01F, 5, 8, 1);
    
    this.e = new bkm(this).a(24, 10).a(0.0F, -4.0F, 0.0F, 5, 8, 0);
    this.f = new bkm(this).a(24, 10).a(0.0F, -4.0F, 0.0F, 5, 8, 0);
    
    this.a.a(0.0F, 0.0F, -1.0F);
    this.b.a(0.0F, 0.0F, 1.0F);
    
    this.g.g = 1.5707964F;
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    this.a.a(☃);
    this.b.a(☃);
    this.g.a(☃);
    
    this.c.a(☃);
    this.d.a(☃);
    
    this.e.a(☃);
    this.f.a(☃);
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    float ☃ = (on.a(☃ * 0.02F) * 0.1F + 1.25F) * ☃;
    
    this.a.g = (3.1415927F + ☃);
    this.b.g = (-☃);
    this.c.g = ☃;
    this.d.g = (-☃);
    
    this.e.g = (☃ - ☃ * 2.0F * ☃);
    this.f.g = (☃ - ☃ * 2.0F * ☃);
    
    this.c.c = on.a(☃);
    this.d.c = on.a(☃);
    this.e.c = on.a(☃);
    this.f.c = on.a(☃);
  }
}
