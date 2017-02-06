import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class bou
{
  private final kk a;
  private final bxp b;
  private final boolean c;
  private final int d;
  
  public bou(kk ☃, bxp ☃, boolean ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
  }
  
  public kk a()
  {
    return this.a;
  }
  
  public bxp b()
  {
    return this.b;
  }
  
  public boolean c()
  {
    return this.c;
  }
  
  public int d()
  {
    return this.d;
  }
  
  public String toString()
  {
    return "Variant{modelLocation=" + this.a + ", rotation=" + this.b + ", uvLock=" + this.c + ", weight=" + this.d + '}';
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if ((☃ instanceof bou))
    {
      bou ☃ = (bou)☃;
      return (this.a.equals(☃.a)) && (this.b == ☃.b) && (this.c == ☃.c) && (this.d == ☃.d);
    }
    return false;
  }
  
  public int hashCode()
  {
    int ☃ = this.a.hashCode();
    ☃ = 31 * ☃ + this.b.hashCode();
    ☃ = 31 * ☃ + Boolean.valueOf(this.c).hashCode();
    ☃ = 31 * ☃ + this.d;
    return ☃;
  }
  
  public static class a
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
}
