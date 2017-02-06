public class brb
  extends brn<aag>
{
  private static final kk[] b = { new kk("textures/entity/boat/boat_oak.png"), new kk("textures/entity/boat/boat_spruce.png"), new kk("textures/entity/boat/boat_birch.png"), new kk("textures/entity/boat/boat_jungle.png"), new kk("textures/entity/boat/boat_acacia.png"), new kk("textures/entity/boat/boat_darkoak.png") };
  protected bjc a = new bik();
  
  public brb(brm ☃)
  {
    super(☃);
    this.d = 0.5F;
  }
  
  public void a(aag ☃, double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    bni.G();
    
    a(☃, ☃, ☃);
    a(☃, ☃, ☃);
    
    d(☃);
    if (this.f)
    {
      bni.h();
      bni.e(c(☃));
    }
    this.a.a(☃, ☃, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    if (this.f)
    {
      bni.n();
      bni.i();
    }
    bni.H();
    
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public void a(aag ☃, float ☃, float ☃)
  {
    bni.b(180.0F - ☃, 0.0F, 1.0F, 0.0F);
    float ☃ = ☃.o() - ☃;
    float ☃ = ☃.n() - ☃;
    if (☃ < 0.0F) {
      ☃ = 0.0F;
    }
    if (☃ > 0.0F) {
      bni.b(on.a(☃) * ☃ * ☃ / 10.0F * ☃.q(), 1.0F, 0.0F, 0.0F);
    }
    bni.b(-1.0F, -1.0F, 1.0F);
  }
  
  public void a(double ☃, double ☃, double ☃)
  {
    bni.c((float)☃, (float)☃ + 0.375F, (float)☃);
  }
  
  protected kk a(aag ☃)
  {
    return b[☃.r().ordinal()];
  }
  
  public boolean H_()
  {
    return true;
  }
  
  public void b(aag ☃, double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    bni.G();
    a(☃, ☃, ☃);
    a(☃, ☃, ☃);
    d(☃);
    ((bjx)this.a).b(☃, ☃, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    bni.H();
  }
}
