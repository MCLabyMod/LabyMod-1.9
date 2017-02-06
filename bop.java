import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;

public class bop
{
  private final kk a;
  private final Map<kk, Float> b;
  
  public bop(kk ☃, Map<kk, Float> ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public kk a()
  {
    return this.a;
  }
  
  boolean a(adq ☃, aht ☃, sa ☃)
  {
    ado ☃ = ☃.b();
    for (Map.Entry<kk, Float> ☃ : this.b.entrySet())
    {
      adr ☃ = ☃.a((kk)☃.getKey());
      if ((☃ == null) || (☃.a(☃, ☃, ☃) < ((Float)☃.getValue()).floatValue())) {
        return false;
      }
    }
    return true;
  }
  
  static class a
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
}
