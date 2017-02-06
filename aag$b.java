public enum aag$b
{
  private final String g;
  private final int h;
  
  private aag$b(int ☃, String ☃)
  {
    this.g = ☃;
    this.h = ☃;
  }
  
  public String a()
  {
    return this.g;
  }
  
  public int b()
  {
    return this.h;
  }
  
  public String toString()
  {
    return this.g;
  }
  
  public static b a(int ☃)
  {
    if ((☃ < 0) || (☃ >= values().length)) {
      ☃ = 0;
    }
    return values()[☃];
  }
  
  public static b a(String ☃)
  {
    for (int ☃ = 0; ☃ < values().length; ☃++) {
      if (values()[☃].a().equals(☃)) {
        return values()[☃];
      }
    }
    return values()[0];
  }
}
