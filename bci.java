public class bci
  implements op
{
  private String a = "";
  private bcf b;
  private String c = "";
  private long d = bcf.I();
  private boolean e;
  private bcx f;
  private bnt g;
  
  public bci(bcf ☃)
  {
    this.b = ☃;
    this.f = new bcx(☃);
    this.g = new bnt(☃.d, ☃.e, false);
    this.g.a(9728);
  }
  
  public void b(String ☃)
  {
    this.e = false;
    d(☃);
  }
  
  public void a(String ☃)
  {
    this.e = true;
    d(☃);
  }
  
  private void d(String ☃)
  {
    this.c = ☃;
    if (!this.b.C)
    {
      if (this.e) {
        return;
      }
      throw new bck();
    }
    bni.m(256);
    bni.n(5889);
    bni.F();
    if (bzg.j())
    {
      int ☃ = this.f.e();
      bni.a(0.0D, this.f.a() * ☃, this.f.b() * ☃, 0.0D, 100.0D, 300.0D);
    }
    else
    {
      bcx ☃ = new bcx(this.b);
      bni.a(0.0D, ☃.c(), ☃.d(), 0.0D, 100.0D, 300.0D);
    }
    bni.n(5888);
    bni.F();
    bni.c(0.0F, 0.0F, -200.0F);
  }
  
  public void c(String ☃)
  {
    if (!this.b.C)
    {
      if (this.e) {
        return;
      }
      throw new bck();
    }
    this.d = 0L;
    this.a = ☃;
    a(-1);
    this.d = 0L;
  }
  
  public void a(int ☃)
  {
    if (!this.b.C)
    {
      if (this.e) {
        return;
      }
      throw new bck();
    }
    long ☃ = bcf.I();
    if (☃ - this.d < 100L) {
      return;
    }
    this.d = ☃;
    
    bcx ☃ = new bcx(this.b);
    int ☃ = ☃.e();
    int ☃ = ☃.a();
    int ☃ = ☃.b();
    if (bzg.j()) {
      this.g.f();
    } else {
      bni.m(256);
    }
    this.g.a(false);
    bni.n(5889);
    bni.F();
    bni.a(0.0D, ☃.c(), ☃.d(), 0.0D, 100.0D, 300.0D);
    bni.n(5888);
    bni.F();
    bni.c(0.0F, 0.0F, -200.0F);
    if (!bzg.j()) {
      bni.m(16640);
    }
    bnu ☃ = bnu.a();
    bmz ☃ = ☃.c();
    this.b.N().a(bcv.b);
    float ☃ = 32.0F;
    ☃.a(7, bvp.i);
    ☃.b(0.0D, ☃, 0.0D).a(0.0D, ☃ / ☃).b(64, 64, 64, 255).d();
    ☃.b(☃, ☃, 0.0D).a(☃ / ☃, ☃ / ☃).b(64, 64, 64, 255).d();
    ☃.b(☃, 0.0D, 0.0D).a(☃ / ☃, 0.0D).b(64, 64, 64, 255).d();
    ☃.b(0.0D, 0.0D, 0.0D).a(0.0D, 0.0D).b(64, 64, 64, 255).d();
    ☃.b();
    if (☃ >= 0)
    {
      int ☃ = 100;
      int ☃ = 2;
      int ☃ = ☃ / 2 - ☃ / 2;
      int ☃ = ☃ / 2 + 16;
      
      bni.z();
      ☃.a(7, bvp.f);
      ☃.b(☃, ☃, 0.0D).b(128, 128, 128, 255).d();
      ☃.b(☃, ☃ + ☃, 0.0D).b(128, 128, 128, 255).d();
      ☃.b(☃ + ☃, ☃ + ☃, 0.0D).b(128, 128, 128, 255).d();
      ☃.b(☃ + ☃, ☃, 0.0D).b(128, 128, 128, 255).d();
      
      ☃.b(☃, ☃, 0.0D).b(128, 255, 128, 255).d();
      ☃.b(☃, ☃ + ☃, 0.0D).b(128, 255, 128, 255).d();
      ☃.b(☃ + ☃, ☃ + ☃, 0.0D).b(128, 255, 128, 255).d();
      ☃.b(☃ + ☃, ☃, 0.0D).b(128, 255, 128, 255).d();
      ☃.b();
      bni.y();
    }
    bni.m();
    bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
    this.b.k.a(this.c, (☃ - this.b.k.a(this.c)) / 2, ☃ / 2 - 4 - 16, 16777215);
    this.b.k.a(this.a, (☃ - this.b.k.a(this.a)) / 2, ☃ / 2 - 4 + 8, 16777215);
    this.g.e();
    if (bzg.j()) {
      this.g.c(☃ * ☃, ☃ * ☃);
    }
    this.b.i();
    try
    {
      Thread.yield();
    }
    catch (Exception localException) {}
  }
  
  public void a() {}
}
