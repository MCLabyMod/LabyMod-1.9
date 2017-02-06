public enum aox$a
  implements or
{
  private static final a[] h;
  private final int i;
  private final String j;
  private final String k;
  private final axf l;
  
  static
  {
    h = new a[values().length];
    for (a ☃ : values()) {
      h[☃.a()] = ☃;
    }
  }
  
  private aox$a(int ☃, axf ☃, String ☃)
  {
    this(☃, ☃, ☃, ☃);
  }
  
  private aox$a(int ☃, axf ☃, String ☃, String ☃)
  {
    this.i = ☃;
    this.j = ☃;
    this.k = ☃;
    this.l = ☃;
  }
  
  public int a()
  {
    return this.i;
  }
  
  public axf c()
  {
    return this.l;
  }
  
  public String toString()
  {
    return this.j;
  }
  
  public static a a(int ☃)
  {
    if ((☃ < 0) || (☃ >= h.length)) {
      ☃ = 0;
    }
    return h[☃];
  }
  
  public String m()
  {
    return this.j;
  }
  
  public String d()
  {
    return this.k;
  }
}
