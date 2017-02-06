public class bsz
  extends bqy<aad>
{
  public static final kk a = new kk("textures/entity/projectiles/arrow.png");
  public static final kk b = new kk("textures/entity/projectiles/tipped_arrow.png");
  
  public bsz(brm ☃)
  {
    super(☃);
  }
  
  protected kk a(aad ☃)
  {
    return ☃.n() > 0 ? b : a;
  }
}
