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
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class bol
{
  static final Gson a = new GsonBuilder().registerTypeAdapter(bol.class, new bol.a()).registerTypeAdapter(bou.class, new bou.a()).registerTypeAdapter(bot.class, new bot.a()).registerTypeAdapter(boy.class, new boy.a()).registerTypeAdapter(bpa.class, new bpa.a()).create();
  private final Map<String, bot> b = Maps.newHashMap();
  private boy c;
  
  public static bol a(Reader ☃)
  {
    return (bol)a.fromJson(☃, bol.class);
  }
  
  public bol(Map<String, bot> ☃, boy ☃)
  {
    this.c = ☃;
    this.b.putAll(☃);
  }
  
  public bol(List<bol> ☃)
  {
    bol ☃ = null;
    for (bol ☃ : ☃)
    {
      if (☃.b())
      {
        this.b.clear();
        ☃ = ☃;
      }
      this.b.putAll(☃.b);
    }
    if (☃ != null) {
      this.c = ☃.c;
    }
  }
  
  public boolean b(String ☃)
  {
    return this.b.get(☃) != null;
  }
  
  public bot c(String ☃)
  {
    bot ☃ = (bot)this.b.get(☃);
    if (☃ == null) {
      throw new bol.b();
    }
    return ☃;
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if ((☃ instanceof bol))
    {
      bol ☃ = (bol)☃;
      if (this.b.equals(☃.b)) {
        return !☃.b() ? true : b() ? this.c.equals(☃.c) : false;
      }
    }
    return false;
  }
  
  public int hashCode()
  {
    return 31 * this.b.hashCode() + (b() ? this.c.hashCode() : 0);
  }
  
  public Set<bot> a()
  {
    Set<bot> ☃ = Sets.newHashSet(this.b.values());
    if (b()) {
      ☃.addAll(this.c.b());
    }
    return ☃;
  }
  
  public boolean b()
  {
    return this.c != null;
  }
  
  public boy c()
  {
    return this.c;
  }
  
  public class b
    extends RuntimeException
  {
    protected b() {}
  }
  
  public static class a
    implements JsonDeserializer<bol>
  {
    public bol a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
      throws JsonParseException
    {
      JsonObject ☃ = ☃.getAsJsonObject();
      
      Map<String, bot> ☃ = a(☃, ☃);
      boy ☃ = b(☃, ☃);
      if ((☃.isEmpty()) && ((☃ == null) || (☃.b().isEmpty()))) {
        throw new JsonParseException("Neither 'variants' nor 'multipart' found");
      }
      return new bol(☃, ☃);
    }
    
    protected Map<String, bot> a(JsonDeserializationContext ☃, JsonObject ☃)
    {
      Map<String, bot> ☃ = Maps.newHashMap();
      if (☃.has("variants"))
      {
        JsonObject ☃ = od.t(☃, "variants");
        for (Map.Entry<String, JsonElement> ☃ : ☃.entrySet()) {
          ☃.put(☃.getKey(), (bot)☃.deserialize((JsonElement)☃.getValue(), bot.class));
        }
      }
      return ☃;
    }
    
    protected boy b(JsonDeserializationContext ☃, JsonObject ☃)
    {
      if (!☃.has("multipart")) {
        return null;
      }
      JsonArray ☃ = od.u(☃, "multipart");
      return (boy)☃.deserialize(☃, boy.class);
    }
  }
}
