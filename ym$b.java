import java.util.List;
import java.util.Random;

class ym$b
  extends sy
{
  private ym i;
  private int j;
  
  public ym$b(ym ☃)
  {
    super(☃);
    this.i = ☃;
  }
  
  public void c()
  {
    if (this.h != sy.a.b) {
      return;
    }
    double ☃ = this.b - this.i.p;
    double ☃ = this.c - this.i.q;
    double ☃ = this.d - this.i.r;
    
    double ☃ = ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
    if (this.j-- <= 0)
    {
      this.j += this.i.bF().nextInt(5) + 2;
      
      ☃ = on.a(☃);
      if (b(this.b, this.c, this.d, ☃))
      {
        this.i.s += ☃ / ☃ * 0.1D;
        this.i.t += ☃ / ☃ * 0.1D;
        this.i.u += ☃ / ☃ * 0.1D;
      }
      else
      {
        this.h = sy.a.a;
      }
    }
  }
  
  private boolean b(double ☃, double ☃, double ☃, double ☃)
  {
    double ☃ = (☃ - this.i.p) / ☃;
    double ☃ = (☃ - this.i.q) / ☃;
    double ☃ = (☃ - this.i.r) / ☃;
    
    bbh ☃ = this.i.bl();
    for (int ☃ = 1; ☃ < ☃; ☃++)
    {
      ☃ = ☃.c(☃, ☃, ☃);
      if (!this.i.l.a(this.i, ☃).isEmpty()) {
        return false;
      }
    }
    return true;
  }
}
