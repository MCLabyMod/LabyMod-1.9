package shadersmod.client;

import Config;
import Lang;
import StrUtils;
import bcf;
import bch;
import bct;
import bcz;
import bfb;
import bfj;
import bwo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GuiShaderOptions
  extends bfb
{
  private bfb prevScreen;
  protected String title;
  private bch settings;
  private int lastMouseX = 0;
  private int lastMouseY = 0;
  private long mouseStillTime = 0L;
  private String screenName = null;
  private String screenText = null;
  private boolean changed = false;
  public static final String OPTION_PROFILE = "<profile>";
  public static final String OPTION_EMPTY = "<empty>";
  public static final String OPTION_REST = "*";
  
  public GuiShaderOptions(bfb guiscreen, bch gamesettings)
  {
    this.title = "Shader Options";
    this.prevScreen = guiscreen;
    this.settings = gamesettings;
  }
  
  public GuiShaderOptions(bfb guiscreen, bch gamesettings, String screenName)
  {
    this(guiscreen, gamesettings);
    this.screenName = screenName;
    if (screenName != null) {
      this.screenText = Shaders.translate("screen." + screenName, screenName);
    }
  }
  
  public void b()
  {
    int baseId = 100;
    
    int baseX = 0;
    int baseY = 30;
    int stepY = 20;
    int btnX = this.l - 130;
    int btnWidth = 120;
    int btnHeight = 20;
    int columns = 2;
    
    ShaderOption[] ops = Shaders.getShaderPackOptions(this.screenName);
    if (ops != null)
    {
      if (ops.length > 18) {
        columns = ops.length / 9 + 1;
      }
      for (int i = 0; i < ops.length; i++)
      {
        ShaderOption so = ops[i];
        if (so != null) {
          if (so.isVisible())
          {
            int col = i % columns;
            int row = i / columns;
            int colWidth = Math.min(this.l / columns, 200);
            baseX = (this.l - colWidth * columns) / 2;
            int x = col * colWidth + 5 + baseX;
            int y = baseY + row * stepY;
            int w = colWidth - 10;
            int h = btnHeight;
            String text = getButtonText(so, w);
            GuiButtonShaderOption btn = new GuiButtonShaderOption(baseId + i, x, y, w, h, so, text);
            btn.l = so.isEnabled();
            this.n.add(btn);
          }
        }
      }
    }
    this.n.add(new bcz(201, this.l / 2 - btnWidth - 20, this.m / 6 + 168 + 11, btnWidth, btnHeight, bwo.a("controls.reset", new Object[0])));
    
    this.n.add(new bcz(200, this.l / 2 + 20, this.m / 6 + 168 + 11, btnWidth, btnHeight, bwo.a("gui.done", new Object[0])));
  }
  
  private String getButtonText(ShaderOption so, int btnWidth)
  {
    String labelName = so.getNameText();
    if ((so instanceof ShaderOptionScreen))
    {
      ShaderOptionScreen soScr = (ShaderOptionScreen)so;
      return labelName + "...";
    }
    bct fr = Config.getMinecraft().k;
    
    int lenSuffix = fr.a(": " + Lang.getOff()) + 5;
    while ((fr.a(labelName) + lenSuffix >= btnWidth) && (labelName.length() > 0)) {
      labelName = labelName.substring(0, labelName.length() - 1);
    }
    String col = so.isChanged() ? so.getValueColor(so.getValue()) : "";
    String labelValue = so.getValueText(so.getValue());
    return labelName + ": " + col + labelValue;
  }
  
  protected void a(bcz guibutton)
  {
    if (!guibutton.l) {
      return;
    }
    if ((guibutton.k < 200) && ((guibutton instanceof GuiButtonShaderOption)))
    {
      GuiButtonShaderOption btnSo = (GuiButtonShaderOption)guibutton;
      ShaderOption so = btnSo.getShaderOption();
      if ((so instanceof ShaderOptionScreen))
      {
        String screenName = so.getName();
        GuiShaderOptions scr = new GuiShaderOptions(this, this.settings, screenName);
        this.j.a(scr);
        return;
      }
      so.nextValue();
      
      updateAllButtons();
      
      this.changed = true;
    }
    if (guibutton.k == 201)
    {
      ShaderOption[] opts = Shaders.getChangedOptions(Shaders.getShaderPackOptions());
      for (int i = 0; i < opts.length; i++)
      {
        ShaderOption opt = opts[i];
        
        opt.resetValue();
        
        this.changed = true;
      }
      updateAllButtons();
    }
    if (guibutton.k == 200)
    {
      if (this.changed)
      {
        Shaders.saveShaderPackOptions();
        
        Shaders.uninit();
      }
      this.j.a(this.prevScreen);
    }
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    super.a(mouseX, mouseY, mouseButton);
    if (mouseButton == 1)
    {
      bcz btn = getSelectedButton(mouseX, mouseY);
      if ((btn instanceof GuiButtonShaderOption))
      {
        GuiButtonShaderOption btnSo = (GuiButtonShaderOption)btn;
        
        ShaderOption so = btnSo.getShaderOption();
        if (so.isChanged())
        {
          btnSo.a(this.j.U());
          
          so.resetValue();
          
          this.changed = true;
          
          updateAllButtons();
        }
      }
    }
  }
  
  private void updateAllButtons()
  {
    for (Iterator it = this.n.iterator(); it.hasNext();)
    {
      bcz btn = (bcz)it.next();
      if ((btn instanceof GuiButtonShaderOption))
      {
        GuiButtonShaderOption gbso = (GuiButtonShaderOption)btn;
        ShaderOption opt = gbso.getShaderOption();
        if ((opt instanceof ShaderOptionProfile))
        {
          ShaderOptionProfile optProf = (ShaderOptionProfile)opt;
          optProf.updateProfile();
        }
        gbso.j = getButtonText(opt, gbso.b());
      }
    }
  }
  
  public void a(int x, int y, float f)
  {
    c();
    if (this.screenText != null) {
      a(this.q, this.screenText, this.l / 2, 15, 16777215);
    } else {
      a(this.q, this.title, this.l / 2, 15, 16777215);
    }
    super.a(x, y, f);
    if ((Math.abs(x - this.lastMouseX) > 5) || (Math.abs(y - this.lastMouseY) > 5))
    {
      this.lastMouseX = x;
      this.lastMouseY = y;
      this.mouseStillTime = System.currentTimeMillis();
      return;
    }
    drawTooltips(x, y, this.n);
  }
  
  private void drawTooltips(int x, int y, List buttonList)
  {
    int activateDelay = 700;
    if (System.currentTimeMillis() < this.mouseStillTime + activateDelay) {
      return;
    }
    int x1 = this.l / 2 - 150;
    int y1 = this.m / 6 - 7;
    if (y <= y1 + 98) {
      y1 += 105;
    }
    int x2 = x1 + 150 + 150;
    int y2 = y1 + 84 + 10;
    
    bcz btn = getSelectedButton(x, y);
    if ((btn instanceof GuiButtonShaderOption))
    {
      GuiButtonShaderOption btnSo = (GuiButtonShaderOption)btn;
      ShaderOption so = btnSo.getShaderOption();
      String[] lines = makeTooltipLines(so, x2 - x1);
      if (lines == null) {
        return;
      }
      a(x1, y1, x2, y2, -536870912, -536870912);
      for (int i = 0; i < lines.length; i++)
      {
        String line = lines[i];
        int col = 14540253;
        if (line.endsWith("!")) {
          col = 16719904;
        }
        this.q.a(line, x1 + 5, y1 + 5 + i * 11, col);
      }
    }
  }
  
  private String[] makeTooltipLines(ShaderOption so, int width)
  {
    if ((so instanceof ShaderOptionProfile)) {
      return null;
    }
    String name = so.getNameText();
    
    String desc = Config.normalize(so.getDescriptionText()).trim();
    String[] descs = splitDescription(desc);
    
    String id = null;
    if (!name.equals(so.getName())) {
      id = Lang.get("of.general.id") + ": " + so.getName();
    }
    String source = null;
    if (so.getPaths() != null) {
      source = Lang.get("of.general.from") + ": " + Config.arrayToString(so.getPaths());
    }
    String def = null;
    if (so.getValueDefault() != null)
    {
      String defVal = so.isEnabled() ? so.getValueText(so.getValueDefault()) : Lang.get("of.general.ambiguous");
      def = Lang.getDefault() + ": " + defVal;
    }
    List<String> list = new ArrayList();
    list.add(name);
    list.addAll(Arrays.asList(descs));
    if (id != null) {
      list.add(id);
    }
    if (source != null) {
      list.add(source);
    }
    if (def != null) {
      list.add(def);
    }
    String[] lines = makeTooltipLines(width, list);
    
    return lines;
  }
  
  private String[] splitDescription(String desc)
  {
    if (desc.length() <= 0) {
      return new String[0];
    }
    desc = StrUtils.removePrefix(desc, "//");
    
    String[] descs = desc.split("\\. ");
    for (int i = 0; i < descs.length; i++)
    {
      descs[i] = ("- " + descs[i].trim());
      descs[i] = StrUtils.removeSuffix(descs[i], ".");
    }
    return descs;
  }
  
  private String[] makeTooltipLines(int width, List<String> args)
  {
    bct fr = Config.getMinecraft().k;
    List<String> list = new ArrayList();
    Iterator it;
    for (int i = 0; i < args.size(); i++)
    {
      String arg = (String)args.get(i);
      if ((arg != null) && (arg.length() > 0))
      {
        List<String> parts = fr.c(arg, width);
        for (it = parts.iterator(); it.hasNext();)
        {
          String part = (String)it.next();
          list.add(part);
        }
      }
    }
    String[] lines = (String[])list.toArray(new String[list.size()]);
    
    return lines;
  }
  
  private bcz getSelectedButton(int x, int y)
  {
    for (int i = 0; i < this.n.size(); i++)
    {
      bcz btn = (bcz)this.n.get(i);
      
      int btnWidth = bfj.getButtonWidth(btn);
      int btnHeight = bfj.getButtonHeight(btn);
      if ((x >= btn.h) && (y >= btn.i) && (x < btn.h + btnWidth) && (y < btn.i + btnHeight)) {
        return btn;
      }
    }
    return null;
  }
}
