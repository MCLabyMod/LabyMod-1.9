import java.util.List;

public class abd
  extends aau
{
  public abc a = new abc(this, 3, 3);
  public qg f = new abr();
  private aht g;
  private cj h;
  
  public abd(zi ☃, aht ☃, cj ☃)
  {
    this.g = ☃;
    this.h = ☃;
    a(new abs(☃.e, this.a, this.f, 0, 124, 35));
    for (int ☃ = 0; ☃ < 3; ☃++) {
      for (int ☃ = 0; ☃ < 3; ☃++) {
        a(new abt(this.a, ☃ + ☃ * 3, 30 + ☃ * 18, 17 + ☃ * 18));
      }
    }
    for (int ☃ = 0; ☃ < 3; ☃++) {
      for (int ☃ = 0; ☃ < 9; ☃++) {
        a(new abt(☃, ☃ + ☃ * 9 + 9, 8 + ☃ * 18, 84 + ☃ * 18));
      }
    }
    for (int ☃ = 0; ☃ < 9; ☃++) {
      a(new abt(☃, ☃, 8 + ☃ * 18, 142));
    }
    a(this.a);
  }
  
  public void a(qg ☃)
  {
    this.f.a(0, afv.a().a(this.a, this.g));
  }
  
  public void b(zj ☃)
  {
    super.b(☃);
    if (this.g.E) {
      return;
    }
    for (int ☃ = 0; ☃ < 9; ☃++)
    {
      adq ☃ = this.a.b(☃);
      if (☃ != null) {
        ☃.a(☃, false);
      }
    }
  }
  
  public boolean a(zj ☃)
  {
    if (this.g.o(this.h).t() != aju.ai) {
      return false;
    }
    if (☃.e(this.h.p() + 0.5D, this.h.q() + 0.5D, this.h.r() + 0.5D) > 64.0D) {
      return false;
    }
    return true;
  }
  
  public adq b(zj ☃, int ☃)
  {
    adq ☃ = null;
    abt ☃ = (abt)this.c.get(☃);
    if ((☃ != null) && (☃.e()))
    {
      adq ☃ = ☃.d();
      ☃ = ☃.k();
      if (☃ == 0)
      {
        if (!a(☃, 10, 46, true)) {
          return null;
        }
        ☃.a(☃, ☃);
      }
      else if ((☃ >= 10) && (☃ < 37))
      {
        if (!a(☃, 37, 46, false)) {
          return null;
        }
      }
      else if ((☃ >= 37) && (☃ < 46))
      {
        if (!a(☃, 10, 37, false)) {
          return null;
        }
      }
      else if (!a(☃, 10, 46, false))
      {
        return null;
      }
      if (☃.b == 0) {
        ☃.d(null);
      } else {
        ☃.f();
      }
      if (☃.b == ☃.b) {
        return null;
      }
      ☃.a(☃, ☃);
    }
    return ☃;
  }
  
  public boolean a(adq ☃, abt ☃)
  {
    return (☃.d != this.f) && (super.a(☃, ☃));
  }
}
