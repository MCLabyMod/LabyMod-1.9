import java.util.ArrayList;
import java.util.List;

public class TooltipManager
{
  private bfb guiScreen = null;
  private int lastMouseX = 0;
  private int lastMouseY = 0;
  private long mouseStillTime = 0L;
  
  public TooltipManager(bfb guiScreen)
  {
    this.guiScreen = guiScreen;
  }
  
  public void drawTooltips(int x, int y, List buttonList)
  {
    if ((Math.abs(x - this.lastMouseX) > 5) || (Math.abs(y - this.lastMouseY) > 5))
    {
      this.lastMouseX = x;
      this.lastMouseY = y;
      this.mouseStillTime = System.currentTimeMillis();
      return;
    }
    int activateDelay = 700;
    if (System.currentTimeMillis() < this.mouseStillTime + activateDelay) {
      return;
    }
    int x1 = this.guiScreen.l / 2 - 150;
    int y1 = this.guiScreen.m / 6 - 7;
    if (y <= y1 + 98) {
      y1 += 105;
    }
    int x2 = x1 + 150 + 150;
    int y2 = y1 + 84 + 10;
    
    bcz btn = getSelectedButton(x, y, buttonList);
    if ((btn instanceof IOptionControl))
    {
      IOptionControl ctl = (IOptionControl)btn;
      bch.a option = ctl.getOption();
      String[] lines = getTooltipLines(option);
      if (lines == null) {
        return;
      }
      bfj.a(x1, y1, x2, y2, -536870912);
      for (int i = 0; i < lines.length; i++)
      {
        String line = lines[i];
        int col = 14540253;
        if (line.endsWith("!")) {
          col = 16719904;
        }
        bct fontRenderer = bcf.z().k;
        fontRenderer.a(line, x1 + 5, y1 + 5 + i * 11, col);
      }
    }
  }
  
  private bcz getSelectedButton(int x, int y, List buttonList)
  {
    for (int k = 0; k < buttonList.size(); k++)
    {
      bcz btn = (bcz)buttonList.get(k);
      int btnWidth = bfj.getButtonWidth(btn);
      int btnHeight = bfj.getButtonHeight(btn);
      boolean flag = (x >= btn.h) && (y >= btn.i) && (x < btn.h + btnWidth) && (y < btn.i + btnHeight);
      if (flag) {
        return btn;
      }
    }
    return null;
  }
  
  private static String[] getTooltipLines(bch.a option)
  {
    return getTooltipLines(option.d());
  }
  
  private static String[] getTooltipLines(String key)
  {
    List<String> list = new ArrayList();
    for (int i = 0; i < 10; i++)
    {
      String lineKey = key + ".tooltip." + (i + 1);
      String line = Lang.get(lineKey, null);
      if (line == null) {
        break;
      }
      list.add(line);
    }
    if (list.size() <= 0) {
      return null;
    }
    String[] lines = (String[])list.toArray(new String[list.size()]);
    
    return lines;
  }
}
