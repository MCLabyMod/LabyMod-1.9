import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import java.util.Random;

public class bam
  extends baf
{
  private final dn a;
  
  public bam(bar[] ☃, dn ☃)
  {
    super(☃);
    this.a = ☃;
  }
  
  public adq a(adq ☃, Random ☃, azz ☃)
  {
    dn ☃ = ☃.o();
    if (☃ == null) {
      ☃ = (dn)this.a.b();
    } else {
      ☃.a(this.a);
    }
    ☃.d(☃);
    return ☃;
  }
  
  public static class a
    extends baf.a<bam>
  {
    public a()
    {
      super(bam.class);
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
}
