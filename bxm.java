import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class bxm
  extends bwt<bxl>
{
  public bxl a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
    throws JsonParseException
  {
    JsonObject ☃ = ☃.getAsJsonObject();
    boolean ☃ = od.a(☃, "blur", false);
    boolean ☃ = od.a(☃, "clamp", false);
    
    return new bxl(☃, ☃);
  }
  
  public String a()
  {
    return "texture";
  }
}
