import com.google.common.base.Predicate;
import java.util.List;
import java.util.Random;

public class yo
  extends yq
{
  private static final ke<Byte> a = kh.a(yo.class, kg.a);
  private static final ke<Integer> b = kh.a(yo.class, kg.b);
  private float c;
  private float bv;
  private float bw;
  private float bx;
  private float by;
  private sa bz;
  private int bA;
  private boolean bB;
  private ug bC;
  
  public yo(aht ☃)
  {
    super(☃);
    
    this.b_ = 10;
    a(0.85F, 0.85F);
    
    this.f = new yo.c(this);
    
    this.bv = (this.c = this.S.nextFloat());
  }
  
  protected void r()
  {
    this.bp.a(4, new yo.a(this));
    tw ☃;
    this.bp.a(5, ☃ = new tw(this, 1.0D));
    this.bp.a(7, this.bC = new ug(this, 1.0D, 80));
    this.bp.a(8, new tp(this, zj.class, 8.0F));
    this.bp.a(8, new tp(this, yo.class, 12.0F, 0.01F));
    this.bp.a(9, new uf(this));
    
    this.bC.a(3);
    ☃.a(3);
    
    this.bq.a(1, new uy(this, sa.class, 10, true, false, new yo.b(this)));
  }
  
  protected void bA()
  {
    super.bA();
    a(yt.e).a(6.0D);
    a(yt.d).a(0.5D);
    a(yt.b).a(16.0D);
    a(yt.a).a(30.0D);
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    
    a(☃.p("Elder"));
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    
    ☃.a("Elder", db());
  }
  
  protected vf b(aht ☃)
  {
    return new vi(this, ☃);
  }
  
  protected void i()
  {
    super.i();
    
    this.Z.a(a, Byte.valueOf((byte)0));
    this.Z.a(b, Integer.valueOf(0));
  }
  
  private boolean a(int ☃)
  {
    return (((Byte)this.Z.a(a)).byteValue() & ☃) != 0;
  }
  
  private void a(int ☃, boolean ☃)
  {
    byte ☃ = ((Byte)this.Z.a(a)).byteValue();
    if (☃) {
      this.Z.b(a, Byte.valueOf((byte)(☃ | ☃)));
    } else {
      this.Z.b(a, Byte.valueOf((byte)(☃ & (☃ ^ 0xFFFFFFFF))));
    }
  }
  
  public boolean o()
  {
    return a(2);
  }
  
  private void o(boolean ☃)
  {
    a(2, ☃);
  }
  
  public int da()
  {
    if (db()) {
      return 60;
    }
    return 80;
  }
  
  public boolean db()
  {
    return a(4);
  }
  
  public void a(boolean ☃)
  {
    a(4, ☃);
    if (☃)
    {
      a(1.9975F, 1.9975F);
      a(yt.d).a(0.30000001192092896D);
      a(yt.e).a(8.0D);
      a(yt.a).a(80.0D);
      cL();
      if (this.bC != null) {
        this.bC.b(400);
      }
    }
  }
  
  public void dc()
  {
    a(true);
    this.by = (this.bx = 1.0F);
  }
  
  private void b(int ☃)
  {
    this.Z.b(b, Integer.valueOf(☃));
  }
  
  public boolean dd()
  {
    return ((Integer)this.Z.a(b)).intValue() != 0;
  }
  
  public sa de()
  {
    if (!dd()) {
      return null;
    }
    if (this.l.E)
    {
      if (this.bz != null) {
        return this.bz;
      }
      rr ☃ = this.l.a(((Integer)this.Z.a(b)).intValue());
      if ((☃ instanceof sa))
      {
        this.bz = ((sa)☃);
        return this.bz;
      }
      return null;
    }
    return A();
  }
  
  public void a(ke<?> ☃)
  {
    super.a(☃);
    if (a.equals(☃))
    {
      if ((db()) && (this.G < 1.0F)) {
        a(1.9975F, 1.9975F);
      }
    }
    else if (b.equals(☃))
    {
      this.bA = 0;
      this.bz = null;
    }
  }
  
  public int C()
  {
    return 160;
  }
  
  protected nf G()
  {
    if (db()) {
      return ai() ? ng.aD : ng.aE;
    }
    return ai() ? ng.ce : ng.cf;
  }
  
  protected nf bR()
  {
    if (db()) {
      return ai() ? ng.aI : ng.aJ;
    }
    return ai() ? ng.ck : ng.cl;
  }
  
  protected nf bS()
  {
    if (db()) {
      return ai() ? ng.aG : ng.aH;
    }
    return ai() ? ng.ch : ng.ci;
  }
  
  protected boolean ae()
  {
    return false;
  }
  
  public float bn()
  {
    return this.H * 0.5F;
  }
  
  public float a(cj ☃)
  {
    if (this.l.o(☃).a() == axe.h) {
      return 10.0F + this.l.n(☃) - 0.5F;
    }
    return super.a(☃);
  }
  
  public void n()
  {
    if (this.l.E)
    {
      this.bv = this.c;
      if (!ai())
      {
        this.bw = 2.0F;
        if ((this.t > 0.0D) && (this.bB) && (!ad())) {
          this.l.a(this.p, this.q, this.r, ng.cj, bz(), 1.0F, 1.0F, false);
        }
        this.bB = ((this.t < 0.0D) && (this.l.d(new cj(this).b(), false)));
      }
      else if (o())
      {
        if (this.bw < 0.5F) {
          this.bw = 4.0F;
        } else {
          this.bw += (0.5F - this.bw) * 0.1F;
        }
      }
      else
      {
        this.bw += (0.125F - this.bw) * 0.2F;
      }
      this.c += this.bw;
      
      this.by = this.bx;
      if (!ai()) {
        this.bx = this.S.nextFloat();
      } else if (o()) {
        this.bx += (0.0F - this.bx) * 0.25F;
      } else {
        this.bx += (1.0F - this.bx) * 0.06F;
      }
      if ((o()) && (ai()))
      {
        bbj ☃ = f(0.0F);
        for (int ☃ = 0; ☃ < 2; ☃++) {
          this.l.a(cy.e, this.p + (this.S.nextDouble() - 0.5D) * this.G - ☃.b * 1.5D, this.q + this.S.nextDouble() * this.H - ☃.c * 1.5D, this.r + (this.S.nextDouble() - 0.5D) * this.G - ☃.d * 1.5D, 0.0D, 0.0D, 0.0D, new int[0]);
        }
      }
      if (dd())
      {
        if (this.bA < da()) {
          this.bA += 1;
        }
        sa ☃ = de();
        if (☃ != null)
        {
          t().a(☃, 90.0F, 90.0F);
          t().a();
          
          double ☃ = s(0.0F);
          double ☃ = ☃.p - this.p;
          double ☃ = ☃.q + ☃.H * 0.5F - (this.q + bn());
          double ☃ = ☃.r - this.r;
          double ☃ = Math.sqrt(☃ * ☃ + ☃ * ☃ + ☃ * ☃);
          ☃ /= ☃;
          ☃ /= ☃;
          ☃ /= ☃;
          double ☃ = this.S.nextDouble();
          while (☃ < ☃)
          {
            ☃ += 1.8D - ☃ + this.S.nextDouble() * (1.7D - ☃);
            this.l.a(cy.e, this.p + ☃ * ☃, this.q + ☃ * ☃ + bn(), this.r + ☃ * ☃, 0.0D, 0.0D, 0.0D, new int[0]);
          }
        }
      }
    }
    if (this.V)
    {
      j(300);
    }
    else if (this.z)
    {
      this.t += 0.5D;
      this.s += (this.S.nextFloat() * 2.0F - 1.0F) * 0.4F;
      this.u += (this.S.nextFloat() * 2.0F - 1.0F) * 0.4F;
      this.v = (this.S.nextFloat() * 360.0F);
      this.z = false;
      this.ai = true;
    }
    if (dd()) {
      this.v = this.aO;
    }
    super.n();
  }
  
  public float a(float ☃)
  {
    return this.bv + (this.c - this.bv) * ☃;
  }
  
  public float r(float ☃)
  {
    return this.by + (this.bx - this.by) * ☃;
  }
  
  public float s(float ☃)
  {
    return (this.bA + ☃) / da();
  }
  
  protected void M()
  {
    super.M();
    if (db())
    {
      int ☃ = 1200;
      int ☃ = 1200;
      int ☃ = 6000;
      int ☃ = 2;
      rk ☃;
      if ((this.T + O()) % 1200 == 0)
      {
        ☃ = rm.d;
        
        List<lr> ☃ = this.l.b(lr.class, new Predicate()
        {
          public boolean a(lr ☃)
          {
            return (yo.this.h(☃) < 2500.0D) && (☃.c.c());
          }
        });
        for (lr ☃ : ☃) {
          if ((!☃.a(☃)) || (☃.b(☃).c() < 2) || (☃.b(☃).b() < 1200))
          {
            ☃.a.a(new gn(10, 0.0F));
            ☃.c(new rl(☃, 6000, 2));
          }
        }
      }
      if (!cY()) {
        a(new cj(this), 16);
      }
    }
  }
  
  protected kk J()
  {
    return db() ? azt.w : azt.v;
  }
  
  protected boolean s_()
  {
    return true;
  }
  
  public boolean cG()
  {
    return (this.l.a(bl(), this)) && (this.l.a(this, bl()).isEmpty());
  }
  
  public boolean cF()
  {
    return ((this.S.nextInt(20) == 0) || (!this.l.i(new cj(this)))) && (super.cF());
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if ((!o()) && (!☃.s()) && ((☃.i() instanceof sa)))
    {
      sa ☃ = (sa)☃.i();
      if (!☃.c()) {
        ☃.a(rc.a(this), 2.0F);
      }
    }
    if (this.bC != null) {
      this.bC.f();
    }
    return super.a(☃, ☃);
  }
  
  public int N()
  {
    return 180;
  }
  
  public void g(float ☃, float ☃)
  {
    if (co())
    {
      if (ai())
      {
        a(☃, ☃, 0.1F);
        d(this.s, this.t, this.u);
        
        this.s *= 0.8999999761581421D;
        this.t *= 0.8999999761581421D;
        this.u *= 0.8999999761581421D;
        if ((!o()) && (A() == null)) {
          this.t -= 0.005D;
        }
      }
      else
      {
        super.g(☃, ☃);
      }
    }
    else {
      super.g(☃, ☃);
    }
  }
  
  static class b
    implements Predicate<sa>
  {
    private yo a;
    
    public b(yo ☃)
    {
      this.a = ☃;
    }
    
    public boolean a(sa ☃)
    {
      return (((☃ instanceof zj)) || ((☃ instanceof wg))) && (☃.h(this.a) > 9.0D);
    }
  }
  
  static class a
    extends tk
  {
    private yo a;
    private int b;
    
    public a(yo ☃)
    {
      this.a = ☃;
      
      a(3);
    }
    
    public boolean a()
    {
      sa ☃ = this.a.A();
      if ((☃ == null) || (!☃.au())) {
        return false;
      }
      return true;
    }
    
    public boolean b()
    {
      return (super.b()) && ((this.a.db()) || (this.a.h(this.a.A()) > 9.0D));
    }
    
    public void c()
    {
      this.b = -10;
      this.a.x().o();
      this.a.t().a(this.a.A(), 90.0F, 90.0F);
      
      this.a.ai = true;
    }
    
    public void d()
    {
      yo.a(this.a, 0);
      this.a.c(null);
      
      yo.a(this.a).f();
    }
    
    public void e()
    {
      sa ☃ = this.a.A();
      
      this.a.x().o();
      this.a.t().a(☃, 90.0F, 90.0F);
      if (!this.a.D(☃))
      {
        this.a.c(null);
        return;
      }
      this.b += 1;
      if (this.b == 0)
      {
        yo.a(this.a, this.a.A().O());
        this.a.l.a(this.a, (byte)21);
      }
      else if (this.b >= this.a.da())
      {
        float ☃ = 1.0F;
        if (this.a.l.ae() == qk.d) {
          ☃ += 2.0F;
        }
        if (this.a.db()) {
          ☃ += 2.0F;
        }
        ☃.a(rc.b(this.a, this.a), ☃);
        ☃.a(rc.a(this.a), (float)this.a.a(yt.e).e());
        this.a.c(null);
      }
      super.e();
    }
  }
  
  static class c
    extends sy
  {
    private yo i;
    
    public c(yo ☃)
    {
      super();
      this.i = ☃;
    }
    
    public void c()
    {
      if ((this.h != sy.a.b) || (this.i.x().n()))
      {
        this.i.l(0.0F);
        yo.a(this.i, false);
        return;
      }
      double ☃ = this.b - this.i.p;
      double ☃ = this.c - this.i.q;
      double ☃ = this.d - this.i.r;
      double ☃ = ☃ * ☃ + ☃ * ☃ + ☃ * ☃;
      ☃ = on.a(☃);
      ☃ /= ☃;
      
      float ☃ = (float)(on.b(☃, ☃) * 57.2957763671875D) - 90.0F;
      
      this.i.v = a(this.i.v, ☃, 90.0F);
      this.i.aM = this.i.v;
      
      float ☃ = (float)(this.e * this.i.a(yt.d).e());
      this.i.l(this.i.ck() + (☃ - this.i.ck()) * 0.125F);
      double ☃ = Math.sin((this.i.T + this.i.O()) * 0.5D) * 0.05D;
      double ☃ = Math.cos(this.i.v * 0.017453292F);
      double ☃ = Math.sin(this.i.v * 0.017453292F);
      this.i.s += ☃ * ☃;
      this.i.u += ☃ * ☃;
      
      ☃ = Math.sin((this.i.T + this.i.O()) * 0.75D) * 0.05D;
      this.i.t += ☃ * (☃ + ☃) * 0.25D;
      this.i.t += this.i.ck() * ☃ * 0.1D;
      
      sx ☃ = this.i.t();
      double ☃ = this.i.p + ☃ / ☃ * 2.0D;
      double ☃ = this.i.bn() + this.i.q + ☃ / ☃;
      double ☃ = this.i.r + ☃ / ☃ * 2.0D;
      double ☃ = ☃.e();
      double ☃ = ☃.f();
      double ☃ = ☃.g();
      if (!☃.b())
      {
        ☃ = ☃;
        ☃ = ☃;
        ☃ = ☃;
      }
      this.i.t().a(☃ + (☃ - ☃) * 0.125D, ☃ + (☃ - ☃) * 0.125D, ☃ + (☃ - ☃) * 0.125D, 10.0F, 40.0F);
      yo.a(this.i, true);
    }
  }
}
