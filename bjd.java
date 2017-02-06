public class bjd
  extends bjc
{
  private bkm a;
  private bkm b;
  private bkm c;
  private bkm d;
  private bkm e;
  private bkm f;
  private bkm g;
  private bkm h;
  private int i = 1;
  
  public bjd()
  {
    a("head.main", 0, 0);
    a("head.nose", 0, 24);
    a("head.ear1", 0, 10);
    a("head.ear2", 6, 10);
    
    this.g = new bkm(this, "head");
    this.g.a("main", -2.5F, -2.0F, -3.0F, 5, 4, 5);
    this.g.a("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2);
    this.g.a("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2);
    this.g.a("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2);
    this.g.a(0.0F, 15.0F, -9.0F);
    
    this.h = new bkm(this, 20, 0);
    this.h.a(-2.0F, 3.0F, -8.0F, 4, 16, 6, 0.0F);
    this.h.a(0.0F, 12.0F, -10.0F);
    
    this.e = new bkm(this, 0, 15);
    this.e.a(-0.5F, 0.0F, 0.0F, 1, 8, 1);
    this.e.f = 0.9F;
    this.e.a(0.0F, 15.0F, 8.0F);
    
    this.f = new bkm(this, 4, 15);
    this.f.a(-0.5F, 0.0F, 0.0F, 1, 8, 1);
    this.f.a(0.0F, 20.0F, 14.0F);
    
    this.a = new bkm(this, 8, 13);
    this.a.a(-1.0F, 0.0F, 1.0F, 2, 6, 2);
    this.a.a(1.1F, 18.0F, 5.0F);
    
    this.b = new bkm(this, 8, 13);
    this.b.a(-1.0F, 0.0F, 1.0F, 2, 6, 2);
    this.b.a(-1.1F, 18.0F, 5.0F);
    
    this.c = new bkm(this, 40, 0);
    this.c.a(-1.0F, 0.0F, 0.0F, 2, 10, 2);
    this.c.a(1.2F, 13.8F, -5.0F);
    
    this.d = new bkm(this, 40, 0);
    this.d.a(-1.0F, 0.0F, 0.0F, 2, 10, 2);
    this.d.a(-1.2F, 13.8F, -5.0F);
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    if (this.q)
    {
      float ☃ = 2.0F;
      bni.G();
      bni.b(1.5F / ☃, 1.5F / ☃, 1.5F / ☃);
      bni.c(0.0F, 10.0F * ☃, 4.0F * ☃);
      this.g.a(☃);
      bni.H();
      bni.G();
      bni.b(1.0F / ☃, 1.0F / ☃, 1.0F / ☃);
      bni.c(0.0F, 24.0F * ☃, 0.0F);
      this.h.a(☃);
      this.a.a(☃);
      this.b.a(☃);
      this.c.a(☃);
      this.d.a(☃);
      this.e.a(☃);
      this.f.a(☃);
      bni.H();
    }
    else
    {
      this.g.a(☃);
      this.h.a(☃);
      this.e.a(☃);
      this.f.a(☃);
      this.a.a(☃);
      this.b.a(☃);
      this.c.a(☃);
      this.d.a(☃);
    }
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    this.g.f = (☃ * 0.017453292F);
    this.g.g = (☃ * 0.017453292F);
    if (this.i != 3)
    {
      this.h.f = 1.5707964F;
      if (this.i == 2)
      {
        this.a.f = (on.b(☃ * 0.6662F) * ☃);
        this.b.f = (on.b(☃ * 0.6662F + 0.3F) * ☃);
        this.c.f = (on.b(☃ * 0.6662F + 3.1415927F + 0.3F) * ☃);
        this.d.f = (on.b(☃ * 0.6662F + 3.1415927F) * ☃);
        this.f.f = (1.7278761F + 0.31415927F * on.b(☃) * ☃);
      }
      else
      {
        this.a.f = (on.b(☃ * 0.6662F) * ☃);
        this.b.f = (on.b(☃ * 0.6662F + 3.1415927F) * ☃);
        this.c.f = (on.b(☃ * 0.6662F + 3.1415927F) * ☃);
        this.d.f = (on.b(☃ * 0.6662F) * ☃);
        if (this.i == 1) {
          this.f.f = (1.7278761F + 0.7853982F * on.b(☃) * ☃);
        } else {
          this.f.f = (1.7278761F + 0.47123894F * on.b(☃) * ☃);
        }
      }
    }
  }
  
  public void a(sa ☃, float ☃, float ☃, float ☃)
  {
    wb ☃ = (wb)☃;
    
    this.h.d = 12.0F;
    this.h.e = -10.0F;
    this.g.d = 15.0F;
    this.g.e = -9.0F;
    this.e.d = 15.0F;
    this.e.e = 8.0F;
    this.f.d = 20.0F;
    this.f.e = 14.0F;
    this.c.d = (this.d.d = 13.8F);
    this.c.e = (this.d.e = -5.0F);
    this.a.d = (this.b.d = 18.0F);
    this.a.e = (this.b.e = 5.0F);
    this.e.f = 0.9F;
    if (☃.aK())
    {
      this.h.d += 1.0F;
      this.g.d += 2.0F;
      this.e.d += 1.0F;
      this.f.d += -4.0F;
      this.f.e += 2.0F;
      this.e.f = 1.5707964F;
      this.f.f = 1.5707964F;
      this.i = 0;
    }
    else if (☃.aL())
    {
      this.f.d = this.e.d;
      this.f.e += 2.0F;
      this.e.f = 1.5707964F;
      this.f.f = 1.5707964F;
      this.i = 2;
    }
    else if (☃.db())
    {
      this.h.f = 0.7853982F;
      this.h.d += -4.0F;
      this.h.e += 5.0F;
      this.g.d += -3.3F;
      this.g.e += 1.0F;
      
      this.e.d += 8.0F;
      this.e.e += -2.0F;
      this.f.d += 2.0F;
      this.f.e += -0.8F;
      this.e.f = 1.7278761F;
      this.f.f = 2.670354F;
      
      this.c.f = (this.d.f = -0.15707964F);
      this.c.d = (this.d.d = 15.8F);
      this.c.e = (this.d.e = -7.0F);
      
      this.a.f = (this.b.f = -1.5707964F);
      this.a.d = (this.b.d = 21.0F);
      this.a.e = (this.b.e = 1.0F);
      this.i = 3;
    }
    else
    {
      this.i = 1;
    }
  }
}
