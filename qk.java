public enum qk
{
  private static final qk[] e;
  private final int f;
  private final String g;
  
  private qk(int ☃, String ☃)
  {
    this.f = ☃;
    this.g = ☃;
  }
  
  public int a()
  {
    return this.f;
  }
  
  public static qk a(int ☃)
  {
    return e[(☃ % e.length)];
  }
  
  static
  {
    e = new qk[values().length];
    for (qk ☃ : values()) {
      e[☃.f] = ☃;
    }
  }
  
  public String b()
  {
    return this.g;
  }
}
