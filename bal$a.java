import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public class bal$a
  extends baf.a<bal>
{
  protected bal$a()
  {
    super(new kk("set_data"), bal.class);
  }
  
  public void a(JsonObject ☃, bal ☃, JsonSerializationContext ☃)
  {
    ☃.add("data", ☃.serialize(bal.a(☃)));
  }
  
  public bal a(JsonObject ☃, JsonDeserializationContext ☃, bar[] ☃)
  {
    return new bal(☃, (bac)od.a(☃, "data", ☃, bac.class));
  }
}
