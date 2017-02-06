public class aic
  implements ahx
{
  protected int a;
  protected int b;
  protected ase[][] c;
  protected boolean d;
  protected aht e;
  
  public aic(aht ☃, cj ☃, cj ☃, int ☃)
  {
    this.e = ☃;
    
    this.a = (☃.p() - ☃ >> 4);
    this.b = (☃.r() - ☃ >> 4);
    int ☃ = ☃.p() + ☃ >> 4;
    int ☃ = ☃.r() + ☃ >> 4;
    
    this.c = new ase[☃ - this.a + 1][☃ - this.b + 1];
    
    this.d = true;
    for (int ☃ = this.a; ☃ <= ☃; ☃++) {
      for (int ☃ = this.b; ☃ <= ☃; ☃++) {
        this.c[(☃ - this.a)][(☃ - this.b)] = ☃.a(☃, ☃);
      }
    }
    for (int ☃ = ☃.p() >> 4; ☃ <= ☃.p() >> 4; ☃++) {
      for (int ☃ = ☃.r() >> 4; ☃ <= ☃.r() >> 4; ☃++)
      {
        ase ☃ = this.c[(☃ - this.a)][(☃ - this.b)];
        if ((☃ != null) && 
          (!☃.c(☃.q(), ☃.q()))) {
          this.d = false;
        }
      }
    }
  }
  
  public boolean aa()
  {
    return this.d;
  }
  
  public apv r(cj ☃)
  {
    int ☃ = (☃.p() >> 4) - this.a;
    int ☃ = (☃.r() >> 4) - this.b;
    
    return this.c[☃][☃].a(☃, ase.a.a);
  }
  
  public int b(cj ☃, int ☃)
  {
    int ☃ = a(ahz.a, ☃);
    int ☃ = a(ahz.b, ☃);
    if (☃ < ☃) {
      ☃ = ☃;
    }
    return ☃ << 20 | ☃ << 4;
  }
  
  public arc o(cj ☃)
  {
    if ((☃.q() >= 0) && 
      (☃.q() < 256))
    {
      int ☃ = (☃.p() >> 4) - this.a;
      int ☃ = (☃.r() >> 4) - this.b;
      if ((☃ >= 0) && (☃ < this.c.length) && (☃ >= 0) && (☃ < this.c[☃].length))
      {
        ase ☃ = this.c[☃][☃];
        if (☃ != null) {
          return ☃.a(☃);
        }
      }
    }
    return aju.a.u();
  }
  
  public aig b(cj ☃)
  {
    return this.e.b(☃);
  }
  
  private int a(ahz ☃, cj ☃)
  {
    if ((☃ == ahz.a) && (this.e.s.m())) {
      return 0;
    }
    if ((☃.q() < 0) || (☃.q() >= 256)) {
      return ☃.c;
    }
    if (o(☃).f())
    {
      int ☃ = 0;
      for (cq ☃ : cq.values())
      {
        int ☃ = b(☃, ☃.a(☃));
        if (☃ > ☃) {
          ☃ = ☃;
        }
        if (☃ >= 15) {
          return ☃;
        }
      }
      return ☃;
    }
    int ☃ = (☃.p() >> 4) - this.a;
    int ☃ = (☃.r() >> 4) - this.b;
    
    return this.c[☃][☃].a(☃, ☃);
  }
  
  public boolean d(cj ☃)
  {
    return o(☃).a() == axe.a;
  }
  
  public int b(ahz ☃, cj ☃)
  {
    if ((☃.q() < 0) || (☃.q() >= 256)) {
      return ☃.c;
    }
    int ☃ = (☃.p() >> 4) - this.a;
    int ☃ = (☃.r() >> 4) - this.b;
    
    return this.c[☃][☃].a(☃, ☃);
  }
  
  public int a(cj ☃, cq ☃)
  {
    return o(☃).b(this, ☃, ☃);
  }
  
  public ahy L()
  {
    return this.e.L();
  }
}
