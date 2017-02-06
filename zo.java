import java.util.Random;

public class zo
  extends rr
{
  private double a;
  private double b;
  private double c;
  private int d;
  private boolean e;
  
  public zo(aht ☃)
  {
    super(☃);
    a(0.25F, 0.25F);
  }
  
  protected void i() {}
  
  public boolean a(double ☃)
  {
    double ☃ = bl().a() * 4.0D;
    if (Double.isNaN(☃)) {
      ☃ = 4.0D;
    }
    ☃ *= 64.0D;
    return ☃ < ☃ * ☃;
  }
  
  public zo(aht ☃, double ☃, double ☃, double ☃)
  {
    super(☃);
    this.d = 0;
    
    a(0.25F, 0.25F);
    
    b(☃, ☃, ☃);
  }
  
  public void a(cj ☃)
  {
    double ☃ = ☃.p();
    int ☃ = ☃.q();
    double ☃ = ☃.r();
    
    double ☃ = ☃ - this.p;double ☃ = ☃ - this.r;
    float ☃ = on.a(☃ * ☃ + ☃ * ☃);
    if (☃ > 12.0F)
    {
      this.a = (this.p + ☃ / ☃ * 12.0D);
      this.c = (this.r + ☃ / ☃ * 12.0D);
      this.b = (this.q + 8.0D);
    }
    else
    {
      this.a = ☃;
      this.b = ☃;
      this.c = ☃;
    }
    this.d = 0;
    this.e = (this.S.nextInt(5) > 0);
  }
  
  public void i(double ☃, double ☃, double ☃)
  {
    this.s = ☃;
    this.t = ☃;
    this.u = ☃;
    if ((this.y == 0.0F) && (this.x == 0.0F))
    {
      float ☃ = on.a(☃ * ☃ + ☃ * ☃);
      this.x = (this.v = (float)(on.b(☃, ☃) * 57.2957763671875D));
      this.y = (this.w = (float)(on.b(☃, ☃) * 57.2957763671875D));
    }
  }
  
  public void m()
  {
    this.M = this.p;
    this.N = this.q;
    this.O = this.r;
    super.m();
    
    this.p += this.s;
    this.q += this.t;
    this.r += this.u;
    
    float ☃ = on.a(this.s * this.s + this.u * this.u);
    this.v = ((float)(on.b(this.s, this.u) * 57.2957763671875D));
    this.w = ((float)(on.b(this.t, ☃) * 57.2957763671875D));
    while (this.w - this.y < -180.0F) {
      this.y -= 360.0F;
    }
    while (this.w - this.y >= 180.0F) {
      this.y += 360.0F;
    }
    while (this.v - this.x < -180.0F) {
      this.x -= 360.0F;
    }
    while (this.v - this.x >= 180.0F) {
      this.x += 360.0F;
    }
    this.w = (this.y + (this.w - this.y) * 0.2F);
    this.v = (this.x + (this.v - this.x) * 0.2F);
    if (!this.l.E)
    {
      double ☃ = this.a - this.p;double ☃ = this.c - this.r;
      float ☃ = (float)Math.sqrt(☃ * ☃ + ☃ * ☃);
      float ☃ = (float)on.b(☃, ☃);
      double ☃ = ☃ + (☃ - ☃) * 0.0025D;
      if (☃ < 1.0F)
      {
        ☃ *= 0.8D;
        this.t *= 0.8D;
      }
      this.s = (Math.cos(☃) * ☃);
      this.u = (Math.sin(☃) * ☃);
      if (this.q < this.b) {
        this.t += (1.0D - this.t) * 0.014999999664723873D;
      } else {
        this.t += (-1.0D - this.t) * 0.014999999664723873D;
      }
    }
    float ☃ = 0.25F;
    if (ai()) {
      for (int ☃ = 0; ☃ < 4; ☃++) {
        this.l.a(cy.e, this.p - this.s * ☃, this.q - this.t * ☃, this.r - this.u * ☃, this.s, this.t, this.u, new int[0]);
      }
    } else {
      this.l.a(cy.y, this.p - this.s * ☃ + this.S.nextDouble() * 0.6D - 0.3D, this.q - this.t * ☃ - 0.5D, this.r - this.u * ☃ + this.S.nextDouble() * 0.6D - 0.3D, this.s, this.t, this.u, new int[0]);
    }
    if (!this.l.E)
    {
      b(this.p, this.q, this.r);
      
      this.d += 1;
      if ((this.d > 80) && (!this.l.E))
      {
        T();
        if (this.e) {
          this.l.a(new yd(this.l, this.p, this.q, this.r, new adq(ads.bR)));
        } else {
          this.l.b(2003, new cj(this), 0);
        }
      }
    }
  }
  
  public void b(dn ☃) {}
  
  public void a(dn ☃) {}
  
  public float e(float ☃)
  {
    return 1.0F;
  }
  
  public int d(float ☃)
  {
    return 15728880;
  }
  
  public boolean aT()
  {
    return false;
  }
}
