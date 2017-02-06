public class bsb
  extends brn<xt>
{
  private static final kk a = new kk("textures/entity/lead_knot.png");
  private bja b = new bja();
  
  public bsb(brm ☃)
  {
    super(☃);
  }
  
  public void a(xt ☃, double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    bni.G();
    bni.r();
    
    bni.c((float)☃, (float)☃, (float)☃);
    
    float ☃ = 0.0625F;
    bni.D();
    bni.b(-1.0F, -1.0F, 1.0F);
    
    bni.e();
    
    d(☃);
    if (this.f)
    {
      bni.h();
      bni.e(c(☃));
    }
    this.b.a(☃, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, ☃);
    if (this.f)
    {
      bni.n();
      bni.i();
    }
    bni.H();
    
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  protected kk a(xt ☃)
  {
    return a;
  }
}
