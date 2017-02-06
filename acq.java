import java.util.List;

public abstract class acq
{
  public static final acq[] a = new acq[12];
  public static final acq b = new acq(0, "buildingBlocks")
  {
    public ado e()
    {
      return ado.a(aju.V);
    }
  };
  public static final acq c = new acq(1, "decorations")
  {
    public ado e()
    {
      return ado.a(aju.cF);
    }
    
    public int f()
    {
      return akw.b.f.a();
    }
  };
  public static final acq d = new acq(2, "redstone")
  {
    public ado e()
    {
      return ads.aE;
    }
  };
  public static final acq e = new acq(3, "transportation")
  {
    public ado e()
    {
      return ado.a(aju.D);
    }
  };
  public static final acq f = new acq(4, "misc")
  {
    public ado e()
    {
      return ads.aA;
    }
  }.a(new agn[] { agn.a });
  public static final acq g = new acq(5, "search")
  {
    public ado e()
    {
      return ads.aX;
    }
  }.a("item_search.png");
  public static final acq h = new acq(6, "food")
  {
    public ado e()
    {
      return ads.e;
    }
  };
  public static final acq i = new acq(7, "tools")
  {
    public ado e()
    {
      return ads.c;
    }
  }.a(new agn[] { agn.h, agn.i, agn.j });
  public static final acq j = new acq(8, "combat")
  {
    public ado e()
    {
      return ads.D;
    }
  }.a(new agn[] { agn.b, agn.c, agn.f, agn.d, agn.e, agn.k, agn.g });
  public static final acq k = new acq(9, "brewing")
  {
    public ado e()
    {
      return ads.bG;
    }
  };
  public static final acq l = new acq(10, "materials")
  {
    public ado e()
    {
      return ads.A;
    }
  };
  public static final acq m = new acq(11, "inventory")
  {
    public ado e()
    {
      return ado.a(aju.ae);
    }
  }.a("inventory.png").k().i();
  private final int n;
  private final String o;
  private String p = "items.png";
  private boolean q = true;
  private boolean r = true;
  private agn[] s;
  private adq t;
  
  public acq(int ☃, String ☃)
  {
    this.n = ☃;
    this.o = ☃;
    
    a[☃] = this;
  }
  
  public int a()
  {
    return this.n;
  }
  
  public String b()
  {
    return this.o;
  }
  
  public String c()
  {
    return "itemGroup." + b();
  }
  
  public adq d()
  {
    if (this.t == null) {
      this.t = new adq(e(), 1, f());
    }
    return this.t;
  }
  
  public abstract ado e();
  
  public int f()
  {
    return 0;
  }
  
  public String g()
  {
    return this.p;
  }
  
  public acq a(String ☃)
  {
    this.p = ☃;
    return this;
  }
  
  public boolean h()
  {
    return this.r;
  }
  
  public acq i()
  {
    this.r = false;
    return this;
  }
  
  public boolean j()
  {
    return this.q;
  }
  
  public acq k()
  {
    this.q = false;
    return this;
  }
  
  public int l()
  {
    return this.n % 6;
  }
  
  public boolean m()
  {
    return this.n < 6;
  }
  
  public agn[] n()
  {
    return this.s;
  }
  
  public acq a(agn... ☃)
  {
    this.s = ☃;
    return this;
  }
  
  public boolean a(agn ☃)
  {
    if (this.s == null) {
      return false;
    }
    for (agn ☃ : this.s) {
      if (☃ == ☃) {
        return true;
      }
    }
    return false;
  }
  
  public void a(List<adq> ☃)
  {
    for (ado ☃ : ado.f) {
      if ((☃ != null) && 
        (☃.b() == this)) {
        ☃.a(☃, this, ☃);
      }
    }
    if (n() != null) {
      a(☃, n());
    }
  }
  
  public void a(List<adq> ☃, agn... ☃)
  {
    for (agm ☃ : agm.b) {
      if ((☃ != null) && (☃.c != null))
      {
        boolean ☃ = false;
        for (int ☃ = 0; (☃ < ☃.length) && (!☃); ☃++) {
          if (☃.c == ☃[☃]) {
            ☃ = true;
          }
        }
        if (☃) {
          ☃.add(ads.cn.a(new agp(☃, ☃.b())));
        }
      }
    }
  }
}
