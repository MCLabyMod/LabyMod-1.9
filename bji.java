public class bji
  extends bjc
{
  bkm a;
  bkm b;
  bkm c;
  bkm d;
  bkm e;
  bkm f;
  bkm g;
  bkm h;
  bkm i;
  bkm j;
  bkm k;
  bkm l;
  
  public bji()
  {
    a("head.main", 0, 0);
    a("head.nose", 0, 24);
    a("head.ear1", 0, 10);
    a("head.ear2", 6, 10);
    
    this.a = new bkm(this, 26, 24);
    this.a.a(-1.0F, 5.5F, -3.7F, 2, 1, 7);
    this.a.a(3.0F, 17.5F, 3.7F);
    this.a.i = true;
    a(this.a, 0.0F, 0.0F, 0.0F);
    
    this.b = new bkm(this, 8, 24);
    this.b.a(-1.0F, 5.5F, -3.7F, 2, 1, 7);
    this.b.a(-3.0F, 17.5F, 3.7F);
    this.b.i = true;
    a(this.b, 0.0F, 0.0F, 0.0F);
    
    this.c = new bkm(this, 30, 15);
    this.c.a(-1.0F, 0.0F, 0.0F, 2, 4, 5);
    this.c.a(3.0F, 17.5F, 3.7F);
    this.c.i = true;
    a(this.c, -0.34906584F, 0.0F, 0.0F);
    
    this.d = new bkm(this, 16, 15);
    this.d.a(-1.0F, 0.0F, 0.0F, 2, 4, 5);
    this.d.a(-3.0F, 17.5F, 3.7F);
    this.d.i = true;
    a(this.d, -0.34906584F, 0.0F, 0.0F);
    
    this.e = new bkm(this, 0, 0);
    this.e.a(-3.0F, -2.0F, -10.0F, 6, 5, 10);
    this.e.a(0.0F, 19.0F, 8.0F);
    this.e.i = true;
    a(this.e, -0.34906584F, 0.0F, 0.0F);
    
    this.f = new bkm(this, 8, 15);
    this.f.a(-1.0F, 0.0F, -1.0F, 2, 7, 2);
    this.f.a(3.0F, 17.0F, -1.0F);
    this.f.i = true;
    a(this.f, -0.17453292F, 0.0F, 0.0F);
    
    this.g = new bkm(this, 0, 15);
    this.g.a(-1.0F, 0.0F, -1.0F, 2, 7, 2);
    this.g.a(-3.0F, 17.0F, -1.0F);
    this.g.i = true;
    a(this.g, -0.17453292F, 0.0F, 0.0F);
    
    this.h = new bkm(this, 32, 0);
    this.h.a(-2.5F, -4.0F, -5.0F, 5, 4, 5);
    this.h.a(0.0F, 16.0F, -1.0F);
    this.h.i = true;
    a(this.h, 0.0F, 0.0F, 0.0F);
    
    this.i = new bkm(this, 52, 0);
    this.i.a(-2.5F, -9.0F, -1.0F, 2, 5, 1);
    this.i.a(0.0F, 16.0F, -1.0F);
    this.i.i = true;
    a(this.i, 0.0F, -0.2617994F, 0.0F);
    
    this.j = new bkm(this, 58, 0);
    this.j.a(0.5F, -9.0F, -1.0F, 2, 5, 1);
    this.j.a(0.0F, 16.0F, -1.0F);
    this.j.i = true;
    a(this.j, 0.0F, 0.2617994F, 0.0F);
    
    this.k = new bkm(this, 52, 6);
    this.k.a(-1.5F, -1.5F, 0.0F, 3, 3, 2);
    this.k.a(0.0F, 20.0F, 7.0F);
    this.k.i = true;
    a(this.k, -0.3490659F, 0.0F, 0.0F);
    
    this.l = new bkm(this, 32, 9);
    this.l.a(-0.5F, -2.5F, -5.5F, 1, 1, 1);
    this.l.a(0.0F, 16.0F, -1.0F);
    this.l.i = true;
    a(this.l, 0.0F, 0.0F, 0.0F);
  }
  
  private void a(bkm ☃, float ☃, float ☃, float ☃)
  {
    ☃.f = ☃;
    ☃.g = ☃;
    ☃.h = ☃;
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    if (this.q)
    {
      float ☃ = 1.5F;
      bni.G();
      bni.b(0.85F / ☃, 0.85F / ☃, 0.85F / ☃);
      bni.c(0.0F, 22.0F * ☃, 2.0F * ☃);
      this.h.a(☃);
      this.j.a(☃);
      this.i.a(☃);
      this.l.a(☃);
      bni.H();
      bni.G();
      bni.b(0.6F / ☃, 0.6F / ☃, 0.6F / ☃);
      bni.c(0.0F, 36.0F * ☃, 0.0F);
      this.a.a(☃);
      this.b.a(☃);
      this.c.a(☃);
      this.d.a(☃);
      this.e.a(☃);
      this.f.a(☃);
      this.g.a(☃);
      this.k.a(☃);
      bni.H();
    }
    else
    {
      bni.G();
      bni.b(0.6F, 0.6F, 0.6F);
      bni.c(0.0F, 16.0F * ☃, 0.0F);
      
      this.a.a(☃);
      this.b.a(☃);
      this.c.a(☃);
      this.d.a(☃);
      this.e.a(☃);
      this.f.a(☃);
      this.g.a(☃);
      this.h.a(☃);
      this.i.a(☃);
      this.j.a(☃);
      this.k.a(☃);
      this.l.a(☃);
      
      bni.H();
    }
  }
  
  private float m = 0.0F;
  private float n = 0.0F;
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    float ☃ = ☃ - ☃.T;
    
    wd ☃ = (wd)☃;
    this.l.f = (this.h.f = this.i.f = this.j.f = ☃ * 0.017453292F);
    this.l.g = (this.h.g = ☃ * 0.017453292F);
    this.i.g = (this.l.g - 0.2617994F);
    this.j.g = (this.l.g + 0.2617994F);
    this.m = on.a(☃.r(☃) * 3.1415927F);
    this.c.f = (this.d.f = (this.m * 50.0F - 21.0F) * 0.017453292F);
    this.a.f = (this.b.f = this.m * 50.0F * 0.017453292F);
    this.f.f = (this.g.f = (this.m * -40.0F - 11.0F) * 0.017453292F);
  }
  
  public void a(sa ☃, float ☃, float ☃, float ☃)
  {
    super.a(☃, ☃, ☃, ☃);
    
    this.m = on.a(((wd)☃).r(☃) * 3.1415927F);
  }
}
