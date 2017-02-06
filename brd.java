public class brd
  extends bsg<vx>
{
  private static final kk a = new kk("textures/entity/chicken.png");
  
  public brd(brm ☃, bjc ☃, float ☃)
  {
    super(☃, ☃, ☃);
  }
  
  protected kk a(vx ☃)
  {
    return a;
  }
  
  protected float a(vx ☃, float ☃)
  {
    float ☃ = ☃.bz + (☃.bv - ☃.bz) * ☃;
    float ☃ = ☃.bx + (☃.bw - ☃.bx) * ☃;
    
    return (on.a(☃) + 1.0F) * ☃;
  }
}
