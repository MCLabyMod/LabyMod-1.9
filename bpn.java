public abstract class bpn<T extends apv>
{
  protected static final kk[] b = { new kk("textures/blocks/destroy_stage_0.png"), new kk("textures/blocks/destroy_stage_1.png"), new kk("textures/blocks/destroy_stage_2.png"), new kk("textures/blocks/destroy_stage_3.png"), new kk("textures/blocks/destroy_stage_4.png"), new kk("textures/blocks/destroy_stage_5.png"), new kk("textures/blocks/destroy_stage_6.png"), new kk("textures/blocks/destroy_stage_7.png"), new kk("textures/blocks/destroy_stage_8.png"), new kk("textures/blocks/destroy_stage_9.png") };
  protected bpm c;
  
  public abstract void a(T paramT, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat, int paramInt);
  
  protected void a(kk ☃)
  {
    bvi ☃ = this.c.e;
    if (☃ != null) {
      ☃.a(☃);
    }
  }
  
  protected aht a()
  {
    return this.c.f;
  }
  
  public void a(bpm ☃)
  {
    this.c = ☃;
  }
  
  public bct b()
  {
    return this.c.a();
  }
  
  public boolean a(T ☃)
  {
    return false;
  }
}
