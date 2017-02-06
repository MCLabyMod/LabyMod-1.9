public class bjn
  extends bjc
{
  private bkm b;
  private bkm c;
  public bkm a;
  
  public bjn()
  {
    this.t = 64;
    this.s = 64;
    
    this.c = new bkm(this);
    this.b = new bkm(this);
    this.a = new bkm(this);
    
    this.c.a(0, 0).a(-8.0F, -16.0F, -8.0F, 16, 12, 16);
    this.c.a(0.0F, 24.0F, 0.0F);
    
    this.b.a(0, 28).a(-8.0F, -8.0F, -8.0F, 16, 8, 16);
    this.b.a(0.0F, 24.0F, 0.0F);
    
    this.a.a(0, 52).a(-3.0F, 0.0F, -3.0F, 6, 6, 6);
    this.a.a(0.0F, 12.0F, 0.0F);
  }
  
  public int a()
  {
    return 28;
  }
  
  public void a(sa ☃, float ☃, float ☃, float ☃) {}
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    yu ☃ = (yu)☃;
    float ☃ = ☃ - ☃.T;
    float ☃ = (0.5F + ☃.a(☃)) * 3.1415927F;
    float ☃ = -1.0F + on.a(☃);
    float ☃ = 0.0F;
    if (☃ > 3.1415927F) {
      ☃ = on.a(☃ * 0.1F) * 0.7F;
    }
    this.c.a(0.0F, 16.0F + on.a(☃) * 8.0F + ☃, 0.0F);
    if (☃.a(☃) > 0.3F) {
      this.c.g = (☃ * ☃ * ☃ * ☃ * 3.1415927F * 0.125F);
    } else {
      this.c.g = 0.0F;
    }
    this.a.f = (☃ * 0.017453292F);
    this.a.g = (☃ * 0.017453292F);
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    this.b.a(☃);
    this.c.a(☃);
  }
}
