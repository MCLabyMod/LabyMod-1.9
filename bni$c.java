import org.lwjgl.opengl.GL11;

class bni$c
{
  private final int a;
  private boolean b = false;
  
  public bni$c(int capabilityIn)
  {
    this.a = capabilityIn;
  }
  
  public void a()
  {
    a(false);
  }
  
  public void b()
  {
    a(true);
  }
  
  public void a(boolean state)
  {
    if (state != this.b)
    {
      this.b = state;
      if (state) {
        GL11.glEnable(this.a);
      } else {
        GL11.glDisable(this.a);
      }
    }
  }
}
