import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.List;

public class ta<T extends rr>
  extends tk
{
  private final Predicate<rr> c = new Predicate()
  {
    public boolean a(rr ☃)
    {
      return (☃.au()) && (ta.this.a.y().a(☃));
    }
  };
  protected sh a;
  private double d;
  private double e;
  protected T b;
  private float f;
  private ayp g;
  private vf h;
  private Class<T> i;
  private Predicate<? super T> j;
  
  public ta(sh ☃, Class<T> ☃, float ☃, double ☃, double ☃)
  {
    this(☃, ☃, Predicates.alwaysTrue(), ☃, ☃, ☃);
  }
  
  public ta(sh ☃, Class<T> ☃, Predicate<? super T> ☃, float ☃, double ☃, double ☃)
  {
    this.a = ☃;
    this.i = ☃;
    this.j = ☃;
    this.f = ☃;
    this.d = ☃;
    this.e = ☃;
    this.h = ☃.x();
    a(1);
  }
  
  public boolean a()
  {
    List<T> ☃ = this.a.l.a(this.i, this.a.bl().b(this.f, 3.0D, this.f), Predicates.and(new Predicate[] { rv.d, this.c, this.j }));
    if (☃.isEmpty()) {
      return false;
    }
    this.b = ((rr)☃.get(0));
    
    bbj ☃ = vm.b(this.a, 16, 7, new bbj(this.b.p, this.b.q, this.b.r));
    if (☃ == null) {
      return false;
    }
    if (this.b.e(☃.b, ☃.c, ☃.d) < this.b.h(this.a)) {
      return false;
    }
    this.g = this.h.a(☃.b, ☃.c, ☃.d);
    if (this.g == null) {
      return false;
    }
    if (!this.g.b(☃)) {
      return false;
    }
    return true;
  }
  
  public boolean b()
  {
    return !this.h.n();
  }
  
  public void c()
  {
    this.h.a(this.g, this.d);
  }
  
  public void d()
  {
    this.b = null;
  }
  
  public void e()
  {
    if (this.a.h(this.b) < 49.0D) {
      this.a.x().a(this.e);
    } else {
      this.a.x().a(this.d);
    }
  }
}
