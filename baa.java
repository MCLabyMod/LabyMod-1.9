import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Collection;
import java.util.Random;

public class baa
  extends azx
{
  protected final kk a;
  
  public baa(kk ☃, int ☃, int ☃, bar[] ☃)
  {
    super(☃, ☃, ☃);
    this.a = ☃;
  }
  
  public void a(Collection<adq> ☃, Random ☃, azz ☃)
  {
    azy ☃ = ☃.e().a(this.a);
    Collection<adq> ☃ = ☃.a(☃, ☃);
    ☃.addAll(☃);
  }
  
  protected void a(JsonObject ☃, JsonSerializationContext ☃)
  {
    ☃.addProperty("name", this.a.toString());
  }
  
  public static baa a(JsonObject ☃, JsonDeserializationContext ☃, int ☃, int ☃, bar[] ☃)
  {
    kk ☃ = new kk(od.h(☃, "name"));
    
    return new baa(☃, ☃, ☃, ☃);
  }
}
