import java.util.Random;

public class bmo
  extends blx
{
  protected bmo(aht ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D);
    this.i *= 0.30000001192092896D;
    this.j = (Math.random() * 0.20000000298023224D + 0.10000000149011612D);
    this.k *= 0.30000001192092896D;
    
    this.y = 1.0F;
    this.z = 1.0F;
    this.A = 1.0F;
    b(19 + this.p.nextInt(4));
    a(0.01F, 0.01F);
    this.x = 0.06F;
    
    this.v = ((int)(8.0D / (Math.random() * 0.8D + 0.2D)));
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
    if (this.v-- <= 0) {
      i();
    }
    if (this.l)
    {
      if (Math.random() < 0.5D) {
        i();
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
        ☃ = 1.0F - amo.e(((Integer)☃.c(amo.b)).intValue());
      } else {
        ☃ = ☃.c(this.b, ☃).e;
      }
      double ☃ = on.c(this.g) + ☃;
      if (this.g < ☃) {
        i();
      }
    }
  }
  
  public static class a
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      return new bmo(☃, ☃, ☃, ☃);
    }
  }
}
