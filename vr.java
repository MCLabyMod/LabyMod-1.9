import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public class vr
  extends ayx
{
  private aht b;
  private final List<cj> c = Lists.newArrayList();
  private final List<vo> d = Lists.newArrayList();
  private final List<vp> e = Lists.newArrayList();
  private int f;
  
  public vr(String ☃)
  {
    super(☃);
  }
  
  public vr(aht ☃)
  {
    super(a(☃.s));
    this.b = ☃;
    c();
  }
  
  public void a(aht ☃)
  {
    this.b = ☃;
    for (vp ☃ : this.e) {
      ☃.a(☃);
    }
  }
  
  public void a(cj ☃)
  {
    if (this.c.size() > 64) {
      return;
    }
    if (!e(☃)) {
      this.c.add(☃);
    }
  }
  
  public void a()
  {
    this.f += 1;
    for (vp ☃ : this.e) {
      ☃.a(this.f);
    }
    e();
    f();
    g();
    if (this.f % 400 == 0) {
      c();
    }
  }
  
  private void e()
  {
    for (Iterator<vp> ☃ = this.e.iterator(); ☃.hasNext();)
    {
      vp ☃ = (vp)☃.next();
      if (☃.g())
      {
        ☃.remove();
        c();
      }
    }
  }
  
  public List<vp> b()
  {
    return this.e;
  }
  
  public vp a(cj ☃, int ☃)
  {
    vp ☃ = null;
    double ☃ = 3.4028234663852886E38D;
    for (vp ☃ : this.e)
    {
      double ☃ = ☃.a().k(☃);
      if (☃ < ☃)
      {
        float ☃ = ☃ + ☃.b();
        if (☃ <= ☃ * ☃)
        {
          ☃ = ☃;
          ☃ = ☃;
        }
      }
    }
    return ☃;
  }
  
  private void f()
  {
    if (this.c.isEmpty()) {
      return;
    }
    b((cj)this.c.remove(0));
  }
  
  private void g()
  {
    for (int ☃ = 0; ☃ < this.d.size(); ☃++)
    {
      vo ☃ = (vo)this.d.get(☃);
      vp ☃ = a(☃.d(), 32);
      if (☃ == null)
      {
        ☃ = new vp(this.b);
        this.e.add(☃);
        c();
      }
      ☃.a(☃);
    }
    this.d.clear();
  }
  
  private void b(cj ☃)
  {
    int ☃ = 16;int ☃ = 4;int ☃ = 16;
    for (int ☃ = -☃; ☃ < ☃; ☃++) {
      for (int ☃ = -☃; ☃ < ☃; ☃++) {
        for (int ☃ = -☃; ☃ < ☃; ☃++)
        {
          cj ☃ = ☃.a(☃, ☃, ☃);
          if (f(☃))
          {
            vo ☃ = c(☃);
            if (☃ == null) {
              d(☃);
            } else {
              ☃.a(this.f);
            }
          }
        }
      }
    }
  }
  
  private vo c(cj ☃)
  {
    for (vo ☃ : this.d) {
      if ((☃.d().p() == ☃.p()) && (☃.d().r() == ☃.r()) && (Math.abs(☃.d().q() - ☃.q()) <= 1)) {
        return ☃;
      }
    }
    for (vp ☃ : this.e)
    {
      vo ☃ = ☃.e(☃);
      if (☃ != null) {
        return ☃;
      }
    }
    return null;
  }
  
  private void d(cj ☃)
  {
    cq ☃ = akv.f(this.b, ☃);
    cq ☃ = ☃.d();
    
    int ☃ = a(☃, ☃, 5);
    int ☃ = a(☃, ☃, ☃ + 1);
    if (☃ != ☃) {
      this.d.add(new vo(☃, ☃ < ☃ ? ☃ : ☃, this.f));
    }
  }
  
  private int a(cj ☃, cq ☃, int ☃)
  {
    int ☃ = 0;
    for (int ☃ = 1; ☃ <= 5; ☃++) {
      if (this.b.h(☃.a(☃, ☃)))
      {
        ☃++;
        if (☃ >= ☃) {
          return ☃;
        }
      }
    }
    return ☃;
  }
  
  private boolean e(cj ☃)
  {
    for (cj ☃ : this.c) {
      if (☃.equals(☃)) {
        return true;
      }
    }
    return false;
  }
  
  private boolean f(cj ☃)
  {
    arc ☃ = this.b.o(☃);
    ajt ☃ = ☃.t();
    if ((☃ instanceof akv)) {
      return ☃.a() == axe.d;
    }
    return false;
  }
  
  public void a(dn ☃)
  {
    this.f = ☃.h("Tick");
    du ☃ = ☃.c("Villages", 10);
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      dn ☃ = ☃.b(☃);
      vp ☃ = new vp();
      ☃.a(☃);
      this.e.add(☃);
    }
  }
  
  public void b(dn ☃)
  {
    ☃.a("Tick", this.f);
    du ☃ = new du();
    for (vp ☃ : this.e)
    {
      dn ☃ = new dn();
      ☃.b(☃);
      ☃.a(☃);
    }
    ☃.a("Villages", ☃);
  }
  
  public static String a(asv ☃)
  {
    return "villages" + ☃.p().c();
  }
}
