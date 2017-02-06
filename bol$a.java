import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class bol$a
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
