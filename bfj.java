import java.util.List;
import shadersmod.client.GuiShaders;

public class bfj
  extends bfb
{
  private bfb f;
  protected String a = "Video Settings";
  private bch g;
  private static bch.a[] i = { bch.a.l, bch.a.f, bch.a.m, bch.a.i, bch.a.AO_LEVEL, bch.a.g, bch.a.n, bch.a.x, bch.a.d, bch.a.I, bch.a.FOG_FANCY, bch.a.FOG_START };
  private static final String __OBFID = "CL_00000718";
  private TooltipManager tooltipManager = new TooltipManager(this);
  
  public bfj(bfb par1GuiScreen, bch par2GameSettings)
  {
    this.f = par1GuiScreen;
    this.g = par2GameSettings;
  }
  
  public void b()
  {
    this.a = bwo.a("options.videoTitle", new Object[0]);
    this.n.clear();
    for (int i = 0; i < i.length; i++)
    {
      bch.a opt = i[i];
      if (opt != null)
      {
        int x = this.l / 2 - 155 + i % 2 * 160;
        int y = this.m / 6 + 21 * (i / 2) - 12;
        if (opt.a()) {
          this.n.add(new GuiOptionSliderOF(opt.c(), x, y, opt));
        } else {
          this.n.add(new GuiOptionButtonOF(opt.c(), x, y, opt, this.g.c(opt)));
        }
      }
    }
    int y = this.m / 6 + 21 * (i.length / 2) - 12;
    int x = 0;
    
    x = this.l / 2 - 155 + 0;
    this.n.add(new bdm(231, x, y, Lang.get("of.options.shaders")));
    
    x = this.l / 2 - 155 + 160;
    this.n.add(new bdm(202, x, y, Lang.get("of.options.quality")));
    
    y += 21;
    
    x = this.l / 2 - 155 + 0;
    this.n.add(new bdm(201, x, y, Lang.get("of.options.details")));
    
    x = this.l / 2 - 155 + 160;
    this.n.add(new bdm(212, x, y, Lang.get("of.options.performance")));
    
    y += 21;
    
    x = this.l / 2 - 155 + 0;
    this.n.add(new bdm(211, x, y, Lang.get("of.options.animations")));
    
    x = this.l / 2 - 155 + 160;
    this.n.add(new bdm(222, x, y, Lang.get("of.options.other")));
    
    y += 21;
    
    this.n.add(new bcz(200, this.l / 2 - 100, this.m / 6 + 168 + 11, bwo.a("gui.done", new Object[0])));
  }
  
  protected void a(bcz button)
  {
    if (!button.l) {
      return;
    }
    int guiScale = this.g.az;
    if ((button.k < 200) && ((button instanceof bdm)))
    {
      this.g.a(((bdm)button).c(), 1);
      button.j = this.g.c(bch.a.a(button.k));
    }
    if (button.k == 200)
    {
      this.j.u.b();
      this.j.a(this.f);
    }
    if (this.g.az != guiScale)
    {
      bcx var3 = new bcx(this.j);
      int var4 = var3.a();
      int var5 = var3.b();
      a(this.j, var4, var5);
    }
    if (button.k == 201)
    {
      this.j.u.b();
      GuiDetailSettingsOF scr = new GuiDetailSettingsOF(this, this.g);
      this.j.a(scr);
    }
    if (button.k == 202)
    {
      this.j.u.b();
      GuiQualitySettingsOF scr = new GuiQualitySettingsOF(this, this.g);
      this.j.a(scr);
    }
    if (button.k == 211)
    {
      this.j.u.b();
      GuiAnimationSettingsOF scr = new GuiAnimationSettingsOF(this, this.g);
      this.j.a(scr);
    }
    if (button.k == 212)
    {
      this.j.u.b();
      GuiPerformanceSettingsOF scr = new GuiPerformanceSettingsOF(this, this.g);
      this.j.a(scr);
    }
    if (button.k == 222)
    {
      this.j.u.b();
      GuiOtherSettingsOF scr = new GuiOtherSettingsOF(this, this.g);
      this.j.a(scr);
    }
    if (button.k == 231)
    {
      if ((Config.isAntialiasing()) || (Config.isAntialiasingConfigured()))
      {
        Config.showGuiMessage(Lang.get("of.message.shaders.aa1"), Lang.get("of.message.shaders.aa2"));
        return;
      }
      if (Config.isAnisotropicFiltering())
      {
        Config.showGuiMessage(Lang.get("of.message.shaders.af1"), Lang.get("of.message.shaders.af2"));
        return;
      }
      if (Config.isFastRender())
      {
        Config.showGuiMessage(Lang.get("of.message.shaders.fr1"), Lang.get("of.message.shaders.fr2"));
        return;
      }
      this.j.u.b();
      GuiShaders scr = new GuiShaders(this, this.g);
      this.j.a(scr);
    }
  }
  
  public void a(int x, int y, float z)
  {
    c();
    a(this.q, this.a, this.l / 2, 15, 16777215);
    
    String ver = Config.getVersion();
    String ed = "HD_U";
    if (ed.equals("HD")) {
      ver = "OptiFine HD A0_pre";
    }
    if (ed.equals("HD_U")) {
      ver = "OptiFine HD A0_pre Ultra";
    }
    if (ed.equals("L")) {
      ver = "OptiFine A0_pre Light";
    }
    c(this.q, ver, 2, this.m - 10, 8421504);
    
    String verMc = "Minecraft 1.9";
    int lenMc = this.q.a(verMc);
    c(this.q, verMc, this.l - lenMc - 2, this.m - 10, 8421504);
    
    super.a(x, y, z);
    
    this.tooltipManager.drawTooltips(x, y, this.n);
  }
  
  public static int getButtonWidth(bcz btn)
  {
    return btn.f;
  }
  
  public static int getButtonHeight(bcz btn)
  {
    return btn.g;
  }
  
  public static void drawGradientRect(bfb guiScreen, int left, int top, int right, int bottom, int startColor, int endColor)
  {
    guiScreen.a(left, top, right, bottom, startColor, endColor);
  }
}
