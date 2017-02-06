import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.exceptions.InvalidCredentialsException;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.math.BigInteger;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import net.minecraft.realms.DisconnectedRealmsScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bkr
  implements jm
{
  private static final Logger a = ;
  private final bcf b;
  private final bfb c;
  private final ek d;
  private GameProfile e;
  
  public bkr(ek ☃, bcf ☃, bfb ☃)
  {
    this.d = ☃;
    this.b = ☃;
    this.c = ☃;
  }
  
  public void a(jo ☃)
  {
    final SecretKey ☃ = ob.a();
    String ☃ = ☃.a();
    PublicKey ☃ = ☃.b();
    String ☃ = new BigInteger(ob.a(☃, ☃, ☃)).toString(16);
    if ((this.b.C() != null) && (this.b.C().d())) {
      try
      {
        b().joinServer(this.b.K().e(), this.b.K().d(), ☃);
      }
      catch (AuthenticationException ☃)
      {
        a.warn("Couldn't connect to auth servers but will continue to join LAN");
      }
    } else {
      try
      {
        b().joinServer(this.b.K().e(), this.b.K().d(), ☃);
      }
      catch (AuthenticationUnavailableException ☃)
      {
        this.d.a(new fb("disconnect.loginFailedInfo", new Object[] { new fb("disconnect.loginFailedInfo.serversUnavailable", new Object[0]) }));
        return;
      }
      catch (InvalidCredentialsException ☃)
      {
        this.d.a(new fb("disconnect.loginFailedInfo", new Object[] { new fb("disconnect.loginFailedInfo.invalidSession", new Object[0]) }));
        return;
      }
      catch (AuthenticationException ☃)
      {
        this.d.a(new fb("disconnect.loginFailedInfo", new Object[] { ☃.getMessage() }));
        return;
      }
    }
    this.d.a(new jt(☃, ☃, ☃.c()), new GenericFutureListener()
    {
      public void operationComplete(Future<? super Void> ☃)
        throws Exception
      {
        bkr.a(bkr.this).a(☃);
      }
    }, new GenericFutureListener[0]);
  }
  
  private MinecraftSessionService b()
  {
    return this.b.X();
  }
  
  public void a(jn ☃)
  {
    this.e = ☃.a();
    this.d.a(el.b);
    this.d.a(new bks(this.b, this.c, this.d, this.e));
  }
  
  public void a(eu ☃)
  {
    if ((this.c != null) && ((this.c instanceof bdz))) {
      this.b.a(new DisconnectedRealmsScreen(((bdz)this.c).a(), "connect.failed", ☃).getProxy());
    } else {
      this.b.a(new bep(this.c, "connect.failed", ☃));
    }
  }
  
  public void a(jq ☃)
  {
    this.d.a(☃.a());
  }
  
  public void a(jp ☃)
  {
    if (!this.d.c()) {
      this.d.a(☃.a());
    }
  }
}
