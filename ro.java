import java.util.Random;

public abstract class ro
  extends sh
{
  private static final ke<Boolean> bv = kh.a(ro.class, kg.h);
  protected int a;
  protected int b;
  protected int c;
  
  public ro(aht ☃)
  {
    super(☃);
  }
  
  public abstract ro a(ro paramro);
  
  public boolean a(zj ☃, qm ☃, adq ☃)
  {
    if ((☃ != null) && (☃.b() == ads.bT))
    {
      if (!this.l.E)
      {
        Class<? extends rr> ☃ = rt.a(rt.a(aeu.h(☃)));
        if ((☃ != null) && (getClass() == ☃))
        {
          ro ☃ = a(this);
          if (☃ != null)
          {
            ☃.b_(41536);
            ☃.b(this.p, this.q, this.r, 0.0F, 0.0F);
            
            this.l.a(☃);
            if (☃.s()) {
              ☃.c(☃.q());
            }
            if (!☃.bJ.d) {
              ☃.b -= 1;
            }
          }
        }
      }
      return true;
    }
    return false;
  }
  
  protected void i()
  {
    super.i();
    this.Z.a(bv, Boolean.valueOf(false));
  }
  
  public int l()
  {
    if (this.l.E) {
      return ((Boolean)this.Z.a(bv)).booleanValue() ? -1 : 1;
    }
    return this.a;
  }
  
  public void a(int ☃, boolean ☃)
  {
    int ☃ = l();
    int ☃ = ☃;
    ☃ += ☃ * 20;
    if (☃ > 0)
    {
      ☃ = 0;
      if (☃ < 0) {
        o();
      }
    }
    int ☃ = ☃ - ☃;
    b_(☃);
    if (☃)
    {
      this.b += ☃;
      if (this.c == 0) {
        this.c = 40;
      }
    }
    if (l() == 0) {
      b_(this.b);
    }
  }
  
  public void a(int ☃)
  {
    a(☃, false);
  }
  
  public void b_(int ☃)
  {
    this.Z.b(bv, Boolean.valueOf(☃ < 0));
    this.a = ☃;
    a(m_());
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    ☃.a("Age", l());
    ☃.a("ForcedAge", this.b);
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    b_(☃.h("Age"));
    this.b = ☃.h("ForcedAge");
  }
  
  public void a(ke<?> ☃)
  {
    if (bv.equals(☃)) {
      a(m_());
    }
    super.a(☃);
  }
  
  public void n()
  {
    super.n();
    if (this.l.E)
    {
      if (this.c > 0)
      {
        if (this.c % 4 == 0) {
          this.l.a(cy.v, this.p + this.S.nextFloat() * this.G * 2.0F - this.G, this.q + 0.5D + this.S.nextFloat() * this.H, this.r + this.S.nextFloat() * this.G * 2.0F - this.G, 0.0D, 0.0D, 0.0D, new int[0]);
        }
        this.c -= 1;
      }
    }
    else
    {
      int ☃ = l();
      if (☃ < 0)
      {
        ☃++;
        b_(☃);
        if (☃ == 0) {
          o();
        }
      }
      else if (☃ > 0)
      {
        ☃--;
        b_(☃);
      }
    }
  }
  
  protected void o() {}
  
  public boolean m_()
  {
    return l() < 0;
  }
  
  public void a(boolean ☃)
  {
    a(☃ ? 0.5F : 1.0F);
  }
  
  private float bw = -1.0F;
  private float bx;
  
  protected final void a(float ☃, float ☃)
  {
    boolean ☃ = this.bw > 0.0F;
    
    this.bw = ☃;
    this.bx = ☃;
    if (!☃) {
      a(1.0F);
    }
  }
  
  protected final void a(float ☃)
  {
    super.a(this.bw * ☃, this.bx * ☃);
  }
}
