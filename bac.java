import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Random;

public class bac
{
  private final float a;
  private final float b;
  
  public bac(float ☃, float ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public bac(float ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public float a()
  {
    return this.a;
  }
  
  public float b()
  {
    return this.b;
  }
  
  public int a(Random ☃)
  {
    return on.a(☃, on.d(this.a), on.d(this.b));
  }
  
  public float b(Random ☃)
  {
    return on.a(☃, this.a, this.b);
  }
  
  public boolean a(int ☃)
  {
    return (☃ <= this.b) && (☃ >= this.a);
  }
  
  public static class a
    implements JsonDeserializer<bac>, JsonSerializer<bac>
  {
    public bac a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
      throws JsonParseException
    {
      if (od.b(☃)) {
        return new bac(od.e(☃, "value"));
      }
      JsonObject ☃ = od.m(☃, "value");
      float ☃ = od.l(☃, "min");
      float ☃ = od.l(☃, "max");
      return new bac(☃, ☃);
    }
    
    public JsonElement a(bac ☃, Type ☃, JsonSerializationContext ☃)
    {
      if (bac.a(☃) == bac.b(☃)) {
        return new JsonPrimitive(Float.valueOf(bac.a(☃)));
      }
      JsonObject ☃ = new JsonObject();
      ☃.addProperty("min", Float.valueOf(bac.a(☃)));
      ☃.addProperty("max", Float.valueOf(bac.b(☃)));
      return ☃;
    }
  }
}
