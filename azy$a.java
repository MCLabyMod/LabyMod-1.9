import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class azy$a
  implements JsonDeserializer<azy>, JsonSerializer<azy>
{
  public azy a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
    throws JsonParseException
  {
    JsonObject ☃ = od.m(☃, "loot table");
    azw[] ☃ = (azw[])od.a(☃, "pools", new azw[0], ☃, azw[].class);
    return new azy(☃);
  }
  
  public JsonElement a(azy ☃, Type ☃, JsonSerializationContext ☃)
  {
    JsonObject ☃ = new JsonObject();
    ☃.add("pools", ☃.serialize(azy.a(☃)));
    
    return ☃;
  }
}
