import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;

public class bam$a
  extends baf.a<bam>
{
  public bam$a()
  {
    super(new kk("set_nbt"), bam.class);
  }
  
  public void a(JsonObject ☃, bam ☃, JsonSerializationContext ☃)
  {
    ☃.addProperty("tag", bam.a(☃).toString());
  }
  
  public bam a(JsonObject ☃, JsonDeserializationContext ☃, bar[] ☃)
  {
    try
    {
      dn ☃ = ed.a(od.h(☃, "tag"));
      return new bam(☃, ☃);
    }
    catch (ec ☃)
    {
      throw new JsonSyntaxException(☃);
    }
  }
}
