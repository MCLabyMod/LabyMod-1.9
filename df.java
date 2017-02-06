import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

public class df
  implements Comparable<df>
{
  public static final df b = new df(0, 0, 0);
  private final int a;
  private final int c;
  private final int d;
  
  public df(int ☃, int ☃, int ☃)
  {
    this.a = ☃;
    this.c = ☃;
    this.d = ☃;
  }
  
  public df(double ☃, double ☃, double ☃)
  {
    this(on.c(☃), on.c(☃), on.c(☃));
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if (!(☃ instanceof df)) {
      return false;
    }
    df ☃ = (df)☃;
    if (p() != ☃.p()) {
      return false;
    }
    if (q() != ☃.q()) {
      return false;
    }
    if (r() != ☃.r()) {
      return false;
    }
    return true;
  }
  
  public int hashCode()
  {
    return (q() + r() * 31) * 31 + p();
  }
  
  public int i(df ☃)
  {
    if (q() == ☃.q())
    {
      if (r() == ☃.r()) {
        return p() - ☃.p();
      }
      return r() - ☃.r();
    }
    return q() - ☃.q();
  }
  
  public int p()
  {
    return this.a;
  }
  
  public int q()
  {
    return this.c;
  }
  
  public int r()
  {
    return this.d;
  }
  
  public df d(df ☃)
  {
    return new df(q() * ☃.r() - r() * ☃.q(), r() * ☃.p() - p() * ☃.r(), p() * ☃.q() - q() * ☃.p());
  }
  
  public double f(int ☃, int ☃, int ☃)
  {
    double ☃ = p() - ☃;
    double ☃ = q() - ☃;
    double ☃ = r() - ☃;
    
    return Math.sqrt(☃ * ☃ + ☃ * ☃ + ☃ * ☃);
  }
  
  public double e(double ☃, double ☃, double ☃)
  {
    double ☃ = p() - ☃;
    double ☃ = q() - ☃;
    double ☃ = r() - ☃;
    return ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
  }
  
  public double f(double ☃, double ☃, double ☃)
  {
    double ☃ = p() + 0.5D - ☃;
    double ☃ = q() + 0.5D - ☃;
    double ☃ = r() + 0.5D - ☃;
    return ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
  }
  
  public double k(df ☃)
  {
    return e(☃.p(), ☃.q(), ☃.r());
  }
  
  public String toString()
  {
    return Objects.toStringHelper(this).add("x", p()).add("y", q()).add("z", r()).toString();
  }
}
