public class bte
  extends bsg<yz>
{
  private static final kk a = new kk("textures/entity/witch.png");
  
  public bte(brm ☃)
  {
    super(☃, new bkc(0.0F), 0.5F);
    
    a(new buf(this));
  }
  
  public void a(yz ☃, double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    ((bkc)this.g).g = (☃.cb() != null);
    
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  protected kk a(yz ☃)
  {
    return a;
  }
  
  public void e()
  {
    bni.c(0.0F, 0.1875F, 0.0F);
  }
  
  protected void a(yz ☃, float ☃)
  {
    float ☃ = 0.9375F;
    bni.b(☃, ☃, ☃);
  }
}
