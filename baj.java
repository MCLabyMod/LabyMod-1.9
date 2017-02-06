import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Random;

public class baj
  extends baf
{
  private final bac a;
  
  public baj(bar[] ☃, bac ☃)
  {
    super(☃);
    this.a = ☃;
  }
  
  public adq a(adq ☃, Random ☃, azz ☃)
  {
    ☃.b = this.a.a(☃);
    return ☃;
  }
  
  public static class a
    extends baf.a<baj>
  {
    protected a()
    {
      super(baj.class);
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
}
