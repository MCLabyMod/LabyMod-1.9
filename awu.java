import java.util.Random;

public class awu
  extends awz
{
  private awt[] a;
  private int b;
  
  public awu(Random ☃, int ☃)
  {
    this.b = ☃;
    this.a = new awt[☃];
    for (int ☃ = 0; ☃ < ☃; ☃++) {
      this.a[☃] = new awt(☃);
    }
  }
  
  public double[] a(double[] ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, double ☃, double ☃, double ☃)
  {
    if (☃ == null) {
      ☃ = new double[☃ * ☃ * ☃];
    } else {
      for (int ☃ = 0; ☃ < ☃.length; ☃++) {
        ☃[☃] = 0.0D;
      }
    }
    double ☃ = 1.0D;
    for (int ☃ = 0; ☃ < this.b; ☃++)
    {
      double ☃ = ☃ * ☃ * ☃;
      double ☃ = ☃ * ☃ * ☃;
      double ☃ = ☃ * ☃ * ☃;
      long ☃ = on.d(☃);
      long ☃ = on.d(☃);
      ☃ -= ☃;
      ☃ -= ☃;
      ☃ %= 16777216L;
      ☃ %= 16777216L;
      ☃ += ☃;
      ☃ += ☃;
      this.a[☃].a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃ * ☃, ☃ * ☃, ☃ * ☃, ☃);
      ☃ /= 2.0D;
    }
    return ☃;
  }
  
  public double[] a(double[] ☃, int ☃, int ☃, int ☃, int ☃, double ☃, double ☃, double ☃)
  {
    return a(☃, ☃, 10, ☃, ☃, 1, ☃, ☃, 1.0D, ☃);
  }
}
