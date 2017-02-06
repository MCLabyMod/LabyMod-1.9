import com.google.common.collect.ForwardingSet;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.util.Set;

public class nv
  extends ForwardingSet<String>
  implements ns
{
  private final Set<String> a = Sets.newHashSet();
  
  public void a(JsonElement ☃)
  {
    if (☃.isJsonArray()) {
      for (JsonElement ☃ : ☃.getAsJsonArray()) {
        add(☃.getAsString());
      }
    }
  }
  
  public JsonElement a()
  {
    JsonArray ☃ = new JsonArray();
    for (String ☃ : this) {
      ☃.add(new JsonPrimitive(☃));
    }
    return ☃;
  }
  
  protected Set<String> delegate()
  {
    return this.a;
  }
}
