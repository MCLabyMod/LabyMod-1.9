import java.util.List;

public class bgk
  extends bge
{
  private float u;
  private float v;
  
  public bgk(zj ☃)
  {
    super(☃.bs);
    this.p = true;
  }
  
  public void e()
  {
    if (this.j.c.h()) {
      this.j.a(new bgc(this.j.h));
    }
  }
  
  public void b()
  {
    this.n.clear();
    if (this.j.c.h()) {
      this.j.a(new bgc(this.j.h));
    } else {
      super.b();
    }
  }
  
  protected void b(int ☃, int ☃)
  {
    this.q.a(bwo.a("container.crafting", new Object[0]), 97, 8, 4210752);
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    super.a(☃, ☃, ☃);
    this.u = ☃;
    this.v = ☃;
  }
  
  protected void a(float ☃, int ☃, int ☃)
  {
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    this.j.N().a(a);
    int ☃ = this.i;
    int ☃ = this.r;
    b(☃, ☃, 0, 0, this.f, this.g);
    
    a(☃ + 51, ☃ + 75, 30, ☃ + 51 - this.u, ☃ + 75 - 50 - this.v, this.j.h);
  }
  
  public static void a(int ☃, int ☃, int ☃, float ☃, float ☃, sa ☃)
  {
    bni.h();
    
    bni.G();
    bni.c(☃, ☃, 50.0F);
    bni.b(-☃, ☃, ☃);
    bni.b(180.0F, 0.0F, 0.0F, 1.0F);
    
    float ☃ = ☃.aM;
    float ☃ = ☃.v;
    float ☃ = ☃.w;
    float ☃ = ☃.aP;
    float ☃ = ☃.aO;
    
    bni.b(135.0F, 0.0F, 1.0F, 0.0F);
    bcd.b();
    bni.b(-135.0F, 0.0F, 1.0F, 0.0F);
    
    bni.b(-(float)Math.atan(☃ / 40.0F) * 20.0F, 1.0F, 0.0F, 0.0F);
    
    ☃.aM = ((float)Math.atan(☃ / 40.0F) * 20.0F);
    ☃.v = ((float)Math.atan(☃ / 40.0F) * 40.0F);
    ☃.w = (-(float)Math.atan(☃ / 40.0F) * 20.0F);
    ☃.aO = ☃.v;
    ☃.aP = ☃.v;
    
    bni.c(0.0F, 0.0F, 0.0F);
    
    brm ☃ = bcf.z().ac();
    ☃.a(180.0F);
    ☃.a(false);
    ☃.a(☃, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
    ☃.a(true);
    
    ☃.aM = ☃;
    ☃.v = ☃;
    ☃.w = ☃;
    ☃.aP = ☃;
    ☃.aO = ☃;
    bni.H();
    bcd.a();
    bni.E();
    
    bni.g(bzg.r);
    bni.z();
    bni.g(bzg.q);
  }
  
  protected void a(bcz ☃)
  {
    if (☃.k == 0) {
      this.j.a(new bfm(this, this.j.h.G()));
    }
    if (☃.k == 1) {
      this.j.a(new bfn(this, this.j.h.G()));
    }
  }
}
