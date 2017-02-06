import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Random;

public abstract class baf
{
  private final bar[] a;
  
  protected baf(bar[] ☃)
  {
    this.a = ☃;
  }
  
  public abstract adq a(adq paramadq, Random paramRandom, azz paramazz);
  
  public bar[] a()
  {
    return this.a;
  }
  
  public static abstract class a<T extends baf>
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
    
    public abstract T b(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext, bar[] paramArrayOfbar);
  }
}
