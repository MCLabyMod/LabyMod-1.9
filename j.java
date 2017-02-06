import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class j
  implements l
{
  private static final Logger a = ;
  private final Map<String, k> b = Maps.newHashMap();
  private final Set<k> c = Sets.newHashSet();
  
  public int a(m ☃, String ☃)
  {
    ☃ = ☃.trim();
    if (☃.startsWith("/")) {
      ☃ = ☃.substring(1);
    }
    String[] ☃ = ☃.split(" ");
    String ☃ = ☃[0];
    
    ☃ = a(☃);
    
    k ☃ = (k)this.b.get(☃);
    int ☃ = a(☃, ☃);
    int ☃ = 0;
    if (☃ == null)
    {
      fb ☃ = new fb("commands.generic.notFound", new Object[0]);
      ☃.b().a(a.m);
      ☃.a(☃);
    }
    else if (☃.a(a(), ☃))
    {
      if (☃ > -1)
      {
        List<rr> ☃ = o.b(☃, ☃[☃], rr.class);
        String ☃ = ☃[☃];
        ☃.a(n.a.c, ☃.size());
        for (rr ☃ : ☃)
        {
          ☃[☃] = ☃.bc().toString();
          if (a(☃, ☃, ☃, ☃)) {
            ☃++;
          }
        }
        ☃[☃] = ☃;
      }
      else
      {
        ☃.a(n.a.c, 1);
        if (a(☃, ☃, ☃, ☃)) {
          ☃++;
        }
      }
    }
    else
    {
      fb ☃ = new fb("commands.generic.permission", new Object[0]);
      ☃.b().a(a.m);
      ☃.a(☃);
    }
    ☃.a(n.a.a, ☃);
    return ☃;
  }
  
  protected boolean a(m ☃, String[] ☃, k ☃, String ☃)
  {
    try
    {
      ☃.a(a(), ☃, ☃);
      return true;
    }
    catch (cf ☃)
    {
      fb ☃ = new fb("commands.generic.usage", new Object[] { new fb(☃.getMessage(), ☃.a()) });
      ☃.b().a(a.m);
      ☃.a(☃);
    }
    catch (bz ☃)
    {
      fb ☃ = new fb(☃.getMessage(), ☃.a());
      ☃.b().a(a.m);
      ☃.a(☃);
    }
    catch (Throwable ☃)
    {
      fb ☃ = new fb("commands.generic.exception", new Object[0]);
      ☃.b().a(a.m);
      ☃.a(☃);
      a.warn("Couldn't process command: '" + ☃ + "'");
    }
    return false;
  }
  
  protected abstract MinecraftServer a();
  
  public k a(k ☃)
  {
    this.b.put(☃.c(), ☃);
    this.c.add(☃);
    for (String ☃ : ☃.b())
    {
      k ☃ = (k)this.b.get(☃);
      if ((☃ == null) || (!☃.c().equals(☃))) {
        this.b.put(☃, ☃);
      }
    }
    return ☃;
  }
  
  private static String[] a(String[] ☃)
  {
    String[] ☃ = new String[☃.length - 1];
    System.arraycopy(☃, 1, ☃, 0, ☃.length - 1);
    return ☃;
  }
  
  public List<String> a(m ☃, String ☃, cj ☃)
  {
    String[] ☃ = ☃.split(" ", -1);
    String ☃ = ☃[0];
    if (☃.length == 1)
    {
      List<String> ☃ = Lists.newArrayList();
      for (Map.Entry<String, k> ☃ : this.b.entrySet()) {
        if ((i.a(☃, (String)☃.getKey())) && (((k)☃.getValue()).a(a(), ☃))) {
          ☃.add(☃.getKey());
        }
      }
      return ☃;
    }
    if (☃.length > 1)
    {
      k ☃ = (k)this.b.get(☃);
      if ((☃ != null) && (☃.a(a(), ☃))) {
        return ☃.a(a(), ☃, a(☃), ☃);
      }
    }
    return Collections.emptyList();
  }
  
  public List<k> a(m ☃)
  {
    List<k> ☃ = Lists.newArrayList();
    for (k ☃ : this.c) {
      if (☃.a(a(), ☃)) {
        ☃.add(☃);
      }
    }
    return ☃;
  }
  
  public Map<String, k> b()
  {
    return this.b;
  }
  
  private int a(k ☃, String[] ☃)
  {
    if (☃ == null) {
      return -1;
    }
    for (int ☃ = 0; ☃ < ☃.length; ☃++) {
      if ((☃.b(☃, ☃)) && (o.a(☃[☃]))) {
        return ☃;
      }
    }
    return -1;
  }
}
