public class afx
  implements afu
{
  private final int a;
  private final int b;
  private final adq[] c;
  private final adq d;
  private boolean e;
  
  public afx(int ☃, int ☃, adq[] ☃, adq ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
  }
  
  public adq b()
  {
    return this.d;
  }
  
  public adq[] b(abc ☃)
  {
    adq[] ☃ = new adq[☃.u_()];
    for (int ☃ = 0; ☃ < ☃.length; ☃++)
    {
      adq ☃ = ☃.a(☃);
      if ((☃ != null) && (☃.b().r())) {
        ☃[☃] = new adq(☃.b().q());
      }
    }
    return ☃;
  }
  
  public boolean a(abc ☃, aht ☃)
  {
    for (int ☃ = 0; ☃ <= 3 - this.a; ☃++) {
      for (int ☃ = 0; ☃ <= 3 - this.b; ☃++)
      {
        if (a(☃, ☃, ☃, true)) {
          return true;
        }
        if (a(☃, ☃, ☃, false)) {
          return true;
        }
      }
    }
    return false;
  }
  
  private boolean a(abc ☃, int ☃, int ☃, boolean ☃)
  {
    for (int ☃ = 0; ☃ < 3; ☃++) {
      for (int ☃ = 0; ☃ < 3; ☃++)
      {
        int ☃ = ☃ - ☃;
        int ☃ = ☃ - ☃;
        adq ☃ = null;
        if ((☃ >= 0) && (☃ >= 0) && (☃ < this.a) && (☃ < this.b)) {
          if (☃) {
            ☃ = this.c[(this.a - ☃ - 1 + ☃ * this.a)];
          } else {
            ☃ = this.c[(☃ + ☃ * this.a)];
          }
        }
        adq ☃ = ☃.c(☃, ☃);
        if ((☃ != null) || (☃ != null))
        {
          if (((☃ == null) && (☃ != null)) || ((☃ != null) && (☃ == null))) {
            return false;
          }
          if (☃.b() != ☃.b()) {
            return false;
          }
          if ((☃.i() != 32767) && (☃.i() != ☃.i())) {
            return false;
          }
        }
      }
    }
    return true;
  }
  
  public adq a(abc ☃)
  {
    adq ☃ = b().k();
    if (this.e) {
      for (int ☃ = 0; ☃ < ☃.u_(); ☃++)
      {
        adq ☃ = ☃.a(☃);
        if ((☃ != null) && (☃.n())) {
          ☃.d((dn)☃.o().b());
        }
      }
    }
    return ☃;
  }
  
  public int a()
  {
    return this.a * this.b;
  }
}
