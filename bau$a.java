import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public class bau$a
  extends bar.a<bau>
{
  protected bau$a()
  {
    super(new kk("killed_by_player"), bau.class);
  }
  
  public void a(JsonObject ☃, bau ☃, JsonSerializationContext ☃)
  {
    ☃.addProperty("inverse", Boolean.valueOf(bau.a(☃)));
  }
  
  public bau a(JsonObject ☃, JsonDeserializationContext ☃)
  {
    return new bau(od.a(☃, "inverse", false));
  }
}
