import java.util.Random;

public class xj
  extends ww
{
  private boolean b;
  private ayp c;
  private bbj d;
  
  public xj(wu ☃)
  {
    super(☃);
  }
  
  public void c()
  {
    if (this.b)
    {
      this.b = false;
      j();
    }
    else
    {
      cj ☃ = this.a.l.q(auc.a);
      double ☃ = this.a.d(☃);
      if (☃ > 100.0D) {
        this.a.cT().a(xk.a);
      }
    }
  }
  
  public void d()
  {
    this.b = true;
    this.c = null;
    this.d = null;
  }
  
  private void j()
  {
    int ☃ = this.a.o();
    bbj ☃ = this.a.a(1.0F);
    int ☃ = this.a.l(-☃.b * 40.0D, 105.0D, -☃.d * 40.0D);
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
    this.c = this.a.a(☃, ☃, null);
    if (this.c != null)
    {
      this.c.a();
      k();
    }
  }
  
  private void k()
  {
    bbj ☃ = this.c.f();
    
    this.c.a();
    double ☃;
    do
    {
      ☃ = ☃.c + this.a.bF().nextFloat() * 20.0F;
    } while (☃ < ☃.c);
    this.d = new bbj(☃.b, ☃, ☃.d);
  }
  
  public bbj g()
  {
    return this.d;
  }
  
  public xk<xj> i()
  {
    return xk.e;
  }
}
