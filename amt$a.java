public enum amt$a
  implements or
{
  private static final a[] g;
  private final int h;
  private final String i;
  private final String j;
  
  static
  {
    g = new a[values().length];
    for (a ☃ : values()) {
      g[☃.a()] = ☃;
    }
  }
  
  private amt$a(int ☃, String ☃)
  {
    this(☃, ☃, ☃);
  }
  
  private amt$a(int ☃, String ☃, String ☃)
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
  
  public String c()
  {
    return this.j;
  }
  
  public static a a(arc ☃)
  {
    for (a ☃ : ) {
      if (☃ == ☃.d()) {
        return ☃;
      }
    }
    return a;
  }
  
  public abstract arc d();
}
