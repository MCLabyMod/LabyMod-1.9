public class bml
  extends blx
{
  private arc a;
  private cj G;
  
  protected bml(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, arc ☃)
  {
    super(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    this.a = ☃;
    a(bcf.z().ab().a().a(☃));
    this.x = ☃.t().w;
    this.y = (this.z = this.A = 0.6F);
    this.w /= 2.0F;
  }
  
  public bml a(cj ☃)
  {
    this.G = ☃;
    if (this.a.t() == aju.c) {
      return this;
    }
    b(☃);
    return this;
  }
  
  public bml m()
  {
    this.G = new cj(this.f, this.g, this.h);
    ajt ☃ = this.a.t();
    if (☃ == aju.c) {
      return this;
    }
    b(null);
    return this;
  }
  
  protected void b(cj ☃)
  {
    int ☃ = bcf.z().al().a(this.a, this.b, ☃, 0);
    this.y *= (☃ >> 16 & 0xFF) / 255.0F;
    this.z *= (☃ >> 8 & 0xFF) / 255.0F;
    this.A *= (☃ & 0xFF) / 255.0F;
  }
  
  public int b()
  {
    return 1;
  }
  
  public void a(bmz ☃, rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    float ☃ = (this.q + this.s / 4.0F) / 16.0F;
    float ☃ = ☃ + 0.015609375F;
    float ☃ = (this.r + this.t / 4.0F) / 16.0F;
    float ☃ = ☃ + 0.015609375F;
    float ☃ = 0.1F * this.w;
    if (this.C != null)
    {
      ☃ = this.C.a(this.s / 4.0F * 16.0F);
      ☃ = this.C.a((this.s + 1.0F) / 4.0F * 16.0F);
      ☃ = this.C.b(this.t / 4.0F * 16.0F);
      ☃ = this.C.b((this.t + 1.0F) / 4.0F * 16.0F);
    }
    float ☃ = (float)(this.c + (this.f - this.c) * ☃ - D);
    float ☃ = (float)(this.d + (this.g - this.d) * ☃ - E);
    float ☃ = (float)(this.e + (this.h - this.e) * ☃ - F);
    
    int ☃ = a(☃);
    int ☃ = ☃ >> 16 & 0xFFFF;
    int ☃ = ☃ & 0xFFFF;
    
    ☃.b(☃ - ☃ * ☃ - ☃ * ☃, ☃ - ☃ * ☃, ☃ - ☃ * ☃ - ☃ * ☃).a(☃, ☃).a(this.y, this.z, this.A, 1.0F).a(☃, ☃).d();
    ☃.b(☃ - ☃ * ☃ + ☃ * ☃, ☃ + ☃ * ☃, ☃ - ☃ * ☃ + ☃ * ☃).a(☃, ☃).a(this.y, this.z, this.A, 1.0F).a(☃, ☃).d();
    ☃.b(☃ + ☃ * ☃ + ☃ * ☃, ☃ + ☃ * ☃, ☃ + ☃ * ☃ + ☃ * ☃).a(☃, ☃).a(this.y, this.z, this.A, 1.0F).a(☃, ☃).d();
    ☃.b(☃ + ☃ * ☃ - ☃ * ☃, ☃ - ☃ * ☃, ☃ + ☃ * ☃ - ☃ * ☃).a(☃, ☃).a(this.y, this.z, this.A, 1.0F).a(☃, ☃).d();
  }
  
  public int a(float ☃)
  {
    int ☃ = super.a(☃);
    int ☃ = 0;
    if (this.b.e(this.G)) {
      ☃ = this.b.b(this.G, 0);
    }
    return ☃ == 0 ? ☃ : ☃;
  }
  
  public static class a
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      return new bml(☃, ☃, ☃, ☃, ☃, ☃, ☃, ajt.c(☃[0])).m();
    }
  }
}
