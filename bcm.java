import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.util.UUIDTypeAdapter;
import java.util.Map;
import java.util.UUID;

public class bcm
{
  private final String a;
  private final String b;
  private final String c;
  private final bcm.a d;
  
  public bcm(String usernameIn, String playerIDIn, String tokenIn, String sessionTypeIn)
  {
    this.a = usernameIn;
    this.b = playerIDIn;
    this.c = tokenIn;
    this.d = bcm.a.a(sessionTypeIn);
  }
  
  public String a()
  {
    return "token:" + this.c + ":" + this.b;
  }
  
  public String b()
  {
    return this.b;
  }
  
  public String c()
  {
    return this.a;
  }
  
  public String d()
  {
    return this.c;
  }
  
  public GameProfile e()
  {
    try
    {
      UUID uuid = UUIDTypeAdapter.fromString(b());
      return new GameProfile(uuid, c());
    }
    catch (IllegalArgumentException var2) {}
    return new GameProfile((UUID)null, c());
  }
  
  public static enum a
  {
    private static final Map<String, a> c;
    private final String d;
    
    private a(String sessionTypeIn)
    {
      this.d = sessionTypeIn;
    }
    
    public static a a(String sessionTypeIn)
    {
      return (a)c.get(sessionTypeIn.toLowerCase());
    }
    
    static
    {
      c = Maps.newHashMap();
      for (a session$type : values()) {
        c.put(session$type.d, session$type);
      }
    }
  }
}
