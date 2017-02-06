import java.util.List;

public class abj
  extends aau
{
  private final qg a;
  
  public abj(zi ☃, qg ☃, zj ☃)
  {
    this.a = ☃;
    ☃.b(☃);
    int ☃ = 51;
    for (int ☃ = 0; ☃ < ☃.u_(); ☃++) {
      a(new abt(☃, ☃, 44 + ☃ * 18, 20));
    }
    for (int ☃ = 0; ☃ < 3; ☃++) {
      for (int ☃ = 0; ☃ < 9; ☃++) {
        a(new abt(☃, ☃ + ☃ * 9 + 9, 8 + ☃ * 18, ☃ * 18 + ☃));
      }
    }
    for (int ☃ = 0; ☃ < 9; ☃++) {
      a(new abt(☃, ☃, 8 + ☃ * 18, 58 + ☃));
    }
  }
  
  public boolean a(zj ☃)
  {
    return this.a.a(☃);
  }
  
  public adq b(zj ☃, int ☃)
  {
    adq ☃ = null;
    abt ☃ = (abt)this.c.get(☃);
    if ((☃ != null) && (☃.e()))
    {
      adq ☃ = ☃.d();
      ☃ = ☃.k();
      if (☃ < this.a.u_())
      {
        if (!a(☃, this.a.u_(), this.c.size(), true)) {
          return null;
        }
      }
      else if (!a(☃, 0, this.a.u_(), false)) {
        return null;
      }
      if (☃.b == 0) {
        ☃.d(null);
      } else {
        ☃.f();
      }
    }
    return ☃;
  }
  
  public void b(zj ☃)
  {
    super.b(☃);
    this.a.c(☃);
  }
}
