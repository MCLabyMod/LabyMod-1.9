public class bsr
  extends brw<yw>
{
  private static final kk k = new kk("textures/entity/skeleton/skeleton.png");
  private static final kk l = new kk("textures/entity/skeleton/wither_skeleton.png");
  
  public bsr(brm ☃)
  {
    super(☃, new bjr(), 0.5F);
    
    a(new btv(this));
    a(new btu(this)
    {
      protected void I_()
      {
        this.c = new bjr(0.5F, true);
        this.d = new bjr(1.0F, true);
      }
    });
  }
  
  protected void a(yw ☃, float ☃)
  {
    if (☃.da() == 1) {
      bni.b(1.2F, 1.2F, 1.2F);
    }
  }
  
  public void e()
  {
    bni.c(0.09375F, 0.1875F, 0.0F);
  }
  
  protected kk a(yw ☃)
  {
    if (☃.da() == 1) {
      return l;
    }
    return k;
  }
}
