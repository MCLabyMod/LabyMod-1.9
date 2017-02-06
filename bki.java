public class bki
  extends bjc
{
  private bkm a;
  private bkm b;
  private bkm c;
  
  public bki(float ☃, boolean ☃)
  {
    this.b = new bkm(this, "glass");
    this.b.a(0, 0).a(-4.0F, -4.0F, -4.0F, 8, 8, 8);
    
    this.a = new bkm(this, "cube");
    this.a.a(32, 0).a(-4.0F, -4.0F, -4.0F, 8, 8, 8);
    if (☃)
    {
      this.c = new bkm(this, "base");
      this.c.a(0, 16).a(-6.0F, 0.0F, -6.0F, 12, 4, 12);
    }
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    bni.G();
    bni.b(2.0F, 2.0F, 2.0F);
    bni.c(0.0F, -0.5F, 0.0F);
    if (this.c != null) {
      this.c.a(☃);
    }
    bni.b(☃, 0.0F, 1.0F, 0.0F);
    bni.c(0.0F, 0.8F + ☃, 0.0F);
    bni.b(60.0F, 0.7071F, 0.0F, 0.7071F);
    this.b.a(☃);
    float ☃ = 0.875F;
    bni.b(☃, ☃, ☃);
    bni.b(60.0F, 0.7071F, 0.0F, 0.7071F);
    bni.b(☃, 0.0F, 1.0F, 0.0F);
    this.b.a(☃);
    bni.b(☃, ☃, ☃);
    bni.b(60.0F, 0.7071F, 0.0F, 0.7071F);
    bni.b(☃, 0.0F, 1.0F, 0.0F);
    this.a.a(☃);
    bni.H();
  }
}
