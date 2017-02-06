public class ble
  extends blx
{
  protected ble(aht ☃, double ☃, double ☃, double ☃, ado ☃)
  {
    this(☃, ☃, ☃, ☃, ☃, 0);
  }
  
  protected ble(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, ado ☃, int ☃)
  {
    this(☃, ☃, ☃, ☃, ☃, ☃);
    this.i *= 0.10000000149011612D;
    this.j *= 0.10000000149011612D;
    this.k *= 0.10000000149011612D;
    this.i += ☃;
    this.j += ☃;
    this.k += ☃;
  }
  
  protected ble(aht ☃, double ☃, double ☃, double ☃, ado ☃, int ☃)
  {
    super(☃, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D);
    a(bcf.z().ad().a().a(☃, ☃));
    this.y = (this.z = this.A = 1.0F);
    this.x = aju.aJ.w;
    this.w /= 2.0F;
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
  
  public static class a
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      int ☃ = ☃.length > 1 ? ☃[1] : 0;
      return new ble(☃, ☃, ☃, ☃, ☃, ☃, ☃, ado.c(☃[0]), ☃);
    }
  }
  
  public static class b
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      return new ble(☃, ☃, ☃, ☃, ads.aT);
    }
  }
  
  public static class c
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      return new ble(☃, ☃, ☃, ☃, ads.aF);
    }
  }
}
