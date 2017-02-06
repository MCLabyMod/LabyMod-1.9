import java.util.List;

public class aay
  extends aau
{
  private qg a;
  private final abt f;
  private int g;
  private int h;
  
  public aay(zi ☃, qg ☃)
  {
    this.a = ☃;
    
    a(new aay.c(☃.e, ☃, 0, 56, 51));
    a(new aay.c(☃.e, ☃, 1, 79, 58));
    a(new aay.c(☃.e, ☃, 2, 102, 51));
    this.f = a(new aay.b(☃, 3, 79, 17));
    a(new aay.a(☃, 4, 17, 17));
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
      if (this.g != this.a.c_(0)) {
        ☃.a(this, 0, this.a.c_(0));
      }
      if (this.h != this.a.c_(1)) {
        ☃.a(this, 1, this.a.c_(1));
      }
    }
    this.g = this.a.c_(0);
    this.h = this.a.c_(1);
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
      if (((☃ >= 0) && (☃ <= 2)) || (☃ == 3) || (☃ == 4))
      {
        if (!a(☃, 5, 41, true)) {
          return null;
        }
        ☃.a(☃, ☃);
      }
      else if ((!this.f.e()) && (this.f.a(☃)))
      {
        if (!a(☃, 3, 4, false)) {
          return null;
        }
      }
      else if (aay.c.c_(☃))
      {
        if (!a(☃, 0, 3, false)) {
          return null;
        }
      }
      else if (aay.a.b_(☃))
      {
        if (!a(☃, 4, 5, false)) {
          return null;
        }
      }
      else if ((☃ >= 5) && (☃ < 32))
      {
        if (!a(☃, 32, 41, false)) {
          return null;
        }
      }
      else if ((☃ >= 32) && (☃ < 41))
      {
        if (!a(☃, 5, 32, false)) {
          return null;
        }
      }
      else if (!a(☃, 5, 41, false))
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
  
  static class c
    extends abt
  {
    private zj a;
    
    public c(zj ☃, qg ☃, int ☃, int ☃, int ☃)
    {
      super(☃, ☃, ☃);
      this.a = ☃;
    }
    
    public boolean a(adq ☃)
    {
      return c_(☃);
    }
    
    public int a()
    {
      return 1;
    }
    
    public void a(zj ☃, adq ☃)
    {
      if (afg.c(☃) != afh.b) {
        this.a.b(nk.B);
      }
      super.a(☃, ☃);
    }
    
    public static boolean c_(adq ☃)
    {
      if (☃ == null) {
        return false;
      }
      ado ☃ = ☃.b();
      return (☃ == ads.bG) || (☃ == ads.bJ) || (☃ == ads.bH) || (☃ == ads.bI);
    }
  }
  
  static class b
    extends abt
  {
    public b(qg ☃, int ☃, int ☃, int ☃)
    {
      super(☃, ☃, ☃);
    }
    
    public boolean a(adq ☃)
    {
      return (☃ != null) && (aff.a(☃));
    }
    
    public int a()
    {
      return 64;
    }
  }
  
  static class a
    extends abt
  {
    public a(qg ☃, int ☃, int ☃, int ☃)
    {
      super(☃, ☃, ☃);
    }
    
    public boolean a(adq ☃)
    {
      return b_(☃);
    }
    
    public static boolean b_(adq ☃)
    {
      return (☃ != null) && (☃.b() == ads.bN);
    }
    
    public int a()
    {
      return 64;
    }
  }
}
