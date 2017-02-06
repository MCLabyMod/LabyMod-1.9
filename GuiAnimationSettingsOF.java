import java.util.List;

public class GuiAnimationSettingsOF
  extends bfb
{
  private bfb prevScreen;
  protected String title;
  private bch settings;
  private static bch.a[] enumOptions = { bch.a.ANIMATED_WATER, bch.a.ANIMATED_LAVA, bch.a.ANIMATED_FIRE, bch.a.ANIMATED_PORTAL, bch.a.ANIMATED_REDSTONE, bch.a.ANIMATED_EXPLOSION, bch.a.ANIMATED_FLAME, bch.a.ANIMATED_SMOKE, bch.a.VOID_PARTICLES, bch.a.WATER_PARTICLES, bch.a.RAIN_SPLASH, bch.a.PORTAL_PARTICLES, bch.a.POTION_PARTICLES, bch.a.DRIPPING_WATER_LAVA, bch.a.ANIMATED_TERRAIN, bch.a.ANIMATED_TEXTURES, bch.a.FIREWORK_PARTICLES, bch.a.o };
  
  public GuiAnimationSettingsOF(bfb guiscreen, bch gamesettings)
  {
    this.prevScreen = guiscreen;
    this.settings = gamesettings;
  }
  
  public void b()
  {
    this.title = bwo.a("of.options.animationsTitle", new Object[0]);
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
    this.n.add(new bcz(210, this.l / 2 - 155, this.m / 6 + 168 + 11, 70, 20, Lang.get("of.options.animation.allOn")));
    this.n.add(new bcz(211, this.l / 2 - 155 + 80, this.m / 6 + 168 + 11, 70, 20, Lang.get("of.options.animation.allOff")));
    
    this.n.add(new bdm(200, this.l / 2 + 5, this.m / 6 + 168 + 11, bwo.a("gui.done", new Object[0])));
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
    if (guibutton.k == 210) {
      this.j.u.setAllAnimations(true);
    }
    if (guibutton.k == 211) {
      this.j.u.setAllAnimations(false);
    }
    bcx sr = new bcx(this.j);
    a(this.j, sr.a(), sr.b());
  }
  
  public void a(int x, int y, float f)
  {
    c();
    a(this.q, this.title, this.l / 2, 15, 16777215);
    super.a(x, y, f);
  }
}
