import org.lwjgl.opengl.GL11;

public class CloudRenderer
{
  private bcf mc;
  private boolean updated = false;
  private boolean renderFancy = false;
  int cloudTickCounter;
  float partialTicks;
  private int glListClouds = -1;
  private int cloudTickCounterUpdate = 0;
  private double cloudPlayerX = 0.0D;
  private double cloudPlayerY = 0.0D;
  private double cloudPlayerZ = 0.0D;
  
  public CloudRenderer(bcf mc)
  {
    this.mc = mc;
    
    this.glListClouds = bce.a(1);
  }
  
  public void prepareToRender(boolean renderFancy, int cloudTickCounter, float partialTicks)
  {
    if (this.renderFancy != renderFancy) {
      this.updated = false;
    }
    this.renderFancy = renderFancy;
    this.cloudTickCounter = cloudTickCounter;
    this.partialTicks = partialTicks;
  }
  
  public boolean shouldUpdateGlList()
  {
    if (!this.updated) {
      return true;
    }
    if (this.cloudTickCounter >= this.cloudTickCounterUpdate + 20) {
      return true;
    }
    rr rve = this.mc.aa();
    boolean belowCloudsPrev = this.cloudPlayerY + rve.bn() < 128.0D + this.mc.u.ofCloudsHeight * 128.0F;
    boolean belowClouds = rve.n + rve.bn() < 128.0D + this.mc.u.ofCloudsHeight * 128.0F;
    if (belowClouds != belowCloudsPrev) {
      return true;
    }
    return false;
  }
  
  public void startUpdateGlList()
  {
    GL11.glNewList(this.glListClouds, 4864);
  }
  
  public void endUpdateGlList()
  {
    GL11.glEndList();
    
    this.cloudTickCounterUpdate = this.cloudTickCounter;
    this.cloudPlayerX = this.mc.aa().m;
    this.cloudPlayerY = this.mc.aa().n;
    this.cloudPlayerZ = this.mc.aa().o;
    
    this.updated = true;
    
    bni.I();
  }
  
  public void renderGlList()
  {
    rr entityliving = this.mc.aa();
    double exactPlayerX = entityliving.m + (entityliving.p - entityliving.m) * this.partialTicks;
    double exactPlayerY = entityliving.n + (entityliving.q - entityliving.n) * this.partialTicks;
    double exactPlayerZ = entityliving.o + (entityliving.r - entityliving.o) * this.partialTicks;
    double dc = this.cloudTickCounter - this.cloudTickCounterUpdate + this.partialTicks;
    float cdx = (float)(exactPlayerX - this.cloudPlayerX + dc * 0.03D);
    float cdy = (float)(exactPlayerY - this.cloudPlayerY);
    float cdz = (float)(exactPlayerZ - this.cloudPlayerZ);
    
    bni.G();
    if (this.renderFancy) {
      bni.c(-cdx / 12.0F, -cdy, -cdz / 12.0F);
    } else {
      bni.c(-cdx, -cdy, -cdz);
    }
    bni.s(this.glListClouds);
    
    bni.H();
    
    bni.I();
  }
  
  public void reset()
  {
    this.updated = false;
  }
}
