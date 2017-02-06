import java.util.Random;

public abstract class aak
  extends aah
  implements qs, qu
{
  private adq[] a = new adq[36];
  private boolean b = true;
  private kk c;
  private long d;
  
  public aak(aht ☃)
  {
    super(☃);
  }
  
  public aak(aht ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃);
  }
  
  public void a(rc ☃)
  {
    super.a(☃);
    if (this.l.U().b("doEntityDrops")) {
      qj.a(this.l, this, this);
    }
  }
  
  public adq a(int ☃)
  {
    f(null);
    return this.a[☃];
  }
  
  public adq a(int ☃, int ☃)
  {
    f(null);
    
    return qh.a(this.a, ☃, ☃);
  }
  
  public adq b(int ☃)
  {
    f(null);
    if (this.a[☃] != null)
    {
      adq ☃ = this.a[☃];
      this.a[☃] = null;
      return ☃;
    }
    return null;
  }
  
  public void a(int ☃, adq ☃)
  {
    f(null);
    this.a[☃] = ☃;
    if ((☃ != null) && (☃.b > w_())) {
      ☃.b = w_();
    }
  }
  
  public void v_() {}
  
  public boolean a(zj ☃)
  {
    if (this.F) {
      return false;
    }
    if (☃.h(this) > 64.0D) {
      return false;
    }
    return true;
  }
  
  public void b(zj ☃) {}
  
  public void c(zj ☃) {}
  
  public boolean b(int ☃, adq ☃)
  {
    return true;
  }
  
  public String h_()
  {
    return o_() ? bf() : "container.minecart";
  }
  
  public int w_()
  {
    return 64;
  }
  
  public rr c(int ☃)
  {
    this.b = false;
    return super.c(☃);
  }
  
  public void T()
  {
    if (this.b) {
      qj.a(this.l, this, this);
    }
    super.T();
  }
  
  public void b(boolean ☃)
  {
    this.b = ☃;
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    if (this.c != null)
    {
      ☃.a("LootTable", this.c.toString());
      if (this.d != 0L) {
        ☃.a("LootTableSeed", this.d);
      }
    }
    else
    {
      du ☃ = new du();
      for (int ☃ = 0; ☃ < this.a.length; ☃++) {
        if (this.a[☃] != null)
        {
          dn ☃ = new dn();
          ☃.a("Slot", (byte)☃);
          this.a[☃].b(☃);
          ☃.a(☃);
        }
      }
      ☃.a("Items", ☃);
    }
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    this.a = new adq[u_()];
    if (☃.b("LootTable", 8))
    {
      this.c = new kk(☃.l("LootTable"));
      this.d = ☃.i("LootTableSeed");
    }
    else
    {
      du ☃ = ☃.c("Items", 10);
      for (int ☃ = 0; ☃ < ☃.c(); ☃++)
      {
        dn ☃ = ☃.b(☃);
        int ☃ = ☃.f("Slot") & 0xFF;
        if ((☃ >= 0) && (☃ < this.a.length)) {
          this.a[☃] = adq.a(☃);
        }
      }
    }
  }
  
  public boolean a(zj ☃, adq ☃, qm ☃)
  {
    if (!this.l.E) {
      ☃.a(this);
    }
    return true;
  }
  
  protected void r()
  {
    float ☃ = 0.98F;
    if (this.c == null)
    {
      int ☃ = 15 - aau.b(this);
      ☃ += ☃ * 0.001F;
    }
    this.s *= ☃;
    this.t *= 0.0D;
    this.u *= ☃;
  }
  
  public int c_(int ☃)
  {
    return 0;
  }
  
  public void b(int ☃, int ☃) {}
  
  public int g()
  {
    return 0;
  }
  
  public boolean x_()
  {
    return false;
  }
  
  public void a(qr ☃) {}
  
  public qr y_()
  {
    return qr.a;
  }
  
  public void f(zj ☃)
  {
    if (this.c != null)
    {
      azy ☃ = this.l.ak().a(this.c);
      this.c = null;
      Random ☃;
      Random ☃;
      if (this.d == 0L) {
        ☃ = new Random();
      } else {
        ☃ = new Random(this.d);
      }
      azz.a ☃ = new azz.a((lp)this.l);
      if (☃ != null) {
        ☃.a(☃.db());
      }
      ☃.a(this, ☃, ☃.a());
    }
  }
  
  public void l()
  {
    f(null);
    for (int ☃ = 0; ☃ < this.a.length; ☃++) {
      this.a[☃] = null;
    }
  }
  
  public void a(kk ☃, long ☃)
  {
    this.c = ☃;
    this.d = ☃;
  }
  
  public kk b()
  {
    return this.c;
  }
}
