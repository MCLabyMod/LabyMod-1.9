public class bgd
  extends bft
{
  private static final kk v = new kk("textures/gui/container/dispenser.png");
  private final zi w;
  public qg u;
  
  public bgd(zi ☃, qg ☃)
  {
    super(new abe(☃, ☃));
    this.w = ☃;
    this.u = ☃;
  }
  
  protected void b(int ☃, int ☃)
  {
    String ☃ = this.u.i_().c();
    this.q.a(☃, this.f / 2 - this.q.a(☃) / 2, 6, 4210752);
    this.q.a(this.w.i_().c(), 8, this.g - 96 + 2, 4210752);
  }
  
  protected void a(float ☃, int ☃, int ☃)
  {
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    this.j.N().a(v);
    int ☃ = (this.l - this.f) / 2;
    int ☃ = (this.m - this.g) / 2;
    b(☃, ☃, 0, 0, this.f, this.g);
  }
}
