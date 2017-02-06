import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class oo
{
  private static final Logger b = ;
  private final List<String> c = Lists.newArrayList();
  private final List<Long> d = Lists.newArrayList();
  public boolean a;
  private String e = "";
  private final Map<String, Long> f = Maps.newHashMap();
  
  public void a()
  {
    this.f.clear();
    this.e = "";
    this.c.clear();
  }
  
  public void a(String name)
  {
    if (this.a)
    {
      if (this.e.length() > 0) {
        this.e += ".";
      }
      this.e += name;
      this.c.add(this.e);
      this.d.add(Long.valueOf(System.nanoTime()));
    }
  }
  
  public void b()
  {
    if (this.a)
    {
      long i = System.nanoTime();
      long j = ((Long)this.d.remove(this.d.size() - 1)).longValue();
      this.c.remove(this.c.size() - 1);
      long k = i - j;
      if (this.f.containsKey(this.e)) {
        this.f.put(this.e, Long.valueOf(((Long)this.f.get(this.e)).longValue() + k));
      } else {
        this.f.put(this.e, Long.valueOf(k));
      }
      if (k > 100000000L) {
        b.warn("Something's taking too long! '" + this.e + "' took aprox " + k / 1000000.0D + " ms");
      }
      this.e = (!this.c.isEmpty() ? (String)this.c.get(this.c.size() - 1) : "");
    }
  }
  
  public List<oo.a> b(String profilerName)
  {
    if (!this.a) {
      return Collections.emptyList();
    }
    long i = this.f.containsKey("root") ? ((Long)this.f.get("root")).longValue() : 0L;
    long j = this.f.containsKey(profilerName) ? ((Long)this.f.get(profilerName)).longValue() : -1L;
    List<oo.a> list = Lists.newArrayList();
    if (profilerName.length() > 0) {
      profilerName = profilerName + ".";
    }
    long k = 0L;
    for (Iterator localIterator = this.f.keySet().iterator(); localIterator.hasNext();)
    {
      s = (String)localIterator.next();
      if ((s.length() > profilerName.length()) && (s.startsWith(profilerName)) && (s.indexOf(".", profilerName.length() + 1) < 0)) {
        k += ((Long)this.f.get(s)).longValue();
      }
    }
    String s;
    float f = (float)k;
    if (k < j) {
      k = j;
    }
    if (i < k) {
      i = k;
    }
    for (String s1 : this.f.keySet()) {
      if ((s1.length() > profilerName.length()) && (s1.startsWith(profilerName)) && (s1.indexOf(".", profilerName.length() + 1) < 0))
      {
        long l = ((Long)this.f.get(s1)).longValue();
        double d0 = l * 100.0D / k;
        double d1 = l * 100.0D / i;
        String s2 = s1.substring(profilerName.length());
        list.add(new oo.a(s2, d0, d1));
      }
    }
    for (String s3 : this.f.keySet()) {
      this.f.put(s3, Long.valueOf(((Long)this.f.get(s3)).longValue() * 999L / 1000L));
    }
    if ((float)k > f) {
      list.add(new oo.a("unspecified", ((float)k - f) * 100.0D / k, ((float)k - f) * 100.0D / i));
    }
    Collections.sort(list);
    list.add(0, new oo.a(profilerName, 100.0D, k * 100.0D / i));
    return list;
  }
  
  public void c(String name)
  {
    b();
    a(name);
  }
  
  public String c()
  {
    return this.c.size() == 0 ? "[UNKNOWN]" : (String)this.c.get(this.c.size() - 1);
  }
  
  public static final class a
    implements Comparable<a>
  {
    public double a;
    public double b;
    public String c;
    
    public a(String profilerName, double usePercentage, double totalUsePercentage)
    {
      this.c = profilerName;
      this.a = usePercentage;
      this.b = totalUsePercentage;
    }
    
    public int a(a p_compareTo_1_)
    {
      return p_compareTo_1_.a > this.a ? 1 : p_compareTo_1_.a < this.a ? -1 : p_compareTo_1_.c.compareTo(this.c);
    }
    
    public int a()
    {
      return (this.c.hashCode() & 0xAAAAAA) + 4473924;
    }
  }
}
