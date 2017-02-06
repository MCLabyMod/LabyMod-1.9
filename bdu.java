public class bdu
  extends bcv
  implements bhv
{
  private static final kk f = new kk("textures/gui/widgets.png");
  public static final kk a = new kk("textures/gui/spectator_widgets.png");
  private final bcf g;
  private long h;
  private bhs i;
  
  public bdu(bcf ☃)
  {
    this.g = ☃;
  }
  
  public void a(int ☃)
  {
    this.h = bcf.I();
    if (this.i != null) {
      this.i.b(☃);
    } else {
      this.i = new bhs(this);
    }
  }
  
  private float c()
  {
    long ☃ = this.h - bcf.I() + 5000L;
    return on.a((float)☃ / 2000.0F, 0.0F, 1.0F);
  }
  
  public void a(bcx ☃, float ☃)
  {
    if (this.i == null) {
      return;
    }
    float ☃ = c();
    if (☃ <= 0.0F)
    {
      this.i.d();
      return;
    }
    int ☃ = ☃.a() / 2;
    float ☃ = this.e;
    this.e = -90.0F;
    float ☃ = ☃.b() - 22.0F * ☃;
    
    bhw ☃ = this.i.f();
    
    a(☃, ☃, ☃, ☃, ☃);
    
    this.e = ☃;
  }
  
  protected void a(bcx ☃, float ☃, int ☃, float ☃, bhw ☃)
  {
    bni.D();
    bni.m();
    bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
    bni.c(1.0F, 1.0F, 1.0F, ☃);
    this.g.N().a(f);
    a(☃ - 91, ☃, 0, 0, 182, 22);
    if (☃.b() >= 0) {
      a(☃ - 91 - 1 + ☃.b() * 20, ☃ - 1.0F, 0, 22, 24, 22);
    }
    bcd.c();
    for (int ☃ = 0; ☃ < 9; ☃++) {
      a(☃, ☃.a() / 2 - 90 + ☃ * 20 + 2, ☃ + 3.0F, ☃, ☃.a(☃));
    }
    bcd.a();
    bni.E();
    bni.l();
  }
  
  private void a(int ☃, int ☃, float ☃, float ☃, bhu ☃)
  {
    this.g.N().a(a);
    if (☃ != bhs.a)
    {
      int ☃ = (int)(☃ * 255.0F);
      
      bni.G();
      bni.c(☃, ☃, 0.0F);
      float ☃ = ☃.G_() ? 1.0F : 0.25F;
      bni.c(☃, ☃, ☃, ☃);
      ☃.a(☃, ☃);
      bni.H();
      
      String ☃ = String.valueOf(bch.c(this.g.u.ak[☃].j()));
      if ((☃ > 3) && (☃.G_())) {
        this.g.k.a(☃, ☃ + 19 - 2 - this.g.k.a(☃), ☃ + 6.0F + 3.0F, 16777215 + (☃ << 24));
      }
    }
  }
  
  public void a(bcx ☃)
  {
    int ☃ = (int)(c() * 255.0F);
    if ((☃ > 3) && (this.i != null))
    {
      bhu ☃ = this.i.b();
      String ☃ = ☃ != bhs.a ? ☃.F_().d() : this.i.c().b().d();
      if (☃ != null)
      {
        int ☃ = (☃.a() - this.g.k.a(☃)) / 2;
        int ☃ = ☃.b() - 35;
        
        bni.G();
        bni.m();
        bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
        this.g.k.a(☃, ☃, ☃, 16777215 + (☃ << 24));
        bni.l();
        bni.H();
      }
    }
  }
  
  public void a(bhs ☃)
  {
    this.i = null;
    this.h = 0L;
  }
  
  public boolean a()
  {
    return this.i != null;
  }
  
  public void b(int ☃)
  {
    int ☃ = this.i.e() + ☃;
    while ((☃ >= 0) && (☃ <= 8) && ((this.i.a(☃) == bhs.a) || (!this.i.a(☃).G_()))) {
      ☃ += ☃;
    }
    if ((☃ >= 0) && (☃ <= 8))
    {
      this.i.b(☃);
      this.h = bcf.I();
    }
  }
  
  public void b()
  {
    this.h = bcf.I();
    if (a())
    {
      int ☃ = this.i.e();
      if (☃ != -1) {
        this.i.b(☃);
      }
    }
    else
    {
      this.i = new bhs(this);
    }
  }
}
