import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.authlib.GameProfile;
import java.lang.reflect.Type;
import java.util.UUID;

public class jz$a
{
  private final int a;
  private final int b;
  private GameProfile[] c;
  
  public jz$a(int ☃, int ☃)
  {
    this.a = ☃;
    this.b = ☃;
  }
  
  public int a()
  {
    return this.a;
  }
  
  public int b()
  {
    return this.b;
  }
  
  public GameProfile[] c()
  {
    return this.c;
  }
  
  public void a(GameProfile[] ☃)
  {
    this.c = ☃;
  }
  
  public static class a
    implements JsonDeserializer<jz.a>, JsonSerializer<jz.a>
  {
    public jz.a a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
      throws JsonParseException
    {
      JsonObject ☃ = od.m(☃, "players");
      jz.a ☃ = new jz.a(od.n(☃, "max"), od.n(☃, "online"));
      if (od.d(☃, "sample"))
      {
        JsonArray ☃ = od.u(☃, "sample");
        if (☃.size() > 0)
        {
          GameProfile[] ☃ = new GameProfile[☃.size()];
          for (int ☃ = 0; ☃ < ☃.length; ☃++)
          {
            JsonObject ☃ = od.m(☃.get(☃), "player[" + ☃ + "]");
            String ☃ = od.h(☃, "id");
            ☃[☃] = new GameProfile(UUID.fromString(☃), od.h(☃, "name"));
          }
          ☃.a(☃);
        }
      }
      return ☃;
    }
    
    public JsonElement a(jz.a ☃, Type ☃, JsonSerializationContext ☃)
    {
      JsonObject ☃ = new JsonObject();
      
      ☃.addProperty("max", Integer.valueOf(☃.a()));
      ☃.addProperty("online", Integer.valueOf(☃.b()));
      if ((☃.c() != null) && (☃.c().length > 0))
      {
        JsonArray ☃ = new JsonArray();
        for (int ☃ = 0; ☃ < ☃.c().length; ☃++)
        {
          JsonObject ☃ = new JsonObject();
          UUID ☃ = ☃.c()[☃].getId();
          ☃.addProperty("id", ☃ == null ? "" : ☃.toString());
          ☃.addProperty("name", ☃.c()[☃].getName());
          ☃.add(☃);
        }
        ☃.add("sample", ☃);
      }
      return ☃;
    }
  }
}
