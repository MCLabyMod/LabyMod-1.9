import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

class bos$a
  implements JsonDeserializer<bos>
{
  public bos a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
    throws JsonParseException
  {
    JsonObject ☃ = ☃.getAsJsonObject();
    
    bor ☃ = a(☃, ☃, "thirdperson_righthand");
    bor ☃ = a(☃, ☃, "thirdperson_lefthand");
    if (☃ == bor.a) {
      ☃ = ☃;
    }
    bor ☃ = a(☃, ☃, "firstperson_righthand");
    bor ☃ = a(☃, ☃, "firstperson_lefthand");
    if (☃ == bor.a) {
      ☃ = ☃;
    }
    bor ☃ = a(☃, ☃, "head");
    bor ☃ = a(☃, ☃, "gui");
    bor ☃ = a(☃, ☃, "ground");
    bor ☃ = a(☃, ☃, "fixed");
    
    return new bos(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  private bor a(JsonDeserializationContext ☃, JsonObject ☃, String ☃)
  {
    if (☃.has(☃)) {
      return (bor)☃.deserialize(☃.get(☃), bor.class);
    }
    return bor.a;
  }
}
