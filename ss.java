public class ss
  extends so
{
  private final double a;
  private final double b;
  private String c;
  
  public ss(sl ☃, String ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃);
    this.a = ☃;
    this.b = ☃;
    if (☃ > ☃) {
      throw new IllegalArgumentException("Minimum value cannot be bigger than maximum value!");
    }
    if (☃ < ☃) {
      throw new IllegalArgumentException("Default value cannot be lower than minimum value!");
    }
    if (☃ > ☃) {
      throw new IllegalArgumentException("Default value cannot be bigger than maximum value!");
    }
  }
  
  public ss a(String ☃)
  {
    this.c = ☃;
    return this;
  }
  
  public String g()
  {
    return this.c;
  }
  
  public double a(double ☃)
  {
    ☃ = on.a(☃, this.a, this.b);
    
    return ☃;
  }
}
