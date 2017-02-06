import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

final class pr$1
  implements JsonDeserializer<eu>
{
  public eu a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
    throws JsonParseException
  {
    if (☃.isJsonPrimitive()) {
      return new fa(☃.getAsString());
    }
    if (☃.isJsonArray())
    {
      JsonArray ☃ = ☃.getAsJsonArray();
      eu ☃ = null;
      for (JsonElement ☃ : ☃)
      {
        eu ☃ = a(☃, ☃.getClass(), ☃);
        if (☃ == null) {
          ☃ = ☃;
        } else {
          ☃.a(☃);
        }
      }
      return ☃;
    }
    throw new JsonParseException("Don't know how to turn " + ☃.toString() + " into a Component");
  }
}
