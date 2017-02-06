public class brt
  extends bsg<yn>
{
  private static final kk a = new kk("textures/entity/zombie/zombie.png");
  private float b;
  
  public brt(brm ☃, bjc ☃, float ☃, float ☃)
  {
    super(☃, ☃, ☃ * ☃);
    
    this.b = ☃;
    
    a(new btv(this));
    a(new btu(this)
    {
      protected void I_()
      {
        this.c = new bkf(0.5F, true);
        this.d = new bkf(1.0F, true);
      }
    });
  }
  
  public void e()
  {
    bni.c(0.0F, 0.1875F, 0.0F);
  }
  
  protected void a(yn ☃, float ☃)
  {
    bni.b(this.b, this.b, this.b);
  }
  
  protected kk a(yn ☃)
  {
    return a;
  }
}
