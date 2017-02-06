public enum amn$a
  implements or
{
  private static final a[] i;
  private final int j;
  private final String k;
  private final cq l;
  
  static
  {
    i = new a[values().length];
    for (a ☃ : values()) {
      i[☃.a()] = ☃;
    }
  }
  
  private amn$a(int ☃, String ☃, cq ☃)
  {
    this.j = ☃;
    this.k = ☃;
    this.l = ☃;
  }
  
  public int a()
  {
    return this.j;
  }
  
  public cq c()
  {
    return this.l;
  }
  
  public String toString()
  {
    return this.k;
  }
  
  public static a a(int ☃)
  {
    if ((☃ < 0) || (☃ >= i.length)) {
      ☃ = 0;
    }
    return i[☃];
  }
  
  public static a a(cq ☃, cq ☃)
  {
    switch (amn.1.a[☃.ordinal()])
    {
    case 1: 
      switch (amn.1.d[☃.k().ordinal()])
      {
      case 1: 
        return a;
      case 2: 
        return h;
      }
      throw new IllegalArgumentException("Invalid entityFacing " + ☃ + " for facing " + ☃);
    case 2: 
      switch (amn.1.d[☃.k().ordinal()])
      {
      case 1: 
        return g;
      case 2: 
        return f;
      }
      throw new IllegalArgumentException("Invalid entityFacing " + ☃ + " for facing " + ☃);
    case 3: 
      return e;
    case 4: 
      return d;
    case 5: 
      return c;
    case 6: 
      return b;
    }
    throw new IllegalArgumentException("Invalid facing: " + ☃);
  }
  
  public String m()
  {
    return this.k;
  }
}
