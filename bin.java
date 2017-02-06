public class bin
  extends bjc
{
  public bkm a;
  public bkm b;
  public bkm c;
  public bkm d;
  public bkm e;
  public bkm f;
  public bkm g;
  public bkm h;
  
  public bin()
  {
    int ☃ = 16;
    this.a = new bkm(this, 0, 0);
    this.a.a(-2.0F, -6.0F, -2.0F, 4, 6, 3, 0.0F);
    this.a.a(0.0F, -1 + ☃, -4.0F);
    
    this.g = new bkm(this, 14, 0);
    this.g.a(-2.0F, -4.0F, -4.0F, 4, 2, 2, 0.0F);
    this.g.a(0.0F, -1 + ☃, -4.0F);
    
    this.h = new bkm(this, 14, 4);
    this.h.a(-1.0F, -2.0F, -3.0F, 2, 2, 2, 0.0F);
    this.h.a(0.0F, -1 + ☃, -4.0F);
    
    this.b = new bkm(this, 0, 9);
    this.b.a(-3.0F, -4.0F, -3.0F, 6, 8, 6, 0.0F);
    this.b.a(0.0F, ☃, 0.0F);
    
    this.c = new bkm(this, 26, 0);
    this.c.a(-1.0F, 0.0F, -3.0F, 3, 5, 3);
    this.c.a(-2.0F, 3 + ☃, 1.0F);
    
    this.d = new bkm(this, 26, 0);
    this.d.a(-1.0F, 0.0F, -3.0F, 3, 5, 3);
    this.d.a(1.0F, 3 + ☃, 1.0F);
    
    this.e = new bkm(this, 24, 13);
    this.e.a(0.0F, 0.0F, -3.0F, 1, 4, 6);
    this.e.a(-4.0F, -3 + ☃, 0.0F);
    
    this.f = new bkm(this, 24, 13);
    this.f.a(-1.0F, 0.0F, -3.0F, 1, 4, 6);
    this.f.a(4.0F, -3 + ☃, 0.0F);
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    if (this.q)
    {
      float ☃ = 2.0F;
      bni.G();
      bni.c(0.0F, 5.0F * ☃, 2.0F * ☃);
      this.a.a(☃);
      this.g.a(☃);
      this.h.a(☃);
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
      this.g.a(☃);
      this.h.a(☃);
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
    
    this.g.f = this.a.f;
    this.g.g = this.a.g;
    
    this.h.f = this.a.f;
    this.h.g = this.a.g;
    
    this.b.f = 1.5707964F;
    
    this.c.f = (on.b(☃ * 0.6662F) * 1.4F * ☃);
    this.d.f = (on.b(☃ * 0.6662F + 3.1415927F) * 1.4F * ☃);
    this.e.h = ☃;
    this.f.h = (-☃);
  }
}
