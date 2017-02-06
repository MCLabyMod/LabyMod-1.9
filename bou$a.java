import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class bou$a
  implements JsonDeserializer<bou>
{
  public bou a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
    throws JsonParseException
  {
    JsonObject ☃ = ☃.getAsJsonObject();
    
    String ☃ = b(☃);
    bxp ☃ = a(☃);
    boolean ☃ = d(☃);
    int ☃ = c(☃);
    
    return new bou(a(☃), ☃, ☃, ☃);
  }
  
  private kk a(String ☃)
  {
    kk ☃ = new kk(☃);
    ☃ = new kk(☃.b(), "block/" + ☃.a());
    return ☃;
  }
  
  private boolean d(JsonObject ☃)
  {
    return od.a(☃, "uvlock", false);
  }
  
  protected bxp a(JsonObject ☃)
  {
    int ☃ = od.a(☃, "x", 0);
    int ☃ = od.a(☃, "y", 0);
    
    bxp ☃ = bxp.a(☃, ☃);
    if (☃ == null) {
      throw new JsonParseException("Invalid BlockModelRotation x: " + ☃ + ", y: " + ☃);
    }
    return ☃;
  }
  
  protected String b(JsonObject ☃)
  {
    return od.h(☃, "model");
  }
  
  protected int c(JsonObject ☃)
  {
    int ☃ = od.a(☃, "weight", 1);
    if (☃ < 1) {
      throw new JsonParseException("Invalid weight " + ☃ + " found, expected integer >= 1");
    }
    return ☃;
  }
}
