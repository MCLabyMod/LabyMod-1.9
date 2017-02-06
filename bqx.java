public class bqx
  extends bsd<xq>
{
  public static final kk a = new kk("textures/entity/armorstand/wood.png");
  
  public bqx(brm ☃)
  {
    super(☃, new big(), 0.0F);
    btu ☃ = new btu(this)
    {
      protected void I_()
      {
        this.c = new bif(0.5F);
        this.d = new bif(1.0F);
      }
    };
    a(☃);
    a(new btv(this));
    a(new bto(a().e));
  }
  
  protected kk a(xq ☃)
  {
    return a;
  }
  
  public big a()
  {
    return (big)super.b();
  }
  
  protected void a(xq ☃, float ☃, float ☃, float ☃)
  {
    bni.b(180.0F - ☃, 0.0F, 1.0F, 0.0F);
    
    float ☃ = (float)(☃.l.P() - ☃.h) + ☃;
    if (☃ < 5.0F) {
      bni.b(on.a(☃ / 1.5F * 3.1415927F) * 3.0F, 0.0F, 1.0F, 0.0F);
    }
  }
  
  protected boolean b(xq ☃)
  {
    return ☃.bg();
  }
  
  public void a(xq ☃, double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    if (☃.u()) {
      this.j = true;
    }
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
    if (☃.u()) {
      this.j = false;
    }
  }
}
