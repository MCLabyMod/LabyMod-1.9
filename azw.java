import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.ArrayUtils;

public class azw
{
  private final azx[] a;
  private final bar[] b;
  private final bac c;
  private final bac d;
  
  public azw(azx[] ☃, bar[] ☃, bac ☃, bac ☃)
  {
    this.a = ☃;
    this.b = ☃;
    this.c = ☃;
    this.d = ☃;
  }
  
  protected void a(Collection<adq> ☃, Random ☃, azz ☃)
  {
    List<azx> ☃ = Lists.newArrayList();
    int ☃ = 0;
    for (azx ☃ : this.a) {
      if (bas.a(☃.e, ☃, ☃))
      {
        int ☃ = ☃.a(☃.f());
        if (☃ > 0)
        {
          ☃.add(☃);
          ☃ += ☃;
        }
      }
    }
    if ((☃ == 0) || (☃.isEmpty())) {
      return;
    }
    int ☃ = ☃.nextInt(☃);
    for (azx ☃ : ☃)
    {
      ☃ -= ☃.a(☃.f());
      if (☃ < 0)
      {
        ☃.a(☃, ☃, ☃);
        return;
      }
    }
  }
  
  public void b(Collection<adq> ☃, Random ☃, azz ☃)
  {
    if (!bas.a(this.b, ☃, ☃)) {
      return;
    }
    int ☃ = this.c.a(☃) + on.d(this.d.b(☃) * ☃.f());
    for (int ☃ = 0; ☃ < ☃; ☃++) {
      a(☃, ☃, ☃);
    }
  }
  
  public static class a
    implements JsonDeserializer<azw>, JsonSerializer<azw>
  {
    public azw a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
      throws JsonParseException
    {
      JsonObject ☃ = od.m(☃, "loot pool");
      azx[] ☃ = (azx[])od.a(☃, "entries", ☃, azx[].class);
      bar[] ☃ = (bar[])od.a(☃, "conditions", new bar[0], ☃, bar[].class);
      bac ☃ = (bac)od.a(☃, "rolls", ☃, bac.class);
      bac ☃ = (bac)od.a(☃, "bonus_rolls", new bac(0.0F, 0.0F), ☃, bac.class);
      return new azw(☃, ☃, ☃, ☃);
    }
    
    public JsonElement a(azw ☃, Type ☃, JsonSerializationContext ☃)
    {
      JsonObject ☃ = new JsonObject();
      ☃.add("entries", ☃.serialize(azw.a(☃)));
      ☃.add("rolls", ☃.serialize(azw.b(☃)));
      if ((azw.c(☃).a() != 0.0F) && (azw.c(☃).b() != 0.0F)) {
        ☃.add("bonus_rolls", ☃.serialize(azw.c(☃)));
      }
      if (!ArrayUtils.isEmpty(azw.d(☃))) {
        ☃.add("conditions", ☃.serialize(azw.d(☃)));
      }
      return ☃;
    }
  }
}
