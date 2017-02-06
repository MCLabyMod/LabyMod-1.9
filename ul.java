import java.util.List;
import java.util.Random;

public class ul
  extends tk
{
  private wk a;
  private double b;
  private double c;
  private double d;
  private double e;
  
  public ul(wk ☃, double ☃)
  {
    this.a = ☃;
    this.b = ☃;
    a(1);
  }
  
  public boolean a()
  {
    if ((this.a.dc()) || (!this.a.aJ())) {
      return false;
    }
    bbj ☃ = vm.a(this.a, 5, 4);
    if (☃ == null) {
      return false;
    }
    this.c = ☃.b;
    this.d = ☃.c;
    this.e = ☃.d;
    return true;
  }
  
  public void c()
  {
    this.a.x().a(this.c, this.d, this.e, this.b);
  }
  
  public boolean b()
  {
    return (!this.a.x().n()) && (this.a.aJ());
  }
  
  public void e()
  {
    if (this.a.bF().nextInt(50) == 0)
    {
      rr ☃ = (rr)this.a.bu().get(0);
      if (☃ == null) {
        return;
      }
      if ((☃ instanceof zj))
      {
        int ☃ = this.a.dq();
        int ☃ = this.a.dw();
        if ((☃ > 0) && (this.a.bF().nextInt(☃) < ☃))
        {
          this.a.g((zj)☃);
          this.a.l.a(this.a, (byte)7);
          return;
        }
        this.a.n(5);
      }
      this.a.az();
      this.a.dE();
      this.a.l.a(this.a, (byte)6);
    }
  }
}
