import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import java.util.Random;

public class bba
  implements baz
{
  private final boolean a;
  
  public bba(boolean ☃)
  {
    this.a = ☃;
  }
  
  public boolean a(Random ☃, rr ☃)
  {
    return ☃.aH() == this.a;
  }
  
  public static class a
    extends baz.a<bba>
  {
    protected a()
    {
      super(bba.class);
    }
    
    public JsonElement a(bba ☃, JsonSerializationContext ☃)
    {
      return new JsonPrimitive(Boolean.valueOf(bba.a(☃)));
    }
    
    public bba b(JsonElement ☃, JsonDeserializationContext ☃)
    {
      return new bba(od.c(☃, "on_fire"));
    }
  }
}
