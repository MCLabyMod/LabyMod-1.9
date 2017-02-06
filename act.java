public enum act
  implements or
{
  private static final act[] q;
  private static final act[] r;
  private final int s;
  private final int t;
  private final String u;
  private final String v;
  private final axf w;
  private final a x;
  
  static
  {
    q = new act[values().length];
    r = new act[values().length];
    for (act ☃ : values())
    {
      q[☃.a()] = ☃;
      r[☃.b()] = ☃;
    }
  }
  
  private act(int ☃, int ☃, String ☃, String ☃, axf ☃, a ☃)
  {
    this.s = ☃;
    this.t = ☃;
    this.u = ☃;
    this.v = ☃;
    this.w = ☃;
    this.x = ☃;
  }
  
  public int a()
  {
    return this.s;
  }
  
  public int b()
  {
    return this.t;
  }
  
  public String d()
  {
    return this.v;
  }
  
  public axf e()
  {
    return this.w;
  }
  
  public static act a(int ☃)
  {
    if ((☃ < 0) || (☃ >= r.length)) {
      ☃ = 0;
    }
    return r[☃];
  }
  
  public static act b(int ☃)
  {
    if ((☃ < 0) || (☃ >= q.length)) {
      ☃ = 0;
    }
    return q[☃];
  }
  
  public String toString()
  {
    return this.v;
  }
  
  public String m()
  {
    return this.u;
  }
}
