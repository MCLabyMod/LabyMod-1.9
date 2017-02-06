import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bwl
  implements bwe
{
  private static final Logger a = ;
  private static final Joiner b = Joiner.on(", ");
  private final Map<String, bvz> c = Maps.newHashMap();
  private final List<bwh> d = Lists.newArrayList();
  private final Set<String> e = Sets.newLinkedHashSet();
  private final bww f;
  
  public bwl(bww ☃)
  {
    this.f = ☃;
  }
  
  public void a(bwi ☃)
  {
    for (String ☃ : ☃.c())
    {
      this.e.add(☃);
      bvz ☃ = (bvz)this.c.get(☃);
      if (☃ == null)
      {
        ☃ = new bvz(this.f);
        this.c.put(☃, ☃);
      }
      ☃.a(☃);
    }
  }
  
  public Set<String> a()
  {
    return this.e;
  }
  
  public bwf a(kk ☃)
    throws IOException
  {
    bwg ☃ = (bwg)this.c.get(☃.b());
    if (☃ != null) {
      return ☃.a(☃);
    }
    throw new FileNotFoundException(☃.toString());
  }
  
  public List<bwf> b(kk ☃)
    throws IOException
  {
    bwg ☃ = (bwg)this.c.get(☃.b());
    if (☃ != null) {
      return ☃.b(☃);
    }
    throw new FileNotFoundException(☃.toString());
  }
  
  private void b()
  {
    this.c.clear();
    this.e.clear();
  }
  
  public void a(List<bwi> ☃)
  {
    b();
    
    a.info("Reloading ResourceManager: " + b.join(Iterables.transform(☃, new Function()
    {
      public String a(bwi ☃)
      {
        return ☃ == null ? "<NULL>" : ☃.b();
      }
    })));
    for (bwi ☃ : ☃) {
      a(☃);
    }
    c();
  }
  
  public void a(bwh ☃)
  {
    this.d.add(☃);
    
    ☃.a(this);
  }
  
  private void c()
  {
    for (bwh ☃ : this.d) {
      ☃.a(this);
    }
  }
}
