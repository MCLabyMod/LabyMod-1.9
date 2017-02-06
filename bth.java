public class bth
  extends bsg<wj>
{
  private static final kk a = new kk("textures/entity/wolf/wolf.png");
  private static final kk b = new kk("textures/entity/wolf/wolf_tame.png");
  private static final kk k = new kk("textures/entity/wolf/wolf_angry.png");
  
  public bth(brm ☃, bjc ☃, float ☃)
  {
    super(☃, ☃, ☃);
    
    a(new buh(this));
  }
  
  protected float a(wj ☃, float ☃)
  {
    return ☃.di();
  }
  
  public void a(wj ☃, double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    if (☃.dh())
    {
      float ☃ = ☃.e(☃) * ☃.r(☃);
      bni.d(☃, ☃, ☃);
    }
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  protected kk a(wj ☃)
  {
    if (☃.cZ()) {
      return b;
    }
    if (☃.dj()) {
      return k;
    }
    return a;
  }
}
