import java.util.Random;

public class bmm
  extends blx
{
  private rr a;
  private int G;
  private int H;
  private cy I;
  
  public bmm(aht ☃, rr ☃, cy ☃)
  {
    super(☃, ☃.p, ☃.bl().b + ☃.H / 2.0F, ☃.r, ☃.s, ☃.t, ☃.u);
    this.a = ☃;
    this.H = 3;
    this.I = ☃;
    a();
  }
  
  public void a(bmz ☃, rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃) {}
  
  public void a()
  {
    for (int ☃ = 0; ☃ < 16; ☃++)
    {
      double ☃ = this.p.nextFloat() * 2.0F - 1.0F;
      double ☃ = this.p.nextFloat() * 2.0F - 1.0F;
      double ☃ = this.p.nextFloat() * 2.0F - 1.0F;
      if (☃ * ☃ + ☃ * ☃ + ☃ * ☃ <= 1.0D)
      {
        double ☃ = this.a.p + ☃ * this.a.G / 4.0D;
        double ☃ = this.a.bl().b + this.a.H / 2.0F + ☃ * this.a.H / 4.0D;
        double ☃ = this.a.r + ☃ * this.a.G / 4.0D;
        this.b.a(this.I, false, ☃, ☃, ☃, ☃, ☃ + 0.2D, ☃, new int[0]);
      }
    }
    this.G += 1;
    if (this.G >= this.H) {
      i();
    }
  }
  
  public int b()
  {
    return 3;
  }
}
