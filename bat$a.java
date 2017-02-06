import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Map.Entry;
import java.util.Set;

public class bat$a
  extends bar.a<bat>
{
  protected bat$a()
  {
    super(new kk("entity_properties"), bat.class);
  }
  
  public void a(JsonObject ☃, bat ☃, JsonSerializationContext ☃)
  {
    JsonObject ☃ = new JsonObject();
    for (baz ☃ : bat.a(☃))
    {
      baz.a<baz> ☃ = bay.a(☃);
      ☃.add(☃.a().toString(), ☃.a(☃, ☃));
    }
    ☃.add("properties", ☃);
    ☃.add("entity", ☃.serialize(bat.b(☃)));
  }
  
  public bat a(JsonObject ☃, JsonDeserializationContext ☃)
  {
    Set<Map.Entry<String, JsonElement>> ☃ = od.t(☃, "properties").entrySet();
    baz[] ☃ = new baz[☃.size()];
    int ☃ = 0;
    for (Map.Entry<String, JsonElement> ☃ : ☃) {
      ☃[(☃++)] = bay.a(new kk((String)☃.getKey())).a((JsonElement)☃.getValue(), ☃);
    }
    return new bat(☃, (azz.b)od.a(☃, "entity", ☃, azz.b.class));
  }
}
