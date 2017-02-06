import java.util.Random;

public class blr
  extends blx
{
  private int a;
  private int G;
  
  protected blr(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃, 0.0D, 0.0D, 0.0D);
    this.G = 8;
  }
  
  public void a(bmz ☃, rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃) {}
  
  public void a()
  {
    for (int ☃ = 0; ☃ < 6; ☃++)
    {
      double ☃ = this.f + (this.p.nextDouble() - this.p.nextDouble()) * 4.0D;
      double ☃ = this.g + (this.p.nextDouble() - this.p.nextDouble()) * 4.0D;
      double ☃ = this.h + (this.p.nextDouble() - this.p.nextDouble()) * 4.0D;
      this.b.a(cy.b, ☃, ☃, ☃, this.a / this.G, 0.0D, 0.0D, new int[0]);
    }
    this.a += 1;
    if (this.a == this.G) {
      i();
    }
  }
  
  public int b()
  {
    return 1;
  }
  
  public static class a
    implements blz
  {
    public blx a(int ☃, aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
    {
      return new blr(☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
}
