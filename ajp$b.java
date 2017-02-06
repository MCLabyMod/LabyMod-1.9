public enum ajp$b
  implements or
{
  private static final b[] k;
  private final int l;
  private final String m;
  
  static
  {
    k = new b[values().length];
    for (b ☃ : values()) {
      k[☃.a()] = ☃;
    }
  }
  
  private ajp$b(int ☃, String ☃)
  {
    this.l = ☃;
    this.m = ☃;
  }
  
  public int a()
  {
    return this.l;
  }
  
  public String toString()
  {
    return this.m;
  }
  
  public boolean c()
  {
    return (this == e) || (this == c) || (this == f) || (this == d);
  }
  
  public static b a(int ☃)
  {
    if ((☃ < 0) || (☃ >= k.length)) {
      ☃ = 0;
    }
    return k[☃];
  }
  
  public String m()
  {
    return this.m;
  }
}
