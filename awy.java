import java.util.Random;

public class awy
{
  private static int[][] e = { { 1, 1, 0 }, { -1, 1, 0 }, { 1, -1, 0 }, { -1, -1, 0 }, { 1, 0, 1 }, { -1, 0, 1 }, { 1, 0, -1 }, { -1, 0, -1 }, { 0, 1, 1 }, { 0, -1, 1 }, { 0, 1, -1 }, { 0, -1, -1 } };
  public static final double a = Math.sqrt(3.0D);
  private int[] f = new int['Ȁ'];
  public double b;
  public double c;
  public double d;
  
  public awy()
  {
    this(new Random());
  }
  
  public awy(Random ☃)
  {
    this.b = (☃.nextDouble() * 256.0D);
    this.c = (☃.nextDouble() * 256.0D);
    this.d = (☃.nextDouble() * 256.0D);
    for (int ☃ = 0; ☃ < 256; ☃++) {
      this.f[☃] = ☃;
    }
    for (int ☃ = 0; ☃ < 256; ☃++)
    {
      int ☃ = ☃.nextInt(256 - ☃) + ☃;
      int ☃ = this.f[☃];
      this.f[☃] = this.f[☃];
      this.f[☃] = ☃;
      
      this.f[(☃ + 256)] = this.f[☃];
    }
  }
  
  private static int a(double ☃)
  {
    return ☃ > 0.0D ? (int)☃ : (int)☃ - 1;
  }
  
  private static double a(int[] ☃, double ☃, double ☃)
  {
    return ☃[0] * ☃ + ☃[1] * ☃;
  }
  
  public double a(double ☃, double ☃)
  {
    double ☃ = 0.5D * (a - 1.0D);
    double ☃ = (☃ + ☃) * ☃;
    int ☃ = a(☃ + ☃);
    int ☃ = a(☃ + ☃);
    double ☃ = (3.0D - a) / 6.0D;
    double ☃ = (☃ + ☃) * ☃;
    double ☃ = ☃ - ☃;
    double ☃ = ☃ - ☃;
    double ☃ = ☃ - ☃;
    double ☃ = ☃ - ☃;
    int ☃;
    int ☃;
    int ☃;
    if (☃ > ☃)
    {
      int ☃ = 1;
      ☃ = 0;
    }
    else
    {
      ☃ = 0;
      ☃ = 1;
    }
    double ☃ = ☃ - ☃ + ☃;
    double ☃ = ☃ - ☃ + ☃;
    double ☃ = ☃ - 1.0D + 2.0D * ☃;
    double ☃ = ☃ - 1.0D + 2.0D * ☃;
    
    int ☃ = ☃ & 0xFF;
    int ☃ = ☃ & 0xFF;
    int ☃ = this.f[(☃ + this.f[☃])] % 12;
    int ☃ = this.f[(☃ + ☃ + this.f[(☃ + ☃)])] % 12;
    int ☃ = this.f[(☃ + 1 + this.f[(☃ + 1)])] % 12;
    
    double ☃ = 0.5D - ☃ * ☃ - ☃ * ☃;
    double ☃;
    double ☃;
    if (☃ < 0.0D)
    {
      ☃ = 0.0D;
    }
    else
    {
      ☃ *= ☃;
      ☃ = ☃ * ☃ * a(e[☃], ☃, ☃);
    }
    double ☃ = 0.5D - ☃ * ☃ - ☃ * ☃;
    double ☃;
    double ☃;
    if (☃ < 0.0D)
    {
      ☃ = 0.0D;
    }
    else
    {
      ☃ *= ☃;
      ☃ = ☃ * ☃ * a(e[☃], ☃, ☃);
    }
    double ☃ = 0.5D - ☃ * ☃ - ☃ * ☃;
    double ☃;
    double ☃;
    if (☃ < 0.0D)
    {
      ☃ = 0.0D;
    }
    else
    {
      ☃ *= ☃;
      ☃ = ☃ * ☃ * a(e[☃], ☃, ☃);
    }
    return 70.0D * (☃ + ☃ + ☃);
  }
  
  private static final double g = 0.5D * (a - 1.0D);
  private static final double h = (3.0D - a) / 6.0D;
  
  public void a(double[] ☃, double ☃, double ☃, int ☃, int ☃, double ☃, double ☃, double ☃)
  {
    int ☃ = 0;
    for (int ☃ = 0; ☃ < ☃; ☃++)
    {
      double ☃ = (☃ + ☃) * ☃ + this.c;
      for (int ☃ = 0; ☃ < ☃; ☃++)
      {
        double ☃ = (☃ + ☃) * ☃ + this.b;
        
        double ☃ = (☃ + ☃) * g;
        int ☃ = a(☃ + ☃);
        int ☃ = a(☃ + ☃);
        double ☃ = (☃ + ☃) * h;
        double ☃ = ☃ - ☃;
        double ☃ = ☃ - ☃;
        double ☃ = ☃ - ☃;
        double ☃ = ☃ - ☃;
        int ☃;
        int ☃;
        int ☃;
        if (☃ > ☃)
        {
          int ☃ = 1;
          ☃ = 0;
        }
        else
        {
          ☃ = 0;
          ☃ = 1;
        }
        double ☃ = ☃ - ☃ + h;
        double ☃ = ☃ - ☃ + h;
        double ☃ = ☃ - 1.0D + 2.0D * h;
        double ☃ = ☃ - 1.0D + 2.0D * h;
        
        int ☃ = ☃ & 0xFF;
        int ☃ = ☃ & 0xFF;
        int ☃ = this.f[(☃ + this.f[☃])] % 12;
        int ☃ = this.f[(☃ + ☃ + this.f[(☃ + ☃)])] % 12;
        int ☃ = this.f[(☃ + 1 + this.f[(☃ + 1)])] % 12;
        
        double ☃ = 0.5D - ☃ * ☃ - ☃ * ☃;
        double ☃;
        double ☃;
        if (☃ < 0.0D)
        {
          ☃ = 0.0D;
        }
        else
        {
          ☃ *= ☃;
          ☃ = ☃ * ☃ * a(e[☃], ☃, ☃);
        }
        double ☃ = 0.5D - ☃ * ☃ - ☃ * ☃;
        double ☃;
        double ☃;
        if (☃ < 0.0D)
        {
          ☃ = 0.0D;
        }
        else
        {
          ☃ *= ☃;
          ☃ = ☃ * ☃ * a(e[☃], ☃, ☃);
        }
        double ☃ = 0.5D - ☃ * ☃ - ☃ * ☃;
        double ☃;
        double ☃;
        if (☃ < 0.0D)
        {
          ☃ = 0.0D;
        }
        else
        {
          ☃ *= ☃;
          ☃ = ☃ * ☃ * a(e[☃], ☃, ☃);
        }
        ☃[(☃++)] += 70.0D * (☃ + ☃ + ☃) * ☃;
      }
    }
  }
}
