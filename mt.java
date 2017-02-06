import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;
import java.util.Map;
import java.util.UUID;

public class mt
  extends mq<GameProfile, mu>
{
  public mt(File ☃)
  {
    super(☃);
  }
  
  protected mp<GameProfile> a(JsonObject ☃)
  {
    return new mu(☃);
  }
  
  public String[] a()
  {
    String[] ☃ = new String[e().size()];
    int ☃ = 0;
    for (mu ☃ : e().values()) {
      ☃[(☃++)] = ((GameProfile)☃.f()).getName();
    }
    return ☃;
  }
  
  protected String b(GameProfile ☃)
  {
    return ☃.getId().toString();
  }
  
  public GameProfile a(String ☃)
  {
    for (mu ☃ : e().values()) {
      if (☃.equalsIgnoreCase(((GameProfile)☃.f()).getName())) {
        return (GameProfile)☃.f();
      }
    }
    return null;
  }
}
