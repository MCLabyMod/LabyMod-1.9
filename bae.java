import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Random;

public class bae
  extends baf
{
  private final bac a;
  private final boolean b;
  
  public bae(bar[] ☃, bac ☃, boolean ☃)
  {
    super(☃);
    this.a = ☃;
    this.b = ☃;
  }
  
  public adq a(adq ☃, Random ☃, azz ☃)
  {
    ago.a(☃, ☃, this.a.a(☃), this.b);
    return ☃;
  }
  
  public static class a
    extends baf.a<bae>
  {
    public a()
    {
      super(bae.class);
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
}
