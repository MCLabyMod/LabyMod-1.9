import java.util.Random;

public class ain
  extends aig
{
  private aud y = new aut(aju.be.u().a(amt.a, amt.a.a), 9);
  private avd z = new avd(false);
  private final ain.a A;
  
  protected ain(ain.a ☃, aig.a ☃)
  {
    super(☃);
    if (☃ == ain.a.b) {
      this.t.z = 3;
    }
    this.A = ☃;
  }
  
  public atp a(Random ☃)
  {
    if (☃.nextInt(3) > 0) {
      return this.z;
    }
    return super.a(☃);
  }
  
  public void a(aht ☃, Random ☃, cj ☃)
  {
    super.a(☃, ☃, ☃);
    
    int ☃ = 3 + ☃.nextInt(6);
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      int ☃ = ☃.nextInt(16);
      int ☃ = ☃.nextInt(28) + 4;
      int ☃ = ☃.nextInt(16);
      
      cj ☃ = ☃.a(☃, ☃, ☃);
      if (☃.o(☃).t() == aju.b) {
        ☃.a(☃, aju.bP.u(), 2);
      }
    }
    for (int ☃ = 0; ☃ < 7; ☃++)
    {
      int ☃ = ☃.nextInt(16);
      int ☃ = ☃.nextInt(64);
      int ☃ = ☃.nextInt(16);
      this.y.b(☃, ☃, ☃.a(☃, ☃, ☃));
    }
  }
  
  public void a(aht ☃, Random ☃, atf ☃, int ☃, int ☃, double ☃)
  {
    this.r = aju.c.u();
    this.s = aju.d.u();
    if (((☃ < -1.0D) || (☃ > 2.0D)) && (this.A == ain.a.c))
    {
      this.r = aju.n.u();
      this.s = aju.n.u();
    }
    else if ((☃ > 1.0D) && (this.A != ain.a.b))
    {
      this.r = aju.b.u();
      this.s = aju.b.u();
    }
    b(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public static enum a
  {
    private a() {}
  }
}
