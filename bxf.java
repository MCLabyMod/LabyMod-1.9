import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Map.Entry;
import java.util.Set;

public class bxf
  extends bwt<bxe>
{
  public bxe a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
    throws JsonParseException
  {
    JsonObject ☃ = ☃.getAsJsonObject();
    Set<bwp> ☃ = Sets.newHashSet();
    for (Map.Entry<String, JsonElement> ☃ : ☃.entrySet())
    {
      String ☃ = (String)☃.getKey();
      JsonObject ☃ = od.m((JsonElement)☃.getValue(), "language");
      String ☃ = od.h(☃, "region");
      String ☃ = od.h(☃, "name");
      boolean ☃ = od.a(☃, "bidirectional", false);
      if (☃.isEmpty()) {
        throw new JsonParseException("Invalid language->'" + ☃ + "'->region: empty value");
      }
      if (☃.isEmpty()) {
        throw new JsonParseException("Invalid language->'" + ☃ + "'->name: empty value");
      }
      if (!☃.add(new bwp(☃, ☃, ☃, ☃))) {
        throw new JsonParseException("Duplicate language->'" + ☃ + "' defined");
      }
    }
    return new bxe(☃);
  }
  
  public String a()
  {
    return "language";
  }
}
