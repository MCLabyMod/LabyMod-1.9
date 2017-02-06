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
import java.util.Random;

public class bas
{
  private static final Map<kk, bar.a<?>> a = ;
  private static final Map<Class<? extends bar>, bar.a<?>> b = Maps.newHashMap();
  
  static
  {
    a(new bav.a());
    a(new baw.a());
    a(new bat.a());
    a(new bau.a());
    a(new baq.a());
  }
  
  public static <T extends bar> void a(bar.a<? extends T> ☃)
  {
    kk ☃ = ☃.a();
    Class<T> ☃ = ☃.b();
    if (a.containsKey(☃)) {
      throw new IllegalArgumentException("Can't re-register item condition name " + ☃);
    }
    if (b.containsKey(☃)) {
      throw new IllegalArgumentException("Can't re-register item condition class " + ☃.getName());
    }
    a.put(☃, ☃);
    b.put(☃, ☃);
  }
  
  public static boolean a(bar[] ☃, Random ☃, azz ☃)
  {
    if (☃ == null) {
      return true;
    }
    int ☃ = 0;
    for (int ☃ = ☃.length; ☃ < ☃; ☃++)
    {
      bar ☃ = ☃[☃];
      if (!☃.a(☃, ☃)) {
        return false;
      }
    }
    return true;
  }
  
  public static bar.a<?> a(kk ☃)
  {
    bar.a<?> ☃ = (bar.a)a.get(☃);
    if (☃ == null) {
      throw new IllegalArgumentException("Unknown loot item condition '" + ☃ + "'");
    }
    return ☃;
  }
  
  public static <T extends bar> bar.a<T> a(T ☃)
  {
    bar.a<T> ☃ = (bar.a)b.get(☃.getClass());
    if (☃ == null) {
      throw new IllegalArgumentException("Unknown loot item condition " + ☃);
    }
    return ☃;
  }
  
  public static class a
    implements JsonDeserializer<bar>, JsonSerializer<bar>
  {
    public bar a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
      throws JsonParseException
    {
      JsonObject ☃ = od.m(☃, "condition");
      kk ☃ = new kk(od.h(☃, "condition"));
      bar.a<?> ☃;
      try
      {
        ☃ = bas.a(☃);
      }
      catch (IllegalArgumentException ☃)
      {
        throw new JsonSyntaxException("Unknown condition '" + ☃ + "'");
      }
      return ☃.b(☃, ☃);
    }
    
    public JsonElement a(bar ☃, Type ☃, JsonSerializationContext ☃)
    {
      bar.a<bar> ☃ = bas.a(☃);
      JsonObject ☃ = new JsonObject();
      ☃.a(☃, ☃, ☃);
      ☃.addProperty("condition", ☃.a().toString());
      return ☃;
    }
  }
}
