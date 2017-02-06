import java.util.List;
import java.util.Random;

public class ym
  extends ry
  implements yl
{
  private static final ke<Boolean> a = kh.a(ym.class, kg.h);
  private int b = 1;
  
  public ym(aht ☃)
  {
    super(☃);
    a(4.0F, 4.0F);
    this.Y = true;
    this.b_ = 5;
    
    this.f = new ym.b(this);
  }
  
  protected void r()
  {
    this.bp.a(5, new ym.d(this));
    
    this.bp.a(7, new ym.a(this));
    this.bp.a(7, new ym.c(this));
    
    this.bq.a(1, new ux(this));
  }
  
  public boolean o()
  {
    return ((Boolean)this.Z.a(a)).booleanValue();
  }
  
  public void a(boolean ☃)
  {
    this.Z.b(a, Boolean.valueOf(☃));
  }
  
  public int cT()
  {
    return this.b;
  }
  
  public void m()
  {
    super.m();
    if ((!this.l.E) && (this.l.ae() == qk.a)) {
      T();
    }
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (b(☃)) {
      return false;
    }
    if (("fireball".equals(☃.p())) && 
      ((☃.j() instanceof zj)))
    {
      super.a(☃, 1000.0F);
      ((zj)☃.j()).b(nk.z);
      return true;
    }
    return super.a(☃, ☃);
  }
  
  protected void i()
  {
    super.i();
    
    this.Z.a(a, Boolean.valueOf(false));
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.a).a(10.0D);
    a(yt.b).a(100.0D);
  }
  
  public nh bz()
  {
    return nh.f;
  }
  
  protected nf G()
  {
    return ng.bJ;
  }
  
  protected nf bR()
  {
    return ng.bL;
  }
  
  protected nf bS()
  {
    return ng.bK;
  }
  
  protected kk J()
  {
    return azt.ae;
  }
  
  protected float cd()
  {
    return 10.0F;
  }
  
  public boolean cF()
  {
    return (this.S.nextInt(20) == 0) && (super.cF()) && (this.l.ae() != qk.a);
  }
  
  public int cJ()
  {
    return 1;
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    ☃.a("ExplosionPower", this.b);
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    if (☃.b("ExplosionPower", 99)) {
      this.b = ☃.h("ExplosionPower");
    }
  }
  
  static class b
    extends sy
  {
    private ym i;
    private int j;
    
    public b(ym ☃)
    {
      super();
      this.i = ☃;
    }
    
    public void c()
    {
      if (this.h != sy.a.b) {
        return;
      }
      double ☃ = this.b - this.i.p;
      double ☃ = this.c - this.i.q;
      double ☃ = this.d - this.i.r;
      
      double ☃ = ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
      if (this.j-- <= 0)
      {
        this.j += this.i.bF().nextInt(5) + 2;
        
        ☃ = on.a(☃);
        if (b(this.b, this.c, this.d, ☃))
        {
          this.i.s += ☃ / ☃ * 0.1D;
          this.i.t += ☃ / ☃ * 0.1D;
          this.i.u += ☃ / ☃ * 0.1D;
        }
        else
        {
          this.h = sy.a.a;
        }
      }
    }
    
    private boolean b(double ☃, double ☃, double ☃, double ☃)
    {
      double ☃ = (☃ - this.i.p) / ☃;
      double ☃ = (☃ - this.i.q) / ☃;
      double ☃ = (☃ - this.i.r) / ☃;
      
      bbh ☃ = this.i.bl();
      for (int ☃ = 1; ☃ < ☃; ☃++)
      {
        ☃ = ☃.c(☃, ☃, ☃);
        if (!this.i.l.a(this.i, ☃).isEmpty()) {
          return false;
        }
      }
      return true;
    }
  }
  
  static class d
    extends tk
  {
    private ym a;
    
    public d(ym ☃)
    {
      this.a = ☃;
      
      a(1);
    }
    
    public boolean a()
    {
      sy ☃ = this.a.u();
      if (!☃.a()) {
        return true;
      }
      double ☃ = ☃.d() - this.a.p;
      double ☃ = ☃.e() - this.a.q;
      double ☃ = ☃.f() - this.a.r;
      
      double ☃ = ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
      if ((☃ < 1.0D) || (☃ > 3600.0D)) {
        return true;
      }
      return false;
    }
    
    public boolean b()
    {
      return false;
    }
    
    public void c()
    {
      Random ☃ = this.a.bF();
      double ☃ = this.a.p + (☃.nextFloat() * 2.0F - 1.0F) * 16.0F;
      double ☃ = this.a.q + (☃.nextFloat() * 2.0F - 1.0F) * 16.0F;
      double ☃ = this.a.r + (☃.nextFloat() * 2.0F - 1.0F) * 16.0F;
      this.a.u().a(☃, ☃, ☃, 1.0D);
    }
  }
  
  static class a
    extends tk
  {
    private ym a;
    
    public a(ym ☃)
    {
      this.a = ☃;
      
      a(2);
    }
    
    public boolean a()
    {
      return true;
    }
    
    public void e()
    {
      if (this.a.A() == null)
      {
        this.a.aM = (this.a.v = -(float)on.b(this.a.s, this.a.u) * 57.295776F);
      }
      else
      {
        sa ☃ = this.a.A();
        
        double ☃ = 64.0D;
        if (☃.h(this.a) < ☃ * ☃)
        {
          double ☃ = ☃.p - this.a.p;
          double ☃ = ☃.r - this.a.r;
          this.a.aM = (this.a.v = -(float)on.b(☃, ☃) * 57.295776F);
        }
      }
    }
  }
  
  static class c
    extends tk
  {
    private ym b;
    public int a;
    
    public c(ym ☃)
    {
      this.b = ☃;
    }
    
    public boolean a()
    {
      return this.b.A() != null;
    }
    
    public void c()
    {
      this.a = 0;
    }
    
    public void d()
    {
      this.b.a(false);
    }
    
    public void e()
    {
      sa ☃ = this.b.A();
      
      double ☃ = 64.0D;
      if ((☃.h(this.b) < ☃ * ☃) && (this.b.D(☃)))
      {
        aht ☃ = this.b.l;
        
        this.a += 1;
        if (this.a == 10) {
          ☃.a(null, 1015, new cj(this.b), 0);
        }
        if (this.a == 20)
        {
          double ☃ = 4.0D;
          bbj ☃ = this.b.f(1.0F);
          
          double ☃ = ☃.p - (this.b.p + ☃.b * ☃);
          double ☃ = ☃.bl().b + ☃.H / 2.0F - (0.5D + this.b.q + this.b.H / 2.0F);
          double ☃ = ☃.r - (this.b.r + ☃.d * ☃);
          
          ☃.a(null, 1016, new cj(this.b), 0);
          zr ☃ = new zr(☃, this.b, ☃, ☃, ☃);
          ☃.e = this.b.cT();
          ☃.p = (this.b.p + ☃.b * ☃);
          ☃.q = (this.b.q + this.b.H / 2.0F + 0.5D);
          ☃.r = (this.b.r + ☃.d * ☃);
          ☃.a(☃);
          this.a = -40;
        }
      }
      else if (this.a > 0)
      {
        this.a -= 1;
      }
      this.b.a(this.a > 10);
    }
  }
  
  public float bn()
  {
    return 2.6F;
  }
}
