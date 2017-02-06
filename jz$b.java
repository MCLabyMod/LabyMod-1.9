import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class jz$b
  implements JsonDeserializer<jz>, JsonSerializer<jz>
{
  public jz a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
    throws JsonParseException
  {
    JsonObject ☃ = od.m(☃, "status");
    jz ☃ = new jz();
    if (☃.has("description")) {
      ☃.a((eu)☃.deserialize(☃.get("description"), eu.class));
    }
    if (☃.has("players")) {
      ☃.a((jz.a)☃.deserialize(☃.get("players"), jz.a.class));
    }
    if (☃.has("version")) {
      ☃.a((jz.c)☃.deserialize(☃.get("version"), jz.c.class));
    }
    if (☃.has("favicon")) {
      ☃.a(od.h(☃, "favicon"));
    }
    return ☃;
  }
  
  public JsonElement a(jz ☃, Type ☃, JsonSerializationContext ☃)
  {
    JsonObject ☃ = new JsonObject();
    if (☃.a() != null) {
      ☃.add("description", ☃.serialize(☃.a()));
    }
    if (☃.b() != null) {
      ☃.add("players", ☃.serialize(☃.b()));
    }
    if (☃.c() != null) {
      ☃.add("version", ☃.serialize(☃.c()));
    }
    if (☃.d() != null) {
      ☃.addProperty("favicon", ☃.d());
    }
    return ☃;
  }
}
