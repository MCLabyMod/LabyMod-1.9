import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public class baw$a
  extends bar.a<baw>
{
  protected baw$a()
  {
    super(new kk("random_chance_with_looting"), baw.class);
  }
  
  public void a(JsonObject ☃, baw ☃, JsonSerializationContext ☃)
  {
    ☃.addProperty("chance", Float.valueOf(baw.a(☃)));
    ☃.addProperty("looting_multiplier", Float.valueOf(baw.b(☃)));
  }
  
  public baw a(JsonObject ☃, JsonDeserializationContext ☃)
  {
    return new baw(od.l(☃, "chance"), od.l(☃, "looting_multiplier"));
  }
}
