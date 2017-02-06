public enum anv$a
  implements or
{
  private static final a[] d;
  private final int e;
  private final String f;
  private final String g;
  
  static
  {
    d = new a[values().length];
    for (a ☃ : values()) {
      d[☃.a()] = ☃;
    }
  }
  
  private anv$a(int ☃, String ☃, String ☃)
  {
    this.e = ☃;
    this.f = ☃;
    this.g = ☃;
  }
  
  public int a()
  {
    return this.e;
  }
  
  public String toString()
  {
    return this.f;
  }
  
  public static a a(int ☃)
  {
    if ((☃ < 0) || (☃ >= d.length)) {
      ☃ = 0;
    }
    return d[☃];
  }
  
  public String m()
  {
    return this.f;
  }
  
  public String c()
  {
    return this.g;
  }
}
