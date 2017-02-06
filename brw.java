public class brw<T extends sb>
  extends bsg<T>
{
  private static final kk k = new kk("textures/entity/steve.png");
  protected bix a;
  protected float b;
  
  public brw(brm ☃, bix ☃, float ☃)
  {
    this(☃, ☃, ☃, 1.0F);
    
    a(new btv(this));
  }
  
  public brw(brm ☃, bix ☃, float ☃, float ☃)
  {
    super(☃, ☃, ☃);
    this.a = ☃;
    this.b = ☃;
    
    a(new bto(☃.e));
  }
  
  protected kk a(T ☃)
  {
    return k;
  }
  
  public void e()
  {
    bni.c(0.0F, 0.1875F, 0.0F);
  }
}
