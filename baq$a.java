import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class baq$a
  extends bar.a<baq>
{
  protected baq$a()
  {
    super(new kk("entity_scores"), baq.class);
  }
  
  public void a(JsonObject ☃, baq ☃, JsonSerializationContext ☃)
  {
    JsonObject ☃ = new JsonObject();
    for (Map.Entry<String, bac> ☃ : baq.a(☃).entrySet()) {
      ☃.add((String)☃.getKey(), ☃.serialize(☃.getValue()));
    }
    ☃.add("scores", ☃);
    ☃.add("entity", ☃.serialize(baq.b(☃)));
  }
  
  public baq a(JsonObject ☃, JsonDeserializationContext ☃)
  {
    Set<Map.Entry<String, JsonElement>> ☃ = od.t(☃, "scores").entrySet();
    Map<String, bac> ☃ = Maps.newLinkedHashMap();
    for (Map.Entry<String, JsonElement> ☃ : ☃) {
      ☃.put(☃.getKey(), od.a((JsonElement)☃.getValue(), "score", ☃, bac.class));
    }
    return new baq(☃, (azz.b)od.a(☃, "entity", ☃, azz.b.class));
  }
}
