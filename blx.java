import java.util.List;
import java.util.Random;

public class blx
{
  private static final bbh a = new bbh(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
  protected aht b;
  protected double c;
  protected double d;
  protected double e;
  protected double f;
  protected double g;
  protected double h;
  protected double i;
  protected double j;
  protected double k;
  private bbh G = a;
  protected boolean l;
  protected boolean m;
  protected float n = 0.6F;
  protected float o = 1.8F;
  protected Random p = new Random();
  protected int q;
  protected int r;
  protected float s;
  protected float t;
  protected int u;
  protected int v;
  protected float w;
  protected float x;
  protected float y;
  protected float z;
  protected float A;
  protected float B = 1.0F;
  protected bvh C;
  public static double D;
  public static double E;
  public static double F;
  
  protected blx(aht ☃, double ☃, double ☃, double ☃)
  {
    this.b = ☃;
    
    a(0.2F, 0.2F);
    b(☃, ☃, ☃);
    this.c = ☃;
    this.d = ☃;
    this.e = ☃;
    this.y = (this.z = this.A = 1.0F);
    
    this.s = (this.p.nextFloat() * 3.0F);
    this.t = (this.p.nextFloat() * 3.0F);
    
    this.w = ((this.p.nextFloat() * 0.5F + 0.5F) * 2.0F);
    
    this.v = ((int)(4.0F / (this.p.nextFloat() * 0.9F + 0.1F)));
    this.u = 0;
  }
  
  public blx(aht ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃)
  {
    this(☃, ☃, ☃, ☃);
    
    this.i = (☃ + (Math.random() * 2.0D - 1.0D) * 0.4000000059604645D);
    this.j = (☃ + (Math.random() * 2.0D - 1.0D) * 0.4000000059604645D);
    this.k = (☃ + (Math.random() * 2.0D - 1.0D) * 0.4000000059604645D);
    float ☃ = (float)(Math.random() + Math.random() + 1.0D) * 0.15F;
    
    float ☃ = on.a(this.i * this.i + this.j * this.j + this.k * this.k);
    this.i = (this.i / ☃ * ☃ * 0.4000000059604645D);
    this.j = (this.j / ☃ * ☃ * 0.4000000059604645D + 0.10000000149011612D);
    this.k = (this.k / ☃ * ☃ * 0.4000000059604645D);
  }
  
  public blx c(float ☃)
  {
    this.i *= ☃;
    this.j = ((this.j - 0.10000000149011612D) * ☃ + 0.10000000149011612D);
    this.k *= ☃;
    return this;
  }
  
  public blx d(float ☃)
  {
    a(0.2F * ☃, 0.2F * ☃);
    this.w *= ☃;
    return this;
  }
  
  public void a(float ☃, float ☃, float ☃)
  {
    this.y = ☃;
    this.z = ☃;
    this.A = ☃;
  }
  
  public void e(float ☃)
  {
    this.B = ☃;
  }
  
  public boolean c()
  {
    return false;
  }
  
  public float d()
  {
    return this.y;
  }
  
  public float e()
  {
    return this.z;
  }
  
  public float f()
  {
    return this.A;
  }
  
  public void a(int ☃)
  {
    this.v = ☃;
  }
  
  public void a()
  {
    this.c = this.f;
    this.d = this.g;
    this.e = this.h;
    if (this.u++ >= this.v) {
      i();
    }
    this.j -= 0.04D * this.x;
    a(this.i, this.j, this.k);
    this.i *= 0.9800000190734863D;
    this.j *= 0.9800000190734863D;
    this.k *= 0.9800000190734863D;
    if (this.l)
    {
      this.i *= 0.699999988079071D;
      this.k *= 0.699999988079071D;
    }
  }
  
  public void a(bmz ☃, rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    float ☃ = this.q / 16.0F;
    float ☃ = ☃ + 0.0624375F;
    float ☃ = this.r / 16.0F;
    float ☃ = ☃ + 0.0624375F;
    float ☃ = 0.1F * this.w;
    if (this.C != null)
    {
      ☃ = this.C.e();
      ☃ = this.C.f();
      ☃ = this.C.g();
      ☃ = this.C.h();
    }
    float ☃ = (float)(this.c + (this.f - this.c) * ☃ - D);
    float ☃ = (float)(this.d + (this.g - this.d) * ☃ - E);
    float ☃ = (float)(this.e + (this.h - this.e) * ☃ - F);
    
    int ☃ = a(☃);
    int ☃ = ☃ >> 16 & 0xFFFF;
    int ☃ = ☃ & 0xFFFF;
    
    ☃.b(☃ - ☃ * ☃ - ☃ * ☃, ☃ - ☃ * ☃, ☃ - ☃ * ☃ - ☃ * ☃).a(☃, ☃).a(this.y, this.z, this.A, this.B).a(☃, ☃).d();
    ☃.b(☃ - ☃ * ☃ + ☃ * ☃, ☃ + ☃ * ☃, ☃ - ☃ * ☃ + ☃ * ☃).a(☃, ☃).a(this.y, this.z, this.A, this.B).a(☃, ☃).d();
    ☃.b(☃ + ☃ * ☃ + ☃ * ☃, ☃ + ☃ * ☃, ☃ + ☃ * ☃ + ☃ * ☃).a(☃, ☃).a(this.y, this.z, this.A, this.B).a(☃, ☃).d();
    ☃.b(☃ + ☃ * ☃ - ☃ * ☃, ☃ - ☃ * ☃, ☃ + ☃ * ☃ - ☃ * ☃).a(☃, ☃).a(this.y, this.z, this.A, this.B).a(☃, ☃).d();
  }
  
  public int b()
  {
    return 0;
  }
  
  public void a(bvh ☃)
  {
    int ☃ = b();
    if (☃ == 1) {
      this.C = ☃;
    } else {
      throw new RuntimeException("Invalid call to Particle.setTex, use coordinate methods");
    }
  }
  
  public void b(int ☃)
  {
    if (b() != 0) {
      throw new RuntimeException("Invalid call to Particle.setMiscTex");
    }
    this.q = (☃ % 16);
    this.r = (☃ / 16);
  }
  
  public void h()
  {
    this.q += 1;
  }
  
  public String toString()
  {
    return getClass().getSimpleName() + ", Pos (" + this.f + "," + this.g + "," + this.h + "), RGBA (" + this.y + "," + this.z + "," + this.A + "," + this.B + "), Age " + this.u;
  }
  
  public void i()
  {
    this.m = true;
  }
  
  protected void a(float ☃, float ☃)
  {
    if ((☃ != this.n) || (☃ != this.o))
    {
      this.n = ☃;
      this.o = ☃;
      bbh ☃ = l();
      a(new bbh(☃.a, ☃.b, ☃.c, ☃.a + this.n, ☃.b + this.o, ☃.c + this.n));
    }
  }
  
  public void b(double ☃, double ☃, double ☃)
  {
    this.f = ☃;
    this.g = ☃;
    this.h = ☃;
    float ☃ = this.n / 2.0F;
    float ☃ = this.o;
    a(new bbh(☃ - ☃, ☃, ☃ - ☃, ☃ + ☃, ☃ + ☃, ☃ + ☃));
  }
  
  public void a(double ☃, double ☃, double ☃)
  {
    double ☃ = ☃;
    double ☃ = ☃;
    double ☃ = ☃;
    
    List<bbh> ☃ = this.b.a(null, l().a(☃, ☃, ☃));
    for (bbh ☃ : ☃) {
      ☃ = ☃.b(l(), ☃);
    }
    a(l().c(0.0D, ☃, 0.0D));
    for (bbh ☃ : ☃) {
      ☃ = ☃.a(l(), ☃);
    }
    a(l().c(☃, 0.0D, 0.0D));
    for (bbh ☃ : ☃) {
      ☃ = ☃.c(l(), ☃);
    }
    a(l().c(0.0D, 0.0D, ☃));
    
    j();
    
    this.l = ((☃ != ☃) && (☃ < 0.0D));
    if (☃ != ☃) {
      this.i = 0.0D;
    }
    if (☃ != ☃) {
      this.k = 0.0D;
    }
  }
  
  protected void j()
  {
    bbh ☃ = l();
    this.f = ((☃.a + ☃.d) / 2.0D);
    this.g = ☃.b;
    this.h = ((☃.c + ☃.f) / 2.0D);
  }
  
  public int a(float ☃)
  {
    cj ☃ = new cj(this.f, this.g, this.h);
    if (this.b.e(☃)) {
      return this.b.b(☃, 0);
    }
    return 0;
  }
  
  public boolean k()
  {
    return !this.m;
  }
  
  public bbh l()
  {
    return this.G;
  }
  
  public void a(bbh ☃)
  {
    this.G = ☃;
  }
}
