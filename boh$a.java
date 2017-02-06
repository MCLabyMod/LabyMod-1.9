import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

class boh$a
  implements JsonDeserializer<boh>
{
  public boh a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
    throws JsonParseException
  {
    JsonObject ☃ = ☃.getAsJsonObject();
    
    cq ☃ = c(☃);
    int ☃ = a(☃);
    String ☃ = b(☃);
    boj ☃ = (boj)☃.deserialize(☃, boj.class);
    
    return new boh(☃, ☃, ☃, ☃);
  }
  
  protected int a(JsonObject ☃)
  {
    return od.a(☃, "tintindex", -1);
  }
  
  private String b(JsonObject ☃)
  {
    return od.h(☃, "texture");
  }
  
  private cq c(JsonObject ☃)
  {
    String ☃ = od.a(☃, "cullface", "");
    return cq.a(☃);
  }
}
