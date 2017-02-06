import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class sq
  implements sm
{
  private final sp a;
  private final sl b;
  private final Map<Integer, Set<sn>> c = Maps.newHashMap();
  private final Map<String, Set<sn>> d = Maps.newHashMap();
  private final Map<UUID, sn> e = Maps.newHashMap();
  private double f;
  private boolean g = true;
  private double h;
  
  public sq(sp ☃, sl ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.f = ☃.b();
    for (int ☃ = 0; ☃ < 3; ☃++) {
      this.c.put(Integer.valueOf(☃), Sets.newHashSet());
    }
  }
  
  public sl a()
  {
    return this.b;
  }
  
  public double b()
  {
    return this.f;
  }
  
  public void a(double ☃)
  {
    if (☃ == b()) {
      return;
    }
    this.f = ☃;
    f();
  }
  
  public Collection<sn> a(int ☃)
  {
    return (Collection)this.c.get(Integer.valueOf(☃));
  }
  
  public Collection<sn> c()
  {
    Set<sn> ☃ = Sets.newHashSet();
    for (int ☃ = 0; ☃ < 3; ☃++) {
      ☃.addAll(a(☃));
    }
    return ☃;
  }
  
  public sn a(UUID ☃)
  {
    return (sn)this.e.get(☃);
  }
  
  public boolean a(sn ☃)
  {
    return this.e.get(☃.a()) != null;
  }
  
  public void b(sn ☃)
  {
    if (a(☃.a()) != null) {
      throw new IllegalArgumentException("Modifier is already applied on this attribute!");
    }
    Set<sn> ☃ = (Set)this.d.get(☃.b());
    if (☃ == null)
    {
      ☃ = Sets.newHashSet();
      this.d.put(☃.b(), ☃);
    }
    ((Set)this.c.get(Integer.valueOf(☃.c()))).add(☃);
    ☃.add(☃);
    this.e.put(☃.a(), ☃);
    
    f();
  }
  
  protected void f()
  {
    this.g = true;
    this.a.a(this);
  }
  
  public void c(sn ☃)
  {
    for (int ☃ = 0; ☃ < 3; ☃++)
    {
      Set<sn> ☃ = (Set)this.c.get(Integer.valueOf(☃));
      ☃.remove(☃);
    }
    Set<sn> ☃ = (Set)this.d.get(☃.b());
    if (☃ != null)
    {
      ☃.remove(☃);
      if (☃.isEmpty()) {
        this.d.remove(☃.b());
      }
    }
    this.e.remove(☃.a());
    f();
  }
  
  public void b(UUID ☃)
  {
    sn ☃ = a(☃);
    if (☃ != null) {
      c(☃);
    }
  }
  
  public void d()
  {
    Collection<sn> ☃ = c();
    if (☃ == null) {
      return;
    }
    ☃ = Lists.newArrayList(☃);
    for (sn ☃ : ☃) {
      c(☃);
    }
  }
  
  public double e()
  {
    if (this.g)
    {
      this.h = g();
      this.g = false;
    }
    return this.h;
  }
  
  private double g()
  {
    double ☃ = b();
    for (sn ☃ : b(0)) {
      ☃ += ☃.d();
    }
    double ☃ = ☃;
    for (sn ☃ : b(1)) {
      ☃ += ☃ * ☃.d();
    }
    for (sn ☃ : b(2)) {
      ☃ *= (1.0D + ☃.d());
    }
    return this.b.a(☃);
  }
  
  private Collection<sn> b(int ☃)
  {
    Set<sn> ☃ = Sets.newHashSet(a(☃));
    
    sl ☃ = this.b.d();
    while (☃ != null)
    {
      sm ☃ = this.a.a(☃);
      if (☃ != null) {
        ☃.addAll(☃.a(☃));
      }
      ☃ = ☃.d();
    }
    return ☃;
  }
}
