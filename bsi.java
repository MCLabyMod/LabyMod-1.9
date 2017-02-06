public class bsi
  extends bsg<wb>
{
  private static final kk a = new kk("textures/entity/cat/black.png");
  private static final kk b = new kk("textures/entity/cat/ocelot.png");
  private static final kk k = new kk("textures/entity/cat/red.png");
  private static final kk l = new kk("textures/entity/cat/siamese.png");
  
  public bsi(brm ☃, bjc ☃, float ☃)
  {
    super(☃, ☃, ☃);
  }
  
  protected kk a(wb ☃)
  {
    switch (☃.dh())
    {
    case 0: 
    default: 
      return b;
    case 1: 
      return a;
    case 2: 
      return k;
    }
    return l;
  }
  
  protected void a(wb ☃, float ☃)
  {
    super.a(☃, ☃);
    if (☃.cZ()) {
      bni.b(0.8F, 0.8F, 0.8F);
    }
  }
}
