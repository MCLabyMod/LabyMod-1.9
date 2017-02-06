import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bal
  extends baf
{
  private static final Logger a = ;
  private final bac b;
  
  public bal(bar[] ☃, bac ☃)
  {
    super(☃);
    this.b = ☃;
  }
  
  public adq a(adq ☃, Random ☃, azz ☃)
  {
    if (!☃.e()) {
      ☃.b(this.b.a(☃));
    } else {
      a.warn("Couldn't set data of loot item " + ☃);
    }
    return ☃;
  }
  
  public static class a
    extends baf.a<bal>
  {
    protected a()
    {
      super(bal.class);
    }
    
    public void a(JsonObject ☃, bal ☃, JsonSerializationContext ☃)
    {
      ☃.add("data", ☃.serialize(bal.a(☃)));
    }
    
    public bal a(JsonObject ☃, JsonDeserializationContext ☃, bar[] ☃)
    {
      return new bal(☃, (bac)od.a(☃, "data", ☃, bac.class));
    }
  }
}
