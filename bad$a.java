import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import java.util.List;

public class bad$a
  extends baf.a<bad>
{
  public bad$a()
  {
    super(new kk("enchant_randomly"), bad.class);
  }
  
  public void a(JsonObject ☃, bad ☃, JsonSerializationContext ☃)
  {
    if ((bad.a(☃) != null) && (!bad.a(☃).isEmpty()))
    {
      JsonArray ☃ = new JsonArray();
      for (agm ☃ : bad.a(☃))
      {
        kk ☃ = (kk)agm.b.b(☃);
        if (☃ == null) {
          throw new IllegalArgumentException("Don't know how to serialize enchantment " + ☃);
        }
        ☃.add(new JsonPrimitive(☃.toString()));
      }
      ☃.add("enchantments", ☃);
    }
  }
  
  public bad a(JsonObject ☃, JsonDeserializationContext ☃, bar[] ☃)
  {
    List<agm> ☃ = null;
    if (☃.has("enchantments"))
    {
      ☃ = Lists.newArrayList();
      JsonArray ☃ = od.u(☃, "enchantments");
      for (JsonElement ☃ : ☃)
      {
        String ☃ = od.a(☃, "enchantment");
        agm ☃ = (agm)agm.b.c(new kk(☃));
        if (☃ == null) {
          throw new JsonSyntaxException("Unknown enchantment '" + ☃ + "'");
        }
        ☃.add(☃);
      }
    }
    return new bad(☃, ☃);
  }
}
