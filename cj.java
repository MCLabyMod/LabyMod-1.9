import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class cj
  extends df
{
  private static final Logger c = ;
  public static final cj a = new cj(0, 0, 0);
  private static final int d = 1 + on.e(on.c(30000000));
  private static final int e = d;
  private static final int f = 64 - d - e;
  private static final int g = 0 + e;
  private static final int h = g + f;
  private static final long i = (1L << d) - 1L;
  private static final long j = (1L << f) - 1L;
  private static final long k = (1L << e) - 1L;
  
  public cj(int ☃, int ☃, int ☃)
  {
    super(☃, ☃, ☃);
  }
  
  public cj(double ☃, double ☃, double ☃)
  {
    super(☃, ☃, ☃);
  }
  
  public cj(rr ☃)
  {
    this(☃.p, ☃.q, ☃.r);
  }
  
  public cj(bbj ☃)
  {
    this(☃.b, ☃.c, ☃.d);
  }
  
  public cj(df ☃)
  {
    this(☃.p(), ☃.q(), ☃.r());
  }
  
  public cj a(double ☃, double ☃, double ☃)
  {
    if ((☃ == 0.0D) && (☃ == 0.0D) && (☃ == 0.0D)) {
      return this;
    }
    return new cj(p() + ☃, q() + ☃, r() + ☃);
  }
  
  public cj a(int ☃, int ☃, int ☃)
  {
    if ((☃ == 0) && (☃ == 0) && (☃ == 0)) {
      return this;
    }
    return new cj(p() + ☃, q() + ☃, r() + ☃);
  }
  
  public cj a(df ☃)
  {
    if ((☃.p() == 0) && (☃.q() == 0) && (☃.r() == 0)) {
      return this;
    }
    return new cj(p() + ☃.p(), q() + ☃.q(), r() + ☃.r());
  }
  
  public cj b(df ☃)
  {
    if ((☃.p() == 0) && (☃.q() == 0) && (☃.r() == 0)) {
      return this;
    }
    return new cj(p() - ☃.p(), q() - ☃.q(), r() - ☃.r());
  }
  
  public cj a()
  {
    return b(1);
  }
  
  public cj b(int ☃)
  {
    return a(cq.b, ☃);
  }
  
  public cj b()
  {
    return c(1);
  }
  
  public cj c(int ☃)
  {
    return a(cq.a, ☃);
  }
  
  public cj c()
  {
    return d(1);
  }
  
  public cj d(int ☃)
  {
    return a(cq.c, ☃);
  }
  
  public cj d()
  {
    return e(1);
  }
  
  public cj e(int ☃)
  {
    return a(cq.d, ☃);
  }
  
  public cj e()
  {
    return f(1);
  }
  
  public cj f(int ☃)
  {
    return a(cq.e, ☃);
  }
  
  public cj f()
  {
    return g(1);
  }
  
  public cj g(int ☃)
  {
    return a(cq.f, ☃);
  }
  
  public cj a(cq ☃)
  {
    return a(☃, 1);
  }
  
  public cj a(cq ☃, int ☃)
  {
    if (☃ == 0) {
      return this;
    }
    return new cj(p() + ☃.g() * ☃, q() + ☃.h() * ☃, r() + ☃.i() * ☃);
  }
  
  public cj c(df ☃)
  {
    return new cj(q() * ☃.r() - r() * ☃.q(), r() * ☃.p() - p() * ☃.r(), p() * ☃.q() - q() * ☃.p());
  }
  
  public long g()
  {
    return (p() & i) << h | (q() & j) << g | (r() & k) << 0;
  }
  
  public static cj a(long ☃)
  {
    int ☃ = (int)(☃ << 64 - h - d >> 64 - d);
    int ☃ = (int)(☃ << 64 - g - f >> 64 - f);
    int ☃ = (int)(☃ << 64 - e >> 64 - e);
    
    return new cj(☃, ☃, ☃);
  }
  
  public static Iterable<cj> a(cj ☃, cj ☃)
  {
    cj ☃ = new cj(Math.min(☃.p(), ☃.p()), Math.min(☃.q(), ☃.q()), Math.min(☃.r(), ☃.r()));
    final cj ☃ = new cj(Math.max(☃.p(), ☃.p()), Math.max(☃.q(), ☃.q()), Math.max(☃.r(), ☃.r()));
    
    new Iterable()
    {
      public Iterator<cj> iterator()
      {
        new AbstractIterator()
        {
          private cj b = null;
          
          protected cj a()
          {
            if (this.b == null)
            {
              this.b = cj.1.this.a;
              return this.b;
            }
            if (this.b.equals(cj.1.this.b)) {
              return (cj)endOfData();
            }
            int ☃ = this.b.p();
            int ☃ = this.b.q();
            int ☃ = this.b.r();
            if (☃ < cj.1.this.b.p())
            {
              ☃++;
            }
            else if (☃ < cj.1.this.b.q())
            {
              ☃ = cj.1.this.a.p();
              ☃++;
            }
            else if (☃ < cj.1.this.b.r())
            {
              ☃ = cj.1.this.a.p();
              ☃ = cj.1.this.a.q();
              ☃++;
            }
            this.b = new cj(☃, ☃, ☃);
            return this.b;
          }
        };
      }
    };
  }
  
  public cj h()
  {
    return this;
  }
  
  public static final class a
    extends cj
  {
    private int c;
    private int d;
    private int e;
    
    public a()
    {
      this(0, 0, 0);
    }
    
    public a(cj ☃)
    {
      this(☃.p(), ☃.q(), ☃.r());
    }
    
    public a(int ☃, int ☃, int ☃)
    {
      super(0, 0);
      this.c = ☃;
      this.d = ☃;
      this.e = ☃;
    }
    
    public int p()
    {
      return this.c;
    }
    
    public int q()
    {
      return this.d;
    }
    
    public int r()
    {
      return this.e;
    }
    
    public a c(int ☃, int ☃, int ☃)
    {
      this.c = ☃;
      this.d = ☃;
      this.e = ☃;
      return this;
    }
    
    public void c(cq ☃)
    {
      this.c += ☃.g();
      this.d += ☃.h();
      this.e += ☃.i();
    }
    
    public void p(int ☃)
    {
      this.d = ☃;
    }
    
    public cj h()
    {
      return new cj(this);
    }
  }
  
  public static Iterable<cj.a> b(cj ☃, cj ☃)
  {
    cj ☃ = new cj(Math.min(☃.p(), ☃.p()), Math.min(☃.q(), ☃.q()), Math.min(☃.r(), ☃.r()));
    final cj ☃ = new cj(Math.max(☃.p(), ☃.p()), Math.max(☃.q(), ☃.q()), Math.max(☃.r(), ☃.r()));
    
    new Iterable()
    {
      public Iterator<cj.a> iterator()
      {
        new AbstractIterator()
        {
          private cj.a b = null;
          
          protected cj.a a()
          {
            if (this.b == null)
            {
              this.b = new cj.a(cj.2.this.a.p(), cj.2.this.a.q(), cj.2.this.a.r());
              return this.b;
            }
            if (this.b.equals(cj.2.this.b)) {
              return (cj.a)endOfData();
            }
            int ☃ = this.b.p();
            int ☃ = this.b.q();
            int ☃ = this.b.r();
            if (☃ < cj.2.this.b.p())
            {
              ☃++;
            }
            else if (☃ < cj.2.this.b.q())
            {
              ☃ = cj.2.this.a.p();
              ☃++;
            }
            else if (☃ < cj.2.this.b.r())
            {
              ☃ = cj.2.this.a.p();
              ☃ = cj.2.this.a.q();
              ☃++;
            }
            cj.a.a(this.b, ☃);
            cj.a.b(this.b, ☃);
            cj.a.c(this.b, ☃);
            return this.b;
          }
        };
      }
    };
  }
  
  public static final class b
    extends cj
  {
    private int c;
    private int d;
    private int e;
    private boolean f;
    private static final List<b> g = ;
    
    private b(int ☃, int ☃, int ☃)
    {
      super(0, 0);
      this.c = ☃;
      this.d = ☃;
      this.e = ☃;
    }
    
    public static b s()
    {
      return c(0, 0, 0);
    }
    
    public static b c(double ☃, double ☃, double ☃)
    {
      return c(on.c(☃), on.c(☃), on.c(☃));
    }
    
    public static b g(df ☃)
    {
      return c(☃.p(), ☃.q(), ☃.r());
    }
    
    public static b c(int ☃, int ☃, int ☃)
    {
      synchronized (g)
      {
        if (!g.isEmpty())
        {
          b ☃ = (b)g.remove(g.size() - 1);
          if ((☃ != null) && (☃.f))
          {
            ☃.f = false;
            ☃.d(☃, ☃, ☃);
            return ☃;
          }
        }
      }
      return new b(☃, ☃, ☃);
    }
    
    public void t()
    {
      synchronized (g)
      {
        if (g.size() < 100) {
          g.add(this);
        }
        this.f = true;
      }
    }
    
    public int p()
    {
      return this.c;
    }
    
    public int q()
    {
      return this.d;
    }
    
    public int r()
    {
      return this.e;
    }
    
    public b d(int ☃, int ☃, int ☃)
    {
      if (this.f)
      {
        cj.o().error("PooledMutableBlockPosition modified after it was released.", new Throwable());
        this.f = false;
      }
      this.c = ☃;
      this.d = ☃;
      this.e = ☃;
      return this;
    }
    
    public b d(double ☃, double ☃, double ☃)
    {
      return d(on.c(☃), on.c(☃), on.c(☃));
    }
    
    public b h(df ☃)
    {
      return d(☃.p(), ☃.q(), ☃.r());
    }
    
    public b c(cq ☃)
    {
      return d(this.c + ☃.g(), this.d + ☃.h(), this.e + ☃.i());
    }
  }
}
