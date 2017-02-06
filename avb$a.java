public class avb$a
{
  private final int a;
  private final int b;
  private final int c;
  private final int d;
  private final boolean e;
  private final bbh f;
  
  public avb$a(int ☃, int ☃, int ☃, int ☃, boolean ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
    this.e = ☃;
    
    this.f = new bbh(☃ - ☃, 0.0D, ☃ - ☃, ☃ + ☃, 256.0D, ☃ + ☃);
  }
  
  public boolean a(cj ☃)
  {
    int ☃ = this.a - this.c;
    int ☃ = this.b - this.c;
    return (☃.p() == (☃ & 0xFFFFFFF0)) && (☃.r() == (☃ & 0xFFFFFFF0));
  }
  
  public int a()
  {
    return this.a;
  }
  
  public int b()
  {
    return this.b;
  }
  
  public int c()
  {
    return this.c;
  }
  
  public int d()
  {
    return this.d;
  }
  
  public boolean e()
  {
    return this.e;
  }
  
  public bbh f()
  {
    return this.f;
  }
}
