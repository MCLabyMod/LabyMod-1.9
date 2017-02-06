public class blo
  extends blx
{
  private static final kk a = new kk("textures/particle/footprint.png");
  private int G;
  private int H;
  private bvi I;
  
  protected blo(bvi ☃, aht ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D);
    this.I = ☃;
    this.i = (this.j = this.k = 0.0D);
    this.H = 200;
  }
  
  public void a(bmz ☃, rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    float ☃ = (this.G + ☃) / this.H;
    ☃ *= ☃;
    
    float ☃ = 2.0F - ☃ * 2.0F;
    if (☃ > 1.0F) {
      ☃ = 1.0F;
    }
    ☃ *= 0.2F;
    
    bni.g();
    float ☃ = 0.125F;
    
    float ☃ = (float)(this.f - D);
    float ☃ = (float)(this.g - E);
    float ☃ = (float)(this.h - F);
    
    float ☃ = this.b.n(new cj(this.f, this.g, this.h));
    
    this.I.a(a);
    bni.m();
    bni.a(bni.r.l, bni.l.j);
    
    ☃.a(7, bvp.i);
    ☃.b(☃ - 0.125F, ☃, ☃ + 0.125F).a(0.0D, 1.0D).a(☃, ☃, ☃, ☃).d();
    ☃.b(☃ + 0.125F, ☃, ☃ + 0.125F).a(1.0D, 1.0D).a(☃, ☃, ☃, ☃).d();
    ☃.b(☃ + 0.125F, ☃, ☃ - 0.125F).a(1.0D, 0.0D).a(☃, ☃, ☃, ☃).d();
    ☃.b(☃ - 0.125F, ☃, ☃ - 0.125F).a(0.0D, 0.0D).a(☃, ☃, ☃, ☃).d();
    bnu.a().b();
    
    bni.l();
    bni.f();
  }
  
  public void a()
  {
    this.G += 1;
    if (this.G == this.H) {
      i();
    }
  }
  
  public int b()
  {
    return 3;
  }
  
  public static class a
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      return new blo(bcf.z().N(), ☃, ☃, ☃, ☃);
    }
  }
}
