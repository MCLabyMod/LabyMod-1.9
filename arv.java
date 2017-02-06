import com.google.common.collect.Lists;
import java.util.List;

public class arv
{
  private final List<art> a = Lists.newArrayList();
  private double b = 0.0D;
  private double c = 0.0D;
  private double d = 6.0E7D;
  private double e = this.d;
  private long f;
  private long g;
  private int h = 29999984;
  private double i = 0.2D;
  private double j = 5.0D;
  private int k = 15;
  private int l = 5;
  
  public boolean a(cj ☃)
  {
    return (☃.p() + 1 > b()) && (☃.p() < d()) && (☃.r() + 1 > c()) && (☃.r() < e());
  }
  
  public boolean a(ahn ☃)
  {
    return (☃.e() > b()) && (☃.c() < d()) && (☃.f() > c()) && (☃.d() < e());
  }
  
  public boolean a(bbh ☃)
  {
    return (☃.d > b()) && (☃.a < d()) && (☃.f > c()) && (☃.c < e());
  }
  
  public double a(rr ☃)
  {
    return b(☃.p, ☃.r);
  }
  
  public double b(double ☃, double ☃)
  {
    double ☃ = ☃ - c();
    double ☃ = e() - ☃;
    double ☃ = ☃ - b();
    double ☃ = d() - ☃;
    double ☃ = Math.min(☃, ☃);
    ☃ = Math.min(☃, ☃);
    return Math.min(☃, ☃);
  }
  
  public aru a()
  {
    if (this.e < this.d) {
      return aru.b;
    }
    if (this.e > this.d) {
      return aru.a;
    }
    return aru.c;
  }
  
  public double b()
  {
    double ☃ = f() - h() / 2.0D;
    if (☃ < -this.h) {
      ☃ = -this.h;
    }
    return ☃;
  }
  
  public double c()
  {
    double ☃ = g() - h() / 2.0D;
    if (☃ < -this.h) {
      ☃ = -this.h;
    }
    return ☃;
  }
  
  public double d()
  {
    double ☃ = f() + h() / 2.0D;
    if (☃ > this.h) {
      ☃ = this.h;
    }
    return ☃;
  }
  
  public double e()
  {
    double ☃ = g() + h() / 2.0D;
    if (☃ > this.h) {
      ☃ = this.h;
    }
    return ☃;
  }
  
  public double f()
  {
    return this.b;
  }
  
  public double g()
  {
    return this.c;
  }
  
  public void c(double ☃, double ☃)
  {
    this.b = ☃;
    this.c = ☃;
    for (art ☃ : k()) {
      ☃.a(this, ☃, ☃);
    }
  }
  
  public double h()
  {
    if (a() != aru.c)
    {
      double ☃ = (float)(System.currentTimeMillis() - this.g) / (float)(this.f - this.g);
      if (☃ >= 1.0D) {
        a(this.e);
      } else {
        return this.d + (this.e - this.d) * ☃;
      }
    }
    return this.d;
  }
  
  public long i()
  {
    if (a() != aru.c) {
      return this.f - System.currentTimeMillis();
    }
    return 0L;
  }
  
  public double j()
  {
    return this.e;
  }
  
  public void a(double ☃)
  {
    this.d = ☃;
    this.e = ☃;
    this.f = System.currentTimeMillis();
    this.g = this.f;
    for (art ☃ : k()) {
      ☃.a(this, ☃);
    }
  }
  
  public void a(double ☃, double ☃, long ☃)
  {
    this.d = ☃;
    this.e = ☃;
    this.g = System.currentTimeMillis();
    this.f = (this.g + ☃);
    for (art ☃ : k()) {
      ☃.a(this, ☃, ☃, ☃);
    }
  }
  
  protected List<art> k()
  {
    return Lists.newArrayList(this.a);
  }
  
  public void a(art ☃)
  {
    this.a.add(☃);
  }
  
  public void a(int ☃)
  {
    this.h = ☃;
  }
  
  public int l()
  {
    return this.h;
  }
  
  public double m()
  {
    return this.j;
  }
  
  public void b(double ☃)
  {
    this.j = ☃;
    for (art ☃ : k()) {
      ☃.c(this, ☃);
    }
  }
  
  public double n()
  {
    return this.i;
  }
  
  public void c(double ☃)
  {
    this.i = ☃;
    for (art ☃ : k()) {
      ☃.b(this, ☃);
    }
  }
  
  public double o()
  {
    if (this.f == this.g) {
      return 0.0D;
    }
    return Math.abs(this.d - this.e) / (this.f - this.g);
  }
  
  public int p()
  {
    return this.k;
  }
  
  public void b(int ☃)
  {
    this.k = ☃;
    for (art ☃ : k()) {
      ☃.a(this, ☃);
    }
  }
  
  public int q()
  {
    return this.l;
  }
  
  public void c(int ☃)
  {
    this.l = ☃;
    for (art ☃ : k()) {
      ☃.b(this, ☃);
    }
  }
}
