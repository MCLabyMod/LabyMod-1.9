public class btq
  implements bty<bmq>
{
  private static final kk a = new kk("textures/entity/elytra.png");
  private final buk b;
  private final biq c = new biq();
  
  public btq(buk ☃)
  {
    this.b = ☃;
  }
  
  public void a(bmq ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    adq ☃ = ☃.a(rw.e);
    if ((☃ == null) || (☃.b() != ads.cR)) {
      return;
    }
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    if ((☃.s()) && (☃.t() != null)) {
      this.b.a(☃.t());
    } else if ((☃.a()) && (☃.r() != null) && (☃.a(zk.a))) {
      this.b.a(☃.r());
    } else {
      this.b.a(a);
    }
    bni.G();
    bni.c(0.0F, 0.0F, 0.125F);
    
    this.c.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    this.c.a(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    if (☃.w()) {
      btj.a(this.b, ☃, this.c, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
    bni.H();
  }
  
  public boolean a()
  {
    return false;
  }
}
