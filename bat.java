import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class bat
  implements bar
{
  private final baz[] a;
  private final azz.b b;
  
  public bat(baz[] ☃, azz.b ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public boolean a(Random ☃, azz ☃)
  {
    rr ☃ = ☃.a(this.b);
    if (☃ == null) {
      return false;
    }
    int ☃ = 0;
    for (int ☃ = this.a.length; ☃ < ☃; ☃++) {
      if (!this.a[☃].a(☃, ☃)) {
        return false;
      }
    }
    return true;
  }
  
  public static class a
    extends bar.a<bat>
  {
    protected a()
    {
      super(bat.class);
    }
    
    public void a(JsonObject ☃, bat ☃, JsonSerializationContext ☃)
    {
      JsonObject ☃ = new JsonObject();
      for (baz ☃ : bat.a(☃))
      {
        baz.a<baz> ☃ = bay.a(☃);
        ☃.add(☃.a().toString(), ☃.a(☃, ☃));
      }
      ☃.add("properties", ☃);
      ☃.add("entity", ☃.serialize(bat.b(☃)));
    }
    
    public bat a(JsonObject ☃, JsonDeserializationContext ☃)
    {
      Set<Map.Entry<String, JsonElement>> ☃ = od.t(☃, "properties").entrySet();
      baz[] ☃ = new baz[☃.size()];
      int ☃ = 0;
      for (Map.Entry<String, JsonElement> ☃ : ☃) {
        ☃[(☃++)] = bay.a(new kk((String)☃.getKey())).a((JsonElement)☃.getValue(), ☃);
      }
      return new bat(☃, (azz.b)od.a(☃, "entity", ☃, azz.b.class));
    }
  }
}
