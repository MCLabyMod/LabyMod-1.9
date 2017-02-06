import com.google.common.collect.Lists;
import java.util.List;

public class tu
  extends tk
{
  private sh a;
  private double b;
  private ayp c;
  private vo d;
  private boolean e;
  private List<vo> f = Lists.newArrayList();
  
  public tu(sh ☃, double ☃, boolean ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.e = ☃;
    a(1);
    if (!(☃.x() instanceof ve)) {
      throw new IllegalArgumentException("Unsupported mob for MoveThroughVillageGoal");
    }
  }
  
  public boolean a()
  {
    f();
    if ((this.e) && (this.a.l.B())) {
      return false;
    }
    vp ☃ = this.a.l.ai().a(new cj(this.a), 0);
    if (☃ == null) {
      return false;
    }
    this.d = a(☃);
    if (this.d == null) {
      return false;
    }
    ve ☃ = (ve)this.a.x();
    boolean ☃ = ☃.f();
    ☃.a(false);
    this.c = ☃.a(this.d.d());
    ☃.a(☃);
    if (this.c != null) {
      return true;
    }
    bbj ☃ = vm.a(this.a, 10, 7, new bbj(this.d.d().p(), this.d.d().q(), this.d.d().r()));
    if (☃ == null) {
      return false;
    }
    ☃.a(false);
    this.c = this.a.x().a(☃.b, ☃.c, ☃.d);
    ☃.a(☃);
    return this.c != null;
  }
  
  public boolean b()
  {
    if (this.a.x().n()) {
      return false;
    }
    float ☃ = this.a.G + 4.0F;
    return this.a.c(this.d.d()) > ☃ * ☃;
  }
  
  public void c()
  {
    this.a.x().a(this.c, this.b);
  }
  
  public void d()
  {
    if ((this.a.x().n()) || (this.a.c(this.d.d()) < 16.0D)) {
      this.f.add(this.d);
    }
  }
  
  private vo a(vp ☃)
  {
    vo ☃ = null;
    int ☃ = Integer.MAX_VALUE;
    List<vo> ☃ = ☃.f();
    for (vo ☃ : ☃)
    {
      int ☃ = ☃.b(on.c(this.a.p), on.c(this.a.q), on.c(this.a.r));
      if (☃ < ☃) {
        if (!a(☃))
        {
          ☃ = ☃;
          ☃ = ☃;
        }
      }
    }
    return ☃;
  }
  
  private boolean a(vo ☃)
  {
    for (vo ☃ : this.f) {
      if (☃.d().equals(☃.d())) {
        return true;
      }
    }
    return false;
  }
  
  private void f()
  {
    if (this.f.size() > 15) {
      this.f.remove(0);
    }
  }
}
