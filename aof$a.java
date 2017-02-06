public enum aof$a
  implements or
{
  private static final a[] c;
  private final int d;
  private final String e;
  private final axf f;
  private final String g;
  
  static
  {
    c = new a[values().length];
    for (a ☃ : values()) {
      c[☃.a()] = ☃;
    }
  }
  
  private aof$a(int ☃, String ☃, String ☃, axf ☃)
  {
    this.d = ☃;
    this.e = ☃;
    this.f = ☃;
    this.g = ☃;
  }
  
  public int a()
  {
    return this.d;
  }
  
  public String toString()
  {
    return this.e;
  }
  
  public axf c()
  {
    return this.f;
  }
  
  public static a a(int ☃)
  {
    if ((☃ < 0) || (☃ >= c.length)) {
      ☃ = 0;
    }
    return c[☃];
  }
  
  public String m()
  {
    return this.e;
  }
  
  public String d()
  {
    return this.g;
  }
}
