public class bbh
{
  public final double a;
  public final double b;
  public final double c;
  public final double d;
  public final double e;
  public final double f;
  
  public bbh(double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    this.a = Math.min(☃, ☃);
    this.b = Math.min(☃, ☃);
    this.c = Math.min(☃, ☃);
    this.d = Math.max(☃, ☃);
    this.e = Math.max(☃, ☃);
    this.f = Math.max(☃, ☃);
  }
  
  public bbh(cj ☃)
  {
    this(☃.p(), ☃.q(), ☃.r(), ☃.p() + 1, ☃.q() + 1, ☃.r() + 1);
  }
  
  public bbh(cj ☃, cj ☃)
  {
    this(☃.p(), ☃.q(), ☃.r(), ☃.p(), ☃.q(), ☃.r());
  }
  
  public bbh e(double ☃)
  {
    return new bbh(this.a, this.b, this.c, this.d, ☃, this.f);
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if (!(☃ instanceof bbh)) {
      return false;
    }
    bbh ☃ = (bbh)☃;
    if (Double.compare(☃.a, this.a) != 0) {
      return false;
    }
    if (Double.compare(☃.b, this.b) != 0) {
      return false;
    }
    if (Double.compare(☃.c, this.c) != 0) {
      return false;
    }
    if (Double.compare(☃.d, this.d) != 0) {
      return false;
    }
    if (Double.compare(☃.e, this.e) != 0) {
      return false;
    }
    return Double.compare(☃.f, this.f) == 0;
  }
  
  public int hashCode()
  {
    long ☃ = Double.doubleToLongBits(this.a);
    int ☃ = (int)(☃ ^ ☃ >>> 32);
    ☃ = Double.doubleToLongBits(this.b);
    ☃ = 31 * ☃ + (int)(☃ ^ ☃ >>> 32);
    ☃ = Double.doubleToLongBits(this.c);
    ☃ = 31 * ☃ + (int)(☃ ^ ☃ >>> 32);
    ☃ = Double.doubleToLongBits(this.d);
    ☃ = 31 * ☃ + (int)(☃ ^ ☃ >>> 32);
    ☃ = Double.doubleToLongBits(this.e);
    ☃ = 31 * ☃ + (int)(☃ ^ ☃ >>> 32);
    ☃ = Double.doubleToLongBits(this.f);
    ☃ = 31 * ☃ + (int)(☃ ^ ☃ >>> 32);
    return ☃;
  }
  
  public bbh a(double ☃, double ☃, double ☃)
  {
    double ☃ = this.a;
    double ☃ = this.b;
    double ☃ = this.c;
    double ☃ = this.d;
    double ☃ = this.e;
    double ☃ = this.f;
    if (☃ < 0.0D) {
      ☃ += ☃;
    } else if (☃ > 0.0D) {
      ☃ += ☃;
    }
    if (☃ < 0.0D) {
      ☃ += ☃;
    } else if (☃ > 0.0D) {
      ☃ += ☃;
    }
    if (☃ < 0.0D) {
      ☃ += ☃;
    } else if (☃ > 0.0D) {
      ☃ += ☃;
    }
    return new bbh(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public bbh b(double ☃, double ☃, double ☃)
  {
    double ☃ = this.a - ☃;
    double ☃ = this.b - ☃;
    double ☃ = this.c - ☃;
    double ☃ = this.d + ☃;
    double ☃ = this.e + ☃;
    double ☃ = this.f + ☃;
    
    return new bbh(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public bbh g(double ☃)
  {
    return b(☃, ☃, ☃);
  }
  
  public bbh a(bbh ☃)
  {
    double ☃ = Math.min(this.a, ☃.a);
    double ☃ = Math.min(this.b, ☃.b);
    double ☃ = Math.min(this.c, ☃.c);
    double ☃ = Math.max(this.d, ☃.d);
    double ☃ = Math.max(this.e, ☃.e);
    double ☃ = Math.max(this.f, ☃.f);
    
    return new bbh(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public bbh c(double ☃, double ☃, double ☃)
  {
    return new bbh(this.a + ☃, this.b + ☃, this.c + ☃, this.d + ☃, this.e + ☃, this.f + ☃);
  }
  
  public bbh a(cj ☃)
  {
    return new bbh(this.a + ☃.p(), this.b + ☃.q(), this.c + ☃.r(), this.d + ☃.p(), this.e + ☃.q(), this.f + ☃.r());
  }
  
  public double a(bbh ☃, double ☃)
  {
    if ((☃.e <= this.b) || (☃.b >= this.e) || (☃.f <= this.c) || (☃.c >= this.f)) {
      return ☃;
    }
    if ((☃ > 0.0D) && (☃.d <= this.a))
    {
      double ☃ = this.a - ☃.d;
      if (☃ < ☃) {
        ☃ = ☃;
      }
    }
    else if ((☃ < 0.0D) && (☃.a >= this.d))
    {
      double ☃ = this.d - ☃.a;
      if (☃ > ☃) {
        ☃ = ☃;
      }
    }
    return ☃;
  }
  
  public double b(bbh ☃, double ☃)
  {
    if ((☃.d <= this.a) || (☃.a >= this.d) || (☃.f <= this.c) || (☃.c >= this.f)) {
      return ☃;
    }
    if ((☃ > 0.0D) && (☃.e <= this.b))
    {
      double ☃ = this.b - ☃.e;
      if (☃ < ☃) {
        ☃ = ☃;
      }
    }
    else if ((☃ < 0.0D) && (☃.b >= this.e))
    {
      double ☃ = this.e - ☃.b;
      if (☃ > ☃) {
        ☃ = ☃;
      }
    }
    return ☃;
  }
  
  public double c(bbh ☃, double ☃)
  {
    if ((☃.d <= this.a) || (☃.a >= this.d) || (☃.e <= this.b) || (☃.b >= this.e)) {
      return ☃;
    }
    if ((☃ > 0.0D) && (☃.f <= this.c))
    {
      double ☃ = this.c - ☃.f;
      if (☃ < ☃) {
        ☃ = ☃;
      }
    }
    else if ((☃ < 0.0D) && (☃.c >= this.f))
    {
      double ☃ = this.f - ☃.c;
      if (☃ > ☃) {
        ☃ = ☃;
      }
    }
    return ☃;
  }
  
  public boolean b(bbh ☃)
  {
    return a(☃.a, ☃.b, ☃.c, ☃.d, ☃.e, ☃.f);
  }
  
  public boolean a(double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    return (this.a < ☃) && (this.d > ☃) && (this.b < ☃) && (this.e > ☃) && (this.c < ☃) && (this.f > ☃);
  }
  
  public boolean a(bbj ☃)
  {
    if ((☃.b <= this.a) || (☃.b >= this.d)) {
      return false;
    }
    if ((☃.c <= this.b) || (☃.c >= this.e)) {
      return false;
    }
    if ((☃.d <= this.c) || (☃.d >= this.f)) {
      return false;
    }
    return true;
  }
  
  public double a()
  {
    double ☃ = this.d - this.a;
    double ☃ = this.e - this.b;
    double ☃ = this.f - this.c;
    return (☃ + ☃ + ☃) / 3.0D;
  }
  
  public bbh h(double ☃)
  {
    return g(-☃);
  }
  
  public bbi a(bbj ☃, bbj ☃)
  {
    bbj ☃ = a(this.a, ☃, ☃);
    cq ☃ = cq.e;
    
    bbj ☃ = a(this.d, ☃, ☃);
    if ((☃ != null) && (a(☃, ☃, ☃)))
    {
      ☃ = ☃;
      ☃ = cq.f;
    }
    ☃ = b(this.b, ☃, ☃);
    if ((☃ != null) && (a(☃, ☃, ☃)))
    {
      ☃ = ☃;
      ☃ = cq.a;
    }
    ☃ = b(this.e, ☃, ☃);
    if ((☃ != null) && (a(☃, ☃, ☃)))
    {
      ☃ = ☃;
      ☃ = cq.b;
    }
    ☃ = c(this.c, ☃, ☃);
    if ((☃ != null) && (a(☃, ☃, ☃)))
    {
      ☃ = ☃;
      ☃ = cq.c;
    }
    ☃ = c(this.f, ☃, ☃);
    if ((☃ != null) && (a(☃, ☃, ☃)))
    {
      ☃ = ☃;
      ☃ = cq.d;
    }
    return ☃ == null ? null : new bbi(☃, ☃);
  }
  
  boolean a(bbj ☃, bbj ☃, bbj ☃)
  {
    return (☃ == null) || (☃.g(☃) < ☃.g(☃));
  }
  
  bbj a(double ☃, bbj ☃, bbj ☃)
  {
    bbj ☃ = ☃.a(☃, ☃);
    return (☃ == null) || (!b(☃)) ? null : ☃;
  }
  
  bbj b(double ☃, bbj ☃, bbj ☃)
  {
    bbj ☃ = ☃.b(☃, ☃);
    return (☃ == null) || (!c(☃)) ? null : ☃;
  }
  
  bbj c(double ☃, bbj ☃, bbj ☃)
  {
    bbj ☃ = ☃.c(☃, ☃);
    return (☃ == null) || (!d(☃)) ? null : ☃;
  }
  
  public boolean b(bbj ☃)
  {
    return (☃.c >= this.b) && (☃.c <= this.e) && (☃.d >= this.c) && (☃.d <= this.f);
  }
  
  public boolean c(bbj ☃)
  {
    return (☃.b >= this.a) && (☃.b <= this.d) && (☃.d >= this.c) && (☃.d <= this.f);
  }
  
  public boolean d(bbj ☃)
  {
    return (☃.b >= this.a) && (☃.b <= this.d) && (☃.c >= this.b) && (☃.c <= this.e);
  }
  
  public String toString()
  {
    return "box[" + this.a + ", " + this.b + ", " + this.c + " -> " + this.d + ", " + this.e + ", " + this.f + "]";
  }
  
  public boolean b()
  {
    return (Double.isNaN(this.a)) || (Double.isNaN(this.b)) || (Double.isNaN(this.c)) || (Double.isNaN(this.d)) || (Double.isNaN(this.e)) || (Double.isNaN(this.f));
  }
}
