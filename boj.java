import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class boj
{
  public float[] a;
  public final int b;
  
  public boj(float[] ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public float a(int ☃)
  {
    if (this.a == null) {
      throw new NullPointerException("uvs");
    }
    int ☃ = d(☃);
    return (☃ == 0) || (☃ == 1) ? this.a[0] : this.a[2];
  }
  
  public float b(int ☃)
  {
    if (this.a == null) {
      throw new NullPointerException("uvs");
    }
    int ☃ = d(☃);
    return (☃ == 0) || (☃ == 3) ? this.a[1] : this.a[3];
  }
  
  private int d(int ☃)
  {
    return (☃ + this.b / 90) % 4;
  }
  
  public int c(int ☃)
  {
    return (☃ + (4 - this.b / 90)) % 4;
  }
  
  public void a(float[] ☃)
  {
    if (this.a == null) {
      this.a = ☃;
    }
  }
  
  static class a
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
}
