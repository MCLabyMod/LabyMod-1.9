public class buk
  extends bsd<bmq>
{
  private boolean a;
  
  public buk(brm ☃)
  {
    this(☃, false);
  }
  
  public buk(brm ☃, boolean ☃)
  {
    super(☃, new bjf(0.0F, ☃), 0.5F);
    this.a = ☃;
    
    a(new btu(this));
    a(new btv(this));
    a(new btk(this));
    a(new btp(this));
    a(new btl(this));
    a(new bto(h().e));
    a(new btq(this));
  }
  
  public bjf h()
  {
    return (bjf)super.b();
  }
  
  public void a(bmq ☃, double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    if ((☃.cJ()) && (this.c.c != ☃)) {
      return;
    }
    double ☃ = ☃;
    if ((☃.aK()) && (!(☃ instanceof bmt))) {
      ☃ -= 0.125D;
    }
    d(☃);
    
    bni.a(bni.q.b);
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
    bni.b(bni.q.b);
  }
  
  private void d(bmq ☃)
  {
    bjf ☃ = h();
    if (☃.y())
    {
      ☃.a(false);
      ☃.e.j = true;
      ☃.f.j = true;
    }
    else
    {
      adq ☃ = ☃.cb();
      adq ☃ = ☃.cc();
      
      ☃.a(true);
      
      ☃.f.j = ☃.a(zk.g);
      ☃.u.j = ☃.a(zk.b);
      ☃.c.j = ☃.a(zk.e);
      ☃.d.j = ☃.a(zk.f);
      ☃.a.j = ☃.a(zk.c);
      ☃.b.j = ☃.a(zk.d);
      
      ☃.n = ☃.aK();
      
      bix.a ☃ = bix.a.a;
      bix.a ☃ = bix.a.a;
      if (☃ != null)
      {
        ☃ = bix.a.b;
        if (☃.cw() > 0)
        {
          afa ☃ = ☃.m();
          if (☃ == afa.d) {
            ☃ = bix.a.c;
          } else if (☃ == afa.e) {
            ☃ = bix.a.d;
          }
        }
      }
      if (☃ != null)
      {
        ☃ = bix.a.b;
        if (☃.cw() > 0)
        {
          afa ☃ = ☃.m();
          if (☃ == afa.d) {
            ☃ = bix.a.c;
          }
        }
      }
      if (☃.cr() == rz.b)
      {
        ☃.m = ☃;
        ☃.l = ☃;
      }
      else
      {
        ☃.m = ☃;
        ☃.l = ☃;
      }
    }
  }
  
  protected kk a(bmq ☃)
  {
    return ☃.o();
  }
  
  public void e()
  {
    bni.c(0.0F, 0.1875F, 0.0F);
  }
  
  protected void a(bmq ☃, float ☃)
  {
    float ☃ = 0.9375F;
    bni.b(☃, ☃, ☃);
  }
  
  protected void a(bmq ☃, double ☃, double ☃, double ☃, String ☃, double ☃)
  {
    if (☃ < 100.0D)
    {
      bbp ☃ = ☃.cW();
      bbl ☃ = ☃.a(2);
      if (☃ != null)
      {
        bbn ☃ = ☃.c(☃.h_(), ☃);
        
        a(☃, ☃.c() + " " + ☃.d(), ☃, ☃, ☃, 64);
        
        ☃ += c().a * 1.15F * 0.025F;
      }
    }
    super.a(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public void b(bmq ☃)
  {
    float ☃ = 1.0F;
    bni.d(☃, ☃, ☃);
    
    float ☃ = 0.0625F;
    
    bjf ☃ = h();
    d(☃);
    
    bni.m();
    ☃.o = 0.0F;
    ☃.n = false;
    ☃.a(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, ☃);
    ☃.h.f = 0.0F;
    ☃.h.a(0.0625F);
    ☃.b.f = 0.0F;
    ☃.b.a(0.0625F);
    bni.l();
  }
  
  public void c(bmq ☃)
  {
    float ☃ = 1.0F;
    bni.d(☃, ☃, ☃);
    
    float ☃ = 0.0625F;
    
    bjf ☃ = h();
    d(☃);
    bni.m();
    ☃.n = false;
    ☃.o = 0.0F;
    ☃.a(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, ☃);
    ☃.i.f = 0.0F;
    ☃.i.a(0.0625F);
    ☃.a.f = 0.0F;
    ☃.a.a(0.0625F);
    bni.l();
  }
  
  protected void a(bmq ☃, double ☃, double ☃, double ☃)
  {
    if ((☃.au()) && (☃.cl())) {
      super.a(☃, ☃ + ☃.bH, ☃ + ☃.cl, ☃ + ☃.bI);
    } else {
      super.a(☃, ☃, ☃, ☃);
    }
  }
  
  protected void a(bmq ☃, float ☃, float ☃, float ☃)
  {
    if ((☃.au()) && (☃.cl()))
    {
      bni.b(☃.cL(), 0.0F, 1.0F, 0.0F);
      bni.b(b(☃), 0.0F, 0.0F, 1.0F);
      bni.b(270.0F, 0.0F, 1.0F, 0.0F);
    }
    else if (☃.cB())
    {
      super.a(☃, ☃, ☃, ☃);
      
      float ☃ = ☃.cC() + ☃;
      float ☃ = on.a(☃ * ☃ / 100.0F, 0.0F, 1.0F);
      
      bni.b(☃ * (-90.0F - ☃.w), 1.0F, 0.0F, 0.0F);
      
      bbj ☃ = ☃.f(☃);
      double ☃ = ☃.s * ☃.s + ☃.u * ☃.u;
      double ☃ = ☃.b * ☃.b + ☃.d * ☃.d;
      if ((☃ > 0.0D) && (☃ > 0.0D))
      {
        double ☃ = (☃.s * ☃.b + ☃.u * ☃.d) / (Math.sqrt(☃) * Math.sqrt(☃));
        double ☃ = ☃.s * ☃.d - ☃.u * ☃.b;
        bni.b((float)(Math.signum(☃) * Math.acos(☃)) * 180.0F / 3.1415927F, 0.0F, 1.0F, 0.0F);
      }
    }
    else
    {
      super.a(☃, ☃, ☃, ☃);
    }
  }
}
