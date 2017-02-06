import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

public abstract class sp
{
  protected final Map<sl, sm> a = Maps.newHashMap();
  protected final Map<String, sm> b = new of();
  protected final Multimap<sl, sl> c = HashMultimap.create();
  
  public sm a(sl ☃)
  {
    return (sm)this.a.get(☃);
  }
  
  public sm a(String ☃)
  {
    return (sm)this.b.get(☃);
  }
  
  public sm b(sl ☃)
  {
    if (this.b.containsKey(☃.a())) {
      throw new IllegalArgumentException("Attribute is already registered!");
    }
    sm ☃ = c(☃);
    this.b.put(☃.a(), ☃);
    this.a.put(☃, ☃);
    
    sl ☃ = ☃.d();
    while (☃ != null)
    {
      this.c.put(☃, ☃);
      ☃ = ☃.d();
    }
    return ☃;
  }
  
  protected abstract sm c(sl paramsl);
  
  public Collection<sm> a()
  {
    return this.b.values();
  }
  
  public void a(sm ☃) {}
  
  public void a(Multimap<String, sn> ☃)
  {
    for (Map.Entry<String, sn> ☃ : ☃.entries())
    {
      sm ☃ = a((String)☃.getKey());
      if (☃ != null) {
        ☃.c((sn)☃.getValue());
      }
    }
  }
  
  public void b(Multimap<String, sn> ☃)
  {
    for (Map.Entry<String, sn> ☃ : ☃.entries())
    {
      sm ☃ = a((String)☃.getKey());
      if (☃ != null)
      {
        ☃.c((sn)☃.getValue());
        ☃.b((sn)☃.getValue());
      }
    }
  }
}
