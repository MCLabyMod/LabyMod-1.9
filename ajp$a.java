import com.google.common.collect.Lists;
import java.util.List;

public class ajp$a
{
  private final aht b;
  private final cj c;
  private final ajp d;
  private arc e;
  private final boolean f;
  private final List<cj> g = Lists.newArrayList();
  
  public ajp$a(ajp arg1, aht ☃, cj ☃, arc ☃)
  {
    this.b = ☃;
    this.c = ☃;
    this.e = ☃;
    this.d = ((ajp)☃.t());
    ajp.b ☃ = (ajp.b)☃.c(this.d.g());
    this.f = this.d.c;
    a(☃);
  }
  
  public List<cj> a()
  {
    return this.g;
  }
  
  private void a(ajp.b ☃)
  {
    this.g.clear();
    switch (ajp.1.a[☃.ordinal()])
    {
    case 1: 
      this.g.add(this.c.c());
      this.g.add(this.c.d());
      break;
    case 2: 
      this.g.add(this.c.e());
      this.g.add(this.c.f());
      break;
    case 3: 
      this.g.add(this.c.e());
      this.g.add(this.c.f().a());
      break;
    case 4: 
      this.g.add(this.c.e().a());
      this.g.add(this.c.f());
      break;
    case 5: 
      this.g.add(this.c.c().a());
      this.g.add(this.c.d());
      break;
    case 6: 
      this.g.add(this.c.c());
      this.g.add(this.c.d().a());
      break;
    case 7: 
      this.g.add(this.c.f());
      this.g.add(this.c.d());
      break;
    case 8: 
      this.g.add(this.c.e());
      this.g.add(this.c.d());
      break;
    case 9: 
      this.g.add(this.c.e());
      this.g.add(this.c.c());
      break;
    case 10: 
      this.g.add(this.c.f());
      this.g.add(this.c.c());
    }
  }
  
  private void d()
  {
    for (int ☃ = 0; ☃ < this.g.size(); ☃++)
    {
      a ☃ = b((cj)this.g.get(☃));
      if ((☃ == null) || (!☃.a(this))) {
        this.g.remove(☃--);
      } else {
        this.g.set(☃, ☃.c);
      }
    }
  }
  
  private boolean a(cj ☃)
  {
    return (ajp.b(this.b, ☃)) || (ajp.b(this.b, ☃.a())) || (ajp.b(this.b, ☃.b()));
  }
  
  private a b(cj ☃)
  {
    cj ☃ = ☃;
    arc ☃ = this.b.o(☃);
    if (ajp.i(☃)) {
      return new a(this.a, this.b, ☃, ☃);
    }
    ☃ = ☃.a();
    ☃ = this.b.o(☃);
    if (ajp.i(☃)) {
      return new a(this.a, this.b, ☃, ☃);
    }
    ☃ = ☃.b();
    ☃ = this.b.o(☃);
    if (ajp.i(☃)) {
      return new a(this.a, this.b, ☃, ☃);
    }
    return null;
  }
  
  private boolean a(a ☃)
  {
    return c(☃.c);
  }
  
  private boolean c(cj ☃)
  {
    for (int ☃ = 0; ☃ < this.g.size(); ☃++)
    {
      cj ☃ = (cj)this.g.get(☃);
      if ((☃.p() == ☃.p()) && (☃.r() == ☃.r())) {
        return true;
      }
    }
    return false;
  }
  
  protected int b()
  {
    int ☃ = 0;
    for (cq ☃ : cq.c.a) {
      if (a(this.c.a(☃))) {
        ☃++;
      }
    }
    return ☃;
  }
  
  private boolean b(a ☃)
  {
    return (a(☃)) || (this.g.size() != 2);
  }
  
  private void c(a ☃)
  {
    this.g.add(☃.c);
    
    cj ☃ = this.c.c();
    cj ☃ = this.c.d();
    cj ☃ = this.c.e();
    cj ☃ = this.c.f();
    
    boolean ☃ = c(☃);
    boolean ☃ = c(☃);
    boolean ☃ = c(☃);
    boolean ☃ = c(☃);
    
    ajp.b ☃ = null;
    if ((☃) || (☃)) {
      ☃ = ajp.b.a;
    }
    if ((☃) || (☃)) {
      ☃ = ajp.b.b;
    }
    if (!this.f)
    {
      if ((☃) && (☃) && (!☃) && (!☃)) {
        ☃ = ajp.b.g;
      }
      if ((☃) && (☃) && (!☃) && (!☃)) {
        ☃ = ajp.b.h;
      }
      if ((☃) && (☃) && (!☃) && (!☃)) {
        ☃ = ajp.b.i;
      }
      if ((☃) && (☃) && (!☃) && (!☃)) {
        ☃ = ajp.b.j;
      }
    }
    if (☃ == ajp.b.a)
    {
      if (ajp.b(this.b, ☃.a())) {
        ☃ = ajp.b.e;
      }
      if (ajp.b(this.b, ☃.a())) {
        ☃ = ajp.b.f;
      }
    }
    if (☃ == ajp.b.b)
    {
      if (ajp.b(this.b, ☃.a())) {
        ☃ = ajp.b.c;
      }
      if (ajp.b(this.b, ☃.a())) {
        ☃ = ajp.b.d;
      }
    }
    if (☃ == null) {
      ☃ = ajp.b.a;
    }
    this.e = this.e.a(this.d.g(), ☃);
    this.b.a(this.c, this.e, 3);
  }
  
  private boolean d(cj ☃)
  {
    a ☃ = b(☃);
    if (☃ == null) {
      return false;
    }
    ☃.d();
    return ☃.b(this);
  }
  
  public a a(boolean ☃, boolean ☃)
  {
    cj ☃ = this.c.c();
    cj ☃ = this.c.d();
    cj ☃ = this.c.e();
    cj ☃ = this.c.f();
    
    boolean ☃ = d(☃);
    boolean ☃ = d(☃);
    boolean ☃ = d(☃);
    boolean ☃ = d(☃);
    
    ajp.b ☃ = null;
    if (((☃) || (☃)) && (!☃) && (!☃)) {
      ☃ = ajp.b.a;
    }
    if (((☃) || (☃)) && (!☃) && (!☃)) {
      ☃ = ajp.b.b;
    }
    if (!this.f)
    {
      if ((☃) && (☃) && (!☃) && (!☃)) {
        ☃ = ajp.b.g;
      }
      if ((☃) && (☃) && (!☃) && (!☃)) {
        ☃ = ajp.b.h;
      }
      if ((☃) && (☃) && (!☃) && (!☃)) {
        ☃ = ajp.b.i;
      }
      if ((☃) && (☃) && (!☃) && (!☃)) {
        ☃ = ajp.b.j;
      }
    }
    if (☃ == null)
    {
      if ((☃) || (☃)) {
        ☃ = ajp.b.a;
      }
      if ((☃) || (☃)) {
        ☃ = ajp.b.b;
      }
      if (!this.f) {
        if (☃)
        {
          if ((☃) && (☃)) {
            ☃ = ajp.b.g;
          }
          if ((☃) && (☃)) {
            ☃ = ajp.b.h;
          }
          if ((☃) && (☃)) {
            ☃ = ajp.b.j;
          }
          if ((☃) && (☃)) {
            ☃ = ajp.b.i;
          }
        }
        else
        {
          if ((☃) && (☃)) {
            ☃ = ajp.b.i;
          }
          if ((☃) && (☃)) {
            ☃ = ajp.b.j;
          }
          if ((☃) && (☃)) {
            ☃ = ajp.b.h;
          }
          if ((☃) && (☃)) {
            ☃ = ajp.b.g;
          }
        }
      }
    }
    if (☃ == ajp.b.a)
    {
      if (ajp.b(this.b, ☃.a())) {
        ☃ = ajp.b.e;
      }
      if (ajp.b(this.b, ☃.a())) {
        ☃ = ajp.b.f;
      }
    }
    if (☃ == ajp.b.b)
    {
      if (ajp.b(this.b, ☃.a())) {
        ☃ = ajp.b.c;
      }
      if (ajp.b(this.b, ☃.a())) {
        ☃ = ajp.b.d;
      }
    }
    if (☃ == null) {
      ☃ = ajp.b.a;
    }
    a(☃);
    this.e = this.e.a(this.d.g(), ☃);
    if ((☃) || (this.b.o(this.c) != this.e))
    {
      this.b.a(this.c, this.e, 3);
      for (int ☃ = 0; ☃ < this.g.size(); ☃++)
      {
        a ☃ = b((cj)this.g.get(☃));
        if (☃ != null)
        {
          ☃.d();
          if (☃.b(this)) {
            ☃.c(this);
          }
        }
      }
    }
    return this;
  }
  
  public arc c()
  {
    return this.e;
  }
}
