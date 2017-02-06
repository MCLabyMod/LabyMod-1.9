public class aii
{
  private static final aii.a a = new aii.a()
  {
    public int a(aig ☃, cj ☃)
    {
      return ☃.b(☃);
    }
  };
  private static final aii.a b = new aii.a()
  {
    public int a(aig ☃, cj ☃)
    {
      return ☃.c(☃);
    }
  };
  private static final aii.a c = new aii.a()
  {
    public int a(aig ☃, cj ☃)
    {
      return ☃.o();
    }
  };
  
  private static int a(ahx ☃, cj ☃, aii.a ☃)
  {
    int ☃ = 0;
    int ☃ = 0;
    int ☃ = 0;
    for (cj.a ☃ : cj.b(☃.a(-1, 0, -1), ☃.a(1, 0, 1)))
    {
      int ☃ = ☃.a(☃.b(☃), ☃);
      
      ☃ += ((☃ & 0xFF0000) >> 16);
      ☃ += ((☃ & 0xFF00) >> 8);
      ☃ += (☃ & 0xFF);
    }
    return (☃ / 9 & 0xFF) << 16 | (☃ / 9 & 0xFF) << 8 | ☃ / 9 & 0xFF;
  }
  
  public static int a(ahx ☃, cj ☃)
  {
    return a(☃, ☃, a);
  }
  
  public static int b(ahx ☃, cj ☃)
  {
    return a(☃, ☃, b);
  }
  
  public static int c(ahx ☃, cj ☃)
  {
    return a(☃, ☃, c);
  }
  
  static abstract interface a
  {
    public abstract int a(aig paramaig, cj paramcj);
  }
}
