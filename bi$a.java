import java.util.Random;

class bi$a
{
  double a;
  double b;
  
  bi$a() {}
  
  bi$a(double ☃, double ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  double a(a ☃)
  {
    double ☃ = this.a - ☃.a;
    double ☃ = this.b - ☃.b;
    
    return Math.sqrt(☃ * ☃ + ☃ * ☃);
  }
  
  void a()
  {
    double ☃ = b();
    this.a /= ☃;
    this.b /= ☃;
  }
  
  float b()
  {
    return on.a(this.a * this.a + this.b * this.b);
  }
  
  public void b(a ☃)
  {
    this.a -= ☃.a;
    this.b -= ☃.b;
  }
  
  public boolean a(double ☃, double ☃, double ☃, double ☃)
  {
    boolean ☃ = false;
    if (this.a < ☃)
    {
      this.a = ☃;
      ☃ = true;
    }
    else if (this.a > ☃)
    {
      this.a = ☃;
      ☃ = true;
    }
    if (this.b < ☃)
    {
      this.b = ☃;
      ☃ = true;
    }
    else if (this.b > ☃)
    {
      this.b = ☃;
      ☃ = true;
    }
    return ☃;
  }
  
  public int a(aht ☃)
  {
    cj ☃ = new cj(this.a, 256.0D, this.b);
    while (☃.q() > 0)
    {
      ☃ = ☃.b();
      if (☃.o(☃).a() != axe.a) {
        return ☃.q() + 1;
      }
    }
    return 257;
  }
  
  public boolean b(aht ☃)
  {
    cj ☃ = new cj(this.a, 256.0D, this.b);
    while (☃.q() > 0)
    {
      ☃ = ☃.b();
      
      axe ☃ = ☃.o(☃).a();
      if (☃ != axe.a) {
        return (!☃.d()) && (☃ != axe.o);
      }
    }
    return false;
  }
  
  public void a(Random ☃, double ☃, double ☃, double ☃, double ☃)
  {
    this.a = on.a(☃, ☃, ☃);
    this.b = on.a(☃, ☃, ☃);
  }
}
