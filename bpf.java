import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class bpf
{
  private Map<ajt, bpi> a = Maps.newIdentityHashMap();
  private Set<ajt> b = Sets.newIdentityHashSet();
  
  public void a(ajt ☃, bpi ☃)
  {
    this.a.put(☃, ☃);
  }
  
  public void a(ajt... ☃)
  {
    Collections.addAll(this.b, ☃);
  }
  
  public Map<arc, bxt> a()
  {
    Map<arc, bxt> ☃ = Maps.newIdentityHashMap();
    for (ajt ☃ : ajt.h) {
      ☃.putAll(b(☃));
    }
    return ☃;
  }
  
  public Set<kk> a(ajt ☃)
  {
    if (this.b.contains(☃)) {
      return Collections.emptySet();
    }
    bpi ☃ = (bpi)this.a.get(☃);
    if (☃ == null) {
      return Collections.singleton(ajt.h.b(☃));
    }
    Set<kk> ☃ = Sets.newHashSet();
    for (bxt ☃ : ☃.a(☃).values()) {
      ☃.add(new kk(☃.b(), ☃.a()));
    }
    return ☃;
  }
  
  public Map<arc, bxt> b(ajt ☃)
  {
    if (this.b.contains(☃)) {
      return Collections.emptyMap();
    }
    return ((bpi)Objects.firstNonNull(this.a.get(☃), new bpg())).a(☃);
  }
}
