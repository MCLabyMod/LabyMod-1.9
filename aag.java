import com.google.common.collect.Lists;
import java.util.List;

public class aag
  extends rr
{
  private static final ke<Integer> a = kh.a(aag.class, kg.b);
  private static final ke<Integer> b = kh.a(aag.class, kg.b);
  private static final ke<Float> c = kh.a(aag.class, kg.c);
  private static final ke<Integer> d = kh.a(aag.class, kg.b);
  private static final ke<Boolean>[] e = { kh.a(aag.class, kg.h), kh.a(aag.class, kg.h) };
  private float[] f = new float[2];
  private float g;
  private float h;
  private float as;
  private int at;
  private double au;
  private double av;
  private double aw;
  private double ax;
  private double ay;
  private boolean az;
  private boolean aA;
  private boolean aB;
  private boolean aC;
  private double aD;
  private float aE;
  private aag.a aF;
  private aag.a aG;
  private double aH;
  
  public aag(aht ☃)
  {
    super(☃);
    this.i = true;
    a(1.375F, 0.5625F);
  }
  
  public aag(aht ☃, double ☃, double ☃, double ☃)
  {
    this(☃);
    b(☃, ☃, ☃);
    
    this.s = 0.0D;
    this.t = 0.0D;
    this.u = 0.0D;
    
    this.m = ☃;
    this.n = ☃;
    this.o = ☃;
  }
  
  protected boolean ae()
  {
    return false;
  }
  
  protected void i()
  {
    this.Z.a(a, Integer.valueOf(0));
    this.Z.a(b, Integer.valueOf(1));
    this.Z.a(c, Float.valueOf(0.0F));
    this.Z.a(d, Integer.valueOf(aag.b.a.ordinal()));
    for (int ☃ = 0; ☃ < e.length; ☃++) {
      this.Z.a(e[☃], Boolean.valueOf(false));
    }
  }
  
  public bbh j(rr ☃)
  {
    return ☃.bl();
  }
  
  public bbh af()
  {
    return bl();
  }
  
  public boolean aq()
  {
    return true;
  }
  
  public double ay()
  {
    return -0.1D;
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if (b(☃)) {
      return false;
    }
    if ((this.l.E) || (this.F)) {
      return true;
    }
    if (((☃ instanceof re)) && (☃.j() != null) && (w(☃.j()))) {
      return false;
    }
    d(-q());
    b(10);
    a(n() + ☃ * 10.0F);
    ao();
    boolean ☃ = ((☃.j() instanceof zj)) && (((zj)☃.j()).bJ.d);
    if ((☃) || (n() > 40.0F))
    {
      if ((!☃) && (this.l.U().b("doEntityDrops"))) {
        a(j(), 1, 0.0F);
      }
      T();
    }
    return true;
  }
  
  public void i(rr ☃)
  {
    if ((☃ instanceof aag))
    {
      if (☃.bl().b < bl().e) {
        super.i(☃);
      }
    }
    else if (☃.bl().b <= bl().b) {
      super.i(☃);
    }
  }
  
  public ado j()
  {
    switch (aag.1.a[r().ordinal()])
    {
    case 1: 
    default: 
      return ads.aG;
    case 2: 
      return ads.aH;
    case 3: 
      return ads.aI;
    case 4: 
      return ads.aJ;
    case 5: 
      return ads.aK;
    }
    return ads.aL;
  }
  
  public void aD()
  {
    d(-q());
    b(10);
    a(n() * 11.0F);
  }
  
  public boolean ap()
  {
    return !this.F;
  }
  
  public void a(double ☃, double ☃, double ☃, float ☃, float ☃, int ☃, boolean ☃)
  {
    this.au = ☃;
    this.av = ☃;
    this.aw = ☃;
    this.ax = ☃;
    this.ay = ☃;
    this.at = 10;
  }
  
  public cq bj()
  {
    return bi().e();
  }
  
  public void m()
  {
    this.aG = this.aF;
    this.aF = t();
    if ((this.aF == aag.a.b) || (this.aF == aag.a.c)) {
      this.h += 1.0F;
    } else {
      this.h = 0.0F;
    }
    if ((!this.l.E) && (this.h >= 60.0F)) {
      az();
    }
    if (o() > 0) {
      b(o() - 1);
    }
    if (n() > 0.0F) {
      a(n() - 1.0F);
    }
    this.m = this.p;
    this.n = this.q;
    this.o = this.r;
    
    super.m();
    s();
    if (bx())
    {
      if ((bu().size() == 0) || (!(bu().get(0) instanceof zj))) {
        a(false, false);
      }
      w();
      if (this.l.E)
      {
        x();
        this.l.a(new iv(a(0), a(1)));
      }
      d(this.s, this.t, this.u);
    }
    else
    {
      this.s = 0.0D;
      this.t = 0.0D;
      this.u = 0.0D;
    }
    for (int ☃ = 0; ☃ <= 1; ☃++) {
      if (a(☃))
      {
        int tmp284_283 = ☃; float[] tmp284_280 = this.f;tmp284_280[tmp284_283] = ((float)(tmp284_280[tmp284_283] + 0.01D));
      }
      else
      {
        this.f[☃] = 0.0F;
      }
    }
    ac();
    
    List<rr> ☃ = this.l.a(this, bl().b(0.20000000298023224D, -0.009999999776482582D, 0.20000000298023224D), rv.a(this));
    if (!☃.isEmpty())
    {
      boolean ☃ = (!this.l.E) && (!(bt() instanceof zj));
      for (int ☃ = 0; ☃ < ☃.size(); ☃++)
      {
        rr ☃ = (rr)☃.get(☃);
        if (!☃.w(this)) {
          if ((☃) && (bu().size() < 2) && (!☃.aI()) && (☃.G < this.G) && ((☃ instanceof sa)) && (!(☃ instanceof wi)) && (!(☃ instanceof zj))) {
            ☃.m(this);
          } else {
            i(☃);
          }
        }
      }
    }
  }
  
  private void s()
  {
    if ((this.at <= 0) || (bx())) {
      return;
    }
    double ☃ = this.p + (this.au - this.p) / this.at;
    double ☃ = this.q + (this.av - this.q) / this.at;
    double ☃ = this.r + (this.aw - this.r) / this.at;
    
    double ☃ = on.g(this.ax - this.v);
    
    this.v = ((float)(this.v + ☃ / this.at));
    this.w = ((float)(this.w + (this.ay - this.w) / this.at));
    
    this.at -= 1;
    b(☃, ☃, ☃);
    b(this.v, this.w);
  }
  
  public void a(boolean ☃, boolean ☃)
  {
    this.Z.b(e[0], Boolean.valueOf(☃));
    this.Z.b(e[1], Boolean.valueOf(☃));
  }
  
  public float a(int ☃, float ☃)
  {
    if (a(☃)) {
      return (float)on.b(this.f[☃] - 0.01D, this.f[☃], ☃);
    }
    return 0.0F;
  }
  
  private aag.a t()
  {
    aag.a ☃ = v();
    if (☃ != null)
    {
      this.aD = bl().e;
      return ☃;
    }
    if (u()) {
      return aag.a.a;
    }
    float ☃ = l();
    if (☃ > 0.0F)
    {
      this.aE = ☃;
      return aag.a.d;
    }
    return aag.a.e;
  }
  
  public float k()
  {
    bbh ☃ = bl();
    int ☃ = on.c(☃.a);
    int ☃ = on.f(☃.d);
    int ☃ = on.c(☃.e);
    int ☃ = on.f(☃.e - this.aH);
    int ☃ = on.c(☃.c);
    int ☃ = on.f(☃.f);
    
    cj.b ☃ = cj.b.s();
    try
    {
      label203:
      for (int ☃ = ☃; ☃ < ☃; ☃++)
      {
        float ☃ = 0.0F;
        for (int ☃ = ☃; ☃ < ☃; ☃++) {
          for (int ☃ = ☃; ☃ < ☃; ☃++)
          {
            ☃.d(☃, ☃, ☃);
            arc ☃ = this.l.o(☃);
            if (☃.a() == axe.h) {
              ☃ = Math.max(☃, a(☃, this.l, ☃));
            }
            if (☃ >= 1.0F) {
              break label203;
            }
          }
        }
        if (☃ < 1.0F) {
          return ☃.q() + ☃;
        }
      }
      return ☃ + 1;
    }
    finally
    {
      ☃.t();
    }
  }
  
  public float l()
  {
    bbh ☃ = bl();
    bbh ☃ = new bbh(☃.a, ☃.b - 0.001D, ☃.c, ☃.d, ☃.b, ☃.f);
    
    int ☃ = on.c(☃.a) - 1;
    int ☃ = on.f(☃.d) + 1;
    int ☃ = on.c(☃.b) - 1;
    int ☃ = on.f(☃.e) + 1;
    int ☃ = on.c(☃.c) - 1;
    int ☃ = on.f(☃.f) + 1;
    
    List<bbh> ☃ = Lists.newArrayList();
    float ☃ = 0.0F;
    int ☃ = 0;
    
    cj.b ☃ = cj.b.s();
    try
    {
      for (int ☃ = ☃; ☃ < ☃; ☃++) {
        for (int ☃ = ☃; ☃ < ☃; ☃++)
        {
          int ☃ = ((☃ == ☃) || (☃ == ☃ - 1) ? 1 : 0) + ((☃ == ☃) || (☃ == ☃ - 1) ? 1 : 0);
          if (☃ != 2) {
            for (int ☃ = ☃; ☃ < ☃; ☃++) {
              if ((☃ <= 0) || ((☃ != ☃) && (☃ != ☃ - 1)))
              {
                ☃.d(☃, ☃, ☃);
                
                arc ☃ = this.l.o(☃);
                ☃.a(this.l, ☃, ☃, ☃, this);
                if (!☃.isEmpty())
                {
                  ☃ += ☃.t().z;
                  ☃++;
                }
                ☃.clear();
              }
            }
          }
        }
      }
    }
    finally
    {
      ☃.t();
    }
    return ☃ / ☃;
  }
  
  private boolean u()
  {
    bbh ☃ = bl();
    int ☃ = on.c(☃.a);
    int ☃ = on.f(☃.d);
    int ☃ = on.c(☃.b);
    int ☃ = on.f(☃.b + 0.001D);
    int ☃ = on.c(☃.c);
    int ☃ = on.f(☃.f);
    
    boolean ☃ = false;
    this.aD = Double.MIN_VALUE;
    
    cj.b ☃ = cj.b.s();
    try
    {
      for (int ☃ = ☃; ☃ < ☃; ☃++) {
        for (int ☃ = ☃; ☃ < ☃; ☃++) {
          for (int ☃ = ☃; ☃ < ☃; ☃++)
          {
            ☃.d(☃, ☃, ☃);
            arc ☃ = this.l.o(☃);
            if (☃.a() == axe.h)
            {
              float ☃ = b(☃, this.l, ☃);
              
              this.aD = Math.max(☃, this.aD);
              ☃ |= ☃.b < ☃;
            }
          }
        }
      }
    }
    finally
    {
      ☃.t();
    }
    return ☃;
  }
  
  private aag.a v()
  {
    bbh ☃ = bl();
    double ☃ = ☃.e + 0.001D;
    
    int ☃ = on.c(☃.a);
    int ☃ = on.f(☃.d);
    int ☃ = on.c(☃.e);
    int ☃ = on.f(☃);
    int ☃ = on.c(☃.c);
    int ☃ = on.f(☃.f);
    
    boolean ☃ = false;
    cj.b ☃ = cj.b.s();
    try
    {
      for (int ☃ = ☃; ☃ < ☃; ☃++) {
        for (int ☃ = ☃; ☃ < ☃; ☃++) {
          for (int ☃ = ☃; ☃ < ☃; ☃++)
          {
            ☃.d(☃, ☃, ☃);
            arc ☃ = this.l.o(☃);
            if ((☃.a() == axe.h) && 
              (☃ < b(☃, this.l, ☃))) {
              if (((Integer)☃.c(amo.b)).intValue() == 0) {
                ☃ = true;
              } else {
                return aag.a.c;
              }
            }
          }
        }
      }
    }
    finally
    {
      ☃.t();
    }
    return ☃ ? aag.a.b : null;
  }
  
  public static float a(arc ☃, ahx ☃, cj ☃)
  {
    int ☃ = ((Integer)☃.c(amo.b)).intValue();
    if (((☃ & 0x7) == 0) && (☃.o(☃.a()).a() == axe.h)) {
      return 1.0F;
    }
    return 1.0F - amo.e(☃);
  }
  
  public static float b(arc ☃, ahx ☃, cj ☃)
  {
    return ☃.q() + a(☃, ☃, ☃);
  }
  
  private void w()
  {
    double ☃ = -0.03999999910593033D;
    double ☃ = ☃;
    double ☃ = 0.0D;
    this.g = 0.05F;
    if ((this.aG == aag.a.e) && (this.aF != aag.a.e) && (this.aF != aag.a.d))
    {
      this.aD = (bl().b + this.H);
      b(this.p, k() - this.H + 0.101D, this.r);
      this.t = 0.0D;
      this.aH = 0.0D;
      
      this.aF = aag.a.a;
    }
    else
    {
      if (this.aF == aag.a.a)
      {
        ☃ = (this.aD - bl().b) / this.H;
        this.g = 0.9F;
      }
      else if (this.aF == aag.a.c)
      {
        ☃ = -7.0E-4D;
        this.g = 0.9F;
      }
      else if (this.aF == aag.a.b)
      {
        ☃ = 0.009999999776482582D;
        this.g = 0.45F;
      }
      else if (this.aF == aag.a.e)
      {
        this.g = 0.9F;
      }
      else if (this.aF == aag.a.d)
      {
        this.g = this.aE;
        if ((bt() instanceof zj)) {
          this.aE /= 2.0F;
        }
      }
      this.s *= this.g;
      this.u *= this.g;
      this.as *= this.g;
      
      this.t += ☃;
      if (☃ > 0.0D)
      {
        double ☃ = 0.65D;
        this.t += ☃ * (-☃ / 0.65D);
        
        double ☃ = 0.75D;
        this.t *= 0.75D;
      }
    }
  }
  
  private void x()
  {
    if (!aJ()) {
      return;
    }
    float ☃ = 0.0F;
    if (this.az) {
      this.as += -1.0F;
    }
    if (this.aA) {
      this.as += 1.0F;
    }
    if ((this.aA != this.az) && (!this.aB) && (!this.aC)) {
      ☃ += 0.005F;
    }
    this.v += this.as;
    if (this.aB) {
      ☃ += 0.04F;
    }
    if (this.aC) {
      ☃ -= 0.005F;
    }
    this.s += on.a(-this.v * 0.017453292F) * ☃;
    this.u += on.b(this.v * 0.017453292F) * ☃;
    
    a((this.aA) || (this.aB), (this.az) || (this.aB));
  }
  
  public void k(rr ☃)
  {
    if (!w(☃)) {
      return;
    }
    float ☃ = 0.0F;
    float ☃ = (float)((this.F ? 0.009999999776482582D : ay()) + ☃.ax());
    if (bu().size() > 1)
    {
      int ☃ = bu().indexOf(☃);
      if (☃ == 0) {
        ☃ = 0.2F;
      } else {
        ☃ = -0.6F;
      }
      if ((☃ instanceof vw)) {
        ☃ = (float)(☃ + 0.2D);
      }
    }
    bbj ☃ = new bbj(☃, 0.0D, 0.0D).b(-this.v * 0.017453292F - 1.5707964F);
    
    ☃.b(this.p + ☃.b, this.q + ☃, this.r + ☃.d);
    
    ☃.v += this.as;
    ☃.h(☃.aS() + this.as);
    
    a(☃);
    if (((☃ instanceof vw)) && (bu().size() > 1))
    {
      int ☃ = ☃.O() % 2 == 0 ? 90 : 270;
      ☃.i(((vw)☃).aM + ☃);
      ☃.h(☃.aS() + ☃);
    }
  }
  
  protected void a(rr ☃)
  {
    ☃.i(this.v);
    
    float ☃ = on.g(☃.v - this.v);
    float ☃ = on.a(☃, -105.0F, 105.0F);
    ☃.x += ☃ - ☃;
    ☃.v += ☃ - ☃;
    ☃.h(☃.v);
  }
  
  public void l(rr ☃)
  {
    a(☃);
  }
  
  protected void b(dn ☃)
  {
    ☃.a("Type", r().a());
  }
  
  protected void a(dn ☃)
  {
    if (☃.b("Type", 8)) {
      a(aag.b.a(☃.l("Type")));
    }
  }
  
  public boolean a(zj ☃, adq ☃, qm ☃)
  {
    if ((!this.l.E) && (!☃.aK()) && (this.h < 60.0F)) {
      ☃.m(this);
    }
    return true;
  }
  
  protected void a(double ☃, boolean ☃, arc ☃, cj ☃)
  {
    this.aH = this.t;
    if (aI()) {
      return;
    }
    if (☃)
    {
      if (this.L > 3.0F)
      {
        if (this.aF != aag.a.d)
        {
          this.L = 0.0F;
          return;
        }
        e(this.L, 1.0F);
        if ((!this.l.E) && (!this.F))
        {
          T();
          if (this.l.U().b("doEntityDrops"))
          {
            for (int ☃ = 0; ☃ < 3; ☃++) {
              a(new adq(ado.a(aju.f), 1, r().b()), 0.0F);
            }
            for (int ☃ = 0; ☃ < 2; ☃++) {
              a(ads.A, 1, 0.0F);
            }
          }
        }
      }
      this.L = 0.0F;
    }
    else if ((this.l.o(new cj(this).b()).a() != axe.h) && 
      (☃ < 0.0D))
    {
      this.L = ((float)(this.L - ☃));
    }
  }
  
  public boolean a(int ☃)
  {
    return (((Boolean)this.Z.a(e[☃])).booleanValue()) && (bt() != null);
  }
  
  public void a(float ☃)
  {
    this.Z.b(c, Float.valueOf(☃));
  }
  
  public float n()
  {
    return ((Float)this.Z.a(c)).floatValue();
  }
  
  public void b(int ☃)
  {
    this.Z.b(a, Integer.valueOf(☃));
  }
  
  public int o()
  {
    return ((Integer)this.Z.a(a)).intValue();
  }
  
  public void d(int ☃)
  {
    this.Z.b(b, Integer.valueOf(☃));
  }
  
  public int q()
  {
    return ((Integer)this.Z.a(b)).intValue();
  }
  
  public void a(aag.b ☃)
  {
    this.Z.b(d, Integer.valueOf(☃.ordinal()));
  }
  
  public aag.b r()
  {
    return aag.b.a(((Integer)this.Z.a(d)).intValue());
  }
  
  protected boolean q(rr ☃)
  {
    return bu().size() < 2;
  }
  
  public rr bt()
  {
    List<rr> ☃ = bu();
    return ☃.isEmpty() ? null : (rr)☃.get(0);
  }
  
  public void a(boolean ☃, boolean ☃, boolean ☃, boolean ☃)
  {
    this.az = ☃;
    this.aA = ☃;
    this.aB = ☃;
    this.aC = ☃;
  }
  
  public static enum b
  {
    private final String g;
    private final int h;
    
    private b(int ☃, String ☃)
    {
      this.g = ☃;
      this.h = ☃;
    }
    
    public String a()
    {
      return this.g;
    }
    
    public int b()
    {
      return this.h;
    }
    
    public String toString()
    {
      return this.g;
    }
    
    public static b a(int ☃)
    {
      if ((☃ < 0) || (☃ >= values().length)) {
        ☃ = 0;
      }
      return values()[☃];
    }
    
    public static b a(String ☃)
    {
      for (int ☃ = 0; ☃ < values().length; ☃++) {
        if (values()[☃].a().equals(☃)) {
          return values()[☃];
        }
      }
      return values()[0];
    }
  }
  
  public static enum a
  {
    private a() {}
  }
}
