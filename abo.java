import java.util.List;

public class abo
  extends aau
{
  private ahf a;
  private abn f;
  private final aht g;
  
  public abo(zi ☃, ahf ☃, aht ☃)
  {
    this.a = ☃;
    this.g = ☃;
    
    this.f = new abn(☃.e, ☃);
    a(new abt(this.f, 0, 36, 53));
    a(new abt(this.f, 1, 62, 53));
    a(new abp(☃.e, ☃, this.f, 2, 120, 53));
    for (int ☃ = 0; ☃ < 3; ☃++) {
      for (int ☃ = 0; ☃ < 9; ☃++) {
        a(new abt(☃, ☃ + ☃ * 9 + 9, 8 + ☃ * 18, 84 + ☃ * 18));
      }
    }
    for (int ☃ = 0; ☃ < 9; ☃++) {
      a(new abt(☃, ☃, 8 + ☃ * 18, 142));
    }
  }
  
  public abn e()
  {
    return this.f;
  }
  
  public void a(aba ☃)
  {
    super.a(☃);
  }
  
  public void b()
  {
    super.b();
  }
  
  public void a(qg ☃)
  {
    this.f.h();
    super.a(☃);
  }
  
  public void d(int ☃)
  {
    this.f.d(☃);
  }
  
  public void b(int ☃, int ☃) {}
  
  public boolean a(zj ☃)
  {
    return this.a.t_() == ☃;
  }
  
  public adq b(zj ☃, int ☃)
  {
    adq ☃ = null;
    abt ☃ = (abt)this.c.get(☃);
    if ((☃ != null) && (☃.e()))
    {
      adq ☃ = ☃.d();
      ☃ = ☃.k();
      if (☃ == 2)
      {
        if (!a(☃, 3, 39, true)) {
          return null;
        }
        ☃.a(☃, ☃);
      }
      else if ((☃ == 0) || (☃ == 1))
      {
        if (!a(☃, 3, 39, false)) {
          return null;
        }
      }
      else if ((☃ >= 3) && (☃ < 30))
      {
        if (!a(☃, 30, 39, false)) {
          return null;
        }
      }
      else if ((☃ >= 30) && (☃ < 39) && 
        (!a(☃, 3, 30, false)))
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
  
  public void b(zj ☃)
  {
    super.b(☃);
    this.a.a_(null);
    
    super.b(☃);
    if (this.g.E) {
      return;
    }
    adq ☃ = this.f.b(0);
    if (☃ != null) {
      ☃.a(☃, false);
    }
    ☃ = this.f.b(1);
    if (☃ != null) {
      ☃.a(☃, false);
    }
  }
}
