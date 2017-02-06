import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.concurrent.GenericFutureListener;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.crypto.SecretKey;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class md
  implements jr, ky
{
  private static final AtomicInteger b = new AtomicInteger(0);
  private static final Logger c = LogManager.getLogger();
  private static final Random d = new Random();
  private final byte[] e = new byte[4];
  private final MinecraftServer f;
  public final ek a;
  private md.a g = md.a.a;
  private int h;
  private GameProfile i;
  private String j = "";
  private SecretKey k;
  private lr l;
  
  public md(MinecraftServer ☃, ek ☃)
  {
    this.f = ☃;
    this.a = ☃;
    d.nextBytes(this.e);
  }
  
  public void c()
  {
    if (this.g == md.a.d)
    {
      b();
    }
    else if (this.g == md.a.e)
    {
      lr ☃ = this.f.al().a(this.i.getId());
      if (☃ == null)
      {
        this.g = md.a.d;
        this.f.al().a(this.a, this.l);
        this.l = null;
      }
    }
    if (this.h++ == 600) {
      a("Took too long to log in");
    }
  }
  
  public void a(String ☃)
  {
    try
    {
      c.info("Disconnecting " + d() + ": " + ☃);
      fa ☃ = new fa(☃);
      this.a.a(new jq(☃));
      this.a.a(☃);
    }
    catch (Exception ☃)
    {
      c.error("Error whilst disconnecting player", ☃);
    }
  }
  
  public void b()
  {
    if (!this.i.isComplete()) {
      this.i = a(this.i);
    }
    String ☃ = this.f.al().a(this.a.b(), this.i);
    if (☃ != null)
    {
      a(☃);
    }
    else
    {
      this.g = md.a.f;
      if ((this.f.aF() >= 0) && (!this.a.c())) {
        this.a.a(new jp(this.f.aF()), new ChannelFutureListener()
        {
          public void a(ChannelFuture ☃)
            throws Exception
          {
            md.this.a.a(md.a(md.this).aF());
          }
        }, new GenericFutureListener[0]);
      }
      this.a.a(new jn(this.i));
      lr ☃ = this.f.al().a(this.i.getId());
      if (☃ != null)
      {
        this.g = md.a.e;
        this.l = this.f.al().g(this.i);
      }
      else
      {
        this.f.al().a(this.a, this.f.al().g(this.i));
      }
    }
  }
  
  public void a(eu ☃)
  {
    c.info(d() + " lost connection: " + ☃.c());
  }
  
  public String d()
  {
    if (this.i != null) {
      return this.i.toString() + " (" + this.a.b().toString() + ")";
    }
    return String.valueOf(this.a.b());
  }
  
  public void a(js ☃)
  {
    Validate.validState(this.g == md.a.a, "Unexpected hello packet", new Object[0]);
    this.i = ☃.a();
    if ((this.f.ab()) && (!this.a.c()))
    {
      this.g = md.a.b;
      this.a.a(new jo(this.j, this.f.O().getPublic(), this.e));
    }
    else
    {
      this.g = md.a.d;
    }
  }
  
  public void a(jt ☃)
  {
    Validate.validState(this.g == md.a.b, "Unexpected key packet", new Object[0]);
    PrivateKey ☃ = this.f.O().getPrivate();
    if (!Arrays.equals(this.e, ☃.b(☃))) {
      throw new IllegalStateException("Invalid nonce!");
    }
    this.k = ☃.a(☃);
    this.g = md.a.c;
    
    this.a.a(this.k);
    
    new Thread("User Authenticator #" + b.incrementAndGet())
    {
      public void run()
      {
        GameProfile ☃ = md.b(md.this);
        try
        {
          String ☃ = new BigInteger(ob.a(md.c(md.this), md.a(md.this).O().getPublic(), md.d(md.this))).toString(16);
          md.a(md.this, md.a(md.this).ay().hasJoinedServer(new GameProfile(null, ☃.getName()), ☃));
          if (md.b(md.this) != null)
          {
            md.e().info("UUID of player " + md.b(md.this).getName() + " is " + md.b(md.this).getId());
            md.a(md.this, md.a.d);
          }
          else if (md.a(md.this).R())
          {
            md.e().warn("Failed to verify username but will let them in anyway!");
            md.a(md.this, md.this.a(☃));
            md.a(md.this, md.a.d);
          }
          else
          {
            md.this.a("Failed to verify username!");
            md.e().error("Username '" + md.b(md.this).getName() + "' tried to join with an invalid session");
          }
        }
        catch (AuthenticationUnavailableException ☃)
        {
          if (md.a(md.this).R())
          {
            md.e().warn("Authentication servers are down but will let them in anyway!");
            md.a(md.this, md.this.a(☃));
            md.a(md.this, md.a.d);
          }
          else
          {
            md.this.a("Authentication servers are down. Please try again later, sorry!");
            md.e().error("Couldn't verify username because servers are unavailable");
          }
        }
      }
    }.start();
  }
  
  protected GameProfile a(GameProfile ☃)
  {
    UUID ☃ = UUID.nameUUIDFromBytes(("OfflinePlayer:" + ☃.getName()).getBytes(Charsets.UTF_8));
    return new GameProfile(☃, ☃.getName());
  }
  
  static enum a
  {
    private a() {}
  }
}
