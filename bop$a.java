import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;

class bop$a
  implements JsonDeserializer<bop>
{
  public bop a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
    throws JsonParseException
  {
    JsonObject ☃ = ☃.getAsJsonObject();
    
    kk ☃ = new kk(od.h(☃, "model"));
    Map<kk, Float> ☃ = a(☃);
    
    return new bop(☃, ☃);
  }
  
  protected Map<kk, Float> a(JsonObject ☃)
  {
    Map<kk, Float> ☃ = Maps.newLinkedHashMap();
    
    JsonObject ☃ = od.t(☃, "predicate");
    for (Map.Entry<String, JsonElement> ☃ : ☃.entrySet()) {
      ☃.put(new kk((String)☃.getKey()), Float.valueOf(od.e((JsonElement)☃.getValue(), (String)☃.getKey())));
    }
    return ☃;
  }
}
