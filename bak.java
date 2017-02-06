import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bak
  extends baf
{
  private static final Logger a = ;
  private final bac b;
  
  public bak(bar[] ☃, bac ☃)
  {
    super(☃);
    this.b = ☃;
  }
  
  public adq a(adq ☃, Random ☃, azz ☃)
  {
    if (☃.e())
    {
      float ☃ = 1.0F - this.b.b(☃);
      ☃.b(on.d(☃ * ☃.j()));
    }
    else
    {
      a.warn("Couldn't set damage of loot item " + ☃);
    }
    return ☃;
  }
  
  public static class a
    extends baf.a<bak>
  {
    protected a()
    {
      super(bak.class);
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
}
