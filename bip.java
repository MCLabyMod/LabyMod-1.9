public class bip
  extends bjc
{
  public bkm a;
  public bkm b;
  public bkm c;
  public bkm d;
  public bkm e;
  public bkm f;
  public bkm g;
  
  public bip()
  {
    this(0.0F);
  }
  
  public bip(float ☃)
  {
    int ☃ = 6;
    
    this.a = new bkm(this, 0, 0);
    this.a.a(-4.0F, -8.0F, -4.0F, 8, 8, 8, ☃);
    this.a.a(0.0F, ☃, 0.0F);
    
    this.b = new bkm(this, 32, 0);
    this.b.a(-4.0F, -8.0F, -4.0F, 8, 8, 8, ☃ + 0.5F);
    this.b.a(0.0F, ☃, 0.0F);
    
    this.c = new bkm(this, 16, 16);
    this.c.a(-4.0F, 0.0F, -2.0F, 8, 12, 4, ☃);
    this.c.a(0.0F, ☃, 0.0F);
    
    this.d = new bkm(this, 0, 16);
    this.d.a(-2.0F, 0.0F, -2.0F, 4, 6, 4, ☃);
    this.d.a(-2.0F, 12 + ☃, 4.0F);
    
    this.e = new bkm(this, 0, 16);
    this.e.a(-2.0F, 0.0F, -2.0F, 4, 6, 4, ☃);
    this.e.a(2.0F, 12 + ☃, 4.0F);
    
    this.f = new bkm(this, 0, 16);
    this.f.a(-2.0F, 0.0F, -2.0F, 4, 6, 4, ☃);
    this.f.a(-2.0F, 12 + ☃, -4.0F);
    
    this.g = new bkm(this, 0, 16);
    this.g.a(-2.0F, 0.0F, -2.0F, 4, 6, 4, ☃);
    this.g.a(2.0F, 12 + ☃, -4.0F);
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    this.a.a(☃);
    this.c.a(☃);
    this.d.a(☃);
    this.e.a(☃);
    this.f.a(☃);
    this.g.a(☃);
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    this.a.g = (☃ * 0.017453292F);
    this.a.f = (☃ * 0.017453292F);
    
    this.d.f = (on.b(☃ * 0.6662F) * 1.4F * ☃);
    this.e.f = (on.b(☃ * 0.6662F + 3.1415927F) * 1.4F * ☃);
    this.f.f = (on.b(☃ * 0.6662F + 3.1415927F) * 1.4F * ☃);
    this.g.f = (on.b(☃ * 0.6662F) * 1.4F * ☃);
  }
}
