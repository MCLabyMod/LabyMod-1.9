import com.google.common.base.Predicate;
import java.util.Arrays;
import java.util.List;

public class xq
  extends sa
{
  private static final dc bp = new dc(0.0F, 0.0F, 0.0F);
  private static final dc bq = new dc(0.0F, 0.0F, 0.0F);
  private static final dc br = new dc(-10.0F, 0.0F, -10.0F);
  private static final dc bs = new dc(-15.0F, 0.0F, 10.0F);
  private static final dc bt = new dc(-1.0F, 0.0F, -1.0F);
  private static final dc bu = new dc(1.0F, 0.0F, 1.0F);
  public static final ke<Byte> a = kh.a(xq.class, kg.a);
  public static final ke<dc> b = kh.a(xq.class, kg.i);
  public static final ke<dc> c = kh.a(xq.class, kg.i);
  public static final ke<dc> d = kh.a(xq.class, kg.i);
  public static final ke<dc> e = kh.a(xq.class, kg.i);
  public static final ke<dc> f = kh.a(xq.class, kg.i);
  public static final ke<dc> g = kh.a(xq.class, kg.i);
  private static final Predicate<rr> bv = new Predicate()
  {
    public boolean a(rr ☃)
    {
      return ((☃ instanceof aah)) && (((aah)☃).v() == aah.a.a);
    }
  };
  private final adq[] bw = new adq[2];
  private final adq[] bx = new adq[4];
  private boolean by;
  public long h;
  private int bz;
  private boolean bA;
  private dc bB = bp;
  private dc bC = bq;
  private dc bD = br;
  private dc bE = bs;
  private dc bF = bt;
  private dc bG = bu;
  
  public xq(aht ☃)
  {
    super(☃);
    this.Q = r();
    a(0.5F, 1.975F);
  }
  
  public xq(aht ☃, double ☃, double ☃, double ☃)
  {
    this(☃);
    b(☃, ☃, ☃);
  }
  
  public boolean co()
  {
    return (super.co()) && (!r());
  }
  
  protected void i()
  {
    super.i();
    this.Z.a(a, Byte.valueOf((byte)0));
    this.Z.a(b, bp);
    this.Z.a(c, bq);
    this.Z.a(d, br);
    this.Z.a(e, bs);
    this.Z.a(f, bt);
    this.Z.a(g, bu);
  }
  
  public Iterable<adq> aE()
  {
    return Arrays.asList(this.bw);
  }
  
  public Iterable<adq> aF()
  {
    return Arrays.asList(this.bx);
  }
  
  public adq a(rw ☃)
  {
    adq ☃ = null;
    switch (xq.2.a[☃.a().ordinal()])
    {
    case 1: 
      ☃ = this.bw[☃.b()];
      break;
    case 2: 
      ☃ = this.bx[☃.b()];
    }
    return ☃;
  }
  
  public void a(rw ☃, adq ☃)
  {
    switch (xq.2.a[☃.a().ordinal()])
    {
    case 1: 
      a_(☃);
      this.bw[☃.b()] = ☃;
      break;
    case 2: 
      a_(☃);
      this.bx[☃.b()] = ☃;
    }
  }
  
  public boolean c(int ☃, adq ☃)
  {
    rw ☃;
    if (☃ == 98)
    {
      ☃ = rw.a;
    }
    else
    {
      rw ☃;
      if (☃ == 99)
      {
        ☃ = rw.b;
      }
      else
      {
        rw ☃;
        if (☃ == 100 + rw.f.b())
        {
          ☃ = rw.f;
        }
        else
        {
          rw ☃;
          if (☃ == 100 + rw.e.b())
          {
            ☃ = rw.e;
          }
          else
          {
            rw ☃;
            if (☃ == 100 + rw.d.b())
            {
              ☃ = rw.d;
            }
            else
            {
              rw ☃;
              if (☃ == 100 + rw.c.b()) {
                ☃ = rw.c;
              } else {
                return false;
              }
            }
          }
        }
      }
    }
    rw ☃;
    if ((☃ == null) || (sb.b(☃, ☃)) || (☃ == rw.f))
    {
      a(☃, ☃);
      return true;
    }
    return false;
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    
    du ☃ = new du();
    for (int ☃ = 0; ☃ < this.bx.length; ☃++)
    {
      dn ☃ = new dn();
      if (this.bx[☃] != null) {
        this.bx[☃].b(☃);
      }
      ☃.a(☃);
    }
    ☃.a("ArmorItems", ☃);
    
    du ☃ = new du();
    for (int ☃ = 0; ☃ < this.bw.length; ☃++)
    {
      dn ☃ = new dn();
      if (this.bw[☃] != null) {
        this.bw[☃].b(☃);
      }
      ☃.a(☃);
    }
    ☃.a("HandItems", ☃);
    if ((bg()) && ((bf() == null) || (bf().isEmpty()))) {
      ☃.a("CustomNameVisible", bg());
    }
    ☃.a("Invisible", aN());
    ☃.a("Small", o());
    
    ☃.a("ShowArms", s());
    
    ☃.a("DisabledSlots", this.bz);
    ☃.a("NoGravity", r());
    ☃.a("NoBasePlate", t());
    if (u()) {
      ☃.a("Marker", u());
    }
    ☃.a("Pose", D());
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    if (☃.b("ArmorItems", 9))
    {
      du ☃ = ☃.c("ArmorItems", 10);
      for (int ☃ = 0; ☃ < this.bx.length; ☃++) {
        this.bx[☃] = adq.a(☃.b(☃));
      }
    }
    if (☃.b("HandItems", 9))
    {
      du ☃ = ☃.c("HandItems", 10);
      for (int ☃ = 0; ☃ < this.bw.length; ☃++) {
        this.bw[☃] = adq.a(☃.b(☃));
      }
    }
    g(☃.p("Invisible"));
    
    l(☃.p("Small"));
    
    n(☃.p("ShowArms"));
    
    this.bz = ☃.h("DisabledSlots");
    m(☃.p("NoGravity"));
    o(☃.p("NoBasePlate"));
    p(☃.p("Marker"));
    this.bA = (!u());
    this.Q = r();
    dn ☃ = ☃.o("Pose");
    g(☃);
  }
  
  private void g(dn ☃)
  {
    du ☃ = ☃.c("Head", 5);
    a(☃.c_() ? bp : new dc(☃));
    
    du ☃ = ☃.c("Body", 5);
    b(☃.c_() ? bq : new dc(☃));
    
    du ☃ = ☃.c("LeftArm", 5);
    c(☃.c_() ? br : new dc(☃));
    
    du ☃ = ☃.c("RightArm", 5);
    d(☃.c_() ? bs : new dc(☃));
    
    du ☃ = ☃.c("LeftLeg", 5);
    e(☃.c_() ? bt : new dc(☃));
    
    du ☃ = ☃.c("RightLeg", 5);
    f(☃.c_() ? bu : new dc(☃));
  }
  
  private dn D()
  {
    dn ☃ = new dn();
    if (!bp.equals(this.bB)) {
      ☃.a("Head", this.bB.a());
    }
    if (!bq.equals(this.bC)) {
      ☃.a("Body", this.bC.a());
    }
    if (!br.equals(this.bD)) {
      ☃.a("LeftArm", this.bD.a());
    }
    if (!bs.equals(this.bE)) {
      ☃.a("RightArm", this.bE.a());
    }
    if (!bt.equals(this.bF)) {
      ☃.a("LeftLeg", this.bF.a());
    }
    if (!bu.equals(this.bG)) {
      ☃.a("RightLeg", this.bG.a());
    }
    return ☃;
  }
  
  public boolean aq()
  {
    return false;
  }
  
  protected void C(rr ☃) {}
  
  protected void cn()
  {
    List<rr> ☃ = this.l.a(this, bl(), bv);
    for (int ☃ = 0; ☃ < ☃.size(); ☃++)
    {
      rr ☃ = (rr)☃.get(☃);
      if (h(☃) <= 0.2D) {
        ☃.i(this);
      }
    }
  }
  
  public qo a(zj ☃, bbj ☃, adq ☃, qm ☃)
  {
    if (u()) {
      return qo.b;
    }
    if ((this.l.E) || (☃.y())) {
      return qo.a;
    }
    rw ☃ = rw.a;
    boolean ☃ = ☃ != null;
    ado ☃ = ☃ ? ☃.b() : null;
    if ((☃) && ((☃ instanceof abw))) {
      ☃ = ((abw)☃).c;
    }
    if ((☃) && ((☃ == ads.ch) || (☃ == ado.a(aju.aU)))) {
      ☃ = rw.f;
    }
    double ☃ = 0.1D;
    double ☃ = 0.9D;
    double ☃ = 0.4D;
    double ☃ = 1.6D;
    
    rw ☃ = rw.a;
    boolean ☃ = o();
    double ☃ = ☃ ? ☃.c * 2.0D : ☃.c;
    if (☃ >= 0.1D) {
      if ((☃ < 0.1D + (☃ ? 0.8D : 0.45D)) && (a(rw.c) != null))
      {
        ☃ = rw.c;
        break label354;
      }
    }
    if (☃ >= 0.9D + (☃ ? 0.3D : 0.0D)) {
      if ((☃ < 0.9D + (☃ ? 1.0D : 0.7D)) && (a(rw.e) != null))
      {
        ☃ = rw.e;
        break label354;
      }
    }
    if (☃ >= 0.4D) {
      if ((☃ < 0.4D + (☃ ? 1.0D : 0.8D)) && (a(rw.d) != null))
      {
        ☃ = rw.d;
        break label354;
      }
    }
    if ((☃ >= 1.6D) && (a(rw.f) != null)) {
      ☃ = rw.f;
    }
    label354:
    boolean ☃ = a(☃) != null;
    if ((b(☃)) || (b(☃)))
    {
      ☃ = ☃;
      if (b(☃)) {
        return qo.c;
      }
    }
    if ((☃) && (☃ == rw.a) && (!s())) {
      return qo.c;
    }
    if (☃) {
      a(☃, ☃, ☃, ☃);
    } else if (☃) {
      a(☃, ☃, ☃, ☃);
    }
    return qo.a;
  }
  
  private boolean b(rw ☃)
  {
    return (this.bz & 1 << ☃.c()) != 0;
  }
  
  private void a(zj ☃, rw ☃, adq ☃, qm ☃)
  {
    adq ☃ = a(☃);
    if ((☃ != null) && ((this.bz & 1 << ☃.c() + 8) != 0)) {
      return;
    }
    if ((☃ == null) && ((this.bz & 1 << ☃.c() + 16) != 0)) {
      return;
    }
    if ((☃.bJ.d) && ((☃ == null) || (☃.b() == ado.a(aju.a))) && (☃ != null))
    {
      adq ☃ = ☃.k();
      ☃.b = 1;
      a(☃, ☃);
      return;
    }
    if ((☃ != null) && (☃.b > 1))
    {
      if (☃ != null) {
        return;
      }
      adq ☃ = ☃.k();
      ☃.b = 1;
      a(☃, ☃);
      ☃.b -= 1;
      return;
    }
    a(☃, ☃);
    ☃.a(☃, ☃);
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if ((this.l.E) || (this.F)) {
      return false;
    }
    if (rc.k.equals(☃))
    {
      T();
      return false;
    }
    if ((b(☃)) || (this.by) || (u())) {
      return false;
    }
    if (☃.c())
    {
      I();
      T();
      return false;
    }
    if (rc.a.equals(☃))
    {
      if (aH()) {
        a(0.15F);
      } else {
        g(5);
      }
      return false;
    }
    if ((rc.c.equals(☃)) && (bQ() > 0.5F))
    {
      a(4.0F);
      return false;
    }
    boolean ☃ = "arrow".equals(☃.p());
    boolean ☃ = "player".equals(☃.p());
    if ((!☃) && (!☃)) {
      return false;
    }
    if ((☃.i() instanceof zm)) {
      ☃.i().T();
    }
    if (((☃.j() instanceof zj)) && (!((zj)☃.j()).bJ.e)) {
      return false;
    }
    if (☃.u())
    {
      E();
      T();
      return false;
    }
    long ☃ = this.l.P();
    if ((☃ - this.h <= 5L) || (☃))
    {
      G();
      E();
      T();
    }
    else
    {
      this.l.a(this, (byte)32);
      this.h = ☃;
    }
    return false;
  }
  
  public void a(byte ☃)
  {
    if (☃ == 32)
    {
      if (this.l.E)
      {
        this.l.a(this.p, this.q, this.r, ng.l, bz(), 0.3F, 1.0F, false);
        this.h = this.l.P();
      }
    }
    else {
      super.a(☃);
    }
  }
  
  public boolean a(double ☃)
  {
    double ☃ = bl().a() * 4.0D;
    if ((Double.isNaN(☃)) || (☃ == 0.0D)) {
      ☃ = 4.0D;
    }
    ☃ *= 64.0D;
    return ☃ < ☃ * ☃;
  }
  
  private void E()
  {
    if ((this.l instanceof lp)) {
      ((lp)this.l).a(cy.M, this.p, this.q + this.H / 1.5D, this.r, 10, this.G / 4.0F, this.H / 4.0F, this.G / 4.0F, 0.05D, new int[] { ajt.j(aju.f.u()) });
    }
  }
  
  private void a(float ☃)
  {
    float ☃ = bQ();
    ☃ -= ☃;
    if (☃ <= 0.5F)
    {
      I();
      T();
    }
    else
    {
      c(☃);
    }
  }
  
  private void G()
  {
    ajt.a(this.l, new cj(this), new adq(ads.ct));
    I();
  }
  
  private void I()
  {
    this.l.a(null, this.p, this.q, this.r, ng.j, bz(), 1.0F, 1.0F);
    for (int ☃ = 0; ☃ < this.bw.length; ☃++) {
      if ((this.bw[☃] != null) && (this.bw[☃].b > 0))
      {
        if (this.bw[☃] != null) {
          ajt.a(this.l, new cj(this).a(), this.bw[☃]);
        }
        this.bw[☃] = null;
      }
    }
    for (int ☃ = 0; ☃ < this.bx.length; ☃++) {
      if ((this.bx[☃] != null) && (this.bx[☃].b > 0))
      {
        if (this.bx[☃] != null) {
          ajt.a(this.l, new cj(this).a(), this.bx[☃]);
        }
        this.bx[☃] = null;
      }
    }
  }
  
  protected float h(float ☃, float ☃)
  {
    this.aN = this.x;
    this.aM = this.v;
    return 0.0F;
  }
  
  public float bn()
  {
    return m_() ? this.H * 0.5F : this.H * 0.9F;
  }
  
  public double ax()
  {
    return u() ? 0.0D : 0.10000000149011612D;
  }
  
  public void g(float ☃, float ☃)
  {
    if (r()) {
      return;
    }
    super.g(☃, ☃);
  }
  
  public void m()
  {
    super.m();
    
    dc ☃ = (dc)this.Z.a(b);
    if (!this.bB.equals(☃)) {
      a(☃);
    }
    dc ☃ = (dc)this.Z.a(c);
    if (!this.bC.equals(☃)) {
      b(☃);
    }
    dc ☃ = (dc)this.Z.a(d);
    if (!this.bD.equals(☃)) {
      c(☃);
    }
    dc ☃ = (dc)this.Z.a(e);
    if (!this.bE.equals(☃)) {
      d(☃);
    }
    dc ☃ = (dc)this.Z.a(f);
    if (!this.bF.equals(☃)) {
      e(☃);
    }
    dc ☃ = (dc)this.Z.a(g);
    if (!this.bG.equals(☃)) {
      f(☃);
    }
    boolean ☃ = u();
    if ((!this.bA) && (☃))
    {
      a(false);
      this.i = false;
    }
    else if ((this.bA) && (!☃))
    {
      a(true);
      this.i = true;
    }
    else
    {
      return;
    }
    this.bA = ☃;
  }
  
  private void a(boolean ☃)
  {
    double ☃ = this.p;
    double ☃ = this.q;
    double ☃ = this.r;
    if (☃) {
      a(0.5F, 1.975F);
    } else {
      a(0.0F, 0.0F);
    }
    b(☃, ☃, ☃);
  }
  
  protected void F()
  {
    g(this.by);
  }
  
  public void g(boolean ☃)
  {
    this.by = ☃;
    super.g(☃);
  }
  
  public boolean m_()
  {
    return o();
  }
  
  public void Q()
  {
    T();
  }
  
  public boolean bq()
  {
    return aN();
  }
  
  private void l(boolean ☃)
  {
    this.Z.b(a, Byte.valueOf(a(((Byte)this.Z.a(a)).byteValue(), 1, ☃)));
  }
  
  public boolean o()
  {
    return (((Byte)this.Z.a(a)).byteValue() & 0x1) != 0;
  }
  
  private void m(boolean ☃)
  {
    this.Z.b(a, Byte.valueOf(a(((Byte)this.Z.a(a)).byteValue(), 2, ☃)));
  }
  
  public boolean r()
  {
    return (((Byte)this.Z.a(a)).byteValue() & 0x2) != 0;
  }
  
  private void n(boolean ☃)
  {
    this.Z.b(a, Byte.valueOf(a(((Byte)this.Z.a(a)).byteValue(), 4, ☃)));
  }
  
  public boolean s()
  {
    return (((Byte)this.Z.a(a)).byteValue() & 0x4) != 0;
  }
  
  private void o(boolean ☃)
  {
    this.Z.b(a, Byte.valueOf(a(((Byte)this.Z.a(a)).byteValue(), 8, ☃)));
  }
  
  public boolean t()
  {
    return (((Byte)this.Z.a(a)).byteValue() & 0x8) != 0;
  }
  
  private void p(boolean ☃)
  {
    this.Z.b(a, Byte.valueOf(a(((Byte)this.Z.a(a)).byteValue(), 16, ☃)));
  }
  
  public boolean u()
  {
    return (((Byte)this.Z.a(a)).byteValue() & 0x10) != 0;
  }
  
  private byte a(byte ☃, int ☃, boolean ☃)
  {
    if (☃) {
      ☃ = (byte)(☃ | ☃);
    } else {
      ☃ = (byte)(☃ & (☃ ^ 0xFFFFFFFF));
    }
    return ☃;
  }
  
  public void a(dc ☃)
  {
    this.bB = ☃;
    this.Z.b(b, ☃);
  }
  
  public void b(dc ☃)
  {
    this.bC = ☃;
    this.Z.b(c, ☃);
  }
  
  public void c(dc ☃)
  {
    this.bD = ☃;
    this.Z.b(d, ☃);
  }
  
  public void d(dc ☃)
  {
    this.bE = ☃;
    this.Z.b(e, ☃);
  }
  
  public void e(dc ☃)
  {
    this.bF = ☃;
    this.Z.b(f, ☃);
  }
  
  public void f(dc ☃)
  {
    this.bG = ☃;
    this.Z.b(g, ☃);
  }
  
  public dc w()
  {
    return this.bB;
  }
  
  public dc x()
  {
    return this.bC;
  }
  
  public dc y()
  {
    return this.bD;
  }
  
  public dc A()
  {
    return this.bE;
  }
  
  public dc B()
  {
    return this.bF;
  }
  
  public dc C()
  {
    return this.bG;
  }
  
  public boolean ap()
  {
    return (super.ap()) && (!u());
  }
  
  public rz cr()
  {
    return rz.b;
  }
  
  protected nf e(int ☃)
  {
    return ng.k;
  }
  
  protected nf bR()
  {
    return ng.l;
  }
  
  protected nf bS()
  {
    return ng.j;
  }
  
  public void a(ya ☃) {}
  
  public boolean cD()
  {
    return false;
  }
}
