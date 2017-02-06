import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;

public class bai$b
  extends baf.a<bai>
{
  public bai$b()
  {
    super(new kk("set_attributes"), bai.class);
  }
  
  public void a(JsonObject ☃, bai ☃, JsonSerializationContext ☃)
  {
    JsonArray ☃ = new JsonArray();
    for (bai.a ☃ : bai.a(☃)) {
      ☃.add(☃.a(☃));
    }
    ☃.add("modifiers", ☃);
  }
  
  public bai a(JsonObject ☃, JsonDeserializationContext ☃, bar[] ☃)
  {
    JsonArray ☃ = od.u(☃, "modifiers");
    bai.a[] ☃ = new bai.a[☃.size()];
    int ☃ = 0;
    for (JsonElement ☃ : ☃) {
      ☃[(☃++)] = bai.a.a(od.m(☃, "modifier"), ☃);
    }
    if (☃.length == 0) {
      throw new JsonSyntaxException("Invalid attribute modifiers array; cannot be empty");
    }
    return new bai(☃, ☃);
  }
}
