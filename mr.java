import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;
import java.util.Map;
import java.util.UUID;

public class mr
  extends mq<GameProfile, ms>
{
  public mr(File ☃)
  {
    super(☃);
  }
  
  protected mp<GameProfile> a(JsonObject ☃)
  {
    return new ms(☃);
  }
  
  public boolean a(GameProfile ☃)
  {
    return d(☃);
  }
  
  public String[] a()
  {
    String[] ☃ = new String[e().size()];
    int ☃ = 0;
    for (ms ☃ : e().values()) {
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
    for (ms ☃ : e().values()) {
      if (☃.equalsIgnoreCase(((GameProfile)☃.f()).getName())) {
        return (GameProfile)☃.f();
      }
    }
    return null;
  }
}
