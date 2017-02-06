import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import java.math.BigInteger;
import java.security.KeyPair;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.Logger;

class md$2
  extends Thread
{
  md$2(md parammd, String ☃)
  {
    super(☃);
  }
  
  public void run()
  {
    GameProfile ☃ = md.b(this.a);
    try
    {
      String ☃ = new BigInteger(ob.a(md.c(this.a), md.a(this.a).O().getPublic(), md.d(this.a))).toString(16);
      md.a(this.a, md.a(this.a).ay().hasJoinedServer(new GameProfile(null, ☃.getName()), ☃));
      if (md.b(this.a) != null)
      {
        md.e().info("UUID of player " + md.b(this.a).getName() + " is " + md.b(this.a).getId());
        md.a(this.a, md.a.d);
      }
      else if (md.a(this.a).R())
      {
        md.e().warn("Failed to verify username but will let them in anyway!");
        md.a(this.a, this.a.a(☃));
        md.a(this.a, md.a.d);
      }
      else
      {
        this.a.a("Failed to verify username!");
        md.e().error("Username '" + md.b(this.a).getName() + "' tried to join with an invalid session");
      }
    }
    catch (AuthenticationUnavailableException ☃)
    {
      if (md.a(this.a).R())
      {
        md.e().warn("Authentication servers are down but will let them in anyway!");
        md.a(this.a, this.a.a(☃));
        md.a(this.a, md.a.d);
      }
      else
      {
        this.a.a("Authentication servers are down. Please try again later, sorry!");
        md.e().error("Couldn't verify username because servers are unavailable");
      }
    }
  }
}
