import java.util.Random;

public class xa
  extends ww
{
  private ayp b;
  private bbj c;
  private boolean d;
  
  public xa(wu ☃)
  {
    super(☃);
  }
  
  public xk<xa> i()
  {
    return xk.a;
  }
  
  public void c()
  {
    double ☃ = this.c == null ? 0.0D : this.c.c(this.a.p, this.a.q, this.a.r);
    if ((☃ < 100.0D) || (☃ > 22500.0D) || (this.a.A) || (this.a.B)) {
      j();
    }
  }
  
  public void d()
  {
    this.b = null;
    this.c = null;
  }
  
  public bbj g()
  {
    return this.c;
  }
  
  private void j()
  {
    if ((this.b != null) && (this.b.b()))
    {
      cj ☃ = this.a.l.q(new cj(auc.a));
      
      int ☃ = this.a.cU() == null ? 0 : this.a.cU().c();
      if (this.a.bF().nextInt(☃ + 3) == 0)
      {
        this.a.cT().a(xk.c);
        return;
      }
      double ☃ = 64.0D;
      zj ☃ = this.a.l.a(☃, ☃, ☃);
      if (☃ != null) {
        ☃ = ☃.d(☃) / 512.0D;
      }
      if ((☃ != null) && ((this.a.bF().nextInt(on.a((int)☃) + 2) == 0) || (this.a.bF().nextInt(☃ + 2) == 0)))
      {
        a(☃);
        return;
      }
    }
    if ((this.b == null) || (this.b.b()))
    {
      int ☃ = this.a.o();
      int ☃ = ☃;
      if (this.a.bF().nextInt(8) == 0)
      {
        this.d = (!this.d);
        ☃ += 6;
      }
      if (this.d) {
        ☃++;
      } else {
        ☃--;
      }
      if ((this.a.cU() == null) || (this.a.cU().c() < 0))
      {
        ☃ -= 12;
        ☃ &= 0x7;
        ☃ += 12;
      }
      else
      {
        ☃ %= 12;
        if (☃ < 0) {
          ☃ += 12;
        }
      }
      this.b = this.a.a(☃, ☃, null);
      if (this.b != null) {
        this.b.a();
      }
    }
    k();
  }
  
  private void a(zj ☃)
  {
    this.a.cT().a(xk.b);
    ((xi)this.a.cT().b(xk.b)).a(☃);
  }
  
  private void k()
  {
    if ((this.b != null) && (!this.b.b()))
    {
      bbj ☃ = this.b.f();
      
      this.b.a();
      double ☃ = ☃.b;
      double ☃ = ☃.d;
      double ☃;
      do
      {
        ☃ = ☃.c + this.a.bF().nextFloat() * 20.0F;
      } while (☃ < ☃.c);
      this.c = new bbj(☃, ☃, ☃);
    }
  }
  
  public void a(wt ☃, cj ☃, rc ☃, zj ☃)
  {
    if (☃ != null) {
      a(☃);
    }
  }
}
