public enum aqp$a
  implements or
{
  private static final a[] e;
  private final String f;
  private final int g;
  
  static
  {
    e = new a[values().length];
    for (a ☃ : values()) {
      e[☃.a()] = ☃;
    }
  }
  
  private aqp$a(String ☃, int ☃)
  {
    this.f = ☃;
    this.g = ☃;
  }
  
  public String m()
  {
    return this.f;
  }
  
  public int a()
  {
    return this.g;
  }
  
  public static a a(int ☃)
  {
    if ((☃ < 0) || (☃ >= e.length)) {
      ☃ = 0;
    }
    return e[☃];
  }
}
