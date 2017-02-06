public class bbj
{
  public static final bbj a = new bbj(0.0D, 0.0D, 0.0D);
  public final double b;
  public final double c;
  public final double d;
  
  public bbj(double ☃, double ☃, double ☃)
  {
    if (☃ == -0.0D) {
      ☃ = 0.0D;
    }
    if (☃ == -0.0D) {
      ☃ = 0.0D;
    }
    if (☃ == -0.0D) {
      ☃ = 0.0D;
    }
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
  }
  
  public bbj(df ☃)
  {
    this(☃.p(), ☃.q(), ☃.r());
  }
  
  public bbj a(bbj ☃)
  {
    return new bbj(☃.b - this.b, ☃.c - this.c, ☃.d - this.d);
  }
  
  public bbj a()
  {
    double ☃ = on.a(this.b * this.b + this.c * this.c + this.d * this.d);
    if (☃ < 1.0E-4D) {
      return a;
    }
    return new bbj(this.b / ☃, this.c / ☃, this.d / ☃);
  }
  
  public double b(bbj ☃)
  {
    return this.b * ☃.b + this.c * ☃.c + this.d * ☃.d;
  }
  
  public bbj c(bbj ☃)
  {
    return new bbj(this.c * ☃.d - this.d * ☃.c, this.d * ☃.b - this.b * ☃.d, this.b * ☃.c - this.c * ☃.b);
  }
  
  public bbj d(bbj ☃)
  {
    return a(☃.b, ☃.c, ☃.d);
  }
  
  public bbj a(double ☃, double ☃, double ☃)
  {
    return b(-☃, -☃, -☃);
  }
  
  public bbj e(bbj ☃)
  {
    return b(☃.b, ☃.c, ☃.d);
  }
  
  public bbj b(double ☃, double ☃, double ☃)
  {
    return new bbj(this.b + ☃, this.c + ☃, this.d + ☃);
  }
  
  public double f(bbj ☃)
  {
    double ☃ = ☃.b - this.b;
    double ☃ = ☃.c - this.c;
    double ☃ = ☃.d - this.d;
    return on.a(☃ * ☃ + ☃ * ☃ + ☃ * ☃);
  }
  
  public double g(bbj ☃)
  {
    double ☃ = ☃.b - this.b;
    double ☃ = ☃.c - this.c;
    double ☃ = ☃.d - this.d;
    return ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
  }
  
  public double c(double ☃, double ☃, double ☃)
  {
    double ☃ = ☃ - this.b;
    double ☃ = ☃ - this.c;
    double ☃ = ☃ - this.d;
    return ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
  }
  
  public bbj a(double ☃)
  {
    return new bbj(this.b * ☃, this.c * ☃, this.d * ☃);
  }
  
  public double b()
  {
    return on.a(this.b * this.b + this.c * this.c + this.d * this.d);
  }
  
  public bbj a(bbj ☃, double ☃)
  {
    double ☃ = ☃.b - this.b;
    double ☃ = ☃.c - this.c;
    double ☃ = ☃.d - this.d;
    if (☃ * ☃ < 1.0000000116860974E-7D) {
      return null;
    }
    double ☃ = (☃ - this.b) / ☃;
    if ((☃ < 0.0D) || (☃ > 1.0D)) {
      return null;
    }
    return new bbj(this.b + ☃ * ☃, this.c + ☃ * ☃, this.d + ☃ * ☃);
  }
  
  public bbj b(bbj ☃, double ☃)
  {
    double ☃ = ☃.b - this.b;
    double ☃ = ☃.c - this.c;
    double ☃ = ☃.d - this.d;
    if (☃ * ☃ < 1.0000000116860974E-7D) {
      return null;
    }
    double ☃ = (☃ - this.c) / ☃;
    if ((☃ < 0.0D) || (☃ > 1.0D)) {
      return null;
    }
    return new bbj(this.b + ☃ * ☃, this.c + ☃ * ☃, this.d + ☃ * ☃);
  }
  
  public bbj c(bbj ☃, double ☃)
  {
    double ☃ = ☃.b - this.b;
    double ☃ = ☃.c - this.c;
    double ☃ = ☃.d - this.d;
    if (☃ * ☃ < 1.0000000116860974E-7D) {
      return null;
    }
    double ☃ = (☃ - this.d) / ☃;
    if ((☃ < 0.0D) || (☃ > 1.0D)) {
      return null;
    }
    return new bbj(this.b + ☃ * ☃, this.c + ☃ * ☃, this.d + ☃ * ☃);
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if (!(☃ instanceof bbj)) {
      return false;
    }
    bbj ☃ = (bbj)☃;
    if (Double.compare(☃.b, this.b) != 0) {
      return false;
    }
    if (Double.compare(☃.c, this.c) != 0) {
      return false;
    }
    return Double.compare(☃.d, this.d) == 0;
  }
  
  public int hashCode()
  {
    long ☃ = Double.doubleToLongBits(this.b);
    int ☃ = (int)(☃ ^ ☃ >>> 32);
    ☃ = Double.doubleToLongBits(this.c);
    ☃ = 31 * ☃ + (int)(☃ ^ ☃ >>> 32);
    ☃ = Double.doubleToLongBits(this.d);
    ☃ = 31 * ☃ + (int)(☃ ^ ☃ >>> 32);
    return ☃;
  }
  
  public String toString()
  {
    return "(" + this.b + ", " + this.c + ", " + this.d + ")";
  }
  
  public bbj a(float ☃)
  {
    float ☃ = on.b(☃);
    float ☃ = on.a(☃);
    
    double ☃ = this.b;
    double ☃ = this.c * ☃ + this.d * ☃;
    double ☃ = this.d * ☃ - this.c * ☃;
    
    return new bbj(☃, ☃, ☃);
  }
  
  public bbj b(float ☃)
  {
    float ☃ = on.b(☃);
    float ☃ = on.a(☃);
    
    double ☃ = this.b * ☃ + this.d * ☃;
    double ☃ = this.c;
    double ☃ = this.d * ☃ - this.b * ☃;
    
    return new bbj(☃, ☃, ☃);
  }
}
