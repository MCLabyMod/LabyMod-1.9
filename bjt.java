public class bjt
  extends bjc
{
  bkm a;
  bkm b;
  bkm c;
  bkm d;
  
  public bjt(int ☃)
  {
    if (☃ > 0)
    {
      this.a = new bkm(this, 0, ☃);
      this.a.a(-3.0F, 17.0F, -3.0F, 6, 6, 6);
      
      this.b = new bkm(this, 32, 0);
      this.b.a(-3.25F, 18.0F, -3.5F, 2, 2, 2);
      
      this.c = new bkm(this, 32, 4);
      this.c.a(1.25F, 18.0F, -3.5F, 2, 2, 2);
      
      this.d = new bkm(this, 32, 8);
      this.d.a(0.0F, 21.0F, -3.5F, 1, 1, 1);
    }
    else
    {
      this.a = new bkm(this, 0, ☃);
      this.a.a(-4.0F, 16.0F, -4.0F, 8, 8, 8);
    }
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    bni.c(0.0F, 0.001F, 0.0F);
    
    this.a.a(☃);
    if (this.b != null)
    {
      this.b.a(☃);
      this.c.a(☃);
      this.d.a(☃);
    }
  }
}
