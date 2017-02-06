import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;

public abstract class alm
  extends ajy
{
  protected arp<alm.a> a;
  
  protected alm()
  {
    w(this.A.b().a(g(), e() == alm.b.b ? alm.a.b : alm.a.a));
  }
  
  public int d(arc ☃)
  {
    return ((alm.a)☃.c(g())).b();
  }
  
  public void a(ado ☃, acq ☃, List<adq> ☃)
  {
    for (alm.a ☃ : alm.a.a(e())) {
      ☃.add(new adq(☃, 1, ☃.b()));
    }
  }
  
  public arc a(int ☃)
  {
    return u().a(g(), alm.a.a(e(), ☃));
  }
  
  public abstract alm.b e();
  
  public arr<alm.a> g()
  {
    if (this.a == null) {
      this.a = arp.a("type", alm.a.class, new Predicate()
      {
        public boolean a(alm.a ☃)
        {
          return ☃.a() == alm.this.e();
        }
      });
    }
    return this.a;
  }
  
  public int e(arc ☃)
  {
    return ((alm.a)☃.c(g())).b();
  }
  
  protected ard b()
  {
    return new ard(this, new arr[] { g() });
  }
  
  public static enum b
  {
    private b() {}
    
    public alm a()
    {
      if (this == a) {
        return aju.N;
      }
      return aju.O;
    }
  }
  
  public static enum a
    implements or
  {
    private static final a[][] k;
    private final alm.b l;
    private final int m;
    private final String n;
    private final String o;
    
    static
    {
      k = new a[alm.b.values().length][];
      for (alm.b ☃ : alm.b.values())
      {
        Collection<a> ☃ = Collections2.filter(Lists.newArrayList(values()), new Predicate()
        {
          public boolean a(alm.a ☃)
          {
            return ☃.a() == this.a;
          }
        });
        k[☃.ordinal()] = ((a[])☃.toArray(new a[☃.size()]));
      }
    }
    
    private a(alm.b ☃, int ☃, String ☃)
    {
      this(☃, ☃, ☃, ☃);
    }
    
    private a(alm.b ☃, int ☃, String ☃, String ☃)
    {
      this.l = ☃;
      this.m = ☃;
      this.n = ☃;
      this.o = ☃;
    }
    
    public alm.b a()
    {
      return this.l;
    }
    
    public int b()
    {
      return this.m;
    }
    
    public static a a(alm.b ☃, int ☃)
    {
      a[] ☃ = k[☃.ordinal()];
      if ((☃ < 0) || (☃ >= ☃.length)) {
        ☃ = 0;
      }
      return ☃[☃];
    }
    
    public static a[] a(alm.b ☃)
    {
      return k[☃.ordinal()];
    }
    
    public String toString()
    {
      return this.n;
    }
    
    public String m()
    {
      return this.n;
    }
    
    public String d()
    {
      return this.o;
    }
  }
  
  public ajt.a v()
  {
    return ajt.a.b;
  }
}
