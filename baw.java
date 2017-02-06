import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Random;

public class baw
  implements bar
{
  private final float a;
  private final float b;
  
  public baw(float ☃, float ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public boolean a(Random ☃, azz ☃)
  {
    int ☃ = 0;
    if ((☃.c() instanceof sa)) {
      ☃ = ago.h((sa)☃.c());
    }
    return ☃.nextFloat() < this.a + ☃ * this.b;
  }
  
  public static class a
    extends bar.a<baw>
  {
    protected a()
    {
      super(baw.class);
    }
    
    public void a(JsonObject ☃, baw ☃, JsonSerializationContext ☃)
    {
      ☃.addProperty("chance", Float.valueOf(baw.a(☃)));
      ☃.addProperty("looting_multiplier", Float.valueOf(baw.b(☃)));
    }
    
    public baw a(JsonObject ☃, JsonDeserializationContext ☃)
    {
      return new baw(od.l(☃, "chance"), od.l(☃, "looting_multiplier"));
    }
  }
}
