public class bmn
  extends blx
{
  protected bmn(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D);
    this.i *= 0.30000001192092896D;
    this.j = (Math.random() * 0.20000000298023224D + 0.10000000149011612D);
    this.k *= 0.30000001192092896D;
    
    this.y = 1.0F;
    this.z = 1.0F;
    this.A = 1.0F;
    b(19);
    a(0.01F, 0.01F);
    
    this.v = ((int)(8.0D / (Math.random() * 0.8D + 0.2D)));
    
    this.x = 0.0F;
    this.i = ☃;
    this.j = ☃;
    this.k = ☃;
  }
  
  public void a()
  {
    this.c = this.f;
    this.d = this.g;
    this.e = this.h;
    
    this.j -= this.x;
    a(this.i, this.j, this.k);
    this.i *= 0.9800000190734863D;
    this.j *= 0.9800000190734863D;
    this.k *= 0.9800000190734863D;
    
    int ☃ = 60 - this.v;
    float ☃ = ☃ * 0.001F;
    a(☃, ☃);
    b(19 + ☃ % 4);
    if (this.v-- <= 0) {
      i();
    }
  }
  
  public static class a
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      return new bmn(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
}
