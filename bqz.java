public class bqz
  extends bsg<vu>
{
  private static final kk a = new kk("textures/entity/bat.png");
  
  public bqz(brm ☃)
  {
    super(☃, new bii(), 0.25F);
  }
  
  protected kk a(vu ☃)
  {
    return a;
  }
  
  protected void a(vu ☃, float ☃)
  {
    bni.b(0.35F, 0.35F, 0.35F);
  }
  
  protected void a(vu ☃, float ☃, float ☃, float ☃)
  {
    if (!☃.o()) {
      bni.c(0.0F, on.b(☃ * 0.3F) * 0.1F, 0.0F);
    } else {
      bni.c(0.0F, -0.1F, 0.0F);
    }
    super.a(☃, ☃, ☃, ☃);
  }
}
