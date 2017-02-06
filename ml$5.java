import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;
import java.util.List;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.Logger;

final class ml$5
  implements ProfileLookupCallback
{
  ml$5(MinecraftServer paramMinecraftServer, List paramList) {}
  
  public void onProfileLookupSucceeded(GameProfile ☃)
  {
    this.a.aA().a(☃);
    this.b.add(☃);
  }
  
  public void onProfileLookupFailed(GameProfile ☃, Exception ☃)
  {
    ml.a().warn("Could not lookup user whitelist entry for " + ☃.getName(), ☃);
  }
}
