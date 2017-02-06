import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class azy
{
  private static final Logger b = ;
  public static final azy a = new azy(new azw[0]);
  private final azw[] c;
  
  public azy(azw[] ☃)
  {
    this.c = ☃;
  }
  
  public List<adq> a(Random ☃, azz ☃)
  {
    List<adq> ☃ = Lists.newArrayList();
    if (☃.a(this))
    {
      for (azw ☃ : this.c) {
        ☃.b(☃, ☃, ☃);
      }
      ☃.b(this);
    }
    else
    {
      b.warn("Detected infinite loop in loot tables");
    }
    return ☃;
  }
  
  public void a(qg ☃, Random ☃, azz ☃)
  {
    List<adq> ☃ = a(☃, ☃);
    List<Integer> ☃ = a(☃, ☃);
    a(☃, ☃.size(), ☃);
    for (adq ☃ : ☃)
    {
      if (☃.isEmpty())
      {
        b.warn("Tried to over-fill a container");
        return;
      }
      if (☃ == null) {
        ☃.a(((Integer)☃.remove(☃.size() - 1)).intValue(), null);
      } else {
        ☃.a(((Integer)☃.remove(☃.size() - 1)).intValue(), ☃);
      }
    }
  }
  
  private void a(List<adq> ☃, int ☃, Random ☃)
  {
    List<adq> ☃ = Lists.newArrayList();
    for (Iterator<adq> ☃ = ☃.iterator(); ☃.hasNext();)
    {
      adq ☃ = (adq)☃.next();
      if (☃.b <= 0)
      {
        ☃.remove();
      }
      else if (☃.b > 1)
      {
        ☃.add(☃);
        ☃.remove();
      }
    }
    ☃ -= ☃.size();
    while ((☃ > 0) && (☃.size() > 0))
    {
      adq ☃ = (adq)☃.remove(on.a(☃, 0, ☃.size() - 1));
      int ☃ = on.a(☃, 1, ☃.b / 2);
      ☃.b -= ☃;
      adq ☃ = ☃.k();
      ☃.b = ☃;
      if ((☃.b > 1) && (☃.nextBoolean())) {
        ☃.add(☃);
      } else {
        ☃.add(☃);
      }
      if ((☃.b > 1) && (☃.nextBoolean())) {
        ☃.add(☃);
      } else {
        ☃.add(☃);
      }
    }
    ☃.addAll(☃);
    
    Collections.shuffle(☃, ☃);
  }
  
  private List<Integer> a(qg ☃, Random ☃)
  {
    List<Integer> ☃ = Lists.newArrayList();
    for (int ☃ = 0; ☃ < ☃.u_(); ☃++) {
      if (☃.a(☃) == null) {
        ☃.add(Integer.valueOf(☃));
      }
    }
    Collections.shuffle(☃, ☃);
    return ☃;
  }
  
  public static class a
    implements JsonDeserializer<azy>, JsonSerializer<azy>
  {
    public azy a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
      throws JsonParseException
    {
      JsonObject ☃ = od.m(☃, "loot table");
      azw[] ☃ = (azw[])od.a(☃, "pools", new azw[0], ☃, azw[].class);
      return new azy(☃);
    }
    
    public JsonElement a(azy ☃, Type ☃, JsonSerializationContext ☃)
    {
      JsonObject ☃ = new JsonObject();
      ☃.add("pools", ☃.serialize(azy.a(☃)));
      
      return ☃;
    }
  }
}
