import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class pr
  implements ox
{
  public static final Gson a = new GsonBuilder().registerTypeAdapter(eu.class, new JsonDeserializer()
  {
    public eu a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
      throws JsonParseException
    {
      if (☃.isJsonPrimitive()) {
        return new fa(☃.getAsString());
      }
      if (☃.isJsonArray())
      {
        JsonArray ☃ = ☃.getAsJsonArray();
        eu ☃ = null;
        for (JsonElement ☃ : ☃)
        {
          eu ☃ = a(☃, ☃.getClass(), ☃);
          if (☃ == null) {
            ☃ = ☃;
          } else {
            ☃.a(☃);
          }
        }
        return ☃;
      }
      throw new JsonParseException("Don't know how to turn " + ☃.toString() + " into a Component");
    }
  }).create();
  
  public int a()
  {
    return 101;
  }
  
  public dn a(dn ☃)
  {
    if ("Sign".equals(☃.l("id")))
    {
      a(☃, "Text1");
      a(☃, "Text2");
      a(☃, "Text3");
      a(☃, "Text4");
    }
    return ☃;
  }
  
  private void a(dn ☃, String ☃)
  {
    String ☃ = ☃.l(☃);
    
    eu ☃ = null;
    if (("null".equals(☃)) || (os.b(☃)))
    {
      ☃ = new fa("");
    }
    else if (((☃.charAt(0) == '"') && (☃.charAt(☃.length() - 1) == '"')) || ((☃.charAt(0) == '{') && (☃.charAt(☃.length() - 1) == '}')))
    {
      try
      {
        ☃ = (eu)a.fromJson(☃, eu.class);
        if (☃ == null) {
          ☃ = new fa("");
        }
      }
      catch (JsonParseException localJsonParseException) {}
      if (☃ == null) {
        try
        {
          ☃ = eu.a.a(☃);
        }
        catch (JsonParseException localJsonParseException1) {}
      }
      if (☃ == null) {
        try
        {
          ☃ = eu.a.b(☃);
        }
        catch (JsonParseException localJsonParseException2) {}
      }
      if (☃ == null) {
        ☃ = new fa(☃);
      }
    }
    else
    {
      ☃ = new fa(☃);
    }
    ☃.a(☃, eu.a.a(☃));
  }
}
