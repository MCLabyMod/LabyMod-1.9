import java.util.List;
import java.util.Random;

public class ud
  extends tk
{
  private ze a;
  private sa b;
  private double c;
  private int d;
  
  public ud(ze ☃, double ☃)
  {
    this.a = ☃;
    this.c = ☃;
    a(1);
  }
  
  public boolean a()
  {
    if (this.a.l() >= 0) {
      return false;
    }
    if (this.a.bF().nextInt(400) != 0) {
      return false;
    }
    List<ze> ☃ = this.a.l.a(ze.class, this.a.bl().b(6.0D, 3.0D, 6.0D));
    double ☃ = Double.MAX_VALUE;
    for (ze ☃ : ☃) {
      if ((☃ != this.a) && 
      
        (!☃.db()) && 
        
        (☃.l() < 0))
      {
        double ☃ = ☃.h(this.a);
        if (☃ <= ☃)
        {
          ☃ = ☃;
          this.b = ☃;
        }
      }
    }
    if (this.b == null)
    {
      bbj ☃ = vm.a(this.a, 16, 3);
      if (☃ == null) {
        return false;
      }
    }
    return true;
  }
  
  public boolean b()
  {
    return this.d > 0;
  }
  
  public void c()
  {
    if (this.b != null) {
      this.a.p(true);
    }
    this.d = 1000;
  }
  
  public void d()
  {
    this.a.p(false);
    this.b = null;
  }
  
  public void e()
  {
    this.d -= 1;
    if (this.b != null)
    {
      if (this.a.h(this.b) > 4.0D) {
        this.a.x().a(this.b, this.c);
      }
    }
    else if (this.a.x().n())
    {
      bbj ☃ = vm.a(this.a, 16, 3);
      if (☃ == null) {
        return;
      }
      this.a.x().a(☃.b, ☃.c, ☃.d, this.c);
    }
  }
}
