import java.util.Random;

public abstract class vw
  extends ro
  implements rq
{
  protected ajt by = aju.c;
  private int bv;
  private zj bw;
  
  public vw(aht ☃)
  {
    super(☃);
  }
  
  protected void M()
  {
    if (l() != 0) {
      this.bv = 0;
    }
    super.M();
  }
  
  public void n()
  {
    super.n();
    if (l() != 0) {
      this.bv = 0;
    }
    if (this.bv > 0)
    {
      this.bv -= 1;
      if (this.bv % 10 == 0)
      {
        double ☃ = this.S.nextGaussian() * 0.02D;
        double ☃ = this.S.nextGaussian() * 0.02D;
        double ☃ = this.S.nextGaussian() * 0.02D;
        this.l.a(cy.I, this.p + this.S.nextFloat() * this.G * 2.0F - this.G, this.q + 0.5D + this.S.nextFloat() * this.H, this.r + this.S.nextFloat() * this.G * 2.0F - this.G, ☃, ☃, ☃, new int[0]);
      }
    }
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (b(☃)) {
      return false;
    }
    this.bv = 0;
    return super.a(☃, ☃);
  }
  
  public float a(cj ☃)
  {
    if (this.l.o(☃.b()).t() == aju.c) {
      return 10.0F;
    }
    return this.l.n(☃) - 0.5F;
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    ☃.a("InLove", this.bv);
  }
  
  public double ax()
  {
    return 0.29D;
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    this.bv = ☃.h("InLove");
  }
  
  public boolean cF()
  {
    int ☃ = on.c(this.p);
    int ☃ = on.c(bl().b);
    int ☃ = on.c(this.r);
    cj ☃ = new cj(☃, ☃, ☃);
    return (this.l.o(☃.b()).t() == this.by) && (this.l.j(☃) > 8) && (super.cF());
  }
  
  public int C()
  {
    return 120;
  }
  
  protected boolean K()
  {
    return false;
  }
  
  protected int b(zj ☃)
  {
    return 1 + this.l.r.nextInt(3);
  }
  
  public boolean e(adq ☃)
  {
    if (☃ == null) {
      return false;
    }
    return ☃.b() == ads.Q;
  }
  
  public boolean a(zj ☃, qm ☃, adq ☃)
  {
    if (☃ != null)
    {
      if ((e(☃)) && (l() == 0) && (this.bv <= 0))
      {
        a(☃, ☃);
        c(☃);
        return true;
      }
      if ((m_()) && (e(☃)))
      {
        a(☃, ☃);
        a((int)(-l() / 20 * 0.1F), true);
        return true;
      }
    }
    return super.a(☃, ☃, ☃);
  }
  
  protected void a(zj ☃, adq ☃)
  {
    if (!☃.bJ.d) {
      ☃.b -= 1;
    }
  }
  
  public void c(zj ☃)
  {
    this.bv = 600;
    this.bw = ☃;
    
    this.l.a(this, (byte)18);
  }
  
  public zj de()
  {
    return this.bw;
  }
  
  public boolean df()
  {
    return this.bv > 0;
  }
  
  public void dg()
  {
    this.bv = 0;
  }
  
  public boolean a(vw ☃)
  {
    if (☃ == this) {
      return false;
    }
    if (☃.getClass() != getClass()) {
      return false;
    }
    return (df()) && (☃.df());
  }
  
  public void a(byte ☃)
  {
    if (☃ == 18) {
      for (int ☃ = 0; ☃ < 7; ☃++)
      {
        double ☃ = this.S.nextGaussian() * 0.02D;
        double ☃ = this.S.nextGaussian() * 0.02D;
        double ☃ = this.S.nextGaussian() * 0.02D;
        this.l.a(cy.I, this.p + this.S.nextFloat() * this.G * 2.0F - this.G, this.q + 0.5D + this.S.nextFloat() * this.H, this.r + this.S.nextFloat() * this.G * 2.0F - this.G, ☃, ☃, ☃, new int[0]);
      }
    } else {
      super.a(☃);
    }
  }
}
