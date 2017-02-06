import java.util.List;

public class abb
  extends aau
{
  private qg a;
  private int f;
  
  public abb(qg ☃, qg ☃, zj ☃)
  {
    this.a = ☃;
    this.f = (☃.u_() / 9);
    ☃.b(☃);
    
    int ☃ = (this.f - 4) * 18;
    for (int ☃ = 0; ☃ < this.f; ☃++) {
      for (int ☃ = 0; ☃ < 9; ☃++) {
        a(new abt(☃, ☃ + ☃ * 9, 8 + ☃ * 18, 18 + ☃ * 18));
      }
    }
    for (int ☃ = 0; ☃ < 3; ☃++) {
      for (int ☃ = 0; ☃ < 9; ☃++) {
        a(new abt(☃, ☃ + ☃ * 9 + 9, 8 + ☃ * 18, 103 + ☃ * 18 + ☃));
      }
    }
    for (int ☃ = 0; ☃ < 9; ☃++) {
      a(new abt(☃, ☃, 8 + ☃ * 18, 161 + ☃));
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
      if (☃ < this.f * 9)
      {
        if (!a(☃, this.f * 9, this.c.size(), true)) {
          return null;
        }
      }
      else if (!a(☃, 0, this.f * 9, false)) {
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
  
  public qg e()
  {
    return this.a;
  }
}
