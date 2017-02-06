import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ban
  extends baf
{
  private static final Logger a = ;
  
  public ban(bar[] ☃)
  {
    super(☃);
  }
  
  public adq a(adq ☃, Random ☃, azz ☃)
  {
    adq ☃ = afq.a().a(☃);
    if (☃ == null)
    {
      a.warn("Couldn't smelt " + ☃ + " because there is no smelting recipe");
      return ☃;
    }
    adq ☃ = ☃.k();
    ☃.b = ☃.b;
    return ☃;
  }
  
  public static class a
    extends baf.a<ban>
  {
    protected a()
    {
      super(ban.class);
    }
    
    public void a(JsonObject ☃, ban ☃, JsonSerializationContext ☃) {}
    
    public ban a(JsonObject ☃, JsonDeserializationContext ☃, bar[] ☃)
    {
      return new ban(☃);
    }
  }
}
