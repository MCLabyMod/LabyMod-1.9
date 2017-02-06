public class bgt
  implements bdl.a
{
  private final bgr c;
  protected final bcf a;
  protected final byp.a b;
  private long d = 0L;
  
  protected bgt(bgr ☃, byp.a ☃)
  {
    this.c = ☃;
    this.b = ☃;
    this.a = bcf.z();
  }
  
  public void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, boolean ☃)
  {
    this.a.k.a(bwo.a("lanServer.title", new Object[0]), ☃ + 32 + 3, ☃ + 1, 16777215);
    this.a.k.a(this.b.a(), ☃ + 32 + 3, ☃ + 12, 8421504);
    if (this.a.u.w) {
      this.a.k.a(bwo.a("selectServer.hiddenAddress", new Object[0]), ☃ + 32 + 3, ☃ + 12 + 11, 3158064);
    } else {
      this.a.k.a(this.b.b(), ☃ + 32 + 3, ☃ + 12 + 11, 3158064);
    }
  }
  
  public boolean a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    this.c.b(☃);
    if (bcf.I() - this.d < 250L) {
      this.c.f();
    }
    this.d = bcf.I();
    return false;
  }
  
  public void a(int ☃, int ☃, int ☃) {}
  
  public void b(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃) {}
  
  public byp.a a()
  {
    return this.b;
  }
}
