import de.labystudio.labymod.LabyMod;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bei
  extends bfb
{
  private static final AtomicInteger a = new AtomicInteger(0);
  private static final Logger f = LogManager.getLogger();
  private ek g;
  private boolean h;
  private final bfb i;
  
  public bei(bfb p_i1181_1_, bcf mcIn, bkx p_i1181_3_)
  {
    this.j = mcIn;
    this.i = p_i1181_1_;
    bkw serveraddress = bkw.a(p_i1181_3_.b);
    mcIn.a((bku)null);
    mcIn.a(p_i1181_3_);
    LabyMod.getInstance().commandQueue.clear();
    if (!p_i1181_3_.serverCommand.isEmpty()) {
      LabyMod.getInstance().commandQueue.add(p_i1181_3_.serverCommand);
    }
    a(serveraddress.a(), serveraddress.b());
  }
  
  public bei(bfb p_i1182_1_, bcf mcIn, String hostName, int port)
  {
    this.j = mcIn;
    this.i = p_i1182_1_;
    mcIn.a((bku)null);
    a(hostName, port);
  }
  
  private void a(final String ip, final int port)
  {
    LabyMod.getInstance().updateServerIP(ip, port);
    f.info("Connecting to " + ip + ", " + port);
    new Thread("Server Connector #" + a.incrementAndGet())
    {
      public void run()
      {
        InetAddress inetaddress = null;
        try
        {
          if (bei.a(bei.this)) {
            return;
          }
          inetaddress = InetAddress.getByName(ip);
          bei.a(bei.this, ek.a(inetaddress, port, bei.this.j.u.f()));
          bei.b(bei.this).a(new bkr(bei.b(bei.this), bei.this.j, bei.c(bei.this)));
          bei.b(bei.this).a(new jj(107, ip, port, el.d));
          bei.b(bei.this).a(new js(bei.this.j.K().e()));
        }
        catch (UnknownHostException unknownhostexception)
        {
          if (bei.a(bei.this)) {
            return;
          }
          bei.a().error("Couldn't connect to server", unknownhostexception);
          bei.this.j.a(new bep(bei.c(bei.this), "connect.failed", new fb("disconnect.genericReason", new Object[] { "Unknown host" })));
        }
        catch (Exception exception)
        {
          if (bei.a(bei.this)) {
            return;
          }
          bei.a().error("Couldn't connect to server", exception);
          String s = exception.toString();
          if (inetaddress != null)
          {
            String s1 = inetaddress.toString() + ":" + port;
            s = s.replaceAll(s1, "");
          }
          bei.this.j.a(new bep(bei.c(bei.this), "connect.failed", new fb("disconnect.genericReason", new Object[] { s })));
        }
      }
    }.start();
  }
  
  public void e()
  {
    if (this.g != null) {
      if (this.g.g()) {
        this.g.a();
      } else {
        this.g.l();
      }
    }
  }
  
  protected void a(char typedChar, int keyCode)
    throws IOException
  {}
  
  public void b()
  {
    this.n.clear();
    this.n.add(new bcz(0, this.l / 2 - 100, this.m / 4 + 120 + 12, bwo.a("gui.cancel", new Object[0])));
  }
  
  protected void a(bcz button)
    throws IOException
  {
    if (button.k == 0)
    {
      this.h = true;
      if (this.g != null) {
        this.g.a(new fa("Aborted"));
      }
      this.j.a(this.i);
    }
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    c();
    if (this.g == null) {
      a(this.q, bwo.a("connect.connecting", new Object[0]), this.l / 2, this.m / 2 - 50, 16777215);
    } else {
      a(this.q, bwo.a("connect.authorizing", new Object[0]), this.l / 2, this.m / 2 - 50, 16777215);
    }
    super.a(mouseX, mouseY, partialTicks);
  }
}
