import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.ProfileLookupCallback;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ml
{
  private static final Logger e = ;
  public static final File a = new File("banned-ips.txt");
  public static final File b = new File("banned-players.txt");
  public static final File c = new File("ops.txt");
  public static final File d = new File("white-list.txt");
  
  private static void a(MinecraftServer ☃, Collection<String> ☃, ProfileLookupCallback ☃)
  {
    String[] ☃ = (String[])Iterators.toArray(Iterators.filter(☃.iterator(), new Predicate()
    {
      public boolean a(String ☃)
      {
        return !os.b(☃);
      }
    }), String.class);
    if (☃.ab()) {
      ☃.az().findProfilesByNames(☃, Agent.MINECRAFT, ☃);
    } else {
      for (String ☃ : ☃)
      {
        UUID ☃ = zj.a(new GameProfile(null, ☃));
        GameProfile ☃ = new GameProfile(☃, ☃);
        ☃.onProfileLookupSucceeded(☃);
      }
    }
  }
  
  public static String a(MinecraftServer ☃, String ☃)
  {
    if ((os.b(☃)) || (☃.length() > 16)) {
      return ☃;
    }
    GameProfile ☃ = ☃.aA().a(☃);
    if ((☃ != null) && (☃.getId() != null)) {
      return ☃.getId().toString();
    }
    if ((☃.R()) || (!☃.ab())) {
      return zj.a(new GameProfile(null, ☃)).toString();
    }
    final List<GameProfile> ☃ = Lists.newArrayList();
    ProfileLookupCallback ☃ = new ProfileLookupCallback()
    {
      public void onProfileLookupSucceeded(GameProfile ☃)
      {
        this.a.aA().a(☃);
        ☃.add(☃);
      }
      
      public void onProfileLookupFailed(GameProfile ☃, Exception ☃)
      {
        ml.a().warn("Could not lookup user whitelist entry for " + ☃.getName(), ☃);
      }
    };
    a(☃, Lists.newArrayList(new String[] { ☃ }), ☃);
    if ((!☃.isEmpty()) && (((GameProfile)☃.get(0)).getId() != null)) {
      return ((GameProfile)☃.get(0)).getId().toString();
    }
    return "";
  }
}
