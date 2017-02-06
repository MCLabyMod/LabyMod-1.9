import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.List;

public class bot$a
  implements JsonDeserializer<bot>
{
  public bot a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
    throws JsonParseException
  {
    List<bou> ☃ = Lists.newArrayList();
    if (☃.isJsonArray())
    {
      JsonArray ☃ = ☃.getAsJsonArray();
      if (☃.size() == 0) {
        throw new JsonParseException("Empty variant array");
      }
      for (JsonElement ☃ : ☃) {
        ☃.add((bou)☃.deserialize(☃, bou.class));
      }
    }
    else
    {
      ☃.add((bou)☃.deserialize(☃, bou.class));
    }
    return new bot(☃);
  }
}
