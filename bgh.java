public class bgh
  extends bft
{
  private static final kk u = new kk("textures/gui/container/furnace.png");
  private final zi v;
  private qg w;
  
  public bgh(zi ☃, qg ☃)
  {
    super(new abh(☃, ☃));
    this.v = ☃;
    this.w = ☃;
  }
  
  protected void b(int ☃, int ☃)
  {
    String ☃ = this.w.i_().c();
    this.q.a(☃, this.f / 2 - this.q.a(☃) / 2, 6, 4210752);
    this.q.a(this.v.i_().c(), 8, this.g - 96 + 2, 4210752);
  }
  
  protected void a(float ☃, int ☃, int ☃)
  {
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    this.j.N().a(u);
    int ☃ = (this.l - this.f) / 2;
    int ☃ = (this.m - this.g) / 2;
    b(☃, ☃, 0, 0, this.f, this.g);
    if (aqg.a(this.w))
    {
      int ☃ = i(13);
      b(☃ + 56, ☃ + 36 + 12 - ☃, 176, 12 - ☃, 14, ☃ + 1);
    }
    int ☃ = h(24);
    b(☃ + 79, ☃ + 34, 176, 14, ☃ + 1, 16);
  }
  
  private int h(int ☃)
  {
    int ☃ = this.w.c_(2);
    int ☃ = this.w.c_(3);
    if ((☃ == 0) || (☃ == 0)) {
      return 0;
    }
    return ☃ * ☃ / ☃;
  }
  
  private int i(int ☃)
  {
    int ☃ = this.w.c_(1);
    if (☃ == 0) {
      ☃ = 200;
    }
    return this.w.c_(0) * ☃ / ☃;
  }
}
