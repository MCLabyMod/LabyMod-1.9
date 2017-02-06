import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class bok$b
  implements JsonDeserializer<bok>
{
  public bok a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
    throws JsonParseException
  {
    JsonObject ☃ = ☃.getAsJsonObject();
    
    List<bog> ☃ = b(☃, ☃);
    String ☃ = c(☃);
    
    Map<String, String> ☃ = b(☃);
    boolean ☃ = a(☃);
    
    bos ☃ = bos.a;
    if (☃.has("display"))
    {
      JsonObject ☃ = od.t(☃, "display");
      ☃ = (bos)☃.deserialize(☃, bos.class);
    }
    List<bop> ☃ = a(☃, ☃);
    
    kk ☃ = ☃.isEmpty() ? null : new kk(☃);
    return new bok(☃, ☃, ☃, ☃, true, ☃, ☃);
  }
  
  protected List<bop> a(JsonDeserializationContext ☃, JsonObject ☃)
  {
    List<bop> ☃ = Lists.newArrayList();
    if (☃.has("overrides"))
    {
      JsonArray ☃ = od.u(☃, "overrides");
      for (JsonElement ☃ : ☃) {
        ☃.add((bop)☃.deserialize(☃, bop.class));
      }
    }
    return ☃;
  }
  
  private Map<String, String> b(JsonObject ☃)
  {
    Map<String, String> ☃ = Maps.newHashMap();
    if (☃.has("textures"))
    {
      JsonObject ☃ = ☃.getAsJsonObject("textures");
      for (Map.Entry<String, JsonElement> ☃ : ☃.entrySet()) {
        ☃.put(☃.getKey(), ((JsonElement)☃.getValue()).getAsString());
      }
    }
    return ☃;
  }
  
  private String c(JsonObject ☃)
  {
    return od.a(☃, "parent", "");
  }
  
  protected boolean a(JsonObject ☃)
  {
    return od.a(☃, "ambientocclusion", true);
  }
  
  protected List<bog> b(JsonDeserializationContext ☃, JsonObject ☃)
  {
    List<bog> ☃ = Lists.newArrayList();
    if (☃.has("elements")) {
      for (JsonElement ☃ : od.u(☃, "elements")) {
        ☃.add((bog)☃.deserialize(☃, bog.class));
      }
    }
    return ☃;
  }
}
