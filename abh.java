import java.util.List;

public class abh
  extends aau
{
  private final qg a;
  private int f;
  private int g;
  private int h;
  private int i;
  
  public abh(zi ☃, qg ☃)
  {
    this.a = ☃;
    
    a(new abt(☃, 0, 56, 17));
    a(new abg(☃, 1, 56, 53));
    a(new abi(☃.e, ☃, 2, 116, 35));
    for (int ☃ = 0; ☃ < 3; ☃++) {
      for (int ☃ = 0; ☃ < 9; ☃++) {
        a(new abt(☃, ☃ + ☃ * 9 + 9, 8 + ☃ * 18, 84 + ☃ * 18));
      }
    }
    for (int ☃ = 0; ☃ < 9; ☃++) {
      a(new abt(☃, ☃, 8 + ☃ * 18, 142));
    }
  }
  
  public void a(aba ☃)
  {
    super.a(☃);
    ☃.a(this, this.a);
  }
  
  public void b()
  {
    super.b();
    for (int ☃ = 0; ☃ < this.e.size(); ☃++)
    {
      aba ☃ = (aba)this.e.get(☃);
      if (this.f != this.a.c_(2)) {
        ☃.a(this, 2, this.a.c_(2));
      }
      if (this.h != this.a.c_(0)) {
        ☃.a(this, 0, this.a.c_(0));
      }
      if (this.i != this.a.c_(1)) {
        ☃.a(this, 1, this.a.c_(1));
      }
      if (this.g != this.a.c_(3)) {
        ☃.a(this, 3, this.a.c_(3));
      }
    }
    this.f = this.a.c_(2);
    this.h = this.a.c_(0);
    this.i = this.a.c_(1);
    this.g = this.a.c_(3);
  }
  
  public void b(int ☃, int ☃)
  {
    this.a.b(☃, ☃);
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
      if (☃ == 2)
      {
        if (!a(☃, 3, 39, true)) {
          return null;
        }
        ☃.a(☃, ☃);
      }
      else if ((☃ == 1) || (☃ == 0))
      {
        if (!a(☃, 3, 39, false)) {
          return null;
        }
      }
      else if (afq.a().a(☃) != null)
      {
        if (!a(☃, 0, 1, false)) {
          return null;
        }
      }
      else if (aqg.c(☃))
      {
        if (!a(☃, 1, 2, false)) {
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
}
