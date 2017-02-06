import java.util.Random;

public class auz
  extends aud
{
  private ajt a;
  private int b;
  
  public auz(ajt ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    if (☃.o(☃).a() != axe.h) {
      return false;
    }
    int ☃ = ☃.nextInt(this.b - 2) + 2;
    int ☃ = 2;
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
            if ((☃ == aju.d) || (☃ == aju.c)) {
              ☃.a(☃, this.a.u(), 2);
            }
          }
        }
      }
    }
    return true;
  }
}
