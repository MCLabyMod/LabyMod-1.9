public class bld
  extends blx
{
  protected bld(aht ☃, double ☃, double ☃, double ☃, ado ☃)
  {
    super(☃, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D);
    a(bcf.z().ad().a().a(☃));
    this.y = (this.z = this.A = 1.0F);
    this.i = (this.j = this.k = 0.0D);
    this.x = 0.0F;
    this.v = 80;
  }
  
  public int b()
  {
    return 1;
  }
  
  public void a(bmz ☃, rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    float ☃ = this.C.e();
    float ☃ = this.C.f();
    float ☃ = this.C.g();
    float ☃ = this.C.h();
    float ☃ = 0.5F;
    
    float ☃ = (float)(this.c + (this.f - this.c) * ☃ - D);
    float ☃ = (float)(this.d + (this.g - this.d) * ☃ - E);
    float ☃ = (float)(this.e + (this.h - this.e) * ☃ - F);
    
    int ☃ = a(☃);
    int ☃ = ☃ >> 16 & 0xFFFF;
    int ☃ = ☃ & 0xFFFF;
    
    ☃.b(☃ - ☃ * 0.5F - ☃ * 0.5F, ☃ - ☃ * 0.5F, ☃ - ☃ * 0.5F - ☃ * 0.5F).a(☃, ☃).a(this.y, this.z, this.A, 1.0F).a(☃, ☃).d();
    ☃.b(☃ - ☃ * 0.5F + ☃ * 0.5F, ☃ + ☃ * 0.5F, ☃ - ☃ * 0.5F + ☃ * 0.5F).a(☃, ☃).a(this.y, this.z, this.A, 1.0F).a(☃, ☃).d();
    ☃.b(☃ + ☃ * 0.5F + ☃ * 0.5F, ☃ + ☃ * 0.5F, ☃ + ☃ * 0.5F + ☃ * 0.5F).a(☃, ☃).a(this.y, this.z, this.A, 1.0F).a(☃, ☃).d();
    ☃.b(☃ + ☃ * 0.5F - ☃ * 0.5F, ☃ - ☃ * 0.5F, ☃ + ☃ * 0.5F - ☃ * 0.5F).a(☃, ☃).a(this.y, this.z, this.A, 1.0F).a(☃, ☃).d();
  }
  
  public static class a
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      return new bld(☃, ☃, ☃, ☃, ado.a(aju.cv));
    }
  }
}
