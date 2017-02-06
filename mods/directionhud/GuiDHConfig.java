package mods.directionhud;

import bcf;
import bct;
import bcz;
import bfb;
import de.labystudio.modapi.ModAPI;
import java.util.List;

public class GuiDHConfig
  extends bfb
{
  private bcz btnEnable;
  private bcz btnShowWhileChat;
  private bcz btnPositionMode;
  private bcz btnMarkerColor;
  private bcz btnSaveSettings;
  private GuiSlideControl sliderCustomX;
  private GuiSlideControl sliderCustomY;
  private bcz lastPressed;
  private int headerPos;
  private int footerPos;
  private byte byte0 = -16;
  
  public void b()
  {
    this.n.clear();
    
    this.headerPos = (this.m / 4 - 52);
    this.footerPos = (this.m - 29);
    
    this.btnEnable = new bcz(1, this.l / 2 - 98, getRowPos(1), 60, 20, String.valueOf(DirectionHUD.optionEnable));
    this.btnShowWhileChat = new bcz(2, this.l / 2 + 102, getRowPos(1), 60, 20, String.valueOf(DirectionHUD.showWhileChat));
    this.btnMarkerColor = new bcz(3, this.l / 2 + 2, getRowPos(2), 60, 20, DirectionHUD.optionMarkerColor);
    this.btnPositionMode = new bcz(133, this.l / 2 + 2, getRowPos(3), 60, 20, DirectionHUD.optionPositionMode);
    
    this.sliderCustomX = new GuiSlideControl(50, this.l / 2 + 2, getRowPos(4), 150, 20, "X Pos: ", 1.0F, this.l - 25, DirectionHUD.optionCustomX, true);
    this.sliderCustomY = new GuiSlideControl(60, this.l / 2 + 2, getRowPos(5), 150, 20, "Y Pos: ", 1.0F, this.m - 8, DirectionHUD.optionCustomY, true);
    
    this.btnSaveSettings = new bcz(100, this.l / 2 - 30, this.footerPos, 60, 20, "Done");
    
    this.n.add(this.btnEnable);
    this.n.add(this.btnShowWhileChat);
    this.n.add(this.btnMarkerColor);
    this.n.add(this.btnPositionMode);
    if (DirectionHUD.optionPositionMode.equals("CUSTOM"))
    {
      this.n.add(this.sliderCustomX);
      this.n.add(this.sliderCustomY);
    }
    this.n.add(this.btnSaveSettings);
  }
  
  public int getRowPos(int rowNumber)
  {
    return rowNumber > 3 ? this.m / 4 + 0 + (24 * (rowNumber - (DirectionHUD.optionPositionMode.equals("CUSTOM") ? 0 : 2)) - 24) + this.byte0 : this.m / 4 + 0 + (24 * rowNumber - 24) + this.byte0;
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
  {
    if (mouseButton == 0) {
      for (int l = 0; l < this.n.size(); l++)
      {
        bcz guibutton = (bcz)this.n.get(l);
        if (guibutton.c(this.j, mouseX, mouseY))
        {
          this.lastPressed = guibutton;
          a(guibutton);
        }
      }
    }
  }
  
  protected void b(int mouseX, int mouseY, int which)
  {
    if ((this.lastPressed != null) && (which == 0))
    {
      this.lastPressed.a(mouseX, mouseY);
      
      actionPerformed_MouseUp(this.lastPressed);
      
      this.lastPressed = null;
    }
  }
  
  protected void actionPerformed_MouseUp(bcz button)
  {
    if ((button instanceof GuiSlideControl)) {
      a(button);
    }
  }
  
  protected void a(bcz button)
  {
    switch (button.k)
    {
    case 1: 
      DirectionHUD.optionEnable = !DirectionHUD.optionEnable;
      this.btnEnable.j = String.valueOf(DirectionHUD.optionEnable);
      break;
    case 2: 
      DirectionHUD.showWhileChat = !DirectionHUD.showWhileChat;
      this.btnShowWhileChat.j = String.valueOf(DirectionHUD.showWhileChat);
      break;
    case 3: 
      DirectionHUD.optionMarkerColor = DirectionHUD.getNextColor(DirectionHUD.optionMarkerColor);
      this.btnMarkerColor.j = DirectionHUD.optionMarkerColor;
      break;
    case 133: 
      String nextMode = PositionMode.getNext(DirectionHUD.optionPositionMode).name();
      DirectionHUD.optionPositionMode = nextMode;
      this.btnPositionMode.j = nextMode;
      b();
      break;
    case 50: 
      DirectionHUD.optionCustomX = this.sliderCustomX.GetValueAsInt();
      break;
    case 60: 
      DirectionHUD.optionCustomY = this.sliderCustomY.GetValueAsInt();
      break;
    case 100: 
      this.j.a(ModAPI.getLastScreen());
    }
    saveAll();
  }
  
  private void saveAll() {}
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    String lblEnable = "Enable";
    String lblShowInChat = "Show while chat";
    String lblMarkerColor = "Marker-Color";
    String lblCustomX = "Custom X Position";
    String lblCustomY = "Custom Y Position";
    String lblPositionMode = "Position-Mode";
    
    c();
    
    a(this.q, "DirectionHUD Settings", this.l / 2, this.headerPos, 16777215);
    
    c(this.q, lblEnable, this.l / 2 - 100 - this.q.a(lblEnable), getRowPos(1) + 6, 16777215);
    c(this.q, lblShowInChat, this.l / 2 + 100 - this.q.a(lblShowInChat), getRowPos(1) + 6, 16777215);
    c(this.q, lblMarkerColor, this.l / 2 - 3 - this.q.a(lblMarkerColor), getRowPos(2) + 6, 16777215);
    c(this.q, lblPositionMode, this.l / 2 - 3 - this.q.a(lblPositionMode), getRowPos(3) + 6, 16777215);
    if (DirectionHUD.optionPositionMode.equals("CUSTOM"))
    {
      c(this.q, lblCustomX, this.l / 2 - 3 - this.q.a(lblCustomX), getRowPos(4) + 6, 16777215);
      c(this.q, lblCustomY, this.l / 2 - 3 - this.q.a(lblCustomY), getRowPos(5) + 6, 16777215);
    }
    super.a(mouseX, mouseY, partialTicks);
  }
}
