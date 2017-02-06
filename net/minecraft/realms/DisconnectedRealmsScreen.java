package net.minecraft.realms;

import eu;
import java.util.List;

public class DisconnectedRealmsScreen
  extends RealmsScreen
{
  private String title;
  private eu reason;
  private List<String> lines;
  private final RealmsScreen parent;
  private int textHeight;
  
  public DisconnectedRealmsScreen(RealmsScreen ☃, String ☃, eu ☃)
  {
    this.parent = ☃;
    this.title = getLocalizedString(☃);
    this.reason = ☃;
  }
  
  public void init()
  {
    Realms.setConnectedToRealms(false);
    Realms.clearResourcePack();
    
    buttonsClear();
    
    this.lines = fontSplit(this.reason.d(), width() - 50);
    this.textHeight = (this.lines.size() * fontLineHeight());
    
    buttonsAdd(newButton(0, width() / 2 - 100, height() / 2 + this.textHeight / 2 + fontLineHeight(), getLocalizedString("gui.back")));
  }
  
  public void keyPressed(char ☃, int ☃)
  {
    if (☃ == 1) {
      Realms.setScreen(this.parent);
    }
  }
  
  public void buttonClicked(RealmsButton ☃)
  {
    if (☃.id() == 0) {
      Realms.setScreen(this.parent);
    }
  }
  
  public void render(int ☃, int ☃, float ☃)
  {
    renderBackground();
    
    drawCenteredString(this.title, width() / 2, height() / 2 - this.textHeight / 2 - fontLineHeight() * 2, 11184810);
    
    int ☃ = height() / 2 - this.textHeight / 2;
    if (this.lines != null) {
      for (String ☃ : this.lines)
      {
        drawCenteredString(☃, width() / 2, ☃, 16777215);
        ☃ += fontLineHeight();
      }
    }
    super.render(☃, ☃, ☃);
  }
}
