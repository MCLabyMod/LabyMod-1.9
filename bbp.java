import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class bbp
{
  private final Map<String, bbl> a = Maps.newHashMap();
  private final Map<bbv, List<bbl>> b = Maps.newHashMap();
  private final Map<String, Map<bbl, bbn>> c = Maps.newHashMap();
  private final bbl[] d = new bbl[19];
  private final Map<String, bbm> e = Maps.newHashMap();
  private final Map<String, bbm> f = Maps.newHashMap();
  
  public bbl b(String ☃)
  {
    return (bbl)this.a.get(☃);
  }
  
  public bbl a(String ☃, bbv ☃)
  {
    if (☃.length() > 16) {
      throw new IllegalArgumentException("The objective name '" + ☃ + "' is too long!");
    }
    bbl ☃ = b(☃);
    if (☃ != null) {
      throw new IllegalArgumentException("An objective with the name '" + ☃ + "' already exists!");
    }
    ☃ = new bbl(this, ☃, ☃);
    
    List<bbl> ☃ = (List)this.b.get(☃);
    if (☃ == null)
    {
      ☃ = Lists.newArrayList();
      this.b.put(☃, ☃);
    }
    ☃.add(☃);
    this.a.put(☃, ☃);
    a(☃);
    
    return ☃;
  }
  
  public Collection<bbl> a(bbv ☃)
  {
    Collection<bbl> ☃ = (Collection)this.b.get(☃);
    
    return ☃ == null ? Lists.newArrayList() : Lists.newArrayList(☃);
  }
  
  public boolean b(String ☃, bbl ☃)
  {
    Map<bbl, bbn> ☃ = (Map)this.c.get(☃);
    if (☃ == null) {
      return false;
    }
    bbn ☃ = (bbn)☃.get(☃);
    if (☃ == null) {
      return false;
    }
    return true;
  }
  
  public bbn c(String ☃, bbl ☃)
  {
    if (☃.length() > 40) {
      throw new IllegalArgumentException("The player name '" + ☃ + "' is too long!");
    }
    Map<bbl, bbn> ☃ = (Map)this.c.get(☃);
    if (☃ == null)
    {
      ☃ = Maps.newHashMap();
      this.c.put(☃, ☃);
    }
    bbn ☃ = (bbn)☃.get(☃);
    if (☃ == null)
    {
      ☃ = new bbn(this, ☃, ☃);
      ☃.put(☃, ☃);
    }
    return ☃;
  }
  
  public Collection<bbn> i(bbl ☃)
  {
    List<bbn> ☃ = Lists.newArrayList();
    for (Map<bbl, bbn> ☃ : this.c.values())
    {
      bbn ☃ = (bbn)☃.get(☃);
      if (☃ != null) {
        ☃.add(☃);
      }
    }
    Collections.sort(☃, bbn.a);
    
    return ☃;
  }
  
  public Collection<bbl> c()
  {
    return this.a.values();
  }
  
  public Collection<String> d()
  {
    return this.c.keySet();
  }
  
  public void d(String ☃, bbl ☃)
  {
    if (☃ == null)
    {
      Map<bbl, bbn> ☃ = (Map)this.c.remove(☃);
      if (☃ != null) {
        a(☃);
      }
    }
    else
    {
      Map<bbl, bbn> ☃ = (Map)this.c.get(☃);
      if (☃ != null)
      {
        bbn ☃ = (bbn)☃.remove(☃);
        if (☃.size() < 1)
        {
          Map<bbl, bbn> ☃ = (Map)this.c.remove(☃);
          if (☃ != null) {
            a(☃);
          }
        }
        else if (☃ != null)
        {
          a(☃, ☃);
        }
      }
    }
  }
  
  public Collection<bbn> e()
  {
    Collection<Map<bbl, bbn>> ☃ = this.c.values();
    List<bbn> ☃ = Lists.newArrayList();
    for (Map<bbl, bbn> ☃ : ☃) {
      ☃.addAll(☃.values());
    }
    return ☃;
  }
  
  public Map<bbl, bbn> c(String ☃)
  {
    Map<bbl, bbn> ☃ = (Map)this.c.get(☃);
    if (☃ == null) {
      ☃ = Maps.newHashMap();
    }
    return ☃;
  }
  
  public void k(bbl ☃)
  {
    this.a.remove(☃.b());
    for (int ☃ = 0; ☃ < 19; ☃++) {
      if (a(☃) == ☃) {
        a(☃, null);
      }
    }
    List<bbl> ☃ = (List)this.b.get(☃.c());
    if (☃ != null) {
      ☃.remove(☃);
    }
    for (Map<bbl, bbn> ☃ : this.c.values()) {
      ☃.remove(☃);
    }
    c(☃);
  }
  
  public void a(int ☃, bbl ☃)
  {
    this.d[☃] = ☃;
  }
  
  public bbl a(int ☃)
  {
    return this.d[☃];
  }
  
  public bbm d(String ☃)
  {
    return (bbm)this.e.get(☃);
  }
  
  public bbm e(String ☃)
  {
    if (☃.length() > 16) {
      throw new IllegalArgumentException("The team name '" + ☃ + "' is too long!");
    }
    bbm ☃ = d(☃);
    if (☃ != null) {
      throw new IllegalArgumentException("A team with the name '" + ☃ + "' already exists!");
    }
    ☃ = new bbm(this, ☃);
    this.e.put(☃, ☃);
    a(☃);
    
    return ☃;
  }
  
  public void d(bbm ☃)
  {
    this.e.remove(☃.b());
    for (String ☃ : ☃.d()) {
      this.f.remove(☃);
    }
    c(☃);
  }
  
  public boolean a(String ☃, String ☃)
  {
    if (☃.length() > 40) {
      throw new IllegalArgumentException("The player name '" + ☃ + "' is too long!");
    }
    if (!this.e.containsKey(☃)) {
      return false;
    }
    bbm ☃ = d(☃);
    if (g(☃) != null) {
      f(☃);
    }
    this.f.put(☃, ☃);
    ☃.d().add(☃);
    return true;
  }
  
  public boolean f(String ☃)
  {
    bbm ☃ = g(☃);
    if (☃ != null)
    {
      a(☃, ☃);
      return true;
    }
    return false;
  }
  
  public void a(String ☃, bbm ☃)
  {
    if (g(☃) != ☃) {
      throw new IllegalStateException("Player is either on another team or not on any team. Cannot remove from team '" + ☃.b() + "'.");
    }
    this.f.remove(☃);
    ☃.d().remove(☃);
  }
  
  public Collection<String> f()
  {
    return this.e.keySet();
  }
  
  public Collection<bbm> g()
  {
    return this.e.values();
  }
  
  public bbm g(String ☃)
  {
    return (bbm)this.f.get(☃);
  }
  
  public void a(bbl ☃) {}
  
  public void b(bbl ☃) {}
  
  public void c(bbl ☃) {}
  
  public void a(bbn ☃) {}
  
  public void a(String ☃) {}
  
  public void a(String ☃, bbl ☃) {}
  
  public void a(bbm ☃) {}
  
  public void b(bbm ☃) {}
  
  public void c(bbm ☃) {}
  
  public static String b(int ☃)
  {
    switch (☃)
    {
    case 0: 
      return "list";
    case 1: 
      return "sidebar";
    case 2: 
      return "belowName";
    }
    if ((☃ >= 3) && (☃ <= 18))
    {
      a ☃ = a.a(☃ - 3);
      if ((☃ != null) && (☃ != a.v)) {
        return "sidebar.team." + ☃.e();
      }
    }
    return null;
  }
  
  public static int h(String ☃)
  {
    if (☃.equalsIgnoreCase("list")) {
      return 0;
    }
    if (☃.equalsIgnoreCase("sidebar")) {
      return 1;
    }
    if (☃.equalsIgnoreCase("belowName")) {
      return 2;
    }
    if (☃.startsWith("sidebar.team."))
    {
      String ☃ = ☃.substring("sidebar.team.".length());
      a ☃ = a.b(☃);
      if ((☃ != null) && (☃.b() >= 0)) {
        return ☃.b() + 3;
      }
    }
    return -1;
  }
  
  private static String[] g = null;
  
  public static String[] h()
  {
    if (g == null)
    {
      g = new String[19];
      for (int ☃ = 0; ☃ < 19; ☃++) {
        g[☃] = b(☃);
      }
    }
    return g;
  }
  
  public void a(rr ☃)
  {
    if ((☃ == null) || ((☃ instanceof zj)) || (☃.au())) {
      return;
    }
    String ☃ = ☃.bc().toString();
    d(☃, null);
    f(☃);
  }
}
