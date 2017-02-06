import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;
import java.util.Map;
import java.util.UUID;

public class mn
  extends mq<GameProfile, mo>
{
  public mn(File ☃)
  {
    super(☃);
  }
  
  protected mp<GameProfile> a(JsonObject ☃)
  {
    return new mo(☃);
  }
  
  public String[] a()
  {
    String[] ☃ = new String[e().size()];
    int ☃ = 0;
    for (mo ☃ : e().values()) {
      ☃[(☃++)] = ((GameProfile)☃.f()).getName();
    }
    return ☃;
  }
  
  public int a(GameProfile ☃)
  {
    mo ☃ = (mo)b(☃);
    if (☃ != null) {
      return ☃.a();
    }
    return 0;
  }
  
  public boolean b(GameProfile ☃)
  {
    mo ☃ = (mo)b(☃);
    if (☃ != null) {
      return ☃.b();
    }
    return false;
  }
  
  protected String c(GameProfile ☃)
  {
    return ☃.getId().toString();
  }
  
  public GameProfile a(String ☃)
  {
    for (mo ☃ : e().values()) {
      if (☃.equalsIgnoreCase(((GameProfile)☃.f()).getName())) {
        return (GameProfile)☃.f();
      }
    }
    return null;
  }
}
