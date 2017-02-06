public class big
  extends bif
{
  public bkm a;
  public bkm b;
  public bkm c;
  public bkm d;
  
  public big()
  {
    this(0.0F);
  }
  
  public big(float ☃)
  {
    super(☃, 64, 64);
    
    this.e = new bkm(this, 0, 0);
    this.e.a(-1.0F, -7.0F, -1.0F, 2, 7, 2, ☃);
    this.e.a(0.0F, 0.0F, 0.0F);
    
    this.g = new bkm(this, 0, 26);
    this.g.a(-6.0F, 0.0F, -1.5F, 12, 3, 3, ☃);
    this.g.a(0.0F, 0.0F, 0.0F);
    
    this.h = new bkm(this, 24, 0);
    this.h.a(-2.0F, -2.0F, -1.0F, 2, 12, 2, ☃);
    this.h.a(-5.0F, 2.0F, 0.0F);
    
    this.i = new bkm(this, 32, 16);
    this.i.i = true;
    this.i.a(0.0F, -2.0F, -1.0F, 2, 12, 2, ☃);
    this.i.a(5.0F, 2.0F, 0.0F);
    
    this.j = new bkm(this, 8, 0);
    this.j.a(-1.0F, 0.0F, -1.0F, 2, 11, 2, ☃);
    this.j.a(-1.9F, 12.0F, 0.0F);
    
    this.k = new bkm(this, 40, 16);
    this.k.i = true;
    this.k.a(-1.0F, 0.0F, -1.0F, 2, 11, 2, ☃);
    this.k.a(1.9F, 12.0F, 0.0F);
    
    this.a = new bkm(this, 16, 0);
    this.a.a(-3.0F, 3.0F, -1.0F, 2, 7, 2, ☃);
    this.a.a(0.0F, 0.0F, 0.0F);
    this.a.j = true;
    
    this.b = new bkm(this, 48, 16);
    this.b.a(1.0F, 3.0F, -1.0F, 2, 7, 2, ☃);
    this.b.a(0.0F, 0.0F, 0.0F);
    
    this.c = new bkm(this, 0, 48);
    this.c.a(-4.0F, 10.0F, -1.0F, 8, 2, 2, ☃);
    this.c.a(0.0F, 0.0F, 0.0F);
    
    this.d = new bkm(this, 0, 32);
    this.d.a(-6.0F, 11.0F, -6.0F, 12, 1, 12, ☃);
    this.d.a(0.0F, 12.0F, 0.0F);
    
    this.f.j = false;
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    if (!(☃ instanceof xq)) {
      return;
    }
    xq ☃ = (xq)☃;
    
    this.i.j = ☃.s();
    this.h.j = ☃.s();
    this.d.j = (!☃.t());
    
    this.k.a(1.9F, 12.0F, 0.0F);
    this.j.a(-1.9F, 12.0F, 0.0F);
    
    this.a.f = (0.017453292F * ☃.x().b());
    this.a.g = (0.017453292F * ☃.x().c());
    this.a.h = (0.017453292F * ☃.x().d());
    
    this.b.f = (0.017453292F * ☃.x().b());
    this.b.g = (0.017453292F * ☃.x().c());
    this.b.h = (0.017453292F * ☃.x().d());
    
    this.c.f = (0.017453292F * ☃.x().b());
    this.c.g = (0.017453292F * ☃.x().c());
    this.c.h = (0.017453292F * ☃.x().d());
    
    float ☃ = (☃.B().b() + ☃.C().b()) / 2.0F;
    float ☃ = (☃.B().c() + ☃.C().c()) / 2.0F;
    float ☃ = (☃.B().d() + ☃.C().d()) / 2.0F;
    
    this.d.f = 0.0F;
    this.d.g = (0.017453292F * -☃.v);
    this.d.h = 0.0F;
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    bni.G();
    if (this.q)
    {
      float ☃ = 2.0F;
      bni.b(1.0F / ☃, 1.0F / ☃, 1.0F / ☃);
      bni.c(0.0F, 24.0F * ☃, 0.0F);
      this.a.a(☃);
      this.b.a(☃);
      this.c.a(☃);
      this.d.a(☃);
    }
    else
    {
      if (☃.aK()) {
        bni.c(0.0F, 0.2F, 0.0F);
      }
      this.a.a(☃);
      this.b.a(☃);
      this.c.a(☃);
      this.d.a(☃);
    }
    bni.H();
  }
  
  public void a(float ☃, rz ☃)
  {
    bkm ☃ = a(☃);
    boolean ☃ = ☃.j;
    ☃.j = true;
    super.a(☃, ☃);
    ☃.j = ☃;
  }
}
