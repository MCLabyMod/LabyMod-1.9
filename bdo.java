import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import java.util.List;

public class bdo
  extends bdl
{
  private final List<bdo.d> u = Lists.newArrayList();
  private final oh<bcv> v = new oh();
  private final List<bdd> w = Lists.newArrayList();
  private final bdo.f[][] x;
  private int y;
  private bdo.b z;
  private bcv A;
  
  public bdo(bcf ☃, int ☃, int ☃, int ☃, int ☃, int ☃, bdo.b ☃, bdo.f[]... ☃)
  {
    super(☃, ☃, ☃, ☃, ☃, ☃);
    this.z = ☃;
    this.x = ☃;
    this.k = false;
    
    s();
    t();
  }
  
  private void s()
  {
    for (bdo.f[] ☃ : this.x) {
      for (int ☃ = 0; ☃ < ☃.length; ☃ += 2)
      {
        bdo.f ☃ = ☃[☃];
        bdo.f ☃ = ☃ < ☃.length - 1 ? ☃[(☃ + 1)] : null;
        bcv ☃ = a(☃, 0, ☃ == null);
        bcv ☃ = a(☃, 160, ☃ == null);
        bdo.d ☃ = new bdo.d(☃, ☃);
        this.u.add(☃);
        if ((☃ != null) && (☃ != null))
        {
          this.v.a(☃.b(), ☃);
          if ((☃ instanceof bdd)) {
            this.w.add((bdd)☃);
          }
        }
        if ((☃ != null) && (☃ != null))
        {
          this.v.a(☃.b(), ☃);
          if ((☃ instanceof bdd)) {
            this.w.add((bdd)☃);
          }
        }
      }
    }
  }
  
  private void t()
  {
    this.u.clear();
    for (int ☃ = 0; ☃ < this.x[this.y].length; ☃ += 2)
    {
      bdo.f ☃ = this.x[this.y][☃];
      bdo.f ☃ = ☃ < this.x[this.y].length - 1 ? this.x[this.y][(☃ + 1)] : null;
      bcv ☃ = (bcv)this.v.a(☃.b());
      bcv ☃ = ☃ != null ? (bcv)this.v.a(☃.b()) : null;
      bdo.d ☃ = new bdo.d(☃, ☃);
      this.u.add(☃);
    }
  }
  
  public void c(int ☃)
  {
    if (☃ == this.y) {
      return;
    }
    int ☃ = this.y;
    this.y = ☃;
    t();
    e(☃, ☃);
    this.n = 0.0F;
  }
  
  public int e()
  {
    return this.y;
  }
  
  public int f()
  {
    return this.x.length;
  }
  
  public bcv g()
  {
    return this.A;
  }
  
  public void h()
  {
    if (this.y > 0) {
      c(this.y - 1);
    }
  }
  
  public void i()
  {
    if (this.y < this.x.length - 1) {
      c(this.y + 1);
    }
  }
  
  public bcv d(int ☃)
  {
    return (bcv)this.v.a(☃);
  }
  
  private void e(int ☃, int ☃)
  {
    for (bdo.f ☃ : this.x[☃]) {
      if (☃ != null) {
        a((bcv)this.v.a(☃.b()), false);
      }
    }
    for (bdo.f ☃ : this.x[☃]) {
      if (☃ != null) {
        a((bcv)this.v.a(☃.b()), true);
      }
    }
  }
  
  private void a(bcv ☃, boolean ☃)
  {
    if ((☃ instanceof bcz)) {
      ((bcz)☃).m = ☃;
    } else if ((☃ instanceof bdd)) {
      ((bdd)☃).e(☃);
    } else if ((☃ instanceof bdf)) {
      ((bdf)☃).j = ☃;
    }
  }
  
  private bcv a(bdo.f ☃, int ☃, boolean ☃)
  {
    if ((☃ instanceof bdo.g)) {
      return a(this.b / 2 - 155 + ☃, 0, (bdo.g)☃);
    }
    if ((☃ instanceof bdo.a)) {
      return a(this.b / 2 - 155 + ☃, 0, (bdo.a)☃);
    }
    if ((☃ instanceof bdo.c)) {
      return a(this.b / 2 - 155 + ☃, 0, (bdo.c)☃);
    }
    if ((☃ instanceof bdo.e)) {
      return a(this.b / 2 - 155 + ☃, 0, (bdo.e)☃, ☃);
    }
    return null;
  }
  
  public void a(boolean ☃)
  {
    for (bdo.d ☃ : this.u)
    {
      if ((bdo.d.a(☃) instanceof bcz)) {
        ((bcz)bdo.d.a(☃)).l = ☃;
      }
      if ((bdo.d.b(☃) instanceof bcz)) {
        ((bcz)bdo.d.b(☃)).l = ☃;
      }
    }
  }
  
  public boolean b(int ☃, int ☃, int ☃)
  {
    boolean ☃ = super.b(☃, ☃, ☃);
    int ☃ = c(☃, ☃);
    if (☃ >= 0)
    {
      bdo.d ☃ = e(☃);
      if ((this.A != bdo.d.c(☃)) && (this.A != null) && ((this.A instanceof bdd))) {
        ((bdd)this.A).b(false);
      }
      this.A = bdo.d.c(☃);
    }
    return ☃;
  }
  
  private bde a(int ☃, int ☃, bdo.g ☃)
  {
    bde ☃ = new bde(this.z, ☃.b(), ☃, ☃, ☃.c(), ☃.e(), ☃.f(), ☃.g(), ☃.a());
    ☃.m = ☃.d();
    return ☃;
  }
  
  private bdj a(int ☃, int ☃, bdo.a ☃)
  {
    bdj ☃ = new bdj(this.z, ☃.b(), ☃, ☃, ☃.c(), ☃.a());
    ☃.m = ☃.d();
    return ☃;
  }
  
  private bdd a(int ☃, int ☃, bdo.c ☃)
  {
    bdd ☃ = new bdd(☃.b(), this.a.k, ☃, ☃, 150, 20);
    ☃.a(☃.c());
    ☃.a(this.z);
    ☃.e(☃.d());
    ☃.a(☃.a());
    return ☃;
  }
  
  private bdf a(int ☃, int ☃, bdo.e ☃, boolean ☃)
  {
    bdf ☃;
    bdf ☃;
    if (☃) {
      ☃ = new bdf(this.a.k, ☃.b(), ☃, ☃, this.b - ☃ * 2, 20, -1);
    } else {
      ☃ = new bdf(this.a.k, ☃.b(), ☃, ☃, 150, 20, -1);
    }
    ☃.j = ☃.d();
    ☃.a(☃.c());
    ☃.a();
    return ☃;
  }
  
  public void a(char ☃, int ☃)
  {
    if (!(this.A instanceof bdd)) {
      return;
    }
    bdd ☃ = (bdd)this.A;
    if (bfb.e(☃))
    {
      String ☃ = bfb.o();
      String[] ☃ = ☃.split(";");
      int ☃ = this.w.indexOf(this.A);
      int ☃ = ☃;
      for (String ☃ : ☃)
      {
        ((bdd)this.w.get(☃)).a(☃);
        if (☃ == this.w.size() - 1) {
          ☃ = 0;
        } else {
          ☃++;
        }
        if (☃ == ☃) {
          break;
        }
      }
      return;
    }
    if (☃ == 15)
    {
      ☃.b(false);
      int ☃ = this.w.indexOf(this.A);
      if (bfb.r())
      {
        if (☃ == 0) {
          ☃ = this.w.size() - 1;
        } else {
          ☃--;
        }
      }
      else if (☃ == this.w.size() - 1) {
        ☃ = 0;
      } else {
        ☃++;
      }
      this.A = ((bcv)this.w.get(☃));
      ☃ = (bdd)this.A;
      ☃.b(true);
      int ☃ = ☃.f + this.h;
      int ☃ = ☃.f;
      if (☃ > this.e) {
        this.n += ☃ - this.e;
      } else if (☃ < this.d) {
        this.n = ☃;
      }
    }
    else
    {
      ☃.a(☃, ☃);
    }
  }
  
  public bdo.d e(int ☃)
  {
    return (bdo.d)this.u.get(☃);
  }
  
  public int b()
  {
    return this.u.size();
  }
  
  public int c()
  {
    return 400;
  }
  
  protected int d()
  {
    return super.d() + 32;
  }
  
  public static class d
    implements bdl.a
  {
    private final bcf a;
    private final bcv b;
    private final bcv c;
    private bcv d;
    
    public d(bcv ☃, bcv ☃)
    {
      this.a = bcf.z();
      this.b = ☃;
      this.c = ☃;
    }
    
    public bcv a()
    {
      return this.b;
    }
    
    public bcv b()
    {
      return this.c;
    }
    
    public void a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, int ☃, boolean ☃)
    {
      a(this.b, ☃, ☃, ☃, false);
      a(this.c, ☃, ☃, ☃, false);
    }
    
    private void a(bcv ☃, int ☃, int ☃, int ☃, boolean ☃)
    {
      if (☃ == null) {
        return;
      }
      if ((☃ instanceof bcz)) {
        a((bcz)☃, ☃, ☃, ☃, ☃);
      } else if ((☃ instanceof bdd)) {
        a((bdd)☃, ☃, ☃);
      } else if ((☃ instanceof bdf)) {
        a((bdf)☃, ☃, ☃, ☃, ☃);
      }
    }
    
    private void a(bcz ☃, int ☃, int ☃, int ☃, boolean ☃)
    {
      ☃.i = ☃;
      if (!☃) {
        ☃.a(this.a, ☃, ☃);
      }
    }
    
    private void a(bdd ☃, int ☃, boolean ☃)
    {
      ☃.f = ☃;
      if (!☃) {
        ☃.g();
      }
    }
    
    private void a(bdf ☃, int ☃, int ☃, int ☃, boolean ☃)
    {
      ☃.h = ☃;
      if (!☃) {
        ☃.a(this.a, ☃, ☃);
      }
    }
    
    public void a(int ☃, int ☃, int ☃)
    {
      a(this.b, ☃, 0, 0, true);
      a(this.c, ☃, 0, 0, true);
    }
    
    public boolean a(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
    {
      boolean ☃ = a(this.b, ☃, ☃, ☃);
      boolean ☃ = a(this.c, ☃, ☃, ☃);
      return (☃) || (☃);
    }
    
    private boolean a(bcv ☃, int ☃, int ☃, int ☃)
    {
      if (☃ == null) {
        return false;
      }
      if ((☃ instanceof bcz)) {
        return a((bcz)☃, ☃, ☃, ☃);
      }
      if ((☃ instanceof bdd)) {
        a((bdd)☃, ☃, ☃, ☃);
      }
      return false;
    }
    
    private boolean a(bcz ☃, int ☃, int ☃, int ☃)
    {
      boolean ☃ = ☃.c(this.a, ☃, ☃);
      if (☃) {
        this.d = ☃;
      }
      return ☃;
    }
    
    private void a(bdd ☃, int ☃, int ☃, int ☃)
    {
      ☃.a(☃, ☃, ☃);
      if (☃.m()) {
        this.d = ☃;
      }
    }
    
    public void b(int ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
    {
      b(this.b, ☃, ☃, ☃);
      b(this.c, ☃, ☃, ☃);
    }
    
    private void b(bcv ☃, int ☃, int ☃, int ☃)
    {
      if (☃ == null) {
        return;
      }
      if ((☃ instanceof bcz)) {
        b((bcz)☃, ☃, ☃, ☃);
      }
    }
    
    private void b(bcz ☃, int ☃, int ☃, int ☃)
    {
      ☃.a(☃, ☃);
    }
  }
  
  public static class f
  {
    private final int a;
    private final String b;
    private final boolean c;
    
    public f(int ☃, String ☃, boolean ☃)
    {
      this.a = ☃;
      this.b = ☃;
      this.c = ☃;
    }
    
    public int b()
    {
      return this.a;
    }
    
    public String c()
    {
      return this.b;
    }
    
    public boolean d()
    {
      return this.c;
    }
  }
  
  public static class g
    extends bdo.f
  {
    private final bde.a a;
    private final float b;
    private final float c;
    private final float d;
    
    public g(int ☃, String ☃, boolean ☃, bde.a ☃, float ☃, float ☃, float ☃)
    {
      super(☃, ☃);
      this.a = ☃;
      this.b = ☃;
      this.c = ☃;
      this.d = ☃;
    }
    
    public bde.a a()
    {
      return this.a;
    }
    
    public float e()
    {
      return this.b;
    }
    
    public float f()
    {
      return this.c;
    }
    
    public float g()
    {
      return this.d;
    }
  }
  
  public static class a
    extends bdo.f
  {
    private final boolean a;
    
    public a(int ☃, String ☃, boolean ☃, boolean ☃)
    {
      super(☃, ☃);
      this.a = ☃;
    }
    
    public boolean a()
    {
      return this.a;
    }
  }
  
  public static class c
    extends bdo.f
  {
    private final Predicate<String> a;
    
    public c(int ☃, String ☃, boolean ☃, Predicate<String> ☃)
    {
      super(☃, ☃);
      this.a = ((Predicate)Objects.firstNonNull(☃, Predicates.alwaysTrue()));
    }
    
    public Predicate<String> a()
    {
      return this.a;
    }
  }
  
  public static class e
    extends bdo.f
  {
    public e(int ☃, String ☃, boolean ☃)
    {
      super(☃, ☃);
    }
  }
  
  public static abstract interface b
  {
    public abstract void a(int paramInt, boolean paramBoolean);
    
    public abstract void a(int paramInt, float paramFloat);
    
    public abstract void a(int paramInt, String paramString);
  }
}
