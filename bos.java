import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;

public class bos
{
  public static final bos a = new bos();
  public static float b = 0.0F;
  public static float c = 0.0F;
  public static float d = 0.0F;
  public static float e = 0.0F;
  public static float f = 0.0F;
  public static float g = 0.0F;
  public static float h = 0.0F;
  public static float i = 0.0F;
  public static float j = 0.0F;
  public final bor k;
  public final bor l;
  public final bor m;
  public final bor n;
  public final bor o;
  public final bor p;
  public final bor q;
  public final bor r;
  
  private bos()
  {
    this(bor.a, bor.a, bor.a, bor.a, bor.a, bor.a, bor.a, bor.a);
  }
  
  public bos(bos ☃)
  {
    this.k = ☃.k;
    this.l = ☃.l;
    this.m = ☃.m;
    this.n = ☃.n;
    this.o = ☃.o;
    this.p = ☃.p;
    this.q = ☃.q;
    this.r = ☃.r;
  }
  
  public bos(bor ☃, bor ☃, bor ☃, bor ☃, bor ☃, bor ☃, bor ☃, bor ☃)
  {
    this.k = ☃;
    this.l = ☃;
    this.m = ☃;
    this.n = ☃;
    this.o = ☃;
    this.p = ☃;
    this.q = ☃;
    this.r = ☃;
  }
  
  public void a(bos.b ☃)
  {
    a(b(☃), false);
  }
  
  public static void a(bor ☃, boolean ☃)
  {
    if (☃ == bor.a) {
      return;
    }
    int ☃ = ☃ ? -1 : 1;
    bni.c(☃ * (b + ☃.c.x), c + ☃.c.y, d + ☃.c.z);
    
    float ☃ = e + ☃.b.x;
    float ☃ = f + ☃.b.y;
    float ☃ = g + ☃.b.z;
    if (☃)
    {
      ☃ = -☃;
      ☃ = -☃;
    }
    bni.a(a(☃, ☃, ☃));
    
    bni.b(h + ☃.d.x, i + ☃.d.y, j + ☃.d.z);
  }
  
  private static Quaternion a(float ☃, float ☃, float ☃)
  {
    float ☃ = ☃ * 0.017453292F;
    float ☃ = ☃ * 0.017453292F;
    float ☃ = ☃ * 0.017453292F;
    
    float ☃ = on.a(0.5F * ☃);
    float ☃ = on.b(0.5F * ☃);
    float ☃ = on.a(0.5F * ☃);
    float ☃ = on.b(0.5F * ☃);
    float ☃ = on.a(0.5F * ☃);
    float ☃ = on.b(0.5F * ☃);
    
    return new Quaternion(☃ * ☃ * ☃ + ☃ * ☃ * ☃, ☃ * ☃ * ☃ - ☃ * ☃ * ☃, ☃ * ☃ * ☃ + ☃ * ☃ * ☃, ☃ * ☃ * ☃ - ☃ * ☃ * ☃);
  }
  
  public bor b(bos.b ☃)
  {
    switch (bos.1.a[☃.ordinal()])
    {
    case 1: 
      return this.k;
    case 2: 
      return this.l;
    case 3: 
      return this.m;
    case 4: 
      return this.n;
    case 5: 
      return this.o;
    case 6: 
      return this.p;
    case 7: 
      return this.q;
    case 8: 
      return this.r;
    }
    return bor.a;
  }
  
  public boolean c(bos.b ☃)
  {
    return b(☃) != bor.a;
  }
  
  static class a
    implements JsonDeserializer<bos>
  {
    public bos a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
      throws JsonParseException
    {
      JsonObject ☃ = ☃.getAsJsonObject();
      
      bor ☃ = a(☃, ☃, "thirdperson_righthand");
      bor ☃ = a(☃, ☃, "thirdperson_lefthand");
      if (☃ == bor.a) {
        ☃ = ☃;
      }
      bor ☃ = a(☃, ☃, "firstperson_righthand");
      bor ☃ = a(☃, ☃, "firstperson_lefthand");
      if (☃ == bor.a) {
        ☃ = ☃;
      }
      bor ☃ = a(☃, ☃, "head");
      bor ☃ = a(☃, ☃, "gui");
      bor ☃ = a(☃, ☃, "ground");
      bor ☃ = a(☃, ☃, "fixed");
      
      return new bos(☃, ☃, ☃, ☃, ☃, ☃, ☃, ☃);
    }
    
    private bor a(JsonDeserializationContext ☃, JsonObject ☃, String ☃)
    {
      if (☃.has(☃)) {
        return (bor)☃.deserialize(☃.get(☃), bor.class);
      }
      return bor.a;
    }
  }
  
  public static enum b
  {
    private b() {}
  }
}
