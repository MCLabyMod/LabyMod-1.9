public class ahg
{
  private adq a;
  private adq b;
  private adq c;
  private int d;
  private int e;
  private boolean f;
  
  public ahg(dn ☃)
  {
    a(☃);
  }
  
  public ahg(adq ☃, adq ☃, adq ☃)
  {
    this(☃, ☃, ☃, 0, 7);
  }
  
  public ahg(adq ☃, adq ☃, adq ☃, int ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
    this.e = ☃;
    this.f = true;
  }
  
  public ahg(adq ☃, adq ☃)
  {
    this(☃, null, ☃);
  }
  
  public ahg(adq ☃, ado ☃)
  {
    this(☃, new adq(☃));
  }
  
  public adq a()
  {
    return this.a;
  }
  
  public adq b()
  {
    return this.b;
  }
  
  public boolean c()
  {
    return this.b != null;
  }
  
  public adq d()
  {
    return this.c;
  }
  
  public int e()
  {
    return this.d;
  }
  
  public int f()
  {
    return this.e;
  }
  
  public void g()
  {
    this.d += 1;
  }
  
  public void a(int ☃)
  {
    this.e += ☃;
  }
  
  public boolean h()
  {
    return this.d >= this.e;
  }
  
  public void i()
  {
    this.d = this.e;
  }
  
  public boolean j()
  {
    return this.f;
  }
  
  public void a(dn ☃)
  {
    dn ☃ = ☃.o("buy");
    this.a = adq.a(☃);
    dn ☃ = ☃.o("sell");
    this.c = adq.a(☃);
    if (☃.b("buyB", 10)) {
      this.b = adq.a(☃.o("buyB"));
    }
    if (☃.b("uses", 99)) {
      this.d = ☃.h("uses");
    }
    if (☃.b("maxUses", 99)) {
      this.e = ☃.h("maxUses");
    } else {
      this.e = 7;
    }
    if (☃.b("rewardExp", 1)) {
      this.f = ☃.p("rewardExp");
    } else {
      this.f = true;
    }
  }
  
  public dn k()
  {
    dn ☃ = new dn();
    ☃.a("buy", this.a.b(new dn()));
    ☃.a("sell", this.c.b(new dn()));
    if (this.b != null) {
      ☃.a("buyB", this.b.b(new dn()));
    }
    ☃.a("uses", this.d);
    ☃.a("maxUses", this.e);
    ☃.a("rewardExp", this.f);
    return ☃;
  }
}
