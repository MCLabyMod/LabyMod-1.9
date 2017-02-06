import java.util.Random;

public class aap
  extends aah
{
  private int a = -1;
  
  public aap(aht ☃)
  {
    super(☃);
  }
  
  public aap(aht ☃, double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃, ☃);
  }
  
  public aah.a v()
  {
    return aah.a.d;
  }
  
  public arc x()
  {
    return aju.W.u();
  }
  
  public void m()
  {
    super.m();
    if (this.a > 0)
    {
      this.a -= 1;
      this.l.a(cy.l, this.p, this.q + 0.5D, this.r, 0.0D, 0.0D, 0.0D, new int[0]);
    }
    else if (this.a == 0)
    {
      c(this.s * this.s + this.u * this.u);
    }
    if (this.A)
    {
      double ☃ = this.s * this.s + this.u * this.u;
      if (☃ >= 0.009999999776482582D) {
        c(☃);
      }
    }
  }
  
  public boolean a(rc ☃, float ☃)
  {
    rr ☃ = ☃.i();
    if ((☃ instanceof zm))
    {
      zm ☃ = (zm)☃;
      if (☃.aH()) {
        c(☃.s * ☃.s + ☃.t * ☃.t + ☃.u * ☃.u);
      }
    }
    return super.a(☃, ☃);
  }
  
  public void a(rc ☃)
  {
    super.a(☃);
    
    double ☃ = this.s * this.s + this.u * this.u;
    if ((!☃.c()) && (this.l.U().b("doEntityDrops"))) {
      a(new adq(aju.W, 1), 0.0F);
    }
    if ((☃.o()) || (☃.c()) || (☃ >= 0.009999999776482582D)) {
      c(☃);
    }
  }
  
  protected void c(double ☃)
  {
    if (!this.l.E)
    {
      double ☃ = Math.sqrt(☃);
      if (☃ > 5.0D) {
        ☃ = 5.0D;
      }
      this.l.a(this, this.p, this.q, this.r, (float)(4.0D + this.S.nextDouble() * 1.5D * ☃), true);
      T();
    }
  }
  
  public void e(float ☃, float ☃)
  {
    if (☃ >= 3.0F)
    {
      float ☃ = ☃ / 10.0F;
      c(☃ * ☃);
    }
    super.e(☃, ☃);
  }
  
  public void a(int ☃, int ☃, int ☃, boolean ☃)
  {
    if ((☃) && (this.a < 0)) {
      j();
    }
  }
  
  public void a(byte ☃)
  {
    if (☃ == 10) {
      j();
    } else {
      super.a(☃);
    }
  }
  
  public void j()
  {
    this.a = 80;
    if (!this.l.E)
    {
      this.l.a(this, (byte)10);
      if (!ad()) {
        this.l.a(null, this.p, this.q, this.r, ng.gj, nh.e, 1.0F, 1.0F);
      }
    }
  }
  
  public int k()
  {
    return this.a;
  }
  
  public boolean l()
  {
    return this.a > -1;
  }
  
  public float a(ahp ☃, aht ☃, cj ☃, arc ☃)
  {
    if ((l()) && ((ajp.i(☃)) || (ajp.b(☃, ☃.a())))) {
      return 0.0F;
    }
    return super.a(☃, ☃, ☃, ☃);
  }
  
  public boolean a(ahp ☃, aht ☃, cj ☃, arc ☃, float ☃)
  {
    if ((l()) && ((ajp.i(☃)) || (ajp.b(☃, ☃.a())))) {
      return false;
    }
    return super.a(☃, ☃, ☃, ☃, ☃);
  }
  
  protected void a(dn ☃)
  {
    super.a(☃);
    if (☃.b("TNTFuse", 99)) {
      this.a = ☃.h("TNTFuse");
    }
  }
  
  protected void b(dn ☃)
  {
    super.b(☃);
    ☃.a("TNTFuse", this.a);
  }
}
