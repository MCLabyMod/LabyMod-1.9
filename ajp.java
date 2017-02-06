import com.google.common.collect.Lists;
import java.util.List;

public abstract class ajp
  extends ajt
{
  protected static final bbh a = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
  protected static final bbh b = new bbh(0.0D, 0.0D, 0.0D, 1.0D, 0.15625D, 1.0D);
  protected final boolean c;
  
  public class a
  {
    private final aht b;
    private final cj c;
    private final ajp d;
    private arc e;
    private final boolean f;
    private final List<cj> g = Lists.newArrayList();
    
    public a(aht ☃, cj ☃, arc ☃)
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
        return new a(ajp.this, this.b, ☃, ☃);
      }
      ☃ = ☃.a();
      ☃ = this.b.o(☃);
      if (ajp.i(☃)) {
        return new a(ajp.this, this.b, ☃, ☃);
      }
      ☃ = ☃.b();
      ☃ = this.b.o(☃);
      if (ajp.i(☃)) {
        return new a(ajp.this, this.b, ☃, ☃);
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
  
  public static boolean b(aht ☃, cj ☃)
  {
    return i(☃.o(☃));
  }
  
  public static boolean i(arc ☃)
  {
    ajt ☃ = ☃.t();
    return (☃ == aju.av) || (☃ == aju.D) || (☃ == aju.E) || (☃ == aju.cs);
  }
  
  protected ajp(boolean ☃)
  {
    super(axe.q);
    this.c = ☃;
    a(acq.e);
  }
  
  public bbh a(arc ☃, aht ☃, cj ☃)
  {
    return k;
  }
  
  public boolean b(arc ☃)
  {
    return false;
  }
  
  public bbh a(arc ☃, ahx ☃, cj ☃)
  {
    ajp.b ☃ = ☃.t() == this ? (ajp.b)☃.c(g()) : null;
    if ((☃ != null) && (☃.c())) {
      return b;
    }
    return a;
  }
  
  public boolean c(arc ☃)
  {
    return false;
  }
  
  public boolean a(aht ☃, cj ☃)
  {
    if (☃.o(☃.b()).q()) {
      return true;
    }
    return false;
  }
  
  public void c(aht ☃, cj ☃, arc ☃)
  {
    if (!☃.E)
    {
      ☃ = a(☃, ☃, ☃, true);
      if (this.c) {
        a(☃, ☃, ☃, this);
      }
    }
  }
  
  public void a(aht ☃, cj ☃, arc ☃, ajt ☃)
  {
    if (☃.E) {
      return;
    }
    ajp.b ☃ = (ajp.b)☃.c(g());
    boolean ☃ = false;
    if (!☃.o(☃.b()).q()) {
      ☃ = true;
    }
    if ((☃ == ajp.b.c) && (!☃.o(☃.f()).q())) {
      ☃ = true;
    } else if ((☃ == ajp.b.d) && (!☃.o(☃.e()).q())) {
      ☃ = true;
    } else if ((☃ == ajp.b.e) && (!☃.o(☃.c()).q())) {
      ☃ = true;
    } else if ((☃ == ajp.b.f) && (!☃.o(☃.d()).q())) {
      ☃ = true;
    }
    if ((☃) && (!☃.d(☃)))
    {
      b(☃, ☃, ☃, 0);
      ☃.g(☃);
    }
    else
    {
      b(☃, ☃, ☃, ☃);
    }
  }
  
  protected void b(aht ☃, cj ☃, arc ☃, ajt ☃) {}
  
  protected arc a(aht ☃, cj ☃, arc ☃, boolean ☃)
  {
    if (☃.E) {
      return ☃;
    }
    return new ajp.a(☃, ☃, ☃).a(☃.y(☃), ☃).c();
  }
  
  public axh h(arc ☃)
  {
    return axh.a;
  }
  
  public ahm f()
  {
    return ahm.c;
  }
  
  public void b(aht ☃, cj ☃, arc ☃)
  {
    super.b(☃, ☃, ☃);
    if (((ajp.b)☃.c(g())).c()) {
      ☃.d(☃.a(), this);
    }
    if (this.c)
    {
      ☃.d(☃, this);
      ☃.d(☃.b(), this);
    }
  }
  
  public abstract arr<ajp.b> g();
  
  public static enum b
    implements or
  {
    private static final b[] k;
    private final int l;
    private final String m;
    
    static
    {
      k = new b[values().length];
      for (b ☃ : values()) {
        k[☃.a()] = ☃;
      }
    }
    
    private b(int ☃, String ☃)
    {
      this.l = ☃;
      this.m = ☃;
    }
    
    public int a()
    {
      return this.l;
    }
    
    public String toString()
    {
      return this.m;
    }
    
    public boolean c()
    {
      return (this == e) || (this == c) || (this == f) || (this == d);
    }
    
    public static b a(int ☃)
    {
      if ((☃ < 0) || (☃ >= k.length)) {
        ☃ = 0;
      }
      return k[☃];
    }
    
    public String m()
    {
      return this.m;
    }
  }
}
