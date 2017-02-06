import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.util.UUID;

public class mo
  extends mp<GameProfile>
{
  private final int a;
  private final boolean b;
  
  public mo(GameProfile ☃, int ☃, boolean ☃)
  {
    super(☃);
    this.a = ☃;
    this.b = ☃;
  }
  
  public mo(JsonObject ☃)
  {
    super(b(☃), ☃);
    this.a = (☃.has("level") ? ☃.get("level").getAsInt() : 0);
    this.b = ((☃.has("bypassesPlayerLimit")) && (☃.get("bypassesPlayerLimit").getAsBoolean()));
  }
  
  public int a()
  {
    return this.a;
  }
  
  public boolean b()
  {
    return this.b;
  }
  
  protected void a(JsonObject ☃)
  {
    if (f() == null) {
      return;
    }
    ☃.addProperty("uuid", ((GameProfile)f()).getId() == null ? "" : ((GameProfile)f()).getId().toString());
    ☃.addProperty("name", ((GameProfile)f()).getName());
    super.a(☃);
    ☃.addProperty("level", Integer.valueOf(this.a));
    ☃.addProperty("bypassesPlayerLimit", Boolean.valueOf(this.b));
  }
  
  private static GameProfile b(JsonObject ☃)
  {
    if ((!☃.has("uuid")) || (!☃.has("name"))) {
      return null;
    }
    String ☃ = ☃.get("uuid").getAsString();
    UUID ☃;
    try
    {
      ☃ = UUID.fromString(☃);
    }
    catch (Throwable ☃)
    {
      return null;
    }
    return new GameProfile(☃, ☃.get("name").getAsString());
  }
}
