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

public class jz
{
  private eu a;
  private jz.a b;
  private jz.c c;
  private String d;
  
  public eu a()
  {
    return this.a;
  }
  
  public void a(eu ☃)
  {
    this.a = ☃;
  }
  
  public jz.a b()
  {
    return this.b;
  }
  
  public void a(jz.a ☃)
  {
    this.b = ☃;
  }
  
  public jz.c c()
  {
    return this.c;
  }
  
  public void a(jz.c ☃)
  {
    this.c = ☃;
  }
  
  public void a(String ☃)
  {
    this.d = ☃;
  }
  
  public String d()
  {
    return this.d;
  }
  
  public static class a
  {
    private final int a;
    private final int b;
    private GameProfile[] c;
    
    public a(int ☃, int ☃)
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
  
  public static class c
  {
    private final String a;
    private final int b;
    
    public c(String ☃, int ☃)
    {
      this.a = ☃;
      this.b = ☃;
    }
    
    public String a()
    {
      return this.a;
    }
    
    public int b()
    {
      return this.b;
    }
    
    public static class a
      implements JsonDeserializer<jz.c>, JsonSerializer<jz.c>
    {
      public jz.c a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
        throws JsonParseException
      {
        JsonObject ☃ = od.m(☃, "version");
        return new jz.c(od.h(☃, "name"), od.n(☃, "protocol"));
      }
      
      public JsonElement a(jz.c ☃, Type ☃, JsonSerializationContext ☃)
      {
        JsonObject ☃ = new JsonObject();
        ☃.addProperty("name", ☃.a());
        ☃.addProperty("protocol", Integer.valueOf(☃.b()));
        return ☃;
      }
    }
  }
  
  public static class b
    implements JsonDeserializer<jz>, JsonSerializer<jz>
  {
    public jz a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
      throws JsonParseException
    {
      JsonObject ☃ = od.m(☃, "status");
      jz ☃ = new jz();
      if (☃.has("description")) {
        ☃.a((eu)☃.deserialize(☃.get("description"), eu.class));
      }
      if (☃.has("players")) {
        ☃.a((jz.a)☃.deserialize(☃.get("players"), jz.a.class));
      }
      if (☃.has("version")) {
        ☃.a((jz.c)☃.deserialize(☃.get("version"), jz.c.class));
      }
      if (☃.has("favicon")) {
        ☃.a(od.h(☃, "favicon"));
      }
      return ☃;
    }
    
    public JsonElement a(jz ☃, Type ☃, JsonSerializationContext ☃)
    {
      JsonObject ☃ = new JsonObject();
      if (☃.a() != null) {
        ☃.add("description", ☃.serialize(☃.a()));
      }
      if (☃.b() != null) {
        ☃.add("players", ☃.serialize(☃.b()));
      }
      if (☃.c() != null) {
        ☃.add("version", ☃.serialize(☃.c()));
      }
      if (☃.d() != null) {
        ☃.addProperty("favicon", ☃.d());
      }
      return ☃;
    }
  }
}
