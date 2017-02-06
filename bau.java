import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Random;

public class bau
  implements bar
{
  private final boolean a;
  
  public bau(boolean ☃)
  {
    this.a = ☃;
  }
  
  public boolean a(Random ☃, azz ☃)
  {
    boolean ☃ = ☃.b() != null;
    return ☃ == (!this.a);
  }
  
  public static class a
    extends bar.a<bau>
  {
    protected a()
    {
      super(bau.class);
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
}
