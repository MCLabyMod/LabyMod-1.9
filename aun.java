import java.util.Random;

public class aun
  extends aud
{
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    if (!☃.d(☃)) {
      return false;
    }
    if (☃.o(☃.a()).t() != aju.aV) {
      return false;
    }
    ☃.a(☃, aju.aX.u(), 2);
    for (int ☃ = 0; ☃ < 1500; ☃++)
    {
      cj ☃ = ☃.a(☃.nextInt(8) - ☃.nextInt(8), -☃.nextInt(12), ☃.nextInt(8) - ☃.nextInt(8));
      if (☃.o(☃).a() == axe.a)
      {
        int ☃ = 0;
        for (cq ☃ : cq.values())
        {
          if (☃.o(☃.a(☃)).t() == aju.aX) {
            ☃++;
          }
          if (☃ > 1) {
            break;
          }
        }
        if (☃ == 1) {
          ☃.a(☃, aju.aX.u(), 2);
        }
      }
    }
    return true;
  }
}
