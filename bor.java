import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import org.lwjgl.util.vector.Vector3f;

public class bor
{
  public static final bor a = new bor(new Vector3f(), new Vector3f(), new Vector3f(1.0F, 1.0F, 1.0F));
  public final Vector3f b;
  public final Vector3f c;
  public final Vector3f d;
  
  public bor(Vector3f ☃, Vector3f ☃, Vector3f ☃)
  {
    this.b = new Vector3f(☃);
    this.c = new Vector3f(☃);
    this.d = new Vector3f(☃);
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if (getClass() == ☃.getClass())
    {
      bor ☃ = (bor)☃;
      
      return (this.b.equals(☃.b)) && (this.d.equals(☃.d)) && (this.c.equals(☃.c));
    }
    return false;
  }
  
  public int hashCode()
  {
    int ☃ = this.b.hashCode();
    ☃ = 31 * ☃ + this.c.hashCode();
    ☃ = 31 * ☃ + this.d.hashCode();
    return ☃;
  }
  
  static class a
    implements JsonDeserializer<bor>
  {
    private static final Vector3f a = new Vector3f(0.0F, 0.0F, 0.0F);
    private static final Vector3f b = new Vector3f(0.0F, 0.0F, 0.0F);
    private static final Vector3f c = new Vector3f(1.0F, 1.0F, 1.0F);
    
    public bor a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
      throws JsonParseException
    {
      JsonObject ☃ = ☃.getAsJsonObject();
      
      Vector3f ☃ = a(☃, "rotation", a);
      
      Vector3f ☃ = a(☃, "translation", b);
      ☃.scale(0.0625F);
      ☃.x = on.a(☃.x, -5.0F, 5.0F);
      ☃.y = on.a(☃.y, -5.0F, 5.0F);
      ☃.z = on.a(☃.z, -5.0F, 5.0F);
      
      Vector3f ☃ = a(☃, "scale", c);
      ☃.x = on.a(☃.x, -4.0F, 4.0F);
      ☃.y = on.a(☃.y, -4.0F, 4.0F);
      ☃.z = on.a(☃.z, -4.0F, 4.0F);
      
      return new bor(☃, ☃, ☃);
    }
    
    private Vector3f a(JsonObject ☃, String ☃, Vector3f ☃)
    {
      if (!☃.has(☃)) {
        return ☃;
      }
      JsonArray ☃ = od.u(☃, ☃);
      if (☃.size() != 3) {
        throw new JsonParseException("Expected 3 " + ☃ + " values, found: " + ☃.size());
      }
      float[] ☃ = new float[3];
      for (int ☃ = 0; ☃ < ☃.length; ☃++) {
        ☃[☃] = od.e(☃.get(☃), ☃ + "[" + ☃ + "]");
      }
      return new Vector3f(☃[0], ☃[1], ☃[2]);
    }
  }
}
