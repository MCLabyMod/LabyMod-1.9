public enum apc$a
  implements or
{
  private static final a[] d;
  private final int e;
  private final String f;
  
  static
  {
    d = new a[values().length];
    for (a ☃ : values()) {
      d[☃.a()] = ☃;
    }
  }
  
  private apc$a(int ☃, String ☃)
  {
    this.e = ☃;
    this.f = ☃;
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
}
