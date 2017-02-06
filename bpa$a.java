import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class bpa$a
  implements JsonDeserializer<bpa>
{
  private static final Function<JsonElement, bow> a = new Function()
  {
    public bow a(JsonElement ☃)
    {
      return ☃ == null ? null : bpa.a.a(☃.getAsJsonObject());
    }
  };
  private static final Function<Map.Entry<String, JsonElement>, bow> b = new Function()
  {
    public bow a(Map.Entry<String, JsonElement> ☃)
    {
      return ☃ == null ? null : bpa.a.a(☃);
    }
  };
  
  public bpa a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
    throws JsonParseException
  {
    JsonObject ☃ = ☃.getAsJsonObject();
    
    return new bpa(b(☃), (bot)☃.deserialize(☃.get("apply"), bot.class));
  }
  
  private bow b(JsonObject ☃)
  {
    if (☃.has("when")) {
      return a(od.t(☃, "when"));
    }
    return bow.a;
  }
  
  static bow a(JsonObject ☃)
  {
    Set<Map.Entry<String, JsonElement>> ☃ = ☃.entrySet();
    if (☃.isEmpty()) {
      throw new JsonParseException("No elements found in selector");
    }
    if (☃.size() == 1)
    {
      if (☃.has("OR")) {
        return new boz(Iterables.transform(od.u(☃, "OR"), a));
      }
      if (☃.has("AND")) {
        return new bov(Iterables.transform(od.u(☃, "AND"), a));
      }
      return b((Map.Entry)☃.iterator().next());
    }
    return new bov(Iterables.transform(☃, b));
  }
  
  private static box b(Map.Entry<String, JsonElement> ☃)
  {
    return new box((String)☃.getKey(), ((JsonElement)☃.getValue()).getAsString());
  }
}
