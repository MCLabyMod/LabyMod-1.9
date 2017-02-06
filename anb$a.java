public enum anb$a
  implements or
{
  private static final a[] b;
  private final int c;
  private final String d;
  private final axf e;
  
  static
  {
    b = new a[values().length];
    for (a ☃ : values()) {
      b[☃.a()] = ☃;
    }
  }
  
  private anb$a(int ☃, String ☃, axf ☃)
  {
    this.c = ☃;
    this.d = ☃;
    this.e = ☃;
  }
  
  public int a()
  {
    return this.c;
  }
  
  public axf c()
  {
    return this.e;
  }
  
  public String toString()
  {
    return this.d;
  }
  
  public static a a(int ☃)
  {
    if ((☃ < 0) || (☃ >= b.length)) {
      ☃ = 0;
    }
    return b[☃];
  }
  
  public String m()
  {
    return this.d;
  }
  
  public String d()
  {
    return this.d;
  }
}
