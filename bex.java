import de.labystudio.gui.GuiGommeHDSearch;
import de.labystudio.gui.GuiMenuScreen;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.listener.GommeHD;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import de.zockermaus.serverpinger.ServerData;
import de.zockermaus.serverpinger.ServerPinger;
import java.io.IOException;
import java.util.List;
import net.minecraft.realms.RealmsBridge;

public class bex
  extends GuiMenuScreen
{
  private int a;
  private int f;
  int confirmDisconnect = 0;
  bcz returnToMenu;
  ServerPinger pinger;
  
  public bex()
  {
    super(null);
    this.childScreen = this;
    this.id = "Menu";
  }
  
  public void b()
  {
    this.a = 0;
    this.n.clear();
    int i = -16;
    int j = 98;
    this.n.add(this.returnToMenu = new bcz(1, this.l / 2 - 100, this.m / 4 + 120 + i, bwo.a("menu.returnToMenu", new Object[0])));
    if (!this.j.D()) {
      ((bcz)this.n.get(0)).j = bwo.a("menu.disconnect", new Object[0]);
    }
    this.n.add(new bcz(4, this.l / 2 - 100, this.m / 4 + 24 + i, bwo.a("menu.returnToGame", new Object[0])));
    this.n.add(new bcz(0, this.l / 2 - 100, this.m / 4 + 96 + i, 98, 20, bwo.a("menu.options", new Object[0])));
    bcz guibutton;
    this.n.add(guibutton = new bcz(7, this.l / 2 + 2, this.m / 4 + 96 + i, 98, 20, bwo.a("menu.shareToLan", new Object[0])));
    this.n.add(new bcz(5, this.l / 2 - 100, this.m / 4 + 48 + i, 98, 20, bwo.a("gui.achievements", new Object[0])));
    this.n.add(new bcz(6, this.l / 2 + 2, this.m / 4 + 48 + i, 98, 20, bwo.a("gui.stats", new Object[0])));
    guibutton.l = ((this.j.E()) && (!this.j.F().a()));
    if (GommeHD.isGommeHD()) {
      this.n.add(new bcz(8, this.l - 53, this.m - 23, 50, 20, "Search"));
    }
    String t = Color.cl("a") + "Gui enabled";
    if (ConfigManager.settings.hideMod) {
      t = Color.cl("c") + "Gui disabled";
    }
    this.n.add(new bcz(9, this.l - 70, 4, 67, 20, t));
    super.b();
    
    ServerPinger server = new ServerPinger(LabyMod.getInstance().ip, LabyMod.getInstance().port);
    server.start();
    this.pinger = server;
  }
  
  protected void a(bcz button)
    throws IOException
  {
    switch (button.k)
    {
    case 0: 
      this.j.a(new bev(this, this.j.u));
      break;
    case 1: 
      if ((ConfigManager.settings.confirmDisconnect) && (!bcf.z().E()) && 
        (this.confirmDisconnect < 1))
      {
        this.confirmDisconnect += 1;
        this.returnToMenu.j = (Color.cl("c") + "Press again to confirm diconnect");
        return;
      }
      button.l = false;
      boolean flag = this.j.D();
      boolean flag1 = this.j.ai();
      button.l = false;
      this.j.f.M();
      this.j.a((bku)null);
      if (flag)
      {
        this.j.a(new bfi());
      }
      else if (flag1)
      {
        RealmsBridge realmsbridge = new RealmsBridge();
        realmsbridge.switchToRealms(new bfi());
      }
      else
      {
        this.j.a(new bgr(new bfi()));
      }
    case 2: 
    case 3: 
    default: 
      break;
    case 4: 
      this.j.a((bfb)null);
      this.j.o();
      break;
    case 5: 
      this.j.a(new bfm(this, this.j.h.G()));
      break;
    case 6: 
      this.j.a(new bfn(this, this.j.h.G()));
      break;
    case 7: 
      this.j.a(new bfc(this));
      break;
    case 8: 
      this.j.a(new GuiGommeHDSearch());
      break;
    }
    ConfigManager.settings.hideMod = (!ConfigManager.settings.hideMod);
    b();
    
    super.actionPermformed(button);
  }
  
  public void drawServerInfo()
  {
    if (!ConfigManager.settings.infoInMenu) {
      return;
    }
    if (LabyMod.getInstance().ip.isEmpty()) {
      return;
    }
    String ip = LabyMod.getInstance().ip;
    if ((this.pinger != null) && (this.pinger.getCurrentData() != null) && (this.pinger.getCurrentData().motd != null)) {
      try
      {
        int i = LabyMod.getInstance().draw.getHeight() - 32;
        String st = Color.c + "c" + LabyMod.getInstance().ip.toUpperCase() + " " + Color.c + "5Players: " + Color.c + "f" + this.pinger.getCurrentData().players + "/" + this.pinger.getCurrentData().maxPlayers + " " + Color.c + "5Ping:" + Color.c + "f " + Color.c + "a" + this.pinger.getCurrentData().ms + "ms";
        DrawUtils.a(1, i - 2, LabyMod.getInstance().draw.getStringWidth(st) + 5, this.m - 1, Integer.MIN_VALUE);
        LabyMod.getInstance().draw.drawString(st, 3.0D, i);
        i += 10;
        if (this.pinger.getCurrentData().motd.contains("\n"))
        {
          String[] s = this.pinger.getCurrentData().motd.split("\n");
          LabyMod.getInstance().draw.drawString(s[0], 3.0D, i);
          i += 10;
          LabyMod.getInstance().draw.drawString(s[1], 3.0D, i);
        }
        else
        {
          int l = 45;
          if (this.pinger.getCurrentData().motd.length() > l)
          {
            LabyMod.getInstance().draw.drawString(this.pinger.getCurrentData().motd.substring(0, l), 3.0D, i);
            i += 10;
            LabyMod.getInstance().draw.drawString(this.pinger.getCurrentData().motd.substring(l, this.pinger.getCurrentData().motd.length()), 3.0D, i);
          }
          else
          {
            LabyMod.getInstance().draw.drawString(this.pinger.getCurrentData().motd, 3.0D, i);
          }
        }
        LabyMod.getInstance().playerPing = this.pinger.getCurrentData().ms;
      }
      catch (Exception error)
      {
        error.printStackTrace();
      }
    } else if (!this.j.E()) {
      LabyMod.getInstance().draw.drawString(Color.cl("c") + "Loading server information..", 2.0D, LabyMod.getInstance().draw.getHeight() - 10);
    }
  }
  
  public void e()
  {
    super.e();
    this.f += 1;
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    c();
    a(this.q, bwo.a("menu.game", new Object[0]), this.l / 2, 40, 16777215);
    drawServerInfo();
    super.a(mouseX, mouseY, partialTicks);
  }
}
