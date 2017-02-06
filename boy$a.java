import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.List;

public class boy$a
  implements JsonDeserializer<boy>
{
  public boy a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
    throws JsonParseException
  {
    return new boy(a(☃, ☃.getAsJsonArray()));
  }
  
  private List<bpa> a(JsonDeserializationContext ☃, JsonArray ☃)
  {
    List<bpa> ☃ = Lists.newArrayList();
    for (JsonElement ☃ : ☃) {
      ☃.add((bpa)☃.deserialize(☃, bpa.class));
    }
    return ☃;
  }
}
