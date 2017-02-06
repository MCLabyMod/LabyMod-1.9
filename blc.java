import java.util.Random;

public class blc
  extends blx
{
  private static final kk a = new kk("textures/entity/sweep.png");
  private static final bvr G = new bvr().a(bvp.m).a(bvp.o).a(bvp.n).a(bvp.p).a(bvp.q).a(bvp.r);
  private int H;
  private int I;
  private bvi J;
  private float K;
  
  protected blc(bvi ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D);
    this.J = ☃;
    this.I = 4;
    this.y = (this.z = this.A = this.p.nextFloat() * 0.6F + 0.4F);
    this.K = (1.0F - (float)☃ * 0.5F);
  }
  
  public void a(bmz ☃, rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    int ☃ = (int)((this.H + ☃) * 3.0F / this.I);
    if (☃ > 7) {
      return;
    }
    this.J.a(a);
    
    float ☃ = ☃ % 4 / 4.0F;
    float ☃ = ☃ + 0.24975F;
    float ☃ = ☃ / 2 / 2.0F;
    float ☃ = ☃ + 0.4995F;
    
    float ☃ = 1.0F * this.K;
    
    float ☃ = (float)(this.c + (this.f - this.c) * ☃ - D);
    float ☃ = (float)(this.d + (this.g - this.d) * ☃ - E);
    float ☃ = (float)(this.e + (this.h - this.e) * ☃ - F);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bni.g();
    bcd.a();
    
    ☃.a(7, G);
    ☃.b(☃ - ☃ * ☃ - ☃ * ☃, ☃ - ☃ * ☃ * 0.5F, ☃ - ☃ * ☃ - ☃ * ☃).a(☃, ☃).a(this.y, this.z, this.A, 1.0F).a(0, 240).c(0.0F, 1.0F, 0.0F).d();
    ☃.b(☃ - ☃ * ☃ + ☃ * ☃, ☃ + ☃ * ☃ * 0.5F, ☃ - ☃ * ☃ + ☃ * ☃).a(☃, ☃).a(this.y, this.z, this.A, 1.0F).a(0, 240).c(0.0F, 1.0F, 0.0F).d();
    ☃.b(☃ + ☃ * ☃ + ☃ * ☃, ☃ + ☃ * ☃ * 0.5F, ☃ + ☃ * ☃ + ☃ * ☃).a(☃, ☃).a(this.y, this.z, this.A, 1.0F).a(0, 240).c(0.0F, 1.0F, 0.0F).d();
    ☃.b(☃ + ☃ * ☃ - ☃ * ☃, ☃ - ☃ * ☃ * 0.5F, ☃ + ☃ * ☃ - ☃ * ☃).a(☃, ☃).a(this.y, this.z, this.A, 1.0F).a(0, 240).c(0.0F, 1.0F, 0.0F).d();
    bnu.a().b();
    
    bni.f();
  }
  
  public int a(float ☃)
  {
    return 61680;
  }
  
  public void a()
  {
    this.c = this.f;
    this.d = this.g;
    this.e = this.h;
    this.H += 1;
    if (this.H == this.I) {
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
      return new blc(bcf.z().N(), ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
}
