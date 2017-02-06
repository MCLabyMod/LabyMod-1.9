public class bjm
  extends bjc
{
  public bkm a;
  
  public bjm()
  {
    this.s = 64;
    this.t = 32;
    
    this.a = new bkm(this);
    this.a.a(0, 0).a(-4.0F, -4.0F, -1.0F, 8, 8, 2, 0.0F);
    this.a.a(0, 10).a(-1.0F, -4.0F, -4.0F, 2, 8, 8, 0.0F);
    this.a.a(20, 0).a(-4.0F, -1.0F, -4.0F, 8, 2, 8, 0.0F);
    this.a.a(0.0F, 0.0F, 0.0F);
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    this.a.a(☃);
  }
  
  public void a(float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, rr ☃)
  {
    super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    
    this.a.g = (☃ * 0.017453292F);
    this.a.f = (☃ * 0.017453292F);
  }
}
