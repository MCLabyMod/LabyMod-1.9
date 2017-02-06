import java.util.List;

public class abk
  extends aau
{
  private qg a;
  private wk f;
  
  public abk(qg ☃, qg ☃, final wk ☃, zj ☃)
  {
    this.a = ☃;
    this.f = ☃;
    int ☃ = 3;
    ☃.b(☃);
    
    int ☃ = (☃ - 4) * 18;
    
    a(new abt(☃, 0, 8, 18)
    {
      public boolean a(adq ☃)
      {
        return (super.a(☃)) && (☃.b() == ads.aC) && (!e());
      }
    });
    a(new abt(☃, 1, 8, 36)
    {
      public boolean a(adq ☃)
      {
        return (super.a(☃)) && (☃.cZ().j()) && (wl.b(☃.b()));
      }
      
      public boolean b()
      {
        return ☃.cZ().j();
      }
    });
    if (☃.dk()) {
      for (int ☃ = 0; ☃ < ☃; ☃++) {
        for (int ☃ = 0; ☃ < 5; ☃++) {
          a(new abt(☃, 2 + ☃ + ☃ * 5, 80 + ☃ * 18, 18 + ☃ * 18));
        }
      }
    }
    for (int ☃ = 0; ☃ < 3; ☃++) {
      for (int ☃ = 0; ☃ < 9; ☃++) {
        a(new abt(☃, ☃ + ☃ * 9 + 9, 8 + ☃ * 18, 102 + ☃ * 18 + ☃));
      }
    }
    for (int ☃ = 0; ☃ < 9; ☃++) {
      a(new abt(☃, ☃, 8 + ☃ * 18, 160 + ☃));
    }
  }
  
  public boolean a(zj ☃)
  {
    return (this.a.a(☃)) && (this.f.au()) && (this.f.g(☃) < 8.0F);
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
      else if ((a(1).a(☃)) && (!a(1).e()))
      {
        if (!a(☃, 1, 2, false)) {
          return null;
        }
      }
      else if (a(0).a(☃))
      {
        if (!a(☃, 0, 1, false)) {
          return null;
        }
      }
      else if ((this.a.u_() <= 2) || (!a(☃, 2, this.a.u_(), false))) {
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
