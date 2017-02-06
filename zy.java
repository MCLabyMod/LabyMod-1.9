import java.util.List;
import java.util.Random;
import java.util.UUID;

public abstract class zy
  extends rr
  implements zs
{
  private int d = -1;
  private int e = -1;
  private int f = -1;
  private ajt g;
  protected boolean a;
  public int b;
  private sa h;
  private String as;
  private int at;
  private int au;
  public rr c;
  private int av;
  
  public zy(aht ☃)
  {
    super(☃);
    
    a(0.25F, 0.25F);
  }
  
  public zy(aht ☃, double ☃, double ☃, double ☃)
  {
    this(☃);
    
    b(☃, ☃, ☃);
  }
  
  public zy(aht ☃, sa ☃)
  {
    this(☃, ☃.p, ☃.q + ☃.bn() - 0.10000000149011612D, ☃.r);
    
    this.h = ☃;
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
  
  public void a(rr ☃, float ☃, float ☃, float ☃, float ☃, float ☃)
  {
    float ☃ = -on.a(☃ * 0.017453292F) * on.b(☃ * 0.017453292F);
    float ☃ = -on.a((☃ + ☃) * 0.017453292F);
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
    this.at = 0;
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
    if (this.b > 0) {
      this.b -= 1;
    }
    if (this.a)
    {
      if (this.l.o(new cj(this.d, this.e, this.f)).t() == this.g)
      {
        this.at += 1;
        if (this.at == 1200) {
          T();
        }
        return;
      }
      this.a = false;
      
      this.s *= this.S.nextFloat() * 0.2F;
      this.t *= this.S.nextFloat() * 0.2F;
      this.u *= this.S.nextFloat() * 0.2F;
      this.at = 0;
      this.au = 0;
    }
    else
    {
      this.au += 1;
    }
    bbj ☃ = new bbj(this.p, this.q, this.r);
    bbj ☃ = new bbj(this.p + this.s, this.q + this.t, this.r + this.u);
    bbi ☃ = this.l.a(☃, ☃);
    
    ☃ = new bbj(this.p, this.q, this.r);
    ☃ = new bbj(this.p + this.s, this.q + this.t, this.r + this.u);
    if (☃ != null) {
      ☃ = new bbj(☃.c.b, ☃.c.c, ☃.c.d);
    }
    rr ☃ = null;
    List<rr> ☃ = this.l.b(this, bl().a(this.s, this.t, this.u).g(1.0D));
    double ☃ = 0.0D;
    boolean ☃ = false;
    for (int ☃ = 0; ☃ < ☃.size(); ☃++)
    {
      rr ☃ = (rr)☃.get(☃);
      if (☃.ap()) {
        if (☃ == this.c)
        {
          ☃ = true;
        }
        else if ((this.T < 2) && (this.c == null))
        {
          this.c = ☃;
          ☃ = true;
        }
        else
        {
          ☃ = false;
          
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
    }
    if (this.c != null) {
      if (☃) {
        this.av = 2;
      } else if (this.av-- <= 0) {
        this.c = null;
      }
    }
    if (☃ != null) {
      ☃ = new bbi(☃);
    }
    if (☃ != null) {
      if ((☃.a == bbi.a.b) && (this.l.o(☃.a()).t() == aju.aY)) {
        e(☃.a());
      } else {
        a(☃);
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
    float ☃ = j();
    if (ai())
    {
      for (int ☃ = 0; ☃ < 4; ☃++)
      {
        float ☃ = 0.25F;
        this.l.a(cy.e, this.p - this.s * ☃, this.q - this.t * ☃, this.r - this.u * ☃, this.s, this.t, this.u, new int[0]);
      }
      ☃ = 0.8F;
    }
    this.s *= ☃;
    this.t *= ☃;
    this.u *= ☃;
    this.t -= ☃;
    
    b(this.p, this.q, this.r);
  }
  
  protected float j()
  {
    return 0.03F;
  }
  
  protected abstract void a(bbi parambbi);
  
  public void b(dn ☃)
  {
    ☃.a("xTile", this.d);
    ☃.a("yTile", this.e);
    ☃.a("zTile", this.f);
    kk ☃ = (kk)ajt.h.b(this.g);
    ☃.a("inTile", ☃ == null ? "" : ☃.toString());
    ☃.a("shake", (byte)this.b);
    ☃.a("inGround", (byte)(this.a ? 1 : 0));
    if (((this.as == null) || (this.as.isEmpty())) && ((this.h instanceof zj))) {
      this.as = this.h.h_();
    }
    ☃.a("ownerName", this.as == null ? "" : this.as);
  }
  
  public void a(dn ☃)
  {
    this.d = ☃.h("xTile");
    this.e = ☃.h("yTile");
    this.f = ☃.h("zTile");
    if (☃.b("inTile", 8)) {
      this.g = ajt.b(☃.l("inTile"));
    } else {
      this.g = ajt.b(☃.f("inTile") & 0xFF);
    }
    this.b = (☃.f("shake") & 0xFF);
    this.a = (☃.f("inGround") == 1);
    this.h = null;
    this.as = ☃.l("ownerName");
    if ((this.as != null) && (this.as.isEmpty())) {
      this.as = null;
    }
    this.h = k();
  }
  
  public sa k()
  {
    if ((this.h == null) && (this.as != null) && (!this.as.isEmpty()))
    {
      this.h = this.l.a(this.as);
      if ((this.h == null) && ((this.l instanceof lp))) {
        try
        {
          rr ☃ = ((lp)this.l).a(UUID.fromString(this.as));
          if ((☃ instanceof sa)) {
            this.h = ((sa)☃);
          }
        }
        catch (Throwable ☃)
        {
          this.h = null;
        }
      }
    }
    return this.h;
  }
}
