import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public class baj$a
  extends baf.a<baj>
{
  protected baj$a()
  {
    super(new kk("set_count"), baj.class);
  }
  
  public void a(JsonObject ☃, baj ☃, JsonSerializationContext ☃)
  {
    ☃.add("count", ☃.serialize(baj.a(☃)));
  }
  
  public baj a(JsonObject ☃, JsonDeserializationContext ☃, bar[] ☃)
  {
    return new baj(☃, (bac)od.a(☃, "count", ☃, bac.class));
  }
}
