import java.util.Random;

public abstract class zp
  extends rr
{
  private int e = -1;
  private int f = -1;
  private int g = -1;
  private ajt h;
  private boolean as;
  public sa a;
  private int at;
  private int au;
  public double b;
  public double c;
  public double d;
  
  public zp(aht ☃)
  {
    super(☃);
    a(1.0F, 1.0F);
  }
  
  protected void i() {}
  
  public boolean a(double ☃)
  {
    double ☃ = bl().a() * 4.0D;
    if (Double.isNaN(☃)) {
      ☃ = 4.0D;
    }
    ☃ *= 64.0D;
    return ☃ < ☃ * ☃;
  }
  
  public zp(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    super(☃);
    a(1.0F, 1.0F);
    
    b(☃, ☃, ☃, this.v, this.w);
    b(☃, ☃, ☃);
    
    double ☃ = on.a(☃ * ☃ + ☃ * ☃ + ☃ * ☃);
    this.b = (☃ / ☃ * 0.1D);
    this.c = (☃ / ☃ * 0.1D);
    this.d = (☃ / ☃ * 0.1D);
  }
  
  public zp(aht ☃, sa ☃, double ☃, double ☃, double ☃)
  {
    super(☃);
    this.a = ☃;
    
    a(1.0F, 1.0F);
    
    b(☃.p, ☃.q, ☃.r, ☃.v, ☃.w);
    b(this.p, this.q, this.r);
    
    this.s = (this.t = this.u = 0.0D);
    
    ☃ += this.S.nextGaussian() * 0.4D;
    ☃ += this.S.nextGaussian() * 0.4D;
    ☃ += this.S.nextGaussian() * 0.4D;
    double ☃ = on.a(☃ * ☃ + ☃ * ☃ + ☃ * ☃);
    this.b = (☃ / ☃ * 0.1D);
    this.c = (☃ / ☃ * 0.1D);
    this.d = (☃ / ☃ * 0.1D);
  }
  
  public void m()
  {
    if ((!this.l.E) && (((this.a != null) && (this.a.F)) || (!this.l.e(new cj(this)))))
    {
      T();
      return;
    }
    super.m();
    if (k()) {
      g(1);
    }
    if (this.as)
    {
      if (this.l.o(new cj(this.e, this.f, this.g)).t() == this.h)
      {
        this.at += 1;
        if (this.at == 600) {
          T();
        }
        return;
      }
      this.as = false;
      
      this.s *= this.S.nextFloat() * 0.2F;
      this.t *= this.S.nextFloat() * 0.2F;
      this.u *= this.S.nextFloat() * 0.2F;
      this.at = 0;
      this.au = 0;
    }
    else
    {
      this.au += 1;
    }
    bbi ☃ = zt.a(this, true, this.au >= 25, this.a);
    if (☃ != null) {
      a(☃);
    }
    this.p += this.s;
    this.q += this.t;
    this.r += this.u;
    
    zt.a(this, 0.2F);
    
    float ☃ = l();
    if (ai())
    {
      for (int ☃ = 0; ☃ < 4; ☃++)
      {
        float ☃ = 0.25F;
        this.l.a(cy.e, this.p - this.s * ☃, this.q - this.t * ☃, this.r - this.u * ☃, this.s, this.t, this.u, new int[0]);
      }
      ☃ = 0.8F;
    }
    this.s += this.b;
    this.t += this.c;
    this.u += this.d;
    this.s *= ☃;
    this.t *= ☃;
    this.u *= ☃;
    
    this.l.a(j(), this.p, this.q + 0.5D, this.r, 0.0D, 0.0D, 0.0D, new int[0]);
    
    b(this.p, this.q, this.r);
  }
  
  protected boolean k()
  {
    return true;
  }
  
  protected cy j()
  {
    return cy.l;
  }
  
  protected float l()
  {
    return 0.95F;
  }
  
  protected abstract void a(bbi parambbi);
  
  public void b(dn ☃)
  {
    ☃.a("xTile", this.e);
    ☃.a("yTile", this.f);
    ☃.a("zTile", this.g);
    kk ☃ = (kk)ajt.h.b(this.h);
    ☃.a("inTile", ☃ == null ? "" : ☃.toString());
    ☃.a("inGround", (byte)(this.as ? 1 : 0));
    ☃.a("direction", a(new double[] { this.s, this.t, this.u }));
    ☃.a("power", a(new double[] { this.b, this.c, this.d }));
    ☃.a("life", this.at);
  }
  
  public void a(dn ☃)
  {
    this.e = ☃.h("xTile");
    this.f = ☃.h("yTile");
    this.g = ☃.h("zTile");
    if (☃.b("inTile", 8)) {
      this.h = ajt.b(☃.l("inTile"));
    } else {
      this.h = ajt.b(☃.f("inTile") & 0xFF);
    }
    this.as = (☃.f("inGround") == 1);
    if (☃.b("power", 9))
    {
      du ☃ = ☃.c("power", 6);
      if (☃.c() == 3)
      {
        this.b = ☃.e(0);
        this.c = ☃.e(1);
        this.d = ☃.e(2);
      }
    }
    this.at = ☃.h("life");
    if ((☃.b("direction", 9)) && (☃.c("direction", 6).c() == 3))
    {
      du ☃ = ☃.c("direction", 6);
      this.s = ☃.e(0);
      this.t = ☃.e(1);
      this.u = ☃.e(2);
    }
    else
    {
      T();
    }
  }
  
  public boolean ap()
  {
    return true;
  }
  
  public float aA()
  {
    return 1.0F;
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (b(☃)) {
      return false;
    }
    ao();
    if (☃.j() != null)
    {
      bbj ☃ = ☃.j().aB();
      if (☃ != null)
      {
        this.s = ☃.b;
        this.t = ☃.c;
        this.u = ☃.d;
        this.b = (this.s * 0.1D);
        this.c = (this.t * 0.1D);
        this.d = (this.u * 0.1D);
      }
      if ((☃.j() instanceof sa)) {
        this.a = ((sa)☃.j());
      }
      return true;
    }
    return false;
  }
  
  public float e(float ☃)
  {
    return 1.0F;
  }
  
  public int d(float ☃)
  {
    return 15728880;
  }
}
