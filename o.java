import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.server.MinecraftServer;

public class o
{
  private static final Pattern a = Pattern.compile("^@([pare])(?:\\[([\\w=,!-]*)\\])?$");
  private static final Pattern b = Pattern.compile("\\G([-!]?[\\w-]*)(?:$|,)");
  private static final Pattern c = Pattern.compile("\\G(\\w+)=([-!]?[\\w-]*)(?:$|,)");
  private static final Set<String> d = Sets.newHashSet(new String[] { "x", "y", "z", "dx", "dy", "dz", "rm", "r" });
  
  public static lr a(m ☃, String ☃)
  {
    return (lr)a(☃, ☃, lr.class);
  }
  
  public static <T extends rr> T a(m ☃, String ☃, Class<? extends T> ☃)
  {
    List<T> ☃ = b(☃, ☃, ☃);
    return ☃.size() == 1 ? (rr)☃.get(0) : null;
  }
  
  public static eu b(m ☃, String ☃)
  {
    List<rr> ☃ = b(☃, ☃, rr.class);
    if (☃.isEmpty()) {
      return null;
    }
    List<eu> ☃ = Lists.newArrayList();
    for (rr ☃ : ☃) {
      ☃.add(☃.i_());
    }
    return i.a(☃);
  }
  
  public static <T extends rr> List<T> b(m ☃, String ☃, Class<? extends T> ☃)
  {
    Matcher ☃ = a.matcher(☃);
    if ((☃.matches()) && (☃.a(1, "@")))
    {
      Map<String, String> ☃ = c(☃.group(2));
      if (!b(☃, ☃)) {
        return Collections.emptyList();
      }
      String ☃ = ☃.group(1);
      cj ☃ = a(☃, ☃.c());
      bbj ☃ = b(☃, ☃.d());
      List<aht> ☃ = a(☃, ☃);
      List<T> ☃ = Lists.newArrayList();
      for (aht ☃ : ☃) {
        if (☃ != null)
        {
          List<Predicate<rr>> ☃ = Lists.newArrayList();
          ☃.addAll(a(☃, ☃));
          ☃.addAll(b(☃));
          ☃.addAll(c(☃));
          ☃.addAll(d(☃));
          ☃.addAll(c(☃, ☃));
          ☃.addAll(e(☃));
          ☃.addAll(f(☃));
          ☃.addAll(a(☃, ☃));
          ☃.addAll(g(☃));
          
          ☃.addAll(a(☃, ☃, ☃, ☃, ☃, ☃));
        }
      }
      return a(☃, ☃, ☃, ☃, ☃, ☃);
    }
    return Collections.emptyList();
  }
  
  private static List<aht> a(m ☃, Map<String, String> ☃)
  {
    List<aht> ☃ = Lists.newArrayList();
    if (h(☃)) {
      ☃.add(☃.e());
    } else {
      Collections.addAll(☃, ☃.h().d);
    }
    return ☃;
  }
  
  private static <T extends rr> boolean b(m ☃, Map<String, String> ☃)
  {
    String ☃ = b(☃, "type");
    ☃ = (☃ != null) && (☃.startsWith("!")) ? ☃.substring(1) : ☃;
    if ((☃ != null) && (!rt.b(☃)))
    {
      fb ☃ = new fb("commands.generic.entity.invalidType", new Object[] { ☃ });
      ☃.b().a(a.m);
      ☃.a(☃);
      return false;
    }
    return true;
  }
  
  private static List<Predicate<rr>> a(Map<String, String> ☃, String ☃)
  {
    List<Predicate<rr>> ☃ = Lists.newArrayList();
    String ☃ = b(☃, "type");
    final boolean ☃ = (☃ != null) && (☃.startsWith("!"));
    if (☃) {
      ☃ = ☃.substring(1);
    }
    String ☃ = ☃;
    
    boolean ☃ = !☃.equals("e");
    boolean ☃ = (☃.equals("r")) && (☃ != null);
    if (((☃ != null) && (☃.equals("e"))) || (☃)) {
      ☃.add(new Predicate()
      {
        public boolean a(rr ☃)
        {
          return rt.a(☃, this.a) != ☃;
        }
      });
    } else if (☃) {
      ☃.add(new Predicate()
      {
        public boolean a(rr ☃)
        {
          return ☃ instanceof zj;
        }
      });
    }
    return ☃;
  }
  
  private static List<Predicate<rr>> b(Map<String, String> ☃)
  {
    List<Predicate<rr>> ☃ = Lists.newArrayList();
    int ☃ = a(☃, "lm", -1);
    final int ☃ = a(☃, "l", -1);
    if ((☃ > -1) || (☃ > -1)) {
      ☃.add(new Predicate()
      {
        public boolean a(rr ☃)
        {
          if (!(☃ instanceof lr)) {
            return false;
          }
          lr ☃ = (lr)☃;
          return ((this.a <= -1) || (☃.bK >= this.a)) && ((☃ <= -1) || (☃.bK <= ☃));
        }
      });
    }
    return ☃;
  }
  
  private static List<Predicate<rr>> c(Map<String, String> ☃)
  {
    List<Predicate<rr>> ☃ = Lists.newArrayList();
    String ☃ = b(☃, "m");
    if (☃ == null) {
      return ☃;
    }
    boolean ☃ = ☃.startsWith("!");
    if (☃) {
      ☃ = ☃.substring(1);
    }
    ahw.a ☃;
    try
    {
      int ☃ = Integer.parseInt(☃);
      ☃ = ahw.a.a(☃, ahw.a.a);
    }
    catch (Throwable ☃)
    {
      ☃ = ahw.a.a(☃, ahw.a.a);
    }
    final ahw.a ☃ = ☃;
    ☃.add(new Predicate()
    {
      public boolean a(rr ☃)
      {
        if (!(☃ instanceof lr)) {
          return false;
        }
        lr ☃ = (lr)☃;
        ahw.a ☃ = ☃.c.b();
        return ☃ != ☃;
      }
    });
    return ☃;
  }
  
  private static List<Predicate<rr>> d(Map<String, String> ☃)
  {
    List<Predicate<rr>> ☃ = Lists.newArrayList();
    String ☃ = b(☃, "team");
    final boolean ☃ = (☃ != null) && (☃.startsWith("!"));
    if (☃) {
      ☃ = ☃.substring(1);
    }
    String ☃ = ☃;
    if (☃ != null) {
      ☃.add(new Predicate()
      {
        public boolean a(rr ☃)
        {
          if (!(☃ instanceof sa)) {
            return false;
          }
          sa ☃ = (sa)☃;
          bbr ☃ = ☃.aO();
          String ☃ = ☃ == null ? "" : ☃.b();
          return ☃.equals(this.a) != ☃;
        }
      });
    }
    return ☃;
  }
  
  private static List<Predicate<rr>> c(m ☃, Map<String, String> ☃)
  {
    final Map<String, Integer> ☃ = a(☃);
    if (☃.isEmpty()) {
      return Collections.emptyList();
    }
    Lists.newArrayList(new Predicate[] { new Predicate()
    {
      public boolean a(rr ☃)
      {
        if (☃ == null) {
          return false;
        }
        bbp ☃ = this.a.h().a(0).ad();
        for (Map.Entry<String, Integer> ☃ : ☃.entrySet())
        {
          String ☃ = (String)☃.getKey();
          boolean ☃ = false;
          if ((☃.endsWith("_min")) && (☃.length() > 4))
          {
            ☃ = true;
            ☃ = ☃.substring(0, ☃.length() - 4);
          }
          bbl ☃ = ☃.b(☃);
          if (☃ == null) {
            return false;
          }
          String ☃ = (☃ instanceof lr) ? ☃.h_() : ☃.bc().toString();
          if (!☃.b(☃, ☃)) {
            return false;
          }
          bbn ☃ = ☃.c(☃, ☃);
          int ☃ = ☃.c();
          if ((☃ < ((Integer)☃.getValue()).intValue()) && (☃)) {
            return false;
          }
          if ((☃ > ((Integer)☃.getValue()).intValue()) && (!☃)) {
            return false;
          }
        }
        return true;
      }
    } });
  }
  
  private static List<Predicate<rr>> e(Map<String, String> ☃)
  {
    List<Predicate<rr>> ☃ = Lists.newArrayList();
    String ☃ = b(☃, "name");
    final boolean ☃ = (☃ != null) && (☃.startsWith("!"));
    if (☃) {
      ☃ = ☃.substring(1);
    }
    String ☃ = ☃;
    if (☃ != null) {
      ☃.add(new Predicate()
      {
        public boolean a(rr ☃)
        {
          return (☃ != null) && (☃.h_().equals(this.a) != ☃);
        }
      });
    }
    return ☃;
  }
  
  private static List<Predicate<rr>> f(Map<String, String> ☃)
  {
    List<Predicate<rr>> ☃ = Lists.newArrayList();
    String ☃ = b(☃, "tag");
    final boolean ☃ = (☃ != null) && (☃.startsWith("!"));
    if (☃) {
      ☃ = ☃.substring(1);
    }
    if (☃ != null)
    {
      String ☃ = ☃;
      ☃.add(new Predicate()
      {
        public boolean a(rr ☃)
        {
          if (☃ == null) {
            return false;
          }
          if ("".equals(this.a)) {
            return ☃.P().isEmpty() != ☃;
          }
          return ☃.P().contains(this.a) != ☃;
        }
      });
    }
    return ☃;
  }
  
  private static List<Predicate<rr>> a(Map<String, String> ☃, bbj ☃)
  {
    double ☃ = a(☃, "rm", -1);
    double ☃ = a(☃, "r", -1);
    
    final boolean ☃ = ☃ < -0.5D;
    boolean ☃ = ☃ < -0.5D;
    if ((☃) && (☃)) {
      return Collections.emptyList();
    }
    double ☃ = Math.max(☃, 1.0E-4D);
    final double ☃ = ☃ * ☃;
    double ☃ = Math.max(☃, 1.0E-4D);
    final double ☃ = ☃ * ☃;
    
    Lists.newArrayList(new Predicate[] { new Predicate()
    {
      public boolean a(rr ☃)
      {
        if (☃ == null) {
          return false;
        }
        double ☃ = this.a.c(☃.p, ☃.q, ☃.r);
        return ((☃) || (☃ >= ☃)) && ((☃) || (☃ <= this.e));
      }
    } });
  }
  
  private static List<Predicate<rr>> g(Map<String, String> ☃)
  {
    List<Predicate<rr>> ☃ = Lists.newArrayList();
    if ((☃.containsKey("rym")) || (☃.containsKey("ry")))
    {
      int ☃ = on.b(a(☃, "rym", 0));
      final int ☃ = on.b(a(☃, "ry", 359));
      
      ☃.add(new Predicate()
      {
        public boolean a(rr ☃)
        {
          if (☃ == null) {
            return false;
          }
          int ☃ = on.b(on.d(☃.v));
          if (this.a > ☃) {
            return (☃ >= this.a) || (☃ <= ☃);
          }
          return (☃ >= this.a) && (☃ <= ☃);
        }
      });
    }
    if ((☃.containsKey("rxm")) || (☃.containsKey("rx")))
    {
      int ☃ = on.b(a(☃, "rxm", 0));
      final int ☃ = on.b(a(☃, "rx", 359));
      
      ☃.add(new Predicate()
      {
        public boolean a(rr ☃)
        {
          if (☃ == null) {
            return false;
          }
          int ☃ = on.b(on.d(☃.w));
          if (this.a > ☃) {
            return (☃ >= this.a) || (☃ <= ☃);
          }
          return (☃ >= this.a) && (☃ <= ☃);
        }
      });
    }
    return ☃;
  }
  
  private static <T extends rr> List<T> a(Map<String, String> ☃, Class<? extends T> ☃, List<Predicate<rr>> ☃, String ☃, aht ☃, cj ☃)
  {
    List<T> ☃ = Lists.newArrayList();
    String ☃ = b(☃, "type");
    ☃ = (☃ != null) && (☃.startsWith("!")) ? ☃.substring(1) : ☃;
    
    boolean ☃ = !☃.equals("e");
    boolean ☃ = (☃.equals("r")) && (☃ != null);
    
    int ☃ = a(☃, "dx", 0);
    int ☃ = a(☃, "dy", 0);
    int ☃ = a(☃, "dz", 0);
    
    int ☃ = a(☃, "r", -1);
    
    Predicate<rr> ☃ = Predicates.and(☃);
    Predicate<rr> ☃ = Predicates.and(rv.a, ☃);
    int ☃ = ☃.i.size();
    int ☃ = ☃.e.size();
    boolean ☃ = ☃ < ☃ / 16;
    if ((☃.containsKey("dx")) || (☃.containsKey("dy")) || (☃.containsKey("dz")))
    {
      bbh ☃ = a(☃, ☃, ☃, ☃);
      if ((☃) && (☃) && (!☃))
      {
        Predicate<rr> ☃ = new Predicate()
        {
          public boolean a(rr ☃)
          {
            return (☃ != null) && (this.a.b(☃.bl()));
          }
        };
        ☃.addAll(☃.b(☃, Predicates.and(☃, ☃)));
      }
      else
      {
        ☃.addAll(☃.a(☃, ☃, ☃));
      }
    }
    else if (☃ >= 0)
    {
      bbh ☃ = new bbh(☃.p() - ☃, ☃.q() - ☃, ☃.r() - ☃, ☃.p() + ☃ + 1, ☃.q() + ☃ + 1, ☃.r() + ☃ + 1);
      if ((☃) && (☃) && (!☃)) {
        ☃.addAll(☃.b(☃, ☃));
      } else {
        ☃.addAll(☃.a(☃, ☃, ☃));
      }
    }
    else if (☃.equals("a"))
    {
      ☃.addAll(☃.b(☃, ☃));
    }
    else if ((☃.equals("p")) || ((☃.equals("r")) && (!☃)))
    {
      ☃.addAll(☃.b(☃, ☃));
    }
    else
    {
      ☃.addAll(☃.a(☃, ☃));
    }
    return ☃;
  }
  
  private static <T extends rr> List<T> a(List<T> ☃, Map<String, String> ☃, m ☃, Class<? extends T> ☃, String ☃, cj ☃)
  {
    int ☃ = a(☃, "c", (☃.equals("a")) || (☃.equals("e")) ? 0 : 1);
    if ((☃.equals("p")) || (☃.equals("a")) || (☃.equals("e"))) {
      Collections.sort(☃, new Comparator()
      {
        public int a(rr ☃, rr ☃)
        {
          return ComparisonChain.start().compare(☃.c(this.a), ☃.c(this.a)).result();
        }
      });
    } else if (☃.equals("r")) {
      Collections.shuffle(☃);
    }
    rr ☃ = ☃.f();
    if ((☃ != null) && (☃.isAssignableFrom(☃.getClass())) && (☃ == 1) && (☃.contains(☃)) && (!"r".equals(☃))) {
      ☃ = Lists.newArrayList(new rr[] { ☃ });
    }
    if (☃ != 0)
    {
      if (☃ < 0) {
        Collections.reverse(☃);
      }
      ☃ = ☃.subList(0, Math.min(Math.abs(☃), ☃.size()));
    }
    return ☃;
  }
  
  private static bbh a(cj ☃, int ☃, int ☃, int ☃)
  {
    boolean ☃ = ☃ < 0;
    boolean ☃ = ☃ < 0;
    boolean ☃ = ☃ < 0;
    int ☃ = ☃.p() + (☃ ? ☃ : 0);
    int ☃ = ☃.q() + (☃ ? ☃ : 0);
    int ☃ = ☃.r() + (☃ ? ☃ : 0);
    int ☃ = ☃.p() + (☃ ? 0 : ☃) + 1;
    int ☃ = ☃.q() + (☃ ? 0 : ☃) + 1;
    int ☃ = ☃.r() + (☃ ? 0 : ☃) + 1;
    return new bbh(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  private static cj a(Map<String, String> ☃, cj ☃)
  {
    return new cj(a(☃, "x", ☃.p()), a(☃, "y", ☃.q()), a(☃, "z", ☃.r()));
  }
  
  private static bbj b(Map<String, String> ☃, bbj ☃)
  {
    return new bbj(a(☃, "x", ☃.b, true), a(☃, "y", ☃.c, false), a(☃, "z", ☃.d, true));
  }
  
  private static double a(Map<String, String> ☃, String ☃, double ☃, boolean ☃)
  {
    return ☃.containsKey(☃) ? on.a((String)☃.get(☃), on.c(☃)) + (☃ ? 0.5D : 0.0D) : ☃;
  }
  
  private static boolean h(Map<String, String> ☃)
  {
    for (String ☃ : d) {
      if (☃.containsKey(☃)) {
        return true;
      }
    }
    return false;
  }
  
  private static int a(Map<String, String> ☃, String ☃, int ☃)
  {
    return ☃.containsKey(☃) ? on.a((String)☃.get(☃), ☃) : ☃;
  }
  
  private static String b(Map<String, String> ☃, String ☃)
  {
    return (String)☃.get(☃);
  }
  
  public static Map<String, Integer> a(Map<String, String> ☃)
  {
    Map<String, Integer> ☃ = Maps.newHashMap();
    for (String ☃ : ☃.keySet()) {
      if ((☃.startsWith("score_")) && (☃.length() > "score_".length())) {
        ☃.put(☃.substring("score_".length()), Integer.valueOf(on.a((String)☃.get(☃), 1)));
      }
    }
    return ☃;
  }
  
  public static boolean a(String ☃)
  {
    Matcher ☃ = a.matcher(☃);
    if (☃.matches())
    {
      Map<String, String> ☃ = c(☃.group(2));
      String ☃ = ☃.group(1);
      int ☃ = ("a".equals(☃)) || ("e".equals(☃)) ? 0 : 1;
      return a(☃, "c", ☃) != 1;
    }
    return false;
  }
  
  public static boolean b(String ☃)
  {
    return a.matcher(☃).matches();
  }
  
  private static Map<String, String> c(String ☃)
  {
    Map<String, String> ☃ = Maps.newHashMap();
    if (☃ == null) {
      return ☃;
    }
    int ☃ = 0;
    int ☃ = -1;
    
    Matcher ☃ = b.matcher(☃);
    while (☃.find())
    {
      String ☃ = null;
      switch (☃++)
      {
      case 0: 
        ☃ = "x";
        break;
      case 1: 
        ☃ = "y";
        break;
      case 2: 
        ☃ = "z";
        break;
      case 3: 
        ☃ = "r";
      }
      if ((☃ != null) && (!☃.group(1).isEmpty())) {
        ☃.put(☃, ☃.group(1));
      }
      ☃ = ☃.end();
    }
    if (☃ < ☃.length())
    {
      Matcher ☃ = c.matcher(☃ == -1 ? ☃ : ☃.substring(☃));
      while (☃.find()) {
        ☃.put(☃.group(1), ☃.group(2));
      }
    }
    return ☃;
  }
}
