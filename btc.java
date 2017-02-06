public class btc
  extends bsg<wh>
{
  private static final kk a = new kk("textures/entity/iron_golem.png");
  
  public btc(brm ☃)
  {
    super(☃, new bjz(), 0.5F);
    
    a(new bue(this));
  }
  
  protected kk a(wh ☃)
  {
    return a;
  }
  
  protected void a(wh ☃, float ☃, float ☃, float ☃)
  {
    super.a(☃, ☃, ☃, ☃);
    if (☃.aF < 0.01D) {
      return;
    }
    float ☃ = 13.0F;
    float ☃ = ☃.aG - ☃.aF * (1.0F - ☃) + 6.0F;
    float ☃ = (Math.abs(☃ % ☃ - ☃ * 0.5F) - ☃ * 0.25F) / (☃ * 0.25F);
    bni.b(6.5F * ☃, 0.0F, 0.0F, 1.0F);
  }
}
