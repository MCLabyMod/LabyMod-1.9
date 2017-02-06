import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;

public abstract class baz$a<T extends baz>
{
  private final kk a;
  private final Class<T> b;
  
  protected baz$a(kk ☃, Class<T> ☃)
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
  
  public abstract JsonElement a(T paramT, JsonSerializationContext paramJsonSerializationContext);
  
  public abstract T a(JsonElement paramJsonElement, JsonDeserializationContext paramJsonDeserializationContext);
}
