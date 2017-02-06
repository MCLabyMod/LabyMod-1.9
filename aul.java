import java.util.Random;

public class aul
  extends aud
{
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    while ((☃.d(☃)) && (☃.q() > 2)) {
      ☃ = ☃.b();
    }
    if (☃.o(☃).t() != aju.aJ) {
      return false;
    }
    ☃ = ☃.b(☃.nextInt(4));
    
    int ☃ = ☃.nextInt(4) + 7;
    int ☃ = ☃ / 4 + ☃.nextInt(2);
    if ((☃ > 1) && (☃.nextInt(60) == 0)) {
      ☃ = ☃.b(10 + ☃.nextInt(30));
    }
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      float ☃ = (1.0F - ☃ / ☃) * ☃;
      int ☃ = on.f(☃);
      for (int ☃ = -☃; ☃ <= ☃; ☃++)
      {
        float ☃ = on.a(☃) - 0.25F;
        for (int ☃ = -☃; ☃ <= ☃; ☃++)
        {
          float ☃ = on.a(☃) - 0.25F;
          if (((☃ == 0) && (☃ == 0)) || (☃ * ☃ + ☃ * ☃ <= ☃ * ☃)) {
            if (((☃ != -☃) && (☃ != ☃) && (☃ != -☃) && (☃ != ☃)) || 
              (☃.nextFloat() <= 0.75F))
            {
              arc ☃ = ☃.o(☃.a(☃, ☃, ☃));
              ajt ☃ = ☃.t();
              if ((☃.a() == axe.a) || (☃ == aju.d) || (☃ == aju.aJ) || (☃ == aju.aI)) {
                a(☃, ☃.a(☃, ☃, ☃), aju.cB.u());
              }
              if ((☃ != 0) && (☃ > 1))
              {
                ☃ = ☃.o(☃.a(☃, -☃, ☃));
                ☃ = ☃.t();
                if ((☃.a() == axe.a) || (☃ == aju.d) || (☃ == aju.aJ) || (☃ == aju.aI)) {
                  a(☃, ☃.a(☃, -☃, ☃), aju.cB.u());
                }
              }
            }
          }
        }
      }
    }
    int ☃ = ☃ - 1;
    if (☃ < 0) {
      ☃ = 0;
    } else if (☃ > 1) {
      ☃ = 1;
    }
    for (int ☃ = -☃; ☃ <= ☃; ☃++) {
      for (int ☃ = -☃; ☃ <= ☃; ☃++)
      {
        cj ☃ = ☃.a(☃, -1, ☃);
        int ☃ = 50;
        if ((Math.abs(☃) == 1) && (Math.abs(☃) == 1)) {
          ☃ = ☃.nextInt(5);
        }
        while (☃.q() > 50)
        {
          arc ☃ = ☃.o(☃);
          ajt ☃ = ☃.t();
          if ((☃.a() != axe.a) && (☃ != aju.d) && (☃ != aju.aJ) && (☃ != aju.aI) && (☃ != aju.cB)) {
            break;
          }
          a(☃, ☃, aju.cB.u());
          
          ☃ = ☃.b();
          ☃--;
          if (☃ <= 0)
          {
            ☃ = ☃.c(☃.nextInt(5) + 1);
            ☃ = ☃.nextInt(5);
          }
        }
      }
    }
    return true;
  }
}
