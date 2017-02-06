import de.labystudio.gui.GuiCompModSettings;
import de.labystudio.gui.GuiModSettings;
import de.labystudio.modapi.ModManager;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class bev
  extends bfb
  implements beg
{
  private static final bch.a[] f = { bch.a.c };
  private final bfb g;
  private final bch h;
  private bcz i;
  private bdk r;
  protected String a = "Options";
  
  public bev(bfb p_i1046_1_, bch p_i1046_2_)
  {
    this.g = p_i1046_1_;
    this.h = p_i1046_2_;
  }
  
  public void b()
  {
    int i = 0;
    this.a = bwo.a("options.title", new Object[0]);
    for (bch.a gamesettings$options : f)
    {
      if (gamesettings$options.a())
      {
        this.n.add(new bdr(gamesettings$options.c(), this.l / 2 - 155 + i % 2 * 160, this.m / 6 - 12 + 24 * (i >> 1), gamesettings$options));
      }
      else
      {
        bdm guioptionbutton = new bdm(gamesettings$options.c(), this.l / 2 - 155 + i % 2 * 160, this.m / 6 - 12 + 24 * (i >> 1), gamesettings$options, this.h.c(gamesettings$options));
        this.n.add(guioptionbutton);
      }
      i++;
    }
    if (this.j.f != null)
    {
      qk enumdifficulty = this.j.f.ae();
      this.i = new bcz(108, this.l / 2 - 155 + i % 2 * 160, this.m / 6 - 12 + 24 * (i >> 1), 150, 20, a(enumdifficulty));
      this.n.add(this.i);
      if ((this.j.E()) && (!this.j.f.T().s()))
      {
        this.i.a(this.i.b() - 20);
        this.r = new bdk(109, this.i.h + this.i.b(), this.i.i);
        this.n.add(this.r);
        this.r.b(this.j.f.T().y());
        this.r.l = (!this.r.c());
        this.i.l = (!this.r.c());
      }
      else
      {
        this.i.l = false;
      }
    }
    else
    {
      this.n.add(new bdm(bch.a.K.c(), this.l / 2 - 155 + i % 2 * 160, this.m / 6 - 12 + 24 * (i >> 1), bch.a.K, this.h.c(bch.a.K)));
    }
    this.n.add(new bcz(110, this.l / 2 - 155, this.m / 6 + 48 - 6, 150, 20, bwo.a("options.skinCustomisation", new Object[0])));
    this.n.add(new bcz(106, this.l / 2 + 5, this.m / 6 + 48 - 6, 150, 20, bwo.a("options.sounds", new Object[0])));
    this.n.add(new bcz(101, this.l / 2 - 155, this.m / 6 + 72 - 6, 150, 20, bwo.a("options.video", new Object[0])));
    this.n.add(new bcz(100, this.l / 2 + 5, this.m / 6 + 72 - 6, 150, 20, bwo.a("options.controls", new Object[0])));
    this.n.add(new bcz(102, this.l / 2 - 155, this.m / 6 + 96 - 6, 150, 20, bwo.a("options.language", new Object[0])));
    this.n.add(new bcz(103, this.l / 2 + 5, this.m / 6 + 96 - 6, 150, 20, bwo.a("options.chat.title", new Object[0])));
    this.n.add(new bcz(105, this.l / 2 - 155, this.m / 6 + 120 - 6, 150, 20, bwo.a("options.resourcepack", new Object[0])));
    this.n.add(new bcz(104, this.l / 2 + 5, this.m / 6 + 120 - 6, 150, 20, bwo.a("options.snooper.view", new Object[0])));
    this.n.add(new bcz(200, this.l / 2 - 100, this.m / 6 + 168, bwo.a("gui.done", new Object[0])));
    
    this.n.add(new bcz(300, this.l / 2 + 5, this.m / 6 + 25 - 6, 150, 20, bwo.a("LabyMod Settings", new Object[0])));
    if (ModManager.getSettings().size() != 0)
    {
      String s = "s";
      if (ModManager.getSettings().size() == 1) {
        s = "";
      }
      this.n.add(new bcz(301, this.l / 2 - 155, this.m / 6 + 25 - 6, 150, 20, bwo.a("Mod Settings (" + ModManager.getSettings().size() + " Mod" + s + ")", new Object[0])));
    }
  }
  
  public String a(qk p_175355_1_)
  {
    eu itextcomponent = new fa("");
    itextcomponent.a(new fb("options.difficulty", new Object[0]));
    itextcomponent.a(": ");
    itextcomponent.a(new fb(p_175355_1_.b(), new Object[0]));
    return itextcomponent.d();
  }
  
  public void a(boolean result, int id)
  {
    this.j.a(this);
    if ((id == 109) && (result) && (this.j.f != null))
    {
      this.j.f.T().e(true);
      this.r.b(true);
      this.r.l = false;
      this.i.l = false;
    }
  }
  
  protected void a(bcz button)
    throws IOException
  {
    if (button.l)
    {
      if ((button.k < 100) && ((button instanceof bdm)))
      {
        bch.a gamesettings$options = ((bdm)button).c();
        this.h.a(gamesettings$options, 1);
        button.j = this.h.c(bch.a.a(button.k));
      }
      if (button.k == 108)
      {
        this.j.f.T().a(qk.a(this.j.f.ae().a() + 1));
        this.i.j = a(this.j.f.ae());
      }
      if (button.k == 109) {
        this.j.a(new beh(this, new fb("difficulty.lock.title", new Object[0]).d(), new fb("difficulty.lock.question", new Object[] { new fb(this.j.f.T().x().b(), new Object[0]) }).d(), 109));
      }
      if (button.k == 110)
      {
        this.j.u.b();
        this.j.a(new bfd(this));
      }
      if (button.k == 101)
      {
        this.j.u.b();
        this.j.a(new bfj(this, this.h));
      }
      if (button.k == 100)
      {
        this.j.u.b();
        this.j.a(new bfr(this, this.h));
      }
      if (button.k == 102)
      {
        this.j.u.b();
        this.j.a(new bet(this, this.h, this.j.Q()));
      }
      if (button.k == 103)
      {
        this.j.u.b();
        this.j.a(new bed(this, this.h));
      }
      if (button.k == 104)
      {
        this.j.u.b();
        this.j.a(new bfe(this, this.h));
      }
      if (button.k == 200)
      {
        this.j.u.b();
        this.j.a(this.g);
      }
      if (button.k == 105)
      {
        this.j.u.b();
        this.j.a(new bgy(this));
      }
      if (button.k == 106)
      {
        this.j.u.b();
        this.j.a(new bff(this, this.h));
      }
      if (button.k == 300)
      {
        this.j.u.b();
        this.j.a(new GuiModSettings(this, this.h));
      }
      if (button.k == 301)
      {
        this.j.u.b();
        this.j.a(new GuiCompModSettings(this));
      }
    }
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    c();
    a(this.q, this.a, this.l / 2, 15, 16777215);
    super.a(mouseX, mouseY, partialTicks);
  }
}
