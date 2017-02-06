import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class zu
  extends rr
{
  private sa a;
  private rr b;
  private cq c;
  private int d;
  private double e;
  private double f;
  private double g;
  private UUID h;
  private cj as;
  private UUID at;
  private cj au;
  
  public zu(aht ☃)
  {
    super(☃);
    a(0.3125F, 0.3125F);
    
    this.Q = true;
  }
  
  public nh bz()
  {
    return nh.f;
  }
  
  public zu(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    this(☃);
    
    b(☃, ☃, ☃, this.v, this.w);
    
    this.s = ☃;
    this.t = ☃;
    this.u = ☃;
  }
  
  public zu(aht ☃, sa ☃, rr ☃, cq.a ☃)
  {
    this(☃);
    this.a = ☃;
    
    cj ☃ = new cj(☃);
    double ☃ = ☃.p() + 0.5D;
    double ☃ = ☃.q() + 0.5D;
    double ☃ = ☃.r() + 0.5D;
    
    b(☃, ☃, ☃, this.v, this.w);
    
    this.b = ☃;
    
    this.c = cq.b;
    a(☃);
  }
  
  protected void b(dn ☃)
  {
    if (this.a != null)
    {
      cj ☃ = new cj(this.a);
      
      dn ☃ = dy.a(this.a.bc());
      ☃.a("X", ☃.p());
      ☃.a("Y", ☃.q());
      ☃.a("Z", ☃.r());
      ☃.a("Owner", ☃);
    }
    if (this.b != null)
    {
      cj ☃ = new cj(this.b);
      
      dn ☃ = dy.a(this.b.bc());
      ☃.a("X", ☃.p());
      ☃.a("Y", ☃.q());
      ☃.a("Z", ☃.r());
      ☃.a("Target", ☃);
    }
    if (this.c != null) {
      ☃.a("Dir", this.c.a());
    }
    ☃.a("Steps", this.d);
    ☃.a("TXD", this.e);
    ☃.a("TYD", this.f);
    ☃.a("TZD", this.g);
  }
  
  protected void a(dn ☃)
  {
    this.d = ☃.h("Steps");
    this.e = ☃.k("TXD");
    this.f = ☃.k("TYD");
    this.g = ☃.k("TZD");
    if (☃.b("Dir", 99)) {
      this.c = cq.a(☃.h("Dir"));
    }
    if (☃.b("Owner", 10))
    {
      dn ☃ = ☃.o("Owner");
      this.h = dy.b(☃);
      this.as = new cj(☃.h("X"), ☃.h("Y"), ☃.h("Z"));
    }
    if (☃.b("Target", 10))
    {
      dn ☃ = ☃.o("Target");
      this.at = dy.b(☃);
      this.au = new cj(☃.h("X"), ☃.h("Y"), ☃.h("Z"));
    }
  }
  
  protected void i() {}
  
  private void a(cq ☃)
  {
    this.c = ☃;
  }
  
  private void a(cq.a ☃)
  {
    double ☃ = 0.5D;
    cj ☃;
    cj ☃;
    if (this.b == null)
    {
      ☃ = new cj(this).b();
    }
    else
    {
      ☃ = this.b.H * 0.5D;
      ☃ = new cj(this.b.p, this.b.q + ☃, this.b.r);
    }
    double ☃ = ☃.p() + 0.5D;
    double ☃ = ☃.q() + ☃;
    double ☃ = ☃.r() + 0.5D;
    
    cq ☃ = null;
    if (☃.f(this.p, this.q, this.r) >= 4.0D)
    {
      cj ☃ = new cj(this);
      List<cq> ☃ = Lists.newArrayList();
      if (☃ != cq.a.a) {
        if ((☃.p() < ☃.p()) && (this.l.d(☃.f()))) {
          ☃.add(cq.f);
        } else if ((☃.p() > ☃.p()) && (this.l.d(☃.e()))) {
          ☃.add(cq.e);
        }
      }
      if (☃ != cq.a.b) {
        if ((☃.q() < ☃.q()) && (this.l.d(☃.a()))) {
          ☃.add(cq.b);
        } else if ((☃.q() > ☃.q()) && (this.l.d(☃.b()))) {
          ☃.add(cq.a);
        }
      }
      if (☃ != cq.a.c) {
        if ((☃.r() < ☃.r()) && (this.l.d(☃.d()))) {
          ☃.add(cq.d);
        } else if ((☃.r() > ☃.r()) && (this.l.d(☃.c()))) {
          ☃.add(cq.c);
        }
      }
      ☃ = cq.a(this.S);
      if (☃.isEmpty())
      {
        int ☃ = 5;
        while ((!this.l.d(☃.a(☃))) && (☃ > 0))
        {
          ☃ = cq.a(this.S);
          ☃--;
        }
      }
      else
      {
        ☃ = (cq)☃.get(this.S.nextInt(☃.size()));
      }
      ☃ = this.p + ☃.g();
      ☃ = this.q + ☃.h();
      ☃ = this.r + ☃.i();
    }
    a(☃);
    
    double ☃ = ☃ - this.p;
    double ☃ = ☃ - this.q;
    double ☃ = ☃ - this.r;
    
    double ☃ = on.a(☃ * ☃ + ☃ * ☃ + ☃ * ☃);
    if (☃ == 0.0D)
    {
      this.e = 0.0D;
      this.f = 0.0D;
      this.g = 0.0D;
    }
    else
    {
      this.e = (☃ / ☃ * 0.15D);
      this.f = (☃ / ☃ * 0.15D);
      this.g = (☃ / ☃ * 0.15D);
    }
    this.ai = true;
    this.d = (10 + this.S.nextInt(5) * 10);
  }
  
  public void m()
  {
    if ((!this.l.E) && (this.l.ae() == qk.a))
    {
      T();
      return;
    }
    super.m();
    if (!this.l.E)
    {
      if ((this.b == null) && (this.at != null))
      {
        List<sa> ☃ = this.l.a(sa.class, new bbh(this.au.a(-2, -2, -2), this.au.a(2, 2, 2)));
        for (sa ☃ : ☃) {
          if (☃.bc().equals(this.at))
          {
            this.b = ☃;
            break;
          }
        }
        this.at = null;
      }
      if ((this.a == null) && (this.h != null))
      {
        List<sa> ☃ = this.l.a(sa.class, new bbh(this.as.a(-2, -2, -2), this.as.a(2, 2, 2)));
        for (sa ☃ : ☃) {
          if (☃.bc().equals(this.h))
          {
            this.a = ☃;
            break;
          }
        }
        this.h = null;
      }
      if ((this.b != null) && (this.b.au()) && ((!(this.b instanceof zj)) || (!((zj)this.b).y())))
      {
        this.e = on.a(this.e * 1.025D, -1.0D, 1.0D);
        this.f = on.a(this.f * 1.025D, -1.0D, 1.0D);
        this.g = on.a(this.g * 1.025D, -1.0D, 1.0D);
        
        this.s += (this.e - this.s) * 0.2D;
        this.t += (this.f - this.t) * 0.2D;
        this.u += (this.g - this.u) * 0.2D;
      }
      else
      {
        this.t -= 0.04D;
      }
      bbi ☃ = zt.a(this, true, false, this.a);
      if (☃ != null) {
        a(☃);
      }
    }
    b(this.p + this.s, this.q + this.t, this.r + this.u);
    
    zt.a(this, 0.5F);
    if (this.l.E)
    {
      this.l.a(cy.R, this.p - this.s, this.q - this.t + 0.15D, this.r - this.u, 0.0D, 0.0D, 0.0D, new int[0]);
    }
    else if ((this.b != null) && (!this.b.F))
    {
      if (this.d > 0)
      {
        this.d -= 1;
        if (this.d == 0) {
          a(this.c == null ? null : this.c.k());
        }
      }
      if (this.c != null)
      {
        cj ☃ = new cj(this);
        cq.a ☃ = this.c.k();
        if (this.l.d(☃.a(this.c), false))
        {
          a(☃);
        }
        else
        {
          cj ☃ = new cj(this.b);
          if (((☃ == cq.a.a) && (☃.p() == ☃.p())) || ((☃ == cq.a.c) && (☃.r() == ☃.r())) || ((☃ == cq.a.b) && (☃.q() == ☃.q()))) {
            a(☃);
          }
        }
      }
    }
  }
  
  public boolean aH()
  {
    return false;
  }
  
  public boolean a(double ☃)
  {
    return ☃ < 16384.0D;
  }
  
  public float e(float ☃)
  {
    return 1.0F;
  }
  
  public int d(float ☃)
  {
    return 15728880;
  }
  
  protected void a(bbi ☃)
  {
    if (☃.d == null)
    {
      ((lp)this.l).a(cy.b, this.p, this.q, this.r, 2, 0.2D, 0.2D, 0.2D, 0.0D, new int[0]);
      a(ng.eU, 1.0F, 1.0F);
    }
    else
    {
      boolean ☃ = ☃.d.a(rc.a(this, this.a).b(), 4.0F);
      if (☃)
      {
        a(this.a, ☃.d);
        if ((☃.d instanceof sa)) {
          ((sa)☃.d).c(new rl(rm.y, 200));
        }
      }
    }
    T();
  }
  
  public boolean ap()
  {
    return true;
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (!this.l.E)
    {
      a(ng.eV, 1.0F, 1.0F);
      ((lp)this.l).a(cy.j, this.p, this.q, this.r, 15, 0.2D, 0.2D, 0.2D, 0.0D, new int[0]);
      T();
    }
    return true;
  }
}
