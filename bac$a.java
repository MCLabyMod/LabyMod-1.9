import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class bac$a
  implements JsonDeserializer<bac>, JsonSerializer<bac>
{
  public bac a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
    throws JsonParseException
  {
    if (od.b(☃)) {
      return new bac(od.e(☃, "value"));
    }
    JsonObject ☃ = od.m(☃, "value");
    float ☃ = od.l(☃, "min");
    float ☃ = od.l(☃, "max");
    return new bac(☃, ☃);
  }
  
  public JsonElement a(bac ☃, Type ☃, JsonSerializationContext ☃)
  {
    if (bac.a(☃) == bac.b(☃)) {
      return new JsonPrimitive(Float.valueOf(bac.a(☃)));
    }
    JsonObject ☃ = new JsonObject();
    ☃.addProperty("min", Float.valueOf(bac.a(☃)));
    ☃.addProperty("max", Float.valueOf(bac.b(☃)));
    return ☃;
  }
}
