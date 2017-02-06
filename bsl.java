public class bsl
  extends brw<yr>
{
  private static final kk k = new kk("textures/entity/zombie_pigman.png");
  
  public bsl(brm ☃)
  {
    super(☃, new bkf(), 0.5F, 1.0F);
    
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
  
  protected kk a(yr ☃)
  {
    return k;
  }
}
