import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

class boj$a
  implements JsonDeserializer<boj>
{
  public boj a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
    throws JsonParseException
  {
    JsonObject ☃ = ☃.getAsJsonObject();
    
    float[] ☃ = b(☃);
    int ☃ = a(☃);
    
    return new boj(☃, ☃);
  }
  
  protected int a(JsonObject ☃)
  {
    int ☃ = od.a(☃, "rotation", 0);
    if ((☃ < 0) || (☃ % 90 != 0) || (☃ / 90 > 3)) {
      throw new JsonParseException("Invalid rotation " + ☃ + " found, only 0/90/180/270 allowed");
    }
    return ☃;
  }
  
  private float[] b(JsonObject ☃)
  {
    if (!☃.has("uv")) {
      return null;
    }
    JsonArray ☃ = od.u(☃, "uv");
    if (☃.size() != 4) {
      throw new JsonParseException("Expected 4 uv values, found: " + ☃.size());
    }
    float[] ☃ = new float[4];
    for (int ☃ = 0; ☃ < ☃.length; ☃++) {
      ☃[☃] = od.e(☃.get(☃), "uv[" + ☃ + "]");
    }
    return ☃;
  }
}
