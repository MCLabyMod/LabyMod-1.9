class acm$1
  implements adr
{
  double a;
  double b;
  long c;
  
  acm$1(acm paramacm) {}
  
  public float a(adq ☃, aht ☃, sa ☃)
  {
    boolean ☃ = ☃ != null;
    rr ☃ = ☃ ? ☃ : ☃.z();
    if ((☃ == null) && (☃ != null)) {
      ☃ = ☃.l;
    }
    if (☃ == null) {
      return 0.0F;
    }
    double ☃;
    if (☃.s.d()) {
      ☃ = ☃.c(1.0F);
    } else {
      ☃ = Math.random();
    }
    double ☃ = a(☃, ☃);
    
    return on.b((float)☃, 1.0F);
  }
  
  private double a(aht ☃, double ☃)
  {
    if (☃.P() != this.c)
    {
      this.c = ☃.P();
      
      double ☃ = ☃ - this.a;
      if (☃ < -0.5D) {
        ☃ += 1.0D;
      }
      this.b += ☃ * 0.1D;
      this.b *= 0.9D;
      this.a += this.b;
    }
    return this.a;
  }
}
