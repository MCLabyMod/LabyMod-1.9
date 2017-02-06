public class bfl
  extends bcv
{
  private static final kk a = new kk("textures/gui/achievement/achievement_background.png");
  private bcf f;
  private int g;
  private int h;
  private String i;
  private String j;
  private nj k;
  private long l;
  private brz m;
  private boolean n;
  
  public bfl(bcf ☃)
  {
    this.f = ☃;
    this.m = ☃.ad();
  }
  
  public void a(nj ☃)
  {
    this.i = bwo.a("achievement.get", new Object[0]);
    this.j = ☃.e().c();
    this.l = bcf.I();
    this.k = ☃;
    this.n = false;
  }
  
  public void b(nj ☃)
  {
    this.i = ☃.e().c();
    this.j = ☃.f();
    
    this.l = (bcf.I() + 2500L);
    this.k = ☃;
    this.n = true;
  }
  
  private void c()
  {
    bni.b(0, 0, this.f.d, this.f.e);
    bni.n(5889);
    bni.F();
    bni.n(5888);
    bni.F();
    
    this.g = this.f.d;
    this.h = this.f.e;
    
    bcx ☃ = new bcx(this.f);
    this.g = ☃.a();
    this.h = ☃.b();
    
    bni.m(256);
    bni.n(5889);
    bni.F();
    bni.a(0.0D, this.g, this.h, 0.0D, 1000.0D, 3000.0D);
    bni.n(5888);
    bni.F();
    bni.c(0.0F, 0.0F, -2000.0F);
  }
  
  public void a()
  {
    if ((this.k == null) || (this.l == 0L) || (bcf.z().h == null)) {
      return;
    }
    double ☃ = (bcf.I() - this.l) / 3000.0D;
    if (!this.n)
    {
      if ((☃ < 0.0D) || (☃ > 1.0D)) {
        this.l = 0L;
      }
    }
    else if (☃ > 0.5D) {
      ☃ = 0.5D;
    }
    c();
    bni.j();
    bni.a(false);
    
    double ☃ = ☃ * 2.0D;
    if (☃ > 1.0D) {
      ☃ = 2.0D - ☃;
    }
    ☃ *= 4.0D;
    ☃ = 1.0D - ☃;
    if (☃ < 0.0D) {
      ☃ = 0.0D;
    }
    ☃ *= ☃;
    ☃ *= ☃;
    
    int ☃ = this.g - 160;
    int ☃ = 0 - (int)(☃ * 36.0D);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bni.y();
    this.f.N().a(a);
    bni.g();
    
    b(☃, ☃, 96, 202, 160, 32);
    if (this.n)
    {
      this.f.k.a(this.j, ☃ + 30, ☃ + 7, 120, -1);
    }
    else
    {
      this.f.k.a(this.i, ☃ + 30, ☃ + 7, 65280);
      this.f.k.a(this.j, ☃ + 30, ☃ + 18, -1);
    }
    bcd.c();
    bni.g();
    bni.D();
    bni.h();
    
    bni.f();
    this.m.b(this.k.d, ☃ + 8, ☃ + 8);
    bni.g();
    
    bni.a(true);
    bni.k();
  }
  
  public void b()
  {
    this.k = null;
    this.l = 0L;
  }
}
