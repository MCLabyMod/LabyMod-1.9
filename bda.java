import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import de.labystudio.gui.GuiAutoText;
import de.labystudio.gui.GuiFilter;
import de.labystudio.gui.GuiNewModChat;
import de.labystudio.gui.GuiSymbolSelector;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.labymod.ModSettings;
import de.labystudio.listener.ChatListener;
import de.labystudio.modapi.ModAPI;
import de.labystudio.modapi.events.ChatReceivedEvent;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import de.labystudio.utils.FilterLoader;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bda
  extends bcv
{
  private static final Splitter a = Splitter.on('\n');
  private static final Joiner f = Joiner.on("\\n");
  private static final Logger g = LogManager.getLogger();
  private final bcf h;
  private final List<String> i = Lists.newArrayList();
  private final List<bcb> j = Lists.newArrayList();
  private final List<bcb> k = Lists.newArrayList();
  private int l;
  private boolean m;
  int detectChange = 0;
  
  public bda(bcf mcIn)
  {
    this.h = mcIn;
  }
  
  public void a(int updateCounter)
  {
    if (this.detectChange != LabyMod.getInstance().draw.getWidth()) {
      b();
    }
    this.detectChange = LabyMod.getInstance().draw.getWidth();
    
    GuiNewModChat.drawChat(updateCounter);
    if (this.h.u.m != zj.b.c)
    {
      int i = i();
      boolean flag = false;
      int j = 0;
      int k = this.k.size();
      float f = this.h.u.q * 0.9F + 0.1F;
      if (k > 0)
      {
        if (e()) {
          flag = true;
        }
        float f1 = h();
        int l = on.f(f() / f1);
        bni.G();
        bni.c(2.0F, 8.0F, 0.0F);
        bni.b(f1, f1, 1.0F);
        for (int i1 = 0; (i1 + this.l < this.k.size()) && (i1 < i); i1++)
        {
          bcb chatline = (bcb)this.k.get(i1 + this.l);
          if (chatline != null)
          {
            int j1 = updateCounter - chatline.b();
            if ((j1 < 200) || (flag))
            {
              double d0 = j1 / 200.0D;
              d0 = 1.0D - d0;
              d0 *= 10.0D;
              d0 = on.a(d0, 0.0D, 1.0D);
              d0 *= d0;
              int l1 = (int)(255.0D * d0);
              if (flag) {
                l1 = 255;
              }
              l1 = (int)(l1 * f);
              j++;
              if (l1 > 3)
              {
                int i2 = 0;
                int j2 = -i1 * 9;
                String var17 = chatline.a().d();
                String p = " " + Color.cl("e") + "✎" + Color.cl("f");
                String o = var17;
                var17 = ChatListener.replaceMessage(chatline.a().d(), chatline.a().c());
                if (!var17.equals(o)) {
                  var17 = var17 + p;
                }
                if ((ChatListener.isMarked(chatline.a().c())) && (FilterLoader.enabled)) {
                  a(i2 - 2, j2 - 9, i2 + l + 4, j2, 1574235432);
                } else {
                  a(i2 - 2, j2 - 9, i2 + l + 4, j2, l1 / 2 << 24);
                }
                bni.m();
                try
                {
                  this.h.k.a(var17, i2, j2 - 8, 16777215 + (l1 << 24));
                }
                catch (Exception error)
                {
                  error.printStackTrace();
                }
                bni.d();
                bni.l();
              }
            }
          }
        }
        if (flag)
        {
          int k2 = this.h.k.a;
          bni.c(-3.0F, 0.0F, 0.0F);
          int l2 = k * k2 + k;
          int i3 = j * k2 + j;
          int j3 = this.l * i3 / k;
          int k1 = i3 * i3 / l2;
          if (l2 != i3)
          {
            int k3 = j3 > 0 ? 170 : 96;
            int l3 = this.m ? 13382451 : 3355562;
            a(0, -j3, 2, -j3 - k1, l3 + (k3 << 24));
            a(2, -j3, 1, -j3 - k1, 13421772 + (k3 << 24));
          }
        }
        bni.H();
      }
    }
  }
  
  public void a()
  {
    this.k.clear();
    this.j.clear();
    this.i.clear();
    GuiNewModChat.clearChatMessages();
  }
  
  public void a(eu chatComponent)
  {
    a(chatComponent, 0);
  }
  
  public void a(eu chatComponent, int chatLineId)
  {
    if (ChatListener.allowedToPrint(chatComponent.d(), chatComponent.c()))
    {
      a(chatComponent, chatLineId, this.h.r.e(), false);
      g.info("[CHAT] " + f.join(a.split(chatComponent.c())));
    }
  }
  
  private void a(eu chatComponent, int chatLineId, int updateCounter, boolean displayOnly)
  {
    if ((chatComponent != null) && (ModAPI.enabled()) && (!displayOnly)) {
      ModAPI.callEvent(new ChatReceivedEvent(chatComponent.d(), chatComponent.c()));
    }
    boolean leftChat = ChatListener.isServerMSG(Color.removeColor(chatComponent.c()));
    if (!ConfigManager.settings.chatPositionRight) {
      leftChat = !leftChat;
    }
    if (leftChat)
    {
      GuiNewModChat.setChatLine(chatComponent, chatLineId, updateCounter, displayOnly);
      return;
    }
    if (chatLineId != 0) {
      c(chatLineId);
    }
    int i = on.d(f() / h());
    List<eu> list = bdb.a(chatComponent, i, this.h.k, false, false);
    boolean flag = e();
    for (eu itextcomponent : list)
    {
      if ((flag) && (this.l > 0))
      {
        this.m = true;
        b(1);
      }
      this.k.add(0, new bcb(updateCounter, itextcomponent, chatLineId));
    }
    while (this.k.size() > 100) {
      this.k.remove(this.k.size() - 1);
    }
    if (!displayOnly)
    {
      this.j.add(0, new bcb(updateCounter, chatComponent, chatLineId));
      while (this.j.size() > 100) {
        this.j.remove(this.j.size() - 1);
      }
    }
  }
  
  public void b()
  {
    ChatListener.init = bcf.I();
    this.k.clear();
    d();
    for (int i = this.j.size() - 1; i >= 0; i--)
    {
      bcb chatline = (bcb)this.j.get(i);
      a(chatline.a(), chatline.c(), chatline.b(), true);
    }
    GuiNewModChat.refreshChat();
  }
  
  public List<String> c()
  {
    return this.i;
  }
  
  public void a(String message)
  {
    if ((this.i.isEmpty()) || (!((String)this.i.get(this.i.size() - 1)).equals(message))) {
      this.i.add(message);
    }
  }
  
  public void d()
  {
    this.l = 0;
    this.m = false;
  }
  
  public void b(int amount)
  {
    GuiNewModChat.scroll(amount);
    if ((DrawUtils.getMouseX() > f()) && ((ConfigManager.settings.extraChat.booleanValue()) || (ConfigManager.settings.chatFilter.booleanValue()))) {
      return;
    }
    this.l += amount;
    int i = this.k.size();
    if (this.l > i - i()) {
      this.l = (i - i());
    }
    if (this.l <= 0)
    {
      this.l = 0;
      this.m = false;
    }
  }
  
  public eu a(int mouseX, int mouseY)
  {
    if (!e()) {
      return null;
    }
    bcx scaledresolution = new bcx(this.h);
    int i = scaledresolution.e();
    float f = h();
    int j = mouseX / i - 2;
    int k = mouseY / i - 40;
    j = on.d(j / f);
    k = on.d(k / f);
    if ((j >= 0) && (k >= 0))
    {
      int l = Math.min(i(), this.k.size());
      if ((j <= on.d(f() / h())) && (k < this.h.k.a * l + l))
      {
        int i1 = k / this.h.k.a + this.l;
        int j1;
        if ((i1 >= 0) && (i1 < this.k.size()))
        {
          bcb chatline = (bcb)this.k.get(i1);
          j1 = 0;
          for (eu itextcomponent : chatline.a()) {
            if ((itextcomponent instanceof fa))
            {
              j1 += this.h.k.a(bdb.a(((fa)itextcomponent).g(), false));
              if (j1 > j) {
                return itextcomponent;
              }
            }
          }
        }
        return GuiNewModChat.getChatComponent(mouseX, mouseY);
      }
      return GuiNewModChat.getChatComponent(mouseX, mouseY);
    }
    return GuiNewModChat.getChatComponent(mouseX, mouseY);
  }
  
  public boolean e()
  {
    return ((this.h.m instanceof bee)) || ((this.h.m instanceof GuiSymbolSelector)) || ((this.h.m instanceof GuiAutoText)) || ((this.h.m instanceof GuiFilter));
  }
  
  public void c(int id)
  {
    Iterator<bcb> iterator = this.k.iterator();
    while (iterator.hasNext())
    {
      bcb chatline = (bcb)iterator.next();
      if (chatline.c() == id) {
        iterator.remove();
      }
    }
    iterator = this.j.iterator();
    while (iterator.hasNext())
    {
      bcb chatline1 = (bcb)iterator.next();
      if (chatline1.c() == id)
      {
        iterator.remove();
        break;
      }
    }
  }
  
  public int f()
  {
    return a(this.h.u.F);
  }
  
  public int g()
  {
    return b(e() ? this.h.u.H : this.h.u.G);
  }
  
  public float h()
  {
    return this.h.u.E;
  }
  
  public static int a(float scale)
  {
    int i = 320;
    int j = 40;
    return on.d(scale * (i - j) + j);
  }
  
  public static int b(float scale)
  {
    int i = 180;
    int j = 20;
    return on.d(scale * (i - j) + j);
  }
  
  public int i()
  {
    return g() / 9;
  }
}
