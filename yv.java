import java.util.Random;

public class yv
  extends yq
{
  private yv.b a;
  
  public yv(aht ☃)
  {
    super(☃);
    a(0.4F, 0.3F);
  }
  
  protected void r()
  {
    this.bp.a(1, new th(this));
    
    this.bp.a(3, this.a = new yv.b(this));
    
    this.bp.a(4, new ts(this, 1.0D, false));
    this.bp.a(5, new yv.a(this));
    
    this.bq.a(1, new uv(this, true, new Class[0]));
    this.bq.a(2, new uy(this, zj.class, true));
  }
  
  public double ax()
  {
    return 0.2D;
  }
  
  public float bn()
  {
    return 0.1F;
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.a).a(8.0D);
    a(yt.d).a(0.25D);
    a(yt.e).a(1.0D);
  }
  
  protected boolean ae()
  {
    return false;
  }
  
  protected nf G()
  {
    return ng.fd;
  }
  
  protected nf bR()
  {
    return ng.ff;
  }
  
  protected nf bS()
  {
    return ng.fe;
  }
  
  protected void a(cj ☃, ajt ☃)
  {
    a(ng.fg, 0.15F, 1.0F);
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (b(☃)) {
      return false;
    }
    if ((((☃ instanceof rd)) || (☃ == rc.m)) && (this.a != null)) {
      this.a.f();
    }
    return super.a(☃, ☃);
  }
  
  protected kk J()
  {
    return azt.t;
  }
  
  public void m()
  {
    this.aM = this.v;
    
    super.m();
  }
  
  public float a(cj ☃)
  {
    if (this.l.o(☃.b()).t() == aju.b) {
      return 10.0F;
    }
    return super.a(☃);
  }
  
  protected boolean s_()
  {
    return true;
  }
  
  public boolean cF()
  {
    if (super.cF())
    {
      zj ☃ = this.l.b(this, 5.0D);
      return ☃ == null;
    }
    return false;
  }
  
  public sf ca()
  {
    return sf.c;
  }
  
  static class b
    extends tk
  {
    private yv a;
    private int b;
    
    public b(yv ☃)
    {
      this.a = ☃;
    }
    
    public void f()
    {
      if (this.b == 0) {
        this.b = 20;
      }
    }
    
    public boolean a()
    {
      return this.b > 0;
    }
    
    public void e()
    {
      this.b -= 1;
      if (this.b <= 0)
      {
        aht ☃ = this.a.l;
        Random ☃ = this.a.bF();
        
        cj ☃ = new cj(this.a);
        for (int ☃ = 0; (☃ <= 5) && (☃ >= -5); ☃ = ☃ <= 0 ? 1 - ☃ : 0 - ☃) {
          for (int ☃ = 0; (☃ <= 10) && (☃ >= -10); ☃ = ☃ <= 0 ? 1 - ☃ : 0 - ☃) {
            for (int ☃ = 0; (☃ <= 10) && (☃ >= -10); ☃ = ☃ <= 0 ? 1 - ☃ : 0 - ☃)
            {
              cj ☃ = ☃.a(☃, ☃, ☃);
              arc ☃ = ☃.o(☃);
              if (☃.t() == aju.be)
              {
                if (☃.U().b("mobGriefing")) {
                  ☃.b(☃, true);
                } else {
                  ☃.a(☃, ((amt.a)☃.c(amt.a)).d(), 3);
                }
                if (☃.nextBoolean()) {
                  return;
                }
              }
            }
          }
        }
      }
    }
  }
  
  static class a
    extends ug
  {
    private final yv a;
    private cq b;
    private boolean c;
    
    public a(yv ☃)
    {
      super(1.0D, 10);
      this.a = ☃;
      
      a(1);
    }
    
    public boolean a()
    {
      if (!this.a.l.U().b("mobGriefing")) {
        return false;
      }
      if (this.a.A() != null) {
        return false;
      }
      if (!this.a.x().n()) {
        return false;
      }
      Random ☃ = this.a.bF();
      if (☃.nextInt(10) == 0)
      {
        this.b = cq.a(☃);
        
        cj ☃ = new cj(this.a.p, this.a.q + 0.5D, this.a.r).a(this.b);
        arc ☃ = this.a.l.o(☃);
        if (amt.i(☃))
        {
          this.c = true;
          return true;
        }
      }
      this.c = false;
      return super.a();
    }
    
    public boolean b()
    {
      if (this.c) {
        return false;
      }
      return super.b();
    }
    
    public void c()
    {
      if (!this.c)
      {
        super.c();
        return;
      }
      aht ☃ = this.a.l;
      cj ☃ = new cj(this.a.p, this.a.q + 0.5D, this.a.r).a(this.b);
      arc ☃ = ☃.o(☃);
      if (amt.i(☃))
      {
        ☃.a(☃, aju.be.u().a(amt.a, amt.a.a(☃)), 3);
        this.a.E();
        this.a.T();
      }
    }
  }
}
