public class bgj
  extends bft
{
  private static final kk u = new kk("textures/gui/container/horse.png");
  private qg v;
  private qg w;
  private wk x;
  private float y;
  private float z;
  
  public bgj(qg ☃, qg ☃, wk ☃)
  {
    super(new abk(☃, ☃, ☃, bcf.z().h));
    this.v = ☃;
    this.w = ☃;
    this.x = ☃;
    this.p = false;
  }
  
  protected void b(int ☃, int ☃)
  {
    this.q.a(this.w.i_().c(), 8, 6, 4210752);
    this.q.a(this.v.i_().c(), 8, this.g - 96 + 2, 4210752);
  }
  
  protected void a(float ☃, int ☃, int ☃)
  {
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    this.j.N().a(u);
    int ☃ = (this.l - this.f) / 2;
    int ☃ = (this.m - this.g) / 2;
    b(☃, ☃, 0, 0, this.f, this.g);
    if (this.x.dk()) {
      b(☃ + 79, ☃ + 17, 0, this.g, 90, 54);
    }
    if (this.x.cZ().j()) {
      b(☃ + 7, ☃ + 35, 0, this.g + 54, 18, 18);
    }
    bgk.a(☃ + 51, ☃ + 60, 17, ☃ + 51 - this.y, ☃ + 75 - 50 - this.z, this.x);
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    this.y = ☃;
    this.z = ☃;
    
    super.a(☃, ☃, ☃);
  }
}
