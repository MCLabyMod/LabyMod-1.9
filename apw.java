import java.util.Arrays;

public class apw
  extends aqj
  implements ky, qy
{
  private static final int[] a = { 3 };
  private static final int[] f = { 0, 1, 2, 3 };
  private static final int[] g = { 0, 1, 2, 4 };
  private adq[] h = new adq[5];
  private int i;
  private boolean[] j;
  private ado k;
  private String l;
  private int m;
  
  public String h_()
  {
    return o_() ? this.l : "container.brewing";
  }
  
  public boolean o_()
  {
    return (this.l != null) && (!this.l.isEmpty());
  }
  
  public void a(String ☃)
  {
    this.l = ☃;
  }
  
  public int u_()
  {
    return this.h.length;
  }
  
  public void c()
  {
    if ((this.m <= 0) && (this.h[4] != null) && (this.h[4].b() == ads.bN))
    {
      this.m = 20;
      this.h[4].b -= 1;
      if (this.h[4].b <= 0) {
        this.h[4] = null;
      }
      v_();
    }
    boolean ☃ = n();
    boolean ☃ = this.i > 0;
    if (☃)
    {
      this.i -= 1;
      
      boolean ☃ = this.i == 0;
      if ((☃) && (☃))
      {
        o();
        v_();
      }
      else if (!☃)
      {
        this.i = 0;
        v_();
      }
      else if (this.k != this.h[3].b())
      {
        this.i = 0;
        v_();
      }
    }
    else if ((☃) && (this.m > 0))
    {
      this.m -= 1;
      this.i = 400;
      this.k = this.h[3].b();
      v_();
    }
    if (!this.b.E)
    {
      boolean[] ☃ = m();
      if (!Arrays.equals(☃, this.j))
      {
        this.j = ☃;
        arc ☃ = this.b.o(v());
        if (!(☃.t() instanceof ajx)) {
          return;
        }
        for (int ☃ = 0; ☃ < ajx.a.length; ☃++) {
          ☃ = ☃.a(ajx.a[☃], Boolean.valueOf(☃[☃]));
        }
        this.b.a(this.c, ☃, 2);
      }
    }
  }
  
  public boolean[] m()
  {
    boolean[] ☃ = new boolean[3];
    for (int ☃ = 0; ☃ < 3; ☃++) {
      if (this.h[☃] != null) {
        ☃[☃] = true;
      }
    }
    return ☃;
  }
  
  private boolean n()
  {
    if ((this.h[3] == null) || (this.h[3].b <= 0)) {
      return false;
    }
    adq ☃ = this.h[3];
    if (!aff.a(☃)) {
      return false;
    }
    for (int ☃ = 0; ☃ < 3; ☃++)
    {
      adq ☃ = this.h[☃];
      if (☃ != null) {
        if (aff.a(☃, ☃)) {
          return true;
        }
      }
    }
    return false;
  }
  
  private void o()
  {
    adq ☃ = this.h[3];
    for (int ☃ = 0; ☃ < 3; ☃++) {
      this.h[☃] = aff.d(☃, this.h[☃]);
    }
    ☃.b -= 1;
    cj ☃ = v();
    if (☃.b().r())
    {
      adq ☃ = new adq(☃.b().q());
      if (☃.b <= 0) {
        ☃ = ☃;
      } else {
        qj.a(this.b, ☃.p(), ☃.q(), ☃.r(), ☃);
      }
    }
    if (☃.b <= 0) {
      ☃ = null;
    }
    this.h[3] = ☃;
    
    this.b.b(1035, ☃, 0);
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    
    du ☃ = ☃.c("Items", 10);
    this.h = new adq[u_()];
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      dn ☃ = ☃.b(☃);
      int ☃ = ☃.f("Slot");
      if ((☃ >= 0) && (☃ < this.h.length)) {
        this.h[☃] = adq.a(☃);
      }
    }
    this.i = ☃.g("BrewTime");
    if (☃.b("CustomName", 8)) {
      this.l = ☃.l("CustomName");
    }
    this.m = ☃.f("Fuel");
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    
    ☃.a("BrewTime", (short)this.i);
    du ☃ = new du();
    for (int ☃ = 0; ☃ < this.h.length; ☃++) {
      if (this.h[☃] != null)
      {
        dn ☃ = new dn();
        ☃.a("Slot", (byte)☃);
        this.h[☃].b(☃);
        ☃.a(☃);
      }
    }
    ☃.a("Items", ☃);
    if (o_()) {
      ☃.a("CustomName", this.l);
    }
    ☃.a("Fuel", (byte)this.m);
  }
  
  public adq a(int ☃)
  {
    if ((☃ >= 0) && (☃ < this.h.length)) {
      return this.h[☃];
    }
    return null;
  }
  
  public adq a(int ☃, int ☃)
  {
    return qh.a(this.h, ☃, ☃);
  }
  
  public adq b(int ☃)
  {
    return qh.a(this.h, ☃);
  }
  
  public void a(int ☃, adq ☃)
  {
    if ((☃ >= 0) && (☃ < this.h.length)) {
      this.h[☃] = ☃;
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
    if (☃ == 3) {
      return aff.a(☃);
    }
    ado ☃ = ☃.b();
    if (☃ == 4) {
      return ☃ == ads.bN;
    }
    return (☃ == ads.bG) || (☃ == ads.bH) || (☃ == ads.bI) || (☃ == ads.bJ);
  }
  
  public int[] a(cq ☃)
  {
    if (☃ == cq.b) {
      return a;
    }
    if (☃ == cq.a) {
      return f;
    }
    return g;
  }
  
  public boolean a(int ☃, adq ☃, cq ☃)
  {
    return b(☃, ☃);
  }
  
  public boolean b(int ☃, adq ☃, cq ☃)
  {
    if (☃ == 3) {
      return ☃.b() == ads.bJ;
    }
    return true;
  }
  
  public String k()
  {
    return "minecraft:brewing_stand";
  }
  
  public aau a(zi ☃, zj ☃)
  {
    return new aay(☃, this);
  }
  
  public int c_(int ☃)
  {
    switch (☃)
    {
    case 0: 
      return this.i;
    case 1: 
      return this.m;
    }
    return 0;
  }
  
  public void b(int ☃, int ☃)
  {
    switch (☃)
    {
    case 0: 
      this.i = ☃;
      break;
    case 1: 
      this.m = ☃;
    }
  }
  
  public int g()
  {
    return 2;
  }
  
  public void l()
  {
    Arrays.fill(this.h, null);
  }
}
