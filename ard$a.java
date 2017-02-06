import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class ard$a
  extends ara
{
  private final ajt a;
  private final ImmutableMap<arr<?>, Comparable<?>> b;
  private ImmutableTable<arr<?>, Comparable<?>, arc> c;
  
  private ard$a(ajt ☃, ImmutableMap<arr<?>, Comparable<?>> ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public Collection<arr<?>> r()
  {
    return Collections.unmodifiableCollection(this.b.keySet());
  }
  
  public <T extends Comparable<T>> T c(arr<T> ☃)
  {
    if (!this.b.containsKey(☃)) {
      throw new IllegalArgumentException("Cannot get property " + ☃ + " as it does not exist in " + this.a.t());
    }
    return (Comparable)☃.b().cast(this.b.get(☃));
  }
  
  public <T extends Comparable<T>, V extends T> arc a(arr<T> ☃, V ☃)
  {
    if (!this.b.containsKey(☃)) {
      throw new IllegalArgumentException("Cannot set property " + ☃ + " as it does not exist in " + this.a.t());
    }
    if (!☃.c().contains(☃)) {
      throw new IllegalArgumentException("Cannot set property " + ☃ + " to " + ☃ + " on block " + ajt.h.b(this.a) + ", it is not an allowed value");
    }
    if (this.b.get(☃) == ☃) {
      return this;
    }
    return (arc)this.c.get(☃, ☃);
  }
  
  public ImmutableMap<arr<?>, Comparable<?>> s()
  {
    return this.b;
  }
  
  public ajt t()
  {
    return this.a;
  }
  
  public boolean equals(Object ☃)
  {
    return this == ☃;
  }
  
  public int hashCode()
  {
    return this.b.hashCode();
  }
  
  public void a(Map<Map<arr<?>, Comparable<?>>, a> ☃)
  {
    if (this.c != null) {
      throw new IllegalStateException();
    }
    Table<arr<?>, Comparable<?>, arc> ☃ = HashBasedTable.create();
    for (Iterator ☃ = this.b.entrySet().iterator(); ☃.hasNext();)
    {
      ☃ = (Map.Entry)☃.next();
      ☃ = (arr)☃.getKey();
      for (Comparable<?> ☃ : ☃.c()) {
        if (☃ != ☃.getValue()) {
          ☃.put(☃, ☃, ☃.get(b(☃, ☃)));
        }
      }
    }
    Map.Entry<arr<?>, Comparable<?>> ☃;
    arr<?> ☃;
    this.c = ImmutableTable.copyOf(☃);
  }
  
  private Map<arr<?>, Comparable<?>> b(arr<?> ☃, Comparable<?> ☃)
  {
    Map<arr<?>, Comparable<?>> ☃ = Maps.newHashMap(this.b);
    ☃.put(☃, ☃);
    return ☃;
  }
  
  public axe a()
  {
    return this.a.q(this);
  }
  
  public boolean b()
  {
    return this.a.l(this);
  }
  
  public int c()
  {
    return this.a.m(this);
  }
  
  public int d()
  {
    return this.a.o(this);
  }
  
  public boolean e()
  {
    return this.a.n(this);
  }
  
  public boolean f()
  {
    return this.a.p(this);
  }
  
  public axf g()
  {
    return this.a.r(this);
  }
  
  public arc a(aoe ☃)
  {
    return this.a.a(this, ☃);
  }
  
  public arc a(amr ☃)
  {
    return this.a.a(this, ☃);
  }
  
  public boolean h()
  {
    return this.a.c(this);
  }
  
  public aob i()
  {
    return this.a.a(this);
  }
  
  public int a(ahx ☃, cj ☃)
  {
    return this.a.c(this, ☃, ☃);
  }
  
  public float j()
  {
    return this.a.f(this);
  }
  
  public boolean k()
  {
    return this.a.s(this);
  }
  
  public boolean l()
  {
    return this.a.t(this);
  }
  
  public boolean m()
  {
    return this.a.g(this);
  }
  
  public int a(ahx ☃, cj ☃, cq ☃)
  {
    return this.a.b(this, ☃, ☃, ☃);
  }
  
  public boolean n()
  {
    return this.a.v(this);
  }
  
  public int a(aht ☃, cj ☃)
  {
    return this.a.d(this, ☃, ☃);
  }
  
  public float b(aht ☃, cj ☃)
  {
    return this.a.b(this, ☃, ☃);
  }
  
  public float a(zj ☃, aht ☃, cj ☃)
  {
    return this.a.a(this, ☃, ☃, ☃);
  }
  
  public int b(ahx ☃, cj ☃, cq ☃)
  {
    return this.a.c(this, ☃, ☃, ☃);
  }
  
  public axh o()
  {
    return this.a.h(this);
  }
  
  public arc b(ahx ☃, cj ☃)
  {
    return this.a.b(this, ☃, ☃);
  }
  
  public bbh c(aht ☃, cj ☃)
  {
    return this.a.c(this, ☃, ☃);
  }
  
  public boolean c(ahx ☃, cj ☃, cq ☃)
  {
    return this.a.a(this, ☃, ☃, ☃);
  }
  
  public boolean p()
  {
    return this.a.b(this);
  }
  
  public bbh d(aht ☃, cj ☃)
  {
    return this.a.a(this, ☃, ☃);
  }
  
  public void a(aht ☃, cj ☃, bbh ☃, List<bbh> ☃, rr ☃)
  {
    this.a.a(this, ☃, ☃, ☃, ☃, ☃);
  }
  
  public bbh c(ahx ☃, cj ☃)
  {
    return this.a.a(this, ☃, ☃);
  }
  
  public bbi a(aht ☃, cj ☃, bbj ☃, bbj ☃)
  {
    return this.a.a(this, ☃, ☃, ☃, ☃);
  }
  
  public boolean q()
  {
    return this.a.k(this);
  }
}
