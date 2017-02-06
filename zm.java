import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.List;
import java.util.Random;

public abstract class zm
  extends rr
  implements zs
{
  private static final Predicate<rr> f = Predicates.and(new Predicate[] { rv.e, rv.a, new Predicate()
  {
    public boolean a(rr ☃)
    {
      return ☃.ap();
    }
  } });
  
  public static enum a
  {
    private a() {}
    
    public static a a(int ☃)
    {
      if ((☃ < 0) || (☃ > values().length)) {
        ☃ = 0;
      }
      return values()[☃];
    }
  }
  
  private static final ke<Byte> g = kh.a(zm.class, kg.a);
  private int h = -1;
  private int as = -1;
  private int at = -1;
  private ajt au;
  private int av;
  protected boolean a;
  protected int b;
  public zm.a c = zm.a.a;
  public int d;
  public rr e;
  private int aw;
  private int ax;
  private double ay = 2.0D;
  private int az;
  
  public zm(aht ☃)
  {
    super(☃);
    
    a(0.5F, 0.5F);
  }
  
  public zm(aht ☃, double ☃, double ☃, double ☃)
  {
    this(☃);
    
    b(☃, ☃, ☃);
  }
  
  public zm(aht ☃, sa ☃)
  {
    this(☃, ☃.p, ☃.q + ☃.bn() - 0.10000000149011612D, ☃.r);
    
    this.e = ☃;
    if ((☃ instanceof zj)) {
      this.c = zm.a.b;
    }
  }
  
  public boolean a(double ☃)
  {
    double ☃ = bl().a() * 10.0D;
    if (Double.isNaN(☃)) {
      ☃ = 1.0D;
    }
    ☃ *= 64.0D * be();
    return ☃ < ☃ * ☃;
  }
  
  protected void i()
  {
    this.Z.a(g, Byte.valueOf((byte)0));
  }
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    float ☃ = -on.a(☃ * 0.017453292F) * on.b(☃ * 0.017453292F);
    float ☃ = -on.a(☃ * 0.017453292F);
    float ☃ = on.b(☃ * 0.017453292F) * on.b(☃ * 0.017453292F);
    c(☃, ☃, ☃, ☃, ☃);
    
    this.s += ☃.s;
    this.u += ☃.u;
    if (!☃.z) {
      this.t += ☃.t;
    }
  }
  
  public void c(double ☃, double ☃, double ☃, float ☃, float ☃)
  {
    float ☃ = on.a(☃ * ☃ + ☃ * ☃ + ☃ * ☃);
    
    ☃ /= ☃;
    ☃ /= ☃;
    ☃ /= ☃;
    
    ☃ += this.S.nextGaussian() * 0.007499999832361937D * ☃;
    ☃ += this.S.nextGaussian() * 0.007499999832361937D * ☃;
    ☃ += this.S.nextGaussian() * 0.007499999832361937D * ☃;
    
    ☃ *= ☃;
    ☃ *= ☃;
    ☃ *= ☃;
    
    this.s = ☃;
    this.t = ☃;
    this.u = ☃;
    
    float ☃ = on.a(☃ * ☃ + ☃ * ☃);
    
    this.x = (this.v = (float)(on.b(☃, ☃) * 57.2957763671875D));
    this.y = (this.w = (float)(on.b(☃, ☃) * 57.2957763671875D));
    this.aw = 0;
  }
  
  public void a(double ☃, double ☃, double ☃, float ☃, float ☃, int ☃, boolean ☃)
  {
    b(☃, ☃, ☃);
    b(☃, ☃);
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
      this.y = this.w;
      this.x = this.v;
      b(this.p, this.q, this.r, this.v, this.w);
      this.aw = 0;
    }
  }
  
  public void m()
  {
    super.m();
    if ((this.y == 0.0F) && (this.x == 0.0F))
    {
      float ☃ = on.a(this.s * this.s + this.u * this.u);
      this.x = (this.v = (float)(on.b(this.s, this.u) * 57.2957763671875D));
      this.y = (this.w = (float)(on.b(this.t, ☃) * 57.2957763671875D));
    }
    cj ☃ = new cj(this.h, this.as, this.at);
    arc ☃ = this.l.o(☃);
    ajt ☃ = ☃.t();
    if (☃.a() != axe.a)
    {
      bbh ☃ = ☃.d(this.l, ☃);
      if ((☃ != ajt.k) && (☃.a(☃).a(new bbj(this.p, this.q, this.r)))) {
        this.a = true;
      }
    }
    if (this.d > 0) {
      this.d -= 1;
    }
    if (this.a)
    {
      int ☃ = ☃.e(☃);
      if ((☃ != this.au) || (☃ != this.av))
      {
        this.a = false;
        
        this.s *= this.S.nextFloat() * 0.2F;
        this.t *= this.S.nextFloat() * 0.2F;
        this.u *= this.S.nextFloat() * 0.2F;
        this.aw = 0;
        this.ax = 0;
      }
      else
      {
        this.aw += 1;
        if (this.aw >= 1200) {
          T();
        }
      }
      this.b += 1;
      return;
    }
    this.b = 0;
    this.ax += 1;
    
    bbj ☃ = new bbj(this.p, this.q, this.r);
    bbj ☃ = new bbj(this.p + this.s, this.q + this.t, this.r + this.u);
    bbi ☃ = this.l.a(☃, ☃, false, true, false);
    
    ☃ = new bbj(this.p, this.q, this.r);
    ☃ = new bbj(this.p + this.s, this.q + this.t, this.r + this.u);
    if (☃ != null) {
      ☃ = new bbj(☃.c.b, ☃.c.c, ☃.c.d);
    }
    rr ☃ = a(☃, ☃);
    if (☃ != null) {
      ☃ = new bbi(☃);
    }
    if ((☃ != null) && (☃.d != null) && ((☃.d instanceof zj)))
    {
      zj ☃ = (zj)☃.d;
      if (((this.e instanceof zj)) && (!((zj)this.e).a(☃))) {
        ☃ = null;
      }
    }
    if (☃ != null) {
      a(☃);
    }
    if (l()) {
      for (int ☃ = 0; ☃ < 4; ☃++) {
        this.l.a(cy.j, this.p + this.s * ☃ / 4.0D, this.q + this.t * ☃ / 4.0D, this.r + this.u * ☃ / 4.0D, -this.s, -this.t + 0.2D, -this.u, new int[0]);
      }
    }
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
    
    float ☃ = 0.99F;
    float ☃ = 0.05F;
    if (ai())
    {
      for (int ☃ = 0; ☃ < 4; ☃++)
      {
        float ☃ = 0.25F;
        this.l.a(cy.e, this.p - this.s * ☃, this.q - this.t * ☃, this.r - this.u * ☃, this.s, this.t, this.u, new int[0]);
      }
      ☃ = 0.6F;
    }
    if (ah()) {
      X();
    }
    this.s *= ☃;
    this.t *= ☃;
    this.u *= ☃;
    this.t -= ☃;
    
    b(this.p, this.q, this.r);
    
    ac();
  }
  
  protected void a(bbi ☃)
  {
    rr ☃ = ☃.d;
    if (☃ != null)
    {
      float ☃ = on.a(this.s * this.s + this.t * this.t + this.u * this.u);
      int ☃ = on.f(☃ * this.ay);
      if (l()) {
        ☃ += this.S.nextInt(☃ / 2 + 2);
      }
      rc ☃;
      rc ☃;
      if (this.e == null) {
        ☃ = rc.a(this, this);
      } else {
        ☃ = rc.a(this, this.e);
      }
      if ((aH()) && (!(☃ instanceof yj))) {
        ☃.g(5);
      }
      if (☃.a(☃, ☃))
      {
        if ((☃ instanceof sa))
        {
          sa ☃ = (sa)☃;
          if (!this.l.E) {
            ☃.k(☃.bX() + 1);
          }
          if (this.az > 0)
          {
            float ☃ = on.a(this.s * this.s + this.u * this.u);
            if (☃ > 0.0F) {
              ☃.g(this.s * this.az * 0.6000000238418579D / ☃, 0.1D, this.u * this.az * 0.6000000238418579D / ☃);
            }
          }
          if ((this.e instanceof sa))
          {
            ago.a(☃, this.e);
            ago.b((sa)this.e, ☃);
          }
          a(☃);
          if ((this.e != null) && (☃ != this.e) && ((☃ instanceof zj)) && ((this.e instanceof lr))) {
            ((lr)this.e).a.a(new gn(6, 0.0F));
          }
        }
        a(ng.t, 1.0F, 1.2F / (this.S.nextFloat() * 0.2F + 0.9F));
        if (!(☃ instanceof yj)) {
          T();
        }
      }
      else
      {
        this.s *= -0.10000000149011612D;
        this.t *= -0.10000000149011612D;
        this.u *= -0.10000000149011612D;
        this.v += 180.0F;
        this.x += 180.0F;
        this.ax = 0;
        if ((!this.l.E) && (this.s * this.s + this.t * this.t + this.u * this.u < 0.0010000000474974513D))
        {
          if (this.c == zm.a.b) {
            a(j(), 0.1F);
          }
          T();
        }
      }
    }
    else
    {
      cj ☃ = ☃.a();
      this.h = ☃.p();
      this.as = ☃.q();
      this.at = ☃.r();
      arc ☃ = this.l.o(☃);
      this.au = ☃.t();
      this.av = this.au.e(☃);
      this.s = ((float)(☃.c.b - this.p));
      this.t = ((float)(☃.c.c - this.q));
      this.u = ((float)(☃.c.d - this.r));
      float ☃ = on.a(this.s * this.s + this.t * this.t + this.u * this.u);
      this.p -= this.s / ☃ * 0.05000000074505806D;
      this.q -= this.t / ☃ * 0.05000000074505806D;
      this.r -= this.u / ☃ * 0.05000000074505806D;
      
      a(ng.t, 1.0F, 1.2F / (this.S.nextFloat() * 0.2F + 0.9F));
      this.a = true;
      this.d = 7;
      a(false);
      if (☃.a() != axe.a) {
        this.au.a(this.l, ☃, ☃, this);
      }
    }
  }
  
  protected void a(sa ☃) {}
  
  protected rr a(bbj ☃, bbj ☃)
  {
    rr ☃ = null;
    List<rr> ☃ = this.l.a(this, bl().a(this.s, this.t, this.u).g(1.0D), f);
    double ☃ = 0.0D;
    for (int ☃ = 0; ☃ < ☃.size(); ☃++)
    {
      rr ☃ = (rr)☃.get(☃);
      if ((☃ != this.e) || (this.ax >= 5))
      {
        bbh ☃ = ☃.bl().g(0.30000001192092896D);
        bbi ☃ = ☃.a(☃, ☃);
        if (☃ != null)
        {
          double ☃ = ☃.g(☃.c);
          if ((☃ < ☃) || (☃ == 0.0D))
          {
            ☃ = ☃;
            ☃ = ☃;
          }
        }
      }
    }
    return ☃;
  }
  
  public void b(dn ☃)
  {
    ☃.a("xTile", this.h);
    ☃.a("yTile", this.as);
    ☃.a("zTile", this.at);
    ☃.a("life", (short)this.aw);
    kk ☃ = (kk)ajt.h.b(this.au);
    ☃.a("inTile", ☃ == null ? "" : ☃.toString());
    ☃.a("inData", (byte)this.av);
    ☃.a("shake", (byte)this.d);
    ☃.a("inGround", (byte)(this.a ? 1 : 0));
    ☃.a("pickup", (byte)this.c.ordinal());
    ☃.a("damage", this.ay);
  }
  
  public void a(dn ☃)
  {
    this.h = ☃.h("xTile");
    this.as = ☃.h("yTile");
    this.at = ☃.h("zTile");
    this.aw = ☃.g("life");
    if (☃.b("inTile", 8)) {
      this.au = ajt.b(☃.l("inTile"));
    } else {
      this.au = ajt.b(☃.f("inTile") & 0xFF);
    }
    this.av = (☃.f("inData") & 0xFF);
    this.d = (☃.f("shake") & 0xFF);
    this.a = (☃.f("inGround") == 1);
    if (☃.b("damage", 99)) {
      this.ay = ☃.k("damage");
    }
    if (☃.b("pickup", 99)) {
      this.c = zm.a.a(☃.f("pickup"));
    } else if (☃.b("player", 99)) {
      this.c = (☃.p("player") ? zm.a.b : zm.a.a);
    }
  }
  
  public void d(zj ☃)
  {
    if ((this.l.E) || (!this.a) || (this.d > 0)) {
      return;
    }
    boolean ☃ = (this.c == zm.a.b) || ((this.c == zm.a.c) && (☃.bJ.d));
    if ((this.c == zm.a.b) && 
      (!☃.br.c(j()))) {
      ☃ = false;
    }
    if (☃)
    {
      a(ng.cU, 0.2F, ((this.S.nextFloat() - this.S.nextFloat()) * 0.7F + 1.0F) * 2.0F);
      ☃.a(this, 1);
      T();
    }
  }
  
  protected abstract adq j();
  
  protected boolean ae()
  {
    return false;
  }
  
  public int d(float ☃)
  {
    return 15728880;
  }
  
  public void c(double ☃)
  {
    this.ay = ☃;
  }
  
  public double k()
  {
    return this.ay;
  }
  
  public void a(int ☃)
  {
    this.az = ☃;
  }
  
  public boolean aT()
  {
    return false;
  }
  
  public float bn()
  {
    return 0.0F;
  }
  
  public void a(boolean ☃)
  {
    byte ☃ = ((Byte)this.Z.a(g)).byteValue();
    if (☃) {
      this.Z.b(g, Byte.valueOf((byte)(☃ | 0x1)));
    } else {
      this.Z.b(g, Byte.valueOf((byte)(☃ & 0xFFFFFFFE)));
    }
  }
  
  public boolean l()
  {
    byte ☃ = ((Byte)this.Z.a(g)).byteValue();
    return (☃ & 0x1) != 0;
  }
}
