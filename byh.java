import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.List;
import org.apache.commons.lang3.Validate;

public class byh
  implements JsonDeserializer<byg>
{
  public byg a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
    throws JsonParseException
  {
    JsonObject ☃ = od.m(☃, "entry");
    
    boolean ☃ = od.a(☃, "replace", false);
    String ☃ = od.a(☃, "subtitle", null);
    List<byf> ☃ = a(☃);
    
    return new byg(☃, ☃, ☃);
  }
  
  private List<byf> a(JsonObject ☃)
  {
    List<byf> ☃ = Lists.newArrayList();
    if (☃.has("sounds"))
    {
      JsonArray ☃ = od.u(☃, "sounds");
      for (int ☃ = 0; ☃ < ☃.size(); ☃++)
      {
        JsonElement ☃ = ☃.get(☃);
        if (od.a(☃))
        {
          String ☃ = od.a(☃, "sound");
          ☃.add(new byf(☃, 1.0F, 1.0F, 1, byf.a.a, false));
        }
        else
        {
          ☃.add(b(od.m(☃, "sound")));
        }
      }
    }
    return ☃;
  }
  
  private byf b(JsonObject ☃)
  {
    String ☃ = od.h(☃, "name");
    
    byf.a ☃ = a(☃, byf.a.a);
    
    float ☃ = od.a(☃, "volume", 1.0F);
    Validate.isTrue(☃ > 0.0F, "Invalid volume", new Object[0]);
    
    float ☃ = od.a(☃, "pitch", 1.0F);
    Validate.isTrue(☃ > 0.0F, "Invalid pitch", new Object[0]);
    
    int ☃ = od.a(☃, "weight", 1);
    Validate.isTrue(☃ > 0, "Invalid weight", new Object[0]);
    
    boolean ☃ = od.a(☃, "stream", false);
    
    return new byf(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  private byf.a a(JsonObject ☃, byf.a ☃)
  {
    byf.a ☃ = ☃;
    if (☃.has("type"))
    {
      ☃ = byf.a.a(od.h(☃, "type"));
      Validate.notNull(☃, "Invalid type", new Object[0]);
    }
    return ☃;
  }
}
