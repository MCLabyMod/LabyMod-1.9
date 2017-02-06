public class bgi
  extends bft
{
  private static final kk u = new kk("textures/gui/container/hopper.png");
  private qg v;
  private qg w;
  
  public bgi(zi ☃, qg ☃)
  {
    super(new abj(☃, ☃, bcf.z().h));
    this.v = ☃;
    this.w = ☃;
    this.p = false;
    
    this.g = 133;
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
  }
}
