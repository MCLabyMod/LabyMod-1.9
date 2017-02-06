import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import java.util.UUID;

class bai$a
{
  private final String a;
  private final String b;
  private final int c;
  private final bac d;
  private final UUID e;
  private final rw[] f;
  
  private bai$a(String ☃, String ☃, int ☃, bac ☃, rw[] ☃, UUID ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
    this.e = ☃;
    this.f = ☃;
  }
  
  public JsonObject a(JsonSerializationContext ☃)
  {
    JsonObject ☃ = new JsonObject();
    ☃.addProperty("name", this.a);
    ☃.addProperty("attribute", this.b);
    ☃.addProperty("operation", a(this.c));
    ☃.add("amount", ☃.serialize(this.d));
    if (this.e != null) {
      ☃.addProperty("id", this.e.toString());
    }
    if (this.f.length == 1)
    {
      ☃.addProperty("slot", this.f[0].d());
    }
    else
    {
      JsonArray ☃ = new JsonArray();
      for (rw ☃ : this.f) {
        ☃.add(new JsonPrimitive(☃.d()));
      }
      ☃.add("slot", ☃);
    }
    return ☃;
  }
  
  public static a a(JsonObject ☃, JsonDeserializationContext ☃)
  {
    String ☃ = od.h(☃, "name");
    String ☃ = od.h(☃, "attribute");
    int ☃ = a(od.h(☃, "operation"));
    bac ☃ = (bac)od.a(☃, "amount", ☃, bac.class);
    
    UUID ☃ = null;
    rw[] ☃;
    if (od.a(☃, "slot"))
    {
      ☃ = new rw[] { rw.a(od.h(☃, "slot")) };
    }
    else if (od.d(☃, "slot"))
    {
      JsonArray ☃ = od.u(☃, "slot");
      rw[] ☃ = new rw[☃.size()];
      int ☃ = 0;
      for (JsonElement ☃ : ☃) {
        ☃[(☃++)] = rw.a(od.a(☃, "slot"));
      }
      if (☃.length == 0) {
        throw new JsonSyntaxException("Invalid attribute modifier slot; must contain at least one entry.");
      }
    }
    else
    {
      throw new JsonSyntaxException("Invalid or missing attribute modifier slot; must be either string or array of strings.");
    }
    rw[] ☃;
    if (☃.has("id"))
    {
      String ☃ = od.h(☃, "id");
      try
      {
        ☃ = UUID.fromString(☃);
      }
      catch (IllegalArgumentException ☃)
      {
        throw new JsonSyntaxException("Invalid attribute modifier id '" + ☃ + "' (must be UUID format, with dashes)");
      }
    }
    return new a(☃, ☃, ☃, ☃, ☃, ☃);
  }
  
  private static String a(int ☃)
  {
    switch (☃)
    {
    case 0: 
      return "addition";
    case 1: 
      return "multiply_base";
    case 2: 
      return "multiply_total";
    }
    throw new IllegalArgumentException("Unknown operation " + ☃);
  }
  
  private static int a(String ☃)
  {
    if (☃.equals("addition")) {
      return 0;
    }
    if (☃.equals("multiply_base")) {
      return 1;
    }
    if (☃.equals("multiply_total")) {
      return 2;
    }
    throw new JsonSyntaxException("Unknown attribute modifier operation " + ☃);
  }
}
