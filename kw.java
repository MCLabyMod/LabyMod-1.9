import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.server.MinecraftServer;

public class kw
  extends bbp
{
  private final MinecraftServer a;
  private final Set<bbl> b = Sets.newHashSet();
  private Runnable[] c = new Runnable[0];
  
  public kw(MinecraftServer ☃)
  {
    this.a = ☃;
  }
  
  public void a(bbn ☃)
  {
    super.a(☃);
    if (this.b.contains(☃.d())) {
      this.a.al().a(new hu(☃));
    }
    b();
  }
  
  public void a(String ☃)
  {
    super.a(☃);
    this.a.al().a(new hu(☃));
    b();
  }
  
  public void a(String ☃, bbl ☃)
  {
    super.a(☃, ☃);
    this.a.al().a(new hu(☃, ☃));
    b();
  }
  
  public void a(int ☃, bbl ☃)
  {
    bbl ☃ = a(☃);
    
    super.a(☃, ☃);
    if ((☃ != ☃) && (☃ != null)) {
      if (h(☃) > 0) {
        this.a.al().a(new hk(☃, ☃));
      } else {
        g(☃);
      }
    }
    if (☃ != null) {
      if (this.b.contains(☃)) {
        this.a.al().a(new hk(☃, ☃));
      } else {
        e(☃);
      }
    }
    b();
  }
  
  public boolean a(String ☃, String ☃)
  {
    if (super.a(☃, ☃))
    {
      bbm ☃ = d(☃);
      this.a.al().a(new ht(☃, Arrays.asList(new String[] { ☃ }), 3));
      b();
      
      return true;
    }
    return false;
  }
  
  public void a(String ☃, bbm ☃)
  {
    super.a(☃, ☃);
    
    this.a.al().a(new ht(☃, Arrays.asList(new String[] { ☃ }), 4));
    
    b();
  }
  
  public void a(bbl ☃)
  {
    super.a(☃);
    b();
  }
  
  public void b(bbl ☃)
  {
    super.b(☃);
    if (this.b.contains(☃)) {
      this.a.al().a(new hr(☃, 2));
    }
    b();
  }
  
  public void c(bbl ☃)
  {
    super.c(☃);
    if (this.b.contains(☃)) {
      g(☃);
    }
    b();
  }
  
  public void a(bbm ☃)
  {
    super.a(☃);
    
    this.a.al().a(new ht(☃, 0));
    
    b();
  }
  
  public void b(bbm ☃)
  {
    super.b(☃);
    
    this.a.al().a(new ht(☃, 2));
    
    b();
  }
  
  public void c(bbm ☃)
  {
    super.c(☃);
    
    this.a.al().a(new ht(☃, 1));
    
    b();
  }
  
  public void a(Runnable ☃)
  {
    this.c = ((Runnable[])Arrays.copyOf(this.c, this.c.length + 1));
    this.c[(this.c.length - 1)] = ☃;
  }
  
  protected void b()
  {
    for (int ☃ = 0; ☃ < this.c.length; ☃++) {
      this.c[☃].run();
    }
  }
  
  public List<ff<?>> d(bbl ☃)
  {
    List<ff<?>> ☃ = Lists.newArrayList();
    ☃.add(new hr(☃, 0));
    for (int ☃ = 0; ☃ < 19; ☃++) {
      if (a(☃) == ☃) {
        ☃.add(new hk(☃, ☃));
      }
    }
    for (bbn ☃ : i(☃)) {
      ☃.add(new hu(☃));
    }
    return ☃;
  }
  
  public void e(bbl ☃)
  {
    List<ff<?>> ☃ = d(☃);
    for (Iterator ☃ = this.a.al().v().iterator(); ☃.hasNext();)
    {
      ☃ = (lr)☃.next();
      for (ff<?> ☃ : ☃) {
        ☃.a.a(☃);
      }
    }
    lr ☃;
    this.b.add(☃);
  }
  
  public List<ff<?>> f(bbl ☃)
  {
    List<ff<?>> ☃ = Lists.newArrayList();
    ☃.add(new hr(☃, 1));
    for (int ☃ = 0; ☃ < 19; ☃++) {
      if (a(☃) == ☃) {
        ☃.add(new hk(☃, ☃));
      }
    }
    return ☃;
  }
  
  public void g(bbl ☃)
  {
    List<ff<?>> ☃ = f(☃);
    for (Iterator ☃ = this.a.al().v().iterator(); ☃.hasNext();)
    {
      ☃ = (lr)☃.next();
      for (ff<?> ☃ : ☃) {
        ☃.a.a(☃);
      }
    }
    lr ☃;
    this.b.remove(☃);
  }
  
  public int h(bbl ☃)
  {
    int ☃ = 0;
    for (int ☃ = 0; ☃ < 19; ☃++) {
      if (a(☃) == ☃) {
        ☃++;
      }
    }
    return ☃;
  }
}
