import java.util.List;

public class GuiQualitySettingsOF
  extends bfb
{
  private bfb prevScreen;
  protected String title;
  private bch settings;
  private static bch.a[] enumOptions = { bch.a.D, bch.a.MIPMAP_TYPE, bch.a.AF_LEVEL, bch.a.AA_LEVEL, bch.a.CLEAR_WATER, bch.a.RANDOM_MOBS, bch.a.BETTER_GRASS, bch.a.BETTER_SNOW, bch.a.CUSTOM_FONTS, bch.a.CUSTOM_COLORS, bch.a.SWAMP_COLORS, bch.a.SMOOTH_BIOMES, bch.a.CONNECTED_TEXTURES, bch.a.NATURAL_TEXTURES, bch.a.CUSTOM_SKY, bch.a.CUSTOM_ITEMS };
  private TooltipManager tooltipManager = new TooltipManager(this);
  
  public GuiQualitySettingsOF(bfb guiscreen, bch gamesettings)
  {
    this.prevScreen = guiscreen;
    this.settings = gamesettings;
  }
  
  public void b()
  {
    this.title = bwo.a("of.options.qualityTitle", new Object[0]);
    this.n.clear();
    for (int i = 0; i < enumOptions.length; i++)
    {
      bch.a opt = enumOptions[i];
      
      int x = this.l / 2 - 155 + i % 2 * 160;
      int y = this.m / 6 + 21 * (i / 2) - 12;
      if (!opt.a()) {
        this.n.add(new GuiOptionButtonOF(opt.c(), x, y, opt, this.settings.c(opt)));
      } else {
        this.n.add(new GuiOptionSliderOF(opt.c(), x, y, opt));
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
    if (guibutton.k != bch.a.AA_LEVEL.ordinal())
    {
      bcx sr = new bcx(this.j);
      a(this.j, sr.a(), sr.b());
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
