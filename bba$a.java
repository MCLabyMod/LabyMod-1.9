import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;

public class bba$a
  extends baz.a<bba>
{
  protected bba$a()
  {
    super(new kk("on_fire"), bba.class);
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
