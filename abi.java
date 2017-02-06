public class abi
  extends abt
{
  private zj a;
  private int b;
  
  public abi(zj ☃, qg ☃, int ☃, int ☃, int ☃)
  {
    super(☃, ☃, ☃, ☃);
    
    this.a = ☃;
  }
  
  public boolean a(adq ☃)
  {
    return false;
  }
  
  public adq a(int ☃)
  {
    if (e()) {
      this.b += Math.min(☃, d().b);
    }
    return super.a(☃);
  }
  
  public void a(zj ☃, adq ☃)
  {
    c(☃);
    super.a(☃, ☃);
  }
  
  protected void a(adq ☃, int ☃)
  {
    this.b += ☃;
    c(☃);
  }
  
  protected void c(adq ☃)
  {
    ☃.a(this.a.l, this.a, this.b);
    if (!this.a.l.E)
    {
      int ☃ = this.b;
      float ☃ = afq.a().b(☃);
      if (☃ == 0.0F)
      {
        ☃ = 0;
      }
      else if (☃ < 1.0F)
      {
        int ☃ = on.d(☃ * ☃);
        if ((☃ < on.f(☃ * ☃)) && (Math.random() < ☃ * ☃ - ☃)) {
          ☃++;
        }
        ☃ = ☃;
      }
      while (☃ > 0)
      {
        int ☃ = rx.a(☃);
        ☃ -= ☃;
        this.a.l.a(new rx(this.a.l, this.a.p, this.a.q + 0.5D, this.a.r + 0.5D, ☃));
      }
    }
    this.b = 0;
    if (☃.b() == ads.l) {
      this.a.b(nk.k);
    }
    if (☃.b() == ads.bc) {
      this.a.b(nk.p);
    }
  }
}
