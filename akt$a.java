public enum akt$a
  implements or
{
  private static final a[] d;
  private final int e;
  private final String f;
  private final String g;
  private final axf h;
  
  static
  {
    d = new a[values().length];
    for (a ☃ : values()) {
      d[☃.a()] = ☃;
    }
  }
  
  private akt$a(int ☃, String ☃, axf ☃)
  {
    this(☃, ☃, ☃, ☃);
  }
  
  private akt$a(int ☃, String ☃, String ☃, axf ☃)
  {
    this.e = ☃;
    this.f = ☃;
    this.g = ☃;
    this.h = ☃;
  }
  
  public int a()
  {
    return this.e;
  }
  
  public String c()
  {
    return this.g;
  }
  
  public axf d()
  {
    return this.h;
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
