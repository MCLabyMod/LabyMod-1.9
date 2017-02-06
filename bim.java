public class bim
  extends bjc
{
  public bkm a;
  public bkm b;
  public bkm c;
  
  public bim()
  {
    this.a = new bkm(this, 0, 0).b(64, 64);
    this.a.a(0.0F, -5.0F, -14.0F, 14, 5, 14, 0.0F);
    this.a.c = 1.0F;
    this.a.d = 7.0F;
    this.a.e = 15.0F;
    
    this.c = new bkm(this, 0, 0).b(64, 64);
    this.c.a(-1.0F, -2.0F, -15.0F, 2, 4, 1, 0.0F);
    this.c.c = 8.0F;
    this.c.d = 7.0F;
    this.c.e = 15.0F;
    
    this.b = new bkm(this, 0, 19).b(64, 64);
    this.b.a(0.0F, 0.0F, 0.0F, 14, 10, 14, 0.0F);
    this.b.c = 1.0F;
    this.b.d = 6.0F;
    this.b.e = 1.0F;
  }
  
  public void a()
  {
    this.c.f = this.a.f;
    
    this.a.a(0.0625F);
    this.c.a(0.0625F);
    this.b.a(0.0625F);
  }
}
