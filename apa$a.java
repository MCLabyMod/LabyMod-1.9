public enum apa$a
  implements or
{
  private static final a[] i;
  private final int j;
  private final axf k;
  private final String l;
  private final String m;
  
  static
  {
    i = new a[values().length];
    for (a ☃ : values()) {
      i[☃.a()] = ☃;
    }
  }
  
  private apa$a(int ☃, axf ☃, String ☃)
  {
    this(☃, ☃, ☃, ☃);
  }
  
  private apa$a(int ☃, axf ☃, String ☃, String ☃)
  {
    this.j = ☃;
    this.k = ☃;
    this.l = ☃;
    this.m = ☃;
  }
  
  public int a()
  {
    return this.j;
  }
  
  public axf c()
  {
    return this.k;
  }
  
  public String toString()
  {
    return this.l;
  }
  
  public static a a(int ☃)
  {
    if ((☃ < 0) || (☃ >= i.length)) {
      ☃ = 0;
    }
    return i[☃];
  }
  
  public String m()
  {
    return this.l;
  }
  
  public String d()
  {
    return this.m;
  }
}
