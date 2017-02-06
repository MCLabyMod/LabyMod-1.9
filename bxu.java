import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class bxu
  implements bxo
{
  private final Map<Predicate<arc>, bxo> f;
  protected final boolean a;
  protected final boolean b;
  protected final bvh c;
  protected final bos d;
  protected final boq e;
  
  public bxu(Map<Predicate<arc>, bxo> ☃)
  {
    this.f = ☃;
    
    bxo ☃ = (bxo)☃.values().iterator().next();
    this.a = ☃.a();
    this.b = ☃.b();
    this.c = ☃.d();
    this.d = ☃.e();
    this.e = ☃.f();
  }
  
  public List<bof> a(arc ☃, cq ☃, long ☃)
  {
    List<bof> ☃ = Lists.newArrayList();
    if (☃ != null) {
      for (Map.Entry<Predicate<arc>, bxo> ☃ : this.f.entrySet()) {
        if (((Predicate)☃.getKey()).apply(☃)) {
          ☃.addAll(((bxo)☃.getValue()).a(☃, ☃, ☃++));
        }
      }
    }
    return ☃;
  }
  
  public boolean a()
  {
    return this.a;
  }
  
  public boolean b()
  {
    return this.b;
  }
  
  public boolean c()
  {
    return false;
  }
  
  public bvh d()
  {
    return this.c;
  }
  
  public bos e()
  {
    return this.d;
  }
  
  public boq f()
  {
    return this.e;
  }
  
  public static class a
  {
    private Map<Predicate<arc>, bxo> a = Maps.newLinkedHashMap();
    
    public void a(Predicate<arc> ☃, bxo ☃)
    {
      this.a.put(☃, ☃);
    }
    
    public bxo a()
    {
      return new bxu(this.a);
    }
  }
}
