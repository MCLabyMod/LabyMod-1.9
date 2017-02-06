import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import de.labystudio.modapi.ModAPI;
import de.labystudio.modapi.events.ActionPerformedEvent;
import de.labystudio.modapi.events.InitScreenEvent;
import de.labystudio.modapi.events.KeyTypedScreenEvent;
import de.labystudio.modapi.events.MouseClickedScreenEvent;
import de.labystudio.modapi.events.RenderScreenOverlayEvent;
import de.labystudio.modapi.events.SendChatMessageEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public abstract class bfb
  extends bcv
  implements beg
{
  private static final Logger a = ;
  private static final Set<String> f = Sets.newHashSet(new String[] { "http", "https" });
  private static final Splitter g = Splitter.on('\n');
  protected bcf j;
  protected brz k;
  public int l;
  public int m;
  protected List<bcz> n = Lists.newArrayList();
  protected List<bdf> o = Lists.newArrayList();
  public boolean p;
  protected bct q;
  private bcz h;
  private int i;
  private long r;
  private int s;
  private URI t;
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    if (ModAPI.enabled()) {
      ModAPI.callEvent(new RenderScreenOverlayEvent(this, mouseX, mouseY));
    }
    for (int i = 0; i < this.n.size(); i++) {
      ((bcz)this.n.get(i)).a(this.j, mouseX, mouseY);
    }
    for (int j = 0; j < this.o.size(); j++) {
      ((bdf)this.o.get(j)).a(this.j, mouseX, mouseY);
    }
  }
  
  protected void a(char typedChar, int keyCode)
    throws IOException
  {
    if (ModAPI.enabled()) {
      ModAPI.callEvent(new KeyTypedScreenEvent(this, typedChar, keyCode));
    }
    if (keyCode == 1)
    {
      this.j.a((bfb)null);
      if (this.j.m == null) {
        this.j.o();
      }
    }
  }
  
  public static String o()
  {
    try
    {
      Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
      if ((transferable != null) && (transferable.isDataFlavorSupported(DataFlavor.stringFlavor))) {
        return (String)transferable.getTransferData(DataFlavor.stringFlavor);
      }
    }
    catch (Exception localException) {}
    return "";
  }
  
  public static void e(String copyText)
  {
    if (!StringUtils.isEmpty(copyText)) {
      try
      {
        StringSelection stringselection = new StringSelection(copyText);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringselection, (ClipboardOwner)null);
      }
      catch (Exception localException) {}
    }
  }
  
  protected void a(adq stack, int x, int y)
  {
    List<String> list = stack.a(this.j.h, this.j.u.x);
    for (int i = 0; i < list.size(); i++) {
      if (i == 0) {
        list.set(i, stack.u().e + (String)list.get(i));
      } else {
        list.set(i, a.h + (String)list.get(i));
      }
    }
    a(list, x, y);
  }
  
  protected void a(String tabName, int mouseX, int mouseY)
  {
    a(Arrays.asList(new String[] { tabName }), mouseX, mouseY);
  }
  
  protected void a(List<String> textLines, int x, int y)
  {
    if (!textLines.isEmpty())
    {
      bni.E();
      bcd.a();
      bni.g();
      bni.j();
      int i = 0;
      for (String s : textLines)
      {
        int j = this.q.a(s);
        if (j > i) {
          i = j;
        }
      }
      int l1 = x + 12;
      int i2 = y - 12;
      int k = 8;
      if (textLines.size() > 1) {
        k += 2 + (textLines.size() - 1) * 10;
      }
      if (l1 + i > this.l) {
        l1 -= 28 + i;
      }
      if (i2 + k + 6 > this.m) {
        i2 = this.m - k - 6;
      }
      this.e = 300.0F;
      this.k.a = 300.0F;
      int l = -267386864;
      a(l1 - 3, i2 - 4, l1 + i + 3, i2 - 3, l, l);
      a(l1 - 3, i2 + k + 3, l1 + i + 3, i2 + k + 4, l, l);
      a(l1 - 3, i2 - 3, l1 + i + 3, i2 + k + 3, l, l);
      a(l1 - 4, i2 - 3, l1 - 3, i2 + k + 3, l, l);
      a(l1 + i + 3, i2 - 3, l1 + i + 4, i2 + k + 3, l, l);
      int i1 = 1347420415;
      int j1 = (i1 & 0xFEFEFE) >> 1 | i1 & 0xFF000000;
      a(l1 - 3, i2 - 3 + 1, l1 - 3 + 1, i2 + k + 3 - 1, i1, j1);
      a(l1 + i + 2, i2 - 3 + 1, l1 + i + 3, i2 + k + 3 - 1, i1, j1);
      a(l1 - 3, i2 - 3, l1 + i + 3, i2 - 3 + 1, i1, i1);
      a(l1 - 3, i2 + k + 2, l1 + i + 3, i2 + k + 3, j1, j1);
      for (int k1 = 0; k1 < textLines.size(); k1++)
      {
        String s1 = (String)textLines.get(k1);
        this.q.a(s1, l1, i2, -1);
        if (k1 == 0) {
          i2 += 2;
        }
        i2 += 10;
      }
      this.e = 0.0F;
      this.k.a = 0.0F;
      bni.f();
      bni.k();
      bcd.b();
      bni.D();
    }
  }
  
  protected void a(eu component, int x, int y)
  {
    if ((component != null) && (component.b().i() != null))
    {
      ew hoverevent = component.b().i();
      if (hoverevent.a() == ew.a.c)
      {
        adq itemstack = null;
        try
        {
          eb nbtbase = ed.a(hoverevent.b().c());
          if ((nbtbase instanceof dn)) {
            itemstack = adq.a((dn)nbtbase);
          }
        }
        catch (ec localec1) {}
        if (itemstack != null) {
          a(itemstack, x, y);
        } else {
          a(a.m + "Invalid Item!", x, y);
        }
      }
      else if (hoverevent.a() == ew.a.d)
      {
        if (this.j.u.x) {
          try
          {
            eb nbtbase1 = ed.a(hoverevent.b().c());
            if ((nbtbase1 instanceof dn))
            {
              List<String> list1 = Lists.newArrayList();
              dn nbttagcompound = (dn)nbtbase1;
              list1.add(nbttagcompound.l("name"));
              if (nbttagcompound.b("type", 8))
              {
                String s = nbttagcompound.l("type");
                list1.add("Type: " + s + " (" + rt.a(s) + ")");
              }
              list1.add(nbttagcompound.l("id"));
              a(list1, x, y);
            }
            else
            {
              a(a.m + "Invalid Entity!", x, y);
            }
          }
          catch (ec var10)
          {
            a(a.m + "Invalid Entity!", x, y);
          }
        }
      }
      else if (hoverevent.a() == ew.a.a)
      {
        a(g.splitToList(hoverevent.b().d()), x, y);
      }
      else if (hoverevent.a() == ew.a.b)
      {
        np statbase = nt.a(hoverevent.b().c());
        if (statbase != null)
        {
          eu itextcomponent = statbase.e();
          eu itextcomponent1 = new fb("stats.tooltip.type." + (statbase.d() ? "achievement" : "statistic"), new Object[0]);
          itextcomponent1.b().b(Boolean.valueOf(true));
          String s1 = (statbase instanceof nj) ? ((nj)statbase).f() : null;
          List<String> list = Lists.newArrayList(new String[] { itextcomponent.d(), itextcomponent1.d() });
          if (s1 != null) {
            list.addAll(this.q.c(s1, 150));
          }
          a(list, x, y);
        }
        else
        {
          a(a.m + "Invalid statistic/achievement!", x, y);
        }
      }
      bni.g();
    }
  }
  
  protected void a(String newChatText, boolean shouldOverwrite) {}
  
  protected boolean a(eu component)
  {
    if (component == null) {
      return false;
    }
    et clickevent = component.b().h();
    if (r())
    {
      if (component.b().j() != null) {
        a(component.b().j(), false);
      }
    }
    else if (clickevent != null)
    {
      if (clickevent.a() == et.a.a)
      {
        if (!this.j.u.o) {
          return false;
        }
        try
        {
          URI uri = new URI(clickevent.b());
          String s = uri.getScheme();
          if (s == null) {
            throw new URISyntaxException(clickevent.b(), "Missing protocol");
          }
          if (!f.contains(s.toLowerCase())) {
            throw new URISyntaxException(clickevent.b(), "Unsupported protocol: " + s.toLowerCase());
          }
          if (this.j.u.p)
          {
            this.t = uri;
            this.j.a(new bef(this, clickevent.b(), 31102009, false));
          }
          else
          {
            a(uri);
          }
        }
        catch (URISyntaxException urisyntaxexception)
        {
          a.error("Can't open url for " + clickevent, urisyntaxexception);
        }
      }
      else if (clickevent.a() == et.a.b)
      {
        URI uri1 = new File(clickevent.b()).toURI();
        a(uri1);
      }
      else if (clickevent.a() == et.a.d)
      {
        a(clickevent.b(), true);
      }
      else if (clickevent.a() == et.a.c)
      {
        b(clickevent.b(), false);
      }
      else
      {
        a.error("Don't know how to handle " + clickevent);
      }
      return true;
    }
    return false;
  }
  
  public void f(String msg)
  {
    b(msg, true);
  }
  
  public void b(String msg, boolean addToChat)
  {
    if (addToChat) {
      this.j.r.d().a(msg);
    }
    if (ModAPI.enabled())
    {
      if (!((SendChatMessageEvent)ModAPI.callEvent(new SendChatMessageEvent(msg))).isCancelled()) {
        this.j.h.g(msg);
      }
    }
    else {
      this.j.h.g(msg);
    }
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    if (ModAPI.enabled()) {
      ModAPI.callEvent(new MouseClickedScreenEvent(this, mouseX, mouseY));
    }
    if (mouseButton == 0) {
      for (int i = 0; i < this.n.size(); i++)
      {
        bcz guibutton = (bcz)this.n.get(i);
        if (guibutton.c(this.j, mouseX, mouseY))
        {
          this.h = guibutton;
          guibutton.a(this.j.U());
          a(guibutton);
          ModAPI.callEvent(new ActionPerformedEvent(this, guibutton));
        }
      }
    }
  }
  
  protected void b(int mouseX, int mouseY, int state)
  {
    if ((this.h != null) && (state == 0))
    {
      this.h.a(mouseX, mouseY);
      this.h = null;
    }
  }
  
  protected void a(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {}
  
  protected void a(bcz button)
    throws IOException
  {}
  
  public void a(bcf mc, int width, int height)
  {
    this.j = mc;
    this.k = mc.ad();
    this.q = mc.k;
    this.l = width;
    this.m = height;
    this.n.clear();
    b();
    if (ModAPI.enabled()) {
      ModAPI.callEvent(new InitScreenEvent(this, this.n));
    }
  }
  
  public void a(int w, int h)
  {
    this.l = w;
    this.m = h;
  }
  
  public void b() {}
  
  public void p()
    throws IOException
  {
    if (Mouse.isCreated()) {
      while (Mouse.next()) {
        k();
      }
    }
    if (Keyboard.isCreated()) {
      while (Keyboard.next()) {
        l();
      }
    }
  }
  
  public void k()
    throws IOException
  {
    int i = Mouse.getEventX() * this.l / this.j.d;
    int j = this.m - Mouse.getEventY() * this.m / this.j.e - 1;
    int k = Mouse.getEventButton();
    if (Mouse.getEventButtonState())
    {
      if ((this.j.u.z) && (this.s++ > 0)) {
        return;
      }
      this.i = k;
      this.r = bcf.I();
      a(i, j, this.i);
    }
    else if (k != -1)
    {
      if ((this.j.u.z) && (--this.s > 0)) {
        return;
      }
      this.i = -1;
      b(i, j, k);
    }
    else if ((this.i != -1) && (this.r > 0L))
    {
      long l = bcf.I() - this.r;
      a(i, j, this.i, l);
    }
  }
  
  public void l()
    throws IOException
  {
    char c0 = Keyboard.getEventCharacter();
    if (((Keyboard.getEventKey() == 0) && (c0 >= ' ')) || (Keyboard.getEventKeyState())) {
      a(c0, Keyboard.getEventKey());
    }
    this.j.W();
  }
  
  public void e() {}
  
  public void m() {}
  
  public void c()
  {
    d_(0);
  }
  
  public void d_(int tint)
  {
    if (this.j.f != null) {
      a(0, 0, this.l, this.m, -1072689136, -804253680);
    } else {
      c(tint);
    }
  }
  
  public void c(int tint)
  {
    bni.g();
    bni.p();
    bnu tessellator = bnu.a();
    bmz vertexbuffer = tessellator.c();
    this.j.N().a(b);
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    float f = 32.0F;
    vertexbuffer.a(7, bvp.i);
    vertexbuffer.b(0.0D, this.m, 0.0D).a(0.0D, this.m / 32.0F + tint).b(64, 64, 64, 255).d();
    vertexbuffer.b(this.l, this.m, 0.0D).a(this.l / 32.0F, this.m / 32.0F + tint).b(64, 64, 64, 255).d();
    vertexbuffer.b(this.l, 0.0D, 0.0D).a(this.l / 32.0F, tint).b(64, 64, 64, 255).d();
    vertexbuffer.b(0.0D, 0.0D, 0.0D).a(0.0D, tint).b(64, 64, 64, 255).d();
    tessellator.b();
  }
  
  public boolean d()
  {
    return true;
  }
  
  public void a(boolean result, int id)
  {
    if (id == 31102009)
    {
      if (result) {
        a(this.t);
      }
      this.t = null;
      this.j.a(this);
    }
  }
  
  private void a(URI url)
  {
    try
    {
      Class<?> oclass = Class.forName("java.awt.Desktop");
      Object object = oclass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
      oclass.getMethod("browse", new Class[] { URI.class }).invoke(object, new Object[] { url });
    }
    catch (Throwable throwable1)
    {
      Throwable throwable = throwable1.getCause();
      a.error("Couldn't open link: {}", new Object[] { throwable == null ? "<UNKNOWN>" : throwable.getMessage() });
    }
  }
  
  public static boolean q()
  {
    return (Keyboard.isKeyDown(219)) || (Keyboard.isKeyDown(220));
  }
  
  public static boolean r()
  {
    return (Keyboard.isKeyDown(42)) || (Keyboard.isKeyDown(54));
  }
  
  public static boolean s()
  {
    return (Keyboard.isKeyDown(56)) || (Keyboard.isKeyDown(184));
  }
  
  public static boolean d(int keyID)
  {
    return (keyID == 45) && (q()) && (!r()) && (!s());
  }
  
  public static boolean e(int keyID)
  {
    return (keyID == 47) && (q()) && (!r()) && (!s());
  }
  
  public static boolean f(int keyID)
  {
    return (keyID == 46) && (q()) && (!r()) && (!s());
  }
  
  public static boolean g(int keyID)
  {
    return (keyID == 30) && (q()) && (!r()) && (!s());
  }
  
  public void b(bcf mcIn, int w, int h)
  {
    a(mcIn, w, h);
  }
}
