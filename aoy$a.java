public enum aoy$a
  implements or
{
  private static final a[] e;
  private final int f;
  private final String g;
  private final String h;
  
  static
  {
    e = new a[values().length];
    for (a ☃ : values()) {
      e[☃.a()] = ☃;
    }
  }
  
  private aoy$a(int ☃, String ☃, String ☃)
  {
    this.f = ☃;
    this.g = ☃;
    this.h = ☃;
  }
  
  public int a()
  {
    return this.f;
  }
  
  public String toString()
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
  
  public String m()
  {
    return this.g;
  }
  
  public String c()
  {
    return this.h;
  }
}
