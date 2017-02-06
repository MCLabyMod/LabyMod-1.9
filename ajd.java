import java.util.List;
import java.util.Random;

public class ajd
  extends aig
{
  private static final auu y = new auu();
  private static final avd z = new avd(false);
  private static final aup A = new aup(false, false);
  private static final aup B = new aup(false, true);
  private static final ats C = new ats(aju.Y, 0);
  private ajd.a D;
  
  public ajd(ajd.a ☃, aig.a ☃)
  {
    super(☃);
    this.D = ☃;
    
    this.v.add(new aig.c(wj.class, 8, 4, 4));
    this.v.add(new aig.c(wd.class, 4, 2, 3));
    
    this.t.z = 10;
    if ((☃ == ajd.a.b) || (☃ == ajd.a.c))
    {
      this.t.B = 7;
      this.t.C = 1;
      this.t.D = 3;
    }
    else
    {
      this.t.B = 1;
      this.t.D = 1;
    }
  }
  
  public atp a(Random ☃)
  {
    if (((this.D == ajd.a.b) || (this.D == ajd.a.c)) && (☃.nextInt(3) == 0))
    {
      if ((this.D == ajd.a.c) || (☃.nextInt(13) == 0)) {
        return B;
      }
      return A;
    }
    if (☃.nextInt(3) == 0) {
      return y;
    }
    return z;
  }
  
  public aud b(Random ☃)
  {
    if (☃.nextInt(5) > 0) {
      return new avf(apc.a.c);
    }
    return new avf(apc.a.b);
  }
  
  public void a(aht ☃, Random ☃, cj ☃)
  {
    if ((this.D == ajd.a.b) || (this.D == ajd.a.c))
    {
      int ☃ = ☃.nextInt(3);
      for (int ☃ = 0; ☃ < ☃; ☃++)
      {
        int ☃ = ☃.nextInt(16) + 8;
        int ☃ = ☃.nextInt(16) + 8;
        cj ☃ = ☃.l(☃.a(☃, 0, ☃));
        C.b(☃, ☃, ☃);
      }
    }
    m.a(akw.b.d);
    for (int ☃ = 0; ☃ < 7; ☃++)
    {
      int ☃ = ☃.nextInt(16) + 8;
      int ☃ = ☃.nextInt(16) + 8;
      int ☃ = ☃.nextInt(☃.l(☃.a(☃, 0, ☃)).q() + 32);
      m.b(☃, ☃, ☃.a(☃, ☃, ☃));
    }
    super.a(☃, ☃, ☃);
  }
  
  public void a(aht ☃, Random ☃, atf ☃, int ☃, int ☃, double ☃)
  {
    if ((this.D == ajd.a.b) || (this.D == ajd.a.c))
    {
      this.r = aju.c.u();
      this.s = aju.d.u();
      if (☃ > 1.75D) {
        this.r = aju.d.u().a(akt.a, akt.a.b);
      } else if (☃ > -0.95D) {
        this.r = aju.d.u().a(akt.a, akt.a.c);
      }
    }
    b(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public static enum a
  {
    private a() {}
  }
}
