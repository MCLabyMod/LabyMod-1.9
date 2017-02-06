import java.util.Random;

public class ate
  extends atk
{
  protected static final arc a = aju.k.u();
  protected static final arc b = aju.a.u();
  private float[] c = new float['Ѐ'];
  
  protected void a(long ☃, int ☃, int ☃, atf ☃, double ☃, double ☃, double ☃, float ☃, float ☃, float ☃, int ☃, int ☃, double ☃)
  {
    Random ☃ = new Random(☃);
    
    double ☃ = ☃ * 16 + 8;
    double ☃ = ☃ * 16 + 8;
    
    float ☃ = 0.0F;
    float ☃ = 0.0F;
    if (☃ <= 0)
    {
      int ☃ = this.e * 16 - 16;
      ☃ = ☃ - ☃.nextInt(☃ / 4);
    }
    boolean ☃ = false;
    if (☃ == -1)
    {
      ☃ = ☃ / 2;
      ☃ = true;
    }
    float ☃ = 1.0F;
    for (int ☃ = 0; ☃ < 256; ☃++)
    {
      if ((☃ == 0) || (☃.nextInt(3) == 0)) {
        ☃ = 1.0F + ☃.nextFloat() * ☃.nextFloat();
      }
      this.c[☃] = (☃ * ☃);
    }
    for (; ☃ < ☃; ☃++)
    {
      double ☃ = 1.5D + on.a(☃ * 3.1415927F / ☃) * ☃;
      double ☃ = ☃ * ☃;
      
      ☃ *= (☃.nextFloat() * 0.25D + 0.75D);
      ☃ *= (☃.nextFloat() * 0.25D + 0.75D);
      
      float ☃ = on.b(☃);
      float ☃ = on.a(☃);
      ☃ += on.b(☃) * ☃;
      ☃ += ☃;
      ☃ += on.a(☃) * ☃;
      
      ☃ *= 0.7F;
      
      ☃ += ☃ * 0.05F;
      ☃ += ☃ * 0.05F;
      
      ☃ *= 0.8F;
      ☃ *= 0.5F;
      ☃ += (☃.nextFloat() - ☃.nextFloat()) * ☃.nextFloat() * 2.0F;
      ☃ += (☃.nextFloat() - ☃.nextFloat()) * ☃.nextFloat() * 4.0F;
      if ((☃) || (☃.nextInt(4) != 0))
      {
        double ☃ = ☃ - ☃;
        double ☃ = ☃ - ☃;
        double ☃ = ☃ - ☃;
        double ☃ = ☃ + 2.0F + 16.0F;
        if (☃ * ☃ + ☃ * ☃ - ☃ * ☃ > ☃ * ☃) {
          return;
        }
        if ((☃ >= ☃ - 16.0D - ☃ * 2.0D) && (☃ >= ☃ - 16.0D - ☃ * 2.0D) && (☃ <= ☃ + 16.0D + ☃ * 2.0D) && (☃ <= ☃ + 16.0D + ☃ * 2.0D))
        {
          int ☃ = on.c(☃ - ☃) - ☃ * 16 - 1;
          int ☃ = on.c(☃ + ☃) - ☃ * 16 + 1;
          
          int ☃ = on.c(☃ - ☃) - 1;
          int ☃ = on.c(☃ + ☃) + 1;
          
          int ☃ = on.c(☃ - ☃) - ☃ * 16 - 1;
          int ☃ = on.c(☃ + ☃) - ☃ * 16 + 1;
          if (☃ < 0) {
            ☃ = 0;
          }
          if (☃ > 16) {
            ☃ = 16;
          }
          if (☃ < 1) {
            ☃ = 1;
          }
          if (☃ > 248) {
            ☃ = 248;
          }
          if (☃ < 0) {
            ☃ = 0;
          }
          if (☃ > 16) {
            ☃ = 16;
          }
          boolean ☃ = false;
          for (int ☃ = ☃; (!☃) && (☃ < ☃); ☃++) {
            for (int ☃ = ☃; (!☃) && (☃ < ☃); ☃++) {
              for (int ☃ = ☃ + 1; (!☃) && (☃ >= ☃ - 1); ☃--) {
                if ((☃ >= 0) && (☃ < 256))
                {
                  arc ☃ = ☃.a(☃, ☃, ☃);
                  if ((☃.t() == aju.i) || (☃.t() == aju.j)) {
                    ☃ = true;
                  }
                  if ((☃ != ☃ - 1) && (☃ != ☃) && (☃ != ☃ - 1) && (☃ != ☃) && (☃ != ☃ - 1)) {
                    ☃ = ☃;
                  }
                }
              }
            }
          }
          if (!☃)
          {
            cj.a ☃ = new cj.a();
            for (int ☃ = ☃; ☃ < ☃; ☃++)
            {
              double ☃ = (☃ + ☃ * 16 + 0.5D - ☃) / ☃;
              for (int ☃ = ☃; ☃ < ☃; ☃++)
              {
                double ☃ = (☃ + ☃ * 16 + 0.5D - ☃) / ☃;
                boolean ☃ = false;
                if (☃ * ☃ + ☃ * ☃ < 1.0D) {
                  for (int ☃ = ☃; ☃ > ☃; ☃--)
                  {
                    double ☃ = (☃ - 1 + 0.5D - ☃) / ☃;
                    if ((☃ * ☃ + ☃ * ☃) * this.c[(☃ - 1)] + ☃ * ☃ / 6.0D < 1.0D)
                    {
                      arc ☃ = ☃.a(☃, ☃, ☃);
                      if (☃.t() == aju.c) {
                        ☃ = true;
                      }
                      if ((☃.t() == aju.b) || (☃.t() == aju.d) || (☃.t() == aju.c)) {
                        if (☃ - 1 < 10)
                        {
                          ☃.a(☃, ☃, ☃, a);
                        }
                        else
                        {
                          ☃.a(☃, ☃, ☃, b);
                          if ((☃) && (☃.a(☃, ☃ - 1, ☃).t() == aju.d))
                          {
                            ☃.c(☃ + ☃ * 16, 0, ☃ + ☃ * 16);
                            ☃.a(☃, ☃ - 1, ☃, this.g.b(☃).r);
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
            if (☃) {
              break;
            }
          }
        }
      }
    }
  }
  
  protected void a(aht ☃, int ☃, int ☃, int ☃, int ☃, atf ☃)
  {
    if (this.f.nextInt(50) != 0) {
      return;
    }
    double ☃ = ☃ * 16 + this.f.nextInt(16);
    double ☃ = this.f.nextInt(this.f.nextInt(40) + 8) + 20;
    double ☃ = ☃ * 16 + this.f.nextInt(16);
    
    int ☃ = 1;
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      float ☃ = this.f.nextFloat() * 6.2831855F;
      float ☃ = (this.f.nextFloat() - 0.5F) * 2.0F / 8.0F;
      float ☃ = (this.f.nextFloat() * 2.0F + this.f.nextFloat()) * 2.0F;
      
      a(this.f.nextLong(), ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, 0, 0, 3.0D);
    }
  }
}
