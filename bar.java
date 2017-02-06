import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Random;

public abstract interface bar
{
  public abstract boolean a(Random paramRandom, azz paramazz);
  
  public static abstract class a<T extends bar>
  {
    private final kk a;
    private final Class<T> b;
    
    protected a(kk ☃, Class<T> ☃)
    {
      this.a = ☃;
      this.b = ☃;
    }
    
    public kk a()
    {
      return this.a;
    }
    
    public Class<T> b()
    {
      return this.b;
    }
    
    public abstract void a(JsonObject paramJsonObject, T paramT, JsonSerializationContext paramJsonSerializationContext);
    
    public abstract T b(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext);
  }
}
