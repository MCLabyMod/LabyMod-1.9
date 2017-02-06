public class bfx
  extends bft
{
  private static final kk u = new kk("textures/gui/container/brewing_stand.png");
  private static final int[] v = { 29, 24, 20, 16, 11, 6, 0 };
  private final zi w;
  private qg x;
  
  public bfx(zi ☃, qg ☃)
  {
    super(new aay(☃, ☃));
    this.w = ☃;
    this.x = ☃;
  }
  
  protected void b(int ☃, int ☃)
  {
    String ☃ = this.x.i_().c();
    this.q.a(☃, this.f / 2 - this.q.a(☃) / 2, 6, 4210752);
    this.q.a(this.w.i_().c(), 8, this.g - 96 + 2, 4210752);
  }
  
  protected void a(float ☃, int ☃, int ☃)
  {
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    this.j.N().a(u);
    int ☃ = (this.l - this.f) / 2;
    int ☃ = (this.m - this.g) / 2;
    b(☃, ☃, 0, 0, this.f, this.g);
    
    int ☃ = this.x.c_(1);
    int ☃ = on.a((18 * ☃ + 20 - 1) / 20, 0, 18);
    if (☃ > 0) {
      b(☃ + 60, ☃ + 44, 176, 29, ☃, 4);
    }
    int ☃ = this.x.c_(0);
    if (☃ > 0)
    {
      int ☃ = (int)(28.0F * (1.0F - ☃ / 400.0F));
      if (☃ > 0) {
        b(☃ + 97, ☃ + 16, 176, 0, 9, ☃);
      }
      ☃ = v[(☃ / 2 % 7)];
      if (☃ > 0) {
        b(☃ + 63, ☃ + 14 + 29 - ☃, 185, 29 - ☃, 12, ☃);
      }
    }
  }
}
