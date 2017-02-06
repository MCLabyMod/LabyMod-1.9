import java.util.List;

public class abe
  extends aau
{
  private qg a;
  
  public abe(qg ☃, qg ☃)
  {
    this.a = ☃;
    for (int ☃ = 0; ☃ < 3; ☃++) {
      for (int ☃ = 0; ☃ < 3; ☃++) {
        a(new abt(☃, ☃ + ☃ * 3, 62 + ☃ * 18, 17 + ☃ * 18));
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
      if (☃ < 9)
      {
        if (!a(☃, 9, 45, true)) {
          return null;
        }
      }
      else if (!a(☃, 0, 9, false)) {
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
}
