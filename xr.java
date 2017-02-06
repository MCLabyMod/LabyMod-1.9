import com.google.common.base.Predicate;
import java.util.List;
import org.apache.commons.lang3.Validate;

public abstract class xr
  extends rr
{
  private static final Predicate<rr> c = new Predicate()
  {
    public boolean a(rr ☃)
    {
      return ☃ instanceof xr;
    }
  };
  private int d;
  protected cj a;
  public cq b;
  
  public xr(aht ☃)
  {
    super(☃);
    a(0.5F, 0.5F);
  }
  
  public xr(aht ☃, cj ☃)
  {
    this(☃);
    this.a = ☃;
  }
  
  protected void i() {}
  
  protected void a(cq ☃)
  {
    Validate.notNull(☃);
    Validate.isTrue(☃.k().c());
    
    this.b = ☃;
    this.x = (this.v = this.b.b() * 90);
    
    j();
  }
  
  protected void j()
  {
    if (this.b == null) {
      return;
    }
    double ☃ = this.a.p() + 0.5D;
    double ☃ = this.a.q() + 0.5D;
    double ☃ = this.a.r() + 0.5D;
    
    double ☃ = 0.46875D;
    double ☃ = a(l());
    double ☃ = a(n());
    
    ☃ -= this.b.g() * 0.46875D;
    ☃ -= this.b.i() * 0.46875D;
    ☃ += ☃;
    
    cq ☃ = this.b.f();
    ☃ += ☃ * ☃.g();
    ☃ += ☃ * ☃.i();
    
    this.p = ☃;
    this.q = ☃;
    this.r = ☃;
    
    double ☃ = l();
    double ☃ = n();
    double ☃ = l();
    if (this.b.k() == cq.a.c) {
      ☃ = 1.0D;
    } else {
      ☃ = 1.0D;
    }
    ☃ /= 32.0D;
    ☃ /= 32.0D;
    ☃ /= 32.0D;
    a(new bbh(☃ - ☃, ☃ - ☃, ☃ - ☃, ☃ + ☃, ☃ + ☃, ☃ + ☃));
  }
  
  private double a(int ☃)
  {
    return ☃ % 32 == 0 ? 0.5D : 0.0D;
  }
  
  public void m()
  {
    this.m = this.p;
    this.n = this.q;
    this.o = this.r;
    if ((this.d++ == 100) && (!this.l.E))
    {
      this.d = 0;
      if ((!this.F) && (!k()))
      {
        T();
        a(null);
      }
    }
  }
  
  public boolean k()
  {
    if (!this.l.a(this, bl()).isEmpty()) {
      return false;
    }
    int ☃ = Math.max(1, l() / 16);
    int ☃ = Math.max(1, n() / 16);
    
    cj ☃ = this.a.a(this.b.d());
    cq ☃ = this.b.f();
    for (int ☃ = 0; ☃ < ☃; ☃++) {
      for (int ☃ = 0; ☃ < ☃; ☃++)
      {
        int ☃ = ☃ > 2 ? -1 : 0;
        int ☃ = ☃ > 2 ? -1 : 0;
        
        cj ☃ = ☃.a(☃, ☃ + ☃).b(☃ + ☃);
        
        arc ☃ = this.l.o(☃);
        if ((!☃.a().a()) && (!akr.B(☃))) {
          return false;
        }
      }
    }
    return this.l.a(this, bl(), c).isEmpty();
  }
  
  public boolean ap()
  {
    return true;
  }
  
  public boolean t(rr ☃)
  {
    if ((☃ instanceof zj)) {
      return a(rc.a((zj)☃), 0.0F);
    }
    return false;
  }
  
  public cq bi()
  {
    return this.b;
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (b(☃)) {
      return false;
    }
    if ((!this.F) && (!this.l.E))
    {
      T();
      ao();
      a(☃.j());
    }
    return true;
  }
  
  public void d(double ☃, double ☃, double ☃)
  {
    if ((!this.l.E) && (!this.F) && (☃ * ☃ + ☃ * ☃ + ☃ * ☃ > 0.0D))
    {
      T();
      a(null);
    }
  }
  
  public void g(double ☃, double ☃, double ☃)
  {
    if ((!this.l.E) && (!this.F) && (☃ * ☃ + ☃ * ☃ + ☃ * ☃ > 0.0D))
    {
      T();
      a(null);
    }
  }
  
  public void b(dn ☃)
  {
    ☃.a("Facing", (byte)this.b.b());
    cj ☃ = q();
    ☃.a("TileX", ☃.p());
    ☃.a("TileY", ☃.q());
    ☃.a("TileZ", ☃.r());
  }
  
  public void a(dn ☃)
  {
    this.a = new cj(☃.h("TileX"), ☃.h("TileY"), ☃.h("TileZ"));
    a(cq.b(☃.f("Facing")));
  }
  
  public abstract int l();
  
  public abstract int n();
  
  public abstract void a(rr paramrr);
  
  public abstract void o();
  
  public yd a(adq ☃, float ☃)
  {
    yd ☃ = new yd(this.l, this.p + this.b.g() * 0.15F, this.q + ☃, this.r + this.b.i() * 0.15F, ☃);
    ☃.q();
    this.l.a(☃);
    return ☃;
  }
  
  protected boolean ar()
  {
    return false;
  }
  
  public void b(double ☃, double ☃, double ☃)
  {
    this.a = new cj(☃, ☃, ☃);
    j();
    this.ai = true;
  }
  
  public cj q()
  {
    return this.a;
  }
  
  public float a(aoe ☃)
  {
    if ((this.b != null) && (this.b.k() != cq.a.b)) {
      switch (xr.2.a[☃.ordinal()])
      {
      case 1: 
        this.b = this.b.d();
        break;
      case 2: 
        this.b = this.b.f();
        break;
      case 3: 
        this.b = this.b.e();
        break;
      }
    }
    return super.a(☃);
  }
  
  public float a(amr ☃)
  {
    return a(☃.a(this.b));
  }
  
  public void a(ya ☃) {}
}
