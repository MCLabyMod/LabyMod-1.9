public class bjj
  extends bjh
{
  private float i;
  
  public bjj()
  {
    super(12, 0.0F);
    
    this.a = new bkm(this, 0, 0);
    this.a.a(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);
    this.a.a(0.0F, 6.0F, -8.0F);
    
    this.b = new bkm(this, 28, 8);
    this.b.a(-4.0F, -10.0F, -7.0F, 8, 16, 6, 1.75F);
    this.b.a(0.0F, 5.0F, 2.0F);
    
    float ☃ = 0.5F;
    this.c = new bkm(this, 0, 16);
    this.c.a(-2.0F, 0.0F, -2.0F, 4, 6, 4, ☃);
    this.c.a(-3.0F, 12.0F, 7.0F);
    
    this.d = new bkm(this, 0, 16);
    this.d.a(-2.0F, 0.0F, -2.0F, 4, 6, 4, ☃);
    this.d.a(3.0F, 12.0F, 7.0F);
    
    this.e = new bkm(this, 0, 16);
    this.e.a(-2.0F, 0.0F, -2.0F, 4, 6, 4, ☃);
    this.e.a(-3.0F, 12.0F, -5.0F);
    
    this.f = new bkm(this, 0, 16);
    this.f.a(-2.0F, 0.0F, -2.0F, 4, 6, 4, ☃);
    this.f.a(3.0F, 12.0F, -5.0F);
  }
  
  public void a(sa ☃, float ☃, float ☃, float ☃)
  {
    super.a(☃, ☃, ☃, ☃);
    
    this.a.d = (6.0F + ((we)☃).r(☃) * 9.0F);
    this.i = ((we)☃).s(☃);
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    this.a.f = this.i;
  }
}
