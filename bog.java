import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;
import org.lwjgl.util.vector.Vector3f;

public class bog
{
  public final Vector3f a;
  public final Vector3f b;
  public final Map<cq, boh> c;
  public final boi d;
  public final boolean e;
  
  public bog(Vector3f ☃, Vector3f ☃, Map<cq, boh> ☃, boi ☃, boolean ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
    this.e = ☃;
    
    a();
  }
  
  private void a()
  {
    for (Map.Entry<cq, boh> ☃ : this.c.entrySet())
    {
      float[] ☃ = a((cq)☃.getKey());
      ((boh)☃.getValue()).e.a(☃);
    }
  }
  
  private float[] a(cq ☃)
  {
    switch (bog.1.a[☃.ordinal()])
    {
    case 1: 
      return new float[] { this.a.x, 16.0F - this.b.z, this.b.x, 16.0F - this.a.z };
    case 2: 
      return new float[] { this.a.x, this.a.z, this.b.x, this.b.z };
    case 3: 
    default: 
      return new float[] { 16.0F - this.b.x, 16.0F - this.b.y, 16.0F - this.a.x, 16.0F - this.a.y };
    case 4: 
      return new float[] { this.a.x, 16.0F - this.b.y, this.b.x, 16.0F - this.a.y };
    case 5: 
      return new float[] { this.a.z, 16.0F - this.b.y, this.b.z, 16.0F - this.a.y };
    }
    return new float[] { 16.0F - this.b.z, 16.0F - this.b.y, 16.0F - this.a.z, 16.0F - this.a.y };
  }
  
  static class a
    implements JsonDeserializer<bog>
  {
    public bog a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
      throws JsonParseException
    {
      JsonObject ☃ = ☃.getAsJsonObject();
      Vector3f ☃ = e(☃);
      Vector3f ☃ = d(☃);
      boi ☃ = a(☃);
      Map<cq, boh> ☃ = a(☃, ☃);
      if ((☃.has("shade")) && (!od.c(☃, "shade"))) {
        throw new JsonParseException("Expected shade to be a Boolean");
      }
      boolean ☃ = od.a(☃, "shade", true);
      
      return new bog(☃, ☃, ☃, ☃, ☃);
    }
    
    private boi a(JsonObject ☃)
    {
      boi ☃ = null;
      if (☃.has("rotation"))
      {
        JsonObject ☃ = od.t(☃, "rotation");
        Vector3f ☃ = a(☃, "origin");
        ☃.scale(0.0625F);
        cq.a ☃ = c(☃);
        float ☃ = b(☃);
        boolean ☃ = od.a(☃, "rescale", false);
        
        ☃ = new boi(☃, ☃, ☃, ☃);
      }
      return ☃;
    }
    
    private float b(JsonObject ☃)
    {
      float ☃ = od.l(☃, "angle");
      if ((☃ != 0.0F) && (on.e(☃) != 22.5F) && (on.e(☃) != 45.0F)) {
        throw new JsonParseException("Invalid rotation " + ☃ + " found, only -45/-22.5/0/22.5/45 allowed");
      }
      return ☃;
    }
    
    private cq.a c(JsonObject ☃)
    {
      String ☃ = od.h(☃, "axis");
      cq.a ☃ = cq.a.a(☃.toLowerCase());
      if (☃ == null) {
        throw new JsonParseException("Invalid rotation axis: " + ☃);
      }
      return ☃;
    }
    
    private Map<cq, boh> a(JsonDeserializationContext ☃, JsonObject ☃)
    {
      Map<cq, boh> ☃ = b(☃, ☃);
      if (☃.isEmpty()) {
        throw new JsonParseException("Expected between 1 and 6 unique faces, got 0");
      }
      return ☃;
    }
    
    private Map<cq, boh> b(JsonDeserializationContext ☃, JsonObject ☃)
    {
      Map<cq, boh> ☃ = Maps.newEnumMap(cq.class);
      JsonObject ☃ = od.t(☃, "faces");
      for (Map.Entry<String, JsonElement> ☃ : ☃.entrySet())
      {
        cq ☃ = a((String)☃.getKey());
        ☃.put(☃, (boh)☃.deserialize((JsonElement)☃.getValue(), boh.class));
      }
      return ☃;
    }
    
    private cq a(String ☃)
    {
      cq ☃ = cq.a(☃);
      if (☃ == null) {
        throw new JsonParseException("Unknown facing: " + ☃);
      }
      return ☃;
    }
    
    private Vector3f d(JsonObject ☃)
    {
      Vector3f ☃ = a(☃, "to");
      if ((☃.x < -16.0F) || (☃.y < -16.0F) || (☃.z < -16.0F) || (☃.x > 32.0F) || (☃.y > 32.0F) || (☃.z > 32.0F)) {
        throw new JsonParseException("'to' specifier exceeds the allowed boundaries: " + ☃);
      }
      return ☃;
    }
    
    private Vector3f e(JsonObject ☃)
    {
      Vector3f ☃ = a(☃, "from");
      if ((☃.x < -16.0F) || (☃.y < -16.0F) || (☃.z < -16.0F) || (☃.x > 32.0F) || (☃.y > 32.0F) || (☃.z > 32.0F)) {
        throw new JsonParseException("'from' specifier exceeds the allowed boundaries: " + ☃);
      }
      return ☃;
    }
    
    private Vector3f a(JsonObject ☃, String ☃)
    {
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
