import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public class bae$a
  extends baf.a<bae>
{
  public bae$a()
  {
    super(new kk("enchant_with_levels"), bae.class);
  }
  
  public void a(JsonObject ☃, bae ☃, JsonSerializationContext ☃)
  {
    ☃.add("levels", ☃.serialize(bae.a(☃)));
    ☃.addProperty("treasure", Boolean.valueOf(bae.b(☃)));
  }
  
  public bae a(JsonObject ☃, JsonDeserializationContext ☃, bar[] ☃)
  {
    bac ☃ = (bac)od.a(☃, "levels", ☃, bac.class);
    boolean ☃ = od.a(☃, "treasure", false);
    return new bae(☃, ☃, ☃);
  }
}
