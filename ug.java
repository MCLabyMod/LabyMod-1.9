import java.util.Random;

public class ug
  extends tk
{
  private sh a;
  private double b;
  private double c;
  private double d;
  private double e;
  private int f;
  private boolean g;
  
  public ug(sh ☃, double ☃)
  {
    this(☃, ☃, 120);
  }
  
  public ug(sh ☃, double ☃, int ☃)
  {
    this.a = ☃;
    this.e = ☃;
    this.f = ☃;
    a(1);
  }
  
  public boolean a()
  {
    if (!this.g)
    {
      if (this.a.bK() >= 100) {
        return false;
      }
      if (this.a.bF().nextInt(this.f) != 0) {
        return false;
      }
    }
    bbj ☃ = vm.a(this.a, 10, 7);
    if (☃ == null) {
      return false;
    }
    this.b = ☃.b;
    this.c = ☃.c;
    this.d = ☃.d;
    this.g = false;
    return true;
  }
  
  public boolean b()
  {
    return !this.a.x().n();
  }
  
  public void c()
  {
    this.a.x().a(this.b, this.c, this.d, this.e);
  }
  
  public void f()
  {
    this.g = true;
  }
  
  public void b(int ☃)
  {
    this.f = ☃;
  }
}
