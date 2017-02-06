import java.util.List;
import java.util.Random;

public class abf
  extends aau
{
  public qg a = new qv("Enchant", true, 2)
  {
    public int w_()
    {
      return 64;
    }
    
    public void v_()
    {
      super.v_();
      abf.this.a(this);
    }
  };
  private aht j;
  private cj k;
  private Random l = new Random();
  public int f;
  public int[] g = new int[3];
  public int[] h = { -1, -1, -1 };
  public int[] i = { -1, -1, -1 };
  
  public abf(zi ☃, aht ☃)
  {
    this(☃, ☃, cj.a);
  }
  
  public abf(zi ☃, aht ☃, cj ☃)
  {
    this.j = ☃;
    this.k = ☃;
    this.f = ☃.e.cQ();
    a(new abt(this.a, 0, 15, 47)
    {
      public boolean a(adq ☃)
      {
        return true;
      }
      
      public int a()
      {
        return 1;
      }
    });
    a(new abt(this.a, 1, 35, 47)
    {
      public boolean a(adq ☃)
      {
        return (☃.b() == ads.bd) && (act.a(☃.i()) == act.l);
      }
    });
    for (int ☃ = 0; ☃ < 3; ☃++) {
      for (int ☃ = 0; ☃ < 9; ☃++) {
        a(new abt(☃, ☃ + ☃ * 9 + 9, 8 + ☃ * 18, 84 + ☃ * 18));
      }
    }
    for (int ☃ = 0; ☃ < 9; ☃++) {
      a(new abt(☃, ☃, 8 + ☃ * 18, 142));
    }
  }
  
  protected void c(aba ☃)
  {
    ☃.a(this, 0, this.g[0]);
    ☃.a(this, 1, this.g[1]);
    ☃.a(this, 2, this.g[2]);
    ☃.a(this, 3, this.f & 0xFFFFFFF0);
    ☃.a(this, 4, this.h[0]);
    ☃.a(this, 5, this.h[1]);
    ☃.a(this, 6, this.h[2]);
    ☃.a(this, 7, this.i[0]);
    ☃.a(this, 8, this.i[1]);
    ☃.a(this, 9, this.i[2]);
  }
  
  public void a(aba ☃)
  {
    super.a(☃);
    
    c(☃);
  }
  
  public void b()
  {
    super.b();
    for (int ☃ = 0; ☃ < this.e.size(); ☃++)
    {
      aba ☃ = (aba)this.e.get(☃);
      c(☃);
    }
  }
  
  public void b(int ☃, int ☃)
  {
    if ((☃ >= 0) && (☃ <= 2)) {
      this.g[☃] = ☃;
    } else if (☃ == 3) {
      this.f = ☃;
    } else if ((☃ >= 4) && (☃ <= 6)) {
      this.h[(☃ - 4)] = ☃;
    } else if ((☃ >= 7) && (☃ <= 9)) {
      this.i[(☃ - 7)] = ☃;
    } else {
      super.b(☃, ☃);
    }
  }
  
  public void a(qg ☃)
  {
    if (☃ == this.a)
    {
      adq ☃ = ☃.a(0);
      if ((☃ == null) || (!☃.v()))
      {
        for (int ☃ = 0; ☃ < 3; ☃++)
        {
          this.g[☃] = 0;
          this.h[☃] = -1;
          this.i[☃] = -1;
        }
      }
      else if (!this.j.E)
      {
        int ☃ = 0;
        for (int ☃ = -1; ☃ <= 1; ☃++) {
          for (int ☃ = -1; ☃ <= 1; ☃++) {
            if ((☃ != 0) || (☃ != 0)) {
              if ((this.j.d(this.k.a(☃, 0, ☃))) && (this.j.d(this.k.a(☃, 1, ☃))))
              {
                if (this.j.o(this.k.a(☃ * 2, 0, ☃ * 2)).t() == aju.X) {
                  ☃++;
                }
                if (this.j.o(this.k.a(☃ * 2, 1, ☃ * 2)).t() == aju.X) {
                  ☃++;
                }
                if ((☃ != 0) && (☃ != 0))
                {
                  if (this.j.o(this.k.a(☃ * 2, 0, ☃)).t() == aju.X) {
                    ☃++;
                  }
                  if (this.j.o(this.k.a(☃ * 2, 1, ☃)).t() == aju.X) {
                    ☃++;
                  }
                  if (this.j.o(this.k.a(☃, 0, ☃ * 2)).t() == aju.X) {
                    ☃++;
                  }
                  if (this.j.o(this.k.a(☃, 1, ☃ * 2)).t() == aju.X) {
                    ☃++;
                  }
                }
              }
            }
          }
        }
        this.l.setSeed(this.f);
        for (int ☃ = 0; ☃ < 3; ☃++)
        {
          this.g[☃] = ago.a(this.l, ☃, ☃, ☃);
          this.h[☃] = -1;
          this.i[☃] = -1;
          if (this.g[☃] < ☃ + 1) {
            this.g[☃] = 0;
          }
        }
        for (int ☃ = 0; ☃ < 3; ☃++) {
          if (this.g[☃] > 0)
          {
            List<agp> ☃ = a(☃, ☃, this.g[☃]);
            if ((☃ != null) && (!☃.isEmpty()))
            {
              agp ☃ = (agp)☃.get(this.l.nextInt(☃.size()));
              this.h[☃] = agm.b(☃.b);
              this.i[☃] = ☃.c;
            }
          }
        }
        b();
      }
    }
  }
  
  public boolean a(zj ☃, int ☃)
  {
    adq ☃ = this.a.a(0);
    adq ☃ = this.a.a(1);
    
    int ☃ = ☃ + 1;
    if (((☃ == null) || (☃.b < ☃)) && (!☃.bJ.d)) {
      return false;
    }
    if ((this.g[☃] > 0) && (☃ != null) && (((☃.bK >= ☃) && (☃.bK >= this.g[☃])) || (☃.bJ.d)))
    {
      if (!this.j.E)
      {
        List<agp> ☃ = a(☃, ☃, this.g[☃]);
        boolean ☃ = ☃.b() == ads.aS;
        if (☃ != null)
        {
          ☃.b(☃);
          if (☃) {
            ☃.a(ads.cn);
          }
          for (int ☃ = 0; ☃ < ☃.size(); ☃++)
          {
            agp ☃ = (agp)☃.get(☃);
            if (☃) {
              ads.cn.a(☃, ☃);
            } else {
              ☃.a(☃.b, ☃.c);
            }
          }
          if (!☃.bJ.d)
          {
            ☃.b -= ☃;
            if (☃.b <= 0) {
              this.a.a(1, null);
            }
          }
          ☃.b(nt.Y);
          this.a.v_();
          this.f = ☃.cQ();
          a(this.a);
        }
      }
      return true;
    }
    return false;
  }
  
  private List<agp> a(adq ☃, int ☃, int ☃)
  {
    this.l.setSeed(this.f + ☃);
    
    List<agp> ☃ = ago.b(this.l, ☃, ☃, false);
    if ((☃.b() == ads.aS) && (☃.size() > 1)) {
      ☃.remove(this.l.nextInt(☃.size()));
    }
    return ☃;
  }
  
  public int e()
  {
    adq ☃ = this.a.a(1);
    if (☃ == null) {
      return 0;
    }
    return ☃.b;
  }
  
  public void b(zj ☃)
  {
    super.b(☃);
    if (this.j.E) {
      return;
    }
    for (int ☃ = 0; ☃ < this.a.u_(); ☃++)
    {
      adq ☃ = this.a.b(☃);
      if (☃ != null) {
        ☃.a(☃, false);
      }
    }
  }
  
  public boolean a(zj ☃)
  {
    if (this.j.o(this.k).t() != aju.bC) {
      return false;
    }
    if (☃.e(this.k.p() + 0.5D, this.k.q() + 0.5D, this.k.r() + 0.5D) > 64.0D) {
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
        if (!a(☃, 2, 38, true)) {
          return null;
        }
      }
      else if (☃ == 1)
      {
        if (!a(☃, 2, 38, true)) {
          return null;
        }
      }
      else if ((☃.b() == ads.bd) && (act.a(☃.i()) == act.l))
      {
        if (!a(☃, 1, 2, true)) {
          return null;
        }
      }
      else if ((!((abt)this.c.get(0)).e()) && (((abt)this.c.get(0)).a(☃)))
      {
        if ((☃.n()) && (☃.b == 1))
        {
          ((abt)this.c.get(0)).d(☃.k());
          ☃.b = 0;
        }
        else if (☃.b >= 1)
        {
          ((abt)this.c.get(0)).d(new adq(☃.b(), 1, ☃.i()));
          ☃.b -= 1;
        }
      }
      else {
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
