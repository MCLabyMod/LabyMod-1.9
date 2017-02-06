import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bad
  extends baf
{
  private static final Logger a = ;
  private final List<agm> b;
  
  public bad(bar[] ☃, List<agm> ☃)
  {
    super(☃);
    this.b = ☃;
  }
  
  public adq a(adq ☃, Random ☃, azz ☃)
  {
    agm ☃;
    agm ☃;
    if ((this.b == null) || (this.b.isEmpty()))
    {
      List<agm> ☃ = Lists.newArrayList();
      for (agm ☃ : agm.b) {
        if ((☃.b() == ads.aS) || (☃.a(☃))) {
          ☃.add(☃);
        }
      }
      if (☃.isEmpty())
      {
        a.warn("Couldn't find a compatible enchantment for " + ☃);
        return ☃;
      }
      ☃ = (agm)☃.get(☃.nextInt(☃.size()));
    }
    else
    {
      ☃ = (agm)this.b.get(☃.nextInt(this.b.size()));
    }
    int ☃ = on.a(☃, ☃.d(), ☃.b());
    if (☃.b() == ads.aS)
    {
      ☃.a(ads.cn);
      ads.cn.a(☃, new agp(☃, ☃));
    }
    else
    {
      ☃.a(☃, ☃);
    }
    return ☃;
  }
  
  public static class a
    extends baf.a<bad>
  {
    public a()
    {
      super(bad.class);
    }
    
    public void a(JsonObject ☃, bad ☃, JsonSerializationContext ☃)
    {
      if ((bad.a(☃) != null) && (!bad.a(☃).isEmpty()))
      {
        JsonArray ☃ = new JsonArray();
        for (agm ☃ : bad.a(☃))
        {
          kk ☃ = (kk)agm.b.b(☃);
          if (☃ == null) {
            throw new IllegalArgumentException("Don't know how to serialize enchantment " + ☃);
          }
          ☃.add(new JsonPrimitive(☃.toString()));
        }
        ☃.add("enchantments", ☃);
      }
    }
    
    public bad a(JsonObject ☃, JsonDeserializationContext ☃, bar[] ☃)
    {
      List<agm> ☃ = null;
      if (☃.has("enchantments"))
      {
        ☃ = Lists.newArrayList();
        JsonArray ☃ = od.u(☃, "enchantments");
        for (JsonElement ☃ : ☃)
        {
          String ☃ = od.a(☃, "enchantment");
          agm ☃ = (agm)agm.b.c(new kk(☃));
          if (☃ == null) {
            throw new JsonSyntaxException("Unknown enchantment '" + ☃ + "'");
          }
          ☃.add(☃);
        }
      }
      return new bad(☃, ☃);
    }
  }
}
