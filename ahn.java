public class ahn
{
  public final int a;
  public final int b;
  private int cachedHashCode = 0;
  
  public ahn(int x, int z)
  {
    this.a = x;
    this.b = z;
  }
  
  public ahn(cj pos)
  {
    this.a = (pos.p() >> 4);
    this.b = (pos.r() >> 4);
  }
  
  public static long a(int x, int z)
  {
    return x & 0xFFFFFFFF | (z & 0xFFFFFFFF) << 32;
  }
  
  public int hashCode()
  {
    if (this.cachedHashCode == 0)
    {
      int ix = 1664525 * this.a + 1013904223;
      int iz = 1664525 * (this.b ^ 0xDEADBEEF) + 1013904223;
      this.cachedHashCode = (ix ^ iz);
    }
    return this.cachedHashCode;
  }
  
  public boolean equals(Object p_equals_1_)
  {
    if (this == p_equals_1_) {
      return true;
    }
    if (!(p_equals_1_ instanceof ahn)) {
      return false;
    }
    ahn chunkcoordintpair = (ahn)p_equals_1_;
    return (this.a == chunkcoordintpair.a) && (this.b == chunkcoordintpair.b);
  }
  
  public double a(rr p_185327_1_)
  {
    double d0 = this.a * 16 + 8;
    double d1 = this.b * 16 + 8;
    double d2 = d0 - p_185327_1_.p;
    double d3 = d1 - p_185327_1_.r;
    return d2 * d2 + d3 * d3;
  }
  
  public int a()
  {
    return (this.a << 4) + 8;
  }
  
  public int b()
  {
    return (this.b << 4) + 8;
  }
  
  public int c()
  {
    return this.a << 4;
  }
  
  public int d()
  {
    return this.b << 4;
  }
  
  public int e()
  {
    return (this.a << 4) + 15;
  }
  
  public int f()
  {
    return (this.b << 4) + 15;
  }
  
  public cj a(int x, int y, int z)
  {
    return new cj((this.a << 4) + x, y, (this.b << 4) + z);
  }
  
  public cj a(int y)
  {
    return new cj(a(), y, b());
  }
  
  public String toString()
  {
    return "[" + this.a + ", " + this.b + "]";
  }
}
