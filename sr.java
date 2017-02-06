import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class sr
  extends sp
{
  private final Set<sm> e = Sets.newHashSet();
  protected final Map<String, sm> d = new of();
  
  public sq e(sl ☃)
  {
    return (sq)super.a(☃);
  }
  
  public sq b(String ☃)
  {
    sm ☃ = super.a(☃);
    if (☃ == null) {
      ☃ = (sm)this.d.get(☃);
    }
    return (sq)☃;
  }
  
  public sm b(sl ☃)
  {
    sm ☃ = super.b(☃);
    if (((☃ instanceof ss)) && (((ss)☃).g() != null)) {
      this.d.put(((ss)☃).g(), ☃);
    }
    return ☃;
  }
  
  protected sm c(sl ☃)
  {
    return new sq(this, ☃);
  }
  
  public void a(sm ☃)
  {
    if (☃.a().c()) {
      this.e.add(☃);
    }
    for (sl ☃ : this.c.get(☃.a()))
    {
      sq ☃ = e(☃);
      if (☃ != null) {
        ☃.f();
      }
    }
  }
  
  public Set<sm> b()
  {
    return this.e;
  }
  
  public Collection<sm> c()
  {
    Set<sm> ☃ = Sets.newHashSet();
    for (sm ☃ : a()) {
      if (☃.a().c()) {
        ☃.add(☃);
      }
    }
    return ☃;
  }
}
