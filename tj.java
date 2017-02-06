import java.util.List;

public class tj
  extends tk
{
  vw a;
  vw b;
  double c;
  private int d;
  
  public tj(vw ☃, double ☃)
  {
    this.a = ☃;
    this.c = ☃;
  }
  
  public boolean a()
  {
    if (this.a.l() >= 0) {
      return false;
    }
    List<vw> ☃ = this.a.l.a(this.a.getClass(), this.a.bl().b(8.0D, 4.0D, 8.0D));
    
    vw ☃ = null;
    double ☃ = Double.MAX_VALUE;
    for (vw ☃ : ☃) {
      if (☃.l() >= 0)
      {
        double ☃ = this.a.h(☃);
        if (☃ <= ☃)
        {
          ☃ = ☃;
          ☃ = ☃;
        }
      }
    }
    if (☃ == null) {
      return false;
    }
    if (☃ < 9.0D) {
      return false;
    }
    this.b = ☃;
    return true;
  }
  
  public boolean b()
  {
    if (this.a.l() >= 0) {
      return false;
    }
    if (!this.b.au()) {
      return false;
    }
    double ☃ = this.a.h(this.b);
    if ((☃ < 9.0D) || (☃ > 256.0D)) {
      return false;
    }
    return true;
  }
  
  public void c()
  {
    this.d = 0;
  }
  
  public void d()
  {
    this.b = null;
  }
  
  public void e()
  {
    if (--this.d > 0) {
      return;
    }
    this.d = 10;
    this.a.x().a(this.b, this.c);
  }
}
