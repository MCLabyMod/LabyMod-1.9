public class brf
  extends bsg<yi>
{
  private static final kk a = new kk("textures/entity/creeper/creeper.png");
  
  public brf(brm ☃)
  {
    super(☃, new bip(), 0.5F);
    
    a(new btn(this));
  }
  
  protected void a(yi ☃, float ☃)
  {
    float ☃ = ☃.a(☃);
    
    float ☃ = 1.0F + on.a(☃ * 100.0F) * ☃ * 0.01F;
    ☃ = on.a(☃, 0.0F, 1.0F);
    ☃ *= ☃;
    ☃ *= ☃;
    float ☃ = (1.0F + ☃ * 0.4F) * ☃;
    float ☃ = (1.0F + ☃ * 0.1F) / ☃;
    bni.b(☃, ☃, ☃);
  }
  
  protected int a(yi ☃, float ☃, float ☃)
  {
    float ☃ = ☃.a(☃);
    if ((int)(☃ * 10.0F) % 2 == 0) {
      return 0;
    }
    int ☃ = (int)(☃ * 0.2F * 255.0F);
    ☃ = on.a(☃, 0, 255);
    
    return ☃ << 24 | 0x30FFFFFF;
  }
  
  protected kk a(yi ☃)
  {
    return a;
  }
}
