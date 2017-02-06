import com.google.common.base.Predicate;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.server.MinecraftServer;

public class lv
{
  private static final Predicate<lr> a = new Predicate()
  {
    public boolean a(lr ☃)
    {
      return (☃ != null) && (!☃.y());
    }
  };
  private static final Predicate<lr> b = new Predicate()
  {
    public boolean a(lr ☃)
    {
      return (☃ != null) && ((!☃.y()) || (☃.x().U().b("spectatorsGenerateChunks")));
    }
  };
  private final lp c;
  private final List<lr> d = Lists.newArrayList();
  private final ol<lu> e = new ol();
  private final Set<lu> f = Sets.newHashSet();
  private final List<lu> g = Lists.newLinkedList();
  private final List<lu> h = Lists.newLinkedList();
  private final List<lu> i = Lists.newArrayList();
  private int j;
  private long k;
  private boolean l = true;
  private boolean m = true;
  
  public lv(lp ☃)
  {
    this.c = ☃;
    
    a(☃.u().al().s());
  }
  
  public lp a()
  {
    return this.c;
  }
  
  public Iterator<ase> b()
  {
    final Iterator<lu> ☃ = this.i.iterator();
    new AbstractIterator()
    {
      protected ase a()
      {
        while (☃.hasNext())
        {
          lu ☃ = (lu)☃.next();
          ase ☃ = ☃.f();
          if (☃ != null)
          {
            if ((!☃.v()) && (☃.u())) {
              return ☃;
            }
            if (!☃.j()) {
              return ☃;
            }
            if (☃.a(128.0D, lv.d())) {
              return ☃;
            }
          }
        }
        return (ase)endOfData();
      }
    };
  }
  
  public void c()
  {
    long ☃ = this.c.P();
    if (☃ - this.k > 8000L)
    {
      this.k = ☃;
      for (int ☃ = 0; ☃ < this.i.size(); ☃++)
      {
        lu ☃ = (lu)this.i.get(☃);
        ☃.d();
        ☃.c();
      }
    }
    if (!this.f.isEmpty())
    {
      for (lu ☃ : this.f) {
        ☃.d();
      }
      this.f.clear();
    }
    if ((this.l) && (☃ % 4L == 0L))
    {
      this.l = false;
      Collections.sort(this.h, new Comparator()
      {
        public int a(lu ☃, lu ☃)
        {
          return ComparisonChain.start().compare(☃.g(), ☃.g()).result();
        }
      });
    }
    if ((this.m) && (☃ % 4L == 2L))
    {
      this.m = false;
      Collections.sort(this.g, new Comparator()
      {
        public int a(lu ☃, lu ☃)
        {
          return ComparisonChain.start().compare(☃.g(), ☃.g()).result();
        }
      });
    }
    long ☃;
    int ☃;
    Iterator<lu> ☃;
    if (!this.h.isEmpty())
    {
      ☃ = System.nanoTime() + 50000000L;
      ☃ = 49;
      for (☃ = this.h.iterator(); ☃.hasNext();)
      {
        lu ☃ = (lu)☃.next();
        if (☃.f() == null)
        {
          boolean ☃ = ☃.a(b);
          if (☃.a(☃))
          {
            ☃.remove();
            if (☃.b()) {
              this.g.remove(☃);
            }
            ☃--;
            if ((☃ < 0) || (System.nanoTime() > ☃)) {
              break;
            }
          }
        }
      }
    }
    int ☃;
    Iterator<lu> ☃;
    if (!this.g.isEmpty())
    {
      ☃ = 81;
      for (☃ = this.g.iterator(); ☃.hasNext();)
      {
        lu ☃ = (lu)☃.next();
        if (☃.b())
        {
          ☃.remove();
          ☃--;
          if (☃ < 0) {
            break;
          }
        }
      }
    }
    if (this.d.isEmpty())
    {
      asv ☃ = this.c.s;
      if (!☃.e()) {
        this.c.r().b();
      }
    }
  }
  
  public boolean a(int ☃, int ☃)
  {
    long ☃ = d(☃, ☃);
    return this.e.a(☃) != null;
  }
  
  public lu b(int ☃, int ☃)
  {
    return (lu)this.e.a(d(☃, ☃));
  }
  
  private lu c(int ☃, int ☃)
  {
    long ☃ = d(☃, ☃);
    lu ☃ = (lu)this.e.a(☃);
    if (☃ == null)
    {
      ☃ = new lu(this, ☃, ☃);
      this.e.a(☃, ☃);
      this.i.add(☃);
      if (☃.f() == null) {
        this.h.add(☃);
      }
      if (!☃.b()) {
        this.g.add(☃);
      }
    }
    return ☃;
  }
  
  public void a(cj ☃)
  {
    int ☃ = ☃.p() >> 4;
    int ☃ = ☃.r() >> 4;
    lu ☃ = b(☃, ☃);
    if (☃ != null) {
      ☃.a(☃.p() & 0xF, ☃.q(), ☃.r() & 0xF);
    }
  }
  
  public void a(lr ☃)
  {
    int ☃ = (int)☃.p >> 4;
    int ☃ = (int)☃.r >> 4;
    
    ☃.d = ☃.p;
    ☃.e = ☃.r;
    for (int ☃ = ☃ - this.j; ☃ <= ☃ + this.j; ☃++) {
      for (int ☃ = ☃ - this.j; ☃ <= ☃ + this.j; ☃++) {
        c(☃, ☃).a(☃);
      }
    }
    this.d.add(☃);
    e();
  }
  
  public void b(lr ☃)
  {
    int ☃ = (int)☃.d >> 4;
    int ☃ = (int)☃.e >> 4;
    for (int ☃ = ☃ - this.j; ☃ <= ☃ + this.j; ☃++) {
      for (int ☃ = ☃ - this.j; ☃ <= ☃ + this.j; ☃++)
      {
        lu ☃ = b(☃, ☃);
        if (☃ != null) {
          ☃.b(☃);
        }
      }
    }
    this.d.remove(☃);
    e();
  }
  
  private boolean a(int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    int ☃ = ☃ - ☃;
    int ☃ = ☃ - ☃;
    if ((☃ < -☃) || (☃ > ☃)) {
      return false;
    }
    if ((☃ < -☃) || (☃ > ☃)) {
      return false;
    }
    return true;
  }
  
  public void c(lr ☃)
  {
    int ☃ = (int)☃.p >> 4;
    int ☃ = (int)☃.r >> 4;
    
    double ☃ = ☃.d - ☃.p;
    double ☃ = ☃.e - ☃.r;
    double ☃ = ☃ * ☃ + ☃ * ☃;
    if (☃ < 64.0D) {
      return;
    }
    int ☃ = (int)☃.d >> 4;
    int ☃ = (int)☃.e >> 4;
    int ☃ = this.j;
    
    int ☃ = ☃ - ☃;
    int ☃ = ☃ - ☃;
    if ((☃ == 0) && (☃ == 0)) {
      return;
    }
    for (int ☃ = ☃ - ☃; ☃ <= ☃ + ☃; ☃++) {
      for (int ☃ = ☃ - ☃; ☃ <= ☃ + ☃; ☃++)
      {
        if (!a(☃, ☃, ☃, ☃, ☃)) {
          c(☃, ☃).a(☃);
        }
        if (!a(☃ - ☃, ☃ - ☃, ☃, ☃, ☃))
        {
          lu ☃ = b(☃ - ☃, ☃ - ☃);
          if (☃ != null) {
            ☃.b(☃);
          }
        }
      }
    }
    ☃.d = ☃.p;
    ☃.e = ☃.r;
    e();
  }
  
  public boolean a(lr ☃, int ☃, int ☃)
  {
    lu ☃ = b(☃, ☃);
    return (☃ != null) && (☃.d(☃)) && (☃.e());
  }
  
  public void a(int ☃)
  {
    ☃ = on.a(☃, 3, 32);
    if (☃ == this.j) {
      return;
    }
    int ☃ = ☃ - this.j;
    
    List<lr> ☃ = Lists.newArrayList(this.d);
    for (lr ☃ : ☃)
    {
      int ☃ = (int)☃.p >> 4;
      int ☃ = (int)☃.r >> 4;
      if (☃ > 0) {
        for (int ☃ = ☃ - ☃; ☃ <= ☃ + ☃; ☃++) {
          for (int ☃ = ☃ - ☃; ☃ <= ☃ + ☃; ☃++)
          {
            lu ☃ = c(☃, ☃);
            if (!☃.d(☃)) {
              ☃.a(☃);
            }
          }
        }
      } else {
        for (int ☃ = ☃ - this.j; ☃ <= ☃ + this.j; ☃++) {
          for (int ☃ = ☃ - this.j; ☃ <= ☃ + this.j; ☃++) {
            if (!a(☃, ☃, ☃, ☃, ☃)) {
              c(☃, ☃).b(☃);
            }
          }
        }
      }
    }
    this.j = ☃;
    e();
  }
  
  private void e()
  {
    this.l = true;
    this.m = true;
  }
  
  public static int b(int ☃)
  {
    return ☃ * 16 - 16;
  }
  
  private static long d(int ☃, int ☃)
  {
    return ☃ + 2147483647L | ☃ + 2147483647L << 32;
  }
  
  public void a(lu ☃)
  {
    this.f.add(☃);
  }
  
  public void b(lu ☃)
  {
    ahn ☃ = ☃.a();
    long ☃ = d(☃.a, ☃.b);
    ☃.c();
    this.e.d(☃);
    this.i.remove(☃);
    this.f.remove(☃);
    this.g.remove(☃);
    this.h.remove(☃);
    a().r().a(☃.a, ☃.b);
  }
}
