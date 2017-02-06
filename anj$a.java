public enum anj$a
  implements or
{
  private static final a[] g;
  private final int h;
  private final String i;
  private final String j;
  private final axf k;
  
  static
  {
    g = new a[values().length];
    for (a ☃ : values()) {
      g[☃.a()] = ☃;
    }
  }
  
  private anj$a(int ☃, String ☃, axf ☃)
  {
    this(☃, ☃, ☃, ☃);
  }
  
  private anj$a(int ☃, String ☃, String ☃, axf ☃)
  {
    this.h = ☃;
    this.i = ☃;
    this.j = ☃;
    this.k = ☃;
  }
  
  public int a()
  {
    return this.h;
  }
  
  public axf c()
  {
    return this.k;
  }
  
  public String toString()
  {
    return this.i;
  }
  
  public static a a(int ☃)
  {
    if ((☃ < 0) || (☃ >= g.length)) {
      ☃ = 0;
    }
    return g[☃];
  }
  
  public String m()
  {
    return this.i;
  }
  
  public String d()
  {
    return this.j;
  }
}
