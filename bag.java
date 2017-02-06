import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;
import java.util.Map;

public class bag
{
  private static final Map<kk, baf.a<?>> a = ;
  private static final Map<Class<? extends baf>, baf.a<?>> b = Maps.newHashMap();
  
  static
  {
    a(new baj.a());
    a(new bal.a());
    a(new bae.a());
    a(new bad.a());
    a(new bam.a());
    a(new ban.a());
    a(new bah.a());
    a(new bak.a());
    a(new bai.b());
  }
  
  public static <T extends baf> void a(baf.a<? extends T> ☃)
  {
    kk ☃ = ☃.a();
    Class<T> ☃ = ☃.b();
    if (a.containsKey(☃)) {
      throw new IllegalArgumentException("Can't re-register item function name " + ☃);
    }
    if (b.containsKey(☃)) {
      throw new IllegalArgumentException("Can't re-register item function class " + ☃.getName());
    }
    a.put(☃, ☃);
    b.put(☃, ☃);
  }
  
  public static baf.a<?> a(kk ☃)
  {
    baf.a<?> ☃ = (baf.a)a.get(☃);
    if (☃ == null) {
      throw new IllegalArgumentException("Unknown loot item function '" + ☃ + "'");
    }
    return ☃;
  }
  
  public static <T extends baf> baf.a<T> a(T ☃)
  {
    baf.a<T> ☃ = (baf.a)b.get(☃.getClass());
    if (☃ == null) {
      throw new IllegalArgumentException("Unknown loot item function " + ☃);
    }
    return ☃;
  }
  
  public static class a
    implements JsonDeserializer<baf>, JsonSerializer<baf>
  {
    public baf a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
      throws JsonParseException
    {
      JsonObject ☃ = od.m(☃, "function");
      kk ☃ = new kk(od.h(☃, "function"));
      baf.a<?> ☃;
      try
      {
        ☃ = bag.a(☃);
      }
      catch (IllegalArgumentException ☃)
      {
        throw new JsonSyntaxException("Unknown function '" + ☃ + "'");
      }
      return ☃.b(☃, ☃, (bar[])od.a(☃, "conditions", new bar[0], ☃, bar[].class));
    }
    
    public JsonElement a(baf ☃, Type ☃, JsonSerializationContext ☃)
    {
      baf.a<baf> ☃ = bag.a(☃);
      JsonObject ☃ = new JsonObject();
      ☃.a(☃, ☃, ☃);
      ☃.addProperty("function", ☃.a().toString());
      if ((☃.a() != null) && (☃.a().length > 0)) {
        ☃.add("conditions", ☃.serialize(☃.a()));
      }
      return ☃;
    }
  }
}
