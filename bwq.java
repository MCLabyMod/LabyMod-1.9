import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bwq
  implements bwh
{
  private static final Logger b = ;
  private final bww c;
  private String d;
  protected static final bwr a = new bwr();
  private Map<String, bwp> e = Maps.newHashMap();
  
  public bwq(bww ☃, String ☃)
  {
    this.c = ☃;
    
    this.d = ☃;
    
    bwo.a(a);
  }
  
  public void a(List<bwi> ☃)
  {
    this.e.clear();
    for (bwi ☃ : ☃) {
      try
      {
        bxe ☃ = (bxe)☃.a(this.c, "language");
        if (☃ != null) {
          for (bwp ☃ : ☃.a()) {
            if (!this.e.containsKey(☃.a())) {
              this.e.put(☃.a(), ☃);
            }
          }
        }
      }
      catch (RuntimeException ☃)
      {
        b.warn("Unable to parse metadata section of resourcepack: " + ☃.b(), ☃);
      }
      catch (IOException ☃)
      {
        b.warn("Unable to parse metadata section of resourcepack: " + ☃.b(), ☃);
      }
    }
  }
  
  public void a(bwg ☃)
  {
    List<String> ☃ = Lists.newArrayList(new String[] { "en_US" });
    if (!"en_US".equals(this.d)) {
      ☃.add(this.d);
    }
    a.a(☃, ☃);
    
    dj.a(a.a);
  }
  
  public boolean a()
  {
    return a.a();
  }
  
  public boolean b()
  {
    return (c() != null) && (c().b());
  }
  
  public void a(bwp ☃)
  {
    this.d = ☃.a();
  }
  
  public bwp c()
  {
    return this.e.containsKey(this.d) ? (bwp)this.e.get(this.d) : (bwp)this.e.get("en_US");
  }
  
  public SortedSet<bwp> d()
  {
    return Sets.newTreeSet(this.e.values());
  }
}
