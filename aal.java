import java.util.Random;

public class aal
  extends aah
{
  private static final ke<Boolean> c = kh.a(aal.class, kg.h);
  private int d;
  public double a;
  public double b;
  
  public aal(aht ☃)
  {
    super(☃);
  }
  
  public aal(aht ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃);
  }
  
  public aah.a v()
  {
    return aah.a.c;
  }
  
  protected void i()
  {
    super.i();
    this.Z.a(c, Boolean.valueOf(false));
  }
  
  public void m()
  {
    super.m();
    if (this.d > 0) {
      this.d -= 1;
    }
    if (this.d <= 0) {
      this.a = (this.b = 0.0D);
    }
    k(this.d > 0);
    if ((j()) && (this.S.nextInt(4) == 0)) {
      this.l.a(cy.m, this.p, this.q + 0.8D, this.r, 0.0D, 0.0D, 0.0D, new int[0]);
    }
  }
  
  protected double o()
  {
    return 0.2D;
  }
  
  public void a(rc ☃)
  {
    super.a(☃);
    if ((!☃.c()) && (this.l.U().b("doEntityDrops"))) {
      a(new adq(aju.al, 1), 0.0F);
    }
  }
  
  protected void a(cj ☃, arc ☃)
  {
    super.a(☃, ☃);
    
    double ☃ = this.a * this.a + this.b * this.b;
    if ((☃ > 1.0E-4D) && (this.s * this.s + this.u * this.u > 0.001D))
    {
      ☃ = on.a(☃);
      this.a /= ☃;
      this.b /= ☃;
      if (this.a * this.s + this.b * this.u < 0.0D)
      {
        this.a = 0.0D;
        this.b = 0.0D;
      }
      else
      {
        double ☃ = ☃ / o();
        this.a *= ☃;
        this.b *= ☃;
      }
    }
  }
  
  protected void r()
  {
    double ☃ = this.a * this.a + this.b * this.b;
    if (☃ > 1.0E-4D)
    {
      ☃ = on.a(☃);
      this.a /= ☃;
      this.b /= ☃;
      double ☃ = 1.0D;
      this.s *= 0.800000011920929D;
      this.t *= 0.0D;
      this.u *= 0.800000011920929D;
      this.s += this.a * ☃;
      this.u += this.b * ☃;
    }
    else
    {
      this.s *= 0.9800000190734863D;
      this.t *= 0.0D;
      this.u *= 0.9800000190734863D;
    }
    super.r();
  }
  
  public boolean a(zj ☃, adq ☃, qm ☃)
  {
    if ((☃ != null) && (☃.b() == ads.j) && (this.d + 3600 <= 32000))
    {
      if (!☃.bJ.d) {
        ☃.b -= 1;
      }
      this.d += 3600;
    }
    this.a = (this.p - ☃.p);
    this.b = (this.r - ☃.r);
    
    return true;
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    ☃.a("PushX", this.a);
    ☃.a("PushZ", this.b);
    ☃.a("Fuel", (short)this.d);
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    this.a = ☃.k("PushX");
    this.b = ☃.k("PushZ");
    this.d = ☃.g("Fuel");
  }
  
  protected boolean j()
  {
    return ((Boolean)this.Z.a(c)).booleanValue();
  }
  
  protected void k(boolean ☃)
  {
    this.Z.b(c, Boolean.valueOf(☃));
  }
  
  public arc x()
  {
    return (j() ? aju.am : aju.al).u().a(als.a, cq.c);
  }
}
