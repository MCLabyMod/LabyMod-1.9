public class bfz
  extends bft
{
  private static final kk u = new kk("textures/gui/container/generic_54.png");
  private qg v;
  private qg w;
  private int x;
  
  public bfz(qg ☃, qg ☃)
  {
    super(new abb(☃, ☃, bcf.z().h));
    this.v = ☃;
    this.w = ☃;
    this.p = false;
    
    int ☃ = 222;
    int ☃ = ☃ - 108;
    this.x = (☃.u_() / 9);
    
    this.g = (☃ + this.x * 18);
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
    b(☃, ☃, 0, 0, this.f, this.x * 18 + 17);
    b(☃, ☃ + this.x * 18 + 17, 0, 126, this.f, 96);
  }
}
