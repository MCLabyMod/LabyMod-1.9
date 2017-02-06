public enum ahw$a
{
  int f;
  String g;
  String h;
  
  private ahw$a(int ☃, String ☃, String ☃)
  {
    this.f = ☃;
    this.g = ☃;
    this.h = ☃;
  }
  
  public int a()
  {
    return this.f;
  }
  
  public String b()
  {
    return this.g;
  }
  
  public void a(zh ☃)
  {
    if (this == c)
    {
      ☃.c = true;
      ☃.d = true;
      ☃.a = true;
    }
    else if (this == e)
    {
      ☃.c = true;
      ☃.d = false;
      ☃.a = true;
      ☃.b = true;
    }
    else
    {
      ☃.c = false;
      ☃.d = false;
      ☃.a = false;
      ☃.b = false;
    }
    ☃.e = (!c());
  }
  
  public boolean c()
  {
    return (this == d) || (this == e);
  }
  
  public boolean d()
  {
    return this == c;
  }
  
  public boolean e()
  {
    return (this == b) || (this == d);
  }
  
  public static a a(int ☃)
  {
    return a(☃, b);
  }
  
  public static a a(int ☃, a ☃)
  {
    for (a ☃ : ) {
      if (☃.f == ☃) {
        return ☃;
      }
    }
    return ☃;
  }
  
  public static a a(String ☃)
  {
    return a(☃, b);
  }
  
  public static a a(String ☃, a ☃)
  {
    for (a ☃ : ) {
      if ((☃.g.equals(☃)) || (☃.h.equals(☃))) {
        return ☃;
      }
    }
    return ☃;
  }
}
