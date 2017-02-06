import java.util.Random;

public class xd
  extends ww
{
  private bbj b;
  
  public xd(wu ☃)
  {
    super(☃);
  }
  
  public void b()
  {
    bbj ☃ = this.a.a(1.0F).a();
    ☃.b(-0.7853982F);
    
    double ☃ = this.a.bu.p;
    double ☃ = this.a.bu.q + this.a.bu.H / 2.0F;
    double ☃ = this.a.bu.r;
    for (int ☃ = 0; ☃ < 8; ☃++)
    {
      double ☃ = ☃ + this.a.bF().nextGaussian() / 2.0D;
      double ☃ = ☃ + this.a.bF().nextGaussian() / 2.0D;
      double ☃ = ☃ + this.a.bF().nextGaussian() / 2.0D;
      this.a.l.a(cy.Q, ☃, ☃, ☃, -☃.b * 0.07999999821186066D + this.a.s, -☃.c * 0.30000001192092896D + this.a.t, -☃.d * 0.07999999821186066D + this.a.u, new int[0]);
      ☃.b(0.19634955F);
    }
  }
  
  public void c()
  {
    if (this.b == null) {
      this.b = new bbj(this.a.l.q(auc.a));
    }
    if (this.b.c(this.a.p, this.a.q, this.a.r) < 1.0D)
    {
      ((xg)this.a.cT().b(xk.f)).j();
      this.a.cT().a(xk.g);
    }
  }
  
  public float f()
  {
    return 1.5F;
  }
  
  public float h()
  {
    float ☃ = on.a(this.a.s * this.a.s + this.a.u * this.a.u) + 1.0F;
    float ☃ = Math.min(☃, 40.0F);
    
    return ☃ / ☃;
  }
  
  public void d()
  {
    this.b = null;
  }
  
  public bbj g()
  {
    return this.b;
  }
  
  public xk<xd> i()
  {
    return xk.d;
  }
}
