import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class pb
  implements pa
{
  private static final Logger a = ;
  private final Map<oy, List<pd>> b = Maps.newHashMap();
  private final Map<oy, List<ox>> c = Maps.newHashMap();
  private final int d;
  
  public pb(int ☃)
  {
    this.d = ☃;
  }
  
  public dn a(oy ☃, dn ☃)
  {
    int ☃ = ☃.b("DataVersion", 99) ? ☃.h("DataVersion") : -1;
    if (☃ >= 169) {
      return ☃;
    }
    return a(☃, ☃, ☃);
  }
  
  public dn a(oy ☃, dn ☃, int ☃)
  {
    if (☃ < this.d)
    {
      ☃ = b(☃, ☃, ☃);
      ☃ = c(☃, ☃, ☃);
    }
    return ☃;
  }
  
  private dn b(oy ☃, dn ☃, int ☃)
  {
    List<ox> ☃ = (List)this.c.get(☃);
    if (☃ != null) {
      for (int ☃ = 0; ☃ < ☃.size(); ☃++)
      {
        ox ☃ = (ox)☃.get(☃);
        if (☃.a() > ☃) {
          ☃ = ☃.a(☃);
        }
      }
    }
    return ☃;
  }
  
  private dn c(oy ☃, dn ☃, int ☃)
  {
    List<pd> ☃ = (List)this.b.get(☃);
    if (☃ != null) {
      for (int ☃ = 0; ☃ < ☃.size(); ☃++) {
        ☃ = ((pd)☃.get(☃)).a(this, ☃, ☃);
      }
    }
    return ☃;
  }
  
  public void a(oz ☃, pd ☃)
  {
    a(☃, ☃);
  }
  
  public void a(oy ☃, pd ☃)
  {
    a(this.b, ☃).add(☃);
  }
  
  public void a(oy ☃, ox ☃)
  {
    List<ox> ☃ = a(this.c, ☃);
    int ☃ = ☃.a();
    if (☃ > this.d)
    {
      a.warn("Ignored fix registered for version: {} as the DataVersion of the game is: {}", new Object[] { Integer.valueOf(☃), Integer.valueOf(this.d) });
      return;
    }
    if ((☃.isEmpty()) || (((ox)g.a(☃)).a() <= ☃)) {
      ☃.add(☃);
    } else {
      for (int ☃ = 0; ☃ < ☃.size(); ☃++) {
        if (((ox)☃.get(☃)).a() > ☃)
        {
          ☃.add(☃, ☃);
          break;
        }
      }
    }
  }
  
  private <V> List<V> a(Map<oy, List<V>> ☃, oy ☃)
  {
    List<V> ☃ = (List)☃.get(☃);
    if (☃ == null)
    {
      ☃ = Lists.newArrayList();
      ☃.put(☃, ☃);
    }
    return ☃;
  }
}
