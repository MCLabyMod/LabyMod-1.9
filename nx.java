import org.apache.commons.lang3.Validate;

public class nx
{
  private final long[] a;
  private final int b;
  private final long c;
  private final int d;
  
  public nx(int ☃, int ☃)
  {
    Validate.inclusiveBetween(1L, 32L, ☃);
    
    this.d = ☃;
    this.b = ☃;
    this.c = ((1L << ☃) - 1L);
    
    this.a = new long[on.c(☃ * ☃, 64) / 64];
  }
  
  public void a(int ☃, int ☃)
  {
    Validate.inclusiveBetween(0L, this.d - 1, ☃);
    Validate.inclusiveBetween(0L, this.c, ☃);
    
    int ☃ = ☃ * this.b;
    int ☃ = ☃ / 64;
    int ☃ = ((☃ + 1) * this.b - 1) / 64;
    int ☃ = ☃ % 64;
    
    this.a[☃] = (this.a[☃] & (this.c << ☃ ^ 0xFFFFFFFFFFFFFFFF) | (☃ & this.c) << ☃);
    if (☃ != ☃)
    {
      int ☃ = 64 - ☃;
      int ☃ = this.b - ☃;
      this.a[☃] = (this.a[☃] >>> ☃ << ☃ | (☃ & this.c) >> ☃);
    }
  }
  
  public int a(int ☃)
  {
    Validate.inclusiveBetween(0L, this.d - 1, ☃);
    
    int ☃ = ☃ * this.b;
    int ☃ = ☃ / 64;
    int ☃ = ((☃ + 1) * this.b - 1) / 64;
    int ☃ = ☃ % 64;
    if (☃ == ☃) {
      return (int)(this.a[☃] >>> ☃ & this.c);
    }
    int ☃ = 64 - ☃;
    return (int)((this.a[☃] >>> ☃ | this.a[☃] << ☃) & this.c);
  }
  
  public long[] a()
  {
    return this.a;
  }
  
  public int b()
  {
    return this.d;
  }
}
