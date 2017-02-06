import de.labystudio.gui.GuiAutoText;
import de.labystudio.gui.GuiFilter;
import de.labystudio.gui.GuiSymbolSelector;
import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.ModSettings;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class bee
  extends bfb
  implements bfg
{
  private static final Logger f = ;
  private String g = "";
  private int h = -1;
  private bfh i;
  protected bdd a;
  private String r = "";
  
  public bee() {}
  
  public bee(String defaultText)
  {
    this.r = defaultText;
  }
  
  public void b()
  {
    Keyboard.enableRepeatEvents(true);
    this.h = this.j.r.d().c().size();
    this.a = new bdd(0, this.q, 4, this.m - 12, this.l - 4, 12);
    this.a.f(100);
    this.a.a(false);
    this.a.b(true);
    this.a.a(this.r);
    this.a.d(false);
    this.i = new bee.a(this.a);
    
    int i = 0;
    this.n.add(new bcz(1, this.l - 48 - i, 4, 45, 20, Color.cl("a") + "Symbols"));
    i += 47;
    if (ConfigManager.settings.chatFilter.booleanValue())
    {
      this.n.add(new bcz(2, this.l - 48 - i, 4, 45, 20, Color.cl("a") + "Filter"));
      i += 47;
    }
    if (ConfigManager.settings.autoText)
    {
      this.n.add(new bcz(3, this.l - 54 - i, 4, 50, 20, Color.cl("a") + "AutoText"));
      i += 47;
    }
  }
  
  protected void a(bcz button)
    throws IOException
  {
    super.a(button);
    if (button.k == 1) {
      this.j.a(new GuiSymbolSelector(this.a.b()));
    }
    if (button.k == 2) {
      this.j.a(new GuiFilter(this.a.b()));
    }
    if (button.k == 3) {
      this.j.a(new GuiAutoText(this.a.b()));
    }
  }
  
  public void m()
  {
    Keyboard.enableRepeatEvents(false);
    this.j.r.d().d();
  }
  
  public void e()
  {
    this.a.a();
  }
  
  protected void a(char typedChar, int keyCode)
    throws IOException
  {
    this.i.d();
    if (keyCode == 15) {
      this.i.a();
    } else {
      this.i.c();
    }
    if (keyCode == 1)
    {
      this.j.a((bfb)null);
    }
    else if ((keyCode != 28) && (keyCode != 156))
    {
      if (keyCode == 200) {
        b(-1);
      } else if (keyCode == 208) {
        b(1);
      } else if (keyCode == 201) {
        this.j.r.d().b(this.j.r.d().i() - 1);
      } else if (keyCode == 209) {
        this.j.r.d().b(-this.j.r.d().i() + 1);
      } else {
        this.a.a(typedChar, keyCode);
      }
    }
    else
    {
      String s = this.a.b().trim();
      if (!s.isEmpty()) {
        f(s);
      }
      this.j.a((bfb)null);
    }
  }
  
  public void k()
    throws IOException
  {
    super.k();
    int i = Mouse.getEventDWheel();
    if (i != 0)
    {
      if (i > 1) {
        i = 1;
      }
      if (i < -1) {
        i = -1;
      }
      if (!r()) {
        i *= 7;
      }
      this.j.r.d().b(i);
    }
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    if (mouseButton == 0)
    {
      eu itextcomponent = this.j.r.d().a(Mouse.getX(), Mouse.getY());
      if (a(itextcomponent)) {
        return;
      }
    }
    this.a.a(mouseX, mouseY, mouseButton);
    super.a(mouseX, mouseY, mouseButton);
  }
  
  protected void a(String newChatText, boolean shouldOverwrite)
  {
    if (shouldOverwrite) {
      this.a.a(newChatText);
    } else {
      this.a.b(newChatText);
    }
  }
  
  public void b(int msgPos)
  {
    int i = this.h + msgPos;
    int j = this.j.r.d().c().size();
    i = on.a(i, 0, j);
    if (i != this.h) {
      if (i == j)
      {
        this.h = j;
        this.a.a(this.g);
      }
      else
      {
        if (this.h == j) {
          this.g = this.a.b();
        }
        this.a.a((String)this.j.r.d().c().get(i));
        this.h = i;
      }
    }
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    a(2, this.m - 14, this.l - 2, this.m - 2, Integer.MIN_VALUE);
    this.a.g();
    eu itextcomponent = this.j.r.d().a(Mouse.getX(), Mouse.getY());
    if ((itextcomponent != null) && (itextcomponent.b().i() != null)) {
      a(itextcomponent, mouseX, mouseY);
    }
    super.a(mouseX, mouseY, partialTicks);
    DrawUtils.updateMouse(mouseX, mouseY);
  }
  
  public boolean d()
  {
    return false;
  }
  
  public void a(String... p_184072_1_)
  {
    this.i.a(p_184072_1_);
  }
  
  public static class a
    extends bfh
  {
    private bcf g = bcf.z();
    
    public a(bdd p_i46749_1_)
    {
      super(false);
    }
    
    public void a()
    {
      super.a();
      if (this.f.size() > 1)
      {
        StringBuilder stringbuilder = new StringBuilder();
        for (String s : this.f)
        {
          if (stringbuilder.length() > 0) {
            stringbuilder.append(", ");
          }
          stringbuilder.append(s);
        }
        this.g.r.d().a(new fa(stringbuilder.toString()), 1);
      }
    }
    
    public cj b()
    {
      cj blockpos = null;
      if ((this.g.t != null) && (this.g.t.a == bbi.a.b)) {
        blockpos = this.g.t.a();
      }
      return blockpos;
    }
  }
}
