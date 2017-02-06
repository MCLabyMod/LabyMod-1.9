import java.util.Random;

public class aqb
  extends aqj
  implements qg
{
  private static final Random f = new Random();
  private adq[] g = new adq[9];
  protected String a;
  
  public int u_()
  {
    return 9;
  }
  
  public adq a(int ☃)
  {
    return this.g[☃];
  }
  
  public adq a(int ☃, int ☃)
  {
    adq ☃ = qh.a(this.g, ☃, ☃);
    if (☃ != null) {
      v_();
    }
    return ☃;
  }
  
  public adq b(int ☃)
  {
    return qh.a(this.g, ☃);
  }
  
  public int m()
  {
    int ☃ = -1;
    int ☃ = 1;
    for (int ☃ = 0; ☃ < this.g.length; ☃++) {
      if ((this.g[☃] != null) && (f.nextInt(☃++) == 0)) {
        ☃ = ☃;
      }
    }
    return ☃;
  }
  
  public void a(int ☃, adq ☃)
  {
    this.g[☃] = ☃;
    if ((☃ != null) && (☃.b > w_())) {
      ☃.b = w_();
    }
    v_();
  }
  
  public int a(adq ☃)
  {
    for (int ☃ = 0; ☃ < this.g.length; ☃++) {
      if ((this.g[☃] == null) || (this.g[☃].b() == null))
      {
        a(☃, ☃);
        return ☃;
      }
    }
    return -1;
  }
  
  public String h_()
  {
    return o_() ? this.a : "container.dispenser";
  }
  
  public void a(String ☃)
  {
    this.a = ☃;
  }
  
  public boolean o_()
  {
    return this.a != null;
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    du ☃ = ☃.c("Items", 10);
    this.g = new adq[u_()];
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      dn ☃ = ☃.b(☃);
      int ☃ = ☃.f("Slot") & 0xFF;
      if ((☃ >= 0) && (☃ < this.g.length)) {
        this.g[☃] = adq.a(☃);
      }
    }
    if (☃.b("CustomName", 8)) {
      this.a = ☃.l("CustomName");
    }
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    du ☃ = new du();
    for (int ☃ = 0; ☃ < this.g.length; ☃++) {
      if (this.g[☃] != null)
      {
        dn ☃ = new dn();
        ☃.a("Slot", (byte)☃);
        this.g[☃].b(☃);
        ☃.a(☃);
      }
    }
    ☃.a("Items", ☃);
    if (o_()) {
      ☃.a("CustomName", this.a);
    }
  }
  
  public int w_()
  {
    return 64;
  }
  
  public boolean a(zj ☃)
  {
    if (this.b.r(this.c) != this) {
      return false;
    }
    if (☃.e(this.c.p() + 0.5D, this.c.q() + 0.5D, this.c.r() + 0.5D) > 64.0D) {
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
  
  public String k()
  {
    return "minecraft:dispenser";
  }
  
  public aau a(zi ☃, zj ☃)
  {
    return new abe(☃, this);
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
  
  public void l()
  {
    for (int ☃ = 0; ☃ < this.g.length; ☃++) {
      this.g[☃] = null;
    }
  }
}
