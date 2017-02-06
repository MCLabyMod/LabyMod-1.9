import java.util.Random;

public class xc
  extends ww
{
  private ayp b;
  private bbj c;
  
  public xc(wu ☃)
  {
    super(☃);
  }
  
  public xk<xc> i()
  {
    return xk.c;
  }
  
  public void d()
  {
    this.b = null;
    this.c = null;
  }
  
  public void c()
  {
    double ☃ = this.c == null ? 0.0D : this.c.c(this.a.p, this.a.q, this.a.r);
    if ((☃ < 100.0D) || (☃ > 22500.0D) || (this.a.A) || (this.a.B)) {
      j();
    }
  }
  
  public bbj g()
  {
    return this.c;
  }
  
  private void j()
  {
    if ((this.b == null) || (this.b.b()))
    {
      int ☃ = this.a.o();
      cj ☃ = this.a.l.q(auc.a);
      zj ☃ = this.a.l.a(☃, 128.0D, 128.0D);
      int ☃;
      int ☃;
      if (☃ != null)
      {
        bbj ☃ = new bbj(☃.p, 0.0D, ☃.r).a();
        ☃ = this.a.l(-☃.b * 40.0D, 105.0D, -☃.d * 40.0D);
      }
      else
      {
        ☃ = this.a.l(40.0D, ☃.q(), 0.0D);
      }
      ayn ☃ = new ayn(☃.p(), ☃.q(), ☃.r());
      
      this.b = this.a.a(☃, ☃, ☃);
      if (this.b != null) {
        this.b.a();
      }
    }
    k();
    if ((this.b != null) && (this.b.b())) {
      this.a.cT().a(xk.d);
    }
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
}
