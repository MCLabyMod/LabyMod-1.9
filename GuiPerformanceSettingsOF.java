import java.util.List;

public class GuiPerformanceSettingsOF
  extends bfb
{
  private bfb prevScreen;
  protected String title;
  private bch settings;
  private static bch.a[] enumOptions = { bch.a.SMOOTH_FPS, bch.a.SMOOTH_WORLD, bch.a.FAST_RENDER, bch.a.FAST_MATH, bch.a.CHUNK_UPDATES, bch.a.CHUNK_UPDATES_DYNAMIC, bch.a.LAZY_CHUNK_LOADING };
  private TooltipManager tooltipManager = new TooltipManager(this);
  
  public GuiPerformanceSettingsOF(bfb guiscreen, bch gamesettings)
  {
    this.prevScreen = guiscreen;
    this.settings = gamesettings;
  }
  
  public void b()
  {
    this.title = bwo.a("of.options.performanceTitle", new Object[0]);
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
  }
  
  public void a(int x, int y, float f)
  {
    c();
    a(this.q, this.title, this.l / 2, 15, 16777215);
    super.a(x, y, f);
    
    this.tooltipManager.drawTooltips(x, y, this.n);
  }
}
