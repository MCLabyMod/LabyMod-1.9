public class ve
  extends vf
{
  private boolean f;
  
  public ve(sb ☃, aht ☃)
  {
    super(☃, ☃);
  }
  
  protected ayq a()
  {
    this.e = new ays();
    this.e.a(true);
    return new ayq(this.e);
  }
  
  protected boolean b()
  {
    return (this.a.z) || ((g()) && (p())) || (this.a.aI());
  }
  
  protected bbj c()
  {
    return new bbj(this.a.p, r(), this.a.r);
  }
  
  public ayp a(cj ☃)
  {
    if (this.b.o(☃).a() == axe.a)
    {
      cj ☃ = ☃.b();
      while ((☃.q() > 0) && (this.b.o(☃).a() == axe.a)) {
        ☃ = ☃.b();
      }
      if (☃.q() > 0) {
        return super.a(☃.a());
      }
      while ((☃.q() < this.b.Y()) && (this.b.o(☃).a() == axe.a)) {
        ☃ = ☃.a();
      }
      ☃ = ☃;
    }
    if (this.b.o(☃).a().a())
    {
      cj ☃ = ☃.a();
      while ((☃.q() < this.b.Y()) && (this.b.o(☃).a().a())) {
        ☃ = ☃.a();
      }
      return super.a(☃);
    }
    return super.a(☃);
  }
  
  public ayp a(rr ☃)
  {
    cj ☃ = new cj(☃);
    return a(☃);
  }
  
  private int r()
  {
    if ((!this.a.ai()) || (!g())) {
      return (int)(this.a.bl().b + 0.5D);
    }
    int ☃ = (int)this.a.bl().b;
    ajt ☃ = this.b.o(new cj(on.c(this.a.p), ☃, on.c(this.a.r))).t();
    int ☃ = 0;
    while ((☃ == aju.i) || (☃ == aju.j))
    {
      ☃++;
      ☃ = this.b.o(new cj(on.c(this.a.p), ☃, on.c(this.a.r))).t();
      ☃++;
      if (☃ > 16) {
        return (int)this.a.bl().b;
      }
    }
    return ☃;
  }
  
  protected void d()
  {
    super.d();
    for (int ☃ = 0; ☃ < this.c.d(); ☃++)
    {
      ayn ☃ = this.c.a(☃);
      ayn ☃ = ☃ + 1 < this.c.d() ? this.c.a(☃ + 1) : null;
      
      arc ☃ = this.b.o(new cj(☃.a, ☃.b, ☃.c));
      ajt ☃ = ☃.t();
      if (☃ == aju.bE)
      {
        this.c.a(☃, ☃.a(☃.a, ☃.b + 1, ☃.c));
        if ((☃ != null) && (☃.b >= ☃.b)) {
          this.c.a(☃ + 1, ☃.a(☃.a, ☃.b + 1, ☃.c));
        }
      }
    }
    if (this.f)
    {
      if (this.b.h(new cj(on.c(this.a.p), (int)(this.a.bl().b + 0.5D), on.c(this.a.r)))) {
        return;
      }
      for (int ☃ = 0; ☃ < this.c.d(); ☃++)
      {
        ayn ☃ = this.c.a(☃);
        if (this.b.h(new cj(☃.a, ☃.b, ☃.c)))
        {
          this.c.b(☃ - 1);
          return;
        }
      }
    }
  }
  
  protected boolean a(bbj ☃, bbj ☃, int ☃, int ☃, int ☃)
  {
    int ☃ = on.c(☃.b);
    int ☃ = on.c(☃.d);
    
    double ☃ = ☃.b - ☃.b;
    double ☃ = ☃.d - ☃.d;
    double ☃ = ☃ * ☃ + ☃ * ☃;
    if (☃ < 1.0E-8D) {
      return false;
    }
    double ☃ = 1.0D / Math.sqrt(☃);
    ☃ *= ☃;
    ☃ *= ☃;
    
    ☃ += 2;
    ☃ += 2;
    if (!a(☃, (int)☃.c, ☃, ☃, ☃, ☃, ☃, ☃, ☃)) {
      return false;
    }
    ☃ -= 2;
    ☃ -= 2;
    
    double ☃ = 1.0D / Math.abs(☃);
    double ☃ = 1.0D / Math.abs(☃);
    
    double ☃ = ☃ - ☃.b;
    double ☃ = ☃ - ☃.d;
    if (☃ >= 0.0D) {
      ☃ += 1.0D;
    }
    if (☃ >= 0.0D) {
      ☃ += 1.0D;
    }
    ☃ /= ☃;
    ☃ /= ☃;
    
    int ☃ = ☃ < 0.0D ? -1 : 1;
    int ☃ = ☃ < 0.0D ? -1 : 1;
    int ☃ = on.c(☃.b);
    int ☃ = on.c(☃.d);
    int ☃ = ☃ - ☃;
    int ☃ = ☃ - ☃;
    while ((☃ * ☃ > 0) || (☃ * ☃ > 0))
    {
      if (☃ < ☃)
      {
        ☃ += ☃;
        ☃ += ☃;
        ☃ = ☃ - ☃;
      }
      else
      {
        ☃ += ☃;
        ☃ += ☃;
        ☃ = ☃ - ☃;
      }
      if (!a(☃, (int)☃.c, ☃, ☃, ☃, ☃, ☃, ☃, ☃)) {
        return false;
      }
    }
    return true;
  }
  
  private boolean a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, bbj ☃, double ☃, double ☃)
  {
    int ☃ = ☃ - ☃ / 2;
    int ☃ = ☃ - ☃ / 2;
    if (!b(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃)) {
      return false;
    }
    for (int ☃ = ☃; ☃ < ☃ + ☃; ☃++) {
      for (int ☃ = ☃; ☃ < ☃ + ☃; ☃++)
      {
        double ☃ = ☃ + 0.5D - ☃.b;
        double ☃ = ☃ + 0.5D - ☃.d;
        if (☃ * ☃ + ☃ * ☃ >= 0.0D)
        {
          aym ☃ = this.e.a(this.b, ☃, ☃ - 1, ☃, this.a, ☃, ☃, ☃, true, true);
          if (☃ == aym.g) {
            return false;
          }
          if (☃ == aym.f) {
            return false;
          }
          if (☃ == aym.b) {
            return false;
          }
          ☃ = this.e.a(this.b, ☃, ☃, ☃, this.a, ☃, ☃, ☃, true, true);
          float ☃ = this.a.a(☃);
          if ((☃ < 0.0F) || (☃ >= 8.0F)) {
            return false;
          }
          if ((☃ == aym.j) || (☃ == aym.i) || (☃ == aym.n)) {
            return false;
          }
        }
      }
    }
    return true;
  }
  
  private boolean b(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, bbj ☃, double ☃, double ☃)
  {
    for (cj ☃ : cj.a(new cj(☃, ☃, ☃), new cj(☃ + ☃ - 1, ☃ + ☃ - 1, ☃ + ☃ - 1)))
    {
      double ☃ = ☃.p() + 0.5D - ☃.b;
      double ☃ = ☃.r() + 0.5D - ☃.d;
      if (☃ * ☃ + ☃ * ☃ >= 0.0D)
      {
        ajt ☃ = this.b.o(☃).t();
        if (!☃.b(this.b, ☃)) {
          return false;
        }
      }
    }
    return true;
  }
  
  public void a(boolean ☃)
  {
    this.e.b(☃);
  }
  
  public void b(boolean ☃)
  {
    this.e.a(☃);
  }
  
  public boolean f()
  {
    return this.e.c();
  }
  
  public void c(boolean ☃)
  {
    this.e.c(☃);
  }
  
  public boolean g()
  {
    return this.e.e();
  }
  
  public void d(boolean ☃)
  {
    this.f = ☃;
  }
}
