import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public class bak$a
  extends baf.a<bak>
{
  protected bak$a()
  {
    super(new kk("set_damage"), bak.class);
  }
  
  public void a(JsonObject ☃, bak ☃, JsonSerializationContext ☃)
  {
    ☃.add("damage", ☃.serialize(bak.a(☃)));
  }
  
  public bak a(JsonObject ☃, JsonDeserializationContext ☃, bar[] ☃)
  {
    return new bak(☃, (bac)od.a(☃, "damage", ☃, bac.class));
  }
}
