public class uc
  extends tk
{
  private sh b;
  protected double a;
  private double c;
  private double d;
  private double e;
  
  public uc(sh ☃, double ☃)
  {
    this.b = ☃;
    this.a = ☃;
    a(1);
  }
  
  public boolean a()
  {
    if ((this.b.bG() == null) && (!this.b.aH())) {
      return false;
    }
    bbj ☃ = vm.a(this.b, 5, 4);
    if (☃ == null) {
      return false;
    }
    this.c = ☃.b;
    this.d = ☃.c;
    this.e = ☃.d;
    if (this.b.aH())
    {
      cj ☃ = a(this.b.l, this.b, 5, 4);
      if (☃ != null)
      {
        this.c = ☃.p();
        this.d = ☃.q();
        this.e = ☃.r();
      }
    }
    return true;
  }
  
  public void c()
  {
    this.b.x().a(this.c, this.d, this.e, this.a);
  }
  
  public boolean b()
  {
    return !this.b.x().n();
  }
  
  private cj a(aht ☃, rr ☃, int ☃, int ☃)
  {
    cj ☃ = new cj(☃);
    cj.a ☃ = new cj.a();
    int ☃ = ☃.p();
    int ☃ = ☃.q();
    int ☃ = ☃.r();
    
    float ☃ = ☃ * ☃ * ☃ * 2;
    cj ☃ = null;
    for (int ☃ = ☃ - ☃; ☃ <= ☃ + ☃; ☃++) {
      for (int ☃ = ☃ - ☃; ☃ <= ☃ + ☃; ☃++) {
        for (int ☃ = ☃ - ☃; ☃ <= ☃ + ☃; ☃++)
        {
          ☃.c(☃, ☃, ☃);
          arc ☃ = ☃.o(☃);
          ajt ☃ = ☃.t();
          if ((☃ == aju.j) || (☃ == aju.i))
          {
            float ☃ = (☃ - ☃) * (☃ - ☃) + (☃ - ☃) * (☃ - ☃) + (☃ - ☃) * (☃ - ☃);
            if (☃ < ☃)
            {
              ☃ = ☃;
              ☃ = new cj(☃);
            }
          }
        }
      }
    }
    return ☃;
  }
}
