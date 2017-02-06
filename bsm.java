public class bsm
  extends bsg<wd>
{
  private static final kk a = new kk("textures/entity/rabbit/brown.png");
  private static final kk b = new kk("textures/entity/rabbit/white.png");
  private static final kk k = new kk("textures/entity/rabbit/black.png");
  private static final kk l = new kk("textures/entity/rabbit/gold.png");
  private static final kk m = new kk("textures/entity/rabbit/salt.png");
  private static final kk n = new kk("textures/entity/rabbit/white_splotched.png");
  private static final kk o = new kk("textures/entity/rabbit/toast.png");
  private static final kk p = new kk("textures/entity/rabbit/caerbannog.png");
  
  public bsm(brm ☃, bjc ☃, float ☃)
  {
    super(☃, ☃, ☃);
  }
  
  protected kk a(wd ☃)
  {
    String ☃ = a.a(☃.h_());
    if ((☃ != null) && (☃.equals("Toast"))) {
      return o;
    }
    switch (☃.db())
    {
    case 0: 
    default: 
      return a;
    case 1: 
      return b;
    case 2: 
      return k;
    case 4: 
      return l;
    case 5: 
      return m;
    case 3: 
      return n;
    }
    return p;
  }
}
