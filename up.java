import com.google.common.collect.Sets;
import java.util.Set;

public class up
  extends tk
{
  private sh a;
  private double b;
  private double c;
  private double d;
  private double e;
  private double f;
  private double g;
  private zj h;
  private int i;
  private boolean j;
  private Set<ado> k;
  private boolean l;
  
  public up(sh ☃, double ☃, ado ☃, boolean ☃)
  {
    this(☃, ☃, ☃, Sets.newHashSet(new ado[] { ☃ }));
  }
  
  public up(sh ☃, double ☃, boolean ☃, Set<ado> ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.k = ☃;
    this.l = ☃;
    a(3);
    if (!(☃.x() instanceof ve)) {
      throw new IllegalArgumentException("Unsupported mob type for TemptGoal");
    }
  }
  
  public boolean a()
  {
    if (this.i > 0)
    {
      this.i -= 1;
      return false;
    }
    this.h = this.a.l.a(this.a, 10.0D);
    if (this.h == null) {
      return false;
    }
    return (a(this.h.cb())) || (a(this.h.cc()));
  }
  
  protected boolean a(adq ☃)
  {
    if (☃ == null) {
      return false;
    }
    return this.k.contains(☃.b());
  }
  
  public boolean b()
  {
    if (this.l)
    {
      if (this.a.h(this.h) < 36.0D)
      {
        if (this.h.e(this.c, this.d, this.e) > 0.010000000000000002D) {
          return false;
        }
        if ((Math.abs(this.h.w - this.f) > 5.0D) || (Math.abs(this.h.v - this.g) > 5.0D)) {
          return false;
        }
      }
      else
      {
        this.c = this.h.p;
        this.d = this.h.q;
        this.e = this.h.r;
      }
      this.f = this.h.w;
      this.g = this.h.v;
    }
    return a();
  }
  
  public void c()
  {
    this.c = this.h.p;
    this.d = this.h.q;
    this.e = this.h.r;
    this.j = true;
  }
  
  public void d()
  {
    this.h = null;
    this.a.x().o();
    this.i = 100;
    this.j = false;
  }
  
  public void e()
  {
    this.a.t().a(this.h, this.a.cE() + 20, this.a.N());
    if (this.a.h(this.h) < 6.25D) {
      this.a.x().o();
    } else {
      this.a.x().a(this.h, this.b);
    }
  }
  
  public boolean f()
  {
    return this.j;
  }
}
