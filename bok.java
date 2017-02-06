import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bok
{
  private static final Logger f = ;
  static final Gson a = new GsonBuilder().registerTypeAdapter(bok.class, new bok.b()).registerTypeAdapter(bog.class, new bog.a()).registerTypeAdapter(boh.class, new boh.a()).registerTypeAdapter(boj.class, new boj.a()).registerTypeAdapter(bor.class, new bor.a()).registerTypeAdapter(bos.class, new bos.a()).registerTypeAdapter(bop.class, new bop.a()).create();
  private final List<bog> g;
  private final boolean h;
  private final boolean i;
  private bos j;
  private final List<bop> k;
  public String b = "";
  protected final Map<String, String> c;
  protected bok d;
  protected kk e;
  
  public static bok a(Reader ☃)
  {
    return (bok)od.a(a, ☃, bok.class, false);
  }
  
  public static bok a(String ☃)
  {
    return a(new StringReader(☃));
  }
  
  public bok(kk ☃, List<bog> ☃, Map<String, String> ☃, boolean ☃, boolean ☃, bos ☃, List<bop> ☃)
  {
    this.g = ☃;
    this.i = ☃;
    this.h = ☃;
    this.c = ☃;
    this.e = ☃;
    this.j = ☃;
    this.k = ☃;
  }
  
  public List<bog> a()
  {
    if ((this.g.isEmpty()) && (k())) {
      return this.d.a();
    }
    return this.g;
  }
  
  private boolean k()
  {
    return this.d != null;
  }
  
  public boolean b()
  {
    if (k()) {
      return this.d.b();
    }
    return this.i;
  }
  
  public boolean c()
  {
    return this.h;
  }
  
  public boolean d()
  {
    return (this.e == null) || ((this.d != null) && (this.d.d()));
  }
  
  public void a(Map<kk, bok> ☃)
  {
    if (this.e != null) {
      this.d = ((bok)☃.get(this.e));
    }
  }
  
  public Collection<kk> e()
  {
    Set<kk> ☃ = Sets.newHashSet();
    for (bop ☃ : this.k) {
      ☃.add(☃.a());
    }
    return ☃;
  }
  
  protected List<bop> f()
  {
    return this.k;
  }
  
  public boq g()
  {
    if (this.k.isEmpty()) {
      return boq.a;
    }
    return new boq(this.k);
  }
  
  static final class a
  {
    public final bok a;
    public bok b;
    
    private a(bok ☃)
    {
      this.a = ☃;
    }
  }
  
  public boolean b(String ☃)
  {
    return !"missingno".equals(c(☃));
  }
  
  public String c(String ☃)
  {
    if (!d(☃)) {
      ☃ = '#' + ☃;
    }
    return a(☃, new bok.a(this, null));
  }
  
  private String a(String ☃, bok.a ☃)
  {
    if (d(☃))
    {
      if (this == ☃.b)
      {
        f.warn("Unable to resolve texture due to upward reference: " + ☃ + " in " + this.b);
        return "missingno";
      }
      String ☃ = (String)this.c.get(☃.substring(1));
      if ((☃ == null) && (k())) {
        ☃ = this.d.a(☃, ☃);
      }
      ☃.b = this;
      if ((☃ != null) && (d(☃))) {
        ☃ = ☃.a.a(☃, ☃);
      }
      if ((☃ == null) || (d(☃))) {
        return "missingno";
      }
      return ☃;
    }
    return ☃;
  }
  
  private boolean d(String ☃)
  {
    return ☃.charAt(0) == '#';
  }
  
  public kk h()
  {
    return this.e;
  }
  
  public bok i()
  {
    return k() ? this.d.i() : this;
  }
  
  public bos j()
  {
    bor ☃ = a(bos.b.b);
    bor ☃ = a(bos.b.c);
    bor ☃ = a(bos.b.d);
    bor ☃ = a(bos.b.e);
    bor ☃ = a(bos.b.f);
    bor ☃ = a(bos.b.g);
    bor ☃ = a(bos.b.h);
    bor ☃ = a(bos.b.i);
    return new bos(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  private bor a(bos.b ☃)
  {
    if ((this.d != null) && (!this.j.c(☃))) {
      return this.d.a(☃);
    }
    return this.j.b(☃);
  }
  
  public static class b
    implements JsonDeserializer<bok>
  {
    public bok a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
      throws JsonParseException
    {
      JsonObject ☃ = ☃.getAsJsonObject();
      
      List<bog> ☃ = b(☃, ☃);
      String ☃ = c(☃);
      
      Map<String, String> ☃ = b(☃);
      boolean ☃ = a(☃);
      
      bos ☃ = bos.a;
      if (☃.has("display"))
      {
        JsonObject ☃ = od.t(☃, "display");
        ☃ = (bos)☃.deserialize(☃, bos.class);
      }
      List<bop> ☃ = a(☃, ☃);
      
      kk ☃ = ☃.isEmpty() ? null : new kk(☃);
      return new bok(☃, ☃, ☃, ☃, true, ☃, ☃);
    }
    
    protected List<bop> a(JsonDeserializationContext ☃, JsonObject ☃)
    {
      List<bop> ☃ = Lists.newArrayList();
      if (☃.has("overrides"))
      {
        JsonArray ☃ = od.u(☃, "overrides");
        for (JsonElement ☃ : ☃) {
          ☃.add((bop)☃.deserialize(☃, bop.class));
        }
      }
      return ☃;
    }
    
    private Map<String, String> b(JsonObject ☃)
    {
      Map<String, String> ☃ = Maps.newHashMap();
      if (☃.has("textures"))
      {
        JsonObject ☃ = ☃.getAsJsonObject("textures");
        for (Map.Entry<String, JsonElement> ☃ : ☃.entrySet()) {
          ☃.put(☃.getKey(), ((JsonElement)☃.getValue()).getAsString());
        }
      }
      return ☃;
    }
    
    private String c(JsonObject ☃)
    {
      return od.a(☃, "parent", "");
    }
    
    protected boolean a(JsonObject ☃)
    {
      return od.a(☃, "ambientocclusion", true);
    }
    
    protected List<bog> b(JsonDeserializationContext ☃, JsonObject ☃)
    {
      List<bog> ☃ = Lists.newArrayList();
      if (☃.has("elements")) {
        for (JsonElement ☃ : od.u(☃, "elements")) {
          ☃.add((bog)☃.deserialize(☃, bog.class));
        }
      }
      return ☃;
    }
  }
  
  public static void b(Map<kk, bok> ☃)
  {
    for (bok ☃ : ☃.values()) {
      try
      {
        bok ☃ = ☃.d;
        bok ☃ = ☃.d;
        while (☃ != ☃)
        {
          ☃ = ☃.d;
          ☃ = ☃.d.d;
        }
        throw new bok.c();
      }
      catch (NullPointerException localNullPointerException) {}
    }
  }
  
  public static class c
    extends RuntimeException
  {}
}
