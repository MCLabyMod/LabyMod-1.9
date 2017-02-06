import java.util.Random;

public class xg
  extends wx
{
  private int b;
  private int c;
  private rp d;
  
  public xg(wu ☃)
  {
    super(☃);
  }
  
  public void b()
  {
    this.b += 1;
    if ((this.b % 2 == 0) && (this.b < 10))
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
        for (int ☃ = 0; ☃ < 6; ☃++) {
          this.a.l.a(cy.Q, ☃, ☃, ☃, -☃.b * 0.07999999821186066D * ☃, -☃.c * 0.6000000238418579D, -☃.d * 0.07999999821186066D * ☃, new int[0]);
        }
        ☃.b(0.19634955F);
      }
    }
  }
  
  public void c()
  {
    this.b += 1;
    if (this.b >= 200)
    {
      if (this.c >= 4) {
        this.a.cT().a(xk.e);
      } else {
        this.a.cT().a(xk.g);
      }
    }
    else if (this.b == 10)
    {
      bbj ☃ = new bbj(this.a.bu.p - this.a.p, 0.0D, this.a.bu.r - this.a.r).a();
      float ☃ = 5.0F;
      double ☃ = this.a.bu.p + ☃.b * ☃ / 2.0D;
      double ☃ = this.a.bu.r + ☃.d * ☃ / 2.0D;
      double ☃ = this.a.bu.q + this.a.bu.H / 2.0F;
      
      cj.a ☃ = new cj.a(on.c(☃), on.c(☃), on.c(☃));
      while (this.a.l.d(☃))
      {
        ☃ -= 1.0D;
        ☃.c(on.c(☃), on.c(☃), on.c(☃));
      }
      ☃ = on.c(☃) + 1;
      this.d = new rp(this.a.l, ☃, ☃, ☃);
      this.d.a(this.a);
      this.d.a(☃);
      this.d.b(200);
      this.d.a(cy.Q);
      this.d.a(new rl(rm.g));
      this.a.l.a(this.d);
    }
  }
  
  public void d()
  {
    this.b = 0;
    this.c += 1;
  }
  
  public void e()
  {
    if (this.d != null)
    {
      this.d.T();
      this.d = null;
    }
  }
  
  public xk<xg> i()
  {
    return xk.f;
  }
  
  public void j()
  {
    this.c = 0;
  }
}
