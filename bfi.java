import com.google.common.collect.Lists;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.labymod.Source;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import de.labystudio.utils.ServerBroadcast;
import de.labystudio.utils.TextureManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.realms.RealmsBridge;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;

public class bfi
  extends bfb
  implements beg
{
  private static final AtomicInteger f = new AtomicInteger(0);
  private static final Logger g = LogManager.getLogger();
  private static final Random h = new Random();
  private float i;
  private String r;
  private bcz s;
  private int t;
  private bux u;
  private boolean v = true;
  private final Object w = new Object();
  private String x;
  private String y;
  private String z;
  private static final kk A = new kk("texts/splashes.txt");
  private static final kk B = new kk("textures/gui/title/minecraft.png");
  private static final kk[] C = { new kk("textures/gui/title/background/panorama_0.png"), new kk("textures/gui/title/background/panorama_1.png"), new kk("textures/gui/title/background/panorama_2.png"), new kk("textures/gui/title/background/panorama_3.png"), new kk("textures/gui/title/background/panorama_4.png"), new kk("textures/gui/title/background/panorama_5.png") };
  public static final String a = "Please click " + a.t + "here" + a.v + " for more information.";
  private int D;
  private int E;
  private int F;
  private int G;
  private int H;
  private int I;
  private kk J;
  private bcz K;
  private boolean L;
  private bfb M;
  private boolean labyModMessage = false;
  
  public bfi()
  {
    this.y = a;
    this.L = false;
    this.r = "missingno";
    bwf iresource = null;
    this.labyModMessage = false;
    try
    {
      List<String> list = Lists.newArrayList();
      iresource = bcf.z().O().a(A);
      BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(iresource.b(), Charsets.UTF_8));
      String s;
      while ((s = bufferedreader.readLine()) != null)
      {
        s = s.trim();
        if (!s.isEmpty()) {
          list.add(s);
        }
      }
      if (!list.isEmpty()) {
        for (;;)
        {
          this.r = ((String)list.get(h.nextInt(list.size())));
          if (this.r.hashCode() != 125780783) {
            break;
          }
        }
      }
    }
    catch (IOException localIOException) {}finally
    {
      IOUtils.closeQuietly(iresource);
    }
    this.i = h.nextFloat();
    this.x = "";
    if ((!GLContext.getCapabilities().OpenGL20) && (!bzg.b()))
    {
      this.x = bwo.a("title.oldgl1", new Object[0]);
      this.y = bwo.a("title.oldgl2", new Object[0]);
      this.z = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
    }
    if (!Source.mod_VersionType.isEmpty())
    {
      this.x = ("You are using a " + Color.clc("e") + Source.mod_VersionType + Color.clc("f") + " version of the LabyMod!");
      this.y = "This is an unstable Version and bugs may occur.";
      this.z = "";
    }
    setUpdate();
  }
  
  public void setUpdate()
  {
    if ((LabyMod.getInstance().getServerBroadcast() != null) && (LabyMod.getInstance().getServerBroadcast().getLine1() != ""))
    {
      this.x = LabyMod.getInstance().getServerBroadcast().getLine1();
      this.y = LabyMod.getInstance().getServerBroadcast().getLine2();
      this.z = LabyMod.getInstance().getServerBroadcast().getUrl();
      this.labyModMessage = true;
    }
    if (LabyMod.getInstance().chatPacketUpdate)
    {
      this.x = ("A new LabyMod Version " + LabyMod.getInstance().latestVersionName + " is available!");
      this.y = ("Click " + Color.clc("n") + "here" + Color.clc("f") + " to download the latest version.");
      this.z = Source.url_Update;
      this.labyModMessage = true;
    }
  }
  
  private boolean a()
  {
    return (bcf.z().u.b(bch.a.K)) && (this.M != null);
  }
  
  public void e()
  {
    this.t += 1;
    if (a()) {
      this.M.e();
    }
  }
  
  public boolean d()
  {
    return false;
  }
  
  protected void a(char typedChar, int keyCode)
    throws IOException
  {}
  
  public void b()
  {
    this.u = new bux(256, 256);
    this.J = this.j.N().a("background", this.u);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    if ((calendar.get(2) + 1 == 12) && (calendar.get(5) == 24)) {
      this.r = "Merry X-mas!";
    } else if ((calendar.get(2) + 1 == 1) && (calendar.get(5) == 1)) {
      this.r = "Happy new year!";
    } else if ((calendar.get(2) + 1 == 10) && (calendar.get(5) == 31)) {
      this.r = "OOoooOOOoooo! Spooky!";
    } else if ((calendar.get(2) + 1 == 11) && (calendar.get(5) == 24)) {
      this.r = "Happy birthday, LabyStudio!";
    } else if ((calendar.get(2) + 1 == 6) && (calendar.get(5) == 23)) {
      this.r = "Happy birthday, Zockermaus!";
    } else if ((calendar.get(2) + 1 == 4) && (calendar.get(5) == 25)) {
      this.r = "Happy birthday, qlow!";
    } else if ((calendar.get(2) + 1 == 10) && (calendar.get(5) == 21)) {
      this.r = "Back to the Future!";
    } else if ((calendar.get(2) + 1 == 4) && (calendar.get(5) == 13)) {
      this.r = "Happy birthday, LabyMod!";
    }
    int i = 24;
    int j = this.m / 4 + 48;
    if (this.j.u()) {
      c(j, 24);
    } else {
      b(j, 24);
    }
    this.n.add(new bcz(0, this.l / 2 - 100, j + 72 + 12, 98, 20, bwo.a("menu.options", new Object[0])));
    this.n.add(new bcz(4, this.l / 2 + 2, j + 72 + 12, 98, 20, bwo.a("menu.quit", new Object[0])));
    this.n.add(new bdg(5, this.l / 2 - 124, j + 72 + 12));
    synchronized (this.w)
    {
      this.E = this.q.a(this.x);
      this.D = this.q.a(this.y);
      int k = Math.max(this.E, this.D);
      this.F = ((this.l - k) / 2);
      this.G = (((bcz)this.n.get(0)).i - 24);
      this.H = (this.F + k);
      this.I = (this.G + 24);
    }
    this.j.a(false);
    if ((bcf.z().u.b(bch.a.K)) && (!this.L))
    {
      RealmsBridge realmsbridge = new RealmsBridge();
      this.M = realmsbridge.getNotificationScreen(this);
      this.L = true;
    }
    if (a())
    {
      this.M.a(this.l, this.m);
      this.M.b();
    }
    LabyMod.getInstance().resetMod();
    LabyMod.getInstance().resetIP();
  }
  
  private void b(int p_73969_1_, int p_73969_2_)
  {
    this.n.add(new bcz(1, this.l / 2 - 100, p_73969_1_, bwo.a("menu.singleplayer", new Object[0])));
    this.n.add(new bcz(2, this.l / 2 - 100, p_73969_1_ + p_73969_2_ * 1, bwo.a("menu.multiplayer", new Object[0])));
    try
    {
      LabyMod.getInstance();LabyMod.overWrite();
      String quickPlay = this.j.u.at;
      String lastServer = ConfigManager.settings.last_Server;
      String realms = "Minecraft Realms";
      if ((quickPlay != null) && (ConfigManager.settings.quickPlay))
      {
        if (this.j.u.at.isEmpty())
        {
          if ((ConfigManager.settings.last_Server != null) && (!ConfigManager.settings.last_Server.isEmpty()))
          {
            this.n.add(new bcz(14, this.l / 2 - 100, p_73969_1_ + p_73969_2_ * 2, 98, 20, bwo.a(realms, new Object[0])));
            if (lastServer.length() > 16) {
              lastServer = lastServer.substring(0, 16);
            }
            this.n.add(new bcz(7, this.l / 2 + 2, p_73969_1_ + p_73969_2_ * 2, 98, 20, bwo.a(lastServer, new Object[0])));
          }
          else
          {
            this.n.add(new bcz(14, this.l / 2 - 100, p_73969_1_ + p_73969_2_ * 2, bwo.a(realms, new Object[0])));
          }
        }
        else if ((ConfigManager.settings.last_Server != null) && (!ConfigManager.settings.last_Server.isEmpty()))
        {
          if (quickPlay.length() > 16) {
            quickPlay = quickPlay.substring(0, 16);
          }
          this.n.add(new bcz(6, this.l / 2 - 100, p_73969_1_ + p_73969_2_ * 2, 98, 20, bwo.a(quickPlay, new Object[0])));
          if (lastServer.length() > 16) {
            lastServer = lastServer.substring(0, 16);
          }
          this.n.add(new bcz(7, this.l / 2 + 2, p_73969_1_ + p_73969_2_ * 2, 98, 20, bwo.a(lastServer, new Object[0])));
        }
        else
        {
          if (quickPlay.length() > 32) {
            quickPlay = quickPlay.substring(0, 32);
          }
          this.n.add(new bcz(6, this.l / 2 - 100, p_73969_1_ + p_73969_2_ * 2, bwo.a(quickPlay, new Object[0])));
        }
      }
      else if ((ConfigManager.settings.last_Server != null) && (!ConfigManager.settings.last_Server.isEmpty()) && (ConfigManager.settings.quickPlay))
      {
        this.n.add(new bcz(14, this.l / 2 - 100, p_73969_1_ + p_73969_2_ * 2, 98, 20, bwo.a(realms, new Object[0])));
        if (lastServer.length() > 16) {
          lastServer = lastServer.substring(0, 16);
        }
        this.n.add(new bcz(7, this.l / 2 + 2, p_73969_1_ + p_73969_2_ * 2, 98, 20, bwo.a(lastServer, new Object[0])));
      }
      else
      {
        this.n.add(new bcz(14, this.l / 2 - 100, p_73969_1_ + p_73969_2_ * 2, bwo.a(realms, new Object[0])));
      }
    }
    catch (Exception error)
    {
      for (int i = 0; i <= this.n.size() - 1; i++)
      {
        bcz b = (bcz)this.n.get(i);
        b.m = false;
      }
      bcz a = new bcz(-1, this.l / 2 - 100, p_73969_1_ + p_73969_2_ * 2 - 45, Color.clc("4") + "An error occurred while loading the LabyMod!");
      a.noGui = true;
      this.n.add(a);
      this.n.add(new bcz(16, this.l / 2 - 100, p_73969_1_ + p_73969_2_ * 2 - 25, Color.clc("e") + "Option 1: " + Color.clc("c") + "Reload the LabyMod"));
      this.n.add(new bcz(15, this.l / 2 - 100, p_73969_1_ + p_73969_2_ * 2, Color.clc("e") + "Option 2: " + Color.clc("c") + "Delete LabyMod config file"));
      return;
    }
  }
  
  private void c(int p_73972_1_, int p_73972_2_)
  {
    this.n.add(new bcz(11, this.l / 2 - 100, p_73972_1_, bwo.a("menu.playdemo", new Object[0])));
    this.n.add(this.s = new bcz(12, this.l / 2 - 100, p_73972_1_ + p_73972_2_ * 1, bwo.a("menu.resetdemo", new Object[0])));
    azk isaveformat = this.j.g();
    azh worldinfo = isaveformat.c("Demo_World");
    if (worldinfo == null) {
      this.s.l = false;
    }
  }
  
  protected void a(bcz button)
    throws IOException
  {
    if (button.k == 0) {
      this.j.a(new bev(this, this.j.u));
    }
    if (button.k == 5) {
      this.j.a(new bet(this, this.j.u, this.j.Q()));
    }
    if (button.k == 1) {
      this.j.a(new bhm(this));
    }
    if (button.k == 2) {
      this.j.a(new bgr(this));
    }
    if ((button.k == 14) && (this.K != null)) {
      f();
    }
    if (button.k == 4) {
      this.j.n();
    }
    if (button.k == 11) {
      this.j.a("Demo_World", "Demo_World", lj.a);
    }
    if (button.k == 12)
    {
      azk isaveformat = this.j.g();
      azh worldinfo = isaveformat.c("Demo_World");
      if (worldinfo != null) {
        this.j.a(new beh(this, bwo.a("selectWorld.deleteQuestion", new Object[0]), "'" + worldinfo.j() + "' " + bwo.a("selectWorld.deleteWarning", new Object[0]), bwo.a("selectWorld.deleteButton", new Object[0]), bwo.a("gui.cancel", new Object[0]), 12));
      }
    }
  }
  
  private void f()
  {
    RealmsBridge realmsbridge = new RealmsBridge();
    realmsbridge.switchToRealms(this);
  }
  
  public void a(boolean result, int id)
  {
    if ((result) && (id == 12))
    {
      azk isaveformat = this.j.g();
      isaveformat.d();
      isaveformat.e("Demo_World");
      this.j.a(this);
    }
    else if (id == 13)
    {
      if (result) {
        try
        {
          Class<?> oclass = Class.forName("java.awt.Desktop");
          Object object = oclass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
          oclass.getMethod("browse", new Class[] { URI.class }).invoke(object, new Object[] { new URI(this.z) });
        }
        catch (Throwable throwable)
        {
          g.error("Couldn't open link", throwable);
        }
      }
      this.j.a(this);
    }
  }
  
  private void b(int p_73970_1_, int p_73970_2_, float p_73970_3_)
  {
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    bni.n(5889);
    bni.G();
    bni.F();
    Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
    bni.n(5888);
    bni.G();
    bni.F();
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    bni.b(180.0F, 1.0F, 0.0F, 0.0F);
    bni.b(90.0F, 0.0F, 0.0F, 1.0F);
    bni.m();
    bni.d();
    bni.r();
    bni.a(false);
    bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
    int i = 8;
    for (int j = 0; j < i * i; j++)
    {
      bni.G();
      float f = (j % i / i - 0.5F) / 64.0F;
      float f1 = (j / i / i - 0.5F) / 64.0F;
      float f2 = 0.0F;
      bni.c(f, f1, f2);
      bni.b(on.a((this.t + p_73970_3_) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
      bni.b(-(this.t + p_73970_3_) * 0.1F, 0.0F, 1.0F, 0.0F);
      for (int k = 0; k < 6; k++)
      {
        bni.G();
        if (k == 1) {
          bni.b(90.0F, 0.0F, 1.0F, 0.0F);
        }
        if (k == 2) {
          bni.b(180.0F, 0.0F, 1.0F, 0.0F);
        }
        if (k == 3) {
          bni.b(-90.0F, 0.0F, 1.0F, 0.0F);
        }
        if (k == 4) {
          bni.b(90.0F, 1.0F, 0.0F, 0.0F);
        }
        if (k == 5) {
          bni.b(-90.0F, 1.0F, 0.0F, 0.0F);
        }
        this.j.N().a(C[k]);
        vertexbuffer.a(7, bvp.i);
        int l = 255 / (j + 1);
        float f3 = 0.0F;
        vertexbuffer.b(-1.0D, -1.0D, 1.0D).a(0.0D, 0.0D).b(255, 255, 255, l).d();
        vertexbuffer.b(1.0D, -1.0D, 1.0D).a(1.0D, 0.0D).b(255, 255, 255, l).d();
        vertexbuffer.b(1.0D, 1.0D, 1.0D).a(1.0D, 1.0D).b(255, 255, 255, l).d();
        vertexbuffer.b(-1.0D, 1.0D, 1.0D).a(0.0D, 1.0D).b(255, 255, 255, l).d();
        tessellator.b();
        bni.H();
      }
      bni.H();
      bni.a(true, true, true, false);
    }
    vertexbuffer.c(0.0D, 0.0D, 0.0D);
    bni.a(true, true, true, true);
    bni.n(5889);
    bni.H();
    bni.n(5888);
    bni.H();
    bni.a(true);
    bni.q();
    bni.k();
  }
  
  private void a(float p_73968_1_)
  {
    this.j.N().a(this.J);
    bni.b(3553, 10241, 9729);
    bni.b(3553, 10240, 9729);
    bni.a(3553, 0, 0, 0, 0, 0, 256, 256);
    bni.m();
    bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
    bni.a(true, true, true, false);
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    vertexbuffer.a(7, bvp.i);
    bni.d();
    int i = 3;
    for (int j = 0; j < i; j++)
    {
      float f = 1.0F / (j + 1);
      int k = this.l;
      int l = this.m;
      float f1 = (j - i / 2) / 256.0F;
      vertexbuffer.b(k, l, this.e).a(0.0F + f1, 1.0D).a(1.0F, 1.0F, 1.0F, f).d();
      vertexbuffer.b(k, 0.0D, this.e).a(1.0F + f1, 1.0D).a(1.0F, 1.0F, 1.0F, f).d();
      vertexbuffer.b(0.0D, 0.0D, this.e).a(1.0F + f1, 0.0D).a(1.0F, 1.0F, 1.0F, f).d();
      vertexbuffer.b(0.0D, l, this.e).a(0.0F + f1, 0.0D).a(1.0F, 1.0F, 1.0F, f).d();
    }
    tessellator.b();
    bni.e();
    bni.a(true, true, true, true);
  }
  
  private void c(int p_73971_1_, int p_73971_2_, float p_73971_3_)
  {
    this.j.b().e();
    bni.b(0, 0, 256, 256);
    b(p_73971_1_, p_73971_2_, p_73971_3_);
    a(p_73971_3_);
    a(p_73971_3_);
    a(p_73971_3_);
    a(p_73971_3_);
    a(p_73971_3_);
    a(p_73971_3_);
    a(p_73971_3_);
    this.j.b().a(true);
    bni.b(0, 0, this.j.d, this.j.e);
    float f = this.l > this.m ? 120.0F / this.l : 120.0F / this.m;
    float f1 = this.m * f / 256.0F;
    float f2 = this.l * f / 256.0F;
    int i = this.l;
    int j = this.m;
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    vertexbuffer.a(7, bvp.i);
    vertexbuffer.b(0.0D, j, this.e).a(0.5F - f1, 0.5F + f2).a(1.0F, 1.0F, 1.0F, 1.0F).d();
    vertexbuffer.b(i, j, this.e).a(0.5F - f1, 0.5F - f2).a(1.0F, 1.0F, 1.0F, 1.0F).d();
    vertexbuffer.b(i, 0.0D, this.e).a(0.5F + f1, 0.5F - f2).a(1.0F, 1.0F, 1.0F, 1.0F).d();
    vertexbuffer.b(0.0D, 0.0D, this.e).a(0.5F + f1, 0.5F + f2).a(1.0F, 1.0F, 1.0F, 1.0F).d();
    tessellator.b();
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    if ((!this.labyModMessage) && (
      (LabyMod.getInstance().chatPacketUpdate) || (LabyMod.getInstance().getServerBroadcast() != null)))
    {
      setUpdate();
      b();
    }
    bni.d();
    c(mouseX, mouseY, partialTicks);
    bni.e();
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    int i = 274;
    int j = this.l / 2 - i / 2;
    int k = 30;
    a(0, 0, this.l, this.m, -2130706433, 16777215);
    a(0, 0, this.l, this.m, 0, Integer.MIN_VALUE);
    this.j.N().a(B);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    if (this.i < 1.0E-4D)
    {
      b(j + 0, k + 0, 0, 0, 99, 44);
      b(j + 99, k + 0, 129, 0, 27, 44);
      b(j + 99 + 26, k + 0, 126, 0, 3, 44);
      b(j + 99 + 26 + 3, k + 0, 99, 0, 26, 44);
      b(j + 155, k + 0, 0, 45, 155, 44);
    }
    else
    {
      b(j + 0, k + 0, 0, 0, 155, 44);
      b(j + 155, k + 0, 0, 45, 155, 44);
    }
    bni.G();
    bni.c(this.l / 2 + 90, 70.0F, 0.0F);
    bni.b(-20.0F, 0.0F, 0.0F, 1.0F);
    float f = 1.8F - on.e(on.a((float)(bcf.I() % 1000L) / 1000.0F * 6.2831855F) * 0.1F);
    f = f * 100.0F / (this.q.a(this.r) + 32);
    bni.b(f, f, f);
    a(this.q, this.r, 0, -8, 65280);
    bni.H();
    String s = "Minecraft 1.9";
    if (this.j.u()) {
      s = s + " Demo";
    } else {
      s = s + ("release".equalsIgnoreCase(this.j.d()) ? "" : new StringBuilder().append("/").append(this.j.d()).toString());
    }
    c(this.q, "" + Source.mod_Name + " " + Source.mod_VersionName + " " + Source.mod_VersionType, 2, this.m - 10, -1);
    c(this.q, s, 2, this.m - 20, -1);
    
    bni.d(1.0F, 1.0F, 1.0F);
    
    LabyMod.getInstance().draw.drawCenteredString("LabyMod Developer", this.l - 55, this.m - 29, 0.8D);
    int m = 48;
    LabyMod.getInstance().textureManager.drawPlayerHead("LabyStudio", this.l - 50 - m, this.m - 18, 0.4D);
    LabyMod.getInstance().draw.drawCenteredString("LabyStudio", this.l - 43 - m, this.m - 7, 0.6D);
    m = 10;
    LabyMod.getInstance().textureManager.drawPlayerHead("Zockermaus", this.l - 50 - m, this.m - 18, 0.4D);
    LabyMod.getInstance().draw.drawCenteredString("Zockermaus", this.l - 43 - m, this.m - 7, 0.6D);
    m = -25;
    LabyMod.getInstance().textureManager.drawPlayerHead("_qlow", this.l - 50 - m, this.m - 18, 0.4D);
    LabyMod.getInstance().draw.drawCenteredString("_qlow", this.l - 44 - m, this.m - 7, 0.6D);
    if ((this.x != null) && (!this.x.isEmpty()))
    {
      a(this.F - 2, this.G - 2, this.H + 2, this.I - 1, 1428160512);
      c(this.q, this.x, this.F, this.G, -1);
      c(this.q, this.y, (this.l - this.D) / 2, ((bcz)this.n.get(0)).i - 12, -1);
    }
    super.a(mouseX, mouseY, partialTicks);
    if (a()) {
      this.M.a(mouseX, mouseY, partialTicks);
    }
    LabyMod.getInstance().overlay(mouseX, mouseY);
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    super.a(mouseX, mouseY, mouseButton);
    synchronized (this.w)
    {
      if ((!this.x.isEmpty()) && (mouseX >= this.F) && (mouseX <= this.H) && (mouseY >= this.G) && (mouseY <= this.I))
      {
        bef guiconfirmopenlink = new bef(this, this.z, 13, true);
        guiconfirmopenlink.f();
        this.j.a(guiconfirmopenlink);
      }
    }
    if (a()) {
      this.M.a(mouseX, mouseY, mouseButton);
    }
  }
  
  public void m()
  {
    if (this.M != null) {
      this.M.m();
    }
  }
}
