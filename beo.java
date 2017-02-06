import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.labymod.Timings;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import de.zockermaus.serverpinger.ServerData;
import de.zockermaus.serverpinger.ServerPinger;
import java.io.IOException;
import java.util.List;
import org.lwjgl.input.Keyboard;

public class beo
  extends bfb
{
  private final bfb a;
  private final bkx f;
  private bdd g;
  ServerPinger pinger;
  int online = 0;
  int max = 0;
  long update = 0L;
  
  public beo(bfb p_i1031_1_, bkx p_i1031_2_)
  {
    this.a = p_i1031_1_;
    this.f = p_i1031_2_;
  }
  
  public void e()
  {
    this.g.a();
    if ((ConfigManager.settings.directConnectInfo) && (!this.g.b().replace(" ", "").isEmpty()) && 
      (this.update + 5000L < System.currentTimeMillis()))
    {
      this.update = System.currentTimeMillis();
      try
      {
        boolean setNull = false;
        bkw serveraddress = bkw.a(this.g.b());
        if ((this.pinger != null) && 
          (this.pinger.getCurrentData() != null) && 
          (this.pinger.getCurrentData().maxPlayers == 0) && 
          (this.pinger.getCurrentData().players == 0) && 
          (this.pinger.getCurrentData().serverName.equals(serveraddress.a()))) {
          setNull = true;
        }
        ServerPinger server = new ServerPinger(serveraddress.a(), serveraddress.b());
        server.start();
        if (setNull)
        {
          this.pinger = null;
          this.max = 0;
          this.online = 0;
        }
        else
        {
          this.pinger = server;
        }
      }
      catch (Exception error)
      {
        this.pinger = null;
        error.printStackTrace();
      }
    }
  }
  
  public void b()
  {
    Keyboard.enableRepeatEvents(true);
    this.n.clear();
    this.n.add(new bcz(0, this.l / 2 - 100, this.m / 4 + 96 + 12, bwo.a("selectServer.select", new Object[0])));
    this.n.add(new bcz(1, this.l / 2 - 100, this.m / 4 + 120 + 12, bwo.a("gui.cancel", new Object[0])));
    this.g = new bdd(2, this.q, this.l / 2 - 100, 116, 200, 20);
    this.g.f(128);
    this.g.b(true);
    this.g.a(this.j.u.at);
    ((bcz)this.n.get(0)).l = ((!this.g.b().isEmpty()) && (this.g.b().split(":").length > 0));
  }
  
  public void m()
  {
    Keyboard.enableRepeatEvents(false);
    this.j.u.at = this.g.b();
    this.j.u.b();
  }
  
  protected void a(bcz button)
    throws IOException
  {
    if (button.l) {
      if (button.k == 1)
      {
        this.a.a(false, 0);
      }
      else if (button.k == 0)
      {
        this.f.b = this.g.b();
        this.a.a(true, 0);
      }
    }
  }
  
  protected void a(char typedChar, int keyCode)
    throws IOException
  {
    if (this.g.a(typedChar, keyCode))
    {
      ((bcz)this.n.get(0)).l = ((!this.g.b().isEmpty()) && (this.g.b().split(":").length > 0));
      this.j.u.at = this.g.b();
    }
    else if ((keyCode == 28) || (keyCode == 156))
    {
      a((bcz)this.n.get(0));
    }
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    super.a(mouseX, mouseY, mouseButton);
    this.g.a(mouseX, mouseY, mouseButton);
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    c();
    a(this.q, bwo.a("selectServer.direct", new Object[0]), this.l / 2, 20, 16777215);
    c(this.q, bwo.a("addServer.enterIp", new Object[0]), this.l / 2 - 100, 100, 10526880);
    this.g.g();
    if (ConfigManager.settings.directConnectInfo)
    {
      Timings.start("Serverlist pinger");
      if ((this.pinger != null) && (this.pinger.getCurrentData() != null))
      {
        int s = this.pinger.getCurrentData().players;
        if (s > this.online)
        {
          if ((s - this.online > 500) && (s != 0)) {
            this.online = s;
          }
          this.online += 1;
        }
        if (s < this.online)
        {
          if ((s - this.online < 500) && (s != 0)) {
            this.online = s;
          }
          this.online -= 1;
        }
        if ((this.pinger.getCurrentData().maxPlayers != 0) && (s == 0)) {
          this.online = s;
        }
        s = this.pinger.getCurrentData().maxPlayers;
        if (s != 0) {
          this.max = s;
        }
        boolean refresh = (this.pinger.getCurrentData().maxPlayers == 0) && (this.pinger.getCurrentData().players == 0);
        if (refresh) {
          LabyMod.getInstance().draw.drawString(Color.c(1) + "Players: " + Color.cl("c") + this.online + "/" + this.max, this.l / 2 - 100, 140.0D);
        } else {
          LabyMod.getInstance().draw.drawString(Color.c(1) + "Players: " + Color.cl("7") + this.online + "/" + this.max, this.l / 2 - 100, 140.0D);
        }
      }
      else
      {
        LabyMod.getInstance().draw.drawString(Color.cl("c") + "Pinging..", this.l / 2 - 100, 140.0D);
      }
      Timings.stop("Serverlist pinger");
    }
    super.a(mouseX, mouseY, partialTicks);
  }
}
