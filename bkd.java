public class bkd
  extends bjc
{
  private bkm[] a;
  private bkm[] b;
  
  public bkd(float ☃)
  {
    this.s = 64;
    this.t = 64;
    
    this.a = new bkm[3];
    
    this.a[0] = new bkm(this, 0, 16);
    this.a[0].a(-10.0F, 3.9F, -0.5F, 20, 3, 3, ☃);
    
    this.a[1] = new bkm(this).b(this.s, this.t);
    this.a[1].a(-2.0F, 6.9F, -0.5F);
    this.a[1].a(0, 22).a(0.0F, 0.0F, 0.0F, 3, 10, 3, ☃);
    this.a[1].a(24, 22).a(-4.0F, 1.5F, 0.5F, 11, 2, 2, ☃);
    this.a[1].a(24, 22).a(-4.0F, 4.0F, 0.5F, 11, 2, 2, ☃);
    this.a[1].a(24, 22).a(-4.0F, 6.5F, 0.5F, 11, 2, 2, ☃);
    
    this.a[2] = new bkm(this, 12, 22);
    this.a[2].a(0.0F, 0.0F, 0.0F, 3, 6, 3, ☃);
    
    this.b = new bkm[3];
    this.b[0] = new bkm(this, 0, 0);
    this.b[0].a(-4.0F, -4.0F, -4.0F, 8, 8, 8, ☃);
    this.b[1] = new bkm(this, 32, 0);
    this.b[1].a(-4.0F, -4.0F, -4.0F, 6, 6, 6, ☃);
    this.b[1].c = -8.0F;
    this.b[1].d = 4.0F;
    this.b[2] = new bkm(this, 32, 0);
    this.b[2].a(-4.0F, -4.0F, -4.0F, 6, 6, 6, ☃);
    this.b[2].c = 10.0F;
    this.b[2].d = 4.0F;
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    for (bkm ☃ : this.b) {
      ☃.a(☃);
    }
    for (bkm ☃ : this.a) {
      ☃.a(☃);
    }
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    float ☃ = on.b(☃ * 0.1F);
    this.a[1].f = ((0.065F + 0.05F * ☃) * 3.1415927F);
    
    this.a[2].a(-2.0F, 6.9F + on.b(this.a[1].f) * 10.0F, -0.5F + on.a(this.a[1].f) * 10.0F);
    this.a[2].f = ((0.265F + 0.1F * ☃) * 3.1415927F);
    
    this.b[0].g = (☃ * 0.017453292F);
    this.b[0].f = (☃ * 0.017453292F);
  }
  
  public void a(sa ☃, float ☃, float ☃, float ☃)
  {
    xo ☃ = (xo)☃;
    for (int ☃ = 1; ☃ < 3; ☃++)
    {
      this.b[☃].g = ((☃.a(☃ - 1) - ☃.aM) * 0.017453292F);
      this.b[☃].f = (☃.b(☃ - 1) * 0.017453292F);
    }
  }
}
