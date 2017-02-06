import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.List;
import java.util.Random;

public class xo
  extends yq
  implements ys
{
  private static final ke<Integer> a = kh.a(xo.class, kg.b);
  private static final ke<Integer> b = kh.a(xo.class, kg.b);
  private static final ke<Integer> c = kh.a(xo.class, kg.b);
  private static final ke<Integer>[] bv = { a, b, c };
  private static final ke<Integer> bw = kh.a(xo.class, kg.b);
  private float[] bx = new float[2];
  private float[] by = new float[2];
  private float[] bz = new float[2];
  private float[] bA = new float[2];
  private int[] bB = new int[2];
  private int[] bC = new int[2];
  private int bD;
  private final ln bE = (ln)new ln(i_(), qe.a.f, qe.b.a).a(true);
  private static final Predicate<rr> bF = new Predicate()
  {
    public boolean a(rr ☃)
    {
      return ((☃ instanceof sa)) && (((sa)☃).ca() != sf.b);
    }
  };
  
  public xo(aht ☃)
  {
    super(☃);
    
    c(bW());
    
    a(0.9F, 3.5F);
    
    this.Y = true;
    
    ((ve)x()).c(true);
    
    this.b_ = 50;
  }
  
  protected void r()
  {
    this.bp.a(0, new xo.a());
    this.bp.a(1, new th(this));
    this.bp.a(2, new uh(this, 1.0D, 40, 20.0F));
    
    this.bp.a(5, new ug(this, 1.0D));
    this.bp.a(6, new tp(this, zj.class, 8.0F));
    this.bp.a(7, new uf(this));
    
    this.bq.a(1, new uv(this, false, new Class[0]));
    this.bq.a(2, new uy(this, sb.class, 0, false, false, bF));
  }
  
  protected void i()
  {
    super.i();
    
    this.Z.a(a, Integer.valueOf(0));
    this.Z.a(b, Integer.valueOf(0));
    this.Z.a(c, Integer.valueOf(0));
    this.Z.a(bw, Integer.valueOf(0));
  }
  
  public void b(dn ☃)
  {
    super.b(☃);
    
    ☃.a("Invul", cZ());
  }
  
  public void a(dn ☃)
  {
    super.a(☃);
    
    l(☃.h("Invul"));
  }
  
  protected nf G()
  {
    return ng.gE;
  }
  
  protected nf bR()
  {
    return ng.gH;
  }
  
  protected nf bS()
  {
    return ng.gG;
  }
  
  public void n()
  {
    this.t *= 0.6000000238418579D;
    if ((!this.l.E) && (m(0) > 0))
    {
      rr ☃ = this.l.a(m(0));
      if (☃ != null)
      {
        if ((this.q < ☃.q) || ((!da()) && (this.q < ☃.q + 5.0D)))
        {
          if (this.t < 0.0D) {
            this.t = 0.0D;
          }
          this.t += (0.5D - this.t) * 0.6000000238418579D;
        }
        double ☃ = ☃.p - this.p;
        double ☃ = ☃.r - this.r;
        double ☃ = ☃ * ☃ + ☃ * ☃;
        if (☃ > 9.0D)
        {
          double ☃ = on.a(☃);
          this.s += (☃ / ☃ * 0.5D - this.s) * 0.6000000238418579D;
          this.u += (☃ / ☃ * 0.5D - this.u) * 0.6000000238418579D;
        }
      }
    }
    if (this.s * this.s + this.u * this.u > 0.05000000074505806D) {
      this.v = ((float)on.b(this.u, this.s) * 57.295776F - 90.0F);
    }
    super.n();
    for (int ☃ = 0; ☃ < 2; ☃++)
    {
      this.bA[☃] = this.by[☃];
      this.bz[☃] = this.bx[☃];
    }
    for (int ☃ = 0; ☃ < 2; ☃++)
    {
      int ☃ = m(☃ + 1);
      rr ☃ = null;
      if (☃ > 0) {
        ☃ = this.l.a(☃);
      }
      if (☃ != null)
      {
        double ☃ = n(☃ + 1);
        double ☃ = o(☃ + 1);
        double ☃ = p(☃ + 1);
        
        double ☃ = ☃.p - ☃;
        double ☃ = ☃.q + ☃.bn() - ☃;
        double ☃ = ☃.r - ☃;
        double ☃ = on.a(☃ * ☃ + ☃ * ☃);
        
        float ☃ = (float)(on.b(☃, ☃) * 57.2957763671875D) - 90.0F;
        float ☃ = (float)-(on.b(☃, ☃) * 57.2957763671875D);
        this.bx[☃] = b(this.bx[☃], ☃, 40.0F);
        this.by[☃] = b(this.by[☃], ☃, 10.0F);
      }
      else
      {
        this.by[☃] = b(this.by[☃], this.aM, 10.0F);
      }
    }
    boolean ☃ = da();
    for (int ☃ = 0; ☃ < 3; ☃++)
    {
      double ☃ = n(☃);
      double ☃ = o(☃);
      double ☃ = p(☃);
      
      this.l.a(cy.l, ☃ + this.S.nextGaussian() * 0.30000001192092896D, ☃ + this.S.nextGaussian() * 0.30000001192092896D, ☃ + this.S.nextGaussian() * 0.30000001192092896D, 0.0D, 0.0D, 0.0D, new int[0]);
      if ((☃) && (this.l.r.nextInt(4) == 0)) {
        this.l.a(cy.p, ☃ + this.S.nextGaussian() * 0.30000001192092896D, ☃ + this.S.nextGaussian() * 0.30000001192092896D, ☃ + this.S.nextGaussian() * 0.30000001192092896D, 0.699999988079071D, 0.699999988079071D, 0.5D, new int[0]);
      }
    }
    if (cZ() > 0) {
      for (int ☃ = 0; ☃ < 3; ☃++) {
        this.l.a(cy.p, this.p + this.S.nextGaussian(), this.q + this.S.nextFloat() * 3.3F, this.r + this.S.nextGaussian(), 0.699999988079071D, 0.699999988079071D, 0.8999999761581421D, new int[0]);
      }
    }
  }
  
  protected void M()
  {
    if (cZ() > 0)
    {
      int ☃ = cZ() - 1;
      if (☃ <= 0)
      {
        this.l.a(this, this.p, this.q + bn(), this.r, 7.0F, false, this.l.U().b("mobGriefing"));
        this.l.a(1023, new cj(this), 0);
      }
      l(☃);
      if (this.T % 10 == 0) {
        b(10.0F);
      }
      return;
    }
    super.M();
    for (int ☃ = 1; ☃ < 3; ☃++) {
      if (this.T >= this.bB[(☃ - 1)])
      {
        this.bB[(☃ - 1)] = (this.T + 10 + this.S.nextInt(10));
        if ((this.l.ae() == qk.c) || (this.l.ae() == qk.d))
        {
          int tmp188_187 = (☃ - 1); int[] tmp188_182 = this.bC; int tmp190_189 = tmp188_182[tmp188_187];tmp188_182[tmp188_187] = (tmp190_189 + 1);
          if (tmp190_189 > 15)
          {
            float ☃ = 10.0F;
            float ☃ = 5.0F;
            double ☃ = on.a(this.S, this.p - ☃, this.p + ☃);
            double ☃ = on.a(this.S, this.q - ☃, this.q + ☃);
            double ☃ = on.a(this.S, this.r - ☃, this.r + ☃);
            a(☃ + 1, ☃, ☃, ☃, true);
            this.bC[(☃ - 1)] = 0;
          }
        }
        int ☃ = m(☃);
        if (☃ > 0)
        {
          rr ☃ = this.l.a(☃);
          if ((☃ == null) || (!☃.au()) || (h(☃) > 900.0D) || (!D(☃)))
          {
            a(☃, 0);
          }
          else if (((☃ instanceof zj)) && (((zj)☃).bJ.a))
          {
            a(☃, 0);
          }
          else
          {
            a(☃ + 1, (sa)☃);
            this.bB[(☃ - 1)] = (this.T + 40 + this.S.nextInt(20));
            this.bC[(☃ - 1)] = 0;
          }
        }
        else
        {
          List<sa> ☃ = this.l.a(sa.class, bl().b(20.0D, 8.0D, 20.0D), Predicates.and(bF, rv.e));
          for (int ☃ = 0; (☃ < 10) && (!☃.isEmpty()); ☃++)
          {
            sa ☃ = (sa)☃.get(this.S.nextInt(☃.size()));
            if ((☃ != this) && (☃.au()) && (D(☃)))
            {
              if ((☃ instanceof zj))
              {
                if (((zj)☃).bJ.a) {
                  break;
                }
                a(☃, ☃.O()); break;
              }
              a(☃, ☃.O());
              break;
            }
            ☃.remove(☃);
          }
        }
      }
    }
    if (A() != null) {
      a(0, A().O());
    } else {
      a(0, 0);
    }
    if (this.bD > 0)
    {
      this.bD -= 1;
      if ((this.bD == 0) && (this.l.U().b("mobGriefing")))
      {
        int ☃ = on.c(this.q);
        int ☃ = on.c(this.p);
        int ☃ = on.c(this.r);
        boolean ☃ = false;
        for (int ☃ = -1; ☃ <= 1; ☃++) {
          for (int ☃ = -1; ☃ <= 1; ☃++) {
            for (int ☃ = 0; ☃ <= 3; ☃++)
            {
              int ☃ = ☃ + ☃;
              int ☃ = ☃ + ☃;
              int ☃ = ☃ + ☃;
              cj ☃ = new cj(☃, ☃, ☃);
              arc ☃ = this.l.o(☃);
              ajt ☃ = ☃.t();
              if ((☃.a() != axe.a) && (a(☃))) {
                ☃ = (this.l.b(☃, true)) || (☃);
              }
            }
          }
        }
        if (☃) {
          this.l.a(null, 1022, new cj(this), 0);
        }
      }
    }
    if (this.T % 20 == 0) {
      b(1.0F);
    }
    this.bE.a(bQ() / bW());
  }
  
  public static boolean a(ajt ☃)
  {
    return (☃ != aju.h) && (☃ != aju.bF) && (☃ != aju.bG) && (☃ != aju.bX) && (☃ != aju.dc) && (☃ != aju.dd) && (☃ != aju.cv);
  }
  
  public void o()
  {
    l(220);
    c(bW() / 3.0F);
  }
  
  public void aQ() {}
  
  public void b(lr ☃)
  {
    super.b(☃);
    this.bE.a(☃);
  }
  
  public void c(lr ☃)
  {
    super.c(☃);
    this.bE.b(☃);
  }
  
  private double n(int ☃)
  {
    if (☃ <= 0) {
      return this.p;
    }
    float ☃ = (this.aM + 180 * (☃ - 1)) * 0.017453292F;
    float ☃ = on.b(☃);
    return this.p + ☃ * 1.3D;
  }
  
  private double o(int ☃)
  {
    if (☃ <= 0) {
      return this.q + 3.0D;
    }
    return this.q + 2.2D;
  }
  
  private double p(int ☃)
  {
    if (☃ <= 0) {
      return this.r;
    }
    float ☃ = (this.aM + 180 * (☃ - 1)) * 0.017453292F;
    float ☃ = on.a(☃);
    return this.r + ☃ * 1.3D;
  }
  
  private float b(float ☃, float ☃, float ☃)
  {
    float ☃ = on.g(☃ - ☃);
    if (☃ > ☃) {
      ☃ = ☃;
    }
    if (☃ < -☃) {
      ☃ = -☃;
    }
    return ☃ + ☃;
  }
  
  private void a(int ☃, sa ☃)
  {
    a(☃, ☃.p, ☃.q + ☃.bn() * 0.5D, ☃.r, (☃ == 0) && (this.S.nextFloat() < 0.001F));
  }
  
  private void a(int ☃, double ☃, double ☃, double ☃, boolean ☃)
  {
    this.l.a(null, 1024, new cj(this), 0);
    
    double ☃ = n(☃);
    double ☃ = o(☃);
    double ☃ = p(☃);
    
    double ☃ = ☃ - ☃;
    double ☃ = ☃ - ☃;
    double ☃ = ☃ - ☃;
    
    aae ☃ = new aae(this.l, this, ☃, ☃, ☃);
    if (☃) {
      ☃.a(true);
    }
    ☃.q = ☃;
    ☃.p = ☃;
    ☃.r = ☃;
    this.l.a(☃);
  }
  
  public void a(sa ☃, float ☃)
  {
    a(0, ☃);
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (b(☃)) {
      return false;
    }
    if ((☃ == rc.f) || ((☃.j() instanceof xo))) {
      return false;
    }
    if ((cZ() > 0) && (☃ != rc.k)) {
      return false;
    }
    if (da())
    {
      rr ☃ = ☃.i();
      if ((☃ instanceof zm)) {
        return false;
      }
    }
    rr ☃ = ☃.j();
    if ((☃ != null) && 
      (!(☃ instanceof zj)) && 
      ((☃ instanceof sa)) && (((sa)☃).ca() == ca())) {
      return false;
    }
    if (this.bD <= 0) {
      this.bD = 20;
    }
    for (int ☃ = 0; ☃ < this.bC.length; ☃++) {
      this.bC[☃] += 3;
    }
    return super.a(☃, ☃);
  }
  
  protected void b(boolean ☃, int ☃)
  {
    yd ☃ = a(ads.cj, 1);
    if (☃ != null) {
      ☃.v();
    }
    if (!this.l.E) {
      for (zj ☃ : this.l.a(zj.class, bl().b(50.0D, 100.0D, 50.0D))) {
        ☃.b(nk.J);
      }
    }
  }
  
  protected void L()
  {
    this.aU = 0;
  }
  
  public int d(float ☃)
  {
    return 15728880;
  }
  
  public void e(float ☃, float ☃) {}
  
  public void c(rl ☃) {}
  
  protected void bA()
  {
    super.bA();
    
    a(yt.a).a(300.0D);
    a(yt.d).a(0.6000000238418579D);
    a(yt.b).a(40.0D);
    a(yt.g).a(4.0D);
  }
  
  public float a(int ☃)
  {
    return this.by[☃];
  }
  
  public float b(int ☃)
  {
    return this.bx[☃];
  }
  
  public int cZ()
  {
    return ((Integer)this.Z.a(bw)).intValue();
  }
  
  public void l(int ☃)
  {
    this.Z.b(bw, Integer.valueOf(☃));
  }
  
  public int m(int ☃)
  {
    return ((Integer)this.Z.a(bv[☃])).intValue();
  }
  
  public void a(int ☃, int ☃)
  {
    this.Z.b(bv[☃], Integer.valueOf(☃));
  }
  
  public boolean da()
  {
    return bQ() <= bW() / 2.0F;
  }
  
  public sf ca()
  {
    return sf.b;
  }
  
  protected boolean n(rr ☃)
  {
    return false;
  }
  
  public boolean aV()
  {
    return false;
  }
  
  class a
    extends tk
  {
    public a()
    {
      a(7);
    }
    
    public boolean a()
    {
      return xo.this.cZ() > 0;
    }
  }
}
