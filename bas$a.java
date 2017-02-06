import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;

public class bas$a
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
