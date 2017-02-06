public class btd
  extends bsg<ze>
{
  private static final kk a = new kk("textures/entity/villager/villager.png");
  private static final kk b = new kk("textures/entity/villager/farmer.png");
  private static final kk k = new kk("textures/entity/villager/librarian.png");
  private static final kk l = new kk("textures/entity/villager/priest.png");
  private static final kk m = new kk("textures/entity/villager/smith.png");
  private static final kk n = new kk("textures/entity/villager/butcher.png");
  
  public btd(brm ☃)
  {
    super(☃, new bka(0.0F), 0.5F);
    
    a(new bto(h().a));
  }
  
  public bka h()
  {
    return (bka)super.b();
  }
  
  protected kk a(ze ☃)
  {
    switch (☃.cZ())
    {
    case 0: 
      return b;
    case 1: 
      return k;
    case 2: 
      return l;
    case 3: 
      return m;
    case 4: 
      return n;
    }
    return a;
  }
  
  protected void a(ze ☃, float ☃)
  {
    float ☃ = 0.9375F;
    if (☃.l() < 0)
    {
      ☃ = (float)(☃ * 0.5D);
      this.d = 0.25F;
    }
    else
    {
      this.d = 0.5F;
    }
    bni.b(☃, ☃, ☃);
  }
}
