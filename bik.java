public class bik
  extends bjc
  implements bjx
{
  public bkm[] a = new bkm[5];
  public bkm[] b = new bkm[2];
  public bkm c;
  private int d = bce.a(1);
  
  public bik()
  {
    this.a[0] = new bkm(this, 0, 0).b(128, 64);
    this.a[1] = new bkm(this, 0, 19).b(128, 64);
    this.a[2] = new bkm(this, 0, 27).b(128, 64);
    this.a[3] = new bkm(this, 0, 35).b(128, 64);
    this.a[4] = new bkm(this, 0, 43).b(128, 64);
    
    int ☃ = 32;
    int ☃ = 6;
    int ☃ = 20;
    int ☃ = 4;
    
    int ☃ = 28;
    this.a[0].a(-☃ / 2, -☃ / 2 + 1, -3.0F, ☃, ☃ - 4, 3, 0.0F);
    this.a[0].a(0.0F, ☃ - 1, 1.0F);
    
    this.a[1].a(-☃ / 2 + 3, -☃ - 1, -1.0F, ☃ - 2, ☃, 2, 0.0F);
    this.a[1].a(-☃ / 2 + 1, ☃, 4.0F);
    
    this.a[2].a(-☃ / 2 + 8, -☃ - 1, -1.0F, ☃ - 4, ☃, 2, 0.0F);
    this.a[2].a(☃ / 2 - 1, ☃, 0.0F);
    
    this.a[3].a(-☃ / 2 + 2, -☃ - 1, -1.0F, ☃ - 4, ☃, 2, 0.0F);
    this.a[3].a(0.0F, ☃, -☃ / 2 + 1);
    
    this.a[4].a(-☃ / 2 + 2, -☃ - 1, -1.0F, ☃ - 4, ☃, 2, 0.0F);
    this.a[4].a(0.0F, ☃, ☃ / 2 - 1);
    
    this.a[0].f = 1.5707964F;
    this.a[1].g = 4.712389F;
    this.a[2].g = 1.5707964F;
    this.a[3].g = 3.1415927F;
    
    this.b[0] = a(true);
    this.b[0].a(3.0F, -5.0F, 9.0F);
    
    this.b[1] = a(false);
    this.b[1].a(3.0F, -5.0F, -9.0F);
    this.b[1].g = 3.1415927F;
    
    this.b[0].h = (this.b[1].h = 0.19634955F);
    
    this.c = new bkm(this, 0, 0).b(128, 64);
    this.c.a(-☃ / 2, -☃ / 2 + 1, -3.0F, ☃, ☃ - 4, 3, 0.0F);
    this.c.a(0.0F, ☃ - 7, 1.0F);
    this.c.f = 1.5707964F;
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    bni.b(90.0F, 0.0F, 1.0F, 0.0F);
    
    aag ☃ = (aag)☃;
    a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    for (int ☃ = 0; ☃ < 5; ☃++) {
      this.a[☃].a(☃);
    }
    a(☃, 0, ☃, ☃);
    a(☃, 1, ☃, ☃);
  }
  
  public void b(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    bni.b(90.0F, 0.0F, 1.0F, 0.0F);
    bni.a(false, false, false, false);
    this.c.a(☃);
    bni.a(true, true, true, true);
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃) {}
  
  bkm a(boolean ☃)
  {
    bkm ☃ = new bkm(this, 62, ☃ ? 0 : 20).b(128, 64);
    int ☃ = 20;
    int ☃ = 7;
    int ☃ = 6;
    float ☃ = -5.0F;
    
    ☃.a(-1.0F, 0.0F, ☃, 2, 2, ☃ - 2);
    ☃.a(☃ ? -1.001F : 0.001F, -☃ / 2, ☃ - ☃ + ☃, 1, ☃, ☃);
    
    return ☃;
  }
  
  void a(aag ☃, int ☃, float ☃, float ☃)
  {
    float ☃ = 40.0F;
    float ☃ = ☃.a(☃, ☃) * ☃;
    bkm ☃ = this.b[☃];
    
    ☃.f = ((float)on.b(-1.0471975803375244D, -0.2617993950843811D, (on.a(-☃) + 1.0F) / 2.0F));
    ☃.g = ((float)on.b(-0.7853981852531433D, 0.7853981852531433D, (on.a(-☃ + 1.0F) + 1.0F) / 2.0F));
    if (☃ == 1) {
      ☃.g = (3.1415927F - ☃.g);
    }
    ☃.a(☃);
  }
}
