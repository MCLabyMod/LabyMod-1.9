public class avl
{
  private final int a;
  private arc b;
  private int c = 1;
  private int d;
  
  public avl(int ☃, ajt ☃)
  {
    this(3, ☃, ☃);
  }
  
  public avl(int ☃, int ☃, ajt ☃)
  {
    this.a = ☃;
    this.c = ☃;
    this.b = ☃.u();
  }
  
  public avl(int ☃, int ☃, ajt ☃, int ☃)
  {
    this(☃, ☃, ☃);
    this.b = ☃.a(☃);
  }
  
  public int b()
  {
    return this.c;
  }
  
  public arc c()
  {
    return this.b;
  }
  
  private ajt e()
  {
    return this.b.t();
  }
  
  private int f()
  {
    return this.b.t().e(this.b);
  }
  
  public int d()
  {
    return this.d;
  }
  
  public void b(int ☃)
  {
    this.d = ☃;
  }
  
  public String toString()
  {
    String ☃;
    if (this.a >= 3)
    {
      kk ☃ = (kk)ajt.h.b(e());
      String ☃ = ☃ == null ? "null" : ☃.toString();
      if (this.c > 1) {
        ☃ = this.c + "*" + ☃;
      }
    }
    else
    {
      ☃ = Integer.toString(ajt.a(e()));
      if (this.c > 1) {
        ☃ = this.c + "x" + ☃;
      }
    }
    int ☃ = f();
    if (☃ > 0) {
      ☃ = ☃ + ":" + ☃;
    }
    return ☃;
  }
}
