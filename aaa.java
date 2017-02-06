import java.util.Random;

public class aaa
  extends zy
{
  private sa d;
  
  public aaa(aht ☃)
  {
    super(☃);
  }
  
  public aaa(aht ☃, sa ☃)
  {
    super(☃, ☃);
    this.d = ☃;
  }
  
  public aaa(aht ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃);
  }
  
  protected void a(bbi ☃)
  {
    sa ☃ = k();
    if (☃.d != null)
    {
      if (☃.d == this.d) {
        return;
      }
      ☃.d.a(rc.a(this, ☃), 0.0F);
    }
    if (☃.a == bbi.a.b)
    {
      cj ☃ = ☃.a();
      apv ☃ = this.l.r(☃);
      if ((☃ instanceof aqq))
      {
        aqq ☃ = (aqq)☃;
        if (☃ != null)
        {
          ☃.a(☃);
          T();
          return;
        }
        ☃.a(this);
        return;
      }
    }
    for (int ☃ = 0; ☃ < 32; ☃++) {
      this.l.a(cy.y, this.p, this.q + this.S.nextDouble() * 2.0D, this.r, this.S.nextGaussian(), 0.0D, this.S.nextGaussian(), new int[0]);
    }
    if (!this.l.E)
    {
      if ((☃ instanceof lr))
      {
        lr ☃ = (lr)☃;
        if ((☃.a.a().g()) && (☃.l == this.l) && (!☃.cl()))
        {
          if ((this.S.nextFloat() < 0.05F) && (this.l.U().b("doMobSpawning")))
          {
            yk ☃ = new yk(this.l);
            ☃.a(true);
            ☃.b(☃.p, ☃.q, ☃.r, ☃.v, ☃.w);
            this.l.a(☃);
          }
          if (☃.aI()) {
            p();
          }
          ☃.a(this.p, this.q, this.r);
          ☃.L = 0.0F;
          ☃.a(rc.i, 5.0F);
        }
      }
      else if (☃ != null)
      {
        ☃.a(this.p, this.q, this.r);
        ☃.L = 0.0F;
      }
      T();
    }
  }
  
  public void m()
  {
    sa ☃ = k();
    if ((☃ != null) && ((☃ instanceof zj)) && (!☃.au())) {
      T();
    } else {
      super.m();
    }
  }
}
