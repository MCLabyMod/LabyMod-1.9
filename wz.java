import java.util.Random;

public class wz
  extends ww
{
  private bbj b;
  private int c;
  
  public wz(wu ☃)
  {
    super(☃);
  }
  
  public void b()
  {
    if (this.c++ % 10 == 0)
    {
      float ☃ = (this.a.bF().nextFloat() - 0.5F) * 8.0F;
      float ☃ = (this.a.bF().nextFloat() - 0.5F) * 4.0F;
      float ☃ = (this.a.bF().nextFloat() - 0.5F) * 8.0F;
      this.a.l.a(cy.c, this.a.p + ☃, this.a.q + 2.0D + ☃, this.a.r + ☃, 0.0D, 0.0D, 0.0D, new int[0]);
    }
  }
  
  public void c()
  {
    this.c += 1;
    if (this.b == null)
    {
      cj ☃ = this.a.l.l(auc.a);
      this.b = new bbj(☃.p(), ☃.q(), ☃.r());
    }
    double ☃ = this.b.c(this.a.p, this.a.q, this.a.r);
    if ((☃ < 100.0D) || (☃ > 22500.0D) || (this.a.A) || (this.a.B)) {
      this.a.c(0.0F);
    } else {
      this.a.c(1.0F);
    }
  }
  
  public void d()
  {
    this.b = null;
    this.c = 0;
  }
  
  public float f()
  {
    return 3.0F;
  }
  
  public bbj g()
  {
    return this.b;
  }
  
  public xk<wz> i()
  {
    return xk.j;
  }
}
