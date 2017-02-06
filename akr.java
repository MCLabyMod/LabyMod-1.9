import java.util.Random;

public abstract class akr
  extends amg
{
  protected static final bbh c = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
  protected final boolean d;
  
  protected akr(boolean ☃)
  {
    super(axe.q);
    this.d = ☃;
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    return c;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    if (☃.o(☃.b()).q()) {
      return super.a(☃, ☃);
    }
    return false;
  }
  
  public boolean b(aht ☃, cj ☃)
  {
    if (☃.o(☃.b()).q()) {
      return true;
    }
    return false;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, Random ☃) {}
  
  public void b(aht ☃, cj ☃, arc ☃, Random ☃)
  {
    if (b(☃, ☃, ☃)) {
      return;
    }
    boolean ☃ = e(☃, ☃, ☃);
    if ((this.d) && (!☃))
    {
      ☃.a(☃, y(☃), 2);
    }
    else if (!this.d)
    {
      ☃.a(☃, x(☃), 2);
      if (!☃) {
        ☃.a(☃, x(☃).t(), D(☃), -1);
      }
    }
  }
  
  public boolean a(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    return ☃.k() != cq.a.b;
  }
  
  protected boolean z(arc ☃)
  {
    return this.d;
  }
  
  public int c(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    return ☃.a(☃, ☃, ☃);
  }
  
  public int b(arc ☃, ahx ☃, cj ☃, cq ☃)
  {
    if (!z(☃)) {
      return 0;
    }
    if (☃.c(D) == ☃) {
      return a(☃, ☃, ☃);
    }
    return 0;
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if (b(☃, ☃))
    {
      g(☃, ☃, ☃);
      return;
    }
    b(☃, ☃, ☃, 0);
    ☃.g(☃);
    for (cq ☃ : cq.values()) {
      ☃.d(☃.a(☃), this);
    }
  }
  
  protected void g(aht ☃, cj ☃, arc ☃)
  {
    if (b(☃, ☃, ☃)) {
      return;
    }
    boolean ☃ = e(☃, ☃, ☃);
    if (((this.d) && (!☃)) || ((!this.d) && (☃) && (!☃.a(☃, this))))
    {
      int ☃ = -1;
      if (i(☃, ☃, ☃)) {
        ☃ = -3;
      } else if (this.d) {
        ☃ = -2;
      }
      ☃.a(☃, this, i(☃), ☃);
    }
  }
  
  public boolean b(ahx ☃, cj ☃, arc ☃)
  {
    return false;
  }
  
  protected boolean e(aht ☃, cj ☃, arc ☃)
  {
    return f(☃, ☃, ☃) > 0;
  }
  
  protected int f(aht ☃, cj ☃, arc ☃)
  {
    cq ☃ = (cq)☃.c(D);
    
    cj ☃ = ☃.a(☃);
    int ☃ = ☃.c(☃, ☃);
    if (☃ >= 15) {
      return ☃;
    }
    arc ☃ = ☃.o(☃);
    return Math.max(☃, ☃.t() == aju.af ? ((Integer)☃.c(anx.e)).intValue() : 0);
  }
  
  protected int c(ahx ☃, cj ☃, arc ☃)
  {
    cq ☃ = (cq)☃.c(D);
    cq ☃ = ☃.e();
    cq ☃ = ☃.f();
    return Math.max(b(☃, ☃.a(☃), ☃), b(☃, ☃.a(☃), ☃));
  }
  
  protected int b(ahx ☃, cj ☃, cq ☃)
  {
    arc ☃ = ☃.o(☃);
    ajt ☃ = ☃.t();
    if (A(☃))
    {
      if (☃ == aju.cn) {
        return 15;
      }
      if (☃ == aju.af) {
        return ((Integer)☃.c(anx.e)).intValue();
      }
      return ☃.a(☃, ☃);
    }
    return 0;
  }
  
  public boolean g(arc ☃)
  {
    return true;
  }
  
  public arc a(aht ☃, cj ☃, cq ☃, float ☃, float ☃, float ☃, int ☃, sa ☃)
  {
    return u().a(D, ☃.bi().d());
  }
  
  public void a(aht ☃, cj ☃, arc ☃, sa ☃, adq ☃)
  {
    if (e(☃, ☃, ☃)) {
      ☃.a(☃, this, 1);
    }
  }
  
  public void c(aht ☃, cj ☃, arc ☃)
  {
    h(☃, ☃, ☃);
  }
  
  protected void h(aht ☃, cj ☃, arc ☃)
  {
    cq ☃ = (cq)☃.c(D);
    cj ☃ = ☃.a(☃.d());
    
    ☃.e(☃, this);
    ☃.a(☃, this, ☃);
  }
  
  public void d(aht ☃, cj ☃, arc ☃)
  {
    if (this.d) {
      for (cq ☃ : cq.values()) {
        ☃.d(☃.a(☃), this);
      }
    }
    super.d(☃, ☃, ☃);
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  protected boolean A(arc ☃)
  {
    return ☃.m();
  }
  
  protected int a(ahx ☃, cj ☃, arc ☃)
  {
    return 15;
  }
  
  public static boolean B(arc ☃)
  {
    return (aju.bb.C(☃)) || (aju.cj.C(☃));
  }
  
  public boolean C(arc ☃)
  {
    ajt ☃ = ☃.t();
    return (☃ == x(u()).t()) || (☃ == y(u()).t());
  }
  
  public boolean i(aht ☃, cj ☃, arc ☃)
  {
    cq ☃ = ((cq)☃.c(D)).d();
    cj ☃ = ☃.a(☃);
    if (B(☃.o(☃))) {
      return ☃.o(☃).c(D) != ☃;
    }
    return false;
  }
  
  protected int D(arc ☃)
  {
    return i(☃);
  }
  
  protected abstract int i(arc paramarc);
  
  protected abstract arc x(arc paramarc);
  
  protected abstract arc y(arc paramarc);
  
  public boolean b(ajt ☃)
  {
    return C(☃.u());
  }
  
  public ahm f()
  {
    return ahm.c;
  }
}
