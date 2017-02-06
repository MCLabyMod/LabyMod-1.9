import java.util.List;

public class aax
  extends aau
{
  private qg a;
  private final aax.a f;
  
  public aax(qg ☃, qg ☃)
  {
    this.a = ☃;
    
    a(this.f = new aax.a(☃, 0, 136, 110));
    
    int ☃ = 36;
    int ☃ = 137;
    for (int ☃ = 0; ☃ < 3; ☃++) {
      for (int ☃ = 0; ☃ < 9; ☃++) {
        a(new abt(☃, ☃ + ☃ * 9 + 9, ☃ + ☃ * 18, ☃ + ☃ * 18));
      }
    }
    for (int ☃ = 0; ☃ < 9; ☃++) {
      a(new abt(☃, ☃, ☃ + ☃ * 18, 58 + ☃));
    }
  }
  
  public void a(aba ☃)
  {
    super.a(☃);
    ☃.a(this, this.a);
  }
  
  public void b(int ☃, int ☃)
  {
    this.a.b(☃, ☃);
  }
  
  public qg e()
  {
    return this.a;
  }
  
  public void b(zj ☃)
  {
    super.b(☃);
    if ((☃ == null) || (☃.l.E)) {
      return;
    }
    adq ☃ = this.f.a(this.f.a());
    if (☃ != null) {
      ☃.a(☃, false);
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
      if (☃ == 0)
      {
        if (!a(☃, 1, 37, true)) {
          return null;
        }
        ☃.a(☃, ☃);
      }
      else if ((!this.f.e()) && (this.f.a(☃)) && (☃.b == 1))
      {
        if (!a(☃, 0, 1, false)) {
          return null;
        }
      }
      else if ((☃ >= 1) && (☃ < 28))
      {
        if (!a(☃, 28, 37, false)) {
          return null;
        }
      }
      else if ((☃ >= 28) && (☃ < 37))
      {
        if (!a(☃, 1, 28, false)) {
          return null;
        }
      }
      else if (!a(☃, 1, 37, false))
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
  
  class a
    extends abt
  {
    public a(qg ☃, int ☃, int ☃, int ☃)
    {
      super(☃, ☃, ☃);
    }
    
    public boolean a(adq ☃)
    {
      if (☃ != null) {
        return (☃.b() == ads.bY) || (☃.b() == ads.k) || (☃.b() == ads.m) || (☃.b() == ads.l);
      }
      return false;
    }
    
    public int a()
    {
      return 1;
    }
  }
}
