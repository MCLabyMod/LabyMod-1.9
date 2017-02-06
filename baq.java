import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class baq
  implements bar
{
  private final Map<String, bac> a;
  private final azz.b b;
  
  public baq(Map<String, bac> ☃, azz.b ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public boolean a(Random ☃, azz ☃)
  {
    rr ☃ = ☃.a(this.b);
    if (☃ == null) {
      return false;
    }
    bbp ☃ = ☃.l.ad();
    for (Map.Entry<String, bac> ☃ : this.a.entrySet()) {
      if (!a(☃, ☃, (String)☃.getKey(), (bac)☃.getValue())) {
        return false;
      }
    }
    return true;
  }
  
  protected boolean a(rr ☃, bbp ☃, String ☃, bac ☃)
  {
    bbl ☃ = ☃.b(☃);
    if (☃ == null) {
      return false;
    }
    String ☃ = (☃ instanceof lr) ? ☃.h_() : ☃.bc().toString();
    if (!☃.b(☃, ☃)) {
      return false;
    }
    return ☃.a(☃.c(☃, ☃).c());
  }
  
  public static class a
    extends bar.a<baq>
  {
    protected a()
    {
      super(baq.class);
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
}
