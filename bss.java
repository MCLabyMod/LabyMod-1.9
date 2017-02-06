public class bss
  extends bsg<yx>
{
  private static final kk a = new kk("textures/entity/slime/slime.png");
  
  public bss(brm ☃, bjc ☃, float ☃)
  {
    super(☃, ☃, ☃);
    
    a(new bua(this));
  }
  
  public void a(yx ☃, double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    this.d = (0.25F * ☃.da());
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  protected void a(yx ☃, float ☃)
  {
    float ☃ = 0.999F;
    bni.b(☃, ☃, ☃);
    
    float ☃ = ☃.da();
    float ☃ = (☃.c + (☃.b - ☃.c) * ☃) / (☃ * 0.5F + 1.0F);
    float ☃ = 1.0F / (☃ + 1.0F);
    bni.b(☃ * ☃, 1.0F / ☃ * ☃, ☃ * ☃);
  }
  
  protected kk a(yx ☃)
  {
    return a;
  }
}
