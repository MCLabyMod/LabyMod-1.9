import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class aus
  extends aud
{
  private static final Logger a = ;
  private static final String[] b = { "Skeleton", "Zombie", "Zombie", "Spider" };
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    int ☃ = 3;
    int ☃ = ☃.nextInt(2) + 2;
    int ☃ = -☃ - 1;
    int ☃ = ☃ + 1;
    
    int ☃ = -1;
    int ☃ = 4;
    
    int ☃ = ☃.nextInt(2) + 2;
    int ☃ = -☃ - 1;
    int ☃ = ☃ + 1;
    
    int ☃ = 0;
    for (int ☃ = ☃; ☃ <= ☃; ☃++) {
      for (int ☃ = -1; ☃ <= 4; ☃++) {
        for (int ☃ = ☃; ☃ <= ☃; ☃++)
        {
          cj ☃ = ☃.a(☃, ☃, ☃);
          axe ☃ = ☃.o(☃).a();
          boolean ☃ = ☃.a();
          if ((☃ == -1) && (!☃)) {
            return false;
          }
          if ((☃ == 4) && (!☃)) {
            return false;
          }
          if (((☃ == ☃) || (☃ == ☃) || (☃ == ☃) || (☃ == ☃)) && 
            (☃ == 0) && (☃.d(☃)) && (☃.d(☃.a()))) {
            ☃++;
          }
        }
      }
    }
    if ((☃ < 1) || (☃ > 5)) {
      return false;
    }
    for (int ☃ = ☃; ☃ <= ☃; ☃++) {
      for (int ☃ = 3; ☃ >= -1; ☃--) {
        for (int ☃ = ☃; ☃ <= ☃; ☃++)
        {
          cj ☃ = ☃.a(☃, ☃, ☃);
          if ((☃ == ☃) || (☃ == -1) || (☃ == ☃) || (☃ == ☃) || (☃ == 4) || (☃ == ☃))
          {
            if ((☃.q() >= 0) && (!☃.o(☃.b()).a().a())) {
              ☃.g(☃);
            } else if ((☃.o(☃).a().a()) && 
              (☃.o(☃).t() != aju.ae)) {
              if ((☃ == -1) && (☃.nextInt(4) != 0)) {
                ☃.a(☃, aju.Y.u(), 2);
              } else {
                ☃.a(☃, aju.e.u(), 2);
              }
            }
          }
          else if (☃.o(☃).t() != aju.ae) {
            ☃.g(☃);
          }
        }
      }
    }
    for (int ☃ = 0; ☃ < 2; ☃++) {
      for (int ☃ = 0; ☃ < 3; ☃++)
      {
        int ☃ = ☃.p() + ☃.nextInt(☃ * 2 + 1) - ☃;
        int ☃ = ☃.q();
        int ☃ = ☃.r() + ☃.nextInt(☃ * 2 + 1) - ☃;
        cj ☃ = new cj(☃, ☃, ☃);
        if (☃.d(☃))
        {
          int ☃ = 0;
          for (cq ☃ : cq.c.a) {
            if (☃.o(☃.a(☃)).a().a()) {
              ☃++;
            }
          }
          if (☃ == 1)
          {
            ☃.a(☃, aju.ae.f(☃, ☃, aju.ae.u()), 2);
            
            apv ☃ = ☃.r(☃);
            if (!(☃ instanceof apx)) {
              break;
            }
            ((apx)☃).a(azt.d, ☃.nextLong()); break;
          }
        }
      }
    }
    ☃.a(☃, aju.ac.u(), 2);
    apv ☃ = ☃.r(☃);
    if ((☃ instanceof aqk)) {
      ((aqk)☃).b().a(a(☃));
    } else {
      a.error("Failed to fetch mob spawner entity at (" + ☃.p() + ", " + ☃.q() + ", " + ☃.r() + ")");
    }
    return true;
  }
  
  private String a(Random ☃)
  {
    return b[☃.nextInt(b.length)];
  }
}
