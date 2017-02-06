import java.util.Random;

public class ats
  extends aud
{
  private final ajt a;
  private final int b;
  
  public ats(ajt ☃, int ☃)
  {
    super(false);
    this.a = ☃;
    this.b = ☃;
  }
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    while (☃.q() > 3)
    {
      if (!☃.d(☃.b()))
      {
        ajt ☃ = ☃.o(☃.b()).t();
        if ((☃ == aju.c) || (☃ == aju.d) || (☃ == aju.b)) {
          break;
        }
      }
      ☃ = ☃.b();
    }
    if (☃.q() <= 3) {
      return false;
    }
    int ☃ = this.b;
    int ☃ = 0;
    while ((☃ >= 0) && (☃ < 3))
    {
      int ☃ = ☃ + ☃.nextInt(2);
      int ☃ = ☃ + ☃.nextInt(2);
      int ☃ = ☃ + ☃.nextInt(2);
      float ☃ = (☃ + ☃ + ☃) * 0.333F + 0.5F;
      for (cj ☃ : cj.a(☃.a(-☃, -☃, -☃), ☃.a(☃, ☃, ☃))) {
        if (☃.k(☃) <= ☃ * ☃) {
          ☃.a(☃, this.a.u(), 4);
        }
      }
      ☃ = ☃.a(-(☃ + 1) + ☃.nextInt(2 + ☃ * 2), 0 - ☃.nextInt(2), -(☃ + 1) + ☃.nextInt(2 + ☃ * 2));
      ☃++;
    }
    return true;
  }
}
