import java.util.Random;

public class auc
  extends aud
{
  public static final cj a = cj.a;
  public static final cj b = new cj(a.p() - 4 & 0xFFFFFFF0, 0, a.r() - 4 & 0xFFFFFFF0);
  private final boolean c;
  
  public auc(boolean ☃)
  {
    this.c = ☃;
  }
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    for (cj.a ☃ : cj.b(new cj(☃.p() - 4, ☃.q() - 1, ☃.r() - 4), new cj(☃.p() + 4, ☃.q() + 32, ☃.r() + 4)))
    {
      double ☃ = ☃.f(☃.p(), ☃.q(), ☃.r());
      if (☃ <= 3.5D) {
        if (☃.q() < ☃.q())
        {
          if (☃ <= 2.5D) {
            a(☃, ☃, aju.h.u());
          } else if (☃.q() < ☃.q()) {
            a(☃, ☃, aju.bH.u());
          }
        }
        else if (☃.q() > ☃.q()) {
          a(☃, ☃, aju.a.u());
        } else if (☃ > 2.5D) {
          a(☃, ☃, aju.h.u());
        } else if (this.c) {
          a(☃, new cj(☃), aju.bF.u());
        } else {
          a(☃, new cj(☃), aju.a.u());
        }
      }
    }
    for (int ☃ = 0; ☃ < 4; ☃++) {
      a(☃, ☃.b(☃), aju.h.u());
    }
    cj ☃ = ☃.b(2);
    for (cq ☃ : cq.c.a) {
      a(☃, ☃.a(☃), aju.aa.u().a(apf.a, ☃));
    }
    return true;
  }
}
