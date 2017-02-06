public class blv
  extends blx
{
  private sa a;
  
  protected blv(aht ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D);
    this.y = (this.z = this.A = 1.0F);
    this.i = (this.j = this.k = 0.0D);
    this.x = 0.0F;
    this.v = 30;
  }
  
  public int b()
  {
    return 3;
  }
  
  public void a()
  {
    super.a();
    if (this.a == null)
    {
      yo ☃ = new yo(this.b);
      ☃.dc();
      this.a = ☃;
    }
  }
  
  public void a(bmz ☃, rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    if (this.a == null) {
      return;
    }
    brm ☃ = bcf.z().ac();
    
    ☃.a(blx.D, blx.E, blx.F);
    
    float ☃ = 0.42553192F;
    float ☃ = (this.u + ☃) / this.v;
    
    bni.a(true);
    bni.m();
    bni.k();
    bni.a(bni.r.l, bni.l.j);
    float ☃ = 240.0F;
    bzg.a(bzg.r, ☃, ☃);
    
    bni.G();
    float ☃ = 0.05F + 0.5F * on.a(☃ * 3.1415927F);
    bni.c(1.0F, 1.0F, 1.0F, ☃);
    
    bni.c(0.0F, 1.8F, 0.0F);
    bni.b(180.0F - ☃.v, 0.0F, 1.0F, 0.0F);
    bni.b(60.0F - 150.0F * ☃ - ☃.w, 1.0F, 0.0F, 0.0F);
    bni.c(0.0F, -0.4F, -1.5F);
    bni.b(☃, ☃, ☃);
    this.a.v = (this.a.x = 0.0F);
    this.a.aO = (this.a.aP = 0.0F);
    ☃.a(this.a, 0.0D, 0.0D, 0.0D, 0.0F, ☃, false);
    bni.H();
    
    bni.k();
  }
  
  public static class a
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      return new blv(☃, ☃, ☃, ☃);
    }
  }
}
