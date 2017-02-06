import java.util.List;

public class GuiOtherSettingsOF
  extends bfb
  implements beg
{
  private bfb prevScreen;
  protected String title;
  private bch settings;
  private static bch.a[] enumOptions = { bch.a.LAGOMETER, bch.a.PROFILER, bch.a.WEATHER, bch.a.TIME, bch.a.v, bch.a.FULLSCREEN_MODE, bch.a.SHOW_FPS, bch.a.AUTOSAVE_TICKS, bch.a.h };
  private TooltipManager tooltipManager = new TooltipManager(this);
  
  public GuiOtherSettingsOF(bfb guiscreen, bch gamesettings)
  {
    this.prevScreen = guiscreen;
    this.settings = gamesettings;
  }
  
  public void b()
  {
    this.title = bwo.a("of.options.otherTitle", new Object[0]);
    this.n.clear();
    for (int i = 0; i < enumOptions.length; i++)
    {
      bch.a enumoptions = enumOptions[i];
      
      int x = this.l / 2 - 155 + i % 2 * 160;
      int y = this.m / 6 + 21 * (i / 2) - 12;
      if (!enumoptions.a()) {
        this.n.add(new GuiOptionButtonOF(enumoptions.c(), x, y, enumoptions, this.settings.c(enumoptions)));
      } else {
        this.n.add(new GuiOptionSliderOF(enumoptions.c(), x, y, enumoptions));
      }
    }
    this.n.add(new bcz(210, this.l / 2 - 100, this.m / 6 + 168 + 11 - 44, bwo.a("of.options.other.reset", new Object[0])));
    
    this.n.add(new bcz(200, this.l / 2 - 100, this.m / 6 + 168 + 11, bwo.a("gui.done", new Object[0])));
  }
  
  protected void a(bcz guibutton)
  {
    if (!guibutton.l) {
      return;
    }
    if ((guibutton.k < 200) && ((guibutton instanceof bdm)))
    {
      this.settings.a(((bdm)guibutton).c(), 1);
      guibutton.j = this.settings.c(bch.a.a(guibutton.k));
    }
    if (guibutton.k == 200)
    {
      this.j.u.b();
      this.j.a(this.prevScreen);
    }
    if (guibutton.k == 210)
    {
      this.j.u.b();
      beh guiyesno = new beh(this, bwo.a("of.message.other.reset", new Object[0]), "", 9999);
      this.j.a(guiyesno);
    }
  }
  
  public void a(boolean flag, int i)
  {
    if (flag) {
      this.j.u.resetSettings();
    }
    this.j.a(this);
  }
  
  public void a(int x, int y, float f)
  {
    c();
    a(this.q, this.title, this.l / 2, 15, 16777215);
    super.a(x, y, f);
    
    this.tooltipManager.drawTooltips(x, y, this.n);
  }
}
