public enum akw$b
  implements or
{
  private static final b[] g;
  private final int h;
  private final String i;
  private final String j;
  
  static
  {
    g = new b[values().length];
    for (b ☃ : values()) {
      g[☃.a()] = ☃;
    }
  }
  
  private akw$b(int ☃, String ☃)
  {
    this(☃, ☃, ☃);
  }
  
  private akw$b(int ☃, String ☃, String ☃)
  {
    this.h = ☃;
    this.i = ☃;
    this.j = ☃;
  }
  
  public int a()
  {
    return this.h;
  }
  
  public String toString()
  {
    return this.i;
  }
  
  public static b a(int ☃)
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
  
  public String c()
  {
    return this.j;
  }
}
