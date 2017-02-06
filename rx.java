import java.util.Random;

public class rx
  extends rr
{
  public int a;
  public int b;
  public int c;
  private int d = 5;
  private int e;
  private zj f;
  private int g;
  
  public rx(aht ☃, double ☃, double ☃, double ☃, int ☃)
  {
    super(☃);
    a(0.5F, 0.5F);
    b(☃, ☃, ☃);
    
    this.v = ((float)(Math.random() * 360.0D));
    
    this.s = ((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F);
    this.t = ((float)(Math.random() * 0.2D) * 2.0F);
    this.u = ((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F);
    
    this.e = ☃;
  }
  
  protected boolean ae()
  {
    return false;
  }
  
  public rx(aht ☃)
  {
    super(☃);
    a(0.25F, 0.25F);
  }
  
  protected void i() {}
  
  public int d(float ☃)
  {
    float ☃ = 0.5F;
    ☃ = on.a(☃, 0.0F, 1.0F);
    int ☃ = super.d(☃);
    
    int ☃ = ☃ & 0xFF;
    int ☃ = ☃ >> 16 & 0xFF;
    ☃ += (int)(☃ * 15.0F * 16.0F);
    if (☃ > 240) {
      ☃ = 240;
    }
    return ☃ | ☃ << 16;
  }
  
  public void m()
  {
    super.m();
    if (this.c > 0) {
      this.c -= 1;
    }
    this.m = this.p;
    this.n = this.q;
    this.o = this.r;
    
    this.t -= 0.029999999329447746D;
    if (this.l.o(new cj(this)).a() == axe.i)
    {
      this.t = 0.20000000298023224D;
      this.s = ((this.S.nextFloat() - this.S.nextFloat()) * 0.2F);
      this.u = ((this.S.nextFloat() - this.S.nextFloat()) * 0.2F);
      a(ng.bz, 0.4F, 2.0F + this.S.nextFloat() * 0.4F);
    }
    j(this.p, (bl().b + bl().e) / 2.0D, this.r);
    
    double ☃ = 8.0D;
    if (this.g < this.a - 20 + O() % 100)
    {
      if ((this.f == null) || (this.f.h(this) > ☃ * ☃)) {
        this.f = this.l.a(this, ☃);
      }
      this.g = this.a;
    }
    if ((this.f != null) && (this.f.y())) {
      this.f = null;
    }
    if (this.f != null)
    {
      double ☃ = (this.f.p - this.p) / ☃;
      double ☃ = (this.f.q + this.f.bn() / 2.0D - this.q) / ☃;
      double ☃ = (this.f.r - this.r) / ☃;
      double ☃ = Math.sqrt(☃ * ☃ + ☃ * ☃ + ☃ * ☃);
      double ☃ = 1.0D - ☃;
      if (☃ > 0.0D)
      {
        ☃ *= ☃;
        this.s += ☃ / ☃ * ☃ * 0.1D;
        this.t += ☃ / ☃ * ☃ * 0.1D;
        this.u += ☃ / ☃ * ☃ * 0.1D;
      }
    }
    d(this.s, this.t, this.u);
    
    float ☃ = 0.98F;
    if (this.z) {
      ☃ = this.l.o(new cj(on.c(this.p), on.c(bl().b) - 1, on.c(this.r))).t().z * 0.98F;
    }
    this.s *= ☃;
    this.t *= 0.9800000190734863D;
    this.u *= ☃;
    if (this.z) {
      this.t *= -0.8999999761581421D;
    }
    this.a += 1;
    
    this.b += 1;
    if (this.b >= 6000) {
      T();
    }
  }
  
  public boolean aj()
  {
    return this.l.a(bl(), axe.h, this);
  }
  
  protected void h(int ☃)
  {
    a(rc.a, ☃);
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (b(☃)) {
      return false;
    }
    ao();
    this.d = ((int)(this.d - ☃));
    if (this.d <= 0) {
      T();
    }
    return false;
  }
  
  public void b(dn ☃)
  {
    ☃.a("Health", (short)this.d);
    ☃.a("Age", (short)this.b);
    ☃.a("Value", (short)this.e);
  }
  
  public void a(dn ☃)
  {
    this.d = ☃.g("Health");
    this.b = ☃.g("Age");
    this.e = ☃.g("Value");
  }
  
  public void d(zj ☃)
  {
    if (this.l.E) {
      return;
    }
    if ((this.c == 0) && (☃.by == 0))
    {
      ☃.by = 2;
      this.l.a(null, ☃.p, ☃.q, ☃.r, ng.bi, nh.h, 0.1F, 0.5F * ((this.S.nextFloat() - this.S.nextFloat()) * 0.7F + 1.8F));
      ☃.a(this, 1);
      adq ☃ = ago.b(agq.A, ☃);
      if ((☃ != null) && (☃.g()))
      {
        int ☃ = Math.min(d(this.e), ☃.h());
        this.e -= b(☃);
        ☃.b(☃.h() - ☃);
      }
      if (this.e > 0) {
        ☃.n(this.e);
      }
      T();
    }
  }
  
  private int b(int ☃)
  {
    return ☃ / 2;
  }
  
  private int d(int ☃)
  {
    return ☃ * 2;
  }
  
  public int j()
  {
    return this.e;
  }
  
  public int k()
  {
    if (this.e >= 2477) {
      return 10;
    }
    if (this.e >= 1237) {
      return 9;
    }
    if (this.e >= 617) {
      return 8;
    }
    if (this.e >= 307) {
      return 7;
    }
    if (this.e >= 149) {
      return 6;
    }
    if (this.e >= 73) {
      return 5;
    }
    if (this.e >= 37) {
      return 4;
    }
    if (this.e >= 17) {
      return 3;
    }
    if (this.e >= 7) {
      return 2;
    }
    if (this.e >= 3) {
      return 1;
    }
    return 0;
  }
  
  public static int a(int ☃)
  {
    if (☃ >= 2477) {
      return 2477;
    }
    if (☃ >= 1237) {
      return 1237;
    }
    if (☃ >= 617) {
      return 617;
    }
    if (☃ >= 307) {
      return 307;
    }
    if (☃ >= 149) {
      return 149;
    }
    if (☃ >= 73) {
      return 73;
    }
    if (☃ >= 37) {
      return 37;
    }
    if (☃ >= 17) {
      return 17;
    }
    if (☃ >= 7) {
      return 7;
    }
    if (☃ >= 3) {
      return 3;
    }
    return 1;
  }
  
  public boolean aT()
  {
    return false;
  }
}
