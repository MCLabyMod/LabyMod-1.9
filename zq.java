import com.google.common.base.Optional;
import java.util.Random;

public class zq
  extends rr
{
  private static final ke<Optional<adq>> a = kh.a(zq.class, kg.f);
  private int b;
  private int c;
  
  public zq(aht ☃)
  {
    super(☃);
    a(0.25F, 0.25F);
  }
  
  protected void i()
  {
    this.Z.a(a, Optional.absent());
  }
  
  public boolean a(double ☃)
  {
    return ☃ < 4096.0D;
  }
  
  public zq(aht ☃, double ☃, double ☃, double ☃, adq ☃)
  {
    super(☃);
    this.b = 0;
    
    a(0.25F, 0.25F);
    
    b(☃, ☃, ☃);
    
    int ☃ = 1;
    if ((☃ != null) && (☃.n()))
    {
      this.Z.b(a, Optional.of(☃));
      
      dn ☃ = ☃.o();
      dn ☃ = ☃.o("Fireworks");
      ☃ += ☃.f("Flight");
    }
    this.s = (this.S.nextGaussian() * 0.001D);
    this.u = (this.S.nextGaussian() * 0.001D);
    this.t = 0.05D;
    
    this.c = (10 * ☃ + this.S.nextInt(6) + this.S.nextInt(7));
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
    
    this.s *= 1.15D;
    this.u *= 1.15D;
    this.t += 0.04D;
    d(this.s, this.t, this.u);
    
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
    if ((this.b == 0) && (!ad())) {
      this.l.a(null, this.p, this.q, this.r, ng.bq, nh.i, 3.0F, 1.0F);
    }
    this.b += 1;
    if ((this.l.E) && (this.b % 2 < 2)) {
      this.l.a(cy.d, this.p, this.q - 0.3D, this.r, this.S.nextGaussian() * 0.05D, -this.t * 0.5D, this.S.nextGaussian() * 0.05D, new int[0]);
    }
    if ((!this.l.E) && (this.b > this.c))
    {
      this.l.a(this, (byte)17);
      T();
    }
  }
  
  public void a(byte ☃)
  {
    if ((☃ == 17) && (this.l.E))
    {
      adq ☃ = (adq)((Optional)this.Z.a(a)).orNull();
      dn ☃ = null;
      if ((☃ != null) && (☃.n())) {
        ☃ = ☃.o().o("Fireworks");
      }
      this.l.a(this.p, this.q, this.r, this.s, this.t, this.u, ☃);
    }
    super.a(☃);
  }
  
  public void b(dn ☃)
  {
    ☃.a("Life", this.b);
    ☃.a("LifeTime", this.c);
    adq ☃ = (adq)((Optional)this.Z.a(a)).orNull();
    if (☃ != null)
    {
      dn ☃ = new dn();
      ☃.b(☃);
      ☃.a("FireworksItem", ☃);
    }
  }
  
  public void a(dn ☃)
  {
    this.b = ☃.h("Life");
    this.c = ☃.h("LifeTime");
    
    dn ☃ = ☃.o("FireworksItem");
    if (☃ != null)
    {
      adq ☃ = adq.a(☃);
      if (☃ != null) {
        this.Z.b(a, Optional.of(☃));
      }
    }
  }
  
  public float e(float ☃)
  {
    return super.e(☃);
  }
  
  public int d(float ☃)
  {
    return super.d(☃);
  }
  
  public boolean aT()
  {
    return false;
  }
}
