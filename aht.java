import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import net.minecraft.server.MinecraftServer;

public abstract class aht
  implements ahx
{
  private int a = 63;
  protected boolean d;
  public final List<rr> e = Lists.newArrayList();
  protected final List<rr> f = Lists.newArrayList();
  public final List<apv> g = Lists.newArrayList();
  public final List<apv> h = Lists.newArrayList();
  private final List<apv> b = Lists.newArrayList();
  private final List<apv> c = Lists.newArrayList();
  public final List<zj> i = Lists.newArrayList();
  public final List<rr> j = Lists.newArrayList();
  protected final oh<rr> k = new oh();
  private long I = 16777215L;
  private int J;
  protected int l = new Random().nextInt();
  protected final int m = 1013904223;
  protected float n;
  protected float o;
  protected float p;
  protected float q;
  private int K;
  public final Random r = new Random();
  public final asv s;
  protected vg t = new vg();
  protected List<ahv> u = Lists.newArrayList(new ahv[] { this.t });
  protected arz v;
  protected final azi w;
  protected azh x;
  protected boolean y;
  protected azs z;
  protected vr A;
  protected bab B;
  public final oo C;
  private final Calendar L = Calendar.getInstance();
  protected bbp D = new bbp();
  public final boolean E;
  protected boolean F = true;
  protected boolean G = true;
  private boolean M;
  private final arv N;
  
  protected aht(azi ☃, azh ☃, asv ☃, oo ☃, boolean ☃)
  {
    this.w = ☃;
    this.C = ☃;
    this.x = ☃;
    this.s = ☃;
    this.E = ☃;
    this.N = ☃.o();
  }
  
  public aht b()
  {
    return this;
  }
  
  public aig b(final cj ☃)
  {
    if (e(☃))
    {
      ase ☃ = f(☃);
      try
      {
        return ☃.a(☃, this.s.k());
      }
      catch (Throwable ☃)
      {
        b ☃ = b.a(☃, "Getting biome");
        c ☃ = ☃.a("Coordinates of biome request");
        
        ☃.a("Location", new Callable()
        {
          public String a()
            throws Exception
          {
            return c.a(☃);
          }
        });
        throw new e(☃);
      }
    }
    return this.s.k().a(☃, ail.c);
  }
  
  public aik A()
  {
    return this.s.k();
  }
  
  protected abstract arz n();
  
  public void a(ahw ☃)
  {
    this.x.d(true);
  }
  
  public MinecraftServer u()
  {
    return null;
  }
  
  public void h()
  {
    A(new cj(8, 64, 8));
  }
  
  public arc c(cj ☃)
  {
    cj ☃ = new cj(☃.p(), K(), ☃.r());
    while (!d(☃.a())) {
      ☃ = ☃.a();
    }
    return o(☃);
  }
  
  private boolean a(cj ☃)
  {
    return (☃.p() >= -30000000) && (☃.r() >= -30000000) && (☃.p() < 30000000) && (☃.r() < 30000000) && (☃.q() >= 0) && (☃.q() < 256);
  }
  
  public boolean d(cj ☃)
  {
    return o(☃).a() == axe.a;
  }
  
  public boolean e(cj ☃)
  {
    return a(☃, true);
  }
  
  public boolean a(cj ☃, boolean ☃)
  {
    if (!a(☃)) {
      return false;
    }
    return a(☃.p() >> 4, ☃.r() >> 4, ☃);
  }
  
  public boolean a(cj ☃, int ☃)
  {
    return a(☃, ☃, true);
  }
  
  public boolean a(cj ☃, int ☃, boolean ☃)
  {
    return a(☃.p() - ☃, ☃.q() - ☃, ☃.r() - ☃, ☃.p() + ☃, ☃.q() + ☃, ☃.r() + ☃, ☃);
  }
  
  public boolean a(cj ☃, cj ☃)
  {
    return a(☃, ☃, true);
  }
  
  public boolean a(cj ☃, cj ☃, boolean ☃)
  {
    return a(☃.p(), ☃.q(), ☃.r(), ☃.p(), ☃.q(), ☃.r(), ☃);
  }
  
  public boolean a(avp ☃)
  {
    return b(☃, true);
  }
  
  public boolean b(avp ☃, boolean ☃)
  {
    return a(☃.a, ☃.b, ☃.c, ☃.d, ☃.e, ☃.f, ☃);
  }
  
  private boolean a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, boolean ☃)
  {
    if ((☃ < 0) || (☃ >= 256)) {
      return false;
    }
    ☃ >>= 4;
    ☃ >>= 4;
    ☃ >>= 4;
    ☃ >>= 4;
    for (int ☃ = ☃; ☃ <= ☃; ☃++) {
      for (int ☃ = ☃; ☃ <= ☃; ☃++) {
        if (!a(☃, ☃, ☃)) {
          return false;
        }
      }
    }
    return true;
  }
  
  protected abstract boolean a(int paramInt1, int paramInt2, boolean paramBoolean);
  
  public ase f(cj ☃)
  {
    return a(☃.p() >> 4, ☃.r() >> 4);
  }
  
  public ase a(int ☃, int ☃)
  {
    return this.v.d(☃, ☃);
  }
  
  public boolean a(cj ☃, arc ☃, int ☃)
  {
    if (!a(☃)) {
      return false;
    }
    if ((!this.E) && (this.x.t() == ahy.g)) {
      return false;
    }
    ase ☃ = f(☃);
    ajt ☃ = ☃.t();
    arc ☃ = ☃.a(☃, ☃);
    if (☃ != null)
    {
      if ((☃.c() != ☃.c()) || (☃.d() != ☃.d()))
      {
        this.C.a("checkLight");
        w(☃);
        this.C.b();
      }
      if (((☃ & 0x2) != 0) && ((!this.E) || ((☃ & 0x4) == 0)) && (☃.i())) {
        a(☃, ☃, ☃, ☃);
      }
      if ((!this.E) && ((☃ & 0x1) != 0))
      {
        c(☃, ☃.t());
        if (☃.n()) {
          f(☃, ☃);
        }
      }
      return true;
    }
    return false;
  }
  
  public boolean g(cj ☃)
  {
    return a(☃, aju.a.u(), 3);
  }
  
  public boolean b(cj ☃, boolean ☃)
  {
    arc ☃ = o(☃);
    ajt ☃ = ☃.t();
    if (☃.a() == axe.a) {
      return false;
    }
    b(2001, ☃, ajt.j(☃));
    if (☃) {
      ☃.b(this, ☃, ☃, 0);
    }
    return a(☃, aju.a.u(), 3);
  }
  
  public boolean a(cj ☃, arc ☃)
  {
    return a(☃, ☃, 3);
  }
  
  public void a(cj ☃, arc ☃, arc ☃, int ☃)
  {
    for (int ☃ = 0; ☃ < this.u.size(); ☃++) {
      ((ahv)this.u.get(☃)).a(this, ☃, ☃, ☃, ☃);
    }
  }
  
  public void c(cj ☃, ajt ☃)
  {
    if (this.x.t() != ahy.g) {
      d(☃, ☃);
    }
  }
  
  public void a(int ☃, int ☃, int ☃, int ☃)
  {
    if (☃ > ☃)
    {
      int ☃ = ☃;
      ☃ = ☃;
      ☃ = ☃;
    }
    if (!this.s.m()) {
      for (int ☃ = ☃; ☃ <= ☃; ☃++) {
        c(ahz.a, new cj(☃, ☃, ☃));
      }
    }
    b(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public void b(cj ☃, cj ☃)
  {
    b(☃.p(), ☃.q(), ☃.r(), ☃.p(), ☃.q(), ☃.r());
  }
  
  public void b(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    for (int ☃ = 0; ☃ < this.u.size(); ☃++) {
      ((ahv)this.u.get(☃)).a(☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
  
  public void d(cj ☃, ajt ☃)
  {
    e(☃.e(), ☃);
    e(☃.f(), ☃);
    e(☃.b(), ☃);
    e(☃.a(), ☃);
    e(☃.c(), ☃);
    e(☃.d(), ☃);
  }
  
  public void a(cj ☃, ajt ☃, cq ☃)
  {
    if (☃ != cq.e) {
      e(☃.e(), ☃);
    }
    if (☃ != cq.f) {
      e(☃.f(), ☃);
    }
    if (☃ != cq.a) {
      e(☃.b(), ☃);
    }
    if (☃ != cq.b) {
      e(☃.a(), ☃);
    }
    if (☃ != cq.c) {
      e(☃.c(), ☃);
    }
    if (☃ != cq.d) {
      e(☃.d(), ☃);
    }
  }
  
  public void e(cj ☃, final ajt ☃)
  {
    if (this.E) {
      return;
    }
    arc ☃ = o(☃);
    try
    {
      ☃.t().a(this, ☃, ☃, ☃);
    }
    catch (Throwable ☃)
    {
      b ☃ = b.a(☃, "Exception while updating neighbours");
      c ☃ = ☃.a("Block being updated");
      
      ☃.a("Source block type", new Callable()
      {
        public String a()
          throws Exception
        {
          try
          {
            return String.format("ID #%d (%s // %s)", new Object[] { Integer.valueOf(ajt.a(☃)), ☃.a(), ☃.getClass().getCanonicalName() });
          }
          catch (Throwable ☃) {}
          return "ID #" + ajt.a(☃);
        }
      });
      c.a(☃, ☃, ☃);
      
      throw new e(☃);
    }
  }
  
  public boolean a(cj ☃, ajt ☃)
  {
    return false;
  }
  
  public boolean h(cj ☃)
  {
    return f(☃).c(☃);
  }
  
  public boolean i(cj ☃)
  {
    if (☃.q() >= K()) {
      return h(☃);
    }
    cj ☃ = new cj(☃.p(), K(), ☃.r());
    if (!h(☃)) {
      return false;
    }
    ☃ = ☃.b();
    while (☃.q() > ☃.q())
    {
      arc ☃ = o(☃);
      if ((☃.c() > 0) && (!☃.a().d())) {
        return false;
      }
      ☃ = ☃.b();
    }
    return true;
  }
  
  public int j(cj ☃)
  {
    if (☃.q() < 0) {
      return 0;
    }
    if (☃.q() >= 256) {
      ☃ = new cj(☃.p(), 255, ☃.r());
    }
    return f(☃).a(☃, 0);
  }
  
  public int k(cj ☃)
  {
    return c(☃, true);
  }
  
  public int c(cj ☃, boolean ☃)
  {
    if ((☃.p() < -30000000) || (☃.r() < -30000000) || (☃.p() >= 30000000) || (☃.r() >= 30000000)) {
      return 15;
    }
    if ((☃) && (o(☃).f()))
    {
      int ☃ = c(☃.a(), false);
      int ☃ = c(☃.f(), false);
      int ☃ = c(☃.e(), false);
      int ☃ = c(☃.d(), false);
      int ☃ = c(☃.c(), false);
      if (☃ > ☃) {
        ☃ = ☃;
      }
      if (☃ > ☃) {
        ☃ = ☃;
      }
      if (☃ > ☃) {
        ☃ = ☃;
      }
      if (☃ > ☃) {
        ☃ = ☃;
      }
      return ☃;
    }
    if (☃.q() < 0) {
      return 0;
    }
    if (☃.q() >= 256) {
      ☃ = new cj(☃.p(), 255, ☃.r());
    }
    ase ☃ = f(☃);
    return ☃.a(☃, this.J);
  }
  
  public cj l(cj ☃)
  {
    int ☃;
    int ☃;
    if ((☃.p() < -30000000) || (☃.r() < -30000000) || (☃.p() >= 30000000) || (☃.r() >= 30000000))
    {
      ☃ = K() + 1;
    }
    else
    {
      int ☃;
      if (a(☃.p() >> 4, ☃.r() >> 4, true)) {
        ☃ = a(☃.p() >> 4, ☃.r() >> 4).b(☃.p() & 0xF, ☃.r() & 0xF);
      } else {
        ☃ = 0;
      }
    }
    return new cj(☃.p(), ☃, ☃.r());
  }
  
  public int b(int ☃, int ☃)
  {
    if ((☃ < -30000000) || (☃ < -30000000) || (☃ >= 30000000) || (☃ >= 30000000)) {
      return K() + 1;
    }
    if (!a(☃ >> 4, ☃ >> 4, true)) {
      return 0;
    }
    ase ☃ = a(☃ >> 4, ☃ >> 4);
    return ☃.w();
  }
  
  public int a(ahz ☃, cj ☃)
  {
    if ((this.s.m()) && (☃ == ahz.a)) {
      return 0;
    }
    if (☃.q() < 0) {
      ☃ = new cj(☃.p(), 0, ☃.r());
    }
    if (!a(☃)) {
      return ☃.c;
    }
    if (!e(☃)) {
      return ☃.c;
    }
    if (o(☃).f())
    {
      int ☃ = b(☃, ☃.a());
      int ☃ = b(☃, ☃.f());
      int ☃ = b(☃, ☃.e());
      int ☃ = b(☃, ☃.d());
      int ☃ = b(☃, ☃.c());
      if (☃ > ☃) {
        ☃ = ☃;
      }
      if (☃ > ☃) {
        ☃ = ☃;
      }
      if (☃ > ☃) {
        ☃ = ☃;
      }
      if (☃ > ☃) {
        ☃ = ☃;
      }
      return ☃;
    }
    ase ☃ = f(☃);
    return ☃.a(☃, ☃);
  }
  
  public int b(ahz ☃, cj ☃)
  {
    if (☃.q() < 0) {
      ☃ = new cj(☃.p(), 0, ☃.r());
    }
    if (!a(☃)) {
      return ☃.c;
    }
    if (!e(☃)) {
      return ☃.c;
    }
    ase ☃ = f(☃);
    return ☃.a(☃, ☃);
  }
  
  public void a(ahz ☃, cj ☃, int ☃)
  {
    if (!a(☃)) {
      return;
    }
    if (!e(☃)) {
      return;
    }
    ase ☃ = f(☃);
    ☃.a(☃, ☃, ☃);
    m(☃);
  }
  
  public void m(cj ☃)
  {
    for (int ☃ = 0; ☃ < this.u.size(); ☃++) {
      ((ahv)this.u.get(☃)).a(☃);
    }
  }
  
  public int b(cj ☃, int ☃)
  {
    int ☃ = a(ahz.a, ☃);
    int ☃ = a(ahz.b, ☃);
    if (☃ < ☃) {
      ☃ = ☃;
    }
    return ☃ << 20 | ☃ << 4;
  }
  
  public float n(cj ☃)
  {
    return this.s.n()[k(☃)];
  }
  
  public arc o(cj ☃)
  {
    if (!a(☃)) {
      return aju.a.u();
    }
    ase ☃ = f(☃);
    return ☃.a(☃);
  }
  
  public boolean B()
  {
    return this.J < 4;
  }
  
  public bbi a(bbj ☃, bbj ☃)
  {
    return a(☃, ☃, false, false, false);
  }
  
  public bbi a(bbj ☃, bbj ☃, boolean ☃)
  {
    return a(☃, ☃, ☃, false, false);
  }
  
  public bbi a(bbj ☃, bbj ☃, boolean ☃, boolean ☃, boolean ☃)
  {
    if ((Double.isNaN(☃.b)) || (Double.isNaN(☃.c)) || (Double.isNaN(☃.d))) {
      return null;
    }
    if ((Double.isNaN(☃.b)) || (Double.isNaN(☃.c)) || (Double.isNaN(☃.d))) {
      return null;
    }
    int ☃ = on.c(☃.b);
    int ☃ = on.c(☃.c);
    int ☃ = on.c(☃.d);
    
    int ☃ = on.c(☃.b);
    int ☃ = on.c(☃.c);
    int ☃ = on.c(☃.d);
    
    cj ☃ = new cj(☃, ☃, ☃);
    
    arc ☃ = o(☃);
    ajt ☃ = ☃.t();
    if ((!☃) || (☃.d(this, ☃) != ajt.k)) {
      if (☃.a(☃, ☃))
      {
        bbi ☃ = ☃.a(this, ☃, ☃, ☃);
        if (☃ != null) {
          return ☃;
        }
      }
    }
    bbi ☃ = null;
    
    int ☃ = 200;
    while (☃-- >= 0)
    {
      if ((Double.isNaN(☃.b)) || (Double.isNaN(☃.c)) || (Double.isNaN(☃.d))) {
        return null;
      }
      if ((☃ == ☃) && (☃ == ☃) && (☃ == ☃)) {
        return ☃ ? ☃ : null;
      }
      boolean ☃ = true;
      boolean ☃ = true;
      boolean ☃ = true;
      
      double ☃ = 999.0D;
      double ☃ = 999.0D;
      double ☃ = 999.0D;
      if (☃ > ☃) {
        ☃ = ☃ + 1.0D;
      } else if (☃ < ☃) {
        ☃ = ☃ + 0.0D;
      } else {
        ☃ = false;
      }
      if (☃ > ☃) {
        ☃ = ☃ + 1.0D;
      } else if (☃ < ☃) {
        ☃ = ☃ + 0.0D;
      } else {
        ☃ = false;
      }
      if (☃ > ☃) {
        ☃ = ☃ + 1.0D;
      } else if (☃ < ☃) {
        ☃ = ☃ + 0.0D;
      } else {
        ☃ = false;
      }
      double ☃ = 999.0D;
      double ☃ = 999.0D;
      double ☃ = 999.0D;
      
      double ☃ = ☃.b - ☃.b;
      double ☃ = ☃.c - ☃.c;
      double ☃ = ☃.d - ☃.d;
      if (☃) {
        ☃ = (☃ - ☃.b) / ☃;
      }
      if (☃) {
        ☃ = (☃ - ☃.c) / ☃;
      }
      if (☃) {
        ☃ = (☃ - ☃.d) / ☃;
      }
      if (☃ == -0.0D) {
        ☃ = -1.0E-4D;
      }
      if (☃ == -0.0D) {
        ☃ = -1.0E-4D;
      }
      if (☃ == -0.0D) {
        ☃ = -1.0E-4D;
      }
      cq ☃;
      if ((☃ < ☃) && (☃ < ☃))
      {
        cq ☃ = ☃ > ☃ ? cq.e : cq.f;
        ☃ = new bbj(☃, ☃.c + ☃ * ☃, ☃.d + ☃ * ☃);
      }
      else if (☃ < ☃)
      {
        cq ☃ = ☃ > ☃ ? cq.a : cq.b;
        ☃ = new bbj(☃.b + ☃ * ☃, ☃, ☃.d + ☃ * ☃);
      }
      else
      {
        ☃ = ☃ > ☃ ? cq.c : cq.d;
        ☃ = new bbj(☃.b + ☃ * ☃, ☃.c + ☃ * ☃, ☃);
      }
      ☃ = on.c(☃.b) - (☃ == cq.f ? 1 : 0);
      ☃ = on.c(☃.c) - (☃ == cq.b ? 1 : 0);
      ☃ = on.c(☃.d) - (☃ == cq.d ? 1 : 0);
      
      ☃ = new cj(☃, ☃, ☃);
      arc ☃ = o(☃);
      ajt ☃ = ☃.t();
      if ((!☃) || (☃.a() == axe.E) || (☃.d(this, ☃) != ajt.k)) {
        if (☃.a(☃, ☃))
        {
          bbi ☃ = ☃.a(this, ☃, ☃, ☃);
          if (☃ != null) {
            return ☃;
          }
        }
        else
        {
          ☃ = new bbi(bbi.a.a, ☃, ☃, ☃);
        }
      }
    }
    return ☃ ? ☃ : null;
  }
  
  public void a(zj ☃, cj ☃, nf ☃, nh ☃, float ☃, float ☃)
  {
    a(☃, ☃.p() + 0.5D, ☃.q() + 0.5D, ☃.r() + 0.5D, ☃, ☃, ☃, ☃);
  }
  
  public void a(zj ☃, double ☃, double ☃, double ☃, nf ☃, nh ☃, float ☃, float ☃)
  {
    for (int ☃ = 0; ☃ < this.u.size(); ☃++) {
      ((ahv)this.u.get(☃)).a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
  
  public void a(double ☃, double ☃, double ☃, nf ☃, nh ☃, float ☃, float ☃, boolean ☃) {}
  
  public void a(cj ☃, nf ☃)
  {
    for (int ☃ = 0; ☃ < this.u.size(); ☃++) {
      ((ahv)this.u.get(☃)).a(☃, ☃);
    }
  }
  
  public void a(cy ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
  {
    a(☃.c(), ☃.e(), ☃, ☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  public void a(cy ☃, boolean ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
  {
    a(☃.c(), ☃.e() | ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  private void a(int ☃, boolean ☃, double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, int... ☃)
  {
    for (int ☃ = 0; ☃ < this.u.size(); ☃++) {
      ((ahv)this.u.get(☃)).a(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
  }
  
  public boolean d(rr ☃)
  {
    this.j.add(☃);
    return true;
  }
  
  public boolean a(rr ☃)
  {
    int ☃ = on.c(☃.p / 16.0D);
    int ☃ = on.c(☃.r / 16.0D);
    
    boolean ☃ = ☃.k;
    if ((☃ instanceof zj)) {
      ☃ = true;
    }
    if ((☃) || (a(☃, ☃, false)))
    {
      if ((☃ instanceof zj))
      {
        zj ☃ = (zj)☃;
        this.i.add(☃);
        e();
      }
      a(☃, ☃).a(☃);
      this.e.add(☃);
      b(☃);
      return true;
    }
    return false;
  }
  
  protected void b(rr ☃)
  {
    for (int ☃ = 0; ☃ < this.u.size(); ☃++) {
      ((ahv)this.u.get(☃)).a(☃);
    }
  }
  
  protected void c(rr ☃)
  {
    for (int ☃ = 0; ☃ < this.u.size(); ☃++) {
      ((ahv)this.u.get(☃)).b(☃);
    }
  }
  
  public void e(rr ☃)
  {
    if (☃.aJ()) {
      ☃.az();
    }
    if (☃.aI()) {
      ☃.p();
    }
    ☃.T();
    if ((☃ instanceof zj))
    {
      this.i.remove(☃);
      e();
      c(☃);
    }
  }
  
  public void f(rr ☃)
  {
    ☃.b(false);
    ☃.T();
    if ((☃ instanceof zj))
    {
      this.i.remove(☃);
      e();
    }
    int ☃ = ☃.ab;
    int ☃ = ☃.ad;
    if ((☃.aa) && (a(☃, ☃, true))) {
      a(☃, ☃).b(☃);
    }
    this.e.remove(☃);
    c(☃);
  }
  
  public vg C()
  {
    return this.t;
  }
  
  public void a(ahv ☃)
  {
    this.u.add(☃);
  }
  
  public void b(ahv ☃)
  {
    this.u.remove(☃);
  }
  
  public List<bbh> a(rr ☃, bbh ☃)
  {
    List<bbh> ☃ = Lists.newArrayList();
    
    int ☃ = on.c(☃.a) - 1;
    int ☃ = on.f(☃.d) + 1;
    int ☃ = on.c(☃.b) - 1;
    int ☃ = on.f(☃.e) + 1;
    int ☃ = on.c(☃.c) - 1;
    int ☃ = on.f(☃.f) + 1;
    
    arv ☃ = aj();
    boolean ☃ = (☃ != null) && (☃.bo());
    boolean ☃ = (☃ != null) && (a(☃, ☃));
    
    arc ☃ = aju.b.u();
    
    cj.b ☃ = cj.b.s();
    for (int ☃ = ☃; ☃ < ☃; ☃++) {
      for (int ☃ = ☃; ☃ < ☃; ☃++)
      {
        int ☃ = ((☃ == ☃) || (☃ == ☃ - 1) ? 1 : 0) + ((☃ == ☃) || (☃ == ☃ - 1) ? 1 : 0);
        if (☃ != 2) {
          if (e(☃.d(☃, 64, ☃))) {
            for (int ☃ = ☃; ☃ < ☃; ☃++) {
              if ((☃ <= 0) || ((☃ != ☃) && (☃ != ☃ - 1)))
              {
                ☃.d(☃, ☃, ☃);
                if (☃ != null) {
                  if ((☃) && (☃)) {
                    ☃.j(false);
                  } else if ((!☃) && (!☃)) {
                    ☃.j(true);
                  }
                }
                arc ☃ = ☃;
                if ((☃.a(☃)) || (!☃)) {
                  ☃ = o(☃);
                }
                ☃.a(this, ☃, ☃, ☃, ☃);
              }
            }
          }
        }
      }
    }
    ☃.t();
    if (☃ != null)
    {
      List<rr> ☃ = b(☃, ☃.g(0.25D));
      for (int ☃ = 0; ☃ < ☃.size(); ☃++)
      {
        rr ☃ = (rr)☃.get(☃);
        if (!☃.x(☃))
        {
          bbh ☃ = ☃.af();
          if ((☃ != null) && (☃.b(☃))) {
            ☃.add(☃);
          }
          ☃ = ☃.j(☃);
          if ((☃ != null) && (☃.b(☃))) {
            ☃.add(☃);
          }
        }
      }
    }
    return ☃;
  }
  
  public boolean a(arv ☃, rr ☃)
  {
    double ☃ = ☃.b();
    double ☃ = ☃.c();
    double ☃ = ☃.d();
    double ☃ = ☃.e();
    if (☃.bo())
    {
      ☃ += 1.0D;
      ☃ += 1.0D;
      ☃ -= 1.0D;
      ☃ -= 1.0D;
    }
    else
    {
      ☃ -= 1.0D;
      ☃ -= 1.0D;
      ☃ += 1.0D;
      ☃ += 1.0D;
    }
    return (☃.p > ☃) && (☃.p < ☃) && (☃.r > ☃) && (☃.r < ☃);
  }
  
  public List<bbh> a(bbh ☃)
  {
    List<bbh> ☃ = Lists.newArrayList();
    
    int ☃ = on.c(☃.a) - 1;
    int ☃ = on.f(☃.d) + 1;
    int ☃ = on.c(☃.b) - 1;
    int ☃ = on.f(☃.e) + 1;
    int ☃ = on.c(☃.c) - 1;
    int ☃ = on.f(☃.f) + 1;
    
    cj.b ☃ = cj.b.s();
    for (int ☃ = ☃; ☃ < ☃; ☃++) {
      for (int ☃ = ☃; ☃ < ☃; ☃++)
      {
        int ☃ = ((☃ == ☃) || (☃ == ☃ - 1) ? 1 : 0) + ((☃ == ☃) || (☃ == ☃ - 1) ? 1 : 0);
        if (☃ != 2) {
          if (e(☃.d(☃, 64, ☃))) {
            for (int ☃ = ☃; ☃ < ☃; ☃++) {
              if ((☃ <= 0) || ((☃ != ☃) && (☃ != ☃ - 1)))
              {
                ☃.d(☃, ☃, ☃);
                arc ☃;
                arc ☃;
                if ((☃ < -30000000) || (☃ >= 30000000) || (☃ < -30000000) || (☃ >= 30000000)) {
                  ☃ = aju.h.u();
                } else {
                  ☃ = o(☃);
                }
                ☃.a(this, ☃, ☃, ☃, null);
              }
            }
          }
        }
      }
    }
    ☃.t();
    
    return ☃;
  }
  
  public boolean b(bbh ☃)
  {
    List<bbh> ☃ = Lists.newArrayList();
    
    int ☃ = on.c(☃.a) - 1;
    int ☃ = on.f(☃.d) + 1;
    int ☃ = on.c(☃.b) - 1;
    int ☃ = on.f(☃.e) + 1;
    int ☃ = on.c(☃.c) - 1;
    int ☃ = on.f(☃.f) + 1;
    
    cj.b ☃ = cj.b.s();
    try
    {
      for (int ☃ = ☃; ☃ < ☃; ☃++) {
        for (int ☃ = ☃; ☃ < ☃; ☃++)
        {
          int ☃ = ((☃ == ☃) || (☃ == ☃ - 1) ? 1 : 0) + ((☃ == ☃) || (☃ == ☃ - 1) ? 1 : 0);
          if (☃ != 2) {
            if (e(☃.d(☃, 64, ☃))) {
              for (int ☃ = ☃; ☃ < ☃; ☃++) {
                if ((☃ <= 0) || ((☃ != ☃) && (☃ != ☃ - 1)))
                {
                  ☃.d(☃, ☃, ☃);
                  if ((☃ < -30000000) || (☃ >= 30000000) || (☃ < -30000000) || (☃ >= 30000000)) {
                    return true;
                  }
                  arc ☃ = o(☃);
                  ☃.a(this, ☃, ☃, ☃, null);
                  if (!☃.isEmpty()) {
                    return true;
                  }
                }
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
    return false;
  }
  
  public int a(float ☃)
  {
    float ☃ = c(☃);
    float ☃ = 1.0F - (on.b(☃ * 6.2831855F) * 2.0F + 0.5F);
    ☃ = on.a(☃, 0.0F, 1.0F);
    ☃ = 1.0F - ☃;
    ☃ = (float)(☃ * (1.0D - j(☃) * 5.0F / 16.0D));
    ☃ = (float)(☃ * (1.0D - h(☃) * 5.0F / 16.0D));
    ☃ = 1.0F - ☃;
    return (int)(☃ * 11.0F);
  }
  
  public float b(float ☃)
  {
    float ☃ = c(☃);
    
    float ☃ = 1.0F - (on.b(☃ * 6.2831855F) * 2.0F + 0.2F);
    ☃ = on.a(☃, 0.0F, 1.0F);
    
    ☃ = 1.0F - ☃;
    
    ☃ = (float)(☃ * (1.0D - j(☃) * 5.0F / 16.0D));
    ☃ = (float)(☃ * (1.0D - h(☃) * 5.0F / 16.0D));
    
    return ☃ * 0.8F + 0.2F;
  }
  
  public bbj a(rr ☃, float ☃)
  {
    float ☃ = c(☃);
    
    float ☃ = on.b(☃ * 6.2831855F) * 2.0F + 0.5F;
    ☃ = on.a(☃, 0.0F, 1.0F);
    
    int ☃ = on.c(☃.p);
    int ☃ = on.c(☃.q);
    int ☃ = on.c(☃.r);
    cj ☃ = new cj(☃, ☃, ☃);
    aig ☃ = b(☃);
    float ☃ = ☃.a(☃);
    int ☃ = ☃.a(☃);
    
    float ☃ = (☃ >> 16 & 0xFF) / 255.0F;
    float ☃ = (☃ >> 8 & 0xFF) / 255.0F;
    float ☃ = (☃ & 0xFF) / 255.0F;
    ☃ *= ☃;
    ☃ *= ☃;
    ☃ *= ☃;
    
    float ☃ = j(☃);
    if (☃ > 0.0F)
    {
      float ☃ = (☃ * 0.3F + ☃ * 0.59F + ☃ * 0.11F) * 0.6F;
      
      float ☃ = 1.0F - ☃ * 0.75F;
      ☃ = ☃ * ☃ + ☃ * (1.0F - ☃);
      ☃ = ☃ * ☃ + ☃ * (1.0F - ☃);
      ☃ = ☃ * ☃ + ☃ * (1.0F - ☃);
    }
    float ☃ = h(☃);
    if (☃ > 0.0F)
    {
      float ☃ = (☃ * 0.3F + ☃ * 0.59F + ☃ * 0.11F) * 0.2F;
      
      float ☃ = 1.0F - ☃ * 0.75F;
      ☃ = ☃ * ☃ + ☃ * (1.0F - ☃);
      ☃ = ☃ * ☃ + ☃ * (1.0F - ☃);
      ☃ = ☃ * ☃ + ☃ * (1.0F - ☃);
    }
    if (this.K > 0)
    {
      float ☃ = this.K - ☃;
      if (☃ > 1.0F) {
        ☃ = 1.0F;
      }
      ☃ *= 0.45F;
      ☃ = ☃ * (1.0F - ☃) + 0.8F * ☃;
      ☃ = ☃ * (1.0F - ☃) + 0.8F * ☃;
      ☃ = ☃ * (1.0F - ☃) + 1.0F * ☃;
    }
    return new bbj(☃, ☃, ☃);
  }
  
  public float c(float ☃)
  {
    return this.s.a(this.x.f(), ☃);
  }
  
  public int D()
  {
    return this.s.a(this.x.f());
  }
  
  public float E()
  {
    return asv.a[this.s.a(this.x.f())];
  }
  
  public float d(float ☃)
  {
    float ☃ = c(☃);
    return ☃ * 6.2831855F;
  }
  
  public bbj e(float ☃)
  {
    float ☃ = c(☃);
    
    float ☃ = on.b(☃ * 6.2831855F) * 2.0F + 0.5F;
    ☃ = on.a(☃, 0.0F, 1.0F);
    
    float ☃ = (float)(this.I >> 16 & 0xFF) / 255.0F;
    float ☃ = (float)(this.I >> 8 & 0xFF) / 255.0F;
    float ☃ = (float)(this.I & 0xFF) / 255.0F;
    
    float ☃ = j(☃);
    if (☃ > 0.0F)
    {
      float ☃ = (☃ * 0.3F + ☃ * 0.59F + ☃ * 0.11F) * 0.6F;
      
      float ☃ = 1.0F - ☃ * 0.95F;
      ☃ = ☃ * ☃ + ☃ * (1.0F - ☃);
      ☃ = ☃ * ☃ + ☃ * (1.0F - ☃);
      ☃ = ☃ * ☃ + ☃ * (1.0F - ☃);
    }
    ☃ *= (☃ * 0.9F + 0.1F);
    ☃ *= (☃ * 0.9F + 0.1F);
    ☃ *= (☃ * 0.85F + 0.15F);
    
    float ☃ = h(☃);
    if (☃ > 0.0F)
    {
      float ☃ = (☃ * 0.3F + ☃ * 0.59F + ☃ * 0.11F) * 0.2F;
      
      float ☃ = 1.0F - ☃ * 0.95F;
      ☃ = ☃ * ☃ + ☃ * (1.0F - ☃);
      ☃ = ☃ * ☃ + ☃ * (1.0F - ☃);
      ☃ = ☃ * ☃ + ☃ * (1.0F - ☃);
    }
    return new bbj(☃, ☃, ☃);
  }
  
  public bbj f(float ☃)
  {
    float ☃ = c(☃);
    return this.s.b(☃, ☃);
  }
  
  public cj p(cj ☃)
  {
    return f(☃).f(☃);
  }
  
  public cj q(cj ☃)
  {
    ase ☃ = f(☃);
    
    cj ☃ = new cj(☃.p(), ☃.g() + 16, ☃.r());
    while (☃.q() >= 0)
    {
      cj ☃ = ☃.b();
      axe ☃ = ☃.a(☃).a();
      if ((☃.c()) && (☃ != axe.j)) {
        break;
      }
      ☃ = ☃;
    }
    return ☃;
  }
  
  public float g(float ☃)
  {
    float ☃ = c(☃);
    
    float ☃ = 1.0F - (on.b(☃ * 6.2831855F) * 2.0F + 0.25F);
    ☃ = on.a(☃, 0.0F, 1.0F);
    
    return ☃ * ☃ * 0.5F;
  }
  
  public boolean b(cj ☃, ajt ☃)
  {
    return true;
  }
  
  public void a(cj ☃, ajt ☃, int ☃) {}
  
  public void a(cj ☃, ajt ☃, int ☃, int ☃) {}
  
  public void b(cj ☃, ajt ☃, int ☃, int ☃) {}
  
  public void k()
  {
    this.C.a("entities");
    this.C.a("global");
    for (int ☃ = 0; ☃ < this.j.size(); ☃++)
    {
      rr ☃ = (rr)this.j.get(☃);
      try
      {
        ☃.T += 1;
        ☃.m();
      }
      catch (Throwable ☃)
      {
        b ☃ = b.a(☃, "Ticking entity");
        c ☃ = ☃.a("Entity being ticked");
        if (☃ == null) {
          ☃.a("Entity", "~~NULL~~");
        } else {
          ☃.a(☃);
        }
        throw new e(☃);
      }
      if (☃.F) {
        this.j.remove(☃--);
      }
    }
    this.C.c("remove");
    this.e.removeAll(this.f);
    for (int ☃ = 0; ☃ < this.f.size(); ☃++)
    {
      rr ☃ = (rr)this.f.get(☃);
      int ☃ = ☃.ab;
      int ☃ = ☃.ad;
      if ((☃.aa) && (a(☃, ☃, true))) {
        a(☃, ☃).b(☃);
      }
    }
    for (int ☃ = 0; ☃ < this.f.size(); ☃++) {
      c((rr)this.f.get(☃));
    }
    this.f.clear();
    
    l();
    
    this.C.c("regular");
    for (int ☃ = 0; ☃ < this.e.size(); ☃++)
    {
      rr ☃ = (rr)this.e.get(☃);
      rr ☃ = ☃.by();
      if (☃ != null)
      {
        if ((☃.F) || (!☃.w(☃))) {
          ☃.p();
        }
      }
      else
      {
        this.C.a("tick");
        if ((!☃.F) && (!(☃ instanceof lr))) {
          try
          {
            g(☃);
          }
          catch (Throwable ☃)
          {
            b ☃ = b.a(☃, "Ticking entity");
            c ☃ = ☃.a("Entity being ticked");
            
            ☃.a(☃);
            
            throw new e(☃);
          }
        }
        this.C.b();
        
        this.C.a("remove");
        if (☃.F)
        {
          int ☃ = ☃.ab;
          int ☃ = ☃.ad;
          if ((☃.aa) && (a(☃, ☃, true))) {
            a(☃, ☃).b(☃);
          }
          this.e.remove(☃--);
          c(☃);
        }
        this.C.b();
      }
    }
    this.C.c("blockEntities");
    this.M = true;
    Iterator<apv> ☃ = this.h.iterator();
    while (☃.hasNext())
    {
      apv ☃ = (apv)☃.next();
      if ((!☃.x()) && (☃.t()))
      {
        cj ☃ = ☃.v();
        if ((e(☃)) && (this.N.a(☃))) {
          try
          {
            this.C.a(☃.getClass().getSimpleName());
            ((ky)☃).c();
            this.C.b();
          }
          catch (Throwable ☃)
          {
            b ☃ = b.a(☃, "Ticking block entity");
            c ☃ = ☃.a("Block entity being ticked");
            
            ☃.a(☃);
            
            throw new e(☃);
          }
        }
      }
      if (☃.x())
      {
        ☃.remove();
        this.g.remove(☃);
        if (e(☃.v())) {
          f(☃.v()).d(☃.v());
        }
      }
    }
    this.M = false;
    if (!this.c.isEmpty())
    {
      this.h.removeAll(this.c);
      this.g.removeAll(this.c);
      this.c.clear();
    }
    this.C.c("pendingBlockEntities");
    if (!this.b.isEmpty())
    {
      for (int ☃ = 0; ☃ < this.b.size(); ☃++)
      {
        apv ☃ = (apv)this.b.get(☃);
        if (!☃.x())
        {
          if (!this.g.contains(☃)) {
            a(☃);
          }
          if (e(☃.v()))
          {
            ase ☃ = f(☃.v());
            arc ☃ = ☃.a(☃.v());
            ☃.a(☃.v(), ☃);
            a(☃.v(), ☃, ☃, 3);
          }
        }
      }
      this.b.clear();
    }
    this.C.b();
    this.C.b();
  }
  
  protected void l() {}
  
  public boolean a(apv ☃)
  {
    boolean ☃ = this.g.add(☃);
    if ((☃) && ((☃ instanceof ky))) {
      this.h.add(☃);
    }
    return ☃;
  }
  
  public void b(Collection<apv> ☃)
  {
    if (this.M) {
      this.b.addAll(☃);
    } else {
      for (apv ☃ : ☃) {
        a(☃);
      }
    }
  }
  
  public void g(rr ☃)
  {
    a(☃, true);
  }
  
  public void a(rr ☃, boolean ☃)
  {
    int ☃ = on.c(☃.p);
    int ☃ = on.c(☃.r);
    int ☃ = 32;
    if ((☃) && (!a(☃ - ☃, 0, ☃ - ☃, ☃ + ☃, 0, ☃ + ☃, true))) {
      return;
    }
    ☃.M = ☃.p;
    ☃.N = ☃.q;
    ☃.O = ☃.r;
    ☃.x = ☃.v;
    ☃.y = ☃.w;
    if ((☃) && (☃.aa))
    {
      ☃.T += 1;
      if (☃.aI()) {
        ☃.aw();
      } else {
        ☃.m();
      }
    }
    this.C.a("chunkCheck");
    if ((Double.isNaN(☃.p)) || (Double.isInfinite(☃.p))) {
      ☃.p = ☃.M;
    }
    if ((Double.isNaN(☃.q)) || (Double.isInfinite(☃.q))) {
      ☃.q = ☃.N;
    }
    if ((Double.isNaN(☃.r)) || (Double.isInfinite(☃.r))) {
      ☃.r = ☃.O;
    }
    if ((Double.isNaN(☃.w)) || (Double.isInfinite(☃.w))) {
      ☃.w = ☃.y;
    }
    if ((Double.isNaN(☃.v)) || (Double.isInfinite(☃.v))) {
      ☃.v = ☃.x;
    }
    int ☃ = on.c(☃.p / 16.0D);
    int ☃ = on.c(☃.q / 16.0D);
    int ☃ = on.c(☃.r / 16.0D);
    if ((!☃.aa) || (☃.ab != ☃) || (☃.ac != ☃) || (☃.ad != ☃))
    {
      if ((☃.aa) && (a(☃.ab, ☃.ad, true))) {
        a(☃.ab, ☃.ad).a(☃, ☃.ac);
      }
      if ((☃.bs()) || (a(☃, ☃, true))) {
        a(☃, ☃).a(☃);
      } else {
        ☃.aa = false;
      }
    }
    this.C.b();
    if ((☃) && (☃.aa)) {
      for (rr ☃ : ☃.bu()) {
        if ((☃.F) || (☃.by() != ☃)) {
          ☃.p();
        } else {
          g(☃);
        }
      }
    }
  }
  
  public boolean c(bbh ☃)
  {
    return a(☃, null);
  }
  
  public boolean a(bbh ☃, rr ☃)
  {
    List<rr> ☃ = b(null, ☃);
    for (int ☃ = 0; ☃ < ☃.size(); ☃++)
    {
      rr ☃ = (rr)☃.get(☃);
      if ((!☃.F) && (☃.i) && (☃ != ☃) && ((☃ == null) || (☃.x(☃)))) {
        return false;
      }
    }
    return true;
  }
  
  public boolean d(bbh ☃)
  {
    int ☃ = on.c(☃.a);
    int ☃ = on.f(☃.d);
    int ☃ = on.c(☃.b);
    int ☃ = on.f(☃.e);
    int ☃ = on.c(☃.c);
    int ☃ = on.f(☃.f);
    
    cj.b ☃ = cj.b.s();
    for (int ☃ = ☃; ☃ < ☃; ☃++) {
      for (int ☃ = ☃; ☃ < ☃; ☃++) {
        for (int ☃ = ☃; ☃ < ☃; ☃++)
        {
          arc ☃ = o(☃.d(☃, ☃, ☃));
          if (☃.a() != axe.a)
          {
            ☃.t();
            return true;
          }
        }
      }
    }
    ☃.t();
    return false;
  }
  
  public boolean e(bbh ☃)
  {
    int ☃ = on.c(☃.a);
    int ☃ = on.f(☃.d);
    int ☃ = on.c(☃.b);
    int ☃ = on.f(☃.e);
    int ☃ = on.c(☃.c);
    int ☃ = on.f(☃.f);
    
    cj.b ☃ = cj.b.s();
    for (int ☃ = ☃; ☃ < ☃; ☃++) {
      for (int ☃ = ☃; ☃ < ☃; ☃++) {
        for (int ☃ = ☃; ☃ < ☃; ☃++)
        {
          arc ☃ = o(☃.d(☃, ☃, ☃));
          if (☃.a().d())
          {
            ☃.t();
            return true;
          }
        }
      }
    }
    ☃.t();
    return false;
  }
  
  public boolean f(bbh ☃)
  {
    int ☃ = on.c(☃.a);
    int ☃ = on.f(☃.d);
    int ☃ = on.c(☃.b);
    int ☃ = on.f(☃.e);
    int ☃ = on.c(☃.c);
    int ☃ = on.f(☃.f);
    if (a(☃, ☃, ☃, ☃, ☃, ☃, true))
    {
      cj.b ☃ = cj.b.s();
      for (int ☃ = ☃; ☃ < ☃; ☃++) {
        for (int ☃ = ☃; ☃ < ☃; ☃++) {
          for (int ☃ = ☃; ☃ < ☃; ☃++)
          {
            ajt ☃ = o(☃.d(☃, ☃, ☃)).t();
            if ((☃ == aju.ab) || (☃ == aju.k) || (☃ == aju.l))
            {
              ☃.t();
              return true;
            }
          }
        }
      }
      ☃.t();
    }
    return false;
  }
  
  public boolean a(bbh ☃, axe ☃, rr ☃)
  {
    int ☃ = on.c(☃.a);
    int ☃ = on.f(☃.d);
    int ☃ = on.c(☃.b);
    int ☃ = on.f(☃.e);
    int ☃ = on.c(☃.c);
    int ☃ = on.f(☃.f);
    if (!a(☃, ☃, ☃, ☃, ☃, ☃, true)) {
      return false;
    }
    boolean ☃ = false;
    bbj ☃ = bbj.a;
    cj.b ☃ = cj.b.s();
    for (int ☃ = ☃; ☃ < ☃; ☃++) {
      for (int ☃ = ☃; ☃ < ☃; ☃++) {
        for (int ☃ = ☃; ☃ < ☃; ☃++)
        {
          ☃.d(☃, ☃, ☃);
          arc ☃ = o(☃);
          ajt ☃ = ☃.t();
          if (☃.a() == ☃)
          {
            double ☃ = ☃ + 1 - amo.e(((Integer)☃.c(amo.b)).intValue());
            if (☃ >= ☃)
            {
              ☃ = true;
              ☃ = ☃.a(this, ☃, ☃, ☃);
            }
          }
        }
      }
    }
    ☃.t();
    if ((☃.b() > 0.0D) && (☃.bd()))
    {
      ☃ = ☃.a();
      double ☃ = 0.014D;
      ☃.s += ☃.b * ☃;
      ☃.t += ☃.c * ☃;
      ☃.u += ☃.d * ☃;
    }
    return ☃;
  }
  
  public boolean a(bbh ☃, axe ☃)
  {
    int ☃ = on.c(☃.a);
    int ☃ = on.f(☃.d);
    int ☃ = on.c(☃.b);
    int ☃ = on.f(☃.e);
    int ☃ = on.c(☃.c);
    int ☃ = on.f(☃.f);
    
    cj.b ☃ = cj.b.s();
    for (int ☃ = ☃; ☃ < ☃; ☃++) {
      for (int ☃ = ☃; ☃ < ☃; ☃++) {
        for (int ☃ = ☃; ☃ < ☃; ☃++) {
          if (o(☃.d(☃, ☃, ☃)).a() == ☃)
          {
            ☃.t();
            return true;
          }
        }
      }
    }
    ☃.t();
    return false;
  }
  
  public boolean b(bbh ☃, axe ☃)
  {
    int ☃ = on.c(☃.a);
    int ☃ = on.f(☃.d);
    int ☃ = on.c(☃.b);
    int ☃ = on.f(☃.e);
    int ☃ = on.c(☃.c);
    int ☃ = on.f(☃.f);
    
    cj.b ☃ = cj.b.s();
    for (int ☃ = ☃; ☃ < ☃; ☃++) {
      for (int ☃ = ☃; ☃ < ☃; ☃++) {
        for (int ☃ = ☃; ☃ < ☃; ☃++)
        {
          arc ☃ = o(☃.d(☃, ☃, ☃));
          if (☃.a() == ☃)
          {
            int ☃ = ((Integer)☃.c(amo.b)).intValue();
            double ☃ = ☃ + 1;
            if (☃ < 8) {
              ☃ = ☃ + 1 - ☃ / 8.0D;
            }
            if (☃ >= ☃.b)
            {
              ☃.t();
              return true;
            }
          }
        }
      }
    }
    ☃.t();
    return false;
  }
  
  public ahp a(rr ☃, double ☃, double ☃, double ☃, float ☃, boolean ☃)
  {
    return a(☃, ☃, ☃, ☃, ☃, false, ☃);
  }
  
  public ahp a(rr ☃, double ☃, double ☃, double ☃, float ☃, boolean ☃, boolean ☃)
  {
    ahp ☃ = new ahp(this, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    ☃.a();
    ☃.a(true);
    return ☃;
  }
  
  public float a(bbj ☃, bbh ☃)
  {
    double ☃ = 1.0D / ((☃.d - ☃.a) * 2.0D + 1.0D);
    double ☃ = 1.0D / ((☃.e - ☃.b) * 2.0D + 1.0D);
    double ☃ = 1.0D / ((☃.f - ☃.c) * 2.0D + 1.0D);
    
    double ☃ = (1.0D - Math.floor(1.0D / ☃) * ☃) / 2.0D;
    double ☃ = (1.0D - Math.floor(1.0D / ☃) * ☃) / 2.0D;
    if ((☃ < 0.0D) || (☃ < 0.0D) || (☃ < 0.0D)) {
      return 0.0F;
    }
    int ☃ = 0;
    int ☃ = 0;
    for (float ☃ = 0.0F; ☃ <= 1.0F; ☃ = (float)(☃ + ☃)) {
      for (float ☃ = 0.0F; ☃ <= 1.0F; ☃ = (float)(☃ + ☃)) {
        for (float ☃ = 0.0F; ☃ <= 1.0F; ☃ = (float)(☃ + ☃))
        {
          double ☃ = ☃.a + (☃.d - ☃.a) * ☃;
          double ☃ = ☃.b + (☃.e - ☃.b) * ☃;
          double ☃ = ☃.c + (☃.f - ☃.c) * ☃;
          if (a(new bbj(☃ + ☃, ☃, ☃ + ☃), ☃) == null) {
            ☃++;
          }
          ☃++;
        }
      }
    }
    return ☃ / ☃;
  }
  
  public boolean a(zj ☃, cj ☃, cq ☃)
  {
    ☃ = ☃.a(☃);
    if (o(☃).t() == aju.ab)
    {
      a(☃, 1009, ☃, 0);
      g(☃);
      return true;
    }
    return false;
  }
  
  public String F()
  {
    return "All: " + this.e.size();
  }
  
  public String G()
  {
    return this.v.f();
  }
  
  public apv r(cj ☃)
  {
    if (!a(☃)) {
      return null;
    }
    apv ☃ = null;
    if (this.M) {
      for (int ☃ = 0; ☃ < this.b.size(); ☃++)
      {
        apv ☃ = (apv)this.b.get(☃);
        if ((!☃.x()) && (☃.v().equals(☃)))
        {
          ☃ = ☃;
          break;
        }
      }
    }
    if (☃ == null) {
      ☃ = f(☃).a(☃, ase.a.a);
    }
    if (☃ == null) {
      for (int ☃ = 0; ☃ < this.b.size(); ☃++)
      {
        apv ☃ = (apv)this.b.get(☃);
        if ((!☃.x()) && (☃.v().equals(☃)))
        {
          ☃ = ☃;
          break;
        }
      }
    }
    return ☃;
  }
  
  public void a(cj ☃, apv ☃)
  {
    if ((☃ != null) && (!☃.x())) {
      if (this.M)
      {
        ☃.a(☃);
        
        Iterator<apv> ☃ = this.b.iterator();
        while (☃.hasNext())
        {
          apv ☃ = (apv)☃.next();
          if (☃.v().equals(☃))
          {
            ☃.y();
            ☃.remove();
          }
        }
        this.b.add(☃);
      }
      else
      {
        a(☃);
        
        f(☃).a(☃, ☃);
      }
    }
  }
  
  public void s(cj ☃)
  {
    apv ☃ = r(☃);
    if ((☃ != null) && (this.M))
    {
      ☃.y();
      this.b.remove(☃);
    }
    else
    {
      if (☃ != null)
      {
        this.b.remove(☃);
        this.g.remove(☃);
        this.h.remove(☃);
      }
      f(☃).d(☃);
    }
  }
  
  public void b(apv ☃)
  {
    this.c.add(☃);
  }
  
  public boolean t(cj ☃)
  {
    bbh ☃ = o(☃).d(this, ☃);
    return (☃ != ajt.k) && (☃.a() >= 1.0D);
  }
  
  public boolean d(cj ☃, boolean ☃)
  {
    if (!a(☃)) {
      return ☃;
    }
    ase ☃ = this.v.b(☃.p() >> 4, ☃.r() >> 4);
    if ((☃ == null) || (☃.f())) {
      return ☃;
    }
    arc ☃ = o(☃);
    return (☃.a().k()) && (☃.h());
  }
  
  public void H()
  {
    int ☃ = a(1.0F);
    if (☃ != this.J) {
      this.J = ☃;
    }
  }
  
  public void a(boolean ☃, boolean ☃)
  {
    this.F = ☃;
    this.G = ☃;
  }
  
  public void d()
  {
    t();
  }
  
  protected void I()
  {
    if (this.x.o())
    {
      this.o = 1.0F;
      if (this.x.m()) {
        this.q = 1.0F;
      }
    }
  }
  
  protected void t()
  {
    if (this.s.m()) {
      return;
    }
    if (this.E) {
      return;
    }
    int ☃ = this.x.z();
    if (☃ > 0)
    {
      ☃--;
      this.x.i(☃);
      this.x.f(this.x.m() ? 1 : 2);
      this.x.g(this.x.o() ? 1 : 2);
    }
    int ☃ = this.x.n();
    if (☃ <= 0)
    {
      if (this.x.m()) {
        this.x.f(this.r.nextInt(12000) + 3600);
      } else {
        this.x.f(this.r.nextInt(168000) + 12000);
      }
    }
    else
    {
      ☃--;
      this.x.f(☃);
      if (☃ <= 0) {
        this.x.a(!this.x.m());
      }
    }
    this.p = this.q;
    if (this.x.m()) {
      this.q = ((float)(this.q + 0.01D));
    } else {
      this.q = ((float)(this.q - 0.01D));
    }
    this.q = on.a(this.q, 0.0F, 1.0F);
    
    int ☃ = this.x.p();
    if (☃ <= 0)
    {
      if (this.x.o()) {
        this.x.g(this.r.nextInt(12000) + 12000);
      } else {
        this.x.g(this.r.nextInt(168000) + 12000);
      }
    }
    else
    {
      ☃--;
      this.x.g(☃);
      if (☃ <= 0) {
        this.x.b(!this.x.o());
      }
    }
    this.n = this.o;
    if (this.x.o()) {
      this.o = ((float)(this.o + 0.01D));
    } else {
      this.o = ((float)(this.o - 0.01D));
    }
    this.o = on.a(this.o, 0.0F, 1.0F);
  }
  
  protected void a(int ☃, int ☃, ase ☃)
  {
    ☃.n();
  }
  
  protected void j() {}
  
  public void a(ajt ☃, cj ☃, Random ☃)
  {
    this.d = true;
    ☃.b(this, ☃, o(☃), ☃);
    this.d = false;
  }
  
  public boolean u(cj ☃)
  {
    return e(☃, false);
  }
  
  public boolean v(cj ☃)
  {
    return e(☃, true);
  }
  
  public boolean e(cj ☃, boolean ☃)
  {
    aig ☃ = b(☃);
    float ☃ = ☃.a(☃);
    if (☃ > 0.15F) {
      return false;
    }
    if ((☃.q() >= 0) && (☃.q() < 256) && (b(ahz.b, ☃) < 10))
    {
      arc ☃ = o(☃);
      ajt ☃ = ☃.t();
      if (((☃ == aju.j) || (☃ == aju.i)) && (((Integer)☃.c(amo.b)).intValue() == 0))
      {
        if (!☃) {
          return true;
        }
        boolean ☃ = (E(☃.e())) && (E(☃.f())) && (E(☃.c())) && (E(☃.d()));
        if (!☃) {
          return true;
        }
      }
    }
    return false;
  }
  
  private boolean E(cj ☃)
  {
    return o(☃).a() == axe.h;
  }
  
  public boolean f(cj ☃, boolean ☃)
  {
    aig ☃ = b(☃);
    float ☃ = ☃.a(☃);
    if (☃ > 0.15F) {
      return false;
    }
    if (!☃) {
      return true;
    }
    if ((☃.q() >= 0) && (☃.q() < 256) && (b(ahz.b, ☃) < 10))
    {
      arc ☃ = o(☃);
      if ((☃.a() == axe.a) && (aju.aH.a(this, ☃))) {
        return true;
      }
    }
    return false;
  }
  
  public boolean w(cj ☃)
  {
    boolean ☃ = false;
    if (!this.s.m()) {
      ☃ |= c(ahz.a, ☃);
    }
    ☃ |= c(ahz.b, ☃);
    return ☃;
  }
  
  int[] H = new int[32768];
  
  private int a(cj ☃, ahz ☃)
  {
    if ((☃ == ahz.a) && (h(☃))) {
      return 15;
    }
    arc ☃ = o(☃);
    int ☃ = ☃ == ahz.a ? 0 : ☃.d();
    int ☃ = ☃.c();
    if ((☃ >= 15) && (☃.d() > 0)) {
      ☃ = 1;
    }
    if (☃ < 1) {
      ☃ = 1;
    }
    if (☃ >= 15) {
      return 0;
    }
    if (☃ >= 14) {
      return ☃;
    }
    cj.b ☃ = cj.b.s();
    for (cq ☃ : cq.values())
    {
      ☃.h(☃).c(☃);
      int ☃ = b(☃, ☃) - ☃;
      if (☃ > ☃) {
        ☃ = ☃;
      }
      if (☃ >= 14) {
        return ☃;
      }
    }
    ☃.t();
    
    return ☃;
  }
  
  public boolean c(ahz ☃, cj ☃)
  {
    if (!a(☃, 17, false)) {
      return false;
    }
    int ☃ = 0;
    int ☃ = 0;
    
    this.C.a("getBrightness");
    int ☃ = b(☃, ☃);
    int ☃ = a(☃, ☃);
    int ☃ = ☃.p();
    int ☃ = ☃.q();
    int ☃ = ☃.r();
    if (☃ > ☃)
    {
      this.H[(☃++)] = 133152;
    }
    else if (☃ < ☃)
    {
      this.H[(☃++)] = (0x20820 | ☃ << 18);
      while (☃ < ☃)
      {
        int ☃ = this.H[(☃++)];
        int ☃ = (☃ & 0x3F) - 32 + ☃;
        int ☃ = (☃ >> 6 & 0x3F) - 32 + ☃;
        int ☃ = (☃ >> 12 & 0x3F) - 32 + ☃;
        int ☃ = ☃ >> 18 & 0xF;
        cj ☃ = new cj(☃, ☃, ☃);
        int ☃ = b(☃, ☃);
        if (☃ == ☃)
        {
          a(☃, ☃, 0);
          if (☃ > 0)
          {
            int ☃ = on.a(☃ - ☃);
            int ☃ = on.a(☃ - ☃);
            int ☃ = on.a(☃ - ☃);
            if (☃ + ☃ + ☃ < 17)
            {
              cj.b ☃ = cj.b.s();
              for (cq ☃ : cq.values())
              {
                int ☃ = ☃ + ☃.g();
                int ☃ = ☃ + ☃.h();
                int ☃ = ☃ + ☃.i();
                ☃.d(☃, ☃, ☃);
                int ☃ = Math.max(1, o(☃).c());
                
                ☃ = b(☃, ☃);
                if ((☃ == ☃ - ☃) && (☃ < this.H.length)) {
                  this.H[(☃++)] = (☃ - ☃ + 32 | ☃ - ☃ + 32 << 6 | ☃ - ☃ + 32 << 12 | ☃ - ☃ << 18);
                }
              }
              ☃.t();
            }
          }
        }
      }
      ☃ = 0;
    }
    this.C.b();
    
    this.C.a("checkedPosition < toCheckCount");
    while (☃ < ☃)
    {
      int ☃ = this.H[(☃++)];
      int ☃ = (☃ & 0x3F) - 32 + ☃;
      int ☃ = (☃ >> 6 & 0x3F) - 32 + ☃;
      int ☃ = (☃ >> 12 & 0x3F) - 32 + ☃;
      cj ☃ = new cj(☃, ☃, ☃);
      
      int ☃ = b(☃, ☃);
      int ☃ = a(☃, ☃);
      if (☃ != ☃)
      {
        a(☃, ☃, ☃);
        if (☃ > ☃)
        {
          int ☃ = Math.abs(☃ - ☃);
          int ☃ = Math.abs(☃ - ☃);
          int ☃ = Math.abs(☃ - ☃);
          boolean ☃ = ☃ < this.H.length - 6;
          if ((☃ + ☃ + ☃ < 17) && (☃))
          {
            if (b(☃, ☃.e()) < ☃) {
              this.H[(☃++)] = (☃ - 1 - ☃ + 32 + (☃ - ☃ + 32 << 6) + (☃ - ☃ + 32 << 12));
            }
            if (b(☃, ☃.f()) < ☃) {
              this.H[(☃++)] = (☃ + 1 - ☃ + 32 + (☃ - ☃ + 32 << 6) + (☃ - ☃ + 32 << 12));
            }
            if (b(☃, ☃.b()) < ☃) {
              this.H[(☃++)] = (☃ - ☃ + 32 + (☃ - 1 - ☃ + 32 << 6) + (☃ - ☃ + 32 << 12));
            }
            if (b(☃, ☃.a()) < ☃) {
              this.H[(☃++)] = (☃ - ☃ + 32 + (☃ + 1 - ☃ + 32 << 6) + (☃ - ☃ + 32 << 12));
            }
            if (b(☃, ☃.c()) < ☃) {
              this.H[(☃++)] = (☃ - ☃ + 32 + (☃ - ☃ + 32 << 6) + (☃ - 1 - ☃ + 32 << 12));
            }
            if (b(☃, ☃.d()) < ☃) {
              this.H[(☃++)] = (☃ - ☃ + 32 + (☃ - ☃ + 32 << 6) + (☃ + 1 - ☃ + 32 << 12));
            }
          }
        }
      }
    }
    this.C.b();
    
    return true;
  }
  
  public boolean a(boolean ☃)
  {
    return false;
  }
  
  public List<aie> a(ase ☃, boolean ☃)
  {
    return null;
  }
  
  public List<aie> a(avp ☃, boolean ☃)
  {
    return null;
  }
  
  public List<rr> b(rr ☃, bbh ☃)
  {
    return a(☃, ☃, rv.e);
  }
  
  public List<rr> a(rr ☃, bbh ☃, Predicate<? super rr> ☃)
  {
    List<rr> ☃ = Lists.newArrayList();
    int ☃ = on.c((☃.a - 2.0D) / 16.0D);
    int ☃ = on.c((☃.d + 2.0D) / 16.0D);
    int ☃ = on.c((☃.c - 2.0D) / 16.0D);
    int ☃ = on.c((☃.f + 2.0D) / 16.0D);
    for (int ☃ = ☃; ☃ <= ☃; ☃++) {
      for (int ☃ = ☃; ☃ <= ☃; ☃++) {
        if (a(☃, ☃, true)) {
          a(☃, ☃).a(☃, ☃, ☃, ☃);
        }
      }
    }
    return ☃;
  }
  
  public <T extends rr> List<T> a(Class<? extends T> ☃, Predicate<? super T> ☃)
  {
    List<T> ☃ = Lists.newArrayList();
    for (rr ☃ : this.e) {
      if ((☃.isAssignableFrom(☃.getClass())) && (☃.apply(☃))) {
        ☃.add(☃);
      }
    }
    return ☃;
  }
  
  public <T extends rr> List<T> b(Class<? extends T> ☃, Predicate<? super T> ☃)
  {
    List<T> ☃ = Lists.newArrayList();
    for (rr ☃ : this.i) {
      if ((☃.isAssignableFrom(☃.getClass())) && (☃.apply(☃))) {
        ☃.add(☃);
      }
    }
    return ☃;
  }
  
  public <T extends rr> List<T> a(Class<? extends T> ☃, bbh ☃)
  {
    return a(☃, ☃, rv.e);
  }
  
  public <T extends rr> List<T> a(Class<? extends T> ☃, bbh ☃, Predicate<? super T> ☃)
  {
    int ☃ = on.c((☃.a - 2.0D) / 16.0D);
    int ☃ = on.f((☃.d + 2.0D) / 16.0D);
    int ☃ = on.c((☃.c - 2.0D) / 16.0D);
    int ☃ = on.f((☃.f + 2.0D) / 16.0D);
    List<T> ☃ = Lists.newArrayList();
    for (int ☃ = ☃; ☃ < ☃; ☃++) {
      for (int ☃ = ☃; ☃ < ☃; ☃++) {
        if (a(☃, ☃, true)) {
          a(☃, ☃).a(☃, ☃, ☃, ☃);
        }
      }
    }
    return ☃;
  }
  
  public <T extends rr> T a(Class<? extends T> ☃, bbh ☃, T ☃)
  {
    List<T> ☃ = a(☃, ☃);
    T ☃ = null;
    double ☃ = Double.MAX_VALUE;
    for (int ☃ = 0; ☃ < ☃.size(); ☃++)
    {
      T ☃ = (rr)☃.get(☃);
      if (☃ != ☃) {
        if (rv.e.apply(☃))
        {
          double ☃ = ☃.h(☃);
          if (☃ <= ☃)
          {
            ☃ = ☃;
            ☃ = ☃;
          }
        }
      }
    }
    return ☃;
  }
  
  public rr a(int ☃)
  {
    return (rr)this.k.a(☃);
  }
  
  public List<rr> J()
  {
    return this.e;
  }
  
  public void b(cj ☃, apv ☃)
  {
    if (e(☃)) {
      f(☃).e();
    }
  }
  
  public int a(Class<?> ☃)
  {
    int ☃ = 0;
    for (rr ☃ : this.e) {
      if ((!(☃ instanceof sb)) || (!((sb)☃).cN())) {
        if (☃.isAssignableFrom(☃.getClass())) {
          ☃++;
        }
      }
    }
    return ☃;
  }
  
  public void a(Collection<rr> ☃)
  {
    this.e.addAll(☃);
    for (rr ☃ : ☃) {
      b(☃);
    }
  }
  
  public void c(Collection<rr> ☃)
  {
    this.f.addAll(☃);
  }
  
  public boolean a(ajt ☃, cj ☃, boolean ☃, cq ☃, rr ☃, adq ☃)
  {
    arc ☃ = o(☃);
    
    bbh ☃ = ☃ ? null : ☃.u().d(this, ☃);
    if ((☃ != ajt.k) && (!a(☃.a(☃), ☃))) {
      return false;
    }
    if ((☃.a() == axe.q) && (☃ == aju.cf)) {
      return true;
    }
    return (☃.a().j()) && (☃.a(this, ☃, ☃, ☃));
  }
  
  public int K()
  {
    return this.a;
  }
  
  public void b(int ☃)
  {
    this.a = ☃;
  }
  
  public int a(cj ☃, cq ☃)
  {
    return o(☃).b(this, ☃, ☃);
  }
  
  public ahy L()
  {
    return this.x.t();
  }
  
  public int x(cj ☃)
  {
    int ☃ = 0;
    ☃ = Math.max(☃, a(☃.b(), cq.a));
    if (☃ >= 15) {
      return ☃;
    }
    ☃ = Math.max(☃, a(☃.a(), cq.b));
    if (☃ >= 15) {
      return ☃;
    }
    ☃ = Math.max(☃, a(☃.c(), cq.c));
    if (☃ >= 15) {
      return ☃;
    }
    ☃ = Math.max(☃, a(☃.d(), cq.d));
    if (☃ >= 15) {
      return ☃;
    }
    ☃ = Math.max(☃, a(☃.e(), cq.e));
    if (☃ >= 15) {
      return ☃;
    }
    ☃ = Math.max(☃, a(☃.f(), cq.f));
    if (☃ >= 15) {
      return ☃;
    }
    return ☃;
  }
  
  public boolean b(cj ☃, cq ☃)
  {
    return c(☃, ☃) > 0;
  }
  
  public int c(cj ☃, cq ☃)
  {
    arc ☃ = o(☃);
    if (☃.l()) {
      return x(☃);
    }
    return ☃.a(this, ☃, ☃);
  }
  
  public boolean y(cj ☃)
  {
    if (c(☃.b(), cq.a) > 0) {
      return true;
    }
    if (c(☃.a(), cq.b) > 0) {
      return true;
    }
    if (c(☃.c(), cq.c) > 0) {
      return true;
    }
    if (c(☃.d(), cq.d) > 0) {
      return true;
    }
    if (c(☃.e(), cq.e) > 0) {
      return true;
    }
    if (c(☃.f(), cq.f) > 0) {
      return true;
    }
    return false;
  }
  
  public int z(cj ☃)
  {
    int ☃ = 0;
    for (cq ☃ : cq.values())
    {
      int ☃ = c(☃.a(☃), ☃);
      if (☃ >= 15) {
        return 15;
      }
      if (☃ > ☃) {
        ☃ = ☃;
      }
    }
    return ☃;
  }
  
  public zj a(rr ☃, double ☃)
  {
    return a(☃.p, ☃.q, ☃.r, ☃, false);
  }
  
  public zj b(rr ☃, double ☃)
  {
    return a(☃.p, ☃.q, ☃.r, ☃, true);
  }
  
  public zj a(double ☃, double ☃, double ☃, double ☃, boolean ☃)
  {
    double ☃ = -1.0D;
    zj ☃ = null;
    for (int ☃ = 0; ☃ < this.i.size(); ☃++)
    {
      zj ☃ = (zj)this.i.get(☃);
      if ((rv.d.apply(☃)) || (!☃)) {
        if ((rv.e.apply(☃)) || (☃))
        {
          double ☃ = ☃.e(☃, ☃, ☃);
          if (((☃ < 0.0D) || (☃ < ☃ * ☃)) && ((☃ == -1.0D) || (☃ < ☃)))
          {
            ☃ = ☃;
            ☃ = ☃;
          }
        }
      }
    }
    return ☃;
  }
  
  public boolean a(double ☃, double ☃, double ☃, double ☃)
  {
    for (int ☃ = 0; ☃ < this.i.size(); ☃++)
    {
      zj ☃ = (zj)this.i.get(☃);
      if (rv.e.apply(☃))
      {
        double ☃ = ☃.e(☃, ☃, ☃);
        if ((☃ < 0.0D) || (☃ < ☃ * ☃)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public zj a(rr ☃, double ☃, double ☃)
  {
    return a(☃.p, ☃.q, ☃.r, ☃, ☃, null, null);
  }
  
  public zj a(cj ☃, double ☃, double ☃)
  {
    return a(☃.p() + 0.5F, ☃.q() + 0.5F, ☃.r() + 0.5F, ☃, ☃, null, null);
  }
  
  public zj a(double ☃, double ☃, double ☃, double ☃, double ☃, Function<zj, Double> ☃, Predicate<zj> ☃)
  {
    double ☃ = -1.0D;
    zj ☃ = null;
    for (int ☃ = 0; ☃ < this.i.size(); ☃++)
    {
      zj ☃ = (zj)this.i.get(☃);
      if ((!☃.bJ.a) && (☃.au()) && (!☃.y()) && ((☃ == null) || (☃.apply(☃))))
      {
        double ☃ = ☃.e(☃, ☃.q, ☃);
        double ☃ = ☃;
        if (☃.aK()) {
          ☃ *= 0.800000011920929D;
        }
        if (☃.aN())
        {
          float ☃ = ☃.cG();
          if (☃ < 0.1F) {
            ☃ = 0.1F;
          }
          ☃ *= 0.7F * ☃;
        }
        if (☃ != null) {
          ☃ *= ((Double)Objects.firstNonNull(☃.apply(☃), Double.valueOf(1.0D))).doubleValue();
        }
        if (((☃ < 0.0D) || (Math.abs(☃.q - ☃) < ☃ * ☃)) && ((☃ < 0.0D) || (☃ < ☃ * ☃)) && ((☃ == -1.0D) || (☃ < ☃)))
        {
          ☃ = ☃;
          ☃ = ☃;
        }
      }
    }
    return ☃;
  }
  
  public zj a(String ☃)
  {
    for (int ☃ = 0; ☃ < this.i.size(); ☃++)
    {
      zj ☃ = (zj)this.i.get(☃);
      if (☃.equals(☃.h_())) {
        return ☃;
      }
    }
    return null;
  }
  
  public zj b(UUID ☃)
  {
    for (int ☃ = 0; ☃ < this.i.size(); ☃++)
    {
      zj ☃ = (zj)this.i.get(☃);
      if (☃.equals(☃.bc())) {
        return ☃;
      }
    }
    return null;
  }
  
  public void M() {}
  
  public void N()
    throws ahu
  {
    this.w.c();
  }
  
  public void a(long ☃)
  {
    this.x.b(☃);
  }
  
  public long O()
  {
    return this.x.a();
  }
  
  public long P()
  {
    return this.x.e();
  }
  
  public long Q()
  {
    return this.x.f();
  }
  
  public void b(long ☃)
  {
    this.x.c(☃);
  }
  
  public cj R()
  {
    cj ☃ = new cj(this.x.b(), this.x.c(), this.x.d());
    if (!aj().a(☃)) {
      ☃ = l(new cj(aj().f(), 0.0D, aj().g()));
    }
    return ☃;
  }
  
  public void A(cj ☃)
  {
    this.x.a(☃);
  }
  
  public void h(rr ☃)
  {
    int ☃ = on.c(☃.p / 16.0D);
    int ☃ = on.c(☃.r / 16.0D);
    
    int ☃ = 2;
    for (int ☃ = -☃; ☃ <= ☃; ☃++) {
      for (int ☃ = -☃; ☃ <= ☃; ☃++) {
        a(☃ + ☃, ☃ + ☃);
      }
    }
    if (!this.e.contains(☃)) {
      this.e.add(☃);
    }
  }
  
  public boolean a(zj ☃, cj ☃)
  {
    return true;
  }
  
  public void a(rr ☃, byte ☃) {}
  
  public arz z()
  {
    return this.v;
  }
  
  public void c(cj ☃, ajt ☃, int ☃, int ☃)
  {
    ☃.a(this, ☃, o(☃), ☃, ☃);
  }
  
  public azi S()
  {
    return this.w;
  }
  
  public azh T()
  {
    return this.x;
  }
  
  public ahr U()
  {
    return this.x.w();
  }
  
  public void e() {}
  
  public float h(float ☃)
  {
    return (this.p + (this.q - this.p) * ☃) * j(☃);
  }
  
  public void i(float ☃)
  {
    this.p = ☃;
    this.q = ☃;
  }
  
  public float j(float ☃)
  {
    return this.n + (this.o - this.n) * ☃;
  }
  
  public void k(float ☃)
  {
    this.n = ☃;
    this.o = ☃;
  }
  
  public boolean V()
  {
    return h(1.0F) > 0.9D;
  }
  
  public boolean W()
  {
    return j(1.0F) > 0.2D;
  }
  
  public boolean B(cj ☃)
  {
    if (!W()) {
      return false;
    }
    if (!h(☃)) {
      return false;
    }
    if (p(☃).q() > ☃.q()) {
      return false;
    }
    aig ☃ = b(☃);
    if (☃.c()) {
      return false;
    }
    if (f(☃, false)) {
      return false;
    }
    return ☃.d();
  }
  
  public boolean C(cj ☃)
  {
    aig ☃ = b(☃);
    return ☃.e();
  }
  
  public azs X()
  {
    return this.z;
  }
  
  public void a(String ☃, ayx ☃)
  {
    this.z.a(☃, ☃);
  }
  
  public ayx a(Class<? extends ayx> ☃, String ☃)
  {
    return this.z.a(☃, ☃);
  }
  
  public int b(String ☃)
  {
    return this.z.a(☃);
  }
  
  public void a(int ☃, cj ☃, int ☃)
  {
    for (int ☃ = 0; ☃ < this.u.size(); ☃++) {
      ((ahv)this.u.get(☃)).a(☃, ☃, ☃);
    }
  }
  
  public void b(int ☃, cj ☃, int ☃)
  {
    a(null, ☃, ☃, ☃);
  }
  
  public void a(zj ☃, int ☃, cj ☃, int ☃)
  {
    try
    {
      for (int ☃ = 0; ☃ < this.u.size(); ☃++) {
        ((ahv)this.u.get(☃)).a(☃, ☃, ☃, ☃);
      }
    }
    catch (Throwable ☃)
    {
      b ☃ = b.a(☃, "Playing level event");
      c ☃ = ☃.a("Level event being played");
      
      ☃.a("Block coordinates", c.a(☃));
      ☃.a("Event source", ☃);
      ☃.a("Event type", Integer.valueOf(☃));
      ☃.a("Event data", Integer.valueOf(☃));
      
      throw new e(☃);
    }
  }
  
  public int Y()
  {
    return 256;
  }
  
  public int Z()
  {
    return this.s.m() ? 128 : 256;
  }
  
  public Random a(int ☃, int ☃, int ☃)
  {
    long ☃ = ☃ * 341873128712L + ☃ * 132897987541L + T().a() + ☃;
    this.r.setSeed(☃);
    return this.r;
  }
  
  public boolean aa()
  {
    return false;
  }
  
  public double ab()
  {
    if (this.x.t() == ahy.c) {
      return 0.0D;
    }
    return 63.0D;
  }
  
  public c a(b ☃)
  {
    c ☃ = ☃.a("Affected level", 1);
    
    ☃.a("Level name", this.x == null ? "????" : this.x.j());
    
    ☃.a("All players", new Callable()
    {
      public String a()
      {
        return aht.this.i.size() + " total; " + aht.this.i.toString();
      }
    });
    ☃.a("Chunk stats", new Callable()
    {
      public String a()
      {
        return aht.this.v.f();
      }
    });
    try
    {
      this.x.a(☃);
    }
    catch (Throwable ☃)
    {
      ☃.a("Level Data Unobtainable", ☃);
    }
    return ☃;
  }
  
  public void c(int ☃, cj ☃, int ☃)
  {
    for (int ☃ = 0; ☃ < this.u.size(); ☃++)
    {
      ahv ☃ = (ahv)this.u.get(☃);
      ☃.b(☃, ☃, ☃);
    }
  }
  
  public Calendar ac()
  {
    if (P() % 600L == 0L) {
      this.L.setTimeInMillis(MinecraftServer.av());
    }
    return this.L;
  }
  
  public void a(double ☃, double ☃, double ☃, double ☃, double ☃, double ☃, dn ☃) {}
  
  public bbp ad()
  {
    return this.D;
  }
  
  public void f(cj ☃, ajt ☃)
  {
    for (cq ☃ : cq.c.a)
    {
      cj ☃ = ☃.a(☃);
      if (e(☃))
      {
        arc ☃ = o(☃);
        if (aju.cj.C(☃))
        {
          ☃.t().a(this, ☃, ☃, ☃);
        }
        else if (☃.l())
        {
          ☃ = ☃.a(☃);
          ☃ = o(☃);
          if (aju.cj.C(☃)) {
            ☃.t().a(this, ☃, ☃, ☃);
          }
        }
      }
    }
  }
  
  public ql D(cj ☃)
  {
    long ☃ = 0L;
    float ☃ = 0.0F;
    if (e(☃))
    {
      ☃ = E();
      ☃ = f(☃).x();
    }
    return new ql(ae(), Q(), ☃, ☃);
  }
  
  public qk ae()
  {
    return T().x();
  }
  
  public int af()
  {
    return this.J;
  }
  
  public void c(int ☃)
  {
    this.J = ☃;
  }
  
  public int ag()
  {
    return this.K;
  }
  
  public void d(int ☃)
  {
    this.K = ☃;
  }
  
  public vr ai()
  {
    return this.A;
  }
  
  public arv aj()
  {
    return this.N;
  }
  
  public boolean c(int ☃, int ☃)
  {
    cj ☃ = R();
    int ☃ = ☃ * 16 + 8 - ☃.p();
    int ☃ = ☃ * 16 + 8 - ☃.r();
    int ☃ = 128;
    return (☃ >= -☃) && (☃ <= ☃) && (☃ >= -☃) && (☃ <= ☃);
  }
  
  public void a(ff<?> ☃)
  {
    throw new UnsupportedOperationException("Can't send packets to server unless you're on the client.");
  }
  
  public bab ak()
  {
    return this.B;
  }
}
