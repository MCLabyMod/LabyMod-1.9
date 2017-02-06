public class bke
  extends bjc
{
  public bkm a;
  public bkm b;
  public bkm c;
  public bkm d;
  public bkm e;
  public bkm f;
  bkm g;
  bkm h;
  
  public bke()
  {
    float ☃ = 0.0F;
    
    float ☃ = 13.5F;
    
    this.a = new bkm(this, 0, 0);
    this.a.a(-2.0F, -3.0F, -2.0F, 6, 6, 4, ☃);
    this.a.a(-1.0F, ☃, -7.0F);
    
    this.b = new bkm(this, 18, 14);
    this.b.a(-3.0F, -2.0F, -3.0F, 6, 9, 6, ☃);
    this.b.a(0.0F, 14.0F, 2.0F);
    
    this.h = new bkm(this, 21, 0);
    this.h.a(-3.0F, -3.0F, -3.0F, 8, 6, 7, ☃);
    this.h.a(-1.0F, 14.0F, 2.0F);
    
    this.c = new bkm(this, 0, 18);
    this.c.a(0.0F, 0.0F, -1.0F, 2, 8, 2, ☃);
    this.c.a(-2.5F, 16.0F, 7.0F);
    
    this.d = new bkm(this, 0, 18);
    this.d.a(0.0F, 0.0F, -1.0F, 2, 8, 2, ☃);
    this.d.a(0.5F, 16.0F, 7.0F);
    
    this.e = new bkm(this, 0, 18);
    this.e.a(0.0F, 0.0F, -1.0F, 2, 8, 2, ☃);
    this.e.a(-2.5F, 16.0F, -4.0F);
    
    this.f = new bkm(this, 0, 18);
    this.f.a(0.0F, 0.0F, -1.0F, 2, 8, 2, ☃);
    this.f.a(0.5F, 16.0F, -4.0F);
    
    this.g = new bkm(this, 9, 18);
    this.g.a(0.0F, 0.0F, -1.0F, 2, 8, 2, ☃);
    this.g.a(-1.0F, 12.0F, 8.0F);
    
    this.a.a(16, 14).a(-2.0F, -5.0F, 0.0F, 2, 2, 1, ☃);
    this.a.a(16, 14).a(2.0F, -5.0F, 0.0F, 2, 2, 1, ☃);
    this.a.a(0, 10).a(-0.5F, 0.0F, -5.0F, 3, 3, 4, ☃);
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    if (this.q)
    {
      float ☃ = 2.0F;
      bni.G();
      bni.c(0.0F, 5.0F * ☃, 2.0F * ☃);
      this.a.b(☃);
      bni.H();
      bni.G();
      bni.b(1.0F / ☃, 1.0F / ☃, 1.0F / ☃);
      bni.c(0.0F, 24.0F * ☃, 0.0F);
      this.b.a(☃);
      this.c.a(☃);
      this.d.a(☃);
      this.e.a(☃);
      this.f.a(☃);
      
      this.g.b(☃);
      this.h.a(☃);
      bni.H();
    }
    else
    {
      this.a.b(☃);
      this.b.a(☃);
      this.c.a(☃);
      this.d.a(☃);
      this.e.a(☃);
      this.f.a(☃);
      
      this.g.b(☃);
      this.h.a(☃);
    }
  }
  
  public void a(sa ☃, float ☃, float ☃, float ☃)
  {
    wj ☃ = (wj)☃;
    if (☃.dj()) {
      this.g.g = 0.0F;
    } else {
      this.g.g = (on.b(☃ * 0.6662F) * 1.4F * ☃);
    }
    if (☃.db())
    {
      this.h.a(-1.0F, 16.0F, -3.0F);
      this.h.f = 1.2566371F;
      this.h.g = 0.0F;
      
      this.b.a(0.0F, 18.0F, 0.0F);
      this.b.f = 0.7853982F;
      
      this.g.a(-1.0F, 21.0F, 6.0F);
      
      this.c.a(-2.5F, 22.0F, 2.0F);
      this.c.f = 4.712389F;
      this.d.a(0.5F, 22.0F, 2.0F);
      this.d.f = 4.712389F;
      
      this.e.f = 5.811947F;
      this.e.a(-2.49F, 17.0F, -4.0F);
      this.f.f = 5.811947F;
      this.f.a(0.51F, 17.0F, -4.0F);
    }
    else
    {
      this.b.a(0.0F, 14.0F, 2.0F);
      this.b.f = 1.5707964F;
      
      this.h.a(-1.0F, 14.0F, -3.0F);
      this.h.f = this.b.f;
      
      this.g.a(-1.0F, 12.0F, 8.0F);
      
      this.c.a(-2.5F, 16.0F, 7.0F);
      this.d.a(0.5F, 16.0F, 7.0F);
      this.e.a(-2.5F, 16.0F, -4.0F);
      this.f.a(0.5F, 16.0F, -4.0F);
      
      this.c.f = (on.b(☃ * 0.6662F) * 1.4F * ☃);
      this.d.f = (on.b(☃ * 0.6662F + 3.1415927F) * 1.4F * ☃);
      this.e.f = (on.b(☃ * 0.6662F + 3.1415927F) * 1.4F * ☃);
      this.f.f = (on.b(☃ * 0.6662F) * 1.4F * ☃);
    }
    this.a.h = (☃.s(☃) + ☃.i(☃, 0.0F));
    
    this.h.h = ☃.i(☃, -0.08F);
    this.b.h = ☃.i(☃, -0.16F);
    this.g.h = ☃.i(☃, -0.2F);
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    this.a.f = (☃ * 0.017453292F);
    this.a.g = (☃ * 0.017453292F);
    
    this.g.f = ☃;
  }
}
