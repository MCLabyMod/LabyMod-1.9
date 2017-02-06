import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public class bav$a
  extends bar.a<bav>
{
  protected bav$a()
  {
    super(new kk("random_chance"), bav.class);
  }
  
  public void a(JsonObject ☃, bav ☃, JsonSerializationContext ☃)
  {
    ☃.addProperty("chance", Float.valueOf(bav.a(☃)));
  }
  
  public bav a(JsonObject ☃, JsonDeserializationContext ☃)
  {
    return new bav(od.l(☃, "chance"));
  }
}
