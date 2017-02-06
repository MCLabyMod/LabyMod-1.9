import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;

public class bag$a
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
