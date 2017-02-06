public enum apt$a
{
  private String N;
  private String O;
  private String[] P = new String[3];
  private adq Q;
  
  private apt$a(String ☃, String ☃)
  {
    this.N = ☃;
    this.O = ☃;
  }
  
  private apt$a(String ☃, String ☃, adq ☃)
  {
    this(☃, ☃);
    this.Q = ☃;
  }
  
  private apt$a(String ☃, String ☃, String ☃, String ☃, String ☃)
  {
    this(☃, ☃);
    this.P[0] = ☃;
    this.P[1] = ☃;
    this.P[2] = ☃;
  }
  
  public String a()
  {
    return this.N;
  }
  
  public String b()
  {
    return this.O;
  }
  
  public String[] c()
  {
    return this.P;
  }
  
  public boolean d()
  {
    return (this.Q != null) || (this.P[0] != null);
  }
  
  public boolean e()
  {
    return this.Q != null;
  }
  
  public adq f()
  {
    return this.Q;
  }
  
  public static a a(String ☃)
  {
    for (a ☃ : ) {
      if (☃.O.equals(☃)) {
        return ☃;
      }
    }
    return null;
  }
}
