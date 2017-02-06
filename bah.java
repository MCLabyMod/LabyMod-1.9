import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Random;

public class bah
  extends baf
{
  private final bac a;
  
  public bah(bar[] ☃, bac ☃)
  {
    super(☃);
    this.a = ☃;
  }
  
  public adq a(adq ☃, Random ☃, azz ☃)
  {
    rr ☃ = ☃.c();
    if ((☃ instanceof sa))
    {
      int ☃ = ago.h((sa)☃);
      if (☃ == 0) {
        return ☃;
      }
      float ☃ = ☃ * this.a.b(☃);
      ☃.b += Math.round(☃);
    }
    return ☃;
  }
  
  public static class a
    extends baf.a<bah>
  {
    protected a()
    {
      super(bah.class);
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
}
