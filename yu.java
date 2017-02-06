import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class yu
  extends vz
  implements yl
{
  private static final UUID bv = UUID.fromString("7E0292F2-9434-48D5-A29F-9583AF7DF27F");
  private static final sn bw = new sn(bv, "Covered armor bonus", 20.0D, 0).a(false);
  protected static final ke<cq> a = kh.a(yu.class, kg.l);
  protected static final ke<Optional<cj>> b = kh.a(yu.class, kg.k);
  protected static final ke<Byte> c = kh.a(yu.class, kg.a);
  private float bx;
  private float by;
  private cj bz;
  private int bA;
  
  public yu(aht ☃)
  {
    super(☃);
    
    a(1.0F, 1.0F);
    this.aN = 180.0F;
    this.aM = 180.0F;
    this.Y = true;
    
    this.bz = null;
    this.b_ = 5;
  }
  
  public sd a(ql ☃, sd ☃)
  {
    this.aM = 180.0F;
    this.aN = 180.0F;
    this.v = 180.0F;
    this.x = 180.0F;
    this.aO = 180.0F;
    this.aP = 180.0F;
    
    return super.a(☃, ☃);
  }
  
  protected void r()
  {
    this.bp.a(1, new tp(this, zj.class, 8.0F));
    this.bp.a(4, new yu.a());
    this.bp.a(7, new yu.e(null));
    this.bp.a(8, new uf(this));
    
    this.bq.a(1, new uv(this, true, new Class[0]));
    this.bq.a(2, new yu.d(this));
    this.bq.a(3, new yu.c(this));
  }
  
  protected boolean ae()
  {
    return false;
  }
  
  public nh bz()
  {
    return nh.f;
  }
  
  protected nf G()
  {
    return ng.eT;
  }
  
  public void D()
  {
    if (!df()) {
      super.D();
    }
  }
  
  protected nf bS()
  {
    return ng.eX;
  }
  
  protected nf bR()
  {
    if (df()) {
      return ng.eZ;
    }
    return ng.eY;
  }
  
  protected void i()
  {
    super.i();
    
    this.Z.a(a, cq.a);
    this.Z.a(b, Optional.absent());
    this.Z.a(c, Byte.valueOf((byte)0));
  }
  
  protected void bA()
  {
    super.bA();
    
    a(yt.a).a(30.0D);
  }
  
  protected su s()
  {
    return new yu.b(this);
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    
    this.Z.b(a, cq.a(☃.f("AttachFace")));
    this.Z.b(c, Byte.valueOf(☃.f("Peek")));
    if (☃.e("APX"))
    {
      int ☃ = ☃.h("APX");
      int ☃ = ☃.h("APY");
      int ☃ = ☃.h("APZ");
      this.Z.b(b, Optional.of(new cj(☃, ☃, ☃)));
    }
    else
    {
      this.Z.b(b, Optional.absent());
    }
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    
    ☃.a("AttachFace", (byte)((cq)this.Z.a(a)).a());
    ☃.a("Peek", ((Byte)this.Z.a(c)).byteValue());
    cj ☃ = da();
    if (☃ != null)
    {
      ☃.a("APX", ☃.p());
      ☃.a("APY", ☃.q());
      ☃.a("APZ", ☃.r());
    }
  }
  
  public void m()
  {
    super.m();
    
    cj ☃ = (cj)((Optional)this.Z.a(b)).orNull();
    if ((☃ == null) && (!this.l.E))
    {
      ☃ = new cj(this);
      this.Z.b(b, Optional.of(☃));
    }
    if (aI())
    {
      ☃ = null;
      float ☃ = by().v;
      this.v = ☃;
      this.aM = ☃;
      this.aN = ☃;
      this.bA = 0;
    }
    else if (!this.l.E)
    {
      arc ☃ = this.l.o(☃);
      if (☃.t() != aju.a) {
        if (☃.t() == aju.M)
        {
          cq ☃ = (cq)☃.c(aqu.H);
          ☃ = ☃.a(☃);
          this.Z.b(b, Optional.of(☃));
        }
        else if (☃.t() == aju.K)
        {
          cq ☃ = (cq)☃.c(aqv.H);
          ☃ = ☃.a(☃);
          this.Z.b(b, Optional.of(☃));
        }
        else
        {
          o();
        }
      }
      cj ☃ = ☃.a(cZ());
      if (!this.l.d(☃, false))
      {
        boolean ☃ = false;
        for (cq ☃ : cq.values())
        {
          ☃ = ☃.a(☃);
          if (this.l.d(☃, false))
          {
            this.Z.b(a, ☃);
            ☃ = true;
            break;
          }
        }
        if (!☃) {
          o();
        }
      }
      cj ☃ = ☃.a(cZ().d());
      if (this.l.d(☃, false)) {
        o();
      }
    }
    float ☃ = db() * 0.01F;
    
    this.bx = this.by;
    if (this.by > ☃) {
      this.by = on.a(this.by - 0.05F, ☃, 1.0F);
    } else if (this.by < ☃) {
      this.by = on.a(this.by + 0.05F, 0.0F, ☃);
    }
    double ☃;
    double ☃;
    double ☃;
    if (☃ != null)
    {
      if (this.l.E) {
        if ((this.bA > 0) && (this.bz != null)) {
          this.bA -= 1;
        } else {
          this.bz = ☃;
        }
      }
      this.M = (this.m = this.p = ☃.p() + 0.5D);
      this.N = (this.n = this.q = ☃.q());
      this.O = (this.o = this.r = ☃.r() + 0.5D);
      
      double ☃ = 0.5D - on.a((0.5F + this.by) * 3.1415927F) * 0.5D;
      double ☃ = 0.5D - on.a((0.5F + this.bx) * 3.1415927F) * 0.5D;
      double ☃ = ☃ - ☃;
      ☃ = 0.0D;
      ☃ = 0.0D;
      ☃ = 0.0D;
      
      cq ☃ = cZ();
      switch (yu.1.a[☃.ordinal()])
      {
      case 1: 
      default: 
        a(new bbh(this.p - 0.5D, this.q, this.r - 0.5D, this.p + 0.5D, this.q + 1.0D + ☃, this.r + 0.5D));
        ☃ = ☃;
        break;
      case 2: 
        a(new bbh(this.p - 0.5D, this.q - ☃, this.r - 0.5D, this.p + 0.5D, this.q + 1.0D, this.r + 0.5D));
        ☃ = -☃;
        break;
      case 3: 
        a(new bbh(this.p - 0.5D, this.q, this.r - 0.5D, this.p + 0.5D, this.q + 1.0D, this.r + 0.5D + ☃));
        ☃ = ☃;
        break;
      case 4: 
        a(new bbh(this.p - 0.5D, this.q, this.r - 0.5D - ☃, this.p + 0.5D, this.q + 1.0D, this.r + 0.5D));
        ☃ = -☃;
        break;
      case 5: 
        a(new bbh(this.p - 0.5D, this.q, this.r - 0.5D, this.p + 0.5D + ☃, this.q + 1.0D, this.r + 0.5D));
        ☃ = ☃;
        break;
      case 6: 
        a(new bbh(this.p - 0.5D - ☃, this.q, this.r - 0.5D, this.p + 0.5D, this.q + 1.0D, this.r + 0.5D));
        ☃ = -☃;
      }
      if (☃ > 0.0D)
      {
        List<rr> ☃ = this.l.b(this, bl());
        if (!☃.isEmpty()) {
          for (rr ☃ : ☃) {
            if ((!(☃ instanceof yu)) && (!☃.Q)) {
              ☃.d(☃, ☃, ☃);
            }
          }
        }
      }
    }
  }
  
  public void b(double ☃, double ☃, double ☃)
  {
    super.b(☃, ☃, ☃);
    if ((this.Z == null) || (this.T == 0)) {
      return;
    }
    Optional<cj> ☃ = (Optional)this.Z.a(b);
    Optional<cj> ☃ = Optional.of(new cj(☃, ☃, ☃));
    if (!☃.equals(☃))
    {
      this.Z.b(b, ☃);
      this.Z.b(c, Byte.valueOf((byte)0));
      this.ai = true;
    }
  }
  
  protected boolean o()
  {
    if ((cR()) || (!au())) {
      return true;
    }
    cj ☃ = new cj(this);
    for (int ☃ = 0; ☃ < 5; ☃++)
    {
      cj ☃ = ☃.a(8 - this.S.nextInt(17), 8 - this.S.nextInt(17), 8 - this.S.nextInt(17));
      if ((☃.q() > 0) && (this.l.d(☃)) && (this.l.a(this.l.aj(), this)) && (this.l.a(this, new bbh(☃)).isEmpty()))
      {
        boolean ☃ = false;
        for (cq ☃ : cq.values()) {
          if (this.l.d(☃.a(☃), false))
          {
            this.Z.b(a, ☃);
            ☃ = true;
            break;
          }
        }
        if (☃)
        {
          a(ng.fc, 1.0F, 1.0F);
          this.Z.b(b, Optional.of(☃));
          this.Z.b(c, Byte.valueOf((byte)0));
          c(null);
          return true;
        }
      }
    }
    return false;
  }
  
  public void n()
  {
    super.n();
    
    this.s = 0.0D;
    this.t = 0.0D;
    this.u = 0.0D;
    this.aN = 180.0F;
    this.aM = 180.0F;
    this.v = 180.0F;
  }
  
  public void a(ke<?> ☃)
  {
    if ((b.equals(☃)) && 
      (this.l.E) && (!aI()))
    {
      cj ☃ = da();
      if (☃ != null)
      {
        if (this.bz == null) {
          this.bz = ☃;
        } else {
          this.bA = 6;
        }
        this.M = (this.m = this.p = ☃.p() + 0.5D);
        this.N = (this.n = this.q = ☃.q());
        this.O = (this.o = this.r = ☃.r() + 0.5D);
      }
    }
    super.a(☃);
  }
  
  public void a(double ☃, double ☃, double ☃, float ☃, float ☃, int ☃, boolean ☃)
  {
    this.bg = 0;
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (df())
    {
      rr ☃ = ☃.i();
      if ((☃ instanceof zm)) {
        return false;
      }
    }
    if (super.a(☃, ☃))
    {
      if ((bQ() < bW() * 0.5D) && (this.S.nextInt(4) == 0)) {
        o();
      }
      return true;
    }
    return false;
  }
  
  private boolean df()
  {
    return db() == 0;
  }
  
  public bbh af()
  {
    return au() ? bl() : null;
  }
  
  public cq cZ()
  {
    return (cq)this.Z.a(a);
  }
  
  public cj da()
  {
    return (cj)((Optional)this.Z.a(b)).orNull();
  }
  
  public void g(cj ☃)
  {
    this.Z.b(b, Optional.fromNullable(☃));
  }
  
  public int db()
  {
    return ((Byte)this.Z.a(c)).byteValue();
  }
  
  public void a(int ☃)
  {
    if (!this.l.E)
    {
      a(yt.g).c(bw);
      if (☃ == 0)
      {
        a(yt.g).b(bw);
        a(ng.eW, 1.0F, 1.0F);
      }
      else
      {
        a(ng.fa, 1.0F, 1.0F);
      }
    }
    this.Z.b(c, Byte.valueOf((byte)☃));
  }
  
  public float a(float ☃)
  {
    return this.bx + (this.by - this.bx) * ☃;
  }
  
  public int dc()
  {
    return this.bA;
  }
  
  public cj dd()
  {
    return this.bz;
  }
  
  public float bn()
  {
    return 0.5F;
  }
  
  public int N()
  {
    return 180;
  }
  
  public int cE()
  {
    return 180;
  }
  
  public void i(rr ☃) {}
  
  public float aA()
  {
    return 0.0F;
  }
  
  public boolean de()
  {
    return (this.bz != null) && (da() != null);
  }
  
  class b
    extends su
  {
    public b(sa ☃)
    {
      super();
    }
    
    public void a() {}
  }
  
  class e
    extends tk
  {
    private int b;
    
    private e() {}
    
    public boolean a()
    {
      return (yu.this.A() == null) && (yu.a(yu.this).nextInt(40) == 0);
    }
    
    public boolean b()
    {
      return (yu.this.A() == null) && (this.b > 0);
    }
    
    public void c()
    {
      this.b = (20 * (1 + yu.b(yu.this).nextInt(3)));
      yu.this.a(30);
    }
    
    public void d()
    {
      if (yu.this.A() == null) {
        yu.this.a(0);
      }
    }
    
    public void e()
    {
      this.b -= 1;
    }
  }
  
  class a
    extends tk
  {
    private int b;
    
    public a()
    {
      a(3);
    }
    
    public boolean a()
    {
      sa ☃ = yu.this.A();
      if ((☃ == null) || (!☃.au())) {
        return false;
      }
      if (yu.this.l.ae() == qk.a) {
        return false;
      }
      return true;
    }
    
    public void c()
    {
      this.b = 20;
      yu.this.a(100);
    }
    
    public void d()
    {
      yu.this.a(0);
    }
    
    public void e()
    {
      if (yu.this.l.ae() == qk.a) {
        return;
      }
      this.b -= 1;
      
      sa ☃ = yu.this.A();
      yu.this.t().a(☃, 180.0F, 180.0F);
      
      double ☃ = yu.this.h(☃);
      if (☃ < 400.0D)
      {
        if (this.b <= 0)
        {
          this.b = (20 + yu.c(yu.this).nextInt(10) * 20 / 2);
          
          zu ☃ = new zu(yu.this.l, yu.this, ☃, yu.this.cZ().k());
          yu.this.l.a(☃);
          yu.this.a(ng.fb, 2.0F, (yu.d(yu.this).nextFloat() - yu.e(yu.this).nextFloat()) * 0.2F + 1.0F);
        }
      }
      else {
        yu.this.c(null);
      }
      super.e();
    }
  }
  
  class d
    extends uy<zj>
  {
    public d(yu ☃)
    {
      super(zj.class, true);
    }
    
    public boolean a()
    {
      if (yu.this.l.ae() == qk.a) {
        return false;
      }
      return super.a();
    }
    
    protected bbh a(double ☃)
    {
      cq ☃ = ((yu)this.e).cZ();
      if (☃.k() == cq.a.a) {
        return this.e.bl().b(4.0D, ☃, ☃);
      }
      if (☃.k() == cq.a.c) {
        return this.e.bl().b(☃, ☃, 4.0D);
      }
      return this.e.bl().b(☃, 4.0D, ☃);
    }
  }
  
  static class c
    extends uy<sa>
  {
    public c(yu ☃)
    {
      super(sa.class, 10, true, false, new Predicate()
      {
        public boolean a(sa ☃)
        {
          return ☃ instanceof yl;
        }
      });
    }
    
    public boolean a()
    {
      if (this.e.aO() == null) {
        return false;
      }
      return super.a();
    }
    
    protected bbh a(double ☃)
    {
      cq ☃ = ((yu)this.e).cZ();
      if (☃.k() == cq.a.a) {
        return this.e.bl().b(4.0D, ☃, ☃);
      }
      if (☃.k() == cq.a.c) {
        return this.e.bl().b(☃, ☃, 4.0D);
      }
      return this.e.bl().b(☃, 4.0D, ☃);
    }
  }
  
  protected kk J()
  {
    return azt.x;
  }
}
