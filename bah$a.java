import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public class bah$a
  extends baf.a<bah>
{
  protected bah$a()
  {
    super(new kk("looting_enchant"), bah.class);
  }
  
  public void a(JsonObject ☃, bah ☃, JsonSerializationContext ☃)
  {
    ☃.add("count", ☃.serialize(bah.a(☃)));
  }
  
  public bah a(JsonObject ☃, JsonDeserializationContext ☃, bar[] ☃)
  {
    return new bah(☃, (bac)od.a(☃, "count", ☃, bac.class));
  }
}
