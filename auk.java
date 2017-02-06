import java.util.Random;

public class auk
  extends aud
{
  private ajt a;
  private int b;
  
  public auk(int ☃)
  {
    this.a = aju.cB;
    this.b = ☃;
  }
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    while ((☃.d(☃)) && (☃.q() > 2)) {
      ☃ = ☃.b();
    }
    if (☃.o(☃).t() != aju.aJ) {
      return false;
    }
    int ☃ = ☃.nextInt(this.b - 2) + 2;
    int ☃ = 1;
    for (int ☃ = ☃.p() - ☃; ☃ <= ☃.p() + ☃; ☃++) {
      for (int ☃ = ☃.r() - ☃; ☃ <= ☃.r() + ☃; ☃++)
      {
        int ☃ = ☃ - ☃.p();
        int ☃ = ☃ - ☃.r();
        if (☃ * ☃ + ☃ * ☃ <= ☃ * ☃) {
          for (int ☃ = ☃.q() - ☃; ☃ <= ☃.q() + ☃; ☃++)
          {
            cj ☃ = new cj(☃, ☃, ☃);
            ajt ☃ = ☃.o(☃).t();
            if ((☃ == aju.d) || (☃ == aju.aJ) || (☃ == aju.aI)) {
              ☃.a(☃, this.a.u(), 2);
            }
          }
        }
      }
    }
    return true;
  }
}
