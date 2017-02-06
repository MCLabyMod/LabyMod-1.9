import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public abstract class baf$a<T extends baf>
{
  private final kk a;
  private final Class<T> b;
  
  protected baf$a(kk ☃, Class<T> ☃)
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
