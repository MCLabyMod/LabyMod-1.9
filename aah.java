import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import net.minecraft.server.MinecraftServer;

public abstract class aah
  extends rr
  implements qt
{
  public static enum a
  {
    private static final Map<Integer, a> h;
    private final int i;
    private final String j;
    
    static
    {
      h = Maps.newHashMap();
      for (a ☃ : values()) {
        h.put(Integer.valueOf(☃.a()), ☃);
      }
    }
    
    private a(int ☃, String ☃)
    {
      this.i = ☃;
      this.j = ☃;
    }
    
    public int a()
    {
      return this.i;
    }
    
    public String b()
    {
      return this.j;
    }
    
    public static a a(int ☃)
    {
      a ☃ = (a)h.get(Integer.valueOf(☃));
      return ☃ == null ? a : ☃;
    }
  }
  
  private static final ke<Integer> a = kh.a(aah.class, kg.b);
  private static final ke<Integer> b = kh.a(aah.class, kg.b);
  private static final ke<Float> c = kh.a(aah.class, kg.c);
  private static final ke<Integer> d = kh.a(aah.class, kg.b);
  private static final ke<Integer> e = kh.a(aah.class, kg.b);
  private static final ke<Boolean> f = kh.a(aah.class, kg.h);
  private boolean g;
  
  public aah(aht ☃)
  {
    super(☃);
    this.i = true;
    a(0.98F, 0.7F);
  }
  
  public static aah a(aht ☃, double ☃, double ☃, double ☃, aah.a ☃)
  {
    switch (aah.1.a[☃.ordinal()])
    {
    case 1: 
      return new aai(☃, ☃, ☃, ☃);
    case 2: 
      return new aal(☃, ☃, ☃, ☃);
    case 3: 
      return new aap(☃, ☃, ☃, ☃);
    case 4: 
      return new aao(☃, ☃, ☃, ☃);
    case 5: 
      return new aam(☃, ☃, ☃, ☃);
    case 6: 
      return new aaj(☃, ☃, ☃, ☃);
    }
    return new aan(☃, ☃, ☃, ☃);
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
    this.Z.a(d, Integer.valueOf(0));
    this.Z.a(e, Integer.valueOf(6));
    this.Z.a(f, Boolean.valueOf(false));
  }
  
  public bbh j(rr ☃)
  {
    if (☃.aq()) {
      return ☃.bl();
    }
    return null;
  }
  
  public bbh af()
  {
    return null;
  }
  
  public boolean aq()
  {
    return true;
  }
  
  public aah(aht ☃, double ☃, double ☃, double ☃)
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
  
  public double ay()
  {
    return 0.0D;
  }
  
  public boolean a(rc ☃, float ☃)
  {
    if ((this.l.E) || (this.F)) {
      return true;
    }
    if (b(☃)) {
      return false;
    }
    e(-u());
    d(10);
    ao();
    a(s() + ☃ * 10.0F);
    boolean ☃ = ((☃.j() instanceof zj)) && (((zj)☃.j()).bJ.d);
    if ((☃) || (s() > 40.0F))
    {
      az();
      if ((!☃) || (o_())) {
        a(☃);
      } else {
        T();
      }
    }
    return true;
  }
  
  public void a(rc ☃)
  {
    T();
    if (this.l.U().b("doEntityDrops"))
    {
      adq ☃ = new adq(ads.aB, 1);
      if (h_() != null) {
        ☃.c(h_());
      }
      a(☃, 0.0F);
    }
  }
  
  public void aD()
  {
    e(-u());
    d(10);
    a(s() + s() * 10.0F);
  }
  
  public boolean ap()
  {
    return !this.F;
  }
  
  public void T()
  {
    super.T();
  }
  
  private static final int[][][] h = { { { 0, 0, -1 }, { 0, 0, 1 } }, { { -1, 0, 0 }, { 1, 0, 0 } }, { { -1, -1, 0 }, { 1, 0, 0 } }, { { -1, 0, 0 }, { 1, -1, 0 } }, { { 0, 0, -1 }, { 0, -1, 1 } }, { { 0, -1, -1 }, { 0, 0, 1 } }, { { 0, 0, 1 }, { 1, 0, 0 } }, { { 0, 0, 1 }, { -1, 0, 0 } }, { { 0, 0, -1 }, { -1, 0, 0 } }, { { 0, 0, -1 }, { 1, 0, 0 } } };
  private int as;
  private double at;
  private double au;
  private double av;
  private double aw;
  private double ax;
  private double ay;
  private double az;
  private double aA;
  
  public cq bj()
  {
    return this.g ? bi().d().e() : bi().e();
  }
  
  public void m()
  {
    if (t() > 0) {
      d(t() - 1);
    }
    if (s() > 0.0F) {
      a(s() - 1.0F);
    }
    if (this.q < -64.0D) {
      Y();
    }
    if ((!this.l.E) && ((this.l instanceof lp)))
    {
      this.l.C.a("portal");
      MinecraftServer ☃ = this.l.u();
      int ☃ = V();
      if (this.ak)
      {
        if (☃.E())
        {
          if ((!aI()) && 
            (this.al++ >= ☃))
          {
            this.al = ☃;
            this.aj = aC();
            int ☃;
            int ☃;
            if (this.l.s.p().a() == -1) {
              ☃ = 0;
            } else {
              ☃ = -1;
            }
            c(☃);
          }
          this.ak = false;
        }
      }
      else
      {
        if (this.al > 0) {
          this.al -= 4;
        }
        if (this.al < 0) {
          this.al = 0;
        }
      }
      if (this.aj > 0) {
        this.aj -= 1;
      }
      this.l.C.b();
    }
    if (this.l.E)
    {
      if (this.as > 0)
      {
        double ☃ = this.p + (this.at - this.p) / this.as;
        double ☃ = this.q + (this.au - this.q) / this.as;
        double ☃ = this.r + (this.av - this.r) / this.as;
        
        double ☃ = on.g(this.aw - this.v);
        
        this.v = ((float)(this.v + ☃ / this.as));
        this.w = ((float)(this.w + (this.ax - this.w) / this.as));
        
        this.as -= 1;
        b(☃, ☃, ☃);
        b(this.v, this.w);
      }
      else
      {
        b(this.p, this.q, this.r);
        b(this.v, this.w);
      }
      return;
    }
    this.m = this.p;
    this.n = this.q;
    this.o = this.r;
    
    this.t -= 0.03999999910593033D;
    
    int ☃ = on.c(this.p);
    int ☃ = on.c(this.q);
    int ☃ = on.c(this.r);
    if (ajp.b(this.l, new cj(☃, ☃ - 1, ☃))) {
      ☃--;
    }
    cj ☃ = new cj(☃, ☃, ☃);
    arc ☃ = this.l.o(☃);
    if (ajp.i(☃))
    {
      a(☃, ☃);
      if (☃.t() == aju.cs) {
        a(☃, ☃, ☃, ((Boolean)☃.c(ann.e)).booleanValue());
      }
    }
    else
    {
      q();
    }
    ac();
    
    this.w = 0.0F;
    double ☃ = this.m - this.p;
    double ☃ = this.o - this.r;
    if (☃ * ☃ + ☃ * ☃ > 0.001D)
    {
      this.v = ((float)(on.b(☃, ☃) * 180.0D / 3.141592653589793D));
      if (this.g) {
        this.v += 180.0F;
      }
    }
    double ☃ = on.g(this.v - this.x);
    if ((☃ < -170.0D) || (☃ >= 170.0D))
    {
      this.v += 180.0F;
      this.g = (!this.g);
    }
    b(this.v, this.w);
    for (rr ☃ : this.l.b(this, bl().b(0.20000000298023224D, 0.0D, 0.20000000298023224D))) {
      if ((!w(☃)) && (☃.aq()) && ((☃ instanceof aah))) {
        ☃.i(this);
      }
    }
    aj();
  }
  
  protected double o()
  {
    return 0.4D;
  }
  
  public void a(int ☃, int ☃, int ☃, boolean ☃) {}
  
  protected void q()
  {
    double ☃ = o();
    this.s = on.a(this.s, -☃, ☃);
    this.u = on.a(this.u, -☃, ☃);
    if (this.z)
    {
      this.s *= 0.5D;
      this.t *= 0.5D;
      this.u *= 0.5D;
    }
    d(this.s, this.t, this.u);
    if (!this.z)
    {
      this.s *= 0.949999988079071D;
      this.t *= 0.949999988079071D;
      this.u *= 0.949999988079071D;
    }
  }
  
  protected void a(cj ☃, arc ☃)
  {
    this.L = 0.0F;
    
    bbj ☃ = k(this.p, this.q, this.r);
    this.q = ☃.q();
    
    boolean ☃ = false;
    boolean ☃ = false;
    ajp ☃ = (ajp)☃.t();
    if (☃ == aju.D)
    {
      ☃ = ((Boolean)☃.c(ann.e)).booleanValue();
      ☃ = !☃;
    }
    double ☃ = 0.0078125D;
    ajp.b ☃ = (ajp.b)☃.c(☃.g());
    switch (aah.1.b[☃.ordinal()])
    {
    case 1: 
      this.s -= 0.0078125D;
      this.q += 1.0D;
      break;
    case 2: 
      this.s += 0.0078125D;
      this.q += 1.0D;
      break;
    case 3: 
      this.u += 0.0078125D;
      this.q += 1.0D;
      break;
    case 4: 
      this.u -= 0.0078125D;
      this.q += 1.0D;
    }
    int[][] ☃ = h[☃.a()];
    
    double ☃ = ☃[1][0] - ☃[0][0];
    double ☃ = ☃[1][2] - ☃[0][2];
    double ☃ = Math.sqrt(☃ * ☃ + ☃ * ☃);
    
    double ☃ = this.s * ☃ + this.u * ☃;
    if (☃ < 0.0D)
    {
      ☃ = -☃;
      ☃ = -☃;
    }
    double ☃ = Math.sqrt(this.s * this.s + this.u * this.u);
    if (☃ > 2.0D) {
      ☃ = 2.0D;
    }
    this.s = (☃ * ☃ / ☃);
    this.u = (☃ * ☃ / ☃);
    
    rr ☃ = bu().isEmpty() ? null : (rr)bu().get(0);
    if ((☃ instanceof sa))
    {
      double ☃ = ((sa)☃).be;
      if (☃ > 0.0D)
      {
        double ☃ = -Math.sin(☃.v * 0.017453292F);
        double ☃ = Math.cos(☃.v * 0.017453292F);
        
        double ☃ = this.s * this.s + this.u * this.u;
        if (☃ < 0.01D)
        {
          this.s += ☃ * 0.1D;
          this.u += ☃ * 0.1D;
          
          ☃ = false;
        }
      }
    }
    if (☃)
    {
      double ☃ = Math.sqrt(this.s * this.s + this.u * this.u);
      if (☃ < 0.03D)
      {
        this.s *= 0.0D;
        this.t *= 0.0D;
        this.u *= 0.0D;
      }
      else
      {
        this.s *= 0.5D;
        this.t *= 0.0D;
        this.u *= 0.5D;
      }
    }
    double ☃ = 0.0D;
    double ☃ = ☃.p() + 0.5D + ☃[0][0] * 0.5D;
    double ☃ = ☃.r() + 0.5D + ☃[0][2] * 0.5D;
    double ☃ = ☃.p() + 0.5D + ☃[1][0] * 0.5D;
    double ☃ = ☃.r() + 0.5D + ☃[1][2] * 0.5D;
    
    ☃ = ☃ - ☃;
    ☃ = ☃ - ☃;
    if (☃ == 0.0D)
    {
      this.p = (☃.p() + 0.5D);
      ☃ = this.r - ☃.r();
    }
    else if (☃ == 0.0D)
    {
      this.r = (☃.r() + 0.5D);
      ☃ = this.p - ☃.p();
    }
    else
    {
      double ☃ = this.p - ☃;
      double ☃ = this.r - ☃;
      
      ☃ = (☃ * ☃ + ☃ * ☃) * 2.0D;
    }
    this.p = (☃ + ☃ * ☃);
    this.r = (☃ + ☃ * ☃);
    
    b(this.p, this.q, this.r);
    
    double ☃ = this.s;
    double ☃ = this.u;
    if (aJ())
    {
      ☃ *= 0.75D;
      ☃ *= 0.75D;
    }
    double ☃ = o();
    ☃ = on.a(☃, -☃, ☃);
    ☃ = on.a(☃, -☃, ☃);
    
    d(☃, 0.0D, ☃);
    if ((☃[0][1] != 0) && (on.c(this.p) - ☃.p() == ☃[0][0]) && (on.c(this.r) - ☃.r() == ☃[0][2])) {
      b(this.p, this.q + ☃[0][1], this.r);
    } else if ((☃[1][1] != 0) && (on.c(this.p) - ☃.p() == ☃[1][0]) && (on.c(this.r) - ☃.r() == ☃[1][2])) {
      b(this.p, this.q + ☃[1][1], this.r);
    }
    r();
    
    bbj ☃ = k(this.p, this.q, this.r);
    if ((☃ != null) && (☃ != null))
    {
      double ☃ = (☃.c - ☃.c) * 0.05D;
      
      ☃ = Math.sqrt(this.s * this.s + this.u * this.u);
      if (☃ > 0.0D)
      {
        this.s = (this.s / ☃ * (☃ + ☃));
        this.u = (this.u / ☃ * (☃ + ☃));
      }
      b(this.p, ☃.c, this.r);
    }
    int ☃ = on.c(this.p);
    int ☃ = on.c(this.r);
    if ((☃ != ☃.p()) || (☃ != ☃.r()))
    {
      ☃ = Math.sqrt(this.s * this.s + this.u * this.u);
      
      this.s = (☃ * (☃ - ☃.p()));
      this.u = (☃ * (☃ - ☃.r()));
    }
    if (☃)
    {
      double ☃ = Math.sqrt(this.s * this.s + this.u * this.u);
      if (☃ > 0.01D)
      {
        double ☃ = 0.06D;
        this.s += this.s / ☃ * ☃;
        this.u += this.u / ☃ * ☃;
      }
      else if (☃ == ajp.b.b)
      {
        if (this.l.o(☃.e()).l()) {
          this.s = 0.02D;
        } else if (this.l.o(☃.f()).l()) {
          this.s = -0.02D;
        }
      }
      else if (☃ == ajp.b.a)
      {
        if (this.l.o(☃.c()).l()) {
          this.u = 0.02D;
        } else if (this.l.o(☃.d()).l()) {
          this.u = -0.02D;
        }
      }
    }
  }
  
  protected void r()
  {
    if (aJ())
    {
      this.s *= 0.996999979019165D;
      this.t *= 0.0D;
      this.u *= 0.996999979019165D;
    }
    else
    {
      this.s *= 0.9599999785423279D;
      this.t *= 0.0D;
      this.u *= 0.9599999785423279D;
    }
  }
  
  public void b(double ☃, double ☃, double ☃)
  {
    this.p = ☃;
    this.q = ☃;
    this.r = ☃;
    float ☃ = this.G / 2.0F;
    float ☃ = this.H;
    a(new bbh(☃ - ☃, ☃, ☃ - ☃, ☃ + ☃, ☃ + ☃, ☃ + ☃));
  }
  
  public bbj a(double ☃, double ☃, double ☃, double ☃)
  {
    int ☃ = on.c(☃);
    int ☃ = on.c(☃);
    int ☃ = on.c(☃);
    if (ajp.b(this.l, new cj(☃, ☃ - 1, ☃))) {
      ☃--;
    }
    arc ☃ = this.l.o(new cj(☃, ☃, ☃));
    if (ajp.i(☃))
    {
      ajp.b ☃ = (ajp.b)☃.c(((ajp)☃.t()).g());
      ☃ = ☃;
      if (☃.c()) {
        ☃ = ☃ + 1;
      }
      int[][] ☃ = h[☃.a()];
      
      double ☃ = ☃[1][0] - ☃[0][0];
      double ☃ = ☃[1][2] - ☃[0][2];
      double ☃ = Math.sqrt(☃ * ☃ + ☃ * ☃);
      ☃ /= ☃;
      ☃ /= ☃;
      
      ☃ += ☃ * ☃;
      ☃ += ☃ * ☃;
      if ((☃[0][1] != 0) && (on.c(☃) - ☃ == ☃[0][0]) && (on.c(☃) - ☃ == ☃[0][2])) {
        ☃ += ☃[0][1];
      } else if ((☃[1][1] != 0) && (on.c(☃) - ☃ == ☃[1][0]) && (on.c(☃) - ☃ == ☃[1][2])) {
        ☃ += ☃[1][1];
      }
      return k(☃, ☃, ☃);
    }
    return null;
  }
  
  public bbj k(double ☃, double ☃, double ☃)
  {
    int ☃ = on.c(☃);
    int ☃ = on.c(☃);
    int ☃ = on.c(☃);
    if (ajp.b(this.l, new cj(☃, ☃ - 1, ☃))) {
      ☃--;
    }
    arc ☃ = this.l.o(new cj(☃, ☃, ☃));
    if (ajp.i(☃))
    {
      ajp.b ☃ = (ajp.b)☃.c(((ajp)☃.t()).g());
      int[][] ☃ = h[☃.a()];
      
      double ☃ = 0.0D;
      double ☃ = ☃ + 0.5D + ☃[0][0] * 0.5D;
      double ☃ = ☃ + 0.0625D + ☃[0][1] * 0.5D;
      double ☃ = ☃ + 0.5D + ☃[0][2] * 0.5D;
      double ☃ = ☃ + 0.5D + ☃[1][0] * 0.5D;
      double ☃ = ☃ + 0.0625D + ☃[1][1] * 0.5D;
      double ☃ = ☃ + 0.5D + ☃[1][2] * 0.5D;
      
      double ☃ = ☃ - ☃;
      double ☃ = (☃ - ☃) * 2.0D;
      double ☃ = ☃ - ☃;
      if (☃ == 0.0D)
      {
        ☃ = ☃ + 0.5D;
        ☃ = ☃ - ☃;
      }
      else if (☃ == 0.0D)
      {
        ☃ = ☃ + 0.5D;
        ☃ = ☃ - ☃;
      }
      else
      {
        double ☃ = ☃ - ☃;
        double ☃ = ☃ - ☃;
        
        ☃ = (☃ * ☃ + ☃ * ☃) * 2.0D;
      }
      ☃ = ☃ + ☃ * ☃;
      ☃ = ☃ + ☃ * ☃;
      ☃ = ☃ + ☃ * ☃;
      if (☃ < 0.0D) {
        ☃ += 1.0D;
      }
      if (☃ > 0.0D) {
        ☃ += 0.5D;
      }
      return new bbj(☃, ☃, ☃);
    }
    return null;
  }
  
  public bbh bm()
  {
    bbh ☃ = bl();
    if (B()) {
      return ☃.g(Math.abs(y()) / 16.0D);
    }
    return ☃;
  }
  
  protected void a(dn ☃)
  {
    if (☃.p("CustomDisplayTile"))
    {
      ajt ☃;
      ajt ☃;
      if (☃.b("DisplayTile", 8)) {
        ☃ = ajt.b(☃.l("DisplayTile"));
      } else {
        ☃ = ajt.b(☃.h("DisplayTile"));
      }
      int ☃ = ☃.h("DisplayData");
      a(☃ == null ? aju.a.u() : ☃.a(☃));
      k(☃.h("DisplayOffset"));
    }
  }
  
  protected void b(dn ☃)
  {
    if (B())
    {
      ☃.a("CustomDisplayTile", true);
      arc ☃ = w();
      kk ☃ = (kk)ajt.h.b(☃.t());
      ☃.a("DisplayTile", ☃ == null ? "" : ☃.toString());
      ☃.a("DisplayData", ☃.t().e(☃));
      ☃.a("DisplayOffset", y());
    }
  }
  
  public void i(rr ☃)
  {
    if (this.l.E) {
      return;
    }
    if ((☃.Q) || (this.Q)) {
      return;
    }
    if (w(☃)) {
      return;
    }
    if (((☃ instanceof sa)) && (v() == aah.a.a) && (this.s * this.s + this.u * this.u > 0.01D) && 
      (!(☃ instanceof zj)) && (!(☃ instanceof wh)) && 
      (!aJ()) && (!☃.aI())) {
      ☃.m(this);
    }
    double ☃ = ☃.p - this.p;
    double ☃ = ☃.r - this.r;
    
    double ☃ = ☃ * ☃ + ☃ * ☃;
    if (☃ >= 9.999999747378752E-5D)
    {
      ☃ = on.a(☃);
      ☃ /= ☃;
      ☃ /= ☃;
      double ☃ = 1.0D / ☃;
      if (☃ > 1.0D) {
        ☃ = 1.0D;
      }
      ☃ *= ☃;
      ☃ *= ☃;
      ☃ *= 0.10000000149011612D;
      ☃ *= 0.10000000149011612D;
      
      ☃ *= (1.0F - this.R);
      ☃ *= (1.0F - this.R);
      ☃ *= 0.5D;
      ☃ *= 0.5D;
      if ((☃ instanceof aah))
      {
        double ☃ = ☃.p - this.p;
        double ☃ = ☃.r - this.r;
        
        bbj ☃ = new bbj(☃, 0.0D, ☃).a();
        bbj ☃ = new bbj(on.b(this.v * 0.017453292F), 0.0D, on.a(this.v * 0.017453292F)).a();
        
        double ☃ = Math.abs(☃.b(☃));
        if (☃ < 0.800000011920929D) {
          return;
        }
        double ☃ = ☃.s + this.s;
        double ☃ = ☃.u + this.u;
        if ((((aah)☃).v() == aah.a.c) && (v() != aah.a.c))
        {
          this.s *= 0.20000000298023224D;
          this.u *= 0.20000000298023224D;
          g(☃.s - ☃, 0.0D, ☃.u - ☃);
          ☃.s *= 0.949999988079071D;
          ☃.u *= 0.949999988079071D;
        }
        else if ((((aah)☃).v() != aah.a.c) && (v() == aah.a.c))
        {
          ☃.s *= 0.20000000298023224D;
          ☃.u *= 0.20000000298023224D;
          ☃.g(this.s + ☃, 0.0D, this.u + ☃);
          this.s *= 0.949999988079071D;
          this.u *= 0.949999988079071D;
        }
        else
        {
          ☃ /= 2.0D;
          ☃ /= 2.0D;
          this.s *= 0.20000000298023224D;
          this.u *= 0.20000000298023224D;
          g(☃ - ☃, 0.0D, ☃ - ☃);
          ☃.s *= 0.20000000298023224D;
          ☃.u *= 0.20000000298023224D;
          ☃.g(☃ + ☃, 0.0D, ☃ + ☃);
        }
      }
      else
      {
        g(-☃, 0.0D, -☃);
        ☃.g(☃ / 4.0D, 0.0D, ☃ / 4.0D);
      }
    }
  }
  
  public void a(double ☃, double ☃, double ☃, float ☃, float ☃, int ☃, boolean ☃)
  {
    this.at = ☃;
    this.au = ☃;
    this.av = ☃;
    this.aw = ☃;
    this.ax = ☃;
    
    this.as = (☃ + 2);
    
    this.s = this.ay;
    this.t = this.az;
    this.u = this.aA;
  }
  
  public void i(double ☃, double ☃, double ☃)
  {
    this.ay = (this.s = ☃);
    this.az = (this.t = ☃);
    this.aA = (this.u = ☃);
  }
  
  public void a(float ☃)
  {
    this.Z.b(c, Float.valueOf(☃));
  }
  
  public float s()
  {
    return ((Float)this.Z.a(c)).floatValue();
  }
  
  public void d(int ☃)
  {
    this.Z.b(a, Integer.valueOf(☃));
  }
  
  public int t()
  {
    return ((Integer)this.Z.a(a)).intValue();
  }
  
  public void e(int ☃)
  {
    this.Z.b(b, Integer.valueOf(☃));
  }
  
  public int u()
  {
    return ((Integer)this.Z.a(b)).intValue();
  }
  
  public abstract aah.a v();
  
  public arc w()
  {
    if (!B()) {
      return x();
    }
    return ajt.c(((Integer)R().a(d)).intValue());
  }
  
  public arc x()
  {
    return aju.a.u();
  }
  
  public int y()
  {
    if (!B()) {
      return A();
    }
    return ((Integer)R().a(e)).intValue();
  }
  
  public int A()
  {
    return 6;
  }
  
  public void a(arc ☃)
  {
    R().b(d, Integer.valueOf(ajt.j(☃)));
    a(true);
  }
  
  public void k(int ☃)
  {
    R().b(e, Integer.valueOf(☃));
    a(true);
  }
  
  public boolean B()
  {
    return ((Boolean)R().a(f)).booleanValue();
  }
  
  public void a(boolean ☃)
  {
    R().b(f, Boolean.valueOf(☃));
  }
}
