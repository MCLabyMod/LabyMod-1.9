import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class ez$a
  implements JsonDeserializer<ez>, JsonSerializer<ez>
{
  public ez a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
    throws JsonParseException
  {
    if (☃.isJsonObject())
    {
      ez ☃ = new ez();
      JsonObject ☃ = ☃.getAsJsonObject();
      if (☃ == null) {
        return null;
      }
      if (☃.has("bold")) {
        ez.a(☃, Boolean.valueOf(☃.get("bold").getAsBoolean()));
      }
      if (☃.has("italic")) {
        ez.b(☃, Boolean.valueOf(☃.get("italic").getAsBoolean()));
      }
      if (☃.has("underlined")) {
        ez.c(☃, Boolean.valueOf(☃.get("underlined").getAsBoolean()));
      }
      if (☃.has("strikethrough")) {
        ez.d(☃, Boolean.valueOf(☃.get("strikethrough").getAsBoolean()));
      }
      if (☃.has("obfuscated")) {
        ez.e(☃, Boolean.valueOf(☃.get("obfuscated").getAsBoolean()));
      }
      if (☃.has("color")) {
        ez.a(☃, (a)☃.deserialize(☃.get("color"), a.class));
      }
      if (☃.has("insertion")) {
        ez.a(☃, ☃.get("insertion").getAsString());
      }
      if (☃.has("clickEvent"))
      {
        JsonObject ☃ = ☃.getAsJsonObject("clickEvent");
        if (☃ != null)
        {
          JsonPrimitive ☃ = ☃.getAsJsonPrimitive("action");
          et.a ☃ = ☃ == null ? null : et.a.a(☃.getAsString());
          
          JsonPrimitive ☃ = ☃.getAsJsonPrimitive("value");
          String ☃ = ☃ == null ? null : ☃.getAsString();
          if ((☃ != null) && (☃ != null) && (☃.a())) {
            ez.a(☃, new et(☃, ☃));
          }
        }
      }
      if (☃.has("hoverEvent"))
      {
        JsonObject ☃ = ☃.getAsJsonObject("hoverEvent");
        if (☃ != null)
        {
          JsonPrimitive ☃ = ☃.getAsJsonPrimitive("action");
          ew.a ☃ = ☃ == null ? null : ew.a.a(☃.getAsString());
          
          eu ☃ = (eu)☃.deserialize(☃.get("value"), eu.class);
          if ((☃ != null) && (☃ != null) && (☃.a())) {
            ez.a(☃, new ew(☃, ☃));
          }
        }
      }
      return ☃;
    }
    return null;
  }
  
  public JsonElement a(ez ☃, Type ☃, JsonSerializationContext ☃)
  {
    if (☃.g()) {
      return null;
    }
    JsonObject ☃ = new JsonObject();
    if (ez.b(☃) != null) {
      ☃.addProperty("bold", ez.b(☃));
    }
    if (ez.c(☃) != null) {
      ☃.addProperty("italic", ez.c(☃));
    }
    if (ez.d(☃) != null) {
      ☃.addProperty("underlined", ez.d(☃));
    }
    if (ez.e(☃) != null) {
      ☃.addProperty("strikethrough", ez.e(☃));
    }
    if (ez.f(☃) != null) {
      ☃.addProperty("obfuscated", ez.f(☃));
    }
    if (ez.g(☃) != null) {
      ☃.add("color", ☃.serialize(ez.g(☃)));
    }
    if (ez.h(☃) != null) {
      ☃.add("insertion", ☃.serialize(ez.h(☃)));
    }
    if (ez.i(☃) != null)
    {
      JsonObject ☃ = new JsonObject();
      ☃.addProperty("action", ez.i(☃).a().b());
      ☃.addProperty("value", ez.i(☃).b());
      ☃.add("clickEvent", ☃);
    }
    if (ez.j(☃) != null)
    {
      JsonObject ☃ = new JsonObject();
      ☃.addProperty("action", ez.j(☃).a().b());
      ☃.add("value", ☃.serialize(ez.j(☃).b()));
      ☃.add("hoverEvent", ☃);
    }
    return ☃;
  }
}
