import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import java.util.Random;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bai
  extends baf
{
  private static final Logger a = ;
  private final bai.a[] b;
  
  public bai(bar[] ☃, bai.a[] ☃)
  {
    super(☃);
    this.b = ☃;
  }
  
  public adq a(adq ☃, Random ☃, azz ☃)
  {
    for (bai.a ☃ : this.b)
    {
      UUID ☃ = bai.a.a(☃);
      if (☃ == null) {
        ☃ = UUID.randomUUID();
      }
      rw ☃ = bai.a.b(☃)[☃.nextInt(bai.a.b(☃).length)];
      ☃.a(bai.a.c(☃), new sn(☃, bai.a.d(☃), bai.a.e(☃).b(☃), bai.a.f(☃)), ☃);
    }
    return ☃;
  }
  
  public static class b
    extends baf.a<bai>
  {
    public b()
    {
      super(bai.class);
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
  
  static class a
  {
    private final String a;
    private final String b;
    private final int c;
    private final bac d;
    private final UUID e;
    private final rw[] f;
    
    private a(String ☃, String ☃, int ☃, bac ☃, rw[] ☃, UUID ☃)
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
}
