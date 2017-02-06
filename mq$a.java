import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

class mq$a
  implements JsonDeserializer<mp<K>>, JsonSerializer<mp<K>>
{
  private mq$a(mq parammq) {}
  
  public JsonElement a(mp<K> ☃, Type ☃, JsonSerializationContext ☃)
  {
    JsonObject ☃ = new JsonObject();
    ☃.a(☃);
    return ☃;
  }
  
  public mp<K> a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
    throws JsonParseException
  {
    if (☃.isJsonObject())
    {
      JsonObject ☃ = ☃.getAsJsonObject();
      mp<K> ☃ = this.a.a(☃);
      return ☃;
    }
    return null;
  }
}
