import java.util.List;
import java.util.Random;

abstract class awk$n
  extends awg
{
  protected int h = -1;
  private int a;
  private boolean b;
  
  public awk$n() {}
  
  protected awk$n(awk.k ☃, int ☃)
  {
    super(☃);
    if (☃ != null) {
      this.b = ☃.b;
    }
  }
  
  protected void a(dn ☃)
  {
    ☃.a("HPos", this.h);
    ☃.a("VCount", this.a);
    ☃.a("Desert", this.b);
  }
  
  protected void b(dn ☃)
  {
    this.h = ☃.h("HPos");
    this.a = ☃.h("VCount");
    this.b = ☃.p("Desert");
  }
  
  protected awg a(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃)
  {
    cq ☃ = e();
    if (☃ != null) {
      switch (awk.1.a[☃.ordinal()])
      {
      case 1: 
        return awk.a(☃, ☃, ☃, this.l.a - 1, this.l.b + ☃, this.l.c + ☃, cq.e, d());
      case 2: 
        return awk.a(☃, ☃, ☃, this.l.a - 1, this.l.b + ☃, this.l.c + ☃, cq.e, d());
      case 3: 
        return awk.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.c - 1, cq.c, d());
      case 4: 
        return awk.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.c - 1, cq.c, d());
      }
    }
    return null;
  }
  
  protected awg b(awk.k ☃, List<awg> ☃, Random ☃, int ☃, int ☃)
  {
    cq ☃ = e();
    if (☃ != null) {
      switch (awk.1.a[☃.ordinal()])
      {
      case 1: 
        return awk.a(☃, ☃, ☃, this.l.d + 1, this.l.b + ☃, this.l.c + ☃, cq.f, d());
      case 2: 
        return awk.a(☃, ☃, ☃, this.l.d + 1, this.l.b + ☃, this.l.c + ☃, cq.f, d());
      case 3: 
        return awk.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.f + 1, cq.d, d());
      case 4: 
        return awk.a(☃, ☃, ☃, this.l.a + ☃, this.l.b + ☃, this.l.f + 1, cq.d, d());
      }
    }
    return null;
  }
  
  protected int b(aht ☃, avp ☃)
  {
    int ☃ = 0;
    int ☃ = 0;
    cj.a ☃ = new cj.a();
    for (int ☃ = this.l.c; ☃ <= this.l.f; ☃++) {
      for (int ☃ = this.l.a; ☃ <= this.l.d; ☃++)
      {
        ☃.c(☃, 64, ☃);
        if (☃.b(☃))
        {
          ☃ += Math.max(☃.q(☃).q(), ☃.s.i());
          ☃++;
        }
      }
    }
    if (☃ == 0) {
      return -1;
    }
    return ☃ / ☃;
  }
  
  protected static boolean a(avp ☃)
  {
    return (☃ != null) && (☃.b > 10);
  }
  
  protected void a(aht ☃, avp ☃, int ☃, int ☃, int ☃, int ☃)
  {
    if (this.a >= ☃) {
      return;
    }
    for (int ☃ = this.a; ☃ < ☃; ☃++)
    {
      int ☃ = a(☃ + ☃, ☃);
      int ☃ = d(☃);
      int ☃ = b(☃ + ☃, ☃);
      if (!☃.b(new cj(☃, ☃, ☃))) {
        break;
      }
      this.a += 1;
      
      ze ☃ = new ze(☃);
      ☃.b(☃ + 0.5D, ☃, ☃ + 0.5D, 0.0F, 0.0F);
      ☃.a(☃.D(new cj(☃)), null);
      ☃.l(c(☃, ☃.cZ()));
      ☃.a(☃);
    }
  }
  
  protected int c(int ☃, int ☃)
  {
    return ☃;
  }
  
  protected arc a(arc ☃)
  {
    if (this.b)
    {
      if ((☃.t() == aju.r) || (☃.t() == aju.s)) {
        return aju.A.u();
      }
      if (☃.t() == aju.e) {
        return aju.A.a(aog.a.a.a());
      }
      if (☃.t() == aju.f) {
        return aju.A.a(aog.a.c.a());
      }
      if (☃.t() == aju.ad) {
        return aju.bO.u().a(aot.a, ☃.c(aot.a));
      }
      if (☃.t() == aju.aw) {
        return aju.bO.u().a(aot.a, ☃.c(aot.a));
      }
      if (☃.t() == aju.n) {
        return aju.A.u();
      }
    }
    return ☃;
  }
  
  protected void a(aht ☃, arc ☃, int ☃, int ☃, int ☃, avp ☃)
  {
    arc ☃ = a(☃);
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  protected void a(aht ☃, avp ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, arc ☃, arc ☃, boolean ☃)
  {
    arc ☃ = a(☃);
    arc ☃ = a(☃);
    super.a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  protected void b(aht ☃, arc ☃, int ☃, int ☃, int ☃, avp ☃)
  {
    arc ☃ = a(☃);
    super.b(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  protected void a(boolean ☃)
  {
    this.b = ☃;
  }
}
