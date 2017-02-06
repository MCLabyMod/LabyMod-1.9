import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Random;

public class bav
  implements bar
{
  private final float a;
  
  public bav(float ☃)
  {
    this.a = ☃;
  }
  
  public boolean a(Random ☃, azz ☃)
  {
    return ☃.nextFloat() < this.a;
  }
  
  public static class a
    extends bar.a<bav>
  {
    protected a()
    {
      super(bav.class);
    }
    
    public void a(JsonObject ☃, bav ☃, JsonSerializationContext ☃)
    {
      ☃.addProperty("chance", Float.valueOf(bav.a(☃)));
    }
    
    public bav a(JsonObject ☃, JsonDeserializationContext ☃)
    {
      return new bav(od.l(☃, "chance"));
    }
  }
}
