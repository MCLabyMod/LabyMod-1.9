import java.util.Random;

public class auw
  extends aud
{
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    for (int ☃ = 0; ☃ < 20; ☃++)
    {
      cj ☃ = ☃.a(☃.nextInt(4) - ☃.nextInt(4), 0, ☃.nextInt(4) - ☃.nextInt(4));
      if (☃.d(☃))
      {
        cj ☃ = ☃.b();
        if ((☃.o(☃.e()).a() == axe.h) || (☃.o(☃.f()).a() == axe.h) || (☃.o(☃.c()).a() == axe.h) || (☃.o(☃.d()).a() == axe.h))
        {
          int ☃ = 2 + ☃.nextInt(☃.nextInt(3) + 1);
          for (int ☃ = 0; ☃ < ☃; ☃++) {
            if (aju.aM.b(☃, ☃)) {
              ☃.a(☃.b(☃), aju.aM.u(), 2);
            }
          }
        }
      }
    }
    return true;
  }
}
