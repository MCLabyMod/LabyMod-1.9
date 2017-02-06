public class bli
  extends blx
{
  private axe a;
  private int G;
  
  protected bli(aht ☃, double ☃, double ☃, double ☃, axe ☃)
  {
    super(☃, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D);
    this.i = (this.j = this.k = 0.0D);
    if (☃ == axe.h)
    {
      this.y = 0.0F;
      this.z = 0.0F;
      this.A = 1.0F;
    }
    else
    {
      this.y = 1.0F;
      this.z = 0.0F;
      this.A = 0.0F;
    }
    b(113);
    a(0.01F, 0.01F);
    this.x = 0.06F;
    this.a = ☃;
    this.G = 40;
    
    this.v = ((int)(64.0D / (Math.random() * 0.8D + 0.2D)));
    this.i = (this.j = this.k = 0.0D);
  }
  
  public int a(float ☃)
  {
    if (this.a == axe.h) {
      return super.a(☃);
    }
    return 257;
  }
  
  public void a()
  {
    this.c = this.f;
    this.d = this.g;
    this.e = this.h;
    if (this.a == axe.h)
    {
      this.y = 0.2F;
      this.z = 0.3F;
      this.A = 1.0F;
    }
    else
    {
      this.y = 1.0F;
      this.z = (16.0F / (40 - this.G + 16));
      this.A = (4.0F / (40 - this.G + 8));
    }
    this.j -= this.x;
    if (this.G-- > 0)
    {
      this.i *= 0.02D;
      this.j *= 0.02D;
      this.k *= 0.02D;
      b(113);
    }
    else
    {
      b(112);
    }
    a(this.i, this.j, this.k);
    this.i *= 0.9800000190734863D;
    this.j *= 0.9800000190734863D;
    this.k *= 0.9800000190734863D;
    if (this.v-- <= 0) {
      i();
    }
    if (this.l)
    {
      if (this.a == axe.h)
      {
        i();
        this.b.a(cy.f, this.f, this.g, this.h, 0.0D, 0.0D, 0.0D, new int[0]);
      }
      else
      {
        b(114);
      }
      this.i *= 0.699999988079071D;
      this.k *= 0.699999988079071D;
    }
    cj ☃ = new cj(this.f, this.g, this.h);
    arc ☃ = this.b.o(☃);
    axe ☃ = ☃.a();
    if ((☃.d()) || (☃.a()))
    {
      double ☃ = 0.0D;
      if ((☃.t() instanceof amo)) {
        ☃ = amo.e(((Integer)☃.c(amo.b)).intValue());
      }
      double ☃ = on.c(this.g) + 1 - ☃;
      if (this.g < ☃) {
        i();
      }
    }
  }
  
  public static class b
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      return new bli(☃, ☃, ☃, ☃, axe.h);
    }
  }
  
  public static class a
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      return new bli(☃, ☃, ☃, ☃, axe.i);
    }
  }
}
