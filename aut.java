import com.google.common.base.Predicate;
import java.util.Random;

public class aut
  extends aud
{
  private final arc a;
  private final int b;
  private final Predicate<arc> c;
  
  public aut(arc ☃, int ☃)
  {
    this(☃, ☃, arj.a(aju.b));
  }
  
  public aut(arc ☃, int ☃, Predicate<arc> ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
  }
  
  public boolean b(aht ☃, Random ☃, cj ☃)
  {
    float ☃ = ☃.nextFloat() * 3.1415927F;
    
    double ☃ = ☃.p() + 8 + on.a(☃) * this.b / 8.0F;
    double ☃ = ☃.p() + 8 - on.a(☃) * this.b / 8.0F;
    double ☃ = ☃.r() + 8 + on.b(☃) * this.b / 8.0F;
    double ☃ = ☃.r() + 8 - on.b(☃) * this.b / 8.0F;
    
    double ☃ = ☃.q() + ☃.nextInt(3) - 2;
    double ☃ = ☃.q() + ☃.nextInt(3) - 2;
    for (int ☃ = 0; ☃ < this.b; ☃++)
    {
      float ☃ = ☃ / this.b;
      double ☃ = ☃ + (☃ - ☃) * ☃;
      double ☃ = ☃ + (☃ - ☃) * ☃;
      double ☃ = ☃ + (☃ - ☃) * ☃;
      
      double ☃ = ☃.nextDouble() * this.b / 16.0D;
      double ☃ = (on.a(3.1415927F * ☃) + 1.0F) * ☃ + 1.0D;
      double ☃ = (on.a(3.1415927F * ☃) + 1.0F) * ☃ + 1.0D;
      
      int ☃ = on.c(☃ - ☃ / 2.0D);
      int ☃ = on.c(☃ - ☃ / 2.0D);
      int ☃ = on.c(☃ - ☃ / 2.0D);
      
      int ☃ = on.c(☃ + ☃ / 2.0D);
      int ☃ = on.c(☃ + ☃ / 2.0D);
      int ☃ = on.c(☃ + ☃ / 2.0D);
      for (int ☃ = ☃; ☃ <= ☃; ☃++)
      {
        double ☃ = (☃ + 0.5D - ☃) / (☃ / 2.0D);
        if (☃ * ☃ < 1.0D) {
          for (int ☃ = ☃; ☃ <= ☃; ☃++)
          {
            double ☃ = (☃ + 0.5D - ☃) / (☃ / 2.0D);
            if (☃ * ☃ + ☃ * ☃ < 1.0D) {
              for (int ☃ = ☃; ☃ <= ☃; ☃++)
              {
                double ☃ = (☃ + 0.5D - ☃) / (☃ / 2.0D);
                if (☃ * ☃ + ☃ * ☃ + ☃ * ☃ < 1.0D)
                {
                  cj ☃ = new cj(☃, ☃, ☃);
                  if (this.c.apply(☃.o(☃))) {
                    ☃.a(☃, this.a, 2);
                  }
                }
              }
            }
          }
        }
      }
    }
    return true;
  }
}
