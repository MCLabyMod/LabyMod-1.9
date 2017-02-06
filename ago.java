import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class ago
{
  private static final Random a = new Random();
  
  public static int a(agm ☃, adq ☃)
  {
    if (☃ == null) {
      return 0;
    }
    du ☃ = ☃.p();
    if (☃ == null) {
      return 0;
    }
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      agm ☃ = agm.c(☃.b(☃).g("id"));
      int ☃ = ☃.b(☃).g("lvl");
      if (☃ == ☃) {
        return ☃;
      }
    }
    return 0;
  }
  
  public static Map<agm, Integer> a(adq ☃)
  {
    Map<agm, Integer> ☃ = Maps.newLinkedHashMap();
    du ☃ = ☃.b() == ads.cn ? ads.cn.h(☃) : ☃.p();
    if (☃ != null) {
      for (int ☃ = 0; ☃ < ☃.c(); ☃++)
      {
        agm ☃ = agm.c(☃.b(☃).g("id"));
        int ☃ = ☃.b(☃).g("lvl");
        
        ☃.put(☃, Integer.valueOf(☃));
      }
    }
    return ☃;
  }
  
  public static void a(Map<agm, Integer> ☃, adq ☃)
  {
    du ☃ = new du();
    for (Map.Entry<agm, Integer> ☃ : ☃.entrySet())
    {
      agm ☃ = (agm)☃.getKey();
      if (☃ != null)
      {
        int ☃ = ((Integer)☃.getValue()).intValue();
        
        dn ☃ = new dn();
        ☃.a("id", (short)agm.b(☃));
        ☃.a("lvl", (short)☃);
        
        ☃.a(☃);
        if (☃.b() == ads.cn) {
          ads.cn.a(☃, new agp(☃, ☃));
        }
      }
    }
    if (☃.c_())
    {
      if (☃.n()) {
        ☃.o().q("ench");
      }
    }
    else if (☃.b() != ads.cn) {
      ☃.a("ench", ☃);
    }
  }
  
  private static void a(ago.c ☃, adq ☃)
  {
    if (☃ == null) {
      return;
    }
    du ☃ = ☃.p();
    if (☃ == null) {
      return;
    }
    for (int ☃ = 0; ☃ < ☃.c(); ☃++)
    {
      int ☃ = ☃.b(☃).g("id");
      int ☃ = ☃.b(☃).g("lvl");
      if (agm.c(☃) != null) {
        ☃.a(agm.c(☃), ☃);
      }
    }
  }
  
  private static void a(ago.c ☃, Iterable<adq> ☃)
  {
    for (adq ☃ : ☃) {
      a(☃, ☃);
    }
  }
  
  static abstract interface c
  {
    public abstract void a(agm paramagm, int paramInt);
  }
  
  static final class e
    implements ago.c
  {
    public int a;
    public rc b;
    
    public void a(agm ☃, int ☃)
    {
      this.a += ☃.a(☃, this.b);
    }
  }
  
  private static final ago.e b = new ago.e(null);
  
  public static int a(Iterable<adq> ☃, rc ☃)
  {
    b.a = 0;
    b.b = ☃;
    
    a(b, ☃);
    
    return b.a;
  }
  
  static final class d
    implements ago.c
  {
    public float a;
    public sf b;
    
    public void a(agm ☃, int ☃)
    {
      this.a += ☃.a(☃, this.b);
    }
  }
  
  private static final ago.d c = new ago.d(null);
  
  public static float a(adq ☃, sf ☃)
  {
    c.a = 0.0F;
    c.b = ☃;
    
    a(c, ☃);
    
    return c.a;
  }
  
  static final class b
    implements ago.c
  {
    public sa a;
    public rr b;
    
    public void a(agm ☃, int ☃)
    {
      ☃.b(this.a, this.b, ☃);
    }
  }
  
  private static final ago.b d = new ago.b(null);
  
  public static void a(sa ☃, rr ☃)
  {
    d.b = ☃;
    d.a = ☃;
    if (☃ != null) {
      a(d, ☃.aG());
    }
    if ((☃ instanceof zj)) {
      a(d, ☃.cb());
    }
  }
  
  static final class a
    implements ago.c
  {
    public sa a;
    public rr b;
    
    public void a(agm ☃, int ☃)
    {
      ☃.a(this.a, this.b, ☃);
    }
  }
  
  private static final ago.a e = new ago.a(null);
  
  public static void b(sa ☃, rr ☃)
  {
    e.a = ☃;
    e.b = ☃;
    if (☃ != null) {
      a(e, ☃.aG());
    }
    if ((☃ instanceof zj)) {
      a(e, ☃.cb());
    }
  }
  
  public static int a(agm ☃, sa ☃)
  {
    Iterable<adq> ☃ = ☃.a(☃);
    if (☃ == null) {
      return 0;
    }
    int ☃ = 0;
    for (adq ☃ : ☃)
    {
      int ☃ = a(☃, ☃);
      if (☃ > ☃) {
        ☃ = ☃;
      }
    }
    return ☃;
  }
  
  public static int a(sa ☃)
  {
    return a(agq.n, ☃);
  }
  
  public static int b(sa ☃)
  {
    return a(agq.o, ☃);
  }
  
  public static int c(sa ☃)
  {
    return a(agq.f, ☃);
  }
  
  public static int d(sa ☃)
  {
    return a(agq.i, ☃);
  }
  
  public static int e(sa ☃)
  {
    return a(agq.q, ☃);
  }
  
  public static int f(sa ☃)
  {
    return a(agq.y, ☃);
  }
  
  public static int g(sa ☃)
  {
    return a(agq.z, ☃);
  }
  
  public static int h(sa ☃)
  {
    return a(agq.p, ☃);
  }
  
  public static boolean i(sa ☃)
  {
    return a(agq.g, ☃) > 0;
  }
  
  public static adq b(agm ☃, sa ☃)
  {
    Iterable<adq> ☃ = ☃.a(☃);
    if (☃ == null) {
      return null;
    }
    List<adq> ☃ = Lists.newArrayList();
    for (adq ☃ : ☃) {
      if ((☃ != null) && (a(☃, ☃) > 0)) {
        ☃.add(☃);
      }
    }
    return ☃.isEmpty() ? null : (adq)☃.get(☃.bF().nextInt(☃.size()));
  }
  
  public static int a(Random ☃, int ☃, int ☃, adq ☃)
  {
    ado ☃ = ☃.b();
    int ☃ = ☃.c();
    if (☃ <= 0) {
      return 0;
    }
    if (☃ > 15) {
      ☃ = 15;
    }
    int ☃ = ☃.nextInt(8) + 1 + (☃ >> 1) + ☃.nextInt(☃ + 1);
    if (☃ == 0) {
      return Math.max(☃ / 3, 1);
    }
    if (☃ == 1) {
      return ☃ * 2 / 3 + 1;
    }
    return Math.max(☃, ☃ * 2);
  }
  
  public static adq a(Random ☃, adq ☃, int ☃, boolean ☃)
  {
    boolean ☃ = ☃.b() == ads.aS;
    List<agp> ☃ = b(☃, ☃, ☃, ☃);
    if (☃) {
      ☃.a(ads.cn);
    }
    for (agp ☃ : ☃) {
      if (☃) {
        ads.cn.a(☃, ☃);
      } else {
        ☃.a(☃.b, ☃.c);
      }
    }
    return ☃;
  }
  
  public static List<agp> b(Random ☃, adq ☃, int ☃, boolean ☃)
  {
    List<agp> ☃ = Lists.newArrayList();
    
    ado ☃ = ☃.b();
    int ☃ = ☃.c();
    if (☃ <= 0) {
      return ☃;
    }
    ☃ += 1 + ☃.nextInt(☃ / 4 + 1) + ☃.nextInt(☃ / 4 + 1);
    
    float ☃ = (☃.nextFloat() + ☃.nextFloat() - 1.0F) * 0.15F;
    ☃ = on.a(Math.round(☃ + ☃ * ☃), 1, Integer.MAX_VALUE);
    
    List<agp> ☃ = a(☃, ☃, ☃);
    if (!☃.isEmpty())
    {
      ☃.add(ov.a(☃, ☃));
      while (☃.nextInt(50) <= ☃)
      {
        a(☃, (agp)g.a(☃));
        if (☃.isEmpty()) {
          break;
        }
        ☃.add(ov.a(☃, ☃));
        ☃ /= 2;
      }
    }
    return ☃;
  }
  
  public static void a(List<agp> ☃, agp ☃)
  {
    Iterator<agp> ☃ = ☃.iterator();
    while (☃.hasNext()) {
      if (!☃.b.a(((agp)☃.next()).b)) {
        ☃.remove();
      }
    }
  }
  
  public static List<agp> a(int ☃, adq ☃, boolean ☃)
  {
    List<agp> ☃ = Lists.newArrayList();
    
    ado ☃ = ☃.b();
    boolean ☃ = ☃.b() == ads.aS;
    for (agm ☃ : agm.b) {
      if (((!☃.e()) || (☃)) && (
      
        (☃.c.a(☃)) || (☃))) {
        for (int ☃ = ☃.b(); ☃ > ☃.d() - 1; ☃--) {
          if ((☃ >= ☃.a(☃)) && (☃ <= ☃.b(☃)))
          {
            ☃.add(new agp(☃, ☃));
            break;
          }
        }
      }
    }
    return ☃;
  }
}
